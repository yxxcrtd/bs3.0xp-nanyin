package com.joyintech.tams.svreport.chb.monthreportdetail.service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.interf.common.constant.InterfMappingConstants;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailAssetMapper;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.position.dto.ChbAssetPosition;
import com.joyintech.tams.svreport.position.service.AssetPositionService;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
public class ChbMonthReportDetailAssetUtil {

    @Autowired
    private ChbMonthReportDetailAssetMapper chbMonthReportDetailAssetMapper;


    /**
     * 映射配置mapper
     */
    @Autowired
    private ChbTypeMappingExMapper chbTypeMappingExMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    public int reportInsertCount;


    private static List<String> fbCode = Arrays.asList("1201","1210","1202","1203","1204","1205","1206","1207","1208","1209","1299","2201","2202","2101","2299");



    /**
     * 数据赋值
     * @param prdPosition
     * @param info
     * @param flagCode
     * @return
     */
    public static ChbMonthAssetPositiondetail buildAssetDetail(ChbMonthAssetPosition position, ChbPrdBaseinfo prodInfo, ChbAssetInfo chbAssetInfo, ChbMonthReportDetail reportDetail){
        ChbMonthAssetPositiondetail chbMonthAssetPositiondetail = new ChbMonthAssetPositiondetail();
        //相同内容字段直接赋值
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,prodInfo);

        if(null!=chbAssetInfo){
            BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,chbAssetInfo);
        }
        //先基本信息,再头寸信息,避免错误覆盖
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,position);
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,reportDetail);

        if(null!=chbAssetInfo){
            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getOptionFlag())){
                chbMonthAssetPositiondetail.setOpenplan(chbAssetInfo.getExerciseDate());
            }
            chbMonthAssetPositiondetail.setSubjectName(null!=chbAssetInfo.getChbIssuerId()?chbAssetInfo.getChbIssuerId():
                    null!=chbAssetInfo.getChbFinancierId()?chbAssetInfo.getChbFinancierId():chbAssetInfo.getChbManagerId());

            if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecPrefer())){
                chbMonthAssetPositiondetail.setIsYhbcgj(DictCodeListExt3.中债月度明细_银行资本补充工具_优先股.getValue());
            }else if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecPerpetual())){
                chbMonthAssetPositiondetail.setIsYhbcgj(DictCodeListExt3.中债月度明细_银行资本补充工具_永续债.getValue());
            }else if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecCapital())){
                chbMonthAssetPositiondetail.setIsYhbcgj(DictCodeListExt3.中债月度明细_银行资本补充工具_二级资本债.getValue());
            }else if(DictCodeList.是否_中债_是.getValue().equals(chbAssetInfo.getBankSecKzzq())){
                chbMonthAssetPositiondetail.setIsYhbcgj(DictCodeListExt3.中债月度明细_银行资本补充工具_可转债.getValue());
            }

        }

