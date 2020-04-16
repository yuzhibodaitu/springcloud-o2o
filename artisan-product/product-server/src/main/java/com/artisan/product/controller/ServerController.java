package com.artisan.product.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tyler.yan
 */
@RestController
@RequestMapping("/product")
public class ServerController {

    @GetMapping("/serverMsg")
    public String showInfo(){
        return "info from product server";
    }
}
