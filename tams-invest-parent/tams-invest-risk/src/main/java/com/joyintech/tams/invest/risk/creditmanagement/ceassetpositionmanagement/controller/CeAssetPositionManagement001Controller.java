/**
 * 
 */
package com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.dto.CeAssetPositionManagement001Dto;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetpositionmanagement.service.CeAssetPositionManagement001Service;

/**
 * 〈一句话功能简述〉 底层持仓导入管理
 * 
 * @author dongzhiling
 * @version 1.0 2019年1月7日
 * @see CeAssetPositionManagement001Controller
 * @since 1.0
 */

@Controller
@PageId("CeAssetPositionManagement001")
@RequestMapping("/invest/risk/creditmanagement/ceassetpositionmanagement/CeAssetPositionManagement001")
public class CeAssetPositionManagement001Controller extends BaseController{
    
    /**
     * CeAssetPositionManagement001Service
     */
    @Autowired(required = false)
    private CeAssetPositionManagement001Service ceAssetPositionManagement001Service;
    
    /**
     * 
     * 页面初始化加载
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.平安资产类型);
        return super.init(model);
    }
    
    /**
     * <一句话功能简述> 画面查询方法
     * 
     * @return 画面查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CeAssetPositionManagement001Dto> selectAll() {
        return ceAssetPositionManagement001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 
     * <一句话功能简述>
     * 查询资产代码联想下拉框
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryAssetCodeInfo")
    public List<SelectOptionDto> queryGroupIdInfo() {
        return ceAssetPositionManagement001Service.queryAssetCodeInfo(RequestUtil.getParameters(getRequest()));
    }
    
    
    /**
     * <一句话功能简述> 导出
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ceAssetPositionManagement001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
