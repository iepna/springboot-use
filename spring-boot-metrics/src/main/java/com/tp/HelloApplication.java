package com.tp;

import com.codahale.metrics.ScheduledReporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(HelloApplication.class, args);

		//启动reporter
		ScheduledReporter reporter = ctx.getBean(ScheduledReporter.class);
		reporter.start(1, TimeUnit.SECONDS);
	}
}
