package com.sunshine.music.brian.server.websocket;

import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.WsTioServerListener;

/**
 * @author qinglinl
 * Created on 2022/6/28 5:30 下午
 */
@Slf4j
public class BrianServerAioListener extends WsTioServerListener {
    public static final BrianServerAioListener me = new BrianServerAioListener();

    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
        super.onAfterConnected(channelContext, isConnected, isReconnect);
    }

    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
        super.onBeforeClose(channelContext, throwable, remark, isRemove);
        log.info("onBeforeClose {}", channelContext);

        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
        if (wsSessionContext != null && wsSessionContext.isHandshaked()) {
            int count = Tio.getAll(channelContext.tioConfig).getObj().size();
            String msg = channelContext.getClientNode().toString() + " 离开了，现在共有[" + count + "]人在线";
            WsResponse wsResponse = WsResponse.fromText(msg, BrianServerConfig.CHARSET);
            Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);
        }
    }

    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
        super.onAfterDecoded(channelContext, packet, packetSize);
        log.info("onAfterDecoded {} {}", packet.logstr(), channelContext);
    }
}
