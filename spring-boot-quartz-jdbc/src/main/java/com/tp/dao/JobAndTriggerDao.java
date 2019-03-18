package com.tp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tp.dto.JobAndTriggerDto;
import com.tp.dto.PageResult;


/**  
* @ClassName: JobAndTriggerMapper  
* @Description: TODO()  
* @author lixin(1309244704@qq.com)  
* @date 2018骞�3鏈�15鏃� 涓婂崍10:02:48  
* @version V1.0  
*/ 
public interface JobAndTriggerDao {
	
	List<JobAndTriggerDto> getJobAndTriggerDetails(PageResult<JobAndTriggerDto> page);
	
	Integer getJobAndTriggerDetailsCount(PageResult<JobAndTriggerDto> page);
	
	JobAndTriggerDto getJobAndTriggerDto();
}
