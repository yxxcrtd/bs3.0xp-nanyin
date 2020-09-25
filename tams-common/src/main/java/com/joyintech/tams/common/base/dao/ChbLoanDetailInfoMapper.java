package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ChbLoanDetailInfoMapper {
    int deleteByPrimaryKey(String assetUuid);

    int insert(ChbLoanDetailInfo record);

    int insertSelective(ChbLoanDetailInfo record);

    int insertBasicByBasicInfo(ChbLoanDetailInfo record);

    ChbLoanDetailInfo selectByPrimaryKey(String assetUuid);

    int updateByPrimaryKeySelective(ChbLoanDetailInfo record);

    int updateByPrimaryKey(ChbLoanDetailInfo record);
    
    /**
     * 查询所有非删除状态的资产收益权贷款明细基本信息
     * @Description TODO  
     * @Author      高健  
     * @Date        2019年12月18日 下午3:12:34  
     * @param @return 参数  
     * @return List<ChbLoanDetailInfo> 返回类型   
     * @throws
     */
    List<ChbLoanDetailInfo> queryChbLoanDetailBatch();
}