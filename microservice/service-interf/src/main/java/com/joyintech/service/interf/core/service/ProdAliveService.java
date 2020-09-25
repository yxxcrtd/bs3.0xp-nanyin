/*
 * 文件名：ProdAliveService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.joyintech.tams.common.base.dao.InterfBranchMapper;
import com.joyintech.tams.common.base.dao.InterfProdInfoMapper;
import com.joyintech.tams.common.base.dao.InterfProdLadderDetailMapper;
import com.joyintech.tams.common.base.entity.InterfBranch;
import com.joyintech.tams.common.base.entity.InterfProdInfo;
import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.prodtrade.dao.ProdIsLayMapper;
import com.joyintech.service.interf.prodtrade.dao.ProdStartToEndMapper;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.service.interf.prodtrade.dto.InterfOutEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 存续期接口处理
 * 
 * @param <T> 发送ESB类型
 * @param <F> 接收ESB类型
 * @author zhangrui1
 * @version 1.0 2018年10月16日
 * @see ProdAliveService
 * @since 1.0
 */
public abstract class ProdAliveService<T extends InterfAliveProdEntity<?>, F extends InterfOutEntity> extends SendEsbService<T, F> {

    /**
     * 产品类型
     */
    private static final String PROD_TYPE = "prodType";

    /**
     * 交易类型
     */
    private static final String PINGAN_BUSI_TYPE = "pinganBusiType";

    /**
     * 产品申赎信息接口查询更新Mapper
     */
    @Autowired
    private InterfProdInfoMapper interfProdIfMapper;

    /**
     * 产品分层信息查询更新Mapper
     */
    @Autowired
    private InterfProdLadderDetailMapper interfLvlMapper;

    /**
     * 产品分行信息查询更新Mapper
     */
    @Autowired
    private InterfBranchMapper interfBranchMapper;

    /**
     * 产品分层分行信息查询
     */
    @Autowired
    private ProdStartToEndMapper prodFoundMapper;

    /**
     * 查询产品是否分层
     */
    @Autowired
    private ProdIsLayMapper prodIsLayMapper;

