package com.sunshine.music.brian.server.websocket;

import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Objects;

/**
 * @author qinglinl
 * Created on 2022/6/28 5:22 下午
 */
@Slf4j
public class BrianWsMsgHandler implements IWsMsgHandler {
    public static final BrianWsMsgHandler me = new BrianWsMsgHandler();
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String clientIp = httpRequest.getClientIp();
        String myname = httpRequest.getParam("name");
        Tio.bindUser(channelContext, myname);
        log.info("收到来自{}的ws握手包\r\n{}", clientIp, httpRequest.toString());
        return httpResponse;
    }

    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        Tio.bindGroup(channelContext, Const.GROUP_ID);
        int count = Tio.getAll(channelContext.tioConfig).getObj().size();

        String msg = "{name:'admin',message:'" + channelContext.userid + " 进来了，共【" + count + "】人在线" + "'}";
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
        WsResponse wsResponse = WsResponse.fromText(msg, BrianServerConfig.CHARSET);
        //群发
        Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);
    }

    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        Tio.remove(channelContext, "receive close flag");
        return null;
    }

    @Override
    public Object onText(WsRequest wsRequest, String s, ChannelContext channelContext) throws Exception {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequest();
        log.debug("握手包:{}", httpRequest);
        if (Objects.equals("心跳内容", s)) {
            return null;
        }

        String msg = "{name:'" + channelContext.userid + "',message:'" + s + "'}";
        WsResponse wsResponse = WsResponse.fromText(msg, BrianServerConfig.CHARSET);
        Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);

        // 返回值是要发送给客户端的内容，一般都是返回null
        return null;
    }
}
