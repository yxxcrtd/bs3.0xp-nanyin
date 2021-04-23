package com.joyintech.tams.valuation.batch.threetableslanding.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.ValTableBas;
import com.joyintech.tams.common.base.entity.ValTableIns;
import com.joyintech.tams.common.base.entity.ValTableScoe;
import com.joyintech.tams.valuation.batch.threetableslanding.dao.ThreeTableLandingMapper;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.AssetLiabilityStatementDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.OwnerEquityChangeDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingResultDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValProfitDto;
import com.joyintech.tams.valuation.common.utils.DataHandleUtil;

@Service
public class ThreeTablesLandingWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private ThreeTableLandingMapper threeTableLandingMapper;
    
//    /**
//     * 账套表mapper
//     */
//    @Autowired
//    private BokBooksetMapper bokBooksetMapper;

    @Override
    public void write(List<? extends NeedToWriteEntity> list)
        throws Exception {
        @SuppressWarnings("unchecked")
        List<ThreeTableLandingResultDto> items = (List<ThreeTableLandingResultDto>)list;
        // 定义 待入库资产负债表数据List
        List<ValTableBas> listValTableBas = new ArrayList<ValTableBas>();
        // 定义 待入库利润表数据List
        List<ValTableIns> listValTableIns = new ArrayList<ValTableIns>();
        // 定义 待入库利润表数据List
        List<ValTableScoe> listValTableScoe = new ArrayList<ValTableScoe>();
//        // 定义 资负不平校验参数
//        BigDecimal checkNum = BigDecimal.ZERO;
//        // 定义 待入库利润表数据List
//        List<ActiveReminderMessage> listActiveReminderMessage = new ArrayList<ActiveReminderMessage>();
        // 循环返回结果
        for (ThreeTableLandingResultDto item : items) {
            // 若资产负债表不为空，则赋值存入待入库List中
            if (!CollectionUtils.isEmpty(item.getListAssetLiability())) {
                for (AssetLiabilityStatementDto dto : item.getListAssetLiability()) {
                    ValTableBas entity = new ValTableBas();
                    entity.setBooksetId(dto.getBooksetId());
                    entity.setItemCode(dto.getItemCode());
                    entity.setItemName(dto.getItemName());
                    entity.setItemType(dto.getItemType());
                    entity.setItemAmt(dto.getBalance());
                    entity.setCdate(dto.getBalanceDate());
                    listValTableBas.add(entity);
                    
//                    if(DictCodeList.科目类型_资产类.getValue().equals(dto.getItemType())){
//                    	checkNum = checkNum.add(dto.getBalance());
//                    }else if(DictCodeList.科目类型_负债类.getValue().equals(dto.getItemType())
//                    		|| DictCodeList.科目类型_所有者权益类.getValue().equals(dto.getItemType())){
//                    	checkNum = checkNum.subtract(dto.getBalance());
//                    }
                }
//                if(checkNum.compareTo(BigDecimal.ZERO) != 0){
//                	AssetLiabilityStatementDto basDto = item.getListAssetLiability().get(0);
//                	ActiveReminderMessage messageDto = new ActiveReminderMessage();
//                	messageDto.setMessageId(IDGenerator.generate());
//                	messageDto.setMessageType(DictCodeList.消息类型_资负不平提醒.getValue());
//                	BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(basDto.getBooksetId());
//                	messageDto.setMessageSubjectId(bokBookset.getBooksetId());
//                	messageDto.setMessageSubjectName(bokBookset.getBooksetName());
//                	messageDto.setMessageDate(basDto.getBalanceDate());
//                	String messageDate = DateUtil.format(basDto.getBalanceDate());
//                	String reminderMessage = basDto.getBooksetId()+"产品"+DateUtil.getMonth(messageDate)+"月"
//                			+DateUtil.getDayOfMonth(messageDate)+"日资产与负债不平";
//                	messageDto.setReminderMessage(reminderMessage);
//                	listActiveReminderMessage.add(messageDto);
//                }
//                checkNum = BigDecimal.ZERO;
            }
            // 若利润表不为空，则赋值存入待入库List中
            if (!CollectionUtils.isEmpty(item.getListValProfitDto())) {
                for (ValProfitDto dto : item.getListValProfitDto()) {
                    ValTableIns entity = dto;
                    listValTableIns.add(entity);
                }
            }
            // 若利润表不为空，则赋值存入待入库List中
            if (!CollectionUtils.isEmpty(item.getListOwnerEquityChangeDto())) {
                for (OwnerEquityChangeDto dto : item.getListOwnerEquityChangeDto()) {
                    ValTableScoe entity = new ValTableScoe();
                    entity.setBooksetId(dto.getBooksetId());
                    entity.setCdate(dto.getBalanceDate());
                    entity.setItemCode(String.valueOf(dto.getSortNum()));
                    entity.setItemName(dto.getProject());
                    entity.setItem1Amt(dto.gettIncomeFund());
                    entity.setItem2Amt(dto.gettUnQuarterProfit());
                    entity.setItem3Amt(dto.gettOwnerEquityTot());
                    // add by zwq 2020-12-17 start
                    entity.setItem4Amt(dto.gettOtherCompIncome());
                    // add by zwq 2020-12-17 end
                    listValTableScoe.add(entity);
                }
            }
        }
        // 待入库资产负债表数据List不为空，则批量入库
        // 定义一个固定大小插入存放集合
        List<List<ValTableBas>> listValTableBas2 = DataHandleUtil.splitList(listValTableBas);
        for (List<ValTableBas> listInsert : listValTableBas2) {
            if (!CollectionUtils.isEmpty(listInsert)) {
                threeTableLandingMapper.insertValTableBasList(listInsert);
            }
        }
        // 待入库利润表数据List不为空，则批量入库
        // 定义一个固定大小插入存放集合
        List<List<ValTableIns>> listValTableIns2 = DataHandleUtil.splitList(listValTableIns);
        for (List<ValTableIns> listInsert : listValTableIns2) {
            if (!CollectionUtils.isEmpty(listInsert)) {
                threeTableLandingMapper.insertValTableInsList(listInsert);
            }
        }
        // 待入库所有者权益表数据List不为空，则批量入库
        // 定义一个固定大小插入存放集合
        List<List<ValTableScoe>> listValTableScoe2 = DataHandleUtil.splitList(listValTableScoe);
        for (List<ValTableScoe> listInsert : listValTableScoe2) {
            if (!CollectionUtils.isEmpty(listInsert)) {
                threeTableLandingMapper.insertValTableScoeList(listInsert);
            }
        }
//        // 待入库主动提醒信息数据List不为空，则批量入库
//        // 定义一个固定大小插入存放集合
//        List<List<ActiveReminderMessage>> listActiveReminderMessage2 = DataHandleUtil.splitList(listActiveReminderMessage);
//        for (List<ActiveReminderMessage> listInsert : listActiveReminderMessage2) {
//            if (!CollectionUtils.isEmpty(listInsert)) {
//            	threeTableLandingMapper.insertActiveReminderMessage(listInsert);
//            }
//        }
    }
}
