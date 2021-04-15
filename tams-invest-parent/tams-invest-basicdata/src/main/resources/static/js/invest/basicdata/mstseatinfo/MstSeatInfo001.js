/**
 * 营业部信息管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	seatId : $('#seatId').val()
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
        /*
         * 双击查看数据
         */
        dblclick:function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    JoyinUtil.getRealPath("invest/basicdata/mstseatinfo/MstSeatInfo002/init"),
                    "75%", "75%", data,
                    function(){table.reload();});
        },
       
        cols:[
             {field:'customerId',width:'15%', sortField:'customer_id', title: 'MstSeatInfo002.customerId'}
            ,{field:'customerName',width:'20%', sortField:'customer_name',  title: 'MstSeatInfo002.customerName'}
            ,{field:'tradeMarketName',width:'15%', sortField:'trade_market',  title: 'MstSeatInfo002.tradeMarket'}
            ,{field:'seatId',width:'15%', sortField:'seat_id',  title: 'MstSeatInfo001.seatId'}
            ,{field:'createUser',width:'10%', sortable:false,  title: 'MstSeatInfo001.createUser'}
            ,{field:'updateUser',width:'10%', sortable:false,  title: 'MstSeatInfo001.updateUser'}
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 导出
     */
    var exportExcel = function(){
        var paramObj=getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = data;
        object.operateType = "edit";
                Joyin.dialog(JoyinUtil.getLabel("MstSeatInfo001.edit"),
                    basePath + "invest/basicdata/mstseatinfo/MstSeatInfo002/init",
                    "75%", "75%", {
                        pageModel: PAGE_MODEL_EDIT,
                        seatId: data.seatId
                    }, function () {
                        table.reload();
                    })
        });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = data;
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo002/save', object, "MstSeatInfo001_btn_delete", "MstSeatInfo002");
            });
        });
    });
   
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#MstSeatInfo001_btn_search').click(function() {
            table.load();
        });
        $('#MstSeatInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstSeatInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstSeatInfo002"),
                        basePath + "invest/basicdata/mstseatinfo/MstSeatInfo002/init","75%", "75%", {pageModel:PAGE_MODEL_NEW}, 
                        function() {
        					table.reload();
        				});
        });
        $('#MstSeatInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstseatinfo/MstSeatInfo001/delete', object, "MstSeatInfo001_btn_delete", "MstSeatInfo002");
                });
            });
        });
    });
}());