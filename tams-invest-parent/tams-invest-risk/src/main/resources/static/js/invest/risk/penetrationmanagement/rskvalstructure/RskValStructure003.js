 /**
 * RskValStructure003 映射明细新建（含修改）
 */
$(function() {
    $("#RskValStructure003_btn_submit").click(function() {
        var object = $.serializeToObject('#submitForm');
        object.operateType = "submit";
        object.pageModel = pageModel;
        object.srcColIndex = object.srcColIndexLetter;
        //判断是新建界面还是编辑界面
        if(pageModel===PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/check',object, function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/insert',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                    })
                })
            }
        else {
            Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/check',object,function(result) {
                Joyin.confirm(result.data, function() {
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure003/update',
                        object,function(result){
                            //点击保存后关闭窗口
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        })
                })
            })
        }
    });
    //页面初始化
    var init = function(){
    	if(pageModel==PAGE_MODEL_EDIT){
    		$('#srcColIndex').attr("disabled",true);
    	}
    	
    };
    $(function() {
    	init();
    });
});
