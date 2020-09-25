/**
 * FinNetProductBonusScheme002 指标参数新建
 */
(function() {
    //画面联动：输入产品代码，联动显示产品名称和产品简称
    var paraDisplayTypeChange = function() {
        /*var paraDisplayType = $("#paraDisplayType").val();
        if(paraDisplayType == paraDisplayTypeFix){
            $('.validDate').show();
            $('.notifyDate').show();
            $('.content').show();
            $('.rtContent').hide();
            $('.exContent').hide();
        }
        else {
            $('.validDate').hide();
            $('.notifyDate').hide();
            $('.content').hide();
            $('.rtContent').show();
            $('.exContent').show();
        }*/
    };

    //---add by 裴家俊 20190506---画面联动：输入产品代码，联动显示产品名称和产品简称
    var paraDisplayClassifyChange = function(flag) {
        /*var paraDisplayClassify = $("#paraDisplayClassify").val();
        if(paraDisplayClassify=='01'){
        	$("#assetTypeDivClassify").show();
        	$("#investPropDivClassify").hide();
        	$('.paraAssetSecType').hide();
        	$('.paraAssetThreeType').hide();
        	 $('.paraAssetSecTypeWithProj').hide();
        	  $('.paraAssetSecTypeWithFund').hide();
        	  $('.paraAssetThreeTypeWithFund').hide();
        	   $('.paraAssetSecTypeWithPlan').hide();
        }
        else{
        	$("#assetTypeDivClassify").hide();
        	$("#investPropDivClassify").show();
        }
        if(flag=='01'){
        $("input:checkbox:checked").each(function(){
      		 if($(this).is(':checked')){
      			 $(this).prop('checked',false);
      			
      		 }
           	})
           	layui.form.render('checkbox')
        }*/
    };
    // //画面联动：资产类型选了债券，显示债券二级类型
    // var paraAssetTypeChange = function() {
    //     var form = layui.form;
    //     form.on('checkbox(F01)', function(data){
    //         if()
    //         console.log(data.elem); //得到checkbox原始DOM对象
    //         console.log(data.elem.checked); //是否被选中，true或者false
    //         console.log(data.value); //复选框value值，也可以通过data.elem.value得到
    //         console.log(data.othis); //得到美化后的DOM对象
    //     });
    //     var paraAssetType = $("#paraAssetType").val();
    //     if(paraAssetType) {
    //         for (var i = 0; i < paraAssetType.length; i++) {
    //             if (paraAssetType[i] == '01') {
    //                 $('.paraAssetSecType').show();
    //             }
    //             else $('.paraAssetSecType').hide();
    //         }
    //     }
    //     else $('.paraAssetSecType').hide();
    // };

    //初始化
    var init = function () {
    	paraDisplayTypeChange();
        if(pageModel == PAGE_MODEL_EDIT){
            $('#paraCode').prop("disabled",true);
        }
        if ($('#isForbid').next().find("em").text() === '是') {
        	$("#forbidEnumDiv").show();
        } else {
        	$("#forbidEnum").val('');
       		$("#forbidEnumDiv").hide();
        }
    }

    //保存按钮
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#submitForm',true);
    	object.workflowId = $("#workflowId").val();
    	object.content = $("#content").val();
    	object.mrContent = $("#mrContent").val();
    	object.mcContent = $("#mcContent").val();
    	object.rjContent = $("#rjContent").val();
    	object.rzContent = $("#rzContent").val();
    	object.forbidEnum = $("#forbidEnum").val();
        //-----add by 裴家俊  2019/03/20-----end---------
        object.pageModel = pageModel;
        if ($('#isForbid').next().find("em").text() === '是') {
            object.isForbid = 'Y';
        } else {
            object.isForbid = 'N';
        }
        if ($('#isFreshCanTrd').next().find("em").text() === '是') {
            object.isFreshCanTrd = 'Y';
        } else {
            object.isFreshCanTrd = 'N';
        }
        object.operateType = operateType;
        object.pageId="RskSupervisionIndicatorParam002";
        //处理特殊字符
		_.each(object,function (val,key) {
			if((key == 'content'||key == 'mrContent'||key == 'mcContent'||key == 'rjContent'||key == 'rzContent')&&val){
				object[key] = val.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','"':'&quot;'}[c];});
			}
		})
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/save', object, "RskSupervisionIndicatorParam002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/save', object, "RskSupervisionIndicatorParam002_btn_submit");
                }
            });
        });
    };
    
	function getByteLen(val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
           var length = val.charCodeAt(i);
           if(length>=0&&length<=128)
            {
                len += 1;
            }
            else
            {
                len += 2;
            }
        }
        return len;
    }
	
    function AntiSqlValid(cvalue)
	{
		var re= /insert |update |delete |create |alter |drop |truncate |merge |@|;/i;
		if ( re.test(cvalue) )
		{
			return false;
		}
		/*else{
			if ( cvalue.toLowerCase().indexOf('select')!=0){
				return false
			}
		}*/
		return true;
	}
   function checkFun(){
	   if($("#content").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#content").val().replace(/(^\s*)|(\s*$)/g, ""))){
				$('#content').addClass('errors');
				Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0023"));
				return false;
			}
		}
		if($("#mrContent").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#mrContent").val().replace(/(^\s*)|(\s*$)/g, ""))){
				$('#mrContent').addClass('errors');
				Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0023"));
				return false;
			}
		}
		if($("#mcContent").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#mcContent").val().replace(/(^\s*)|(\s*$)/g, ""))){
				$('#mcContent').addClass('errors');
				Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0023"));
				return false;
			}
		}
		if($("#rjContent").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#rjContent").val().replace(/(^\s*)|(\s*$)/g, ""))){
				$('#rjContent').addClass('errors');
				Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0023"));
				return false;
			}
		}
		if($("#rzContent").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#rzContent").val().replace(/(^\s*)|(\s*$)/g, ""))){
				$('#rzContent').addClass('errors');
				Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0023"));
				return false;
			}
		}
		return true;
   }
   var paraDisplayTypeChange = function(){
	   var paraDisplayType = $("#paraDisplayType").val();
	   if(paraDisplayType==PLUGIN){
		   $("#redisField").val('');
		   $("#isFreshCanTrd").val('');
		   $("#mrContent").val('');
		   $("#mcContent").val('');
		   $("#redisFieldDiv").hide();
		   //$("#isFreshCanTrdDiv").hide();
		   $("#mrContentDiv").hide();
		   $("#mcContentDiv").hide();
	   }else{
		   $("#redisFieldDiv").show();
		   //$("#isFreshCanTrdDiv").show();
		   $("#mrContentDiv").show();
		   $("#mcContentDiv").show();
	   }
   };
   function isForbidChang(data) {
   	if(data.checked){
   		$("#forbidEnumDiv").show();
   	}
   	else{
   		$("#forbidEnum").val('');
   		$("#forbidEnumDiv").hide();
   	}
   }
    /**
     * 画面加载完成
     *
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#RskSupervisionIndicatorParam002_btn_save').click(function() {
        	if(checkFun()){
        		saveOrSubmit("save");
        	}
        });
        $('#RskSupervisionIndicatorParam002_btn_submit').click(function() {
        	if(checkFun()){
        		saveOrSubmit("submit");
        	}
        });
        $("#paraDisplayType").change(function(){
        	paraDisplayTypeChange();
        });
        layui.form.on('switch(isForbidFilter)', function(data){
        	isForbidChang(this);
		  });
        $("#choose").click(function(){
        	Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam004/init",
                    "70%", "70%",{redisField:$("#redisField").val()},function(result) {
                                  if(result){
                                	 $("#redisField").val(result) 
                                  }      
                                    });
        })
        //--add by 裴家俊 20190506---分类维度联动
        
        //--add by 裴家俊 20190506---分类维度联动
        //类型联动
        
        //var form = layui.form;
        
        //----add by 裴家俊 2019/05/06----end------
        
    });
}());

