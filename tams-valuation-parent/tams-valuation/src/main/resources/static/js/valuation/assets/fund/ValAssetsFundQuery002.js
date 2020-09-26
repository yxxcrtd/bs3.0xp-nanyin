$(function() {
    //基金类型
    var type = $("#fundType").val();
    
    //操作类型:新建new、修改edit、双击查看view
    var operateType = $("#operateType").val();
    
    //新建或者修改做如下操作
    if(operateType=="new" || operateType=="edit"){
//    	$("#profitTypeDiv").hide();
        if (type == '01') {// 货币基金显示，分红规则和分红计划
            $("#rule").show();
            $("#showPlan").show();
            $("#isCompoundIntDiv").show();
            $("#showPlan").click(function() {
                $("#plan").toggle();
            });
            
        }else{
            $("#isCompoundIntDiv").hide();
            
        }
        //当基金类型为“货币基金”时，分红频率为“每日”时，分红日、营业日准则和推算方式等字段不可用
//        if($('#payCycle').val() == "D"){
//            $("#sdate").attr("disabled",true);
//            $("#tdayRule").attr("disabled",true);
//            $("#calcRule").attr("disabled",true);
//        }else{
            //分红频率不为“每日”时，这些字段可用
            $("#sdate").attr("disabled",false);
            $("#tdayRule").attr("disabled",false);
            $("#calcRule").attr("disabled",false);
//        }

        //基金类型下拉框变换
        $("#fundType").change(function() {
//        	$("#profitTypeDiv").hide();
            if ($('#fundType').val() == "01") {
                $("#rule").show();
                $("#isCompoundIntDiv").show();
                $("#showPlan").click(function() {
                    $("#plan").toggle();
                });

            } else {
                $("#rule").hide();
                $("#plan").hide();
                $("#isCompoundIntDiv").hide();
                $("#showPlan").click(function() {
                    $("#plan").toggle();
                });
            }

        });
        //分红频率下拉框变换
//        $("#payCycle").change(function() {
//            if ($('#payCycle').val() == "D") {
//                $("#sdate").attr("disabled",true);
//                $("#tdayRule").attr("disabled",true);
//                $("#calcRule").attr("disabled",true);
//            } else {
//                $("#sdate").attr("disabled",false);
//                $("#tdayRule").attr("disabled",false);
//                $("#calcRule").attr("disabled",false);
//            }
//        });
        
        //当已存在交易数据时，基金类型和货币均不可修改
        if(trade == "true"){
          //alert(trade);
            $("#fundType").attr("disabled",true);
            $("#ccy").attr("disabled",true);
        }
        
        //托管人禁用
        $("#custodian").attr("disabled",true);
    }else{
        //双击查看做如下操作
        //境内外-禁用
        $('#blnArea').next().addClass('disabled').find('li').off('click');
        //本金收益特征-禁用
        $('#capiIncomeFeature').next().addClass('disabled').find('li').off('click');
//        $("#profitTypeDiv").hide();
        if (type == '01') {// 货币基金显示，分红规则和分红计划
            $("#rule").show();
            $("#showPlan").show();
            $("#isCompoundIntDiv").show();
            $("#showPlan").click(function() {
                $("#plan").toggle();
            });
        }else{
            $("#isCompoundIntDiv").hide();
        }
    }
    
    
    
//    $("#ValAssetsFundQuery002_btn_submit").click(function() {
//        var object = $.serializeToObject('#fundForm');
//        object.isCompoundInt = $("#isCompoundInt").next().hasClass('layui-form-onswitch') ? 'Y' : 'N'
//        object.pageModel = pageModel;
//        Joyin.ajaxJson('doCommit', object, function(result) {
//            if (result.errorList.length == 0) {
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
//            }
//        });
//    })
    
    
    /**
         * 修改/新建启动
         */
        var saveOrSubmit = function(operateType) {
        var object = $.serializeToObject('#fundForm');
        object.isCompoundInt = $("#isCompoundInt").next().hasClass('layui-form-onswitch') ? 'Y' : 'N'
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valuation/assets/fund/ValAssetsFundQuery002/check', object, function(result) {
                if(operateType === 'save'){
                    WorkFlow.doSubmitList(basePath + 'valuation/assets/fund/ValAssetsFundQuery002/doUpdate', 
                                object, "ValAssetsFundQuery002_btn_save");
                }else{// submit
                    WorkFlow.doSubmitList(basePath + 'valuation/assets/fund/ValAssetsFundQuery002/save', 
                            object, "ValAssetsFundQuery002_btn_submit",null,function(data){
                            var index = parent.layer.getFrameIndex(window.name);
                             parent.layer.close(index);
                    });
                }
        });
        
    };
        
    
    /**
         * 根据操作类型：显示按钮
         */
         function btnShow() {
            var operateType = $("#operateType").val();        
            if(operateType=="edit") {
                 $("#ValAssetsFundQuery002_btn_save").show();
                 $("#ValAssetsFundQuery002_btn_submit").hide();
            }else if(operateType=="new"){
                 $("#ValAssetsFundQuery002_btn_save").hide();
                 $("#ValAssetsFundQuery002_btn_submit").show();
            }        
        };
    /**
     * 画面加载完成
     */
    $(function() {     
      // 绑定按钮事件
        $('#ValAssetsFundQuery002_btn_save').click(function() {
            saveOrSubmit('save');
           
        });
        $('#ValAssetsFundQuery002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        btnShow();// 根据操作类型：显示按钮
    });
});