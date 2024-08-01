package com.oim.stores.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.AxisFault;

import com.oim.stores.web.service.ExternalServiceStub.QueryUsefulNumber;
import com.oim.stores.web.service.IDispatchControlServiceStub.DispatchInstruct;
import com.oim.stores.web.service.IDispatchControlServiceStub.DispatchInstructE;
import com.oim.stores.web.service.UnPilotProvinceServiceStub.Exchange;
/**
 * 接口监控util
 * @author yuyan
 *
 */
public class WSStatusUtil {
	public final static String CZ_PATH="1";
	public final static String XH_PATH="2";
	public final static String UPP_PATH="3";
	public final static String INTERFACE_GROUP_NAME="InterfacePath";
	/**
	 * 获取UPP接口返回结果
	 * @param inxml
	 * @param url
	 * @return
	 */
	public static String getUPP(String inxml,String url){
		String target="http://192.168.1.208:8015/uppservice/UnPilotProvinceServiceHttpPort";
		try {
			UnPilotProvinceServiceStub stub=new UnPilotProvinceServiceStub(target);
			Exchange exch=new Exchange();
			exch.setIn0(inxml);
			UnPilotProvinceServiceStub.ExchangeResponse str=stub.exchange(exch);
			String re=str.getOut();
			//System.out.println(re);
			return re;
		} catch(AxisFault fa){
			System.out.println("axisFault:"+fa.getMessage());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 调用充值接口
	 * @param inxml
	 * @param url
	 * @return
	 */
	public static String getCZ(String inxml,String url){
		String target="http://192.168.1.203:8080/ElecChannel/services/IDispatchControlPort";
		try {
			IDispatchControlServiceStub stub=new IDispatchControlServiceStub(target);
			DispatchInstruct para=new DispatchInstruct();
			String paramsStr="<DestinationId>13385918986</DestinationId><DestinationAttr>2</DestinationAttr><ObjType>3</ObjType>" +
			"<DestinationIdPin></DestinationIdPin><BalanceQueryFlag>1</BalanceQueryFlag>";
			para.setArg0(paramsStr);
			DispatchInstructE e=new DispatchInstructE();
			e.setDispatchInstruct(para);
			IDispatchControlServiceStub.DispatchInstructResponseE str=stub.dispatchInstruct(e);
			String re=str.getDispatchInstructResponse().get_return();
			//System.out.println(re);
			return re;
		} catch(AxisFault fa){
			System.out.println("axisFault:"+fa.getMessage());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 调用选号接口
	 * @param path
	 * @return
	 */
	public static boolean getXHStatus(String path){
		String target="http://192.168.1.208:8015/ExternalWebService/services/ExternalService";
		if(path!=null){
			target=path;
		}
		try {
			ExternalServiceStub stub=new ExternalServiceStub(target);
			QueryUsefulNumber num=new QueryUsefulNumber();
			String xml="<RequestParameter><REQUEST-INFO AGENCYID=\"591000010\" SVRCODE=\"100002010\" " +
			"SERIALNUMBER=\"20121127211702000000010059\" REQUESTTIME=\"20121127210000\" " +
			"AUTHENTICATOR=\"BD04266A463629A2CC6F6050CCA295E1\" /><PARAMETERS><areaCode>591</areaCode>" +
			"<queryType>1</queryType><codeType>1</codeType><codeCharacter></codeCharacter><codePosition>0</codePosition>" +
			"<filterFour>0</filterFour><page>1</page><pageSize>50</pageSize></PARAMETERS></RequestParameter>";
			num.setInputXml(xml);
			ExternalServiceStub.QueryUsefulNumberResponse res=stub.queryUsefulNumber(num);
			@SuppressWarnings("unused")
			String str=res.get_return();
			//System.out.println("result:"+str);
			return true;
		} catch(AxisFault fa){
			System.out.println("axisFault:"+fa.getMessage());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 查看UPP接口状态
	 * @param path
	 * @return
	 */
	public static boolean getUPPStatus(String path){
		String target="http://192.168.1.208:8015/uppservice/UnPilotProvinceServiceHttpPort";
		if(path!=null){
			target=path;
		}
		try {
			UnPilotProvinceServiceStub stub=new UnPilotProvinceServiceStub(target);
			Exchange exch=new Exchange();
			String inpar="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			//订单列表查询
	        String xml="<ContractRoot><TcpCont><TransactionID>1000000045201204166001052278</TransactionID><ActionCode>0</ActionCode>"+
	        "<BusCode>BUS80001</BusCode><ServiceCode>SVC80002</ServiceCode><ServiceContractVer></ServiceContractVer>"+
	        "<ServiceLevel>1</ServiceLevel><SrcOrgID>100000</SrcOrgID><SrcSysID>1000000037</SrcSysID><SrcSysSign></SrcSysSign>"+
	        "<DstOrgID>600105</DstOrgID><DstSysID>6001050001</DstSysID><ReqTime>20120416114018</ReqTime></TcpCont>"+
	        "<SvcCont><IdentityCardNumber>35010319811201222</IdentityCardNumber><Name>李四</Name></SvcCont></ContractRoot>";
	    //"<SvcCont></SvcCont></ContractRoot>";
			exch.setIn0(inpar+xml);
			UnPilotProvinceServiceStub.ExchangeResponse str=stub.exchange(exch);
			@SuppressWarnings("unused")
			String re=str.getOut();
			//System.out.println(re);
			return true;
		} catch(AxisFault fa){
			System.out.println("axisFault:"+fa.getMessage());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 观察充值接口状态
	 * @param path
	 * @return
	 */
	public static boolean getCZStatus(String path){
		String target="http://192.168.1.203:8080/ElecChannel/services/IDispatchControlPort";
		if(path!=null){
			target=path;
		}
		try {
			IDispatchControlServiceStub stub=new IDispatchControlServiceStub(target);
			DispatchInstruct para=new DispatchInstruct();
			String paramsStr="<DestinationId>13385918986</DestinationId><DestinationAttr>2</DestinationAttr><ObjType>3</ObjType>" +
			"<DestinationIdPin></DestinationIdPin><BalanceQueryFlag>1</BalanceQueryFlag>";
			para.setArg0(paramsStr);
			DispatchInstructE e=new DispatchInstructE();
			e.setDispatchInstruct(para);
			IDispatchControlServiceStub.DispatchInstructResponseE str=stub.dispatchInstruct(e);
			@SuppressWarnings("unused")
			String re=str.getDispatchInstructResponse().get_return();
			//System.out.println(re);
			return true;
		} catch(AxisFault fa){
			System.out.println("axisFault:"+fa.getMessage());
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 执行命令
	 * @param shellCommand
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String executeShell(String shellCommand) throws IOException, InterruptedException {
		@SuppressWarnings("unused")
		int success = 0;
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader bufferedReader = null;
		// 格式化日期时间，记录日志时使用
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");
		    stringBuffer.append(dateFormat.format(new Date()))
			    .append("准备执行Shell命令 ").append(shellCommand)
			    .append(" \r\n");

		    Process pid = null;
		    String[] cmd = { "/bin/sh", "-c", shellCommand };
		    // 执行Shell命令
		    pid = Runtime.getRuntime().exec(cmd);
		    if (pid != null) {
			stringBuffer.append("进程号：").append(pid.toString())
				.append("\r\n");
			pid.waitFor();
			InputStreamReader ir = new InputStreamReader(
				pid.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			int index = 0;
			stringBuffer.append("执行结果:");
			String line = "";
			while ((line = input.readLine()) != null) {
			    index++;
			    stringBuffer.append(line);
			}
		    } else {
			stringBuffer.append("没有pid\r\n");
		    }
		    stringBuffer.append(dateFormat.format(new Date())).append(
			    "Shell命令执行完毕\r\n执行结果为：\r\n");
		    String line = null;
		    // 读取Shell的输出内容，并添加到stringBuffer中
		    while (bufferedReader != null
			    && (line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line).append("\r\n");
		    }
		    success = 1;
		return stringBuffer.toString();
	    }
}
