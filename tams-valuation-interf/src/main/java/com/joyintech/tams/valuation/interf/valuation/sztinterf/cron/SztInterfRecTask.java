package com.joyintech.tams.valuation.interf.valuation.sztinterf.cron;
/**
 * @author yanya
 * @create 2019-12-12 8:47
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.service.SztInterfRecService;


/**
 * @ClassName SztInterfRecTask
 * @Description: 深圳通接口轮询接收文件定时任务
 * @Author Administrator
 * @Date 2019/12/13 0013 
 * @Version V1.0
 **/
@Service("sztInterfRecTask")
public class SztInterfRecTask {

    private static Logger logger = LoggerFactory.getLogger(SztInterfRecTask.class.getName());

    @Autowired
    private SztInterfRecService SztInterfRecService;

    /**
     * 深圳通定时轮询任务
     */
    public void sztInterfRec() {
        try {
            SztInterfRecService.sztRecDeal();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("the SztInterfRecTask throws Exception!");
        }
    }
}
