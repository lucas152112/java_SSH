package com.oim.stores.system.scheduler;

import java.util.Date;
import com.oim.stores.system.domain.SchInfoBean;
import com.oim.stores.system.domain.SySmsParam;
import com.oim.stores.web.sendSms.SmsService;
import com.oim.stores.web.sendSms.SmsServiceHttpBindingStub;
import com.oim.stores.web.sendSms.SmsServiceLocator;

public class WsUtils {
	public static int sendMsg(String tel,String msg,int num) throws Exception{
		SmsService sendService = new SmsServiceLocator();
		try {//ouxiaofeng 18060508846
			SmsServiceHttpBindingStub sendStub = (SmsServiceHttpBindingStub)sendService.getSmsServiceHttpPort();
			int res = sendStub.sendSms(tel, msg, num, "carnival", 1);
			System.out.println(res);
			return res;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public static int sendSmsMsg(SySmsParam sms) throws Exception{
		SmsService sendService = new SmsServiceLocator();
		try {
			SmsServiceHttpBindingStub sendStub = (SmsServiceHttpBindingStub)sendService.getSmsServiceHttpPort();
			int num=Integer.parseInt(sms.getBusiNum().toString());
			int type=Integer.parseInt(sms.getSendType().toString());
			int res = sendStub.sendSms(sms.getBusiTels(), sms.getShortContent(), num, sms.getUserName(),type);
			System.out.println(res);
			return res;
		} catch (Exception e) {
			throw e;
		} 
	}
	public static void main(String[] args) throws Exception {
		int result =sendMsg("15860808742", "sunyuyan test", 1);
		System.out.println(result);
	}
	
	@SuppressWarnings("deprecation")
	public static int checkTime(SchInfoBean schInfo){
		String schTime = schInfo.getSchtime(); //任务时间
		String schTimeend = schInfo.getSchtimeend(); //任务截止时间
		
		Date nowDate = new Date();
		int nowSeconds = nowDate.getHours() * 60 * 60 + nowDate.getMinutes() * 60 + nowDate.getSeconds();
		int startSchTime = getSeconds(schTime);
		int endSchTime = getSeconds(schTimeend);
		
		if((startSchTime != -1 && startSchTime > nowSeconds) || (endSchTime != -1 && endSchTime < nowSeconds)){
			return 2;
		}
		return 1;
	}
	private static int getSeconds(String time){
		if(time != null){
			String[] schTimeArray = time.split(":");
			if(schTimeArray != null && schTimeArray.length >= 2){
				int schTimeSeconds = Integer.parseInt(schTimeArray[0]) * 60 * 60 + Integer.parseInt(schTimeArray[1]) * 60;
				if(schTimeArray.length == 3){
					schTimeSeconds += Integer.parseInt(schTimeArray[2]);
				}
				return schTimeSeconds;
			}
		}
		return -1;
	}
}
