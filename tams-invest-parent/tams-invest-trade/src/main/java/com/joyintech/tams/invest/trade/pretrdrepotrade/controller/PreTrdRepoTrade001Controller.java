

package com.joyintech.tams.invest.trade.pretrdrepotrade.controller;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBond003Dto;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondDto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade001Dto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.service.PreTrdRepoTrade001Service;
import com.joyintech.tams.invest.trade.pretrdrepotrade.service.PreTrdRepoTrade002Service;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.grid.Page;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * 回购投资交易预录入查询
 * 〈功能详细描述〉
 *
 * @see PreTrdRepoTrade001Controller
 * @since 1.0
 */
@PageId("PreTrdRepoTrade001")
@RequestMapping("/invest/trade/pretrdrepotrade/PreTrdRepoTrade001")
@Controller
public class PreTrdRepoTrade001Controller extends BaseController {
    /**
     * TrdRepoTrade001Service
     */
    @Autowired
    private PreTrdRepoTrade001Service preTrdRepoTrade001Service;
    @Autowired
    private PreTrdRepoTrade002Service preTrdRepoTrade002Service;
    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;
    @Override
    public String init(Model model) {
        // 向model添加下拉选项列表，及相关下拉选项常量
        this.setSelectOptionToModel(model,DictCode.回购拆借方向,DictCode.清算速度, BussinessDictCode.登记托管机构,DictCode.交易流程状态);
        model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
        model.addAttribute("platDate", platformDate.getSysDateStr());
        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<PreTrdRepoTrade002Dto> selectForPage() throws Exception {
        Page<PreTrdRepoTrade002Dto> trdRepoTrade001DtoDtoPage = preTrdRepoTrade001Service.selectForPage(RequestUtil.getPageParam());
        //针对查询流程中的质押品信息，特殊处理
        if (!StringUtil.isEmptyList(trdRepoTrade001DtoDtoPage.getRoot()) && !StringUtil.isEmpty(trdRepoTrade001DtoDtoPage.getRoot().get(0).getBusinessData())){
            PreTrdRepoTrade002Dto dto = JSON.parseObject(trdRepoTrade001DtoDtoPage.getRoot().get(0).getBusinessData(),PreTrdRepoTrade002Dto.class);
            List<PledgeBondInfoDto> pledgeBondInfoDtoList = dto.getBussRepoSecPtl().getPledgeBondListDetailed();
            List<PreTrdRepoTrade002Dto>  list = new ArrayList<>();
            if (!StringUtil.isEmptyList(pledgeBondInfoDtoList)){
                pledgeBondInfoDtoList.forEach(item ->{
                    PreTrdRepoTrade002Dto PreTrdRepoTrade002Dto = new PreTrdRepoTrade002Dto();
                    BeanUtil.copyPropertiesIgnoreNull(PreTrdRepoTrade002Dto,trdRepoTrade001DtoDtoPage.getRoot().get(0));
                    BeanUtil.copyPropertiesIgnoreNull(PreTrdRepoTrade002Dto,item);
                    PreTrdRepoTrade002Dto.setPortfolioName(item.getPortfolioName());
                    PreTrdRepoTrade002Dto.setFinprodMarketId(item.getBondCode());
                    PreTrdRepoTrade002Dto.setFinprodAbbr(item.getBondAbbrName());
                    PreTrdRepoTrade002Dto.setPledgeAmt(ArithmeticUtil.divide(item.getPrin(),new BigDecimal(10000),4));
                    PreTrdRepoTrade002Dto.setPledgeRatio(item.getPledgeRatio());
                    list.add(PreTrdRepoTrade002Dto);
                });
            }
            trdRepoTrade001DtoDtoPage.setRoot(list);
            return trdRepoTrade001DtoDtoPage;
        }
        return trdRepoTrade001DtoDtoPage;
    }


    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @param
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
    	//modify by yujiaping 2020-12-10 BS-1409【 质押式回购交易预录入】未选择点击导出应导出全部，选中点击导出，只导出选中数据start
    	String datas = this.getParam("datas");
    	List<PreTrdRepoTrade002Dto> listdto ;
    	if(datas == null) {
    		return preTrdRepoTrade001Service.export(this.getPageId(), RequestUtil.getPageParam());    		
    	}
       listdto = JsonUtils.json2List(datas, PreTrdRepoTrade002Dto.class);
       File outfile = preTrdRepoTrade001Service.makeFile(
    		   "/com/joyintech/tams/invest/trade/pretrdrepotrade/conf/export_conf.xml", listdto);
       return ExportUtil.export(
               this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                    + DateUtil.format(DateUtil.platDateFromCache())
                                    + SystemGlobalConstant.CLOSE_PARENTHESIS
                                    + SystemGlobalConstant.POINT
                                    + FileNameUtil.getFileExtName(outfile),
               outfile, true);
   	//modify by yujiaping 2020-12-10 BS-1409【 质押式回购交易预录入】未选择点击导出应导出全部，选中点击导出，只导出选中数据end
    }

