package com.joyintech.tams.svreport.chb.transmonthreport.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.SvrCBBaseService;
import com.joyintech.tams.common.base.dao.ChbTransMonthReportSubMapper;
import com.joyintech.tams.common.base.entity.ChbTransMonthReportSub;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportPrdConstants;
import com.joyintech.tams.svreport.chb.monthreport.service.MonthReportAssetService;
import com.joyintech.tams.svreport.chb.monthreport.service.MonthReportProdService;
import com.joyintech.tams.svreport.chb.transmonthreport.dao.ChbTransMonthReportSubRegistBatchMapper;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能:中债理财产品转型情况月度统计表批处理
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransMonthReportRegistBatchService extends SvrCBBaseService{
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MonthReportProdService monthReportProdService;
    @Autowired
    private MonthReportAssetService monthReportAssetService;
    @Autowired
    private ChbTransMonthReportSubRegistBatchMapper chbTransMonthReportSubRegistBatchMapper;
    @Autowired
    private ChbTransMonthReportSubMapper chbTransMonthReportSubMapper;
    @Autowired
    private ChbTransMonthReportProdBatchService chbTransMonthReportProdBatchService;
    @Autowired
    private ChbTransMonthReportAssetBatchService chbTransMonthReportAssetBatchService;
    
    @Transactional(rollbackFor = Exception.class)
	public void genReport(Date executeDate,List<String> prodids,boolean ifTrigger){
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.理财产品转型情况月度统计表生成);
        // 批处理开始
        logInfoBatchStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(executeDate);

        if ( calendar.get(Calendar.DATE)!=1 &&!ifTrigger) {  //每月1日生成上月的数据
            return;
        }

        /** 中债理财理财产品转型情况月度统计表-报表生成(从宁银批处理中拆出来放到该逻辑中) modify by yanya on 20210511 start**/
        genReportService(executeDate, prodids, ifTrigger);
        /** 中债理财理财产品转型情况月度统计表-报表生成(从宁银批处理中拆出来放到该逻辑中) modify by yanya on 20210511 end**/

	}


    /**
     * 生成报表方法
     *
     * @param executeDate 跑批日
     * @param prodids 产品列表
     * @param ifTrigger 是否触发
     */
    @Transactional(rollbackFor = Exception.class)
    public void genReportService(Date executeDate, List<String> prodids, boolean ifTrigger) {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.理财产品转型情况月度统计表生成);
        // 批处理开始
        logInfoBatchStart();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(executeDate);

        if (calendar.get(Calendar.DATE) != 1 && !ifTrigger) { // 每月1日生成上月的数据
            return;
        }

        if (prodids == null)
            prodids = new ArrayList<String>();
        // 获取上月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstday = calendar.getTime();
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date lastday = calendar.getTime();
        calendar.setTime(lastday);

        int year = calendar.get(Calendar.YEAR);// 获取年份
        int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
        logger.info("生成理财产品转型情况月度统计表：" + year + "年" + month + "月");

        ChbTransMonthReportSubDto report = new ChbTransMonthReportSubDto();
        report.setStartdate(firstday);
        report.setEnddate(lastday);
        report.setStatusDate(ToolsUtil.getCurrentTime(executeDate));
        report.setProdids(prodids);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        ChbTransMonthReportSub chbTransMonthReportSub = new ChbTransMonthReportSub();
        chbTransMonthReportSub.setTermid(sdf.format(firstday));
        // 报表ID
        chbTransMonthReportSub.setReportuuid(UUID.randomUUID().toString());

        // 报表类型
        chbTransMonthReportSub.setReporttype(ChbMonthReportPrdConstants.REPORT_TYPE_MONTH);

        // 起止日期
        chbTransMonthReportSub.setStartdate(firstday);
        chbTransMonthReportSub.setEnddate(lastday);

        // 数据日期：批处理日期
        chbTransMonthReportSub.setStatusDate(report.getStatusDate());

        // 报表频率：月报
        chbTransMonthReportSub.setReportfrequ(ChbMonthReportPrdConstants.REPORT_FREQU_M);

        // 发行机构代码取值
        chbTransMonthReportSub.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
        chbTransMonthReportSub.setIssuePartyName(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_NAME));

        // 用户信息
        chbTransMonthReportSub.setOrgCode(CommonUtil.getOrgCode());
        chbTransMonthReportSub.setCreateDept(CommonUtil.getDeptCode());
        chbTransMonthReportSub.setCreateUser(CommonUtil.getUserName());
        chbTransMonthReportSub.setUpdateUser(CommonUtil.getUserName());

        // 数据状态
        chbTransMonthReportSub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        chbTransMonthReportSub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        // 报送时点
        // 当前批次报送时点集合
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        List<Date> dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_理财产品转型情况月度统计表.getValue(),
                chbTransMonthReportSub.getStatus(), dateMap, chbTransMonthReportSub.getEnddate());
        if (!StringUtil.isEmptyList(dateList)) {
            chbTransMonthReportSub.setChbSubmitDeadline(dateList.get(0));
            chbTransMonthReportSub.setSubmitDeadline(dateList.get(1));
        }
        // 插入月报主表数据
        List<ChbTransMonthReportSub> ybs = chbTransMonthReportSubRegistBatchMapper.selectSendDataByTermId(chbTransMonthReportSub.getTermid());
        calendar.setTime(executeDate);
        ChbTransMonthReportSub chbDelReport = new ChbTransMonthReportSub();
        chbDelReport.setTermid(chbTransMonthReportSub.getTermid());
        // 查询未报送
        List<ChbTransMonthReportSub> wbs = chbTransMonthReportSubRegistBatchMapper.selectUnSendDataByTermId(chbTransMonthReportSub.getTermid());
        if (calendar.get(Calendar.DATE) == 1 && ybs != null && !ybs.isEmpty() && (wbs == null || wbs.isEmpty())
                && !ifTrigger) {
            return;
        }
        if (wbs != null && !wbs.isEmpty()) {
            report.setWbsreportuuid(wbs.get(0).getReportuuid());
        }
        if (ybs != null && !ybs.isEmpty()) {// 有已报送数据 不作处理
            // 增加留痕处理 已报送后可生成修改未报送
            if (wbs != null && !wbs.isEmpty()) {
                chbDelReport.setReportuuid(wbs.get(0).getReportuuid());
                chbTransMonthReportSubRegistBatchMapper.deleteByModel(chbDelReport);
            }
            chbTransMonthReportSub.setStatus("02");// 修改
        } else {
            chbTransMonthReportSubRegistBatchMapper.deleteByModel(chbDelReport);
        }
        chbTransMonthReportSubMapper.insert(chbTransMonthReportSub);
        report.setReportuuid(chbTransMonthReportSub.getReportuuid());

        // 生成产品端月报明细
        chbTransMonthReportProdBatchService.genMonthPrd(report);

        // 生成资产端月报明细
        chbTransMonthReportAssetBatchService.genMonthAsset(report);
        // 批处理结束
        logInfoBatchEnd();
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
     * @see SvrCBBaseService#execute(Date, List, List)
     */
    @Override
    public void execute(Date executeDate, List<String> prodList, List<String> assetList,Map<String,List<String>> param) throws BusinessException {
        try {
            genReport(executeDate,null,true);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
            throw new BusinessException(e);
        }
    }

}
