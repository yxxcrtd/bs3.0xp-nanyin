package com.joyintech.service.basicdata.mstfuturespriceinfo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstfundbonusnotice.dto.MstFundBonusNoticeDto;
import com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;
import com.joyintech.service.basicdata.mstfuturespriceinfo.service.MstFuturesPriceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstFuturesPriceInfoController
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/26 20:23 
 * @version 1.0 
 */
@RestController("MstFuturesPriceInfoCtr_dtx")
@RequestMapping("/MstFuturesPriceInfo")
public class MstFuturesPriceInfoController {

    @Autowired
    MstFuturesPriceInfoService mstFuturesPriceInfoService;


    /**
     *
     * @Description //分页查询
     * @Param [page, length, map]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto>
     * @version 1.0
     * @see
     **/
    @PostMapping("findPage/{page}/{length}")
    public Page<MstFuturesPriceInfoDto> findPage(@RequestBody Map<String,Object> map, @PathVariable("page")int page, @PathVariable("length")int length){
       return mstFuturesPriceInfoService.findPage(page,length,map);
    }

    /**
     *
     * @Description //合约代码下拉框
     * @Param [map]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
     **/
    @PostMapping("/getContractId")
    public List<SelectOptionDto> getContractId(@RequestBody Map<String, Object> map){
        return mstFuturesPriceInfoService.getContractId(map);
    }


    /**
     *
     * @Description //获得Wind代码
     * @Param [map]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
     **/
    @PostMapping("/getWindCode")
    public List<SelectOptionDto> getWindCode( @RequestBody Map<String,Object> map){
        return mstFuturesPriceInfoService.getWindCode(map);
    }

    /**
     *
     * @Description //根据主键查询Dto
     * @Param [map]
     * @return com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto
     * @version 1.0
     * @see
     **/
    @PostMapping("/selectByKey")
    public MstFuturesPriceInfoDto selectByKey( @RequestBody Map<String,Object> map){
        return mstFuturesPriceInfoService.selectByKey(map);
    }



    /**
     *
     * @Description //工作流结束方法
     * @Param [entity]
     * @return void
     * @version 1.0
     * @see
     **/
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody MstFuturesPriceInfoDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        mstFuturesPriceInfoService.doComplete(dto);
    }
}
