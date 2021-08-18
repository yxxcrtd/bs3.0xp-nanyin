/**
 * MstCalendarType002 日历类型管理新建（含修改）
 */
$(function() {
    var getSearchParams = function() {
        return {
        	workflowId: workflowId,
        	isTodoList: isTodoList,
        	customerId: $('#customerId').val(),
        	pflsh: $('#pflsh').val(),
        	mdate: $('#mdatebak').val(),
        	ccy: $('#ccy').val(),
        }
    };
  
	/**
     * 页面加载初始化
     */
	var options = {
		page: false,
		ajax: {
	        url: JoyinUtil.getRealPath('invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/getCreditConfig'),
	        type: 'POST',
	        data: function() {
	              return getSearchParams();
	        }
	    },
        loadonce: false,
        rowno: true,
        checkbox: false,
        searchOnInit: true,
        cols:[
		        {field:'creditTagId',visible: false,edit: false, width:200,title: 'CeLine001.creditTagName', sortable:false}
	            ,{field:'seqno',visible: false,title: 'seqno', width:50,}
	            ,{field:'orgType',visible: false,title: 'LimitManage001.orgType', width:50}
/*                ,{field:'creditTypeText',edit: false, width:200,title: 'LimitManage002.creditType',  editOption:{type:'select', result: LIMIT_TYPE}, sortable:false}
*/              ,{field:'creditType',edit: true, width:150,title: 'LimitManage002.creditType',  editOption:{type:'select', result: LIMIT_TYPE}, sortable:false}
                ,{field:'creditAmt', edit: false,width:150, sortable:false,title: 'LimitManage002.creditAmt',align:'center',type: 'number',digit:4 }
                ,{field:'creditAmtAdd',visible: false, edit: false,width:150, sortable:false,title: 'LimitManage002.creditAmtAdd',align:'center',type: 'number',digit:4 }
                ,{field:'term',sortable:false,edit: false, width:100, type: 'number', title: 'LimitManage002.term'}
		        ,{field:'termUnit',sortable:false,edit: true, width:50,editOption:{type:'select', result: TERM_UNIT}, sortable:false, title: 'LimitManage002.termUnit'}
	            ,{field:'riskRatio', width:150,edit: false, sortable:false,title: 'LimitManage002.riskRatio',align:'center',type: 'number',digit:4 }
	            ,{field:'mdate', width:100, edit: false,sortable:false,title: 'LimitManage002.mdate',align:'center',type: 'date' }
	            ,{field:'upTime', width:100,edit: false, sortable:false,title: 'LimitManage002.updateTime',align:'center',type: 'text'}
	            ,{field:'remark', width:200,edit: false,sortable:false,title: 'LimitManage002.remark',align:'center',type: 'text'}
	            ,{field:'codeStatus',visible: false, width:100, sortable:false,title: 'LimitManage002.status',align:'center', }
	            ,{field:'codeStatusText',width:150 ,  title: 'LimitManage002.status'}
	            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ],
     /*  editCell : function(value, data, field, cell, column){
          		var rows = table.getAllRows();

              	   if(field=='term'){
              		 var termUnit=data.termUint;  
              		 if(termUnit!='Y'){
              			if (!(/(^[1-9]\d*$)/.test(value))){

              			}else{
              				JoyinUtil.showMessage("期限单位只能为整数", 'error', function() {
              					data.term=null;
              					return;
              				});
              			}
              		 }else if(termUnit=='Y'){
              			   var str = value.substring(0,s.indexOf(".") + 3);
              			   if(str=="25" || str=="50" || str=="75"){
         
              			   }else{
            					JoyinUtil.showMessage("期限单位为年时,小数点为0.25/0.5/0.75", 'error', function() {
                  					data.term=null;
                  					return;
                  				});
              			   }
              		 }
              			 
              		 }
 
             }*/
        done:function(table, allData){
            //数据加载完成后，加载图表
        	calCreditAmt();
        },
        
    };
	var table = $("#dataTable").joyintable(options);
	   /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    	var num=null;
        var rows=table.getAllRows();
    	for(var i in rows){
    		if(i==param.index){
  				table.renderRow(i, param);
    		}
    		num+=Number(rows[i].creditAmt);
    	}
    	$('#creditAmt').val(num);
    });
    
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
   	    top.modelSelectParam =null;
   	    var object = {};
   	    object.pageModel = PAGE_MODEL_EDIT;
        object.operateType = 'edit';
        object.isModelSelect='yes';
        object.index=index;
        object.datas=data;

        object.creditTagId=data.creditTagId;
        object.seqno=data.seqno;
        object.orgType=data.orgType;
        object.creditType=data.creditType;
        object.creditAmt=data.creditAmt;
        object.term=data.term; 
        object.termUnit=data.termUnit;
        object.riskRatio=data.riskRatio;
        object.mdate=data.mdate;
        object.upTime=data.upTime;
        object.remark=data.remark;
        object.codeStatus=data.codeStatus;

            Joyin.dialog(JoyinUtil.getLabel("LimitManage003.edit"),
                    basePath + "invest/risk/creditrisklimitmanagement/limitmanage/LimitManage003/init",
                    "60%", "80%",object,function(result){
                    	  modelEditParam(top.modelSelectParam);
                     });
    });
    
    
    //判断是提交或是保存
    function saveOrSubmit(operateType) {  
        var object = $.serializeToObject('#limitManageForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.creditMstId='0001';
       //object.creditQuoteItem=[];
        object.creditLabelItem=[];
        var rows=table.getAllRows();
        rows.map(function(v){
        	if(null!=v.creditAmt&&typeof v.creditAmt!="undefined"){
        		//v.creditMstId='0004';
        		//v.updateTime=new Date(v.updateTime).format("yyyy-MM-dd HH:mm:ss");
        		object.creditLabelItem.push(v);
        	}
        /*	alert(v);
        	if(null!=v.updateTime&&typeof v.updateTime!="undefined"){
        		alert(object.updateTime);
        		v.updateTime=new Date(v.updateTime);
        	}*/
        
        })

        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/save', object, "LimitManage002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditrisklimitmanagement/limitmanage/LimitManage002/save', object, "LimitManage002_btn_submit");
                }
            });
        });
        
        
    };

    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
    	top.modelSelectParam =null;
        Joyin.dialog(JoyinUtil.getLabel("LimitManage003"),
                JoyinUtil.getRealPath("invest/risk/creditrisklimitmanagement/limitmanage/LimitManage003/init"),
                "60%", "80%",{isModelSelect:"yes"},function(result){
                	modelSelectParam(top.modelSelectParam);
                }
        );
    }
    
    function modelSelectParam(param){
        var rows=table.getAllRows();
    	table.clear(null);
    	//var dataParam = param.orgType;
    	var num=null;
       	for(var i in rows){
    	 	table.addRow({creditTagId:rows[i].creditTagId,seqno:rows[i].seqno,termUnit:rows[i].termUnit,orgType:rows[i].orgType,creditTypeText:rows[i].creditTypeText,creditType:rows[i].creditType, creditAmt:rows[i].creditAmt,term:rows[i].term,riskRatio:rows[i].riskRatio,mdate:rows[i].mdate,upTime:rows[i].upTime,remark:rows[i].remark});
    	 	num+=Number(rows[i].creditAmt);
       	}
	 	table.addRow({creditTagId:param.creditTagId,seqno:param.seqno,termUnit:param.termUnit,orgType:param.orgType,creditType:param.creditType, creditAmt:param.creditAmt,term:param.term,riskRatio:param.riskRatio,mdate:param.mdate,upTime:param.upTime,remark:param.remark});
		num+=Number(param.creditAmt);
		$('#creditAmt').val(num);
    };
    
    function modelEditParam(param){
    	var num=null;
        var rows=table.getAllRows();
    	for(var i in rows){
    		if(i==param.index){
  				table.renderRow(i, param);
  				num+=Number(param.creditAmt);
    		}else{
        		num+=Number(rows[i].creditAmt);
    		}
    	}
    	$('#creditAmt').val(num);
	 	//table.addRow({seqno:param.seqno,orgType:param.orgType,creditType:param.creditType, creditAmt:param.creditAmt,term:param.term,riskRatio:param.riskRatio,mdate:param.mdate,updateTime:param.updateTime,remark:param.remark});
    }
    
    var calCreditAmt = function() {
    	var num=null;
        var rows=table.getAllRows();
    	for(var i in rows){
    		num+=Number(rows[i].creditAmt);
    	}
    	$('#creditAmt').val(num);
    };
    
    $(function() {
 
        // 绑定按钮事件
        $('#LimitManage002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#LimitManage002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
 
        $('#LimitManage002_btn_add').click(function() {
            openNew();
        });
		
        

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT )) {
			$('#customerName').prop('disabled', true);
	        $('#customerId').attr("readonly","readonly")//将input元素设置为readonly
			$('#customerId').prop('disabled', true);

       }
        $('#creditAmt').attr("readonly","readonly")//将input元素设置为readonly       
        //calCreditAmt();

    });
}());