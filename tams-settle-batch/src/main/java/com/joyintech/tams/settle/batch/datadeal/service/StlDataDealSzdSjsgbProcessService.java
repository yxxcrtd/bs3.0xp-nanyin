package com.joyintech.tams.settle.batch.datadeal.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.MstFundBonusNotice;
import com.joyintech.tams.common.base.entity.SzdSjsgb;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealOperatorDto;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealWriteDto;
import com.joyintech.tams.settle.common.dto.FundInfoDto;
import com.joyintech.tams.settle.common.service.SettleCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author mashukai
 * @date 2021/12/13
 * @see StlDataDealSzdSjsgbProcessService
 * @since 1.0
 */
@Service
public class StlDataDealSzdSjsgbProcessService {

    @Autowired
    private SettleCommonService settleCommonService;

    @Autowired
    private StlDataDealProcessCommonService stlDataDealProcessCommonService;
    
    /**
     * 生成上中登SJSGB数据清算批处理step2 processor的结果Dto
     *
     * @param stlDownDataDealOperatorDto StlDownDataDealOperatorDto
     */
    public StlDownDataDealWriteDto generatorStlDownDataDealSzdSjsgbWriteDto(StlDownDataDealOperatorDto stlDownDataDealOperatorDto) {
        // 全量基金信息获取
        Map<String, FundInfoDto> fundInfoDtoMap = settleCommonService.getAllFundInfoDto();
        StlDownDataDealWriteDto stlDownDataDealWriteDto = new StlDownDataDealWriteDto();
        if (stlDownDataDealOperatorDto != null && stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto() != null &&
                stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdTzxxList() != null &&
                stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdTzxxList().size() > 0) {
            
            List<MstFundBonusNotice> mstFundBonusNoticeArrayList = new ArrayList<MstFundBonusNotice>();
            for (SzdSjsgb szdSjsgb : stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSzdSjsgbList()) {
                if (StringUtil.equals(szdSjsgb.getGblb(), DictCodeListStl.广播类别_权益分派方案)){
                    String finprodId = DictCodeListExt1.金融产品类型_投管用_基金.getValue() + GlobalConstant.UNDER_LINE + szdSjsgb.getGbdm1() + ".SZ";
                    FundInfoDto fundInfoDto = fundInfoDtoMap.get(finprodId);
                    if(null == fundInfoDto) {
                        continue;
                    }
                    MstFundBonusNotice mstFundBonusNotice = new MstFundBonusNotice();
                    //基金代码
                    mstFundBonusNotice.setFundId(szdSjsgb.getGbdm1()+".SH");
                    //权益登记日
                    mstFundBonusNotice.setRegDate(szdSjsgb.getGbrq1());
                    //除权日
                    mstFundBonusNotice.setDelDate(nextWorkDay(szdSjsgb.getGbrq1()));
                    //派息日
                    mstFundBonusNotice.setDevidentDate(szdSjsgb.getGbrq2());
                    //单位分红净值 
                    mstFundBonusNotice.setUnitBonusUnitValue(szdSjsgb.getGbbl());
                    mstFundBonusNoticeArrayList.add(mstFundBonusNotice);   
                }
            }
            stlDownDataDealWriteDto.setMstFundBonusNoticeList(mstFundBonusNoticeArrayList);
        }

        return stlDownDataDealWriteDto;
    }

    /**
     * 取下一个工作日
     * @param parseDate
     * @return
     */
    private Date nextWorkDay(Date parseDate) {
        String calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SZJYS_CALID);
        return stlDataDealProcessCommonService.getNextWorkDay(parseDate, calendarId);
    }
}