    /**
     * 获取 产品分层分行信息查询Mapper
     * 
     * @return 产品分层分行信息查询Mapper.
     */
    public ProdStartToEndMapper getProdFoundMapper() {
        return prodFoundMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T preparedLocalData(Object param)
        throws Exception {
        InterfFinProdDto dto = (InterfFinProdDto)param;
        // 设置产类型
        String prodType = (String)getServiceInfo(PROD_TYPE);
        if (StringUtil.isEmpty(prodType)) {
            setProdType(dto.getType());
        }
        InterfAliveProdEntity<?> entity = prodIsLayMapper.selectProdInfo(dto.getFinProdId());
        T inEntity = getReturnEntity();
        BeanUtil.copyPropertiesIgnoreNull(inEntity, entity);
        // 设置查询日期
        if (dto.getCdate() == null) {
            inEntity.setcDate(getSysDate());
        }
        else {
            setSysDate(dto.getCdate());
            inEntity.setcDate(dto.getCdate());
        }
        // 设置销售开始日
        inEntity.setStartdate(
            dto.getStartdate() == null ? entity.getStartdate() : dto.getStartdate());
        // 设置销售截止日
        inEntity.setEnddate(dto.getEnddate() == null ? entity.getEnddate() : dto.getEnddate());
        if (DictCodeList.是否_是.getValue().equals(entity.getIsLay())) {
            List<InterfProdLadderDetail> lvlList = prodIsLayMapper.selectProdLvlInfo(
                dto.getFinProdId(), DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
            inEntity.setLvlList(turnLvl(lvlList));
        }
        dto.setProdId(entity.getProdid());
        return inEntity;
    }

    /**
     * 父类List转化为子类List
     * 
     * @param lvlList 父类List
     * @param <L> 返回类型
     * @return 子类List
     * @see
     * @since 1.0
     */
    private <L extends InterfProdLadderDetail> List<L> turnLvl(List<InterfProdLadderDetail> lvlList) {
        List<L> returnList = new ArrayList<>();
        for (InterfProdLadderDetail iDetail : lvlList) {
            L inLvlEntity = getReturnLvlEntity();
            BeanUtil.copyPropertiesIgnoreNull(inLvlEntity, iDetail);
            returnList.add(inLvlEntity);
        }
        return returnList;
    }

    /**
     * 获取返回类型的实例
     * 
     * @return 返回类型的实例
     * @see
     * @since 1.0
     */
    public abstract T getReturnEntity();

    /**
     * 获取返回类型的分层信息实例
     * 
     * @param <L> 返回类型
     * @return 返回类型的分层信息实例
     * @see
     * @since 1.0
     */
    public abstract <L extends InterfProdLadderDetail> L getReturnLvlEntity();

    /**
     * 删除业务表
     * 
     * @param entity 删除数据
     * @return 删除行数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public int deleteInterfData(InterfOutEntity entity) {
        String inter = getInterfId();
        int result = 0;
        String cDate = getSysDate();
        entity.setCdate(cDate);
        entity.setInterfId(inter);
        // 删除申赎表信息
        result += getProdFoundMapper().deleteProdInfo(entity);
        InterfProdLadderDetail detail = new InterfProdLadderDetail();
        detail.setProdid(entity.getProdid());
        detail.setCdate(cDate);
        detail.setInterfId(inter);
        // 删除分层表信息
        result += getProdFoundMapper().deleteProdLadderDetail(detail);
        InterfBranch branch = new InterfBranch();
        branch.setProdid(entity.getProdid());
        branch.setCdate(cDate);
        branch.setInterfId(inter);
        // 删除分行表信息
        result += getProdFoundMapper().deleteProdBranchInfo(branch);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfOutEntity entity)
        throws Exception {
        entity.setProdtype((String)getServiceInfo(PROD_TYPE));
        entity.setPinanBusinessType((String)getServiceInfo(PINGAN_BUSI_TYPE));
        deleteInterfData(entity);
        int result = insertProdInfo(entity);
        // 分层数据登录
        for (InterfProdLadderDetail info : entity.getLvlList()) {
            setBuyDrawAmount(info);
            result += insertLvlInfo(info);
        }
        // 分行数据登录
        for (InterfBranch info : entity.getBranchList()) {
            result += insertBranchInfo(info);
        }
        return result;
    }

    /**
     * 设置申赎金额
     * 
     * @param lvl 分层信息
     * @see
     * @since 1.0
     */
    private void setBuyDrawAmount(InterfProdLadderDetail lvl) {
        InterfProdLadderDetail lvl2 = getProdFoundMapper().selectLvlInfo(lvl);
        if (lvl.getAccountamountbyrate() != null && lvl2 != null
            && lvl2.getAccountamountbyrate() != null) {
            if (lvl.getAccountamountbyrate().compareTo(lvl2.getAccountamountbyrate()) > 0) {
                lvl.setBuyamount(
                    lvl.getAccountamountbyrate().subtract(lvl2.getAccountamountbyrate()));
            }
            else {
                lvl.setDrawamount(
                    lvl2.getAccountamountbyrate().subtract(lvl.getAccountamountbyrate()));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfOutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * 产品申赎信息登录
     * 
     * @param info 产品申赎信息
     * @return int 登录件数
     * @see
     * @since 1.0
     */
    public int insertProdInfo(InterfProdInfo info) {
        String interf = getInterfId();
        info.setCdate(getSysDate());
        info.setInterfId(interf);
        return interfProdIfMapper.insert(info);
    }

    /**
     * 分层信息登录
     * 
     * @param info 分层信息
     * @return int 登录件数
     * @see
     * @since 1.0
     */
    public int insertLvlInfo(InterfProdLadderDetail info) {
        String interf = getInterfId();
        info.setCdate(getSysDate());
        info.setInterfId(interf);
        return interfLvlMapper.insert(info);
    }

    /**
     * 分行信息登录
     * 
     * @param info 分行信息
     * @return int 登录件数
     * @see
     * @since 1.0
     */
    public int insertBranchInfo(InterfBranch info) {
        String interf = getInterfId();
        info.setCdate(getSysDate());
        info.setInterfId(interf);
        return interfBranchMapper.insert(info);
    }

    /**
     * 设置 交易类型
     * 
     * @param pinganBusiType 交易类型
     */
    public void setPinganBusiType(String pinganBusiType) {
        setServiceInfo(PINGAN_BUSI_TYPE, pinganBusiType);
    }

    /**
     * 设置 prodType
     * 
     * @param prodType prodType
     */
    public void setProdType(String prodType) {
        setServiceInfo(PROD_TYPE, prodType);
    }
}
