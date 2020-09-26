package com.joyintech.tams.valuation.interf.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

import com.joyintech.tams.valuation.interf.core.entity.InterfNoSkipException;

import java.util.List;

public class JoyinSkipPolicy implements SkipPolicy {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 失败产品列表
     */
    private List<String> failedProdList;

    /**
     *
     */
    public JoyinSkipPolicy() {
        super();
    }

    @Override
    public boolean shouldSkip(Throwable t, int skipCount)
            throws SkipLimitExceededException {
        if (skipCount == -1) {
            logger.error("阻断型性异常：skipCount||" + skipCount, t);
            return false;
        }
        StackTraceElement[] trace = t.getStackTrace();
        for (StackTraceElement traceElement : trace) {
            try {
                Class clazz = Class.forName(traceElement.getClassName());
                Class superclass = clazz.getSuperclass();
                while (superclass != null) {
                    if (superclass.getName().equals("com.joyintech.tams.valuation.interf.core.InterfBaseWriter")) {
                        logger.error("阻断型性异常", t);
                        return false;
                    }
                    superclass = superclass.getSuperclass();
                }
            } catch (ClassNotFoundException e) {
                logger.error("类型未找到", e);
            }
        }
        if (skipCount > 1) {
            if (t instanceof InterfNoSkipException) {
                logger.error("阻断型性异常", t);
                return false;
            } else {
                logger.error("非阻断异常，已跳过", t);
            }
        }
        return true;
//        StackTraceElement[] trace = t.getStackTrace();
//        StringBuilder sb = new StringBuilder();
//        for (StackTraceElement traceElement : trace) {
//            sb.append("\t" + traceElement);
//        }
//        if (t instanceof InterfNoSkipException) {
//            logger.error("阻断型性异常", t);
//            return false;
//        }
//        else {
//            logger.error("非阻断异常，已跳过", t);
//        }
//
//        if (t instanceof InterfException) {
//            logger.error("异常， 已跳过，异常信息" + t.getMessage());
//        }
//        return true;
    }

    /**
     * 获取 failedProdList
     *
     * @return failedProdList.
     */
    public List<String> getFailedProdList() {
        return failedProdList;
    }

    /**
     * 设置 failedProdList
     *
     * @param failedProdList failedProdList
     */
    public void setFailedProdList(List<String> failedProdList) {
        this.failedProdList = failedProdList;
    }
}
