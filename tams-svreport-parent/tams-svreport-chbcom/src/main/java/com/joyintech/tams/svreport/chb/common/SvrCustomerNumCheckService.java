package com.joyintech.tams.svreport.chb.common;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbPrdCustNumIfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.common.dao.ChbCustomerNumCheckMapper;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 客户数检查共通方法
 */
@Service
public class SvrCustomerNumCheckService {


    @Autowired
    private ChbCustomerNumCheckMapper chbCustomerNumCheckMapper;

    @Autowired
    private CheckReport001Mapper checkReport001Mapper;
    /**
     * 客户数检查-数据接入用方法
     *
     * @param listData
     * @param
     */
    public void cusNumChkInterf(List<Map<String, String>> listData,String   checkDate) {
        //定义返回对象
        List<CheckReport001Dto> resultList = new ArrayList<>();


        //新建对象： map<string 产品ID，产品客户数信息DTO>
        HashMap<String, ChbPrdCustNumIfo> prdMap = new HashMap<>();

        //新建对象： 产品列表list<Stirng>  对象产品list
        List<String> prdList = new ArrayList<>();

        //以输入参数.ListData为基础、进行循环处理，生成产品维度的客户数数据
        for (Map<String, String> map : listData) {
            //以此条数据的产品代码为Key,进行是否认购数据判断，当此条数据为认购数据的情况下，进行以下处理：
            if (!StringUtil.isEmpty(map.get("prinamt")) && new BigDecimal(map.get("prinamt")).compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal cust_counts = new BigDecimal(map.get("CUST_COUNTS"));

                //在【产品信息map】中存在prodDealInterfService
                if (prdMap.containsKey(map.get("PROD_ID"))) {

                    // 产品信息map.产品客户数信息DTO.持有客户数  + 此条信息的.保有量客户数
                    prdMap.get(map.get("PROD_ID")).getCustHeldNum().add(cust_counts);

                } else {
                    //在【产品信息map】中不存在
                    //以此条记录的信息，对【产品客户信息DTO】进行设值
                    ChbPrdCustNumIfo custNumIfo = new ChbPrdCustNumIfo();
                    custNumIfo.setPrdCode(map.get("PROD_ID"));
                    custNumIfo.setInspectionResults(null);
                    custNumIfo.setWarningInformation(null);
                    //custNumIfo.setDataOfDate(map.get());
                    BigDecimal counts = cust_counts;
                    custNumIfo.setCustHeldNum(counts);
                    custNumIfo.setThrCustNum(new BigDecimal("0"));
                    //往产品信息Map中，设值<此条记录的.产品代码，循环中新建的.产品客户信息DTO>
                    prdMap.put(map.get("PROD_ID"), custNumIfo);
                    //对象产品list.add(此条数据的产品ID）
                    prdList.add(map.get("PROD_ID"));
                }
            }

            //组装一二期客户数信息
            List<ChbPrdCustNumIfo> lsitOtbch =new ArrayList<ChbPrdCustNumIfo>(prdMap.values());

            // 2、以1中处理【对象产品List】为查询条件，查询三期数据、并进行比较处理
            List<ChbPrdCustNumIfo> lsitOtbchThree = chbCustomerNumCheckMapper.getraiseCustNumThree(prdList, DateUtil.parse(checkDate));

            //2.2 、比较一二期 与 三期的客户数

            //a、三期不存在、一二期存在  &&  c、三期存在、一二期存在，但是客户数不一致
            complareList(lsitOtbchThree, lsitOtbch, DateUtil.parse(checkDate), resultList, false);
            //b、三期存在，一二期不存在
            complareList(lsitOtbch, lsitOtbchThree, DateUtil.parse(checkDate), resultList, true);

            //3、检查结果数据、批量入库

            int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
            int batchLastIndex = batchCount;// 每批最后一个的下标
            for (int index = 0; index < resultList.size();) {
                if (batchLastIndex > resultList.size() - 1) {
                    batchLastIndex = resultList.size();
                    checkReport001Mapper.batchInsert(resultList.subList(index, batchLastIndex));
                    break;// 数据删除完毕,退出循环
                } else {
                    checkReport001Mapper.batchInsert(resultList.subList(index, batchLastIndex));
                    index = batchLastIndex;// 设置下一批下标
                    batchLastIndex = index + batchCount;
                }
            }
        }

    }


