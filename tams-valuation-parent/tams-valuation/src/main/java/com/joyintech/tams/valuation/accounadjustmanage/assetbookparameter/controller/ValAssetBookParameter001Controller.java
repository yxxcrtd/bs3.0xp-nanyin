/*
 * 文件名：ValAssetBookParameter001Controller.java
 * 描述：账套参数管理
 * 修改人：tanxuan
 * 修改时间：2019年11月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.dto.ValAssetBookParameter002Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assetbookparameter.service.ValAssetBookParameter001Service;

@Controller
@PageId("ValAssetBookParameter001")
@RequestMapping("/valuation/accounadjustmanage/assetbookparameter/ValAssetBookParameter001")
public class ValAssetBookParameter001Controller extends BaseController {

    /**
     * ValAssetBookParameter001Service
     */
    @Autowired
    private ValAssetBookParameter001Service valAssetBookParameter001Service;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.账套参数类型);
        return super.init(model);
    }
    
    /**
     * 获取查询结果
     * 
     * @return List<ValAssetBookParameter002Dto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping("ValAssetBookParameter001search")
    @ResponseBody
    public Page<ValAssetBookParameter002Dto> search() throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("booksetId",this.getParam("booksetId"));
        params.put("paramCode",this.getParam("paramCode"));
        params.put("paramType",this.getParam("paramType"));
        return valAssetBookParameter001Service.getAllAssetbookPage(params);
    }

    /**
     * 检索账套代码(只取理财账套)
     *
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public Page<SelectOptionDto> getBooksetId() throws Exception {
        return valAssetBookParameter001Service.getBooksetIdNew(RequestUtil.getPageParam());
    }
    
    /**
     * 新建参数check
     *
     * @return 
     * @throws Exception
     * @see
     * @since 1.0
     */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody ValAssetBookParameter002Dto paramdto, BindingResult bindingResult)
			throws Exception {
//		String msg = StringUtil.EMPTY;
//		String booksetId = paramdto.getBooksetId();
//		List<ValAssetBookParameter002Dto> paramlist = new ArrayList<>();
//		paramlist = valAssetBookParameter001Service.checkfornew(booksetId);
//		if(paramlist.isEmpty()) {
//			msg = "该账套所用模板无更多参数！";
//			return new AjaxResult(msg, MessageType.错误消息);
//		}
        return new AjaxResult();
	}
	
    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<ValAssetBookParameter002Dto> listdto;
        
        if (datas == null) {
            listdto = valAssetBookParameter001Service.queryAllList(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ValAssetBookParameter002Dto.class);
        }

        File outFile = valAssetBookParameter001Service.makeFile(
            "/com/joyintech/tams/valuation/accounadjustmanage/assetbookparameter/conf/export_valassetbookparameter.xml",
            listdto);
        return ExportUtil.export("账套参数"  + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

}
