
package com.joyintech.service.entrust.etrstockconnect.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectPoDto;
import com.joyintech.service.entrust.etrstockconnect.service.EtrStockConnect001Service;
import com.joyintech.service.entrust.etrstockconnect.service.EtrStockConnect002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 港股通指令
 *
 * @see EtrStockConnect001Controller
 * @since 1.0
 */
@RestController("EtrStockConnect001Ctr_dtx")
@RequestMapping("EtrStockConnect001")
public class EtrStockConnect001Controller {
    @Autowired
    private EtrStockConnect001Service etrStockConnect001Service;

    /**
     * 指令管理查询
     *
     * @param param param
     * @return Page<EtrStockConnect002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectEtrStockConnect")
    public List<EtrStockConnectDto> selectEtrStockConnect(@RequestBody Map<String,Object> param){
        return etrStockConnect001Service.selectEtrStockConnect(param);
    }

    /**
     * 检索产品持仓分页数据。 <功能详细描述>
     * 
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrStockConnect002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPositionsPage/{page}/{length}")
    public Page<EtrStockConnectPoDto> findPositionsPage(@RequestBody Map<String, Object> pageParam,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
        throws Exception {
        return etrStockConnect001Service.findPositionsPage(page, length, pageParam);
    }

}
