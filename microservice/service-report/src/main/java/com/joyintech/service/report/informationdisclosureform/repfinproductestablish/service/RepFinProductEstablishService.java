/*
 * 文件名：RepFinProductEstablishService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.informationdisclosureform.repfinproductestablish.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.informationdisclosureform.repfinproductestablish.dao.RepFinProductEstablishMapper;
import com.joyintech.service.report.informationdisclosureform.repfinproductestablish.dto.RepFinProductEstablishDto;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huwenbin
 * @version 1.0 2018年9月7日
 * @see RepFinProductEstablishService
 * @since 1.0
 */
@Service
public class RepFinProductEstablishService extends BaseService<RepFinProductEstablishDto> {
    
	/**
     * 平台日期
     */
    @Autowired(required=false)
    private PlatformDateImpl platformDateImpl;
    /**
     * 自定义Mapper                                                     
     */
    @Autowired
    private RepFinProductEstablishMapper repFinProductEstablishMapper;
   
    /**
     * 
     * 页面下拉框初始化
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectOption(Map<String, Object> map) {
        return repFinProductEstablishMapper.getSelectOption(map);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param page page
     * @param length length
     * @param pageParam pageParam
     * @return Page<RepFinProductEstablishDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepFinProductEstablishDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        if(StringUtil.isNotEmpty(pageParam.get("finprodIdList"))){
            pageParam.put("finprodIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("finprodIdList")));
        }
        if(platformDateImpl != null){
            pageParam.put("sysDate", platformDateImpl.getSysDate());
        }
        pageParam.put("endVDate", DateUtil.parse((String)pageParam.get("endVDate")));
        pageParam.put("startVDate", DateUtil.parse((String)pageParam.get("startVDate")));
        pageParam.put("endMDate", DateUtil.parse((String)pageParam.get("endMDate")));
        pageParam.put("startMDate", DateUtil.parse((String)pageParam.get("startMDate")));
        pageParam.put("yes", DictCodeList.是否_是.getValue());
        pageParam.put("no",DictCodeList.是否_否.getValue());
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("porfitType",DictCodeList.收益类型_收益型.getValue());
//        pageParam.put("busiType", DictCodeListExt1.金融产品交易业务类型_产品交易.getValue());
        pageParam.put("ps", DictCodeList.交易方向_产品认购.getValue());
        pageParam.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        //pageParam.put("noBranchNoValue", (long)0);//
        //Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        return super.queryForPage(page, length, () -> repFinProductEstablishMapper.select(pageParam));
    }
    
    /**
     * 
     * 根据母产品代码查询投资标的比例
     * <功能详细描述>
     * @param pFinprodId String
     * @return String
     * @see
     * @since 1.0
     */
    public String selectResult(String pFinprodId){
        String str = null; 
        List<PtlGoal> ptlGoleList = repFinProductEstablishMapper.selectResult(pFinprodId);
        if(ptlGoleList.size()>0){
            for(int i=0; i<ptlGoleList.size(); i++){
                //循环到最后一个不加“：”
                if(i==ptlGoleList.size()-1){
                    str = str + ptlGoleList.get(i).getAssetClassify()+"-"+
                        ptlGoleList.get(i).getInvestMax()+"-"+ptlGoleList.get(i).getInvestMin(); 
                }
                else{
                    str = str + ptlGoleList.get(i).getAssetClassify()+"-"+
                        ptlGoleList.get(i).getInvestMax()+"-"+ptlGoleList.get(i).getInvestMin()+":"; 
                }
            }
            return str;
        }
        else{
            return null; 
        }
    }
    
    /**
     * 
     * 根据金融产品代码和分支序号查询
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return RepFinProductEstablishDto
     * @see
     * @since 1.0
     */
    public RepFinProductEstablishDto selectByFinprodIdAndBranch(Map<String,Object> map){
        map.put("cashType1Cost", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType1Rate", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2Rate", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("cashType2Trustee", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("cashType2Sales", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        return repFinProductEstablishMapper.selectByFinprodIdAndBranch(map);
    }
}
