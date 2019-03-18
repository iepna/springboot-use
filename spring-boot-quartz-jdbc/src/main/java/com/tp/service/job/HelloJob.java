package com.tp.service.job;

import java.util.Date;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;   
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
  
/**  
* @ClassName: HelloJob  
* @Description: TODO()  
* @author lixin(1309244704@qq.com)  
* @date 2018�?3�?15�? 上午10:02:16  
* @version V1.0  
*/ 
public class HelloJob implements BaseJob {  
  
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);  
     
    public HelloJob() {  
          
    }  
     
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
        _log.error("Hello Job执行时间: " + new Date());  
        System.err.println("Hello Job执行时间: " + new Date());
    }  
}  
