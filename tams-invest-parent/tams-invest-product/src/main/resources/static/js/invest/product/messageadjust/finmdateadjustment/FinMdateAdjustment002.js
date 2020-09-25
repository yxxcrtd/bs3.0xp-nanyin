(function(){
    
    /*
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
    	ajax: {
            url: JoyinUtil.getRealPath('invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/queryRelateInfoList'),
            type: 'POST',
            data: function() {
                return {finprodId: $('#FinMdateAdjustment002_finprodId').val(), workflowId:workflowId, dealMode:$('#FinMdateAdjustment002_hidden_dealMode').val()
                	};
            }
		},
		searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: false,
        height : 290,
        cols:[
            {field:'feeName', title: 'FinPayPlanAdjustment001.feeName',display:false, visible:true, width : 220, edit:false , sortable:false}  
            ,{field:'prodStyle', title: 'FinMdateAdjustment002.prodStyle',visible:true, width : 220, edit:false , sortable:false}  
            ,{field:'eAccrDate', title: 'FinPayPlanAdjustment001.mdate', visible:true, width : 170, type:'date', edit:true , sortable:false}
            ,{field:'feeType', visible:false} 
            ,{field:'feeId', visible:false} 
            ,{field:'cashId',visible:false}
            ,{field:'finprodId',visible:false}
            ]
        ,done: function(table, allData){
        	if($('#FinMdateAdjustment002_hidden_dealMode').val() != '11'||$('#FinMdateAdjustment002_hidden_profitType').val() != '01'){
            	$("[data-field='prodStyle']").attr("style","display:none")//列头(适用款型)隐藏
            	$('#dataTable').next().find("table tr").find('td:eq(2)').attr('style',"display:none");
        	}
        	
        	$("[data-field='feeType']").attr("style","display:none")//列头(费用类型)隐藏
        	$("[data-field='feeId']").attr("style","display:none")//列头(费用代码)隐藏
        	$("[data-field='cashId']").attr("style","display:none")//列头(现金流代码)隐藏
        	$("[data-field='finprodId']").attr("style","display:none")//列头(产品代码)隐藏
        	$('#dataTable').next().find("table tr").find('td:eq(4)').attr('style',"display:none");
        	$('#dataTable').next().find("table tr").find('td:eq(5)').attr('style',"display:none");
        	$('#dataTable').next().find("table tr").find('td:eq(6)').attr('style',"display:none");
        	$('#dataTable').next().find("table tr").find('td:eq(7)').attr('style',"display:none");
        }
    });
    
    //选择调整后到期日，联动调整后清盘日、计提结束日
    var mdateAdjust=function(mdate){
    	if(mdate!=''&&mdate!=null){
    		//调整后清盘日:默认为调整后到期日+1
//    		$("#FinMdateAdjustment002_adjustAfterLdate").val(formatTime(mdate,1));
    	    getNextWorkDay(mdate);
    		//计提结束日
    		var selectedItem = table.getAllRows();
        	$.each(selectedItem ,function(index,feeType){
        		if(selectedItem[index].feeType==FEE_SPECIES_TYPE_12){//若为浮动管理费，默认为实际到期日
        			$($('#dataTable').next().find("table tr").get(index+1)).find('td:eq(3)').attr('title', mdate).html('<div' +
						' class="layui-table-cell laytable-cell-1-0-3">'+mdate+'</div>');
        		}else{//若为(除浮动管理费外)固定费用，默认为实际到期日-1
        			var date = formatTime(mdate,-1);
        			$($('#dataTable').next().find("table tr").get(index+1)).find('td:eq(3)').attr('title', date).html('<div' +
						' class="layui-table-cell laytable-cell-1-0-3">'+date+'</div>');
        		}
        	});
    		
        	//更新缓存
    		$.each($('#dataTable').data("joyintable").getAllRows(true),function(index,obj){
    			if(selectedItem[index].feeType==FEE_SPECIES_TYPE_12){
    				$('#dataTable').data("joyintable").getAllRows(true)[index].eAccrDate=mdate;
    			}else{
    				$('#dataTable').data("joyintable").getAllRows(true)[index].eAccrDate=formatTime(mdate,-1);
    			}
    		});
    		
    	}
    };
    
    var formatTime=function(date,days) {
    	var newDate=Date.ofDate(date);
    	newDate.setDate(newDate.getDate()+days);
	    var year = newDate.getFullYear();
	  
	    var month = newDate.getMonth()+1, month = month < 10 ? '0' + month : month;
	    var day = newDate.getDate(), day =day < 10 ? '0' + day : day;
	    return year + '-' + month + '-' + day;
	}
    
    var preEndFlagAction = function() {
        /* $('#FinMdateAdjustment002_adjustBeforeLdate').val('');
         $('#FinMdateAdjustment002_adjustAfterLdate').val('');*/
         table.reload();
//     	var mdateUnadjust = $('#FinMdateAdjustment002_mdateUnadjust').val();
//     	var mdate = $('#FinMdateAdjustment002_mdate').val();
//     	if (mdateUnadjust && mdate && mdate < mdateUnadjust){
// 			$('#FinMdateAdjustment002_preEndFlag').prop('checked', true).attr('disabled',true);
//     	}else{
// 			$('#FinMdateAdjustment002_preEndFlag').prop('checked', false).attr('disabled',true);
//     	}
     	layui.form.render('checkbox');
     };
     
     var finprodMarketIdChangeAction = function(){
     	$('#FinMdateAdjustment002_prodAbbr').val('');
     	$('#FinMdateAdjustment002_mdateUnadjust').val('');
     	$('#FinMdateAdjustment002_adjustBeforeLdate').val('');
     	$('#FinMdateAdjustment002_hidden_finprodMarketId').val('');
     	$('#FinMdateAdjustment002_hidden_adjustNum').val('');
     	var finprodId = $('#FinMdateAdjustment002_finprodId').val();
     	if (finprodId){
     		//$('#FinMdateAdjustment002_div_layering').attr("hidden",false);
     		Joyin.ajax(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/queryAdjustInfoByFinprodId', {finprodId : finprodId}, function(result){
     			if (result){
     				$('#FinMdateAdjustment002_prodAbbr').val(result.finprodAbbr);
     		    	$('#FinMdateAdjustment002_mdateUnadjust').val(result.mdateStr).trigger('change');
     		    	$('#FinMdateAdjustment002_adjustBeforeLdate').val(result.ldateUnadjustStr).trigger('change');
     		    	$('#FinMdateAdjustment002_hidden_finprodMarketId').val(result.finprodMarketId);
     		    	$('#FinMdateAdjustment002_hidden_adjustNum').val(result.adjustNum);
     		    	//$('#FinMdateAdjustment002_div_layering').prop('hidden', false);
     		    	//增加产品处理模式，判断是否分层产品
     		    	$('#FinMdateAdjustment002_hidden_dealMode').val(result.dealMode);
     		    	$('#FinMdateAdjustment002_hidden_profitType').val(result.profitType);
     		    	
     		    	calendarId = result.calendarId;
     		    	
     		    	vdate = result.vdate;
     		    	
     		    	table.reload();
     			}
     		})
     	}else{
     		table.reload();
     	}
     	/*else{
 			$('#FinMdateAdjustment002_div_layering').attr("hidden",true);
 		}*/
     };

     // 选择调整按钮后  清空日期。重新选。否则直接提交会异常
     var adjustTypeChangeAction = function(){
            $('#FinMdateAdjustment002_mdate').val('');
         	$('#FinMdateAdjustment002_rdate').val('');
         	$('#FinMdateAdjustment002_bdate').val('');
     }
    
    var onSaveOrSubmitAction = function(operateType){
    	var param = {};
    	var adjustType = $("#FinMdateAdjustment002_adjustType").val();
    	var mdate=$('#FinMdateAdjustment002_mdate').val();
    	var rdate=$('#FinMdateAdjustment002_rdate').val();
    	var bdate=$('#FinMdateAdjustment002_bdate').val();
    	if(adjustType==ADJUSTTYPE_01){//提前终止
    		param.mdate=bdate;
    	}else if(adjustType==ADJUSTTYPE_02){//产品展期
    		param.mdate=rdate;
    	}else{//到期调整
    		param.mdate=mdate;
    	}
    	param.finprodId = $('#FinMdateAdjustment002_finprodId').val();
    	param.finprodMarketId = $('#FinMdateAdjustment002_hidden_finprodMarketId').val();
    	param.finprodAbbr = $('#FinMdateAdjustment002_prodAbbr').val();
    	param.adjustType = adjustType;
    	param.mdateUnadjust = $('#FinMdateAdjustment002_mdateUnadjust').val();
    	param.ldateUnadjust = $("#FinMdateAdjustment002_adjustBeforeLdate").val();
    	param.ldate = $("#FinMdateAdjustment002_adjustAfterLdate").val();
    	param.adjustNum = $('#FinMdateAdjustment002_hidden_adjustNum').val();
    	param.remark = $('#FinMdateAdjustment002_remark').val();
    	//增加产品处理模式
    	param.dealMode = $('#FinMdateAdjustment002_hidden_dealMode').val();
    	param.profitType = $('#FinMdateAdjustment002_hidden_profitType').val();
    	var selectedItem = table.getAllRows();
    	param.selectedItem=selectedItem;
    	param.operateType = operateType;
    	//为净值型分档增加
    	param.selectedItem1=selectedItem;

    	param.vdate = vdate;

    	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/check', param, function(result){
    		Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/save', param, "FinMdateAdjustment002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/save', param, "FinMdateAdjustment002_btn_submit");
                }
            })
    	})
    }
    
    var getNextWorkDay = function(mdate){
    	//modify by wm 修改其他类型不带出清盘日问题 start 2019-07-16
        //var par = {'mdate':$('#FinMdateAdjustment002_bdate').val(),'calendarId': calendarId};
        var par = {'mdate': mdate,'calendarId': calendarId};
        //modify by wm 修改其他类型不带出清盘日问题 end 2019-07-16
        Joyin.ajax(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/getNextWorkDay', par, function(result) {
            if(result){
                $('#FinMdateAdjustment002_adjustAfterLdate').val(result);
            }
        });
    };
    
    var onBulid = function() {
    	if ($('#wfl_operType').val()){
			if ($('#wfl_operType').val() != 'NEW'){
				$('#FinMdateAdjustment002_finprodId').prop('disabled', true);
			}
		}else{
			if (pageModel != PAGE_MODEL_NEW){
				$('#FinMdateAdjustment002_finprodId').prop('disabled', true);
			}
		}
    	//代办中数据产品ID不可修改 yzg 20200115 start
    	if(workflowId){
        	$("#FinMdateAdjustment002_finprodId").prop('disabled', true);
        }
    	//代办中数据产品ID不可修改 yzg 20200115 end
    };
    
    $(function() {

    	preEndFlagAction();
    	onBulid();
//    	$('#FinMdateAdjustment002_mdateUnadjust').change(function(){
//    		preEndFlagAction();
//    	});
//    	
//    	$('#FinMdateAdjustment002_mdate').change(function(){
//    		preEndFlagAction();
//    	});
    	//选择产品代码，自动带出产品简称、调整前到期日、调整前清盘日、费用名称、计提结束日
    	$('#FinMdateAdjustment002_finprodId').change(function(){
    		finprodMarketIdChangeAction();
    	});

    	$('#FinMdateAdjustment002_adjustType').change(function(){
    	    adjustTypeChangeAction();
    	});
    	
        //选择的调整类型，联动调整后到期日字段名展示
    	FinMdateCommon.adjustTypeSelect();
    	FinMdateCommon.getElementById('adjustType').change(function(){
    		FinMdateCommon.adjustTypeSelect();
    	});
        
        //选择调整到日期,联动调整后清盘日、计提结束日，计提结束日可改
    	$('#FinMdateAdjustment002_mdate').change(function(){
    		var mdate=$('#FinMdateAdjustment002_mdate').val();
    		mdateAdjust(mdate);
    	});
    	$('#FinMdateAdjustment002_bdate').change(function(){
    		var bdate=$('#FinMdateAdjustment002_bdate').val();
    		mdateAdjust(bdate);
    	});
    	$('#FinMdateAdjustment002_rdate').change(function(){
    		var rdate=$('#FinMdateAdjustment002_rdate').val();
    		mdateAdjust(rdate);
    	});
        
        /**
         * 绑定表格编辑事件
         */
        table.bind('click', '.delt', function(data, index) {
            table.removeRow(data);
        });
        
        $('#FinMdateAdjustment002_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        $('#FinMdateAdjustment002_btn_submit').click(function() {
        	onSaveOrSubmitAction('submit');
        });
        
	});
}());