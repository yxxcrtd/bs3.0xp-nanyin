package com.joyintech.service.basicdata.mstsharebonusnotice.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;
import com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto;
import com.joyintech.service.basicdata.mstsharebonusnotice.service.MstShareBonusNoticeService;
import com.joyintech.service.basicdata.mstshareprice.dto.MstSharePriceDto;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstShareBonusNoticeController
 * @Description 股票分红公告
 * @author zhuhanlu 
 * @date 2020/8/6 16:52 
 * @version 1.0 
 */
@RestController("MstShareBonusNoticeCtr_dtx")
@RequestMapping("/MstShareBonusNotice")
public class MstShareBonusNoticeController {

    @Autowired
    private MstShareBonusNoticeService mstShareBonusNoticeService;

    //资产代码下拉框
    @PostMapping("/getShareId")
    public List<SelectOptionDto> getShareId(@RequestBody Map<String, Object> map) throws Exception{
        return mstShareBonusNoticeService.getShareId(map);
    }


    /**
     *
     * @Description 分页查询
     * @Param [param, page, length]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto>
     * @version 1.0
     * @see
    **/
    @PostMapping("findPage/{page}/{length}")
    public Page<MstShareBounsNoticeDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page")int page, @PathVariable("length")int length)
            throws Exception {
        return mstShareBonusNoticeService.selectForPage(page,length,param);
    }

    /**
     *
     * @Description 回调函数。new delete update
     * @Param [dto, type]
     * @return void
     * @version 1.0
     * @see
    **/
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete/{type}")
    public void doComplete(@RequestBody MstShareBounsNoticeDto dto, @PathVariable("type") String type) {
        dto.setOperateType(type);
        mstShareBonusNoticeService.doComplete(dto);
    }

    /**
     *
     * @Description //根据shareId和regDate查数据
     * @Param [pageParam]
     * @return java.util.List<com.joyintech.service.basicdata.mstshareprice.dto.MstSharePriceDto>
     * @version 1.0
     * @see
    **/
    @PostMapping("/selectAllList")
    public List<MstShareBounsNoticeDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return mstShareBonusNoticeService.selectAllList(pageParam);
    }


}
