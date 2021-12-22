package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.db.domain.ServiceNode;
import com.joyintech.tams.exchange.common.db.domain.Terminal;
import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import com.joyintech.tams.exchange.service.trade.management.service.ServiceNodeManagement;
import com.joyintech.tams.exchange.service.trade.management.service.TerminalManagement;
import com.joyintech.tams.exchange.service.trade.management.service.TerminalMappingManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/joyin")
public class SystemParamController {

    @Autowired
    private ServiceNodeManagement serviceNodeManagement;

    @Autowired
    private TerminalManagement terminalManagement;

    @Autowired
    private TerminalMappingManagement terminalMappingManagement;

    /**
     * 自定义节点查询
     *
     * @param userId 用户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/serviceNode", method = RequestMethod.GET)
    public ResultBean queryServiceNode(@RequestParam(value = "user_id") String userId,
                                       @RequestParam(value = "node_name", required = false) String nodeName) {
        return serviceNodeManagement.queryServiceNode(nodeName);
    }

    /**
     * 节点添加
     *
     * @param userId      用户Id
     * @param serviceNode 新增节点
     * @return ResultBean
     */
    @RequestMapping(value = "/add/serviceNode", method = RequestMethod.POST)
    public ResultBean addServiceNode(@RequestParam(value = "user_id") String userId,
                                     @RequestBody ServiceNode serviceNode) {
        return serviceNodeManagement.addServiceNode(serviceNode);
    }

    /**
     * 节点修改
     *
     * @param userId      用户Id
     * @param serviceNode 修改节点信息
     * @return ResultBean
     */
    @RequestMapping(value = "/update/serviceNode", method = RequestMethod.POST)
    public ResultBean updateServiceNode(@RequestParam(value = "user_id") String userId,
                                        @RequestBody ServiceNode serviceNode) {
        return serviceNodeManagement.updateServiceNode(serviceNode);
    }

    /**
     * 账号删除
     *
     * @param userId   用户Id
     * @param nodeName 节点名称
     * @return ResultBean
     */
    @RequestMapping(value = "/delete/serviceNode", method = RequestMethod.POST)
    public ResultBean deleteServiceNode(@RequestParam(value = "user_id") String userId,
                                        @RequestParam(value = "node_name") String nodeName) {
        return serviceNodeManagement.deleteServiceNode(nodeName);
    }

    /**
     * @param userId
     * @param terminalName
     * @return
     */
    @RequestMapping(value = "/terminal", method = RequestMethod.GET)
    public ResultBean queryTerminal(@RequestParam(value = "user_id") String userId,
                                    @RequestParam(value = "terminal_name", required = false) String terminalName) {
        return terminalManagement.queryTerminalInfo(terminalName);
    }

    /**
     * @param userId
     * @param terminal
     * @return
     */
    @RequestMapping(value = "/add/terminal", method = RequestMethod.POST)
    public ResultBean addTerminal(@RequestParam(value = "user_id") String userId,
                                  @RequestBody Terminal terminal) {
        return terminalManagement.addTerminalInfo(terminal);
    }

    /**
     * @param userId
     * @param terminal
     * @return
     */
    @RequestMapping(value = "/update/terminal", method = RequestMethod.POST)
    public ResultBean updateTerminal(@RequestParam(value = "user_id") String userId,
                                     @RequestParam(value = "terminal_name") String terminalName,
                                     @RequestBody Terminal terminal) {
        return terminalManagement.updateTerminalInfo(terminalName, terminal);
    }

    /**
     * @param userId
     * @param terminalName
     * @return
     */
    @RequestMapping(value = "/delete/terminal", method = RequestMethod.POST)
    public ResultBean deleteTerminal(@RequestParam(value = "user_id") String userId,
                                     @RequestParam(value = "terminal_name") String terminalName) {
        return terminalManagement.deleteTerminalInfo(terminalName);
    }

    /**
     * @param userId
     * @param accountId
     * @param terminalName
     * @return
     */
    @RequestMapping(value = "/terminalMapping", method = RequestMethod.GET)
    public ResultBean queryTerminalMapping(@RequestParam(value = "user_id") String userId,
                                           @RequestParam(value = "account_id", required = false) String accountId,
                                           @RequestParam(value = "terminal_name", required = false) String terminalName) {
        return terminalMappingManagement.queryTerminalMappingInfo(accountId, terminalName);
    }

    /**
     * @param userId
     * @param terminalMappings
     * @return
     */
    @RequestMapping(value = "/add/terminalMapping", method = RequestMethod.POST)
    public ResultBean addTerminalMapping(@RequestParam(value = "user_id") String userId,
                                         @RequestBody List<TerminalMapping> terminalMappings) {
        return terminalMappingManagement.addTerminalMappingInfo(terminalMappings);
    }

    /**
     * @param userId
     * @param terminalMapping
     * @return
     */
    @RequestMapping(value = "/update/terminalMapping", method = RequestMethod.POST)
    public ResultBean updateTerminalMapping(@RequestParam(value = "user_id") String userId,
                                            @RequestParam(value = "id") String id,
                                            @RequestBody TerminalMapping terminalMapping) {
        return terminalMappingManagement.updateTerminalMappingInfo(id, terminalMapping);
    }

    /**
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/terminalMapping", method = RequestMethod.POST)
    public ResultBean deleteTerminalMapping(@RequestParam(value = "user_id") String userId,
                                            @RequestParam(value = "id") String id) {
        return terminalMappingManagement.deleteTerminalMappingInfo(id);
    }
}
