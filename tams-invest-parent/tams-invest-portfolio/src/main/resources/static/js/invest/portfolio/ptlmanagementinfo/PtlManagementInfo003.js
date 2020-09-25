(function() {
    /**
     * 加载树结构
     */
    $('#tree').joyinTree({
        onTreeLoadSuccess: function () {
            if(pageModel == PAGE_MODEL_REFERENCE) {
                var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
                var nodes = treeObj.getNodes();
                for(var i=0; i < nodes.length;i++) {
                    if( $('#allocationWay').val() == '01'){
                        var node = nodes[i];
                        DisabledNode(treeObj, node);
                    }
                }
            }
        }
    });
    
    function expandAll() {
        $.fn.zTree.getZTreeObj('tree_tree').expandAll(!$('#expandAll').is(':checked'));
    };
    
    $('#portfolioId').change(function(){
        $('#tree').reloadTree({portfolioId:$('#portfolioId').val()});
    })
    
    function DisabledNode(treeObj, node) {
        treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    }
    /**.
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            userId:$('#userId').val(),
            filter:$('#filter').val()
        }
    };

    /**
     * 分配方法改变事件
     */
    $('#allocationWay').change(function(){
        if($('#allocationWay').val() != null && $('#allocationWay').val() == '02'){
            $('#portfolioId').val('').trigger('change');
            $('.user').css('display','');
            $('.portfolio').css('display','none');
            $('.filter').css('display','none');
        }else if($('#allocationWay').val() != null && $('#allocationWay').val() == '01'){
            //$('#userId').val('').trigger('change');
            $('.user').css('display','none');
            $('.portfolio').css('display','');
            $('.filter').css('display','none');
            $('#filter').val('');
        }else{
            $('#portfolio').val('').trigger('change');
            $('.user').css('display','none');
            $('.filter').css('display','none');
            $('#filter').val('');
            $('.portfolioId').css('display','none');
            $('#userId').val('').trigger('change');
        }
        
        
    })
    
    //获取选择的列
    var getCheckedRows = function() {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
        }
        if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var portfolioIdList = '';
        for(var i = 0; i<selArr.length;i++){
            portfolioIdList +=' ' + selArr[i].portfolioId;
        }
        var object = {selectedItem: selArr, operateType:"delete"};//请求参数
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/check', object, function(result) {//数据验证
            Joyin.confirm(JoyinUtil.getMessage('COMMON_C_0001',JoyinUtil.getMessage(JoyinUtil.getLabel("SysCommon.delete"))+'['+portfolioIdList+']'), function(){
                WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo001/delete', object, "PtlManagementInfo001_btn_delete", "PtlManagementInfo002");//删除
            });
        });
    }
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/getPortPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        page:false,
        loadonce:true,
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:data.portfolioId});//主键组合代码
        },
        /**
         * 定义表格名称
         */
        height:350,
        cols:[
            {field:'portfolioId', title: 'PtlManagementInfo001.portfolioId',sortable:false}//组合代码
            ,{field:'portfolioName', title: 'PtlManagementInfo001.portfolioName',sortable:false}//组合名称
        ],
        done: function(tables, allData){
            if(table.doneChecked) {
                return;
            }
            table.doneChecked = true;
            var allRows = tables;
            var object = {userId:$('#userId').val()};
            if(pageModel != PAGE_MODEL_NEW){
                $('#dataTable').next().find(':checkbox').prop('disabled',false);
                if(portData){
                    for(var i in portData){
                        for(var j in allRows){
                            if(allRows[j].portfolioId === portData[i].portfolioId){
//                                table.selectRow(allRows[j]);
                                table.checkRow(j);
                            }
                        }
                    }
                }
                if(pageModel == PAGE_MODEL_REFERENCE){
                    $('#dataTable').next().find(':checkbox').prop('disabled',true);
                    layui.$('#dataTable').next().find(':checkbox[lay-filter="layTableAllChoose"]').remove();
                }
            }else{
                Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/getSelectedport', object, function(result) {
                    for(var i in allRows){
                        for(var j in result){
                            if(allRows[i].portfolioId === result[j].portfolioId){
                                table.checkRow(i);
                            }
                        }
                    }
                });
            }
        }
    });
    
    var init = function(){
        if(pageModel != PAGE_MODEL_NEW){
            $('#allocationWay').attr('disabled',true);
        }
        if(pageModel != PAGE_MODEL_NEW && $('#allocationWay').val() == '02'){
            table.load();
        }
        
//        if(pageModel == PAGE_MODEL_REFERENCE){
//            $('#tree').attr('disabled',true);
//        }
    }
    
    /**
     * 
     * @param operateType
     * @returns
     */
    function saveOrSubmit(operateType) {
        var allocationWay = null; 
        var object = $.serializeToObject('#submitForm');
        if(object.allocationWay == '01'){
            var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
            var nodes = treeObj.getCheckedNodes();
            var params = new Array();
            for (var i = 0; i < nodes.length; i++) {
                getParam(nodes[i], params);
            }
            if(params.length == 0){
                params = null;
            }
            object.portfolioName = $('#portfolioId').text().trim();
            object.userList = params;
            object.pageModel = pageModel;
            object.operateType = operateType;
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/save', object, "PtlManagementInfo003_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/save', object, "PtlManagementInfo003_btn_submit");
                    }
                });
            });
        }else if(object.allocationWay == '02'){
            var selArr = table.getAllCheckedRows();//获取选择的数据
            if($('#userId').val() == null && $('#userId').val() == ''){
                Joyin.error(JoyinUtil.getMessage("FW_E_0006",JoyinUtil.getLabel("PtlManagementInfo003.user")));
                return;
            }
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0002
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else{
                for(var i in selArr){
                    selArr[i].userId = $('#userId').val();
                }
            }
//            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/insertUser', {selectedList:selArr},function(){
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
//            });
            object.pageModel = pageModel;
            object.operateType = operateType;
            object.selectedList = selArr
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/save', object, "PtlManagementInfo003_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo003/save', object, "PtlManagementInfo003_btn_submit");
                    }
                });
            });
        }else{
            Joyin.error(JoyinUtil.getMessage("FW_E_0006",JoyinUtil.getLabel("PtlManagementInfo003.allocationWay")));
        }
    }
    /**
     * 递归获取节点和父节点数据
     */
     function getParam(node, params) {
        if (node) {
            if(node.id != null && $.inArray(node.id, params) < 0){
                params.push(node.id);
            }
            getParam(node.getParentNode(), params);
        }
    }
     /**
      * 组合筛选变化事件
      */
     $('#PtlManagementInfo003_btn_001').on('click',function(){
         table.doneChecked = true;
         var rows = table.getAllCheckedRows();
         table.reload();
         var newRows = table.getAllRows();
         $.each(newRows,function(k,v){
             $.each(rows,function(m,n){
                 if(n && v.portfolioId === n.portfolioId){
                     table.checkRow(k);
                     rows.splice(m,1);
                 }
             });
         })
         for(var row in rows){
             //table.addRow(rows[row]);
             table.checkRow(row);
         }
     })
    
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定查询按钮
        $('#userId').change(function() {
            if($('#userId').val() == null || $('#userId').val() == ''){
                var rows = table.getAllRows();
                for(var row = rows.length;row >= 0;row--){
                    table.removeRow(rows[row]);
                }
            }else{
                $('.filter').css('display','');
                table.doneChecked = false;
                table.load();//加载表格
            }
        });
        if ($('#allocationWay').val()) {
            $('#allocationWay').change();
            if ($('#portfolioId').val()) {
                $('#tree').reloadTree({portfolioId:$('#portfolioId').val()});
            }
        }
        $('#expandAll').next().on('click',function(){
            expandAll();
        });
        $('#PtlManagementInfo003_btn_save').on('click',function(){
            saveOrSubmit('save');
        })
        $('#PtlManagementInfo003_btn_submit').on('click',function(){
            saveOrSubmit('submit');
        })
        });
}());