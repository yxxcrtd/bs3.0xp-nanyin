$(function() {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId:$('#finprodId').val(),
            workflowId:$("#workflowId").val(),
            finprodType:$("#finprodType").val(),
            vdateString:$("#vdateString").val(),
            };
        };
    /**
     * 定义表头
     */
    var cols = [];
    cols = [
        {field:'finprodId',  title: 'FinAssetRateAdj001.finprodId',visible:false,
            render(data,type,row,meta){return finprodId ? finprodId:data;} //金融产品代码
        }
        ,{field:'finprodType',visible:false,
            render(value){return INT_ASSET_TYPE;}
        }
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
  //根据资产类型处理不同的表头
    var handleCols = function(){
        $.each(cols,function(id,val){
            if(val["field"] =="finprodName"){
                if(INT_ASSET_TYPE==FINPROD_TYPE_F01 || INT_ASSET_TYPE==FINPROD_TYPE_F07 || INT_ASSET_TYPE==FINPROD_TYPE_F21){
                    val.visible = false;
                }else{
                    val.visible = true;
                }
            }
            if(val["field"] =="finprodAbbr"){
                if(INT_ASSET_TYPE ==FINPROD_TYPE_F01 || INT_ASSET_TYPE==FINPROD_TYPE_F07 || INT_ASSET_TYPE==FINPROD_TYPE_F21){
                    val.visible = true;
                }else{
                    val.visible = false;
                }
            }
            if(val["field"] =="yield"){
                if(INT_ASSET_TYPE ==FINPROD_TYPE_F01 || INT_ASSET_TYPE==FINPROD_TYPE_F07 || INT_ASSET_TYPE==FINPROD_TYPE_F21){
                    val.title = 'FinAssetRateAdj001.rate';
                }else{
                    val.title = 'FinAssetRateAdj001.loadRate';
                }
            }
            if(val["field"] =="operation"){
                if($('#rateType').val() != RATE_TYPE_01 ){
                    val.visible = false;
                }else{
                    val.visible = true;
                }
            }
        });
        return cols;
    }
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
            ajax: {
                url: JoyinUtil.getRealPath('/valuation/component/asset/FinAssetRateAdj001/initAssetRateAdjInfo'),
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
        var object = {
                selectedItem: selArr,
                finprodId:$('#finprodId').val(),
                finprodType:$('#finprodType').val(),
                finprodType2:$('#finprodType2').val(),
                finprodMarketId:$('#finprodMarketId').val(),
                finprodName:$('#finprodName').val(),
                vdateString:$('#vdateString').val(),
                operateType:operateType
        };
        finalSubmit(object);
    };
    
    /**
     * 最终提交
     */
    var finalSubmit = function(object){
        Joyin.ajaxJson(basePath + 'valuation/component/asset/FinAssetRateAdj001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'valuation/component/asset/FinAssetRateAdj001/save', object, "FinAssetRateAdj001_btn_submit");
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
