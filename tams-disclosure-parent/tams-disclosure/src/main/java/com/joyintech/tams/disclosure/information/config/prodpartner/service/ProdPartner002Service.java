/*
 * 文件名：ProdPartner001Service.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodpartner.service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbPrdDeclareInfoErrodataWithBLOBs;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.prodpartner.dao.ProdPartner001Mapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dao.ProdPartner002Mapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dao.WorkflowMapper;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 合作机构信息维护
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdPartner002Service
 * @since 1.0
 */
@Service
public class ProdPartner002Service extends CommonExportService<ProdPartnerDto>
    implements BaseProcessService<ProdPartnerDto> {

    @Autowired
    private ProdPartner002Mapper prodPartner002Mapper;

    @Autowired
    private WorkflowMapper workflowMapper;

    /**
     * 双击查看详情
     *
     * @param prodPartenerId
     * @return
     */
    public ProdPartnerDto selectDetail(String prodPartenerId) {
        ProdPartnerDto dto = new ProdPartnerDto();
        Map<String,Object> param=new HashMap<>();
        param.put("prodPartenerId",prodPartenerId);

        return prodPartner002Mapper.selectDetail(param);
    }

    @Override
    public void doComplete(ProdPartnerDto dto, OperationType type) {

        if (OperationType.DELETE.equals(type)) {
            //删除
            /*if(StringUtil.isNotEmpty(dto.getWorkflowId())){
                BaseEntity baseEntity = workflowMapper.selectWflCreateInfo(dto.getWorkflowId());
                if(baseEntity!=null){
                    dto.setUpdateUser(baseEntity.getCreateUser());
                    dto.setUpdateTime(baseEntity.getCreateTime());
                }
            }*/
            dto.setUpdateUser(LoginUserUtil.getUserName());
            prodPartner002Mapper.deleteProdPartnerByPrimaryKey(dto);
        }
        else if (OperationType.NEW.equals(type)) {//新建
            //数据状态
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            //数据来源
            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            prodPartner002Mapper.insert(dto);
        }
        else {
            //更新
            dto.setUpdateUser(LoginUserUtil.getUserName());
            prodPartner002Mapper.updateByPrimaryKeySelective(dto);
        }
    }

    /**
     * 查询产品所有的合作机构信息
     * @param param
     * @return
     */
    public List<ProdPartnerDto> checkPartnerExistByProdId(Map<String, Object> param) {
        param.put("status",DictCodeListExt3.信披_数据状态_有效E.getValue());
        return prodPartner002Mapper.selectPartnerByProdId(param);
    }
}
