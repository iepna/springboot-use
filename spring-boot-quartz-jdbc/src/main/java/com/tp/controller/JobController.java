package com.tp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.dto.PageResult;
import com.tp.dto.ResponseEntity;
import com.tp.service.IJobAndTriggerService;


/**  
* @ClassName: JobController  
* @Description: TODO()  
* @author lixin(1309244704@qq.com)  
* @date 2018骞�8鏈�15鏃� 涓婂崍10:02:00  
* @version V1.0  
*/ 
@Controller
@RequestMapping(value = "/job")
public class JobController extends BaseController {
	
	@Autowired
	private IJobAndTriggerService jobAndTriggerService;

	private static Logger log = LoggerFactory.getLogger(JobController.class);

	@RequestMapping(value = "/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public PageResult queryjob(PageResult search, HttpServletRequest request,HttpServletResponse response) {
		log.debug("queryjob");
		setHeader(response);
		search.setParams(getParams(request));
		return jobAndTriggerService.getPageJob(search);
	}
	
	/**
	 * @Title: addJob
	 * @Description: TODO(娣诲姞Job)
	 * @param jobClassName
	 *            绫诲悕
	 * @param jobGroupName
	 *            缁勫悕
	 * @param cronExpression
	 *            琛ㄨ揪寮忥紝濡傦細0/5 * * * * ? (姣忛殧5绉�)
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName, 
			@RequestParam(value = "cronExpression") String cronExpression,
			HttpServletResponse response){
		try {
			setHeader(response);
			jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
			return ResponseEntity.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.FAILURE(e.getMessage());
		}
	}

	/**
	 * @Title: pauseJob
	 * @Description: TODO(鏆傚仠Job)
	 * @param jobClassName
	 *            绫诲悕
	 * @param jobGroupName
	 *            缁勫悕
	 */
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity pauseJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName,
			HttpServletResponse response) {
		try {
			setHeader(response);
			jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
			return ResponseEntity.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.FAILURE(e.getMessage());
		}
	}

	/**
	 * @Title: resumeJob
	 * @Description: TODO(鎭㈠Job)
	 * @param jobClassName
	 *            绫诲悕
	 * @param jobGroupName
	 *            缁勫悕
	 */
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity resumeJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName,
			HttpServletResponse response) {
		try {
			setHeader(response);
			jobAndTriggerService.resumejob(jobClassName, jobGroupName);
			return ResponseEntity.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.FAILURE(e.getMessage());
		}
	}

	/**
	 * @Title: rescheduleJob
	 * @Description: TODO(閲嶆柊璁剧疆Job)
	 * @param jobClassName
	 *            绫诲悕
	 * @param jobGroupName
	 *            缁勫悕
	 * @param cronExpression
	 *            琛ㄨ揪寮�
	 */
	@RequestMapping(value = "/reschedule", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity rescheduleJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName, 
			@RequestParam(value = "cronExpression") String cronExpression,
			HttpServletResponse response) {
		try {
			setHeader(response);
			jobAndTriggerService.updateJob(jobClassName, jobGroupName, cronExpression);
			return ResponseEntity.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.FAILURE(e.getMessage());
		}
	}

	/**
	 * @Title: deleteJob
	 * @Description: TODO(鍒犻櫎Job)
	 * @param jobClassName
	 *            绫诲悕
	 * @param jobGroupName
	 *            缁勫悕
	 * @param cronExpression
	 *            琛ㄨ揪寮�
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
			HttpServletResponse response) {
		try {
			setHeader(response);
			jobAndTriggerService.deleteJob(jobClassName, jobGroupName);
			return ResponseEntity.SUCCESS();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.FAILURE(e.getMessage());
		}
	}

	
	/*@PostMapping(value = "/addjob")
	public void addjob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
			jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
	}

	@PostMapping(value = "/pausejob")
	public void pausejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
	}

	@PostMapping(value = "/resumejob")
	public void resumejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		jobAndTriggerService.resumejob(jobClassName, jobGroupName);
	}

	@PostMapping(value = "/reschedulejob")
	public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		jobAndTriggerService.updateJob(jobClassName, jobGroupName, cronExpression);
	}

	@PostMapping(value = "/deletejob")
	public void deletejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		jobAndTriggerService.deleteJob(jobClassName, jobGroupName);
	}
*/

}
