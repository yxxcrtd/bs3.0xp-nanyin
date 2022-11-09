/**
 * 证券账户管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            prodId:$('#prodId').val()== null ? null : $('#prodId').val().join(","),
            prodName:$('#prodName').val() == null ? null : $('#prodName').val().join(","),
            raiseStrdateStart: $.trim($('#raiseStrdate').startDate()),
            raiseStrdateEnd: $.trim($('#raiseStrdate').endDate()),
            setDateStart: $.trim($('#setDate').startDate()),
            setDateEnd: $.trim($('#setDate').endDate()),
            prodRegistNo:$('#prodRegistNo').val() == null ? null : $('#prodRegistNo').val().join(","),
            status : $('#status').val() == null ? null : $('#status').val().join(","),
            raiseType:$('#raiseType').val() == null ? null : $('#raiseType').val().join(","),
            isGrade:$('#isGrade').val() == null ? null : $('#isGrade').val().join(","),
            profitType:$('#profitType').val() == null ? null : $('#profitType').val().join(",")
        };
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick: function (table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel(" 产品基本信息查看"),
                basePath + "disclosure/information/config/repprodinfo/ProdInfo002/init",
                "99%", "95%", data);
        },
        cols:[
            {field:'prodId', sortable:true, width:150, title: '子产品代码',sortField:'PROD_ID'}
            ,{field:'prodName', sortable:true, width:200, title: '子产品名称',sortField:'PROD_NAME'}
            ,{field:'setDateStr', sortable:true, width:120, title: '子产品成立日期',sortField:'SET_DATE'}
            ,{field:'pdStatusName', sortable:true, width:100, title: '产品状态',sortField:'PD_STATUS'}
            ,{field:'issueStatusStr', sortable:true, width:100, title: '发行状态',sortField:'ISSUE_STATUS'}
            ,{field:'prodSeries', sortable:true, width:100, title: '产品系列',sortField:'PROD_SERIES'}
            ,{field:'pProdName', sortable:true, width:200, title: '产品名称',sortField:'pProdName'}
            ,{field:'prodAbbr', sortable:true, width:150, title: '产品简称',sortField:'PROD_ABBR'}
            ,{field:'prodRe', sortable:true, width:150, title: '产品登记编码',sortField:'PROD_RE'}
            ,{field:'pProdId', sortable:true, width:150, title: '产品主代码',sortField:'P_PROD_ID'}
            ,{field:'raiseStrdateStr', sortable:true, width:120, title: '募集起始日期',sortField:'RAISE_STRDATE'}
            ,{field:'raiseEnddateStr', sortable:true, width:120, title: '募集结束日期',sortField:'RAISE_ENDDATE'}
            ,{field:'raiseAmt', sortable:true, width:120, title: '总产品募集金额',sortField:'RAISE_AMT'}
            ,{field:'pSetDateStr', sortable:true, width:120, title: '产品成立日期',sortField:'pSetDate'}
            ,{field:'mdateStr', sortable:true, width:120, title: '产品到期日期',sortField:'MDATE'}
            ,{field:'raiseTypeName', sortable:true, width:100, title: '募集方式',sortField:'RAISE_TYPE'}
            ,{field:'prodTypeName', sortable:true, width:100, title: '产品类型',sortField:'PROD_TYPE'}
            ,{field:'issuingWayName', sortable:true, width:100, title: '发行方式',sortField:'ISSUING_WAY'}
            ,{field:'openFreqName', sortable:true, width:150, title: '开放频率',sortField:'OPEN_FREQ'}
            ,{field:'holdday', sortable:true, width:120, title: '最低持有天数',sortField:'HOLDDAY'}
            ,{field:'profitTypeName', sortable:true, width:120, title: '产品运作方式',sortField:'PROFIT_TYPE'}
            ,{field:'isPeriodName', sortable:true, width:150, title: '是否是客户周期型产品',sortField:'IS_PERIOD'}
            ,{field:'isGradeName', sortable:true, width:120, title: '是否是分级产品',sortField:'IS_GRADE'}
            ,{field:'isIndexName', sortable:true, width:130, title: '是否是指数型产品',sortField:'IS_INDEX'}
            ,{field:'performCompare', sortable:true, width:130, title: '产品业绩比较基准',sortField:'PERFORM_COMPARE'}
            ,{field:'natureInvestName', sortable:true, width:100, title: '投资性质',sortField:'NATURE_INVEST'}
            ,{field:'acctChnname', sortable:true, width:130, title: '产品托管人名称',sortField:'ACCT_CHNNAME'}
            ,{field:'statusName',  sortable:true,width:100 , title: '数据状态',sortField:'STATUS'}
            ,{field:'trustpart', sortable:true, width:100, title: '托管部门',sortField:'TRUSTPART'}
            ,{field:'errormsg', sortable:true, width:100, title: '错误信息',sortField:'ERRORMSG'}
            ,{field:'updateDateStr', sortable:true, width:150, title: '更新时间',sortField:'UPDATE_TIME'}
            ,{field:'updateUser', sortable:true, width:100, title: '更新人',sortField:'UPDATE_USER'}
            ,{field:'operation', width:100, title: 'SysCommon.operation',sortable:true, type:'action',
                buttons: ['edit', 'delt'],render: function (data, type, row, meta) {
                    if (row.status == 'D'|| row.status == 'C')
                        return '';
                    else
                        return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
                            '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                }
            }
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType='edit'
        data.pageModel=PAGE_MODEL_EDIT;
        data.editFlag='01'
        if(data.pdStatus !=null && data.pdStatus =='01'){
            Joyin.confirm("请确认是否修改?", function(){
                Joyin.dialog(JoyinUtil.getLabel("产品基本信息编辑"),
                    basePath + "disclosure/information/config/repprodinfo/ProdInfo002/init",
                    "98%", "95%",data,

                    function(){table.reload();});
            });
        }else{
             Joyin.error("当前产品不能修改 !");
        }
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/delete', object, "ProdInfo001_btn_delete", "ProdInfo002",function(){
                table.reload();
            });
        });
    });

    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    var reset = function(conditionArea,table){
        $(conditionArea).find("input").val("");
        $(conditionArea).find("select").val("").trigger("change");// select清空
    }
    function addrule() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage003/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(!finprodIds || finprodIds.length ==0){
                    return;
                }
                $('#prodId').textSelect('setValue',finprodIds);
            });
        return;
    };
    function addrule1() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage004/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(!finprodIds || finprodIds.length ==0){
                    return;
                }
                $('#prodName').textSelect('setValue',finprodIds);
            });
        return;
    };
    /**
     * 画面加载完成
     */
    $(function() {
        //新建
        $('#ProdInfo001_btn_design').click(function() {
            Joyin.dialog(JoyinUtil.getLabel(' 产品基本信息新建'),
                basePath + "disclosure/information/config/repprodinfo/ProdInfo002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW,editFlag:'01'});
        });
        $('#ProdInfo001_btn_issueStatusMan').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
                if( selArr != null &&  selArr.length > 0 ){
                    var data = {};
                    data.selectedItem = selArr;
                    data.operateType = 'issueStatus';
                    //Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo003/check', data, function(result) {
                    var prodIdStr = "";
                    var confirmFlag = false ;
                    $.each(selArr, function(idx,v) {
                        if(v.issueStatus =='01' || v.issueStatus =='02'){
                            confirmFlag = true ;
                        }
                        prodIdStr = prodIdStr + v.prodId + ",";
                    });
                    // if(confirmFlag){
                    //     Joyin.confirm("只有发行状态为确认发行的数据才能处理");
                    // }else{
                    //     // 跳转发行状态管理页面
                    //     Joyin.dialog(JoyinUtil.getLabel("发行状态管理"),
                    //         JoyinUtil.getRealPath("disclosure/information/config/repprodinfo/ProdInfo003/init"),
                    //         "70%", "80%",{prodIdStr : prodIdStr.substring(0, prodIdStr.length - 1)},function(){table.reload();}
                    //     );
                    // }
                    Joyin.dialog(JoyinUtil.getLabel("发行状态管理"),
                        JoyinUtil.getRealPath("disclosure/information/config/repprodinfo/ProdInfo003/init"),
                        "70%", "80%",{prodIdStr : prodIdStr.substring(0, prodIdStr.length - 1)},function(){table.reload();}
                    );
                    //});
                }else{
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                    return;
                }
            }else{
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        });

        // 绑定按钮事件
        $('#ProdInfo001_btn_treeAdd').click(addrule);
        $('#ProdInfo001_btn_treeChildrenAdd').click(addrule1);
        $('#ProdInfo001_btn_search').click(function() {
            table.load();
            $('#ProdInfo001_btn_delete').show();
        });
        $('#ProdInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#ProdInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请至少选择一条记录"));
                return;
            }
            var selArrUseful =[];
            var selArrDFH = [];
            var selArrDelete = [];
            selArr.forEach(v => {
                if(v.status == 'E'||v.status == 'A'){
                    //selArr.remove();
                    selArrUseful.push(v);
                }
                if(v.status == 'C'){
                    //selArr.remove();
                    selArrDFH.push(v);
                }
                if(v.status == 'D'){
                    //selArr.remove();
                    selArrDelete.push(v);
                }
            })
            if ( selArrDFH.length > 0) {
                Joyin.error(JoyinUtil.getMessage("数据在流程中，不允许删除"));
                return;
            }
            if ( selArrDelete.length > 0) {
                Joyin.error(JoyinUtil.getMessage("只能删除有效和待处理数据，请重新选择!"));
                return;
            }
            if ((!selArrUseful || selArrUseful.length == 0)){
                Joyin.error(JoyinUtil.getMessage("只能删除有效和待处理数据，请重新选择!"));
                return;
            }


            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/delete', object, "ProdInfo001_btn_delete", "ProdInfo002",function(){
                    table.reload();
                });
            });
        });

        /** 事件绑定: 置空按钮 */
        $("#ProdInfo001_btn_reset").click(function(event){
            reset( '#selectArea',table);
        });


        /**
         * 导入按钮
         */

        // $('#ProdInfo001_btn_import').click(function() {
        //     Joyin.confirm("请确认是否要导入数据？", function () {
        //         Joyin.uploadFile({pageId:'ProdInfo001'},function(fileName) {
        //             Joyin.ajax(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/checkImportData',
        //                 {fileName: fileName}, function(checkResult) {
        //                     if (checkResult) {
        //                         setTimeout( function () {
        //                                 if(checkResult.data.errorMsg){
        //                                     Joyin.error(checkResult.data.errorMsg);
        //                                     return;
        //                                 }
        //                                 if (checkResult.data.confirmMsg) {
        //                                     Joyin.confirm(checkResult.data.confirmMsg, function () {
        //                                         var data = {
        //                                             tempList: checkResult.data.correctData
        //                                         }
        //                                         Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/importFile', data,
        //                                             function (resultObj) {
        //                                                 if (resultObj.statuCode.toString() === '200') {
        //                                                     /** modify by yanya on 20210329 修正导入逻辑**/
        //                                                     //Joyin.alert("文件导入成功！");
        //                                                     table.reload();
        //                                                     return;
        //                                                 }
        //                                             });
        //                                     },{}, function () {
        //                                         layui.layer.closeAll()
        //                                         table.reload();
        //                                     });
        //                                 }else {
        //                                     var data = {
        //                                         tempList: checkResult.data.correctData
        //                                     }
        //                                     Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/importFile', data,
        //                                         function (resultObj) {
        //                                             if (resultObj.statuCode.toString() === '200') {
        //                                                 /** modify by yanya on 20210329 修正导入逻辑**/
        //                                                 //Joyin.alert("文件导入成功！");
        //                                                 table.reload();
        //                                                 return;
        //                                             }
        //                                         });
        //                                 }
        //                             }
        //                             ,1000);
        //                     }
        //                 });
        //         },function() {
        //             table.reload();
        //         });
        //     });
        // });



        $('#ProdInfo001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function () {
                Joyin.uploadFile({pageId:'ProdInfo001'},function(fileName) {
                    Joyin.ajax('checkImportData', {fileName: fileName}, function() {
                        Joyin.ajax('importFile', {fileName: fileName});
                        /*if (checkResult) {
                            setTimeout( function () {
                                    if(checkResult.data.errorMsg){
                                        Joyin.error(checkResult.data.errorMsg);
                                        return;
                                    }
                                    if (checkResult.data.confirmMsg) {
                                        Joyin.confirm(checkResult.data.confirmMsg, function () {
                                            if (checkResult.data.confirmMsg2) {
                                                Joyin.confirm(checkResult.data.confirmMsg2, function () {
                                                    var data = {
                                                        tempList: checkResult.data.correctData
                                                    }
                                                    Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/importFile', data,
                                                        function (resultObj) {
                                                            if (resultObj.statuCode.toString() === '200') {
                                                                table.reload();
                                                                return;
                                                            }
                                                        });
                                                },{}, function () {
                                                    layui.layer.closeAll()
                                                    table.reload();
                                                });
                                            }else{
                                                var data = {
                                                    tempList: checkResult.data.correctData
                                                }
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/importFile', data,
                                                    function (resultObj) {
                                                        if (resultObj.statuCode.toString() === '200') {
                                                            table.reload();
                                                            return;
                                                        }
                                                    });
                                            }

                                        },{}, function () {
                                            layui.layer.closeAll()
                                            table.reload();
                                        });
                                    }else {
                                        if (checkResult.data.confirmMsg2) {
                                            Joyin.confirm(checkResult.data.confirmMsg2, function () {
                                                var data = {
                                                    tempList: checkResult.data.correctData
                                                }
                                                Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/importFile', data,
                                                    function (resultObj) {
                                                        if (resultObj.statuCode.toString() === '200') {
                                                            table.reload();
                                                            return;
                                                        }
                                                    });
                                            },{}, function () {
                                                layui.layer.closeAll()
                                                table.reload();
                                            });
                                        }else{
                                            var data = {
                                                tempList: checkResult.data.correctData
                                            }
                                            Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo001/importFile', data,
                                                function (resultObj) {
                                                    if (resultObj.statuCode.toString() === '200') {
                                                        table.reload();
                                                        return;
                                                    }
                                                });
                                        }
                                    }
                                }
                                ,1000);
                        }*/
                    },function() {
                        $('iframe').contents().find("#loading").hide();
                    });
                },function() {
                    table.reload();
                });
            });
        });

    });
}());