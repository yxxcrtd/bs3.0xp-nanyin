/*
 * 文件名：CstpTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.cstptrade.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfCstpTradeMapper;
import com.joyintech.tams.common.base.dao.InterfOpicsTradRepoMapper;
import com.joyintech.tams.common.base.dao.InterfTradRepoasstMapper;
import com.joyintech.tams.common.base.dao.InterfTradSecdealMapper;
import com.joyintech.tams.common.base.entity.InterfCstpTrade;
import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.cstptrade.dao.CstpTradeMapper;
import com.joyintech.service.interf.cstptrade.entity.SecurityEntity;
import com.joyintech.service.interf.cstptrade.entity.XmlFileEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.util.XmlParseUtil;

/**
 * cstp交易入库
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月19日
 * @see CstpTradeService
 * @since 1.0
 */
@Service
public class CstpTradeService extends InterfBaseService {

    /**
     * 库表生成的cstp交易处理
     */
    @Autowired
    private InterfCstpTradeMapper mapper;

    /**
     * 接口处理cstp
     */
    @Autowired
    private CstpTradeMapper cstpMapper;

    /**
     * 库表生成的债券交易处理
     */
    @Autowired
    private InterfTradSecdealMapper secMapper;

    /**
     * 库表生成的回购交易处理
     */
    @Autowired
    private InterfOpicsTradRepoMapper repoMapper;

    /**
     * 库表生成的质押式回购关联
     */
    @Autowired
    private InterfTradRepoasstMapper repoAsstMapper;

    /**
     * cstp交易入库
     * 
     * @param fileName 文件路径+文件名
     * @return 插入数据库条数
     * @see
     * @since 1.0
     */
    public int insertCstpTrade(String fileName) {

        // xml文件解析成bean
        XmlFileEntity xmlFileEntity = XmlParseUtil.readXmlToJavaBean(fileName,
            XmlFileEntity.class);
        InterfCstpTrade iCstpTrade = new InterfCstpTrade();
        BeanUtil.copyPropertiesIgnoreNull(iCstpTrade, xmlFileEntity);
        // 插入cstp交易表
        int count = mapper.insertSelective(iCstpTrade);

        // 从cstp分发到债券和回购两张表
        if (InterfDictCodeList.业务场景_债券.getValue().equals(xmlFileEntity.getBusinesstype())) {
            // 插入或更新债券交易表
            count += insertOrUpdateSec(xmlFileEntity);

        }
        else {
            // 插入或更新回购交易表
            count += insertOrUpdateRepo(xmlFileEntity);
        }

        return count;

    }

    /**
     * 插入或更新回购交易表
     * 
     * @param xmlFileEntity y 解析xml文件实体类
     * @return 条数
     * @see
     * @since 1.0
     */
    private int insertOrUpdateRepo(XmlFileEntity xmlFileEntity) {

        int count = 0;
        // 成交编号
        String execid = xmlFileEntity.getExecid();
        // 交易编号
        String dealno = cstpMapper.selectRepoByExecid(execid);
        // 本方交易员查询,交易员代码
        String traderId = cstpMapper.selectBuytrader(xmlFileEntity.getOurtraderchinesename());
        // 对手方交易员查询
        String sellerId = cstpMapper.selectSeller(xmlFileEntity.getInstitutionchinesefullname());

        InterfOpicsTradRepo interfOpicsTradRepo = cstpMapper.selectTradRepo(execid, getSysDate(),
            InterfDictCodeList.业务场景_回购.getValue());
        interfOpicsTradRepo.setDealno(dealno);
        interfOpicsTradRepo.setBuycost(
            selectPortfolioId(xmlFileEntity.getOurcapitalaccountnumber()));

        if (StringUtil.isNotEmpty(traderId)) {
            interfOpicsTradRepo.setBuytrader(traderId);
        }

        if (StringUtil.isNotEmpty(sellerId)) {
            interfOpicsTradRepo.setSeller(sellerId);
        }
        if (StringUtil.isNotEmpty(dealno)) {
            // 更新
            interfOpicsTradRepo.setTradeid(dealno);
            // 回购交易表新加主键会自动生成更新
            count += repoMapper.updateByPrimaryKeySelective(interfOpicsTradRepo);
        }
        else {
            // 插入
            dealno = cstpMapper.selectDealNo(getSysDate());
            interfOpicsTradRepo.setTradeid(dealno);
            count += repoMapper.insertSelective(interfOpicsTradRepo);
        }

        // 如果是质押式，需要插入或更新质押品信息
        if (StringUtil.isNotEmpty(xmlFileEntity.getSeList())) {
            count += insertOrUpdateRepoAsset(xmlFileEntity.getSeList(), execid);

        }
        return count;
    }

