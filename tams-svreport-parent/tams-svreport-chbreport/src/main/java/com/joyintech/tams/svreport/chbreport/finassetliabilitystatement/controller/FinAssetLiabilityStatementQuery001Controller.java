package com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery002Dto;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.service.FinAssetLiabilityStatementQueryMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 理财产品资产负债表Controller
 *
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see FinAssetLiabilityStatementQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrFinAssetLiabilityStatementQuery001")
@RequestMapping("/chbreport/finassetliabilitystatement/FinAssetLiabilityStatementQuery001")
public class FinAssetLiabilityStatementQuery001Controller extends BaseController {

    @Autowired
    private FinAssetLiabilityStatementQueryMService finAssetLiabilityStatementQueryMService;

    @Override
    public String init(Model model) {


        return super.init(model);
    }

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(SVReportGlobalConstant.SEARCH_PAGE)
    public Page<FinAssetLiabilityStatementQuery001Dto> selectAllData() throws Exception {
        return finAssetLiabilityStatementQueryMService.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 检索产品代码
     *
     * @return 产品代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrdBaseinfo")
    public List<SelectOptionDto> getPrdBaseinfo() {
        return finAssetLiabilityStatementQueryMService.getPrdBaseinfo(RequestUtil.getPageParam());
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
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        List<FinAssetLiabilityStatementQuery002Dto> list = JsonUtils.json2List(datas,
                FinAssetLiabilityStatementQuery002Dto.class);
        return finAssetLiabilityStatementQueryMService.exportExcel(list);
    }
}
