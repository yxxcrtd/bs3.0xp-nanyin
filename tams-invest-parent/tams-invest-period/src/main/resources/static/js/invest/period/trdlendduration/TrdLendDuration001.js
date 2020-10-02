/**
 * 拆借存续期
 */
$(function() {
	top.TrdLendDuration001 = function(){
		return{
			getTable:function(){
				return table;
			}
		}
	}
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var object={
            	//业务代码
            	busiCode: $('#busiCode').val(),
            	//交易方向
            	backLendDirection:$('#backLendDirection').val(),
            	//交易日期
            	tradeDateStart:$("#tradeDate").startDate(),
            	tradeDateEnd:$("#tradeDate").endDate(),
            };
    	if($('#statusValid').val() && $('#statusValid').val() !=''){
    	    object.statusValid = $('#statusValid').val().join(',');
    	}else{
    	    object.statusValid = null;
    	}
    	if($('#portfolioId').val() && $('#portfolioId').val() !=''){
    	    object.portfolioId = $('#portfolioId').val().join(',');
    	}else{
    	    object.portfolioId = null;
    	}
        return object;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
    	ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
    	
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdLendDuration001.reference"),
                    JoyinUtil.getRealPath("invest/period/trdlendduration/TrdLendDuration002/init"),
                    "98%", "95%",data,function(){table.reload();});
        },
        progressBar: true,
        cols:[
             {field:'statusValidStr',width:'10%',sortField:"statusValid", title: 'TrdLendDuration001.statusValid'}
            ,{field:'busiCode',width:'20%', title: 'TrdLendDuration001.busiCode'}
            ,{field:'portfolioId',width:'20%', title: 'TrdLendDuration001.portfolioId'}
            ,{field:'portfolioName',width:'20%',title: 'TrdLendDuration001.portfolioName'}
            ,{field:'pingAnCodeStr',width:'10%',sortField:'finprodType2', title: 'TrdLendDuration001.ps'}
            ,{field:'tradeDate',width:'10%', type:'date',title: 'TrdLendDuration001.tradeDate'}
            ,{field:'vdate',width:'10%',type:'date',title: 'TrdLendDuration001.vdate'}
            ,{field:'mdate',width:'10%',type:'date', title: 'TrdLendDuration001.mdate'}
            ,{field:'prinAmt',width:'10%', title: 'TrdLendDuration001.prinAmt',type:'money'}
            ,{field:'settleDate',width:'10%', type:'date',title: 'TrdLendDuration001.settleDate'}
            ,{field:'intAmt',width:'10%', title: 'TrdLendDuration001.intAmt',type:'money'}
            ,{field:'theoryAmt',width:'10%', title: 'TrdLendDuration001.theoryAmt',type:'money'}
            ,{field:'tradeAmt',width:'10%', title: 'TrdLendDuration001.tradeAmt',type:'money'}
            , {field: 'statusValid', visible: false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']
            ,render: function (data, type, row, meta) {
                if (row.statusValid != '01')
                    return "";
                else
                    //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 S
                    if(purviewElements.indexOf('_edit')!=-1){
                        return '<i class="btn iconfont edit icon-circle-correct_fill" title="确认"></i>';
                    }
                    //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 E
                  }
            }
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	if(data.statusValid != PAGE_DATA_STATUS_01){
    		return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
    	}
        data.pageModel = PAGE_MODEL_NEW;
        data.operateType = 'edit'
        Joyin.ajaxJson(basePath + 'invest/period/trdlendduration/TrdLendDuration001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("TrdLendDuration002.edit"),
                    basePath + "invest/period/trdlendduration/TrdLendDuration002/init",
                    "95%", "90%", data,function(){table.reload();});
            
        });
    });
    
    
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams()) 
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdLendDuration001_btn_search').click(function() {
            table.load();
            $('#TrdLendDuration001_btn_revoke').show();
        });
        
        $('#TrdLendDuration001_btn_export').click(function() {
            exportExcel();
        });
        
        $('#TrdLendDuration001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            if (selArr[0].statusValid != PAGE_DATA_STATUS_05) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0006"));
                return;
            }
            var object = selArr[0];
            object.operateType="submit";
            object.mode="revoke";
            if(object && object.busiCode && object.busiCode != ''){
                object.pTradeId = object.busiCode;
                object.busiCode = object.busiCode + 'R';
            }
            Joyin.ajaxJson(basePath + 'invest/period/trdlendduration/TrdLendDuration001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                	Joyin.prompt(JoyinUtil.getLabel("TrdLendDuration001.revokeReason"),2,function(res){
                		object.cancelRemark = res;
                		WorkFlow.doRevokeList(basePath + 'invest/period/trdlendduration/TrdLendDuration001/revoke', object, "TrdLendDuration001_btn_revoke", "TrdLendDuration002");
                	},null,200);
                });
            });
               
        });
    });
}());