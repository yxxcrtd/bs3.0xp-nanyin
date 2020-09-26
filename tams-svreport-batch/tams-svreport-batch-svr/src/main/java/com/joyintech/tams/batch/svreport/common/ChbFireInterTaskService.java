/*
 * 文件名：CnBondUpInterfTaskService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年11月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.svreport.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.AbstractFileImpService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstant;
import com.joyintech.tams.report.interf.common.service.InterfService;

/**
 * 中债上游接口定时任务处理
 *
 * @author wm
 * @version 1.0 2017年11月9日
 * @see ChbFireInterTaskService
 * @since 1.0
 */
@Service("chbFireInterTaskService")
public class ChbFireInterTaskService extends InterfService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChbFireInterTaskService.class);

//    /**
//     * 上游接口service
//     */
//    @Autowired
//    private ChbFireInterfService chbFireInterfService;
    
    
    @Autowired
    private List<AbstractFileImpService> listFileCommonInterfService;
    

    /**
     * 上游接口轮询任务
     *
     */
    @SuppressWarnings("resource")
    public void execute() {
        FileChannel channel = null;
        FileLock lock = null;
        try {
            String fileLockPath = this.getParaVauleByparaCode(InterfConstant.KEY_LOCK_FILEPATH);
            // 查看锁文件是否存在
            File lockPath = new File(fileLockPath);
            if (!lockPath.exists()) {
                lockPath.mkdirs();
            }
            File lockFile = new File(fileLockPath + File.separator + "up.lockfile");
            if (!lockFile.exists()) {
                lockFile.createNewFile();
            }
            channel = new FileOutputStream(lockFile).getChannel();
            // 获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
            lock = channel.tryLock();// 无参lock()为独占锁
            if (lock == null) {
                return;
            }

            try {
                // 获取消息（读取前一日文件夹信息）
//                cnBondUpInterfService.getUpInterfMessage(
//                    DateUtil.addDayLocal(DateUtil.getCurrentDate(), -1));
//            	chbFireInterfService.getUpInterfMessage(DateUtil.getYesterDayDate());
            	
            	//add by liubin start
                for(AbstractFileImpService service : listFileCommonInterfService){
                	service.getInfoByInterf(DateUtil.getYesterDayDate());
                }
                
                //add by liubin end
            	
            }
            catch (Exception e) {
                logger.error("读取前一日文件夹数据异常Error",e);
            }

//            // 获取消息（读取当前日期文件夹信息）
//            chbFireInterfService.getUpInterfMessage(DateUtil.getCurrentDate());
            
            
          //add by liubin start
            for(AbstractFileImpService service : listFileCommonInterfService){
            	service.getInfoByInterf(DateUtil.getCurrentDate());
            }
            //add by liubin end
            
            
        }
        catch (FileNotFoundException e) {
            logger.error("任务锁文件", e);
        }
        catch (IOException e) {
            logger.error("任务锁文件", e);
        }
        finally {
            if (lock != null) {
                try {
                    lock.release();
                    lock = null;
                }
                catch (IOException e) {
//                    e.printStackTrace();
                    logger.error("中债上游接口定时任务处理Error",e);
                }
            }

            if (channel != null) {
                try {
                    channel.close();
                    channel = null;
                }
                catch (IOException e) {
//                    e.printStackTrace();
                    logger.error("中债上游接口定时任务处理Error",e);
                }
            }
        }
    }

}
