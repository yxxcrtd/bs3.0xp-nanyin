(function(){
    /**
     * 画面数据加载
     */
    var CmsFileCommon = function() {
        return {
            /**
             * 获取上传文件信息事件
             */
            getUploadFileInfo : function(id) {
                var fun = $('#'+id).data('getSelectedFiles');
                if(typeof fun === 'function') {
                    return fun();
                }
                return [];
            }
        };
    }();
    
    var initPageDataAction = function(data){
        var obj = $('#projectCmxinfo_div');
        if(!data){
            return ;
        }
        $.each(data, function(id,value){
            //特殊化处理  id与报送id冲突 
            if(id=='repIndustryLevel2'){
                id = 'repIndustryLevel21';
            }
            var id = obj.find('#' + id);
            if(id && typeof (id.length) != "undefined"){
                if (id.length > 0){
                    if(id.attr('id')=='cashOutNo'){
                        //出账编号直接continue
                    }
                    else if(id.attr('id')=='repIndustryLevel2'){
                        $('#repIndustryLevel21').val(value).trigger('change');
                    }
                    // switch 特殊处理
                    else if (id.attr('lay-skin') === 'switch'){
                        id.next().removeClass('layui-form-onswitch').trigger('change');
                        id.next().find("em").text('否');
                        if (value === 'Y'){
                            id.next().addClass('layui-form-onswitch').trigger('change');
                            id.next().find("em").text('是');
                        }
                    }else{
                        // 清空值
                        id.val('').trigger('change');
                        // 日期特殊处理
                        if (id.hasClass('input-dateSelect')){
                            id.val(new Date(value).format('yyyy-MM-dd'));
                        }else{
                            // 多选下拉框特殊处理
                            if (id.attr('multiple') === 'multiple'){
                                id.val(value.split(",")).trigger('change');
                            }else{
                                id.val(value).trigger('change');
                            }
                        }
                    }
                }
            }
        });
    }
    
    /**
     * 出账编号change事件
     */
    var cashOutNoChange = function(){
        var cashOutNo = $('#cashOutNo').val();
        Joyin.ajax(JoyinUtil.getRealPath('/invest/asset/project/FinProjectTargetAsset003/getCmsInfoBycashOutNo'), {cashOutNo : cashOutNo}, function(result){
            initPageDataAction(result.data);
        });
    }
    
 // 公开接口
    window.FinProjectTargetAssetCms = {
            getUploadFileInfo: CmsFileCommon.getUploadFileInfo
    };

    
    $(function(){
        $('#cashOutNo').change(cashOutNoChange).trigger("change");
        $('#multFileBtn1').click(function() {
            $('.mult-file-btn', '#fileUploader1').trigger('click');
        });
        $('#multFileBtn2').click(function() {
            $('.mult-file-btn', '#fileUploader2').trigger('click');
        });
    })
}());