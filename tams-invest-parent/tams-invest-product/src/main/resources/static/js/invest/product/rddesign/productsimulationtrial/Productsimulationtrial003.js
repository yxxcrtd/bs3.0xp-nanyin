(function () {

    var getElementById = function (id) {
        return $("#" +productSimulationTrial + "_" + id);
    };


    /**
     * 模拟资产列表（Total）
     */
    var pledgeBondTotalTbl = getElementById("productSimulationTrialTotalList").joyintable({
        page: false,
        data: (entity && entity.productSimulationTrialList) ? entity.productSimulationTrialList : [],
        height: 200,
        addbtn:true,
        /**
         * 定义表头
         */
        cols: [
            // 资产大类
            {
                field: 'assetType',
                width: 180,
                title: 'ProductSimulationTrial002.assetType',
                sortable: false
            }
            // 资产风险等级
            , {
                field: 'assetRiskLevel',
                width: 120,
                title: 'ProductSimulationTrial002.assetRiskLevel',
                sortable: false
            }
            //模拟匹配金额（元）
            , {
                field: 'mockAmt',
                width: 200,
                title: 'ProductSimulationTrial002.mockAmt',
                type: 'money',
                sortable: false
            }
            // 模拟匹配份额
            , {
                field: 'mockShareAmt',
                width: 170,
                title: 'ProductSimulationTrial002.mockShareAmt',
                type: 'number',
                sortable: false
            }
            //收益率上限
            , {
                field: 'rateMax',
                width: 160,
                type: 'number',
                title: 'ProductSimulationTrial002.rateMax',
                sortable: false
            }
            //收益率下限
            , {
                field: 'rateMix',
                width: 160,
                type: 'number',
                title: 'ProductSimulationTrial002.rateMix',
                sortable: false
            }
            //收益率上限公式
            , {
                field: 'floatMaxRate',
                width: 260,
                title: 'ProductSimulationTrial002.floatMaxRate',
                sortable: false
            }
            //收益率下限公式
            , {
                field: 'floatMinRate',
                width: 260,
                title: 'ProductSimulationTrial002.floatMinRate',
                sortable: false
            }
            //下限基准
            , {field: 'benchmarkMin',visible: false}
            //下限基准来源
            , {field: 'benchmarkMinSource', visible: false}
            //上限基准
            , {field: 'benchmarkMax', visible: false}
            //上限基准来源
            , {field: 'benchmarkMaxSource', visible: false}
            , {field: 'coefficientMin', visible: false}
            , {field: 'spreadRateMin', visible: false}
            , {field: 'coefficientMax', visible: false}
            , {field: 'spreadRateMax', visible: false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件

        ],

    });

    /**
     * 绑定删除事件
     */
    pledgeBondTotalTbl.bind('click', '.delt', function(data, index) {
        pledgeBondTotalTbl.removeRow(data);
    });

    /**
     * 转换表格数据
     */
    var convertRow = function(rowData) {
        _.each(JSON.parse(ASSET_CLASS), function(v) {
            if(v.id === rowData.assetType) {
                rowData.assetType = v.text;
            }
        });
        _.each(JSON.parse(ASSET_RISK_LEVEL), function(v) {
            if(v.id === rowData.assetRiskLevel) {
                rowData.assetRiskLevel = v.text;
            }
        });
        _.each(JSON.parse(INDEX_ID_SOURCE), function(v) {
            if(v.object === rowData.benchmarkMinSource) {
                rowData.benchmarkMinSource = v.col;
            }
            if(v.object === rowData.benchmarkMaxSource) {
                rowData.benchmarkMaxSource = v.col;
            }
        });
    }


   /* var deleteProcess = function () {
        var selArr;
        if (pledgeBondTotalTbl != null && pledgeBondTotalTbl != '') {
            selArr = pledgeBondTotalTbl.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        // 倒序删除，正序会存在问题
        for (var index = selArr.length - 1; index >= 0; index--) {
            pledgeBondTotalTbl.removeRow(selArr[index]);
        }
    };*/
    /**
     * 画面加载完成
     */
    $(function () {
        // 加载模拟信息列表
        
        pledgeBondTotalTbl.table? pledgeBondTotalTbl.reload():pledgeBondTotalTbl.load();

        /**
         * 删除操作
         */
      /*  $('#productSimulationTrial_btn_delete').click(deleteProcess);*/

        /**
         * 添加操作
         */
        $('#productSimulationTrial_btn_add').click(function() {
            var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("ProductSimulationTrial004.add"),
                "/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial004/init",
                "98%", "95%", null, function(data) {
                    if(!data) return;
                    var floatMinRate;
                    var floatMaxRate;
                    if(data.rateType == "02"){
                        floatMinRate =  data.benchmarkMin+"*"+data.coefficientMin+"+"+data.spreadRateMin;
                        floatMaxRate = data.benchmarkMax+"*"+data.coefficientMax+"+"+data.spreadRateMax;
                    }

                    var row = {
                        assetType: data.assetType,
                        assetRiskLevel: data.assetRiskLevel,
                        mockAmt: data.mockAmt,
                        mockShareAmt: data.mockShareAmt,
                        rateMax: data.rateMax,
                        rateMix: data.rateMix,
                        floatMinRate:floatMinRate,
                        floatMaxRate:floatMaxRate,
                        benchmarkMin:data.benchmarkMin,
                        benchmarkMinSource:data.benchmarkMinSource,
                        benchmarkMax:data.benchmarkMax,
                        benchmarkMaxSource:data.benchmarkMaxSource,
                        coefficientMin:data.coefficientMin,
                        spreadRateMin:data.spreadRateMin,
                        coefficientMax:data.coefficientMax,
                        spreadRateMax:data.spreadRateMax
                    };
                    convertRow(row);
                    pledgeBondTotalTbl.addRow(row);
                    window.parent.doSimulationTrial();
                });
        });

    });

}());