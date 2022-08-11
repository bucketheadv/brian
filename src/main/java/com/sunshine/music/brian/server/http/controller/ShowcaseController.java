package com.sunshine.music.brian.server.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.http.server.annotation.RequestPath;
import org.tio.http.server.util.Resps;

/**
 * @author qinglinl
 * Created on 2022/6/28 4:45 下午
 */
@Slf4j
@RequestPath("/")
public class ShowcaseController {
    @RequestPath("404")
    public HttpResponse page404(HttpRequest request) throws Exception {
        return Resps.html(request, "没找到你要的页面");
    }

    @RequestPath("500")
    public HttpResponse page500(HttpRequest request) throws Exception {
        return Resps.html(request, "服务器内部错误");
    }

    @RequestPath("test")
    public HttpResponse abtest(HttpRequest request) throws Exception {
        return Resps.html(request, "OK");
    }
}
