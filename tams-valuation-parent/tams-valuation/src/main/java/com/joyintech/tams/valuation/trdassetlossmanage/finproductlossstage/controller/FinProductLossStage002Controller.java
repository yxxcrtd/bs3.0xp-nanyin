package com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.service.FinProductLossStage001Service;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.service.FinProductLossStage002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@PageId("FinProductLossStage002")
@RequestMapping("/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002")
public class
FinProductLossStage002Controller extends BaseController {

    @Autowired
    private FinProductLossStage001Service finProductLossStage001Service;
    @Autowired
    private FinProductLossStage002Service finProductLossStage002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.减值阶段);
        model.addAttribute("finprodId",this.getParam("finprodId"));
        model.addAttribute("stage",this.getParam("stage"));
        model.addAttribute("seqNo",this.getParam("seqNo"));
        // 当新建的时候，将生效日设置为平台日
        if (this.getParam("effDateStr")==null || this.getParam("effDateStr")==""){
            String sysDate = DateUtil.getSysDate();
            model.addAttribute("effDateStr",sysDate);
        }else { // 当编辑的时候，将生效日回显即可
            model.addAttribute("effDateStr",this.getParam("effDateStr"));
        }
        return super.init(model);
    }


    /**
     * 新建方法
     */
    @ResponseBody
    @RequestMapping("saveFinProLosSta")
    public AjaxResult saveFinProLosSta(@RequestBody FinProductLossStage001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();

        /**
         * 判断是否存在相同资产和相同生效日期的数据，如果存在则不允许插入到数据库
         */
        String finprodId = dto.getFinprodId();
        Date effDate = dto.getEffDate();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("finprodId",finprodId);
        hashMap.put("effDate",effDate);
        Page<FinProductLossStage001Dto> page = finProductLossStage001Service.queryPageList(hashMap);
        if (page.getRoot().size()>0){
            List<FinProductLossStage001Dto> root = page.getRoot();
            for (int i=0;i<root.size();i++){
                FinProductLossStage001Dto finProductLossStage001Dto = root.get(i);
                String fId = finProductLossStage001Dto.getFinprodId();
                Date eDate = finProductLossStage001Dto.getEffDate();
                if (fId.equals(finprodId)&&eDate.compareTo(effDate)==0){
                    errorList.add(new ValidMessage("已经存在相同资产和相同生效日期的数据，请重新选择！"));
                    ajaxResult.setErrorList(errorList);
                    return ajaxResult;
                }
            }
        }

//        查询上一条有效的的资产减值阶段对象
        List<FinProductLossStage001Dto> prevValidObj = finProductLossStage001Service.getPrevValidObj(dto);
        if (prevValidObj.size()>0){
            prevValidObj.get(0).setInvalidDate(dto.getEffDate());
            //        更新失效日期
            finProductLossStage002Service.updateInvalidDate(prevValidObj.get(0));
        }

//        查询下一条有效的的资产减值阶段对象
        List<FinProductLossStage001Dto> nextValidObj = finProductLossStage001Service.getNextValidObj(dto);
        if (nextValidObj.size()>0){
            dto.setInvalidDate(nextValidObj.get(0).getEffDate());
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2099-12-31");
            dto.setInvalidDate(date);
        }

        String generate = IDGenerator.generate();
        BaseEntity baseEntity = new BaseEntity();
        dto.setSeqNo(generate);
        dto.setCreateUser(baseEntity.getCreateUser());
        dto.setCreateTime(baseEntity.getCreateTime());
        dto.setUpdateUser(baseEntity.getUpdateUser());
        dto.setUpdateTime(baseEntity.getUpdateTime());
        String msg = "保存成功！";
        finProductLossStage002Service.saveFinProLosSta(dto);
        //        更新失效日期
        finProductLossStage002Service.updateInvalidDate(dto);
        return new AjaxResult(msg);
    }

    /**
     * 编辑
     */
    @ResponseBody
    @RequestMapping("editFinProLosSta")
    public AjaxResult editFinProLosSta(@RequestBody FinProductLossStage001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        // 更新修改时间
        BaseEntity baseEntity = new BaseEntity();
        dto.setUpdateTime(baseEntity.getUpdateTime());
        finProductLossStage002Service.editFinProLosSta(dto);
        return new AjaxResult("修改成功！");
    }

}
