package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.pipline;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解决拆包粘包问题
 */
public class SSEBinaryMarketProtocolDecoder extends LengthFieldBasedFrameDecoder {

    public SSEBinaryMarketProtocolDecoder() {
        super(Integer.MAX_VALUE, 12, 4, 4, 0);
    }
}
