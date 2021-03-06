package com.tp.controller;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import com.tp.config.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {
    @Autowired
    private MetricService metricService;

    /*@Autowired
    private ListManager listManager;*/

    @RequestMapping("/hello")
    public String index() {
        metricService.getMeters("hello_meter").mark();
        metricService.getCounter("hello_counter").inc();
        metricService.getHistogram("hello_histogram").update(new Random().nextInt(10));
        final Timer.Context context = metricService.getTimer("hello_time").time();
        try {
            return "Hello Spring Boot 2.0!";
        }finally {
            context.stop();
        }
    }
}