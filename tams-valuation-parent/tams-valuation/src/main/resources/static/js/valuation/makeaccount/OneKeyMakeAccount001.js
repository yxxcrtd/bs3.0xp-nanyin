$(function () {
	var searchCheckLog = '';
    var statusData='';
    var statusDeal='';
    var statusBatch='';
    var status4='';
    var status5='';
    var status6='';
    var status7='';
    var status8='';
    var detailName='--';
    var bookSetDate='';
    var execTaskIdData;
    var execTaskIdDeal;
    var execTaskIdBatch;
    var execTaskId4;
    var execTaskId5;
    var execTaskId6;
    var execTaskId7;
    var execTaskId8;
    var excuteDate;
    var seqNo;
    var runstatus;
    var ischeck;
    var busiDate="";
    var startDate ="-";
  //轮询启动接口ID
    var interIdData;
    var interIdDeal;
    var interIdBatch;
    var interId4;
    var interId5;
    var interId6;
    var interId7;
    var mainId;
    var booksetIdArr="";
    //轮询开始
    var startInterval = function(){
//        //每5秒进行一次轮序
//        interIdData = setInterval(interfdataTable,5000);
//        interIdDeal = setInterval(interfdealTable,5000);
//        interIdBatch = setInterval(batchTable,5000);
//        mainId = setInterval(mainLog,5000);
        mainId=setInterval(mianTnterval,5000);
        
    };
    
    var mianTnterval = function (){
        
        interfdealTable();
        interfdataTable();
        batchTable();
        lab5();
        lab6();
        databackhaul();
        mainLog();
    }
  //轮询结束
  var stopIntervalmain = function(){
      clearInterval(mainId);
      gradeChange();
  }
  //估值报表生成、
  var getParams4 = function() {
      return {
      }
  }; 
//估值表对账
  var getParams5 = function() {
      return {
          busiDate:busiDate,
          booksetIdArr:booksetIdArr,
          type:"1"
      }
  }; 
  //余额表对账
  var getParams6 = function() {
      return {
          busiDate:busiDate,
          booksetIdArr:booksetIdArr,
          type:"0"
      }
  };  
//净值下发
  var getParams7 = function() {
      return {
      }
  };
//数据回传
  var getParams8 = function() {
      return {exDate : $.trim(startDate),
          status : status8,
          execTaskId : $.trim(execTaskId8)
          }
  };
  
  
  //估值报表生成、 
 var lab4 = function(){
      
  }
 //估值表对账
 var lab5 = function(){
     var table5=$("#valuation-checklog").joyintable({
         ajax: {
             url: 'checkAccountLog',
             type: 'POST',
             data: function(){
                 return getParams5();
             }
         },
         checkbox: false,
         searchOnInit: false,
         loadonce:true,
         page : false,
         cols: [
            {field:'finprodId', title: '账套代码', sort: true },
            {field:'finprodName', title: '账套名称', sort: true },
            {field:'interType', title: '文件类型',  sort: true,render: function (data, type, row, meta) {
                if(row.interType=="1"){
                    return "估值表";
                }else if(row.interType=="0"){
                    return "余额表";
                }
            }},
            {field:'status', title: '对账状态',  sort: true,render: function (data, type, row, meta) {
                if(status=="0"){
                    return "未对账";
                }else if(row.status=="1"){
                    return "对账中";
                }else if(row.status=="2"){
                    return "对账成功";
                }else if(row.status=="3"){
                    return "对账失败";
                }
            }},
            {field:'busiDate', title: '估值日期',  sort: true}
//            {field:'useingTime', title: '耗时',  sort: true},
//            {field:'startTime', title: '开始时间',  sort: true},
//            {field:'endTime', title: '结束时间',  sort: true}
         ]
         
     });
     
     if(!table5.table) {
         table5.load();
       }else {
           table5.reload();
       }
     
  
       
     
 }
 //余额表对账
 var lab6 = function(){
     var table6=$("#balance-checklog").joyintable({
         ajax: {
             url: 'checkAccountLog',
             type: 'POST',
             data: function(){
                 return getParams6();
             }
         },
         checkbox: false,
         searchOnInit: false,
         loadonce:true,
         page : false,
         cols: [
                {field:'finprodId', title: '账套代码', sort: true },
                {field:'finprodName', title: '账套名称', sort: true },
                {field:'interType', title: '文件类型',  sort: true,render: function (data, type, row, meta) {
                    if(row.interType=="1"){
                        return "估值表";
                    }else if(row.interType=="0"){
                        return "余额表";
                    }
                }},
                {field:'status', title: '对账状态',  sort: true,render: function (data, type, row, meta) {
                    if(status=="0"){
                        return "未对账";
                    }else if(row.status=="1"){
                        return "对账中";
                    }else if(row.status=="2"){
                        return "对账成功";
                    }else if(row.status=="3"){
                        return "对账失败";
                    }
                }},
                {field:'busiDate', title: '估值日期',  sort: true}
//                {field:'useingTime', title: '耗时',  sort: true},
//                {field:'startTime', title: '开始时间',  sort: true},
//                {field:'endTime', title: '结束时间',  sort: true}
             ]
             
         });
     
     if(!table6.table) {
         table6.load();
       }else {
           table6.reload();
       }
     
  
       
     
 }
 
 //净值下发
 var lab7 = function(){
     
 }
 
 
    
    /**
     * 批处理查询参数
     */
    var getPageParamsBatch = function() {
        return {
            booksetId:$.trim($('#booksetId').val()),
            startDate : $('#executeDate').val(),
            endDate : $('#executeDate').val(),
            taskStatus:statusBatch,
            execTaskIdBatch: $.trim(execTaskIdBatch)
        }
    };
    
    /**
     * 获取基础数据接口查询参数
     */
    var getSearchParamsData = function() {
        return {exDate : $.trim(excuteDate),
            status : statusData,
            execTaskId : $.trim(execTaskIdData)}
    };
    
    /**
     * 获取交易数据接口查询参数
     */
    var getSearchParamsDeal = function() {
        return {exDate : $.trim(excuteDate),
            status : statusDeal,
            execTaskId : $.trim(execTaskIdDeal)}
    };
    
    $('#typeDatatree').joyinMixTree({
        onTreeLoadSuccess: function() {
            $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(true);
        }
    });
    
    function DisabledNode(treeObj, node) {
        treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    };
    
    function CheckNodeById(pid){
        var zTree = $.fn.zTree.getZTreeObj("typeDatatree_tree");
        var node = zTree.getNodeByParam("id",pid );
        zTree.cancelSelectedNode();//先取消所有的选中状态
        zTree.selectNode(node);//将指定ID的节点选中
        zTree.expandNode(node, true, false);//将指定ID节点展开
        zTree.checkNode(node);//将制定ID勾选】
    }

 /**
  * 总进度
  */
    var mainLog =function(){
    
    var table=$("#log-total").joyintable({
        ajax: {
            url: 'findloglogtotal',
            type: 'POST',
            data: function() {
                return {seqNo:$.trim(seqNo)};
            }
        },
        checkbox: false,
        searchOnInit: false,
        loadonce:true,
        page : false,
        done:function(data){
            if(data.length>0){
                //如果结束时产品全部执行完毕,则取消轮询
                var flag = 1;
                var index = 0;
                for(var i=0; i<data.length;i++){
                    var status = data[i].status;
                    if(status==1||status==0){
                        flag = 0;
                        index++;
                    }
                }
                if(flag==1){
                    stopIntervalmain();
                    //$("#ValPublicDataImport001_btn_doInterBtn").attr("title", '').prop("disabled", false);
                    //setTimeout(close(),3000);

//                    $("#OneKeyMakeAccount001_btn_doInterBtn").removeClass("layui-btn-disabled");
//                    clickFlag=true;
//                    progress(1);
                }else{
//                    progress((data.length-index)/data.length);
                }
            }
        },
        
        
        cols: [
           {field:'modelCode', title: '任务名称', sort: true,render: function (data, type, row, meta) {
               if(row.modelCode=="1"){
                   return "公共数据导入";
               }else if(row.modelCode=="2"){
                   return "交易数据导入";
               }else if(row.modelCode=="3"){
                   return "登账批处理";
               }else if(row.modelCode=="4"){
                   return "估值表核对（管理人）";
               }else if(row.modelCode=="5"){
                   return "估值表核对（托管人）";
               }else if(row.modelCode=="6"){
                   return "余额表核对";
               }else if(row.modelCode=="7"){
                   return "净值确认下发";
               }else if(row.modelCode=="8"){
                   return "数据回传";
               }
           }},
           {field:'status', title: '执行状态',  sort: true,render: function (data, type, row, meta) {
               if(row.status=="0"){
                   return "未执行";
               }else if(row.status=="1"){
                   return "执行中";
               }else if(row.status=="2"){
                   return "执行成功";
               }else if(row.status=="3"){
                   return "执行失败";
               }
           }},
           {field:'status', title: '执行结果',  sort: true,render: function (data, type, row, meta) {
               if(status=="0"){
                   return "未执行";
               }else if(row.status=="1"){
                   return "执行中";
               }else if(row.status=="2"){
                   return "执行成功";
               }else if(row.status=="3"){
                   return "执行失败";
               }
           }},
           {field:'busiDate', title: '操作日期',  sort: true},
           {field:'useingTime', title: '耗时',  sort: true},
           {field:'startTime', title: '开始时间',  sort: true},
           {field:'endTime', title: '结束时间',  sort: true}
        ]
        
    });
    
    if(!table.table) {
    	table.load();
      }else {
    	  table.reload();
      }
    
    }
    
    /**
     * 控制tab页
     */
    var divshow=function(){
        if(YJZZ.indexOf("1")>=0){
        }else{
            $('#interf-data').hide();
        }
        if(YJZZ.indexOf("2")>=0){
        }else{
            $('#interf-deal').hide();
        }
        if(YJZZ.indexOf("3")>=0){
        }else{
            $('#batch-task').hide();
        }
        if(YJZZ.indexOf("4")>=0){
        }else{
            $('#valuation-build').hide();
        }
        if(YJZZ.indexOf("5")>=0){
        }else{
            $('#valuation-check').hide();
        }
        if(YJZZ.indexOf("6")>=0){
        }else{
            $('#balance-check').hide();
        }
        if(YJZZ.indexOf("7")>=0){
        }else{
            $('#log-confirm').hide();
        }
        
        if(YJZZ.indexOf("8")>=0){
        }else{
            $('#data-backhaul').hide();
        }
        
        
    }

    
    /**
     * 批处理
     * 日志页签
     */
    var batchTable = function(obj){
        $("#layui-tab-content-batch-task").css('display','block');
//        $("#layui-tab-check").css('display','none');
        var id = $(obj).attr("id");
        if(!id){
            id='log-title-batch-task';
            statusBatch='';
        }if(id=='log-title-batch-task'){
            statusBatch = '';
        }else if(id=='success-title-batch-task'){
            statusBatch = 'END';
        }else if(id=='error-title-batch-task'){
            statusBatch = 'EXP';
        }else if(id=='warning-title-batch-task'){
            statusBatch = 'UNR';
        }else if(id=='eWarning-title-batch-task'){
            statusBatch = 'RUN';
        }else{
            id='log-title-batch-task';
            statusBatch='';
        }
        var table = id.replace('title','table');
        
        var docH= $(document).height(); 
        
        var tabTableBatch=$('#'+table).joyintable({
            ajax: {
                url: 'findLogPage',
                type: 'POST',
                data:function(){
                    return getPageParamsBatch();
                }
            },
//            done:function(data){
//                if(data.length>0){
//                    //如果结束时产品全部执行完毕,则取消轮询
//                    var flag = 1;
//                    var index = 0;
//                    for(var i=0; i<data.length;i++){
//                        var status = data[i].taskStatusCode;
//                        if(status=='END'||status=='EXP'){
//                            flag = 0;
//                            index++;
//                        }
//                    }
//                    if(index>=batchTaskNums){
////                        stopIntervalBatch();
////                        progress(1);
////                        setTimeout(close(),3000);
//                    }else{
////                        progress(index/batchTaskNums);
//                    }
//                }
//            },
            height:docH-200,
            checkbox: false,
            rowno:true,
            cols:
                [ 
                  {field: 'taskId', width: 150, title: '业务日期',visible:false}
                 ,{field: 'batchDate', width: 130, title: '业务日期',sort: true,
                       render: function (data, type, row, meta) {
                           return data.substring(0,10);
                       }
                    }
//                 , {field: 'detailName', width: 200, title: '任务名称',sort: true}
                 , {field: 'taskName', width: 180, title: '批处理类型',visible:false}
                 , {field: 'taskName', width: 220, title: '任务明细',sort: true}
                 , {field: 'taskStatus', width: 100, title: '执行状态',sortField:'taskStatusCode',sort: true,
                     render: function (data, type, row, meta) {
                         if(data=='正常结束'){
                             return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                         }else if(data=='异常结束'){
                             return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                         }else if(data=='执行中'){
                             return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#13a7edd1;color:#fff">'+'执行中'+'</span>';
                         }else if(data=='未执行'){
                             return '<span class="layui-badge-rim" style="width:55px;height:20px;">'+'未执行'+'</span>';
                         }
                     }
                 }
                 
                 , {field: 'startDateFmt',sorfiled:'batchStartTime', width: 130, title: '执行日期',sort: true}
                 , {field: 'useingTime', width: 100, title: '耗时(s)',sort: true}
                 , {field: 'startTimeFmt',sorfiled:'batchStartTime', width: 150, title: '开始时间',sort: true}
                 , {field: 'endTimeFmt',sorfiled:'batchEndTime', width: 150, title: '结束时间',sort: true}
//                 , {field:'operation', title: '操作',sort: true, 
//                     render: function (data, type, row, meta) {
//                         return '<a href="javascript:void(0);" onclick="openShow()" data-taskId="' + row.taskId + '">详细</a>';
//                     } 
//                 }
              ],
            loadonce:true,
            page:false
        });
        if(!tabTableBatch.table) {
            tabTableBatch.load();
          }else {
              tabTableBatch.reload();
          }
    };
    
    
    /**
     * 交易接口
     * 日志页签
     */
    var interfdealTable = function(obj){
        
        var id = $(obj).attr("id");
        if(!id){
            id='log-title-interf-deal';
        }
        //进入前status初始化
        statusDeal = "";
        
        if(id=='success-title-interf-deal'){
            statusDeal = '02';
        }else if(id=='error-title-interf-deal'){
            statusDeal = '03';
        }else if(id=='warning-title-interf-deal'){
            statusDeal = '09';
        }else if(id=='eWarning-title-interf-deal'){
            statusDeal = '04';
        }else{
            id='log-title-interf-deal';
        }
        //根据title获取table
        var table = id.replace('title','table');  
        
        var docH= $(document).height(); 
        
        var tabTableDeal = $('#'+table).joyintable({
            ajax: {
                url: basePath +'valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport001/findLogPage',
                type: 'POST',
                data : function(){
                    return getSearchParamsDeal();
                }
            },
//            done:function(data){
//                if(data.length>0){
//                    //如果结束时产品全部执行完毕,则取消轮询
//                    var flag = 1;
//                    var index = 0;
//                    for(var i=0; i<data.length;i++){
//                        var status = data[i].status;
//                        if(status==1||status==0){
//                            flag = 0;
//                            index++;
//                        }
//                    }
//                    if(flag==1){
////                        stopIntervalDeal();
//                        //$("#ValTransactionDataImport001_btn_doInterBtn").attr("title", '').prop("disabled", false);
//                        //setTimeout(close(),3000);
//
////                        $("#ValTransactionDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
////                        clickFlag=true;
////                        progress(1);
//                    }else{
////                        progress((data.length-index)/data.length);
//                    }
//                }
//            },
            dblclick:function(table, data) {
                //跳转详情界面
                if(data.status=='02'||data.status=='09'){
                    Joyin.dialog(JoyinUtil.getLabel("查看导入数据："+data.taskCname),
                            JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport002/init"),
                            "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno},
                            function(){
                                //table.reload();
                                });
                }
            },
            height:docH-200,
            checkbox: false,
            cellMinWidth : 100,
            rowno:true,
            cols:[ {field:'seqno', title: 'seqno', visible:false},
                   {field:'busiDate', title: '业务日期', width:120, sort: true},
                   {field:'taskCname', title: '任务名称',width:160, sort: true},
                   {field:'status', title: '执行状态',width:105,
                         render: function (data, type, row, meta) {
                             if(data=='02'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                             }else if(data=='03'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                             }else if(data=='01'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#13a7edd1;color:#fff">'+'执行中'+'</span>';
                             }else if(data=='00'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;">'+'未执行'+'</span>';
                             }else if(data=='04'){
                                 return '<span class="layui-badge-rim" style="width:65px;height:20px;">'+'终止执行'+'</span>';
                             }else if(data=='09'){
                                 return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
                             }
                         }},
                   {field:'detailAction', title: '执行结果',width:300, sort: true},
                   {field:'startDateFmt',sorfiled:"startTime", title: '操作日期',width:120, sort: true},
                   {field:'lastTime', title: '耗时(s)',width:100, sort: true},
                   {field:'startTimeFmt',sorfiled:"startTime", title: '开始时间',width:120, sort: true},
                   {field:'endTimeFmt',sorfiled:"endTime",title: '结束时间',width:120, sort: true}]
            ,loadonce:true,
            page:false
        });
        //tabTable.load();
        if(!tabTableDeal.table) {
            tabTableDeal.load();

          }else {
              tabTableDeal.reload();
          }
    };
    
    
    /**
     * 基础信息接口
     * 日志页签
     */
    var interfdataTable = function(obj){
        
        var id = $(obj).attr("id");
        if(!id){
            id='log-title-interf-data';
        }
        //进入前status初始化
        statusData = "";
        
        if(id=='success-title-interf-data'){
            statusData = '02';
        }else if(id=='error-title-interf-data'){
            statusData = '03';
        }else if(id=='warning-title-interf-data'){
            statusData = '09';
        }else if(id=='eWarning-title-interf-data'){
            statusData = '04';
        }else{
            id='log-title-interf-data';
        }
        //根据title获取table
        var table = id.replace('title','table');        
        
        var docH= $(document).height(); 

        
        var tabTableData = $('#'+table).joyintable({
            ajax: {
                url: basePath +'valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport001/findLogPage',
                type: 'POST',
                data : function(){
                    return getSearchParamsData();
                }
            },
//            done:function(data){
//                if(data.length>0){
//                    //如果结束时产品全部执行完毕,则取消轮询
//                    var flag = 1;
//                    var index = 0;
//                    for(var i=0; i<data.length;i++){
//                        var status = data[i].status;
//                        if(status==1||status==0){
//                            flag = 0;
//                            index++;
//                        }
//                    }
//                    if(flag==1){
////                        stopIntervalData();
//                        //$("#ValPublicDataImport001_btn_doInterBtn").attr("title", '').prop("disabled", false);
//                        //setTimeout(close(),3000);
//
////                        $("#OneKeyMakeAccount001_btn_doInterBtn").removeClass("layui-btn-disabled");
////                        clickFlag=true;
////                        progress(1);
//                    }else{
////                        progress((data.length-index)/data.length);
//                    }
//                }
//            },
            height:docH-200,
            dblclick:function(table, data) {
                //跳转详情界面
                if(data.status=='02'||data.status=='09'){
                    Joyin.dialog(JoyinUtil.getLabel("查看导入数据："+data.taskCname),
                            JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport002/init"),
                            "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno},
                            null);
                }
                
            },
            checkbox: false,
            cellMinWidth : 100,
            rowno:true,
            cols:[ {field:'seqno', title: 'seqno', visible:false},
                   {field:'busiDate', title: '业务日期', width:120, sort: true},
                   {field:'taskCname', title: '任务名称',width:160, sort: true},
                   {field:'status', title: '执行状态',width:105,
                         render: function (data, type, row, meta) {
                             if(data=='02'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                             }else if(data=='03'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                             }else if(data=='01'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#13a7edd1;color:#fff">'+'执行中'+'</span>';
                             }else if(data=='00'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;">'+'未执行'+'</span>';
                             }else if(data=='04'){
                                 return '<span class="layui-badge-rim" style="width:65px;height:20px;">'+'终止执行'+'</span>';
                             }else if(data=='09'){
                                 return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
                             }
                         }},
                   {field:'detailAction', title: '执行结果',width:300, sort: true},
                   {field:'startDateFmt',sorfiled:"startTime", title: '操作日期',width:120, sort: true},
                   {field:'lastTime', title: '耗时(s)',width:100, sort: true},
                   {field:'startTimeFmt',sorfiled:"startTime", title: '开始时间',width:120, sort: true},
                   {field:'endTimeFmt',sorfiled:"endTime",title: '结束时间',width:120, sort: true}]
            ,loadonce:true,
            page:false
        });
        //tabTable.load();
        if(!tabTableData.table) {
            tabTableData.load();
          }else {
              tabTableData.reload();
          }
    };
    
    
    /**
     * 数据回传
     */
    var databackhaul = function(obj){
        
        var id = $(obj).attr("id");
        if(!id){
            id='log-title-data-backhaul';
        }
        //进入前status初始化
        status8 = "";
        
        if(id=='success-title-data-backhaul'){
            status8 = '02';
        }else if(id=='error-title-data-backhaul'){
            status8 = '03';
        }else if(id=='warning-title-data-backhaul'){
            status8 = '09';
        }else if(id=='eWarning-title-data-backhaul'){
            status8 = '04';
        }else{
            id='log-title-data-backhaul';
        }
        //根据title获取table
        var table = id.replace('title','table');        
        
        var docH= $(document).height(); 

        
        var tabDataBackhaul = $('#'+table).joyintable({
            ajax: {
                url: 'findDataBackLog',
                type: 'POST',
                data : function(){
                    return getParams8();
                }
            },
//            done:function(data){
//                if(data.length>0){
//                    //如果结束时产品全部执行完毕,则取消轮询
//                    var flag = 1;
//                    var index = 0;
//                    for(var i=0; i<data.length;i++){
//                        var status = data[i].status;
//                        if(status==1||status==0){
//                            flag = 0;
//                            index++;
//                        }
//                    }
//                    if(flag==1){
//                        stopInterval();
//                        //$("#ValPublicDataImport001_btn_doInterBtn").attr("title", '').prop("disabled", false);
//                        //setTimeout(close(),3000);
//
//                        $("#ValPublicDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
//                        clickFlag=true;
////                        progress(1);
//                    }else{
//                        progress((data.length-index)/data.length);
//                    }
//                }
//            },
            height:docH-200,
            dblclick:function(table, data) {
                //跳转详情界面
                if(data.status=='02'||data.status=='09'){
                    Joyin.dialog(JoyinUtil.getLabel("查看导入数据："+data.taskCname),
                            JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport002/init"),
                            "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno},
                            null);
                }
                
            },
            checkbox: false,
            cellMinWidth : 100,
            rowno:true,
            cols:[ {field:'seqno', title: 'seqno', visible:false},
                   {field:'busiDate', title: '业务日期', width:120, sort: true},
                   {field:'taskCname', title: '任务名称',width:160, sort: true},
                   {field:'status', title: '执行状态',width:105,
                         render: function (data, type, row, meta) {
                             if(data=='02'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                             }else if(data=='03'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                             }else if(data=='01'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#13a7edd1;color:#fff">'+'执行中'+'</span>';
                             }else if(data=='00'){
                                 return '<span class="layui-badge-rim" style="width:55px;height:20px;">'+'未执行'+'</span>';
                             }else if(data=='04'){
                                 return '<span class="layui-badge-rim" style="width:65px;height:20px;">'+'终止执行'+'</span>';
                             }else if(data=='09'){
                                 return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
                             }
                         }},
                   {field:'detailAction', title: '执行结果',width:300, sort: true},
                   {field:'startDateFmt',sorfiled:"startTime", title: '操作日期',width:120, sort: true},
                   {field:'lastTime', title: '耗时(s)',width:100, sort: true},
                   {field:'startTimeFmt',sorfiled:"startTime", title: '开始时间',width:120, sort: true},
                   {field:'endTimeFmt',sorfiled:"endTime",title: '结束时间',width:120, sort: true}]
            ,loadonce:true,
            page:false
        });
        //tabTable.load();
        if(!tabDataBackhaul.table) {
            tabDataBackhaul.load();
          }else {
              tabDataBackhaul.reload();
          }
    };
    
    
    
    
    
    
    
    
    var doInter = function (){
        //公共数据接口 交易数据接口校验  启动
//        var startDate = $('#executeDate').val();
//        var endDate = $('#executeDate').val();
//      
//        if(startDate==''||endDate==''){
//            layer.alert('请选择时间!');
//            return ;
//        }
//        $.ajax({
//            url : "checkExecute",
//            type : "POST",
//            data : '',
//            success: function(msg){
//                if(msg>0){
//                    layer.alert('当前正在进行登帐，请稍后再试!!');
//                    return;
//                }else{
////                    Joyin.ajax('runAs', 
////                            {   
////                                datas : datas,
////                                startDate:startDate,
////                                endDate:endDate
////                            }, 
////                         function(data) {
////                            var result = JSON.parse(data);
////                            if(result.isCheck==1){
////                                //再次执行启动前停止上一次轮询
////                                stopInterval();
////                                //成功调用轮询
////                                excuteDate = result.createDate;
////                                execTaskId = result.execTaskId;
////                              //调用后一秒再查询接口,快速展示界面数据
////                                startInterval();
////                                progress(0);
////                                $("#ValPublicDataImport001_btn_doInterBtn").addClass("layui-btn-disabled");
////                                //$("#ValPublicDataImport001_btn_doInterBtn").prop("disabled",true).trigger("change");
////                                clickFlag=false;
////                                //doprocess();
////                            }else if(result.isCheck==0){
////                                layui.layer.alert("任务执行中，请稍后再试!");
////                            }else{
////                                layui.layer.alert("发送启动请求失败");
////
////                            }
////                        });
//                }
//            },
//            failure : function() {
//            }
//        });
//        
//        
//        

        var startDate = $('#executeDate').val();
        var endDate = $('#executeDate').val();
        var booksetId=$.trim($('#booksetId').val());
        if(booksetId==''){
            layer.alert('请选择账套！');
            return ;
        }
        if(startDate==''||endDate==''){
            layer.alert('请选择时间!');
            return ;
        }
//        var treeObj=$.fn.zTree.getZTreeObj("typeDatatree_tree");
        //执行全部任务，不管有没有选中，都要执行(false)是未选中
//        var nodes=treeObj.getCheckedNodes(false);
//        var datas = "100";
//        for(var i=0;i<nodes.length;i++){
//            if(nodes[i].id!='00'){
//                datas+=nodes[i].id + ",";
//            }
//        }
//        nodes='';
//        //执行全部任务，不管有没有选中，都要执行(true)是已选中
//        nodes=treeObj.getCheckedNodes(true);
//        for(var i=0;i<nodes.length;i++){
//            if(nodes[i].id!='00'){
//                datas+=nodes[i].id + ",";
//            }
//        }
        Joyin.ajaxJson(basePath + 'valuation/makeaccount/OneKeyMakeAccount001/check', null, function(result) {
            
          executeBatch(null,booksetId,startDate,endDate);
            
            
        });
    };
    
    
    
    var executeBatch = function(selectedBatchGroup, selectedProductGroup, startDate, endDate) {
        var date= new Date();
        var detailName=curUser.userName+'-'+startDate+'-'+date.format('yyyy-MM-dd HH:mm:ss');
        //Joyin.prompt("请为此次执行命名，以便在日志中查看", 0, function(result) {
            Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001', JoyinUtil
                    .getLabel('SysCommon.execute')), function() {
                var batchGroupType = '1';
                var prodGroupType = $("#booksets").val();
                Joyin.ajax('docomplete', {
                    selectedBatchGroup : selectedBatchGroup,
                    selectedProductGroup : selectedProductGroup,
                    startDate : startDate,
                    endDate : endDate,
                    batchGroupType : batchGroupType,
                    prodGroupType : prodGroupType,
                    detailName : detailName
                }, function(data) {
//                    $("#setting_btn_save").attr("title", '5秒后才能再次执行').prop("disabled", true);
//                    setTimeout(function() {
//                        startInterval();
//                        $("#setting_btn_save").attr("title", '').prop("disabled", false);
//                    },5000)
                    
                     var result = JSON.parse(data);
                     
                     if(result.check==1){
                     execTaskIdData= result.execTaskIdData;
                     execTaskIdDeal= result.execTaskIdDeal;
                     execTaskIdBatch= result.execTaskIdBatch;
                     execTaskId4= result.execTaskId4;
                     execTaskId5= result.execTaskId5;
                     execTaskId6= result.execTaskId6;
                     execTaskId7= result.execTaskId7;
                     execTaskId7= result.execTaskId7;
                     execTaskId8= result.execTaskId8;
                     excuteDate= result.excuteDate;
                     seqNo=result.seqNo;
                     runstatus= result.runstatus;
                     booksetIdArr= result.booksetIdArr;
                     busiDate= result.busiDate;
                     startDate= result.startDate;
                     startInterval();
                     ischeck=result.check;
                     }else if(result.check==0){
                         layui.layer.alert("任务执行中，请稍后再试!");
                     }else{
                         layui.layer.alert("发送启动请求失败");

                     }
                     
                    
                });
            });
        //}, detailName)

    }
    
    var gradeChange=function(){
        var booksetId=$.trim($('#booksetId').val());
        if(booksetId==''||booksetId==null){
            return;
        }else{
            $.ajax({
                url : basePath+'valuation/dailyoperation/dailyaccountmake/valaccounthandle/ValAccountHandle001/checkExecute',
                type : "POST",
                data : {booksetId:booksetId, prodGroupType:$("#booksets").val()},
                success: function(msg){
                    if(msg>0){
                        $('#booksetId').val('').trigger('change');
                        layer.alert('选择的账套存在正在进行登帐，请稍后再试!!');
                        return;
                    }else{
                        $.ajax({
                            url : basePath+'valuation/dailyoperation/dailyaccountmake/valaccounthandle/ValAccountHandle001/checkBokkSetDate',
                            type : "POST",
                            data : {booksetId:booksetId, prodGroupType:$("#booksets").val()},
                            success: function(msg){
                                if(msg=='-1'){
                                    $('#booksetId').val('').trigger('change');
                                    layer.alert('所选账套的会计日期不一致，请分开执行!!');
                                }else{
                                    $('#executeDate').val(msg);
                                    bookSetDate=msg;
                                }
                            },
                            failure : function() {
                            }
                        });
                    }
                },
                failure : function() {
                }
            });
        }
    }
    var bookSetIdChange=function(){
        var booksetId=$.trim($('#booksetId').val());
        if(booksetId==''||booksetId==null){
            return;
        }else{
            $.ajax({
                url : basePath+'valuation/dailyoperation/dailyaccountmake/valaccounthandle/ValAccountHandle001/checkExecute',
                type : "POST",
                data : {booksetId:booksetId,prodGroupType:$("#booksets").val()},
                success: function(msg){
                    if(msg>0){
                        $('#booksetId').val('').trigger('change');
                        layer.alert('选择的账套存在正在进行登帐，请稍后再试!!');
                        return;
                    }else{
                    }
                },
                failure : function() {
                }
            });
        }
    }
    var dateChange = function(){
        var date = $('#executeDate').val();
        var nowDate = new Date(Date.parse(sysDate));
        var chooseDate = new Date(Date.parse(date));
        if(new Date(date)<new Date(bookSetDate)){
            layer.alert('选择日期小于账套当前日期'+bookSetDate+'，若需要对历史日期重新登账，请先对账套日期进行切换，再进行一键做账!');
            $('#executeDate').val(bookSetDate);
            return ;
        }else if(new Date(date)>new Date(bookSetDate)){
            layer.alert('选择日期必须等于账套当前日期'+bookSetDate);
            $('#executeDate').val(bookSetDate);
            return;
        }
//        if(nowDate>chooseDate){
//            layui.layer.alert("选择日期小于平台日，请判断是否影响已生成账务信息，若影响需要调整账套会计日期，重新进行登账批处理");
//        }
    }
    var bokCheck = function(){
//      Joyin.confirm(JoyinUtil.getMessage('COMMON_I_0009', JoyinUtil
//              .getLabel('SysCommon.execute')), function(){
//          
//       });
        var endDate = $('#executeDate').val();
        var booksetId=$.trim($('#booksetId').val());
        Joyin.ajax(basePath+"valuation/dailyoperation/dailyaccountmake/valaccounthandle/ValAccountHandle001/geyCheckSysValue",null,function(res){
            //如果系统参数值为off
            if(res==0){
                //原先批处理
                bookSetIdChange();
                dateChange();
                doInter();
            }else{
                Joyin.ajax(basePath+"valuation/dailyoperation/dailyaccountmake/valaccounthandle/ValAccountHandle001/isCheckBefore",{booksetId:$.trim($('#booksetId').val()),endDate:$('#executeDate').val()},function(msg){
                    if(msg==0){
                        bookSetIdChange();
                        dateChange();
                        doInter();
                    }else{
                        Joyin.confirm(JoyinUtil.getMessage('COMMON_I_0009', JoyinUtil
                                .getLabel('SysCommon.execute')), function(){
                            bookSetIdChange();
                            dateChange();
                            doInter();
                        });
                    }
                    
                });
                
            }
            
        });
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  

    /**
     * 画面加载完成
     */
    $(function() {
    	$("#batchBooksetType").change(function() {
    		$("#booksetId").val('');
    		var batchBooksetType = $("#batchBooksetType").val();
    		$("#booksets").val(batchBooksetType);
    		if ('0' == batchBooksetType) {
    			$("#dynamicName").html("账套组");
    		} else {
    			$("#dynamicName").html("账套");
    		}
    	});
    	//根据数据字典中的值来显示tab页
    	divshow();
    	// 绑定查询按钮事件
        $('#OneKeyMakeAccount001_btn_doInterBtn').click(function() {
            bokCheck();
            //bookSetIdChange();
           // dateChange();
            //doInter();
        });
        //日期控件变化校验
        $('#executeDate').change(function(){
            dateChange();
        });
        //账套变化校验
        $('#booksetId').change(function(){
            gradeChange();
        });
        //tab页点击
    	   $('#log-title-interf-data').click(function(){
   
    	       interfdataTable(this);
           });
    	   $('#success-title-interf-data').click(function(){
   
    	       interfdataTable(this);
           });
    	   $('#error-title-interf-data').click(function(){
  
    	       interfdataTable(this);
           });
    	   $('#warning-title-interf-data').click(function(){
    	
    	       interfdataTable(this);
           });
    	   $('#eWarning-title-interf-data').click(function(){
    	
    	       interfdataTable(this);
           });
    	   
    	   $('#log-title-batch-task').click(function(){
    	
    	       batchTable(this);
           });
           $('#success-title-batch-task').click(function(){
       
               batchTable(this);
           });
           $('#error-title-batch-task').click(function(){
     
               batchTable(this);
           });
           $('#warning-title-batch-task').click(function(){
        
               batchTable(this);
           });
           $('#eWarning-title-batch-task').click(function(){
     
               batchTable(this);
           });
    	   
           $('#log-title-interf-deal').click(function(){
     
               interfdealTable(this);
           });
           $('#success-title-interf-deal').click(function(){
   
               interfdealTable(this);
           });
           $('#error-title-interf-deal').click(function(){
        
               interfdealTable(this);
           });
           $('#warning-title-interf-deal').click(function(){
        	
               interfdealTable(this);
           });
           $('#eWarning-title-interf-deal').click(function(){
        
               interfdealTable(this);
           });
           
           $('#interf-deal').click(function(){
               interfdealTable(this);
           });
           $('#batch-task').click(function(){
        		   
        		   batchTable(this);
           });
           $('#interf-data').click(function(){
               interfdataTable(this);
           });
    	 $('#valuation-check').click(function(){
    	     lab5();
       });
    	 $('#balance-check').click(function(){
    	     lab6();
    });
    	 $('#data-backhaul').click(function(){
    	     databackhaul();
    });
    	 
    	 $('#log-title-data-backhaul').click(function(){
    	     
    	     databackhaul(this);
         });
         $('#success-title-data-backhaul').click(function(){
 
             databackhaul(this);
         });
         $('#error-title-data-backhaul').click(function(){

             databackhaul(this);
         });
         $('#warning-title-data-backhaul').click(function(){
      
             databackhaul(this);
         });
         $('#eWarning-title-data-backhaul').click(function(){
      
             databackhaul(this);
         });
    	 
    	 
    	 
        var tree=$("#typeDatatree");
        var docH= $(document).height(); 
        var tabHeadUlH=100;
        tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");  
        tree.children('div').eq(1).css("overflow-x", "hidden");     
        
//        table.load();
        mainLog();
    });

});

function searchByBatchName() {
    var batchName=$('#batchName').val();
    $('#typeDatatree').reloadTree({batchName:batchName});
}
