/*
 * 文件名：BaseRepAssetAstBillInfoService.java
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

import com.joyintech.tams.common.base.dao.RepAssetAstBillMapper;
import com.joyintech.tams.common.base.entity.RepAssetAstBill;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.base.report.asset.BaseRepAssetAstBillInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepAssetAstBillInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepAssetAstBillInfoMapper;
import com.joyintech.tools.util.ListUtils;

/**
 * 非标资产(票据或信用)报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BaseRepAssetAstBillInfoService
 * @since 1.0
 */
@Service
public class BaseRepAssetAstBillInfoImplService extends BaseService<RepAssetAstBill> implements BaseRepAssetAstBillInfoService{

    /**
     * 生成
     */
    @Autowired
    private RepAssetAstBillMapper repAssetAstBillMapper;
    
    /**
     * 自定义
     */
    @Autowired
    private BaseRepAssetAstBillInfoMapper baseRepAssetAstBillInfoMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(List<RepAssetAstBill> list) {
        if (list != null && list.size() > 0) {
            for(RepAssetAstBill repAssetAstBill : list){
                repAssetAstBillMapper.insert(repAssetAstBill);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(BaseRepAssetAstBillInfoDto dto) {
        List<String> finlist = dto.getRepFinprodIdList();
        if (finlist != null && finlist.size() > 0) {
            List<List<String>> list= ListUtils.splitList(finlist);
            for(List<String> idlist:list){
                baseRepAssetAstBillInfoMapper.delete(idlist);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(BaseRepAssetAstBillInfoDto dto) {
        List<RepAssetAstBill> list = dto.getRepAssetAstBillList();
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
    public List<RepAssetAstBill> select(BaseRepAssetAstBillInfoDto dto) {
        List<String> finprodIdList = dto.getRepFinprodIdList();
        if (finprodIdList != null && finprodIdList.size() > 0) {
            int maxDataCountOneTime = 1000;
            int dataCount = finprodIdList.size();
            List<RepAssetAstBill> result = new ArrayList<>();
            if (dataCount <= maxDataCountOneTime) {
                result.addAll(baseRepAssetAstBillInfoMapper.select(finprodIdList));
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(finprodIdList);
                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    result.addAll(baseRepAssetAstBillInfoMapper.select(
                        newDataList.subList(0, maxDataCountOneTime)));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    result.addAll(baseRepAssetAstBillInfoMapper.select(newDataList));
                }
            }
            return result;
        }
        return null;
    }

    
}
