package com.joyintech.service.product.productplanning.informationMaintenance.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.informationMaintenance.dao.FinProductPlanningEstablishMapper;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning2DetailDto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning2Dto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanning3Dto;
import com.joyintech.service.product.productplanning.informationMaintenance.dto.FinProductPlanningDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

@Service("backFinProductPlanningEstablishService")
public class FinProductPlanningEstablishService extends BaseService {
    @Autowired
    private FinProductPlanningEstablishMapper finProductPlanningEstablishMapper;

    /**
     * 查询产品规划信息
     *
     * @param page page
     * @param length length
     * @param pageParam 参数
     * @return 产品规划信息
     * @throws Exception 异常
     * @see
     *
     */
    public Page<FinProductPlanning2DetailDto> findPage(int page, int length,
                                                       Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue()); // 数据字典.金融产品类型（投管）.理财产品
        pageParam.put("PS_QZ", DictCodeList.交易方向_份额强增.getValue()); //数据字典.交易方向_份额强增
        pageParam.put("PS_CPRG", DictCodeList.交易方向_产品认购.getValue());//数据字典.交易方向_产品认购
        pageParam.put("PS_CPSG", DictCodeList.交易方向_产品申购.getValue());//数据字典.交易方向_产品申购
        pageParam.put("PS_CPSH", DictCodeList.交易方向_产品赎回.getValue());//数据字典.交易方向_产品赎回
        pageParam.put("PS_CPTQSH", DictCodeList.交易方向_产品提前赎回.getValue());//数据字典.交易方向_产品提前赎回
        pageParam.put("PS_CPDQ", DictCodeList.交易方向_产品到期.getValue());//数据字典.交易方向_产品到期
        pageParam.put("PS_TQZZ", DictCodeList.交易方向_提前终止.getValue());//数据字典.交易方向_提前终止
        pageParam.put("PS_FEQJ", DictCodeList.交易方向_份额强减.getValue());//数据字典.交易方向_份额强减
        pageParam.put("JYZT", DictCodeListExt1.交易状态_初始.getValue());//数据字典.交易状态_初始
        pageParam.put("YES_NO_N", DictCodeList.是否_否.getValue());//数据字典.是否_否
//        pageParam.put("PS_CPZJHB", DictCodeList.交易方向_产品中间还本.getValue());
        pageParam.put("PS_CPWYSH", DictCodeList.交易方向_产品违约赎回.getValue());//数据字典.交易方向_产品违约赎回
        pageParam.put("PS_CPHLTZ", DictCodeList.交易方向_产品红利转投.getValue());//数据字典.交易方向_产品红利转投
        pageParam.put("SYS_HBX", DictCodeList.业务类型_货币型TA交易.getValue());//数据字典.业务类型_货币型TA交易
        pageParam.put("SYS_JZX", DictCodeList.业务类型_净值型TA交易.getValue());//数据字典.业务类型_净值型TA交易
        pageParam.put("SYS_YQSYX", DictCodeList.业务类型_预期收益型TA交易.getValue());//数据字典.业务类型_预期收益型TA交易

        Date date = DateUtil.platDateFromCache();
        pageParam.put("PTR_DATE", DateUtil.format(date));
        /**
         * 不需要有效状态字段  dgf  20191228
         */
//        pageParam.put("VALID_STATUS_01", DictCodeListExt1.有效状态_有效.getValue());//数据字典.有效状态_有效
//        pageParam.put("VALID_STATUS_03", DictCodeListExt1.有效状态_撤销.getValue());//数据字典.有效状态_撤销
//        if("".equals(pageParam.get("validStatus"))){
//            pageParam.put("validStatus", DictCodeListExt1.有效状态_有效.getValue());
//        }
        return this.queryForPageForSelectReturn(page, length,
                () -> finProductPlanningEstablishMapper.selectPageData(pageParam));
    }

    /**
     * 查询目标资产规模
     * @param map
     * @return
     * @throws Exception
     */
    public List<FinProductPlanning3Dto> getTargetSize(Map<String,Object> map) throws Exception {
       return  finProductPlanningEstablishMapper.getTargetSize(map);
    }
    /**
     * 产品规划信息维护 用于相同时间check
     * @param params
     * @return
     */
    public int getConflictCount(Map<String,Object> params){
        return finProductPlanningEstablishMapper.getConflictCount(params);
    }
    /**
     * 产品规划信息维护 用于重叠时间check
     * @param params
     * @return
     */
    public int getOverlapCount(Map<String,Object> params){
        return finProductPlanningEstablishMapper.getOverlapCount(params);
    }
    /**
     * 查询页面查询
     * @return
     */
    public Page<FinProductPlanningDto> queryPageAll(int page, int length,
                                                    Map<String, Object> pageParam)
            throws Exception {
        /**
         * 用于查询条件中的名字查询的多字段查询
         */
        if (pageParam.get("planningSeriesName") != null) {
            pageParam.put("planningSeriesNameList",
                    StringUtil.splitToList(",", (String) pageParam.get("planningSeriesName")));
        } else {
            pageParam.put("planningSeriesNameList", null);
        }
        return  this.queryForPage(page, length,
                () -> finProductPlanningEstablishMapper.findPageAll(pageParam));
    }
    /**
     * 根据系列id 查name
     * @param page
     * @param length
     * @param param
     * @return
     */
    /**
     * 新建页面根据id 联动名称,需求更改不需要这个联动 dgf 20191228
     */
