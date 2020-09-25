$(function () { 
    var getTreeParams = function(){
        return {
            TmpCName: $('#TmpCName').val()
        }
    }
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return{
                //报表名称
                reportName: $('#reportName').val(),               
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
        checkbox: true,
        cols:[
             {field:'repId', title: '报表ID',visible:false},
             {field:'reportName', title: '报表名称'},
             {field:'templatePath', title: '模板目录'},
             {field:'templateId', title: '模板编号'}
             
        ],
    });
    $('#typeDatatree').joyinMixTree({  
            onTreeAddSubFunction: function(obj,obj1){
                var PId=obj;
                Joyin.dialog(JoyinUtil.getLabel("增加子级"),
                        basePath + "disclosure/information/config/reporttree/ReportTreeMenuConfiguration003/init",
                        "80%", "70%",{pid:PId},
                        function(){
                                $('#typeDatatree').reloadTree(null);
                            });
            },
            onTreeAddFunction: function(callback){
                var tree = $.fn.zTree.getZTreeObj('typeDatatree_tree');
                var nodes = tree.getSelectedNodes();
                var target = $(event.currentTarget).text();
                var id=nodes[0].id;
                var pId=nodes[0].pId
                if(target.indexOf('增加一级') != -1) {
                    Joyin.dialog(JoyinUtil.getLabel("增加一级"),
                            basePath + "disclosure/information/config/reporttree/ReportTreeMenuConfiguration002/init",
                            "80%", "70%",null,function(){
                            $('#typeDatatree').reloadTree(null);
                    });
                }
                else {
                    if (table != null && table != '') {
                        var selArr = table.getAllCheckedRows();
                        var datas = JSON.stringify(selArr);
                    }
                    Joyin.ajax('getAddAll',
                            {datas:datas,Id:id,PId:pId},function(){
                            $('#typeDatatree').reloadTree(null);
                            });
                } 
            },
           onTreeEditFunction: function(obj,callback){
               var tree = $.fn.zTree.getZTreeObj('typeDatatree_tree');
               var nodes = tree.getSelectedNodes();
               var target = $(event.currentTarget).text();
               var pid=obj.pId == null ? "": obj.pId;
               var id=obj.id;
               var name=obj.name;
               if(target.indexOf('编辑') != -1) {
                   Joyin.dialog(JoyinUtil.getLabel("编辑"),
                           basePath + "disclosure/information/config/reporttree/ReportTreeMenuConfiguration004/init",
                           "80%", "70%",{Name:name,ID:id,pId:pid},
                           function(){
                                   $('#typeDatatree').reloadTree(null);
                               });
               }if(target.indexOf('上移') != -1) {
                   Joyin.ajax('moveFlag',
            			   {Id:id,PId:pid,flag:0},function(){
            				   $('#typeDatatree').reloadTree(null);
            			   });
               }if(target.indexOf('下移') != -1) {
            	   Joyin.ajax('moveFlag',
            			   {Id:id,PId:pid,flag:1},function(){
            				   $('#typeDatatree').reloadTree(null);
            			   });
               }else{
                   Joyin.ajax('edit',
                           {Id:id},function(){
                           $('#typeDatatree').reloadTree(null);
                           });
               }
               
           },
           onTreeDelFunction: function(obj,callback){
               var id=obj.id;
               var pId=obj.pId;
               Joyin.confirm("确定删除吗？",function(){
            	   Joyin.ajax('getDelete',
            			   {Id:id,PId:pId},function(){
            				   $('#typeDatatree').reloadTree(null);
            			   });
            	   
               });
               $('#typeDatatree').reloadTree(null);
           },
           onTreeLoadSuccess: function() {
            $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(true);
            //$.fn.zTree.getZTreeObj('typeDatatree_tree').checkAllNodes(true);
           },
           col:[{text:'批量增加报表',clas:'add',isdefine:true},
                {text:'设置为报表集',clas:'edit',isdefine:true},
                {text:'编辑',clas:'edit',isdefine:true},
                {text:'删除',clas:'del',isdefine:true},
                {text:'增加子级',clas:'addsub',isdefine:true},
                {text:'增加一级',clas:'add',isdefine:true}
                /*{text:'上移',clas:'edit',isdefine:true},
                {text:'下移',clas:'edit',isdefine:true}*/
               ]
    });
        function expandAll() {
        $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(!$('#expandAll').is(':checked'));
    };
    /**
     * 画面加载完成
     */
    $(function() {
     // 绑定按钮事件
        $('#ReportTreeMenuConfiguration001_btn_search').click(function() {
            table.load();
        });
        var tree=$("#typeDatatree");
        var docH= $(document).height(); 
        var tabHeadUlH=100;
        tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");  
//        tree.children('div').eq(1).css("overflow-x", "hidden");
        
    });
});
function serachKey(){
    $('#typeDatatree').reloadTree({
            TmpCName: $('#TmpCName').val()
    });
    
}
