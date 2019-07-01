package com.tp.config;

import com.codahale.metrics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetricService {
    @Autowired
    private MetricRegistry metrics;

    private Map<String,Timer> timers = new HashMap<>();
    private static Map<String, Counter> counters = new HashMap();
    private static Map<String, Meter> meters = new HashMap();
    private static Map<String, Histogram> histograms = new HashMap();

    /**
     * TPS 计算器
     *
     * @return
     */
    public Meter getMeters(String name) {
        if (meters.containsKey(name)) {
            return meters.get(name);
        } else {
            Meter meter = metrics.meter(name);
            meters.put(name, meter);
            return meter;
        }
    }

    /**
     * 直方图
     *
     * @return
     */
    public Histogram getHistogram(String name) {
        if (histograms.containsKey(name)){
            return histograms.get(name);
        }else{
            Histogram histogram = metrics.histogram(name);
            histograms.put(name,histogram);
            return histogram;
        }
    }

    /**
     * 计数器
     *
     * @return
     */
    public Counter getCounter(String name) {
        if (counters.containsKey(name)) {
            return counters.get(name);
        } else {
            Counter counter = metrics.counter(name);
            counters.put(name, counter);
            return counter;
        }
    }
    /**
     * 计时器
     *
     * @return
     */
    public Timer getTimer(String name) {
        if (timers.containsKey(name)){
            return timers.get(name);
        }else{
            Timer timer = metrics.timer(name);
            timers.put(name,timer);
            return timer;
        }
    }
}
