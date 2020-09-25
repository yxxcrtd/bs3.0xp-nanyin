/*
 * 文件名：BaseRepBondAssetInfoImplService.java
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
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepAssetEquityMapper;
import com.joyintech.tams.common.base.entity.RepAssetEquity;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetEquityInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetEquityInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepAssetEquityInfoMapper;
import com.joyintech.tools.util.ListUtils;

/**
 * 权益类资产中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepBondAssetInfoImplService
 * @since 1.0
 */
@Service
public class BaseRepAssetEquityInfoImplService extends BaseService<RepAssetEquity>  implements BaseRepAssetEquityInfoService{

    /**
     * 生成
     */
    @Autowired
    private RepAssetEquityMapper repAssetEquityMapper;
    
    /**
     * 自定义
     */
    @Autowired
    private BaseRepAssetEquityInfoMapper baseRepAssetEquityInfoMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(List<RepAssetEquity> list) {
        if (list != null && list.size() > 0) {
            for(RepAssetEquity repAssetEquity : list){
                repAssetEquityMapper.insert(repAssetEquity);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(BaseRepAssetEquityInfoDto dto) {
        List<String> finlist = dto.getRepFinprodIdList();
        if (finlist != null && finlist.size() > 0) {
            List<List<String>> list= ListUtils.splitList(finlist);
            for(List<String> idlist:list){
                baseRepAssetEquityInfoMapper.delete(idlist);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(BaseRepAssetEquityInfoDto dto) {
        List<RepAssetEquity> list = dto.getRepAssetEquityList();
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
    public List<RepAssetEquity> select(BaseRepAssetEquityInfoDto dto) {
        List<String> finprodIdList = dto.getRepFinprodIdList();
        if (finprodIdList != null && finprodIdList.size() > 0) {
            int maxDataCountOneTime = 1000;
            int dataCount = finprodIdList.size();
            List<RepAssetEquity> result = new ArrayList<>();
            if (dataCount <= maxDataCountOneTime) {
                result.addAll(baseRepAssetEquityInfoMapper.select(finprodIdList));
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(finprodIdList);
                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    result.addAll(baseRepAssetEquityInfoMapper.select(
                        newDataList.subList(0, maxDataCountOneTime)));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    result.addAll(baseRepAssetEquityInfoMapper.select(newDataList));
                }
            }
            return result;
        }
        return null;
    }

}
