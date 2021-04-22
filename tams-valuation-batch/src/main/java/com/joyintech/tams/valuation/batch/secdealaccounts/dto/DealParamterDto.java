/*
 * 文件名：DealTypesFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.secdealaccounts.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 交易金额计算参数
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see DealParamterDto
 * @since 1.0
 */
public class DealParamterDto {

    /**
     * 变量.成本科目余额
     */
    private BigDecimal varCostSubjectAmt;

    /**
     * 变量.成本科目数量
     */
    private BigDecimal varCostSubjectNum;

    /**
     * 变量.应计利息科目余额
     */
    private BigDecimal varLxSubjectAmt;

    /**
     * 变量.公允价值变动科目余额
     */
    private BigDecimal varGySubjectAmt;

    /**
     * 变量.溢折价科目余额
     */
    private BigDecimal varYzjSubjectAmt;
    
    /**
     * 变量.其他应收款-利息科目余额 = MAP（KEY：分支序号，VALUE:科目余额）
     */
    private Map<String, BigDecimal> varQtyskxSubjectAmt;

    /**
     * 变量.收息已结清份额 = MAP（KEY：分支序号，VALUE:收息已结清份额）
     */
    private Map<String, BigDecimal> cleanShareMap;
    
    /**
     * 日期重算集合
     * ADD BY WUJIE ON 20191216
     */
    private Map<Date,Boolean> retryDateMap;
    
    // ADD BY CAOGUO ON 20201015 减值相关修改 START 
    /**
     * 变量.其他应收款-本金科目余额  
     */
    private Map<String, BigDecimal> varQtyskxBJSubjectAmt;
    
    /**
     * 变量.减值准备科目余额
     */
    private BigDecimal varJzzbSubjectAmt;
    
    /**
     * 变量.减值准备科目余额_本位币
     */
    private BigDecimal varJzzbSubjectAmtBccy;
    // ADD BY CAOGUO ON 20201015 END 
    
    
   /**add by wujie on 20201019 start remark:资产减值**/
    /**
     * 变量.逾期罚息-科目余额
     */
    private Map<String,BigDecimal> varYqfxSubjectAmt;

    /**
     * 变量.其他应收款利息汇总余额
     */
    private BigDecimal varQtyskLxSubjectSumAmt;
    
    /**
     * 变量.其他应收款本金汇总余额
     */
    private BigDecimal varQtyskBjSubjectSumAmt;
    
    /**
     * 变量.逾期罚息汇总余额
     */
    private BigDecimal varYqfxSubjectSumAmt;
    
    /**add by wujie on 20201019 end**/
    
    /**add by wujie on 20201124 start remar:资产减值**/
    //变量.其他应收款当前发生余额
    private BigDecimal glb_tdyqtyskAmtSum;
    /**add by wujie on 20201124 end remar:资产减值**/

    /** modify by yanya on 20210311 增加结转日相关数据集合 start**/
    //结转日成本科目余额Map
    private Map<String, BigDecimal> jzrcbkmyeMap = new HashMap<>();
    //结转日成本科目数量Map
    private Map<String, BigDecimal> jzrcbkmslMap = new HashMap<>();
    //结转日应计利息余额Map
    private Map<String, BigDecimal> jzryjlxyeMap = new HashMap<>();
    //结转日溢折价科目余额Map
    private Map<String, BigDecimal> jzryzjkmyeMap = new HashMap<>();
    //结转日公允价值变动科目余额Map
    private Map<String, BigDecimal> jzrgyjzbdMap = new HashMap<>();
    /** modify by yanya on 20210311 增加结转日相关数据集合 end**/

    /** modify by yanya on 20210322 增加结转日相关数据本位币处理集合 start**/
    private Map<String, BigDecimal> jzrcbkmyeMapBccy = new HashMap<>();
    //结转日成本科目数量Map
    private Map<String, BigDecimal> jzrcbkmslMapBccy = new HashMap<>();
    //结转日应计利息余额Map
    private Map<String, BigDecimal> jzryjlxyeMapBccy = new HashMap<>();
    //结转日溢折价科目余额Map
    private Map<String, BigDecimal> jzryzjkmyeMapBccy = new HashMap<>();
    //结转日公允价值变动科目余额Map
    private Map<String, BigDecimal> jzrgyjzbdMapBccy = new HashMap<>();
    /** modify by yanya on 20210322 增加结转日相关数据本位币处理集合 end**/

    /**
     * 获取 变量.成本科目余额
     * 
     * @return varCostSubjectAmt.
     */
    public BigDecimal getVarCostSubjectAmt() {
        return varCostSubjectAmt;
    }

    /**
     * 设置 变量.成本科目余额
     * 
     * @param varCostSubjectAmt 变量.成本科目余额
     */
    public void setVarCostSubjectAmt(BigDecimal varCostSubjectAmt) {
        this.varCostSubjectAmt = varCostSubjectAmt;
    }

