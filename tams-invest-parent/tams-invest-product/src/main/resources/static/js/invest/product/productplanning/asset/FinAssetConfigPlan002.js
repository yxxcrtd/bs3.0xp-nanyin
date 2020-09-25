$(function() {
	var getElementById = function(id) {
        return $('#' + 'FinAssetConfigPlan002' + '_' + id);
    };
    
    var laydate = layui.laydate;
    laydate.render({
		elem: '#FinAssetConfigPlan002_configMonth2',
		type: 'month',
		format: 'yyyy-MM'
			
	});
    
    function saveOrSubmit(operateType) {
    	if(getElementById('configMonth2').val() == null || getElementById('configMonth2').val() == ''
    		||getElementById('tagetScale').val() == null || getElementById('tagetScale').val() == ''
    			||getElementById('benchDate').val() == null || getElementById('benchDate').val() == ''){
    		Joyin.error(JoyinUtil.getMessage("请输入必填项！"));
    		return;
    	}
    	if(Date.ofDate(getElementById('benchDate').val())>=Date.ofDate(iPlatformDate)){
 		   Joyin.error(JoyinUtil.getMessage("基准日必须小于平台日!"));
 		   return ;
 	    }
        var object = $.serializeToObject('#FinAssetConfigPlan002_form');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.configMonth2 = getElementById('configMonth2').val();
        var selectedItem = table.getAllRows();
        object.selectedItem=selectedItem;
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/asset/FinAssetConfigPlan002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productplanning/asset/FinAssetConfigPlan002/save', object, "FinAssetConfigPlan002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productplanning/asset/FinAssetConfigPlan002/save', object, "FinAssetConfigPlan002_btn_submit");
                }
            });
        });
    };
    
    var init = true;
    //var editable = pageModel != PAGE_MODEL_REFERENCE;
    /**
     * 页面加载初始化
     */
	var options = {
			page: true,
			ajax: {
            url: JoyinUtil.getRealPath('invest/product/productplanning/asset/FinAssetConfigPlan002/findPage'),
            type: 'POST',
            data: function() {
                return {assetConfigCode: getElementById('assetConfigCode').val(),
                		//assetType: getElementById('assetType').val(),
                		//configMonth: getElementById('configMonth').val(),
                		configMonth2: getElementById('configMonth2').val(),
                		pageModel:pageModel,
                		workflowId:workflowId, 
                		tagetScale:getElementById('tagetScale').val(),
                		benchDate:getElementById('benchDate').val(),
                		genDate:getElementById('genDate').val()
                	};
            }
			},
		/*data:[],*/
        checkbox: true,
        done:function(){
        	if(init){
        		showmonths();
        		init = false;
        	}
        },
        //addbtn: true,
    	
        searchOnInit: true,
        cols:[
        	{field:'assetConfigCode', visible:false}
            ,{field:'assetType', visible:false}
            ,{field:'assetTypeStr', title: 'FinAssetConfigPlan001.assetType', width:160, sortable:false}
            ,{field:'totalRatio', title: 'FinAssetConfigPlan001.totalRatio', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'totalRatioStr', title: 'FinAssetConfigPlan001.totalRatio', width:100, sortable:false}
            ,{field:'totalAmt', title: 'FinAssetConfigPlan001.totalAmt', type:'number', sortable:false, digit:2}
            ,{field:'curPerAmt', title: 'FinAssetConfigPlan001.curPerAmt', type:'number', sortable:false, digit:2}
            ,{field:'curEndAmt1', title: 'FinAssetConfigPlan001.curEndAmt1', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt1', title: 'FinAssetConfigPlan001.curInvAmt1', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt2', title: 'FinAssetConfigPlan001.curEndAmt2', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt2', title: 'FinAssetConfigPlan001.curInvAmt2', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt3', title: 'FinAssetConfigPlan001.curEndAmt3', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt3', title: 'FinAssetConfigPlan001.curInvAmt3', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt4', title: 'FinAssetConfigPlan001.curEndAmt4', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt4', title: 'FinAssetConfigPlan001.curInvAmt4', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt5', title: 'FinAssetConfigPlan001.curEndAmt5', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt5', title: 'FinAssetConfigPlan001.curInvAmt5', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt6', title: 'FinAssetConfigPlan001.curEndAmt6', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt6', title: 'FinAssetConfigPlan001.curInvAmt6', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt7', title: 'FinAssetConfigPlan001.curEndAmt7', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt7', title: 'FinAssetConfigPlan001.curInvAmt7', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt8', title: 'FinAssetConfigPlan001.curEndAmt8', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt8', title: 'FinAssetConfigPlan001.curInvAmt8', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt9', title: 'FinAssetConfigPlan001.curEndAmt9', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt9', title: 'FinAssetConfigPlan001.curInvAmt9', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt10', title: 'FinAssetConfigPlan001.curEndAmt10', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt10', title: 'FinAssetConfigPlan001.curInvAmt10', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt11', title: 'FinAssetConfigPlan001.curEndAmt11', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt11', title: 'FinAssetConfigPlan001.curInvAmt11', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curEndAmt12', title: 'FinAssetConfigPlan001.curEndAmt12', type:'number', sortable:false, digit:2, visible:false}
            ,{field:'curInvAmt12', title: 'FinAssetConfigPlan001.curInvAmt12', type:'number', sortable:false, digit:2, visible:false}
           //,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    };
    var search = function(){
    	if(getElementById('configMonth2').val() == null || getElementById('configMonth2').val() == ''
    		||getElementById('tagetScale').val() == null || getElementById('tagetScale').val() == ''
    			||getElementById('benchDate').val() == null || getElementById('benchDate').val() == ''){
    		//Joyin.error(JoyinUtil.getMessage("请输入查询日！"));
    		return;
    	}
    	table.reload();
    	showmonths();   	
    }  
    
    var showmonths = function () {
		//alert(222);
    	if(getElementById('configMonth2').val() != null && getElementById('configMonth2').val() != ''){
    		date =Date.parse(getElementById('configMonth2').val(),'yyyy-MM');
    		//alert(333);
    		var month = new Date(date).getMonth() + 2;
    		var param = [];
    		for(var i= parseInt(month);i<=12;i++){
    			param.push({field:'curEndAmt'+i, title: 'FinAssetConfigPlan001.curEndAmt'+i, type:'number', sortable:false, digit:2, visible:true});
    			param.push({field:'curInvAmt'+i, title: 'FinAssetConfigPlan001.curInvAmt'+i, type:'number', sortable:false, digit:2, visible:true});
    		}
    		table.showColumns(param);
		}
    }
    /*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
    /**
     *绑定删除事件
    */
    /*table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });*/
    

    $(function(){
    	//search();
    	getElementById('configMonth2').change(function(){
    		//alert(111);
    		search();
    	});
    	getElementById('tagetScale').change(function(){
    		search();
    	});
    	getElementById('benchDate').change(function(){
    		search();
    	});
        $('#FinAssetConfigPlan002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinAssetConfigPlan002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());