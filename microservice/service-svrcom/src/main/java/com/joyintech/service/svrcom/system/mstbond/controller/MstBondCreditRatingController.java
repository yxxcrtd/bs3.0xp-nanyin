package com.joyintech.service.svrcom.system.mstbond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.chbmst.service.ChbMstCounterParty001Service;
import com.joyintech.tams.report.system.mstbond.service.MstBondCreditRating001Service;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/MstBondCreditRating")
public class MstBondCreditRatingController {

    @Autowired
    private MstBondCreditRating001Service mstBondCreditRating001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<MstBondCreditRating> search(@RequestBody Map<String,Object> paramMap) {
        return mstBondCreditRating001Service.searchForPage(paramMap);
    }
    @PostMapping("getoCode")
    public List<ChbAssetInfo> getoCode(@RequestBody Map<String, Object> param) {
        List<ChbAssetInfo> list = mstBondCreditRating001Service.getoCode(param);
        return list;
    }
    @PostMapping("updateMstBondCreditRating")
    public void updateMstBondCreditRating(@RequestBody List<MstBondCreditRating> dtoList) {
        mstBondCreditRating001Service.updateMstBondCreditRating(dtoList);
        return ;
    }

    @PostMapping("queryReportList")
    public List<MstBondCreditRating> queryReportList(@RequestBody Map<String, Object> pageParam) {
        List<MstBondCreditRating> list = mstBondCreditRating001Service.queryReportList(pageParam);
        return list;
    }
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) throws ParseException {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        return mstBondCreditRating001Service.checkConfirmMsgForImportData(dataList);
    }
    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) throws ParseException {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.mstBondCreditRating001Service.importData(dataList,tempList,userName,orgCode,deptCode);
    }

    @PostMapping("selectOneDate")
    public List<MstBondCreditRating> selectOneDate(@RequestBody Map<String, Object> param) {
        List<MstBondCreditRating> list = mstBondCreditRating001Service.selectOneDate(param);
        return list;
    }
    @PostMapping("selectChbMstCounterPartyErrordata")
    public List<ChbMstCounterPartyErrodataWithBLOBs> selectChbMstCounterPartyErrordata(String datauuid) {
        List<ChbMstCounterPartyErrodataWithBLOBs> list = mstBondCreditRating001Service.selectChbMstCounterPartyErrordata(datauuid);
        return list;
    }
    @PostMapping("selectChbMstCounterPartyData")
    public List<Integer> selectChbMstCounterPartyData (@RequestBody List<ChbMstCounterParty> dtoList){
        List<Integer> intList =mstBondCreditRating001Service.selectChbMstCounterPartyData(dtoList);
        return intList;
    }
    @PostMapping("insertMstBondCreditRatingData")
    public void insertMstBondCreditRatingData(@RequestBody List<MstBondCreditRating> dtoList) {
        mstBondCreditRating001Service.insertMstBondCreditRatingData(dtoList);
        return ;
    }
    @PostMapping("updateOne")
    public void updateOne(@RequestBody Map<String, Object> param) {
        mstBondCreditRating001Service.updateOne(param);
        return ;
    }

    @PostMapping("getSysDataDict")
    public List<SysDataDict> getSysDataDict(@RequestBody Map<String, Object> param) {
        List<SysDataDict> list = mstBondCreditRating001Service.getSysDataDict(param);
        return list;
    }





}
