package com.sunshine.music.brian.server.http;

import lombok.extern.slf4j.Slf4j;
import org.tio.core.ssl.SslUtils;
import org.tio.http.common.HttpConfig;
import org.tio.http.common.handler.HttpRequestHandler;
import org.tio.http.server.HttpServerStarter;
import org.tio.http.server.handler.DefaultHttpRequestHandler;
import org.tio.server.TioServerConfig;
import org.tio.utils.jfinal.P;

/**
 * @author qinglinl
 * Created on 2022/6/28 4:32 下午
 */
@Slf4j
public class HttpServerInit {
    private static HttpConfig httpConfig;

    private static HttpRequestHandler requestHandler;

    private static HttpServerStarter httpServerStarter;

    private static TioServerConfig tioServerConfig;

    public static void init() throws Exception {
        long start = System.currentTimeMillis();
        int port = P.getInt("http.port");
        String pageRoot = P.get("http.page");
        httpConfig = new HttpConfig(port, null, null, null);
        httpConfig.setPageRoot(pageRoot);
        httpConfig.setPage404(P.get("http.404"));
        httpConfig.setPage500(P.get("http.500"));
        httpConfig.setUseSession(false);
        httpConfig.setCheckHost(false);

        requestHandler = new DefaultHttpRequestHandler(httpConfig, TioServerStarter.class);
        httpServerStarter = new HttpServerStarter(httpConfig, requestHandler);
        tioServerConfig = httpServerStarter.getTioServerConfig();
        httpServerStarter.start();

        String protocol = SslUtils.isSsl(tioServerConfig) ? "https" : "http";
        long iv = System.currentTimeMillis() - start;
        if (log.isInfoEnabled()) {
            log.info("Tio Http Server启动完毕,耗时:{}ms, 访问地址: {}://127.0.0.1:{}", iv, protocol, port);
        } else {
            System.out.println("\r\nTio Http Server启动完毕,耗时: " + iv + "ms, 访问地址: " + protocol + "://127.0.0.1:" + port);
        }
    }
}
