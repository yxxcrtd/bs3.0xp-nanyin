package com.joyintech.service.basicdata.standardcouponreconversionrate.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

import com.joyintech.service.basicdata.standardcouponreconversionrate.dao.BasicStandardCouponReConversionRateMapper;
import com.joyintech.service.basicdata.standardcouponreconversionrate.dto.StandardCouponReConversionRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("backStandardCouponReConversionRateService")
public class StandardCouponReConversionRateService extends BaseService<StandardCouponReConversionRateDto> {

    /**
     * 资金账户管理mapper
     */
    @Autowired
    private BasicStandardCouponReConversionRateMapper basicStandardCouponReConversionRateMapper;



    /**
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     * @param length
     * @param page
     *
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<StandardCouponReConversionRateDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        return super.queryForPage(page,length,() -> basicStandardCouponReConversionRateMapper.select(pageParam));
    }
    /**
     * 获取券商
     *
     * @param map
     *            检索参数
     * @return SelectOptionDto
     */
    public List<SelectOptionDto> getMstCustomerInfo(Map<String, Object> map) {
        return basicStandardCouponReConversionRateMapper.getMstCustomerInfo(map);
    }

    /**
     * 主键唯一性验证
     *
     *            检索参数
     * @return SelectOptionDto
     */
    public StandardCouponReConversionRateDto selectByPrimaryKey(StandardCouponReConversionRateDto dto) {
        return basicStandardCouponReConversionRateMapper.selectByPrimaryKey(dto);
    }

    /**
     * 删除操作
     */
    public int delete(StandardCouponReConversionRateDto entity) {
        int res = 0;
        if (entity.getSelectedItem() != null && entity.getSelectedItem().size() > 0) {
            for (StandardCouponReConversionRateDto unit : entity.getSelectedItem()) {
                res += basicStandardCouponReConversionRateMapper.deleteByPrimaryKey(unit);
            }
        } else {
            res = basicStandardCouponReConversionRateMapper.deleteByPrimaryKey(entity);
        }
        return res;
    }

    /**
     * 插入操作
     */
    public int insert(StandardCouponReConversionRateDto dto) {
        dto.setTandardRate (String.valueOf (new Float (dto.getTandardRate ()) / 100));
        return basicStandardCouponReConversionRateMapper.insertSelective(dto);
    }

    /**
     * 更新操作
     */
    public int update(StandardCouponReConversionRateDto dto) {
        dto.setTandardRate (String.valueOf (new Float (dto.getTandardRate ()) / 100));
        return basicStandardCouponReConversionRateMapper.updateByPrimaryKeySelective(dto);
    }


    /**
     * 组合资金账户
     * @param map
     * @return
     */
    public List<SelectOptionDto> getPtlSecAcctInfo(Map<String,Object> map){
        List<SelectOptionDto> list = basicStandardCouponReConversionRateMapper.getPtlSecAcctInfo(map);
        return list;
    }


    public List<SelectOptionDto> brokerIdChange(Map<String,Object> map){
        map.put ("broker",map.get ("borker"));
        List<SelectOptionDto> list = basicStandardCouponReConversionRateMapper.brokerIdChange(map);
        return list;
    }
}
