
package com.joyintech.service.valuation.basicdata.mstcurvedefmanage.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.service.valuation.basicdata.mstcurvedefmanage.service.MstCurveDefManageService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 收益率曲线管理
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveDefManageController
 * @since 1.0
 */
@RestController("backMstCurveDefManageController")
@RequestMapping("/service/MstCurveDefManage")
public class MstCurveDefManageController {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private MstCurveDefManageService mstCurveDefManageService;
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
    public Page<MstCurveDefManageDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        pageParam.put("curve_type", DictCodeListExt2.曲线类别_收益率曲线.getValue());
        return mstCurveDefManageService.selectForPage(page,length,pageParam);
    }

    /**
     * 收益率曲线下拉框代码查询
     */
    @RequestMapping("/getCurveCode/{page}/{length}")
    public Page<SelectOptionDto> getCurveCode(@RequestBody  Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length){
        param.put("curve_type", DictCodeListExt2.曲线类别_收益率曲线.getValue());
        return  mstCurveDefManageService.getCurveCode(page, length,param);
    }

    /**
     * 更新收益率曲线状态
     */
    @RequestMapping("updateEffStatus")
    public void updateEffStatus(@RequestBody  MstCurveDefManageDto dto){
        mstCurveDefManageService.updateEffStatus(dto);
    }

    /**
     * 查询收益率曲线定义是否存在
     */
    @RequestMapping("getCurveDefCount")
    public  List<MstCurveDefManageDto> getCurveDefCount(@RequestBody  MstCurveDefManageDto dto){
        return  mstCurveDefManageService.getCurveDefCount(dto);
    }

    /**
     * 查询收益率曲线详情
     */
    @RequestMapping("selectByKey")
    public MstCurveDefManageDto selectByKey(@RequestBody Map<String,Object> param){
        return  mstCurveDefManageService.selectByKey(param);
    }
}
