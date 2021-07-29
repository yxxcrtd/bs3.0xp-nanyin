/**
 * 快速转仓指令产品查询
 */
(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            portfolioId: $('#portfolioId').val()?$('#portfolioId').val().join(","):null,
            mDateBegin: $("#mDate").startDate(),
            mDateEnd: $("#mDate").endDate()
        }
    };

    /**
     * 资产配置
     */
    function assetConfigAction() {
        var selArr= null!=table?table.getAllCheckedRows():null;
        //判断是否选中元素
        if( null!=selArr &&selArr.length > 0 ) {
            if (selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var data={
            portfolioId:selArr[0].portfolioId  //组合代码
        }
        Joyin.dialog(JoyinUtil.getLabel('EtrAssetConfigTransfer001'), basePath + 'invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer002/init', '98%', '95%',data,function () {
            table.reload();
        });
    }

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        dblclick: function (table, data) {
            data.operateType = "dblclick";
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrStock002.refence"),
                JoyinUtil.getRealPath("invest/entrust/etrstock/EtrStock002/init"),
                "98%", "95%", data,
                function () {
                    table.reload();
                }
            );
        },
        cols: [
            {field: 'portfolioId', title: 'EtrAssetConfigTransfer001.portfolioId', width: 280,
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>" + row.portfolioId + "</ a>";
                }, sortField: "portfolio_Id"
            },
            {field: 'portfolioName', title: 'EtrAssetConfigTransfer001.portfolioName', width: 390, sortField: "portfolio_Name"},
            {field: 'vdate', title: 'EtrAssetConfigTransfer001.vDate', width: 200, type: 'date', sortField: "vdate"},
            {field: 'mdate', title: 'EtrAssetConfigTransfer001.mDate', width: 200, type: 'date', sortField: "mdate"},
            {field: 'remark', title: 'EtrAssetConfigTransfer001.lastPeriod',width:200, sortable: false,
                render: function (data, type, row, meta) {
                    return DateUtil.daysBetween(Date.ofDate(row.mdate).format('yyyy-MM-dd'),Date.ofDate(platDate).format('yyyy-MM-dd'));
                }
            }
        ]
    });
    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel(basePath + "invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer001/export", getSearchParams());
    };

    /**
     * 组合代码链接
     */
    table.bind('click', '.portfolioIdLink', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init","98%", "90%", {
            pageModel: PAGE_MODEL_REFERENCE,
            portfolioId: row.portfolioId
        });
    });

    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrAssetConfigTransfer001_btn_search').click(function () {
            table.load();
        });
        /**
         * 导出按钮
         */
        $('#EtrAssetConfigTransfer001_btn_export').click(function () {
            exportExcel();
        });
        /**
         * 资产配置
         */
        $('#EtrAssetConfigTransfer001_btn_config').click(function () {
            assetConfigAction();
        });
    });
}());