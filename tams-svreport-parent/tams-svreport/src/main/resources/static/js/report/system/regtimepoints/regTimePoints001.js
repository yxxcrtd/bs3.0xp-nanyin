$(function() {

    var table=$('#RegTimePoints001_submitPointManeInquiry').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                // 报表名称
            	var reportCode = $('#RegTimePoints001_reportCode').val();
            	if (reportCode) {
            		reportCode = reportCode.join();
            	}
                return {reportCode: reportCode};
            }
        },
        checkbox : false,
        searchOnInit: true, //初始化的时候就请求后台数据
        page: false, // 不分页
        rowno:true, // 显示行号
//        initSort: {
//        	field: 'reportCode',
//        	type: 'desc'
//        },
    	height:function(){
 	       var offset = $("#RegTimePoints001_submitPointManeInquiry").offset().top + 30;
 	       return 'full-' + (offset + $('#RegTimePoints001_buttonArea').find('label').height());
    	}(),
        cols: [
            { title: "报表名称", field:'reportCode'},
            { title: "细分类型", field:'typeDetail'},
            { title: "报送要求说明", field:'submitRequire'},
            { title: "行内报送时点要求", field:'submitPointBond'},
            { title: "监管报送时点要求",field:'submitPointBank'},
            { title: "更新时间",field: 'updateTime', type:'datetime',width:200}
        ]
    });
    // 查询按钮点击事件
    $('#RegTimePoints001_btn_search').click(function() {
        if($('#RegTimePoints001_searchForm').joyinvalid()) {
            table.load();
        }
    });

    // 保存按钮点击事件
    $('#RegTimePoints001_btn_save').click(function() {
        var param={};
        param.updateSubmitPoint=$("#RegTimePoints001_updateSubmitPoint").val();
        param.deleteSubmingPoint=$("#RegTimePoints001_deleteSubmitPoint").val();
        Joyin.ajax('saveData', param,function(result){
            if (result && result.statuCode == "200"){
                Joyin.alert("保存成功");
            }
        });
    });
});