package com.joyintech.tams.svreport.pbc.reportdetail.service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.dao.ChbPrdBaseinfoMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.reportdetail.dao.PbcMonthReportDetailAssetMapper;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailAsset;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
public class PbcMonthReportDetailAssetUtil {

    @Autowired
    private PbcMonthReportDetailAssetMapper pbcMonthReportDetailAssetMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    public int reportInsertCount;

    private static List<String> existsCode = Arrays.asList(PbcReportProperycode.AMT_710000_XJ,PbcReportProperycode.AMT_790000_YSZK,PbcReportProperycode.AMT_7A0000_HJ
            ,PbcReportProperycode.AMT_7B0000_FJRZC);

    private static Map<String,ChbPrdBaseinfo> prodInfoMap;

    private static Map<String,ChbAssetInfo> assetInfoMap;


    /**
     *
     * @param position
     * @return
     */
    public static PbcMonthReportDetailAsset buildAssetDetail(PbcMonthAssetPositionDto position,Object dto,String code){

        if(null==assetInfoMap){
            PbcMonthReportDetailAssetMapper detailAssetMapper =  SpringContextUtil.getBean(PbcMonthReportDetailAssetMapper.class);
            List<ChbAssetInfo> assetInfoList = detailAssetMapper.selectChbAssetInfoByAssetCodeList(null);
            assetInfoMap = null!=assetInfoList?assetInfoList.stream().collect(Collectors.toMap(p->p.getAssetCode(), p-> p,(key1, key2)->key1)):new HashMap<>();
        }
        if(null==prodInfoMap){
            /***获取产品信息列表**/
            ChbPrdBaseinfoMapper chbPrdBaseinfoMapper =  SpringContextUtil.getBean(ChbPrdBaseinfoMapper.class);
            List<ChbPrdBaseinfo> prdBaseinfoList = chbPrdBaseinfoMapper.selectAllProd(null);
            prodInfoMap = null!=prdBaseinfoList?prdBaseinfoList.stream().collect(Collectors.toMap(p->p.getProdId(), p-> p,(key1,key2)->key1)):new HashMap<>();
        }
        PbcMonthReportDetailAsset pbcMonthReportDetailAsset = new PbcMonthReportDetailAsset();
        //相同内容字段直接赋值
        if(null!=prodInfoMap.get(position.getProdid())){
            BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset,prodInfoMap.get(position.getProdid()));
        }
        if(null!=assetInfoMap.get(position.getAssetCode())) {
            BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset, assetInfoMap.get(position.getAssetCode()));
        }
        //先基本信息,再头寸信息,避免错误覆盖
        BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset,position);

        BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset,dto);
        //资产端绑定编号
        pbcMonthReportDetailAsset.setDetailno(code);

        if(null!=assetInfoMap.get(position.getAssetCode())){
            pbcMonthReportDetailAsset.setIndustry(null!=assetInfoMap.get(position.getAssetCode()).getIndustry()?
                    assetInfoMap.get(position.getAssetCode()).getIndustry(): assetInfoMap.get(position.getAssetCode()).getIndustryPbc());
        }
        //期次赋值
        pbcMonthReportDetailAsset.setTerm(DateUtil.format(position.getEnddate(),"yyyyMM"));

        return pbcMonthReportDetailAsset;
    }

    /**
     * 2-4数据特殊处理
     * @param position
     * @return
     */
    public static PbcMonthReportDetailAsset buildAssetDetail2(PbcMonthAssetPositionDto position,Object dto,String code,String code2){

        if(null==assetInfoMap){
            PbcMonthReportDetailAssetMapper detailAssetMapper =  SpringContextUtil.getBean(PbcMonthReportDetailAssetMapper.class);
            List<ChbAssetInfo> assetInfoList = detailAssetMapper.selectChbAssetInfoByAssetCodeList(null);
            assetInfoMap = null!=assetInfoList?assetInfoList.stream().collect(Collectors.toMap(p->p.getAssetCode(), p-> p,(key1, key2)->key1)):new HashMap<>();
        }
        if(null==prodInfoMap){
            /***获取产品信息列表**/
            ChbPrdBaseinfoMapper chbPrdBaseinfoMapper =  SpringContextUtil.getBean(ChbPrdBaseinfoMapper.class);
            List<ChbPrdBaseinfo> prdBaseinfoList = chbPrdBaseinfoMapper.selectAllProd(null);
            prodInfoMap = null!=prdBaseinfoList?prdBaseinfoList.stream().collect(Collectors.toMap(p->p.getProdId(), p-> p,(key1,key2)->key1)):new HashMap<>();
        }
        PbcMonthReportDetailAsset pbcMonthReportDetailAsset = new PbcMonthReportDetailAsset();
        //相同内容字段直接赋值
        if(null!=prodInfoMap.get(position.getProdid())){
            BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset,prodInfoMap.get(position.getProdid()));
        }
        if(null!=assetInfoMap.get(position.getAssetCode())) {
            BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset, assetInfoMap.get(position.getAssetCode()));
        }
        //先基本信息,再头寸信息,避免错误覆盖
        BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset,position);

        BeanUtil.copyPropertiesIgnoreNull(pbcMonthReportDetailAsset,dto);
        //资产端绑定编号
        pbcMonthReportDetailAsset.setDetailno(code);

        pbcMonthReportDetailAsset.setDetailno2(code2);

        if(null!=assetInfoMap.get(position.getAssetCode())){
            pbcMonthReportDetailAsset.setIndustry(null!=assetInfoMap.get(position.getAssetCode()).getIndustry()?
                    assetInfoMap.get(position.getAssetCode()).getIndustry(): assetInfoMap.get(position.getAssetCode()).getIndustryPbc());
        }
        //期次赋值
        pbcMonthReportDetailAsset.setTerm(DateUtil.format(position.getEnddate(),"yyyyMM"));

        return pbcMonthReportDetailAsset;
    }


    /**
     * 赋值贷款类资产数据
     * @param position
     * @param dto
     * @param code
     */
    public static void updateLoan(List<PbcMonthReportDetailAsset> position, Map<String, ChbLoanDetailInfo> loanMap){
        for(PbcMonthReportDetailAsset detailAsset: position){
            if(null!=loanMap.get(detailAsset.getAssetCode())){
                BeanUtil.copyPropertiesIgnoreNull(detailAsset,loanMap.get(detailAsset.getAssetCode()));
            }
        }

    }

    /**
     * 判断2-1数据是否要进入,所有
     * @param code
     * @return
     */
    public static Boolean isAssetDate(String code){
        boolean flag = false;
        if(null!=code){
            //特殊资产类型判断
            if(existsCode.contains(code)){
                flag = true;
            //如果6-7位编号不是0.则认为是一二级类型.屏蔽
            }else if(!code.substring(6,7).equals("0")){
                flag = true;
            }
        }

        return flag;
    }

    /**
     * 批量入库方法
     * @param prdPositionList
     * @return
     */
    public int insertBatch(List<PbcMonthReportDetailAsset> prdPositionList, String term){
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertCount;
        //同批次的详情数据删除
        pbcMonthReportDetailAssetMapper.deleteDetail(term);


        //插入list大小
        int dataCount = prdPositionList.size();
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            dataCount = pbcMonthReportDetailAssetMapper.insertBatch(prdPositionList);
            return dataCount;
        }

        List<PbcMonthReportDetailAsset> newDataList = new ArrayList<PbcMonthReportDetailAsset>();
        newDataList.addAll(prdPositionList);

        // 分批存入
        int part = dataCount / maxDataCountOneTime; // 分批数
        int insertCount = 0;
        for (int i = 0; i < part; i++ ) {
            insertCount += pbcMonthReportDetailAssetMapper.insertBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            insertCount += pbcMonthReportDetailAssetMapper.insertBatch(newDataList);
        }
        return insertCount;
    }
}
