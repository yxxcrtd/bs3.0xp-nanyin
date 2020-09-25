/*
 * 文件名：BaseRepAssetCreditNonstdInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.asset;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.RepAssetCreditNonstdMapper;
import com.joyintech.tams.common.base.entity.RepAssetCreditNonstd;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetCreditNonstdInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetCreditNonstdInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepAssetCreditNonstdInfoMapper;
import com.joyintech.tools.util.ListUtils;

/**
 *  信贷及非标资产中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepAssetCreditNonstdInfoImplService
 * @since 1.0
 */
@Service
public class BaseRepAssetCreditNonstdInfoImplService extends BaseService<RepAssetCreditNonstd> implements  BaseRepAssetCreditNonstdInfoService{

    /**
     * 生成
     */
    @Autowired
    private RepAssetCreditNonstdMapper repAssetCreditNonstdMapper;
    
    /**
     * 自定义
     */
    @Autowired
    private BaseRepAssetCreditNonstdInfoMapper baseRepAssetCreditNonstdInfoMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(List<RepAssetCreditNonstd> list) {
        if (list != null && list.size() > 0) {
            for(RepAssetCreditNonstd repAssetCreditNonstd : list){
                repAssetCreditNonstdMapper.insert(repAssetCreditNonstd);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(BaseRepAssetCreditNonstdInfoDto dto) {
        List<String> finlist = dto.getRepFinprodIdList();
        if (finlist != null && finlist.size() > 0) {
            List<List<String>> list= ListUtils.splitList(finlist);
            for(List<String> idlist:list){
                baseRepAssetCreditNonstdInfoMapper.delete(idlist);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(BaseRepAssetCreditNonstdInfoDto dto) {
        List<RepAssetCreditNonstd> list = dto.getRepAssetCreditNonstdList();
        if(list!=null && list.size()>0){
            if(this.delete(dto)){
                return this.insert(list);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RepAssetCreditNonstd> select(BaseRepAssetCreditNonstdInfoDto dto) {
        List<String> finprodIdList = dto.getRepFinprodIdList();
        if (finprodIdList != null && finprodIdList.size() > 0) {
            int maxDataCountOneTime = 1000;
            int dataCount = finprodIdList.size();
            List<RepAssetCreditNonstd> result = new ArrayList<>();
            if (dataCount <= maxDataCountOneTime) {
                result.addAll(baseRepAssetCreditNonstdInfoMapper.select(finprodIdList));
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(finprodIdList);
                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    result.addAll(baseRepAssetCreditNonstdInfoMapper.select(
                        newDataList.subList(0, maxDataCountOneTime)));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    result.addAll(baseRepAssetCreditNonstdInfoMapper.select(newDataList));
                }
            }
            return result;
        }
        return null;
    }

}
