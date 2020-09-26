/*
 * 文件名：BooksetInitResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokAcctSubjectInfo;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokDetailId;


public class BooksetInitResult {

    /**
     * 账套信息
     */
    private BokBookset valBokBookset;

    /**
     * 账套日期Dto
     */
    private BokBooksetDate valBokBooksetDate;

    /**
     * 账套配置
     */
    private List<BokBooksetExt> listValBokBooksetExt;

    /**
     * 账套科目信息
     */
    // private List<BokSubject> listValBokSubject; //modify by dongjun on 20181024

    /**
     * 会计场景
     */
    // private List<BokTablelist> listValBokTablelist;//modify by dongjun on 20181029

    /**
     * 会计元素
     */
    // private List<BokTabledict> listValBokTabledict;//modify by dongjun on 20181029

    /**
     * 场景科目关系
     */
    // private List<BokScenesubject> listValBokScenesubject;//modify by dongjun on 20181029

    /**
     * 场景分支配置
     */
    // private List<BokSceneconfig> listValBokSceneconfig;//modify by dongjun on 20181029

    /**
     * 账套估值方案
     */
    // private List<BokBooksetPlan> listValBooksetValPlan ;//modify by dongjun on 20181024

    /**
     * 核算主体信息
     */
    private BokAcctSubjectInfo acctSubjectInfo;

    /**
     * 账务明细代码
     */
    private BokDetailId bokDetailId;

    /**
     * 核算分层信息 add by wujie on 20181030
     */
    private BokAcctLayeringInfo acctLayeringInfo;

    /**
     * 产品估值方案
     */
    private BokBooksetPlan bokBooksetPlan;

    /**
     * 获取 bokBooksetPlan
     * 
     * @return bokBooksetPlan.
     */
    public BokBooksetPlan getBokBooksetPlan() {
        return bokBooksetPlan;
    }

    /**
     * 设置 bokBooksetPlan
     * 
     * @param bokBooksetPlan bokBooksetPlan
     */
    public void setBokBooksetPlan(BokBooksetPlan bokBooksetPlan) {
        this.bokBooksetPlan = bokBooksetPlan;
    }

    /**
     * 获取 valBokBookset
     * 
     * @return valBokBookset.
     */
    public BokBookset getValBokBookset() {
        return valBokBookset;
    }

    /**
     * 设置 valBokBookset
     * 
     * @param valBokBookset valBokBookset
     */
    public void setValBokBookset(BokBookset valBokBookset) {
        this.valBokBookset = valBokBookset;
    }

    /**
     * 获取 valBokBooksetDate
     * 
     * @return valBokBooksetDate.
     */
    public BokBooksetDate getValBokBooksetDate() {
        return valBokBooksetDate;
    }

    /**
     * 设置 valBokBooksetDate
     * 
     * @param valBokBooksetDate valBokBooksetDate
     */
    public void setValBokBooksetDate(BokBooksetDate valBokBooksetDate) {
        this.valBokBooksetDate = valBokBooksetDate;
    }

    /**
     * 获取 listValBokBooksetExt
     * 
     * @return listValBokBooksetExt.
     */
    public List<BokBooksetExt> getListValBokBooksetExt() {
        return listValBokBooksetExt;
    }

    /**
     * 设置 listValBokBooksetExt
     * 
     * @param listValBokBooksetExt listValBokBooksetExt
     */
    public void setListValBokBooksetExt(List<BokBooksetExt> listValBokBooksetExt) {
        this.listValBokBooksetExt = listValBokBooksetExt;
    }

    /** modify by dongjun on 20181024 start **/
    /**
     * 获取 listValBokSubject
     * 
     * @return listValBokSubject.
     */
    // public List<BokSubject> getListValBokSubject() {
    // return listValBokSubject;
    // }

    /**
     * 设置 listValBokSubject
     * 
     * @param listValBokSubject listValBokSubject
     */
    // public void setListValBokSubject(List<BokSubject> listValBokSubject) {
    // this.listValBokSubject = listValBokSubject;
    // }
    /** modify by dongjun on 20181024 end **/

    /**
     * 获取 listValBokTablelist
     * 
     * @return listValBokTablelist.
     */
    /** modify by dongjun on 20181029 start **/
    // public List<BokTablelist> getListValBokTablelist() {
    // return listValBokTablelist;
    // }
    //
    //
    // /**
    // * 设置 listValBokTablelist
    // * @param listValBokTablelist listValBokTablelist
    // */
    // public void setListValBokTablelist(List<BokTablelist> listValBokTablelist) {
    // this.listValBokTablelist = listValBokTablelist;
    // }
    //
    //
    // /**
    // * 获取 listValBokTabledict
    // * @return listValBokTabledict.
    // */
    // public List<BokTabledict> getListValBokTabledict() {
    // return listValBokTabledict;
    // }
    //
    //
    // /**
    // * 设置 listValBokTabledict
    // * @param listValBokTabledict listValBokTabledict
    // */
    // public void setListValBokTabledict(List<BokTabledict> listValBokTabledict) {
    // this.listValBokTabledict = listValBokTabledict;
    // }
    //
    //
    // /**
    // * 获取 listValBokScenesubject
    // * @return listValBokScenesubject.
    // */
    // public List<BokScenesubject> getListValBokScenesubject() {
    // return listValBokScenesubject;
    // }
    //
    //
    // /**
    // * 设置 listValBokScenesubject
    // * @param listValBokScenesubject listValBokScenesubject
    // */
    // public void setListValBokScenesubject(List<BokScenesubject> listValBokScenesubject) {
    // this.listValBokScenesubject = listValBokScenesubject;
    // }
    //
    //
    // /**
    // * 获取 listValBokSceneconfig
    // * @return listValBokSceneconfig.
    // */
    // public List<BokSceneconfig> getListValBokSceneconfig() {
    // return listValBokSceneconfig;
    // }
    //
    //
    // /**
    // * 设置 listValBokSceneconfig
    // * @param listValBokSceneconfig listValBokSceneconfig
    // */
    // public void setListValBokSceneconfig(List<BokSceneconfig> listValBokSceneconfig) {
    // this.listValBokSceneconfig = listValBokSceneconfig;
    // }
    /** modify by dongjun on 20181029 end **/

    /**
     * 获取 listValBooksetValPlan
     * 
     * @return listValBooksetValPlan.
     */
    /** modify by dongjun on 20181024 start **/
    // public List<BokBooksetPlan> getListValBooksetValPlan() {
    // return listValBooksetValPlan;
    // }

    /**
     * 设置 listValBooksetValPlan
     * 
     * @param listValBooksetValPlan listValBooksetValPlan
     */
    // public void setListValBooksetValPlan(List<BokBooksetPlan> listValBooksetValPlan) {
    // this.listValBooksetValPlan = listValBooksetValPlan;
    // }
    /** modify by dongjun on 20181024 end **/

    public BokAcctSubjectInfo getAcctSubjectInfo() {
        return acctSubjectInfo;
    }

    public void setAcctSubjectInfo(BokAcctSubjectInfo acctSubjectInfo) {
        this.acctSubjectInfo = acctSubjectInfo;
    }

    public BokDetailId getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(BokDetailId bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * @return the acctLayeringInfo
     */
    public BokAcctLayeringInfo getAcctLayeringInfo() {
        return acctLayeringInfo;
    }

    /**
     * @param acctLayeringInfo the acctLayeringInfo to set
     */
    public void setAcctLayeringInfo(BokAcctLayeringInfo acctLayeringInfo) {
        this.acctLayeringInfo = acctLayeringInfo;
    }

}
