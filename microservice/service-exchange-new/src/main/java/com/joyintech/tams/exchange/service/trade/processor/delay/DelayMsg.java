package com.joyintech.tams.exchange.service.trade.processor.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayMsg<T> implements Delayed {
    private T item;
    private long delayTime;

    protected final static TimeUnit DELAY_TIME_UNIT = TimeUnit.MILLISECONDS;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public DelayMsg(T item, long time) {
        this.item = item;
        delayTime = time + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayTime - System.currentTimeMillis(), DELAY_TIME_UNIT);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this)
            return 0;
        if (other instanceof DelayMsg) {
            DelayMsg<?> x = (DelayMsg<?>) other;
            long diff = delayTime - x.delayTime;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else
                return 0;
        }
        long d = (getDelay(DELAY_TIME_UNIT) - other.getDelay(DELAY_TIME_UNIT));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }
}
