package com.joyintech.tams.basicdata.perprodeclareinfo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.basicdata.perprodeclareinfo.dto.SvrPerProDeclareInfo001Dto;
import com.joyintech.tams.basicdata.perprodeclareinfo.service.SvrPerProDeclareInfo001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品申报登记信息
 *
 * @author yxj
 * @date 1.0 2022年03月07日
 * @since 1.0
 */
@Controller
@PageId("SvrPerProDeclareInfo001")
@RequestMapping("/basicdata/perprodeclareinfo/SvrPerProDeclareInfo001")
public class SvrPerProDeclareInfo001Controller extends BaseController {

    @Autowired
    private SvrPerProDeclareInfo001Service svrPerProDeclareInfo001Service;

    /**
     * 初始化
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.产品期限, DictCode.产品运作模式, DictCode.报送数据状态,DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 页面查询
     * @return page
     */
    @ResponseBody
    @PostMapping("selectForPage")
    public Page<SvrPerProDeclareInfo001Dto> selectForPage() {
        return svrPerProDeclareInfo001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 产品名称 联想下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("queryProdInfo")
    public List<SuggestDto> queryProdInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("prodName",RequestUtil.getPageParam().get("prodName"));
        return svrPerProDeclareInfo001Service.queryProdInfo(map);
    }

    /**
     * 删除check
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody SvrPerProDeclareInfo001Dto dto) {
        AjaxResult ar =new AjaxResult();
        List<ValidMessage> errorList = ar.getErrorList();
        if (StringUtil.equals(dto.getStatus(), DictCodeListExt1.维护数据状态_删除.getValue())) {
            errorList.add(new ValidMessage("数据状态为删除的数据不允许修改！"));
            ar.setErrorList(errorList);
        }
        if(dto.getSelectedItem() != null && (!dto.getSelectedItem().isEmpty())){
            for (SvrPerProDeclareInfo001Dto entity : dto.getSelectedItem()) {
                if (StringUtil.equals(entity.getStatus(), DictCodeListExt1.维护数据状态_删除.getValue())){
                    errorList.add(new ValidMessage("数据状态为删除的数据不允许修改！"));
                    ar.setErrorList(errorList);
                }
            }
        }
        return ar;
    }

    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody SvrPerProDeclareInfo001Dto dto, BindingResult br)
            throws Exception {
        if (!(dto.getSelectedItem().isEmpty())) {
            svrPerProDeclareInfo001Service.deleteByPrimaryKey(dto);
            return new AjaxResult("删除成功");
        }
        return new AjaxResult("删除失败");
    }

    /**
     * 多行删除
     * @param map
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("deleteList")
    public AjaxResult deleteList(@RequestBody Map<String,Object> map) throws Exception {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        if (map.get("datas") != null && !map.get("datas").equals("")) {
             svrPerProDeclareInfo001Service.deleteList(map);
            return new AjaxResult("删除成功");
        }
        return new AjaxResult("删除失败");
    }


    /**
     * excel 导出
     * @return
     * @throws Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> params= RequestUtil.getPageParam();
        List<SvrPerProDeclareInfo001Dto> listdto = null ;
        if (datas == null) {
              listdto = svrPerProDeclareInfo001Service.queryAllList(params);
        } else {
            listdto = JsonUtils.json2List(datas, SvrPerProDeclareInfo001Dto.class);
        }
        File outFile = svrPerProDeclareInfo001Service.makeFile(
                "/com/joyintech/tams/basicdata/perprodeclareinfo/conf/export_conf.xml",
                listdto);
        return ExportUtil.export(
                "产品申报登记信息维护" + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        return svrPerProDeclareInfo001Service.checkConfirmMsgForImportData(fileName);
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("importData")
    public List<ExcelValidDto> importFile(String fileName) {
        return svrPerProDeclareInfo001Service.importData(fileName);
    }
}
