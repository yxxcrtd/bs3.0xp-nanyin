package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao.UnClaimAssetDetailMapper;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UnClaimAssetDetail002Service extends CommonExportService<UnClaimAssetDetailDto>
    implements BaseProcessService<UnClaimAssetDetailDto> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UnClaimAssetDetailMapper unClaimAssetDetailMapper;

//    /**
//     * 双击查看,修改
//     *
//     * @param nostdAssetId
//     * @return
//     */
//    public UnClaimAssetDetailDto selectDetail(String nostdAssetId) {
//        Map<String,Object> param=new HashMap<>();
//        param.put("nostdAssetId",nostdAssetId);
//        return unClaimAssetDetailMapper.selectDetail(param);
//    }

    /**
     * 根据prodId主产品代码,effDate生效日,effEndDate生效截止日查询该资产所投非标资产明细
     * @param params
     * @return
     */
    public List<UnClaimAssetDetailDto> selectDetailList(Map<String,Object> params) {
        return unClaimAssetDetailMapper.selectDetailList(params);
    }
    @Override
    public void doComplete(UnClaimAssetDetailDto dto, OperationType type) {

        if (OperationType.DELETE.equals(type)) {
            //删除,删除该产品所投该生效区间所有非标资产,逻辑删除,更新数据状态为D：无效
                HashMap map =new HashMap();
                map.put("prodId",dto.getProdId());
                map.put("eddDate", DateUtil.format(dto.getEffDate()));
                map.put("effEndDate",DateUtil.format(dto.getEffEndDate()));
                 unClaimAssetDetailMapper.deleteOnStatus(map);
        }
        else if (OperationType.NEW.equals(type)) {//新建
//            for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()
//                 ) {
//                unclaimDto.setProjectNo(dto.getProjectNo());
//                unclaimDto.setProdId(dto.getProdId());
//                unclaimDto.setEffDate(dto.getEffDate());
//                unclaimDto.setEffEndDate(dto.getEffEndDate());
//                unclaimDto.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
//                //数据来源
//                unclaimDto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
//                unClaimAssetDetailMapper.insert(unclaimDto);
//            }
//            画面dto的列表中的非标资产逐一插入
            for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()){
                unClaimAssetDetailMapper.insert(unclaimDto);
            }
//            dto.setSort(unClaimAssetDetailMapper.selectNostdCount()+1);
//            dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
//            //数据来源
//            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());

        }
        else {
            //编辑后更新  全删全插，覆盖掉 删除按照prodId,effDate,effEndDate
                unClaimAssetDetailMapper.deleteOnReal(dto);
            for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()){
                String code = IDGenerator.generate(36);
                unclaimDto.setNostdAssetId(code);
                unclaimDto.setEffDate(dto.getEffDate());
                unclaimDto.setEffEndDate(dto.getEffEndDate());
                unClaimAssetDetailMapper.insert(unclaimDto);
            }
        }
    }
    /**
     * 查询产品所有的非标资产信息
     * @param param
     * @return
     */
    public List<UnClaimAssetDetailDto> checkNostdExistByProdId(Map<String, Object> param) {
        param.put("status",DictCodeListExt1.维护数据状态_有效.getValue());
        return unClaimAssetDetailMapper.checkNostdExistByProdId(param);
    }




    public int selectCount() {
        return unClaimAssetDetailMapper.selectCount();
    }
}
