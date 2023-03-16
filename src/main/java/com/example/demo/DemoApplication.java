package com.example.demo;

import com.example.demo.configuration.AppConfig;
import com.example.demo.model.SmsObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Autowired
    AppConfig appConfig;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("")
    public String index(){
        return "Trash App for Sending bulk sms";
    }

    @GetMapping("config")
    public String config(){
        return appConfig.toString();
    }

    @PostMapping("")
    public String sendSms(@RequestBody @Validated SmsObject object){
        RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(appConfig.getConnectTimeout()))
                .setReadTimeout(Duration.ofMillis(appConfig.getConnectTimeout()))
                .build();
        String url = appConfig.getUrl();
        for (String phone: object.getPhones()) {
            url = MessageFormat.format(url, appConfig.getUsername(), appConfig.getPassword(), true, phone, object.getMessage());
            System.out.println("url = " + url);
            ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
            System.out.println("result = " + result);
            url = appConfig.getUrl();
        }
        return "Sent but new don't know the status";
    }
}
