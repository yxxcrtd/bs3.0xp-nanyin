/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.cfets.etrib.repo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.bond.dto.CfetsEtrIbBondPosiInfoRequestDto;
import com.joyintech.tams.cfets.etrib.repo.dto.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtribRepoInfo003Dto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.cfets.etrib.repo.service.CfetsEtrIbRepoinfoService;
import com.joyintech.tams.component.business.trade.BussEtrInvestPersonInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see CfetsEtrIbRepoinfoController
 * @since 1.0
 */
@RestController
@PageId("CfetsEtrRepoInfo")
@RequestMapping("/cfets/etrib/cfetsEtrIbRepoinfo")
@Api(tags = "银行间回购指令")
public class CfetsEtrIbRepoinfoController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CfetsEtrIbRepoinfoController.class);
    /**
     * 市场行情接口地址
     */
/*    @Value("${MarketInfoInterf.BaseUrl}")
    private String MarketInfoInterf_BaseUrl;*/
    /**
     * 外汇交易中心行情接口地址
     */
    @Value("${MarketInfoInterf.cmdsUrl}")
    private String MarketInfoInterf_cmdsUrl;

    /**
     * 债券持仓查询
     */
    @Autowired
    private CfetsEtrIbRepoinfoService cfetsEtrIbRepoinfoService;
    
    @Autowired(required=false)
    private BussEtrInvestPersonInfoService bussEtrInvestPersonInfoService;

    @Value("${BondWebSocketUrl}")
    private String RepoWebSocketUrl;


    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        initPageParams.put("FINPROD_TYPE_TG_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        initPageParams.put("FINPROD_TYPE_TG_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        // 回购模式
        initPageParams.put("repo_pledge_model", CacheUtil.getSysParam("repo_pledge_model"));
        //本币业务指令画面头寸信息区域自动刷新时间(ms)
        initPageParams.put("cfetsEtrIbRepoinfo_positionTable_refresh_period", CacheUtil.getSysParam("cfetsEtrIbRepoinfo_positionTable_refresh_period"));
        //本币业务指令画面指令列表区域自动刷新时间(ms)
        initPageParams.put("cfetsEtrIbRepoinfo_etrTable_refresh_period", CacheUtil.getSysParam("cfetsEtrIbRepoinfo_etrTable_refresh_period"));

        //CMDS地址
        initPageParams.put("RepoWebSocketUrl", RepoWebSocketUrl);
        initPageParams.put("NEED_WORKFLOW", true);
        initPageParams.put("platDate",DateUtil.platDateFromCache());
        //风险
        initPageParams.put("riskMathSwitch", CacheUtil.getSysParam("risk_math_switch"));
        initPageParams.put("pageRedisFlag", CacheUtil.getSysParam("pageRedisFlag"));
        initPageParams.put("isRiskmathSoftcontrol", CacheUtil.getSysParam("IS_RISKMATH_SOFTCONTROL"));
        //数据字典初始化数据
        initPageParams.put("FINPROD_TYPE", this.getSelectDataFromDataDic(DictCode.行内资产四级分类, null));
        initPageParams.put("PRICE_MODEL", this.getSelectDataFromDataDic(DictCode.价格模式, null));
        initPageParams.put("ACCOUNT_SPEED", this.getSelectDataFromDataDic(DictCode.清算速度, null));
        initPageParams.put("REGIST_ORG", this.getSelectDataFromDataDic(DictCode.登记托管机构, null));
        //pageId
        initPageParams.put("pageId", "CfetsEtrIbRepoinfo003");
        return new Message<Map<String, Object>>(initPageParams).success();
    }
    
    /**
     * 指令管理查询
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findZlglPage")
    @ApiOperation(value = "指令管理查询", notes = "指令管理查询")
    public Message<Page<CfetsEtrRepoZLCXResponse>> findZlglPage(@RequestBody CfetsEtrRepoZLCXRequest cfetsEtrRepoZLCXRequest){
        try {
            Page<CfetsEtrRepoZLCXResponse> list = cfetsEtrIbRepoinfoService.findZlglPage(cfetsEtrRepoZLCXRequest);
            return new Message<Page<CfetsEtrRepoZLCXResponse>>().success().setData(list);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询指令管理分页数据异常",e);
            return new Message<Page<CfetsEtrRepoZLCXResponse>>().fail().setMessage("查询指令管理分页数据异常");
        }
    }

    /**
     * 查询债券质押持仓
     * 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findZlglPositionPage")
    @ApiOperation(value = "质押券管理查询", notes = "质押券管理查询")
    public Message<Page<CfetsEtrRepoZLCXPositionResponse>> findZlglPositionPage(@RequestBody CfetsEtrRepoZLCXPositionRequest cfetsEtrRepoZLCXPositionRequest){
        try {
            Page<CfetsEtrRepoZLCXPositionResponse> list = cfetsEtrIbRepoinfoService.findZlglPositionPage(cfetsEtrRepoZLCXPositionRequest);
            return new Message<Page<CfetsEtrRepoZLCXPositionResponse>>().success().setData(list);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询指令管理分页数据异常",e);
            return new Message<Page<CfetsEtrRepoZLCXPositionResponse>>().fail().setMessage("查询债券质押持仓");
        }
    }

    @PostMapping(value="/exportPosition")
    @ApiOperation(value = "质押券列表导出", notes = "质押券列表导出")
    public void exportPosition(@RequestBody CfetsExportRequestDto requestDto, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(requestDto, response, "持仓列表", () -> {
            CfetsEtrRepoZLCXPositionRequest dto = requestDto.getDto(CfetsEtrRepoZLCXPositionRequest.class);
            List dataList = cfetsEtrIbRepoinfoService.findZlglPositionPage(dto).getRoot();
            return dataList;
        });
    }

    @PostMapping(value="/exportEtrInfo")
    @ApiOperation(value = "指令管理导出", notes = "指令管理导出")
    public void exportEtrInfo(@RequestBody CfetsExportRequestDto req, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(req, response, "指令管理", () -> {
            List dataList = cfetsEtrIbRepoinfoService.findZlglPage(req.getDto(CfetsEtrRepoZLCXRequest.class)).getRoot();
            return dataList;
        });
    }

    /**
     * 质押库：下拉框
     *
     * @param params 画面参数
     * @return 质押库：下拉框
     * @see
     * @since 1.0
     */
    @PostMapping("getPledgeStockIdByPortfolioId")
    @ApiOperation(value = "质押库：下拉框", notes = "质押库：下拉框")
    public Message<Page<SelectOptionDto>> getPledgeStockIdByPortfolioId(@RequestBody CfetsSearchPledgeStockRequest params) {
    	try {
    		Page<SelectOptionDto> page = cfetsEtrIbRepoinfoService.getPledgeStockIdByPortfolioId(params);
			return new Message<Page<SelectOptionDto>>().success().setData(page);
		} catch (Exception e) {
			logger.error("查询功能异常", e);
			return new Message<Page<SelectOptionDto>>().fail().setMessage("查询异常");
		}
    	
    }
    
    /**
     * 出库操作
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "出库操作", notes = "出库操作")
    @PostMapping("outSave")
    public AjaxResult outSave(@RequestBody CfetsEtrRepoSaveRequest dto) {
     // 校验操作的角色是否是投资经理
        Map<String,Object> map = new HashMap<>();
        map.put("role_pos", DictCodeListExt2.角色岗位_投资经理.getValue());
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        Long count = cfetsEtrIbRepoinfoService.selectUserRolePosList(map) ;
        if(count > 0){
            Long outFlat = cfetsEtrIbRepoinfoService.outSave(dto);
            // 标记为大于0，出库成功
            if(outFlat > 0){
                return new AjaxResult(this.getMessage("EtrStandardBond001.outSave_002"));
            }else{
                return new AjaxResult(this.getMessage("EtrStandardBond001.outSave_003"));
            }
        }else{
            AjaxResult ar = new AjaxResult();
            List<JoyinMessage> joyinMessages = new ArrayList<>();
            joyinMessages.add(
                    new JoyinMessage("角色岗位为投资经理才能操作", MessageType.警告消息));
            ar.setJoyinMessages(joyinMessages);

            return ar ;
        }
    }
    
//    @RequestMapping(value="findQuotations")
//    @ResponseBody
//    public Map<String, Object> findQuotations() {
//    	return etrIbRepo001Service.findQuotations(RequestUtil.getPageParam());
//    }

    @ApiOperation(value = "多组合指令新建保存/提交校验", notes = "多组合指令新建保存/提交校验")
    @PostMapping("checkSaveError")
    public Message<AjaxResult> checkSaveError(@RequestBody CfetsEtrRepoSaveRequest cfetsDto, BindingResult br)
            throws Exception {
        try {
            AjaxResult ajax= cfetsEtrIbRepoinfoService.checkSaveErrorMethod(cfetsDto, br);
            if(null!=ajax){
                return new Message<AjaxResult>().success().setData(ajax);
            }else{
                return new Message<AjaxResult>().success().setMessage("校验通过");
            }
        }catch (Exception e) {
            logger.error("多组合指令新建保存/提交校验异常",e);
            return new Message<AjaxResult>().fail().setMessage("校验失败");
        }
    }

    @ApiOperation(value = "头寸信息查询", notes = "头寸信息查询")
    @PostMapping("/queryPositionInfo")
    @ResponseBody
    public Message<List<BaseMap>> queryPositionInfo(@RequestBody CfetsEtrIbBondPosiInfoRequestDto requestDto) {
        List<BaseMap> m = cfetsEtrIbRepoinfoService.queryPositionInfo(requestDto);
        return new Message<List<BaseMap>>().success().setData(m);
    }
}
