/*
 * 文件名：DelayVerification001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月25日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.svrcom.submitexamination.investorsqualitycheck.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.dto.InvestorsQualityCheck001Dto;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheck001Service;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckBaseService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckCPFEFBXService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckCPFEKFXService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckCPYEFBXService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckCPYEKFXService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckCPZTService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckDJWZXService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckMJQFEService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckMJQYEService;
import com.joyintech.tams.report.submitexamination.investorsqualitycheck.service.InvestorsQualityCheckSLPPService;


/**
 * 延时报送检查服务
 * 〈功能详细描述〉
 * @author zhagnzhongliang
 * @version 1.0 2019年04月19日
 * @see InvestorsQualityCheckController
 * @since 1.0
 */
@RestController
@RequestMapping("/InvestorsQualityCheck")
public class InvestorsQualityCheckController{
    /**
     * 延时报送检查service
     */
    @Autowired
    private InvestorsQualityCheck001Service investorsQualityCheck001Service;

    @Autowired
    private InvestorsQualityCheckCPZTService investorsQualityCheckCPZTService;

    @Autowired
    private InvestorsQualityCheckSLPPService investorsQualityCheckSLPPService;
    
    @Autowired
    private InvestorsQualityCheckCPYEFBXService investorsQualityCheckCPYEFBXService;
    
    @Autowired
    private InvestorsQualityCheckCPYEKFXService investorsQualityCheckCPYEKFXService;
    
    @Autowired
    private InvestorsQualityCheckCPFEFBXService investorsQualityCheckCPFEFBXService;
    
    @Autowired
    private InvestorsQualityCheckCPFEKFXService investorsQualityCheckCPFEKFXService;
    
    @Autowired
    private InvestorsQualityCheckMJQYEService investorsQualityCheckMJQYEService;
    
    @Autowired
    private InvestorsQualityCheckMJQFEService investorsQualityCheckMJQFEService;
    
    @Autowired
    private InvestorsQualityCheckDJWZXService investorsQualityCheckDJWZXService;

