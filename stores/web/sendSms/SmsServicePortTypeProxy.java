package com.oim.stores.web.sendSms;
/**
 * 接口类型代理
 * @author yuyan
 *
 */
public class SmsServicePortTypeProxy implements SmsServicePortType {
  private String _endpoint = null;//终点
  private SmsServicePortType smsServicePortType = null;//端口类型;
  
  public SmsServicePortTypeProxy() {
    _initSmsServicePortTypeProxy();
  }
  
  public SmsServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmsServicePortTypeProxy();
  }
  /**
   * 初始化
   */
  private void _initSmsServicePortTypeProxy() {
    try {
      smsServicePortType = (new SmsServiceLocator()).getSmsServiceHttpPort();
      if (smsServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smsServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smsServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smsServicePortType != null)
      ((javax.xml.rpc.Stub)smsServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  /**
   * 获取端口类型
   * @return
   */
  public SmsServicePortType getSmsServicePortType() {
    if (smsServicePortType == null)
      _initSmsServicePortTypeProxy();
    return smsServicePortType;
  }
  /**
   * 发送短信
   */
  public int sendSms(java.lang.String in0, java.lang.String in1, int in2, java.lang.String in3, int in4) throws java.rmi.RemoteException{
    if (smsServicePortType == null)
      _initSmsServicePortTypeProxy();
    return smsServicePortType.sendSms(in0, in1, in2, in3, in4);
  }
  
  
}