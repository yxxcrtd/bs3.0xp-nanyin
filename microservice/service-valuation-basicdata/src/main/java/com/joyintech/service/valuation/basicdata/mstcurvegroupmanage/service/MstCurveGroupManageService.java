
package com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.dao.MstCurveGroupManageMapper;
import com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 收益率曲线service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2021-06-16
 * @see MstCurveGroupManageService
 * @since 1.0
 */
@Service("MstCurveGroupManageService")
@Transactional(rollbackFor = Exception.class)
public class MstCurveGroupManageService extends BaseService<MstCurveGroupManageDto> {

   /**
    *
    */
    @Autowired
    private MstCurveGroupManageMapper mstCurveGroupManageMapper;

    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<MstCurveGroupManageDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page,length,()-> mstCurveGroupManageMapper.select(pageParam));
    }


    /**
     * 查询收益率曲线详情
     */
    public MstCurveGroupManageDto selectByKey(Map<String,Object> param){
        return  mstCurveGroupManageMapper.selectByKey(param);
    }


    /**
     * 插入数据
     * @param dto
     * @return
     */
    public int insert(MstCurveGroupManageDto dto) {
        return mstCurveGroupManageMapper.insertSelective(dto);
    }
    /**
     * 更新数据
     * @param dto
     * @return
     */
    public int update(MstCurveGroupManageDto dto) {
        return mstCurveGroupManageMapper.updateByPrimaryKey(dto);
    }
    /**
     * 删除数据
     * @param dto
     * @return
     */
    public int delete(MstCurveGroupManageDto dto) {
        return mstCurveGroupManageMapper.deleteByPrimaryKey(dto);
    }



}
