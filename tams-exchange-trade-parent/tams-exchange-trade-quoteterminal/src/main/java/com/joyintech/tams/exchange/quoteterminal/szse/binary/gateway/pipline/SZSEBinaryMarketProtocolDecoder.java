package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.pipline;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解决拆包粘包问题
 */
public class SZSEBinaryMarketProtocolDecoder extends LengthFieldBasedFrameDecoder {

    public SZSEBinaryMarketProtocolDecoder() {
        super(Integer.MAX_VALUE, 4, 4,4,0);
    }
}
