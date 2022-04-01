(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            fsubjectId: $('#fsubjectId').val()
            ,subjectLevel: $('#subjectLevel').val()
            ,subjectNo: $('#subjectNo').val()
            ,subjectName: $('#subjectName').val()
            ,subjectType: $('#subjectType').val()
            ,status:$('#status').val()
        }
    };
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
		searchOnInit: false,
		cellMinWidth:100 ,
		cols: [
		    {field:'subjectNo', width:100, title: 'BokSubject.subjectNo'}
		    ,{field:'subjectName', width:180, title: 'BokSubject.subjectName'}
		    ,{field:'fsubjectId', width:120, title: 'BokSubject.fsubjectId'}
		    ,{field:'subjectType', width:120, sortField:"subjectType", title: 'BokSubject.subjectType'}
		    ,{field:'subjectLevel', width:100, title: 'BokSubject.subjectLevel'}
		    ,{field:'dataSource', width:100, title: 'BokSubject.dataSource'}
		    ,{field:'status', width:100, title: 'BokSubject.status'}
		    ,{field:'remark', width:150, title: 'BokSubject.remark'}
		    ,{field:'describe', width:150, title: 'BokSubject.describe'}
		    ,{field:'updateDate', width:100, title: 'BokSubject.updateTime'}
		    ,{field:'updateUser', width:100, title: 'BokSubject.updateUser'}
		    ,{field:'action', title: 'SysCommon.operation' , type:'action',buttons: ['edit', 'delt'] , width:100}
		]
	  });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("科目信息编辑"),
                basePath + "valuation/accounadjustmanage/subjectset/ValSubjectSet002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
                                booksetId:data.booksetId,
                                subjectNo:data.subjectNo},function(){
                                    table.reload();
                                });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'chb/boksubject/boksubject001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/subjectset/BokSubject001/delete', object, function(result) {
                    table.reload();
                });
            });
        });
    });

    /**
     * 画面加载完成
     */
    $(function() {
      //新建
      $('#BokSubject001_btn_add').click(function() {
          Joyin.dialog(JoyinUtil.getLabel("SysCommon.create")+JoyinUtil.getLabel("BokSubject002"),
                  basePath + "chb/boksubject/boksubject002/init",
                  "95%", "80%", {pageModel:PAGE_MODEL_NEW});
      });
      // 绑定按钮事件
      $('#BokSubject001_btn_search').click(function() {
          table.load();
          $('#BokSubject001_btn_delete').show();
      });
      $('#BokSubject001_btn_export').click(function() {
          exportExcel();
      });
      $('#BokSubject001_btn_delete').click(function() {
          if(table!=null && table!=''){
              var selArr = table.getAllCheckedRows();
          }
          if (!selArr || selArr.length == 0) {
              Joyin.error(JoyinUtil.getMessage("没有选中数据"));
              return;
          }
          var object = {selectedItem: selArr, operateType:"delete"};
          Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/subjectset/BokSubject001/check', object, function(result) {
              Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                  Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/subjectset/BokSubject001/delete', object, function(result) {
                      table.reload();
                  });
              });
          });
      });
    });
}());