/*
 * 文件名：MstNaturalPerson001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstnaturalperson.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.MstCustomerInfoAddMapper;
import com.joyintech.tams.common.base.dao.MstCustomerInfoMapper;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstnaturalperson.dao.MstNaturalPersonMapper;
import com.joyintech.service.basicdata.mstnaturalperson.dto.MstNaturalPerson001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 自然人管理
 * 
 * @author xiexinting
 * @version 1.0 2018年8月14日
 * @see MstNaturalPersonService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MstNaturalPersonService extends BaseService<MstNaturalPerson001Dto> {

    /**
     * 手动书写mapper
     */
    @Autowired
    private MstNaturalPersonMapper mstNaturalPerson001Mapper;
    /**
     * 客户信息附属表自动生成mapper
     */
    @Autowired
    private MstCustomerInfoAddMapper mstCustomerInfoAddMapper;
    /**
     * 客户信息表自动生成mapper
     */
    @Autowired
    private MstCustomerInfoMapper mstCustomerInfoMapper;
    /**
     * 查询 <功能详细描述>
     * 
     * @param pageParam 查询信息
     * @return 查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<MstNaturalPerson001Dto> selectForPage(int page,int length,Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("customerType", DictCodeList.客户类型_自然人.getValue());
        return super.queryForPage(page,length,() -> mstNaturalPerson001Mapper.selectForPage(pageParam));
    }

    /**
     * 
     * 客户名称联想文本框service层实现
     * <功能详细描述>
     * @param map 联想文本框查询信息
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getcName(Map<String, String> map) {
        return mstNaturalPerson001Mapper.getcName(map);
    }

    /**
     * 客户身份证联想文本框service层实现
     *
     * @param param 检索参数
     * @return SuggestDto
     */
    public List<SuggestDto> getcID(String param) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("param", param);
        map.put("naturalperson", DictCodeList.客户类型_自然人.getValue());
        return mstNaturalPerson001Mapper.getcID(map);
    }

    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto 检查条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstNaturalPerson001Dto dto) {
        return mstNaturalPerson001Mapper.checkCodeIsUse(dto);
    }

    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item 检查条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public int checkAllCodeIsUse(List<MstNaturalPerson001Dto> item) {
        return mstNaturalPerson001Mapper.checkAllCodeIsUse(item);
    }

    /**
     * 双击查看详细信息查询 修改页面信息查询
     * 
     * @param customerId 查询条件
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public MstNaturalPerson001Dto selectDetail(String customerId) {
        return mstNaturalPerson001Mapper.selectDetail(customerId);
    }

    /**
     * 实现工作流doComplete方法
     */
    public void doComplete(MstNaturalPerson001Dto entity, String type) {
        if (StringUtil.equals(type, "NEW")) {
            entity.setCustomerType(DictCodeList.客户类型_自然人.getValue());
            MstCustomerInfoAddWithBLOBs istEntity = new MstCustomerInfoAddWithBLOBs();
            BeanUtil.copyPropertiesIgnoreNull(istEntity, entity);
            mstCustomerInfoAddMapper.insert(istEntity);
            mstNaturalPerson001Mapper.insertCustomerInfo(entity);
        }
        else {
            MstCustomerInfoAddWithBLOBs istEntity = new MstCustomerInfoAddWithBLOBs();
            BeanUtil.copyPropertiesIgnoreNull(istEntity, entity);
            mstCustomerInfoAddMapper.updateByPrimaryKeySelective(istEntity);
            mstNaturalPerson001Mapper.updateByPrimaryKeySelective(entity);
        }

    }
    /**
     * 
     * 删除自然人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public boolean delete(List<String> customerIdList){
        if(!customerIdList.isEmpty()){
            for(String customerId:customerIdList){
                mstCustomerInfoAddMapper.deleteByPrimaryKey(customerId);
                mstCustomerInfoMapper.deleteByPrimaryKey(customerId);
            }
            return true;
        }
        return false;
    }

    /**
     * 
     * 新建验证
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int selectByPersonId(MstNaturalPerson001Dto dto){
        return mstNaturalPerson001Mapper.selectByPersonId(dto);
    }
    /**
     * 
     * 修改验证
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int selectByPidandCid(MstNaturalPerson001Dto dto){
        return mstNaturalPerson001Mapper.selectByPidandCid(dto);
    }
}
