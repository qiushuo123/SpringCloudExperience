package com.example.eurekafeignclient.client;

import com.example.eurekafeignclient.client.hystrix.HiHystrix;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",configuration = FeignAutoConfiguration.class,
                fallback = HiHystrix.class)
public interface EurekaClientFeign {
    @GetMapping(value="/hi")
    String sayHiFromClientEureka(@RequestParam(value = "name")String name);
}
