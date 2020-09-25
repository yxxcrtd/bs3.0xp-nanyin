package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.controller;

import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil; 
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinPledgeDisplaceDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.service.PtlRepoPledgeSearch001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: tams-invest-parent
 * @description: PtlRepoPledgeSearch001Controller
 * @author: liuwei
 * @create: 2018-11-27 16:45
 **/
@Controller
@PageId("PtlRepoPledgeSearch001")
@RequestMapping("/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/PtlRepoPledgeSearch001")
public class PtlRepoPledgeSearch001Controller extends BaseController {
    /**
     * 债券质押查询service
     */
    @Autowired
    private PtlRepoPledgeSearch001Service ptlRepoPledgeSearch001Service;

    /**
     * 债券质押查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.WIND一级分类_含债券一级分类等,
                DictCode.WIND二级分类_含债券二级分类等);
        //质押券置换查询
        List<SelectOptionDto> psList =new ArrayList<SelectOptionDto>();
        SelectOptionDto psSelectOptionOne=new SelectOptionDto();
        psSelectOptionOne.setId(DictCodeList.回购拆借方向_融入.getValue());
        psSelectOptionOne.setText(DictCodeList.回购拆借方向_融入.getCodeName());
        psList.add(psSelectOptionOne);
        SelectOptionDto psSelectOptionTwo=new SelectOptionDto();
        psSelectOptionTwo.setId(DictCodeList.回购拆借方向_融出.getValue());
        psSelectOptionTwo.setText(DictCodeList.回购拆借方向_融出.getCodeName());
        psList.add(psSelectOptionTwo);
        SelectOptionDto psSelectOptionThree=new SelectOptionDto();
        psSelectOptionThree.setId(DictCodeList.回购拆借方向_正回购.getValue());
        psSelectOptionThree.setText(DictCodeList.回购拆借方向_正回购.getCodeName());
        psList.add(psSelectOptionThree);
        SelectOptionDto psSelectOptionFour=new SelectOptionDto();
        psSelectOptionFour.setId(DictCodeList.回购拆借方向_逆回购.getValue());
        psSelectOptionFour.setText(DictCodeList.回购拆借方向_逆回购.getCodeName());
        psList.add(psSelectOptionFour);

        List<SelectOptionDto> typeList =new ArrayList<SelectOptionDto>();
        SelectOptionDto typeSelectOptionOne=new SelectOptionDto();
        typeSelectOptionOne.setId(DictCodeList.业务类型_债券质押式回购交易.getValue());
        typeSelectOptionOne.setText(DictCodeList.业务类型_债券质押式回购交易.getCodeName());
        typeList.add(typeSelectOptionOne);
        SelectOptionDto typeSelectOptionTwo=new SelectOptionDto();
        typeSelectOptionTwo.setId(DictCodeList.业务类型_债券借贷交易.getValue());
        typeSelectOptionTwo.setText(DictCodeList.业务类型_债券借贷交易.getCodeName());
        typeList.add(typeSelectOptionTwo);
        model.addAttribute("TradePs", JsonUtil.objToString(psList));
        model.addAttribute("TradeType", JsonUtil.objToString(typeList));
        return super.init(model);
    }

    /**
     * 组合代码的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getCounterId")
    public List<SelectOptionDto> getCounterId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<SelectOptionDto> temp = ptlRepoPledgeSearch001Service.getCounterId(map);
        return temp;
    }
    /**
     * 根据交易编号查询质押券置换明细
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPledgeDetail")
    public Page<FinPledgeDisplaceDetail> getPledgeDetail(){
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<FinPledgeDisplaceDetail> list = ptlRepoPledgeSearch001Service.getPledgeDetail(map);
        return list;
    }

    /**
     * 资产代码的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<SelectOptionDto> temp = ptlRepoPledgeSearch001Service.getFinprodMarketId(map);
        return temp;
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlRepoPledgeSearchDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getPageParam();
        return ptlRepoPledgeSearch001Service.selectForPage(map);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck()
            throws Exception {
        Page<PtlRepoPledgeSearchDto> result = ptlRepoPledgeSearch001Service.selectForPage(RequestUtil.getPageParam());
//        if (result.getRoot().isEmpty()) {
//            return new AjaxResult(getMessage("PTL_COM_E_0012"), MessageType.错误消息);
//        }
        return new AjaxResult();
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ptlRepoPledgeSearch001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
