package com.tp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		new Thread(()->{
			for (int i=0;i<100;i++){
				logger.info("---test---"+i);
				try {
					Thread.sleep(3000);
				}catch (Exception e){
					logger.info("chucuo");
				}
			}
		}).start();
		SpringApplication.run(Application.class, args);
	}
}
