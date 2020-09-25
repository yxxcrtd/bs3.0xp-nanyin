//package com.joyintech.tams.invest.batch.common.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.joyintech.batch.batchbase.service.BatchCacheService;
////import com.joyintech.batch.valuation.common.cache.service.entity.ValBokBussDetsubMem;
////import com.joyintech.batch.valuation.common.cache.service.entity.ValBokSubjectDetailMem;
//import com.joyintech.framework.constant.GlobalConstant;
//import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
//import com.joyintech.framework.jdbc.enums.DataSourceType;
//import com.joyintech.framework.util.CacheUtil;
//import com.joyintech.tams.invest.batch.common.dao.BatchCacheMapper;
//import com.joyintech.tams.invest.batch.common.dto.SysParaDto;
//
//@Service
//public class PinganBatchCacheService extends BatchCacheService {
//
//	@Value("${batch.properties.maxSqlNum}")
//    public int maxSqlNum;
//
//    /**
//     * BatchCacheMapper
//     */
//    @Autowired
//    private BatchCacheMapper batchCacheMapper;
//    String SYS_CODE_ = "SYS_CODE_";
//
//	@Override
//	public void putCache() {
//	    CacheUtil.putAppCache(GlobalConstant.SYS_PARA_KEY, findAllSysParam());
////	    CacheUtil.putAppCache(com.joyintech.valuation.common.constants.GlobalConstant.DETAIL_SUBJECT, findAllDetailSubjectKey());
////        CacheUtil.putAppCache(com.joyintech.valuation.common.constants.GlobalConstant.BUSS_DETSUB, findAllBussDetsubKey());
////        CacheUtil.putAppCache(com.joyintech.valuation.common.constants.GlobalConstant.MAX_SQL_NUM, maxSqlNum);
//	}
//
//    /**
//     * 获取所有业务明细科目的key
//     * @return set
//     * @see
//     * @since 1.0
//     */
//    @Override
//    public Map<String, Object> findAllSysParam() {
//        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
//        List<SysParaDto> paraList = batchCacheMapper.selectAllSysParam();
//        DynamicDataSourceHolder.clearDataSource();
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        if(CollectionUtils.isNotEmpty(paraList)) {
//            for (SysParaDto sysPara : paraList) {
//                resultMap.put(sysPara.getParaCode(), sysPara.getParamValue());
//                // sysPara鏈変袱涓富閿�
//                if (resultMap.get(SYS_CODE_ + sysPara.getSystemCode()) == null) {
//                    Map<String, String> map = new HashMap<>();
//                    map.put(sysPara.getParaCode(), sysPara.getParamValue());
//                    resultMap.put(SYS_CODE_ + sysPara.getSystemCode(), map);
//                } else {
//                    ((Map<String, String>) resultMap.get(SYS_CODE_ + sysPara.getSystemCode())).put(sysPara.getParaCode(), sysPara.getParamValue());
//                }
//            }
//        }
//        return resultMap;
////        return paraList.stream().collect(JoyinCollectors.toMap(SysParaDto::getParaCode, SysParaDto::getParamValue));
//    }
//
////    /**
////     * 获取所有明细科目的key
////     * @return set
////     * @see
////     * @since 1.0
////     */
////    public Set<ValBokSubjectDetailMem> findAllDetailSubjectKey() {
////        List<ValBokSubjectDetailMem> keyList = batchCacheMapper.findAllDetailSubject();
////        if (keyList == null) {
////            keyList = new ArrayList<ValBokSubjectDetailMem>();
////        }
////        return Collections.synchronizedSet(new HashSet<>(keyList));
////    }
////
////    /**
////     * 获取所有业务明细科目的key
////     * @return set
////     * @see
////     * @since 1.0
////     */
////    public Set<ValBokBussDetsubMem> findAllBussDetsubKey() {
////        List<ValBokBussDetsubMem> keyList = batchCacheMapper.findAllBussDetsubKey();
////        if (keyList == null) {
////            keyList = new ArrayList<ValBokBussDetsubMem>();
////        }
////        return Collections.synchronizedSet(new HashSet<>(keyList));
////    }
//
//}
