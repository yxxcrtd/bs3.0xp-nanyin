/*
 * 文件名：DocumentManagementService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.document.documentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.document.documentmanagement.dao.DocumentManagementMapper;
import com.joyintech.service.document.documentmanagement.dto.DocumentManagementDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月16日
 * @see DocumentManagementService
 * @since 1.0
 */
@Service
public class DocumentManagementService  extends BaseService<DocumentManagementDto>{
    @Autowired
    private DocumentManagementMapper documentManagementMapper;
    /**
     * 文件上传
     */
    @Autowired
    private MstFileCommonService mstFileCommonService;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(int page, int length,Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> documentManagementMapper.getPortfolioId(param));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getDeptInstitutionCode(int page, int length,Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> documentManagementMapper.getDeptInstitutionCode(param));
    }

    /**
     * 分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<DocumentManagementDto> selectForPage(int page,int length,Map<String, Object> param) {
        String portfolioId = (String)param.get("portfolioId");
        if(StringUtil.isNotEmpty(portfolioId)){
            param.put("portfolioId", StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }else{
            param.put("portfolioId",null);
        }
       // return queryForPageForSelectReturn(page, length, () -> documentManagementMapper.selectForPage(param));
       //将视图转换成多段sql查询 多级法人改造 add by hxy 20200615 start 
        List<DocumentManagementDto> listAll=new ArrayList<DocumentManagementDto>();
       
        listAll.addAll(documentManagementMapper.selectForPageBranch1(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch2(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch3(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch4(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch5(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch6(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch7(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch8(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch9(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch10(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch11(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch12(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch13(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch14(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch15(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch16(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch17(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch18(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch19(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch20(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch21(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch22(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch23(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch24(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch25(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch26(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch27(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch28(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch29(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch30(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch31(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch32(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch33(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch34(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch35(param));
        listAll.addAll(documentManagementMapper.selectForPageBranch36(param));
       
        
        Page<DocumentManagementDto> documentManagementList = new Page<DocumentManagementDto>();
    	
		int beginIndex = (page - 1) * length;
		int endIndex = page * length;
		int count = listAll.size();
		if (beginIndex > count) {
			beginIndex = 0;
		}
		if (endIndex > count) {
			endIndex = count;
		}
		listAll = listAll.subList(beginIndex, endIndex);
		 documentManagementList.setRoot(listAll);
        documentManagementList.setRecords(count);
        return documentManagementList;
        //将视图转换成多段sql查询 多级法人改造 add by hxy 20200615 end


        
    }

    /**
     * 附件详情查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<DocumentManagementDto> selectForDetails(int page, int length,Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> documentManagementMapper.selectForDetails(param));
    }

    /**
     * 文件上传
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(DocumentManagementDto dto) {
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setFileList(dto.getFileArray());
        mstFileInputParameter.setBussinessId(dto.getBusinessId());
        if(mstFileCommonService != null){
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
        return 1;
    }

    /**
     * 删除
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(DocumentManagementDto dto) {
        return documentManagementMapper.delete(dto);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusinessType(int page, int length, Map<String, Object> param) {
        Page<SelectOptionDto> pageList = queryForPageForSelectReturn(page, length, () -> documentManagementMapper.getBusinessType(param));
        return pageList;
    }

}
