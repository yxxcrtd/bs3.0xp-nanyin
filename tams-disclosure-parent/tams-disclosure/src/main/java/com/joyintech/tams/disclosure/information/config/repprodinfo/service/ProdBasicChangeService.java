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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 根据产品主代码 查询所有的业绩基准变更信息
     *
     * @param prodId
     * @return
     */
    public List<ProdBasicChangeDto> selectListByProdId(String prodId) {
        List<ProdBasicChangeDto> result = prodBasicChangeMapper.selectListByProdId(prodId);
        return result;
    }


    /**
     * 更新 业绩基准变更信息
     *
     * @param list
     * @return
     */
    public void updateProdBasicChangeList(List<ProdBasicChangeDto> list ,String prodId) {
        if(CollectionUtils.isNotEmpty(list)){
            //根据prodId进行删除
            prodBasicChangeMapper.deleteByProdId(prodId);
            //新增
            for(ProdBasicChangeDto i : list){
                i.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
                i.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
                i.setProdId(prodId);
                i.setBasicChangeId(IDGenerator.generate(36));
                prodBasicChangeMapper.insert(i);
            }

        }
    }


}
