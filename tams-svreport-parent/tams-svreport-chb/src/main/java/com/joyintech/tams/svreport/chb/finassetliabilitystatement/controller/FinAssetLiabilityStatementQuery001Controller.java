package com.joyintech.tams.svreport.chb.finassetliabilitystatement.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery002Dto;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.service.FinAssetLiabilityStatementQuery001Service;
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
@RequestMapping("/chb/finassetliabilitystatement/FinAssetLiabilityStatementQuery001")
public class FinAssetLiabilityStatementQuery001Controller extends BaseController {

    @Autowired
    private FinAssetLiabilityStatementQuery001Service finAssetLiabilityStatementQuery001Service;

    @Override
    public String init(Model model) {


        return super.init(model);
    }

//    @ResponseBody
//    @RequestMapping("check")
//    public AjaxResult check(@RequestBody @Valid CashFlowsStatement001Dto dto, BindingResult result) {
////        if (StringUtils.isEmpty(dto.getReportDimen())) {
////            return new AjaxResult("报表维度不能为空！", MessageType.错误消息);
////        } else {
////            if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(dto.getReportDimen())) {
////                // 账套代码不能为空
////                if (StringUtil.isEmpty(dto.getBooksetId())) {
////                    return new AjaxResult("请输入账套代码。", MessageType.错误消息);
////                }
////                // 账套代码必须存在
////                String[] booksetIds = new String[1];
////                booksetIds[0] = dto.getBooksetId();
////                List<Product> booksetList = booksetCommonService.findProductByIds(booksetIds);
////                if (booksetList == null || booksetList.isEmpty()) {
////                    return new AjaxResult("账套代码不存在。", MessageType.错误消息);
////                }
////            } else {
////                // 账套组不能为空
////                if (StringUtil.isEmpty(dto.getBooksetGroup())) {
////                    return new AjaxResult("请输入账套组。", MessageType.错误消息);
////                }
////            }
////        }
//
//        return commonCheck(result, "");
//    }


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
        return finAssetLiabilityStatementQuery001Service.selectForPage(RequestUtil.getPageParam());
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
        return finAssetLiabilityStatementQuery001Service.getPrdBaseinfo(RequestUtil.getPageParam());
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
        return finAssetLiabilityStatementQuery001Service.exportExcel(list);
    }
}
