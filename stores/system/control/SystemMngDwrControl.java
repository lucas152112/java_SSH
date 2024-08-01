package com.oim.stores.system.control;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.Content;
import com.oim.stores.common.Cryto;
import com.oim.stores.common.ShellUtils;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.flydz.domain.AnewDzCfgBean;
import com.oim.stores.flydz.domain.InBankYipay;
import com.oim.stores.flydz.domain.InBankYipayRefund;
import com.oim.stores.flydz.domain.InDzYipayChange;
import com.oim.stores.flydz.domain.InDzYipayDetail;
import com.oim.stores.flydz.domain.InDzYipaySum;
import com.oim.stores.flydz.domain.SySchFile;
import com.oim.stores.flydz.domain.SySchFileBean;
import com.oim.stores.flydz.domain.SySchFileDetail;
import com.oim.stores.flydz.service.FlyCheckService;
import com.oim.stores.module.domain.ShModuleCode;
import com.oim.stores.module.service.ModuleCodeService;
import com.oim.stores.order.domain.ShOrderDetailAttr;
import com.oim.stores.order.domain.ShOrderInfo;
import com.oim.stores.order.domain.ShOrderPay;
import com.oim.stores.order.domain.ShOrderReturn;
import com.oim.stores.order.domain.ViewOrderInfoDetail;
import com.oim.stores.order.service.OrderInfoService;
import com.oim.stores.product.domain.TyCard;
import com.oim.stores.product.service.ProductService;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.system.domain.SchInfoBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.domain.SyRoleMenuRelationId;
import com.oim.stores.system.domain.SySmsInfo;
import com.oim.stores.system.domain.SySmsParam;
import com.oim.stores.system.domain.UserManageBean;
import com.oim.stores.system.domain.UserMenuBean;
import com.oim.stores.system.service.MenuService;
import com.oim.stores.system.service.RoleManageService;
import com.oim.stores.system.service.SchInfoService;
import com.oim.stores.system.service.UserManageService;
import com.oim.stores.system.service.PowerMngService;
import com.oim.stores.system.service.WsMngService;
import com.oim.stores.user.domain.SyLogOrder;
import com.oim.stores.user.domain.SyLogOrderId;
import com.oim.stores.user.domain.SyUserInfo;
import com.oim.stores.web.service.WSStatusUtil;
/**
 * 系统管理dwr
 * @author yuyan
 *
 */
@SuppressWarnings("unchecked")
public class SystemMngDwrControl {
	private UserManageService userMngService;//用户管理service
	private RoleManageService roleMngService;//角色管理service
	private PowerMngService powerMngService;//权限管理service
	private MenuService menuService;//菜单管理service
	private SchInfoService schinfoService;//任务管理service
	private WsMngService wsService;//接口service
	private FlyCheckService flyService;//对账service
	private OrderInfoService orderService;//订单管理service
	private ProductService productService;//产品管理service
	private ModuleCodeService codeService;//code service;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private static final String CZ_OPEN="/root/startchannel.sh";//充值开
	private static final String CZ_CLOSE="/root/shutdownchannel.sh";//充值关
	private static final String UPP_OPEN="/usr/local/tomcat/startup/./gos.sh";//UPP开
	private static final String UPP_CLOSE="/usr/local/tomcat/startup/./gos.shutdown";//UPP关
	private static final String XH_OPEN="/root/startcardservice";//选号开
	private static final String XH_CLOSE="/root/shutdowncrmservice";//选号关
	
