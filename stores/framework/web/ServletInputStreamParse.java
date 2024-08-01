package com.oim.stores.framework.web;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.ServletInputStream;

//����ʽ����ĸ�ʽ
// Boundary
// Content-Disposition: form-data; name="field1"; filename="file1.txt"
// Content-Type: type/subtype
// Content-Transfer-Encoding: binary

/**
 * 对servlet文件流对象的封装和解析
 * @author iampc
 *
 */
public class ServletInputStreamParse {

	private String encoding = "UTF-8";

	private HttpServletRequest request;

	private ServletInputStream in;

	private String boundary;

	public ServletInputStreamParse(HttpServletRequest arequest)
			throws IOException {
		this.request = arequest;
		this.in = arequest.getInputStream();
		this.boundary = this.extractBoundary(arequest);
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getEncoding() {
		return this.encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Map<String, HttpRequestObject> getHttpRequestObject() throws Exception {
		String line = this.extractFirstBoundary();
		Map<String, String> map = new HashMap<String, String>();
		Map<String, HttpRequestObject> linkmap = new LinkedHashMap<String, HttpRequestObject>();
		int lsh = 0;
		while (true) { 
			lsh++; 
			map = this.extractDataHead(line);
			if (map == null)
				break;
			// ���һ��\r\n
			this.readLine(in);
			// ��ȡ�ò���ŵ���������
			HttpRequestObject httpobj = new HttpRequestObject();
			httpobj.setContentType((String) map.get("contenttype"));
			httpobj.setDisposition((String) map.get("disposition"));
			httpobj.setFilename((String) map.get("filename"));
			httpobj.setName((String) map.get("name"));
			httpobj.setOriginFilename((String) map.get("origname"));
			String paramtype = (String) map.get("paramtype");
			httpobj.setParamType(paramtype);
			ParamPart aa = new ParamPart(in, boundary, this.encoding);
			byte[] value = null;
			if (!"file".equalsIgnoreCase(paramtype))// ���ļ�ʱ����ת��
				value = aa.getStringValue().getBytes();
			else
				value = aa.getValue();
			httpobj.setValue(value);
			linkmap.put(Integer.toString(lsh), httpobj);
		}
		return linkmap;
	}

	private String extractFirstBoundary() throws IOException {
		String line = this.readLine(in);
		return line;
	}

	private Map<String, String> extractDataHead(String aBoundary) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String line = aBoundary;
		if (this.isBoundary(line, this.boundary)) {
			line = this.readLine(this.in);
			if (line == null)
				return null;
			line = new String(line.getBytes(), this.encoding);
			// System.out.println(line);
			map = extracDispositionInfo(line);
			String paramtype = (String) map.get("paramtype");
			if (paramtype.equalsIgnoreCase("file")) {
				line = this.readLine(this.in);
				line = new String(line.getBytes(), this.encoding);
				String contentType = this.extractContentType(line);
				contentType = new String(contentType.getBytes(), this.encoding);
				map.put("contenttype", contentType);
			} else {
				map.put("contenttype", null);
			} 
//			printMap(map);
			return map;
		} else {
			return null;
		}

	}

	/**
	 * �õ�������ķָ���� extractBoundary
	 * 
	 * @param request
	 *            WEB����
	 * @return �ָ����
	 * @throws IOException
	 * 
	 */
	private String extractBoundary(HttpServletRequest request)
			throws IOException {
		String type = this.extractType(request);
		return this.extractBoundary(type);
	}

	private String extractContentType(String line) {
		line = line.toLowerCase();
		int end = line.indexOf(";");
		if (end == -1) {
			end = line.length();
		}
		line = line.substring(13, end).trim();
		return line; // "content-type:" is 13
	}

	/**
	 * �õ�������������
	 * 
	 * @param request
	 *            WEB����
	 * @return ����
	 * @throws IOException
	 */
	private String extractType(HttpServletRequest request) throws IOException {
		String type = null;
		String type1 = request.getHeader("Content-Type");
		String type2 = request.getContentType();
		if (type1 == null && type2 != null) {
			type = type2;
		} else if (type2 == null && type1 != null) {
			type = type1;
		} else if (type1 != null && type2 != null) {
			type = (type1.length() > type2.length() ? type1 : type2);
		}
		if (type == null
				|| !type.toLowerCase().startsWith("multipart/form-data")) {
			throw new IOException(
					"Posted content type isn't multipart/form-data");
		}
		return type;
	}

	/**
	 * 
	 * @param aType
	 *            form����
	 * @return ��ݷָ���
	 */
	private String extractBoundary(String aType) {
		int index = aType.lastIndexOf("boundary=");
		if (index == -1) {
			return null;
		}
		String boundary = aType.substring(index + 9); // 9 for "boundary="
		if (boundary.charAt(0) == '"') {
			index = boundary.lastIndexOf('"');
			boundary = boundary.substring(1, index);
		}
		boundary = "--" + boundary;
		return boundary;
	}

	/**
	 * ���������м�õ�һ��
	 * 
	 * @param in
	 *            request.getInputStream
	 * @return һ�е��ַ�
	 * @throws IOException
	 */
	private String readLine(ServletInputStream in) throws IOException {
		byte[] buf = new byte[8 * 1024];
		StringBuffer sbuf = new StringBuffer();
		int result;
		// String line;
		do {
			result = in.readLine(buf, 0, buf.length); // does +=
			if (result != -1) {
				sbuf.append(new String(buf, 0, result, encoding));
			}
		} while (result == buf.length);
		if (sbuf.length() == 0) {
			return null;
		}
		int len = sbuf.length();
		if (len >= 2 && sbuf.charAt(len - 2) == '\r') {
			sbuf.setLength(len - 2); // cut \r\n
		} else if (len >= 1 && sbuf.charAt(len - 1) == '\n') {
			sbuf.setLength(len - 1); // cut \n
		}
		return sbuf.toString();
	}

	/**
	 * �ж��Ƿ�����ݷָ����
	 * 
	 * @param aLine
	 *            ���ַ�
	 * @param aBoundary
	 *            ��ݷָ����
	 * @return
	 */
	private boolean isBoundary(String aLine, String aBoundary) {
		if (aLine != null && aLine.startsWith(aBoundary)) {
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @param line
	 *            ���ַ�,����UTF-8
	 * @return ���ϴ��������Ϣ
	 *         <p>
	 *         disposition ����
	 *         </p>
	 *         <p>
	 *         name ����
	 *         </p>
	 *         <p>
	 *         filename �ļ���
	 *         </p>
	 *         <p>
	 *         origname Դ�ļ���
	 *         </p>
	 * @throws IOException
	 */
	private Map<String, String> extracDispositionInfo(String line) throws IOException {
		Map<String, String> retval = new HashMap<String, String>();
		String disposition = null;
		String name = null;
		String filename = null;
		String origname = null;
		String paramtype = "param";

		String origline = line;
		line = origline.toLowerCase();
		int start = line.indexOf("content-disposition: ");
		int end = line.indexOf(";");
		if (start == -1 || end == -1) {
			throw new IOException("Content disposition corrupt: " + origline);
		}
		disposition = line.substring(start + 21, end);
		if (!disposition.equals("form-data")) {
			throw new IOException("Invalid content disposition: " + disposition);
		}
		// Get the field name
		start = line.indexOf("name=\"", end); // start at last semicolon
		end = line.indexOf("\"", start + 7); // skip name=\"
		int startOffset = 6;
		if (start == -1 || end == -1) {
			start = line.indexOf("name=", end);
			end = line.indexOf(";", start + 6);
			if (start == -1) {
				throw new IOException("Content disposition corrupt: "
						+ origline);
			} else if (end == -1) {
				end = line.length();
			}
			startOffset = 5; // without quotes we have one fewer char to skip
		}
		name = origline.substring(start + startOffset, end);
		start = line.indexOf("filename=\"", end + 2);
		end = line.indexOf("\"", start + 10);
		if (start != -1 && end != -1) {
			filename = origline.substring(start + 10, end);
			origname = filename;
			paramtype = "file";
			int slash = Math.max(filename.lastIndexOf('/'), filename
					.lastIndexOf('\\'));
			if (slash > -1) {
				filename = filename.substring(slash + 1); // past last slash
			}
		}
		retval.put("disposition", disposition);
		retval.put("name", name);
		retval.put("filename", filename);
		retval.put("origname", origname);
		retval.put("paramtype", paramtype);
		return retval;
	}

	/**
	 * ��ӡMap�е�Key��Value
	 * 
	 * @param map
	 */
	public void printMap(HashMap map) {
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			System.out.println(key + "=" + (String) map.get(key));
		}
	}
	// end---------------------------------------------------------------------------
}