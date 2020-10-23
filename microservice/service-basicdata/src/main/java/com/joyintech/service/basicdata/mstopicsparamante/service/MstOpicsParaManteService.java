/*
 * 文件名：MstOpicsParaMante001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstopicsparamante.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.OpicsParamMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstopicsparamante.dao.MstOpicsParaManteMapper;
import com.joyintech.service.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;

/**
 * OPICS参数维护业务层 
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaManteService
 * @since 1.0
 */
@Service("backMstOpicsParaManteService")
@Transactional
public class MstOpicsParaManteService extends BaseService<MstOpicsParaManteDto> {

    /**
     * OPICS参数维护Mapper
     */
    @Autowired
    private MstOpicsParaManteMapper mstOpicsParaManteMapper;

    /**
     * OPICS参数维护系统生成Mapper
     */
    @Autowired
    private OpicsParamMapper OpicsParamMapper;

    /**
     * 查询按钮的查询方法
     * @param pageParam map
     * @return Page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstOpicsParaManteDto> selectOpicsParaManteInfo(int page,int length,Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> mstOpicsParaManteMapper.selectOpicsParaManteInfo(pageParam));
    }

    /**
     * 通过主键查询方法 
     * @param param map
     * @return MstOpicsParaManteDto
     * @see
     * @since 1.0
     */
    public MstOpicsParaManteDto selectByPrimaryKey(Map<String, Object> param) {
        return mstOpicsParaManteMapper.selectByPrimaryKey(param);
    }

    /**
     * doComplete{@inheritDoc}
     */
    public void doComplete(MstOpicsParaManteDto entity, String type) {
        if (StringUtil.equals(type, "DELETE")) {
            OpicsParamMapper.deleteByPrimaryKey(entity);
        }
        else if (StringUtil.equals(type, "NEW")) {
            OpicsParamMapper.insert(entity);
        }
        else {
            OpicsParamMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
