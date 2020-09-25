$(function () {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/save', object, "ProdDevelopManage002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/save', object, "ProdDevelopManage002_btn_submit");
                }
            });
        });
    };
    var linkBind = function (){
        //  绑定联动：产品设计系列编号
        $("#prdDesignId").change(function(){
              var prdDesignId =  $("#prdDesignId").val();
              var generateTime =  $("#generateTime").val();
              Joyin.ajax(
                    basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/getProdResultInfos', 
                    {prdDesignId : prdDesignId,generateTime : generateTime},
                    function (result) {
                          //产品设计系列名称
                          $("#prdDesignName").val(result.prdDesignName);
                          //产品可行性分析结果
                          $("#feasibilityResult").val(result.feasibilityResult).trigger('change');
                          //产品要素设计结果
                          $("#prdDesignResult").val(result.prdDesignResult.replace(/;/g,";\r\n"));
                          //产品压力测试结果
                          $("#prdLoadResult").val(result.prdLoadResult);
                          //产品风险等级评定结果
                          $("#prdRiskLevel").val(result.prdRiskLevel).trigger('change');
                          //产品模拟测算结果
                          $("#prdMockResult").val(result.prdMockResult);
              });
        });

        //  绑定联动：生成日
        $("#generateTime").change(function(){
            var prdDesignId =  $("#prdDesignId").val();
            var generateTime =  $("#generateTime").val();
            if(prdDesignId && generateTime){
                Joyin.ajax(
                        basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/getProdResultInfos', 
                        {prdDesignId : prdDesignId,generateTime : generateTime},
                        function (result) {
                            //产品设计系列名称
                            $("#prdDesignName").val(result.prdDesignName);
                            //产品可行性分析结果
                            $("#feasibilityResult").val(result.feasibilityResult).trigger('change');
                            //产品要素设计结果
                            $("#prdDesignResult").val(result.prdDesignResult.replace(/;/g,";\r\n"));
                            //产品压力测试结果
                            $("#prdLoadResult").val(result.prdLoadResult);
                            //产品风险等级评定结果
                            $("#prdRiskLevel").val(result.prdRiskLevel).trigger('change');
                            //产品模拟测算结果
                            $("#prdMockResult").val(result.prdMockResult);
                        });
            }
      });
        
    }
    
    
    $(function() {
        linkBind(); // 联动绑定
         // 绑定按钮事件
        $('#ProdDevelopManage002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ProdDevelopManage002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
                        
    });
    
});