//        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,reportDetail);
        chbMonthAssetPositiondetail.setDetailno(reportDetail.getPropertycode());
        chbMonthAssetPositiondetail.setTerm(reportDetail.getTermid());
        //生成主键
        chbMonthAssetPositiondetail.setPosuuid(IDGenerator.generate(36));

        return chbMonthAssetPositiondetail;
    }

    public void  insertBefore(List<ChbMonthAssetPositiondetail> prdPositionList,Date batchDate){
        /**展示产品购买资产首次买入交易的交易对手名称***/
        Map<String,Object> paraMap = new HashMap<>();
        Set<String> codeList = prdPositionList.stream().map(ChbMonthAssetPositiondetail::getCounterListCode).collect(Collectors.toSet());
        paraMap.put("keysList",codeList);
        //展示产品购买资产首次买入交易的交易对手名称
        List<SelectOptionDto> oDto = chbMonthReportDetailAssetMapper.getCounterList(paraMap);
        Map<String,String> CounterMap = oDto.stream().collect(JoyinCollectors.toMap(x -> x.getId(), x -> x.getText()));
        //展示资产对应主体（没有主体的找交易对手）在市场机构中的行业一级分类
        List<MstCustomerInfoAdd> customerList = chbMonthReportDetailAssetMapper.getCustomerList(null);
        Map<String,MstCustomerInfoAdd> customerMap = customerList.stream().collect(Collectors.toMap(MstCustomerInfoAdd::getCustomerId,x->x));
        for(ChbMonthAssetPositiondetail dto : prdPositionList){
            //先赋值行业
            MstCustomerInfoAdd info = null!=customerMap.get(dto.getSubjectName())?customerMap.get(dto.getSubjectName()):customerMap.get(dto.getRivalParty());
            //展示资产对应主体（没有主体的找交易对手）在市场机构中的行业一级分类
            if(null!=info){
                dto.setIndustry1(info.getIndustry());
                dto.setIndustry2(info.getIndustry2());
                dto.setIndustry3(info.getIndustry3());
                dto.setIndustry4(info.getIndustry4());

            }
            //非标转标字段塞值
            String isFbhy = null;
            switch (dto.getDetailno()){
                case "AMT3_3_1" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_农林牧渔业.getValue(); break;
                case "AMT3_3_2" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_制造业.getValue(); break;
                case "AMT3_3_3" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_采矿业.getValue(); break;
                case "AMT3_3_4" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_建筑业.getValue(); break;
                case "AMT3_3_5" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_基础设施建设.getValue(); break;
                case "AMT3_3_6" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_房地产业.getValue(); break;
                case "AMT3_3_7" : isFbhy =DictCodeListExt3.中债月度明细_非标行业_金融业.getValue(); break;
                default:  isFbhy =DictCodeListExt3.中债月度明细_非标行业_其他服务业.getValue(); break;
            }

            dto.setIsFbhy(isFbhy);


            //再赋值交易对手名称  展示产品购买资产首次买入交易的交易对手名称
            dto.setRivalParty(CounterMap.get(dto.getProdId()+"_"+dto.getAssetCode()));
        }
        //现金特殊处理产品代码
//        List<ChbMonthAssetPositiondetail> detail1001 = prdPositionList.stream().filter(s-> "1001".equals(s.getAssettype2())&&s.getAssetvalueA()!=null).collect(Collectors.toList());
        Map<String, ChbMonthAssetPositiondetail>  detail1001 = prdPositionList.stream().filter(detail ->
                ("1001".equals(detail.getAssettype2())&&"REMAININGCASH".equals(detail.getAssetCode())&&detail.getAssetvalueA()!=null)).collect(Collectors.toMap(ChbMonthAssetPositiondetail::getProdId,Positiondetail -> Positiondetail));

        if(null!=detail1001&&!detail1001.isEmpty()){
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("cdate", batchDate);
            param.put("prodids", detail1001.keySet());
            List<ChbMonthAssetPositiondetail> prdPosition1001List = new ArrayList<>();
            List<ChbMonthAssetPositiondetail> subjectAddList = new ArrayList<>();

            //获取产品科目信息
            this.buildPrdProstion(prdPosition1001List,param);
            //科目类数据合计
            if(!StringUtil.isEmptyList(prdPosition1001List)){
                for(ChbMonthAssetPositiondetail dto : prdPosition1001List){
                    //如果金额未0则不进行记录
                    if(dto.getAssetvalueA().compareTo(BigDecimal.ZERO)==0&&
                            dto.getAssetvalueB().compareTo(BigDecimal.ZERO)==0){
                        continue;
                    }
                    dto.setPosuuid(IDGenerator.generate(36));
                    dto.setDetailno(detail1001.get(dto.getProdId()).getDetailno());
                    dto.setTerm(detail1001.get(dto.getProdId()).getTerm());
                    dto.setAssettype(detail1001.get(dto.getProdId()).getAssettype());
                    dto.setAssettype2(detail1001.get(dto.getProdId()).getAssettype2());
                    dto.setManageType(detail1001.get(dto.getProdId()).getManageType());
                    dto.setVdate(detail1001.get(dto.getProdId()).getVdate());
                    dto.setMdate(detail1001.get(dto.getProdId()).getMdate());
                    subjectAddList.add(dto);
                }
                //将所有现金删除
                prdPositionList.removeAll(detail1001.values());
                //替换为对应的科目信息
                prdPositionList.addAll(subjectAddList);
            }
        }

    }


    /**
     * 估值表现金查询方式拆分
     * @param assetPositionListAdd
     * @param param
     */
    public void buildPrdProstion(List<ChbMonthAssetPositiondetail> assetPositionListAdd,Map<String, Object> param){
        // 查询当日的头寸数据
        String monthReportCalWay = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ASSETPOSITION_CALWAY);
        String isBtm = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_CHL_BTM);
        String iscal= SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ASSETPOSITION_CASHCALWAY);
        String subjectno = null;
        //如果是移动平均法,且取值不是科目余额表中.则跳出获取估值科目信息的方法
        if(!SVReportGlobalConstant.ASSETPOSITION_CALWAY_AVERAGE
                .equals(monthReportCalWay)&&DictCodeList.是否_中债_是.getValue().equals(iscal)){
            return;
        }else if(SVReportGlobalConstant.ASSETPOSITION_CALWAY_MARKETPRICE
                .equals(monthReportCalWay)){
            subjectno = InterfMappingConstants.MAPPINGCODE_CASH_SUBJECTNO_MKT;

        }else if(!SVReportGlobalConstant.ASSETPOSITION_CALWAY_AVERAGE.equals(monthReportCalWay)){
            subjectno = InterfMappingConstants.MAPPINGCODE_CASH_SUBJECTNO_AVG;
        }
        // 通过系统参数判断是查询一体化还是中债子系统
        String isSubSystem = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SUB_SYSTEM);
        // 查询科目余额
        List<String> subjectNoList = new ArrayList<String>();
        List<ChbTypeMappingEx>  subjectNoMappingList = chbTypeMappingExMapper
                .selectByMappingcode(subjectno);
        for(ChbTypeMappingEx mapping: subjectNoMappingList){
            // 获取源属性1：科目号
            subjectNoList.add(mapping.getSrcfield1());
        }
        param.put("subjectNoList", subjectNoList);

        if (DictCodeList.是否_中债_是.getValue().equals(isSubSystem)){
            assetPositionListAdd.addAll(chbMonthReportDetailAssetMapper.selectBalanceDataSubSystem(param));
        } else if (DictCodeList.是否_中债_否.getValue().equals(isSubSystem)){
            // 读取是否穿透开关
            param.put("isBtm", isBtm);
            assetPositionListAdd.addAll(chbMonthReportDetailAssetMapper.selectBalanceData(param));
        } else {
            throw new RuntimeException("系统参数中：SVR_IS_SUB_SYSTEM配置错误！");
        }

    }





    /**
     * 批量入库方法
     * @param prdPositionList
     * @return
     */
    public int insertBatch(List<ChbMonthAssetPositiondetail> prdPositionList,String term,Date batchDate){

        if(null!=prdPositionList&&prdPositionList.size()==0){
            return 0;
        }
        //部分数据的统一处理
        this.insertBefore(prdPositionList,batchDate);

        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertCount;
        //同批次的详情数据删除
        chbMonthReportDetailAssetMapper.deleteDetail(term);


        //插入list大小
        int dataCount = prdPositionList.size();
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            dataCount = chbMonthReportDetailAssetMapper.insertBatch(prdPositionList);
            return dataCount;
        }

        List<ChbMonthAssetPositiondetail> newDataList = new ArrayList<ChbMonthAssetPositiondetail>();
        newDataList.addAll(prdPositionList);

        // 分批存入
        int part = dataCount / maxDataCountOneTime; // 分批数
        int insertCount = 0;
        for (int i = 0; i < part; i++ ) {
            insertCount += chbMonthReportDetailAssetMapper.insertBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            insertCount += chbMonthReportDetailAssetMapper.insertBatch(newDataList);
        }
        return insertCount;
    }
}
