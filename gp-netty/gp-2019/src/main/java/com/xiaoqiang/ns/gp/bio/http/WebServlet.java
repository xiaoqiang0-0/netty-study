package com.xiaoqiang.ns.gp.bio.http;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface WebServlet {
    String name();
}
