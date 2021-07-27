package com.joyintech.tams.invest.entrust.etrastconfigtransfer.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.entrust.etrastconfigtransfer.service.EtrAssetConfigTransfer001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 资产配置快速转仓指令
 * 产品信息查询
 * @author 裴家俊
 * @version 2021年7月20日
 * @see EtrAssetConfigTransfer001Controller
 * @since 1.0
 */

@Controller
@PageId("EtrAssetConfigTransfer001")
@RequestMapping("/invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer001")
public class EtrAssetConfigTransfer001Controller extends BaseController {

    /**
     *资产配置快速转仓指令service
     */
    @Autowired
    private EtrAssetConfigTransfer001Service etrAssetConfigTransfer001Service;

    /**
     * 菜单进入
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 产品基本信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlPortfolio> selectForPage(){
        return etrAssetConfigTransfer001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出
     * @return
     * @throws Exception
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return etrAssetConfigTransfer001Service.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
