/**
 * ChannelAsset001 通道资产查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        param.startDate = $('#SelectDate').find("input[name='selectDate']").startDate();
        param.endDate = $('#SelectDate').find("input[name='selectDate']").endDate();
        if (param.secAcctId) {
            param.secAcctId = param.secAcctId.join(',');
        }
        if (param.secAcctName) {
            param.secAcctName = param.secAcctName.join(',');
        }
        return param;
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
        cols:[
            {field:'portfolioId',width:150,title: 'ChannelAsset001.portfolioId'}
            ,{field:'portfolioName',width:150,title: 'ChannelAsset001.portfolioName'}
            ,{field:'finprodMarketId',width:150,title: function(){return  JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset001.secAcctId");}()}
            ,{field:'secAcctName',width:150,title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset001.secAcctName");}()}
            ,{field:'type8',width:150,title: 'ChannelAsset001.assetType1',visible:false}
            ,{field:'assetTypeStr',width:150,title: 'ChannelAsset001.type8', sortField: 'type8'}
            ,{field:'type6',width:150,title: 'ChannelAsset001.assetType2',visible:false}
            ,{field:'assetSecTypeStr',width:150,title: 'ChannelAsset001.type6', sortField: 'type6'}
            ,{field:'holdingTypeString',width:150,title: '持仓类型', sortField: 'holdingType'}
            ,{field:'cdate',width:150,title: '日期'}
            ,{field:'finprodId',width:150,title: 'ChannelAsset001.finprodId'}
            ,{field:'finprodAbbr',width:150,title: 'ChannelAsset001.finprodName'}
            ,{field:'amount',width:150,title: 'ChannelAsset001.amount', type: 'number'}
        ]
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        $('#ChannelAssetQuery_btn_search').click(function() {
            table.load();
        });
        // 绑定按钮事件
        $('#ChannelAssetQuery_btn_export').click(function() {
            exportExcel();
        });
    });
}());