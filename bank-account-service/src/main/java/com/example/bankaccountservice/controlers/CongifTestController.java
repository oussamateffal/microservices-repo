package com.example.bankaccountservice.controlers;

import com.example.bankaccountservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CongifTestController {

    @Autowired
    private GlobalConfig globalConfig;
    @GetMapping("/globalConfig")
    public GlobalConfig globalConfig(){

        return globalConfig;
    }
}
