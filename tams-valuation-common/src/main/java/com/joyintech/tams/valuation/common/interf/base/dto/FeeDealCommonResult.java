package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import org.apache.shiro.crypto.hash.Hash;

/**
 * 费用交易 处理返回共通结果 〈功能详细描述〉
 * 
 * @author huwei
 * @version 1.0 2018年10月16日
 * @see FeeDealCommonResult
 * @since 1.0
 */
public class FeeDealCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 金融产品交易
     */
    private List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();

    /**
     * 估值核算交易扩展表
     */
    private List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();

    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();

    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();

    /**
     * 账务明细代码表
     */
    private List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();

    /**
     * 交易费用明细表
     */
    private List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();

    /**
     * 结算指令
     */
    private List<StlOrders> listValOrders = new ArrayList<StlOrders>();

    private List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();

    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    /**
     * 交易类金融产品表
     */
    List<FinTradeProduct> listFinTradeProduct = new ArrayList<FinTradeProduct>();

    /**
     * 交易类金融产品附属表
     */
    List<FinTradeProductAdd> listFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();

    /**
     * 金融产品分类List
     */
    List<FinProductType> listFinProductType = new ArrayList<FinProductType>();

    private Map<String, Object> paramMap = new HashMap<String,Object>();

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取 listValFinProductDeal
     * 
     * @return listValFinProductDeal.
     */
    public List<TrdProductDeal> getListValFinProductDeal() {
        return listValFinProductDeal;
    }

    /**
     * 设置 listValFinProductDeal
     * 
     * @param listValFinProductDeal listValFinProductDeal
     */
    public void setListValFinProductDeal(List<TrdProductDeal> listValFinProductDeal) {
        this.listValFinProductDeal = listValFinProductDeal;
    }

    /**
     * 获取 listValFinProductDealExt
     * 
     * @return listValFinProductDealExt.
     */
    public List<TrdProductDealAdd> getListValFinProductDealExt() {
        return listValFinProductDealExt;
    }

    /**
     * 设置 listValFinProductDealExt
     * 
     * @param listValFinProductDealExt listValFinProductDealExt
     */
    public void setListValFinProductDealExt(List<TrdProductDealAdd> listValFinProductDealExt) {
        this.listValFinProductDealExt = listValFinProductDealExt;
    }

    /**
     * 获取 listValDealAccounting
     * 
     * @return listValDealAccounting.
     */
    public List<BokDealAccounting> getListValDealAccounting() {
        return listValDealAccounting;
    }

    /**
     * 设置 listValDealAccounting
     * 
     * @param listValDealAccounting listValDealAccounting
     */
    public void setListValDealAccounting(List<BokDealAccounting> listValDealAccounting) {
        this.listValDealAccounting = listValDealAccounting;
    }

    /**
     * 获取 listValDealAccountingDetail
     * 
     * @return listValDealAccountingDetail.
     */
    public List<BokDealAccountingDetail> getListValDealAccountingDetail() {
        return listValDealAccountingDetail;
    }

    /**
     * 设置 listValDealAccountingDetail
     * 
     * @param listValDealAccountingDetail listValDealAccountingDetail
     */
    public void setListValDealAccountingDetail(List<BokDealAccountingDetail> listValDealAccountingDetail) {
        this.listValDealAccountingDetail = listValDealAccountingDetail;
    }

    /**
     * 获取 listValBokDetailId
     * 
     * @return listValBokDetailId.
     */
    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }

    /**
     * 设置 listValBokDetailId
     * 
     * @param listValBokDetailId listValBokDetailId
     */
    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
    }

    /**
     * 获取 listValTradeFeeDetail
     * 
     * @return listValTradeFeeDetail.
     */
    public List<TrdTradeFeeDetail> getListValTradeFeeDetail() {
        return listValTradeFeeDetail;
    }

    /**
     * 设置 listValTradeFeeDetail
     * 
     * @param listValTradeFeeDetail listValTradeFeeDetail
     */
    public void setListValTradeFeeDetail(List<TrdTradeFeeDetail> listValTradeFeeDetail) {
        this.listValTradeFeeDetail = listValTradeFeeDetail;
    }

    /**
     * 获取 listValOrders
     * 
     * @return listValOrders.
     */
    public List<StlOrders> getListValOrders() {
        return listValOrders;
    }

    /**
     * 设置 listValOrders
     * 
     * @param listValOrders listValOrders
     */
    public void setListValOrders(List<StlOrders> listValOrders) {
        this.listValOrders = listValOrders;
    }

    /**
     * 获取 listValOrdersExt
     * 
     * @return listValOrdersExt.
     */
    public List<StlOrdersExt> getListValOrdersExt() {
        return listValOrdersExt;
    }

    /**
     * 设置 listValOrdersExt
     * 
     * @param listValOrdersExt listValOrdersExt
     */
    public void setListValOrdersExt(List<StlOrdersExt> listValOrdersExt) {
        this.listValOrdersExt = listValOrdersExt;
    }

    /**
     * 获取 valInterflog
     * 
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 设置 valInterflog
     * 
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private int datanum;// 读取文件数

    /**
     * @return the datanum
     */
    public int getDatanum() {
        return datanum;
    }

    /**
     * @param datanum the datanum to set
     */
    public void setDatanum(int datanum) {
        this.datanum = datanum;
    }

    /**
     * 获取 交易类金融产品表
     * 
     * @return 交易类金融产品表
     */
    public List<FinTradeProduct> getListFinTradeProduct() {
        return listFinTradeProduct;
    }

    /**
     * 设置 交易类金融产品表
     * 
     * @param listFinTradeProduct 交易类金融产品表
     */
    public void setListFinTradeProduct(List<FinTradeProduct> listFinTradeProduct) {
        this.listFinTradeProduct = listFinTradeProduct;
    }

    /**
     * 获取 交易类金融产品附属表
     * 
     * @return 交易类金融产品附属表
     */
    public List<FinTradeProductAdd> getListFinTradeProductAdd() {
        return listFinTradeProductAdd;
    }

    /**
     * 设置 交易类金融产品附属表
     * 
     * @param listFinTradeProductAdd 交易类金融产品附属表
     */
    public void setListFinTradeProductAdd(List<FinTradeProductAdd> listFinTradeProductAdd) {
        this.listFinTradeProductAdd = listFinTradeProductAdd;
    }

    /**
     * 获取 金融产品分类List
     * 
     * @return 金融产品分类List
     */
    public List<FinProductType> getListFinProductType() {
        return listFinProductType;
    }

    /**
     * 设置 金融产品分类List
     * 
     * @param listFinProductType 金融产品分类List
     */
    public void setListFinProductType(List<FinProductType> listFinProductType) {
        this.listFinProductType = listFinProductType;
    }

}
