package com.joyintech.tams.report.system.mstbond.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MstBondCreditRatingNewService extends BaseService<MstBondCreditRating> {

    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<MstBondCreditRating> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/findPage", paramMap,
                MstBondCreditRating.class);
    }
    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    public List<ChbAssetInfo> getoCode(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/getoCode",param,
                ChbAssetInfo.class);
    }
    public void updateMstBondCreditRating(List<MstBondCreditRating> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/updateMstBondCreditRating",dtoList,
                MstBondCreditRating.class);
        return;
    }


    public List<MstBondCreditRating> queryReportList(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/queryReportList",pageParam,
                MstBondCreditRating.class);
    }
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/checkConfirmMsgForImportData",
                param,
                SvrAjaxResult.class);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("tempList", ExcelUtil.getTempList1(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/importData",
                param,
                ExcelValidDto.class);
    }
    public List<MstBondCreditRating> selectOneDate(Map<String, Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/selectOneDate",map,
                MstBondCreditRating.class);
    }
    public List<ChbMstCounterPartyErrodataWithBLOBs> selectChbMstCounterPartyErrordata(String datauuid) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/selectChbMstCounterPartyErrordata",datauuid,
                ChbMstCounterPartyErrodataWithBLOBs.class);
    }
    public List<Integer> selectChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/selectChbMstCounterPartyData",dtoList,
                Integer.class);
    }
    public void insertMstBondCreditRatingData(List<MstBondCreditRating> dtoList) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/insertMstBondCreditRatingData",dtoList,
                MstBondCreditRating.class);
        return;
    }
    public void updateOne(Map<String, Object> param) {
        restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/updateOne",param,
                MstBondCreditRating.class);
        return;
    }



    public List<SysDataDict> getSysDataDict(Map<String , Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/MstBondCreditRating/getSysDataDict",map,
                SysDataDict.class);
    }



}
