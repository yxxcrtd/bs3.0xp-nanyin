/**
 * 
 */
package com.joyintech.tams.svreport.chb.datacheck.beforeinsert.service;

import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.service.CommonCodeCheckBatchService;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 生成报送业务数据前共通检查
 * 
 * @author dubaojun
 * @since 2020-10-13
 */
@Service
public class ChbCommonCheckService extends FileImpService{

    /**
     * 校验类
     */
    @Autowired
    private CommonCodeCheckBatchService commonCodeCheckBatchService;

    /**
     * 中债基础数据mapper
     */
    @Autowired
    private BaseMapper baseMapper;

    /**
     * 数据校验入口
     * 
     * @param datas 需要校验的数据
     * @param tableInfo 表信息
     * @return 校验后的数据
     */
    public <T> List<T> checkData(List<T> datas, ChbCheckTableInfo tableInfo) {
        // 判断传入的数据是否为空
        if(CollectionUtils.isEmpty(datas)) {
            return datas;
        }
        // 2 、校验、生成错误数据、正确数据的数据结构
        // 查询数据库表字段
        List<String> listCloumn = baseMapper.getTableColumnList(tableInfo.getTableName());
        // 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
        List<Map<String, String>> listMap = SvrMaptoEntityUtil.convertEntityToMap(datas, listCloumn);
        // 2.2调用FileImpService.checkData方法，如果需要执行代码校验，则不需要删除错误数据
        checkData(!tableInfo.isNeedCodeCheck(), listMap, tableInfo.getTableName(), tableInfo.getTableName(),
                tableInfo.getTableName(), tableInfo.getBussinessKey(), tableInfo.getCheckEuplicatedKeys());
        // 2.3调用CommonCodeCheckBatchService.codeCheckCommon方法
        if(tableInfo.isNeedCodeCheck()) {
            commonCodeCheckBatchService.codeCheckCommon(listMap, tableInfo, null);
        }
        // 2.4对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
        //获得运行期的泛型类型
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) datas.get(0).getClass();
        List<T> resultList = SvrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn, clazz);
        return resultList;
    }

    @Override
    public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
        return null;
    }

    @Override
    public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
        return null;
    }
}
