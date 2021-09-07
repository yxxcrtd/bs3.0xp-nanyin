$(function () {

    /**
     * 获取表单参数
     */

    var getSearchParams = function () {
        return {
            curveCode: $('#curveCode').val(),
            intCurveType: $('#intCurveType').val()
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
            //币种
            , {field: 'ccy', width: 100, title: 'MstCurveDefManage001.ccy'}
            //插值方式
            , {field: 'interMethod', width: 100, title: 'MstCurveDefManage001.interMethod'}
            //日算规则
            , {field: 'dayRules', width: 100, title: 'MstCurveDefManage001.dayRules'}
            // 日历
            , {field: 'calender', width: 120, title: 'MstCurveDefManage001.calender'}
            // 付息日规则
            , {field: 'interestDateRule', width: 100, title: 'MstCurveDefManage001.interestDateRule'}
            // 计息方式
            , {field: 'interestCalMethod', width: 100, title: 'MstCurveDefManage001.interestCalMethod'}
            // 付息频率
            , {field: 'interestFreq', title: 'MstCurveDefManage001.interestFreq'}
            // 生效状态
            // , {field: 'effStatus', width: 100, title: 'MstCurveDefManage001.effStatus', visible: false}
            <!-- add by yanya on 20210701 去除曲线管理界面生失效按钮 start-->
            //,{field:'effStatusName',width:100, title: 'MstCurveDefManage001.effStatus'}
            <!-- add by yanya on 20210701 去除曲线管理界面生失效按钮 end-->
        ]
    });

    var updateEffStatus = function (effStatus) {
        //获得表中选中的对象
        let object = table.getAllCheckedRows();
        if (object.length == 0) {
            Joyin.alert("您还未选中数据");
            return;
        }


        let commitObj = {
            selectedItem: object
        }
        Joyin.confirm("确定要更新收益率曲线状态？", function () {
            //遍历对象
            let msg = "";
            for (let i = 0; i < object.length; i++) {
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
            debugger;
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


    $(function () {

        // 绑定按钮事件
        $('#MstCurveDefManage001_btn_search').click(function () {
            table.load();
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

