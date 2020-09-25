package com.joyintech.service.period.trdsecovertransfer.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.period.trdsecovertransfer.service.TrdSecOverTransferService;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration.TrdSecOverdueDurationZrDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("TrdSecOverTransferCtr_dtx")
@RequestMapping("/TrdSecOverTransfer")
public class TrdSecOverTransferController {
    @Autowired
    private TrdSecOverTransferService trdSecOverTransferService;
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdSecOverdueDurationZrDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                           @PathVariable("length") int length) {
        return trdSecOverTransferService.findPage(page, length, param);
    }
}
