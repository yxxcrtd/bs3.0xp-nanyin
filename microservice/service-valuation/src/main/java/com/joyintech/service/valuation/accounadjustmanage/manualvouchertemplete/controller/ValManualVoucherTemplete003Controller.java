/*
 * 文件名：ValManualVoucher001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher003CheckValidatorDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003AddDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003SubjectDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete003Service;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 新建-手工凭证模板
 *
 * @author sunshuaishuai
 * @version 2.0 2021年2月9日
 * @see ValManualVoucherTemplete003Controller
 * @since 2.0
 */
@RestController(value="serviceValManualVoucherTemplete003Controller")
@RequestMapping("/${application.service}/ValManualVoucherTemplete003")
public class ValManualVoucherTemplete003Controller extends BaseController {

    @Autowired
    private ValManualVoucherTemplete003Service valManualVoucherTemplete003Service;

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Autowired
    private BokBooksetDateMapper bokBooksetDateMapper;

    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param tmpCode
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getByTmpCode")
    public ValManualVoucherTemplete001Dto getByTmpCode(@RequestBody String tmpCode) {
        return valManualVoucherTemplete003Service.getByTmpCode(tmpCode);
    }

    /**
     * 根据模板编号tmpCode获取对应相同编号的所有数据
     *
     * @return
     * @throws Exception 异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValManualVoucherTemplete003AddDto> searchList(@RequestBody String tmpCode) {
        if (StringUtil.isNotEmpty(tmpCode)) {
            return valManualVoucherTemplete003Service.searchList(tmpCode);
        }
        else {
            return null;
        }
    }

    /**
     * 确认校验
     *
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doCheck")
    public AjaxResult doCheck(@Valid @RequestBody ValManualVoucher003CheckValidatorDto dto,
                              BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 检索科目代码
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getSubjectNo")
    public ValManualVoucherTemplete003SubjectDto getSubjectNo(@RequestBody ValManualVoucherTemplete003SubjectDto dto)
        throws Exception {
        String subjectNo = dto.getSubjectNo();
        return valManualVoucherTemplete003Service.getSubjectNo(subjectNo);
    }

    /**
     *
     * 手工凭证科目号联想
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/querySubctNo")
    public List<SelectOptionDto> querySubctNo(@RequestBody Map<String,Object> para){
        para.put("level3", DictCodeListExt1.科目级别_3级.getValue());
        List<SelectOptionDto> dtolist = valManualVoucherTemplete003Service.querySubctNo(para);
        return dtolist;
    }

    /**
     *
     * 删除
     *
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteVouch")
    public boolean deleteVouch(@RequestBody List<String> tmpIdList){
    	return valManualVoucherTemplete003Service.deleteVouch(tmpIdList);

    }

    @Transactional
    @DTXTransaction
    @PostMapping("doSubmit")
    public boolean doSubmit(@RequestBody ValManualVoucherTemplete003Dto dto){
        return valManualVoucherTemplete003Service.doSubmit(dto);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doUpdate")
    public boolean doUpdate(@RequestBody ValManualVoucherTemplete003Dto dto){
        return valManualVoucherTemplete003Service.update(dto);
    }

    /**
     *
     * 编号查询
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/queryPdSequences")
    public int queryPdSequences(){
        int  count = valManualVoucherTemplete003Service.queryPdSequences();
        return count;
    }

    /**
     *
     * 修改时，模板名称重复校验
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/checkByTmpName")
    public int checkByTmpName(@RequestBody Map<String, Object> map){
        String tmpCode=String.valueOf(map.get("tmpCode"));
        String tmpName=String.valueOf(map.get("tmpName"));
        int  count = valManualVoucherTemplete003Service.checkByTmpName(tmpName,tmpCode);
        return count;
    }

    /**
     *
     * 新建时，模板名称重复校验
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/checkTmpName")
    public int checkTmpName(@RequestBody String tmpName){
        int  count = valManualVoucherTemplete003Service.checkTmpName(tmpName);
        return count;
    }
}
