package com.joyintech.service.period.trdsecoverdueduration.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdsecoverdueduration.service.TrdSecOverdueDurationService;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationZrDto;

/**
 * 已到期资产
 *
 * @author : wangxuelei
 * @version V1.0
 * @Project: microservice
 * @date 2019年7月1日
 */
@RestController("TrdSecOverdueDurationCtr_dtx")
@RequestMapping("/TrdSecOverdueDuration")
public class TrdSecOverdueDurationController {

    @Autowired
    private TrdSecOverdueDurationService trdSecOverdueDurationService;

    @PostMapping("theoryFindPage/{page}/{length}")
    public Page<TrdSecOverdueDurationDto> theoryFindPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                         @PathVariable("length") int length) {
        return trdSecOverdueDurationService.theoryFindPage(page, length, param);
    }

    @PostMapping("realityFindPage/{page}/{length}")
    public Page<TrdSecOverdueDurationDto> realityFindPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return trdSecOverdueDurationService.realityFindPage(page, length, param);
    }
    /** @MethodName: recePositionDetailFindPage
    * @Description: 持仓明细
    * @Param: [param, page, length]
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto>
    * @Author: wangxuelei
    * @Date: 2019/12/6 0006
    **/
    @PostMapping("recePositionDetailFindPage/{page}/{length}")
    public Page<TrdSecOverdueDurationDto> recePositionDetailFindPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                          @PathVariable("length") int length) {
        return trdSecOverdueDurationService.recePositionDetailFindPage(page, length, param);
    }
    /** @MethodName: findRecePositionDetailByPortfolioId
    * @Description: TODO 子组合持仓明细列表
    * @Param: [param, page, length]
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto>
    * @Author: wangxuelei
    * @Date: 2019/12/12 0012
    **/
    @PostMapping("findRecePositionDetailByPortfolioId/{page}/{length}")
    public Page<TrdSecOverdueDurationDto> findRecePositionDetailByPortfolioId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                                     @PathVariable("length") int length) {
        return trdSecOverdueDurationService.findRecePositionDetailByPortfolioId(page, length, param);
    }
    /** @MethodName: intertstDetailFindPage
    * @Description: TODO 收息明细信息
    * @Param: [param, page, length]
    * @Return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto>
    * @Author: wangxuelei
    * @Date: 2019/12/6 0006
    **/
    @PostMapping("intertstDetailFindPage/{page}/{length}")
    public Page<TrdSecOverdueDurationDto> intertstDetailFindPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                                     @PathVariable("length") int length) {
        return trdSecOverdueDurationService.intertstDetailFindPage(page, length, param);
    }
    @PostMapping("selectJxwuFromFinprodId")
    public TrdSecOverdueDurationDto selectJxwuFromFinprodId(@RequestBody Map<String, Object> param){
        TrdSecOverdueDurationDto trdSecOverdueDurationDto = trdSecOverdueDurationService.selectJxwuFromFinprodId(param);
        return trdSecOverdueDurationDto;
    }
    @PostMapping("selectCashIdsByBranchAll")
    public TrdSecOverdueDurationDto selectCashIdsByBranchAll(@RequestBody Map<String, Object> param){
        TrdSecOverdueDurationDto trdSecOverdueDurationDto = trdSecOverdueDurationService.selectCashIdsByBranchAll(param);
        return trdSecOverdueDurationDto;
    }

    @PostMapping("selectWithOutBranchByLiBranch")
    public TrdSecOverdueDurationDto selectWithOutBranchByLiBranch(@RequestBody Map<String, Object> param){
        TrdSecOverdueDurationDto trdSecOverdueDurationDto = trdSecOverdueDurationService.selectWithOutBranchByLiBranch(param);
        return trdSecOverdueDurationDto;
    }
    
    /**
     * 获取分红明细list-有效
     * 
     * @return List<DividDetailInfo>
     * @param busiCode
     * @return
     * @throws Exception
     */
    @PostMapping("selectNewBasicdata")
    public TrdSecOverdueDurationDto selectNewBasicdata(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdSecOverdueDurationService.selectNewBasicdata(param);
    }
    
    /**
     * 获取分红明细list-有效
     * 
     * @return List<DividDetailInfo>
     * @param busiCode
     * @return
     * @throws Exception
     */
    @PostMapping("selectNewInterestDetails")
    public List<FinAssetIntInfo> selectNewInterestDetails(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdSecOverdueDurationService.selectNewInterestDetails(param);
    }

    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
            throws Exception {
        return trdSecOverdueDurationService.getFinprodMarketId(page, length, param);
    }
    
    /**
     * 获取转让信息
     *
     * @return 获取转让信息 （组合代码联动）
     * @param param 画面参数
     * @return 匹配结果
     */
    @PostMapping("genAssetTfInfo")
    public Map<String,Object> genAssetTfInfo(@RequestBody Map<String, Object> param) {
        return trdSecOverdueDurationService.genAssetTfInfo(param);
    }

    /**
     * 查询到期资产转让待处理
     * @param param 参数
     * @return 查询结果
     */
    @PostMapping("findTFEffData")
    public TrdSecOverdueDurationDto findTFEffData(@RequestBody Map<String, Object> param) {
        return trdSecOverdueDurationService.findTFEffData(param);
    }
    @RequestMapping("selectPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> selectPortfolioId(@PathVariable("page") int page,
                                                   @PathVariable("length") int length,
                                                   @RequestBody Map<String, Object> param) {
        return trdSecOverdueDurationService.selectPortfolioId(page, length, param);
    }
    /**
     * 转让完成complete
     * @param trdSecOverdueDurationDto 数据dto
     * @return 处理结果
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doCompleteZr")
    public String doCompleteZr(@RequestBody TrdSecOverdueDurationZrDto trdSecOverdueDurationDto) {
        return trdSecOverdueDurationService.doCompleteZr(trdSecOverdueDurationDto);
    }
    @PostMapping("isSecManage")
    public int isSecManage(@RequestBody  Map<String,Object>map){
        return trdSecOverdueDurationService.isSecManage(map);
    }
    
    
    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdSecOverdueDurationDto trdSecOverdueDurationDto) {
        return trdSecOverdueDurationService.doComplete(trdSecOverdueDurationDto);
    }
    
    /**
     * 撤销
     *
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdSecOverdueDurationDto dto) {
        return trdSecOverdueDurationService.doRevoke(dto);
    }
    
    /**
     * 查询到期资产还本带息信息
     * @param param 参数
     * @return 查询结果
     */
    @RequestMapping("findSecOverdueData")
    public TrdSecOverdueDurationDto findSecOverdueData(@RequestBody Map<String, Object> param) {
        return trdSecOverdueDurationService.findSecOverdueData(param);
    }
    /**
     * 查询到期资产转让后续交易
     * @param param 参数
     * @return 查询结果
     */
    @RequestMapping("checkCounterFlowDeal")
    public int checkCounterFlowDeal(@RequestBody Map<String, Object> param) {
        return trdSecOverdueDurationService.checkCounterFlowDeal(param);
    }

    /**
     * 根据汇总的汇总交易编号查询有效的数据
     * @param busiCode
     * @return
     */
    @RequestMapping("findEffectiveData")
    public TrdSecOverdueDurationDto findEffectiveData(@RequestBody String busiCode) {
        return trdSecOverdueDurationService.findEffectiveData(busiCode);
    }
}
