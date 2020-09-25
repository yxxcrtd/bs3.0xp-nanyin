/*
 * 文件名：ValBalanceUnusualQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.balanceunusualquery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.financemanage.balanceunusualquery.dao.ValBalanceUnusualQuery001Mapper;
import com.joyintech.service.valuation.financemanage.balanceunusualquery.dto.QueryDto;
import com.joyintech.service.valuation.financemanage.balanceunusualquery.dto.ValBalanceUnusualQuery001Dto;
import com.joyintech.tams.valuation.common.constants.DictCodeBaseSubjectNatrue;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 存续产品平仓资产余额异常查询
 * 
 * @author HuangJian
 * @version 1.0 2019年1月31日
 * @see ValBalanceUnusualQuery001Service
 * @since 1.0
 */
@Service
public class ValBalanceUnusualQuery001Service extends CommonExportService<ValBalanceUnusualQuery001Dto> {

    @Autowired
    private ValBalanceUnusualQuery001Mapper valBalanceUnusualQuery001Mapper;

    /**
     * 分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<ValBalanceUnusualQuery001Dto> queryPageList(Map<String, Object> params)
        throws Exception {
        // List<QueryDto> queryDtoList = new ArrayList<>();
        List<ValBalanceUnusualQuery001Dto> listDto = new ArrayList<>();
        String booksetId = (String)params.get("booksetId");
        String[] booksetidArr = null;
        if (StringUtil.isNotEmpty(booksetId)) {
            booksetidArr = booksetId.split(",");
        }
        String qrydate = (String)params.get("qrydate");
        params.put("booksetidArr", booksetidArr);
        // 2.1、债券类资产
        // 2.1.1、T+0资产处理
        // 2.1.1.1、查询出已平仓的债券类资产
        // 变量.成本科目性质组 = 基础科目性质.【公允价值损益类-成本、综合收益类成本、摊余成本类成本】
        String[] basesubnatures = {DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue(),
            DictCodeBaseSubjectNatrue.综合收益类成本.getValue(),
            DictCodeBaseSubjectNatrue.摊余成本类成本.getValue()};
        String[] finprodtypes = {DictCodeListExt1.金融产品类型_债券.getValue()};
        Map<String, List<String>> map1 = this.getEveningUpAssetsBond(qrydate, finprodtypes, booksetidArr,
            basesubnatures);
        // 2.1.2、判断清零科目是否已清零
        if (map1 != null) {
            for (String key : map1.keySet()) {
                if (map1.get(key) == null || "".equals(map1.get(key))) {

                }
                else {
                    // 2.1.2.1、查询当日交易份额是否为0，交易所按发生日统计，银行间按交割日统计
                    List<String> assetidArrs = this.getAssetNoZero(key, map1.get(key), qrydate);

                    if (assetidArrs == null || assetidArrs.size()==0) {
                        // 2.1.2.1、所有科目检查，针对查询日当日不存在银行间T+1的资产
                        String[] subjectno2 = {DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue()};
                        // list.addAll(this.getquery(qrydate, finprodtypes, subjectno2, key,
                        // map1.get(key),
                        // assetidArr));
                        // Map<String, Object> param = new HashMap<>();
                        QueryDto queryDto2 = new QueryDto();
                        // param.put("qrydate", qrydate);
                        queryDto2.setQrydate(qrydate);
                        // param.put("fintypes", finprodtypes);
                        queryDto2.setFintypes(finprodtypes);
                        // param.put("subjectnos", subjectno2);
                        queryDto2.setSubjectno(subjectno2);
                        // param.put("booksetId", key);
                        queryDto2.setBooksetId(key);
                        // param.put("assetidArr", map1.get(key));
//                        List<String> assts = map1.get(key);
//                        String[] newAsset = (String[])assts.toArray();
//                        if (StringUtil.isNotEmpty(assts)) {
//                            newAsset = assts.split(",");
//                        }
                        queryDto2.setAssetidArr(map1.get(key));
                        // param.put("array", assetidArr);
                        queryDto2.setNoNullArr(assetidArrs);
                        listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto2));
                    }
                    else {
                        // 2.1.2.1、公允价值科目检查，针对查询日当日存在银行间T+1的资产
                        String[] subjectno1 = {DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue()};
                        // list.addAll(this.getquery(qrydate, finprodtypes, subjectno1, key,
                        // assetidArr,
                        // null));
                        // Map<String, Object> param1 = new HashMap<>();
                        QueryDto queryDto1 = new QueryDto();
                        // param1.put("qrydate", qrydate);
                        queryDto1.setQrydate(qrydate);
                        // param1.put("fintypes", finprodtypes);
                        queryDto1.setFintypes(finprodtypes);
                        // param1.put("subjectnos", subjectno1);
                        queryDto1.setSubjectno(subjectno1);
                        // param1.put("booksetId", key);
                        queryDto1.setBooksetId(key);
                        // param1.put("assetidArr", assetidArr);

                        queryDto1.setAssetidArr(assetidArrs);
                        // param1.put("array", null);
                        queryDto1.setNoNullArr(null);
                        
                        
                        // queryDtoList.add(queryDto1);
                        // pages.setRoot((List<ValBalanceUnusualQuery001Dto>)super.queryForPage( ()
                        // ->
                        // valBalanceUnusualQuery001Mapper.queryPageList(param1)));
                        listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto1));
                        // 2.1.2.1、所有科目检查，针对查询日当日不存在银行间T+1的资产
                        String[] subjectno2 = {DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue()};
                        // list.addAll(this.getquery(qrydate, finprodtypes, subjectno2, key,
                        // map1.get(key),
                        // assetidArr));
                        // Map<String, Object> param = new HashMap<>();
                        QueryDto queryDto2 = new QueryDto();
                        // param.put("qrydate", qrydate);
                        queryDto2.setQrydate(qrydate);
                        // param.put("fintypes", finprodtypes);
                        queryDto2.setFintypes(finprodtypes);
                        // param.put("subjectnos", subjectno2);
                        queryDto2.setSubjectno(subjectno2);
                        // param.put("booksetId", key);
                        queryDto2.setBooksetId(key);
                        // param.put("assetidArr", map1.get(key));
//                        List<String> assts = map1.get(key);
//                        String[] newAsset = (String[])assts.toArray();
//                        if (StringUtil.isNotEmpty(assts)) {
//                            newAsset = assts.split(",");
//                        }
                        queryDto2.setAssetidArr(map1.get(key));
                        // param.put("array", assetidArr);
                        queryDto2.setNoNullArr(assetidArrs);
                        listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto2));
                        // queryDtoList.add(queryDto2);
                        // pages.setRoot((List<ValBalanceUnusualQuery001Dto>)super.queryForPage( ()
                        // ->
                        // valBalanceUnusualQuery001Mapper.queryPageList(param)));
                    }
                }
            }
        }
        // 2.2、非债券类资产
        // 2.2.1、查询出已平仓的非债券类资产
        // 变量.成本科目性质组 = 基础科目性质.【公允价值损益类-成本、综合收益类成本、摊余成本类成本】
        String[] basesubnaturesNoBond = {DictCodeBaseSubjectNatrue.计划类资产_成本.getValue(),
            DictCodeBaseSubjectNatrue.成本.getValue(), DictCodeBaseSubjectNatrue.基金投资_成本.getValue(),
            DictCodeBaseSubjectNatrue.债券借贷成本.getValue(),
            DictCodeBaseSubjectNatrue.计划类_货币型资产_成本.getValue(),
            DictCodeBaseSubjectNatrue.计划类_净值型资产_成本.getValue()};

        String[] finprodtypesNoBond = {DictCodeListExt1.金融产品类型_债券质押式正回购.getValue(),
            DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue(), DictCodeListExt1.金融产品类型_债券买断式正回购.getValue(),
            DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue(), DictCodeListExt1.金融产品类型_基金.getValue(),
            DictCodeListExt1.金融产品类型_存款.getValue(), DictCodeListExt1.金融产品类型_拆入.getValue(),
            DictCodeListExt1.金融产品类型_拆出.getValue(), DictCodeListExt1.金融产品类型_计划类资产.getValue()};
        Map<String, List<String>> map4 = getEveningUpAssetsNoBond(finprodtypesNoBond,
            basesubnaturesNoBond, qrydate, booksetidArr);
        // 变量.清零科目性质组
        String[] subjectno4 = {DictCodeBaseSubjectNatrue.计划类资产_公允价值变动.getValue(),
            DictCodeBaseSubjectNatrue.计划类资产_利息调整.getValue(),
            DictCodeBaseSubjectNatrue.应计利息.getValue(), DictCodeBaseSubjectNatrue.应收红利.getValue(),
            DictCodeBaseSubjectNatrue.公允价值变动.getValue(),
            DictCodeBaseSubjectNatrue.应收债券借贷费用.getValue(),
            DictCodeBaseSubjectNatrue.应付债券借贷借入费.getValue()};
        if (map4 != null) {
            for (String key : map4.keySet()) {
                List<String> assetidArrs = map4.get(key);

                if (assetidArrs == null || assetidArrs.size()==0) {

                }
                else {
                    // List<ValBalanceUnusualQuery001Dto> querylist = getquery(qrydate,
                    // finprodtypesNoBond,
                    // subjectno4, key, assetidArr,null);
                    // list.addAll(querylist);
                    // Map<String, Object> param = new HashMap<>();
                    QueryDto queryDto3 = new QueryDto();
                    // param.put("qrydate", qrydate);
                    queryDto3.setQrydate(qrydate);
                    // param.put("fintypes", finprodtypesNoBond);
                    queryDto3.setFintypes(finprodtypesNoBond);
                    // param.put("subjectnos", subjectno4);
                    queryDto3.setSubjectno(subjectno4);
                    // param.put("booksetId", key);
                    queryDto3.setBooksetId(key);
                    // param.put("assetidArr", assetidArr);

                    queryDto3.setAssetidArr(assetidArrs);
                    // param.put("array", null);
                    queryDto3.setNoNullArr(null);
                    // queryDtoList.add(queryDto3);
                    listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto3));
                    // pages.setRoot((List<ValBalanceUnusualQuery001Dto>)super.queryForPage( () ->
                    // valBalanceUnusualQuery001Mapper.queryPageList(param)));
                }
            }
        }
        // Collections.sort(list,new Comparator(){
        // @Override
        // public int compare(Object o1, Object o2) {
        // ValBalanceUnusualQuery001Dto u1 = (ValBalanceUnusualQuery001Dto)o1;
        // ValBalanceUnusualQuery001Dto u2 = (ValBalanceUnusualQuery001Dto)o2;
        // int i = u1.getProdId().compareTo(u2.getProdId());
        // if(i==0){
        // int j = u1.getFinprodId().compareTo(u2.getFinprodId());
        // if(j==0){
        // return u1.getSubjectNo().compareTo(u2.getSubjectNo());
        // }
        // return j;
        // }
        // return i;
        // }
        // });
        // if (queryDtoList == null || queryDtoList.size() == 0) {
        // return new Page<ValBalanceUnusualQuery001Dto>();
        // }
        // else {
        // Page<ValBalanceUnusualQuery001Dto> page = super.queryForPage(
        // () -> valBalanceUnusualQuery001Mapper.queryPageList(queryDtoList,qrydate));
        // List<ValBalanceUnusualQuery001Dto> listDto = new ArrayList<>();
        // for(QueryDto item:queryDtoList){
        //
        // listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(item));
        // }
        // Page<ValBalanceUnusualQuery001Dto> page = new
        // Page<ValBalanceUnusualQuery001Dto>(listDto);
        // page.setRecords(listDto.size());
        // page.setTotal(listDto.size());
        for(int i=0;i<listDto.size();i++){
            listDto.get(i).setOrderId(Integer.toString((i+1)));
        }
        
        return new Page<ValBalanceUnusualQuery001Dto>(listDto);

        // return page;
        // }

    }

    // /**
    // * 异常查询
    // *
    // * @param qrydate 查询日期
    // * @param fintypes 资产类型组
    // * @param subjectno 清零科目性质组
    // * @param booksetId 账套代码
    // * @param assetidArr 资产代码组
    // * @return
    // * @see
    // * @since 1.0
    // */
    // public List<ValBalanceUnusualQuery001Dto> getquery(String qrydate, String[] fintypes,
    // String[] subjectnos, String booksetId,String assetidArr,String noNullArr) {
    // Map<String, Object> params = new HashMap<>();
    // params.put("qrydate", qrydate);
    // params.put("fintypes", fintypes);
    // params.put("subjectnos", subjectnos);
    // params.put("booksetId", booksetId);
    // params.put("assetidArr", assetidArr);
    // params.put("array", noNullArr);
    // return valBalanceUnusualQuery001Mapper.queryPageList(params);
    //
    // }
    /**
     * 导出
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public List<ValBalanceUnusualQuery001Dto> getAllList(Map<String, Object> params) {
        // List<QueryDto> queryDtoList = new ArrayList<>();
        List<ValBalanceUnusualQuery001Dto> listDto = new ArrayList<>();
        String booksetId = (String)params.get("booksetId");
        String[] booksetidArr = null;
        if (StringUtil.isNotEmpty(booksetId)) {
            booksetidArr = booksetId.split(",");
        }
        String qrydate = (String)params.get("qrydate");
        params.put("booksetidArr", booksetidArr);
        // 2.1、债券类资产
        // 2.1.1、T+0资产处理
        // 2.1.1.1、查询出已平仓的债券类资产
        // 变量.成本科目性质组 = 基础科目性质.【公允价值损益类-成本、综合收益类成本、摊余成本类成本】
        String[] basesubnatures = {DictCodeBaseSubjectNatrue.公允价值损益类_成本.getValue(),
            DictCodeBaseSubjectNatrue.综合收益类成本.getValue(),
            DictCodeBaseSubjectNatrue.摊余成本类成本.getValue()};
        String[] finprodtypes = {DictCodeListExt1.金融产品类型_债券.getValue()};
        Map<String, List<String>> map1 = this.getEveningUpAssetsBond(qrydate, finprodtypes, booksetidArr,
            basesubnatures);
        // 2.1.2、判断清零科目是否已清零
        if (map1 != null) {
            for (String key : map1.keySet()) {
                if (map1.get(key) == null || "".equals(map1.get(key))) {

                }
                else {
                    // 2.1.2.1、查询当日交易份额是否为0，交易所按发生日统计，银行间按交割日统计
                    List<String> assetidArrs = this.getAssetNoZero(key, map1.get(key), qrydate);

                    if (assetidArrs == null || assetidArrs.size()==0) {
                        // 2.1.2.1、所有科目检查，针对查询日当日不存在银行间T+1的资产
                        String[] subjectno2 = {DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue()};
                        // list.addAll(this.getquery(qrydate, finprodtypes, subjectno2, key,
                        // map1.get(key),
                        // assetidArr));
                        // Map<String, Object> param = new HashMap<>();
                        QueryDto queryDto2 = new QueryDto();
                        // param.put("qrydate", qrydate);
                        queryDto2.setQrydate(qrydate);
                        // param.put("fintypes", finprodtypes);
                        queryDto2.setFintypes(finprodtypes);
                        // param.put("subjectnos", subjectno2);
                        queryDto2.setSubjectno(subjectno2);
                        // param.put("booksetId", key);
                        queryDto2.setBooksetId(key);
                        // param.put("assetidArr", map1.get(key));
//                        List<String> assts = map1.get(key);
//                        String[] newAsset = (String[])assts.toArray();
//                        if (StringUtil.isNotEmpty(assts)) {
//                            newAsset = assts.split(",");
//                        }
                        queryDto2.setAssetidArr(map1.get(key));
                        // param.put("array", assetidArr);
                        queryDto2.setNoNullArr(assetidArrs);
                        listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto2));
                    }
                    else {
                        // 2.1.2.1、公允价值科目检查，针对查询日当日存在银行间T+1的资产
                        String[] subjectno1 = {DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue()};
                        // list.addAll(this.getquery(qrydate, finprodtypes, subjectno1, key,
                        // assetidArr,
                        // null));
                        // Map<String, Object> param1 = new HashMap<>();
                        QueryDto queryDto1 = new QueryDto();
                        // param1.put("qrydate", qrydate);
                        queryDto1.setQrydate(qrydate);
                        // param1.put("fintypes", finprodtypes);
                        queryDto1.setFintypes(finprodtypes);
                        // param1.put("subjectnos", subjectno1);
                        queryDto1.setSubjectno(subjectno1);
                        // param1.put("booksetId", key);
                        queryDto1.setBooksetId(key);
                        // param1.put("assetidArr", assetidArr);

                        queryDto1.setAssetidArr(assetidArrs);
                        // param1.put("array", null);
                        queryDto1.setNoNullArr(null);
                        
                        
                        // queryDtoList.add(queryDto1);
                        // pages.setRoot((List<ValBalanceUnusualQuery001Dto>)super.queryForPage( ()
                        // ->
                        // valBalanceUnusualQuery001Mapper.queryPageList(param1)));
                        listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto1));
                        // 2.1.2.1、所有科目检查，针对查询日当日不存在银行间T+1的资产
                        String[] subjectno2 = {DictCodeBaseSubjectNatrue.公允价值损益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.公允价值损益类应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类_公允价值变动.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.摊余成本类债券应计利息.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类利息调整.getValue(),
                            DictCodeBaseSubjectNatrue.综合收益类应计利息.getValue()};
                        // list.addAll(this.getquery(qrydate, finprodtypes, subjectno2, key,
                        // map1.get(key),
                        // assetidArr));
                        // Map<String, Object> param = new HashMap<>();
                        QueryDto queryDto2 = new QueryDto();
                        // param.put("qrydate", qrydate);
                        queryDto2.setQrydate(qrydate);
                        // param.put("fintypes", finprodtypes);
                        queryDto2.setFintypes(finprodtypes);
                        // param.put("subjectnos", subjectno2);
                        queryDto2.setSubjectno(subjectno2);
                        // param.put("booksetId", key);
                        queryDto2.setBooksetId(key);
                        // param.put("assetidArr", map1.get(key));
//                        List<String> assts = map1.get(key);
//                        String[] newAsset = (String[])assts.toArray();
//                        if (StringUtil.isNotEmpty(assts)) {
//                            newAsset = assts.split(",");
//                        }
                        queryDto2.setAssetidArr(map1.get(key));
                        // param.put("array", assetidArr);
                        queryDto2.setNoNullArr(assetidArrs);
                        listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto2));
                        // queryDtoList.add(queryDto2);
                        // pages.setRoot((List<ValBalanceUnusualQuery001Dto>)super.queryForPage( ()
                        // ->
                        // valBalanceUnusualQuery001Mapper.queryPageList(param)));
                    }
                }
            }
        }
        // 2.2、非债券类资产
        // 2.2.1、查询出已平仓的非债券类资产
        // 变量.成本科目性质组 = 基础科目性质.【公允价值损益类-成本、综合收益类成本、摊余成本类成本】
        String[] basesubnaturesNoBond = {DictCodeBaseSubjectNatrue.计划类资产_成本.getValue(),
            DictCodeBaseSubjectNatrue.成本.getValue(), DictCodeBaseSubjectNatrue.基金投资_成本.getValue(),
            DictCodeBaseSubjectNatrue.债券借贷成本.getValue(),
            DictCodeBaseSubjectNatrue.计划类_货币型资产_成本.getValue(),
            DictCodeBaseSubjectNatrue.计划类_净值型资产_成本.getValue()};

        String[] finprodtypesNoBond = {DictCodeListExt1.金融产品类型_债券质押式正回购.getValue(),
            DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue(), DictCodeListExt1.金融产品类型_债券买断式正回购.getValue(),
            DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue(), DictCodeListExt1.金融产品类型_基金.getValue(),
            DictCodeListExt1.金融产品类型_存款.getValue(), DictCodeListExt1.金融产品类型_拆入.getValue(),
            DictCodeListExt1.金融产品类型_拆出.getValue(), DictCodeListExt1.金融产品类型_计划类资产.getValue()};
        Map<String, List<String>> map4 = getEveningUpAssetsNoBond(finprodtypesNoBond,
            basesubnaturesNoBond, qrydate, booksetidArr);
        // 变量.清零科目性质组
        String[] subjectno4 = {DictCodeBaseSubjectNatrue.计划类资产_公允价值变动.getValue(),
            DictCodeBaseSubjectNatrue.计划类资产_利息调整.getValue(),
            DictCodeBaseSubjectNatrue.应计利息.getValue(), DictCodeBaseSubjectNatrue.应收红利.getValue(),
            DictCodeBaseSubjectNatrue.公允价值变动.getValue(),
            DictCodeBaseSubjectNatrue.应收债券借贷费用.getValue(),
            DictCodeBaseSubjectNatrue.应付债券借贷借入费.getValue()};
        if (map4 != null) {
            for (String key : map4.keySet()) {
                List<String> assetidArrs = map4.get(key);

                if (assetidArrs == null || assetidArrs.size()==0) {

                }
                else {
                    // List<ValBalanceUnusualQuery001Dto> querylist = getquery(qrydate,
                    // finprodtypesNoBond,
                    // subjectno4, key, assetidArr,null);
                    // list.addAll(querylist);
                    // Map<String, Object> param = new HashMap<>();
                    QueryDto queryDto3 = new QueryDto();
                    // param.put("qrydate", qrydate);
                    queryDto3.setQrydate(qrydate);
                    // param.put("fintypes", finprodtypesNoBond);
                    queryDto3.setFintypes(finprodtypesNoBond);
                    // param.put("subjectnos", subjectno4);
                    queryDto3.setSubjectno(subjectno4);
                    // param.put("booksetId", key);
                    queryDto3.setBooksetId(key);
                    // param.put("assetidArr", assetidArr);

                    queryDto3.setAssetidArr(assetidArrs);
                    // param.put("array", null);
                    queryDto3.setNoNullArr(null);
                    // queryDtoList.add(queryDto3);
                    listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(queryDto3));
                    // pages.setRoot((List<ValBalanceUnusualQuery001Dto>)super.queryForPage( () ->
                    // valBalanceUnusualQuery001Mapper.queryPageList(param)));
                }
            }
        }
        // Collections.sort(list,new Comparator(){
        // @Override
        // public int compare(Object o1, Object o2) {
        // ValBalanceUnusualQuery001Dto u1 = (ValBalanceUnusualQuery001Dto)o1;
        // ValBalanceUnusualQuery001Dto u2 = (ValBalanceUnusualQuery001Dto)o2;
        // int i = u1.getProdId().compareTo(u2.getProdId());
        // if(i==0){
        // int j = u1.getFinprodId().compareTo(u2.getFinprodId());
        // if(j==0){
        // return u1.getSubjectNo().compareTo(u2.getSubjectNo());
        // }
        // return j;
        // }
        // return i;
        // }
        // });
        // if (queryDtoList == null || queryDtoList.size() == 0) {
        // return new Page<ValBalanceUnusualQuery001Dto>();
        // }
        // else {
        // Page<ValBalanceUnusualQuery001Dto> page = super.queryForPage(
        // () -> valBalanceUnusualQuery001Mapper.queryPageList(queryDtoList,qrydate));
        // List<ValBalanceUnusualQuery001Dto> listDto = new ArrayList<>();
        // for(QueryDto item:queryDtoList){
        //
        // listDto.addAll(valBalanceUnusualQuery001Mapper.queryPageListo(item));
        // }
        // Page<ValBalanceUnusualQuery001Dto> page = new
        // Page<ValBalanceUnusualQuery001Dto>(listDto);
        // page.setRecords(listDto.size());
        // page.setTotal(listDto.size());
        for(int i=0;i<listDto.size();i++){
            listDto.get(i).setOrderId(Integer.toString((i+1)));
        }
        
        return listDto;

        // return page;
        // }

    }

    /**
     * 债券
     * 
     * @param tflag T+0/T+1标志:0/1
     * @param gflag 是否公允价值科目
     * @param qrydate 日期
     * @param finprodtypes 资产类型组
     * @param booksetidArr 账套代码组
     * @param basesubnatures 成本科目性质组
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, List<String>> getEveningUpAssetsBond(String qrydate, String[] finprodtypes,
                                                      String[] booksetidArr,
                                                      String[] basesubnatures) {
        Map<String, Object> param = new HashMap<>();
        param.put("finprodtypes", finprodtypes);
        param.put("basesubnatures", basesubnatures);
        param.put("qrydate", qrydate);
        param.put("booksetIdArr", booksetidArr);
        Map<String, List<String>> map = new HashMap<>();
        List<ValBalanceUnusualQuery001Dto> list = valBalanceUnusualQuery001Mapper.getEveningUpAssetsBond(
            param);
        for (ValBalanceUnusualQuery001Dto item : list) {
            String key = item.getBooksetId();
            if (map.containsKey(key)) {
                map.get(key).add(item.getBusiId());
            }else{
                List<String> assetidArr = new ArrayList<>();
                assetidArr.add(item.getBusiId());
                map.put(key, assetidArr);
            }
        }

        return map;

    }

    /**
     * 非债券
     * 
     * @param finprodtypes 资产类型组
     * @param basesubnatures 成本科目性质组
     * @param qrydate 日期
     * @param booksetidArr 账套代码组
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, List<String>> getEveningUpAssetsNoBond(String[] finprodtypes,
                                                        String[] basesubnatures, String qrydate,
                                                        String[] booksetidArr) {
        Map<String, Object> param = new HashMap<>();
        param.put("finprodtypes", finprodtypes);
        param.put("basesubnatures", basesubnatures);
        param.put("qrydate", qrydate);
        param.put("booksetIdArr", booksetidArr);

        Map<String, List<String>> map = new HashMap<>();
        List<ValBalanceUnusualQuery001Dto> list = valBalanceUnusualQuery001Mapper.getEveningUpAssetsNoBond(
            param);
        for (ValBalanceUnusualQuery001Dto item : list) {
            String key = item.getBooksetId();
            if (map.containsKey(key)) {
                map.get(key).add(item.getBusiId());
            }else{
                List<String> assetidArr = new ArrayList<>();
                assetidArr.add(item.getBusiId());
                map.put(key, assetidArr);
            }
        }
        return map;
    }

    /**
     * 查询日当日份额不为0的资产
     * 
     * @param booksetId
     * @param assetidArr
     * @param qrydate
     * @return
     * @see
     * @since 1.0
     */
    public List<String> getAssetNoZero(String booksetId, List<String> assetidArr, String qrydate) {
        Map<String, Object> param = new HashMap<>();
        param.put("booksetId", booksetId);
        param.put("assetidArr", assetidArr);
        param.put("qrydate", qrydate);
//        String assetids = null;
        List<String> list = valBalanceUnusualQuery001Mapper.getAssetNoZero(
            param);
       
        

        return list;

    }

}
