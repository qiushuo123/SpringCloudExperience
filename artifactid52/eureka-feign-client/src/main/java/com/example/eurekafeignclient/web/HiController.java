package com.example.eurekafeignclient.web;


import com.example.eurekafeignclient.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    HiService hiService;
    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "sonic",required = false)String name){
        return hiService.sayHi(name);
    }
}
