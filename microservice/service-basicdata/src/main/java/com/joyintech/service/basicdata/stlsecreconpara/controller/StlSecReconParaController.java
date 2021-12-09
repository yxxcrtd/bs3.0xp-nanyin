package com.joyintech.service.basicdata.stlsecreconpara.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.stlsecreconpara.dto.StlSecReconParaDto;
import com.joyintech.service.basicdata.stlsecreconpara.service.StlSecReconParaService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对账参数设置-微服务接口
 */
@RestController
@RequestMapping("/StlSecReconPara")
public class StlSecReconParaController {

    /**
     * 对账参数设置-service
     */
    @Autowired
    private StlSecReconParaService stlSecReconParaService;

    /**
     * 分页查询数据
     * @param pageParam 页面参数
     * @param page 第多少页
     * @param length 页的大小
     * @return 分页数据
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<StlSecReconParaDto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                  @PathVariable("page") int page, @PathVariable("length") int length) {
        return stlSecReconParaService.selectForPage(pageParam, page, length);
    }

    /**
     * 查询所有券商ID的下拉列表
     * @return
     */
    @PostMapping("findAllBrokerIds")
    public List<SuggestDto> findAllBrokerIds(@RequestBody Map<String, Object> param) {
        param.put("isBroker", DictCodeList.是否_是.getValue());
        return stlSecReconParaService.findAllBrokerIds(param);
    }

    /**
     * 查询所有券商ID的下拉列表
     * @return
     */
    @PostMapping("findAllBrokerIdsSelect")
    public List<SelectOptionDto> findAllBrokerIdsSelect(@RequestBody Map<String, Object> param) {
        param.put("isBroker", DictCodeList.是否_是.getValue());
        return stlSecReconParaService.findAllBrokerIdsSelect(param);
    }


    /**
     * 通过主键查出实体
     * @param param
     * @return
     */
    @PostMapping("findEntityByPrimarykey")
    public StlSecReconParaDto findEntityByPrimarykey(@RequestBody Map<String, Object> param) {
        String seqNo = (String)param.get("seqNo");
        return stlSecReconParaService.findEntityByPrimarykey(seqNo);
    }

    /**
     * 插入一条记录
     * @param entity
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public Integer insert(@RequestBody StlSecReconParaDto entity) {
        return stlSecReconParaService.insert(entity);
    }

    /**
     * 删除一条记录
     * @param entity
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public Integer delete(@RequestBody StlSecReconParaDto entity) {
        return stlSecReconParaService.delete(entity);
    }

    /**
     * 更新一条记录
     * @param entity
     * @return
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public Integer update(@RequestBody StlSecReconParaDto entity) {
        return stlSecReconParaService.update(entity);
    }


    @PostMapping("selectAllList")
    public List<StlSecReconParaDto> selectAllList(@RequestBody Map<String, Object> params) {
        return stlSecReconParaService.selectAllList(params);
    }

    /**
     * 检查dto 是否已存在
     * @return
     */
    @PostMapping("checkParaIsExist")
    public Boolean checkParaIsExist(@RequestBody StlSecReconParaDto dto) {
        return stlSecReconParaService.checkParaIsExist(dto);
    }
}
