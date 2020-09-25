var valTableFileName = '';
CmsFileCommon = function(){
            return {
                uploadCallback : function(res){
                    valTableFileName = '';
                    if(res && res.length > 0){
                        valTableFileName = res[0].fileName;
                     // 为了消除进度条
                        Joyin.ajax(JoyinUtil.getRealPath('invest/risk/namelistmanage/orgnamelist/OrgNameManagement004/uploadFile'));
                    }
                }
            }
    }();
(function () {
    
    /**
     * 获取查询参数
     */
    var searchForm = function () {
        var data = $.serializeToObject('#searchForm');
        return data;
    }
    /**
     * 名单明细变更列表（Detail）
     */
    var table = $('#dataTable').joyintable({
        data: (entity && entity.rskAccessListDetailList) ? entity.rskAccessListDetailList : [],
        page: false,
        loadonce: true,
        addbtn:true,
        checkbox:false,
        searchOnInit: true,
        cols: [
             {field:'listId',visible:false},
             {field:'operateType',visible:false},
             {field:'oldlistDetailName',visible:false}, //金融产品代码
            // 资产简称
             {
                field: 'listDetailName',
                width: '30%',
                edit : true,
                title: 'OrgNameManagement004.finprodName',
                type:'text',
                sortable: false
            } ,
            // 资产代码
             {
                 field: 'listDetailId',
                 width: '30%',
                 edit : true,
                 title: 'OrgNameManagement004.finprodId',
                 sortable: false
             }
            , {
                field: 'operatePs',
                width: '30%',
                title: 'AssetNameManagement003.operatePs',
                sortable: false,
                render:function (data, type, row, meta) {
                    if (row.operatePs == RSK_OPERATE_PS_01)
                        return "移出";
                    else if (row.operatePs == RSK_OPERATE_PS_02){
                        return "移入";
                    }else if (row.operatePs == RSK_OPERATE_PS_03){
                        return "属性变更";
                    }else{ return '不变'};
                }
            }
        ]
    });
    
    var init = function(){
       if((pageModel === PAGE_MODEL_REFERENCE||pageModel === PAGE_MODEL_EDIT)&&workflowId){
           $('#addNum').html(entity.addNum).trigger('blur');
           $('#editNum').html(entity.editNum).trigger('blur');
           $('#deleteNum').html(entity.deleteNum).trigger('blur');
           $('#tplDowload').hide();
           //$('#inputTypeId').hide();
           //$('#uploadFileId').hide();
        }
       if(pageModel === PAGE_MODEL_REFERENCE){
           $("div.layui-upload-drag").eq(1).find('i.layui-icon').eq(0).closest('div').empty();
        }
    }

    /**
     * 导出
     */
    var exportExcel = function() {
        var object = $.serializeToObject('#submitForm');
        Joyin.exportExcel('export', object);//导出
    };
    
    //导入
/*    $('#OrgNameManagement004_btn_upLoadFile').click(function() {
        Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据"), function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + "invest/risk/namelistmanage/assetnamelist/OrgNameManagement004/importFile", {
                    fileName : fileName
                },function(result){
                       $('#addValue').html(Object.keys(result.list).length).trigger('blur');
                       layui.layer.closeAll();
                       table.addRow(result.list); 
                      
                });
            });
        });
    });*/
    /**
     * 获取文件路径名
     */
    var getFileName = function(){
        var fun = $('#appendix').data('getSelectedFiles');
        if(typeof fun === 'function') {
            return fun();
        }
        return [];
    }
    
    var uploadFile =function() {
        if(typeof $('input[name="inputType"]:checked').val() == 'undefined'){
            Joyin.error(JoyinUtil.getMessage("RSK_W_0060"));
            return;
        }
        var fileInfo = getFileName();
        if(typeof fileInfo[0] != 'undefined'){
           fileName = fileInfo[0].targetFile;
           var fileNo = fileInfo[0].fileNo;
           var object = $.serializeToObject('#OrgNameManagement004_submitForm', true);
           object.fileName = fileName;
           //检查文件格式是否正确
           Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement004/check', object, function(
                   result) {
             //导入文件数据
             Joyin.ajax(basePath + "invest/risk/namelistmanage/orgnamelist/OrgNameManagement004/importFile", {
               fileName : fileName,
               inputType:$('input[name="inputType"]:checked').val(),
               listId:$('#listId').val(),
               revealFileId:revealFileId,
               fileNo:fileNo
             },function(result){
                   layer.alert("导入成功", {
                       icon:  1,
                       closeBtn: 0
                   });
                   table.clear();
                   var length= Object.keys(result.list).length;
                   if(length != null && length >0){
                       $('#appendix').data('removeSelectedFiles');
                       $('#addNum').html(result.addNum).trigger('blur');
                       $('#editNum').html(result.editNum).trigger('blur');
                       $('#deleteNum').html(result.deleteNum).trigger('blur');
                       table.addRow(result.list);
                   }
                  
            });
         });
        }else{
            Joyin.error(JoyinUtil.getMessage("RSK_W_0061"));
            return;
        }
    };
    
    /**
     * 保存按钮按下
     *
     * @param array
     * @returns {boolean}
     */
    function saveOrSubmit(operateType) {
        if(table.getAllRows() == '' || table.getAllRows() == null){
            Joyin.error(JoyinUtil.getMessage("RSK_W_0062"));
            return; 
        }
        var object = $.serializeToObject('#OrgNameManagement004_submitForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.addNum = $('#addNum').html();
        object.editNum = $('#editNum').html();
        object.deleteNum = $('#deleteNum').html();
        object.inputType = $('input[name="inputType"]:checked').val();
        object.rskAccessListDetailList = table.getAllRows();
        object.revealFileId = revealFileId;
        object.operateStyle = $('#operateStyle').val();
        Joyin.ajaxJson(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement004/check', object, function(
            result) {
                Joyin.confirm(result.data,function() {
                    finalSave(object,operateType);
                 });
               });
    }
    
    /**
     * 最终提交
     */
    var finalSave = function(object,operateType){
            if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement004/save',
                    object, "OrgNameManagement004_btn_save");
            } else {
                WorkFlow.doSubmitList(basePath + 'invest/risk/namelistmanage/orgnamelist/OrgNameManagement004/save',
                    object, "OrgNameManagement004_btn_submit");
            }
    }
    
    /**
     * 调整后预览结果
     */
    var showAdjPriview = function(){
        Joyin.dialog(JoyinUtil.getLabel('OrgNameManagement005'), basePath +'invest/risk/namelistmanage/orgnamelist/OrgNameManagement005/init', '80%', '80%',
            {listId:$("#listId").val(),
            operateStyle:$("#operateStyle").val()},function(){
            });
    }
    
    var getAssetNameNewAllRows = function(){
        return table.getAllRows();
    }

    // 公开接口
    window.OrgNameManagement004 = {
        getAssetNameNewAllRows:getAssetNameNewAllRows
    };

    /**
     * 画面加载完成
     */
    $(function () {
     // 初始化时
        init();
      //绑定导出按钮
        $('#tplDowload').click(function() {
            exportExcel();//导出
        });
        //调整后预览
        $('#OrgNameManagement004_btn_adj').click(showAdjPriview);
        // 绑定按钮事件
        $('#OrgNameManagement004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#OrgNameManagement004_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#OrgNameManagement004_btn_upLoadFile').click(function() {
            uploadFile();
        });
    });
    
}());