package com.sunshine.music.brian.server.websocket;

import org.tio.utils.time.Time;

/**
 * @author qinglinl
 * Created on 2022/6/28 4:55 下午
 */
public class BrianServerConfig {
    /**
     * 协议名称（随便取）
     */
    public static final String PROTOCOL_NAME = "brian";

    public static final String CHARSET = "utf-8";

    public static final int SERVER_PORT = 9326;

    /**
     * 心跳超时时间
     */
    public static int HEARTBEAT_TIMEOUT = 1000 * 60;

    /**
     * ip数据监控统计，时间段
     * @author tanyaowu
     *
     */
    public static interface IpStatDuration {
        public static final Long DURATION_1 = Time.MINUTE_1 * 5;
        public static final Long[] IPSTAT_DURATIONS = new Long[] { DURATION_1 };
    }
}
