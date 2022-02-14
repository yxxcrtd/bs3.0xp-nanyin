package com.joyintech.tams.exchange.common.net.handler;


import com.joyintech.tams.exchange.common.net.protocol.Protocol;
import com.joyintech.tams.exchange.common.net.protocol.ProtocolMessageFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by yking on 2016/4/15 0015.
 */
public class CXServerDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(CXServerDecoder.class);
    private long reqId = -1;
    private int msgType = -1;
    private int fc = -1;
    private int bodyLen = -1;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < Protocol.HEADER_SIZE) {
            logger.debug("Header size < " + Protocol.HEADER_SIZE);
            return;
        }

        in.markReaderIndex();
        this.fc = in.readInt();
        this.reqId = in.readLong();
        this.msgType = in.readInt();
        this.bodyLen = in.readInt();

        if (bodyLen > Protocol.MAX_SIZE || bodyLen < 0) {
            throw new Exception("got unexpected body size: " + bodyLen);
        } else if (reqId < 0 || msgType < 0 || fc < 0) {
            throw new Exception("invalid reqId or msgType or function code");
        }

        if (in.isReadable(bodyLen)) {
            byte[] body = new byte[bodyLen];
            in.readBytes(body);
            out.add(ProtocolMessageFactory.newMessage(fc, reqId, msgType, body));
        } else {
            in.resetReaderIndex();
        }
    }
}
