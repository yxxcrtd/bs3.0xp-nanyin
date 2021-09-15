package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlSubstitutionRepoPledgeDto;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.service.PtlRepoPledgeSearch002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: 质押券置换查询
 * @description: PtlRepoPledgeSearch002Controller
 * @author: nixiukuan
 * @create: 2019-11-12
 **/
@Controller
@RequestMapping("/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/PtlRepoPledgeSearch002")
public class PtlRepoPledgeSearch002Controller extends BaseController {
    /**
     * 质押券置换查询service
     */
    @Autowired
    private PtlRepoPledgeSearch002Service ptlRepoPledgeSearch002Service;

    /**
     * 债券质押查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        return super.init(model);
    }


    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("substitutionFindPage")
    public Page<PtlSubstitutionRepoPledgeDto> selectAll(){
        Map<String,Object> map = RequestUtil.getPageParam();
        Page<PtlSubstitutionRepoPledgeDto> ptlSubstitutionRepoPledgeDtoPage = ptlRepoPledgeSearch002Service.selectForPage(map);
         return ptlSubstitutionRepoPledgeDtoPage;
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck() {
        Page<PtlSubstitutionRepoPledgeDto> result = ptlRepoPledgeSearch002Service.selectForPage(RequestUtil.getPageParam());
//        if (result.getRoot().isEmpty()) {
//            return new AjaxResult(getMessage("PTL_COM_E_0012"), MessageType.错误消息);
//        }
        return new AjaxResult();
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws IOException {
        String datas = this.getParam("datas");
        if(datas==null) {
            Map<String, Object> selectParam = new HashMap<>();
            selectParam.put("pageId", "TrdRepoTrade004");
            return ptlRepoPledgeSearch002Service.export("TrdRepoTrade004",
                    selectParam);
        }
        // 导出所选行
        List<PtlSubstitutionRepoPledgeDto> param1 = JsonUtils.json2List(datas, PtlSubstitutionRepoPledgeDto.class);
        File outFile =  ptlRepoPledgeSearch002Service.makeFile(
                "/com/joyintech/tams/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/conf/export_substitution_conf.xml",
                param1);
        return ExportUtil.export(
                this.getMessage("TrdRepoTrade004") + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(DateUtil.platDateFromCache())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);

    }
}
