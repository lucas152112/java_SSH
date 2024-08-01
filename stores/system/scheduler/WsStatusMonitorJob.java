package com.oim.stores.system.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import com.oim.stores.common.LogManager;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.order.dao.OrderInfoDao;
import com.oim.stores.system.domain.SchInfoBean;
import com.oim.stores.system.domain.SySmsParam;
import com.oim.stores.web.service.WSStatusUtil;

public class WsStatusMonitorJob implements Job{
	private static Date preTime;
	private OrderInfoDao orderDao;
	
	public WsStatusMonitorJob() {
	}
	public WsStatusMonitorJob(OrderInfoDao orderDao) {
		this.orderDao = orderDao;
	}

	public void run() throws SchedulerException{
		//get a referrence to a scheduler;
		SchedulerFactory sf=new StdSchedulerFactory();
		Scheduler sch=sf.getScheduler();
		//computer a time that on the next round minutes;
		//Date runtime=TriggerUtils.
		//define the job and tie to our jobclass;
		JobDetail job=newJob(WsStatusMonitorJob.class).withIdentity("job2", "group2").build();//new JobDetail("job1","group2",WsStatusMonitorJob.class);
		JobDataMap map = job.getJobDataMap();
        map.put("dao", orderDao);
        map.put("time", null);
		//Trigger the job to run on the next round minutes;
		Trigger trigger=newTrigger().withIdentity("testTrigger", "group2")
						.forJob(job.getKey()).withSchedule(cronSchedule("0/5 * * * * ?")).build();
		//tell quartz to scheduler the job using the trigger;
		sch.scheduleJob(job, trigger);
		sch.start();
	}
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		orderDao = (OrderInfoDao) context.getJobDetail().getJobDataMap().get("dao");
		SchInfoBean schInfo = null;
		if(orderDao.getSchById("3")!=null){
			schInfo=orderDao.getSchById("3");
		}else{
			schInfo=new SchInfoBean();
		}
		String interval = schInfo.getInterval(); //间隔时间
		String unitType = schInfo.getUnittype(); //单位：1.秒，2.分
		
		if(WsUtils.checkTime(schInfo)==2){
			return;
		}
		
		int unitTypeInt = 2;
		String timeStr = "30";
		if(interval != null){
			timeStr = interval.toString();
			if(unitType != null){
				unitTypeInt = Integer.parseInt(unitType);//unitType.intValue();
			}
		}
		int time = Integer.parseInt(timeStr);
		
		/*若上一次执行日期不为空，则判断当前日期与上次执行日期的间隔时间是否小于配置的间隔时间*/
		if(preTime != null){ 
			Date currTime = new Date();
			Long timeInterval = (currTime.getTime() - preTime.getTime())/1000/60;
			if(unitTypeInt == 1){
				timeInterval = (currTime.getTime() - preTime.getTime())/1000;
			}
			if(timeInterval.intValue() < time){
				return;
			}
		}
		
		/*监控接口状态任务*/
		preTime = new Date();
		LogManager.info("wsstatusJob start，时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try{
			List<SyParameter> list=orderDao.qryInterPath(WSStatusUtil.INTERFACE_GROUP_NAME);
			String czpath=null;
			String xhpath=null;
			String upppath=null;
			for (int i = 0; i < list.size(); i++) {
				if("1".equals(list.get(i).getId().getGroupType())){
					czpath=list.get(i).getGroupValue();
				}else if("2".equals(list.get(i).getId().getGroupType())){
					xhpath=list.get(i).getGroupValue();
				}else if("3".equals(list.get(i).getId().getGroupType())){
					upppath=list.get(i).getGroupValue();
				}
			}
			boolean czStatus=WSStatusUtil.getCZStatus(czpath);
			boolean xhStatus=WSStatusUtil.getXHStatus(xhpath);
			boolean uppStatus=WSStatusUtil.getUPPStatus(upppath);
			
//			boolean czStatus=WSStatusUtil.getCZStatus();
//			boolean xhStatus=WSStatusUtil.getXHStatus();
//			boolean uppStatus=WSStatusUtil.getUPPStatus();
			String str=null;//"normal";
			if(!czStatus){
				str="充值接口不可用,请检查!";
			}
			if(!xhStatus){
				str="选号接口不可用,请检查!";
			}
			if(!uppStatus){
				str="UPP接口不可用,请检查!";
			}
			LogManager.info("wsstatusJob executing，时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//tell smsSend to send info to user;
			if(str!=null&&str!=""){
				//call interface and send message to user;
				SySmsParam sms=orderDao.qrySmsParam();
				int result=0;
				if(sms!=null){
					result =WsUtils.sendSmsMsg(sms);
					System.out.println("sms result:"+result);
				}else{
					result=WsUtils.sendMsg("18060508846", str, 1);
				}
				LogManager.info("wsstatusJob executing,result:"+result+",时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}catch (Exception e) {
			LogManager.errorLog("短信发送", "发送异常", e.getMessage());
			e.printStackTrace();
		}
	}

}
