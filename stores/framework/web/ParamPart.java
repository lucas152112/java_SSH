package com.oim.stores.framework.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletInputStream;

public class ParamPart {
    private byte[] value;

    private String encoding;

    ParamPart(ServletInputStream in,
              String boundary, String encoding) throws IOException {
        this.encoding = encoding;
        PartInputStream pis = new PartInputStream(in, boundary);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        byte[] buf = new byte[128];
        int read;
        while ( (read = pis.read(buf)) != -1) {
            baos.write(buf, 0, read);
        }
        pis.close();
        baos.close();
        value = baos.toByteArray();
    }
    public byte[] getValue() {
        return value;
    }
    public String getStringValue() throws UnsupportedEncodingException {
        return getStringValue(encoding);
    }
    public String getStringValue(String encoding) throws UnsupportedEncodingException {
        return new String(value, encoding);
    }
    public boolean isParam() {
        return true;
    }
}
