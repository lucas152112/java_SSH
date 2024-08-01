package com.oim.stores.system.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ViewStoreAccount;
import com.oim.stores.system.domain.SySmsInfo;
import com.oim.stores.system.domain.SySmsParam;
import com.oim.stores.system.domain.SyVirtualTeam;
import com.oim.stores.system.service.SyVirtualTeamService;
import com.oim.stores.system.service.WsMngService;
import com.oim.stores.web.service.WSStatusUtil;
/**
 * 接口状态管理action
 * @author yuyan
 *
 */
@SuppressWarnings({"serial","unchecked"})
public class WSStatusAction extends BaseAction{
	private String czStatus;//充值状态
	private String xhStatus;//选号状态
	private String uppStatus;//upp状态
	private SySmsParam sySmsInfo;//系统参数对象
	private WsMngService wsService;//接口监控service
	private String czpath;//充值接口路径
	private String xhpath;//选号接口路径
	private String upppath;//upp接口路径
	private SySmsInfo smsInfo;//企信通短信发送;
	private SyVirtualTeamService syVirtualTeamService;
	private int total;
	private String jsonDatas;
	/**
	 * to UPP 测试页
	 * @return
	 */
	public String toUPPTest(){
		return SUCCESS;
	}
	/**
	 * to 选号测试页
	 * @return
	 */
	public String toXHTest(){
		request.setAttribute("htmlUrl", "http://192.168.1.208:8015/ServiceTest/telList.jsp");
		return SUCCESS;
	}
	/**
	 * 处理选号;
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String processXH(){
		long startTime = System.currentTimeMillis();
		String testUrl = "http://192.168.1.208:8015/ExternalWebService/services/ExternalService?wsdl";
		String inputXml = request.getParameter("xmltext");
		String method = "queryUsefulNumber";
		String returnXml = "";

		String namespace = "http://service.external.oim.com";
		RPCServiceClient serviceClient = null;
		try {
			serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference(testUrl);
			options.setTo(targetEPR);
			options.setProperty(HTTPConstants.CHUNKED, "false");
			Object[] opAddEntryArgs = new Object[] { inputXml };
			Class[] classes = new Class[] { String.class };
			QName qName = new QName(namespace, method);
			returnXml = serviceClient.invokeBlocking(qName, opAddEntryArgs, classes)[0] + "";
			serviceClient.cleanupTransport();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			returnXml="接口异常";
			e.printStackTrace();
		}
		
		if(returnXml!=null){
		}else{
			returnXml="";
		}
		request.setAttribute("xmltext", inputXml);
		request.setAttribute("returnXml", returnXml);
		request.setAttribute("telList", parseTelList(returnXml));
		request.setAttribute("batchId", parseBatchId(returnXml));
		request.setAttribute("pageSize", request.getParameter("pageSize"));
		request.setAttribute("pageNumber", request.getParameter("pageNumber"));
		request.setAttribute("areaCode", request.getParameter("areaCode"));
		request.setAttribute("filterFour", request.getParameter("filterFour"));
		request.setAttribute("codeMark", request.getParameter("codeMark"));
		request.setAttribute("lastCode", request.getParameter("lastCode"));
		
		long endTime = System.currentTimeMillis();
		request.setAttribute("timer", (endTime - startTime) / 1000F);
		return SUCCESS;
	}
	/**
	 * 解析xml
	 * @param xml
	 * @return
	 */
	private List<Map<String, String>> parseTelList(String xml) {
		List<Map<String, String>> tellist = new ArrayList<Map<String, String>>();
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			if (null != root) {
				List<Element> es = root.elements();
				for (Element e : es) {
					if (e.getName().equals("PARAMETERS")) {
						List<Element> ces = e.elements();
						for (Element ce : ces) {
							Map<String, String> telmap = new HashMap<String, String>();
							if (ce.getName().equals("numberList")) {
								List<Element> cces = ce.elements();
								for (Element cce : cces) {
									telmap.put(cce.getName(), cce.getText());
								}
								tellist.add(telmap);
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return tellist;
	}
	/**
	 * to 充值测试页
	 * @return
	 */
	public String toCZTest(){
		request.setAttribute("htmlUrl", "http://192.168.1.203:9081/AgentDemo/");
		return SUCCESS;
	}
	/**
	 * 查询系统参数
	 * @return
	 */
	public String qrySmsParam(){
		try {
			sySmsInfo=wsService.qrySmsParam();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 查询团队
	 * @return
	 */
	public String queryTeamPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("teamName", request.getParameter("teamName"));
		Map<String, String> params2 = new HashMap<String, String>();
		try {
			PageResponse pageList = syVirtualTeamService.queryTeamForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyVirtualTeam si = (SyVirtualTeam) pageList.getList().get(i);
				JSONObject jsonObject = JSONUtils.toJSONObject(si);
				jsonArray.add(jsonObject);
			}
			jsonDatas = jsonArray.toString();
			total = pageList.getTotalRecord();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "json";
	}

	/**
	 * 查询企信通短信发送管理信息
	 * @return
	 */
	public String qrySmsInfo(){
		try {
			smsInfo=wsService.qryQxtSmsInfo();
			if(smsInfo!=null){
//				Map<String,String> params=new HashMap<String, String>();
//				params.put("teamId", smsInfo.getSmsObj());
//				List<SyVirtualTeam> list=syVirtualTeamService.queryTeamList(params);
//				if(list.size()>0){
//					SyVirtualTeam team=list.get(0);
//					smsInfo.setSmsObjStr(team.getTeamName());
//				}
				
				//支持multiple;
				String[] str=smsInfo.getSmsObj().split(",");
				String ids="";
				for (int i = 0; i < str.length; i++) {
					ids=ids+str[i]+",";
				}
				if(ids!=null&&!"".equals(ids)){
					ids=ids.substring(0,ids.length()-1);
				}
				Map<String,String> params=new HashMap<String, String>();
				params.put("teamIds", ids);
				List<SyVirtualTeam> list=syVirtualTeamService.queryTeamList(params);
				String objstr="";
				for (int i = 0; i < list.size(); i++) {
					objstr=objstr+list.get(i).getTeamName()+",";
				}
				if(objstr!=null&&!"".equals(objstr)){
					objstr=objstr.substring(0,objstr.length()-1);
					smsInfo.setSmsObjStr(objstr);
				}
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 查询接口参数配置
	 * @return
	 */
	public String qryInterCfg(){
		try {
			List<SyParameter> list=wsService.qryInterPath(WSStatusUtil.INTERFACE_GROUP_NAME);
			for (int i = 0; i < list.size(); i++) {
				if("1".equals(list.get(i).getId().getGroupType())){
					czpath=list.get(i).getGroupValue();
				}else if("2".equals(list.get(i).getId().getGroupType())){
					xhpath=list.get(i).getGroupValue();
				}else if("3".equals(list.get(i).getId().getGroupType())){
					upppath=list.get(i).getGroupValue();
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 查询接口状态
	 * @return
	 */
	public String qryWSStatus(){
		try {
			List<SyParameter> list=wsService.qryInterPath(WSStatusUtil.INTERFACE_GROUP_NAME);
			String czpath1=null;
			String xhpath1=null;
			String upppath1=null;
			for (int i = 0; i < list.size(); i++) {
				if("1".equals(list.get(i).getId().getGroupType())){
					czpath1=list.get(i).getGroupValue();
				}else if("2".equals(list.get(i).getId().getGroupType())){
					xhpath1=list.get(i).getGroupValue();
				}else if("3".equals(list.get(i).getId().getGroupType())){
					upppath1=list.get(i).getGroupValue();
				}
			}
			czStatus=WSStatusUtil.getCZStatus(czpath1)+"";
			xhStatus=WSStatusUtil.getXHStatus(xhpath1)+"";
			uppStatus=WSStatusUtil.getUPPStatus(upppath1)+"";
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//czStatus=WSStatusUtil.getCZStatus(null)+"";
		//xhStatus=WSStatusUtil.getXHStatus(null)+"";
		//uppStatus=WSStatusUtil.getUPPStatus(null)+"";
		return SUCCESS;
	}
	/**
	 * 解析batch xml
	 * @param xml
	 * @return
	 */
	private String parseBatchId(String xml) {
		@SuppressWarnings("unused")
		List<Map<String, String>> tellist = new ArrayList<Map<String, String>>();
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			if (null != root) {
				List<Element> es = root.elements();
				for (Element e : es) {
					if (e.getName().equals("PARAMETERS")) {
						List<Element> ces = e.elements();
						for (Element ce : ces) {
							@SuppressWarnings("unused")
							Map<String, String> telmap = new HashMap<String, String>();
							if (ce.getName().equals("batchId")) {
								return ce.getText();
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public SyVirtualTeamService getSyVirtualTeamService() {
		return syVirtualTeamService;
	}
	public void setSyVirtualTeamService(SyVirtualTeamService syVirtualTeamService) {
		this.syVirtualTeamService = syVirtualTeamService;
	}
	public SySmsInfo getSmsInfo() {
		return smsInfo;
	}
	public void setSmsInfo(SySmsInfo smsInfo) {
		this.smsInfo = smsInfo;
	}
	public WsMngService getWsService() {
		return wsService;
	}
	public void setWsService(WsMngService wsService) {
		this.wsService = wsService;
	}
	public SySmsParam getSySmsInfo() {
		return sySmsInfo;
	}
	public void setSySmsInfo(SySmsParam sySmsInfo) {
		this.sySmsInfo = sySmsInfo;
	}
	public String getXhStatus() {
		return xhStatus;
	}
	public String getCzStatus() {
		return czStatus;
	}

	public void setCzStatus(String czStatus) {
		this.czStatus = czStatus;
	}

	public void setXhStatus(String xhStatus) {
		this.xhStatus = xhStatus;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getJsonDatas() {
		return jsonDatas;
	}
	public void setJsonDatas(String jsonDatas) {
		this.jsonDatas = jsonDatas;
	}
	public String getUppStatus() {
		return uppStatus;
	}

	public void setUppStatus(String uppStatus) {
		this.uppStatus = uppStatus;
	}

	
	public String getCzpath() {
		return czpath;
	}
	public void setCzpath(String czpath) {
		this.czpath = czpath;
	}
	public String getXhpath() {
		return xhpath;
	}
	public void setXhpath(String xhpath) {
		this.xhpath = xhpath;
	}
	public String getUpppath() {
		return upppath;
	}
	public void setUpppath(String upppath) {
		this.upppath = upppath;
	}
}
