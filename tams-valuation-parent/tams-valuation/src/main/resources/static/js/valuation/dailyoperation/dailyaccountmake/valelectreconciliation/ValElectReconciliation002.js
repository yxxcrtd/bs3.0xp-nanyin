$(function() {
    
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
		    displayRange : $.trim($('#displayRange').val()),
		    subjectLevel : $.trim($('#subjectLevel').val()),
		    serialNo : $.trim($('#serialNo').val()),
		    interType : $.trim($('#interType').val()),
		}
	};
	
	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'post',
			data : function(){
				return getSearchParams();
			}
		},
        checkbox: false,
        searchOnInit: false,
        cellMinWidth:100 ,
        isMulti:true,
        cols: [[
//                {align: "center", title: ' ', align:"center", colspan: 1},
                {align: "center", title: '科目号', align:"center", colspan: 2},
                {align: 'center', title: '科目名称', align:'center', colspan: 2},
                {align: 'center', title: '数量', align:'center', colspan: 3},
                {align: 'center', title: '成本', align:'center', colspan: 3},
                {align: 'center', title: '市值', align:'center', colspan: 3},
                {align: 'center', title: '估值增值', align:'center', colspan: 3}
            ],[
//                {field:'index', width:80,  title: ' ', align:'center'},
                {field:'fxmdmb', width:120,  title: '本方', align:'center'},
                {field:'fxmdmd', width:120,  title: '对方', align:'center'},
                {field:'bsubjectName', width:120,  title: '本方', align:'center'},
                {field:'dsubjectName', width:120,  title: '对方', align:'center'},
                {field:'bsl', type:"number", width:120,  title: '本方', align:'center'},
                {field:'dsl', type:"number", width:120,  title: '对方', align:'center'},
                {field:'csl', type:"number", width:120,  title: '差额', align:'center'},
                {field:'bje',type:"money",digit:6, width:120,  title: '本方', align:'center'},
                {field:'dje', type:"money", digit:6,width:120,  title: '对方', align:'center'},
                {field:'cje', type:"money", digit:6,width:120,  title: '差额', align:'center'},
                {field:'bjeo', type:"money",digit:6,width:120,  title: '本方', align:'center'},
                {field:'djeo', type:"money", digit:6,width:120,  title: '对方', align:'center'},
                {field:'cjeo', type:"money", digit:6,width:120,  title: '差额', align:'center'},
                {field:'bjet', type:"money", digit:6,width:120,  title: '本方', align:'center'},
                {field:'djet',type:"money", digit:6,width:120,  title: '对方', align:'center'},
                {field:'cjet', type:"money",digit:6, width:120,  title: '差额', align:'center'}
            ]]
	});

	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValElectReconciliation002_btn_search').click(function() {
             if(!table.table) {
                 table.load();
              } else {
                  table.reload();
              }
        });       
    });
}());