package com.joyintech.service.basicdata.futuresdepositpercent.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.futuresdepositpercent.dao.FuturesDepositPercentMapper;
import com.joyintech.service.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;

/**
 * @Classname FuturesDepositPercentService
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 18:45 
 * @version 1.0 
 */
@Service("backFuturesDepositPercentService")
public class FuturesDepositPercentService  extends BaseService<FuturesDepositPercentDto> {
    @Autowired
    private FuturesDepositPercentMapper futuresDepositPercentMapper;

    /**
     *
     * @Description //分页查询
     * @Param [page, length, param]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto>
     * @version 1.0
     * @see
    **/
    public Page<FuturesDepositPercentDto> selectForPage(int page, int length, Map<String, Object> param) throws Exception{
        return this.queryForPage(page,length,()->futuresDepositPercentMapper.selectForPage(param));
    }



    /**
     *
     * 工作流完结之后的操作
     * <功能详细描述>
     * @param entity 实体
     * @param
     * @see
     * @since 1.0
     */
    public void doComplete(FuturesDepositPercentDto entity) {
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
            futuresDepositPercentMapper.deleteByPrimaryKey(entity);
        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            entity.setDepositPercent(entity.getDepositPercent().divide(new BigDecimal(100)));
            futuresDepositPercentMapper.insert(entity);
        }else{
            entity.setDepositPercent(entity.getDepositPercent().divide(new BigDecimal(100)));
            futuresDepositPercentMapper.updateByPrimaryKeySelective(entity);
        }

    }

    public FuturesDepositPercentDto selectByPrimaryKey(Map<String, Object> param) {
        return futuresDepositPercentMapper.selectByPrimaryKey(param);
    }
}
