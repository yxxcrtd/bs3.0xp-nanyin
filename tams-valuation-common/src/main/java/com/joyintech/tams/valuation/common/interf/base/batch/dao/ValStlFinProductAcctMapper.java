package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.StlFinProductAcct;

@Repository
public interface ValStlFinProductAcctMapper {
	
    int insertBatch(List<StlFinProductAcct> list);

    int deleteBatch(List<StlFinProductAcct> list);
}