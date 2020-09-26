$(function () {
  //当前是否包含流程
    var workflowIdNow = $("#workflowId").val();
    //当资产类型为活期类资产时，还本计划table不显示
    if(entityType=='02'){
        $("#hbjh").hide();
    }
    $("#type1").change(function(){
        var type1 = $('#type1').val();
            
        if(type1=='02'){
            $("#hbjh").hide(); 
        }else{
            $("#hbjh").show();  
        }
        
    });
    
    var status = $('#status').val();
    
    if(status=='Y'){
    
   
      //定义表格列数据
        var table1Cols = [{field:'effDate', title: '生效日', edit:true, editOption:{type: 'date'}},
                         {field:'yield', title: '利率%', edit:true,type:"money",pattern: '#.00',editOption:{type:"money",pattern: '#.00'}}
                         ];
        
        
        
        var table2Cols = [{field:'vdate', title: '计息开始日',edit:true, editOption:{type: 'date'},fixed:'right'},
                          {field:'mdate', title: '计息结束日', edit:true, editOption:{type: 'date'},fixed:'right'},
                          {field:'payDate', title: '理论支付日',edit:true, editOption:{type: 'date'},fixed:'right'},
                          {field:'intAmt', title: '应计利息(元)', edit:true,type:"money",pattern: '#.00', editOption:{type:"money",pattern: '#.00'},fixed:'right'}
        ];
        /**modify by dongjun on 20181201 start **/
        var table3Cols = [{field:'repayDate', title: '还本日', edit:true, editOption:{type: 'date'},fixed:'right'},
                          {field:'payDate', title: '支付日', edit:true, editOption:{type: 'date'},fixed:'right'},
                          {field:'prinAmt', title: '还本金额(元)',edit:true, type:"money",pattern: '#.00', editOption:{type:"money",pattern: '#.00'},fixed:'right'},
                          {field:'intAmt', title: '利息金额(元)', edit:true,type:"money",pattern: '#.00', editOption:{type:"money",pattern: '#.00'},fixed:'right'}
        ];
        /**modify by dongjun on 20181201 end **/
        
        //无流程信息，代表是新建页面，不是我的待办中点击弹出的页面
        if(workflowIdNow==null || workflowIdNow.length==0){
            
            table1Cols.push(
                        {field:'operation', fixed:'right',width : 120, title : '<i id="ValDespositAssetQuery002table1_btn_add" class="iconfont icon-found" style="color:green"></i>',
                           align : 'center',type : 'action', buttons : [  {text: '<i class="iconfont icon-submit"></i>', clas: 'check', title: '确认'},'delt' ]}      
            );
            table2Cols.push(
                        {field:'operation', fixed:'right',width : 120, title : '<i id="ValDespositAssetQuery002table2_btn_add" class="iconfont icon-found" style="color:green"></i>',
                           align : 'center',type : 'action', buttons : [ 'delt' ]}      
          );
          /**modify by dongjun on 20181201 start **/
            table3Cols.push(
                    {field:'operation', fixed:'right',width : 120, title : '<i id="ValDespositAssetQuery002table3_btn_add" class="iconfont icon-found" style="color:green"></i>',
                       align : 'center',type : 'action', buttons : [ 'delt' ]}      
            );
          /**modify by dongjun on 20181201 end **/
        }
            
        
    }
    
    
    else{
      //定义表格列数据
        var table1Cols = [{field:'effDate', title: '生效日', edit:false, editOption:{type: 'date'},fixed:'right'},
                         {field:'yield', title: '利率%',edit:false,type:"number",pattern: '#.00', editOption:{ },fixed:'right'}
                         ];
        
        
        
        var table2Cols = [{field:'vdate', title: '计息开始日',edit:false, editOption:{type: 'date'},fixed:'right'},
                          {field:'mdate', title: '计息结束日',edit:false, editOption:{type: 'date'},fixed:'right'},
                          {field:'payDate', title: '理论支付日',edit:false, editOption:{type: 'date'},fixed:'right'},
                          {field:'intAmt', title: '应计利息(元)', type:"money",pattern: '#.00',edit:false, editOption:{},fixed:'right'}
        ];
        /**modify by dongjun on 20181201 start **/
        var table3Cols = [{field:'repayDate', title: '还本日', edit:false, editOption:{type: 'date'},fixed:'right'},
                          {field:'payDate', title: '支付日', edit:false, editOption:{type: 'date'},fixed:'right'},
                          {field:'prinAmt', title: '还本金额(元)',edit:false, type:"money",pattern: '#.00', editOption:{type:"money",pattern: '#.00'},fixed:'right'},
                          {field:'intAmt', title: '利息金额(元)', edit:false,type:"money",pattern: '#.00', editOption:{type:"money",pattern: '#.00'},fixed:'right'}
        ];
        /**modify by dongjun on 20181201 end **/
    }
  
    
 
    
    /**
     * 定义数据表格
     */
    var table1=$("#dataTable1").joyintable({
        searchOnInit: true,
        checkbox: false,
        height: 300,
        rowno: true,
        loading: false,
        page: false,
        ajax: {
            url : basePath + 'valuation/assets/posit/ValDespositAssetQuery002/getTable1',
            type: 'POST',
            data: {workflowId: workflowIdNow,finprodId:$('#finprodId').val()}
        }, editCell: function(value, data, field, cell, column) {
          
        
        },
        cols: table1Cols,
        loadonce:true
    });
    
    
    /**
     * 定义数据表格
     */
    var table2=$("#dataTable2").joyintable({
        searchOnInit: true,
        checkbox: false,
        height: 300,
        rowno: true,
        loading: false,
        page: false,
        ajax: {
            url : basePath + 'valuation/assets/posit/ValDespositAssetQuery002/getTable2',
            type: 'POST',
            data: {workflowId: workflowIdNow,finprodId:$('#finprodId').val()}
        }, editCell: function(value, data, field, cell, column) {
          
        
        },
        cols: table2Cols,
        loadonce:true
    });
    
    /**modify by dongjun on 20181201 start **/
    /**
     * 定义数据表格
     */
    var table3=$("#dataTable3").joyintable({
        searchOnInit: true,
        checkbox: false,
        height: 300,
        rowno: true,
        loading: false,
        page: false,
        ajax: {
            url : basePath + 'valuation/assets/posit/ValDespositAssetQuery002/getTable3',
            type: 'POST',
            data: {workflowId: workflowIdNow,finprodId:$('#finprodId').val()}
        }, editCell: function(value, data, field, cell, column) {
        },
        cols: table3Cols,
        loadonce:true
    });
    
    /**
     * 点击添加按钮触发事件
     */
    table3.bind('click',"#ValDespositAssetQuery002table3_btn_add",function(){
            var object = $.serializeToObject('#submitForm');
            object.pageModel = pageModel;
        Joyin.ajaxJson('doAddtable1', object, function(result) {
            if (result.errorList.length == 0) {
                result.data.status = 'N';
                table3.addRow(result.data);
                table3.events();
            }
        });
    });
    /**
     * 绑定删除事件
     */
    table3.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table3.removeRow(data);
                layui.layer.alert("删除成功");
                table3.events();
            }
        });
    });
    /**modify by dongjun on 20181201 end **/
    
    /**
     * 点击添加按钮触发事件
     */
    table2.bind('click',"#ValDespositAssetQuery002table2_btn_add",function(){
        
//        var vdate = $('#vdate').val();
//        if(vdate==''){
//            layui.layer.alert("起息日不能为空");
//        }else{
            var object = $.serializeToObject('#submitForm');
//            object.table2 = table2.getAllRows();
            object.pageModel = pageModel;
//        Joyin.ajaxJson('doAddtable1', object, function(result){
//            if(result.errorList.length==0){
//                result.data.status = 'N';
//                table2.addRow(result.data);
//                table2.events();
//            
//            }
//        });
        Joyin.ajaxJson('doAddtable1', object, function(result) {
            if (result.errorList.length == 0) {
                result.data.status = 'N';
                table2.addRow(result.data);
                table2.events();
            }
        });
        
        
        
//        }
    });
    
    /**
     * 点击添加按钮触发事件
     */
    table1.bind('click',"#ValDespositAssetQuery002table1_btn_add",function(){
        
//        var vdate = $('#vdate').val();
//        if(vdate==''){
//            layui.layer.alert("起息日不能为空");
//        }else{
            var object = $.serializeToObject('#submitForm');
            object.pageModel = pageModel;
        Joyin.ajaxJson('doAddtable1', object, function(result){
            if(result.errorList.length==0){
                result.data.status = 'N';
                table1.addRow(result.data);
                table1.events();
            
            }
        });
//        }
    });
          
      
    
    $("#ValDespositAssetQuery002_btn_submit").click(function() {
        var object = $.serializeToObject('#submitForm');
        object.isEdit =  $("#isEdit").val();
        object.table1 = table1.getAllRows();
        object.table2 = table2.getAllRows();
        //如果类型为活期存款类型 还本计划表格清空
        var type1 = $('#type1').val();
        if(type1=='02'){
            table3.clear(function(){});
        }
        object.table3 = table3.getAllRows();//modfiy by dongjun on 20181201
        object.blnArea =  $("#blnArea").val();
        object.remark =  $("#remark").val();
        object.pageModel = pageModel;
//        Joyin.ajaxJson('doCommit', object, function(result) {
//            if (result.errorList.length == 0) {
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
//            }
//        });
  Joyin.ajaxJson(basePath + 'valuation/assets/posit/ValDespositAssetQuery002/commitCheck', object, function(result) {
            
            
            
           
              
                WorkFlow.doSubmitList(
                        basePath + 'valuation/assets/posit/ValDespositAssetQuery002/doCommit', 
                        object, 
                        "ValDespositAssetQuery002_btn_submit", 
                        "ValDespositAssetQuery002",
                        function() {
                          if (result.errorList.length == 0) {
                          var index = parent.layer.getFrameIndex(window.name);
                          parent.layer.close(index);
                      }
                        });
        
         
            
        });
        
        
        
        
        
    });
          
    /**
     * 绑定删除事件
     */
    table1.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table1.removeRow(data);
                layui.layer.alert("删除成功");
                table1.events();
            }
        });
        
    });
    /**
     * 绑定删除事件
     */
    table2.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table2.removeRow(data);
                layui.layer.alert("删除成功");
                table2.events();
            }
        });
        
    });
   
    

    $("#mdate").change(function(){
        
      var DateOne = $('#vdate').val();
      var DateTwo = $('#mdate').val();
      if(DateOne==null||DateOne==''){
          
      }else{
          $('#term').val(daysBetween(DateOne,DateTwo));
      }
     
    
    });
    
    $("#vdate").change(function(){
        
        var DateOne = $('#vdate').val();
      var DateTwo = $('#mdate').val();
      if(DateTwo==null||DateTwo==''){
          
      }else{
          $('#term').val(daysBetween(DateOne,DateTwo));
      }
    
    });
    
    
    function daysBetween(DateOne, DateTwo) {
        var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
        var OneDay = DateOne.substring(DateOne.length, DateOne.lastIndexOf('-') + 1);
        var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

        var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
        var TwoDay = DateTwo.substring(DateTwo.length, DateTwo.lastIndexOf('-') + 1);
        var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

        var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
        return Math.abs(cha);
    }
    
     
    
    /**
     * 
     */
    table1.bind('click', '.check', function(data, index) {
        var object = data;
        var vdate = $('#vdate').val();
        var table1data = table1.getAllRows();
        var  count=0;
        for(var i=0;i<table1data.length;i++){
            if(table1data[i].effDate==vdate){
                count++;
            }
        }
        if(count==1){
        if(object.effDate==null||object.effDate==''){
            layui.layer.alert("生效日为空");
        }else if(object.yield==null||object.yield=='')
        {
            layui.layer.alert("利率为空");
        }else if(object.effDate==vdate){
            $('#basicRate').val(object.yield);
        }
        }else if(count>1){
            layui.layer.alert("生效日存在多条值等于起息日");
        }
        
        
    });
    
  
    
    
    
    
    
    
    
	
});