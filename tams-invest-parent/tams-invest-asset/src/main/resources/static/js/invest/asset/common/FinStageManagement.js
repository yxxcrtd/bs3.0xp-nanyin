$(function() {
    
    /**
     * 禁止重复reload table
     */
    var doneFlag =false;
    /**
     * 编辑单元格前数据
     */
    var prevdate = '';
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId:$('#finprodId').val(),
            workflowId:$("#workflowId").val()
            };
        };
    /**
     * 定义表头
     */
    var cols = [
                {field:'finprodId',  title: 'FinStageManagement.finprodId',visible:false},
                {field:'priCashId',visible:false},
                {field:'intCashId',visible:false},
                {field:'finprodMarketId',  title: 'FinStageManagement.finprodId',width:150,sortable: false, 
                    render(data,type,row,meta){return finprodMarketId?finprodMarketId:data;}
                }//资产代码
                ,{field:'finprodName',  title: 'FinStageManagement.finprodName',width:150,sortable: false,
                    render(data,type,row,meta){return finprodName?finprodName:data;}
                }//资产全称
                ,{field:'period',title:'FinStageManagement.period',edit:true,width:150}//期次
                ,{field:'vdate',title:'FinStageManagement.vdate',sortable: false,width:150,edit:true,type:'date'}//起息日
                ,{field:'mdate',title:'FinStageManagement.mdate',sortable: false,width:150,edit:true,type:'date'}//到期日
                ,{field:'issueAmt',title:'FinStageManagement.issueAmt',sortable: false,width:150,edit:true,type:'number',digit:'4'}//利率
                ,{field:'loadRate',title:'FinStageManagement.loadRate',sortable: false,width:150,edit:true,type:'number',digit:'4'}//利率
                ,{field:'investRate',title:'FinStageManagement.investRate',sortable: false,width:150,type:'number',digit:'4'}//利率
                ,{field:'buyoutRate',title:'FinStageManagement.buyoutRate',sortable: false,width:150,edit:true,type:'number',digit:'4'}//利率
                ,{field:'referRate',title:'FinStageManagement.referRate',sortable: false,width:150,type:'number',digit:'4'}//利率
                ,{field:'periodCount',visible:false,type:'number'}//利率
                , {field: 'operation', width: 150, title: 'SysCommon.operation', type: 'action', buttons: ['delt'],
                    render: function (data, type, row, meta) {
                        if (row.isPayed == 'Y')
                            return "";
                        else
                            return '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                    }}
            ];
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
            ajax: {
                url: JoyinUtil.getRealPath('/invest/asset/common/FinStageManagement/initStageManagementInfo'),
                type: 'POST',//请求类型
                dataType: 'json',
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },
        cols:cols,
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        page:false,
        addbtn:true,
        addclick: function(row) {
            /*row.finprodId = finprodId ;*/
            row.finprodName = finprodName;
            row.finprodMarketId = finprodMarketId;
            row.buyoutRate = buyoutRate;
        },
        editCell : function(value, data, field, cell, column){
            if(field=='loadRate'){
                var rowData = {};
                rowData.investRate = JoyinMath.doMinus(data.loadRate,chlRate,4);
                rowData.referRate =JoyinMath.doMinus(JoyinMath.doMinus(data.loadRate,chlRate,4), data.buyoutRate,4);
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            }
            if(field=='buyoutRate'){
                var rowData = {};
                rowData.referRate =JoyinMath.doMinus(data.investRate,data.buyoutRate,4);
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            }
            if(field=='vdate'){
                var rowData = {};
                var list = table.getAllRows();
                var max = 0;
                if(list){
                    $.each(list,function(index,obj){
                        if(data.vdate == new Date(obj.vdate).format("yyyy-MM-dd")){
                            if(max<=obj.periodCount){
                                max = obj.periodCount;
                            }
                        }
                    });
                }
                max = parseInt(max)+parseInt(1);
                //max+=1;


                var index = max<10?"0"+(max):max;
                rowData.periodCount = max;
                rowData.period = data.vdate.replace("-","").replace("-","")+index;
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            }
            //不能小于平台日
            if (field == 'mdate') {
                if (value < platDate){
                    Joyin.error('到期日小于平台日，无法修改到期日！', function () {
                        table.renderRow(data.LAY_TABLE_INDEX, {mdate: prevdate}); 
                    });                   
                }
            }
            if(field == 'period'){
                var reg = /^[0-9]+.?[0-9]*$/;
                if (!reg.test(value)) {
                    Joyin.error('期次请输入数字！', function () {
                        table.renderRow(data.LAY_TABLE_INDEX, {period: ""});
                    });
                }
            }
        },
        done:function(table, allData){
            //删除后  期次重排 
            //orderPeriod();
        },
        beforeEditCell:function(preVal, field, rowData){
            //校验有没子期次做过到期，如果有，则不允许修改到期日
            if(preVal != ""){
                if (field == 'mdate') {
                    prevdate = preVal;
                    if (preVal < platDate){
                        return Joyin.error('到期日小于平台日，无法修改到期日！');
                    }else if (preVal == platDate){
                        //查询是否已经做过到期交易
                        Joyin.ajaxJson(basePath + 'invest/asset/common/FinStageManagement/isCheckMature', rowData, function(result){
                            if (result.data){
                                Joyin.error('已做过到期交易，无法修改到期日！');
                                return false;
                            }
                        });
                    }
                }
            }
            if(rowData.updateFlg=='02'){
                return false;
            }
           return true;
        }
    });
    
    var orderPeriod = function(){
        if(doneFlag){
            return;
        }
        var list = table.getAllRows();
        if(list){
            $.each(list,function(index,obj){
                index = index+1<10?"0"+(index+1):index+1;
                obj.period = data.vdate.replace("-","").replace("-","")+index;
            });
        }
        doneFlag = true;
        table.reload({data:list, url:null});
    }
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	//期次子计划存在交易不能删除 yzg 20190909 start
    	Joyin.ajaxJson(basePath + 'invest/asset/common/FinStageManagement/isCheckTarde', data, function(result){
            if (result){
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_CHILD_D_1"));
                return;
            }else{
            	table.removeRow(data);
            }
        });
    	//期次子计划存在交易不能删除 yzg 20190909 end
    });
    
    function saveOrSubmit(operateType) {
        if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0057",JoyinUtil.getLabel("FinStageManagement.title")));
            return;
        }
        var object = {selectedItem: selArr,finprodId:finprodId,finprodMarketId:finprodMarketId,finprodName:finprodName,calendarId:calendarId, operateType:operateType,ccy: $("#ccy").val()};
                finalSubmit(object);
           /* Joyin.ajaxJson(basePath + 'invest/asset/common/FinStageManagement/isTransInAssetCheck', object, function(result) {
                if(result){
                    finalSubmit(object);
                }else{
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0002'),function(){
                        finalSubmit(object);
                    });
                }
            });*/
    };
    
    /**
     * 最终提交
     */
    var finalSubmit = function(object){
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinStageManagement/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/asset/common/FinStageManagement/save', object, "FinStageManagement_btn_submit");
            });
        });
    }
    
    $(function(){
        $('#FinStageManagement_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
});
