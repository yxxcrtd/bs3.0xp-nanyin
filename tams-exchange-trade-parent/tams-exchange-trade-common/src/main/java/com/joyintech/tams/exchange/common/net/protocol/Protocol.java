package com.joyintech.tams.exchange.common.net.protocol;

/**
 * Created by liuyang on 12/9/15.
 */
public interface Protocol {
    public static final int HEADER_SIZE = 20;
    public static final int CLIENT_ID_SIZE = 4;
    public static final int REQID_SIZE = 4;
    public static final int FC_SIZE = 4;
    public static final int LEN_SZIE = 4;
    public static final int MAX_SIZE = 15000000;
}
