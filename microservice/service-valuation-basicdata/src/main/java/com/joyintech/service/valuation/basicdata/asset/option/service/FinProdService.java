package com.joyintech.service.valuation.basicdata.asset.option.service; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-19
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.asset.option.dao.FinOptionTargetMapper;
import com.joyintech.service.valuation.basicdata.asset.option.dto.FinOptionTargetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Service(value="serviceFinProdService")
public class FinProdService extends BaseService<FinOptionTargetDto> {

     @Autowired
     private FinOptionTargetMapper finOptionTargetMapper;
//     /**
//      * 附件信息共通Service
//      */
//     @Autowired(required=false)
//     private MstFileCommonService mstFileCommonService;

    /**
     * 获取标的代码
     * @param input
     * @return
     */
     public List<SuggestDto> getFinprodcodeList(String input){
         return finOptionTargetMapper.getFinprodcodeList(input);
     }

    /**
     * 分页查询期权标的信息
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception
     */
    public Page<FinOptionTargetDto> selectForPage(int page, int length, Map<String, Object> param)
        throws Exception {
        //导出时考虑标的类型被多选的情况
        String type =(String)param.get("targetType");
        if(StringUtil.isNotEmpty(type) && type.contains(",")){
            String[] list = type.split(",");
            param.put("tlist",list);
            param.put("targetType",null);
        }
        return super.queryForPage(page, length, () -> finOptionTargetMapper.selectForPage(param));
    }

    public List<FinOptionTargetDto> selectForList(Map<String, Object> param)
            throws Exception {
        //导出时考虑标的类型被多选的情况
        String type =(String)param.get("targetType");
        if(StringUtil.isNotEmpty(type) && type.contains(",")){
            String[] list = type.split(",");
            param.put("tlist",list);
            param.put("targetType",null);
        }
        return finOptionTargetMapper.selectForPage(param);
    }

    /**
     * 根据id获取期权标的信息
     * @param code
     * @return
     */
    public FinOptionTargetDto getFinprodcodebyId(String code){
        return finOptionTargetMapper.getFinprodcodebyId(code);
    }

    /**
     * 保存标的信息
     * @param dto
     * @return
     */
    public int  save(FinOptionTargetDto dto){
    	  //文件
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        mstFileInputParameter.setFileList(dto.getFileArray());
//        mstFileInputParameter.setBussinessId(dto.getTargetId());
//
//        if(mstFileCommonService != null){
//            mstFileCommonService.updateBusinessId(mstFileInputParameter);
//        }
        return finOptionTargetMapper.save(dto);
    }

    /**
     * 更新标的信息
     * @param dto
     * @return
     */
    public int  update(FinOptionTargetDto dto){
    	 //文件
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        mstFileInputParameter.setFileList(dto.getFileArray());
//        mstFileInputParameter.setBussinessId(dto.getTargetId());
//        if(mstFileCommonService != null){
//            mstFileCommonService.updateBusinessId(mstFileInputParameter);
//        }
        return finOptionTargetMapper.update(dto);
    }

    /**
     * 删除标的信息
     * @param dto
     * @return
     */
    public int  delete(FinOptionTargetDto dto){
        return finOptionTargetMapper.delete(dto);
    }
    /**
    *
    * <一句话功能简述>
    * <功能详细描述>
    * @param entity EtrFundDto
    * @throws IllegalAccessException IllegalAccessException
    * @throws InvocationTargetException InvocationTargetException
    * @see
    * @since 1.0
    */
   public void doInsert(FinOptionTargetDto entity)
       throws IllegalAccessException,
       InvocationTargetException {
       //文件
//       MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//       mstFileInputParameter.setFileList(entity.getFileArray());
//       mstFileInputParameter.setBussinessId(entity.getWorkflowId());
//       if(mstFileCommonService != null){
//           mstFileCommonService.updateBusinessId(mstFileInputParameter);
//       }
   }
}
