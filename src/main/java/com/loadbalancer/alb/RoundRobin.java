package com.loadbalancer.alb;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


@Service
public class RoundRobin implements ServerInterface {

    private String service;
    public final Queue<String> queue;


    RoundRobin(AlbProperties properties) {
        this.queue = new ConcurrentLinkedQueue<>(properties.getServers());
    }

    public String Service() {
        service = queue.poll();
        queue.offer(service);
        return service;
    }

}
