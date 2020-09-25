package com.joyintech.tams.invest.product.rddesign.productelementdes.controller;
/**
 * 产品要素设计-查询
 * @author jiangyunlong
 * @date 2019-9-25
 */

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes001Dto;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes002Dto;
import com.joyintech.tams.invest.product.rddesign.productelementdes.service.ProductelementdesService;
import com.joyintech.tams.invest.product.rddesign.productelementdes.service.ProductelementdesService002;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@PageId("Productelementdes001")
@RequestMapping("/invest/product/rddesign/productelementdes/productelementdes001")
public class Productelementdes001Controller extends BaseController {

    /**
     * 产品报价管理查询service
     */
    @Autowired
    private ProductelementdesService ProductelementdesService;
    /**
     * 产品报价管理查询service
     */
    @Autowired
    private ProductelementdesService002 productelementdesService002;
    /**
     * 工作流CommonServic
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        setSelectOptionToModel(model, DictCode.上报状态,DictCode.募集方式,DictCode.投资性质,DictCode.产品运作模式,
                DictCode.目标客户类型);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.有效状态, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeListExt1.有效状态_撤销.getValue(), dto.getId())
                    || StringUtil.equals(DictCodeListExt1.有效状态_有效.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.有效状态.getValue(),
                JsonUtils.objectArray2Json(optionsNew.toArray()));
        return super.init(model);
    }
    


    /**
     * 产品要素设计查询
     * 
     * @return Page<Productelementdes002Dto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<Productelementdes002Dto> findPage()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        Page<Productelementdes002Dto> p=ProductelementdesService.findPage(param);
        return p;
    }
    @ResponseBody
    @RequestMapping("findDetailPage")
    public Page<Productelementdes001Dto> findDetailPage()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        Page<Productelementdes001Dto> p=ProductelementdesService.findDetailPage(param);
        return p;
    }
    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody Productelementdes002Dto dto)
            throws Exception {
        ProcessParameter<Productelementdes002Dto> processParameter = new ProcessParameter<Productelementdes002Dto>();
        WorkFlowParameter<Productelementdes002Dto> params = new WorkFlowParameter<Productelementdes002Dto>();
        //判断是否在工作流中
        String prdDesignId = dto.getPrdDesignId();
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(getPageId());
        parameter.setBusinessDataPk1(prdDesignId);
        if(mstWorkflowCommonService.isInWflProcess(parameter)){
            AjaxResult result = new AjaxResult();
            result.setMessage("不能删除流程中的数据！");
            result.setMessageType(MessageType.错误消息);
            return result;
        }
        params.setPageId("Productelementdes004");
        dto.setCreateUser(LoginUserUtil.getUserName());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getPrdDesignId());

        params.setBusinessType(DictCodeList.共通业务类型_产品要素设计.getValue());
        params.setComment(entity->"产品要素设计编号" + "(" + entity.getPrdDesignId() + ")"
                );
        processParameter.setWorkFlowParameter(params);

        String msg = productelementdesService002.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        return ProductelementdesService.export(this.getPageId(), RequestUtil.getPageParam());
    }
}
