package com.joyintech.tams.batch.svreport.pbc.singleprdreport.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.service.PbcPrdAccountSubService;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.service.PbcPrdAccountEndSubService;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubService;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service.PbcPrdCashRaiseSubService;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.serivce.PbcPrdInfoEndSubService;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService;


@Service
@Transactional(rollbackFor = Exception.class)
public class PbcSinglePrdReportCBMainService  extends AutoSendCBBaseService{

    
    @Autowired
    private PbcPrdAssetDebtSubService pbcPrdAssetDebtSubService;
    
    @Autowired
    private PbcPrdCashRaiseSubService pbcPrdCashRaiseSubService;

    @Autowired
    private PbcPrdInitRaiseSubService pbcPrdInitRaiseSubService;
    
    @Autowired
    private PbcPrdInfoEndSubService pbcPrdInfoEndSubService;
    
    @Autowired
    private PbcPrdAccountEndSubService pbcPrdAccountEndSubService;
    
    @Autowired
    private PbcPrdBaseinfoSubService pbcPrdBaseinfoSubService;
    
    @Autowired
    private PbcPrdAccountSubService pbcPrdAccountSubService;
    /**
     * 功能:生成人行单产品报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @param isEdit 是否是修改的数据
     * @throws Exception 
     */
    public void generateReport(final Date batchDate,final List<String> prodIdList) throws Exception{
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.人行报表单产品批处理);
        logInfoBatchStart();
        // 按照顺序调度人行单产品批处理的执行顺序
        // 人行单产品报表-资产池基本信息登记
        pbcPrdAccountSubService.genReport(batchDate, prodIdList);
    	 // 人行单产品报表-产品基本信息登记
    	pbcPrdBaseinfoSubService.genReport(batchDate, prodIdList);
    	// 人行单产品报表-产品起始募集信息
    	pbcPrdInitRaiseSubService.genReport(batchDate, prodIdList);
        // 人行单产品报表-产品资金募集信息
        pbcPrdCashRaiseSubService.genReport(batchDate, prodIdList);
        // 人行单产品报表-资产负债信息
        pbcPrdAssetDebtSubService.genReport(batchDate, prodIdList);
        // 人行单产品报表-产品终止信息
    	pbcPrdInfoEndSubService.genReport(batchDate, prodIdList);
    	// 人行单产品报表-资产池终止信息
    	pbcPrdAccountEndSubService.genReport(batchDate, prodIdList);
        logInfoBatchEnd();
    }
}
