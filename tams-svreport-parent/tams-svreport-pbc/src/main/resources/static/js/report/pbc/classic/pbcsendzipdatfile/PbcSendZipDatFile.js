$(function() {
  //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //报送类型
            datZipType : $('#datZipType').val()==null?null:$('#datZipType').val().join(","),
            //发送状态
            zipSendStatus : $('#zipSendStatus').val()==null?null:$('#zipSendStatus').val().join(","),
            //反馈状态
            returnZipSendStatus: $('#returnZipSendStatus').val()==null?null:$('#returnZipSendStatus').val().join(","),
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
//        showDetail: true,//双击详情对比画面
//        done : function(){
//            if(validKey){
//                table.resize($(document).height() - $('.container').height() + table.getHeight());
//              //标志是否在当前页选中数据
//                var isChecked = false;
//                var addTableData = table.getAllRows();
//                if(addTableData){
//                    $(addTableData).each(function(i,v){
//                        if(validKey.prdRegistCode===v.prdRegistCode && validKey.indentifyMark===v.indentifyMark
//                                && (Date.ofDate(validKey.holdDate).getTime()) === (Date.ofDate(v.holdDate).getTime()) 
//                                && validKey.ccy===v.ccyCode 
//                                && validKey.registBankCode===v.registBankCode
//                                && v.sendStatusCode==='01'){
//                            table.checkRow(v.LAY_TABLE_INDEX);
//                            isChecked = true;
//                            dateCheckMethod();
//                            return false;
//                        }
//                    })
//                }
//                if(!isChecked){
//                    pageCount++;
//                    table.reload({page:{curr:pageCount}});
//                }
//            }
//        
//        },
        cols:[
            { title: "压缩包编号", field: 'datZipCode',width:200,sortable:false},
            { title: "报送类型", field: 'datZipType',width:100, type: 'datetime',sortable:false},
            { title: "报送明细类型", field: 'datZipDatType',width:150,sortable:false},
            { title: "业务日期", field: 'submitTime',width:100, type: 'datetime',sortable:false},
            { title: "DAT明细数", field: 'pibDetailCnt',width:100, type: 'datetime',sortable:false},
            { title: "发送状态", field: 'zipSendStatusStr',width:100,sortable:false},
            { title: '反馈状态',field:'returnZipSendStatusStr',width:100,sortable:false},
            { title: '反馈消息',field:'returnMsg',width:150,sortable:false},
            { title: '压缩包放置路径',field:'datZipPath',width:150,sortable:false},
            {
                title : '操作',
    			field : 'operation',
    			type : 'action',
    			width : 150,
    			buttons : [ 
    			{
    				text : '&#xe615;',
    				clas : 'batch',
    				title : '预览'
    			} ] 
    			}

        ]
    });
       $(function(){
           // 绑定按钮事件
           $('#PbcSendZipDatFile_btn_search').click(function() {
               table.load();
           });
       });
}());