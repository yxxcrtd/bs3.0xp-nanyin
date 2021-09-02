package com.joyintech.tams.model.impairment.batch.common.searchinfo.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.ModelDictCodeList;

import java.util.Date;

public class ViewRatingInfoDto extends BaseEntity {

    /**
     * 评级主体
     */
    private String ocode;
    /**
     * 评级类型
     */
    private String otype;

    /**
     *  评级机构类型：01最新；02指定
     */
    private String gradeOrgType;
    /**
     * 评级机构
     */
    private String gradeorgid;
    /**
     * 评级结果
     */
    private String mstratingresult01;
    /**
     * 查询日
     */
    private Date searchDate;

    public  ViewRatingInfoDto(){

    }
    public  ViewRatingInfoDto(String ocode, String gradeorgid, String otype, Date searchDate){
        this.ocode = ocode;
        this.gradeorgid = gradeorgid;
        this.otype = otype;
        this.searchDate = searchDate;
    }

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getGradeorgid() {
        return gradeorgid;
    }

    public void setGradeorgid(String gradeorgid) {
        this.gradeorgid = gradeorgid;
    }

    public String getMstratingresult01() {
        return mstratingresult01;
    }

    public void setMstratingresult01(String mstratingresult01) {
        this.mstratingresult01 = mstratingresult01;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
}
