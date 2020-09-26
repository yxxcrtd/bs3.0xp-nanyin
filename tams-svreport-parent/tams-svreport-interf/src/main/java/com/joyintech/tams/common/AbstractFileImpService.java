package com.joyintech.tams.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstant;
import com.joyintech.tams.report.interf.common.service.InterfService;

/**
 * 外部数据文件接入,入口接口
 * @author zhangzhongliang
 * @version 1.0 2019年5月07日
 * @see AbstractFileImpService
 * @since 1.7
 */
public abstract class AbstractFileImpService extends InterfService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//文件接口，入口方法定义
	public abstract String getInfoByInterf(String currentDate);

    /**
     * 上游接口轮询任务
     *
     */
    @SuppressWarnings("resource")
    public String execute(Date batchDate,String interfType) {
        FileChannel channel = null;
        FileLock lock = null;
        String result = null;
        FileOutputStream fos = null;
        try {
            String fileLockPath = this.getParaVauleByparaCode(InterfConstant.KEY_LOCK_FILEPATH);
            // 查看锁文件是否存在
            File lockPath = new File(fileLockPath);
            if (!lockPath.exists()) {
                lockPath.mkdirs();
            }
            File lockFile = new File(fileLockPath + File.separator + interfType + "up.lockfile");
            if (!lockFile.exists()) {
                lockFile.createNewFile();
            }
            fos = new FileOutputStream(lockFile);
            channel = fos.getChannel();
            // 获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
            lock = channel.tryLock();// 无参lock()为独占锁
            if (lock == null) {
                logger.error("当前接口文件已被锁！");
                return "fail";
            }
//            try {
//                // 获取消息（读取前一日文件夹信息）
////                cnBondUpInterfService.getUpInterfMessage(
////                    DateUtil.addDayLocal(DateUtil.getCurrentDate(), -1));
////              chbFireInterfService.getUpInterfMessage(DateUtil.getYesterDayDate());
//                //add by liubin start
//                result = this.getInfoByInterf(DateUtil.getYesterDayDate(batchDate));
//                if ("fail".equals(result)){
//                    return result;
//                }
//                //add by liubin end
//            }
//            catch (Exception e) {
//                logger.error("读取前一日文件夹数据异常");
//                return "fail";
//            }
//            // 获取消息（读取当前日期文件夹信息）
//            chbFireInterfService.getUpInterfMessage(DateUtil.getCurrentDate());
            try {
                //add by liubin start
                result = this.getInfoByInterf(DateUtil.format(batchDate));
                //add by liubin end
            }
            catch (Exception e) {
                logger.error("读取今日文件夹数据异常"+e.getMessage(),e);
                return "fail";
            }
        }
        catch (FileNotFoundException e) {
            logger.error("任务锁文件"+e.getMessage(), e);
            return "fail";
        }
        catch (IOException e) {
            logger.error("任务锁文件"+e.getMessage(), e);
            return "fail";
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
                    //return "fail";
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
                   // return "fail";
                }
            }
            if (fos != null) {
                try {
                	fos.close();
                	fos = null;
                }
                catch (IOException e) {
                    logger.error(e.getMessage(),e);
                  //  return "fail";
                }
            }
        }
        return result;
    }

}
