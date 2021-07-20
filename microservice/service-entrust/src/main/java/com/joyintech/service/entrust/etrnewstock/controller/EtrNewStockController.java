package com.joyintech.service.entrust.etrnewstock.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.service.entrust.common.service.WFlowCommonService;
import com.joyintech.service.entrust.etrnewstock.dto.EtrNewStockDto;
import com.joyintech.service.entrust.etrnewstock.service.EtrNewStockService;
import com.joyintech.service.entrust.etrstock.dto.EtrStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;


/**   
 * @title: EtrStockController
 * @package com.joyintech.service.entrust.etrstock.controller
 * @description: 股票委托微服务
 * @author: junYin
 * @date: 2019-06-04 10:51
*/
@RestController("EtrNewStockCtr_dtx")
@RequestMapping("EtrNewStock")
public class EtrNewStockController {

    /**
     * 股票委托微服务service
     */
    @Autowired
    private EtrNewStockService etrNewStockService;

    /**
     *@title: selectForPage
     *@description: 股票委托分页查询
     *@author: junYin
     *@date: 2019/06/04 10:57
     *@param: * @param param 1
     * @param page 2
     * @param length 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.service.entrust.etrstock.dto.EtrNewStockDto>
     *@throws:
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrNewStockDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrNewStockService.selectForPage(page,length,param);
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecAcctId")
    public List<SuggestDto> getSecAcctId(@RequestBody Map<String, Object> param) {
        return etrNewStockService.getSecAcctId(param);
    }

    /**
     *@title: getentrustId
     *@description: 获取组合代码联想文本框信息
     * 支持组合代码或组合名称进行模糊查询
     *@author: junYin
     *@date: 2019/06/04 11:02
     *@param: * @param map 1
     *@return: java.util.List<com.joyintech.framework.select.SuggestDto>
     *@throws:
     */
    @PostMapping("getentrustId")
    public List<SuggestDto> getentrustId(@RequestBody Map<String, Object> map) throws Exception {
        List<SuggestDto> temp = etrNewStockService.getentrustId(map);
        return temp;
    }
    /**
     *@title: getfinprodId
     *@description: 获取资产代码联想文本框信息
     *@author: junYin
     *@date: 2019/06/04 11:03
     *@param: * @param finprodId 1
     *@return: java.util.List<com.joyintech.framework.select.SuggestDto>
     *@throws:
     */
    @GetMapping("getfinprodId")
    public List<SuggestDto> getfinprodId(String finprodId) throws Exception {
        List<SuggestDto> temp = etrNewStockService.getfinprodId(finprodId);
        return temp;
    }
    /**
     *@title: getSelectTongDao
     *@description: 获取新建界面的通道联想下拉框
     *@author: daizhiwei
     *@date: 2019/07/03 11:03
     *@param: * @param map 1
     * @param page 2
     * @param length 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @PostMapping("getSelectTongDao/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getSelectTongDao(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        Page<SelectOptionDto> temp = etrNewStockService.getSelectTongDao(page,length,map);
        return temp;
    }
    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    @PostMapping("/getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String, Object> map){
        BigDecimal big = etrNewStockService.getTongDaoCash(map);
        if(big!=null){
            return big;
        }
        return BigDecimal.ZERO;
    }

    /**
     *@title: getfinprodIdForAdd
     *@description: 获取新建界面的资产代码联想下拉框
     *@author: junYin
     *@date: 2019/06/04 11:03
     *@param: * @param map 1
     * @param page 2
     * @param length 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = etrNewStockService.getfinprodIdForAdd(page,length,map);
        return temp;
    }
    /**
     *@title: getentrustIdAdd
     *@description: 获取新建界面的组合代码联想下拉框
     *@author: junYin
     *@date: 2019/06/04 11:04
     *@param: * @param map 1
     * @param page 2
     * @param length 3
     *@return: com.joyintech.framework.grid.Page<com.joyintech.framework.select.SelectOptionDto>
     *@throws:
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> temp = etrNewStockService.getentrustIdAdd(page,length,map);
        return temp;
    }
    /**
     *@title: check
     *@description: 操作前校验
     *@author: junYin
     *@date: 2019/06/04 11:04
     *@param: * @param dto 1
     *@return: java.util.List<com.joyintech.framework.validation.ValidMessage>
     *@throws:
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrNewStockDto dto){
        return etrNewStockService.check(dto);
    }
    /**
     *@title: recallDelegate
     *@description: 撤回委托
     *@author: junYin
     *@date: 2019/06/04 11:05
     *@param: * @param dto 1
     *@return: boolean
     *@throws:
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recall")
    public boolean recallDelegate(@RequestBody EtrNewStockDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrNewStockService.recallDelegate(dto);
    }
    /**
     *@title: closeDelegate
     *@description: 关闭委托
     *@author: junYin
     *@date: 2019/06/04 11:05
     *@param: * @param entrustId 1
     * @param closeWay 2
     *@return: boolean
     *@throws:
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrNewStockService.closeDelegate(entrustId, closeWay);
    }
    /**
     *@title: doComplete
     *@description: 工作流完成方法
     *@author: junYin
     *@date: 2019/06/04 11:08
     *@param: * @param dto 1
     *@return: void
     *@throws:
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrNewStockDto dto) throws IllegalAccessException, InvocationTargetException{
        etrNewStockService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrNewStockDto dto){
        etrNewStockService.doInsert(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doUpdate")
    public void doUpdate(@RequestBody EtrNewStockDto dto){
        WFlowCommonService.doUpdateFlow(dto.getOperateType(), etrNewStockService.initBaseDto(dto));

        etrNewStockService.doInsert(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doBack")
    public void doBack(@RequestBody EtrStockDto dto){
        WFlowCommonService.doBackFlow(dto.getWorkflowId(), dto.getEntrustId());
    }

    /**
     *@title: selectByKey
     *@description: 通过主键查询数据
     *@author: junYin
     *@date: 2019/06/04 11:09
     *@param: * @param entrustId 1
     *@return: com.joyintech.service.entrust.etrstock.dto.EtrNewStockDto
     *@throws:
     */
    @GetMapping("selectByKey")
    public EtrNewStockDto selectByKey(String entrustId) {
        return etrNewStockService.selectByKey(entrustId);
    }

    /**
     *@title: getProAvailableCash
     *@description: 组合可用现金查询
     *@author: junYin
     *@date: 2019/06/04 11:09
     *@param: * @param param 1
     *@return: java.util.Map<java.lang.String,java.lang.Object>
     *@throws:
     */
    @PostMapping("getProAvailableCash")
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrNewStockService.getProAvailableCash(param);
    }
    /**
     *@title: getAssetAvailablePosition
     *@description: 组合可用持仓查询
     *@author: junYin
     *@date: 2019/06/04 11:09
     *@param: * @param dto 1
     *@return: java.math.BigDecimal
     *@throws:
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        return etrNewStockService.getAssetAvailablePosition(dto);
    }
    /**
     * 查询估值净价
     * @author wangguiqi
     */
    @PostMapping("getClosePriceByPara")
    public BigDecimal getClosePriceByPara(@RequestBody Map<String, Object> param) {
        return etrNewStockService.getClosePriceByPara(param);
    }
}
