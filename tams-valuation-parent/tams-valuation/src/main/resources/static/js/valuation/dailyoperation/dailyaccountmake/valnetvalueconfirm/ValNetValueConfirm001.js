$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            valDateBegin: $('#valDate').startDate(),
            valDateEnd: $('#valDate').endDate(),
            profitType: $.trim($('#profitType').val()),     
            finprodId: $.trim($('#finprodId').val()), 
            checkStatus:$.trim($('#checkStatus').val()),   
            checkResult: $.trim($('#checkResult').val()),   
            confirmStatus:$.trim($('#confirmStatus').val()),   
            noticeStatus:$.trim($('#noticeStatus').val())
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		checkbox: true,
		cellMinWidth : 100,
		dblclick:function(data,row) {
		    if(data.valBalFlag=='02'){
		        // 绑定双击事件，
		        Joyin.dialog(JoyinUtil.getLabel("余额表查询"),
		                JoyinUtil.getRealPath("/valuation/accounadjustmanage/bokbalancestatementquery/BokBalanceStatementQuery001/init"),
		                "100%", "100%", {pageModel:PAGE_MODEL_EDIT,finprodId:row.finprodId,
                                            valDate:row.valDate,
                                            valBalFlag:row.valBalFlag,
                                            isInit:false
                                            },
		                function(){
		                });  
		    }else{
	            // 绑定双击事件，
                Joyin.dialog(JoyinUtil.getLabel("估值表查询"),
                        JoyinUtil.getRealPath("/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/init"),
                        "100%", "100%", {pageModel:PAGE_MODEL_EDIT,finprodId:row.finprodId,
                                        valDate:row.valDate,
                                        valBalFlag:row.valBalFlag,
                                        isInit:false
                                        },
                        function(){
                        });  
		    }
        },
		cols: [ 
		        
		        {field:'confirmStatusCode', title: '确认状态',width:120,sort: true,visible:false},
		        {field:'confirmStatus', title: '确认状态',width:120,sort: true,fixed : 'left'},
		        {field:'noticeStatusCode', title: '下发状态',width:120,sort: true,fixed : 'left',visible:false},
                {field:'noticeStatus', title: '下发状态',width:120,sort: true,fixed : 'left'},
                {field:'checkStatusCode', title: '对账状态',width:150, sort: true,fixed : 'left',align: 'center', visible:false},
		        {field:'checkStatus', title: '对账状态',width:150, sort: true,align: 'center', visible:false,
		           render: function (data, type, row, meta) {
		                if(row.checkStatusCode=='1'){
		                    return '<span class="layui-badge-rim layui-bg-blue">'+row.checkStatus+'</span>';
		                }else{
		                    return '<span class="layui-badge-rim">' + row.checkStatus + '</span>';
		                }
	                }
		       },
		       {field:'checkResultCode', title: '对账结果',width:150, sort: true,visible:false},
		       {field:'checkResult', title: '对账结果',width:150, sort: true,fixed : 'left',visible:false,
		           render: function (data, type, row, meta) {
		               if(row.checkResultCode=='1'){
                           var name=row.checkResultCode=='1'?'对账一致':'对账不一致';
                           return '<a href="javascript:void(0);" class="batchName" data-finprodId="' + row.finprodId + '">'+name+'</a>';
		               }else{
		                   return '--';
		               }
                   }   
		        },
    		   {field:'finprodId', title: '产品代码',width:150, sort: true,fixed : 'left'},
    		   {field:'finprodAbbr', title: '产品简称',width:150, sort: true},
    		   {field:'profitType', title: '产品类型',sortField:'profitTypeCode',width:150, sort: true},
    		   {field:'valBalFlagCode', title: '估值余额标志',width:150, sort: true,visible:false},
               {field:'valBalFlag', title: '估值余额标志',width:150, sort: true,visible:false},
    		   {field:'valDate', title: '估值日期',width:120, sort: true},
               {field:'capiTal', title: '理财份额',width:150, sort: true,type:'money',unit: 10000},
                {field:'prdTotAssetVal', title: '产品总资产',width:150, sort: true,type:'money',unit: 10000},//modfiy by dongjun on 20190429 净值下发增加字段
                {field:'prdAssetVal', title: '产品净资产',width:150, sort: true,type:'money',unit: 10000},//modfiy by dongjun on 20190429 净值下发增加字段
    		   {field:'assetValue', title: '产品总净值/本日收益',width:180, sort: true,type:'money', unit: 10000},
               {field:'netUnitValue', title: '单位净值/万份收益/收益率',width:210,sort: true,align:"right", unit: 10000},
               {field:'totalNetNalue', title: '累计净值',width:120,sort: true,align:"right", unit: 10000},
               {field:'tdyYield', title: '当日年化收益率%',width:160,sort: true,align:"right", unit: 10000},
               {field:'sevenDayRate', title: '七日年化收益率%',width:160,sort: true,align:"right", unit: 10000},
               {field:'payProfit', title: '应付利润',width:120,sort: true,type:'money',unit: 10000},
               {field:'unitvaluedec', title: '单位净值小数位', visible:false},
               {field:'unitvaluemode', title: '单位净值位数处理方式', visible:false},
               {field:'operation', title: '操作',fixed : 'right',
            	   //update by hechuan 2019-10-09 start
            	   type: 'action',
//            	   buttons: [{
//            		   text: '',
//                       clas: 'iconfont icon-upload',
//                       title: '附件上传下载'
//                   }, {
//	                   text: '',
//	                   clas: 'iconfont icon-edit',
//	                   title: '净值修改'
//                   }]
            	   render:function(data,type,row,meta){
   					var html= "";
   					if(purviewElements.indexOf('_manual')!=-1){
   	                	  html = html+"<i class='iconfont icon-upload manual' title='附件上传下载'></i> &nbsp;&nbsp;&nbsp;&nbsp;";
   	                  }
   	                  if(purviewElements.indexOf('_edit')!=-1){
   	                	  html = html+"<i class='iconfont icon-edit edit' title = '净值修改'></i>";
   	                  }
   	                  return html;
   					} 
               }],
	});
	
	//add by hechuan 2019-10-9 start
	/**
	 * 绑定上传下载事件
	 */
	table.bind('click','.manual',function(data, table){
		Joyin.dialog(JoyinUtil.getLabel("附件上传下载"), JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/init?"), 
				"95%","95%", {
					finprodId : data.finprodId,
					valBalFlagCode : data.valBalFlagCode,
					valDate: data.valDate,
					finprodAbbr: data.finprodAbbr
	            });
	});
	
	/**
     * 绑定修改事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.data = JSON.stringify(data);
        Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm001/checkEdit', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("净值修改"),
        			basePath + "valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm004/init",
                    "80%", "80%", {data : data.data, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
        	});
    });
	//add by hechuan 2019-10-9 end

	var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    var openBatchGroupDetail = function (finprodId, pageModel) {
        var params = {finprodId: finprodId, pageModel: pageModel};
        layer.alert('还没有做!!!!');
//        if (!finprodId) {
//            delete params.finprodId;
//        }
//        Joyin.dialog(JoyinUtil.getLabel("查看详情"), 'openBatchGroupDetail', '99%', '99%',
//            params,
//            function () {
//                Joyin.ajax('deleteBatchJob', {}, function () {
//                    //table.load();
//                })
//        });
    }
    
    // 确认相关操作
    function checkAllCheckedRows(confirmStatus){
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
            layer.alert("请选择一条记录");
            return;
        }
        var flag=true;
        var msg='';
        var index='';
        var arr=new Array();
        if(confirmStatus=='1'){
            for(var i = 0, row; row = rows[i]; i++){
                if(row.confirmStatusCode=='0'){
                    if(i==0){
                        index=i+1;
                    }else{
                        index=index+','+(i+1);
                    }
                }else{
                    layer.alert("只能对未确认的产品进行确认！！！");
                    return;
                }
                var parms={};
                parms.finprodId=row.finprodId;
                parms.finprodAbbr = row.finprodAbbr;
                parms.valDate=row.valDate;
                parms.valBalFlag = row.valBalFlag;
                parms.valBalFlagCode=row.valBalFlagCode;
                
                parms.confirmStatusCode= confirmStatus;
                parms.confirmStatus = '确认';
                
                arr.push(parms);
            }
            if(index!=''){
                msg='第'+index+'条产品已线下对账成功?';
                Joyin.confirm(msg, function(){
                	
            		var object = {}; 
            		object.data = JSON.stringify(arr);
            		object.operateType = 'submit';
                    object.pageModel = pageModel;
                    object.confirmOrNotice = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
                    
                    //启动流程操作
                    startFunction(object,"ValNetValueConfirm001_btn_submit");
                     
                }, function() {
                    console.log('取消');
                });
            }
        }
        if(confirmStatus=='0'){
        	//定义变量
        	var notConfirmIndexArr = [];//确认状态为未确认的数据
        	var okNoticeStatusIndexArr = [];//下发状态为成功的数据
        	
        	//循环选中的数据，进行数据过滤
            for(var i = 0, row; row = rows[i]; i++){
            	//存在【确认状态为未确认】或者【下发状态为成功】的情况
                if(row.confirmStatusCode=='0' || row.noticeStatusCode=='1'){
                	//【确认状态为未确认】的 情况
                	if(row.confirmStatusCode=='0'){
                		notConfirmIndexArr.push(i+1);
                	}
                	//【下发状态为成功】的情况
                	if(row.noticeStatusCode=='1'){
                		okNoticeStatusIndexArr.push(i+1);
                	}
                	
                }
                //可正常操作的数据
                else{
                    var parms={};
                    parms.finprodId=row.finprodId;
                    parms.finprodAbbr = row.finprodAbbr;
                    parms.valDate=row.valDate;
                    parms.valBalFlag = row.valBalFlag;
                    parms.valBalFlagCode=row.valBalFlagCode;

                    parms.confirmStatusCode= confirmStatus;
                    parms.confirmStatus = '撤销确认';
                    
                    arr.push(parms);
                }
            }
            
            //存在【确认状态为未确认】的 情况
            if(notConfirmIndexArr != null && notConfirmIndexArr.length > 0){
                msg='第'+notConfirmIndexArr.join(',')+'条估值表未确认,不需要取消!!';
                layer.alert(msg);
            }
            //存在【下发状态为成功】的情况
            else if(okNoticeStatusIndexArr != null && okNoticeStatusIndexArr.length > 0){
                msg='存在已经下发的估值数据不能取消确认，请重新选择!!';
                layer.alert(msg);
            }
            //无错误情况，可进行【撤销确认】操作
            else{
                Joyin.confirm('是否撤销确认', function(){
                	
                	var object = {}; 
                	object.data = JSON.stringify(arr);
            		object.operateType = 'submit';
                    object.pageModel = pageModel;
                    object.confirmOrNotice = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
                    
                    //启动流程操作
                    startFunction(object,"ValNetValueConfirm001_btn_delete");
                     
                }, function() {
                    console.log('取消');
                });
            }
            
        }
    }
    
    //下发相关操作
    function checkUnfrozen(unfrozen){
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
            layer.alert("请选择一条记录");
            return;
        }
        var flag=true;
        var msg='';
        var arr=new Array();
        for(var i = 0, row; row = rows[i]; i++){
            //撤销下发操作
        	if(unfrozen=='0'){
                if(row.noticeStatusCode!='1'){
                    flag=false;
                    msg='只能对下发状态为“下发成功”的记录进行取消下发操作，请重新选择！';
                    break;
                }
                else{
                    var parms={};
                    parms.finprodId=row.finprodId;
                    parms.finprodAbbr = row.finprodAbbr;
                    parms.valDate=row.valDate;
                    parms.valBalFlag = row.valBalFlag;
                    parms.valBalFlagCode=row.valBalFlagCode;
                    
                    parms.noticeStatusCode=unfrozen;
                    parms.noticeStatus = '撤销下发';
                    
                    arr.push(parms);
                    
                }
            }
        	//下发操作
        	else{
                //【确认状态不为已确认】或者【下发状态为下发成功】情况
        		if(row.confirmStatusCode!='1' || row.noticeStatusCode=='1'){
                	flag=false;
                    msg='只能对确认状态为“已确认”并且下发状态为“未下发”或者“下发失败”的记录进行下发操作，请重新选择！';
                    break;
                    
                }else{
                    var parms={};
                    var parms={};
                    parms.finprodId=row.finprodId;
                    parms.finprodAbbr = row.finprodAbbr;
                    parms.valDate=row.valDate;
                    parms.valBalFlag = row.valBalFlag;
                    parms.valBalFlagCode=row.valBalFlagCode;
                    
                    parms.noticeStatusCode=unfrozen;
                    parms.noticeStatus = '下发';
                    
                    
                    parms.finprodType=row.finprodType;
                    parms.capiTal=row.capiTal;
                    parms.prdTotAssetVal=row.prdTotAssetVal;//modfiy by dongjun on 20190429 净值下发增加字段
                    parms.prdAssetVal=row.prdAssetVal;//modfiy by dongjun on 20190429 净值下发增加字段
                    parms.assetValue=row.assetValue;
                    parms.netUnitValue=row.netUnitValue;
                    parms.totalNetNalue=row.totalNetNalue; 
                    parms.sevenDayRate =row.sevenDayRate;
                    arr.push(parms);
                }
            }
            
        }
        
        //非正常情况
        if(!flag){
            layer.alert(msg);
            return;
        }
        //正常情况
        else{
        	
       		var object = {}; 
       		object.data = JSON.stringify(arr);
    		object.operateType = 'submit';
            object.pageModel = pageModel;
            object.confirmOrNotice = "XF";//当前操作为确认还是下发【确认："QR",下发："XF"】
            
            //撤销下发
            if(unfrozen=='0'){
            	//启动流程操作
                startFunction(object,"ValNetValueConfirm001_btn_deleteunfrozen");
            }
            //下发
            else{
            	//启动流程操作
            	startFunction(object,"ValNetValueConfirm001_btn_unfrozen");
            }
            
            return;
        }
        
    }
    
    
    /**
     * 启动流程
     */
    var startFunction = function(objectDate,elementId){
        Joyin.ajax(basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm001/check', 
        		objectDate, 
        		function(result) {
                	WorkFlow.doSubmitList(
                    		basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm001/updateConfirmStatusAndNoticeStatus', 
                    		objectDate, 
                    		elementId,
                    		null,
                    		function (data) {table.reload();}
                    		);
        		}
        );
    }
    
    var openShow=function(){
        var rows=table.getAllCheckedRows();
        Joyin.dialog(JoyinUtil.getLabel("附件上传"),
                JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/init"),
                "90%", "90%", {pageModel:PAGE_MODEL_UPLOAD,
            finprodId:rows.finprodId,finprodAbbr : rows.finprodAbbr,valDate:rows.valDate,valBalFlag:rows.valBalFlag},
                function(){
                });
    }
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValNetValueConfirm001_btn_search').click(function() {
            table.load();
        });
        //UAT-342 1：缺少【重置】按钮 leipeng 20180905
