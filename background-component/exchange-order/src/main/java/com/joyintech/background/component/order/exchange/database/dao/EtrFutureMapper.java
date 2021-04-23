package com.joyintech.background.component.order.exchange.database.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.order.exchange.business.dto.EtrFutureBiddingDto;


@Repository
public interface EtrFutureMapper extends CmpComponentMapper <EtrFutureBiddingDto>{

	/**
     * {@inheritDoc}
     */
    @Override
    EtrFutureBiddingDto select(String entrustId);
}
