package com.joyintech.service.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.ckgradepdconfig.service.ModCkGradePdConfigService;
import com.joyintech.service.valuation.lossmodel.pddefaultmanager.gradepdconfig.dto.ModGradePdConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.framework.grid.Page;

/**
 * @Description:存款评级PD维护Controller
 * @auther: yuzj
 * @date: 2021-8-13
 * @param:
 * @return:
 */
@RestController(value="serviceModCkGradePdConfigController")
@RequestMapping("ModCkGradePdConfig")
public class ModCkGradePdConfigController{
    @Autowired
    private ModCkGradePdConfigService modCkGradePdConfigService;

    /**
     * @Description:分页查询
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ModGradePdConfigDto> selectForPage(@RequestBody Map<String,Object> params,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception {
        Page<ModGradePdConfigDto> pages = modCkGradePdConfigService.selectForPage(params,page,length);

        return pages;
    }


    /**
     * @Description:根据主键查询实体信息
     * @auther: yuzj
     * @date: 2021/7/1
     * @param:
     * @return:
     */
    @PostMapping("/selectByKey")
    public ModGradePdConfigDto selectByKey(@RequestBody ModGradePdConfigDto dto) throws IllegalAccessException, InvocationTargetException{
        return modCkGradePdConfigService.selectByKey(dto);
    }


    /**
     * 根据条件查询需要导出的数据
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectExportList")
    public List<ModGradePdConfigDto> selectExportList(@RequestBody ModGradePdConfigDto dto) throws IllegalAccessException, InvocationTargetException{
        return modCkGradePdConfigService.selectExportList(dto);
    }

    /**
     * 根据map条件查询数据
     * @param
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByMap")
    public List<ModGradePdConfigDto> selectByMap(@RequestBody Map<String,Object> params) throws IllegalAccessException, InvocationTargetException{
        return modCkGradePdConfigService.selectByMap(params);
    }

    /**
     * 根据主键查询映射明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByPrimaryKey")
    public ModGradePdConfigDto selectByPrimaryKey(@RequestBody ModGradePdConfigDto dto) throws IllegalAccessException, InvocationTargetException{
        return modCkGradePdConfigService.selectByPrimaryKey(dto);
    }




    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody ModGradePdConfigDto dto) throws Exception {
        return modCkGradePdConfigService.insert(dto);
    }

    /**
     * 更新数据
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody ModGradePdConfigDto dto) throws Exception {
        return modCkGradePdConfigService.update(dto);
    }

    /**
     *
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody ModGradePdConfigDto dto) throws Exception {
        return modCkGradePdConfigService.delete(dto);
    }

    /**
     *
     * 工作流完成
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("getRepeatCheck")
    public Boolean getRepeatCheck(@RequestBody ModGradePdConfigDto entity){
        Boolean flag =false;
        int num = modCkGradePdConfigService.getRepeatCheckList(entity);
        if(num>0){
            flag =true;
        }
        return flag;
    }




     /* 保存导入的存款评级PDexcel
     * <功能详细描述>
     * @param repProdIssue RepProdIssue
     * @return int
     * @see
     * @since 1.0
            */
    @Transactional
    @DTXTransaction
    @PostMapping("/saveCoverModGradePdConfigDtoList")
    public void saveCoverModGradePdConfigDtoList(@RequestBody List<ModGradePdConfigDto> dtoList){
        modCkGradePdConfigService.saveModGradePdConfigDtoList(dtoList,"1");
    }

    /* 保存导入的存款评级PDexcel
     * <功能详细描述>
     * @param repProdIssue RepProdIssue
     * @return int
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/saveModGradePdConfigDtoList")
    public void saveModGradePdConfigDtoList(@RequestBody List<ModGradePdConfigDto> dtoList){
        modCkGradePdConfigService.saveModGradePdConfigDtoList(dtoList,"0");
    }





    /**
     * 校验是否被使用过
     * @param map
     * @return
     */
    @PostMapping("checkBySeqNoAndType")
    public int checkBySeqNoAndType(@RequestBody Map<String, Object> map) {
        return modCkGradePdConfigService.checkBySeqNoAndType(map);
    }



}
