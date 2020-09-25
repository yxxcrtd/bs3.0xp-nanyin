package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam005Dto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 指数参数管理
 * @author : liuwei
 * @version V1.0
 * @Project: microservice
 */
@RestController("backRskSupervisionIndicatorParamController_dtx")
@RequestMapping("/RskSupervisionIndicatorParam")
public class RskSupervisionIndicatorParamController {

    /**
     * 指数参数管理Service
     */
    @Autowired
    private RskSupervisionIndicatorParamService rskSupervisionIndicatorParamService;

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskSupervisionIndicatorParamDto> selectAll(@RequestBody Map<String, Object> param,
                                                           @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskSupervisionIndicatorParamService.selectForPage(page, length, param);
    }

    /**
     * 删除方法
     *
     * @param dto 画面参数
     * @return 删除结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskSupervisionIndicatorParamDto dto){
        return rskSupervisionIndicatorParamService.delete(dto);
    }

    /**
     * 插入方法
     *
     * @param dto 画面参数
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody RskSupervisionIndicatorParamDto dto){
        return rskSupervisionIndicatorParamService.save(dto);
    }

    /**
     * 修改方法
     *
     * @param dto 画面参数
     * @return 保存结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody RskSupervisionIndicatorParamDto dto){
        return rskSupervisionIndicatorParamService.update(dto);
    }

    /**
     * 修改或查看界面
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    @GetMapping("selectInfo")
    public List<RskSupervisionIndicatorParamDto> selectInfo(String paraCode){
        return rskSupervisionIndicatorParamService.selectInfo(paraCode);
    }
    
    @GetMapping("selectFixRecode")
    public List<RskSupervisionIndicatorParam003Dto> selectFixRecode(String paraCode){
        return rskSupervisionIndicatorParamService.selectFixRecode(paraCode);
    }
    
    @GetMapping("selectFixRecode2")
    public List<RskSupervisionIndicatorParam003Dto> selectFixRecode2(String paraCode){
        return rskSupervisionIndicatorParamService.selectFixRecode2(paraCode);
    }
    
    /**
     * 查询参数代码
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    @GetMapping("selectParaCode")
    public int selectParaCode(String paraCode){
        return rskSupervisionIndicatorParamService.selectParaCode(paraCode);
    }

    /**
     * 是否在监督指标里配置
     *
     * @param paraCode 参数代码
     * @return 结果
     */
    @GetMapping("isConfigure")
    public int isConfigure(String paraCode){
        return rskSupervisionIndicatorParamService.isConfigure("{"+paraCode+"}");
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/insertFixRecode")
    public int  insertFixRecode(@RequestBody RskSupervisionIndicatorParam003Dto dto){
    	return rskSupervisionIndicatorParamService.insertFixRecode(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/updateFixRecode")
    public int  updateFixRecode(@RequestBody RskSupervisionIndicatorParam003Dto dto){
    	return rskSupervisionIndicatorParamService.updateFixRecode(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/insertCustomParam")
    public int  insertCustomParam(@RequestBody RskSupervisionIndicatorParam005Dto dto){
    	return rskSupervisionIndicatorParamService.insertCustomParam(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("/updateCustomParam")
    public int  updateCustomParam(@RequestBody RskSupervisionIndicatorParam005Dto dto){
    	return rskSupervisionIndicatorParamService.updateCustomParam(dto);
    }
    @PostMapping("/isExistParaName")
    public boolean isExistParaName(@RequestBody Map<String, Object> param) {
    	return rskSupervisionIndicatorParamService.isExistParaName(param);
    }
    @GetMapping("selectCustomParam")
    public List<RskSupervisionIndicatorParam005Dto> selectCustomParam(String paraCode){
        return rskSupervisionIndicatorParamService.selectCustomParam(paraCode);
    }
    @GetMapping("getModelValue")
    public String getModelValue(String modelCode){
    	return rskSupervisionIndicatorParamService.getModelValue(modelCode);
    }
    
    @PostMapping("getInstitutionId")
    public List<SelectOptionDto> getInstitutionId(@RequestBody Map<String, Object> param){
        return rskSupervisionIndicatorParamService.getInstitutionId(param);
    }
}
