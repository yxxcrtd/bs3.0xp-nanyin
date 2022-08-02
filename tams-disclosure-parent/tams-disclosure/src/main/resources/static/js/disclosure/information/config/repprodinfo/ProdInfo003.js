(function(){
	console.log(ISSUE_STATUS);
	/**
     * 定义数据表格
     */
    var table=$("#ProdInfo003_dataTable").joyintable({
    	ajax: {
            url: JoyinUtil.getRealPath('disclosure/information/config/repprodinfo/ProdInfo003/queryForList'),
            type: 'POST',
            data: function() {
                return {prodIdStr : prodIdStr};
            }
        },
        searchOnInit : true,
        page: false,
        loadonce: true,
        rowno: true,
        cols:[
            {field:'prodId', visible:false, title: '子产品代码'}
            ,{field:'prodId', visible:false, title: '子产品代码'}
			,{field:'prodId', sortable:false, width:100, title: '子产品代码'}
			,{field:'prodName',sortable:false,width:100,  title: '产品名称'}
			,{field:'issueStatusStr', sortable:false, width:100,title: '发行状态'}
			,{field:'issuingStatusNew', title: '新发行状态', width:100, edit : true, editOption:{type:'select',result: ISSUE_STATUS}}
			,{field:'issueStatusRemark', title: '状态备注', width:200, edit : true,}
        ]
    });

    /**
	 * 提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#issueStatusForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo003/check',object, function(result) {
            Joyin.confirm(result.data, function() {
                Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo003/updateIssuingStatus', object, function (result){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            });
        })
	}

	$(function() {
        // 提交按钮执行事件
        $('#ProdInfo003_btn_submit').click(function(){
        	onSaveOrSubmitAction('submit');
        })
	});
}());