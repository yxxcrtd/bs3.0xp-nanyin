/*
 * 文件名：MstCaptialAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstcaptialaccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.StlCapacctInfoMapper;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstcaptialaccount.dao.BasicMstCaptialAccountMapper;
import com.joyintech.service.basicdata.mstcaptialaccount.dto.MstCaptialAccountDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;

/**
 * 
 * 资金账户
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see MstCaptialAccountService
 * @since 1.0
 */
@Service
public class MstCaptialAccountService extends BaseService<MstCaptialAccountDto>{
    
    /**
     * 附件信息共通Service
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    
    /**
     * 资金账户管理mapper
     */
    @Autowired
    private BasicMstCaptialAccountMapper basicMstCaptialAccountMapper;

    /**
     * 资金账户管理mapper(自动生成)
     */
    @Autowired
    private StlCapacctInfoMapper stlCapacctInfoMapper;

    /**
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     * @param length 
     * @param page 
     *
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<MstCaptialAccountDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> basicMstCaptialAccountMapper.select(pageParam));
    }

    /**
     * 联想文本框service层实现
     *
     * @param map 检索参数
     * @return SuggestDto
     */
    public List<SuggestDto> getAcctName(String acctName) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("acctName", acctName);
        return basicMstCaptialAccountMapper.getAcctName(map);
    }

    /**
     * 根据网点账号与币种查询详细信息 双击修改页面
     * 
     * @param map 网点账号与币种
     * @return 资金账户实体
     * @see
     * @since 1.0
     */
    public MstCaptialAccountDto selectDetail(Map<String, Object> map) {
        return basicMstCaptialAccountMapper.selectDetail(map);
    }

    /**
     * 实现docomplement方法
     */
    public void doComplete(MstCaptialAccountDto entity) {
        String type = entity.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
//            entity.setAcctNo(entity.getLargePayno()+":"+entity.getBusiAcctNo());
            //资金账户删除
            stlCapacctInfoMapper.deleteByPrimaryKey(entity);
            
        }
        else if (StringUtil.equals(type, "NEW")) {
//            entity.setAcctNo(entity.getLargePayno()+":" + entity.getBusiAcctNo());
            stlCapacctInfoMapper.insert(entity);
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            for(SysUploadFileInfo fileArray:entity.getFileArray()){
                fileArray.setSeqNo(fileArray.getSeqNo());
            }
            mstFileInputParameter.setFileList(entity.getFileArray());
            mstFileInputParameter.setBussinessId(entity.getLargePayno()+":"+entity.getBusiAcctNo());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        else {
            //资金账户更新
//            entity.setAcctNo(entity.getLargePayno()+":"+entity.getBusiAcctNo());
            stlCapacctInfoMapper.updateByPrimaryKeySelective(entity);
 
            //上传文件修改更新
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            for(SysUploadFileInfo fileArray:entity.getFileArray()){
                fileArray.setSeqNo(fileArray.getSeqNo());//生成流水号
            }
            mstFileInputParameter.setBussinessId(entity.getLargePayno()+":"+entity.getBusiAcctNo());
            mstFileInputParameter.setFileList(entity.getFileArray());
            if(mstFileCommonService != null){
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
    }

    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto 业务实体
     * @return 查询条数
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstCaptialAccountDto dto) {
        int i = basicMstCaptialAccountMapper.checkCodeIsUse(dto);
        return i;
    }

    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item 资金账户实体集合
     * @return 返回查询的所有条数
     * @see
     * @since 1.0
     */
    public List<MstCaptialAccountDto> checkAllCodeIsUse(List<MstCaptialAccountDto> item) {
        return basicMstCaptialAccountMapper.checkAllCodeIsUse(item);
    }

//    /**
//     * 
//     * {@inheritDoc}
//     */
//    @Override
//    protected void buildExportParam(ExportParam exportParam) {
//        exportParam.config("/com/joyintech/basicdata/mstcaptialaccount/conf/export_conf.xml").select(
//            () -> mstCaptialAccountMapper.select(exportParam.getSelectParam())).dataSource(
//                DataSourceType.MASERT);
//    }

    
    /**
     * 
     * 查询上传文件
     * <功能详细描述>
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId){
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(businessId);
        if(mstFileCommonService != null){
            MstFileOutputParameter output = mstFileCommonService.getFileInfoByBusiId(businessId);
            return output.getUploadFileList();
        }
        else{
            return null;
        }
    }

    /**
     * 查询 
     * <功能详细描述>
     * @param map 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<MstCaptialAccountDto> select(Map<String, Object> map) {
        return basicMstCaptialAccountMapper.select(map);
    }
    
    public List<MstCaptialAccountDto> selectCheck(Map<String, Object> map) {
        return basicMstCaptialAccountMapper.selectCheck(map);
    }

    public int updateTitle(Map<String, Object> map){
        return basicMstCaptialAccountMapper.updateTitle(map);
    }
    
}
