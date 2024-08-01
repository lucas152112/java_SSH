/**
 * SmsServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.oim.stores.web.sendSms;
/**
 * 短信接口类型
 * @author yuyan
 *
 */
public interface SmsServicePortType extends java.rmi.Remote {
	/**
	 * 发送短信
	 * @param in0
	 * @param in1
	 * @param in2
	 * @param in3
	 * @param in4
	 * @return
	 * @throws java.rmi.RemoteException
	 */
    public int sendSms(java.lang.String in0, java.lang.String in1, int in2, java.lang.String in3, int in4) throws java.rmi.RemoteException;
}
