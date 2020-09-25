package com.joyintech.service.report.poc.portfolio.ptlmock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.MockProductDealMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.MockTestMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.MockTestNetTrendMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.MockTestRskMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.MockTestTaaMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.MockTradeProductMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dao.PtlMockTestMapper;
//import com.joyintech.tams.common.base.dao.MockProductDealMapper;
//import com.joyintech.tams.common.base.dao.MockTestMapper;
//import com.joyintech.tams.common.base.dao.MockTestNetTrendMapper;
//import com.joyintech.tams.common.base.dao.MockTestRskMapper;
//import com.joyintech.tams.common.base.dao.MockTestTaaMapper;
//import com.joyintech.tams.common.base.dao.MockTradeProductMapper;
import com.joyintech.service.report.poc.portfolio.ptlmock.dto.PtlMockTestDto;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockProductDeal;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTest;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestNetTrend;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestRsk;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTestTaa;
import com.joyintech.service.report.poc.portfolio.ptlmock.entity.MockTradeProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * 模拟试算
 *
 * @author yjm
 * @version 1.0 2020/04/14
 * @see PtlMockTestService
 * @since 1.0
 */
@Service
public class PtlMockTestService extends BaseService<PtlMockTestDto> {

    /**
     * PtlMockTestMapper
     */
    @Autowired
    private PtlMockTestMapper testMapper;

    /**
     * MockTestMapper
     */
    @Autowired
    private MockTestMapper mockTestMapper;

    /**
     * MockTestTaaMapper
     */
    @Autowired
    private MockTestTaaMapper mockTestTaaMapper;

    /**
     * MockTestRskMapper
     */
    @Autowired
    private MockTestRskMapper mockTestRskMapper;

    /**
     * MockProductDealMapper
     */
    @Autowired
    private MockProductDealMapper mockProductDealMapper;

    /**
     * MockTradeProductMapper
     */
    @Autowired
    private MockTradeProductMapper mockTradeProductMapper;

    /**
     * MockTestNetTrendMapper
     */
    @Autowired
    private MockTestNetTrendMapper mockTestNetTrendMapper;

    /**
     * 组合代码下拉框选项获取
     * 
     * @param map 参数map
     * @return 组合代码下拉框选项
     */
    public List<SelectOptionDto> getPtlInfo(Map<String, Object> map) {
        map.put("PROFIT_TYPE_HBX", DictCodeList.收益类型_货币型.getValue());
        map.put("PROFIT_TYPE_JZX", DictCodeList.收益类型_净值型.getValue());
        map.put("PTL_ISSUE_MODE_FBS", "01");
        map.put("PTL_ISSUE_MODE_DQKF", "02");
        map.put("PORTFOLIO_TYPE_CPZH", DictCodeListExt1.组合类型_投资类组合.getValue());
        map.put("PORTFOLIO_TYPE_XNZH", "04");

        return testMapper.getPtlInfo(map);
    }

    /**
     * 画面查询方法
     * 
     * @param pageParam 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<PtlMockTestDto> selectForPage(int page, int length,
                                              Map<String, Object> pageParam) {
        Page<PtlMockTestDto> dtoPage = super.queryForPage(page, length,
            () -> testMapper.selectForPage(pageParam));
        return dtoPage;

    }

    /**
     * 删除方法
     *
     * @param entity entity
     * @return 删除结果
     */
    public Integer delete(PtlMockTestDto entity) {
        List<PtlMockTestDto> mockIdList = entity.getSelectedItem();
        for (int i = 0; i < mockIdList.size(); i++ ) {
            String mockId = mockIdList.get(i).getMockId();
            // 模拟交易类金融产品
            testMapper.deleteMockTradeProduct(mockId);
            // 模拟金融产品交易表
            testMapper.deleteMockProductDeal(mockId);
            // 模拟测算风险测算主键删除
            testMapper.deleteMockTestRsk(mockId);
            // 模拟测算净值主键删除
            testMapper.deleteMockTestNetTrend(mockId);
            // 模拟测算资产配置
            testMapper.deleteMockTestTaa(mockId);
            // 模拟测算主键删除
            mockTestMapper.deleteByPrimaryKey(mockId);
        }

        return 1;
    }

