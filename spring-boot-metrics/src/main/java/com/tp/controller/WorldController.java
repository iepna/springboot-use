package com.tp.controller;

import com.codahale.metrics.Timer;
import com.tp.config.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class WorldController {
    @Autowired
    private MetricService metricService;

    /*@Autowired
    private ListManager listManager;*/

    @RequestMapping("/world")
    public String index() {
        metricService.getMeters("world_meter").mark();
        metricService.getCounter("world_counter").inc();
        metricService.getHistogram("world_histogram").update(new Random().nextInt(10));
        final Timer.Context context = metricService.getTimer("world_time").time();
        try {
            return "Hello Spring Boot 2.0!";
        }finally {
            context.stop();
        }
    }
}