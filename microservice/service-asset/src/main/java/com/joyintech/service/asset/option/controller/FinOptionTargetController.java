package com.joyintech.service.asset.option.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-19
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.option.dto.FinOptionTargetDto;
import com.joyintech.service.asset.option.service.FinProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("FinOptionTargetController_dtx")
@RequestMapping("/FinOptionTarget")
public class FinOptionTargetController {
    @Autowired
    private  FinProdService finProdService;
    /**
     * 获取标的代码
     * @param getFinprodcodeList
     * @return
     */
    @GetMapping("getFinprodcodeList")
    public List<SuggestDto>  getFinprodcodeList(String  getFinprodcodeList) {
        return finProdService.getFinprodcodeList(getFinprodcodeList);
    }

    /**
     * 分页查询标的信息
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FinOptionTargetDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finProdService.selectForPage(page, length, param);
    }

    /**
     * 根据id获取期权标的信息
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping("getFinprodcodebyId")
    public FinOptionTargetDto getFinprodcodebyId(String code)
        throws Exception {
        return finProdService.getFinprodcodebyId(code);
    }

    /**
     * 保存标的信息
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int save(@RequestBody FinOptionTargetDto dto ){
        return finProdService.save(dto);
    }

    /**
     * 更新标的信息
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody FinOptionTargetDto dto ){
        return finProdService.update(dto);
    }

    /**
     * 删除标的信息
     * @param dto
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody FinOptionTargetDto dto ){
        return finProdService.delete(dto);
    }
}
