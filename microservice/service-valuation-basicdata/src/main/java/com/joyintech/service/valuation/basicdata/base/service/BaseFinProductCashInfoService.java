package com.joyintech.service.valuation.basicdata.base.service;/**
 * @author yanya
 * @create 2021-03-03 18:44
 */

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.base.dao.BaseFinProductCashInfoMapper;
import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductCashInfoDto;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseFinProductCashInfoService
 * @Description: 金融产品现金流信息
 * @Author yanya
 * @Date 2021/3/4 0004
 * @Version V1.0
 **/
@Service(value="serviceBaseFinProductCashInfoService")
@Transactional(rollbackFor = Exception.class)
public class BaseFinProductCashInfoService {

    /**
     * 金融产品现金流信息组件mapper
     */
    @Autowired
    private BaseFinProductCashInfoMapper baseFinProductCashInfoMapper;
    /**
     * 自动生成现金流mapper
     */
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    /**
     * 删除金融产品现金流信息
     */
    public boolean deleteByPrimaryKey(FinCashInfo finCashInfo) {
        return baseFinProductCashInfoMapper.deleteByPrimaryKey(finCashInfo)==1?true:false;
    }
    /**
     * 新增金融产品现金流信息
     */
    public boolean insertList(List<FinCashInfo> list) {
        if(list!=null&&list.size()>0){
            for(FinCashInfo finCashInfo:list){
                finCashInfoMapper.insert(finCashInfo);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改金融产品现金流信息
     */
    public boolean update(BaseFinProductCashInfoDto dto){
        String cashId =dto.getCashId();
        List<FinCashInfo> list=dto.getFinCashInfoList();
        if(StringUtil.isNotEmpty(cashId)&&StringUtil.isNotEmpty(list)){
            FinCashInfo entity = new FinCashInfo();
            entity.setCashId(cashId);
            entity.setCashType1(dto.getCashType1());
            entity.setCashType2(dto.getCashType2());
            entity.setFinprodId(dto.getFinprodId());
            entity.setBranch(dto.getBranch());
            if(this.deleteByPrimaryKey(entity)){
                this.insertList(list);
                return true;
            }
        }
        return false;
    }

    /**
     * 查询
     */
    public List<FinCashInfo> select(FinCashInfo finCashInfo) {
        return baseFinProductCashInfoMapper.selectDetail(finCashInfo);
    }


}