//        $('#ValNetValueConfirm001_btn_reset').hide();
        $("body").on("click", ".batchName", function () {
            openBatchGroupDetail($(this).data("finprodId"), PAGE_MODEL_REFERENCE);
        })
        $('#ValNetValueConfirm001_btn_export').click(function() {
            exportExcel();
        });
        
        $('#ValNetValueConfirm001_btn_submit').click(function() {
            checkAllCheckedRows('1');
        });
        $('#ValNetValueConfirm001_btn_delete').click(function() {
            checkAllCheckedRows('0');
        });
        $('#ValNetValueConfirm001_btn_unfrozen').click(function() {
            checkUnfrozen('1');
        });
        $('#ValNetValueConfirm001_btn_deleteunfrozen').click(function() {
            checkUnfrozen('0');
        });
        $('#ValNetValueConfirm001_btn_chart').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("产品净值趋势（净值型）"),
                basePath + "valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm005/init",
                "90%", "90%", {pageModel:PAGE_MODEL_NEW
                });
        });
        
    });
	
});
/**
 * 跳转页面
 */
var redirectUrl = function(param){
//    window.location.href = JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/init?"+param);
    
    Joyin.dialog(JoyinUtil.getLabel("附件上传下载"),
            JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/init?"+param),
            "95%", "95%", {},
            function(){
            });  
}