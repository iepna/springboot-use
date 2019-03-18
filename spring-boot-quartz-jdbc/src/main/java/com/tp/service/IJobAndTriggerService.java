package com.tp.service;


import java.util.Map;

import com.tp.dto.JobAndTriggerDto;
import com.tp.dto.PageResult;



/**  
* @ClassName: IJobAndTriggerService  
* @Description: TODO(quartz鎺ュ彛)  
* @author lixin(1309244704@qq.com)  
* @date 2018骞�3鏈�15鏃� 涓婂崍10:00:07  
* @version V1.0  
*/ 
public interface IJobAndTriggerService {
	/**  
	* @Title: getPageJob  
	* @Description: TODO(鏌ヨ瀹氭椂浠诲姟锛屽垎椤�)  
	* @param @param search
	* @param @return    鍙傛暟  
	* @return Map<String,Object>    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public PageResult<JobAndTriggerDto> getPageJob(PageResult search);
	
	/**  
	* @Title: getPageJobmod  
	* @Description: TODO(鏌ヨ瀹氭椂浠诲姟)  
	* @param @return    鍙傛暟  
	* @return JobAndTriggerDto    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public JobAndTriggerDto getPageJobmod();
	
	/**  
	* @Title: addJob  
	* @Description: TODO(娣诲姞浠诲姟)  
	* @param @param jobClassName 浠诲姟璺緞鍚嶇О
	* @param @param jobGroupName 浠诲姟鍒嗙粍
	* @param @param cronExpression cron鏃堕棿瑙勫垯
	* @param @throws Exception    鍙傛暟  
	* @return void    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;
	
	/**  
	* @Title: updateJob  
	* @Description: TODO(鏇存柊瀹氭椂浠诲姟)  
	* @param @param jobClassName 浠诲姟璺緞鍚嶇О
	* @param @param jobGroupName 浠诲姟鍒嗙粍
	* @param @param cronExpression cron鏃堕棿瑙勫垯
	* @param @throws Exception    鍙傛暟  
	* @return void    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;
	
	/**  
	* @Title: deleteJob  
	* @Description: TODO(鍒犻櫎瀹氭椂浠诲姟)  
	* @param @param jobClassName 浠诲姟璺緞鍚嶇О
	* @param @param jobGroupName 浠诲姟鍒嗙粍
	* @param @throws Exception    鍙傛暟  
	* @return void    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public void deleteJob(String jobClassName, String jobGroupName) throws Exception;
	
	/**  
	* @Title: pauseJob  
	* @Description: TODO(鏆傚仠瀹氭椂浠诲姟)  
	* @param @param jobClassName 浠诲姟璺緞鍚嶇О
	* @param @param jobGroupName 浠诲姟鍒嗙粍
	* @param @throws Exception    鍙傛暟  
	* @return void    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public void pauseJob(String jobClassName, String jobGroupName) throws Exception;
	
	/**  
	* @Title: resumejob  
	* @Description: TODO(鎭㈠浠诲姟)  
	* @param @param jobClassName 浠诲姟璺緞鍚嶇О
	* @param @param jobGroupName 浠诲姟鍒嗙粍
	* @param @throws Exception    鍙傛暟  
	* @return void    杩斿洖绫诲瀷  
	* @throws  
	*/ 
	public void resumejob(String jobClassName, String jobGroupName) throws Exception;
}
