package com.joyintech.tams.svreport.pbc.report.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetPositionService;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetReportService;
import com.joyintech.tams.svreport.pbc.prod.service.PbcMonthProdPositionService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 功能:银行月度报表登记service
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PbcMonthReportRegistBatchService  extends SvrCBBaseService{

    private static final Logger logger = LoggerFactory.getLogger(PbcMonthReportRegistBatchService.class);

    @Autowired
    private PbcMonthAssetPositionService pbcAssetPositionInterf;
    
    @Autowired
    private PbcMonthAssetReportService pbcMonthAssetPositionService;
    
    @Autowired
    private PbcMonthProdPositionService pbcMonthProdPositionService;

    /**
     * 功能:生成人行报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @param isEdit 是否是修改的数据
     * @throws Exception 
     * void
     */
    public void generateReport(Date batchDate,List<String> prodIdList,boolean isEdit,String orgCode) {
        setBatchBeanName(BatchBeanName.人行报表生成);
        logInfoBatchStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchDate);
        // 每月1日生成上月的数据
        // 如果走修改消息池进入则自动进入批处理
        if ( calendar.get(Calendar.DATE) != 1 
                && !isEdit) {  
            return;
        }
        // 先生成产品端
        generateProdReport(batchDate,prodIdList,isEdit,orgCode);
        // 在生成资产端
        generateAssetReport(batchDate,prodIdList,isEdit,orgCode);
        logInfoBatchEnd();
    }
    
    /**
     * 功能:生成资产端的报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @param isEdit 是否是修改的数据
     * @throws Exception 
     * void
     */
	public void generateAssetReport(Date batchDate,List<String> prodIdList,boolean isEdit,String  orgCode) {
//        //已报送报送检查
        List<String> reporttypeList = new ArrayList<String>();
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品资产负债统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资产收益权按基础资产投向分类统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品只数情况统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品资产负债剩余期限统计表.getValue());
        String msg = pbcMonthProdPositionService.checkSendStatus(batchDate,reporttypeList,orgCode);
        if(msg!=null&&!StringUtil.isEmpty(msg)){
            logger.error(msg);
        }else{
            // 上游资产头寸数据接入
            pbcAssetPositionInterf.generateAssetPositionData(batchDate, prodIdList, null, false,isEdit);
            // 人行报表生成处理
            pbcMonthAssetPositionService.generateMonthAssetReport(batchDate, prodIdList,isEdit);
        }
	}


	/**
	 * 功能:生成产品端的报表
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @param isEdit 是否是修改的数据
	 * @throws Exception 
	 * void
	 */
    public void generateProdReport(Date batchDate,List<String> prodIdList,boolean isEdit,String orgCode) {
        //已报送报送检查
        List<String> reporttypeList = new ArrayList<String>();
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品募集余额统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品募集及兑付发生额统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品只数情况统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品境内募集余额分地区统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品提前及延期兑付统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_资管产品到期未兑付统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_存续产品分合同期限募集余额统计表.getValue());
        reporttypeList.add(DictCodeListExt2.人行报表名称_新发产品分合同期限募集金额统计表.getValue());
        String msg = pbcMonthProdPositionService.checkSendStatus(batchDate,reporttypeList,orgCode);
        if(msg!=null&&!StringUtil.isEmpty(msg)){
            logger.error(msg);
        }else{
            pbcMonthProdPositionService.generateProdPositionData(batchDate,prodIdList,false,isEdit);
        }


//        //已报送报送检查
//        List<String> reporttypeAssetList = new ArrayList<String>();
//        reporttypeAssetList.add(DictCodeListExt2.人行报表名称_资管产品资产负债统计表.getValue());
//        reporttypeAssetList.add(DictCodeListExt2.人行报表名称_资管产品资产负债剩余期限统计表.getValue());
//        String msgAsset = pbcMonthProdPositionService.checkSendStatus(batchDate,reporttypeList);
//        if(msgAsset!=null&&!StringUtil.isEmpty(msgAsset)){
//            throw new Exception(msgAsset+"\n");
//        }
//        // 人行报表2_1\3_3生成处理
//        pbcMonthAssetPositionService.generateMonthProdReport(batchDate, prodIdList);
    }

    /**
     * 功能:数据变更接口统一调度入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param executeDate
     * @param prodList
     * @param assetList
     * @throws BusinessException 
     * @see com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService#execute(java.util.Date, java.util.List, java.util.List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        try {
            generateReport(executeDate,null,true, CommonUtil.getOrgCode());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
}
