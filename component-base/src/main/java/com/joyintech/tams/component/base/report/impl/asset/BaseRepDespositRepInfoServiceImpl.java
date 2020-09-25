/*
 * 文件名：BaseRepDespositRepInfoServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.asset;



import java.util.ArrayList;
import java.util.List;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepAssetBankDepositMapper;
import com.joyintech.tams.common.base.entity.RepAssetBankDeposit;
import com.joyintech.tams.component.base.report.asset.BaseRepDespositRepInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepDespositAssetInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepDespositAssetInfoMapper;
import com.joyintech.tools.util.ListUtils;


/**
 * 存款资产报送信息组件
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月30日
 * @see BaseRepDespositRepInfoServiceImpl
 * @since 1.0
 */
@Service
public class BaseRepDespositRepInfoServiceImpl implements BaseRepDespositRepInfoService {

    /**
     * 自动生成的mapper
     */
    @Autowired
    private RepAssetBankDepositMapper repAssetBankDepositMapper;
    /**
     * 存款资产报送信息组件mapper
     */
    @Autowired
    private BaseRepDespositAssetInfoMapper baseRepDespositAssetInfoMapper;

    /**
     * 新建
     */
    @Override
    public boolean insert(List<RepAssetBankDeposit> list) {
        if(list!=null&&list.size()>0){
            for(RepAssetBankDeposit repAssetBankDeposit:list){
                repAssetBankDepositMapper.insert(repAssetBankDeposit);
            }
            return true;
        }
        return false;
    }

    /**
     * 删除
     */
    @Override
    public boolean delete(BaseRepDespositAssetInfoDto dto) {
        List<String> finprodIdList=dto.getRepFinprodIdList();
        List<List<String>> list= ListUtils.splitList(finprodIdList);
        if(list!=null&&list.size()>0){
            for(List<String> idList:list){
                baseRepDespositAssetInfoMapper.delete(idList);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改
     */
    @Override
    public boolean update(BaseRepDespositAssetInfoDto dto) {
       List<RepAssetBankDeposit> list=dto.getRepAssetBankDepositList();
        if(list!=null&&list.size()>0){
                if(this.delete(dto)){
                    this.insert(list);
            }
        }
        return false;
    }

    /**
     * 查询
     */
    @Override
    public List<RepAssetBankDeposit> select(BaseRepDespositAssetInfoDto dto) {
        List<String> finprodIdList =dto.getRepFinprodIdList();
        if(finprodIdList!=null&&finprodIdList.size()>0){
            int maxDataCountOneTime=500;
            int dataCount=finprodIdList.size();
            List<RepAssetBankDeposit> list=new ArrayList<>();
            if(dataCount<=maxDataCountOneTime){
                list.addAll(baseRepDespositAssetInfoMapper.select(finprodIdList)); 
            }else{
                List<String> newDataList =new ArrayList<>();
                newDataList.addAll(finprodIdList);
                //分批处理
                int part=dataCount/maxDataCountOneTime;
                for(int i=0;i<part;i++){
                    list.addAll(baseRepDespositAssetInfoMapper.select(newDataList.subList(0, maxDataCountOneTime)));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                //最后剩下的数据
                if(CollectionUtils.isNotEmpty(newDataList)){
                    list.addAll(baseRepDespositAssetInfoMapper.select(newDataList));
                }
            }
            return list;
        }
        return null;
    }
        
}
