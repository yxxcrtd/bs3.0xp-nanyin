$(function() {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId:$('#finprodId').val(),
            workflowId:$("#workflowId").val(),
            finprodType2:$("#finprodType2").val()
            };
        };
    /**
     * 定义表头
     */
    var cols = [];
    //通道专用
    if($('#finprodType2').val() == FINPROD_TYPE_TG_F28){
        cols = [
                {field:'finprodId',  title: 'FinAssetRateAdj001.finprodId',visible:false,
                    render(data,type,row,meta){return finprodId?finprodId:data;} //金融产品代码
                }
                ,{field:'finprodType',visible:false} 
                ,{field:'finprodType2',visible:false,
                    render(value){return INT_ASSET_TYPE;}
                }
                ,{field:'vdate',visible:false} 
                ,{field:'issueAmt',visible:false} 
                ,{field:'cashId',visible:false} 
                ,{field:'calcType',visible:false} 
                ,{field:'baseType',visible:false} 
                ,{field:'baseDateType',visible:false} 
                ,{field:'basis',visible:false} 
                ,{field:'isInitial',visible:false} 
                ,{field:'investRate',visible:false} 
                ,{field:'buyOutRate',visible:false} 
                ,{field:'investRate',visible:false}
                ,{field:'collectType',visible:false}
                ,{field:'finprodMarketId',  title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodIdBak");}(),sortable: false,
                    render(data,type,row,meta){return finprodMarketId?finprodMarketId:data;}
                }//资产代码
                ,{field:'finprodName',  title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodNameBak");}(),sortable: false,
                    render(data,type,row,meta){return finprodName?finprodName:data;}
                }//资产全称
                ,{field:'feeId',  title: 'BussFinProductFeeInfo.feeName',sortable: false,
                    edit:true,
                    editOption:{type:'select', url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfo/queryFeeInfoList')
                        ,postData:function () {
                            return {finprodId:$('#finprodId').val(),finprodType:$('#finprodType2').val(),isRateAdj:'Y'};
                        }
                    }
                }
                /*,{field:'feeType', width:150, title: 'BussFinProductFeeInfo.feeName', sortable: false,
                    edit:true,
                    editOption:{type:'select', result: COLLECT_TYPE}
                }*/
                ,{field:'effDate',title:'FinAssetRateAdj001.effDate',sortable: false,edit:true,type:'date'}//生效日
                ,{field:'yield',title:'FinAssetRateAdj001.rate',sortable: false,edit:true,type:'number',digit:'4'}//利率
                ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//删除事件
            ];
    }else{
        cols = [
                {field:'finprodId',  title: 'FinAssetRateAdj001.finprodId',visible:false,
                    render(data,type,row,meta){return finprodId?finprodId:data;} //金融产品代码
                }
                ,{field:'finprodType',visible:false} 
                ,{field:'finprodType2',visible:false,
                    render(value){return INT_ASSET_TYPE;}
                }
                ,{field:'vdate',visible:false} 
                ,{field:'issueAmt',visible:false} 
                ,{field:'cashId',visible:false} 
                ,{field:'calcType',visible:false} 
                ,{field:'baseType',visible:false} 
                ,{field:'baseDateType',visible:false} 
                ,{field:'basis',visible:false} 
                ,{field:'isInitial',visible:false} 
                ,{field:'investRate',visible:false} 
                ,{field:'buyOutRate',visible:false} 
                ,{field:'investRate',visible:false}
                ,{field:'finprodMarketId',  title: 'FinAssetRateAdj001.finprodId',sortable: false, 
                    render(data,type,row,meta){return finprodMarketId?finprodMarketId:data;}
                }//资产代码
                ,{field:'finprodName',  title: 'FinAssetRateAdj001.finprodName',sortable: false,visible:false,
                    render(data,type,row,meta){return finprodName?finprodName:data;}
                }//资产全称
                ,{field:'finprodAbbr',  title: 'FinAssetRateAdj001.finprodAbbr',sortable: false,visible:false,
                    render(data,type,row,meta){return finprodAbbr?finprodAbbr:data;}
                }//资产简称
                ,{field:'effDate',title:'FinAssetRateAdj001.effDate',sortable: false,edit:true,type:'date'}//生效日
                ,{field:'yield',title:'FinAssetRateAdj001.rate',sortable: false,edit:true,type:'number',digit:'2'}//利率
                ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//删除事件
            ];
    }
  //根据资产类型处理不同的表头
    var handleCols = function(){
        if($('#finprodType2').val() == FINPROD_TYPE_TG_F28){
            
        }else{
            $.each(cols,function(id,val){
                if(val["field"] =="finprodName"){
                    if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F01){
                        val.visible = false;
                    }else{
                        val.visible = true;
                    }
                }
                if(val["field"] =="finprodAbbr"){
                    if(INT_ASSET_TYPE ==FINPROD_TYPE_TG_F01 ){
                        val.visible = true;
                    }else{
                        val.visible = false;
                    }
                }
                if(val["field"] =="yield"){
                    if(INT_ASSET_TYPE ==FINPROD_TYPE_TG_F01 ){
                        val.title = 'FinAssetRateAdj001.rate';
                    }else{
                        val.title = 'FinAssetRateAdj001.loadRate';
                    }
                }
                if(val["field"] =="operation"){
                    if($('#rateType').val()!=RATE_TYPE_01){
                        val.visible = false;
                    }else{
                        val.visible = true;
                    }
                }  
        });
        }
        return cols;
    }
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
            ajax: {
                url: JoyinUtil.getRealPath('/invest/asset/common/FinAssetRateAdj001/initAssetRateAdjInfo'),
                type: 'POST',//请求类型
                dataType: 'json',
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },
        cols:handleCols(),
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        page:false,
        addbtn:true,
        addclick: function(row) {
            var _row = table.getRow(0);
            row.cashId = _row ? _row.cashId : '';
            row.finprodId = _row ? _row.finprodId : '';
            row.finprodName = _row ? _row.finprodName : '';
            row.finprodMarketId = _row ? _row.finprodMarketId : '';
            row.finprodAbbr = _row ? _row.finprodAbbr : '';
            row.isInitial = isInitial;
        },
        beforeEditCell:function(preVal, field, rowData){
            if(rowData.updateFlg=='02'){
                return false;
            }
           return true;
        },
        editCell : function(value, data, field, cell, column){
            if (field === "feeId"){
                var rowData = {};
                rowData.cashId = ""; // 现金流id
                if (value != null && value != ''){
                    Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFeeInfo/queryMstFeeinfoForRateAdjByFeeId'), {feeId : data.feeId, finprodId : $('#finprodId').val(),isRateAdj:'Y'}, function(result){
                        if (result){
                            rowData.cashId = result.cashId;
                            rowData.baseType = result.basicBill;
                            rowData.basis = result.basis;
                            rowData.collectType = result.collectType;
                            rowData.isInitial = 'N';
                            table.renderRow(data.LAY_TABLE_INDEX, rowData);
                        }
                    });
                }
            }
            //add by yujiaping 2020-11-27 ZGBS-21债券标的，利率调整，利率不可以输入负数 start
            if(field == "yield"){
             if (value != null && value != ''){
            	if(value < 0){
            		  Joyin.error('利率不可为负数', function () {
                          table.renderRow(data.LAY_TABLE_INDEX, {yield: " "});
                      });
            	}
             }
           }
            //add by yujiaping 2020-11-27 ZGBS-21债券标的，利率调整，利率不可以输入负数 end
        }
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    
    function saveOrSubmit(operateType) {
        if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0057"),JoyinUtil.getLabel("FinAssetRateAdj001.rateAdj"));
            return;
        }
        var object = {selectedItem: selArr,finprodId:$('#finprodId').val(),finprodType2:$('#finprodType2').val()
                ,finprodMarketId:$('#finprodMarketId').val(),finprodName:$('#finprodName').val(), operateType:operateType};
                finalSubmit(object);
           /* Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result) {
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
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'invest/asset/common/FinAssetRateAdj001/save', object, "FinAssetRateAdj001_btn_submit");
            });
        });
    }
    
    $(function(){
        handleCols();
        if($('#rateType').val()==RATE_TYPE_01){
            $('#FinAssetRateAdj001_btn_submit_div').show();
        }
        $('#FinAssetRateAdj001_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
});
