/**
 *资产五级分类联动处理js
 */
(function() {
    /**
     * 系统配置资产五级分类联动处理
     * 
     * inputValue1 资产大类
     * inputValue2 二级分类输入元素ID
     * inputValue3 三级分类输入元素ID
     * inputValue4 四级分类输入元素ID
     * inputValue5 五级分类输入元素ID
     * outputValue1 一级分类输出元素ID
     * outputValue2 二级分类输出元素ID
     * outputValue3 三级分类输出元素ID
     * outputValue4 四级分类输出元素ID
     * outputValue5 五级分类输出元素ID
     */
    var doAssetFiveTypeChange = function(isShowType,inputValue1, inputValue2, inputValue3,
            inputValue4, inputValue5, outputValue1, outputValue2, outputValue3,
            outputValue4, outputValue5){
        var param = {
                inputValue1:inputValue1,
                inputValue2:inputValue2==null ? '': $('#'+inputValue2).val(),
                inputValue3:inputValue3==null ? '': $('#'+inputValue3).val(),
                inputValue4:inputValue4==null ? '': $('#'+inputValue4).val(),
                inputValue5:inputValue5==null ? '': $('#'+inputValue5).val()
        }
        if(isShowType == '01'){
        	if(!(param.inputValue1 && param.inputValue2 && param.inputValue3 && param.inputValue4 && param.inputValue5)) {
                return;
            }
        }else{
        	if(!(param.inputValue1 && param.inputValue2 && param.inputValue3)) {
                return;
            }
        }
        
        if(param){
            Joyin.ajaxJson(basePath + 'component/asset/BussAssetFiveType/doAssetFiveTypeChange', param,
                    function(result) {
                if(result != null){
                    if(outputValue1 && result.outputValue1){
                    	if(document.getElementById(outputValue1).tagName == "input"
                    		|| document.getElementById(outputValue1).tagName == "INPUT"){
                    		$('#'+outputValue1).val(result.outputValue1);
                    	} else {
                            $('#'+outputValue1).textSelect('setValue',result.outputValue1);
                    	}
                        $('#'+outputValue1).trigger('change');
                    } else if(outputValue1){
                    	$('#'+outputValue1).val('');
                        $('#'+outputValue1).trigger('change');
                    }
                    if(outputValue2 && result.outputValue2){
                    	if(document.getElementById(outputValue2).tagName == "input"
                    		|| document.getElementById(outputValue2).tagName == "INPUT"){
                    		$('#'+outputValue2).val(result.outputValue2);
                    	} else {
                    	    $('#'+outputValue2).textSelect('setValue',result.outputValue2);
                    	}
                        $('#'+outputValue2).trigger('change');
                    } else if(outputValue2){
                    	$('#'+outputValue2).val('');
                        $('#'+outputValue2).trigger('change');
                    }
                    if(outputValue3 && result.outputValue3){
                    	if(document.getElementById(outputValue3).tagName == "input"
                    		|| document.getElementById(outputValue3).tagName == "INPUT"){
                    		$('#'+outputValue3).val(result.outputValue3);
                    	} else {
                    	    $('#'+outputValue3).textSelect('setValue',result.outputValue3);
                    	}
                        $('#'+outputValue3).trigger('change');
                    } else if(outputValue3){
                    	$('#'+outputValue3).val('');
                        $('#'+outputValue3).trigger('change');
                    }
                    if(outputValue4 && result.outputValue4){
                    	if(document.getElementById(outputValue4).tagName == "input"
                    		|| document.getElementById(outputValue4).tagName == "INPUT"){
                    		$('#'+outputValue4).val(result.outputValue4);
                    	} else {
                    	    $('#'+outputValue4).textSelect('setValue',result.outputValue4);
                    	}
                        $('#'+outputValue4).trigger('change');
                    } else if(outputValue4){
                    	$('#'+outputValue4).textSelect('setValue','');
                        $('#'+outputValue4).trigger('change');
                    }
                    if(outputValue5 && result.outputValue5){
                    	if(document.getElementById(outputValue5).tagName == "input"
                    		|| document.getElementById(outputValue5).tagName == "INPUT"){
                    		$('#'+outputValue5).val(result.outputValue5);
                    	} else {
                            $('#'+outputValue5).textSelect('setValue',result.outputValue5);
                    	}
                        $('#'+outputValue5).trigger('change');
                    } else if(outputValue5){
                    	$('#'+outputValue5).val('');
                        $('#'+outputValue5).trigger('change');
                    }
                }
            });
        }
    }
    
    // 外汇共通组件公开接口
    if(window.BussAssetFiveType == undefined){
        window.BussAssetFiveType = {};
    }
    window.BussAssetFiveType = {
        doAssetFiveTypeChange : doAssetFiveTypeChange
    };
}());