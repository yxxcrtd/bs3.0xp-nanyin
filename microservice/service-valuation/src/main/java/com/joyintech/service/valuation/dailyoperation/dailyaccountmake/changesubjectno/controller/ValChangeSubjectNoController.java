/*
 * 文件名：ValIncomeStatisticsQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.changesubjectno.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.changesubjectno.service.ValChangeSubjectNoService;
import com.joyintech.tams.common.base.entity.BokSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 科目号变更
 * @author yanya
 * @version 1.0 2020-12-30
 * @see ValChangeSubjectNoController
 * @since 1.0
 */
@RestController(value="serviceValChangeSubjectNoController")
@RequestMapping("/ValChangeSubjectNo")
public class ValChangeSubjectNoController extends BaseController {

    @Autowired
    private ValChangeSubjectNoService valChangeSubjectNoService;

    /**
     * 根据科目号list查询科目数据
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getSubjectList")
    public List<BokSubject> getSubjectList(@RequestBody List<String> list)throws Exception {
        return valChangeSubjectNoService.getSubjectList(list);
    }

    /**
     * 查询所有账套ID
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getBooksetIds")
    public List<String> getBooksetIds(@RequestBody Map<String, Object> map)throws Exception {
        return valChangeSubjectNoService.getBooksetIds(map);
    }


    /**
     * 检索账套代码列表(分页下拉框)
     *
     * @param param
     * @return
     */
    @PostMapping("/findBooksetIdByPage/{page}/{length}")
    public Page<SelectOptionDto> findBooksetIdByPage(@RequestBody Map<String, Object> pageParam,
                                                     @PathVariable("page") int page, @PathVariable("length") int length) {
        return valChangeSubjectNoService.findBooksetIdByPage(page, length, pageParam);
    }

    /**
     * 检索科目号列表(分页下拉框)
     * @param param
     * @return
     */
    @PostMapping("/findSubjectIdByPage/{page}/{length}")
    public Page<SelectOptionDto> findSubjectIdByPage(@RequestBody Map<String, Object> pageParam,
                                                     @PathVariable("page") int page, @PathVariable("length") int length) {
        return valChangeSubjectNoService.getSubjectIdByPage(page, length, pageParam);
    }

    /**
     * 查询核算分类
     * @return
     */
    @ResponseBody
    @RequestMapping("selectDataDictType/{page}/{length}")
    public Page<SelectOptionDto> selectDataDictType(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> dataDictType = valChangeSubjectNoService.selectDataDictType(page, length,param);
        return dataDictType;
    }
}
