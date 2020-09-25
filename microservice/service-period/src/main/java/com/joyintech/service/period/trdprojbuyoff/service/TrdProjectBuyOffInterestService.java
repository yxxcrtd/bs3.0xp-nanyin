package com.joyintech.service.period.trdprojbuyoff.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PaFinProductBuyoutRateMapper;
import com.joyintech.tams.common.base.entity.PaFinProductBuyoutRate;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.service.period.trdprojbuyoff.dao.TrdProjectBuyOffInterestMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.trdprojbuyoff.TrdProjectBuyOffInterestDto;

/**
 * @program: component-base
 * @description: 买断利率维护Service
 * @author: 强成西
 * @create: 2018-10-23 14:53
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdProjectBuyOffInterestService extends BaseService<TrdProjectBuyOffInterestDto> {

    @Autowired
    private PaFinProductBuyoutRateMapper paFinProductBuyoutRateMapper;

    @Autowired
    private TrdProjectBuyOffInterestMapper trdProjectBuyOffInterestMapper;

    public Page<TrdProjectBuyOffInterestDto> findPage(int page, int length,
                                                      Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return queryForPageForSelectReturn(page, length,
            () -> trdProjectBuyOffInterestMapper.findPage(param));
    }

    public Page<SelectOptionDto> getProjAssetId(int page, int length, Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return queryForPageForSelectReturn(page, length,
            () -> trdProjectBuyOffInterestMapper.getProjAssetId(param));
    }

    public String save(TrdProjectBuyOffInterestDto dto) {
        PaFinProductBuyoutRate rate = new PaFinProductBuyoutRate();
        rate.setBuyoutRate(
            ArithmeticUtil.divide(dto.getRate(), new BigDecimal(100), 10, RoundingMode.HALF_UP));
        rate.setFinprodId(dto.getFinprodId());
        rate.setEffDate(DateUtil.parse(dto.getEffDate()));
        rate.setCreateUser(dto.getCreateUser());
        rate.setUpdateTime(dto.getUpdateTime());
        rate.setCreateTime(dto.getCreateTime());
        rate.setCreateDept(dto.getCreateDept());
        rate.setUpdateUser(dto.getUpdateUser());
        paFinProductBuyoutRateMapper.deleteByPrimaryKey(rate);
        paFinProductBuyoutRateMapper.insert(rate);
        return Boolean.TRUE.toString();
    }

    public int selectCountForMatch(TrdProjectBuyOffInterestDto dto) {
        return trdProjectBuyOffInterestMapper.selectCountForMatch(dto);
    }

    public String checkIdExists(List<TrdProjectBuyOffInterestDto> equityList) {
        Map<String, Object> param = new HashMap<>();
        param.put("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        param.put("list", equityList);
        return trdProjectBuyOffInterestMapper.checkIdExists(param);
    }

    public String saveList(List<TrdProjectBuyOffInterestDto> equityList) {
        int size = equityList.size();
        if (size > 1000) {
            int part = size / 1000;// 分批数
            for (int i = 0; i < part; i++ ) {
                trdProjectBuyOffInterestMapper.deleteBuyOff(equityList.subList(0, 1000));
                trdProjectBuyOffInterestMapper.insertBuyOff(equityList.subList(0, 1000));
                equityList.subList(0, 1000).clear();
            }
            if (!equityList.isEmpty()) {
                trdProjectBuyOffInterestMapper.deleteBuyOff(equityList);
                trdProjectBuyOffInterestMapper.insertBuyOff(equityList);
            }
        }
        else {
            trdProjectBuyOffInterestMapper.deleteBuyOff(equityList);
            trdProjectBuyOffInterestMapper.insertBuyOff(equityList);
        }
        equityList.clear();
        return Boolean.TRUE.toString();
    }

    public String deleteData(List<TrdProjectBuyOffInterestDto> equityList) {
        int size = equityList.size();
        if (size > 1000) {
            int part = size / 1000;// 分批数
            for (int i = 0; i < part; i++ ) {
                trdProjectBuyOffInterestMapper.deleteBuyOff(equityList.subList(0, 1000));
                equityList.subList(0, 1000).clear();
            }
            if (!equityList.isEmpty()) {
                trdProjectBuyOffInterestMapper.deleteBuyOff(equityList);
            }
        }
        else {
            trdProjectBuyOffInterestMapper.deleteBuyOff(equityList);
        }
        equityList.clear();
        return Boolean.TRUE.toString();
    }

    public TrdProjectBuyOffInterestDto findBuyOffInfo(Map<String, String> params) {
        params.put("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return trdProjectBuyOffInterestMapper.findBuyOffInfo(params);
    }
}