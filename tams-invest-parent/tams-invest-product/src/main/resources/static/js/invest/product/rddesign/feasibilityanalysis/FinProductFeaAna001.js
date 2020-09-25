(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
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
            {field:'effectStatus', title: 'FinProductFeaAna001.table.effectStatus'}
            ,{field:'requirementId', title: 'FinProductFeaAna001.table.requirementId'}
            ,{field:'feasibilityStatusStr', title: 'FinProductFeaAna001.table.feasibilityStatus',sortField:'feasibilityStatus'}
            ,{field:'feasibilityTypeStr', title: 'FinProductFeaAna001.table.feasibilityType',sortField:'feasibilityType'}
            ,{field:'feasibilityResult', title: 'FinProductFeaAna001.table.feasibilityResult'}
            ,{field:'tarCustomer', title: 'FinProductFeaAna001.table.tarCustomer'}
            ,{field:'prdScale', title: 'FinProductFeaAna001.table.prdScale'}
            ,{field:'prdPeriod', title: 'FinProductFeaAna001.table.prdPeriod'}
            ,{field:'prdRate', title: 'FinProductFeaAna001.table.prdRate'}
            ,{field:'prdRequirementDetial', title: 'FinProductFeaAna001.table.prdRequirementDetial'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    });

    /**
     * check查询条件和加载table
     */
    var checkAndLoadTable = function () {
        table.load();
    };

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {requirementId:data.requirementId};
        data.operateType = 'delete';
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            Joyin.ajaxJson(basePath + 'invest/product/rddesign/feasibilityanalysis/FinProductFeaAna002/delete', data, function(result) {
                table.reload();
            });
        });
    });

    var analyse = function(){
        if(table != null && table != ''){
            var selArr = table.getAllCheckedRows();
            if(selArr != null && selArr.length > 0){
                //check选择长度
                if(selArr.length != 1){
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                var data = selArr[0];
                if((data.effectStatus != null && data.effectStatus == effective) ||
                    (data.feasibilityStatusStr != null && data.feasibilityStatusStr == analyse_status)){
                    Joyin.dialog(JoyinUtil.getLabel("FinProductFeaAna001.analysis"),
                        JoyinUtil.getRealPath("invest/product/rddesign/feasibilityanalysis/FinProductFeaAna002/init"),
                        "98%", "95%",{pageModel : PAGE_MODEL_EDIT, confirmFlag : 'confirm', requirementId:data.requirementId},
                        function(){table.reload();}
                    );
                }else{
                    Joyin.error("请选择待处理或待定的数据");
                }
            }else{
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }

    };

    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };

    $(function () {
       $("#FinProductFeaAna001_btn_search").click(function () {
           checkAndLoadTable();
       });
       $("#FinProductFeaAna001_btn_analysis").click(function () {
           analyse();
       });
       $('#FinProductFeaAna001_btn_export').click(function () {
           exportExcel();
       });
    });
}());
