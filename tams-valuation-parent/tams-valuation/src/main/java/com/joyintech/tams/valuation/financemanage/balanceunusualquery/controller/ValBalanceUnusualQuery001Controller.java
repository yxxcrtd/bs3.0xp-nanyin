/*
 * 文件名：ValBalanceUnusualQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.financemanage.balanceunusualquery.controller;

import java.io.File;
import java.util.Date;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.financemanage.balanceunusualquery.dto.ValBalanceUnusualQuery001Dto;
import com.joyintech.tams.valuation.financemanage.balanceunusualquery.service.ValBalanceUnusualQuery001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 存续产品平仓资产余额异常查询
 * 
 * @author HuangJian
 * @version 1.0 2019年1月31日
 * @see ValBalanceUnusualQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValBalanceUnusualQuery001")
@RequestMapping("/valuation/financemanage/balanceunusualquery/ValBalanceUnusualQuery001")
public class ValBalanceUnusualQuery001Controller extends BaseController {
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private ValBalanceUnusualQuery001Service valBalanceUnusualQuery001Service;

    
    @Autowired
    private BaseCommonService baseCommonService;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String bookDate = baseCommonService.getSysDateString();
        if (bookDate == null || "".equals(bookDate)) {
            bookDate = DateUtil.format(new Date());
        }
        model.addAttribute("bookDate", bookDate);
        return super.init(model);
    }
    
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/findBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetId() {
        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }
    
    
    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public List<ValBalanceUnusualQuery001Dto> findPage()
        throws Exception {
        return valBalanceUnusualQuery001Service.getAllList(RequestUtil.getPageParam());
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
            List<ValBalanceUnusualQuery001Dto> listdto;
            if (datas == null) {
                listdto = valBalanceUnusualQuery001Service.getAllList(RequestUtil.getPageParam());
            }
            else {
                listdto = JsonUtils.json2List(datas, ValBalanceUnusualQuery001Dto.class);
            }
            File outFile = valBalanceUnusualQuery001Service.makeFile(
                "/com/joyintech/tams/valuation/financemanage/balanceunusualquery/conf/export_ValBalanceUnusualQuery001.xml", listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        }
    
    
}