    /**
     * 获取资产代码下拉框选项
     * @param map 参数map
     * @return 资产代码下拉框选项
     */
    public List<SelectOptionDto> getFinProdId(Map<String, Object> map) {
        List<String> finprodType2List = new ArrayList<String>();
        finprodType2List.add(DictCodeList.平安资产类型_债券.getValue());
        finprodType2List.add(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        finprodType2List.add(DictCodeList.平安资产类型_项目类资产.getValue());
        map.put("finprodType2List", finprodType2List);
        return testMapper.getFinProdId(map);
    }

    /**
     * 工作流完成方法 <功能详细描述>
     *
     * @param dto 数据dto
     * @param operationType 操作类型
     * @see
     * @since 1.0
     */
    public void doComplete(PtlMockTestDto dto, String operationType) {
        // 插入 模拟测算
        MockTest mockTest = new MockTest();
        BeanUtil.copyPropertiesIgnoreNull(mockTest, dto);
        mockTestMapper.insert(mockTest);
        
        
        // 模拟金融产品交易表
        if (!StringUtil.isEmptyList(dto.getMockProductDealList())) {
            for (MockProductDeal deal : dto.getMockProductDealList()) {
                deal.setMockId(mockTest.getMockId());
                mockProductDealMapper.insert(deal);
            }
        }
        
        // 模拟交易类金融产品
        if (!StringUtil.isEmptyList(dto.getMockTradeProductList())) {
            for (MockTradeProduct mockTradeProduct : dto.getMockTradeProductList()) {
                mockTradeProductMapper.insert(mockTradeProduct);
            }
        }
        
        // 模拟测算风险试算
        if (!StringUtil.isEmptyList(dto.getMockTestRskList())) {
            for (MockTestRsk mockTestRsk : dto.getMockTestRskList()) {
                mockTestRsk.setMockId(mockTest.getMockId());
                mockTestRskMapper.insert(mockTestRsk);
            }
        }
        
        // 模拟测算资产配置数据(测算前（考虑当日成交）)
        if (!StringUtil.isEmptyList(dto.getPreMockTestTaaList())) {
            for (MockTestTaa mockTestTaa : dto.getPreMockTestTaaList()) {
                mockTestTaa.setMockId(mockTest.getMockId());
                mockTestTaaMapper.insert(mockTestTaa);
            }
        }
        
        // 模拟测算资产配置数据(测算后)
        if (!StringUtil.isEmptyList(dto.getAfterMockTestTaaList())) {
            for (MockTestTaa mockTestTaa : dto.getAfterMockTestTaaList()) {
                mockTestTaa.setMockId(mockTest.getMockId());
                mockTestTaaMapper.insert(mockTestTaa);
            }
        }

        // 模拟测算净值数据
        if (!StringUtil.isEmptyList(dto.getMockTestNetTrendList())) {
            for (MockTestNetTrend mockTestNetTrend : dto.getMockTestNetTrendList()) {
                mockTestNetTrend.setMockId(mockTest.getMockId());
                mockTestNetTrendMapper.insert(mockTestNetTrend);
            }
        }
    }

    /**
     * 根据模拟编号查询模拟试算具体信息
     * @param map 参数map
     * @return 模拟试算具体信息
     */
    public PtlMockTestDto getInitInfo(Map<String, Object> map) {
        PtlMockTestDto dto = testMapper.getInitInfo(map);
        // 模拟交易
        dto.setPageMockDealList(testMapper.getMockProductDeal(map));
        // 模拟测算风险试算
        dto.setMockTestRskList(testMapper.selectMockTestRskData(map));
        // 模拟测算资产配置表数据
        List<MockTestTaa> mockTestTaaList = testMapper.selectMockTestTaaData(map);
        dto.setPreMockTestTaaList(mockTestTaaList.stream().filter(
            x -> StringUtil.equals(DictCodeList.是否_是.getValue(), x.getIsMock())).collect(
                Collectors.toList()));
        dto.setAfterMockTestTaaList(mockTestTaaList.stream().filter(
            x -> StringUtil.equals(DictCodeList.是否_否.getValue(), x.getIsMock())).collect(
                Collectors.toList()));
        // 模拟测算净值数据表数据
        dto.setMockTestNetTrendList(testMapper.selectMockTestNetTrendData(map));

        return dto;
    }
}
