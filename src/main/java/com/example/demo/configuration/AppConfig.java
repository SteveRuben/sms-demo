package com.example.demo.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@Validated
@ConfigurationProperties(prefix = "sms")
public class AppConfig {

    public enum MethodTypeEnum {
        GET("GET"), POST("POST");

        private String code;
        MethodTypeEnum(String code) {
            this.code = code;
        }
    }

    @NotNull
    private String url;
    @NotNull
    private MethodTypeEnum type;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull
    private Integer connectTimeout;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MethodTypeEnum getType() {
        return type;
    }

    public void setType(MethodTypeEnum type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", connectTimeout=" + connectTimeout +
                '}';
    }
}
