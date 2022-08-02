/*
 * 文件名：ProdPartner001Service.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.prodassetinfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dao.ProdAssetInfo002Mapper;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.dao.ProdPartner002Mapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 合作机构信息维护
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see ProdAssetInfo002Service
 * @since 1.0
 */
@Service
public class ProdAssetInfo002Service extends CommonExportService<ProdAssetInfoDto>
    implements BaseProcessService<ProdAssetInfoDto> {

    @Autowired
    private ProdAssetInfo002Mapper prodAssetInfo002Mapper;

    /**
     * 双击查看详情
     *
     * @param prodAssetId
     * @return
     */
    public ProdAssetInfoDto selectDetail(String prodAssetId) {
        ProdAssetInfoDto dto = new ProdAssetInfoDto();
        Map<String,Object> param=new HashMap<>();
        param.put("prodAssetId",prodAssetId);

        return prodAssetInfo002Mapper.selectDetail(param);
    }

    @Override
    public void doComplete(ProdAssetInfoDto dto, OperationType type) {

        if (OperationType.DELETE.equals(type)) {
            //删除
            prodAssetInfo002Mapper.deleteProdAssetByPrimaryKey(dto);
        }
        else if (OperationType.NEW.equals(type)) {//新建
            //数据状态
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            //数据来源
            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            prodAssetInfo002Mapper.insert(dto);
        }
        else {
            //更新
            prodAssetInfo002Mapper.updateByPrimaryKeySelective(dto);
        }
    }

    /**
     * 查询产品所有的合作机构信息
     * @param dto
     * @return
     */
    public List<ProdAssetInfoDto> selectProdAssetInfoByProdId(Map<String,Object> param) {
        param.put("status",DictCodeListExt3.信披_数据状态_有效E.getValue());
        return prodAssetInfo002Mapper.selectProdAssetInfoByProdId(param);
    }
}
