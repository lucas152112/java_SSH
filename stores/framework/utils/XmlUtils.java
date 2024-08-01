package com.oim.stores.framework.utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * xml文档工具类
 * @author zhuangjf
 *
 */
public class XmlUtils {

    public XmlUtils() {

    }

    /**
     * 从Request的路径建立DOM树,
     * 
     * @param request
     *            HttpServletRequest
     * @param fileWebPath
     *            String 例如 /doc/file.xml
     * @return Document
     */
    public static Document docFromRequest(HttpServletRequest request,
	    String fileWebPath) throws IOException, JDOMException {
	Document doc = new Document();
	InputStream in = request.getSession().getServletContext()
		.getResourceAsStream(fileWebPath);
	SAXBuilder builder = new SAXBuilder();
	doc = builder.build(in);
	return doc;
    }

    /**
     * dom转化成字符串
     * 
     * @param aDocument
     *            Document
     * @param aEncoding
     *            编码
     * @param aDefault
     *            Document==null时返回的字符串,aDefault==null则返回<root/>
     * @param xmlPrefix
     *            是否产生Xml前缀
     * @return
     * @throws IOException
     */
    public static String docToString(org.jdom.Document aDocument,
	    String aEncoding, String aDefault, boolean xmlPrefix)
	    throws IOException {
	if (aDocument == null)
	    return aDefault == null ? "<root/>" : aDefault;
	String xmlStr = null;
	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	Format format = outputter.getFormat();
	if (aEncoding != null)
	    format.setEncoding(aEncoding);
	format.setExpandEmptyElements(true);
	outputter.setFormat(format);

	xmlStr = outputter.outputString(aDocument);
	xmlStr = xmlPrefix == true ? xmlStr : xmlStr.substring(xmlStr
		.indexOf("?>") + 2);
	return xmlStr;
    }

    /**
     * 将xml格式的字符串转成document
     * 
     * @param aXmlData
     *            xml字符串
     * @return document 失败返回null
     * @throws IOException
     * @throws JDOMException
     */
    public static org.jdom.Document stringToDocument(String aXmlData)
	    throws JDOMException, IOException {
	//替换BOM头
	Pattern pattern;
	Matcher match;
	String reg = "^([\\W]+)<";
	pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
	match = pattern.matcher(aXmlData.trim());
	aXmlData = match.replaceFirst("<");
	//对象转换
	SAXBuilder builder = new SAXBuilder();
	Document document = null;
	Reader in = new StringReader(aXmlData);
	document = builder.build(in);
	return document;
    }

    /**
     * 功能: JDOM xml常用操作函数 1 由查询得到的ResultSet 生成 org.jdom.Document 2
     * 由org.jdom.Document 生成 String 3 由org.jdom.Document 生成 文件
     * 
     * @throws IOException
     */
    public static void docToFile(org.jdom.Document aDocument, String aFilename,
	    String aEncoding) throws IOException {
	FileWriter writer = new FileWriter(aFilename);
	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	Format format = outputter.getFormat();
	if (aEncoding != null)
	    format.setEncoding(aEncoding);
	format.setExpandEmptyElements(true);
	outputter.setFormat(format);
	outputter.output(aDocument, writer);
	writer.close();
    }

    /**
     * 从文件里面读取XML建立Document
     * 
     * @param fileName
     * @return Document
     * @throws FileNotFoundException
     * @throws IOException
     * @throws JDOMException
     */
    public static Document docFromFile(String fileName)
	    throws FileNotFoundException, IOException, JDOMException {
	InputStream in = new FileInputStream(fileName);
	SAXBuilder builder = new SAXBuilder();
	Document doc = builder.build(in);
	return doc;
    }

    /**
     * 从符合dom结构的单条记录提取属性的值
     * 
     * @param str
     * @param attrName
     * @return
     * @throws Exception
     */

    public static String getAttributeFromSingleRecordDom(String str,
	    String attrName) throws Exception {
	if (str.equals(""))
	    return "";
	String attrValue = "";
	Document doc = null;
	doc = XmlUtils.stringToDocument(str);
	Element root = doc.getRootElement();
	if (root.getChildren().size() == 0) {
	    return "";
	} else {
	    Element data = (Element) root.getChildren().get(0);
	    attrValue = data.getAttributeValue(attrName);
	    attrValue = attrValue == null ? "" : attrValue;
	}
	return attrValue;
    }

    public static void writeToFile(String aFileName, String content)
	    throws IOException {
	BufferedWriter out = new BufferedWriter(new FileWriter(aFileName));
	out.write(content == null ? "" : content);
	out.close();
    }

}
