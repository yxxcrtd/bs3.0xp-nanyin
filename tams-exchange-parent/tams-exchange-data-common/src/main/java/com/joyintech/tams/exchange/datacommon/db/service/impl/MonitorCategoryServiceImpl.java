package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.db.domain.MonitorCategory;
import com.joyintech.tams.exchange.common.db.domain.Order;
import com.joyintech.tams.exchange.datacommon.db.mapper.MonitorCategoryMapper;
import com.joyintech.tams.exchange.datacommon.db.service.MonitorCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MonitorCategoryServiceImpl
 * @Description
 * @Author swwan
 * @Date 2020/11/10 13:54
 * @Version 1.0
 **/
@Service
public class MonitorCategoryServiceImpl implements MonitorCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorCategoryServiceImpl.class);

    @Autowired
    private MonitorCategoryMapper monitorCategoryMapper;

    private final Map<String, List<String>> categoryRecordMap = new ConcurrentHashMap<>();

    private final Set<String> categorySet = new HashSet<>();

    private final Set<String> recordNameSet = new HashSet<>();

    @Override
    public List<MonitorCategory> queryAll() {
        return monitorCategoryMapper.selectByExample(null);
    }

    @Override
    public void saveMonitorCategories(List<MonitorCategory> monitorCategories) {
        monitorCategoryMapper.insertBatch(monitorCategories);
    }

    private void saveMemoryCache() {
        List<MonitorCategory> monitorCategories = queryAll();
        monitorCategories.forEach(monitorCategory -> {
            recordNameSet.add(monitorCategory.getRecordName());
            if (categorySet.add(monitorCategory.getCategoryName())) {
                List<String> recordList = new ArrayList<>();
                recordList.add(monitorCategory.getRecordName());
                categoryRecordMap.put(monitorCategory.getCategoryName(), recordList);
            } else {
                categoryRecordMap.get(monitorCategory.getCategoryName()).add(monitorCategory.getRecordName());
            };
        });
    }

    @Override
    public List<String> queryRecordNameByCategoryInCache(String category) {
        if (categoryRecordMap.isEmpty()) {
            saveMemoryCache();
        }
        return categoryRecordMap.getOrDefault(category, new ArrayList<>());
    }

    @Override
    public List<String> queryAllRecordNameInCache() {
        if (categoryRecordMap.isEmpty()) {
            saveMemoryCache();
        }
        return new ArrayList<>(recordNameSet);
    }

    @Override
    public List<String> queryAllCategoryNameInCache() {
        if (categoryRecordMap.isEmpty()) {
            saveMemoryCache();
        }
        return new ArrayList<>(categorySet);
    }
}
