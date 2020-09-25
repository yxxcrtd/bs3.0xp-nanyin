$(function () {
    var getSearchParams = function () {
//       var param = $.serializeToObject('#searchForm');
        return $.serializeToObject('#searchForm');
    };
    var exportExcel = function () {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    var oneTable = $("#oneTable").joyintable({
        ajax: {
            //	url : 'findOnePage',
            url: basePath + '/invest/risk/stresstestmanage/riskfactor/RiskFactor001/findOnePage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        searchOnInit: false,
        checkbox: true,
        /*loadonce:true,
        searchOnInit:true,*/
        cols: [
            {field: 'type1', width: 110, title: '风险分类', sortField: 'type1'}
            , {field: 'type2', width: 110, title: '因子分类', sortField: 'type2'}
            , {field: 'ftrType2Name', width: 110, title: '因子二级分类', sortField: 'ftrtype2'}
            , {field: 'ftrid', width: 150, title: '因子代码', sortField: 'ftrid'}
            , {field: 'sdate', width: 150, title: '情景时间开始', sortable: false, visible: false}
            , {field: 'edate', width: 150, title: '情景时间结束', sortable: false, visible: false}
            , {field: 'ftrname', width: 180, title: '因子名称', sortField: 'ftrname'}
            , {field: 'ftrvaluestruct', width: 110, title: '因子类型', sortField: 'ftrvaluestruct'}
            , {field: 'valueexetype', width: 110, title: '变动形式', sortable: false}
            , {field: 'effstatus', width: 100, sortField: "effstatus1", title: '运用状态',
                render: function (data, type, row, meta) {
                    if (row.effstatus == '启用') {
                        return '<a class="btn layui-btn effstatus" title="运用状态" style="background:#81bb5e;color:white;">启用</a>';
                    } else {
                        return '<a class="btn layui-btn effstatus" title="运用状态" style="background:white;color:black;border:1px solid #e6e6e6;">停用</a>';
                    }

                }
            }
            , {field: 'remark', width: 200, title: '备注', sortable: false}
            , {
                field: 'finprodMarketId', title: '操作', width: 80,fixed:'right',
                render: function (data, type, row, meta) {
                    if (row.configid == 'N'|| purviewElements.indexOf('_batch')==-1) {

                    } else {
                        return '<span class="jyiconfont jyicon-history-data batch btn " title="历史数据" style="color:#208fff;margin-left:6px;"></span>';
                    }

                },
                sortable: false
            }
        ]

    });
    /**
     * 绑定编辑事件
     */
    oneTable.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_REFERENCE;
        data.operateType = 'EDIT';
        Joyin.dialog('风险因子编辑',
            basePath + "",
            "98%", "95%", data, function () {
                table.reload();
            });
    });

    /**
     * 历史数据弹出页面
     */
    oneTable.bind('click', '.batch', function (data, index) {
        var object = {selectedItem: [data], operateType: "select_data", listId: data.listId};//请求参数
        Joyin.dialog('因子历史数据',
            basePath + "/invest/risk/stresstestmanage/riskfactor/RiskFactor003/init",
            "1200", "85%", data);

        //   Joyin.dialog(JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.title"), '../ChannelAsset002/init', '98%', '98%');

    });

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialogOne() {
        Joyin.dialog('风险因子新建', basePath
            + "",
            "98%", "95%", {pageModel: PAGE_MODEL_NEW}, function () {
            });
    }

    /**
     * 绑定删除事件
     */
    oneTable.bind('click', '.delt', function (data, index) {
        Joyin.confirm("是否删除该风险因子?");
    });

    function onSaveOrSubmitAction(operateType) {
        object.pageModel = pageModel;
        object.operateType = 'save';
        Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList('/invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint002/save', object, "RskSupervisionPortfPoint002_btn_save");
                } else {
                    WorkFlow.doSubmitList('/invest/risk/limitmanagement/portfolioindicator/RskSupervisionPortfPoint002/save', object, "RskSupervisionPortfPoint002_btn_submit");
                }
            });
        });
    };
    /**
     * 画面加载完成
     */
    $(function () {
        ftrType2List = ftrType2List.map(function(item) {
            return {
                col: "FTR_RSK_TYPE2",
                id: item.ftrType2Code,
                text: item.ftrType2Value,
                type1Id:item.ftrTypeCode
            };
        });
        //$('#RiskFactor001_btn_filter').click();

        //新建
        $('#RiskFactor001_btn_add').click(function () {
            showDialogOne();
        });

        $('#AssetPool001_btn_access').click(function () {
            showDialogTwo();
        });

        $('#RiskFactor001_btn_search').click(function () {
            oneTable.load();//加载表格
        });
        $('#RiskFactor001_btn_export').click(function () {
            exportExcel();
        });
        $("#investtype1").change(function () {
            var dicode = "";
            if ($('#investtype1').val() != null && $('#investtype1').val() != '' && $('#investtype1').val() != '01') {
                dicode = 'FTR_RSK_TYPE_' + $('#investtype1').val();
            }
            resetInvestType2(dicode);
            resetTwoClass($('#investtype1').val(), null, null);
            resetMrkFtrType2($('#investtype1').val(), "");
        });
        $('#investtype1').trigger("change");
        $("#investtype2").change(function () {
            resetTwoClass($('#investtype1').val(), $('#investtype2').val(), null);
            resetMrkFtrType2("", $('#investtype2').val());
            /*$("#twoClass").attr('data-url','/tams/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorMap/'+value+'/'+value2);
            clearBasicCondition();*/
        });
        $("#mrkFtrType2").change(function () {
            resetTwoClass($('#investtype1').val(), $('#investtype2').val(), $('#mrkFtrType2').val());
        });

    });

    //重设因子分类下拉值
    function resetInvestType2(dicode){
        Joyin.ajax(JoyinUtil.getRealPath('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorType1/'), {dicode: dicode},function (result) {
            if (result) {
                var sceneOptArray = result;
                var option = "";
                for (var i = 0; i < sceneOptArray.length; i++) {
                    option += '<option value ="' + sceneOptArray[i].id + '">' + sceneOptArray[i].text + '</option>';
                }
                $('#investtype2').empty();
                $("#investtype2").append('<option value =""></option>' + option);

            }

        })

    }

    //重设因子名称下拉值
    function resetTwoClass(investType1Val, investType2Val, mrkFtrType2){
        Joyin.ajax(JoyinUtil.getRealPath('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorMap/'), {
            type1: investType1Val,
            type2: investType2Val,
            type3: mrkFtrType2
        },
        function (result) {
            if (result) {
                var sceneOptArray = result;
                var option = "";
                for (var i = 0; i < sceneOptArray.length; i++) {
                    option += '<option value ="' + sceneOptArray[i].id + '">' + sceneOptArray[i].text + '</option>';
                }
                $('#twoClass').empty();
                $("#twoClass").append('<option value =""></option>' + option);

            }

        })
    }

    //重设因子二级分类下拉值
    function resetMrkFtrType2(type1Val, type2Val){
        var list_tmp = [];
        if(type2Val==""){
            list_tmp = ftrType2List;
        }else{
            list_tmp = ftrType2List.filter(function(item){
                return item.id.indexOf(type2Val) == 0;
            });
        }
        var option = "";
        for (var i = 0; i < list_tmp.length; i++) {
            option += '<option value ="' + list_tmp[i].id + '">' + list_tmp[i].text + '</option>';
        }
        $('#mrkFtrType2').empty();
        $("#mrkFtrType2").append('<option value =""></option>' + option);

        var strIndex = type2Val.indexOf("Lqu");
        if(type1Val == '02' || strIndex == 0){
            $('#mrkFtrType2').prop('disabled', true).trigger('change');
        }else{
            $('#mrkFtrType2').prop('disabled', false).trigger('change');
        }
    }

    function clearBasicCondition() {
        $("#investtype2").val('').trigger('change');
        $("#twoClass").val('').trigger('change');
        $("#investtype1").val('').trigger('change');
    }

    $('#RiskFactor001_btn_y').click(function () {
        if (oneTable != null && oneTable != '') {
            var selArr = oneTable.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.warn(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var flag = true;
        var selectedItem = [];
        $.each(selArr, function (i, v) {
            if (v.effstatus1 == '01') {
                flag = false;
                return false;
            }
        })
        if (!flag) {
            Joyin.error(JoyinUtil.getMessage('RSK_W_0013', "停用"));
            return;
        }
        var object = {selectedItem: selArr, operateType: "Y"};
        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/riskfactor/RiskFactor001/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", "启用"), function () {
                object.pageModel = pageModel;
                object.operateType = 'save';
                WorkFlow.doSubmitList('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/submit', object, "RiskFactor001_btn_y","RiskFactor002");

            });
        });
    });
    $('#RiskFactor001_btn_n').click(function () {
        if (oneTable != null && oneTable != '') {
            var selArr = oneTable.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.warn(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var flag = true;
        var selectedItem = [];
        $.each(selArr, function (i, v) {
            if (v.effstatus1 != '01') {
                flag = false;
                return false;
            }
        })
        if (!flag) {
            Joyin.error(JoyinUtil.getMessage('RSK_W_0013', "启用"));
            return;
        }
        var object = {selectedItem: selArr, operateType: "N"};
        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/riskfactor/RiskFactor001/check', object, function (result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", "停用"), function () {
                object.pageModel = pageModel;
                object.operateType = 'save';
                WorkFlow.doSubmitList('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/submit', object, "RiskFactor001_btn_n","RiskFactor002");
            });
        });
    });

}());
