package com.joyintech.tams.invest.interf.fromjkpt.settle.dao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SettleDownLinkMapper {

    List<String> listSysBatchTask(Map<String,Object> param);
}