//    public Page<FinProductPlanningDto> queryPrdPlanSerName(int page, int length, Map<String, Object> param) {
//        param.put("dataStatus", DictCodeListExt1.有效状态_有效.getValue());
//        param.put("isNew", DictCodeList.是否_否.getValue());
//        return this.queryForPageForSelectReturn(page, length, () ->finProductPlanningEstablishMapper.queryPrdPlanSerName(param));
//    }

    /**
     * 系列名称下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public Page<SelectOptionDto> querySeriesName(int page, int length, Map<String, Object> paramMap) {
        paramMap.put("dataStatus", DictCodeListExt1.有效状态_有效.getValue());
        paramMap.put("isNew", DictCodeList.是否_否.getValue());
        return this.queryForPageForSelectReturn(page, length,
                ()-> finProductPlanningEstablishMapper.querySeriesNamess(paramMap));
    }
    /**
     * 根据流水号查询研发方案详情
     *
     * @param
     * @return 研发方案详情
     * @see
     * @since 1.0
     */
    public FinProductPlanning2Dto getEntityByNo(String planningNo) {
        List<FinProductPlanning2DetailDto> entityDetailList = finProductPlanningEstablishMapper.getEntityDetailByNo(planningNo);
        FinProductPlanning2Dto  entity=  finProductPlanningEstablishMapper.getEntityByNo(planningNo);
        entity.setDetailDtoList(entityDetailList);
        return entity;
    }
    /**
     * 点击+号操作
     * 根据系列编号查询是否存在该数据
     * @param
     * @return
     */
    public Integer findCountByNo(String planningSeriesNo){
       return  finProductPlanningEstablishMapper.findCountByNo(planningSeriesNo);
    }

    /**
     * 点击+号操作
     * 根据系列名称查询是否存在该数据
     * @param
     * @return
     */
    public Integer findCountByName(String planningSeriesName){
        return  finProductPlanningEstablishMapper.findCountByName(planningSeriesName);
    }
    /**
     * 修改页面查询到期份额
     * @param
     * @return
     */
    public BigDecimal findProductsDue( Map<String, Object> pageParam){
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue()); // 数据字典.金融产品类型（投管）.理财产品
        pageParam.put("PS_QZ", DictCodeList.交易方向_份额强增.getValue()); //数据字典.交易方向_份额强增
        pageParam.put("PS_CPRG", DictCodeList.交易方向_产品认购.getValue());//数据字典.交易方向_产品认购
        pageParam.put("PS_CPSG", DictCodeList.交易方向_产品申购.getValue());//数据字典.交易方向_产品申购
        pageParam.put("PS_CPSH", DictCodeList.交易方向_产品赎回.getValue());//数据字典.交易方向_产品赎回
        pageParam.put("PS_CPTQSH", DictCodeList.交易方向_产品提前赎回.getValue());//数据字典.交易方向_产品提前赎回
        pageParam.put("PS_CPDQ", DictCodeList.交易方向_产品到期.getValue());//数据字典.交易方向_产品到期
        pageParam.put("PS_TQZZ", DictCodeList.交易方向_提前终止.getValue());//数据字典.交易方向_提前终止
        pageParam.put("PS_FEQJ", DictCodeList.交易方向_份额强减.getValue());//数据字典.交易方向_份额强减
        pageParam.put("JYZT", DictCodeListExt1.交易状态_初始.getValue());//数据字典.交易状态_初始
        pageParam.put("YES_NO_N", DictCodeList.是否_否.getValue());//数据字典.是否_否
        pageParam.put("PS_CPWYSH", DictCodeList.交易方向_产品违约赎回.getValue());//数据字典.交易方向_产品违约赎回
        pageParam.put("PS_CPHLTZ", DictCodeList.交易方向_产品红利转投.getValue());//数据字典.交易方向_产品红利转投
        pageParam.put("SYS_HBX", DictCodeList.业务类型_货币型TA交易.getValue());//数据字典.业务类型_货币型TA交易
        pageParam.put("SYS_JZX", DictCodeList.业务类型_净值型TA交易.getValue());//数据字典.业务类型_净值型TA交易
        pageParam.put("SYS_YQSYX", DictCodeList.业务类型_预期收益型TA交易.getValue());//数据字典.业务类型_预期收益型TA交易
        Long productsDue = finProductPlanningEstablishMapper.findProductsDue(pageParam);
        if(StringUtil.isNotEmpty(productsDue)){
            BigDecimal bigDecimal =new BigDecimal(productsDue);//获取剩余份额
            BigDecimal divide = ArithmeticUtil.divide(bigDecimal, 10000, 4);//除10000
            return divide;
        }else{
            return new BigDecimal("0");
        }
    }
    /**
     * 新增或者修改的产品规划
     */
    public Integer updateOrAdd(FinProductPlanning2Dto dto) {
        List<FinProductPlanning2DetailDto> detail = new ArrayList<>();
        //产品规划详情表不为空时才取
        if (dto != null && dto.getDetailDtoList() != null) {
            detail = dto.getDetailDtoList();
        }
        if (dto != null && dto.getOperateType() != null && StringUtil.equals(dto.getOperateType(), "MODIFY")) {
            //修改
            int i = finProductPlanningEstablishMapper.updateByPrimaryKey(dto);
            String planningNo = dto.getPlanningNo();
            //所有明细数据先删除再插入
            Map<String, Object> param = new HashMap<>();
            param.put("planningNo", planningNo);
            finProductPlanningEstablishMapper.deletePlanDetail(param);
            detail.forEach(e -> {
                //只插入目标规模不为空的
                if(e.getPlanningTargetValue()!=null){
                    e.setPlanningNo(planningNo);
                    e.setInitialPlanningValue(ArithmeticUtil.multiply(e.getInitialPlanningValue(), BigDecimal.valueOf(10000)));//初始值乘10000
                    e.setPlanningChangeValue(ArithmeticUtil.multiply(e.getPlanningChangeValue(),BigDecimal.valueOf(10000)));//变化值乘10000
                    e.setPlanningTargetValue(ArithmeticUtil.multiply(e.getPlanningTargetValue(),BigDecimal.valueOf(10000)));//目标值乘10000
                    finProductPlanningEstablishMapper.insert(e);
                }
            });
            return i;
        }else{
            //新增
            int i = finProductPlanningEstablishMapper.insertInfo(dto);
            detail.forEach(e->{
                if(e.getPlanningTargetValue()!=null) {
                    //只插入目标值不为空的
                    e.setInitialPlanningValue(ArithmeticUtil.multiply(e.getInitialPlanningValue(), BigDecimal.valueOf(10000)));//初始值乘10000
                    e.setPlanningChangeValue(ArithmeticUtil.multiply(e.getPlanningChangeValue(),BigDecimal.valueOf(10000)));//变化值乘10000
                    e.setPlanningTargetValue(ArithmeticUtil.multiply(e.getPlanningTargetValue(),BigDecimal.valueOf(10000)));//目标值乘10000
                    finProductPlanningEstablishMapper.insert(e);
                }
            });
            return i;
        }
    }
    /**
     * 删除
     */
    public Integer deleteByPrimary(Map<String,Object> params){
        /**
         * 不需要有效状态字段 dgf  20191228
         */
//        params.put("VALID_STATUS_03", DictCodeListExt1.有效状态_撤销.getValue());
//        int plan = finProductPlanningEstablishMapper.updateValidStatus(params);
        Integer plan = finProductPlanningEstablishMapper.deletePlanDetail(params);
        finProductPlanningEstablishMapper.deleteInfoPlan(params);
        return plan;
    }
}

