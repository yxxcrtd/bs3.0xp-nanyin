$(function() {

    var workflowIdNow = $("#workflowId").val();
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId : $.trim($('#finprodId').val()),
            workflowIdNow : $("#workflowId").val()
        }
    };
    if (workflowIdNow == null || workflowIdNow.length == 0) {

    } else {

    }

    /**
     * 定义数据表格
     */
    var table = $("#dataTable")
        .joyintable(
            {
                ajax : {
                    url : basePath
                        + 'valuation/assetsamortize/ValAssetsAmortizeSet002/findPage',
                    type : 'POST',
                    data : function() {
                        return getSearchParams();
                    }
                },
                searchOnInit : true,
                done : function(table, allData) {
                },

                checkbox : true,
                page : false,
                cols : [
                    {
                        field : 'busiId',
                        title : '资产代码',
                        sortable : false
                    },
                    {
                        field : 'finprodName',
                        title : '资产名称',
                        sortable : false
                    },
                    {
                        field : 'booksetId',
                        title : '账套代码',
                        sortable : false,
                        visible : false
                    },
                    {
                        field : 'booksetName',
                        title : '账套名称',
                        sortable : false
                    },
                    {
                        field : 'cdate',
                        title : '会计日期',
                        sortable : false
                    },
                    {
                        field : 'recalType',
                        title : '重算方式',
                        sortable : false,
                        edit : true,
                        editOption : {
                            type : 'select',
                            multiple : false,
                            result : RECAL_TYPE,
                            showField: 'text',
                            initValue : '02'
                        }
                    }, {
                        field : 'dailydscYield',
                        title : '实际日利率',
                        sortable : false,
                        edit : true,
                        width:300,
                        type:"money",pattern: '#.000000000000',
                        digit:12,
                        editOption : {
                            type:"money",pattern: '#.000000000000',
                        },

                        render : function(data, type, row, meta) { 
						    if (row.recalTypeVal == '03') {
							   //       return Number(row.dailydscYield).toFixed(12);
							   return row.dailydscYield;
							
							} else {
							   return null;
							} 
                        }
                    } ],
                editCell : function(value, data, field, cell, column) {
                },

                loadonce : true
            });

    $('#finprodId').change(function() {
        if ($.trim($('#finprodId').val()) != '') {
            table.load();
        }

    });

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValAssetsAmortizeSet002_btn_submit')
            .click(
                function() {
                    var object = {};
                    if (table != null && table != '') {
                        var selArr = table.getAllCheckedRows();
                        var selAll = table.getAllRows();
                        var datas = JSON.stringify(selArr);
                    }

                    if (!selAll || selAll.length == 0) {
                        Joyin.error(JoyinUtil.getMessage("表格无数据！"));
                        return;
                    }

                    if (!selArr || selArr.length == 0) {
                        Joyin.error(JoyinUtil.getMessage("请至少选择一条记录！"));
                        return;
                    } else {
                        var countRecal = 0;
                        var countYield = 0;
                        var countYield1 = 0;
                        // 判断每一条数据
                        for (var a = 0; a < selArr.length; a++) {
                            var obj = {};
                            obj = selArr[a];

                            //recalTypeVal始终为默认值，保存提交的时候改为修改后的值。
                            obj.recalTypeVal = obj.recalType;

                            // 重算方式是否为空
                            if (typeof (obj.recalTypeVal) == undefined
                                || obj.recalTypeVal == "" || obj.recalTypeVal == null) {
                                countRecal++;
                            } else {
                                // 重算方式为指定利率 实际日利率不能为空
                                if (obj.recalTypeVal == '03') {
                                    if (typeof (obj.dailydscYield) == undefined
                                        || obj.dailydscYield == ""
                                        || obj.dailydscYield == null) {
                                        countYield++;
                                    } else if (obj.dailydscYield >= 10000000000000000
                                        || obj.dailydscYield < 0
                                        || isNaN(obj.dailydscYield)) {
                                        countYield1++;
                                    }
                                    console.log(obj.dailydscYield);
                                }else{
                                	 if (!(typeof (obj.dailydscYield) == undefined
                                         || obj.dailydscYield == ""
                                         || obj.dailydscYield == null)) {
                                		 countYield1++;
                                     }
                                }

                            }

                        }
                        if (countRecal > 0) {
                            Joyin.error(JoyinUtil.getMessage("重算方式不能为空！"));
                            return;
                        }
                        if (countYield > 0) {
                            Joyin.error(JoyinUtil.getMessage("重算方式为指定利率时实际日利率不能为空！"));
                            return;
                        }
                        if (countYield1 > 0) {
                            Joyin.error(JoyinUtil.getMessage("重算方式不为指定利率时,实际日利率应该为空！"));
                            return;
                        }
                        //九江银行uat测试说实际情况可以为负数 yjm start 20190923
                        /*if (countYield1 > 0) {
                            Joyin.error(JoyinUtil.getMessage("重算方式为指定利率时实际日利率格式错误！"));
                            return;
                        }*/
                        //九江银行uat测试说实际情况可以为负数 yjm end 20190923
                        object.listValAssetsAmortizeSet001Dto = selArr;
                        object.operate = "new";
                        Joyin
                            .ajaxJson(
                                basePath
                                + 'valuation/assetsamortize/ValAssetsAmortizeSet002/doCommitCheck',
                                object,
                                function(result) {

                                    WorkFlow
                                        .doSubmitList(
                                            basePath
                                            + 'valuation/assetsamortize/ValAssetsAmortizeSet002/doCommit',
                                            object,
                                            "ValAssetsAmortizeSet002_btn_submit",
                                            "ValAssetsAmortizeSet002",
                                            function() {
                                                if (result.errorList.length == 0) {
                                                    var index = parent.layer
                                                        .getFrameIndex(window.name);
                                                    parent.layer
                                                        .close(index);
                                                }
                                            });

                                });

                    }

                });

    });

});
