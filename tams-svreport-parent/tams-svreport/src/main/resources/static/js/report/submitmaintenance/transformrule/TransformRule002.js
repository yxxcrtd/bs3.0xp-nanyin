$(function() {
	var initFunc = function() {
	    if(pageModel == PAGE_MODEL_EDIT){
	        $('#orgField').attr("disabled","true");
	    }
	};

	var getAllData=function(){
        var param={};
        var str='';
        $.each($('#submitForm').find(":input"),function(i,v){
            var id = $(v).attr("id");
            var idv=$("#"+id).val();
            str=str+'"'+id+'":"'+idv +'",'
        });
        str="{"+str.substring(0,str.length-1)+"}";
        param=JSON.parse(str);
        return param;
    };
	
	var transFunc = function() {
		if ($("#orgField").val() == "" || $("#pinyin").val() == "") {
			return;
		} else {
			var pinyinStr=$("#pinyin").val().trim();
			var orgFieldStr=$("#orgField").val().trim();
			var unicodeStr=ch2Unicdoe(orgFieldStr);
			var targetField=pinyinStr+"["+unicodeStr+"]";
			$("#fieldUnicode").attr("value",unicodeStr);
			$("#targetField").attr("value",targetField);
		}
	};
	

	// 判断字符是否为汉字
	function isChinese(s){
		return /[\u4e00-\u9ff5]/.test(s);
	}
	
	//中文转为Unicode
	function ch2Unicdoe(str){
		if(!str){
			return;
		}
		var unicode = '';
		for (var i = 0; i <  str.length; i++) {
			var temp = str.charAt(i);
			unicode +=  temp.charCodeAt(0).toString(16);
//			if(isChinese(temp)){
//				unicode +=  temp.charCodeAt(0).toString(16);
//			}
//			else{
//				unicode += temp;
//			}
		}
		return preZeroFill(unicode);
	};
	
	//补足长度
	function preZeroFill (str) {
		str ='00000'+str;
	    return str.substring(str.length-5,str.length);
	}
	

	initFunc();
	
    $("#orgField").on('change',function(){
        transFunc();
    });
    
    $("#pinyin").on('change',function(){
    	transFunc();
    });

    $("#orgField").keydown(function(e) {  
        if (e.keyCode == 13) {  
        	transFunc();
        }  
    });  
    
    $("#pinyin").keydown(function(e) {  
        if (e.keyCode == 13) {  
        	transFunc();
        }  
    });  
    
    $("#TransformRule002_btn_save").on('click',function(){	
    	
    	if(pageModel==PAGE_MODEL_EDIT){
        	var param = getAllData();
        	param.seqno=seqno;
        	param.operateType = "edit";
        	Joyin.ajaxJson("checkSave",param,function(result){
                if(result){
                	Joyin.ajaxJson("doEditSave",param,function(result){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        Joyin.ajaxJson("doRefreshTrans",null,function(result){
                    		
                    	})
                	});
                }
            })
        }else if(pageModel==PAGE_MODEL_NEW){
        	var param = getAllData();
        	param.operateType = "save";
        	Joyin.ajaxJson("checkSave",param,function(result){
                if(result){
                	Joyin.ajaxJson("doSave",param,function(result){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        console.log(result);
                        Joyin.ajaxJson("doRefreshTrans",null,function(result){
                    		
                    	})
                	});
                }
            })
        }

    });
    
});