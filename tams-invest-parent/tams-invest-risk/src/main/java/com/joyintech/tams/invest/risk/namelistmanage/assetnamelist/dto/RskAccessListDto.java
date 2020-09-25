package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskAccessList;
import com.joyintech.tams.common.base.entity.WflRskAccessList;
import com.joyintech.tams.common.base.entity.WflRskAccessListDetail;
import com.joyintech.tams.common.base.entity.WflRskAccessListLog;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.valid.AssetNameManagementValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("AssetNameManagement003")
@ValidLogic(targetClass = AssetNameManagementValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class RskAccessListDto extends RskAccessList implements ITableRow, ProcessEntity {
    /**
     * 操作类型
     */
    private String operateType;

    private String pageModel;

    /**
     * 选择数据
     */
    private List<RskAccessListDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 操作方式，手工、批量
     */
    private String operateStyle;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     *名单明细
     */
    private List<RskAccessListDetailDto> rskAccessListDetailList;

    /**
     * 履历明细
     */
    private List<RskAccessListLogDto> rskAccessListLogDtoList;

    /**
     * 名单(业务流程表)
     * @return
     */
    private WflRskAccessList  wflRskAccessList;

    /**
     * 名单明细(业务流程表)
     */
    private List<WflRskAccessListDetail> wflRskAccessListDetail;

    /**
     * 名单履历(业务流程表)
     * @return
     */
    private List<WflRskAccessListLog> wflRskAccessListLog;
    
    /**
     * 新增条数
     */
    private int addNum;
    /**
     * 修改条数
     */
    private int editNum;
    /**
     * 删除条数
     */
    private int deleteNum;
    
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 披露文档代码
     */
    private String revealFileId;
    /**
     * 导入方式
     */
    private String inputType;

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getListId(){
        return super.getListId();
    }

    public List<RskAccessListDetailDto> getRskAccessListDetailList() {
        return rskAccessListDetailList;
    }


    public void setRskAccessListDetailList(List<RskAccessListDetailDto> rskAccessListDetailList) {
        this.rskAccessListDetailList = rskAccessListDetailList;
    }

    public List<RskAccessListLogDto> getRskAccessListLogDtoList() {
        return rskAccessListLogDtoList;
    }

    public void setRskAccessListLogDtoList(List<RskAccessListLogDto> rskAccessListLogDtoList) {
        this.rskAccessListLogDtoList = rskAccessListLogDtoList;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public List<RskAccessListDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RskAccessListDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public WflRskAccessList getWflRskAccessList() {
        return wflRskAccessList;
    }

    public void setWflRskAccessList(WflRskAccessList wflRskAccessList) {
        this.wflRskAccessList = wflRskAccessList;
    }

    public List<WflRskAccessListDetail> getWflRskAccessListDetail() {
        return wflRskAccessListDetail;
    }

    public void setWflRskAccessListDetail(List<WflRskAccessListDetail> wflRskAccessListDetail) {
        this.wflRskAccessListDetail = wflRskAccessListDetail;
    }

    public List<WflRskAccessListLog> getWflRskAccessListLog() {
        return wflRskAccessListLog;
    }

    public void setWflRskAccessListLog(List<WflRskAccessListLog> wflRskAccessListLog) {
        this.wflRskAccessListLog = wflRskAccessListLog;
    }

    public String getOperateStyle() {
        return operateStyle;
    }

    public void setOperateStyle(String operateStyle) {
        this.operateStyle = operateStyle;
    }

    
    /**
     * 获取 addNum
     * @return addNum.
     */
    public int getAddNum() {
        return addNum;
    }

    
    /**
     * 设置 addNum
     * @param addNum addNum
     */
    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    
    /**
     * 获取 editNum
     * @return editNum.
     */
    public int getEditNum() {
        return editNum;
    }

    
    /**
     * 设置 editNum
     * @param editNum editNum
     */
    public void setEditNum(int editNum) {
        this.editNum = editNum;
    }

    
    /**
     * 获取 deleteNum
     * @return deleteNum.
     */
    public int getDeleteNum() {
        return deleteNum;
    }

    
    /**
     * 设置 deleteNum
     * @param deleteNum deleteNum
     */
    public void setDeleteNum(int deleteNum) {
        this.deleteNum = deleteNum;
    }

    
    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    
    /**
     * 设置 fileName
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    /**
     * 获取 revealFileId
     * @return revealFileId.
     */
    public String getRevealFileId() {
        return revealFileId;
    }

    
    /**
     * 设置 revealFileId
     * @param revealFileId revealFileId
     */
    public void setRevealFileId(String revealFileId) {
        this.revealFileId = revealFileId;
    }

    
    /**
     * 获取 inputType
     * @return inputType.
     */
    public String getInputType() {
        return inputType;
    }

    
    /**
     * 设置 inputType
     * @param inputType inputType
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
    

    public String getListTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.名单属性, this.getListType());
    }

    public String getListLevelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.名单级别, this.getListLevel());
    }
}
