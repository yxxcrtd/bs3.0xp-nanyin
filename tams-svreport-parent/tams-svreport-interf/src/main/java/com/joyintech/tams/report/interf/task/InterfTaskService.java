/*
 * 文件名：InterfTask.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年4月13日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.interf.cnbond.service.CnBondInterfService;
import com.joyintech.tams.report.interf.common.constant.InterfConstant;
import com.joyintech.tams.report.interf.common.service.InterfService;

/**
 * 接口轮询任务
 * 
 * @author 何阳阳
 * @version 1.0 2017年10月30日
 * @see InterfTaskService
 * @since 1.0
 */
@Service("InterfTaskService")
public class InterfTaskService extends InterfService {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(InterfTaskService.class);

    /**
     * 中债报送接口service
     */
    @Autowired
    private CnBondInterfService cnBondInterfService;

    /**
     * 处理中债报送接口反馈信息
     *
     * @see
     * @since 1.0
     */
    @SuppressWarnings("resource")
    public void execute() {
        FileChannel channel = null;
        FileLock lock = null;
        FileOutputStream fos = null;
        try {
            String fileLockPath = this.getParaVauleByparaCode(InterfConstant.KEY_LOCK_FILEPATH);
            // 查看锁文件是否存在
            File lockPath = new File(fileLockPath);
            if (!lockPath.exists()) {
                lockPath.mkdirs();
            }
            File lockFile = new File(fileLockPath + File.separator + "chb.lockfile");
            if (!lockFile.exists()) {
                lockFile.createNewFile();
            }
            fos = new FileOutputStream(lockFile);
            channel = fos.getChannel();

            // 获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
            lock = channel.tryLock();// 无参lock()为独占锁
            if (lock == null) {
                return;
            }
            // 获取消息
            cnBondInterfService.getMessage(DateUtil.now());
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
                    logger.error(e.getMessage(),e);
                }
            }

            if (channel != null) {
                try {
                    channel.close();
                    channel = null;
                }
                catch (IOException e) {
//                    e.printStackTrace();
                    logger.error(e.getMessage(),e);
                }
            }
            if (fos != null) {
                try {
                	fos.close();
                	fos = null;
                }
                catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }
}
