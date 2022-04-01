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
        data.operateType='edit'
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'chb/boksubject/boksubject002/check', data, function(result) {
            Joyin.confirm("请确认是否修改?", function(){
                Joyin.dialog(JoyinUtil.getLabel("CBokSubject002"),
                    basePath + "chb/boksubject/boksubject002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
            });
        });

    });

     /**
         * 绑定删除事件
         */
        table.bind('click', '.delt', function(data, index) {
        	var object = {selectedItem: [data], operateType:"delete"};
            Joyin.ajaxJson(basePath + 'chb/boksubject/boksubject001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'chb/boksubject/boksubject001/delete', object, "BokSubject001_btn_delete", "CBokSubject002",function(){
                        table.reload();
                    });
                });
            });
        });
    var exportExcel = function(){
        //modify by yanya on 20210209 增加按照页面选择数据进行导出
        //Joyin.exportExcel('export',getSearchParams())
        var selArr = [];
        var datas = null;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getPageParams();
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
      //新建
      $('#BokSubject001_btn_add').click(function() {
          Joyin.dialog(JoyinUtil.getLabel("SysCommon.create")+JoyinUtil.getLabel("CBokSubject002"),
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
            Joyin.ajaxJson(basePath + 'chb/boksubject/boksubject001/check', object, function(result) {
               Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                   WorkFlow.doDeleteList(basePath + 'chb/boksubject/boksubject001/delete', object, "BokSubject001_btn_delete", "CBokSubject002",function(){
                       table.reload();
                   });
               });
           });
      });
      /**
       * 导入按钮
       */
      $('#BokSubject001_btn_import').click(function() {
          Joyin.uploadFile(function(fileName) {
              Joyin.ajax(basePath +"chb/boksubject/boksubject001/importFile", {
                  fileName : fileName
              },function(result){
                  if(!result || !($.isArray(result) && result.length > 0)){
                      Joyin.ajax(
                              basePath + 'chb/boksubject/boksubject001/importFileSave',
                              {fileName : fileName},function (data1) {
                                  layui.layer.closeAll()
                                  table.reload();
                        });
                  }
//                  else if($.isArray(result) && result.length > 0 && result[0].rowNum != "") {
//                      Joyin.ajax(
//                              basePath + 'chb/boksubject/boksubject001/duplicateCheck',
//                              {fileName : fileName},function (data) {
//                                  if(result.length == data.length){
//                                      Joyin.confirm("导入文件存在"+data.length+"条数据重复，重复数据不导入，是否确认？", function(){
//                                          Joyin.ajax(
//                                                  basePath + 'chb/boksubject/boksubject001/importFileSave',
//                                                  {fileName : fileName},function (data1) {
//                                                      layui.layer.closeAll()
//                                                      table.reload();
//                                                      Joyin.alert("文件导入成功！", 0);
//                                            });
//                                      }, {}, function() {
//                                          layui.layer.closeAll()
//                                          table.reload();
//                                      });
//                                  }
//                        });
//                  }
              });
          });
      });
    });
}());