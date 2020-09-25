/*
 * 文件名：MstBankTranFeeManageController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstbanktranfeemanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.service.basicdata.mstbanktranfeemanage.service.MstBankTranFeeManageService;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;

/**
 * 银行间交易费用
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年3月25日
 * @see MstBankTranFeeManageController
 * @since 1.0
 */
@RestController("MstBankTranFeeManageCtr_dtx")
@RequestMapping("/MstBankTranFeeManage")
public class MstBankTranFeeManageController {
    @Autowired
    private MstBankTranFeeManageService mstBankTranFeeManageService;
    /**
     * 畫面查詢方法
     * @param map 参数
     * @return 所有數據
     * @throws Exception 異常
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<MstBankTranFeeManageDto> selectForPage(@RequestBody Map<String, Object> map,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return mstBankTranFeeManageService.selectForPage(page,length,map);
    }
    /**
     * 根据流水号查询数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getEntity")
    public MstBankTranFeeManageDto getEntity(@RequestBody Map<String, Object> map)
        throws Exception {
        return mstBankTranFeeManageService.getEntity(map);
    }
    /**
     * 删除
     * 
     * @param dto
     *            页面选中的数据
     * @param
     *
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstBankTranFeeManageDto dto) throws Exception {
        return mstBankTranFeeManageService.delete(dto);
    }
    
    /**
     * 插入
     * 
     * @param dto
     *            页面选中的数据
     * @param
     *
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstBankTranFeeManageDto dto) throws Exception {
        return mstBankTranFeeManageService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     *            页面选中的数据
     * @param
     *
     * @return 结果
     * @throws Exception
     *             异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstBankTranFeeManageDto dto) throws Exception {
        return mstBankTranFeeManageService.update(dto);
    }
    /**
     * 
     * 判断要删除的数据是否存在
     * <功能详细描述>
     * @param batchSeq
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("isExit")
    public boolean isExit(String batchSeq){
        return mstBankTranFeeManageService.isExit(batchSeq);
    }
    /**
     * 查询数据库中的所有数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return List
     * @see
     * @since 1.0
     */
    @GetMapping("getAllDto")
    public List<MstBankTranFeeManageDto> getAllDto(){
        return mstBankTranFeeManageService.getAllDto();
    }
    /**
     * 根据pageId查询所有存在于工作流当中的数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param parameter
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getBusinessData")
    public List<MstBankTranFeeManageDto> getBusinessData(@RequestBody WflValidInputParameter parameter){
        return mstBankTranFeeManageService.getBusinessData(parameter);
    }
    
    /**
     * 
     * 费用设置查重
     * @param param 入参 tradeType:交易品种，ps:交易方向，termType:期限品种
     * @return 重复条数
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selExistCnt")
    public Integer selExistCnt(@RequestBody Map<String, Object> param)
        throws Exception {
        return mstBankTranFeeManageService.selExistCnt(param);
    }
    
    @PostMapping("/selExistCnt1")
    public Integer selExistCnt1(@RequestBody Map<String, Object> param)
        throws Exception {
        return mstBankTranFeeManageService.selExistCnt1(param);
    }
}
