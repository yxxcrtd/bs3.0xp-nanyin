/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repprodinfo.service;

import com.google.common.base.Joiner;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dao.ProdBasicChangeMapper;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdBasicChangeDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 业绩基准变更
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ProdBasicChangeService
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBasicChangeService {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBasicChangeService.class);


    @Autowired
    private ProdBasicChangeMapper prodBasicChangeMapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;
    /**
     * 根据产品主代码 查询所有的业绩基准变更信息
     *
     * @param prodId
     * @return
     */
    public List<ProdBasicChangeDto> selectListByProdId(String prodId) {
        List<ProdBasicChangeDto> list = new ArrayList<>();
        List<ProdBasicChangeDto> result = prodBasicChangeMapper.selectListByProdId(prodId);
        if(CollectionUtils.isNotEmpty(result)){
            result.forEach(i->{
                if(StringUtils.contains(i.getProportion(),"%")){
                    i.setProportion(i.getProportion().substring(0, i.getProportion().indexOf("%")));
                }
                list.add(i);
            });
        }

        return list;
    }


    /**
     * 更新 业绩基准变更信息
     *
     * @param list
     * @return
     */
    public void updateProdBasicChangeList(List<ProdBasicChangeDto> list ,String prodId) {
        //TODO 添加平台日时间信息和组id信息
        Date platDate = commonPlatformDateService.getSysDate(); //平台日
        if(CollectionUtils.isNotEmpty(list)){
            //根据prodId进行更新，将老数据状态改成 删除状态
            // prodBasicChangeMapper.deleteByProdId(prodId);
            List<ProdBasicChangeDto> oldList = prodBasicChangeMapper.selectListByProdId(prodId);
            if(CollectionUtils.isNotEmpty(oldList)){
                for(ProdBasicChangeDto i : oldList){
                    i.setStatus(DictCodeListExt3.信披_数据状态_删除D.getValue());
                    prodBasicChangeMapper.updateByPrimaryKeySelective(i);
                }
            }
            //新增
            String groupId = IDGenerator.generate(36);
            for(ProdBasicChangeDto i : list){
                i.setStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
                i.setProdId(prodId);
                i.setGroupId(groupId);
                i.setEffDate(platDate);
                i.setProportion(i.getProportion()+"%");
                prodBasicChangeMapper.insert(i);
            }

        }
    }


}
