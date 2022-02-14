package com.joyintech.tams.exchange.datacommon.async;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by yking on 2017/12/13 0013. God bless you!
 */
public class AsyncPersistenceBean implements Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(AsyncPersistenceBean.class);
    private IAsync async;

    public AsyncPersistenceBean() {

    }

    public void setAsync(IAsync async) {
        this.async = async;
    }

    public IAsync getAsync() {
        return async;
    }

    public AsyncPersistenceBean clone() {
        try {
            return (AsyncPersistenceBean) super.clone();
        } catch (Exception e) {
            logger.warn("clone error.{}", e);
            return new AsyncPersistenceBean();
        }
    }
}
