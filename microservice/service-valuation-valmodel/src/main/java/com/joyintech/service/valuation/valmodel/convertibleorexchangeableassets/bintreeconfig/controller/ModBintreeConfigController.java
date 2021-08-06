
package com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.controller;

import java.util.Map;

import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service.ModBintreeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;

/**
 * 二叉树模型参数配置
 *
 * @author yuluan
 * @version 1.0 2021年07月01日
 * @see ModBintreeConfigController
 * @since 1.0
 */
@RestController
@RequestMapping("/ModBintreeConfig")
public class ModBintreeConfigController {

    @Autowired
    private ModBintreeConfigService modBintreeConfigService;


    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ModBintreeConfigDto> selectForPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return modBintreeConfigService.selectForPage(page, length, param);
    }
    @PostMapping("check")
    public String check(@RequestBody Map<String,Object> map) {
    	return modBintreeConfigService.check(map);
    }
    @PostMapping("queryDtoBySeqno")
    public ModBintreeConfigDto queryDtoBySeqno(@RequestBody Map<String, Object> param) {
    	String seqno=param.get("seqno").toString();
    	return modBintreeConfigService.queryDtoBySeqno(seqno);
    }
    /**
     * 新增
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody ModBintreeConfigDto dto) throws Exception {
    	modBintreeConfigService.insert(dto);
    }
    /**
     * 修改
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody ModBintreeConfigDto dto) throws Exception {
    	modBintreeConfigService.update(dto);
    }

    /**
     * 删除
     *
     * @param dto 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ModBintreeConfigDto dto) throws Exception {
        return modBintreeConfigService.delete(dto);
    }
    @Transactional
    @DTXTransaction
    @PostMapping("initBintreeConfig")
    public int initBintreeConfig(@RequestBody Map<String,String> map){
    	return modBintreeConfigService.initBintreeConfig(map);
    }

}
