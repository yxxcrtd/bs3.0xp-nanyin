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

/*
package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.ResponseResultBody;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.request.*;
import com.joyintech.tams.exchange.service.trade.management.service.ServiceNodeManagement;
import com.joyintech.tams.exchange.service.trade.management.service.TerminalManagement;
import com.joyintech.tams.exchange.service.trade.management.service.TerminalMappingManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trade/joyin")
@ResponseResultBody
@Api(tags = "场内交易服务节点配置接口")
public class SystemParamController {

    @Autowired
    private ServiceNodeManagement serviceNodeManagement;

    @Autowired
    private TerminalManagement terminalManagement;

    @Autowired
    private TerminalMappingManagement terminalMappingManagement;


    @ApiOperation(value = "查询服务节点信息", notes = "查询服务节点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userId", value = "交易员编号"),
            @ApiImplicitParam(required = false, name = "nodeName", value = "节点名称")
    })
    @PostMapping(value = "/serviceNode")
    @Validated
    public ResultBean queryServiceNode(@RequestParam String userId,
                                       @RequestParam(required = false) String nodeName) {
        return serviceNodeManagement.queryServiceNode(nodeName);
    }

    @ApiOperation(value = "添加服务节点信息", notes = "添加服务节点信息")
    @PostMapping(value = "/add/serviceNode")
    public ResultBean addServiceNode(@RequestBody @Validated ServiceNodeRequest request) {
        return serviceNodeManagement.addServiceNode(request);
    }

    @ApiOperation(value = "修改服务节点信息", notes = "修改服务节点信息")
    @PostMapping(value = "/update/serviceNode")
    public ResultBean updateServiceNode(@RequestBody @Validated ServiceNodeRequest request) {
        return serviceNodeManagement.updateServiceNode(request);
    }

    @ApiOperation(value = "删除服务节点信息", notes = "删除服务节点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userId", value = "交易员编号"),
            @ApiImplicitParam(required = true, name = "nodeName", value = "节点名称")
    })
    @PostMapping(value = "/delete/serviceNode")
    @Validated
    public ResultBean deleteServiceNode(@RequestParam String userId,
                                        @RequestParam String nodeName) {
        return serviceNodeManagement.deleteServiceNode(nodeName);
    }

    @ApiOperation(value = "查询终端信息", notes = "查询终端信息")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userId", value = "交易员编号"),
            @ApiImplicitParam(required = false, name = "terminalName", value = "终端名称")
    })
    @PostMapping(value = "/terminal")
    @Validated
    public ResultBean queryTerminal(@RequestParam String userId,
                                    @RequestParam(required = false) String terminalName) {
        return terminalManagement.queryTerminalInfo(terminalName);
    }

    @ApiOperation(value = "添加终端信息", notes = "添加终端信息")
    @PostMapping(value = "/add/terminal")
    public ResultBean addTerminal(@RequestBody @Validated TerminalInsertRequest request) {
        return terminalManagement.addTerminalInfo(request);
    }

    @ApiOperation(value = "修改终端信息", notes = "修改终端信息")
    @PostMapping(value = "/update/terminal")
    public ResultBean updateTerminal(@RequestBody @Validated TerminalUpdateRequest request) {
        return terminalManagement.updateTerminalInfo(request);
    }

    @ApiOperation(value = "删除终端信息", notes = "删除终端信息")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userId", value = "交易员编号"),
            @ApiImplicitParam(required = true, name = "terminalName", value = "终端名称")
    })
    @PostMapping(value = "/delete/terminal")
    @Validated
    public ResultBean deleteTerminal(@RequestParam String userId,
                                     @RequestParam String terminalName) {
        return terminalManagement.deleteTerminalInfo(terminalName);
    }

    @ApiOperation(value = "查询账户终端映射信息", notes = "查询账户终端映射信息")
    @PostMapping(value = "/terminalMapping")
    public ResultBean queryTerminalMapping(@RequestBody @Validated TerminalMappingRequest request) {
        return terminalMappingManagement.queryTerminalMappingInfo(request);
    }

    @ApiOperation(value = "添加账户终端映射信息", notes = "添加账户终端映射信息")
    @PostMapping(value = "/add/terminalMapping")
    public ResultBean addTerminalMapping(@RequestBody @Validated TerminalMappingInsertRequest request) {
        return terminalMappingManagement.addTerminalMappingInfo(request.getTerminalMappingList());
    }

    @ApiOperation(value = "更新账户终端映射信息", notes = "更新账户终端映射信息")
    @PostMapping(value = "/update/terminalMapping")
    public ResultBean updateTerminalMapping(@RequestBody @Validated TerminalMappingUpdateRequest request) {
        return terminalMappingManagement.updateTerminalMappingInfo(request);
    }

    @ApiOperation(value = "删除账户终端映射信息", notes = "删除账户终端映射信息")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userId", value = "交易员编号"),
            @ApiImplicitParam(required = true, name = "id", value = "账户终端映射信息编号")
    })
    @PostMapping(value = "/delete/terminalMapping")
    @Validated
    public ResultBean deleteTerminalMapping(@RequestParam String userId,
                                            @RequestParam String id) {
        return terminalMappingManagement.deleteTerminalMappingInfo(id);
    }
}
*/
