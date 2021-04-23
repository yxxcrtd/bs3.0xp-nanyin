/*
 * 文件名：RepayScheduleSubCal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.joyintech.calculation.bonds.BastsInfo;
import com.joyintech.calculation.bonds.interest.BasisScheduleSubCal;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.dto.calc.InvRateQuo;
import com.joyintech.tams.valuation.common.dto.calc.InvRepaySchedule;
import com.joyintech.tams.valuation.common.dto.calc.InvRpayscheDetail;
import com.joyintech.tams.valuation.common.service.InstanceOfClass;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 还本计划分段区间计算
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see RepayScheduleSubCal
 * @since 1.0
 */
public class RepayScheduleSubCal {

    /**
     * 分段区间计算 
     * 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param liy 利率报价列表
     * @param lirs 还本计划列表 计息截至日从小到大排序
     * @param amt 初始本金
     * @param <T> 泛型
     * @param isUp      
     * @param class1 需要返回的类型
     * @return  计划分段列表
     */
    public static <T extends InvRpayscheDetail> List<T> cal(Date startDate, Date endDate,
                                                            List<InvRateQuo> liy,
                                                            List<InvRepaySchedule> lirs,
                                                            BigDecimal amt, String isUp,
                                                            Class<T> class1) {
        // 【变量】本金 =
        BigDecimal papi = amt;

        List<T> res = new ArrayList<T>();
        List<T> lisd = new ArrayList<T>();
        
        List<T> resultList = new ArrayList<T>();//ADD BY WUJIE ON 20190903
        
        T isd = InstanceOfClass.createInstance(class1);
        isd.setVdate(startDate);
        isd.setCapi(papi);
        
        // 根据报价，拆分付息计划
        for (InvRateQuo iy : liy) {
            if (DateUtil.compareDate(iy.getStartDate(), startDate) <= 0) {
                isd.setYield(iy.getPaperRate().setScale(12, RoundingMode.HALF_UP));
                isd.setAccountBasis(iy.getAccountBasis());
            }
            else if (DateUtil.compareDate(iy.getStartDate(), startDate) > 0
                     && DateUtil.compareDate(iy.getStartDate(), endDate) < 0) {
                isd.setMdate(iy.getStartDate());
                isd.setAccountBasis(iy.getAccountBasis());
                lisd.add(isd);
                isd = InstanceOfClass.createInstance(class1);
                isd.setCapi(papi);
                isd.setVdate(iy.getStartDate());
                isd.setYield(iy.getPaperRate().setScale(12, RoundingMode.HALF_UP));
                isd.setAccountBasis(iy.getAccountBasis());
            }
            else if (DateUtil.compareDate(iy.getStartDate(), endDate) >= 0) {
                break;
            }
        }
        
        isd.setMdate(endDate);
        lisd.add(isd);
        // 根据还本计划，拆分“已拆分的付息计划” 
        for (T tempisd : lisd) {
            T isd2 = InstanceOfClass.createInstance(class1);
            isd2.setVdate(tempisd.getVdate());
            isd2.setAccountBasis(tempisd.getAccountBasis());
            isd2.setYield(ArithmeticUtil.round(tempisd.getYield(), 12, RoundingMode.HALF_UP));
            isd2.setCapi(papi);
            // 根据还本，拆分付息计划
            if (lirs != null) {
                for (InvRepaySchedule irs : lirs) {
                    if (irs.getActMdate().compareTo(tempisd.getVdate()) == 0) {
                        if ("0".equals(isUp)) {
                            papi = papi.subtract(irs.getRepayAmt());
                        }
                        else {
                            papi = irs.getRepayAmt();
                        }
                    }
                    if (irs.getActMdate().compareTo(tempisd.getVdate()) > 0
                        && irs.getActMdate().compareTo(tempisd.getMdate()) < 0) {
                        if (irs.getIsPayInt() == null
                            || DictCodeList.是否_否.getValue().equals(irs.getIsPayInt())) {
                            isd2.setCapi(papi);
                            isd2.setYield(ArithmeticUtil.round(tempisd.getYield(), 12,
                                RoundingMode.HALF_UP));
                            isd2.setMdate(irs.getActMdate());
                            isd2.setAccountBasis(tempisd.getAccountBasis());
                            res.add(isd2);
                            isd2 = InstanceOfClass.createInstance(class1);
                            isd2.setVdate(irs.getActMdate());
                        }
                        if ("0".equals(isUp)) {
                            papi = papi.subtract(irs.getRepayAmt());
                        }
                        else {
                            papi = irs.getRepayAmt();
                        }
                    }
                    else if (irs.getActMdate().compareTo(tempisd.getMdate()) >= 0) {
                        break;
                    }
                }
            }
            isd2.setYield(ArithmeticUtil.round(tempisd.getYield(), 12, RoundingMode.HALF_UP));
            isd2.setCapi(papi);
            isd2.setMdate(tempisd.getMdate());
            isd2.setAccountBasis(tempisd.getAccountBasis());
            res.add(isd2);
        }
        /**ADD BY WUJIE ON 20190903 START**/
        //计息基础(A/A_D)变更处理
        
        for (T tempisd : res) {
        	 //【变量】分段付息区间N
        	 T isd3 = InstanceOfClass.createInstance(class1);
        	 isd3.setVdate(tempisd.getVdate());
        	 isd3.setAccountBasis(tempisd.getAccountBasis());
        	 isd3.setYield(tempisd.getYield());
        	 isd3.setCapi(tempisd.getCapi());
        	 
        	 if(!DictCodeListExt1.计息基础_A_A_D.getValue().equals(tempisd.getAccountBasis())){
        		 isd3.setMdate(tempisd.getMdate()); 
        		 resultList.add(isd3);
        		 continue;
        	 }
        	 List<BastsInfo> bastsInfoList = BasisScheduleSubCal.getBastsInfoList(BasicUtil.dateToLocal(tempisd.getVdate()),BasicUtil.dateToLocal(tempisd.getMdate()));
        	 if(bastsInfoList==null || bastsInfoList.size()<=1){
        		 isd3.setMdate(tempisd.getMdate()); 
        		 resultList.add(isd3);
        		 continue;
        	 }
        	 
        	 // 计息基础按照生效日排序
             Collections.sort(bastsInfoList, new Comparator<BastsInfo>() {
                 @Override
                 public int compare(BastsInfo o1, BastsInfo o2) {
                     return o1.getEffdDate().compareTo(o1.getEffdDate());
                 }
             });
        	 
        	 List<T> resLs = new ArrayList<T>();
        	 for(BastsInfo bastsInfo :bastsInfoList){
        		 Date effDate = BasicUtil.localToDate(bastsInfo.getEffdDate());
        		 //【变量】计息基础变更N.生效日 <= 【变量】分段付息区间N.分段起始日
        		 if(DateUtil.compareDate(effDate, tempisd.getVdate())<=0){
        			 resLs.clear();
        			 
        			 isd3 = InstanceOfClass.createInstance(class1);
                	 isd3.setVdate(tempisd.getVdate());
                	 isd3.setAccountBasis(tempisd.getAccountBasis());
                	 isd3.setYield(tempisd.getYield());
                	 isd3.setCapi(tempisd.getCapi());
        			 resLs.add(isd3);
        			 
        		 }
        		 //【变量】计息基础变更N.生效日 > 【变量】分段付息区间N.分段起始日 
        		 //and 【变量】计息基础变更N.生效日 <  【变量】分段付息区间N.分段结束日
        		 else if(DateUtil.compareDate(effDate, tempisd.getVdate())>0
        				 && DateUtil.compareDate(effDate, tempisd.getMdate())<0){
        			if(resLs.size()>0){
        				resLs.get(resLs.size()-1).setMdate(effDate);
        			} 
        			isd3 = InstanceOfClass.createInstance(class1);
               	    isd3.setVdate(effDate);
               	    isd3.setAccountBasis(tempisd.getAccountBasis());
               	    isd3.setYield(tempisd.getYield());
               	    isd3.setCapi(tempisd.getCapi());
        			resLs.add(isd3);
        		 }
        		 else if(DateUtil.compareDate(effDate, tempisd.getMdate())<=0){
        			if(resLs.size()>0){
         				resLs.get(resLs.size()-1).setMdate(tempisd.getMdate());
         			} 
        			break;
        		 }
        	 }
        	 if(resLs.size()>0){
        		 resLs.get(resLs.size()-1).setMdate(tempisd.getMdate());
        		 resultList.addAll(resLs);
        	 }
        }
        /**ADD BY WUJIE ON 20190903 END**/
        return resultList;
    }

    /**
     * 分段区间计算
     * 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param liy 利率报价列表
     * @param lirs 还本计划列表 计息截至日从小到大排序
     * @param amt 初始本金
     * @param <T> 泛型
     * @param class1 需要返回的类型
     * @return 计划分段列表
     * @see
     * @since 1.0
     */
    public static <T extends InvRpayscheDetail> List<T> cal(Date startDate, Date endDate,
                                                            List<InvRateQuo> liy,
                                                            List<InvRepaySchedule> lirs,
                                                            BigDecimal amt, Class<T> class1) {
        return cal(startDate, endDate, liy, lirs, amt, "0", class1);
    }

}
