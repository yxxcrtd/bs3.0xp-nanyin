package com.joyintech.tams.invest.risk.outsvaltableimporthis.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;

import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.outsvaltableimporthis.dto.RskOutValtblImportHis001Dto;
import com.joyintech.tams.invest.risk.outsvaltableimporthis.service.RskOutValtblImportHis001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 *  估值表读取处理监控
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2021年5月22日
 * @see RskOutValtblImportHis001Controller
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/risk/outsvaltableimporthis/RskOutValtblImportHis001")
@PageId("RskOutValtblImportHis001")
public class RskOutValtblImportHis001Controller extends BaseController {

    @Autowired
    private RskOutValtblImportHis001Service RskOutValtblImportHis001Service;

    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.估值表自动解析处理状态};

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DICT_CODES);
        String sysDate = DateUtil.format(DateUtil.platDateFromCache());
        model.addAttribute("quaryDate", DateUtil.addDay(sysDate,0)+" - "+DateUtil.addDay(sysDate,0));
        return super.init(model);
    }


    /**
     * 画面查询方法 <功能详细描述>
     *
     * @return 所有数据
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskOutValtblImportHis001Dto> selectAll(){
        return RskOutValtblImportHis001Service.selectPageData(RequestUtil.getParameters(getRequest()));
    }

    /**
     * 运行
     * @param dto RskValtblAssetSubDto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("run")
    public AjaxResult finalSave(@RequestBody RskOutValtblImportHis dto, BindingResult br){
        AjaxResult result = new AjaxResult();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        RskOutValtblImportHis returnDto = RskOutValtblImportHis001Service.runExecute(dto);
        if(StringUtil.equals(returnDto.getImportType(),DictCodeListExt1.估值表自动解析处理状态_正常结束.getValue())
           && !StringUtil.isNotEmpty(returnDto.getErrorCode())){
            paramMap.put("resultCode", returnDto.getImportType());
            paramMap.put("resultMsg", "文件解析成功");
            result.setData(paramMap);
        }else{
            String errorDara = SysDataUtil.getNameFromDataDict(DictCode.估值表自动解析错误码, returnDto.getErrorCode());
            paramMap.put("resultCode", returnDto.getImportType());
            paramMap.put("resultMsg", errorDara);
            result.setData(paramMap);
        }
        return result;
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(){
        return RskOutValtblImportHis001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
