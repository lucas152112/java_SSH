package com.oim.stores.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * shell工具类
 * @author Administrator
 *
 */
public class ShellUtils {
	/**
	 * 执行shell命令
	 * @param shellCommand
	 * @return
	 */
    public String executeShell(String shellCommand) {
	@SuppressWarnings("unused")
	int success = 0;
	StringBuffer stringBuffer = new StringBuffer();
	BufferedReader bufferedReader = null;
	// 格式化日期时间，记录日志时使用
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");

	try {
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
	} catch (Exception ioe) {
	    stringBuffer.append("执行Shell命令时发生异常：\r\n").append(ioe.getMessage())
		    .append("\r\n");
	} 
	return stringBuffer.toString();
    }
}
