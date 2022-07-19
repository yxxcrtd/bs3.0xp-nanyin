//package com.joyintech.service.disclosure.information.config.hugeinfopro.controller;
//
//
//import com.joyintech.framework.annotation.DTXTransaction;
//import com.joyintech.framework.base.BaseController;
//import com.joyintech.framework.grid.Page;
//import com.joyintech.framework.select.SelectOptionDto;
//import com.joyintech.service.disclosure.information.config.hugeinfopro.dto.HugeInfoProDto;
//import com.joyintech.service.disclosure.information.config.hugeinfopro.service.HugeInfoProService;
//import com.joyintech.service.disclosure.information.config.proelementadj.dto.ProElementAdjDto;
//import com.joyintech.service.disclosure.information.config.proelementadj.service.ProElementAdjService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/HugeInfoPro")
//public class HugeInfoProController extends BaseController {
//
//    @Autowired
//    private HugeInfoProService hugeInfoProService;
//
//    @PostMapping("findPage/{page}/{length}")
//    public Page<HugeInfoProDto> selectAll(@RequestBody Map<String, Object> param,
//                                          @PathVariable("page")int page, @PathVariable("length")int length)
//    {
//        return hugeInfoProService.selectForPage(page,length,param);
//    }
//
//    @PostMapping("/selectByPrimaryKey")
//    public ProElementAdjDto selectByPrimaryKey(@RequestBody String prodRegistNo) {
//        return hugeInfoProService.selectByPrimaryKey(prodRegistNo);
//    }
//
//    @Transactional
//    @DTXTransaction
//    @PostMapping("/doComplete")
//    public void doComplete(@RequestBody ProElementAdjDto dto) {
//        hugeInfoProService.doComplete(dto);
//    }
//
//
//
//    @PostMapping("/selectByProdRegistNo")
//    public String selectByProdRegistNo(@RequestBody Map<String,Object>map) {
//        return hugeInfoProService.selectByProdRegistNo(map);
//    }
//
//
//}
