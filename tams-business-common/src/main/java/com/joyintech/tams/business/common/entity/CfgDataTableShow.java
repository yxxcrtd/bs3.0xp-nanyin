package com.joyintech.tams.business.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "数据表格设置实体类")
public class CfgDataTableShow {

    @ApiModelProperty(name = "pageCode", value = "页面编码")
    private String pageCode;

    @ApiModelProperty(name = "dtCode", value = "数据表格编号")
    private String dtCode;

    @ApiModelProperty(name = "colCode", value = "列的字段名")
    private String colCode;

    @ApiModelProperty(name = "pageName", value = "页面名称")
    private String pageName;

    @ApiModelProperty(name = "dtnName", value = "数据表格名称")
    private String dtnName;

    @ApiModelProperty(name = "colName", value = "列的名称")
    private String colName;

    @ApiModelProperty(name = "dictCode", value = "字典值的字段名")
    private String dictCode;

    @ApiModelProperty(name = "seqNo", value = "顺序号")
    private Long seqNo;

    @ApiModelProperty(name = "disPlayed", value = "是否显示(0:不显示,1:显示)")
    private Long disPlayed;

    @ApiModelProperty(name = "frozen", value = "是否冻结(0:不冻结,1:冻结)")
    private Long frozen;

    @ApiModelProperty(name = "minLen", value = "是否排序")
    private Long sort;

    @ApiModelProperty(name = "maxLen", value = "最大长度(px单位)")
    private Long maxLen;

    @ApiModelProperty(name = "extendCfg", value = "扩张配置")
    private String extendCfg;

    @ApiModelProperty(name = "effeRange", value = "有效范围(0:全局,1:个人)")
    private Long effeRange;

    @ApiModelProperty(name = "rangeId", value = "范围ID(见有效范围字段)")
    private String rangeId;

    @ApiModelProperty(name = "mdTime", value = "更新时间(yyyy-MM-dd HH:mm:ss)")
    private String mdTime;

    @ApiModelProperty(name = "status", value = "可用状态(0:不可用,1:可用)")
    private Long status;

    @ApiModelProperty(name = "remark1", value = "备注1")
    private Long remark1;

    @ApiModelProperty(name = "remark2", value = "备注2")
    private String remark2;

    @ApiModelProperty(name = "remark3", value = "备注3")
    private String remark3;

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getDtCode() {
        return dtCode;
    }

    public void setDtCode(String dtCode) {
        this.dtCode = dtCode;
    }

    public String getColCode() {
        return colCode;
    }

    public void setColCode(String colCode) {
        this.colCode = colCode;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getDtnName() {
        return dtnName;
    }

    public void setDtnName(String dtnName) {
        this.dtnName = dtnName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public Long getDisPlayed() {
        return disPlayed;
    }

    public void setDisPlayed(Long disPlayed) {
        this.disPlayed = disPlayed;
    }

    public Long getFrozen() {
        return frozen;
    }

    public void setFrozen(Long frozen) {
        this.frozen = frozen;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(Long maxLen) {
        this.maxLen = maxLen;
    }

    public String getExtendCfg() {
        return extendCfg;
    }

    public void setExtendCfg(String extendCfg) {
        this.extendCfg = extendCfg;
    }

    public Long getEffeRange() {
        return effeRange;
    }

    public void setEffeRange(Long effeRange) {
        this.effeRange = effeRange;
    }

    public String getRangeId() {
        return rangeId;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }

    public String getMdTime() {
        return mdTime;
    }

    public void setMdTime(String mdTime) {
        this.mdTime = mdTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getRemark1() {
        return remark1;
    }

    public void setRemark1(Long remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
}
