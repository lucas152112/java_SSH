/**
 * SmsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.oim.stores.web.sendSms;
/**
 * 短信service
 * @author yuyan
 *
 */
public interface SmsService extends javax.xml.rpc.Service {
    /**
     * 获取HTTP端口地址
     * @return
     */
	public java.lang.String getSmsServiceHttpPortAddress();
	/**
	 * 获取端口
	 * @return
	 * @throws javax.xml.rpc.ServiceException
	 */
    public SmsServicePortType getSmsServiceHttpPort() throws javax.xml.rpc.ServiceException;
    /**
     * 根据参数获取端口
     * @param portAddress
     * @return
     * @throws javax.xml.rpc.ServiceException
     */
    public SmsServicePortType getSmsServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
