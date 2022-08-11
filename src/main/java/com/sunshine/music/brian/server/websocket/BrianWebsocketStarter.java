package com.sunshine.music.brian.server.websocket;

import com.sunshine.music.brian.server.http.HttpServerInit;
import org.tio.server.TioServerConfig;
import org.tio.utils.jfinal.P;
import org.tio.websocket.server.WsServerStarter;

/**
 * @author qinglinl
 * Created on 2022/6/28 5:28 下午
 */
public class BrianWebsocketStarter {
    private WsServerStarter wsServerStarter;
    private TioServerConfig tioServerConfig;

    public BrianWebsocketStarter(int port, BrianWsMsgHandler wsMsgHandler) throws Exception {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);

        tioServerConfig = wsServerStarter.getTioServerConfig();
        tioServerConfig.setName(BrianServerConfig.PROTOCOL_NAME);
        tioServerConfig.setTioServerListener(BrianServerAioListener.me);

        // Ip监控
        tioServerConfig.setIpStatListener(BrianIpStatListener.me);
        // ip统计时间段
        tioServerConfig.ipStats.addDurations(BrianServerConfig.IpStatDuration.IPSTAT_DURATIONS);
        tioServerConfig.setHeartbeatTimeout(BrianServerConfig.HEARTBEAT_TIMEOUT);

        if (P.getInt("ws.use.ssl", 1) == 1) {
            String keyStoreFile = P.get("ssl.keystore", null);
            String trustStoreFile = P.get("ssl.truststore", null);
            String keyStorePwd = P.get("ssl.pwd", null);
            tioServerConfig.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
        }
    }

    public static void start() throws Exception {
        BrianWebsocketStarter appStarter = new BrianWebsocketStarter(BrianServerConfig.SERVER_PORT, BrianWsMsgHandler.me);
        appStarter.wsServerStarter.start();
    }

    public TioServerConfig getTioServerConfig() {
        return tioServerConfig;
    }

    public WsServerStarter getWsServerStarter() {
        return wsServerStarter;
    }

    public static void main(String[] args) throws Exception {
        P.use("app.properties");

        if (P.getInt("start.http", 1) == 1) {
            HttpServerInit.init();
        }

        start();
    }
}
