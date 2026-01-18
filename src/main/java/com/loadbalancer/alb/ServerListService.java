package com.loadbalancer.alb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@Service
public class ServerListService {


    public final Queue<String> queue;


    ServerListService() {
        this.queue = new LinkedList<>(
                List.of(
                        "http://localhost:8081/",
                        "http://localhost:8082/"
                )
        );
    }

    public String serverGet() {
        return queue.poll();
    }
    public void addServer(String server) {
        queue.offer(server);
    }

}
