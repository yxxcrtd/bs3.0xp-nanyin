(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	var model = $('#model').val();
    	if (model == REDIS_MODEL_PARAMVAL) {
        	if(param.rdsKey){
        		param.rdsKey = param.rdsKey.join(",");
        	}
    	} else {
    		param.rdsKey = param.model;
    	}
        return param;
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
        rowno: true,
        checkbox: false,
        page: true,
        searchOnInit: false,
        cols:[
             {field:'key', title: 'RedisView.key', width:'30%'}
            ,{field:'field', title: 'RedisView.field', width:'30%'}
			,{field:'value', title: 'RedisView.value', width:'29%',sort:false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: [{
                text: '&#xe614;',
                clas: 'manual',
                title: '刷新'
            }]}
			/*,{field: 'operation', title: 'SysCommon.operation', type: 'action', 
			    render: function (data, type, row, meta) {
			       
			            if(purviewElements.indexOf('_manual')!=-1){
			                return '<i class="btn layui-icon manual" title="刷新">&#xe614;</i>';
			            }
			        } 
			    }*/
            
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.manual', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/redismgr/RedisView/refresh', data, function(result) {
        	if (result.data) {
        		data = result.data;
        	}
        	table.renderRow(index, data);
        });
    });

    var modelClick = function(){
    	var model = $('#model').val();
    	if (model == REDIS_MODEL_PARAMVAL) {
    		$('#div_key').show();
    		$("#RedisView_btn_refresh").hide();
    	} else {
    		$('#div_key').hide();
    		$("#RedisView_btn_refresh").show();
    	}
    	/*if (model == REDIS_MODEL_EVENT) {
    		$('#div_field').hide();
    	} else {
    		$('#div_field').show();
    	}*/
    }
    
	$(function() {
		$('#model').val(REDIS_MODEL_PARAMVAL);
		$('#model').change(function(){
			modelClick();
		});
		$('#model').trigger('change');
		// 选择模块
		// 查询按钮
        $('#RedisView_btn_search').click(function() {
            var model = $('#model').val();
            var rdsKey = $('#rdsKey').val();
        	if (model == REDIS_MODEL_PARAMVAL) {
            	if(rdsKey === null || rdsKey === ''){
            		Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel("RedisView.key")));
            		return;
            	}
        	}
            table.load();
        });
        
        $("#RedisView_btn_refresh").click(function(){
        	var data = {key:$('#model').val()};
        	Joyin.ajaxJson(basePath + 'invest/riskredis/quota/redismgr/RedisView/refresh', data, function(result) {
        		table.load();
        });
        })
	});
}());