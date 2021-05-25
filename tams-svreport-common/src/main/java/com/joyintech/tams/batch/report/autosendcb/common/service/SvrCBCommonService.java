/*
 * 文件名：ReportRegistBaseService.java
 * 版权：Copyright 2019-2020 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债监管报送平台
 * 修改人：魏玉强
 * 修改时间：2019年0月8日
 * 修改内容：新建
 * 系统名称：ReportRegistBaseService
 */

package com.joyintech.tams.batch.report.autosendcb.common.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能:中债报送系统报表登记批处理统一调度入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：zhangliang
 * @创建时间：2020年11月6日
 * @修改记录：
 */
@Service
public abstract class SvrCBCommonService extends AutoSendCBBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 功能:中债报送系统报表登记批统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月8日
     * @param executeDate 执行日期
     * @param prodList 产品list
     * @param assetList 资产list
     * @throws BusinessException 业务异常
     * void
     */
    public abstract void execute(Date executeDate,List<String> prodList,List<String> assetList,Map<String,List<String>> param) throws BusinessException;

    /**
     *  查询待插入数据列表
     * @param batchTime 跑批日
     * @param prodIdList 跑批产品列表
     * @param listCloumn 数据库字段集合
     * @return 待插入数据列表
     */
    public abstract Map<String, Map<String, String>> queryUnRegData(Date batchTime,List<String> prodIdList, List<String> listCloumn);

    /**
     *  查询已存在的登记列表
     *  key值为业务数据唯一字段值
     * @param batchTime 跑批日
     * @param listCloumn 数据库字段集合
     * @param unRegDataMap 待插入数据列表
     * @return 已存在的登记列表
     */
    public abstract Map<String, Map<String, String>> queryRegDate(Date batchTime, List<String> listCloumn, Map<String, Map<String, String>> unRegDataMap);

    /**
     *  比较待插入的数据与已存在的数据是否相同
     * @param unregData 待插入的数据
     * @param regData 已存在的数据
     * @return true:相同，false:不相同
     */
    public abstract boolean compareData(Map<String, String> unregData, Map<String, String> regData);

    /**
     *  入库前需要进行数据检验，检验错误的数据不入库
     * @param insertDataList 待校验数据
     * @return 校验结果
     */
    public abstract List<Map<String, String>> checkData(List<Map<String, String>> insertDataList);

    /**
     *  数据入库操作处理
     * @param deleteDataList 待删除数据列表
     * @param insertDataList 待插入数据列表
     * @param listCloumn 数据库字段集合
     */
    public abstract void dbHandle(List<Map<String, String>> deleteDataList, List<Map<String, String>> insertDataList, List<String> listCloumn);

    /**
     *  info -> sub 共通处理
     * @param batchTime 跑批日
     * @param prodIdList 跑批产品列表
     * @param listCloumn 数据库字段集合
     */
    public void batchCommonHandle(Date batchTime, List<String> prodIdList, List<String> listCloumn){
        // 登记插入列表
        List<Map<String, String>> insertDataList = new ArrayList<>();
        // 登记删除列表
        List<Map<String, String>> deleteDataList = new ArrayList<>();

        // 查询未登记信息
        Map<String, Map<String, String>>  unRegDataListMap = queryUnRegData(batchTime, prodIdList, listCloumn);
        if (!unRegDataListMap.isEmpty()){
            // 查询已登记信息
            Map<String, Map<String, String>> regDataMap = queryRegDate(batchTime, listCloumn, unRegDataListMap);

            for (String key : unRegDataListMap.keySet()){
                // 库里有对应数据
                if (regDataMap.containsKey(key)){
                    if (StringUtil.equals(DictCodeListExt1.维护数据状态_删除.getValue(), unRegDataListMap.get(key).get("STATUS"))){
                        if (compareData(unRegDataListMap.get(key), regDataMap.get(key))){
                            if (StringUtil.equals(DictCodeListExt2.报送数据状态_新建.getValue(), regDataMap.get(key).get("STATUS"))){
                                if (StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(), regDataMap.get(key).get("SEND_STATUS"))){
                                    deleteDataList.add(unRegDataListMap.get(key));
                                }else{
                                    unRegDataListMap.get(key).put("STATUS", DictCodeListExt2.报送数据状态_删除.getValue());
                                    insertDataList.add(unRegDataListMap.get(key));
                                }
                            }else if (StringUtil.equals(DictCodeListExt2.报送数据状态_修改.getValue(), regDataMap.get(key).get("STATUS"))){
                                if (StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(), regDataMap.get(key).get("SEND_STATUS"))){
                                    deleteDataList.add(unRegDataListMap.get(key));
                                }
                                unRegDataListMap.get(key).put("STATUS", DictCodeListExt2.报送数据状态_删除.getValue());
                                insertDataList.add(unRegDataListMap.get(key));
                            }
                        }
                    }
                    else if (StringUtil.equals(DictCodeListExt1.维护数据状态_修改.getValue(), unRegDataListMap.get(key).get("STATUS"))){
                        if (!compareData(unRegDataListMap.get(key), regDataMap.get(key))){
                            if (StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(), regDataMap.get(key).get("SEND_STATUS"))){
                                deleteDataList.add(unRegDataListMap.get(key));
                                insertDataList.add(unRegDataListMap.get(key));
                            }
                            else{
                                unRegDataListMap.get(key).put("STATUS", DictCodeListExt2.报送数据状态_修改.getValue());
                                if (StringUtil.equals(DictCodeListExt2.报送数据状态_删除.getValue(), regDataMap.get(key).get("STATUS"))){
                                    unRegDataListMap.get(key).put("STATUS", DictCodeListExt2.报送数据状态_新建.getValue());
                                }
                                insertDataList.add(unRegDataListMap.get(key));
                            }
                        }
                    }
                    else if (StringUtil.equals(DictCodeListExt1.维护数据状态_有效.getValue(), unRegDataListMap.get(key).get("STATUS"))){
                        if (compareData(unRegDataListMap.get(key), regDataMap.get(key))){
                            if (StringUtil.equals(DictCodeListExt2.报送数据状态_删除.getValue(), regDataMap.get(key).get("STATUS"))){
                                if (StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(), regDataMap.get(key).get("SEND_STATUS"))){
                                    deleteDataList.add(unRegDataListMap.get(key));
                                }else{
                                    unRegDataListMap.get(key).put("STATUS", DictCodeListExt2.报送数据状态_删除.getValue());
                                    insertDataList.add(unRegDataListMap.get(key));
                                }
                            }
                        }
                    }
                    else {
                        logger.info("数据维护状态有误，应为：D|A|M!");
                        continue;
                    }
                }
                else{
                    if (StringUtil.equals(DictCodeListExt1.维护数据状态_删除.getValue(), unRegDataListMap.get(key).get("STATUS"))){
                        deleteDataList.add(unRegDataListMap.get(key));
                    } else {
                        unRegDataListMap.get(key).put("STATUS", DictCodeListExt2.报送数据状态_新建.getValue());
                        deleteDataList.add(unRegDataListMap.get(key));
                        insertDataList.add(unRegDataListMap.get(key));
                    }
                }
            }
        }

        insertDataList = checkData(insertDataList);

        dbHandle(deleteDataList, insertDataList, listCloumn);
    }
}