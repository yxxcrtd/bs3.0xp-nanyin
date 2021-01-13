package com.joyintech.tams.svreport.chb.assetinfodetail.asset.factoryserver;


import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AssetInfoFactoryService{

    @Autowired
    Map<String, BussAssetInfoService> AssetFactory = new ConcurrentHashMap<>();

    public BussAssetInfoService getAssetInfo(String assetType2) throws Exception{
        BussAssetInfoService strategy = null;
        for(Map.Entry<String,BussAssetInfoService> entity:AssetFactory.entrySet()){
            if(entity.getValue().getBussinessCode(assetType2)){
                strategy = entity.getValue();
                break;
            }
        }
        return strategy;
    }


}