    /**
     * 插入或更新质押品信息
     * 
     * @param seList 抵押品信息
     * @param execid 成交编号
     * @return 条数
     * @see
     * @since 1.0
     */
    private int insertOrUpdateRepoAsset(List<SecurityEntity> seList, String execid) {
        int count = 0;
        for (SecurityEntity sEntity : seList) {
            InterfTradRepoasst interfTradRepoasst = new InterfTradRepoasst();
            BeanUtil.copyPropertiesIgnoreNull(interfTradRepoasst, sEntity);
            interfTradRepoasst.setTradeid(execid);
            // 折算比例 * 100
            interfTradRepoasst.setConvrate(new BigDecimal(100).multiply(sEntity.getConvrate()));
            // 成交金额=折算比例*券面总额
            interfTradRepoasst.setDealamt(
                sEntity.getConvrate().multiply(sEntity.getFacetotalamt()));
            // 判断抵押品信息是否存在，若存在则更新，否则插入
            String tradeid = cstpMapper.selectrepoAsset(sEntity.getSecid(), execid);
            if (StringUtil.isNotEmpty(tradeid)) {
                // 更新
                count += cstpMapper.updateRepoAsst(interfTradRepoasst);
            }
            else {
                count += repoAsstMapper.insertSelective(interfTradRepoasst);
            }

        }
        return count;
    }

    /**
     * 获取组合代码
     * 
     * @param ourcapitalaccountnumber 资金账号
     * @return 组合代码
     * @see
     * @since 1.0
     */
    private String selectPortfolioId(String ourcapitalaccountnumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("A08", DictCodeListExt1.资金账户类型_甲类户.getValue());
        map.put("A09", DictCodeListExt1.资金账户类型_上清所DVP账户.getValue());
        map.put("A10", DictCodeListExt1.资金账户类型_中债登DVP账户.getValue());
        map.put("ourcapitalaccountnumber", ourcapitalaccountnumber);

        return cstpMapper.selectPortfolioId(map);

    }

    /**
     * 插入或更新债券交易表
     * 
     * @param xmlFileEntity 解析xml文件实体类
     * @return 条数
     * @see
     * @since 1.0
     */
    private int insertOrUpdateSec(XmlFileEntity xmlFileEntity) {
        int count = 0;
        // 交易编号
        String dealno = cstpMapper.selectSecByExecid(xmlFileEntity.getExecid());
        // 本方交易员查询,交易员代码
        String traderId = cstpMapper.selectBuytrader(xmlFileEntity.getOurtraderchinesename());
        // 对手方交易员查询
        String sellerId = cstpMapper.selectSeller(xmlFileEntity.getInstitutionchinesefullname());

        InterfTradSecdeal interfCstpSecdeal = cstpMapper.selectTradeSec(xmlFileEntity.getExecid(),
            getSysDate(), InterfDictCodeList.业务场景_债券.getValue());
        interfCstpSecdeal.setProfolioId(
            selectPortfolioId(xmlFileEntity.getOurcapitalaccountnumber()));

        if (StringUtil.isNotEmpty(traderId)) {
            interfCstpSecdeal.setTrad(traderId);
        }

        if (StringUtil.isNotEmpty(sellerId)) {
            interfCstpSecdeal.setCno(sellerId);
        }
        // 根据原始交易编号，判断数据是否已经存在，如果已存在则更新，否则进入插入操作
        if (StringUtil.isNotEmpty(dealno)) {
            // 更新
            interfCstpSecdeal.setDealno(dealno);
            count += secMapper.updateByPrimaryKeySelective(interfCstpSecdeal);
        }
        else {
            dealno = cstpMapper.selectDealNo(getSysDate());
            interfCstpSecdeal.setDealno(dealno);
            // 插入
            count += secMapper.insertSelective(interfCstpSecdeal);
        }
        return count;
    }

}
