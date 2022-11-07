package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service;


import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao.UnClaimAssetDetailMapper;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
//                HashMap map =new HashMap();
//                map.put("prodId",dto.getProdId());
//                map.put("eddDate", DateUtil.format(dto.getEffDate()));
//                map.put("effEndDate",DateUtil.format(dto.getEffEndDate()));
//                map.put("dataStatus",dto.getDataStatus());
//                 unClaimAssetDetailMapper.deleteOnStatus(map);

            List<UnClaimAssetDetailDto> dtoList = dto.getUnclaimList();
            unClaimAssetDetailMapper.deleteByNostdAssetId(LoginUserUtil.getUserName(),dtoList);
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
                if(!StringUtil.isNotEmpty(unclaimDto.getNostdAssetId())){
                    unclaimDto.setNostdAssetId(IDGenerator.generate(36));

                }
                unclaimDto.setCreateTime(dto.getCreateTime());
                unclaimDto.setUpdateTime(dto.getUpdateTime());
                unclaimDto.setCreateUser(CommonUtil.getUserName());
                unclaimDto.setUpdateUser(CommonUtil.getUserName());
                unclaimDto.setProdId(dto.getProdId());
                unclaimDto.setEffDate(dto.getEffDate());
                unclaimDto.setEffEndDate(dto.getEffEndDate());
                unclaimDto.setDataStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                //数据来源
                unclaimDto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
                unClaimAssetDetailMapper.insert(unclaimDto);
            }
