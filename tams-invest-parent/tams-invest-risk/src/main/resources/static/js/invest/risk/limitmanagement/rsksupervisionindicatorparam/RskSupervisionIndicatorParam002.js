/**
 * FinNetProductBonusScheme002 指标参数新建
 */
(function() {
    //---add by 裴家俊 20190811---是否禁投联动---
    var isForbidChangeAction=function () {
        var isForbid = $("#isForbid").val();
        if(isForbid=="Y"){
            $("#forbidEnumDiv").show();
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="SQL"]').addClass('laytable-checkbox-selected');
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="PLUGIN"]').hide();
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="PLUGIN"]').removeClass('laytable-checkbox-selected');
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="FIX"]').hide();
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="FIX"]').removeClass('laytable-checkbox-selected');
        }else {
            $("#forbidEnum").val('').trigger('change');
            $("#forbidEnumDiv").hide();
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="PLUGIN"]').show();
            $("div#paraDisplayTypeDiv").find('ul.layui-checkbox-u').find('li[data-value="FIX"]').show();
        }

    }
    //---add by 裴家俊 20190811---是否禁投联动---
    //画面联动：选择参数实现类型，SQL，类名或固定值
    var paraDisplayTypeChange = function() {
        var paraDisplayType = $("#paraDisplayType").val();
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
        }
    };

    //---add by 裴家俊 20190506---画面联动：输入产品代码，联动显示产品名称和产品简称
    var paraDisplayClassifyChange = function(flag) {
        var paraDisplayClassify = $("#paraDisplayClassify").val();
        if(paraDisplayClassify=='01'){
        	$("#assetTypeDivClassify").show();
        	$("#investPropDivClassify").hide();
        	$('.paraAssetSecType').hide();
        	$('.paraAssetThreeType').hide();
        	 $('.paraAssetSecTypeWithProj').hide();
        	  $('.paraAssetSecTypeWithFund').hide();
        	  $('.paraAssetThreeTypeWithFund').hide();
        	   $('.paraAssetSecTypeWithPlan').hide();
            $('.paraAssetThreeTypeWithProj').hide();

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
        }
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
        if(pageModel == PAGE_MODEL_EDIT){
            $('#paraCode').prop("disabled",true);
        }
        else if(pageModel == PAGE_MODEL_REFERENCE){
            $("input[name='paraAssetType']").attr("disabled",true);
            $("input[name='paraAssetSecType']").attr("disabled",true);
        }
        //---add by 裴家俊 2019/03/22--更改与项目类资产类型code包含冲突--start------
        var arrayAstSecType=new Array();
        if(null!=entity&&null!=entity.paraAssetSecType){
        	arrayAstSecType=entity.paraAssetSecType.split(",");
        }
        $(".paraAssetSecTypeWithPlan").find('input[type="checkbox"]').each(function(i,objchk){
            var checkObj=$(objchk).val();
        	$.each(arrayAstSecType,function(index,value){
        		if(checkObj==value){
        			$(objchk).prop("checked",true);
        		}
        	});
        	layui.form.render('checkbox');
    	})
    	//---add by 裴家俊 2019/03/22----end------
        paraDisplayTypeChange();
        
        //--add by 裴家俊 20190506----start----
        paraDisplayClassifyChange("02");
        //--add by 裴家俊 20190506----end----
        if($("input[name='paraAssetType'][value='F01']").prop('checked')) {
            $('.paraAssetSecType').show();
        }
        else {
            $('.paraAssetSecType').hide();
        }
        //-----add by 裴家俊  2019/03/16-----start---------
        if($("input[name='paraAssetType'][value='F06']").prop('checked')) {
            $('.paraAssetSecTypeWithFund').show();
        }
        else {
            $('.paraAssetSecTypeWithFund').hide();
        }
        if($("input[name='paraAssetType'][value='F17']").prop('checked')) {
            $('.paraAssetSecTypeWithPlan').show();
        }
        else {
            $('.paraAssetSecTypeWithPlan').hide();
        }
        if($("input[name='paraAssetType'][value='F18']").prop('checked')) {
            $('.paraAssetSecTypeWithProj').show();
        }
        else {
            $('.paraAssetSecTypeWithProj').hide();
        }
        //-----add by 裴家俊  2019/03/16-----end---------
        //-----add by 裴家俊  2019/05/06-----start---------
        if($("input[name='paraAssetSecType'][value='B05']").prop('checked')) {
            $('.paraAssetThreeType').show();
        }
        else {
            $('.paraAssetThreeType').hide();
        }
        if($("input[name='paraAssetSecType'][value='F02']").prop('checked')) {
            $('.paraAssetThreeTypeWithFund').show();
        }
        else {
            $('.paraAssetThreeTypeWithFund').hide();
        }
        //-----add by 裴家俊  2019/08/15-----start---------
        if($("input[name='paraAssetSecType'][value='P01']").prop('checked')) {
            $('.paraAssetThreeTypeWithProj').show();
        }
        else {
            $('.paraAssetThreeTypeWithProj').hide();
        }
        //-----add by 裴家俊  2019/08/15-----end---------
        //--add by 裴家俊 20190811---动态禁投-start----
        isForbidChangeAction();
    }

    //保存按钮
    function save() {
        var object = $.serializeToObject('#submitForm');
        object.rtContent=$("#rtContent").val();
        object.exContent=$("#exContent").val();
        object.fixContent=$("#fixContent").val();
        //-----add by 裴家俊  20190811-----start---------
        object.forbidEnum=$("#forbidEnum").val();
        //-----add by 裴家俊  20190811-----end---------
      //-----add by 裴家俊  2019/03/20-----start---------
        if($("#paraDisplayType").val()=='FIX'&&''!=object.fixContent&&getByteLen(object.fixContent)>60){
			Joyin.error(JoyinUtil.getMessage('RSK_W_0033',["","60"]));
			return ;
		}
        //-----add by 裴家俊  2019/03/20-----end---------
        object.pageModel = pageModel;
        object.operateType = "save";
        Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'invest/risk/limitmanagement/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/save', object, function(result){
                    //点击保存后关闭窗口
                    // parent.rsk.refreshTable();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
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
		var re= /insert|update|delete|exec|@|;/i;
		if ( re.test(cvalue) )
		{
			return false;
		}
		else{
			if ( cvalue.toLowerCase().indexOf('select')!=0||cvalue.toLowerCase().indexOf('from')<=6){
				return false
			}
		}
		return true;
	}
   function checkFun(){
	   if($("#paraDisplayType").val()=='SQL'&&$("#rtContent").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#rtContent").val().replace(/(^\s*)|(\s*$)/g, ""))){
				Joyin.error(JoyinUtil.getMessage("RSK_W_0023"));
				return false;
			}
		}
		if($("#paraDisplayType").val()=='SQL'&&$("#exContent").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
			if(!AntiSqlValid($("#exContent").val().replace(/(^\s*)|(\s*$)/g, ""))){
				Joyin.error(JoyinUtil.getMessage("RSK_W_0023"));
				return false;
			}
		}
		//----add by jjpei 20190812-----禁投枚举值的验证---start--------
       if($("#paraDisplayType").val()=='SQL'&&$("#forbidEnum").val().replace(/(^\s*)|(\s*$)/g, "")!=''){
           if(!AntiSqlValid($("#forbidEnum").val().replace(/(^\s*)|(\s*$)/g, ""))){
               Joyin.error(JoyinUtil.getMessage("RSK_W_0023"));
               return false;
           }
       }
       //----add by jjpei 20190812-----禁投枚举值的验证---end----------
		return true;
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
        		save();
        	}
        });
        //--add by 裴家俊 20190506---分类维度联动
        $('#paraDisplayClassify').change(function() {
        	paraDisplayClassifyChange("01");
        });
        //--add by 裴家俊 20190506---分类维度联动
        // ---add by 裴家俊 20190811--是否禁投改变联动
        $('#isForbid').change(function(){
            isForbidChangeAction();
        });
        // ---add by 裴家俊 20190811--是否禁投改变联动
        //类型联动
        $('#paraDisplayType').change(function() {
            paraDisplayTypeChange();
        });
        //资产类型联动
        $('#paraAssetType').change(function() {
            paraAssetTypeChange();
        });
        var form = layui.form;
        form.on('checkbox(F01)', function(data){
            if(data.elem.checked){
                $('.paraAssetSecType').show();
            }
            else {
            	$('.paraAssetSecType').hide();
            	$("input[id='paraAssetSecTypeWithBond']").each(function(){
           		 if($(this).is(':checked')){
           			 $(this).prop('checked',false);
           			
           		 }
	           	})
	           	layui.form.render('checkbox')
            }
        });
        //----add by 裴家俊 2019/03/16----start------
        form.on('checkbox(F06)', function(data){
            if(data.elem.checked){
                $('.paraAssetSecTypeWithFund').show();
            }
            else {
            	$('.paraAssetSecTypeWithFund').hide();
            	$("input[id='paraAssetSecTypeWithFund']").each(function(){
           		 if($(this).is(':checked')){
           			 $(this).prop('checked',false);
           			
           		 }
	           	})
	           	layui.form.render('checkbox')
            }
        });
        form.on('checkbox(F17)', function(data){
            if(data.elem.checked){
                $('.paraAssetSecTypeWithPlan').show();
            }
            else {
            	$('.paraAssetSecTypeWithPlan').hide();
            	$("input[id='paraAssetSecTypeWithPlan']").each(function(){
           		 if($(this).is(':checked')){
           			 $(this).prop('checked',false);
           			
           		 }
	           	})
	           	layui.form.render('checkbox')
            }
        });
        form.on('checkbox(F18)', function(data){
            if(data.elem.checked){
                $('.paraAssetSecTypeWithProj').show();
            }
            else {
            	$('.paraAssetSecTypeWithProj').hide();
            	$("input[id='paraAssetSecTypeWithProj']").each(function(){
           		 if($(this).is(':checked')){
           			 $(this).prop('checked',false);
           			
           		 }
	           	})
	           	layui.form.render('checkbox')
            }
        });
        //----add by 裴家俊 2019/03/16----end------
        
      //----add by 裴家俊 2019/05/06----start------
        form.on('checkbox(B05_bondsec)', function(data){
            if(data.elem.checked){
                $('.paraAssetThreeType').show();
            }
            else {
            	$('.paraAssetThreeType').hide();
            	$("input[id='paraAssetThreeType']").each(function(){
           		 if($(this).is(':checked')){
           			 $(this).prop('checked',false);
           			
           		 }
	           	})
	           	layui.form.render('checkbox')
            }
        });
        form.on('checkbox(F02_fundsec)', function(data){
            if(data.elem.checked){
                $('.paraAssetThreeTypeWithFund').show();
            }
            else {
            	$('.paraAssetThreeTypeWithFund').hide();
            	$("input[id='paraAssetThreeTypeWithFund']").each(function(){
           		 if($(this).is(':checked')){
           			 $(this).prop('checked',false);
           			
           		 }
	           	})
	           	layui.form.render('checkbox')
            }
        });
        //----add by 裴家俊 2019/05/06----end------

        //----add by 裴家俊 2019/08/15----end------
        form.on('checkbox(P01_projectsec)', function(data){
            if(data.elem.checked){
                $('.paraAssetThreeTypeWithProj').show();
            }
            else {
                $('.paraAssetThreeTypeWithProj').hide();
                $("input[id='paraAssetThreeTypeWithProj']").each(function(){
                    if($(this).is(':checked')){
                        $(this).prop('checked',false);

                    }
                })
                layui.form.render('checkbox')
            }
        });
        //----add by 裴家俊 2019/08/15----end------
    });
}());

