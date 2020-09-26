$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodIdForFlow:$("#finprodIdForFlow").val(), 
            valDateForFlow:$("#valDateForFlow").val(),
            valBalFlagCodeForFlow:$("#valBalFlagCodeForFlow").val()
        }
    };
    
    /**
     * 定义数据表格（复用【ValNetValueConfirm001.js】中表格加载类）
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm001/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		checkbox: false,
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
		                    return '<span class="layui-badge-rim layui-bg-blue">'+checkStatus+'</span>';
		                }else{
		                    return '<span class="layui-badge-rim">'+checkStatus+'</span>';
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
    		   {field:'profitType', title: '产品类型',width:150, sort: true},
    		   {field:'valBalFlagCode', title: '估值余额标志',width:150, sort: true,visible:false},
               {field:'valBalFlag', title: '估值余额标志',width:150, sort: true,visible:false},
    		   {field:'valDate', title: '估值日期',width:120, sort: true},
               {field:'capiTal', title: '理财份额',width:150, sort: true,type:'money',unit: 10000},
    		   {field:'assetValue', title: '产品总净值/本日收益',width:180, sort: true,type:'money', unit: 10000},
               {field:'netUnitValue', title: '单位净值/万份收益/收益率',width:210,sort: true,align:"right", unit: 10000},
               {field:'totalNetNalue', title: '累计净值',width:120,sort: true,align:"right", unit: 10000},
               {field:'sevenDayRate', title: '七日年化收益率%',width:160,sort: true,align:"right", unit: 10000},
               {field:'payProfit', title: '应付利润',width:120,sort: true,type:'money',unit: 10000}
		],
	});
	
	$('#ValNetValueConfirm002_btn_submit').click(function() {
	    var rows=table.getAllRows();
        msg='当前数据已线下对账成功?';
        Joyin.confirm(msg, function(){
            var object = rows[0];
            object.operateType = 'submit';
            object.pageModel = pageModel;
            object.confirmOrNotice = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
            object.workflowId = $("#workflowId").val();
            // debugger;
            //启动流程操作
            WorkFlow.doSubmitList(
                    basePath + 'valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm001/updateConfirmStatusAndNoticeStatus', 
                    object, 
                    "ValNetValueConfirm001_btn_submit",
                    null,
                    function (data) {}
                    );
             
        });
        
    });

	/**
     * 画面加载完成
     */
    $(function() {
        //直接加载显示
        table.load();
        
    });
	
});