
package com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.service.MstCurveGroupManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:收益率曲线族Controller
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@RestController("backMstCurveGroupManageController")
@RequestMapping("/service/MstCurveGroupManage")
public class MstCurveGroupManageController {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private MstCurveGroupManageService mstCurveGroupManageService;
    /**
     *
     * 查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<MstCurveGroupManageDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstCurveGroupManageService.selectForPage(page,length,pageParam);
    }


    /**
     * 查询收益率曲线详情
     */
    @RequestMapping("selectByKey")
    public MstCurveGroupManageDto selectByKey(@RequestBody Map<String,Object> param){
        return  mstCurveGroupManageService.selectByKey(param);
    }


    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstCurveGroupManageDto dto) throws Exception {
        return mstCurveGroupManageService.insert(dto);
    }

    /**
     * 更新数据
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstCurveGroupManageDto dto) throws Exception {
        return mstCurveGroupManageService.update(dto);
    }

    /**
     *
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstCurveGroupManageDto dto) throws Exception {
        return mstCurveGroupManageService.delete(dto);
    }



}
