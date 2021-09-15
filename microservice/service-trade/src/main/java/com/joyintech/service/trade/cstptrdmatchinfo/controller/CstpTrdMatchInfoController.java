package com.joyintech.service.trade.cstptrdmatchinfo.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.cstptrdmatchinfo.dto.CstpTrdMatchInfoDto;
import com.joyintech.service.trade.cstptrdmatchinfo.service.CstpTrdMatchInfoService;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CstpTrdMatchInfoController
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
@RestController("backCstpTrdMatchInfoController")
@RequestMapping("CSTPTrdMatchInfo")
public class CstpTrdMatchInfoController {
    @Autowired
    private CstpTrdMatchInfoService cstpTrdMatchInfoService;

    @ResponseBody
    @PostMapping("findPage/{page}/{length}")
    public Page<CstpTrdMatchInfoDto> findPage(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        return cstpTrdMatchInfoService.findPage(page, length, map);
    }
    
    @ResponseBody
    @PostMapping("deleteTrdPledgeMatchInfo")
    public boolean deleteTrdPledgeMatchInfo(@RequestBody TrdMatchInfo trdMatchInfo) throws Exception{

        return cstpTrdMatchInfoService.deleteTrdPledgeMatchInfo(trdMatchInfo);
    }

    @ResponseBody
    @PostMapping("getOurdepositaccountnumber")
    public String getOurdepositaccountnumber(@RequestBody Map<String,Object> map) throws Exception{
        return cstpTrdMatchInfoService.getOurdepositaccountnumber(map);
    }

    @ResponseBody
    @PostMapping("getFeeSetting")
    public CstpTrdMatchInfoDto getFeeSetting(@RequestBody Map<String,Object> map) throws Exception{
        return cstpTrdMatchInfoService.getFeeSetting(map);
    }

    @ResponseBody
    @PostMapping("getCstpInfo")
    public List<CstpTrdMatchInfoDto> getCstpInfo(@RequestBody Map<String,Object> map) throws Exception{
        return cstpTrdMatchInfoService.getCstpInfo(map);
    }
}