    /**
     获取交易对手列表
     * @param param 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    @RequestMapping("getCounterAcct")
    @ResponseBody
    public List<SelectOptionDto> getCounterAcct(@RequestParam Map<String, Object> param) throws Exception {
        List<SelectOptionDto> TrdRepoTrade001DtoDtoPage = preTrdRepoTrade001Service.getCounterAcct(param);
        return TrdRepoTrade001DtoDtoPage;
    }


    /**
     * 分页查询
     *
     * 获取资金账号下拉框
     *
     * @return 资产代码代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getAcct")
    @ResponseBody
    public List<SelectOptionDto> getAcct(@RequestParam Map<String, Object> param) throws Exception {
        List<SelectOptionDto> TrdRepoTrade001DtoDtoPage = preTrdRepoTrade001Service.getAcct(param);
        return TrdRepoTrade001DtoDtoPage;
    }


    /**
     * 获取托管账号下拉框
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecAcct")
    @ResponseBody
    public List<SelectOptionDto> getSecAcct(@RequestParam Map<String, Object> param) throws Exception {
        if (!"null".equals(String.valueOf(param.get("portfolioId")))) {
            param.put("portfolioId","portfolioId");
        }
        List<SelectOptionDto>  TrdRepoTrade001DtoDtoPage = preTrdRepoTrade001Service.getSecAcct(param);
        return TrdRepoTrade001DtoDtoPage;
    }
    /**
     * 获取托管账号下拉框,组合代码为空，无可选择
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecAcctNew")
    @ResponseBody
    public List<SelectOptionDto> getSecAcctNew(@RequestParam Map<String, Object> param) throws Exception {
        List<SelectOptionDto>  list = new ArrayList<>();
        if (!DictCodeListExt1.是否_否.getValue().equals(String.valueOf(param.get("validPortfolioId")))
            && ("null".equals(String.valueOf(param.get("portfolioId"))) || "".equals(String.valueOf(param.get("portfolioId"))))) {
            return list;
        }
        //查询所有
        List<String> portfolioIdList = StringUtil.splitToList(GlobalConstant.COMMA, String.valueOf(param.get("portfolioId")));
        if (StringUtil.isEmptyList(portfolioIdList)){
            param.put("portfolioId","");
            list.addAll(preTrdRepoTrade001Service.getSecAcct(param));
            return list;
        }
        //根据组合查询取交集
        boolean flag = true;
        for (String portfolioId : portfolioIdList){
            param.put("portfolioId",portfolioId);
            List<SelectOptionDto> dtoList = preTrdRepoTrade001Service.getSecAcct(param);
            if (flag){
                list.addAll(dtoList);
                flag = false;
            }
            list.retainAll(dtoList);
            if (StringUtil.isEmptyList(list)){
                break;
            }
        }
        return list;
    }

    /**
     * 获取资金账号详细
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getAcctId")
    @ResponseBody
    public PreTrdRepoTrade002Dto getAcctId(@RequestParam Map<String, Object> param) throws Exception {
        PreTrdRepoTrade002Dto preTrdRepoTrade002Dto = preTrdRepoTrade001Service.getAcctId(param);
        return preTrdRepoTrade002Dto;
    }


    /**
     * 获取托管账号详细
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecAcctDto")
    @ResponseBody
    public PreTrdRepoTrade002Dto getSecAcctDto(@RequestParam Map<String, Object> param) throws Exception {
        PreTrdRepoTrade002Dto preTrdRepoTrade002Dto = preTrdRepoTrade001Service.getSecAcctDto(param);
        if (preTrdRepoTrade002Dto != null){
            preTrdRepoTrade002Dto.setRegistOrg(SysDataUtil.getNameFromDataDict(BussinessDictCode.登记托管机构,preTrdRepoTrade002Dto.getRegistOrg()));
        }
        return preTrdRepoTrade002Dto;
    }

    /**
     * 保存时校验
     *
     * @param inputDto    dto
     * @return
     */
    @RequestMapping("businessCheck")
    @ResponseBody
    public AjaxResult businessCheck(@RequestBody PreTrdRepoTrade002Dto inputDto) {
        AjaxResult at = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        List<PreTrdRepoTrade002Dto> lists = inputDto.getPreTrdRepoTrade002DtoList();
        Iterator<PreTrdRepoTrade002Dto> iterator = lists.iterator();
        //去除子节点
        while (iterator.hasNext()){
            PreTrdRepoTrade002Dto item = iterator.next();
            if (!StringUtil.equals("root",item.getParentId())){
                iterator.remove();
            }
        }
        if (StringUtil.isEmptyList(lists)) {
            errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0026")));
        }else {
            Map<String,Object> params = new HashMap<>();
            List<String> tradeIdList = new ArrayList<>();
            if (StringUtil.equals(inputDto.getOperateType(),"export")){
                for(PreTrdRepoTrade002Dto item : lists){
                    tradeIdList.add(item.getTotleTradeId());
                    if (!StringUtil.equals(item.getValidStatus(), DictCodeListExt1.交易流程状态_有效.getValue())){
                        errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_007", DictCodeListExt1.交易流程状态_有效.getCodeName())));
                        break;
                    }
                }
                params.put("tradeIdList",tradeIdList);
                List<Map<String,Object>> list = preTrdRepoTrade001Service.findTrdMatchInfo(params);
                if (!StringUtil.isEmptyList(list)){
                    errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_001","")));
                }
            }
            if (StringUtil.equals(inputDto.getOperateType(),"revoke")){
                for(PreTrdRepoTrade002Dto item : lists){
                    tradeIdList.add(item.getTotleTradeId());
                    if (!StringUtil.equals(item.getValidStatus(), DictCodeListExt1.交易流程状态_有效.getValue())){
                        errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_007", DictCodeListExt1.交易流程状态_有效.getCodeName())));
                        break;
                    }else if (StringUtil.equals(item.getValidStatus(),DictCodeListExt1.交易流程状态_流程中.getValue())){
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
                    }
                }
                params.put("tradeIdList",tradeIdList);
                List<Map<String,Object>> list = preTrdRepoTrade001Service.findTrdMatchInfo(params);
                if (!StringUtil.isEmptyList(list)){
                    errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_002")));
                }
            }
        }
        if (!StringUtil.isEmptyList(errorList)){
            at.setErrorList(errorList);
        }else {
            String btnName = "";
            if (StringUtil.equalsIgnoreCase(ACTION_EXPORT, inputDto.getOperateType())) {
                btnName = "SysCommon.export";
            }
            else if (StringUtil.equalsIgnoreCase("revoke", inputDto.getOperateType())) {
                btnName = "SysCommon.revoke";
            }
            at.setData(this.getMessage("COMMON_C_0001", this.getMessage(btnName)));
        }
        return at;
     }

