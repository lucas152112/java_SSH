package com.oim.stores.framework.web;

/**
 * 封装请求对象
 * @author zhuangjf
 *
 */
public class HttpRequestObject {

    private String contentType;        //�ϴ��ļ�����
    private String disposition;        //����
    private String filename="";        //�ϴ��ļ���
    private String name;               //����
    private String originFilename="";  //�ϴ�Դ�ļ���
    private String paramtype;          //��files����param
    private byte[] value;              //�ļ����ֽڻ��߲�����ֽ�

    public HttpRequestObject() {
        //dfda
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }
 
    public String getDisposition() {
        return disposition;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOriginFilename(String originFilename) {
        this.originFilename = originFilename;
    }

    public String getOriginFilename() {
        return originFilename;
    }
    /**
     * 
     * @param paramtype  files/param
     */
    public void setParamType(String paramtype) {
        this.paramtype = paramtype;
    }
    
    public String getParamType() {
        return this.paramtype;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }

    public String toString(){
        String str;
        str ="contentType--->"+contentType+"\r\n";
        str = str+"disposition--->"+disposition+"\r\n";
        str = str+"filename--->"+filename+"\r\n";
        str = str+"name--->"+name+"\r\n";
        str = str+"originFilename--->"+originFilename+"\r\n";
        str = str+"paramtype--->"+paramtype+"\r\n";
        str = str+"value--->"+new String(value)+"\r\n";
        return str;
    }

}
