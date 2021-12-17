package com.joyintech.tams.exchange.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.NetworkIF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtils {
    private final static Logger logger = LoggerFactory.getLogger(AddressUtils.class);

    private final static String defaultValue = "NA";
    private final static String iport = defaultValue;
    private final static String pcn = "JOYIN";

    public static String getTerminalType() {
        String res = "OH";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            res = "PC";
        }
        return res;
    }

    public static String getCpuSerialNo() {
        String res = defaultValue;
        try {
            res = new SystemInfo().getHardware().getProcessor().getProcessorIdentifier().
                    getProcessorID().replace(" ", "");
        } catch (Exception e) {
            logger.warn("getCpuSerialNo from system fail {}", e);
        }
        return res;
    }

    public static String getLocalIp() {
        String res = defaultValue;
        try {
            res = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.warn("getLocalIp  fail {}", e);
        }
        return res;
    }

    public static String getLocalMac() {
        String res = defaultValue;
        try {
            List<NetworkIF> networkIFs = new SystemInfo().getHardware().getNetworkIFs();
            for (NetworkIF networkIF : networkIFs) {
                res = networkIF.getMacaddr().toUpperCase().replace(":", "");
                break;
            }

        } catch (Exception e) {
            logger.warn("getLocalMac address  fail {}", e);
        }
        return res;
    }

    public static String getIdentifier() {
        String res = defaultValue;
        try {
            List<HWDiskStore> diskStores = new SystemInfo().getHardware().getDiskStores();
            for (HWDiskStore store : diskStores) {
                res = store.getSerial().trim();
                if (res != "unknown") {
                    break;
                }
            }
        } catch (Exception e) {
            logger.warn("getIdentifier from system fail {}", e);
        }
        if(res == "unknown"){
            res = defaultValue;
        }
        return res;
    }


    public static String getV4IP() {
        String res = defaultValue;
        String chinaz = "http://ip.chinaz.com";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url;
        HttpURLConnection urlConnection;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
        } catch (Exception e) {
            logger.warn("getV4IP from http://ip.chinaz.com fail {}", e);
            return res;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.warn("getV4IP from http://ip.chinaz.com error {}", e);
                }
            }
        }
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            res = m.group(1);
        }
        return res;
    }

    public static String genOpStation(String extendInfo) {
        return String.format("%s;IIP=%s;IPORT=%s;LIP=%s;MAC=%s;HD=%s;PCN=%s;CPU=%s;PI=%s;VOL=%s@%s",
                getTerminalType(), getV4IP(), iport, getLocalIp(), getLocalMac(), getIdentifier(), pcn, getCpuSerialNo(), defaultValue, defaultValue, extendInfo);
    }
}
