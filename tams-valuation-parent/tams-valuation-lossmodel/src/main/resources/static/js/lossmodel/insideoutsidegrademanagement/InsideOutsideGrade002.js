$(function () {
        var init=function() {
            if(pageModel == PAGE_MODEL_EDIT || pageModel == PAGE_MODEL_REFERENCE){
                $("#gradeInst").attr("disabled", true);
                $("#oType").attr("disabled", true);
            }
        };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        data:detailList != null ? detailList : [],
        searchOnInit:true,
        checkbox: false,
        height:'330px',
        cols:[
              {field:'gradeResult',title: '外部评级',sortable:false, edit:true, editOption:{type:'text'}} // initValue:ACCT_TYPE_A05
             ,{field:'gradeStandardResult',title: '内部评级',edit:true,sortable:false,editOption:{type:'select',formatoption:true,result: MODEL_STANDARD_GRADE_RESULT}} // ,initValue:IS_OPEN_01
             ,{field:'operation', title: 'SysCommon.operation',sortable:false,style:'text-align:left;', type:'action', buttons: ['delt']}
         ],
         addbtn:true,
         editCell: function (value, row, field, cell, column) {
             if (column.field == 'gradeResult') {
                var flag = true;
                debugger;
                 // 控制外部评级的唯一性
                 if (typeof value != "undefined" && value) {
                        flag = checkUniqueData(value,column,row);
                        if(!flag){
                            Joyin.error('该外部评级已配置，请重新输入！');
                            row.gradeResult = null;
                            table.renderRow(row.LAY_TABLE_INDEX,row);
                        }else{
                             table.renderRow(row.LAY_TABLE_INDEX,row);
                        }
                        return flag;
                 }
             }else if(column.field == 'gradeStandardResult'){
                  // 控制内部评级的唯一性
                   if (typeof value != "undefined" && value) {
                        flag = checkUniqueData(value,column,row);
                        if(!flag){
                            Joyin.error('该内部评级已配置，请重新选择！');
                            setTimeout(function() {
                                row.gradeStandardResult = null;
                                table.renderRow(row.LAY_TABLE_INDEX,row);
                            }, 1000);
                        }else{
                             table.renderRow(row.LAY_TABLE_INDEX,row);
                        }
                        return flag;
                   }
             }
         }
    });


var checkUniqueData = function(value,column,row){
    var selArr = table.getAllRows();
    var flag = true;
    if (column.field == 'gradeResult') {
        for(var i =0;i<selArr.length;i++){
            if(selArr[i].LAY_TABLE_INDEX !=row.LAY_TABLE_INDEX && selArr[i].gradeResult == value){
                flag = false;
                break;
            }
        }
    }else if (column.field == 'gradeStandardResult') {
      for(var i =0;i<selArr.length;i++){
          if(selArr[i].LAY_TABLE_INDEX !=row.LAY_TABLE_INDEX && selArr[i].gradeStandardResult == value){
              flag = false;
              break;
          }
      }
    }
    return flag;
}

/**
     * 账号删除事件
     */
    var acctNoDel = function(table,data,index){
            if(pageModel==PAGE_MODEL_NEW || pageModel==PAGE_MODEL_EDIT){
                table.deleteRow(index);
            }
        }

    /**
     * 表格一的删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        acctNoDel(table,data,index);
    });

	/**
	 * 根据操作类型显示
	 */
    var initShow = function () {
      var pageModel = $('#pageModel').val();
      var status = $('#status').val();
    };

function serializeTable(tables){
        if(tables!=null && tables!=''){
            var selArr = tables.getAllRows();
        }
        if (!selArr || selArr.length == 0) {
            return;
        }else{
             for(var j=selArr.length-1;j>=0;j--){
                 delete selArr[j].LAY_TABLE_INDEX;
             }
         }
        return selArr;
    }

    function checkListData(){
        var selectDto= serializeTable(table);
        if(selectDto == null){
            Joyin.error("评级映射表无数据！");
            return false;
        }else{
            for(var i=0;i<selectDto.length;i++){
                if (selectDto[i].gradeResult == null || selectDto[i].gradeResult == "") {
                    Joyin.error("请输入外部评级！");
                    return false;
                }
                if (selectDto[i].gradeStandardResult == null || selectDto[i].gradeStandardResult == "") {
                    Joyin.error("请输入内部评级！");
                    return false;
                }
            };
        }
        return true;
    }

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#marketForm');
        var selectDto= serializeTable(table);
            object.selectInsideOutsideDetail = selectDto;
            object.operateType = operateType;
            object.pageModel = pageModel;

            // 获取seqNo
            if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
                object.gradeInst = $('#gradeInst').val();
                object.oType = $('#oType').val();
            }

        Joyin.ajaxJson(basePath + 'model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save'){
                    WorkFlow.doSaveList(basePath + 'model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002/save', object, "ValInsideOutsideGrade002_btn_save");
                }else{// submit
                    WorkFlow.doSubmitList(basePath + 'model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002/save', object, "ValInsideOutsideGrade002_btn_submit");
                }
            });
        });
    }

    $(function() {
        initShow();// 根据操作类型显示
        init();
        /**
         * 绑定按钮事件
         */
        $('#ValInsideOutsideGrade002_btn_submit').click(function() {
            var rst = checkListData();
            if(rst){
                saveOrSubmit('submit');
            }
        });
    });
    
});