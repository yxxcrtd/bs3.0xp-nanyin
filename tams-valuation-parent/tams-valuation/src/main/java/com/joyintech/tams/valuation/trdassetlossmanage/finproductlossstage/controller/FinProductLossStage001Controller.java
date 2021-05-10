package com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.service.FinProductLossStage001Service;
import com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.service.FinProductLossStage002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.File;
import java.util.*;

@Controller
@PageId("FinProductLossStage001")
@RequestMapping("/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001")
public class FinProductLossStage001Controller extends BaseController {

    @Autowired
    private FinProductLossStage001Service finProductLossStage001Service;
    @Autowired
    private FinProductLossStage002Service finProductLossStage002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.减值阶段);
        return super.init(model);
    }

    /**
     * check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinProductLossStage001Dto dto, BindingResult br) throws Exception {
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        String finprodId = null;
        String stage = null;
        Date effDate = null;
        if (dto.getSelectedItem()!=null){
            finprodId = dto.getSelectedItem().get(0).getFinprodId();
            stage = dto.getSelectedItem().get(0).getStage();
            effDate = dto.getSelectedItem().get(0).getEffDate();
        }else {
            finprodId = dto.getFinprodId();
            stage = dto.getStage();
            effDate = dto.getEffDate();
        }
        if (!StringUtil.isNotEmpty(finprodId)){
            errorList.add(new ValidMessage("资产名称为必填项！"));
        }
        if(!StringUtil.isNotEmpty(stage)){
            errorList.add(new ValidMessage("阶段为必填项！"));
        }
        if(effDate == null){
            errorList.add(new ValidMessage("生效日期为必填项！"));
        }
        ajaxResult.setErrorList(errorList);
        if (errorList.size()>0){
            return ajaxResult;
        }

        /**
         * 判断是否存在相同资产和相同生效日期的数据，如果存在则不允许插入到数据库
         */
//        Map<String, Object> hashMap = new HashMap<>();
//        hashMap.put("finprodId",finprodId);
//        hashMap.put("effDate",effDate);
//        Page<FinProductLossStage001Dto> page = finProductLossStage001Service.queryPageList(hashMap);
//        if (page.getRoot().size()>0){
//            List<FinProductLossStage001Dto> root = page.getRoot();
//            for (int i=0;i<root.size();i++){
//                FinProductLossStage001Dto finProductLossStage001Dto = root.get(i);
//                String fId = finProductLossStage001Dto.getFinprodId();
//                Date eDate = finProductLossStage001Dto.getEffDate();
//                if (fId.equals(finprodId)&&eDate.compareTo(effDate)==0){
//                    errorList.add(new ValidMessage("已经存在相同资产和相同生效日期的数据，请重新选择！"));
//                    ajaxResult.setErrorList(errorList);
//                    return ajaxResult;
//                }
//            }
//        }
        FinProductLossStage001Dto f = null;
        if (dto.getSelectedItem()==null){
            f = finProductLossStage001Service.getBooksetDate(dto);
        }
        if (f==null)
            return ajaxResult;
        Date date1 = dto.getEffDate();
        Date date2 = f.getEffDate();
        int compareTo = date1.compareTo(date2);
        if (compareTo>0)
        {
            errorList.add(new ValidMessage("存在账套日大于生效日期，请修改！"));
            ajaxResult.setErrorList(errorList);
        }
        return ajaxResult;
    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductLossStage001Dto> selectAll() throws Exception {
        Page<FinProductLossStage001Dto> page= finProductLossStage001Service.queryPageList(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 查询资产名称
     */
    @ResponseBody
    @RequestMapping("getFinprodName")
    public Page<SelectOptionDto> getFinprodName(){
        Map<String, Object> param =RequestUtil.getPageParam();
        Page<SelectOptionDto> finprodName = finProductLossStage001Service.getFinprodName(param);
        return finprodName;
    }

    /**
     * 删除数据方法
     */
    @ResponseBody
    @RequestMapping("deleteFinProLosSta")
    public AjaxResult delete(@RequestBody FinProductLossStage001Dto dto,BindingResult br){
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }

        List<FinProductLossStage001Dto> selectedItem = dto.getSelectedItem();
        // 查询上一条有效的资产减值阶段对象
        for (FinProductLossStage001Dto d:selectedItem){
            List<FinProductLossStage001Dto> prevValidObj = finProductLossStage001Service.getPrevValidObj(d);
            if (prevValidObj.size()>0){
                prevValidObj.get(0).setInvalidDate(d.getInvalidDate());
                //        更新失效日期
                finProductLossStage002Service.updateInvalidDate(prevValidObj.get(0));
            }
        }

        // 进行删除
        if (selectedItem!=null&&selectedItem.size()>0){
            for (FinProductLossStage001Dto d:selectedItem){
                int n = finProductLossStage001Service.deleteFinProLosSta(d);
            }
            return new AjaxResult("删除成功！");
        }
        return new AjaxResult("删除失败！");
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<FinProductLossStage001Dto> listdto;
        if (datas == null) {
            listdto = finProductLossStage001Service.getAllList(paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, FinProductLossStage001Dto.class);
        }
        listdto.forEach(
                item->{
                    item.setEffDateStr(DateUtil.format(item.getEffDate()));
                }
        );
        File outFile = finProductLossStage001Service.makeFile(
                "/com/joyintech/tams/valuation/trdassetlossmanage/finproductlossstage/conf/export_conf.xml", listdto);
        String name = this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}