    /**
     * 获取 变量.成本科目数量
     * 
     * @return varCostSubjectNum.
     */
    public BigDecimal getVarCostSubjectNum() {
        return varCostSubjectNum;
    }

    /**
     * 设置 变量.成本科目数量
     * 
     * @param varCostSubjectNum 变量.成本科目数量
     */
    public void setVarCostSubjectNum(BigDecimal varCostSubjectNum) {
        this.varCostSubjectNum = varCostSubjectNum;
    }

    /**
     * 获取 变量.应计利息科目余额
     * 
     * @return varLxSubjectAmt.
     */
    public BigDecimal getVarLxSubjectAmt() {
        return varLxSubjectAmt;
    }

    /**
     * 设置 变量.应计利息科目余额
     * 
     * @param varLxSubjectAmt 变量.应计利息科目余额
     */
    public void setVarLxSubjectAmt(BigDecimal varLxSubjectAmt) {
        this.varLxSubjectAmt = varLxSubjectAmt;
    }

    /**
     * 获取 变量.公允价值变动科目余额
     * 
     * @return varGySubjectAmt.
     */
    public BigDecimal getVarGySubjectAmt() {
        return varGySubjectAmt;
    }

    /**
     * 设置 变量.公允价值变动科目余额
     * 
     * @param varGySubjectAmt 变量.公允价值变动科目余额
     */
    public void setVarGySubjectAmt(BigDecimal varGySubjectAmt) {
        this.varGySubjectAmt = varGySubjectAmt;
    }

    /**
     * 获取 变量.溢折价科目余额
     * 
     * @return varYzjSubjectAmt.
     */
    public BigDecimal getVarYzjSubjectAmt() {
        return varYzjSubjectAmt;
    }

    /**
     * 设置 变量.溢折价科目余额
     * 
     * @param varYzjSubjectAmt 变量.溢折价科目余额
     */
    public void setVarYzjSubjectAmt(BigDecimal varYzjSubjectAmt) {
        this.varYzjSubjectAmt = varYzjSubjectAmt;
    }

    /**
     * 获取 变量.其他应收款-利息科目余额
     * 
     * @return varQtyskxSubjectAmt.
     */
    public Map<String, BigDecimal> getVarQtyskxSubjectAmt() {
        return varQtyskxSubjectAmt;
    }

    /**
     * 设置 变量.其他应收款-利息科目余额
     * 
     * @param varQtyskxSubjectAmt 变量.其他应收款-利息科目余额
     */
    public void setVarQtyskxSubjectAmt(Map<String, BigDecimal> varQtyskxSubjectAmt) {
        this.varQtyskxSubjectAmt = varQtyskxSubjectAmt;
    }

    /**
     * 获取 收息已结清份额
     * 
     * @return 收息已结清份额
     */
    public Map<String, BigDecimal> getCleanShareMap() {
        return cleanShareMap;
    }

    /**
     * 设置 收息已结清份额
     * 
     * @param cleanShareMap 收息已结清份额
     */
    public void setCleanShareMap(Map<String, BigDecimal> cleanShareMap) {
        this.cleanShareMap = cleanShareMap;
    }
    
    
    public Map<Date, Boolean> getRetryDateMap() {
		if(retryDateMap==null){
			retryDateMap = new HashMap<Date,Boolean>();
		}
		return retryDateMap;
	}



	public void setRetryDateMap(Map<Date, Boolean> retryDateMap) {
		if(retryDateMap==null){
			retryDateMap = new HashMap<Date,Boolean>();
		}
		this.retryDateMap = retryDateMap;
	}

    
    /**
     * 获取 varQtyskxBJSubjectAmt
     * 
     * @return varQtyskxBJSubjectAmt.
     */
    public Map<String, BigDecimal> getVarQtyskxBJSubjectAmt() {
        return varQtyskxBJSubjectAmt;
    }

    /**
     * 设置 varQtyskxBJSubjectAmt
     * 
     * @param varQtyskxBJSubjectAmt varQtyskxBJSubjectAmt
     */
    public void setVarQtyskxBJSubjectAmt(Map<String, BigDecimal> varQtyskxBJSubjectAmt) {
        this.varQtyskxBJSubjectAmt = varQtyskxBJSubjectAmt;
    }

    /**
     * 获取 varJzzbSubjectAmt
     * 
     * @return varJzzbSubjectAmt.
     */
    public BigDecimal getVarJzzbSubjectAmt() {
        return varJzzbSubjectAmt;
    }

    /**
     * 设置 varJzzbSubjectAmt
     * 
     * @param varJzzbSubjectAmt varJzzbSubjectAmt
     */
    public void setVarJzzbSubjectAmt(BigDecimal varJzzbSubjectAmt) {
        this.varJzzbSubjectAmt = varJzzbSubjectAmt;
    }

	public Map<String, BigDecimal> getVarYqfxSubjectAmt() {
		return varYqfxSubjectAmt;
	}

	public void setVarYqfxSubjectAmt(Map<String, BigDecimal> varYqfxSubjectAmt) {
		this.varYqfxSubjectAmt = varYqfxSubjectAmt;
	}

