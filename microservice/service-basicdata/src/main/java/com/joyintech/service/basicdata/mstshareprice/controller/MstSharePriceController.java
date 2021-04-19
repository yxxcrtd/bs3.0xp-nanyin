/*
 * 文件名：MstSharePriceController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangpeng 修改时间：2019年6月28日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstshareprice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.mstshareprice.dto.MstSharePriceDto;
import com.joyintech.service.basicdata.mstshareprice.service.MstSharePriceQuotationInfoImportDataService;
import com.joyintech.service.basicdata.mstshareprice.service.MstSharePriceService;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

/**
 * 股票行情信息 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年6月20日
 * @see MstSharePriceController
 * @since 1.0
 */
@RestController("MstSharePriceCtr_dtx")
@RequestMapping("/MstSharePrice")
public class MstSharePriceController {

    /**
     * 股票行情信息service
     */
    @Autowired
    private MstSharePriceService mstSharePriceService;
    
    @Autowired
    private MstSharePriceQuotationInfoImportDataService mstSharePriceQuotationInfoImportDataService;

    /**
     * 查询 支持分页 <功能详细描述>
     * 
     * @param pageParam Map<String, Object> sharesId dealDateString
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<MstSharePriceDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        Page<MstSharePriceDto> page1 = mstSharePriceService.selectForPage(page, length, pageParam);
        return page1;
    }

    /**
     * 获取指定日期最近的股票信息
     * 
     * @param pageParam dealdateString sharesId
     * @return
     * @throws Exception
     */
    @RequestMapping("/getLatelyShareById")
    public MstSharePriceDto getLatelyShareById(@RequestBody Map<String, Object> pageParam)
        throws Exception {
        MstSharePriceDto page = mstSharePriceService.getLatelyShareById(pageParam);
        return page;
    }

    /**
     * 获取所有的数据 <功能详细描述>
     * 
     * @param pageParam Map<String, Object>
     * @return List<MstSharePriceDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAllList")
    public List<MstSharePriceDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return mstSharePriceService.selectAllList(pageParam);
    }

    /**
     * 回调函数 操作具体 new delete update <功能详细描述>
     * 
     * @param pageParam Map<String, Object> dto type
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody MstSharePriceDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        mstSharePriceService.doComplete(dto);
    }

    /**
     * 通过资产代码或者资产名称等信息
     * 
     * @param map
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("getFinprodIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getFinprodIdForAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = mstSharePriceService.getFinprodIdForAdd(page, length, map);
        return temp;
    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入的文件名
     * @return 处理结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return mstSharePriceQuotationInfoImportDataService.checkConfirmMsgForImportData(dataList);
    }


    /**
     * 导入数据文件
     *
     * @param param 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        List<List<String>> dataList = (List<List<String>>) param.get("dataList");
        List<List<String>> tempList = (List<List<String>>) param.get("tempList");
        String userName=param.get("userName")==null?null:param.get("userName").toString();
        String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
        String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
        return this.mstSharePriceQuotationInfoImportDataService.importData(dataList,tempList,userName,orgCode,deptCode);
    }
}
