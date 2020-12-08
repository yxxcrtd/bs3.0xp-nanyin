(function() {
    var valAssetBook006DtoList=[];
    var object={};
    var confirmFlag=true;

	var getSearchParams = function() {
        return {
            paramCode: $('#paramCode').val(),
            paramName: $('#paramName').val(),
            remark: $('#remark').val(),
            booksetId:$('#booksetId').val(),
            valAssetBook006DtoList:valAssetBook006DtoList,
            workflowId:workflowId
        }
    };

    var table=$("#dataTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('valuation/accounadjustmanage/assetbook/ValAssetBook006/findPage'),
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },

        cols:[{field:'booksetId',title:'账套代码',visible:false},
             {field:'paramName', title: '参数名称'},
        	 {field:'paramCode', title: '参数代码'},
        	 {field:'paramValue',title:'参数值'},
        	 {field:'objParamValue',edit:modifyFlag=='false'?false:true, title: '修改参数值',sortable : false},
        	 {field:'remark', title: '备注'}
        ],
        editCell:function(value, data, field, cell, column){
            if(field=='objParamValue'){
                var flag=true;
                if(value!=null&&value!=''){
                    if(value!=data.paramValue){
                        for(j=0;j<valAssetBook006DtoList.length;j++){
                            if(valAssetBook006DtoList[j].paramCode==data.paramCode){
                                valAssetBook006DtoList[j].objParamValue=data.objParamValue;
                                flag=false;
                            }
                        }
                        if(flag){
                            valAssetBook006DtoList.push(data);
                        }
                    }else{
                        for(l=0;l<valAssetBook006DtoList.length;l++){
                            if(valAssetBook006DtoList[l].paramCode==data.paramCode){
                                valAssetBook006DtoList.splice(l,1);
                                l--;
                            }
                        }
                    }
                }else{
                    for(k=0;k<valAssetBook006DtoList.length;k++){
                        if(valAssetBook006DtoList[k].paramCode==data.paramCode){
                            valAssetBook006DtoList.splice(k,1);
                            k--;
                        }
                    }
                }
            }
        },
        searchOnInit: true,
        checkbox:false
    });


    function submit(operateType) {
        object.workflowId=workflowId;
    	object.booksetId=booksetId;
        object.valAssetBook006DtoList = valAssetBook006DtoList;
        if(workflowId!=null&&workflowId!=''){
            valAssetBook006DtoList=table.getAllRows();
            for(m=0;m<valAssetBook006DtoList.length;m++){
                if(valAssetBook006DtoList[m].objParamValue==null||valAssetBook006DtoList[m].objParamValue==''||valAssetBook006DtoList[m].objParamValue==valAssetBook006DtoList[m].paramValue){
                    valAssetBook006DtoList.splice(m,1);
                    m--;
                }
            }
            object.valAssetBook006DtoList=valAssetBook006DtoList;
        }
        if(!(object.valAssetBook006DtoList.length!=null&&object.valAssetBook006DtoList.length!=''&&object.valAssetBook006DtoList.length!=0)){
            Joyin.error(JoyinUtil.getMessage("并未有参数被修改!"));
            return;
        }
        Joyin.confirm("是否确认修改？", function() {
                if(operateType=='submit'){
                    WorkFlow.doSubmitList(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook006/save', object, "ValAssetBook006_btn_save");
                }else if(operateType=='save'){
                    WorkFlow.doSaveList(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook006/save', object, "ValAssetBook006_btn_submit");
                }
        });
    };

    //导出
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };
    /**
     * 画面加载完成
     */
    $(function() {
        if(modifyFlag=='false'){
            $('#ValAssetBook006_btn_save').hide();
            $('#ValAssetBook006_btn_submit').hide();
        }
    	// 绑定按钮事件
    	$('#ValAssetBook006_btn_search').click(function() {
    		table.load();
    	});

        $('#ValAssetBook006_btn_submit').click(function() {
            submit('submit');
        });
        $('#ValAssetBook006_btn_save').click(function() {
            submit('save');
        });
        $('#ValAssetBook006_btn_export').click(function(){
            exportExcel();
        })
    });
}());