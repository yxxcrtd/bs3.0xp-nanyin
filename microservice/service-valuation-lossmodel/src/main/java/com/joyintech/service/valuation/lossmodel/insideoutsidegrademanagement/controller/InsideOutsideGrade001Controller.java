
package com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGradeDetail001Dto;
import com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.service.InsideOutsideGrade001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 内外部评级映射管理微服务控制类
 * 
 * @author 刘华明
 * @version 1.0 2021年7月6日
 * @see InsideOutsideGrade001Controller
 * @since 1.0
 */
@RestController("InsideOutsideGrade001Ctl")
@RequestMapping("InsideOutsideGrade001")
public class InsideOutsideGrade001Controller {

    /**
     * InsideOutsideGrade001Service
     */
    @Autowired
    private InsideOutsideGrade001Service insideOutsideGrade001Service;


    /**
     * 删除
     *
     * @param dto 页面选中的数据
     * @return 结果
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody InsideOutsideGrade001Dto dto) throws Exception {
        return insideOutsideGrade001Service.delete(dto);
    }

    /**
     * 修改
     * 先删除映射记录，在插入新的映射关系记录
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("update")
    public void update(@RequestBody InsideOutsideGrade001Dto dto) throws Exception {

        insideOutsideGrade001Service.delete(dto);

        for (InsideOutsideGradeDetail001Dto item:dto.getSelectInsideOutsideDetail()) {
            InsideOutsideGrade001Dto data = new InsideOutsideGrade001Dto();
            data.setSeqNo(IDGenerator.generate());
            data.setGradeInst(dto.getGradeInst());
            data.setoType(dto.getoType());
            data.setGradeResult(item.getGradeResult());
            data.setGradeStandardResult(item.getGradeStandardResult());
            insideOutsideGrade001Service.insert(data);
        }
    }

    /**
     * 新增
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @PostMapping("insert")
    public void insert(@RequestBody InsideOutsideGrade001Dto dto) throws Exception {
        for (InsideOutsideGradeDetail001Dto item:dto.getSelectInsideOutsideDetail()) {
            InsideOutsideGrade001Dto data = new InsideOutsideGrade001Dto();
            data.setSeqNo(IDGenerator.generate());
            data.setGradeInst(dto.getGradeInst());
            data.setoType(dto.getoType());
            data.setGradeResult(item.getGradeResult());
            data.setGradeStandardResult(item.getGradeStandardResult());
            insideOutsideGrade001Service.insert(data);
        }
    }

    /**
     * 检查数据是否已经存在
     * @param dto
     * @return
     */
    @PostMapping("/haveDataCheck")
    public boolean haveDataCheck(@RequestBody InsideOutsideGrade001Dto dto) {
        return insideOutsideGrade001Service.haveData(dto);
    }

    /**
     * 根据评级机构和对象类型查询
     *
     * @return List<InsideOutsideGrade001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getQueryDataListPage/{page}/{length}")
    public Page<InsideOutsideGrade001Dto> getQueryDataListPage(@RequestBody Map<String,Object> params,
                                                                    @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return insideOutsideGrade001Service.getQueryDataList(page,length,params);
    }

    /**
     * 根据评级机构和对象类型查询
     *
     * @return List<InsideOutsideGrade001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getDataBySeqNo")
    public List<InsideOutsideGrade001Dto> getDataBySeqNo(@RequestBody Map<String,Object> map) throws Exception {
        return insideOutsideGrade001Service.getBySeqNo(map);
    }

    /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<InsideOutsideGrade001Dto> selectForExport(@RequestBody Map<String, Object> map) {
        return insideOutsideGrade001Service.selectForExport(map);
    }
}
