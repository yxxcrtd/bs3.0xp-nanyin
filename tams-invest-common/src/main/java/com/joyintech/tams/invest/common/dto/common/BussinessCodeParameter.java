package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

import java.util.Map;

/**
 * 业务编号生成规则参数 〈功能详细描述〉
 * 
 * @author dingzhipeng
 * @version 1.0 2018年8月20日
 * @see BussinessCodeParameter
 * @since 1.0
 */
public class BussinessCodeParameter {
    /**
     * 业务编号规则序号
     */
    private String seqNo;

    /**
     * 共通业务类型
     */
    private DictCodeList commonBusinessType;
    //-----------add by jjpei 20190627---------
    private BussinessDictCodeList commonBusinessType1;
    /**
     * 共通业务类型EXT
     */
    private Map<String,Object> commonBusinessExtType;
    //-----------add by jjpei 20190627---------
    /**
     * 一级分类
     */
    private DictCodeList type1;

    /**
     * 二级分类
     */
    private DictCodeList type2;

    /**
     * 三级分类
     */
    private DictCodeList type3;

    /**
     * 四级分类
     */
    private DictCodeList type4;

    /**
     * 五级分类
     */
    private DictCodeList type5;

    /**
     * 六级分类
     */
    private DictCodeList type6;

    public BussinessCodeParameter() {
    }

    public BussinessCodeParameter(DictCodeList commonBusinessType) {
        this.commonBusinessType = commonBusinessType;
    }

    /**
     * 获取 commonBusinessType
     * 
     * @return commonBusinessType.
     */
    public DictCodeList getCommonBusinessType() {
        return commonBusinessType;
    }

    /**
     * 设置 commonBusinessType
     * 
     * @param commonBusinessType commonBusinessType
     */
    public void setCommonBusinessType(DictCodeList commonBusinessType) {
        this.commonBusinessType = commonBusinessType;
    }

    /**
     * 设置 commonBusinessType
     *
     * @param commonBusinessType commonBusinessType
     */
    public void setCommonBusinessType1(BussinessDictCodeList commonBusinessType) {
        this.commonBusinessType1 = commonBusinessType;
    }

    /**
     * 获取 type1
     * 
     * @return type1.
     */
    public DictCodeList getType1() {
        return type1;
    }

    /**
     * 设置 type1
     * 
     * @param type1 type1
     */
    public void setType1(DictCodeList type1) {
        this.type1 = type1;
    }

    /**
     * 获取 type2
     * 
     * @return type2.
     */
    public DictCodeList getType2() {
        return type2;
    }

    /**
     * 设置 type2
     * 
     * @param type2 type2
     */
    public void setType2(DictCodeList type2) {
        this.type2 = type2;
    }

    /**
     * 获取 type3
     * 
     * @return type3.
     */
    public DictCodeList getType3() {
        return type3;
    }

    /**
     * 设置 type3
     * 
     * @param type3 type3
     */
    public void setType3(DictCodeList type3) {
        this.type3 = type3;
    }

    /**
     * 获取 type4
     * 
     * @return type4.
     */
    public DictCodeList getType4() {
        return type4;
    }

    /**
     * 设置 type4
     * 
     * @param type4 type4
     */
    public void setType4(DictCodeList type4) {
        this.type4 = type4;
    }

    /**
     * 获取 type5
     * 
     * @return type5.
     */
    public DictCodeList getType5() {
        return type5;
    }

    /**
     * 设置 type5
     * 
     * @param type5 type5
     */
    public void setType5(DictCodeList type5) {
        this.type5 = type5;
    }

    /**
     * 获取 type6
     * 
     * @return type6.
     */
    public DictCodeList getType6() {
        return type6;
    }

    /**
     * 设置 type6
     * 
     * @param type6 type6
     */
    public void setType6(DictCodeList type6) {
        this.type6 = type6;
    }

    /**
     * 获取 seqNo
     *
     * @return seqNo
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 seqNo
     *
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Map<String, Object> getCommonBusinessExtType() {
        return commonBusinessExtType;
    }

    public void setCommonBusinessExtType(Map<String, Object> commonBusinessExtType) {
        this.commonBusinessExtType = commonBusinessExtType;
    }
}
