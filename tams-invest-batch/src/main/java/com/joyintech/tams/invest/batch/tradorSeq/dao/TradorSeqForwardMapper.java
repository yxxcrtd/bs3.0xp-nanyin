package com.joyintech.tams.invest.batch.tradorSeq.dao;

import com.joyintech.tams.invest.batch.tradorSeq.dto.TradorSeqForwardDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 逾期资产结转批处理DAO
 */
@Repository
public interface TradorSeqForwardMapper {

    /** @MethodName: 删除交易员序列数据
    * @Description: TODO
    * @Param: []
    * @Return: void
    * @Author: wangxuelei
    * @Date: 2020/1/6 0006
    **/
    void deleteTradorSeq();

    List<TradorSeqForwardDto> selectTradorSeqByRole();
}
