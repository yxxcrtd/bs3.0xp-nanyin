/**
 * 画面加载完成
 */
$(function() {
	/**
     * 获取查询参数
     */
    var getPageParams = function() {  
        return {
        	parentSeqno : $('#parentSeqno').val() ,
        	tmpName : $('#tmpName').val() ,
        	logStatus : $('#logStatus').val()
        }
    };
    
    /**
     * 表格加载
     */
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'searchList',
            type: 'POST',
            data:  function() {
                return getPageParams();
            }
        },
        searchOnInit: true,
        checkbox: false,
		cols: [
		    {field:'fileName', width:150, title: '文件名称'}
            ,{field : 'exDateFmt',width:120,title : '执行日期',sortField : 'exDate'}
		    ,{field:'num', width:120, title: '明细记录编号'}
            ,{field:'isCheck', width:75, title: '是否通过',
            	render: function (data, type, row, meta) {
                if(data=='Y'){
                    return '<span class="layui-badge-rim layui-bg-green">'+'通过'+'</span>';
                }else if(data=='N'){
                    return '<span class="layui-badge-rim layui-bg-red">'+'失败'+'</span>';
                }
            }}
            ,{field:'detail', width:380, title: '记录内容'}
            ,{field:'remark', width:100, title: '备注'}
		],
		loadonce:true
	});
});