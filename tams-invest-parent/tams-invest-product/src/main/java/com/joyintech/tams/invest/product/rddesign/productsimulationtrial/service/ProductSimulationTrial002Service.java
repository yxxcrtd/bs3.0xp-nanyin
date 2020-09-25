package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.EngineContext;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MockResultInfoMapper;
import com.joyintech.tams.common.base.dao.MockTradeDealMapper;
import com.joyintech.tams.common.base.dao.MockTradeRateMapper;
import com.joyintech.tams.common.base.dao.WflMockResultInfoMapper;
import com.joyintech.tams.common.base.dao.WflMockTradeDealMapper;
import com.joyintech.tams.common.base.dao.WflMockTradeRateMapper;
import com.joyintech.tams.common.base.entity.MockResultInfo;
import com.joyintech.tams.common.base.entity.MockTradeDeal;
import com.joyintech.tams.common.base.entity.MockTradeRate;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.common.base.entity.WflMockResultInfo;
import com.joyintech.tams.common.base.entity.WflMockTradeDeal;
import com.joyintech.tams.common.base.entity.WflMockTradeRate;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto.ProductSimulationTrial002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 产品模拟交易service
 * 
 * @author jiaobo
 * @version 1.0 2019年10月31日
 * @see ProductSimulationTrial002Dto
 * @since 1.0
 */
