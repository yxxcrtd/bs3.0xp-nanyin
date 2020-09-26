$(function () {
    
  
    
  //当前是否包含流程
    var workflowIdNow = $("#workflowId").val();
    var accountType = $("#accountType").val();
    //帐号改变 帐户类型作出联动改变
//    $("#accountId").change(function(){
//        var object = {};
//        object.accountName = $("#accountId").find("option:selected").text();
//        accountId = $("#accountId").val();
//        console.log(object);
//        object.accountId=accountId;
//        if(accountId!=''){
//            
//            Joyin.ajaxJson('searchType', object, function(result){
//                if(result.errorList.length==0){
//                    result.data.status = 'N';
//                    $("#accountType").val("").trigger("change");
//                    $("#accountType").val(result.data).trigger("change");
//                    $("#accountType").val("A03").trigger("change");
//                    $("#accountType").attr("data-initvalue","A03");
                
//                }
//            });
//        }
       
        
//    });
    $('#rate').change(function(){
        var rate = $('#rate').val();
        if(rate==''){
            
        }else{
        if(isNaN(rate)){
            Joyin.error(JoyinUtil.getMessage("利率格式NaN不符合要求"));  
            return;
        }
        else{
        if(rate>100){
            Joyin.error(JoyinUtil.getMessage("利率格式不符合要求"));  
            return;
        }else if(rate<0.01){
            Joyin.error(JoyinUtil.getMessage("利率格式不符合要求"));  
            return;
        }
    }
        }
    });
    
    $("#AccountCurrentRate002_btn_submit").click(function() {
//        var object = $.serializeToObject('#submitForm');
        console.log(pageModel);
        var object = {};
        object.effDate = $('#effDate').val();
        object.cashId = $('#cashId').val();
        object.accountId = $('#accountId').val();
        object.basis=$('#basis').val();
        object.intRateStructure=$('#intRateStructure').val();
        object.intType=$('#intType').val();
        object.rate=$('#rate').val();
        object.basicRate=$('#basicRate').val();
        object.effDate1=$('#effDate1').val();
        object.accountType= $('#accountType').val();
//        console.log(object);
        var intType =$('#intType').val();
        if(intType=='01'){
         var rate =$('#rate').val();
         if(rate==''){
             Joyin.error(JoyinUtil.getMessage("利率不能为空"));  
             return ;
         }
      }
      else if(intType=='02'){
          var basicRate =$('#basicRate').val();
          if(basicRate==''){
              Joyin.error(JoyinUtil.getMessage("基准利率不能为空"));  
              return ;
          }
      }
   
    $("#accountType").change(function(){
        $("#accountId").val("");
    });
        Joyin.ajaxJson(basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate002/commitCheck', object, function(result) {
            
            
            
            if(object.cashId==''){
                console.log("new");
                object.operateType = "new";
                WorkFlow.doSubmitList(
                        basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate002/doCommit', 
                        object, 
                        "AccountCurrentRate002_btn_submit", 
                        "AccountCurrentRate002",
                        function() {
                          if (result.errorList.length == 0) {
                          var index = parent.layer.getFrameIndex(window.name);
                          parent.layer.close(index);
                      }
                        });
            }else{
                console.log("modify");
                object.operateType = "modify";
                WorkFlow.doSubmitList(
                        basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate002/doCommit', 
                        object, 
                        "AccountCurrentRate002_btn_submit", 
                        "AccountCurrentRate002",
                        function() {
                          if (result.errorList.length == 0) {
                          var index = parent.layer.getFrameIndex(window.name);
                          parent.layer.close(index);
                      }
                        });
            }
            
        });

        
        
//        Joyin.ajaxJson('doCommit', object, function(result) {
//            if (result.errorList.length == 0) {
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
//            }
//        });
    });
    
//    $("#intRateStructure").change(function(){
//        var intRateStructure =$('#intRateStructure').val();
//        var intType =$('#intType').val();
//        if(intRateStructure=='01'&&intType=='01'){
//            $("#rate").attr("disabled",false);
////            $("#basicRate").val("");
//            $("#basicRate").attr("disabled",true);
//        }
//        else if(intRateStructure=='01'&&intType=='02'){
//            $("#basicRate").attr("disabled",false);
////            $("#rate").val("");
//            $("#rate").attr("disabled",true);
//        }
//        else{
//            $("#basicRate").attr("disabled",false);
//            $("#rate").attr("disabled",false);
//        }
//    });
//    
//    $("#intType").change(function(){
//        var intRateStructure =$('#intRateStructure').val();
//        var intType =$('#intType').val();
//        if(intRateStructure=='01'&&intType=='01'){
//            $("#rate").attr("disabled",false);
////            $("#basicRate").val("");
//            $("#basicRate").attr("disabled",true);
//        }
//        else if(intRateStructure=='01'&&intType=='02'){
//            $("#basicRate").attr("disabled",false);
////            $("#rate").val("");
//            $("#rate").attr("disabled",true);
//        }
//        else{
//            $("#basicRate").attr("disabled",false);
//            $("#rate").attr("disabled",false);
//        }
//        
//    });
    
//    $("#accountType").change(function(){
//        var accountType =  $("#accountType").val();
//        $("#accountId").val("").trigger("change");
//        console.log(accountType);
//        Joyin.ajaxJson(basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate002/getAccNo',{accountType:accountType}, function(result) {
//            if(result.errorList.length==0){
////            console.log("result");
////           console.log(result);
////           $("#accountId").attr("result",result);
////        });
////        
//    });
//    
//    var accountType =  $("#accountType").val();
    $("#intType").change(function(){
        var intType =$('#intType').val();
        if(intType=='01'){
          $("#dRate").show();
          $("#rate").attr("disabled",false);
//          $("#rate").attr("required",true);
//          $("#basicRate").val("").trigger("change");
//          $("#basicRate").empty();
          $("#dBasicRate").hide();
          $("#basicRate").attr("disabled",true);
      }
      else if(intType=='02'){
          $("#dBasicRate").show();
          $("#basicRate").attr("disabled",false);
//          $("#rate").val("");
//          $("#basicRate").attr("required",true);
          $("#dRate").hide();
          $("#rate").attr("disabled",true);
      }
    });
    $("#accountType").change(function(){
        $("#accountId").val("");
    });
 
    
    

  //页面全部加载完毕后
 

     
    $(document).ready(function(){  
        // 初始化内容
        var intType =$('#intType').val();
//            var intType =document.getElementById("intType");
            if(intType=='01'){
//                var dBasicRate =document.getElementById("dBasicRate");
//                dBasicRate.style.display = "none";
                 $("#dBasicRate").hide();
            }
            else if(intType=='02'){
//                var dRate =document.getElementById("dRate");
//                dRate.style.display = "none";
                 $("#dRate").hide();
            }else{
                
                $("#dRate").hide();
            }
            
        
        
        
    });  
    
    
    
});



