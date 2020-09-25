$(function () {
	
	
	var cols = [{field:'prodId',title: '产品代码',fixed:true}
    ,{field:'prodName',title: '产品名称',width:449,fixed:true}
    ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['add']}
    ];
	if($.trim($('#onlyView').val())=='01'){
		cols = [{field:'prodId',title: '产品代码',width:440,fixed:true}
	    ,{field:'prodName',title: '产品名称',width:494,fixed:true}
	    ];
	}

	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var object = $.serializeToObject('#searchForm', true);
        var chooseProdTable= object.chooseProdTable;
        return {
        	settedRule:$.trim($('#settedRule').val()),
        	hasProdTable:$.trim($('#hasProdTable').val()),
        	chooseProdTable:chooseProdTable,
        	prodId:$.trim($('#prodId').val()),
        	prodName:$.trim($('#prodName').val())
        }
    };
    var previewProdTable=$("#previewProdTable").joyintable({
    	
    	ajax: {
            url: basePath + 'disclosure/information/manage/workmanage/ReportWorkManage003/findProdPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox : false,
        rowno : true,
	    searchOnInit: true,
	    loadonce:true,
	    page : true,
        cols:cols
    });
    var datas1 = new Array();
    var chooseProdTable=$("#chooseProdTable").joyintable({
    	data: datas1,
    	page : false,
	    searchOnInit: true,
    	cellMinWidth:100,
        cols:[{field:'prodId',title: '产品代码',width:200,fixed:true}
            ,{field:'prodName',title: '产品名称',width:350,fixed:true}
	        ]
    });
    /**
     * 绑定编辑事件
     */
    previewProdTable.bind('click', '.add', function(data, index) {
    	if($.trim($('#onlyView').val())=='01'){
    		return;
    	}
    	var index=0;
        var obj=chooseProdTable.getAllRows();
        for(var i in obj){
           if(obj[i].prodId==data.prodId){
             index++;
           }
        }
        if(index==0){
        	chooseProdTable.addRow(data);
        }
        $(this).parent().css('background-color','#ffffca');
        $(this).parent()['0'].innerText='已选'
	});
    
	/**
     * 画面加载完成
     */
    $(function() {
    	$('#chooseProdTableDiv').hide();
    	if($.trim($('#onlyView').val())=='01'){
    		$('#ReportWorkManage003_btn_save').hide();
    	}
    	else{
    		$('#ReportWorkManage003_btn_save').show();
    	}
    	//保存绑定点击事件
        $('#ReportWorkManage003_btn_save').click(function(){
        	var param=chooseProdTable.getAllRows();
        	top.getSelectData = param;
        	var index = parent.layer.getFrameIndex(window.name);
    	    parent.layer.close(index);
        })
        // 绑定按钮事件
        $('#ReportWorkManage003_btn_search').click(function() {
        	previewProdTable.load();
        });
    });
	 
});

