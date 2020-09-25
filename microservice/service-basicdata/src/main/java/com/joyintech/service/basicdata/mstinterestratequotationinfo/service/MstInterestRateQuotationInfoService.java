package com.joyintech.service.basicdata.mstinterestratequotationinfo.service;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.MstIntRateInfoMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstinterestratequotationinfo.dao.MstInterestRateQuotationInfoMapper;
import com.joyintech.service.basicdata.mstinterestratequotationinfo.dto.MstInterestRateQuotationInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
/**
 * 利率行情信息service
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see MstInterestRateQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional
public class MstInterestRateQuotationInfoService extends BaseService<MstInterestRateQuotationInfoDto> {

    /**
     * 注入Mapper类自己创建的
     */
    @Autowired
    private MstInterestRateQuotationInfoMapper myMstIntRateInfoMapper;

    /**
     * 注入继承的Mapper
     */
    @Autowired
    private MstIntRateInfoMapper mstIntRateInfoMapper;

    /**
     * 分页查询
     * 
     * @param pageParam 传入的查询条件
     * @return Page<MstInterestRateQuotationInfoDto> page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstInterestRateQuotationInfoDto> selectForPage(int page,int length,Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("BENCHMARK_TYPE_04", DictCodeListExt1.基准类型_2_利率.getValue());
        return super.queryForPage(page,length,() -> myMstIntRateInfoMapper.select(pageParam));
    };

    /**
     * 根据主键查询
     * @param map 查询条件
     * @return MstInterestRateQuotationInfoDto 实体类
     */
    public MstInterestRateQuotationInfoDto selectByPrimaryKey(Map<String, Object> map) {
        map.replace("effDate", DateUtil.parse((String)(map.get("effDate"))));
        return myMstIntRateInfoMapper.selectByPrimaryKey(map);
    }

    /**
     * 设置日期格式
     * {@inheritDoc}
     */
//    @Override
//    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
//        if(("relaeseDate".equals(propName) || "effDate".equals(propName)) && value != null){
//            cell.setCellValue(DateUtil.format((Date)value));
//        }
//    }
    /**
     * 流程实现方法 {@inheritDoc}
     */
    public void doComplete(MstInterestRateQuotationInfoDto entity, String type) {
        entity.setIntRateType(DictCodeListExt1.基准类型_2_利率.getValue());
        if (StringUtil.equals(type, "DELETE")) {
            // 调用父类的主键删除方法
            mstIntRateInfoMapper.deleteByPrimaryKey(entity);
        }
        else if (StringUtil.equals(type, "NEW")) {
            // 调用父类的主键插入方法
        	// 一体化对应etliu start
//            entity.setInputType(DictCodeListExt1.录入方式_手工维护.getValue());
        	entity.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
         // 一体化对应etliu end
            mstIntRateInfoMapper.insert(entity);
        }
        else {
            // 调用父类的主键修改方法
            mstIntRateInfoMapper.updateByPrimaryKey(entity);
        }
    }

}
