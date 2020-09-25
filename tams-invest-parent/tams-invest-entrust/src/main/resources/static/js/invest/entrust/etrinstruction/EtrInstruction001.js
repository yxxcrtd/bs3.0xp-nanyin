/**
 * 平安基金类标 的资产
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        //获取查询条件金融产品名称
        var  assetTypeOne;
        var assetTypeOneTemp =$('#assetType1').val();
        if(assetTypeOneTemp)
            assetTypeOne=assetTypeOneTemp.join(",");

        var  assetTypeTwo;
        var assetTypeTwoTemp =$('#assetType10').val();
        if(assetTypeTwoTemp)
            assetTypeTwo=assetTypeTwoTemp.join(",");

        return {
            finprodMarketId: $('#finprodMarketId').val(),
            repFinancier: $('#repFinancier').val(),
            entrustStatus: $('#entrustStatus').val(),
            ccy: $('#ccy').val(),
            assetType1:  assetTypeOne,
            assetType10:  assetTypeTwo
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/entrust/etrinstruction/EtrInstruction002/init",
                "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,entrustId:data.entrustId});
        },

        cols:[
            {field:'entrustId', visible:false},
            {field:'entrustStatusStr',width:150 , title: 'EtrInstruction001.statusValid'},
            {field:'invManagerId',width:150 ,  title: 'EtrInstruction001.repManger'},
            {field:'assetType1Str',width:150 ,  title: 'EtrInstruction001.assetTypeOne'},
            {field:'assetType10Str',width:150 ,  title: 'EtrInstruction001.assetTypeTwo'},
            {field:'finprodMarketId',width:150 ,  title: 'EtrInstruction001.finprodMarketId'},
            {field:'contractResult',width:150 ,  title: 'EtrInstruction001.contractResult'},
            {field:'contractNo',width:150 ,  title: 'EtrInstruction001.contractNo'},
            {field:'repFinancier',width:150 ,  title: 'EtrInstruction001.repFinancier'},
            {field:'matureYield',width:150 ,  title: 'EtrInstruction001.prdRate'},
            {field:'entrustAmt',width:150 ,  title: 'EtrInstruction001.entrustAmt'},
            {field:'ccyStr',width:150 ,  title: 'EtrInstruction001.ccy'},
            {field:'vdateStr', title: 'EtrInstruction001.vDate', type:'date', width:'10%',sortField:"vdate"},
            {field:'mdateStr', title: 'EtrInstruction001.mDate', type:'date', width:'10%',sortField:"mdate"},
            {field:'operation', width:100,title: 'SysCommon.operation', type:'action', buttons: ['edit']
            }
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        if(data.entrustStatusStr =="已退回" || data.entrustStatusStr =="已撤回") {
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction001/check', data, function (result) {
                Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction001/editCheck', data, function (editResult) {
                    Joyin.dialog(JoyinUtil.getLabel("EtrInstruction002.edit"),
                        basePath + "invest/entrust/etrinstruction/EtrInstruction002/init",
                        "90%", "90%", data, function () {
                            table.reload();
                        });
                });
            });
        }else{
            Joyin.error('只能对已退回或已撤销的指令进行编辑！');
            return;
        }
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
        // 绑定按钮事件
        $('#EtrInstruction001_btn_search').click(function() {
            table.load();
            $('#EtrInstruction001_btn_delete').show();
        });
        $('#EtrInstruction001_btn_export').click(function() {
            exportExcel();
        });
        $('#EtrInstruction001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("EtrInstruction002"),
                basePath + "invest/entrust/etrinstruction/EtrInstruction002/init",
                "90%", "90%",{pageModel:PAGE_MODEL_NEW});
        });

        /**
         * 关闭委托
         */
        $('#EtrInstruction001_btn_close').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            for(var i=0; i<selArr.length; i++){
                if(selArr[i].statusValid == '待接收'){
                    Joyin.error('不能对待接收的数据进行关闭操作');
                    return;
                }
                if(selArr[i].statusValid == '已关闭'){
                    Joyin.error('已关闭状态的数据不允许再次关闭');
                    return;
                }
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"),"关闭");
                return;
            }
            //	画面提示message【ETR_EI_008：请确认是否要关闭委托？】
            var object = {selectedItem: selArr, operateType:"close"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("ETR_EI_008"), function(){
                    Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction001/close', object, function(){

                        table.reload();
                    });
                });
            });
        })
        /**
         * 撤销委托
         */
        $('#EtrInstruction001_btn_revoke').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }

            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }//BUSS_COM_C_0032
            var object = {selectedItem: selArr, operateType:"recall"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                    Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction001/recall', object, function(result){

                        table.reload();
                    });
                });
            });
        })
    });
}());