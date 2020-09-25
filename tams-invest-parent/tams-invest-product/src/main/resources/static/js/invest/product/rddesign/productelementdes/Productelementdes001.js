/**
 * 产品要素设计查询
 */
(function() {
    /**
     * 获取查询参数
     */

    var getPageParams = function() {
        var pageParam =  {
            dataStatus: $('#dataStatus').val()
            ,reportStatus2: $('#reportStatus2').val()
            ,prdDesignId: $('#prdDesignId').val()
            ,prdDesignSerialName: $('#prdDesignSerialName').val()
            ,raiseType: $('#raiseType').val()
            ,investNature: $('#investNature').val()
            ,SaletargetDeclare: $('#SaletargetDeclare').val()
            ,operateMode: $('#operateMode').val()
            ,tableId: "detail"
        };
        if ($("#dataStatus").val()) {
            pageParam.dataStatus = $("#dataStatus").val();
        } else {
            pageParam.dataStatus = null;
        }
        if ($("#reportStatus2").val()) {
            pageParam.reportStatus2 = $("#reportStatus2").val();
        } else {
            pageParam.reportStatus2 = null;
        }
        if ($('#prdDesignId').val()) {
            pageParam.prdDesignId = $("#prdDesignId").val();
        }else {
            pageParam.prdDesignId =null;
        }
        if ($('#prdDesignSerialName').val()) {
            pageParam.prdDesignSerialName = $("#prdDesignSerialName").val();
        }else {
            pageParam.prdDesignSerialName = null;
        }
        if ($("#raiseType").val()) {
            pageParam.raiseType = $("#raiseType").val();
        } else {
            pageParam.raiseType = null;
        }
        if ($("#investNature").val()) {
            pageParam.investNature = $("#investNature").val();
        } else {
            pageParam.investNature = null;
        }
        if ($("#SaletargetDeclare").val()) {
            pageParam.SaletargetDeclare = $("#SaletargetDeclare").val();
        } else {
            pageParam.SaletargetDeclare = null;
        }
        if ($("#operateMode").val()) {
            pageParam.operateMode = $("#operateMode").val();
        } else {
            pageParam.operateMode = null;
        }
        if ($('#dataTable').parent().attr("class") == "layui-tab-item") {
            pageParam.tableId = "detail";
        }else {
            pageParam.tableId = "total";
        }
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        height: 'full-' + 220,
//        dblclick:function(table, data) {
//            data.pageModel = PAGE_MODEL_REFERENCE;
//            Joyin.dialog(JoyinUtil.getLabel("FinProductDurDeal002"),
//                    JoyinUtil.getRealPath("invest/product/durdeal/FinProductDurDeal002/init"),
//                    "99%", "99%", data);
//        },
        cols:[
            {field:'dataStatusStr', width:120, title: 'Productelementdes001.statusValid',sortField:'dataStatus'}
            ,{field:'prdDesignId', width:120, title: 'Productelementdes001.prdDesignId'}
            ,{field:'prdDesignSerialName', width:120, title: 'Productelementdes001.prdDesignSerialName'}
            ,{field:'prdBaseGoal', width:200, title: 'Productelementdes001.prdBaseGoal'}
            ,{field:'prdCharater', width:120, title: 'Productelementdes001.prdCharater'}
            ,{field:'raiseTypeStr', width:130, title: 'Productelementdes001.raiseType',sortField:'raiseType'}
            ,{field:'investNatureStr', width:130, title: 'Productelementdes001.investNature',sortField:'investNature'}
            ,{field:'operateModeStr', width:130, title: 'Productelementdes001.SvrOperatemodeDeclare',sortField:'operateMode'}
            ,{field:'tarCustomerTypeStr', width:130, title: 'Productelementdes001.SaletargetDeclare',sortField:'tarCustomerType'}
            ,{field:'tarInvestor', width:130, title: 'Productelementdes001.tarInvestor'}
            ,{field:'rskLevelStr', width:130, title: 'Productelementdes001.iskLevel',sortField:'rskLevel'}
            ,{field:'openDescribe', width:130, title: 'Productelementdes001.openDescribe'}
            ,{field:'prdTerm', width:130, title: 'Productelementdes001.prdTerm'}
            ,{field:'planIssueDate', width:130, title: 'Productelementdes001.planIssueDate'}
            ,{field:'planIssueScale', width:130, title: 'Productelementdes001.planIssueScale'}
            ,{field:'saleArea', width:130, title: 'Productelementdes001.saleArea'}
            ,{field:'baseRuleInfo', width:130, title: 'Productelementdes001.baseRuleInfo'}
            ,{field:'feeRate', width:130, title: 'Productelementdes001.peeRate'}
            ,{field:'toffStart', width:130, title: 'Productelementdes001.toffStart'}
            ,{field:'hugeRedRatio', width:130, title: 'Productelementdes001.hugeRedRatto'}
            ,{field:'other', width:130, title: 'Productelementdes001.other'}
            ,{field:'requirementId', width:130, title: 'Productelementdes001.requirementId', type: 'action', buttons: ['showDetail'],
               render: function (data, type, row, meta) {
                   return "<a class='pro-link showDetail' style='cursor: pointer;'>" + row.requirementId + "</a>";
               }
            }
        ]
    });

    /**
     * 定义数据表格
     */
    var detailDataTable = $("#detailDataTable").joyintable({

        ajax: {
            url: 'findDetailPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        height: 'full-' + 220,
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            var isCycle = data.isCycle;
            var profitType = data.profitType;
            var dealType = data.dealType;
            if(profitType=='01' || profitType=='03'){
                if(profitType=='01'){
                    if(dealType=='C8'){
                        //净值型分红方案确认撤销
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme003.view"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/init"),
                            "98%", "95%", data,function(){detailDataTable.reload();});
                    }else if(dealType=='C9'){
                        //净值型红利下发撤销
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme004.view"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/init"),
                            "98%", "95%", data,function(){detailDataTable.reload();});
                    }else if(dealType=='C10'){
                        //净值型红利再投撤销
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme005.view"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/init"),
                            "98%", "95%", data,function(){detailDataTable.reload();});
                    }else{
                        Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.view"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                            "98%", "95%",data,function(){detailDataTable.reload();}
                        );
                    }
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.view"),
                        JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                        "98%", "95%",data,function(){detailDataTable.reload();}
                    );
                }


            }else if(profitType=='02'){
                if(dealType=='C1'||dealType=='C2'){//收益型产品认购申购
                    Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002.view"),
                        JoyinUtil.getRealPath("/invest/product/productduration/establish/FinProductEstablish002/init"),
                        "98%", "95%",data,function(){detailDataTable.reload();}
                    );
                    //alert("请到预期收益产品相关模块操作，暂时未集成");
                }
                else if(dealType=='C17'||dealType=='C18'){//收益型产品提前赎回
                    Joyin.dialog(JoyinUtil.getLabel("FinProductAddReduce002.view"),
                        JoyinUtil.getRealPath("/invest/product/productduration/addreduce/FinProductAddReduce002/init"),
                        "98%", "95%",data,function(){detailDataTable.reload();}
                    );
                }
                else if(dealType=='C7'){//收益型产品中间付息
                    Joyin.dialog(JoyinUtil.getLabel("FinProductPayInt003.view"),
                        JoyinUtil.getRealPath("/invest/product/payint/FinProductPayInt003/init"),
                        "98%", "95%",data,function(){detailDataTable.reload();}
                    );
                }
                else if(dealType=='C4'||dealType=='C5'||dealType=='C20'){//收益型产品赎回到期
                    Joyin.dialog(JoyinUtil.getLabel("FinProductExpire002.view"),
                        JoyinUtil.getRealPath("/invest/product/productduration/expire/FinProductExpire002/init"),
                        "98%", "95%",data,function(){detailDataTable.reload();}
                    );
                }
            }
        },
        cols:[

            {field:'dataStatusStr', width:120, title: 'Productelementdes001.statusValid',sortField:'dataStatus'}
            ,{field:'requirementId', width:130, title: 'Productelementdes001.requirementId'}
            ,{field:'requirementBasis', width:200, title: 'Productelementdes001.requirementBasis'}
            ,{field:'tarCustomer', width:120, title: 'Productelementdes001.tarCustomer'}
            ,{field:'prdScale', width:130, title: 'Productelementdes001.prdScale'}
            ,{field:'prdPeriod', width:130, title: 'Productelementdes001.prdPeriod'}
            ,{field:'prdRate', width:130, title: 'Productelementdes001.prdRate'}
            ,{field:'prdRequirementDetial', width:130, title: 'Productelementdes001.prdRequirementDetial'}
            ,{field:'requirementSourceStr', width:130, title: 'Productelementdes001.requirementSource',sortField:'requirementSource'}
            ,{field:'feasibilityStatusStr', width:130, title: 'Productelementdes001.feasibilityStatus',sortField:'feasibilityStatus'}
            ,{field:'feasibilitTypeStr', width:130, title: 'Productelementdes001.feasibilitType',sortField:'feasibilitType'}
            // ,{field: 'operation', title: 'Productelementdes001.table.operation', type: 'action', buttons: ['dealRevoke'],
            //     render: function (data, type, row, meta) {
            //         if(row.effectStatus=="01")
            //             return "<a class='pro-link dealRevoke' style='cursor: pointer;'>" + "撤销" + "</a>";
            //     }
            // }
        ],
        checkbox: true
    });

    /**
     * 绑定查看明细事件
     */
   table.bind('click', '.showDetail', function (data, index) {
   var requirementId=data.requirementId;
   Joyin.dialog(JoyinUtil.getLabel("Productelementdes002"),
           basePath + "invest/product/rddesign/productelementdes/productelementdes003/init",
           "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE, requirementId:requirementId
           });   });

    /**
     * 撤销界面--根据条件进入不同的交易撤销界面
     */
    detailDataTable.bind('click', '.dealRevoke', function (data, index) {
        data.pageModel = PAGE_MODEL_REFERENCE;
        data.operateType = 'revoke';
        var isCycle = data.isCycle;
        var profitType = data.profitType;
        var dealType = data.dealType;
        if(profitType=='01' || profitType=='03'){
            if(profitType=='01'){
                if(dealType=='C8'){
                    //净值型分红方案确认撤销
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme003.revoke"),
                        JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/init"),
                        "98%", "95%", data,function(){detailDataTable.reload();});
                }else if(dealType=='C9'){
                    //净值型红利下发撤销
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme004.revoke"),
                        JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/init"),
                        "98%", "95%", data,function(){detailDataTable.reload();});
                }else if(dealType=='C10'){
                    //净值型红利再投撤销
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme005.revoke"),
                        JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/init"),
                        "98%", "95%", data,function(){detailDataTable.reload();});
                }else{
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.revokepage"),
                        JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                        "98%", "95%",data,function(){detailDataTable.reload();}
                    );
                }
            }else{
                Joyin.dialog(JoyinUtil.getLabel("FinNetProductDuration.revokepage"),
                    JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();}
                );
            }


        }else if(profitType=='02'){
            if(dealType=='C1'||dealType=='C2'){//收益型产品认购申购撤销
                Joyin.dialog(JoyinUtil.getLabel("FinProductEstablish002.revoke"),
                    JoyinUtil.getRealPath("/invest/product/productduration/establish/FinProductEstablish002/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();}
                );
                //alert("请到预期收益产品相关模块操作，暂时未集成");
            }
            else if(dealType=='C17'||dealType=='C18'){//收益型产品提前赎回撤销
                Joyin.dialog(JoyinUtil.getLabel("FinProductAddReduce002.revoke"),
                    JoyinUtil.getRealPath("/invest/product/productduration/addreduce/FinProductAddReduce002/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();}
                );
            }
            else if(dealType=='C7'){//收益型产品中间付息撤销
                Joyin.dialog(JoyinUtil.getLabel("FinProductPayInt003.revoke"),
                    JoyinUtil.getRealPath("/invest/product/payint/FinProductPayInt003/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();}
                );
            }
            else if(dealType=='C4'||dealType=='C5'||dealType=='C20'){//收益型产品赎回到期撤销,增加提前终止
                Joyin.dialog(JoyinUtil.getLabel("FinProductExpire002.revoke"),
                    JoyinUtil.getRealPath("/invest/product/productduration/expire/FinProductExpire002/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();}
                );
            }
        }

    });
    var showDig=function () {
        
    }
    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.exportExcel('export', getPageParams());
    };

    /**
     * 同步
     */
    var synchroData = function() {
        Joyin.ajax(basePath + 'invest/product/durdeal/Productelementdes001/synchroData', null, function(result) {

        })
    };

    /**
     * 产品要素设计数据选择
     */
    var prdDesignIdEx;
    var showprdDesignId=function(){

        if(table!=null && table!=''){
            prdDesignIdEx = table.getAllCheckedRows();
        }

    }

    $(function () {
        //dataTable03.load();
        //显示隐藏要素设计、修改、
        $('#table1').click(function () {
            $('#Productelementdes001_btn_edit').css("display","none");
            $('#Productelementdes001_btn_delete').css("display","none");
            $('#Productelementdes001_btn_design').css("display","");
        })
        $('#table2').click(function () {
            $('#Productelementdes001_btn_design').css("display","none");
            $('#Productelementdes001_btn_edit').css("display","");
            $('#Productelementdes001_btn_delete').css("display","");
        })
        // 绑定按钮事件
        //查询
        $('#Productelementdes001_btn_search').click(function() {
            table.load();
            detailDataTable.load();
        });
        //产品要素设计
        $('#Productelementdes001_btn_design').click(function() {
            var requirementIdEx;
            if(detailDataTable!=null && detailDataTable!=''){
                requirementIdEx = detailDataTable.getAllCheckedRows();
            }
            if (!requirementIdEx || requirementIdEx.length !== 1) {
                Joyin.error(JoyinUtil.getMessage("Productelementdes002Error"));
                return;
            }
            //判断是否可行，是否为新系列
            if (requirementIdEx[0].feasibilityStatus!="01"||requirementIdEx[0].feasibilitType!="02"){
                Joyin.error(JoyinUtil.getMessage("Productelementdes002Error1"));
                return;
            }

            Joyin.dialog(JoyinUtil.getLabel("Productelementdes0021"),
                basePath + "invest/product/rddesign/productelementdes/productelementdes002/init",
                "90%", "90%", {pageModel:PAGE_MODEL_NEW,requirementId:requirementIdEx[0].requirementId,
                    dataStatus:requirementIdEx[0].dataStatus
                });
        });
        //修改
        $('#Productelementdes001_btn_edit').click(function() {
            showprdDesignId();
            if (!prdDesignIdEx || prdDesignIdEx.length !== 1) {
                Joyin.error(JoyinUtil.getMessage("Productelementdes002Error"));
                return;
            }
            //判断数据状态是否有效
            if (prdDesignIdEx[0].dataStatus!="01"){
                Joyin.error('操作的数据状态必须为有效');
                return;
            }
            prdDesignIdEx[0].operateType="edit";
            Joyin.dialog(JoyinUtil.getLabel("Productelementdes004"),
                basePath + "invest/product/rddesign/productelementdes/productelementdes004/init",
                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,prdDesignId:prdDesignIdEx[0].prdDesignId
                });
        });
        //删除
        $('#Productelementdes001_btn_delete').click(function() {
            showprdDesignId();
            if (!prdDesignIdEx || prdDesignIdEx.length !== 1) {
                Joyin.error(JoyinUtil.getMessage("Productelementdes002Error"));
                return;
            }
            //判断数据状态是否有效
            if (prdDesignIdEx[0].dataStatus!="01"){
                Joyin.error('操作的数据状态必须为有效');
                return;
            }
            prdDesignIdEx[0].operateType="delete";
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/rddesign/productelementdes/productelementdes001/delete', prdDesignIdEx[0],
                    "Productelementdes001_btn_delete","Productelementdes001");
            });
        });
        //导出
        $('#Productelementdes001_btn_export').click(function() {
            exportExcel();
        });
    });
}());