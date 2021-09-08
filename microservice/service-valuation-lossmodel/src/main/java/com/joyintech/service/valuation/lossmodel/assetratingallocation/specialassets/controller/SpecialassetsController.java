
package com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetInfoDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetSideDetialRepoDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.service.SpecialassetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;

/**
 * 额度标签管理
 *
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see SpecialassetsController
 * @since 1.0
 */
@RestController
@RequestMapping("/Specialassets")
public class SpecialassetsController {

    @Autowired
    private SpecialassetsService specialassetsService;


    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ModAssetGradeSpecialConfigDto> selectForPage(@RequestBody Map<String, Object> param,
                                                             @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return specialassetsService.selectForPage(page, length, param);
    }
    @PostMapping("/selectForExport")
    public List<ModAssetGradeSpecialConfigDto> selectForExport(@RequestBody Map<String, Object> param) throws Exception {
        return specialassetsService.selectForExport(param);
    }
    @PostMapping("check")
    public String check(@RequestBody Map<String,Object> map) {
    	return specialassetsService.check(map);
    }
    @PostMapping("queryDtoBySeqno")
    public ModAssetGradeSpecialConfigDto queryDtoBySeqno(@RequestBody Map<String, Object> param) {
    	String seqno=param.get("seqno").toString();
    	return specialassetsService.queryDtoBySeqno(seqno);
    }
    /**
     * 新增
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody ModAssetGradeSpecialConfigDto dto) throws Exception {
    	specialassetsService.insert(dto);
    }
    /**
     * 修改
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody ModAssetGradeSpecialConfigDto dto) throws Exception {
    	specialassetsService.update(dto);
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
    public int delete(@RequestBody ModAssetGradeSpecialConfigDto dto) throws Exception {
        return specialassetsService.delete(dto);
    }


    /**
     * 资产代码：下拉框
     *
     * @param param 画面参数
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodIdForImpairment")
    public List<SelectOptionDto> getFinprodIdForImpairment(@RequestBody Map<String, Object> param) throws Exception {
        return specialassetsService.getFinprodIdForImpairment( param);
    }


    /**
     *
     * 查询有分类的资产代码减值用 只查债券，项目类的新增和非标 不可传资产分类
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssetForImpairment")
    @ResponseBody
    public List<AssetInfoDto> getAssetForImpairment(@RequestBody AssetInfoDto assetInfoDto){
        return specialassetsService.getAssetForImpairment(assetInfoDto);
    }

    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepAssetCode")
    public List<SelectOptionDto> getRepAssetCode(@RequestBody Map<String,Object> map){
        return specialassetsService.getRepAssetCode(map);
    }


    @PostMapping("findAll")
    public List<AssetSideDetialRepoDto> findAll(@RequestBody Map<String, Object> param) throws Exception {
        return specialassetsService.findAll(param);
    }

    @PostMapping("getSecTypeByFinprodId")
    public String getSecTypeByFinprodId(@RequestBody Map<String, Object> param) throws Exception {
        return specialassetsService.getSecTypeByFinprodId(param);
    }

    /**
     * @Description:根据资产代码查询条数
     * @auther: yuzj
     * @date: 2021/9/8
     * @param:
     * @return:
     */
    @PostMapping("getSpecialCountByFinprodId")
    public Integer getSpecialCountByFinprodId(@RequestBody ModAssetGradeSpecialConfigDto dto) throws Exception {
        return specialassetsService.getSpecialCountByFinprodId(dto);
    }





}
