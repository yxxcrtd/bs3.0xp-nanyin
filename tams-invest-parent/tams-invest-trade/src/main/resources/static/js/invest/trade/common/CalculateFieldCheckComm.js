(function() {
	var param = null;
	
	var saveOrSubmitCheck = function(mes){
        if(mes == 'save'){
            mes = JoyinUtil.getLabel("SysCommon.save");
        }else if(mes == 'submit'){
            mes = JoyinUtil.getLabel("SysCommon.submit");
        }
    }
	
	var getparam = function (data) {
        param = data ;
    }

    var deepCopy = function deepCopy(obj) {
        var result = Array.isArray(obj) ? [] : {};
        for (var key in obj) {
            if (obj.hasOwnProperty(key)) {
                if (typeof obj[key] === 'object' && obj[key]!==null) {
                    result[key] = deepCopy(obj[key]);   //递归复制
                } else {
                    result[key] = obj[key];
                }
            }
        }
        return result;
    }

    // 检查生成风险试算参数是否发生变化
    var isCalcConditionChanged = function(newdata) {
        // 参照模式，不做check
        if (pageModel === PAGE_MODEL_REFERENCE) {
            return false;
        }
        if (param == null) {
            return false;
        }

        if(newdata != null){
            var currentCalcConditionObj = newdata;
            var calcConditionObjBackUp = param;
            // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
            for ( var key in currentCalcConditionObj) {
                if (typeof currentCalcConditionObj[key] != "object"
                    && currentCalcConditionObj[key] != calcConditionObjBackUp[key]) {
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                        ["影响计算按钮要素", "点击计算"]));
                    return true;
                }
            }
        }
    };
    
	window.CalculateFieldCheckComm = {
            isCalcConditionChanged:isCalcConditionChanged,
            getparam:getparam,
            deepCopy: deepCopy
    };
}());