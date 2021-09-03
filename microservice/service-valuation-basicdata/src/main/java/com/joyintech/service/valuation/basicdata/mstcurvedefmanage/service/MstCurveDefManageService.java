
package com.joyintech.service.valuation.basicdata.mstcurvedefmanage.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basicdata.mstcurvedefmanage.dao.MstCurveDefManage001Mapper;
import com.joyintech.service.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 收益率曲线service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2021-06-16
 * @see MstCurveDefManageService
 * @since 1.0
 */
@Service("backMstYieldCurveInfoManageService")
@Transactional(rollbackFor = Exception.class)
public class MstCurveDefManageService extends BaseService<MstCurveDefManageDto> {

   /**
    *
    */
    @Autowired
    private MstCurveDefManage001Mapper mstCurveDefManage001Mapper;

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
    public Page<MstCurveDefManageDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page,length,()-> mstCurveDefManage001Mapper.select(pageParam));
    }

    /**
     * 收益率曲线下拉框
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getCurveCode(int page, int length,Map<String,Object> param){
        return  this.queryForPageForSelectReturn(page, length,() ->  mstCurveDefManage001Mapper.getCurveCode(param));
    }

    /**
     * 更新生效状态
     * @param dto
     */
    public void updateEffStatus(MstCurveDefManageDto dto){
        for (int i = 0; i < dto.getSelectedItem().size(); i++) {
            mstCurveDefManage001Mapper.updateEffStatus(dto.getSelectedItem().get(i));
        }

    }

    /**
     * 查询收益率曲线定义是否存在
     */
   public List<MstCurveDefManageDto> getCurveDefCount(MstCurveDefManageDto dto){
       return  mstCurveDefManage001Mapper.getCurveDefCount(dto);
   }

    /**
     * 查询收益率曲线详情
     */
    public MstCurveDefManageDto selectByKey(Map<String,Object> param){
        return  mstCurveDefManage001Mapper.selectByKey(param);
    }
}
