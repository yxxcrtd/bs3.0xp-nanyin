$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            bond:$.trim($('#bond').val()),
            couponType:$.trim($('#couponType').val()),
            bondType:$.trim($('#bondType').val()),
            vDateBegin: $('#vDate').startDate(),
            vDateEnd: $('#vDate').endDate(),
            mDateBegin: $('#mDate').startDate(),
            mDateEnd: $('#mDate').endDate(),
            workflowId:workflowId
        }
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: basePath + 'valuation/assets/bondasset/ValBondassetquery001/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }    
        },
        //cellMinWidth : 120,
        searchOnInit: false,
        dblclick:function(table, data) {
          var selChe = table.getAllCheckedRows();
          if(selChe.length > 1){
              alert("请选择一条记录进行操作！");
              return;
          }
            Joyin.dialog(JoyinUtil.getLabel("查看债券类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/bondasset/ValBondassetquery002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE, finprodId:data.finprodId, couponType:data.couponTypeCode},
                    function(){
                        //table.reload();
                        });
        },
        cols: [
            {field:'bondid',width:106,title: '债券代码', sort: true},
            {field:'bondname',width:160,title: '债券简称', sort: true},
            {field:'vdate1',width:94,title: '起息日',sort: true},
            {field:'mdate1',width:94,title: '到期日',sort: true},
            {field:'couponTypeName',width:90, title: '息票品种', sortField:'couponType',sort: true},
            {field:'couponTypeCode', title: '息票品种', sort: true,visible:false},
            {field:'bondtype', width:100,title: '债券分类', sort: true, sortField:'bondtypeCode'},
            {field:'bondtypeCode', title: '债券分类', sort: true,visible:false},
            {field:'rate',width:90, align : "right", title: '当前利率(%)',sort: true},
            {field:'basis',width:140,title: '计息基础', sort: true},
            {field:'price',width:60, type:"money", title: '发行价', sort: true},
            {field:'finprodName', width:400,title: '债券名称', sort: true},
            {field:'finprodId', title: '资产代码', sort: true,visible:false},
            {field:'operation', title: 'SysCommon.operation', type:'action',width:80,buttons:['edit']}
        ] 
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("债券类资产修改"),
                basePath + "valuation/assets/bondasset/ValBondassetquery002/init",
                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,  finprodId:data.finprodId, couponType:data.couponTypeCode},function(){
                });
    });
    
    /**
     * 导出
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询事件
        $('#ValBondassetquery001_btn_search').click(function() {
            table.load();
        });
        // 绑定导出事件
        $('#ValBondassetquery001_btn_export').click(function() {
            exportExcel();
        });
        // 绑定删除事件
        $('#ValBondassetquery001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length != 1) {
                Joyin.error('请选择一条记录');
                return;
            }
            
            // 启动删除流程
            var object = selArr[0];
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/assets/bondasset/ValBondassetquery001/doDeleteCheck', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(
                             basePath + 'valuation/assets/bondasset/ValBondassetquery001/delete', 
                             object, 
                             "ValBondassetquery001_btn_delete", 
                             "ValBondassetquery003",
                             function() {
                                 table.reload();
                             }
                         );
                });
                
            });
        });
    });
}());