package com.joyintech.tams.exchange.service.trade.thead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Component
public class ThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    private static ExecutorService pool;

    @Value("${trade.thread.msg.num}")
    private int threadSize;
    @Value("${trade.thread.risk.alive}")
    private int aliveTime;
    @Value("${trade.thread.risk.capacity}")
    private int capacity;

    @PostConstruct
    public void createPool() {
        pool = new ThreadPoolExecutor(threadSize, threadSize * 2, aliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(capacity),
                Executors.defaultThreadFactory(), (r, executor) -> {
            logger.error("thread {} error, queue is full, execute reject.", r.toString());
        });
    }

    public void execute(Runnable command) {
        pool.execute(command);
    }
}
