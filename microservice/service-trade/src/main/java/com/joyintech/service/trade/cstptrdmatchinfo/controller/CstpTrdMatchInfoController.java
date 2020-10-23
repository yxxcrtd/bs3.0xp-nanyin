package com.joyintech.service.trade.cstptrdmatchinfo.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.cstptrdmatchinfo.dto.CstpTrdMatchInfoDto;
import com.joyintech.service.trade.cstptrdmatchinfo.service.CstpTrdMatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName CstpTrdMatchInfoController
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
@RestController("backCstpTrdMatchInfoController")
@RequestMapping("/CSTPTrdMatchInfo")
public class CstpTrdMatchInfoController {
    @Autowired
    private CstpTrdMatchInfoService cstpTrdMatchInfoService;

    @ResponseBody
    @PostMapping("findPage/{page}/{length}")
    public Page<CstpTrdMatchInfoDto> findPage(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{

        return cstpTrdMatchInfoService.findPage(page, length, map);
    }
}
