package com.joyintech.tams.invest.period.trdprojbuyoff.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.period.trdprojbuyoff.dto.TrdProjectBuyOffInterest001Dto;
import com.joyintech.tams.invest.period.trdprojbuyoff.service.TrdProjectBuyOffInterest001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: 买断利率维护
 * @author: 强成西
 * @create: 2018-10-23 13:44
 **/
@Controller
@PageId("TrdProjectBuyOffInterest001")
@RequestMapping("/invest/period/trdprojbuyoff/TrdProjectBuyOffInterest001")
public class TrdProjectBuyOffInterest001Controller extends BaseController {

    @Autowired
    private TrdProjectBuyOffInterest001Service trdProjectBuyOffInterest001Service;
    /**
     * 需要初始化的下拉菜单
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {
        DictCode.项目类一级分类};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        return super.init(model);
    }

    /**
     * 获取所有项目类资产
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProjAssetId")
    public Page<SelectOptionDto> getProjAssetId() {
        return trdProjectBuyOffInterest001Service.getProjAssetId();
    }

    /**
     * 获取所有项目类资产
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdProjectBuyOffInterest001Dto> findPage() {
        return trdProjectBuyOffInterest001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 删除数据
     *
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("deleteData")
    public AjaxResult deleteData(@RequestBody TrdProjectBuyOffInterest001Dto dto) {
         trdProjectBuyOffInterest001Service.deleteData(dto.getSelectedData());
        return new AjaxResult();
    }

    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
            throws Exception{
        return this.trdProjectBuyOffInterest001Service.importData(fileName);
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return trdProjectBuyOffInterest001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
}