	/**
	 * 异常订单处理
	 * @param detailId
	 * @param ip
	 * @return 处理结果
	 */
	public String dzhandleView(long detailId,String ip){
		String flag="0";
		try {
			SySchFileDetail de=flyService.qryFileDetailById(detailId);
			InDzYipayDetail dz=flyService.qryDzDetailByTranId(de.getOtherId());
			InDzYipaySum sum=flyService.qryDzSumById(dz.getId().getDzId());
			if(dz==null){
				return "3";
			}
			if("0001".equals(dz.getPayType())){//支付视图处理
				InBankYipay pay=flyService.qryYipayByTranseq(de.getOtherId());
				//ShOrderPay order=flyService.qryOrderById(dz.getOrderseq());
				ShOrderPay order=flyService.qryOrderByQId(dz.getOrderreqtranseq());
				//ViewYiPayId payView=flyService.queryYipayViewById(dz.getOrderreqtranseq());
				if("3".equals(de.getDzFlag().toString())){//金额不符
					if(pay!=null&&!pay.getOrderamount().toString().equals(dz.getOrderamount())){
						//String payAmout=(Double.parseDouble(dz.getOrderamount().toString())*100)+"";
						//pay.setOrderamount(new BigDecimal(payAmout));
						pay.setOrderamount(new BigDecimal(dz.getOrderamount()));
						flyService.saveYipay(pay);
						addChangeLog(pay, dz, sum);//保存日志
					}
					if(order!=null&&!order.getAmount().toString().equals(dz.getOrderamount())){
						//保存日志
			    		SyLogOrder log=new SyLogOrder();
			    		log.setIpaddress("");
			    		log.setRemark("对账日志,原金额："+order.getAmount());
			    		String orderAmout=(Double.parseDouble(dz.getOrderamount().toString())*0.01)+"";
						order.setAmount(Double.parseDouble(orderAmout));
						if("1".equals(order.getPayStatus().toString())){
			    			order.setDzState(new BigDecimal("2"));
			    		}else if("4".equals(order.getPayStatus().toString())){
			    			order.setDzState(new BigDecimal("4"));
			    		}
			    		flyService.saveOrderPay(order);
						SyLogOrderId id=new SyLogOrderId();
			    		id.setSysTime(new Date());
			    		ViewOrderInfoDetail view=flyService.queryOrderStatus(order.getOrderNo().toString());
			    		if(view==null){
			    			return "6";
			    		}
			    		id.setOrderStatus(view.getOrderStatus());
			    		id.setOrderNo(order.getOrderNo());
			    		log.setId(id);
			    		log.setIpaddress(ip);
			    		flyService.saveOrderLog(log);
					}
		    		//update file detail status;
					de.setProcessStatus(new BigDecimal(1));
					de.setProcessDate(new Date());
					flyService.saveFileDetail(de);					
					flag="1";
				}else if("5".equals(de.getDzFlag().toString())){//支付结果错误
					if(!"0000".equals(pay.getRetncode())){
						pay.setRetncode("0000");
						flyService.saveYipay(pay);
						addChangeLog(pay, dz, sum);
					}
					//if("1".equals(order.getPayType().toString())){
						order.setPayStatus(new BigDecimal(1));//已支付
						order.setDzState(new BigDecimal(2));//已支付已对账
			    		flyService.saveOrderPay(order);
			    		//update order status;
			    		ShOrderInfo orderInfo=flyService.queryOrderInfo(dz.getOrderseq());
			    		if(orderInfo==null){
			    			return "5";
			    		}
			    		orderInfo.setOrderStatus(new BigDecimal(1));//等待发货
			    		flyService.saveShOrder(orderInfo);
			    		//保存日志
			    		SyLogOrder log=new SyLogOrder();
			    		log.setIpaddress("");
			    		log.setRemark("对账日志,调整支付状态为成功。");
			    		SyLogOrderId id=new SyLogOrderId();
			    		id.setSysTime(new Date());
			    		id.setOrderStatus(new BigDecimal(1));
			    		id.setOrderNo(order.getOrderNo());
			    		log.setId(id);
			    		log.setIpaddress(ip);
			    		flyService.saveOrderLog(log);
			    		//判断是否购买天翼号码
			    		List<ShOrderDetailAttr> atrr=orderService.getAttrByOrderNo(Content.ATTR_ID_PHONE_NUMBER, order.getOrderNo().toString());
			    		if(atrr.size()>0){
			    			ShOrderDetailAttr att=atrr.get(0);
			    			TyCard card=productService.getCardById(att.getAttrValue());
			    			card.setCardStatus(new BigDecimal(3));
			    			productService.saveTyCard(card);
			    		}
			    		
					//}
					//update file detail status;
					de.setProcessStatus(new BigDecimal(1));
					de.setProcessDate(new Date());
					flyService.saveFileDetail(de);
					flag="1";
				}else if("2".equals(de.getDzFlag().toString())){//外部系统多
					if(order!=null){
						if(pay==null){
							pay=new InBankYipay();
							pay.setRetncode("0000");
							pay.setMerchantid(sum.getMerchantid());
							pay.setSubmerchantid(dz.getSubmerchantid());
							pay.setOrderseq(dz.getOrderseq());
							pay.setOrderreqtranseq(dz.getOrderreqtranseq());
							pay.setOrderdate(sum.getCreateDate());
							pay.setOrderamount(new BigDecimal(dz.getOrderamount()));
							pay.setProductamount(new BigDecimal(dz.getOrderamount()));//产品金额
							pay.setAttachamount(new BigDecimal("0"));//附加金额;
							pay.setCurtype("RMB");
							pay.setEncodetype(new BigDecimal(0));
							pay.setMac("0");
							pay.setBusicode("0001");
							pay.setMerchanturl("0");
							pay.setBackmerchanturl("0");
							pay.setProductid("0");
							pay.setTmnum("0");
							pay.setCustomerid("0");
							pay.setProductdesc("0");
							pay.setClientip("0");
							
							flyService.saveYipay(pay);
							de.setProcessStatus(new BigDecimal(1));
							de.setProcessDate(new Date());
							flyService.saveFileDetail(de);
							addChangeLog(pay, dz, sum);
							flag="1";
						}
					}else{
						return "4";
					}
				}else{
					return "3";//目前不支持该种处理;
				}
				
				de.setProcessStatus(new BigDecimal(1));
				de.setProcessDate(new Date());
				flyService.saveFileDetail(de);
				flag="1";
			}else if("0002".equals(dz.getPayType())){//退款视图处理
				InBankYipayRefund refund=flyService.qryRefundByTranseq(de.getOtherId());
				//ViewYiPayRefundId refundView=flyService.queryRefundViewById(dz.getOrderreqtranseq());
				//ShOrderPay order=flyService.qryOrderById(refundView.getOrderNo().toString());
				ShOrderPay order=flyService.qryOrderByOrderNumber(dz.getOrderseq());
				//ShOrderPay order=flyService.qryOrderByQId(dz.getOrderreqtranseq());
				if("3".equals(de.getDzFlag().toString())){//金额不符
					if(refund!=null&&!refund.getTransamt().toString().equals(dz.getOrderamount())){
						//String reAmout=(Double.parseDouble(dz.getOrderamount().toString())*100)+"";
						refund.setTransamt(new BigDecimal(dz.getOrderamount()));
						flyService.saveRefund(refund);
						addChangeLogRe(refund, dz, sum);
					}
					if(order!=null&&!order.getAmount().toString().equals(dz.getOrderamount())){
						//保存日志
			    		SyLogOrder log=new SyLogOrder();
			    		log.setIpaddress("");
			    		log.setRemark("对账日志,原金额："+order.getAmount());
			    		String orderAmout=(Double.parseDouble(dz.getOrderamount().toString())*0.01)+"";
			    		order.setAmount(Double.parseDouble(orderAmout));
						//order.setAmount(Double.parseDouble(dz.getOrderamount()));
						if("1".equals(order.getPayStatus().toString())){
			    			order.setDzState(new BigDecimal("2"));
			    		}else if("4".equals(order.getPayStatus().toString())){
			    			order.setDzState(new BigDecimal("4"));
			    		}
			    		flyService.saveOrderPay(order);
						SyLogOrderId id=new SyLogOrderId();
			    		id.setSysTime(new Date());
			    		ViewOrderInfoDetail view=flyService.queryOrderStatus(order.getOrderNo().toString());
			    		if(view==null){
			    			return "6";
			    		}
			    		id.setOrderStatus(view.getOrderStatus());
			    		id.setOrderNo(order.getOrderNo());
			    		log.setId(id);
			    		log.setIpaddress(ip);
			    		flyService.saveOrderLog(log);
					}
					de.setProcessStatus(new BigDecimal(1));
					de.setProcessDate(new Date());
					flyService.saveFileDetail(de);
					flag="1";
				}else if("5".equals(de.getDzFlag().toString())){//支付结果错误
					if(!"0000".equals(refund.getResultcode())&&!"0".equals(refund.getResultcode())){
						refund.setResultcode("0000");
						flyService.saveRefund(refund);
						addChangeLogRe(refund, dz, sum);
					}
					//if("1".equals(order.getPayType().toString())){
						order.setPayStatus(new BigDecimal(4));//已退款
						order.setDzState(new BigDecimal(4));//已退款已对账
			    		flyService.saveOrderPay(order);
			    		//update order status;
			    		ShOrderInfo orderInfo=flyService.queryOrderInfo(dz.getOrderseq());
			    		if(orderInfo==null){
			    			return "5";
			    		}
			    		//orderInfo.setOrderStatus(new BigDecimal(1));
			    		orderInfo.setOrderStatus(new BigDecimal(10));//订单关闭
			    		flyService.saveShOrder(orderInfo);
			    		//保存日志
			    		SyLogOrder log=new SyLogOrder();
			    		log.setIpaddress("");
			    		log.setRemark("对账日志,调整支付状态为成功。");
			    		SyLogOrderId id=new SyLogOrderId();
			    		id.setSysTime(new Date());
			    		id.setOrderStatus(new BigDecimal(1));
			    		id.setOrderNo(order.getOrderNo());
			    		log.setId(id);
			    		log.setIpaddress(ip);
			    		flyService.saveOrderLog(log);
			    		
			    		//修改退款表
			    		ShOrderReturn ret=orderService.queryOrderReturn(order.getOrderNo().toString());
			    		if(ret!=null){
			    			ret.setReturnStatus(new BigDecimal(55));//商家已退款;
				    		orderService.saveOrderReturn(ret);
			    		}else{
			    			return "7";
			    		}
					//}
					de.setProcessStatus(new BigDecimal(1));
					de.setProcessDate(new Date());
					flyService.saveFileDetail(de);
					flag="1";
				}else if("2".equals(de.getDzFlag().toString())){//外部系统多
					if(order!=null){
						if(refund==null){
							refund=new InBankYipayRefund();
							refund.setResultcode("0000");
							refund.setCommcode(sum.getMerchantid());
							refund.setReqtime(sum.getCreateDate());
							refund.setSubcommcode(dz.getSubmerchantid());
							refund.setTransamt(new BigDecimal(dz.getOrderamount()));
							refund.setMac("0");
							refund.setCommpwd("0");
							refund.setOrderrefundid(dz.getOrderreqtranseq());//退款流水号
							//去支付表查询流水号
							Map<String, String> params=new HashMap<String, String>();
							params.put("orderseq", refund.getOldorderid());
							params.put("retncode", "0000");
							InBankYipay pay=flyService.queryYiPay(params);
							refund.setOldorderpayid(pay.getOrderreqtranseq());//原扣款成功的请求支付流水号;
							
							refund.setOldorderid(dz.getOrderseq());//原扣款成功的订单号；
							flyService.saveRefund(refund);
							de.setProcessStatus(new BigDecimal(1));
							de.setProcessDate(new Date());
							flyService.saveFileDetail(de);
							addChangeLogRe(refund, dz, sum);
							flag="1";
						}
					}else{
						return "4";
					}
				}else{
					return "3";//目前不支持该种处理;
				}
				
				
			}
		} catch (ServiceException e) {
			flag="0";
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * old handle
	 * @param detailId
	 * @param ip
	 * @return
	 */
	public String dzhandleViewOld(long detailId,String ip){
		String flag="0";
		try {
			SySchFileDetail de=flyService.qryFileDetailById(detailId);
			if("2".equals(de.getDetailType().toString())){//订单表调整
				//handle order resultcode;
				InDzYipayDetail dz=flyService.qryDzDetailByTranId(de.getOtherId());
				if("3".equals(de.getDzFlag().toString())){//金额不符
		    		ShOrderPay pay=flyService.qryOrderById(dz.getOrderseq());
		    		//保存日志
		    		SyLogOrder log=new SyLogOrder();
		    		log.setIpaddress("");
		    		log.setRemark("对账日志,原金额："+pay.getAmount());
		    		
		    		pay.setAmount(Double.parseDouble(dz.getOrderamount()));
		    		if("1".equals(pay.getPayStatus().toString())){
		    			pay.setDzState(new BigDecimal("2"));
		    		}else if("4".equals(pay.getPayStatus().toString())){
		    			pay.setDzState(new BigDecimal("4"));
		    		}
		    		flyService.saveOrderPay(pay);
		    		//update file detail status;
		    		de.setProcessStatus(new BigDecimal(1));
					de.setProcessDate(new Date());
					flyService.saveFileDetail(de);
					
		    		SyLogOrderId id=new SyLogOrderId();
		    		id.setSysTime(new Date());
		    		ViewOrderInfoDetail view=flyService.queryOrderStatus(pay.getOrderNo().toString());
		    		id.setOrderStatus(view.getOrderStatus());
		    		id.setOrderNo(pay.getOrderNo());
		    		log.setId(id);
		    		log.setIpaddress(ip);
		    		flyService.saveOrderLog(log);
		    		flag="1";//处理完成
				}else if("5".equals(de.getDzFlag().toString())){//结果不符
					ShOrderPay pay=flyService.qryOrderById(dz.getOrderseq());
		    		pay.setPayType(new BigDecimal(1));
		    		flyService.saveOrderPay(pay);
		    		//update order status;
		    		ShOrderInfo order=flyService.queryOrderInfo(dz.getOrderseq());
		    		if(order==null){
		    			return "5";
		    		}
		    		order.setOrderStatus(new BigDecimal(1));
		    		flyService.saveShOrder(order);
		    		//update file detail status;
		    		de.setProcessStatus(new BigDecimal(1));
					de.setProcessDate(new Date());
					flyService.saveFileDetail(de);
		    		//保存日志
		    		SyLogOrder log=new SyLogOrder();
		    		log.setIpaddress("");
		    		log.setRemark("对账日志,调整支付状态为成功。");
		    		SyLogOrderId id=new SyLogOrderId();
		    		id.setSysTime(new Date());
		    		id.setOrderStatus(new BigDecimal(1));
		    		id.setOrderNo(pay.getOrderNo());
		    		log.setId(id);
		    		log.setIpaddress(ip);
		    		flyService.saveOrderLog(log);
		    		flag="1";
				}else{
					return "3";//目前不支持该种处理;
				}
			}else{//handle interface table exception;
				InDzYipayDetail dz=flyService.qryDzDetailByTranId(de.getOtherId());
				if(dz==null){
					return "3";
				}
				InDzYipaySum sum=flyService.qryDzSumById(dz.getId().getDzId());
				if("3".equals(de.getDzFlag().toString())){//处理金额不符
					if("0001".equals(dz.getPayType())){//支付表处理
						InBankYipay pay=flyService.qryYipayByTranseq(de.getOtherId());
						pay.setOrderamount(new BigDecimal(dz.getOrderamount()));
						flyService.saveYipay(pay);
						de.setProcessStatus(new BigDecimal(1));
						de.setProcessDate(new Date());
						flyService.saveFileDetail(de);
						addChangeLog(pay, dz, sum);//保存日志
						flag="1";
					}else{
						InBankYipayRefund refund=flyService.qryRefundByTranseq(de.getOtherId());
						refund.setTransamt(new BigDecimal(dz.getOrderamount()));
						flyService.saveRefund(refund);
						de.setProcessStatus(new BigDecimal(1));
						de.setProcessDate(new Date());
						flyService.saveFileDetail(de);
						addChangeLogRe(refund, dz, sum);
						flag="1";
					}
				}else if("5".equals(de.getDzFlag().toString())){//支付结果错误
					if("0001".equals(dz.getPayType())){//
						InBankYipay pay=flyService.qryYipayByTranseq(de.getOtherId());
						pay.setRetncode("0000");
						flyService.saveYipay(pay);
						de.setProcessStatus(new BigDecimal(1));
						de.setProcessDate(new Date());
						flyService.saveFileDetail(de);
						addChangeLog(pay, dz, sum);
						flag="1";
					}else{
						InBankYipayRefund refund=flyService.qryRefundByTranseq(de.getOtherId());
						refund.setResultcode("0000");
						flyService.saveRefund(refund);
						
						de.setProcessStatus(new BigDecimal(1));
						de.setProcessDate(new Date());
						flyService.saveFileDetail(de);
						addChangeLogRe(refund, dz, sum);
						flag="1";
					}
				}else if("2".equals(de.getDzFlag().toString())){//外部系统多
					//InDzYipaySum sum=flyService.qryDzSumById(dz.getId().getDzId());
					if("0001".equals(dz.getPayType())){//支付表处理
						InBankYipay pay=new InBankYipay();
						pay.setRetncode("0000");
						pay.setMerchantid(sum.getMerchantid());
						pay.setSubmerchantid(dz.getSubmerchantid());
						pay.setOrderseq(dz.getOrderseq());
						pay.setOrderreqtranseq(dz.getOrderreqtranseq());
						pay.setOrderdate(sum.getCreateDate());
						pay.setOrderamount(new BigDecimal(dz.getOrderamount()));
						pay.setProductamount(new BigDecimal(dz.getOrderamount()));//产品金额
						pay.setAttachamount(new BigDecimal("0"));//附加金额;
						pay.setCurtype("RMB");
						pay.setEncodetype(new BigDecimal(0));
						pay.setMac("0");
						pay.setBusicode("0001");
						pay.setMerchanturl("0");
						pay.setBackmerchanturl("0");
						pay.setProductid("0");
						pay.setTmnum("0");
						pay.setCustomerid("0");
						pay.setProductdesc("0");
						pay.setClientip("0");
						
						flyService.saveYipay(pay);
						de.setProcessStatus(new BigDecimal(1));
						de.setProcessDate(new Date());
						flyService.saveFileDetail(de);
						addChangeLog(pay, dz, sum);
						flag="1";
					}else{
						InBankYipayRefund refund=new InBankYipayRefund();
						refund.setResultcode("0000");
						refund.setCommcode(sum.getMerchantid());
						refund.setReqtime(sum.getCreateDate());
						refund.setSubcommcode(dz.getSubmerchantid());
						refund.setTransamt(new BigDecimal(dz.getOrderamount()));
						refund.setMac("0");
						refund.setCommpwd("0");
						refund.setOrderrefundid(dz.getOrderreqtranseq());//退款流水号
						//去支付表查询流水号
						Map<String, String> params=new HashMap<String, String>();
						params.put("orderseq", refund.getOldorderid());
						params.put("retncode", "0000");
						InBankYipay pay=flyService.queryYiPay(params);
						refund.setOldorderpayid(pay.getOrderreqtranseq());//原扣款成功的请求支付流水号;
						
						refund.setOldorderid(dz.getOrderseq());//原扣款成功的订单号；
						flyService.saveRefund(refund);
						de.setProcessStatus(new BigDecimal(1));
						de.setProcessDate(new Date());
						flyService.saveFileDetail(de);
						addChangeLogRe(refund, dz, sum);
						flag="1";
					}
				}else{
					return "3";//目前不支持该种处理;
				}
			}
		} catch (ServiceException e) {
			flag="0";
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * add refund handle log
	 * @param refund
	 * @param dz
	 * @param sum
	 * @throws ServiceException
	 */
	public void addChangeLogRe(InBankYipayRefund refund,InDzYipayDetail dz,InDzYipaySum sum) throws ServiceException{
		InDzYipayChange cha=new InDzYipayChange();
		cha.setAddDate(new Date());
		cha.setDzId(dz.getId().getDzId());
		cha.setOrderreqtranseq(refund.getOrderrefundid());
		cha.setPayType("0002");
		cha.setOrderamount(refund.getTransamt().toString());
		cha.setSubmerchantid(refund.getSubcommcode());
		cha.setCheckResult(dz.getCheckResult());
		cha.setRetncode("1");//1,处理成功，0，处理失败
		flyService.saveDZChange(cha);
	}
	/**
	 * add pay handle log
	 * @param pay
	 * @param dz
	 * @param sum
	 * @throws ServiceException
	 */
	public void addChangeLog(InBankYipay pay,InDzYipayDetail dz,InDzYipaySum sum) throws ServiceException{
		InDzYipayChange cha=new InDzYipayChange();
		cha.setAddDate(new Date());
		cha.setDzId(dz.getId().getDzId());
		cha.setOrderreqtranseq(dz.getOrderreqtranseq());
		cha.setPayType("0001");
		cha.setOrderamount(pay.getOrderamount().toString());
		cha.setSubmerchantid(pay.getSubmerchantid());
		cha.setCheckResult(dz.getCheckResult());//对账结果
		cha.setRetncode("1");//1,处理成功，0，处理失败
		flyService.saveDZChange(cha);
	}
	/**
	 * update short message param;    
	 * @param bean
	 * @param updateUser
	 * @return
	 */
	public String updateSmsParam(SySmsParam bean,long updateUser){
		String res=null;
		try {
			wsService.save(bean);
			res="1";
		} catch (Exception e) {
			res="-1";
			e.printStackTrace();
		}
		return res;
	}
	public String updateQXTSms(SySmsInfo bean,long updateUser){
		String res=null;
		try {
			if(bean.getSmsId()==null){
				bean.setSmsId(new BigDecimal(1));
			}
			wsService.saveQXTSms(bean);
			res="1";
		} catch (Exception e) {
			res="-1";
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 更新接口配置
	 * @param czpath
	 * @param xh
	 * @param upp
	 * @return
	 */
	public String updateInterCfg(String czpath,String xh,String upp){
		String res=null;
		try {
			List<SyParameter> list=wsService.qryInterPath(WSStatusUtil.INTERFACE_GROUP_NAME);
			for (int i = 0; i < list.size(); i++) {
				if("1".equals(list.get(i).getId().getGroupType())){
					SyParameter sp=list.get(i);
					sp.setGroupValue(czpath.trim());
					wsService.saveInterCfg(sp);
				}else if("2".equals(list.get(i).getId().getGroupType())){
					SyParameter sp=list.get(i);
					sp.setGroupValue(xh.trim());
					wsService.saveInterCfg(sp);
				}else if("3".equals(list.get(i).getId().getGroupType())){
					SyParameter sp=list.get(i);
					sp.setGroupValue(upp.trim());
					wsService.saveInterCfg(sp);
				}
			}
			
			res="1";
		} catch (Exception e) {
			res="-1";
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 执行shell
	 * @param ws
	 * @return
	 */
	public String execShell(String ws) {
		ShellUtils su = new ShellUtils();
		//WSStatusUtil su=new WSStatusUtil();
		String path=null;
		if("CZ_OPEN".equals(ws))
			path=CZ_OPEN;
		if("CZ_CLOSE".equals(ws))
			path=CZ_CLOSE;
		if("UPP_OPEN".equals(ws))
			path=UPP_OPEN;
		if("UPP_CLOSE".equals(ws))
			path=UPP_CLOSE;
		if("XH_OPEN".equals(ws))
			path=XH_OPEN;
		if("XH_CLOSE".equals(ws))
			path=XH_CLOSE;
		String result = su.executeShell(path);
		return result;
//		try {
//			String result = su.executeShell(path);
//			return "1";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "2";
//		}
	}
	/**
	 * 接口执行测试
	 * @param inxml
	 * @param url
	 * @return
	 */
	public String wsExeTest(String inxml,String url){
		String outxml=WSStatusUtil.getUPP(inxml, url);
		if(outxml!=null){
			return outxml;
		}else{
			return "接口异常";
		}
	}
	/**
	 * 充值接口测试
	 * @param inxml
	 * @param url
	 * @return
	 */
	public String czExeTest(String inxml,String url){
		String outxml=WSStatusUtil.getCZ(inxml, url);
		if(outxml!=null){
			return outxml;
		}else{
			return "接口异常";
		}
	}
	/**
	 * 测试接口状态
	 * @param type
	 * @return
	 */
	public String wsTest(int type){
		String res="";
		String pre="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		if(type==1){
			res="<ContractRoot>\n<TcpCont>\n<TransactionID>1000000045201204166001052278</TransactionID>\n<ActionCode>0</ActionCode>" +
					"\n<BusCode>BUS80001</BusCode>\n<ServiceCode>SVC80002</ServiceCode>\n<ServiceContractVer></ServiceContractVer>\n" +
					"<ServiceLevel>1</ServiceLevel>\n<SrcOrgID>100000</SrcOrgID><SrcSysID>1000000037</SrcSysID><SrcSysSign></SrcSysSign>" +
					"<DstOrgID>600105</DstOrgID><DstSysID>6001050001</DstSysID><ReqTime>20120416114018</ReqTime></TcpCont><SvcCont>" +
					"<!--身份证号码--><IdentityCardNumber>35010319811201222</IdentityCardNumber><!--用户名称--><Name>李四</Name>" +
					"\n</SvcCont></ContractRoot>";
		}else if(type==2){
			res="<ContractRoot>\n<TcpCont>\n<TransactionID>1000000045201204160000692238</TransactionID><ActionCode>0</ActionCode>" +
					"<BusCode>BUS80001</BusCode><ServiceCode>SVC80003</ServiceCode><ServiceContractVer></ServiceContractVer>" +
					"<ServiceLevel>1</ServiceLevel><SrcOrgID>100000</SrcOrgID><SrcSysID>1000000037</SrcSysID>" +
					"<SrcSysSign></SrcSysSign><DstOrgID>600104</DstOrgID><DstSysID>6001040001</DstSysID>" +
					"<ReqTime>20120416114018</ReqTime></TcpCont><SvcCont><!-- 订单号Order_Code -->" +
					"<OrderId>600102310100003720120924003381</OrderId>\n</SvcCont>\n</ContractRoot>";
		}else if(type==3){
			res="<ContractRoot>\n<TcpCont>\n<TransactionID>1000000045201204160000692238</TransactionID><ActionCode>0</ActionCode>" +
					"<BusCode>BUS80001</BusCode><ServiceCode>SVC80005</ServiceCode><ServiceContractVer></ServiceContractVer>" +
					"<ServiceLevel>1</ServiceLevel><SrcOrgID>100000</SrcOrgID><SrcSysID>1000000037</SrcSysID><SrcSysSign></SrcSysSign>" +
					"<DstOrgID>600104</DstOrgID><DstSysID>6001040001</DstSysID><ReqTime>20120416114018</ReqTime>\n</TcpCont>\n<SvcCont>" +
					"<!-- 订单信息 -->\n<OrderStatusChangeInfo><!--订单ID--><OrderId>600102310100003720120924003384</OrderId>" +
					"<!--订单状态编码--><StatusCode>10108</StatusCode><!--订单状态变更时间-->" +
					"<StatusChangeTime>2012-10-10 10:20:10.100</StatusChangeTime></OrderStatusChangeInfo><!-- 订单信息 -->" +
					"<OrderStatusChangeInfo><!--订单ID--><OrderId>600102310100003720120924003383</OrderId><!--订单状态编码-->" +
					"<StatusCode>10107</StatusCode><!--订单状态变更时间--><StatusChangeTime>2012-10-10 10:20:10.100</StatusChangeTime>" +
					"</OrderStatusChangeInfo>\n</SvcCont>\n</ContractRoot>";
		}
		return pre+res;
	}
	/**
	 * update user info;
	 * @param bean
	 * @param updateUser
	 * @return
	 */
	public String updateUser(UserManageBean bean,long updateUser) {
		try {
			if(bean.getUserId()!=null){
				userMngService.updateUserInfo(bean, updateUser);
			}else{
				if(userMngService.hasUserMail(bean.getUserMail())){
					return "3";
				}
				if(userMngService.hasLoginName(bean.getUserName())){
					return "2";
				}
				SyUserInfo user=new SyUserInfo();
				user.setUserName(bean.getUserName());
				user.setUserRealname(bean.getUserRealname());
				user.setTelephone(bean.getTelephone());
				String newPwd=Cryto.cryptMD5ToHEX(bean.getUserPwd());
				user.setUserPwd(newPwd);
				user.setUserMail(bean.getUserMail());
				user.setUserStatus(bean.getUserStatus());
				user.setUpdateDate(new Date());
				userMngService.saveUserInfo(user, updateUser);
				bean.setUserId(user.getUserId());
				
				ShStoreUserRelationId ids=new ShStoreUserRelationId();
				ids.setUserId(bean.getUserId());
				ids.setStoreId(1L);
				ShStoreUserRelation storeRe=new ShStoreUserRelation();
					//storeRe = userMngService.queryStoreById(ids);
					storeRe.setId(ids);
					storeRe.setUserStoreRoleId(bean.getRoleId());
					storeRe.setUpdateDate(new Date());
					storeRe.setUserStatus(bean.getUserStatus());
					storeRe.setUpdateUser(updateUser);
					storeRe.setAddDate(new Date());
					userMngService.saveStoreInfo(storeRe);
				//userMngService.updateUserInfo(bean, updateUser);
			}
			return "1";
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "0";
	}
	/**
	 * Access to user information
	 * @param userId
	 * @param storeId
	 * @return
	 */
	public UserManageBean getUserInfo(long userId,long storeId) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId+"");
			params.put("storeId", storeId+"");
			List<UserManageBean> list = userMngService.queryUserInfo(params);
			UserManageBean ban=list.get(0);	
			return ban;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Get a list of roles
	 * @return
	 */
	public List<SyRoleInfo> getRoleList(){
		Map<String, String> params = new HashMap<String, String>();
		//params.put("parentType", "0");
		try {
			List<SyRoleInfo> list = userMngService.query(params);
			return list;
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Get backstage role list
	 * @return
	 */
	public List<SyRoleInfo> getAdminRoleList(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("roleType", "1");
		try {
			List<SyRoleInfo> list = userMngService.query(params);
			return list;
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * delete user;
	 * @param userId
	 * @param storeId
	 * @return
	 */
	public boolean delUser(long userId,long storeId){
		try {
			userMngService.delUserInfo(userId,storeId);
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Update the role
	 * @param bean
	 * @param updateUser
	 * @return
	 */
	public String updateRoleInfo(SyRoleInfo bean,long updateUser) {
		try {
			if(roleMngService.hasRoleName(bean.getRoleName())){
				return "2";
			}
			if(bean.getRoleId()==null){
				bean.setRoleType(1L);
				bean.setUpdateUser(updateUser);
				bean.setUpdateDate(new Date());
				roleMngService.saveRoleInfo(bean);
			}else{
				roleMngService.updateRoleInfo(bean, updateUser);
			}
			return "1";
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "0";
	}
	/**
	 * 更新商家角色
	 * @param bean
	 * @param updateUser
	 * @return
	 */
	public String updateBusiRoleInfo(SyRoleInfo bean,long updateUser) {
		try {
			if(roleMngService.hasBusiRoleName(bean.getRoleName())){
				return "2";
			}
			if(bean.getRoleId()==null){
				bean.setRoleType(2L);
				bean.setUpdateUser(updateUser);
				bean.setUpdateDate(new Date());
				roleMngService.saveRoleInfo(bean);
			}else{
				roleMngService.updateRoleInfo(bean, updateUser);
			}
			return "1";
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "0";
	}
	/**
	 * delete role;
	 * @param roleId
	 * @return
	 */
	public int delRoleInfo(long roleId){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("roleId", roleId+"");
			List<UserManageBean> list=userMngService.queryUserInfo(params);
			if(list.size()>0){
				return 2;
			}else{
				roleMngService.delRoleInfo(roleId);
				return 1;//true;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return 0;//false;
	}
	/**
	 * access the role;
	 * @param roleId
	 * @return
	 */
	public SyRoleInfo getRoleInfo(long roleId) {
		try {
			SyRoleInfo bean=roleMngService.queryById(roleId);
			return bean;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//权限管理
	public UserMenuBean getUserMenuInfo(long userId,long relationType,long menuId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("userroleId", userId+"");
		params.put("menuId", menuId+"");
		params.put("relationType", relationType+"");
		try {
			UserMenuBean bean= powerMngService.queryById(params);
			return bean;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//权限管理
	public UserMenuBean getRoleMenuInfo(long userId,long relationType,long menuId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("userroleId", userId+"");
		params.put("menuId", menuId+"");
		params.put("relationType", relationType+"");
		try {
			UserMenuBean bean= powerMngService.queryRoleMenuById(params);
			return bean;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Delete the user context menu
	 * @param userId
	 * @param relationType
	 * @param menuId
	 * @return
	 */
	public boolean delUserMenu(long userId,long relationType,long menuId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("userroleId", userId+"");
		params.put("menuId", menuId+"");
		params.put("relationType", relationType+"");
		try {
			SyRoleMenuRelation rela=powerMngService.queryRelationById(params);
			powerMngService.delUserMenu(rela);
			return true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Update the user context menu
	 * @param bean
	 * @param addOrUpd
	 * @return
	 */
	public boolean updateUserMenu(UserMenuBean bean,int addOrUpd) {
		try {
			if(addOrUpd==1){//add
				SyRoleMenuRelation rela=new SyRoleMenuRelation();
				SyRoleMenuRelationId id=new SyRoleMenuRelationId();
				id.setMenuId(bean.getMenuId());
				id.setRelationType(bean.getRelationType());
				id.setUserroleId(bean.getUserroleId());
				rela.setId(id);
				rela.setIsActive(bean.getIsActive());
				rela.setUpdateDate(new Date());
				rela.setUpdateUser(bean.getUpdateUser());
				powerMngService.save(rela);
			}else{//update
				powerMngService.updateUserMenu(bean);
			}
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * get the menu 
	 * @param menuId
	 * @return
	 */
	public SyMenuList getMenuInfo(long menuId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("menuId", menuId+"");
		try {
			SyMenuList bean= menuService.queryById(menuId);
			return bean;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * delete menu;
	 * @param menuId
	 * @return
	 */
	public int delMenu(long menuId){
		try {
			SyMenuList menu=menuService.queryById(menuId);
//			if("30".equals(menu.getMenuType().toString())){
//				menuService.delMenu(menu);
//				//menuService.delMenuRelatin(menu.getMenuId().toString());
//				return 1;
//			}else{
				List<SyRoleMenuRelation> list=menuService.queryMenuRelation(menuId+"");
				if(list.size()>0){
					return 2;
				}else{
					menuService.delMenu(menu);
					//menuService.delMenuRelatin(menu.getMenuId().toString());
					return 1;
				}
//			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * impower
	 * @param ids
	 * @param roleId
	 * @param updateUser
	 * @return 
	 */
	public boolean impower(String ids,long roleId,long updateUser){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("relationType", 1+"");
			params.put("userroleId", roleId+"");
			List<SyRoleMenuRelation> dblist=powerMngService.queryRoleRelation(params);
			List<SyRoleMenuRelation> both=new  ArrayList<SyRoleMenuRelation>();
			//List<SyRoleMenuRelation> predel=new  ArrayList<SyRoleMenuRelation>();
			String[] menuIds=ids.split(",");
			List<String> newIds=new ArrayList<String>();
			List<SyRoleMenuRelation> presave=new  ArrayList<SyRoleMenuRelation>();
			for (int i = 0; i < dblist.size(); i++) {
				for (int j = 0; j < menuIds.length; j++) {
					String temp=dblist.get(i).getId().getMenuId().toString();
					if(menuIds[j].equals(temp)){
						both.add(dblist.get(i));
						newIds.add(temp);
					}
				}
			}
			//you de bu dong;meiyou de save
			dblist.removeAll(both);
			if(!"".equals(ids)&&(ids!=null)){
				for(int i=0;i<menuIds.length;i++){
					if(!newIds.contains(menuIds[i])){
						SyRoleMenuRelation rela=new SyRoleMenuRelation();
						SyRoleMenuRelationId id=new SyRoleMenuRelationId();
						id.setMenuId(new Long(menuIds[i]));
						id.setRelationType(1L);
						id.setUserroleId(roleId);
						rela.setId(id);
						rela.setUpdateDate(new Date());
						rela.setUpdateUser(updateUser);
						presave.add(rela);
						//powerMngService.save(rela);
					}
				}
				powerMngService.saveRoleRelation(presave);
			}
			powerMngService.deleteRoleRelation(dblist);
			return true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Businesses authorized
	 * @param ids
	 * @param roleId
	 * @param updateUser
	 * @return
	 */
	public boolean busiImpower(String ids,long roleId,long updateUser){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("relationType", 1+"");
			params.put("userroleId", roleId+"");
			List<SyRoleMenuRelation> dblist=powerMngService.queryRoleRelation(params);
			List<SyRoleMenuRelation> both=new  ArrayList<SyRoleMenuRelation>();
			//List<SyRoleMenuRelation> predel=new  ArrayList<SyRoleMenuRelation>();
			String[] menuIds=ids.split(",");
			List<String> newIds=new ArrayList<String>();
			List<SyRoleMenuRelation> presave=new  ArrayList<SyRoleMenuRelation>();
			for (int i = 0; i < dblist.size(); i++) {
				for (int j = 0; j < menuIds.length; j++) {
					String temp=dblist.get(i).getId().getMenuId().toString();
					if(menuIds[j].equals(temp)){
						both.add(dblist.get(i));
						newIds.add(temp);
					}
				}
			}
			//you de bu dong;meiyou de save
			dblist.removeAll(both);
			if(!"".equals(ids)&&(ids!=null)){
				for(int i=0;i<menuIds.length;i++){
					if(!newIds.contains(menuIds[i])){
						SyRoleMenuRelation rela=new SyRoleMenuRelation();
						SyRoleMenuRelationId id=new SyRoleMenuRelationId();
						id.setMenuId(new Long(menuIds[i]));
						//id.setRelationType(2L);
						id.setRelationType(1L);
						id.setUserroleId(roleId);
						rela.setId(id);
						rela.setUpdateDate(new Date());
						rela.setUpdateUser(updateUser);
						presave.add(rela);
						//powerMngService.save(rela);
					}
				}
				powerMngService.saveRoleRelation(presave);
			}
			powerMngService.deleteRoleRelation(dblist);
			return true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * update the menu;
	 * @param bean
	 * @param addOrUpd
	 * @return
	 */
	public boolean updateMenu(SyMenuList bean,int addOrUpd) {
		try {
			if(addOrUpd==1){//add
				bean.setWebName("admin");
				menuService.save(bean);
			}else{//update
				menuService.updateMenu(bean);
			}
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * update business menu;
	 * @param bean
	 * @param addOrUpd
	 * @return
	 */
	public boolean updateBusiMenu(SyMenuList bean,int addOrUpd) {
		try {
			if(addOrUpd==1){//add
				bean.setWebName("business");
				menuService.save(bean);
			}else{//update
				menuService.updateMenu(bean);
			}
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Get the task object
	 * @param schid
	 * @return
	 */
	public SchInfoBean getSchInfo(String schid) {
		try {
			SchInfoBean bean=schinfoService.queryById(schid);	
			return bean;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * update the task;
	 * @param bean
	 * @return
	 */
	public String updateSchInfo(SchInfoBean bean){
	    String ret="1";
	    try {
		schinfoService.updateSchInfo(bean);
	    } catch (ServiceException e) {
		e.printStackTrace();
		ret="0";
	    }
	    return ret;
	}
	/**
	 * Get task config ;
	 * @param schid
	 * @return
	 */
	public AnewDzCfgBean getSchInfoAndFile(String schid){
		try {
			AnewDzCfgBean bean=new AnewDzCfgBean();
			SchInfoBean info=schinfoService.queryById(schid);
			bean.setSchClass(info.getSchClass());
			bean.setCronExp(info.getCronExp());
			bean.setInterval(info.getInterval());
			bean.setIsFile(info.getIsFile());
			bean.setMemo(info.getMemo());
			bean.setSchid(info.getSchid());
			bean.setSchname(info.getSchname());
			bean.setSchRepeat(info.getSchRepeat());
			bean.setSchtime(info.getSchtime());
			bean.setSchtimeend(info.getSchtimeend());
			bean.setSchType(info.getSchType());
			bean.setSendSch(info.getSendSch());
			bean.setStatus(info.getStatus());
			bean.setUnittype(info.getUnittype());
			if("5".equals(bean.getSchid())){
				try {
					SySchFile ff=flyService.querySchFileById(schid);
					DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");// SimpleDateFormat("yyyy-MM-dd");
			    	String str=sdf.format(ff.getDoDate());
			    	bean.setDoDateStr(str);
			    	//bean.setDoDate(dd);
			    	bean.setFilePath(ff.getFilePath());
			    	bean.setFileType(ff.getFileType());
			    	bean.setFtpIp(ff.getFtpIp());
			    	bean.setFtpPort(ff.getFtpPort());
			    	bean.setFtpPwd(ff.getFtpPwd());
			    	bean.setFtpUser(ff.getFtpUser());
			    	bean.setIsLocal(ff.getIsLocal());
			    	bean.setIsReload(ff.getIsReload());
			    	bean.setRowLine(ff.getRowLine());
			    	bean.setUpdatedDate(ff.getUpdatedDate());
			    	bean.setFileName(ff.getFileName());
			    	bean.setSchId(ff.getSchId());
				} catch (ServiceException e) {
					e.printStackTrace();
					return null;
				}
			}
			
			return bean;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * update the task config;
	 * @param bean
	 * @param ddd
	 * @return
	 */
	public String updateSchInfoAndFile(AnewDzCfgBean bean,String ddd){
		SchInfoBean info=new SchInfoBean();
		info.setCronExp(bean.getCronExp());
		info.setInterval(bean.getInterval());
		info.setIsFile(bean.getIsFile());
		info.setMemo(bean.getMemo());
		info.setSchClass(bean.getSchClass());
		info.setSchid(bean.getSchid());
		info.setSchname(bean.getSchname());
		info.setSchRepeat(bean.getSchRepeat());
		info.setSchtime(bean.getSchtime());
		info.setSchtimeend(bean.getSchtimeend());
		info.setSchType(bean.getSchType());
		info.setSendSch(bean.getSendSch());
		info.setStatus(bean.getStatus());
		info.setUnittype(bean.getUnittype());
		String ret="1";
	    try {
	    	schinfoService.updateSchInfo(info);
	    } catch (ServiceException e) {
	    	e.printStackTrace();
	    	ret="0";
	    	return ret;
	    }
		if("5".equals(bean.getSchid())){
		  try {
			//SySchFile file=new SySchFile();
			SySchFile file=flyService.querySchFileById(bean.getSchid());
			if(file==null){
				file=new SySchFile();
				file.setSchId(bean.getSchId());
			}
	    	DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");// SimpleDateFormat("yyyy-MM-dd");
	    	Date dd=sdf.parse(ddd);
	    	@SuppressWarnings("unused")
			String str=sdf.format(dd);
	    	file.setDoDate(dd); 
	    	file.setFilePath(bean.getFilePath());
	    	file.setFileType(bean.getFileType());
	    	file.setFtpIp(bean.getFtpIp());
	    	file.setFtpPort(bean.getFtpPort());
	    	file.setFtpPwd(bean.getFtpPwd());
	    	file.setFtpUser(bean.getFtpUser());
	    	file.setIsLocal(bean.getIsLocal());
	    	file.setIsReload(bean.getIsReload());
	    	file.setRowLine(bean.getRowLine());
	    	file.setUpdatedDate(new Date());
	    	file.setFileName(bean.getFileName());
	    	flyService.saveSchFile(file);
			} catch (Exception e) {
				e.printStackTrace();
		    	ret="0";
			} 
		}
		return ret;
	}
	/**
	 * update the task file;
	 * @param bean
	 * @param ddd
	 * @return
	 */
	public String updateSchFileInfo(SySchFileBean bean,String ddd){
	    String ret="1";
	    try {
	    	SySchFile file=new SySchFile();
	    	DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");// SimpleDateFormat("yyyy-MM-dd");
	    	Date dd=sdf.parse(ddd);
	    	@SuppressWarnings("unused")
			String str=sdf.format(dd);

	    	file.setDoDate(dd);
	    	file.setFilePath(bean.getFilePath());
	    	file.setFileType(bean.getFileType());
	    	file.setFtpIp(bean.getFtpIp());
	    	file.setFtpPort(bean.getFtpPort());
	    	file.setFtpPwd(bean.getFtpPwd());
	    	file.setFtpUser(bean.getFtpUser());
	    	file.setIsLocal(bean.getIsLocal());
	    	file.setIsReload(bean.getIsReload());
	    	file.setRowLine(bean.getRowLine());
	    	file.setUpdatedDate(new Date());
	    	//file.setUpdatedUser(updatedUser);
	    	//SySchFileId id=new SySchFileId();
	    	//id.setFileName(bean.getFileName());
	    	//id.setSchId(bean.getSchId());
	    	file.setFileName(bean.getFileName());
	    	file.setSchId(bean.getSchId());
	    	
	    	flyService.saveSchFile(file);
	    } catch (ServiceException e) {
		e.printStackTrace();
		ret="0";
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ret;
	}
	/**
	 * Get the file infomation;
	 * @param schid
	 * @return
	 */
	public SySchFile getSchFileInfo(String schid) {
		try {
			SySchFile bean=flyService.querySchFileById(schid);
			DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");// SimpleDateFormat("yyyy-MM-dd");
	    	String str=sdf.format(bean.getDoDate());
	    	bean.setDoDateStr(str);
			return bean;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	public FlyCheckService getFlyService() {
		return flyService;
	}

	public void setFlyService(FlyCheckService flyService) {
		this.flyService = flyService;
	}

	public WsMngService getWsService() {
		return wsService;
	}
	public void setWsService(WsMngService wsService) {
		this.wsService = wsService;
	}
	public UserManageService getUserMngService() {
		return userMngService;
	}

	public void setUserMngService(UserManageService userMngService) {
		this.userMngService = userMngService;
	}

	public RoleManageService getRoleMngService() {
		return roleMngService;
	}

	public void setRoleMngService(RoleManageService roleMngService) {
		this.roleMngService = roleMngService;
	}

	public PowerMngService getPowerMngService() {
		return powerMngService;
	}

	public void setPowerMngService(PowerMngService powerMngService) {
		this.powerMngService = powerMngService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

        /**
         * @return the schinfoService
         */
        public SchInfoService getSchinfoService() {
    	return schinfoService;
        }
    
        /**
         * @param schinfoService
         *            the schinfoService to set
         */
        public void setSchinfoService(SchInfoService schinfoService) {
    	this.schinfoService = schinfoService;
        }

		public OrderInfoService getOrderService() {
			return orderService;
		}

		public void setOrderService(OrderInfoService orderService) {
			this.orderService = orderService;
		}

		/**
		 * @return the codeService
		 */
		public ModuleCodeService getCodeService() {
			return codeService;
		}

		/**
		 * @param codeService the codeService to set
		 */
		public void setCodeService(ModuleCodeService codeService) {
			this.codeService = codeService;
		}
	
}
