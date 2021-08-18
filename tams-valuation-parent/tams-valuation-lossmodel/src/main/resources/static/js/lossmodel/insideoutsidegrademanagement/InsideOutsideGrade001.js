$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            gradeInst : $('#gradeInst').val(),
            oType : $('#oType').val(),
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValInsideOutsideGrade001search',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
        cols : [
                {field : 'gradeInst',title : 'gradeInst',visible : false},
                {field : 'oType',title : 'oType',visible : false},
                { title: "评级机构", field: 'gradeInstStr',sortField:'gradeInst',sort : true},
                { title: "对象类型", field: 'oTypeStr',sortField:'oType',sort : true},
                { field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
               ],
        dblclick: function(table, data){
        	Joyin.dialog(JoyinUtil.getLabel("内外部评级映射管理明细"), "/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002/init",
                    "90%", "90%", {gradeInst:data.gradeInst,oType:data.oType, pageModel:PAGE_MODEL_REFERENCE,status:"02"}, function(){
                        //table.reload();
                    }
              );
        }
    })
    /**
     * 绑定编辑事件
     */
     table.bind('click','.edit', function(row,index) {
                 row.pageModel = PAGE_MODEL_EDIT;
                    Joyin.dialog("内外部评级映射管理修改",
                           "/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002/init",
                           "90%", "100%", row,function(){
                       table.reload();
                   });
             });
    /**
     * 新建资产大类评级配置
     */
    $('#ValInsideOutsideGrade001_btn_add').click(
        function() {
            Joyin.dialog(JoyinUtil.getLabel("新建内外部评级映射"), basePath
                    + "model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002/init", "90%",
                    "90%", null,
                    function(){
                        table.reload();
                        pageModel : PAGE_MODEL_NEW
                    },
                    null
            );
    });

   /**
   *删除
   */
    $('#ValInsideOutsideGrade001_btn_delete').click(function() {
       	delRows();
       });
       var delRows = function () {
       	 if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                                WorkFlow.doDeleteList('/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade001/delete', object, "ValInsideOutsideGrade001_btn_delete", "InsideOutsideGrade002", function(){
                                    table.reload();//删除事件请求及回调函数
                                });
                            });
       };

    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams())
    };

        /**
         * 查询
         */
        $('#InsideOutsideGrade001_btn_search').click(function() {
            table.load();
        });
        /**
         * 导出
         */
        $('#ValInsideOutsideGrade001_btn_export').click(function() {
            exportExcel();
        });
});