    /**
     * 客户数检查-批前检查用方法
     * 募集期登记检查-客户数一二三期一致性检查
     *
     * @param checkdate
     * @return
     */
    public List<CheckReport001Dto> checkProdRaiseTotalRegCusNum(Date checkdate) {
        //定义返回对象
        List<CheckReport001Dto> returList = new ArrayList<>();

        //新建对象： map<string 产品ID，产品客户数信息DTO>
        Map<String, ChbPrdCustNumIfo> prdMap = new HashMap<>();

        //新建对象： 产品列表list<Stirng>  对象产品list
        List<String> prodList = new ArrayList<>();

        //1、组装一二期客户数信息
        List<ChbPrdCustNumIfo> lsitOtbch = chbCustomerNumCheckMapper.getraiseCustNum(checkdate);

        //往产品信息Map中，设值<此条记录的.产品代码，循环中新建的.产品客户信息DTO>
        for (ChbPrdCustNumIfo info : lsitOtbch) {
            prdMap.put(info.getPrdCode(), info);
            prodList.add(info.getPrdCode());
        }

        //以1中处理【对象产品List】为查询条件，查询三期数据
        List<ChbPrdCustNumIfo> lsitOtbchThree = chbCustomerNumCheckMapper.getraiseCustNumThree(prodList, checkdate);

        //a、三期不存在、一二期存在  &&  c、三期存在、一二期存在，但是客户数不一致
        complareList(lsitOtbchThree, lsitOtbch, checkdate, returList, false);
        //b、三期存在，一二期不存在
        complareList(lsitOtbch, lsitOtbchThree, checkdate, returList, true);


        return returList;
    }

    /**
     * 比较2个list中是否相互存在
     * otList  一二期的集合     thList  三期的集合
     *
     * @param otList
     * @param thList
     * @return
     */
    public void complareList(List<ChbPrdCustNumIfo> otList, List<ChbPrdCustNumIfo> thList, Date checkdate,
                             List<CheckReport001Dto> returList, boolean flag) {
        Map<String, ChbPrdCustNumIfo> map = new HashMap<>();
        //otList 转为map
        if (otList != null) {
            map = otList.stream().collect(Collectors.toMap(ChbPrdCustNumIfo::getPrdCode, Function.identity(), (key1, key2) -> key2));
        }
        if (thList != null && thList.size() > 0) {
            for (ChbPrdCustNumIfo cinfo : thList) {
                if (!map.containsKey(cinfo.getPrdCode())) {
                    CheckReport001Dto dto = new CheckReport001Dto();
                    //检查序列号  自动生成
                    dto.setCheckSeqno(IDGenerator.generate(32));
                    //报表ID=DictCodeListExt2.报表名称_募集总量登记.getValue()
                    dto.setRepCode(DictCodeListExt2.报表名称_募集总量登记.getValue());
                    //唯一标识="产品代码:" + 此条记录的.产品代码
                    dto.setValidKey("产品代码" + cinfo.getPrdCode());
                    //检查结果
                    dto.setCheckResult("03");
                    //警告信息
                    if (flag) {
                        dto.setWarnInfo("客户数不一致（批前检查），三期客户数：{" + cinfo.getCustHeldNum() + "}、一二期客户数：{" + 0 + "}；");
                    } else {
                        dto.setWarnInfo("客户数不一致（批前检查），三期客户数：{" + 0 + "}、一二期客户数：{" + cinfo.getCustHeldNum() + "}；");
                    }
                    //数据日期
                    dto.setDataTime(checkdate);
                    returList.add(dto);
                } else {
                    //c、三期存在、一二期存在，但是客户数不一致
                    if (cinfo.getCustHeldNum().compareTo(map.get(cinfo.getPrdCode()).getCustHeldNum()) != 0 &&
                            !flag) {
                        CheckReport001Dto dto = new CheckReport001Dto();
                        //检查序列号  自动生成
                        dto.setCheckSeqno(IDGenerator.generate(32));
                        //报表ID=DictCodeListExt2.报表名称_募集总量登记.getValue()
                        dto.setRepCode(DictCodeListExt2.报表名称_募集总量登记.getValue());
                        //唯一标识="产品代码:" + 此条记录的.产品代码
                        dto.setValidKey("产品代码" + cinfo.getPrdCode());
                        //检查结果
                        dto.setCheckResult("03");
                        //警告信息
                        dto.setWarnInfo("客户数不一致（批前检查），三期客户数：{" + cinfo.getCustHeldNum() + "}、一二期客户数：{" + map.get(cinfo.getPrdCode()).getCustHeldNum() + "}；");
                        //数据日期
                        dto.setDataTime(checkdate);
                        returList.add(dto);
                    }
                }
            }

        }

    }




}


