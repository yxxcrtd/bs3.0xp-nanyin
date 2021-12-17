package com.joyintech.tams.exchange.datacommon.async;

import com.lmax.disruptor.EventFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yking on 2017/12/13 0013. God bless you!
 */
@Component
public class AsyncPersistenceFactory implements EventFactory<AsyncPersistenceBean> {

    private AsyncPersistenceBean beanTemplate = new AsyncPersistenceBean();

    @Override
    public AsyncPersistenceBean newInstance() {
        return beanTemplate.clone();
    }
}
