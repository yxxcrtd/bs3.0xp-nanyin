package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.MonitorCategory;
import java.util.List;

public interface MonitorCategoryService {
    List<MonitorCategory> queryAll();

    void saveMonitorCategories(List<MonitorCategory> monitorCategories);

    List<String> queryRecordNameByCategoryInCache(String category);

    List<String> queryAllRecordNameInCache();

    List<String> queryAllCategoryNameInCache();
}
