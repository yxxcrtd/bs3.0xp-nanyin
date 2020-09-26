/**
 * 主动提醒页面
 */
(function() {

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
        	 url: 'findDetail',
             type: 'POST',
        },
        checkbox: false,
        height: 400,
        cols: [
           {field:'bookSetId', title: '账套代码', width: 200,sort: true},
           {field:'bookSetName', title: '账套名称', width: 200,sort: true},
           {field:'bookDate', title: '会计日期', width: 200,sort: true},
           {field:'reminder', title: '提醒信息', width: 500,sort: true}
        ],
        //loadonce:true,
    });
    
    
//    var fn = function () {
//    	
//        if(table.getAllRows().length==0){
//        	document.getElementById("table").style.visibility="hidden"
//        }else{
//        	table.reload();
//        }
//        
//        setTimeout(fn,120000);
//    }
    /**
     * 绑定按钮事件
     */
    $(function() {
    	table.load();
    	setInterval(function() {
        	table.reload();
    	}, 120000);
//    	 setTimeout(function () {
//             fn();
//         }, 500);
    	
    	$(window.parent.document).find("iframe").load(function(){
	    	var main = $(window.parent.document).find("iframe");
	    	var thisheight = $(window.parent.document).height() - 90;
	    	main.height(thisheight);
    	});
        	
        	//document.getElementById("table").style.visibility="hidden";//隐藏
    });
}());
