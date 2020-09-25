/*
 * 文件名：RepBondAssetInfoImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.impl.asset;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.RepAssetBondMapper;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.base.report.asset.BaseRepBondAssetInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepBondAssetInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepBondAssetInfoMapper;
import com.joyintech.tools.util.ListUtils;

/**
 * 债券资产报送信息组件实现 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年8月29日
 * @see BaseRepBondAssetInfoServiceImpl
 * @since 1.0
 */
@Component
@Transactional(rollbackFor=Exception.class)
public class BaseRepBondAssetInfoServiceImpl extends BaseService<RepAssetBond> implements BaseRepBondAssetInfoService {

    /**
     * 注入债券资产信息报送表mapper对象
     */
    @Autowired
    private RepAssetBondMapper repAssetBondMapper;

    /**
     * 自定义债券资产信息报送表mapper对象
     */
    @Autowired
    private BaseRepBondAssetInfoMapper baseRepBondAssetInfoMapper;

    /**
     * 新建债券资产报送信息
     */
    @Override
    public boolean insert(List<RepAssetBond> list) {
        if (list != null && list.size() > 0) {
            // int maxDataCountOneTime = 1000;
            // int dataCount = list.size();
            // List<RepAssetBond> result = new ArrayList<>();
            // if (dataCount <= maxDataCountOneTime) {
            // baseRepBondAssetInfoMapper.insert(list);
            // }
            // else {
            // List<RepAssetBond> newDataList = new ArrayList<RepAssetBond>();
            // newDataList.addAll(list);
            // // 分批处理
            // int part = dataCount / maxDataCountOneTime; // 分批数
            // for (int i = 0; i < part; i++ ) {
            // baseRepBondAssetInfoMapper.insert(
            // newDataList.subList(0, maxDataCountOneTime));
            // newDataList.subList(0, maxDataCountOneTime).clear();
            // }
            // // 最后剩下的数据
            // if (CollectionUtils.isNotEmpty(newDataList)) {
            // baseRepBondAssetInfoMapper.insert(newDataList);
            // }
            // }
            for (RepAssetBond repAssetBond : list) {
                repAssetBondMapper.insert(repAssetBond);
            }
            return true;
        }
        return false;
    }

    /**
     * 删除债券资产报送信息
     */
    @Override
    public boolean delete(BaseRepBondAssetInfoDto dto) {
        List<String> finlist = dto.getRepFinprodIdList();
        if (finlist != null && finlist.size() > 0) {
            List<List<String>> list= ListUtils.splitList(finlist);
            for(List<String> idlist:list){
                baseRepBondAssetInfoMapper.delete(idlist);             
            }
            return true;
        }
        return false;
    }

    /**
     * 修改债券资产报送信息
     */
    @Override
    public boolean update(BaseRepBondAssetInfoDto dto) {
        List<RepAssetBond> list = dto.getRepAssetBondList();
        if(list!=null && list.size()>0){
            if (this.delete(dto)) {
               return this.insert(dto.getRepAssetBondList());
            }
        }
        return false;
    }

    /**
     * 查询债券资产报送信息
     */
    @Override
    public List<RepAssetBond> select(BaseRepBondAssetInfoDto dto) {
        List<String> finprodIdList = dto.getRepFinprodIdList();
        if (finprodIdList != null && finprodIdList.size() > 0) {
            int maxDataCountOneTime = 1000;
            int dataCount = finprodIdList.size();
            List<RepAssetBond> result = new ArrayList<>();
            if (dataCount <= maxDataCountOneTime) {
                result.addAll(baseRepBondAssetInfoMapper.select(finprodIdList));
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(finprodIdList);
                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    result.addAll(baseRepBondAssetInfoMapper.select(
                        newDataList.subList(0, maxDataCountOneTime)));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    result.addAll(baseRepBondAssetInfoMapper.select(newDataList));
                }
            }
            return result;
        }
        return null;
    }

}
