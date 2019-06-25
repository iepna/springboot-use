package com.tp.controller;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {
    @Autowired
    private Meter requestMeter;

    @Autowired
    private Histogram responseSizes;

    @Autowired
    private Counter pendingJobs;

    @Autowired
    private Timer responses;

    /*@Autowired
    private ListManager listManager;*/

    @RequestMapping("/")
    public String index() {
        requestMeter.mark();
        pendingJobs.inc();
        responseSizes.update(new Random().nextInt(10));
        final Timer.Context context = responses.time();
        try {
            return "Hello Spring Boot 2.0!";
        }finally {
            context.stop();
        }
    }
}