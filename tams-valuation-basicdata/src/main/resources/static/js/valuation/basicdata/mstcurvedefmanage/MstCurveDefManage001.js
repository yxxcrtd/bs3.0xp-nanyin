$(function () {

    /**
     * 获取表单参数
     */

    var getSearchParams = function () {
        return {
            curveCode: $('#curveCode').val(),
            intCurveType: $('#intCurveType').val(),
            curveName:$('#curveName').val(),
            valueSource:$('#valueSource').val()
        };
    };

    var table = $("#dataTable").joyintable({

        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },


        /*
         * 定义表头
         */
        sortfont: true,//高亮排序
        cols: [
            //曲线代码
            {field: 'curveCode', width: 120, title: 'MstCurveDefManage001.curveCode'}
            //曲线名称
            , {field: 'curveName', width: 200, title: 'MstCurveDefManage001.curveName'}
            //曲线类型
            , {field: 'intCurveType', width: 100, title: 'MstCurveDefManage001.intCurveType', visible: false}
            , {field: 'intCurveTypeName', width: 100, title: 'MstCurveDefManage001.intCurveType'}
            //曲线来源 字典：VALUATION_SOURCE
            ,{field: 'valueSource', width: 120, title: 'MstCurveDefManage001.valueSource', visible: false}
            , {field: 'valueSourceName', width: 120, title: 'MstCurveDefManage001.valueSource'}

            , {field: 'curveGroupCode', width: 100, title: '曲线族'}
            , {field: 'remark', width: 100, title: '描述'}
            //币种
            , {field: 'ccy', width: 100, title: 'MstCurveDefManage001.ccy'}
            //插值方式
            , {field: 'interMethod', width: 100, title: 'MstCurveDefManage001.interMethod'}
            //计息基础
            , {field: 'dayRules', width: 100, title: '计息基础'}
            // 日历
            , {field: 'calender', width: 120, title: 'MstCurveDefManage001.calender'}
            // 付息日规则
            , {field: 'interestDateRule', width: 120, title: 'MstCurveDefManage001.interestDateRule'}
            // 计息方式
            , {field: 'interestCalMethod', width: 100, title: 'MstCurveDefManage001.interestCalMethod'}
            // 付息频率
            , {field: 'interestFreq', width: 100,title: '付息频率'}
            ,{field:'operation', width:80,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            // 生效状态
            // , {field: 'effStatus', width: 100, title: 'MstCurveDefManage001.effStatus', visible: false}
            <!-- add by yanya on 20210701 去除曲线管理界面生失效按钮 start-->
            //,{field:'effStatusName',width:100, title: 'MstCurveDefManage001.effStatus'}
            <!-- add by yanya on 20210701 去除曲线管理界面生失效按钮 end-->
        ]
    });

    var updateEffStatus = function (effStatus) {
        //获得表中选中的对象
        var object = table.getAllCheckedRows();
        if (object.length == 0) {
            Joyin.alert("您还未选中数据");
            return;
        }


        var commitObj = {
            selectedItem: object
        }
        Joyin.confirm("确定要更新收益率曲线状态？", function () {
            //遍历对象
            var msg = "";
            for (var i = 0; i < object.length; i++) {
                if (object[i].effStatus == effStatus) {
                    if (msg == "") {
                        msg += (i + 1);
                    } else {
                        msg = msg + ',' + (i + 1);
                    }
                    continue;
                }
                object[i].effStatus = effStatus;
            }
            if (msg != "") {
                Joyin.alert("第" + msg + '行数据状态不正确，不能更新');
                return;
            } else {
                Joyin.ajaxJson(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage001/updateEffStatus', commitObj, function (result) {
                    if (result) {
                        Joyin.alert("更新成功");
                        table.reload();
                    }
                })
            }
        })


    }
    /**
     * 以excel形式导出数据
     */
    /*var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };*/

    /**
     * 导出查询列表
     */
    var exportExcel = function () {
        var paramObj = getSearchParams();
        var expArr = table.getAllCheckedRows();

        if (expArr && expArr.length > 0) {
            paramObj.selectedRows = JSON.stringify(expArr);
        }

        Joyin.exportExcel('export', paramObj, function () {
        });
    };


    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog('新建收益率曲线', basePath +'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/init', '85%', '75%',
            {pageModel:PAGE_MODEL_NEW},function(){
                table.reload();
            });
    }

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",curveCode: data.curveCode};
        Joyin.ajaxJson(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage001/deleteCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/save', object, "MstCurveDefManage001_btn_delete", "MstCurveDefManage002",function(){
                    table.reload();
                });
            });
        });
    });

    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/check', data, function (result) {
            Joyin.dialog('收益率曲线编辑',
                basePath + "valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002/init",
                "85%", "75%", {
                    pageModel: PAGE_MODEL_EDIT,
                    curveCode: data.curveCode
                }, function () {
                    table.reload();
                });
        });
    });


    $(function () {

        // 绑定按钮事件
        $('#MstCurveDefManage001_btn_search').click(function () {
            table.load();
        });

        // 绑定重置按钮事件
        $('#MstCurveDefManage001_btn_reset').click(function(){
            window.location.reload();
        });

        //绑定新增按钮事件
        $('#MstCurveDefManage001_btn_add').click(function() {
            showDialog();
        });

        $('#MstCurveDefManage001_btn_export').click(function () {
            exportExcel();
        })

        $('#MstCurveDefManage001_btn_eff').click(function () {
            updateEffStatus('E');
        })
        $('#MstCurveDefManage001_btn_inEff').click(function () {
            updateEffStatus('D');
        })


    });
}());

