
package com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import com.joyintech.service.valuation.lossmodel.lossgivendefaultmaintain.service.LossGivenDefaultMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;

/**
 * 额度标签管理
 *
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see LossGivenDefaultMaintainController
 * @since 1.0
 */
@RestController
@RequestMapping("/LossGivenDefaultMaintain")
public class LossGivenDefaultMaintainController {

    @Autowired
    private LossGivenDefaultMaintainService lossGivenDefaultMaintainService;


    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ModLgdConfigDto> selectForPage(@RequestBody Map<String, Object> param,
                                               @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return lossGivenDefaultMaintainService.selectForPage(page, length, param);
    }
    @PostMapping("/selectForExport")
    public List<ModLgdConfigDto> selectForExport(@RequestBody Map<String, Object> param) throws Exception {
        return lossGivenDefaultMaintainService.selectForExport(param);
    }
//    @PostMapping("check")
//    public String check(@RequestBody Map<String,Object> map) {
//    	return lossGivenDefaultMaintainService.check(map);
//    }
    @PostMapping("queryDtoBySeqno")
    public ModLgdConfigDto queryDtoBySeqno(@RequestBody Map<String, Object> param) {
    	String seqno=param.get("seqno").toString();
    	return lossGivenDefaultMaintainService.queryDtoBySeqno(seqno);
    }
    @PostMapping("checkIfUsed")
    public String checkIfUsed(@RequestBody Map<String, Object> param) {
    	String seqno=param.get("seqno").toString();
    	return lossGivenDefaultMaintainService.checkIfUsed(seqno);
    }
    /**
     * 新增
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody ModLgdConfigDto dto) throws Exception {
    	lossGivenDefaultMaintainService.insert(dto);
    }
    /**
     * 修改
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody ModLgdConfigDto dto) throws Exception {
    	lossGivenDefaultMaintainService.update(dto);
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
    public int delete(@RequestBody ModLgdConfigDto dto) throws Exception {
        return lossGivenDefaultMaintainService.delete(dto);
    }


    /**
     * 联动：证券品种 - 交易流通场所 获得交易市场
     *
     * @return
     * @throws Exception
     */
    @PostMapping("getLinkSelectOption")
    public List<SelectOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param) {
        return lossGivenDefaultMaintainService.getLinkSelectOption(param);
    }

}
