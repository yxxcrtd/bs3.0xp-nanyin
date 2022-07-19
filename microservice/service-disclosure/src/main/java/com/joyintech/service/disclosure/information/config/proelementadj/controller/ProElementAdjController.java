//package com.joyintech.service.disclosure.information.config.proelementadj.controller;
//
//
//import com.joyintech.framework.annotation.DTXTransaction;
//import com.joyintech.framework.base.BaseController;
//import com.joyintech.framework.grid.Page;
//import com.joyintech.framework.select.SelectOptionDto;
//import com.joyintech.service.disclosure.information.config.proelementadj.dto.ProElementAdjDto;
//import com.joyintech.service.disclosure.information.config.proelementadj.service.ProElementAdjService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/ProElementAdj")
//public class ProElementAdjController extends BaseController {
//
//    @Autowired
//    private ProElementAdjService proElementAdjService;
//
//    @PostMapping("findPage/{page}/{length}")
//    public Page<ProElementAdjDto> selectAll(@RequestBody Map<String, Object> param,
//                                            @PathVariable("page")int page, @PathVariable("length")int length)
//    {
//        return proElementAdjService.selectForPage(page,length,param);
//    }
//
//    @PostMapping("/selectByPrimaryKey")
//    public ProElementAdjDto selectByPrimaryKey(@RequestBody String prodRegistNo) {
//        return proElementAdjService.selectByPrimaryKey(prodRegistNo);
//    }
//
//    @Transactional
//    @DTXTransaction
//    @PostMapping("/doComplete")
//    public void doComplete(@RequestBody ProElementAdjDto dto) {
//        proElementAdjService.doComplete(dto);
//    }
//
//
//
//    @PostMapping("getRegistNo/{page}/{length}")
//    @ResponseBody
//    public Page<SelectOptionDto> getRegistNo(@RequestBody Map<String, String> map, @PathVariable("page") int page,
//                                                    @PathVariable("length") int length)
//            throws Exception {
//        Page<SelectOptionDto> temp = proElementAdjService.getRegistNo(page, length, map);
//        return temp;
//    }
//
//
//    @PostMapping("/selectByProdRegistNo")
//    public String selectByProdRegistNo(@RequestBody Map<String,Object>map) {
//        return proElementAdjService.selectByProdRegistNo(map);
//    }
//
//    @PostMapping("getProdId/{page}/{length}")
//    @ResponseBody
//    public Page<SelectOptionDto> getProdId(@RequestBody Map<String, String> map, @PathVariable("page") int page,
//                                             @PathVariable("length") int length)
//            throws Exception {
//        Page<SelectOptionDto> temp = proElementAdjService.getProdId(page, length, map);
//        return temp;
//    }
//}