	public BigDecimal getVarQtyskLxSubjectSumAmt() {
		return varQtyskLxSubjectSumAmt;
	}

	public void setVarQtyskLxSubjectSumAmt(BigDecimal varQtyskLxSubjectSumAmt) {
		this.varQtyskLxSubjectSumAmt = varQtyskLxSubjectSumAmt;
	}

	public BigDecimal getVarQtyskBjSubjectSumAmt() {
		return varQtyskBjSubjectSumAmt;
	}

	public void setVarQtyskBjSubjectSumAmt(BigDecimal varQtyskBjSubjectSumAmt) {
		this.varQtyskBjSubjectSumAmt = varQtyskBjSubjectSumAmt;
	}

	public BigDecimal getVarYqfxSubjectSumAmt() {
		return varYqfxSubjectSumAmt;
	}

	public void setVarYqfxSubjectSumAmt(BigDecimal varYqfxSubjectSumAmt) {
		this.varYqfxSubjectSumAmt = varYqfxSubjectSumAmt;
	}

	public BigDecimal getVarJzzbSubjectAmtBccy() {
		return varJzzbSubjectAmtBccy;
	}

	public void setVarJzzbSubjectAmtBccy(BigDecimal varJzzbSubjectAmtBccy) {
		this.varJzzbSubjectAmtBccy = varJzzbSubjectAmtBccy;
	}

	public BigDecimal getGlb_tdyqtyskAmtSum() {
		return glb_tdyqtyskAmtSum;
	}

	public void setGlb_tdyqtyskAmtSum(BigDecimal glb_tdyqtyskAmtSum) {
		this.glb_tdyqtyskAmtSum = glb_tdyqtyskAmtSum;
	}

    public Map<String, BigDecimal> getJzrcbkmyeMap() {
        return jzrcbkmyeMap;
    }

    public void setJzrcbkmyeMap(Map<String, BigDecimal> jzrcbkmyeMap) {
        this.jzrcbkmyeMap = jzrcbkmyeMap;
    }

    public Map<String, BigDecimal> getJzrcbkmslMap() {
        return jzrcbkmslMap;
    }

    public void setJzrcbkmslMap(Map<String, BigDecimal> jzrcbkmslMap) {
        this.jzrcbkmslMap = jzrcbkmslMap;
    }

    public Map<String, BigDecimal> getJzryjlxyeMap() {
        return jzryjlxyeMap;
    }

    public void setJzryjlxyeMap(Map<String, BigDecimal> jzryjlxyeMap) {
        this.jzryjlxyeMap = jzryjlxyeMap;
    }

    public Map<String, BigDecimal> getJzryzjkmyeMap() {
        return jzryzjkmyeMap;
    }

    public void setJzryzjkmyeMap(Map<String, BigDecimal> jzryzjkmyeMap) {
        this.jzryzjkmyeMap = jzryzjkmyeMap;
    }

    public Map<String, BigDecimal> getJzrgyjzbdMap() {
        return jzrgyjzbdMap;
    }

    public void setJzrgyjzbdMap(Map<String, BigDecimal> jzrgyjzbdMap) {
        this.jzrgyjzbdMap = jzrgyjzbdMap;
    }

    public Map<String, BigDecimal> getJzrcbkmyeMapBccy() {
        return jzrcbkmyeMapBccy;
    }

    public void setJzrcbkmyeMapBccy(Map<String, BigDecimal> jzrcbkmyeMapBccy) {
        this.jzrcbkmyeMapBccy = jzrcbkmyeMapBccy;
    }

    public Map<String, BigDecimal> getJzrcbkmslMapBccy() {
        return jzrcbkmslMapBccy;
    }

    public void setJzrcbkmslMapBccy(Map<String, BigDecimal> jzrcbkmslMapBccy) {
        this.jzrcbkmslMapBccy = jzrcbkmslMapBccy;
    }

    public Map<String, BigDecimal> getJzryjlxyeMapBccy() {
        return jzryjlxyeMapBccy;
    }

    public void setJzryjlxyeMapBccy(Map<String, BigDecimal> jzryjlxyeMapBccy) {
        this.jzryjlxyeMapBccy = jzryjlxyeMapBccy;
    }

    public Map<String, BigDecimal> getJzryzjkmyeMapBccy() {
        return jzryzjkmyeMapBccy;
    }

    public void setJzryzjkmyeMapBccy(Map<String, BigDecimal> jzryzjkmyeMapBccy) {
        this.jzryzjkmyeMapBccy = jzryzjkmyeMapBccy;
    }

    public Map<String, BigDecimal> getJzrgyjzbdMapBccy() {
        return jzrgyjzbdMapBccy;
    }

    public void setJzrgyjzbdMapBccy(Map<String, BigDecimal> jzrgyjzbdMapBccy) {
        this.jzrgyjzbdMapBccy = jzrgyjzbdMapBccy;
    }
}
