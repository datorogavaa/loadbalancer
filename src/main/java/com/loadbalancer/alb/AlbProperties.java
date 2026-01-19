package com.loadbalancer.alb;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "alb")
public class AlbProperties {
    private List<String> servers;

    public List<String> getServers() {
        return servers;
    }
    public void setServers(List<String> servers) {
        this.servers = servers;
    }
}
