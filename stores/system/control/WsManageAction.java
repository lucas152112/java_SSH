package com.oim.stores.system.control;

import java.io.DataInputStream;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.ShellUtils;
/**
 * 接口进程管理action
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class WsManageAction extends BaseAction {
    private String shellCmd;//shell命令
    private String fileName;//文件名

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the shellCmd
     */
    public String getShellCmd() {
	return shellCmd;
    }

    /**
     * @param shellCmd
     *            the shellCmd to set
     */
    public void setShellCmd(String shellCmd) {
	this.shellCmd = shellCmd;
    }
    /**
     * 执行shell
     * @return
     */
    public String execShell() {
	ShellUtils su = new ShellUtils();
	String result = su.executeShell(shellCmd);
	request.setAttribute("result", String.valueOf(result));
	return SUCCESS;
    }
    /**
     * download log
     * @return
     */
    public String downLog() {
	try {
	    FileInputStream fis = new FileInputStream(fileName);
	    ServletOutputStream sos = response.getOutputStream();
	    String fileType = fileName.substring(fileName.indexOf(".") + 1);
	    this.setFileType(response, fileType);
	    response.setHeader("Content-disposition", "attachment; filename="
		    + "catalina.txt");
	    byte[] buffer = new byte[2 * 1024];
	    DataInputStream dis = new DataInputStream(fis);
	    int i = dis.read(buffer, 0, buffer.length);
	    while (i != -1) {
		sos.write(buffer, 0, i);
		sos.flush();
		i = dis.read(buffer, 0, buffer.length);
	    }
	    response.flushBuffer();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return SUCCESS;
    }
    /**
     * 设置文件类型信息
     * @param response
     * @param fileType
     */
    private void setFileType(HttpServletResponse response, String fileType) {
	if (fileType.equalsIgnoreCase("xls"))
	    response.setContentType("application/vnd.ms-excel");
	else if (fileType.equalsIgnoreCase("doc"))
	    response.setContentType("application/msword");
	else if (fileType.equalsIgnoreCase("ppt"))
	    response.setContentType("application/vnd.ms-powerpoint");
	else if (fileType.equalsIgnoreCase("pdf"))
	    response.setContentType("application/pdf");
	else if (fileType.equalsIgnoreCase("gif"))
	    response.setContentType("image/gif");
	else if (fileType.equalsIgnoreCase("jpg"))
	    response.setContentType("image/jpeg");
	else if (fileType.equalsIgnoreCase("png"))
	    response.setContentType("image/png");
	else if (fileType.equalsIgnoreCase("bmp"))
	    response.setContentType("image/x-xbitmap");
	else if (fileType.equalsIgnoreCase("zip"))
	    response.setContentType("application/zip");
	else
	    response.setContentType("appliation/octet-stream");
    }
}
