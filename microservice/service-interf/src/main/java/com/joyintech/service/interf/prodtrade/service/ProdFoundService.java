package com.joyintech.service.interf.prodtrade.service;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.prodtrade.dao.ProdStartToEndMapper;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 成立存续期到期产品查询Service
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see ProdFoundService
 * @since 1.0
 */
@Service
public class ProdFoundService {

    /**
     * 产品成立查询
     */
    @Autowired
    private ProdStartToEndMapper prodFoundMapper;

    /**
     * 取得成立产品
     * 
     * @param sysDate 日期
     * @param prodList 产品列表
     * @return List<ProdFoundDto> 成立产品列表
     * @see
     * @since 1.0
     */
    private List<InterfFinProdDto> getProdFoundInfo(String sysDate, List<String> prodList) {
        List<String> typeList = new ArrayList<>();
        typeList.add(BussinessDictCodeList.处理模式_开放阶梯.getValue());
        typeList.add(BussinessDictCodeList.处理模式_滚动.getValue());
        typeList.add(BussinessDictCodeList.处理模式_滚动N天.getValue());
        typeList.add(BussinessDictCodeList.处理模式_普通N天.getValue());
        return prodFoundMapper.selectProdFoundInfo(
            DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(), sysDate, typeList, prodList);
    }

    /**
     * 取得存续期产品
     * 
     * @param sysDate 日期
     * @param prodList 产品列表
     * @return List<ProdFoundDto> 存续期产品列表
     * @see
     * @since 1.0
     */
    private List<InterfFinProdDto> getProdAliveInfo(String sysDate, List<String> prodList) {
        List<String> typeList = new ArrayList<>();
        typeList.add(BussinessDictCodeList.处理模式_货币型.getValue());
        typeList.add(BussinessDictCodeList.处理模式_净值型.getValue());
        typeList.add(BussinessDictCodeList.处理模式_日添利.getValue());
        typeList.add(BussinessDictCodeList.处理模式_智能日添利.getValue());
        return prodFoundMapper.selectProdAliveInfo(
            DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(), sysDate, typeList, prodList);
    }

    /**
     * 取得到期产品
     * 
     * @param sysDate 日期
     * @param prodList 产品列表
     * @return List<ProdFoundDto> 到期产品列表
     * @see
     * @since 1.0
     */
    private List<InterfFinProdDto> getProdEndInfo(String sysDate, List<String> prodList) {
        return prodFoundMapper.selectProdEndInfo(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(),
            sysDate, prodList);
    }

    /**
     * 成立存续到期产品取得
     * 
     * @param type 成立存续到期类型
     * @param sysDate 平台日期
     * @param prodList 产品列表
     * @return 成立存续到期产品列表
     * @see
     * @since 1.0
     */
    public List<InterfFinProdDto> getProdFoundAliveEndInfo(InterfDictCodeList type, String sysDate,
                                                           List<String> prodList) {
        if (InterfDictCodeList.交易类型_成立.equals(type)) {
            return getProdFoundInfo(sysDate, prodList);
        }
        else if (InterfDictCodeList.交易类型_存续.equals(type)) {
            return getProdAliveInfo(sysDate, prodList);
        }
        else if (InterfDictCodeList.交易类型_到期.equals(type)) {
            return getProdEndInfo(sysDate, prodList);
        }
        return new ArrayList<>();
    }
}
