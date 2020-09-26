package com.joyintech.tams.valuation.basedata.marketdata.externaloptions.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.basedata.marketdata.externaloptions.dto.ExternalOptions001Dto;
import com.joyintech.tams.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2020- 01-06 16:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExternalOptions001Service extends CommonExportService<ExternalOptions001Dto> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    public Page<ExternalOptions001Dto> queryPageList(Map<String, Object> params){
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);

        params.put("BENCHMARK_TYPE2",str);
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/findPage",
                params, ExternalOptions001Dto.class);
    }


    public List<SuggestDto> findTargetId(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/findTargetId",
                params, SuggestDto.class);
    }

    public List<SuggestDto> findFinprodId(Map<String, Object> params) {

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/findFinprodId",
                params, SuggestDto.class);
    }

    /**
     * 查询所有
     * @param params
     * @return
     */
    public List<ExternalOptions001Dto> queryAllList(Map<String, Object> params){
        String str = DictCodeListExt3.基准类型_2_合约标的.getValue(); //07
        //System.out.println(str);

        params.put("BENCHMARK_TYPE2",str);
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/queryAllList",
                params, ExternalOptions001Dto.class);
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/checkConfirmMsgForImportData",
                param,
                InvAjaxResult.class);
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/ExternalOptions/importData",
                param,
                ExcelValidDto.class);
    }
}