//            dto.setSort(unClaimAssetDetailMapper.selectNostdCount()+1);
//            dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
//            //数据来源
//            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());

        }
        else {
            //编辑后更新  全删全插，覆盖掉 删除按照prodId,effDate,effEndDate
            dto.setDataStatus("C");
            unClaimAssetDetailMapper.deleteOnReal(dto);

            Map<String, Object> map = new HashMap<>();
            map.put("prodId", dto.getProdId());
            map.put("effDate",dto.getEffDateStr());
            map.put("effEndDate", dto.getEffEndDateStr());
            map.put("dataStatus", DictCodeListExt1.维护数据状态_有效.getValue());
            Integer num = unClaimAssetDetailMapper.selectCount(map);

            for (UnClaimAssetDetailDto unclaimDto:dto.getUnclaimList()){
                String code = IDGenerator.generate(36);
                if (unclaimDto.getProdId()==null){
                    unclaimDto.setProdId(dto.getProdId());
                }
                num+=1;
                unclaimDto.setDataStatus("E");
                unclaimDto.setNostdAssetId(code);
                unclaimDto.setEffDate(dto.getEffDate());
                unclaimDto.setEffEndDate(dto.getEffEndDate());
                unclaimDto.setProjectNo(String.valueOf(num));
                unClaimAssetDetailMapper.insert(unclaimDto);
            }
        }
    }

    @Override
    public void doInsert(UnClaimAssetDetailDto dto, OperationType type) {
        if(OperationType.MODIFY.equals(type)) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId", dto.getProdId());
            map.put("effDate", dto.getEffDateStr());
            map.put("effEndDate", dto.getEffEndDateStr());
            map.put("dataStatus", DictCodeListExt1.维护数据状态_有效.getValue());

            List<UnClaimAssetDetailDto> existList=new ArrayList<>();

            List<UnClaimAssetDetailDto> existEList = selectDetailList(map);
            if(CollectionUtils.isNotEmpty(existEList)){
                existList.addAll(existEList);
            }
            map.put("dataStatus", DictCodeListExt1.维护数据状态_待处理.getValue());
            List<UnClaimAssetDetailDto> existAList = selectDetailList(map);
            if(CollectionUtils.isNotEmpty(existAList)){
                existList.addAll(existAList);
            }
            List<UnClaimAssetDetailDto> unclaimList = dto.getUnclaimList();

            //对于已经存在的数据需要入历史表
            existList.forEach(item -> {
                item.setId(item.getNostdAssetId());
                item.setWorkflowId(dto.getWorkflowId());
                // 数据存入留痕表备份 流程作废方便恢复
                unClaimAssetDetailMapper.saveHisData(item);
//                //进入流程，数据状态改为 待复核
//                UnClaimAssetDetailDto unClaimAssetDetailDto = new UnClaimAssetDetailDto();
//                BeanUtil.copyPropertiesIgnoreNull(unClaimAssetDetailDto, item);
//                unClaimAssetDetailDto.setDataStatus("C");
//                unClaimAssetDetailMapper.update(unClaimAssetDetailDto);
            });
            existList.forEach(item -> {
                item.setId(item.getNostdAssetId());
                item.setWorkflowId(dto.getWorkflowId());
                // 数据存入留痕表备份 流程作废方便恢复
                unClaimAssetDetailMapper.saveHisData(item);
                //进入流程，数据状态改为 待复核
                UnClaimAssetDetailDto unClaimAssetDetailDto = new UnClaimAssetDetailDto();
                BeanUtil.copyPropertiesIgnoreNull(unClaimAssetDetailDto, item);
                unClaimAssetDetailDto.setDataStatus("C");
                unClaimAssetDetailDto.setUpdateTime(new Date());
                unClaimAssetDetailMapper.update(unClaimAssetDetailDto);
            });
        }else if(OperationType.DELETE.equals(type)){
            Map<String, Object> map = new HashMap<>();
            map.put("prodId", dto.getProdId());
            map.put("effDate", dto.getEffDateStr());
            map.put("effEndDate", dto.getEffEndDateStr());
            map.put("dataStatus", DictCodeListExt1.维护数据状态_有效.getValue());

            List<UnClaimAssetDetailDto> existList = selectDetailList(map);

            existList.forEach(item -> {
                item.setId(item.getNostdAssetId());
                item.setWorkflowId(dto.getWorkflowId());
                // 数据存入留痕表备份 流程作废方便恢复
                unClaimAssetDetailMapper.saveHisData(item);
                //进入流程，数据状态改为 待复核
                UnClaimAssetDetailDto unClaimAssetDetailDto = new UnClaimAssetDetailDto();
                BeanUtil.copyPropertiesIgnoreNull(unClaimAssetDetailDto, item);
                unClaimAssetDetailDto.setDataStatus("C");
                unClaimAssetDetailMapper.update(unClaimAssetDetailDto);
            });
        }
    }

    @Override
    public void doDelete(String workflowId) {
        //查留痕表对应workflowId的数据，并还原数据状态
        List<UnClaimAssetDetailDto> dataList = unClaimAssetDetailMapper.selectHisData(workflowId);
        if(CollectionUtils.isNotEmpty(dataList)){
            UnClaimAssetDetailDto newDto = new UnClaimAssetDetailDto();
            BeanUtil.copyPropertiesIgnoreNull(newDto, dataList.get(0));
            newDto.setDataStatus("C");
            newDto.setEffDateO(newDto.getEffDate());
            newDto.setEffEndDateO(newDto.getEffEndDateO());
            unClaimAssetDetailMapper.deleteOnReal(newDto);

            for (UnClaimAssetDetailDto tmp:dataList){
                tmp.setEffDateO(tmp.getEffDate());
                tmp.setEffEndDateO(tmp.getEffEndDateO());
                UnClaimAssetDetailDto unClaimAssetDetailDto = new UnClaimAssetDetailDto();
                BeanUtil.copyPropertiesIgnoreNull(unClaimAssetDetailDto, tmp);
                unClaimAssetDetailMapper.insert(unClaimAssetDetailDto);
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

    public List<UnClaimAssetDetailDto> getWorklowData(UnClaimAssetDetailDto detailDto) {
        List<ProcessMark> processMarkList = unClaimAssetDetailMapper.getWorklowData(detailDto);
        List<UnClaimAssetDetailDto> list = new ArrayList<>();
        if (processMarkList != null && processMarkList.size() > 0){
            for (ProcessMark processMark : processMarkList){
                UnClaimAssetDetailDto unClaimAssetDetailDto = this.getBussinessEntity(processMark);
                list.add(unClaimAssetDetailDto);
            }

        }
        return list;
    }


//
//
//
//
//    public int selectCount() {
//        return unClaimAssetDetailMapper.selectCount();
//    }
}
