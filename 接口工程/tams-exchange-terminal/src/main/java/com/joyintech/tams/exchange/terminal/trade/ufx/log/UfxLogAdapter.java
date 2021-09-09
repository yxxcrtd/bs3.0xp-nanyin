package com.joyintech.tams.exchange.terminal.trade.ufx.log;

import com.hundsun.t2sdk.impl.util.AbstractLogAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * @Author: think
 * @Description: 将ufx日志打印到本地文件中保留
 * @Date: Created in 17:23 2020/11/17
 */

public class UfxLogAdapter extends AbstractLogAdapter {
    protected final static Logger logger = LoggerFactory.getLogger(UfxLogAdapter.class);
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    @Override
    public void export(String msg) {
        logger.info("T2LOG:[{}] {}", format.format(System.currentTimeMillis()), msg);
    }
}