@Service
public class ProductSimulationTrial002Service extends CommonExportService<ProductSimulationTrial002Dto> implements  
                BaseProcessService<ProductSimulationTrial002Dto>{
    
    @Autowired
    private WflMockTradeDealMapper wflMockTradeDealMapper;
    
    @Autowired
    private WflMockTradeRateMapper wflMockTradeRateMapper;
    @Autowired
    private WflMockResultInfoMapper wflMockResultInfoMapper;
    
    @Autowired
    private MockTradeDealMapper mockTradeDealMapper;
    
    @Autowired
    private MockTradeRateMapper mockTradeRateMapper;
    
    @Autowired
    private MockResultInfoMapper mockResultInfoMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * 待试算产品的产品规模查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param queryParam
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getPrdScale(Map<String, Object> queryParam) {

        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/getPrdScale", 
            queryParam, BigDecimal.class);
    }
    
    /**
     * 
     * 利率查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<MstIntRateInfo> queryMstIntRateInfo(Map<String,Object> param) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/queryMstIntRateInfo", 
            param, MstIntRateInfo.class); 
    }
    
    /**
     * 查询模拟试算结果
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public MockResultInfo queryMockResultInfo(Map<String ,Object> param) {
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/queryMockResultInfo", 
            param, MockResultInfo.class); 
    }
    
    /**
     * 查询模拟试算交易
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public boolean deleteMockTrade (Map<String ,Object> param) {
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/deleteMockTrade", 
            param, boolean.class); 
    }
    
    /**
     * 通过流水号查询产品模拟试算结果
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqNo
     * @return
     * @see
     * @since 1.0
     */
    public ProductSimulationTrial002Dto getProductSimulationTrialBySeqNo(String seqNo){
        Map<String ,Object> param = new HashMap<String ,Object>();
        param.put("seqNo", seqNo);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/getProductSimulationTrialBySeqNo", 
            param, ProductSimulationTrial002Dto.class); 
    }
    
    /**
     * 获取列表信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<ProductSimulationTrial002Dto> findTablePage(Map<String ,Object> param) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/findTablePage", 
            param, ProductSimulationTrial002Dto.class);  
    }
    
    /**
     * 获取新的批次号
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getNewBacthId(Map<String,Object> param){
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/ProductSimulationTrial/getNewBacthId",param,String.class);
        
    }
    
    /**
     * 
     * 模拟试算
     * <功能详细描述>
     * @param productSimulationTrial002Dto
     * @return
     * @see
     * @since 1.0
     */
    public ProductSimulationTrial002Dto doSimulationTrial(ProductSimulationTrial002Dto productSimulationTrial002Dto) {
        
        ProductSimulationTrial002Dto resultDto = new ProductSimulationTrial002Dto();
        if(!StringUtil.isEmptyList(productSimulationTrial002Dto.getSimulationAssetsList())) {
            //变量:上限
            String max = "";
            //变量:下限
            String min = "";
            //变量：总份额
            BigDecimal mockShareAmtTotal = BigDecimal.ZERO;
            //变量：总金额
            BigDecimal mockAmtTotal = BigDecimal.ZERO;
            //变量.收益率上限
            BigDecimal rateMax = BigDecimal.ZERO;
            //变量.收益率下限
            BigDecimal rateMin = BigDecimal.ZERO;
            //变量.收益率上限浮动部分
            BigDecimal rateMaxFloat = BigDecimal.ZERO;
            //变量.收益率下限浮动部分
            BigDecimal rateMinFloat = BigDecimal.ZERO;
            //变量.浮动指数上限
            String floatIndexMax = "";
            //变量.浮动指数下限
            String floatIndexMin = "";
            //变量.浮动曲线上限
            String floatCurveMax = "";
            //变量.浮动曲线下限
            String floatCurveMin = "";
            //处理基准类型：指数或者曲线 的 浮动指数
            Map<String,Object> floatIndexMaxPartMap = new HashMap<String, Object>();
            Map<String,Object> floatIndexMinPartMap = new HashMap<String, Object>();
            Map<String,Object> floatCurveMaxPartMap = new HashMap<String, Object>();
            Map<String,Object> floatCurveMinPartMap = new HashMap<String, Object>();
            
            Map<String,Object> floatBenchmarkMaxStrMap = new HashMap<String, Object>();
            Map<String,Object> floatBenchmarkMinStrMap = new HashMap<String, Object>();
            //计算总份额和初始化处理浮动指数的Map（去重处理）
            for(ProductSimulationTrial002Dto tmpDto:productSimulationTrial002Dto.getSimulationAssetsList()) {
                mockShareAmtTotal = ArithmeticUtil.doAdd(mockShareAmtTotal,tmpDto.getMockShareAmt());
                mockAmtTotal = ArithmeticUtil.doAdd(mockAmtTotal,tmpDto.getMockAmt());
                
                if(StringUtil.equals(tmpDto.getRateType(), DictCodeList.利率类型_浮动利率.getValue())) {
                    if(StringUtil.equals(tmpDto.getBenchmarkMaxSource(), DictCodeListExt1.基准类型_2_指数.getValue())) {
                        floatIndexMaxPartMap.put(tmpDto.getBenchmarkMax(), BigDecimal.ZERO);
                       
                    } 
                    else if (StringUtil.equals(tmpDto.getBenchmarkMaxSource(), DictCodeListExt1.基准类型_2_收益率曲线.getValue())) {
                        floatCurveMaxPartMap.put(tmpDto.getBenchmarkMax(), BigDecimal.ZERO);
                    }
                    floatBenchmarkMaxStrMap.put(tmpDto.getBenchmarkMax(), tmpDto.getBenchmarkMaxStr());
                    
                    if(StringUtil.equals(tmpDto.getBenchmarkMinSource(), DictCodeListExt1.基准类型_2_指数.getValue())) {
                        floatIndexMinPartMap.put(tmpDto.getBenchmarkMin(), BigDecimal.ZERO);
                    } 
                    else if (StringUtil.equals(tmpDto.getBenchmarkMinSource(), DictCodeListExt1.基准类型_2_收益率曲线.getValue())) {
                        floatCurveMinPartMap.put(tmpDto.getBenchmarkMin(), BigDecimal.ZERO);
                    }
                    floatBenchmarkMinStrMap.put(tmpDto.getBenchmarkMin(), tmpDto.getBenchmarkMinStr());
                } 
            }
            
            for(ProductSimulationTrial002Dto dto:productSimulationTrial002Dto.getSimulationAssetsList()) {
                //当利率类型为固定利率
                if(StringUtil.equals(dto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
                    rateMax = ArithmeticUtil.doAdd(rateMax,ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(new BigDecimal(dto.getRateMax()),dto.getMockShareAmt()), mockShareAmtTotal, 
                        EngineContext.getInstance().getConfig().getAllPrecision()));
                    
                    rateMin = ArithmeticUtil.doAdd(rateMin,ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(new BigDecimal(dto.getRateMix()),dto.getMockShareAmt()), mockShareAmtTotal, 
                        EngineContext.getInstance().getConfig().getAllPrecision()));
                } //当利率类型为浮动利率
                else if(StringUtil.equals(dto.getRateType(), DictCodeList.利率类型_浮动利率.getValue())){
                    //计算收益率上限
                    if(StringUtil.equals(dto.getBenchmarkMaxSource(), DictCodeListExt1.基准类型_2_利率.getValue())) {
                        Map<String,Object> param = new HashMap<String,Object>();
                        param.put("mockDate", DateUtil.format(productSimulationTrial002Dto.getMockDate()));
                        param.put("benchmark", dto.getBenchmarkMax());
                        List<MstIntRateInfo> listMstIntRateInfo= this.queryMstIntRateInfo(param);
                        if(!StringUtil.isEmptyList(listMstIntRateInfo)) {
                            BigDecimal intRate = ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ArithmeticUtil.multiply(
                                listMstIntRateInfo.get(0).getIntRate(),new BigDecimal(100)),dto.getCoefficientMax()),dto.getSpreadRateMax());
                            rateMax =  ArithmeticUtil.doAdd(rateMax,ArithmeticUtil.divide(
                                ArithmeticUtil.multiply(intRate,dto.getMockShareAmt()), mockShareAmtTotal, 
                                EngineContext.getInstance().getConfig().getAllPrecision()));
                        }
                    } 
                    else if(StringUtil.equals(dto.getBenchmarkMaxSource(), DictCodeListExt1.基准类型_2_指数.getValue())) {
                        rateMax = ArithmeticUtil.doAdd(rateMax,ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getSpreadRateMax(),dto.getMockShareAmt()),mockShareAmtTotal,
                            EngineContext.getInstance().getConfig().getAllPrecision()));
                        rateMaxFloat = ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getCoefficientMax(),dto.getMockShareAmt()), mockShareAmtTotal, 
                            EngineContext.getInstance().getConfig().getAllPrecision());
                        
                        BigDecimal rateMaxFloatTotal = this.getrateFloatTotal(floatIndexMaxPartMap,dto.getBenchmarkMax(),rateMaxFloat);
                        floatIndexMaxPartMap.put(dto.getBenchmarkMax(), rateMaxFloatTotal);
                    } 
                    else if (StringUtil.equals(dto.getBenchmarkMaxSource(), DictCodeListExt1.基准类型_2_收益率曲线.getValue())) {
                        rateMax = ArithmeticUtil.doAdd(rateMax,ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getSpreadRateMax(),dto.getMockShareAmt()),mockShareAmtTotal,
                            EngineContext.getInstance().getConfig().getAllPrecision()));
                        rateMaxFloat = ArithmeticUtil.divide(ArithmeticUtil.multiply(
                            dto.getCoefficientMax(),dto.getMockShareAmt()), mockShareAmtTotal, 
                            EngineContext.getInstance().getConfig().getAllPrecision());
                        BigDecimal rateMaxFloatTotal = this.getrateFloatTotal(floatCurveMaxPartMap,dto.getBenchmarkMax(),rateMaxFloat);
                        floatCurveMaxPartMap.put(dto.getBenchmarkMax(), rateMaxFloatTotal);
                    }

                    //计算收益率下限
                    if(StringUtil.equals(dto.getBenchmarkMinSource(), DictCodeListExt1.基准类型_2_利率.getValue())) {
                        Map<String,Object> param = new HashMap<String,Object>();
                        param.put("mockDate",DateUtil.format(productSimulationTrial002Dto.getMockDate()));
                        param.put("benchmark", dto.getBenchmarkMin());
                        List<MstIntRateInfo> listMstIntRateInfo= this.queryMstIntRateInfo(param);
                        if(!StringUtil.isEmptyList(listMstIntRateInfo)) {
                            BigDecimal intRate = ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ArithmeticUtil.multiply(
                                listMstIntRateInfo.get(0).getIntRate(),new BigDecimal(100)),dto.getCoefficientMin()),dto.getSpreadRateMin());
                            
                            rateMin =  ArithmeticUtil.doAdd(rateMin,ArithmeticUtil.divide(
                                ArithmeticUtil.multiply(intRate,dto.getMockShareAmt()), mockShareAmtTotal, 
                                EngineContext.getInstance().getConfig().getAllPrecision()));
                        }
                    } 
                    else if(StringUtil.equals(dto.getBenchmarkMinSource(), DictCodeListExt1.基准类型_2_指数.getValue())) {
                        
                        rateMin = ArithmeticUtil.doAdd(rateMin,ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getSpreadRateMin(),dto.getMockShareAmt()),mockShareAmtTotal,
                            EngineContext.getInstance().getConfig().getAllPrecision()));
                        rateMinFloat = ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getCoefficientMin(),dto.getMockShareAmt()), mockShareAmtTotal, 
                            EngineContext.getInstance().getConfig().getAllPrecision());
                        
                        BigDecimal rateMinFloatTotal = this.getrateFloatTotal(floatIndexMinPartMap,dto.getBenchmarkMin(),rateMinFloat);
                        floatIndexMinPartMap.put(dto.getBenchmarkMin(), rateMinFloatTotal);
                    } 
                    else if (StringUtil.equals(dto.getBenchmarkMinSource(), DictCodeListExt1.基准类型_2_收益率曲线.getValue())) {
                        rateMin = ArithmeticUtil.doAdd(rateMin,ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getSpreadRateMin(),dto.getMockShareAmt()),mockShareAmtTotal,
                            EngineContext.getInstance().getConfig().getAllPrecision()));
                        rateMinFloat = ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(dto.getCoefficientMin(),dto.getMockShareAmt()), mockShareAmtTotal, 
                            EngineContext.getInstance().getConfig().getAllPrecision());
                        
                        BigDecimal rateMinFloatTotal = this.getrateFloatTotal(floatCurveMinPartMap,dto.getBenchmarkMin(),rateMinFloat);
                        floatCurveMinPartMap.put(dto.getBenchmarkMin(), rateMinFloatTotal);
                    }
                }
            }
            
            //计算浮动指数和浮动曲线
            if(!floatIndexMaxPartMap.isEmpty()) {
                for(String key:floatIndexMaxPartMap.keySet()) {
                    String benchmarkMaxStr =(String)floatBenchmarkMaxStrMap.get(key);
                    BigDecimal value = ((BigDecimal)floatIndexMaxPartMap.get(key)).setScale(4,RoundingMode.HALF_UP);
                    if(StringUtil.isBlank(floatIndexMax)) {
                        floatIndexMax = value.toString()+"*"+benchmarkMaxStr; 
                    } 
                    else {
                        floatIndexMax = floatIndexMax + "+"+value.toString()+"*"+benchmarkMaxStr;  
                    }
                    
                }
            }
            if(!floatCurveMaxPartMap.isEmpty()) {
                for(String key:floatCurveMaxPartMap.keySet()) {
                    String benchmarkMaxStr =(String)floatBenchmarkMaxStrMap.get(key);
                    BigDecimal value = ((BigDecimal)floatCurveMaxPartMap.get(key)).setScale(4,RoundingMode.HALF_UP);
                    if(StringUtil.isBlank(floatCurveMax)) {
                        floatCurveMax = value.toString()+"*"+benchmarkMaxStr; 
                    } 
                    else {
                        floatCurveMax = floatCurveMax + "+"+value.toString()+"*"+benchmarkMaxStr;  
                    } 
                }
            }
            if(!floatIndexMinPartMap.isEmpty()) {
                for(String key:floatIndexMinPartMap.keySet()) {
                    String benchmarkMinStr =(String)floatBenchmarkMinStrMap.get(key);
                    BigDecimal value = ((BigDecimal)floatIndexMinPartMap.get(key)).setScale(4,RoundingMode.HALF_UP);
                    
                    if(StringUtil.isBlank(floatIndexMin)) {
                        floatIndexMin = value.toString()+"*"+benchmarkMinStr; 
                    } 
                    else {
                        floatIndexMin = floatIndexMin + "+"+value.toString()+"*"+benchmarkMinStr;  
                    } 
                }
            }
            if(!floatCurveMinPartMap.isEmpty()) {
                for(String key:floatCurveMinPartMap.keySet()) {
                    String benchmarkMinStr =(String)floatBenchmarkMinStrMap.get(key);
                    BigDecimal value = ((BigDecimal)floatCurveMinPartMap.get(key)).setScale(4,RoundingMode.HALF_UP);                    
                    if(StringUtil.isBlank(floatCurveMin)) {
                        floatCurveMin = value.toString()+"*"+benchmarkMinStr; 
                    } else {
                        floatCurveMin = floatCurveMin + "+"+value.toString()+"*"+benchmarkMinStr;  
                    } 
                }
            }
            
            //计算上限
            if(rateMax != null && rateMax != BigDecimal.ZERO) {
                max = rateMax.setScale(4,RoundingMode.HALF_UP).toString();
                if(!StringUtil.isBlank(floatIndexMax)) {
                    max = max + "+" + floatIndexMax;
                }
                
                if(!StringUtil.isBlank(floatCurveMax)) {
                    max = max + "+" + floatCurveMax;
                }
            } 
            else {
                
                if(!StringUtil.isBlank(floatIndexMax)) {
                    max = floatIndexMax;
                }
                
                if(!StringUtil.isBlank(floatCurveMax)) {
                    max = max + "" + floatCurveMax;
                }
                else if (!StringUtil.isBlank(floatCurveMax)) {
                    max = floatCurveMax;
                } 
            }
            

            //计算下限
            if(rateMin != null && rateMin != BigDecimal.ZERO) {
                min = rateMin.setScale(4,RoundingMode.HALF_UP).toString();
                if(!StringUtil.isBlank(floatIndexMin)) {
                    min = min + "+" +  floatIndexMin;
                }
                
                if(!StringUtil.isBlank(floatCurveMin)){
                    min = min + "+" +  floatCurveMin;
                }
            } 
            else {
                
                if(!StringUtil.isBlank(floatIndexMin)) {
                    min = floatIndexMin;
                }
                
                if(!StringUtil.isBlank(floatCurveMin)) {
                    min = min + "+" + floatCurveMin;
                }
                else if (!StringUtil.isBlank(floatCurveMin)) {
                    min = floatCurveMax;
                }  
            }
            resultDto.setRateMax(max);
            resultDto.setRateMix(min);
            if(productSimulationTrial002Dto.getPrdScale() != null) {
                resultDto.setMockAvailableAmt(ArithmeticUtil.subtract(productSimulationTrial002Dto.getPrdScale(), mockAmtTotal));   
            }
        }
        return resultDto;
    }
    
    private BigDecimal getrateFloatTotal(Map<String , Object> map, String benchmark, BigDecimal rateFloat) {
        BigDecimal rateFloatTotal = (BigDecimal)map.get(benchmark);
        if(rateFloatTotal != null) {
            rateFloatTotal = ArithmeticUtil.doAdd(rateFloatTotal,rateFloat);
            return rateFloatTotal;
        }
        return rateFloat;
    }

    /**
     * 审批完成,DB登录
     */
    @Override
    public void doComplete(ProductSimulationTrial002Dto dto, OperationType operationType) {
        // TODO Auto-generated method stub
        List<MockTradeDeal> listMockTradeDeal = new ArrayList<MockTradeDeal>();
        List<MockTradeRate> listMockTradeRate = new ArrayList<MockTradeRate>();
        if(!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            if(!StringUtil.isEmptyList(dto.getSimulationAssetsList())) {
                for(ProductSimulationTrial002Dto entity:dto.getSimulationAssetsList())  {
                    entity.setMockDate(dto.getMockDate());
                    entity.setBatchId(dto.getBatchId());
                    entity.setPrdDesignId(dto.getPrdDesignId());
                    MockTradeDeal mockTradeDeal = this.genMockTradeDeal(entity); 
                    listMockTradeDeal.add(mockTradeDeal);
                    
                    listMockTradeRate = this.genMockTradeRate(entity, mockTradeDeal, listMockTradeRate);
                }
                //新增模拟试算交易和模拟试算交易利率
                if(!StringUtil.isEmptyList(listMockTradeDeal) && !StringUtil.isEmptyList(listMockTradeRate)){
                    for(MockTradeDeal mockTradeDeal:listMockTradeDeal) {
                        mockTradeDealMapper.insertSelective(mockTradeDeal);
                    }
                    
                    for(MockTradeRate wflMockTradeRate:listMockTradeRate) {
                        mockTradeRateMapper.insertSelective(wflMockTradeRate);
                    }
                }
                //新增模拟试算结果
                MockResultInfo mockResultInfo = this.genMockResultInfo(dto);
                mockResultInfoMapper.insertSelective(mockResultInfo);
                
                //删除原流程数据
                wflMockTradeRateMapper.deleteByWorkflowId(dto.getWorkflowId());
                wflMockTradeDealMapper.deleteByWorkflowId(dto.getWorkflowId());
                wflMockResultInfoMapper.deleteByPrimaryKey(dto.getSeqNo());
            }
            
        } else {
            Map<String,Object> param = new HashMap<String,Object>();
            MockResultInfo  mockResultInfo = mockResultInfoMapper.selectByPrimaryKey(dto.getSeqNo());
            if(mockResultInfo != null) {
                param.put("prdDesignId", mockResultInfo.getPrdDesignId());
                param.put("batchId", mockResultInfo.getBatchId());
                //删除模拟试算交易和交易利率
                deleteMockTrade(param);
                
                //删除模拟试算结果
                mockResultInfoMapper.deleteByPrimaryKey(dto.getSeqNo()); 
            }

        }
        
    }
    
    /**
     * 交易流程表插入
     */
    @Override
    public void doInsert(ProductSimulationTrial002Dto entity, OperationType type) {
        if (!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            this.insertBussFlowData(entity);
        }
    }
    
    private void insertBussFlowData(ProductSimulationTrial002Dto entity){
        List<WflMockTradeDeal> listWflMockTradeDeal = new ArrayList<WflMockTradeDeal>();
        List<WflMockTradeRate> listWflMockTradeRate = new ArrayList<WflMockTradeRate>();
        if(!StringUtil.isEmptyList(entity.getSimulationAssetsList())) {
            for(ProductSimulationTrial002Dto dto:entity.getSimulationAssetsList())  {
                dto.setMockDate(entity.getMockDate());
                dto.setWorkflowId(entity.getWorkflowId());
                dto.setBatchId(entity.getBatchId());
                dto.setPrdDesignId(entity.getPrdDesignId());
                WflMockTradeDeal wflMockTradeDeal = this.genWflMockTradeDeal(dto); 
                listWflMockTradeDeal.add(wflMockTradeDeal);
                
                listWflMockTradeRate = this.genWflMockTradeRate(dto, wflMockTradeDeal, listWflMockTradeRate);
            }
            
            if(!StringUtil.isEmptyList(listWflMockTradeDeal) && !StringUtil.isEmptyList(listWflMockTradeRate)){
                for(WflMockTradeDeal mockTradeDeal:listWflMockTradeDeal) {
                    wflMockTradeDealMapper.insertSelective(mockTradeDeal);
                }
                
                for(WflMockTradeRate wflMockTradeRate:listWflMockTradeRate) {
                    wflMockTradeRateMapper.insertSelective(wflMockTradeRate);
                }
            }
            
            WflMockResultInfo wflMockResultInfo =  this.genWflMockResultInfo(entity);
            wflMockResultInfoMapper.insertSelective(wflMockResultInfo);
        }

    }
    
    /**
     * 生成模拟交易dto
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private MockTradeDeal genMockTradeDeal(ProductSimulationTrial002Dto dto) {
        MockTradeDeal mockTradeDeal = new MockTradeDeal();
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_新研发产品模拟试算);
        mockTradeDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        mockTradeDeal.setTradeDate(dto.getMockDate());
        mockTradeDeal.setPrdDesignId(dto.getPrdDesignId());
        mockTradeDeal.setAssetType(dto.getAssetType());
        mockTradeDeal.setAssetRiskLevel(dto.getAssetRiskLevel());
        mockTradeDeal.setMockAmt(dto.getMockAmt());
        mockTradeDeal.setMockShareAmt(dto.getMockShareAmt());
        mockTradeDeal.setBatchId(dto.getBatchId());
        
        return mockTradeDeal; 
    }
    
    /**
     * 生成模拟交易利率dto
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param mockTradeDeal
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    private List<MockTradeRate> genMockTradeRate(ProductSimulationTrial002Dto dto,MockTradeDeal mockTradeDeal,List<MockTradeRate> list) {
        MockTradeRate mockTradeRateMax = new MockTradeRate();
        MockTradeRate mockTradeRateMin = new MockTradeRate();
        
        mockTradeRateMax.setTradeId(mockTradeDeal.getTradeId());
        mockTradeRateMax.setRateType(dto.getRateType());
        mockTradeRateMax.setDataStatus(DictCodeListExt1.有效状态_有效.getValue());
        mockTradeRateMax.setRateMnclass(DictCodeListExt3.利率上下限区分_利率上限.getValue());
        
        mockTradeRateMin.setTradeId(mockTradeDeal.getTradeId());
        mockTradeRateMin.setRateType(dto.getRateType());
        mockTradeRateMin.setDataStatus(DictCodeListExt1.有效状态_有效.getValue());
        mockTradeRateMin.setRateMnclass(DictCodeListExt3.利率上下限区分_利率下限.getValue());
        
        if(StringUtil.equals(dto.getRateType(),DictCodeList.利率类型_浮动利率.getValue())) {
            if(StringUtil.isNotEmpty(dto.getRateMax())) {
                mockTradeRateMax.setIndexId(dto.getBenchmarkMax());
                mockTradeRateMax.setIndexSource(dto.getBenchmarkMaxSource());
                mockTradeRateMax.setCoefficient(dto.getCoefficientMax().toString());
                mockTradeRateMax.setSpreadRate(dto.getSpreadRateMax().toString());
                list.add(mockTradeRateMax);
            }
            
            if(StringUtil.isNotEmpty(dto.getRateMix())) {
                mockTradeRateMin.setIndexId(dto.getBenchmarkMin());
                mockTradeRateMin.setIndexSource(dto.getBenchmarkMinSource());
                mockTradeRateMin.setCoefficient(dto.getCoefficientMin().toString());
                mockTradeRateMin.setSpreadRate(dto.getSpreadRateMin().toString());
                list.add(mockTradeRateMin);
            }
            
        } else if(StringUtil.equals(dto.getRateType(),DictCodeList.利率类型_固定利率.getValue())){
            
            if(StringUtil.isNotEmpty(dto.getRateMax())) {
                mockTradeRateMax.setSpreadRate(dto.getRateMax());
                list.add(mockTradeRateMax);
            }
            
            if(StringUtil.isNotEmpty(dto.getRateMix())) {
                mockTradeRateMin.setSpreadRate(dto.getRateMix());
                list.add(mockTradeRateMin);
            }
        }
        
        return list; 
    }
    
    /**
     * 生成模拟交易流程DTO
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private WflMockTradeDeal genWflMockTradeDeal(ProductSimulationTrial002Dto dto) {
        WflMockTradeDeal wflMockTradeDeal = new WflMockTradeDeal();
        
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_新研发产品模拟试算);
        wflMockTradeDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        
        wflMockTradeDeal.setTradeDate(dto.getMockDate());
        wflMockTradeDeal.setPrdDesignId(dto.getPrdDesignId());
        wflMockTradeDeal.setAssetType(dto.getAssetType());
        wflMockTradeDeal.setAssetRiskLevel(dto.getAssetRiskLevel());
        wflMockTradeDeal.setMockAmt(dto.getMockAmt());
        wflMockTradeDeal.setMockShareAmt(dto.getMockShareAmt());
        wflMockTradeDeal.setBatchId(dto.getBatchId());
        wflMockTradeDeal.setStatus(DictCodeList.数据状态_新建.getValue());
        wflMockTradeDeal.setWorkflowId(dto.getWorkflowId());
        return wflMockTradeDeal; 
    }
    /**
     * 生成模拟交易利率流程
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @param wflMockTradeDeal
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    private List<WflMockTradeRate> genWflMockTradeRate(ProductSimulationTrial002Dto dto,WflMockTradeDeal wflMockTradeDeal,List<WflMockTradeRate> list) {
        WflMockTradeRate wflMockTradeRateMax = new WflMockTradeRate();
        WflMockTradeRate wflMockTradeRateMin = new WflMockTradeRate();
                
        wflMockTradeRateMax.setTradeId(wflMockTradeDeal.getTradeId());
        wflMockTradeRateMax.setRateType(dto.getRateType());
        wflMockTradeRateMax.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
        wflMockTradeRateMax.setRateMnclass(DictCodeListExt3.利率上下限区分_利率上限.getValue());
        wflMockTradeRateMax.setWorkflowId(dto.getWorkflowId());
        
        wflMockTradeRateMin.setTradeId(wflMockTradeDeal.getTradeId());
        wflMockTradeRateMin.setRateType(dto.getRateType());
        wflMockTradeRateMin.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
        wflMockTradeRateMin.setRateMnclass(DictCodeListExt3.利率上下限区分_利率下限.getValue());
        wflMockTradeRateMin.setWorkflowId(dto.getWorkflowId());
        
        if(StringUtil.equals(dto.getRateType(),DictCodeList.利率类型_浮动利率.getValue())) {
            if(StringUtil.isNotEmpty(dto.getRateMax())) {
                wflMockTradeRateMax.setIndexId(dto.getBenchmarkMax());
                wflMockTradeRateMax.setIndexSource(dto.getBenchmarkMaxSource());
                wflMockTradeRateMax.setCoefficient(dto.getCoefficientMax().toString());
                wflMockTradeRateMax.setSpreadRate(dto.getSpreadRateMax().toString());
                list.add(wflMockTradeRateMax);
            }
            
            if(StringUtil.isNotEmpty(dto.getRateMix())) {
                wflMockTradeRateMin.setIndexId(dto.getBenchmarkMin());
                wflMockTradeRateMin.setIndexSource(dto.getBenchmarkMinSource());
                wflMockTradeRateMin.setCoefficient(dto.getCoefficientMin().toString());
                wflMockTradeRateMin.setSpreadRate(dto.getSpreadRateMin().toString());
                list.add(wflMockTradeRateMin);
            }
            
        } else if(StringUtil.equals(dto.getRateType(),DictCodeList.利率类型_固定利率.getValue())){
            
            if(StringUtil.isNotEmpty(dto.getRateMax())) {
                wflMockTradeRateMax.setSpreadRate(dto.getRateMax());
                list.add(wflMockTradeRateMax);
            }
            
            if(StringUtil.isNotEmpty(dto.getRateMix())) {
                wflMockTradeRateMin.setSpreadRate(dto.getRateMix());
                list.add(wflMockTradeRateMin);
            }
        }
        
        return list;
    }
    
    /**
     * 生成模式试算结果
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private MockResultInfo genMockResultInfo(ProductSimulationTrial002Dto dto) {
        MockResultInfo mockResultInfo = new MockResultInfo();
        mockResultInfo.setSeqNo(dto.getSeqNo());
        mockResultInfo.setGenerateTime(new Date());
        mockResultInfo.setMockDate(dto.getMockDate());
        mockResultInfo.setPrdDesignId(dto.getPrdDesignId());
        mockResultInfo.setPrdScale(dto.getPrdScale());
        mockResultInfo.setPrdPeriod(dto.getPrdPeriod());
        mockResultInfo.setRateMax(dto.getRateMax());
        mockResultInfo.setRateMix(dto.getRateMix());
        mockResultInfo.setRequirementId(dto.getRequirementId());
        mockResultInfo.setBatchId(dto.getBatchId());
        mockResultInfo.setDataStatus(DictCodeListExt1.有效状态_有效.getValue());
        return mockResultInfo;
    }
    
    /**
     * 生成模拟试算结果流程
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private WflMockResultInfo genWflMockResultInfo(ProductSimulationTrial002Dto dto) {
        WflMockResultInfo wflmockResultInfo = new WflMockResultInfo();
        wflmockResultInfo.setSeqNo(dto.getSeqNo());
        wflmockResultInfo.setGenerateTime(new Date());
        wflmockResultInfo.setMockDate(dto.getMockDate());
        wflmockResultInfo.setPrdDesignId(dto.getPrdDesignId());
        wflmockResultInfo.setPrdScale(dto.getPrdScale());
        wflmockResultInfo.setPrdPeriod(dto.getPrdPeriod());
        wflmockResultInfo.setRateMax(dto.getRateMax());
        wflmockResultInfo.setRateMix(dto.getRateMix());
        wflmockResultInfo.setRequirementId(dto.getRequirementId());
        wflmockResultInfo.setBatchId(dto.getBatchId());
        wflmockResultInfo.setWorkflowId(dto.getWorkflowId());
        wflmockResultInfo.setWorkflowStatus(DictCodeListExt1.有效状态_新建.getValue());
        return wflmockResultInfo;
    }
}
