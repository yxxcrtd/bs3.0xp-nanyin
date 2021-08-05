package com.joyintech.service.basicdata.mstfuturespriceinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstfuturespriceinfo.dao.MstFuturesPriceInfoMapper;
import com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto;

/**
 * @Classname MstFuturesPriceInfoService
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/26 20:25 
 * @version 1.0 
 */
@Service("backMstFuturesPriceInfoService")
public class MstFuturesPriceInfoService extends BaseService<MstFuturesPriceInfoDto> {

    @Autowired
    MstFuturesPriceInfoMapper mstFuturesPriceInfoMapper;


    /**
     *
     * @Description //分页查询
     * @Param [page, length, map]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto>
     * @version 1.0
     * @see
    **/
    public Page<MstFuturesPriceInfoDto> findPage(int page,int length,Map<String,Object> map){
        return this.queryForPage(page,length,()->mstFuturesPriceInfoMapper.findPage(map));
    }

    /**
     *
     * @Description //合约代码下拉框
     * @Param [map]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    public List<SelectOptionDto> getContractId(Map<String, Object> map){
        return mstFuturesPriceInfoMapper.getContractId(map);
    }


    /**
     *
     * @Description //获得Wind代码
     * @Param [map]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    public List<SelectOptionDto> getWindCode(Map<String,Object> map){
        return mstFuturesPriceInfoMapper.getWindCode(map);
    }

    /**
     *
     * @Description //根据主键查询Dto
     * @Param [map]
     * @return com.joyintech.service.basicdata.mstfuturespriceinfo.dto.MstFuturesPriceInfoDto
     * @version 1.0
     * @see
    **/
    public MstFuturesPriceInfoDto selectByKey(Map<String,Object> map){
        return mstFuturesPriceInfoMapper.selectByKey(map);
    }



    /**
     *
     * @Description //工作流结束方法
     * @Param [entity]
     * @return void
     * @version 1.0
     * @see
    **/
    public void doComplete(MstFuturesPriceInfoDto entity){
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
            mstFuturesPriceInfoMapper.delete(entity);
        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            mstFuturesPriceInfoMapper.insert(entity);
        }else{
            mstFuturesPriceInfoMapper.update(entity);
        }
    }
}
