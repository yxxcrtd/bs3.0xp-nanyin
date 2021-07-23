package com.joyintech.service.risk.outsvaltableimport.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.outsvaltableimport.dao.RskOutsValTableImportHisMapper;
import com.joyintech.service.risk.outsvaltableimport.dto.RskOutValtblImportHisDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImportDto;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.base.dao.RskOutValtblImportHisMapper;
import com.joyintech.tams.common.base.dao.RskValtblPrtImpMapMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumsubMapMapper;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHisKey;
import com.joyintech.tams.common.base.entity.RskValtblPrtImpMap;
import com.joyintech.tams.common.base.entity.RskValtblSumsubMap;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Service
public class RskOutValtblImportHisService extends BaseService<RskOutValtblImportHisDto> {

    @Autowired
    private RskOutValtblImportHisMapper rskOutValtblImportHisMapper;

    @Autowired
    private RskOutsValTableImportHisMapper rskOutsValTableImportHisMapper;

    @Autowired
    private RskValtblPrtImpMapMapper rskValtblPrtImpMapMapper;

    @Autowired
    private RskValtblSumsubMapMapper rskValtblSumsubMapMapper;

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<RskOutValtblImportHisDto> selectForPage(int page, int length,
                                                        Map<String, Object> param){
        if (StringUtil.isNotEmpty(SecurityUtil.getLoginUser().getOrgCode())){
            String orgCode = SecurityUtil.getLoginUser().getOrgCode();
            param.put("orgCode", orgCode);
        }
        
        Page<RskOutValtblImportHisDto> pageList = super.queryForPage(page, length, () -> rskOutsValTableImportHisMapper.selectForPage(param));
        List<String> assetSubMapIdList = rskOutsValTableImportHisMapper.selectAssetSubMapId();
        pageList.getRoot().forEach(item->{
            if (StringUtil.isNotEmpty(item.getPortfolioId())) {
                assetSubMapIdList.forEach(assetSubMapId -> {
                    String[] assetSubMapIds = assetSubMapId.split(",");
                     if(StringUtil.equals(item.getPortfolioId(),assetSubMapIds[0])){
                         item.setAssetSubMapId(assetSubMapIds[1]);
                     }
                });
            }
        });
        return pageList;
    }

    public Map<String,String> selectPortfolioId(Map<String, Object> param){
        return rskOutsValTableImportHisMapper.selectPortfolioId(param);
    }

    public Map<String,String> selectFinProductByFinprodMarketId(Map<String, Object> param){
        return rskOutsValTableImportHisMapper.selectFinProductByFinprodMarketId(param);
    }

    public int insert(RskOutValtblImportHis dto) {
        RskOutValtblImportHisKey key=new RskOutValtblImportHisKey();
        key.setFileName(dto.getFileName());
        key.setPortfolioName(dto.getPortfolioName());
        key.setValdate(dto.getValdate());
        RskOutValtblImportHis rskOutValtblImportHis = rskOutValtblImportHisMapper.selectByPrimaryKey(key);
        if(rskOutValtblImportHis!=null){
            return rskOutValtblImportHisMapper.updateByPrimaryKey(dto);
        }
        return rskOutValtblImportHisMapper.insert(dto);
    }

    public int queryorgCodeCount(String param){
        return rskOutsValTableImportHisMapper.queryorgCodeCount(param);
    }

    public RskValtblPrtImpMap selectInfoById(String ptlId) {
        RskValRelateImportDto dto = new RskValRelateImportDto();
        try {
            RskValtblPrtImpMap rskValtblPrtImpMap =  rskValtblPrtImpMapMapper.selectByPrimaryKey(ptlId);
            if(null!=rskValtblPrtImpMap){
                BeanUtils.copyProperties(dto, rskValtblPrtImpMap);
                RskValtblSumsubMap rskValtblSumsubMap= rskValtblSumsubMapMapper.selectByPrimaryKey(rskValtblPrtImpMap.getSumsubMapId());
                if(null!=rskValtblSumsubMap){
                    dto.setTrimChars(rskValtblSumsubMap.getTrimChars());
                }
            }
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //查询组合名称
        dto.setName(rskOutsValTableImportHisMapper.selectGroupName(ptlId));
        return dto;
    }
}
