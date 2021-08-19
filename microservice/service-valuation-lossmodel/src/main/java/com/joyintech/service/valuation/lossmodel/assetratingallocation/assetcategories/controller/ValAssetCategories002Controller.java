
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto.ValAssetCategories002Dto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.service.ValAssetCategories002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 模板示例<br>
 * 新建账套
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetCategories002Controller
 * @since 1.0
 */
@RestController("valAssetCategories002Ctl")
@RequestMapping("ValAssetCategories002")
public class ValAssetCategories002Controller {

    /**
     * ValAssetBook002Service
     */
    @Autowired
    private ValAssetCategories002Service valAssetCategories002Service;



    /**
     * 通过流水号查询资产大类评级配置信息
     * @param seqNo 流水号
     * @return ValAssetCategories002Dto
     * @see
     * @since 1.0
     */
    @PostMapping("/getAssetCategoriesBySeqNo")
    public ValAssetCategories002Dto getAssetCategoriesBySeqNo(@RequestBody String seqNo) {
       return valAssetCategories002Service.getAssetCategoriesBySeqNo(seqNo);
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
    public int delete(@RequestBody ValAssetCategories002Dto dto) throws Exception {
        return valAssetCategories002Service.delete(dto);
    }

    /**
     * 修改
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody ValAssetCategories002Dto dto) throws Exception {
        valAssetCategories002Service.update(dto);
    }

    /**
     * 新增
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody ValAssetCategories002Dto dto) throws Exception {
        valAssetCategories002Service.insert(dto);
    }

    @PostMapping("/checkAssetCategories")
    public boolean checkAssetCategories(@RequestBody ValAssetCategories002Dto dto) {
        return valAssetCategories002Service.check(dto);
    }
}