    /**
     * 撤销
     * @param inputDto 画面输入参数dto
     * @return 撤销结果
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("doRevoke")
    @ResponseBody
    AjaxResult doRevoke(@RequestBody @Validated({
            ValidateGroupDelete.class}) PreTrdRepoTrade002Dto inputDto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        String msg = "";
        PreTrdRepoTrade002Dto dto = inputDto.getPreTrdRepoTrade002DtoList().get(0);
        ProcessParameter<PreTrdRepoTrade002Dto> processParameter = new ProcessParameter<PreTrdRepoTrade002Dto>();
        WorkFlowParameter<PreTrdRepoTrade002Dto> params = new WorkFlowParameter<PreTrdRepoTrade002Dto>();
        processParameter.setWorkFlowParameter(params);
        processParameter.setPageId("PreTrdRepoTrade002");
        params.setPageId("PreTrdRepoTrade002");
        params.setElementId(this.getParam("elementId"));
        params.setBusinessCode(item -> item.getTotleTradeId());
        params.setBusinessType(DictCodeListExt1.共通业务类型_质押式回购交易预录入.getValue());
        // 设置流程业务说明, entity即传入的dto
        // “交易编号：” + 上述取得的交易编号+“资产名称：”+画面.资产名称+“交易日：”+画面.交易日
        params.setComment((entity) -> WorkFlowUtil.concatComment(
                getMessage("TrdRepoTrade002.tradeId") + GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdRepoTrade002.ps") + GlobalConstant.OPEN_PARENTHESIS + SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向,entity.getPs())+ GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("TrdRepoTrade002.tradeDate") + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(entity.getTradeDate()) + GlobalConstant.CLOSE_PARENTHESIS)
        );
        processParameter.setWorkFlowParameter(params);
        dto.setOperateType(inputDto.getOperateType());
        msg = preTrdRepoTrade002Service.start(processParameter, dto);
        return new AjaxResult(msg);


    }

}
