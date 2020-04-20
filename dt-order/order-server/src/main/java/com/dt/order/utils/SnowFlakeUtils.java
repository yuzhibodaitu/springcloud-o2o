package com.dt.order.utils;

/**
 * SnowFlake算法的优点：
 *         1.生成ID时不依赖于DB，完全在内存生成，高性能高可用。
 *         2.ID呈趋势递增，后续插入索引树的时候性能较好。
 *
 * SnowFlake算法的缺点：
 *         依赖于系统时钟的一致性。如果某台机器的系统时钟回拨，有可能造成ID冲突，或者ID乱序。
 * 应用场景 
 * 1、数据库表主键：很多DBA在大型生产应用禁用auto_increment的ID，这时可以选snowflake替代。 
 * 2、TraceId：分布式系统追踪，希望用一个ID贯穿所有子系统来追踪分布式交互过程。也有系统产生一个Exception，我们需要对Exception编号等。 
 * 3、摇一摇/抢红包ID：摇一摇的特点是活动促销的时候，短时间内访问特别大，需要一个高性能的ID生成器。
 *
 * 参考文档：https://blog.csdn.net/qq_36095679/article/details/90677138
 * SnowFlake所生成的ID一共分成四部分：
 * 1.第一位
 *     占用1bit，其值始终是0，没有实际作用。
 *
 * 2.时间戳
 *     占用41bit，精确到毫秒，一般实现上不会存储当前的时间戳，而是时间戳的差值（当前时间-固定的开始时间），这样可以使产生的ID从更小值开始；41位的时间戳可以使用69年，(1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69年
 *
 * 3.工作机器id
 *     占用10bit，其中高位5bit是数据中心ID（datacenterId），低位5bit是工作节点ID（workerId），做多可以容纳1024个节点。
 *
 * 4.序列号
 *     占用12bit，这个值在同一毫秒同一节点上从0开始不断累加，最多可以累加到4095。
 *
 * SnowFlake算法在同一毫秒内最多可以生成多少个全局唯一ID呢？只需要做一个简单的乘法：
 *     同一毫秒的ID数量 = 1024 X 4096 = 4194304
 *     这个数字在绝大多数并发场景下都是够用的。
 *
 */
//使用snowFlake算法生成分布式唯一id
public class SnowFlakeUtils {

    // 起始的时间戳
    private final static long START_STMP = 1480166465631L;
    // 每一部分占用的位数，就三个
    private final static long SEQUENCE_BIT = 12;// 序列号占用的位数
    private final static long MACHINE_BIT = 5; // 机器标识占用的位数
    private final static long DATACENTER_BIT = 5;// 数据中心占用的位数
    // 每一部分最大值
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
    // 每一部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    private long datacenterId; // 数据中心
    private long machineId; // 机器标识
    private long sequence = 0L; // 序列号
    private long lastStmp = -1L;// 上一次时间戳

    public SnowFlakeUtils(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    //产生下一个ID
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过  这个时候应当抛出异常
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //if条件里表示当前调用和上一次调用落在了相同毫秒内，只能通过第三部分，序列号自增来判断为唯一，所以+1.
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大，只能等待下一个毫秒
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            //不同毫秒内，序列号置为0
            //执行到这个分支的前提是currTimestamp > lastTimestamp，说明本次调用跟上次调用对比，已经不再同一个毫秒内了，这个时候序号可以重新回置0了。
            sequence = 0L;
        }

        lastStmp = currStmp;
        //就是用相对毫秒数、机器ID和自增序号拼接
        //移位  并通过  或运算拼到一起组成64位的ID
        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT      //数据中心部分
                | machineId << MACHINE_LEFT            //机器标识部分
                | sequence;                            //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        //使用while循环等待直到下一毫秒。
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }
}

