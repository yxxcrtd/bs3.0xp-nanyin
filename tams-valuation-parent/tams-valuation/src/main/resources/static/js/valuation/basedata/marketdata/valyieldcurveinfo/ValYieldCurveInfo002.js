$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.curveCode=$("#curveCode").val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valyieldcurveinfo/ValYieldCurveInfo002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/basedata/marketdata/valyieldcurveinfo/ValYieldCurveInfo002/save', object, "ValYieldCurveInfo002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/valyieldcurveinfo/ValYieldCurveInfo002/save', object, "ValYieldCurveInfo002_btn_submit");
                }
            });
        });
    };


    //添加n、k联动事件 add by lishuping 20191008 start
    var selectNK = function(curveCode, n, k){
        var param = {
            curveCode:curveCode==null ? '': $('#'+curveCode).val(),
        }
        if(!(param.curveCode)) {
            return;
        }
        if(param){
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valyieldcurveinfo/ValYieldCurveInfo002/selectNK', param,
                function(result) {
                    if(result != null){
                        if(n && result.n){
                            if(document.getElementById(n).tagName == "input"
                                || document.getElementById(n).tagName == "INPUT"){
                                $('#'+n).val(result.n);
                            } else {
                                $('#'+n).textSelect('setValue',result.n);
                            }
                            $('#'+n).trigger('change');
                        } else if(n){
                            $('#'+n).val('');
                            $('#'+n).trigger('change');
                        }
                        if(k && result.k){
                            if(document.getElementById(k).tagName == "input"
                                || document.getElementById(k).tagName == "INPUT"){
                                $('#'+k).val(result.k);
                            } else {
                                $('#'+k).textSelect('setValue',result.k);
                            }
                            $('#'+k).trigger('change');
                        } else if(k){
                            $('#'+k).val('');
                            $('#'+k).trigger('change');
                        }
                    }
                });
        }
    }
    //添加n、k联动事件 add by lishuping 20191008 end
  //画面初始化
    var init = function(){
        if(pageModel==PAGE_MODEL_EDIT){
            $('#curveCode').attr("disabled",true);
            $('#curveDate').attr("disabled",true);
            $('#curveType').attr("disabled",true);
        }
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	//初始化画面
    	init();
    	//绑定联动事件 add by lishuping 20191008 start
        $('#curveCode').change(function(){
            selectNK('curveCode','n','k');
        });
        //绑定联动事件 add by lishuping 20191008 end
        // 绑定按钮事件
        $('#ValYieldCurveInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValYieldCurveInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());