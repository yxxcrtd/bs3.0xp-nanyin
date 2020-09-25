
package com.joyintech.service.risk.stresstestmanage.riskfactor.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.CreditAssetpool;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor003selectDto
 * @since 1.0
 */

public class RiskFactor003selectDto extends CreditAssetpool implements ITableRow, ProcessEntity {

    private String ftrid;
    private String configid;
    private String inputvalue;
    private String tablename;
    private String selectcolumn;
    private String selectcriteria;
    private String inputcolumn;
    private String dateperiod;
    private String asccolumn;
    private String desccolumn;
    private Date sdate;
    private Date edate;
    private String conditionext;

    public String getConditionext() {
        return conditionext;
    }

    public void setConditionext(String conditionext) {
        this.conditionext = conditionext;
    }

    /**
     * 获取 sdate
     *
     * @return sdate.
     */
    public Date getSdate() {
        return sdate;
    }


    /**
     * 设置 sdate
     *
     * @param sdate sdate
     */
    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }


    /**
     * 获取 edate
     *
     * @return edate.
     */
    public Date getEdate() {
        return edate;
    }


    /**
     * 设置 edate
     *
     * @param edate edate
     */
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * 获取 ftrid
     *
     * @return ftrid.
     */
    public String getFtrid() {
        return ftrid;
    }

    /**
     * 设置 ftrid
     *
     * @param ftrid ftrid
     */
    public void setFtrid(String ftrid) {
        this.ftrid = ftrid;
    }

    /**
     * 获取 configid
     *
     * @return configid.
     */
    public String getConfigid() {
        return configid;
    }

    /**
     * 设置 configid
     *
     * @param configid configid
     */
    public void setConfigid(String configid) {
        this.configid = configid;
    }

    /**
     * 获取 inputvalue
     *
     * @return inputvalue.
     */
    public String getInputvalue() {
        return inputvalue;
    }

    /**
     * 设置 inputvalue
     *
     * @param inputvalue inputvalue
     */
    public void setInputvalue(String inputvalue) {
        this.inputvalue = inputvalue;
    }

    /**
     * 获取 tablename
     *
     * @return tablename.
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * 设置 tablename
     *
     * @param tablename tablename
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * 获取 selectcolumn
     *
     * @return selectcolumn.
     */
    public String getSelectcolumn() {
        return selectcolumn;
    }

    /**
     * 设置 selectcolumn
     *
     * @param selectcolumn selectcolumn
     */
    public void setSelectcolumn(String selectcolumn) {
        this.selectcolumn = selectcolumn;
    }

    /**
     * 获取 selectcriteria
     *
     * @return selectcriteria.
     */
    public String getSelectcriteria() {
        return selectcriteria;
    }

    /**
     * 设置 selectcriteria
     *
     * @param selectcriteria selectcriteria
     */
    public void setSelectcriteria(String selectcriteria) {
        this.selectcriteria = selectcriteria;
    }

    /**
     * 获取 inputcolumn
     *
     * @return inputcolumn.
     */
    public String getInputcolumn() {
        return inputcolumn;
    }

    /**
     * 设置 inputcolumn
     *
     * @param inputcolumn inputcolumn
     */
    public void setInputcolumn(String inputcolumn) {
        this.inputcolumn = inputcolumn;
    }

    /**
     * 获取 dateperiod
     *
     * @return dateperiod.
     */
    public String getDateperiod() {
        return dateperiod;
    }

    /**
     * 设置 dateperiod
     *
     * @param dateperiod dateperiod
     */
    public void setDateperiod(String dateperiod) {
        this.dateperiod = dateperiod;
    }

    /**
     * 获取 asccolumn
     *
     * @return asccolumn.
     */
    public String getAsccolumn() {
        return asccolumn;
    }

    /**
     * 设置 asccolumn
     *
     * @param asccolumn asccolumn
     */
    public void setAsccolumn(String asccolumn) {
        this.asccolumn = asccolumn;
    }

    /**
     * 获取 desccolumn
     *
     * @return desccolumn.
     */
    public String getDesccolumn() {
        return desccolumn;
    }

    /**
     * 设置 desccolumn
     *
     * @param desccolumn desccolumn
     */
    public void setDesccolumn(String desccolumn) {
        this.desccolumn = desccolumn;
    }


}
