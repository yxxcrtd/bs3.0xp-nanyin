/*
 * 文件名：CompareColDateDtoForExcel.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.checkcommon.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;

public class CompareColDateDtoForExcel  extends BaseEntity implements ITableRow  {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private String compareId;
    
    private String mySubjectId;
    
    private String mySubjectName;
    
    private String othersideSubjectId;
    
    private String othersideSubjectName;
    
    private String remark;

    private BigDecimal myData_A;
    private BigDecimal othersideData_A;
    private BigDecimal difference_A;
    
    private BigDecimal myData_B;
    private BigDecimal othersideData_B;
    private BigDecimal difference_B;
    
    private BigDecimal myData_C;
    private BigDecimal othersideData_C;
    private BigDecimal difference_C;
    
    private BigDecimal myData_D;
    private BigDecimal othersideData_D;
    private BigDecimal difference_D;
    
    private BigDecimal myData_E;
    private BigDecimal othersideData_E;
    private BigDecimal difference_E;
    
    private BigDecimal myData_F;
    private BigDecimal othersideData_F;
    private BigDecimal difference_F;
    
    private BigDecimal myData_G;
    private BigDecimal othersideData_G;
    private BigDecimal difference_G;
    
    private BigDecimal myData_H;
    private BigDecimal othersideData_H;
    private BigDecimal difference_H;
    
    private BigDecimal myData_I;
    private BigDecimal othersideData_I;
    private BigDecimal difference_I;
    
    private BigDecimal myData_J;
    private BigDecimal othersideData_J;
    private BigDecimal difference_J;
    
    private BigDecimal myData_K;
    private BigDecimal othersideData_K;
    private BigDecimal difference_K;
    
    private BigDecimal myData_L;
    private BigDecimal othersideData_L;
    private BigDecimal difference_L;
    
    private BigDecimal myData_M;
    private BigDecimal othersideData_M;
    private BigDecimal difference_M;
    
    private BigDecimal myData_N;
    private BigDecimal othersideData_N;
    private BigDecimal difference_N;
    
    private BigDecimal myData_O;
    private BigDecimal othersideData_O;
    private BigDecimal difference_O;
    
    private BigDecimal myData_P;
    private BigDecimal othersideData_P;
    private BigDecimal difference_P;
    
    private BigDecimal myData_Q;
    private BigDecimal othersideData_Q;
    private BigDecimal difference_Q;
    
    private BigDecimal myData_R;
    private BigDecimal othersideData_R;
    private BigDecimal difference_R;
    
    private BigDecimal myData_S;
    private BigDecimal othersideData_S;
    private BigDecimal difference_S;
    
    private BigDecimal myData_T;
    private BigDecimal othersideData_T;
    private BigDecimal difference_T;
    
    private BigDecimal myData_U;
    private BigDecimal othersideDataU;
    private BigDecimal difference_U;
    
    private BigDecimal myData_V;
    private BigDecimal othersideData_V;
    private BigDecimal difference_V;
    
    private BigDecimal myData_W;
    private BigDecimal othersideData_W;
    private BigDecimal difference_W;
    
    private BigDecimal myData_X;
    private BigDecimal othersideData_X;
    private BigDecimal difference_X;
    
    private BigDecimal myData_Y;
    private BigDecimal othersideData_Y;
    private BigDecimal difference_Y;
    
    private BigDecimal myData_Z;
    private BigDecimal othersideData_Z;
    private BigDecimal difference_Z;
    
    /**
     * 获取 compareId
     * @return compareId.
     */
    public String getCompareId() {
        return compareId;
    }
    
    /**
     * 设置 compareId
     * @param compareId compareId
     */
    public void setCompareId(String compareId) {
        this.compareId = compareId;
    }
    
    /**
     * 获取 mySubjectId
     * @return mySubjectId.
     */
    public String getMySubjectId() {
        return mySubjectId;
    }
    
    /**
     * 设置 mySubjectId
     * @param mySubjectId mySubjectId
     */
    public void setMySubjectId(String mySubjectId) {
        this.mySubjectId = mySubjectId;
    }
    
    /**
     * 获取 mySubjectName
     * @return mySubjectName.
     */
    public String getMySubjectName() {
        return mySubjectName;
    }
    
    /**
     * 设置 mySubjectName
     * @param mySubjectName mySubjectName
     */
    public void setMySubjectName(String mySubjectName) {
        this.mySubjectName = mySubjectName;
    }
    
    /**
     * 获取 othersideSubjectId
     * @return othersideSubjectId.
     */
    public String getOthersideSubjectId() {
        return othersideSubjectId;
    }
    
    /**
     * 设置 othersideSubjectId
     * @param othersideSubjectId othersideSubjectId
     */
    public void setOthersideSubjectId(String othersideSubjectId) {
        this.othersideSubjectId = othersideSubjectId;
    }
    
    /**
     * 获取 othersideSubjectName
     * @return othersideSubjectName.
     */
    public String getOthersideSubjectName() {
        return othersideSubjectName;
    }
    
    /**
     * 设置 othersideSubjectName
     * @param othersideSubjectName othersideSubjectName
     */
    public void setOthersideSubjectName(String othersideSubjectName) {
        this.othersideSubjectName = othersideSubjectName;
    }
    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * 获取 myData_A
     * @return myData_A.
     */
    public BigDecimal getMyData_A() {
        return myData_A;
    }
    
    /**
     * 设置 myData_A
     * @param myData_A myData_A
     */
    public void setMyData_A(BigDecimal myData_A) {
        this.myData_A = myData_A;
    }
    
    /**
     * 获取 othersideData_A
     * @return othersideData_A.
     */
    public BigDecimal getOthersideData_A() {
        return othersideData_A;
    }
    
    /**
     * 设置 othersideData_A
     * @param othersideData_A othersideData_A
     */
    public void setOthersideData_A(BigDecimal othersideData_A) {
        this.othersideData_A = othersideData_A;
    }
    
    /**
     * 获取 difference_A
     * @return difference_A.
     */
    public BigDecimal getDifference_A() {
        return difference_A;
    }
    
    /**
     * 设置 difference_A
     * @param difference_A difference_A
     */
    public void setDifference_A(BigDecimal difference_A) {
        this.difference_A = difference_A;
    }
    
    /**
     * 获取 myData_B
     * @return myData_B.
     */
    public BigDecimal getMyData_B() {
        return myData_B;
    }
    
    /**
     * 设置 myData_B
     * @param myData_B myData_B
     */
    public void setMyData_B(BigDecimal myData_B) {
        this.myData_B = myData_B;
    }
    
    /**
     * 获取 othersideData_B
     * @return othersideData_B.
     */
    public BigDecimal getOthersideData_B() {
        return othersideData_B;
    }
    
    /**
     * 设置 othersideData_B
     * @param othersideData_B othersideData_B
     */
    public void setOthersideData_B(BigDecimal othersideData_B) {
        this.othersideData_B = othersideData_B;
    }
    
    /**
     * 获取 difference_B
     * @return difference_B.
     */
    public BigDecimal getDifference_B() {
        return difference_B;
    }
    
    /**
     * 设置 difference_B
     * @param difference_B difference_B
     */
    public void setDifference_B(BigDecimal difference_B) {
        this.difference_B = difference_B;
    }
    
    /**
     * 获取 myData_C
     * @return myData_C.
     */
    public BigDecimal getMyData_C() {
        return myData_C;
    }
    
    /**
     * 设置 myData_C
     * @param myData_C myData_C
     */
    public void setMyData_C(BigDecimal myData_C) {
        this.myData_C = myData_C;
    }
    
    /**
     * 获取 othersideData_C
     * @return othersideData_C.
     */
    public BigDecimal getOthersideData_C() {
        return othersideData_C;
    }
    
    /**
     * 设置 othersideData_C
     * @param othersideData_C othersideData_C
     */
    public void setOthersideData_C(BigDecimal othersideData_C) {
        this.othersideData_C = othersideData_C;
    }
    
    /**
     * 获取 difference_C
     * @return difference_C.
     */
    public BigDecimal getDifference_C() {
        return difference_C;
    }
    
    /**
     * 设置 difference_C
     * @param difference_C difference_C
     */
    public void setDifference_C(BigDecimal difference_C) {
        this.difference_C = difference_C;
    }
    
    /**
     * 获取 myData_D
     * @return myData_D.
     */
    public BigDecimal getMyData_D() {
        return myData_D;
    }
    
    /**
     * 设置 myData_D
     * @param myData_D myData_D
     */
    public void setMyData_D(BigDecimal myData_D) {
        this.myData_D = myData_D;
    }
    
    /**
     * 获取 othersideData_D
     * @return othersideData_D.
     */
    public BigDecimal getOthersideData_D() {
        return othersideData_D;
    }
    
    /**
     * 设置 othersideData_D
     * @param othersideData_D othersideData_D
     */
    public void setOthersideData_D(BigDecimal othersideData_D) {
        this.othersideData_D = othersideData_D;
    }
    
    /**
     * 获取 difference_D
     * @return difference_D.
     */
    public BigDecimal getDifference_D() {
        return difference_D;
    }
    
    /**
     * 设置 difference_D
     * @param difference_D difference_D
     */
    public void setDifference_D(BigDecimal difference_D) {
        this.difference_D = difference_D;
    }
    
    /**
     * 获取 myData_E
     * @return myData_E.
     */
    public BigDecimal getMyData_E() {
        return myData_E;
    }
    
    /**
     * 设置 myData_E
     * @param myData_E myData_E
     */
    public void setMyData_E(BigDecimal myData_E) {
        this.myData_E = myData_E;
    }
    
    /**
     * 获取 othersideData_E
     * @return othersideData_E.
     */
    public BigDecimal getOthersideData_E() {
        return othersideData_E;
    }
    
    /**
     * 设置 othersideData_E
     * @param othersideData_E othersideData_E
     */
    public void setOthersideData_E(BigDecimal othersideData_E) {
        this.othersideData_E = othersideData_E;
    }
    
    /**
     * 获取 difference_E
     * @return difference_E.
     */
    public BigDecimal getDifference_E() {
        return difference_E;
    }
    
    /**
     * 设置 difference_E
     * @param difference_E difference_E
     */
    public void setDifference_E(BigDecimal difference_E) {
        this.difference_E = difference_E;
    }
    
    /**
     * 获取 myData_F
     * @return myData_F.
     */
    public BigDecimal getMyData_F() {
        return myData_F;
    }
    
    /**
     * 设置 myData_F
     * @param myData_F myData_F
     */
    public void setMyData_F(BigDecimal myData_F) {
        this.myData_F = myData_F;
    }
    
    /**
     * 获取 othersideData_F
     * @return othersideData_F.
     */
    public BigDecimal getOthersideData_F() {
        return othersideData_F;
    }
    
    /**
     * 设置 othersideData_F
     * @param othersideData_F othersideData_F
     */
    public void setOthersideData_F(BigDecimal othersideData_F) {
        this.othersideData_F = othersideData_F;
    }
    
    /**
     * 获取 difference_F
     * @return difference_F.
     */
    public BigDecimal getDifference_F() {
        return difference_F;
    }
    
    /**
     * 设置 difference_F
     * @param difference_F difference_F
     */
    public void setDifference_F(BigDecimal difference_F) {
        this.difference_F = difference_F;
    }
    
    /**
     * 获取 myData_G
     * @return myData_G.
     */
    public BigDecimal getMyData_G() {
        return myData_G;
    }
    
    /**
     * 设置 myData_G
     * @param myData_G myData_G
     */
    public void setMyData_G(BigDecimal myData_G) {
        this.myData_G = myData_G;
    }
    
    /**
     * 获取 othersideData_G
     * @return othersideData_G.
     */
    public BigDecimal getOthersideData_G() {
        return othersideData_G;
    }
    
    /**
     * 设置 othersideData_G
     * @param othersideData_G othersideData_G
     */
    public void setOthersideData_G(BigDecimal othersideData_G) {
        this.othersideData_G = othersideData_G;
    }
    
    /**
     * 获取 difference_G
     * @return difference_G.
     */
    public BigDecimal getDifference_G() {
        return difference_G;
    }
    
    /**
     * 设置 difference_G
     * @param difference_G difference_G
     */
    public void setDifference_G(BigDecimal difference_G) {
        this.difference_G = difference_G;
    }
    
    /**
     * 获取 myData_H
     * @return myData_H.
     */
    public BigDecimal getMyData_H() {
        return myData_H;
    }
    
    /**
     * 设置 myData_H
     * @param myData_H myData_H
     */
    public void setMyData_H(BigDecimal myData_H) {
        this.myData_H = myData_H;
    }
    
    /**
     * 获取 othersideData_H
     * @return othersideData_H.
     */
    public BigDecimal getOthersideData_H() {
        return othersideData_H;
    }
    
    /**
     * 设置 othersideData_H
     * @param othersideData_H othersideData_H
     */
    public void setOthersideData_H(BigDecimal othersideData_H) {
        this.othersideData_H = othersideData_H;
    }
    
    /**
     * 获取 difference_H
     * @return difference_H.
     */
    public BigDecimal getDifference_H() {
        return difference_H;
    }
    
    /**
     * 设置 difference_H
     * @param difference_H difference_H
     */
    public void setDifference_H(BigDecimal difference_H) {
        this.difference_H = difference_H;
    }
    
    /**
     * 获取 myData_I
     * @return myData_I.
     */
    public BigDecimal getMyData_I() {
        return myData_I;
    }
    
    /**
     * 设置 myData_I
     * @param myData_I myData_I
     */
    public void setMyData_I(BigDecimal myData_I) {
        this.myData_I = myData_I;
    }
    
    /**
     * 获取 othersideData_I
     * @return othersideData_I.
     */
    public BigDecimal getOthersideData_I() {
        return othersideData_I;
    }
    
    /**
     * 设置 othersideData_I
     * @param othersideData_I othersideData_I
     */
    public void setOthersideData_I(BigDecimal othersideData_I) {
        this.othersideData_I = othersideData_I;
    }
    
    /**
     * 获取 difference_I
     * @return difference_I.
     */
    public BigDecimal getDifference_I() {
        return difference_I;
    }
    
    /**
     * 设置 difference_I
     * @param difference_I difference_I
     */
    public void setDifference_I(BigDecimal difference_I) {
        this.difference_I = difference_I;
    }
    
    /**
     * 获取 myData_J
     * @return myData_J.
     */
    public BigDecimal getMyData_J() {
        return myData_J;
    }
    
    /**
     * 设置 myData_J
     * @param myData_J myData_J
     */
    public void setMyData_J(BigDecimal myData_J) {
        this.myData_J = myData_J;
    }
    
    /**
     * 获取 othersideData_J
     * @return othersideData_J.
     */
    public BigDecimal getOthersideData_J() {
        return othersideData_J;
    }
    
    /**
     * 设置 othersideData_J
     * @param othersideData_J othersideData_J
     */
    public void setOthersideData_J(BigDecimal othersideData_J) {
        this.othersideData_J = othersideData_J;
    }
    
    /**
     * 获取 difference_J
     * @return difference_J.
     */
    public BigDecimal getDifference_J() {
        return difference_J;
    }
    
    /**
     * 设置 difference_J
     * @param difference_J difference_J
     */
    public void setDifference_J(BigDecimal difference_J) {
        this.difference_J = difference_J;
    }
    
    /**
     * 获取 myData_K
     * @return myData_K.
     */
    public BigDecimal getMyData_K() {
        return myData_K;
    }
    
    /**
     * 设置 myData_K
     * @param myData_K myData_K
     */
    public void setMyData_K(BigDecimal myData_K) {
        this.myData_K = myData_K;
    }
    
    /**
     * 获取 othersideData_K
     * @return othersideData_K.
     */
    public BigDecimal getOthersideData_K() {
        return othersideData_K;
    }
    
    /**
     * 设置 othersideData_K
     * @param othersideData_K othersideData_K
     */
    public void setOthersideData_K(BigDecimal othersideData_K) {
        this.othersideData_K = othersideData_K;
    }
    
    /**
     * 获取 difference_K
     * @return difference_K.
     */
    public BigDecimal getDifference_K() {
        return difference_K;
    }
    
    /**
     * 设置 difference_K
     * @param difference_K difference_K
     */
    public void setDifference_K(BigDecimal difference_K) {
        this.difference_K = difference_K;
    }
    
    /**
     * 获取 myData_L
     * @return myData_L.
     */
    public BigDecimal getMyData_L() {
        return myData_L;
    }
    
    /**
     * 设置 myData_L
     * @param myData_L myData_L
     */
    public void setMyData_L(BigDecimal myData_L) {
        this.myData_L = myData_L;
    }
    
    /**
     * 获取 othersideData_L
     * @return othersideData_L.
     */
    public BigDecimal getOthersideData_L() {
        return othersideData_L;
    }
    
    /**
     * 设置 othersideData_L
     * @param othersideData_L othersideData_L
     */
    public void setOthersideData_L(BigDecimal othersideData_L) {
        this.othersideData_L = othersideData_L;
    }
    
    /**
     * 获取 difference_L
     * @return difference_L.
     */
    public BigDecimal getDifference_L() {
        return difference_L;
    }
    
    /**
     * 设置 difference_L
     * @param difference_L difference_L
     */
    public void setDifference_L(BigDecimal difference_L) {
        this.difference_L = difference_L;
    }
    
    /**
     * 获取 myData_M
     * @return myData_M.
     */
    public BigDecimal getMyData_M() {
        return myData_M;
    }
    
    /**
     * 设置 myData_M
     * @param myData_M myData_M
     */
    public void setMyData_M(BigDecimal myData_M) {
        this.myData_M = myData_M;
    }
    
    /**
     * 获取 othersideData_M
     * @return othersideData_M.
     */
    public BigDecimal getOthersideData_M() {
        return othersideData_M;
    }
    
    /**
     * 设置 othersideData_M
     * @param othersideData_M othersideData_M
     */
    public void setOthersideData_M(BigDecimal othersideData_M) {
        this.othersideData_M = othersideData_M;
    }
    
    /**
     * 获取 difference_M
     * @return difference_M.
     */
    public BigDecimal getDifference_M() {
        return difference_M;
    }
    
    /**
     * 设置 difference_M
     * @param difference_M difference_M
     */
    public void setDifference_M(BigDecimal difference_M) {
        this.difference_M = difference_M;
    }
    
    /**
     * 获取 myData_N
     * @return myData_N.
     */
    public BigDecimal getMyData_N() {
        return myData_N;
    }
    
    /**
     * 设置 myData_N
     * @param myData_N myData_N
     */
    public void setMyData_N(BigDecimal myData_N) {
        this.myData_N = myData_N;
    }
    
    /**
     * 获取 othersideData_N
     * @return othersideData_N.
     */
    public BigDecimal getOthersideData_N() {
        return othersideData_N;
    }
    
    /**
     * 设置 othersideData_N
     * @param othersideData_N othersideData_N
     */
    public void setOthersideData_N(BigDecimal othersideData_N) {
        this.othersideData_N = othersideData_N;
    }
    
    /**
     * 获取 difference_N
     * @return difference_N.
     */
    public BigDecimal getDifference_N() {
        return difference_N;
    }
    
    /**
     * 设置 difference_N
     * @param difference_N difference_N
     */
    public void setDifference_N(BigDecimal difference_N) {
        this.difference_N = difference_N;
    }
    
    /**
     * 获取 myData_O
     * @return myData_O.
     */
    public BigDecimal getMyData_O() {
        return myData_O;
    }
    
    /**
     * 设置 myData_O
     * @param myData_O myData_O
     */
    public void setMyData_O(BigDecimal myData_O) {
        this.myData_O = myData_O;
    }
    
    /**
     * 获取 othersideData_O
     * @return othersideData_O.
     */
    public BigDecimal getOthersideData_O() {
        return othersideData_O;
    }
    
    /**
     * 设置 othersideData_O
     * @param othersideData_O othersideData_O
     */
    public void setOthersideData_O(BigDecimal othersideData_O) {
        this.othersideData_O = othersideData_O;
    }
    
    /**
     * 获取 difference_O
     * @return difference_O.
     */
    public BigDecimal getDifference_O() {
        return difference_O;
    }
    
    /**
     * 设置 difference_O
     * @param difference_O difference_O
     */
    public void setDifference_O(BigDecimal difference_O) {
        this.difference_O = difference_O;
    }
    
    /**
     * 获取 myData_P
     * @return myData_P.
     */
    public BigDecimal getMyData_P() {
        return myData_P;
    }
    
    /**
     * 设置 myData_P
     * @param myData_P myData_P
     */
    public void setMyData_P(BigDecimal myData_P) {
        this.myData_P = myData_P;
    }
    
    /**
     * 获取 othersideData_P
     * @return othersideData_P.
     */
    public BigDecimal getOthersideData_P() {
        return othersideData_P;
    }
    
    /**
     * 设置 othersideData_P
     * @param othersideData_P othersideData_P
     */
    public void setOthersideData_P(BigDecimal othersideData_P) {
        this.othersideData_P = othersideData_P;
    }
    
    /**
     * 获取 difference_P
     * @return difference_P.
     */
    public BigDecimal getDifference_P() {
        return difference_P;
    }
    
    /**
     * 设置 difference_P
     * @param difference_P difference_P
     */
    public void setDifference_P(BigDecimal difference_P) {
        this.difference_P = difference_P;
    }
    
    /**
     * 获取 myData_Q
     * @return myData_Q.
     */
    public BigDecimal getMyData_Q() {
        return myData_Q;
    }
    
    /**
     * 设置 myData_Q
     * @param myData_Q myData_Q
     */
    public void setMyData_Q(BigDecimal myData_Q) {
        this.myData_Q = myData_Q;
    }
    
    /**
     * 获取 othersideData_Q
     * @return othersideData_Q.
     */
    public BigDecimal getOthersideData_Q() {
        return othersideData_Q;
    }
    
    /**
     * 设置 othersideData_Q
     * @param othersideData_Q othersideData_Q
     */
    public void setOthersideData_Q(BigDecimal othersideData_Q) {
        this.othersideData_Q = othersideData_Q;
    }
    
    /**
     * 获取 difference_Q
     * @return difference_Q.
     */
    public BigDecimal getDifference_Q() {
        return difference_Q;
    }
    
    /**
     * 设置 difference_Q
     * @param difference_Q difference_Q
     */
    public void setDifference_Q(BigDecimal difference_Q) {
        this.difference_Q = difference_Q;
    }
    
    /**
     * 获取 myData_R
     * @return myData_R.
     */
    public BigDecimal getMyData_R() {
        return myData_R;
    }
    
    /**
     * 设置 myData_R
     * @param myData_R myData_R
     */
    public void setMyData_R(BigDecimal myData_R) {
        this.myData_R = myData_R;
    }
    
    /**
     * 获取 othersideData_R
     * @return othersideData_R.
     */
    public BigDecimal getOthersideData_R() {
        return othersideData_R;
    }
    
    /**
     * 设置 othersideData_R
     * @param othersideData_R othersideData_R
     */
    public void setOthersideData_R(BigDecimal othersideData_R) {
        this.othersideData_R = othersideData_R;
    }
    
    /**
     * 获取 difference_R
     * @return difference_R.
     */
    public BigDecimal getDifference_R() {
        return difference_R;
    }
    
    /**
     * 设置 difference_R
     * @param difference_R difference_R
     */
    public void setDifference_R(BigDecimal difference_R) {
        this.difference_R = difference_R;
    }
    
    /**
     * 获取 myData_S
     * @return myData_S.
     */
    public BigDecimal getMyData_S() {
        return myData_S;
    }
    
    /**
     * 设置 myData_S
     * @param myData_S myData_S
     */
    public void setMyData_S(BigDecimal myData_S) {
        this.myData_S = myData_S;
    }
    
    /**
     * 获取 othersideData_S
     * @return othersideData_S.
     */
    public BigDecimal getOthersideData_S() {
        return othersideData_S;
    }
    
    /**
     * 设置 othersideData_S
     * @param othersideData_S othersideData_S
     */
    public void setOthersideData_S(BigDecimal othersideData_S) {
        this.othersideData_S = othersideData_S;
    }
    
    /**
     * 获取 difference_S
     * @return difference_S.
     */
    public BigDecimal getDifference_S() {
        return difference_S;
    }
    
    /**
     * 设置 difference_S
     * @param difference_S difference_S
     */
    public void setDifference_S(BigDecimal difference_S) {
        this.difference_S = difference_S;
    }
    
    /**
     * 获取 myData_T
     * @return myData_T.
     */
    public BigDecimal getMyData_T() {
        return myData_T;
    }
    
    /**
     * 设置 myData_T
     * @param myData_T myData_T
     */
    public void setMyData_T(BigDecimal myData_T) {
        this.myData_T = myData_T;
    }
    
    /**
     * 获取 othersideData_T
     * @return othersideData_T.
     */
    public BigDecimal getOthersideData_T() {
        return othersideData_T;
    }
    
    /**
     * 设置 othersideData_T
     * @param othersideData_T othersideData_T
     */
    public void setOthersideData_T(BigDecimal othersideData_T) {
        this.othersideData_T = othersideData_T;
    }
    
    /**
     * 获取 difference_T
     * @return difference_T.
     */
    public BigDecimal getDifference_T() {
        return difference_T;
    }
    
    /**
     * 设置 difference_T
     * @param difference_T difference_T
     */
    public void setDifference_T(BigDecimal difference_T) {
        this.difference_T = difference_T;
    }
    
    /**
     * 获取 myData_U
     * @return myData_U.
     */
    public BigDecimal getMyData_U() {
        return myData_U;
    }
    
    /**
     * 设置 myData_U
     * @param myData_U myData_U
     */
    public void setMyData_U(BigDecimal myData_U) {
        this.myData_U = myData_U;
    }
    
    /**
     * 获取 othersideDataU
     * @return othersideDataU.
     */
    public BigDecimal getOthersideDataU() {
        return othersideDataU;
    }
    
    /**
     * 设置 othersideDataU
     * @param othersideDataU othersideDataU
     */
    public void setOthersideDataU(BigDecimal othersideDataU) {
        this.othersideDataU = othersideDataU;
    }
    
    /**
     * 获取 difference_U
     * @return difference_U.
     */
    public BigDecimal getDifference_U() {
        return difference_U;
    }
    
    /**
     * 设置 difference_U
     * @param difference_U difference_U
     */
    public void setDifference_U(BigDecimal difference_U) {
        this.difference_U = difference_U;
    }
    
    /**
     * 获取 myData_V
     * @return myData_V.
     */
    public BigDecimal getMyData_V() {
        return myData_V;
    }
    
    /**
     * 设置 myData_V
     * @param myData_V myData_V
     */
    public void setMyData_V(BigDecimal myData_V) {
        this.myData_V = myData_V;
    }
    
    /**
     * 获取 othersideData_V
     * @return othersideData_V.
     */
    public BigDecimal getOthersideData_V() {
        return othersideData_V;
    }
    
    /**
     * 设置 othersideData_V
     * @param othersideData_V othersideData_V
     */
    public void setOthersideData_V(BigDecimal othersideData_V) {
        this.othersideData_V = othersideData_V;
    }
    
    /**
     * 获取 difference_V
     * @return difference_V.
     */
    public BigDecimal getDifference_V() {
        return difference_V;
    }
    
    /**
     * 设置 difference_V
     * @param difference_V difference_V
     */
    public void setDifference_V(BigDecimal difference_V) {
        this.difference_V = difference_V;
    }
    
    /**
     * 获取 myData_W
     * @return myData_W.
     */
    public BigDecimal getMyData_W() {
        return myData_W;
    }
    
    /**
     * 设置 myData_W
     * @param myData_W myData_W
     */
    public void setMyData_W(BigDecimal myData_W) {
        this.myData_W = myData_W;
    }
    
    /**
     * 获取 othersideData_W
     * @return othersideData_W.
     */
    public BigDecimal getOthersideData_W() {
        return othersideData_W;
    }
    
    /**
     * 设置 othersideData_W
     * @param othersideData_W othersideData_W
     */
    public void setOthersideData_W(BigDecimal othersideData_W) {
        this.othersideData_W = othersideData_W;
    }
    
    /**
     * 获取 difference_W
     * @return difference_W.
     */
    public BigDecimal getDifference_W() {
        return difference_W;
    }
    
    /**
     * 设置 difference_W
     * @param difference_W difference_W
     */
    public void setDifference_W(BigDecimal difference_W) {
        this.difference_W = difference_W;
    }
    
    /**
     * 获取 myData_X
     * @return myData_X.
     */
    public BigDecimal getMyData_X() {
        return myData_X;
    }
    
    /**
     * 设置 myData_X
     * @param myData_X myData_X
     */
    public void setMyData_X(BigDecimal myData_X) {
        this.myData_X = myData_X;
    }
    
    /**
     * 获取 othersideData_X
     * @return othersideData_X.
     */
    public BigDecimal getOthersideData_X() {
        return othersideData_X;
    }
    
    /**
     * 设置 othersideData_X
     * @param othersideData_X othersideData_X
     */
    public void setOthersideData_X(BigDecimal othersideData_X) {
        this.othersideData_X = othersideData_X;
    }
    
    /**
     * 获取 difference_X
     * @return difference_X.
     */
    public BigDecimal getDifference_X() {
        return difference_X;
    }
    
    /**
     * 设置 difference_X
     * @param difference_X difference_X
     */
    public void setDifference_X(BigDecimal difference_X) {
        this.difference_X = difference_X;
    }
    
    /**
     * 获取 myData_Y
     * @return myData_Y.
     */
    public BigDecimal getMyData_Y() {
        return myData_Y;
    }
    
    /**
     * 设置 myData_Y
     * @param myData_Y myData_Y
     */
    public void setMyData_Y(BigDecimal myData_Y) {
        this.myData_Y = myData_Y;
    }
    
    /**
     * 获取 othersideData_Y
     * @return othersideData_Y.
     */
    public BigDecimal getOthersideData_Y() {
        return othersideData_Y;
    }
    
    /**
     * 设置 othersideData_Y
     * @param othersideData_Y othersideData_Y
     */
    public void setOthersideData_Y(BigDecimal othersideData_Y) {
        this.othersideData_Y = othersideData_Y;
    }
    
    /**
     * 获取 difference_Y
     * @return difference_Y.
     */
    public BigDecimal getDifference_Y() {
        return difference_Y;
    }
    
    /**
     * 设置 difference_Y
     * @param difference_Y difference_Y
     */
    public void setDifference_Y(BigDecimal difference_Y) {
        this.difference_Y = difference_Y;
    }
    
    /**
     * 获取 myData_Z
     * @return myData_Z.
     */
    public BigDecimal getMyData_Z() {
        return myData_Z;
    }
    
    /**
     * 设置 myData_Z
     * @param myData_Z myData_Z
     */
    public void setMyData_Z(BigDecimal myData_Z) {
        this.myData_Z = myData_Z;
    }
    
    /**
     * 获取 othersideData_Z
     * @return othersideData_Z.
     */
    public BigDecimal getOthersideData_Z() {
        return othersideData_Z;
    }
    
    /**
     * 设置 othersideData_Z
     * @param othersideData_Z othersideData_Z
     */
    public void setOthersideData_Z(BigDecimal othersideData_Z) {
        this.othersideData_Z = othersideData_Z;
    }
    
    /**
     * 获取 difference_Z
     * @return difference_Z.
     */
    public BigDecimal getDifference_Z() {
        return difference_Z;
    }
    
    /**
     * 设置 difference_Z
     * @param difference_Z difference_Z
     */
    public void setDifference_Z(BigDecimal difference_Z) {
        this.difference_Z = difference_Z;
    }
    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
   
    
}
