package com.tp.service.impl;

import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.dao.JobAndTriggerDao;
import com.tp.dto.JobAndTriggerDto;
import com.tp.dto.PageResult;
import com.tp.service.IJobAndTriggerService;
import com.tp.service.job.BaseJob;
import javafx.scene.control.Pagination;

/**  
* @ClassName: JobAndTriggerImpl  
* @Description: TODO()  
* @author lixin(1309244704@qq.com)  
* @date 2018骞�3鏈�15鏃� 涓婂崍10:03:00  
* @version V1.0  
*/ 
@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {
	
	@Autowired
	private Scheduler scheduler;
	
	
	@Autowired
	// 鍔犲叆Qulifier娉ㄨВ锛岄�氳繃鍚嶇О娉ㄥ叆bean
	//	@Qualifier("Scheduler")
	private JobAndTriggerDao jobAndTriggerDao;
	

	public PageResult<JobAndTriggerDto> getPageJob(PageResult search) {
		List<JobAndTriggerDto> records = jobAndTriggerDao.getJobAndTriggerDetails(search);
		search.setList(records);
		Integer count = jobAndTriggerDao.getJobAndTriggerDetailsCount(search);
		search.setTotalRows(count);
		return search;
	}

	@Override
	public JobAndTriggerDto getPageJobmod() {
		return jobAndTriggerDao.getJobAndTriggerDto();
	}

	@Override
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
		// 鍚姩璋冨害鍣�
				scheduler.start();

				// 鏋勫缓job淇℃伅
				JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
						.withIdentity(jobClassName, jobGroupName).build();

				// 琛ㄨ揪寮忚皟搴︽瀯寤哄櫒(鍗充换鍔℃墽琛岀殑鏃堕棿)
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

				// 鎸夋柊鐨刢ronExpression琛ㄨ揪寮忔瀯寤轰竴涓柊鐨則rigger
				CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
						.withSchedule(scheduleBuilder).build();

				try {
					scheduler.scheduleJob(jobDetail, trigger);
					System.out.println("鍒涘缓瀹氭椂浠诲姟鎴愬姛");

				} catch (SchedulerException e) {
					System.out.println("鍒涘缓瀹氭椂浠诲姟澶辫触" + e);
					throw new Exception("鍒涘缓瀹氭椂浠诲姟澶辫触");
				}
		
	}

	@Override
	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
			// 琛ㄨ揪寮忚皟搴︽瀯寤哄櫒锛堝姩鎬佷慨鏀瑰悗涓嶇珛鍗虫墽琛岋級
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 鎸夋柊鐨刢ronExpression琛ㄨ揪寮忛噸鏂版瀯寤簍rigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 鎸夋柊鐨則rigger閲嶆柊璁剧疆job鎵ц
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			System.out.println("鏇存柊瀹氭椂浠诲姟澶辫触" + e);
			throw new Exception("鏇存柊瀹氭椂浠诲姟澶辫触");
		}
	}

	@Override
	public void deleteJob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void pauseJob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void resumejob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
	}
	
	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}
	
}