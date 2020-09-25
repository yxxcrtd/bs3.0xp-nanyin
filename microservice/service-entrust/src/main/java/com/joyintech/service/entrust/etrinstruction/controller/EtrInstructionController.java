package com.joyintech.service.entrust.etrinstruction.controller; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2020/1/6
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.service.entrust.etrinstruction.dto.EtrInstructionDto;
import com.joyintech.service.entrust.etrinstruction.service.EtrInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController("EtrInstruction_dtx")
@RequestMapping("/EtrInstruction")
public class EtrInstructionController {

    @Autowired EtrInstructionService etrInstructionService;

    /**
     *
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     * <功能详细描述>
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrFundDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrInstructionDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page,
                                                 @PathVariable("length") int length)throws Exception {
        return etrInstructionService.selectForPage(page,length,pageParam);
    }


    /**
     *
     * 资产代码联想文本框
     * <功能详细描述>
     * @param finprodId String
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId(String finprodId){
        return etrInstructionService.getFinprodMarketId(finprodId);
    }

    /**
     *
     * 融资人/管理人联想文本框
     * <功能详细描述>
     * @param repFinancier String
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @GetMapping("/getRepFinancierList")
    public List<SuggestDto> getRepFinancierList(String repFinancier){
        return etrInstructionService.getRepFinancierList(repFinancier);
    }

    /**
     *
     * 编辑、双击页面查看详情
     * 根据主键查询
     * @param entrustId String
     * @return EtrFundDto
     * @see
     * @since 1.0
     */
    @GetMapping("/selectByKey")
    public EtrInstructionDto selectByKey(String entrustId){
        return etrInstructionService.selectByKey(entrustId);
    }

    /**
     *
     * 操作前校验
     * <功能详细描述>
     * @param dto EtrFundDto
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrInstructionDto dto){
        return etrInstructionService.check(dto);
    }

    /**
     *
     * 撤销委托
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/recall")
    public boolean recallDelegate(String entrustId){
        return etrInstructionService.recallDelegate(entrustId);
    }

    /**
     *
     * 关闭委托
     * <功能详细描述>
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/close")
    public boolean closeDelegate(String entrustId,String closeWay){
        return etrInstructionService.closeDelegate(entrustId, closeWay);
    }


    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody EtrInstructionDto dto) throws IllegalAccessException, InvocationTargetException {
        etrInstructionService.doInsert(dto);
    }


    /**
     *
     *
     * 工作流完成操作
     * @param dto EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody EtrInstructionDto dto) throws IllegalAccessException, InvocationTargetException {
        etrInstructionService.doComplete(dto);
    }

}
