package com.joyintech.tams.invest.common.recon.controller;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.tams.common.base.dao.StlCashReconMapper;
import com.joyintech.tams.common.base.dao.StlSecpReconMapper;
import com.joyintech.tams.invest.common.recon.dto.StlCashReconDto;
import com.joyintech.tams.invest.common.recon.dto.StlSecpReconDto;
import com.joyintech.tams.invest.common.recon.service.ReconInterfService;
import com.joyintech.tams.invest.common.settle.service.SettleDownLinkService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 对账文件接口表数据处理到业务表(恒生、金证)
 * @author zznn
 * @date 2021/10/25
 */
@RestController("reconInterf_dtx")
@RequestMapping("/reconInterf")
public class ReconInterfController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private ReconInterfService reconInterfService;

    @Autowired
    private StlCashReconMapper stlCashReconMapper;

    @Autowired
    private StlSecpReconMapper stlSecpReconMapper;

    @Autowired
    private SettleDownLinkService settleDownLinkService;

    /**
     * 资金余额
     * 必输项：execDate(String)
     * @param param
     */
    @Transactional
    @DTXTransaction
    @PostMapping("executecash")
    public void executecash(@RequestBody Map<String,Object> param) {
        try{
            logger.debug("接口平台调用资金余额业务处理开始");
            if(!param.containsKey("execDate")){
                param.put("execDate", DateUtil.format(iPlatformDate.getSysDate(),"yyyyMMdd"));
            }
            reconInterfService.deleteStlCashRecon(param);
            List<StlCashReconDto> stlCashReconDtos = reconInterfService.listStlCashRecon(param);
            stlCashReconDtos.stream().forEach(item->{
                stlCashReconMapper.insertSelective(item);
            });
            logger.debug("接口平台调用资金余额业务处理成功");
        }catch (Exception e){
            logger.error("接口平台调用资金余额业务处理失败!"+e.getMessage());
        }
    }

    /**
     * 持仓余额
     * 必输项：execDate(String)
     * @param param
     */
    @Transactional
    @DTXTransaction
    @PostMapping("executeposition")
    public void executeposition(@RequestBody Map<String,Object> param) {
        try{
            logger.debug("接口平台调用持仓余额业务处理开始");
            if(!param.containsKey("execDate")){
                param.put("execDate",iPlatformDate.getSysDateStr());
            }
            reconInterfService.deleteStlSecpRecon(param);
            List<StlSecpReconDto> stlSecpReconDtos = reconInterfService.listStlSecpRecon(param);
            stlSecpReconDtos.stream().forEach(item ->{
                stlSecpReconMapper.insertSelective(item);
            });
            logger.debug("接口平台调用持仓余额业务处理成功");
        }catch (Exception e){
            logger.error("接口平台调用持仓余额业务处理失败!"+e.getMessage());
        }
    }


    /**
     * 对账单
     * 必输项：execDate(String)
     * @param param
     */
    @Transactional
    @DTXTransaction
    @PostMapping("executerecon")
    public void executerecon(@RequestBody Map<String,Object> param) {
        try{
            logger.debug("接口平台调用对账单业务处理开始");
            if(!param.containsKey("execDate")){
                param.put("execDate",iPlatformDate.getSysDateStr());
            }
            reconInterfService.executerecon(param);
            logger.debug("接口平台调用对账单业务处理成功");
        }catch (Exception e){
            logger.error("接口平台调用对账单业务处理失败!"+e.getMessage());
        }
    }


    @Transactional
    @DTXTransaction
    @PostMapping("execute")
    public void execute(@RequestBody Map<String,Object> param) {
        try{
            logger.debug("接口平台调用批处理开始");
            param.put("beanNames", Arrays.asList("autoReconciliationTask","stlSettleTransDataGatherTask","stlDownDataGatherTask"));
            if(!param.containsKey("execDate")){
                param.put("execDate", DateUtil.format(iPlatformDate.getSysDate(),"yyyyMMdd"));
            }
            settleDownLinkService.execute(param);
            logger.debug("接口平台调用批处理成功");
        }catch (Exception e){
            logger.error("接口平台调用批处理失败!"+e.getMessage());
        }
    }

}

