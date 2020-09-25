(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#FinCreditProject001_DIV");
        return {
            finprodId:objDiv.find("input[name='finprodId']").val(),//资产代码
            cashOutNo: objDiv.find("input[name='cashOutNo']").val(),//资产类型
            vdateStart : objDiv.find("input[name='vdate']").startDate(),//起息日
            vdateEnd : objDiv.find("input[name='vdate']").endDate(),//起息日
            mdateStart : objDiv.find("input[name='mdate']").startDate(),//起息日
            mdateEnd : objDiv.find("input[name='mdate']").endDate()//起息日
        };
    };
    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
            ajax: {
                url: 'findPage',//请求路径
                type: 'POST',//请求类型
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },
            checkbox: false,
        /**
         * 定义表头
         */
        cols:[
              {field:'finprodId',  title: 'FinCreditProject001.finprodId',visible:false} 
            ,{field:'cashOutNo',  title: 'FinCreditProject001.cashOutNo',width:100} 
            ,{field:'finprodMarketId',  title: 'FinCreditProject001.finprodId',width:100} 
            ,{field:'relContractNo',  title: 'FinCreditProject001.relContractNo',width:100}
            ,{field:'becif',  title: 'FinCreditProject001.becif',width:100}
            ,{field:'creditCustomerNo',  title: 'FinCreditProject001.creditCustomerNo',width:100}
            ,{field:'creditCustomerName',title:'FinCreditProject001.creditCustomerName',width:100}
            ,{field:'creditBusinessNo',  title: 'FinCreditProject001.creditBusinessNo',width:100}
            ,{field:'creditBusinessName',  title: 'FinCreditProject001.creditBusinessName',width:100}
            ,{field:'aproveAmount',title:'FinCreditProject001.aproveAmount',type:'number',digit:'4',width:100}
            ,{field:'paymentAmount',title:'FinCreditProject001.paymentAmount',type:'number',digit:'4',width:100}
            ,{field:'ccyName',title:'FinCreditProject001.ccy',width:100}
            ,{field:'vdate',title:'FinCreditProject001.vdate',type:'date',width:100}
            ,{field:'mdate',title:'FinCreditProject001.mdate',type:'date',width:100}
            ,{field:'yield',title:'FinCreditProject001.yield',type:'number',digit:'4',width:100}
            ,{field:'lastAproveIns',  title: 'FinCreditProject001.lastAproveIns',width:100}
            ,{field:'reason',  title: 'FinCreditProject001.reason',width:100}
            ,{field:'isRevoke',  title: 'FinCreditProject001.isRevoke',width:100}
            ,{field:'operation', title: 'SysCommon.operation', type:'action',
                render:function (data, type, row, meta) {
                    if((row.finprodId!=''&&typeof row.finprodId != 'undefined')
                            ||row.isRevoke=='Y'){
                        return '';
                        /*return '<i class="btn iconfont icon-revoke revoke-icon revoke" disabled title="撤销"></i>';*/
                    }else{
                        return '<i class="btn iconfont icon-revoke layui-icon revoke" title="撤销"></i>';
                    }
                }
            }
        ]
    });
    
    /**
     * 绑定撤销事件
     */
    table.bind('click', '.revoke', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("FinCreditProject001.revokeTitle"),
                    basePath + "invest/asset/credit/FinCreditProject002/init",//打开新界面
                    "50%", "50%", {
                                    cashOutNo:data.cashOutNo  //出账标号
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    });     
    });
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };
    
    
    /**
     * 画面加载完成
     */
    $(function() {
        //导出
        $('#FinCreditProject001_btn_export').click(exportExcel);
        //初始化查询
        $('#FinCreditProject001_btn_search').click(function() {
            table.load();
        });
    });
}());