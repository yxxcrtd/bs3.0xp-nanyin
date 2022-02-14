package com.joyintech.tams.exchange.terminal.test;

import com.joyintech.tams.exchange.terminal.utils.AddressUtils;

import java.net.UnknownHostException;

public class AddressUtilsTest {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(AddressUtils.getCpuSerialNo());
        System.out.println(AddressUtils.getLocalIp());
        System.out.println(AddressUtils.getLocalMac());
        System.out.println(AddressUtils.getIdentifier());
        System.out.println(AddressUtils.getV4IP());

        System.out.println(AddressUtils.genOpStation("O32Client;V2.0;TJYH;O32Server1.0;192.168.0.1;10.0.2"));
    }
}
