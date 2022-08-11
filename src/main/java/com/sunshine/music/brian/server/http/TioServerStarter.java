package com.sunshine.music.brian.server.http;

import org.tio.utils.jfinal.P;

/**
 * @author qinglinl
 * Created on 2022/6/28 4:25 下午
 */
public class TioServerStarter {
    public static void main(String[] args) throws Exception {
        P.use("app.properties");
        HttpServerInit.init();
    }
}
