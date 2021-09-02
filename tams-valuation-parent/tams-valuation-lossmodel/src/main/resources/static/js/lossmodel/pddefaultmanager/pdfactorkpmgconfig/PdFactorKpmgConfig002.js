/**
 * PdFactorConfig002 计划类（净值型）资产估值行情信息新建（含修改）
 */
$(function () {
	
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  $(this).removeAttr("lay-key");
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#vouchYear',//指定元素
		    type: 'year'
		  });
		});
	
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        //获取资产代码联想文本框的hidcode金融产品代码
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.seqno = $('#seqno').val();
        }
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/check', object, function (result) {
            Joyin.confirmMulti(result.data, ['是', '否'], function () {
				if(result.data.indexOf("影响历史")>0){
					Joyin.confirm("修改后需手工跑历史批处理！", function () {
						if (operateType === 'save') {
	                    	WorkFlow.doSaveList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/save', object, "PdFactorKpmgConfig002_btn_save");
	                	} else {
	                    	WorkFlow.doSubmitList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/save', object, "PdFactorKpmgConfig002_btn_submit");
	                	}
					});
				}else{
					if (operateType === 'save') {
                    	WorkFlow.doSaveList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/save', object, "PdFactorKpmgConfig002_btn_save");
                	} else {
                    	WorkFlow.doSubmitList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/save', object, "PdFactorKpmgConfig002_btn_submit");
                	}
				}
            });
        });
    };
    //画面初始化
    var init = function () {
        //编辑模式资产代码、估值日期、资产简称不可编辑
        if (pageModel == PAGE_MODEL_EDIT) {
			$('#vouchYear').attr("disabled", true);
            $('#vouchQuarter').attr("disabled", true);
        }
    };
    
    $(function () {
        //初始化画面
        init();
        // 绑定按钮事件
        $('#PdFactorKpmgConfig002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#PdFactorKpmgConfig002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        
    });
}());