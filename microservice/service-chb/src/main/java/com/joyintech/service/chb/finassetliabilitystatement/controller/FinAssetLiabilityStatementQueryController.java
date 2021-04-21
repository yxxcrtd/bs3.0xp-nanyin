package com.joyintech.service.chb.finassetliabilitystatement.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery004Dto;
import com.joyintech.tams.svreport.chb.finassetliabilitystatement.service.FinAssetLiabilityStatementQuery001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 理财产品资产负债表查询服务
 *
 * @author WanShiwei
 * @version 2.1.6 2021年04月20日
 * @see
 * @since 2.1.6
 */
@RestController("FinAssetLiabilityStatementQueryCtr_dtx")
@RequestMapping("/FinAssetLiabilityStatementQuery")
public class FinAssetLiabilityStatementQueryController {

    /**
     * service
     */
    @Autowired
    private FinAssetLiabilityStatementQuery001Service finAssetLiabilityStatementQuery001Service;

    /**
     * 产品代码下拉框取得值
     *
     * @param map
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @PostMapping("getPrdBaseinfo")
    public List<SelectOptionDto> getPrdBaseinfo(@RequestBody Map<String, Object> map) {
        return finAssetLiabilityStatementQuery001Service.getPrdBaseinfo(map);
    }

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinAssetLiabilityStatementQuery001Dto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetLiabilityStatementQuery001Service.selectForPage(param);
    }

    /**
     * 查询导出数据-资产
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectAssetList")
    public List<FinAssetLiabilityStatementQuery004Dto> selectAssetList(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetLiabilityStatementQuery001Service.selectAssetList(param);
    }

    /**
     * 查询导出数据-负债，所有者权益
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectLiabilitiesAndOwnerEquityList")
    public List<FinAssetLiabilityStatementQuery004Dto> selectLiabilitiesAndOwnerEquityList(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetLiabilityStatementQuery001Service.selectLiabilitiesAndOwnerEquityList(param);
    }

    /**
     * 查询表头数据
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectTitle")
    public List<FinAssetLiabilityStatementQuery004Dto> selectTitle(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetLiabilityStatementQuery001Service.selectTitle(param);
    }

//    /**
//     * 业务画面查询事件
//     *
//     * @return
//     * @see
//     * @since 1.0
//     */
//    @PostMapping("searchPageBysubmitDate")
//    public ChbMonthReport searchPageBysubmitDate(@RequestBody Map<String, Object> param) {
//        return chbMonthReport001Service.searchPageBysubmitDate(param);
//    }
//
//    /**
//     * 手工处理功能
//     *
//     * @return 提示信息
//     * @param map 条件map
//     * @seea
//     * @since 1.0
//     */
//    @Transactional
//    @DTXTransaction
//    @PostMapping("updateSumStaus")
//    public void updateSumStaus(@RequestBody Map<String, Object> map) {
//        chbMonthReport001Service.updateSumStaus(map);
//    }
//
//    // @PostMapping("doAseetBatch")
//    // public void doAssetbatch(@RequestBody Map<String, Object> map) {
//    // chbMonthReport001Service.doAseetBatch(map);
//    // }
//    @Transactional
//    @DTXTransaction
//    @PostMapping("recalReport")
//    public String recalReport(@RequestBody Map<String, Object> map) {
//        String message = chbMonthReport001Service.recalReport(map);
//        return message;
//    }
//
//    /**
//     * 取消手工处理功能
//     *
//     * @return 提示信息
//     * @param map 条件map
//     * @seea
//     * @since 1.0
//     */
//    @Transactional
//    @DTXTransaction
//    @PostMapping("cancelMannul")
//    public void cancelMannul(@RequestBody Map<String, Object> map) {
//        chbMonthReport001Service.cancelMannul(map);
//    }
//
//    @Transactional
//    @DTXTransaction
//    @PostMapping("createData")
//    public Map<Object, Object> createData(@RequestBody Map<String, Object> selectParam) {
//        return chbMonthReport001Service.createData(selectParam);
//    }
//
//    /**
//     * 报送检查
//     *
//     * @param map 查询条件
//     * @return 检查结果
//     * @see
//     * @since 1.0
//     */
//    @PostMapping("reportCheck")
//    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
//        List<String> keyList = new ArrayList<String>();
//        Map<String, Object> param = map;
//        if (!param.containsKey("checkKeys")) {
//            keyList = chbMonthReport001Service.getCheckRepList(param);
//        }
//        else {
//            keyList = StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
//        }
//        if (CollectionUtils.isEmpty(keyList)) {
//            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
//            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据", null));
//            return checkResult;
//        }
//        return chbMonthReport001Service.checkData(keyList);
//    }
}