    /**
     * 查询列表
     *
     * @return Page
     */
    @PostMapping("/readConfig")
    public Map<String,String>  readConfig(){
        return investorsQualityCheck001Service.readConfig();
    }
    /**
     * 查询左侧-核查项合格率信息
     * @return
     */
    @PostMapping("/searchInvestorsPassRate")
    public List<InvestorsQualityCheck001Dto> searchInvestorsPassRate(@RequestBody Map<String, Object> param){
        investorsQualityCheck001Service.dealParamMap(param);
        List<InvestorsQualityCheck001Dto> resList = investorsQualityCheck001Service.searchInvestorsPassRate(param);

        // add by zhangliang 20210224 多线程处理查询结果 start
        List<InvestorsQualityCheckBaseService> serviceList = new ArrayList<>();
        // 添加需要查询的Service对象
        serviceList.add(investorsQualityCheckCPZTService);
        serviceList.add(investorsQualityCheckSLPPService);


        // 创建线程池
        ExecutorService taskpool = new JoyinThreadPoolExecutor(serviceList.size());
        List<Callable<InvestorsQualityCheck001Dto>> tasks = new ArrayList<>();
        // 创建任务
        for (InvestorsQualityCheckBaseService service : serviceList) {
            tasks.add(new Callable<InvestorsQualityCheck001Dto>() {
                @Override
                public InvestorsQualityCheck001Dto call() {
                    return service.cntHGL(param);
                }
            });
        }
        // 为线程池添加任务
        try {
            List<Future<InvestorsQualityCheck001Dto>> taskResult = taskpool.invokeAll(tasks);
            // 获取任务执行结果
            for (Future<InvestorsQualityCheck001Dto> res : taskResult) {
                // 需要计算
                resList.add(res.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();

            return resList;
        }finally {
            // 任务结束，停止线程池的所有线程
            taskpool.shutdown();
        }


        return resList;
        // add by zhangliang 20210224 多线程处理查询结果 end
    }
    /**
     * 查询列表
     * --证件类型合格率
     * @return Page
     */
    @PostMapping("/searchTableZJLX/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableZJLX(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchTableZJLX(param);
    }
    /**
     * 查询列表
     * --证件匹配合格率
     * @return Page
     */
    @PostMapping("/searchTableZJPP/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableZJPP(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchTableZJPP(param);
    }
    /**
     * 查询列表
     * --及时性合格率
     * @return Page
     */
    @PostMapping("/searchTableJSX/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableJSX(@RequestBody Map<String, Object> param){
        return investorsQualityCheck001Service.searchTableJSX(param);
    }

    /**
     * 查询列表
     * --及时性合格率
     * @return Page
     */
    @PostMapping("/searchTableCPZT/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableCPZT(@RequestBody Map<String, Object> param){
        return investorsQualityCheckCPZTService.queryPage(param);
    }

    /**
     * 查询列表
     * --投资者数量匹配合格率
     * @return Page
     */
    @PostMapping("/searchTableSLPP/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableSLPP(@RequestBody Map<String, Object> param){
        return investorsQualityCheckSLPPService.queryPage(param);
    }
    
    /**
     * 查询列表
     * --募集总份额合格率
     * @return List
     */
    @PostMapping("/searchTableMJQFE/{page}/{length}")
    public List<InvestorsQualityCheck001Dto> searchTableMJQFE(@RequestBody Map<String, Object> param){
        return investorsQualityCheckMJQFEService.queryErrorRecList(param);
    }
    
    /**
     * 查询列表
     * --实际募集金额（元）合格率
     * @return List
     */
    @PostMapping("/searchTableMJQYE/{page}/{length}")
    public List<InvestorsQualityCheck001Dto> searchTableMJQYE(@RequestBody Map<String, Object> param){
        return investorsQualityCheckMJQYEService.queryErrorRecList(param);
    }
    
    /**
     * 查询列表
     * --登记完整性合格率
     * @return List
     */
    @PostMapping("/searchTableDJWZX/{page}/{length}")
    public Page<InvestorsQualityCheck001Dto> searchTableDJWZX(@RequestBody Map<String, Object> param){
        return investorsQualityCheckDJWZXService.queryPage(param);
    }
    
    /**
     * 查询列表
     * --产品余额（元）合格率（封闭式产品）
     * @return List
     */
    @PostMapping("/searchTableCPYEFBX/{page}/{length}")
    public List<InvestorsQualityCheck001Dto> searchTableCPYEFBX(@RequestBody Map<String, Object> param){
        return investorsQualityCheckCPYEFBXService.queryErrorRecList(param);
    }
    
    /**
     * 查询列表
     * --产品余额（元）合格率（开放式式产品）
     * @return List
     */
    @PostMapping("/searchTableCPYEKFX/{page}/{length}")
    public List<InvestorsQualityCheck001Dto> searchTableCPYEKFX(@RequestBody Map<String, Object> param){
        return investorsQualityCheckCPYEKFXService.queryErrorRecList(param);
    }
    
    /**
     * 查询列表
     * --产品份额（元）合格率（封闭式产品）
     * @return List
     */
    @PostMapping("/searchTableCPFEFBX/{page}/{length}")
    public List<InvestorsQualityCheck001Dto> searchTableCPFEFBX(@RequestBody Map<String, Object> param){
        return investorsQualityCheckCPFEFBXService.queryErrorRecList(param);
    }
    
    /**
     * 查询列表
     * --产品份额（元）合格率（开放式产品）
     * @return List
     */
    @PostMapping("/searchTableCPFEKFX/{page}/{length}")
    public List<InvestorsQualityCheck001Dto> searchTableCPFEKFX(@RequestBody Map<String, Object> param){
        return investorsQualityCheckCPFEKFXService.queryErrorRecList(param);
    }
}
