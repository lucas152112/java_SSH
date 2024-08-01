/**
 * SmsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.oim.stores.web.sendSms;
/**
 * 短信service实现类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SmsServiceLocator extends org.apache.axis.client.Service implements SmsService {
	/**
	 * 默认构造器
	 */
    public SmsServiceLocator() {
    }

    /**
     * 带参构造器
     * @param config
     */
    public SmsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }
    /**
     * 参数构造器
     * @param wsdlLoc
     * @param sName
     * @throws javax.xml.rpc.ServiceException
     */
    public SmsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SmsServiceHttpPort
    private java.lang.String SmsServiceHttpPort_address = "http://sms.59ibuy.com.cn:8080/CarnivalSMSinf/services/SmsService";

    public java.lang.String getSmsServiceHttpPortAddress() {
        return SmsServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SmsServiceHttpPortWSDDServiceName = "SmsServiceHttpPort";

    public java.lang.String getSmsServiceHttpPortWSDDServiceName() {
        return SmsServiceHttpPortWSDDServiceName;
    }

    public void setSmsServiceHttpPortWSDDServiceName(java.lang.String name) {
        SmsServiceHttpPortWSDDServiceName = name;
    }
    /**
     * Get default port;
     */
    public SmsServicePortType getSmsServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SmsServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSmsServiceHttpPort(endpoint);
    }
    /**
     * Get port;
     */
    public SmsServicePortType getSmsServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            SmsServiceHttpBindingStub _stub = new SmsServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getSmsServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }
    /**
     * Set http address
     * @param address
     */
    public void setSmsServiceHttpPortEndpointAddress(java.lang.String address) {
        SmsServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("unchecked")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SmsServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                SmsServiceHttpBindingStub _stub = new SmsServiceHttpBindingStub(new java.net.URL(SmsServiceHttpPort_address), this);
                _stub.setPortName(getSmsServiceHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("unchecked")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SmsServiceHttpPort".equals(inputPortName)) {
            return getSmsServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }
    /**
     * Get service name;
     */
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vivianj.org/", "SmsService");
    }

    @SuppressWarnings("unchecked")
	private java.util.HashSet ports = null;
    /**
     * Get ports;
     */
    @SuppressWarnings("unchecked")
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vivianj.org/", "SmsServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SmsServiceHttpPort".equals(portName)) {
            setSmsServiceHttpPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
