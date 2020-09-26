$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId: $.trim($('#finprodId').val()),
            taxPType: $.trim($('#taxPType').val()),
            vatType: $.trim($('#vatType').val()),
            startDate: $('#Date').startDate(),//确认日开始            
            endDate: $('#Date').endDate()//确认日结束
            ,assetId: $.trim($('#assetId').val())
            ,prdCheckBox: $('#prdCheckBox').next().hasClass('layui-form-onswitch') ? 'Y' : 'N'
        }
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
        checkbox: true,
        cellMinWidth : 100,
        cols: [
            {field:'finprodId', title: '产品代码',width:240, sort: true},
            {field:'finprodName', title: '产品名称', sort: true},
            {field:'assetId', title: '资产代码', width:150,sort: true},
            {field:'assetIdName', title: '资产名称', width:180,sort: true},
            {field:'happenDate',width:150, title: '日期', sort: true},
            {field:'taxPTypeFt',width:150, title: '业务类型', sort: true,sortField:'taxPType'},
            {field:'vatTypeFt',width:150, title: '收入类型', sort: true,sortField:'vatType'},
            {field:'taxAmt',type:"money",width:200, title: '预估税费余额', sort: true,
                render : function(data, type, row, meta) {
                    if(row.taxPTypeCode=='01'){
//                       console.log(row.dailydscYield);
//                       console.log(Number(row.dailydscYield).toFixed(12));
                        return  formatMoney(row.taxAmt);

                    }else{
                        return formatMoney(0.00);
                    }
                }},

            {field:'taxAmt2',type:"money",width:200, title: '发生税费余额', sort: true,
                render : function(data, type, row, meta) {
                    if(row.taxPTypeCode=='02'){
//                           console.log(row.dailydscYield);
//                           console.log(Number(row.dailydscYield).toFixed(12));
                        return  formatMoney(row.taxAmt2);

                    }else{
                        return formatMoney(0.00);
                    }
                },
            }
        ],
    });
    var formatMoney = function (s, n)
    {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var t = '';
        for (var i = 0; i < l.length; i++)
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }

    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
//		var paramObj = getSearchParams();
//		Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TaxBalanceQuery001_btn_search').click(function() {
            table.load();
            if($("#prdCheckBox").next().hasClass('layui-form-onswitch')){
                table.showColumns([
                    {field:'assetId', visible: false},
                    {field:'assetIdName', visible: false}
                ]);
            }else{
                table.showColumns([
                    {field:'assetId', visible: true},
                    {field:'assetIdName', visible: true}
                ]);
            }
        });

        //  绑定导出事件
        $('#TaxBalanceQuery001_btn_export').click(function() {
            exportExcel();
        });

    });

});



