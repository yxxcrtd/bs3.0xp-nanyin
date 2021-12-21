/**
 * 银行间成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.cfets.trdredeem.controller;




import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.cfets.trdquery.controller.CfetsTrdQueryController;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdQueryRegInfoListRequest;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdQueryRegInfoListResponse;
import com.joyintech.tams.cfets.trdquery.service.CfetsTrdQueryService;
import com.joyintech.tams.cfets.trdredeem.dto.CfetsTrdRedeemRegInfoListRequest;
import com.joyintech.tams.cfets.trdredeem.dto.CfetsTrdRedeemRegInfoListResponse;
import com.joyintech.tams.cfets.trdredeem.service.CfetsTrdRedeemService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@PageId("CfetsTrdRedeem")
@RequestMapping("/cfets/trdredeem/CfetsTrdRedeem")
@Api(tags = "银行间赎回登记")
public class CfetsTrdRedeemController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsTrdRedeemController.class);

    @Autowired
    private CfetsTrdRedeemService cfetsTrdRedeemService;

    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    @ResponseBody
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        //自动刷新时间
        initPageParams.put("cfetsTrdRedeem_regList_refresh_period", CacheUtil.getSysParam("cfetsTrdRedeem_regList_refresh_period"));

        //数据字典初始化数据
        initPageParams.put("STATUS_VALID", this.getSelectDataFromDataDic(DictCode.有效状态, null));
        initPageParams.put("CCY", this.getSelectDataFromDataDic(DictCode.币种, null));
        initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
        initPageParams.put("PS_T39", DictCodeList.交易方向_行权_赎回.getValue());
        initPageParams.put("NEED_WORKFLOW", true);

        //pageId
        initPageParams.put("pageId", "CfetsTrdRedeem001");
        //当前平台日
        initPageParams.put("platDate", DateUtil.format(DateUtil.platDateFromCache()));
        return new Message<Map<String, Object>>(initPageParams).success();
    }
    @ApiOperation(value = "赎回登记列表分页查询", notes = "赎回登记列表分页查询")
    @PostMapping("/queryAllRedeemRegInfoList")
    @ResponseBody
    public Message<Page<CfetsTrdRedeemRegInfoListResponse>> queryAllRedeemRegInfoList(@RequestBody CfetsTrdRedeemRegInfoListRequest dto)
            throws Exception {
        try {
            Page<CfetsTrdRedeemRegInfoListResponse> page =cfetsTrdRedeemService.queryAllRedeemRegInfoList(dto);
            return new Message<Page<CfetsTrdRedeemRegInfoListResponse>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询赎回登记列表分页数据异常", e);
            return new Message<Page<CfetsTrdRedeemRegInfoListResponse>>().fail().setMessage("查询赎回登记列表分页数据异常");
        }
    }
    @PostMapping(value="/exportQueryAllRedeemRegInfoList")
    @ResponseBody
    @ApiOperation(value = "赎回登记列表导出", notes = "赎回登记列表导出")
    public void exportQueryAllRedeemRegInfoList(@RequestBody CfetsExportRequestDto req, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(req, response, "交易登记列表管理", () -> {
            List dataList = cfetsTrdRedeemService.queryAllRedeemRegInfoList(req.getDto(CfetsTrdRedeemRegInfoListRequest.class)).getRoot();
            return dataList;
        });
    }


}
