package com.joyintech.tams.report.submitexamination.investorsqualitycheck.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSubSum;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dao.InvestorsQualityCheck001Mapper;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 投资者质量核查Service
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestorsQualityCheck001Service extends BaseService<InvestorsQualityCheck001Dto>{

    @Autowired
    private InvestorsQualityCheck001Mapper investorsQualityCheck001Mapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 读取系统中的对于颜色的配置参数
     * @return 保存了配置参数的map
     */
    public Map<String, String> readConfig() {
        Map<String, String> resultMap =new HashMap<String, String>();

        resultMap.put("mid", CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_COLOR_CONFIG_MID));
        resultMap.put("top", CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_COLOR_CONFIG_TOP));
        return resultMap;
    }

    /**
     *  查询左侧-核查项合格率信息
     * @param pageParam
     * @return
     */
    public List<InvestorsQualityCheck001Dto> searchInvestorsPassRate(Map<String, Object> pageParam) {
        // 参数处理
        dealParamMap(pageParam);
        return investorsQualityCheck001Mapper.searchInvestorsPassRate(pageParam);
    }

    /**
     * 共通方法：查询界面选择的参数处理
     * @param param
     */
    public void dealParamMap(Map<String, Object> param) {
        String orgCode = (String)param.get("orgCode");
        List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
        param.put("orgCodeList", orgCodeList);
    }

    /**
     * 共通方法：查询返回结果处理
     * @param pageList
     */
    private void dealReturnInfo(List<InvestorsQualityCheck001Dto> pageList) {
        for (InvestorsQualityCheck001Dto dto : pageList) {
            dto.setHolderMark(SysDataUtil.getNameFromDataDict(
                    DictCode.境内外, dto.getHolderMark()));
            dto.setHolderType(SysDataUtil.getNameFromDataDict(
                    DictCode.权益人类别, dto.getHolderType()));
//        	dto.setPersonalIdentifyType(SysDataUtil.getNameFromDataDict(
//                DictCode.个人证件类别, dto.getPersonalIdentifyType()));
            if (StringUtil.isNotEmpty(dto.getPersonalIdentifyType())) {
                dto.setIdentifyTypeStr(SysDataUtil.getNameFromDataDict(
                        DictCode.个人证件类别, dto.getPersonalIdentifyType()));
            }else {
                dto.setIdentifyTypeStr(SysDataUtil.getNameFromDataDict(
                        DictCode.机构证件类别, dto.getInstitutionIdentifyType()));
            }
            dto.setSendStatus(SysDataUtil.getNameFromDataDict(
                    DictCode.报送状态, dto.getSendStatus()));
            dto.setSubmitTimeStr(DateUtil.format(dto.getSubmitTime(), "yyyy-MM-dd"));
            dto.setHoldDateStr(DateUtil.format(dto.getHoldDate(), "yyyy-MM-dd"));
        }
    }

    /**
     * 根据页面的参数查询Table的数据
     * --证件类型合格率
     * @param pageParam 存储了画面参数的MAP
     * @return 存储了查询结果
     */
    public Page<InvestorsQualityCheck001Dto> searchTableZJLX(Map<String, Object> pageParam) {
        Page<InvestorsQualityCheck001Dto> resultPage=new Page<InvestorsQualityCheck001Dto>();
        // 参数处理
        dealParamMap(pageParam);
        int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        resultPage =super.queryForPage(pagenum,legth,()->investorsQualityCheck001Mapper.searchTableZJLX(pageParam));
        // 返回结果处理
        dealReturnInfo(resultPage.getRoot());
        return resultPage;
    }

    /**
     * 根据页面的参数查询Table的数据
     * --证件匹配合格率
     * @param pageParam 存储了画面参数的MAP
     * @return 存储了查询结果
     */
    public Page<InvestorsQualityCheck001Dto> searchTableZJPP(Map<String, Object> pageParam) {
        Page<InvestorsQualityCheck001Dto> resultPage=new Page<InvestorsQualityCheck001Dto>();
        // 参数处理
        dealParamMap(pageParam);
        int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        resultPage =super.queryForPage(pagenum,legth,()->investorsQualityCheck001Mapper.searchTableZJPP(pageParam));
        // 返回结果处理
        dealReturnInfo(resultPage.getRoot());
        return resultPage;
    }

    /**
     * 根据页面的参数查询Table的数据
     * --及时性合格率
     * @param pageParam 存储了画面参数的MAP
     * @return 存储了查询结果
     */
    public Page<InvestorsQualityCheck001Dto> searchTableJSX(Map<String, Object> pageParam) {
        Page<InvestorsQualityCheck001Dto> resultPage=new Page<InvestorsQualityCheck001Dto>();
        // 参数处理
        dealParamMap(pageParam);
        int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
        resultPage =super.queryForPage(pagenum,legth,()->investorsQualityCheck001Mapper.searchTableJSX(pageParam));
        // 返回结果处理
        dealReturnInfo(resultPage.getRoot());
        return resultPage;
    }


    /**
     * 获取上次汇总时间
     * @return
     */
    public String getLastSummaryTime() {
        return investorsQualityCheck001Mapper.getLastSummaryTime();
    }

    /**
     * 执行持有汇总
     * @param pordIdList 产品列表
     * @param startDateStr 开始日期，格式:YYYY-MM-DD
     * @param endDateStr 结束日，格式:YYYY-MM-DD
     * @param poolSize 线程池数据
     * @return
     */
    public String investorsSummary(List<String> pordIdList, String startDateStr, String endDateStr, int poolSize) {
        Date startDate = DateUtil.parse(startDateStr);
        Date endDate = DateUtil.parse(endDateStr);
        Date endDate1 = DateUtil.addDay(endDate, 1);      //由于for循环的原因，需要+1
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        boolean errFlag = false;

        // 1.使用线程池处理
        ExecutorService taskpool = new JoyinThreadPoolExecutor(poolSize);
        List<Callable<List<ChbInvestholdInfoSubSum>>> tasks = new ArrayList<>();
        for(; cal.getTime().before(endDate1); cal.add(Calendar.DAY_OF_MONTH, 1)){
            final Date cdate = cal.getTime();
            tasks.add(()->{
                return investorsQualityCheck001Mapper.queryChbInvestholdInfoSubSumByHoldDate(cdate);
            });
        }
        List<ChbInvestholdInfoSubSum> dataList = new ArrayList<>();

        // 2.合并线程池结果
        try {
            List<Future<List<ChbInvestholdInfoSubSum>>> resultList = taskpool.invokeAll(tasks);
            // 获取任务执行结果
            for (Future<List<ChbInvestholdInfoSubSum>> res : resultList) {
                // 需要计算
                List<ChbInvestholdInfoSubSum> dailyList = res.get();
                if(!StringUtil.isEmptyList(dailyList)){
                    dataList.addAll(dailyList);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("InvestorsQualityCheckController.searchInvestorsPassRate统计失败：", e);
            e.printStackTrace();
            Thread.currentThread().interrupt();
            errFlag = true;
        }finally {
            // 任务结束，停止线程池的所有线程
            taskpool.shutdown();
        }

        // 3.结果处理
        if(!errFlag){
            //删除
            investorsQualityCheck001Mapper.deleteChbInvestholdInfoSubSum(startDate, endDate);

            //批量插入数据
            int batchSize = 250;
            if(dataList.size() > batchSize){
                for(int i=0; i<dataList.size(); i+=batchSize){
                    int endIndex = Math.min(dataList.size(), i+batchSize);
                    investorsQualityCheck001Mapper.insertChbInvestholdInfoSubSumBatch(dataList.subList(i, endIndex));
                }
            }else if(dataList.size() > 0){
                investorsQualityCheck001Mapper.insertChbInvestholdInfoSubSumBatch(dataList);
            }
            return "执行完成！";
        }else{
            return "执行失败，请联系管理员！";
        }

    }

}
