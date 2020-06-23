package com.xiaoqiang.hss;

import java.util.concurrent.ConcurrentHashMap;

public class ResourceType {
    public static final String CSS = "css";
    public static final String JS = "js";
    public static final String HTML = "html";


    public static ConcurrentHashMap<String, String> typeOfContentType = new ConcurrentHashMap<>();

    static {
        typeOfContentType.put("css", "text/css; charset=utf-8");
        typeOfContentType.put("js", "text/javascript; charset=utf-8");
        typeOfContentType.put("html", "text/html; charset=utf-8");

    }

    public static String typeOf(String type) {
        return typeOfContentType.get(type);
    }
}
