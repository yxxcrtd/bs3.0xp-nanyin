(function() {

    var getPageParams = function() {
        return {
            oCode:$('#oCode').val()==null?null:$('#oCode').val().join(),
            oType:$('#oType').val()==null?null:$('#oType').val().join(),
            status:$('#status').val()==null?null:$('#status').val().join()
        };
    };

    var table = $('#MstBondCreditRating001_prorate').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
            { title: "数据状态", field:'statusName',sortField:'status',width:'5%'},
            { title: "status",  visible:false, field:'status'},
            { title: "债券代码", field:'oCode',width:'8%',sortField:'oCode'},
            { title: "评级机构", field:'gradeOrgIdName',width:'8%',sortField:'gradeOrgId'},
            { field:'gradeOrgId',visible:false,width:'10%'},
            { title: "评级日期", field:'gradeDate',width:'12%',type:'date',sortField:'gradeDate'},
            { title: "评级类型", field:'oTypeName',width:'10%',sortField:'oType'},
            { field:'oType',visible:false,width:'10%'},
            { title: "评级结果", field:'gradeResultName',width:'10%',sortField:'gradeResult'},
            { field:'gradeResult',visible:false,width:'10%'},
            { title: "创建人", field:'createUser',width:'8%',sortField:'createUser'},
            { title: "创建时间", field:'createTime',type:'date',width:'12%',sortField:'createTime'},
            { title: "更新人", field:'updateUser',width:'8%',sortField:'updateUser'},
            { title: "更新时间", field:'updateTime',type:'date', width:'8%',sortField:'updateTime'},
            { title: "数据来源", field:'svrDataSourcesName', width:'10%',sortField:'svrDataSources'},
            { field:'svrDataSources', visible:false },
            {
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 130,
                buttons : [
                    {text: '&#xe642;',clas: 'edit',title: '编辑'},
                    {text: '&#xe640;',clas: 'delt',title: '删除'}
                    ]
            }

        ]
    });

    /**
     * 事件绑定
     */
    var tableBind = function () {
        /**
         * 绑定事件：修改
         */
        table.bind('click', '.edit', function(data, index) {
            if(data.oCode == null || data.oCode == '' || data.oCode == undefined){
                Joyin.error("债券代码为空,数据不允许编辑");
                return;
            }else{
                data.pageModel = PAGE_MODEL_EDIT;
                data.type="update";
                Joyin.dialog(JoyinUtil.getLabel("编辑债券评级信息"),
                    basePath + "svreport/system/mstbond/MstBondCreditRating002/init",
                    "95%", "95%", data,function(){
                        table.reload();
                    });
            }

        });

        /**
         * 绑定事件：删除
         */
        table.bind('click', '.delt', function(data, index) {
            var listdto=[data];
            var error='';
            var s='D';
            for(var i=0; i<listdto.length; i++){
                    if(listdto[i].status.indexOf("D")!= -1){
                    error = "选择的数据中包含数据状态为删除的数据,请重新选择"+"<br>";
                }
            }
            if(error != '' && error != null){
                Joyin.error(error);
                return;
            }
            var object = {selectedItem: listdto, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'svreport/system/mstbond/MstBondCreditRating001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });

    };

    $(function() {
        // 导入按钮点击事件
        $('#MstBondCreditRating001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'svreport/system/mstbond/MstBondCreditRating001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'svreport/system/mstbond/MstBondCreditRating001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });

        // 导出按钮点击事件
        $('#MstBondCreditRating001_btn_export').click(function() {
            var param ={};
            var rows = table.getAllCheckedRows();
            if(rows.length > 0) {
                var keyList = [];
                $.each(rows, function(i, row) {
                    keyList.push(row.oCode+','+row.gradeOrgId+','+row.gradeDate+','+row.oType+" ");
                });
                param.keys = keyList.join();
            }
            else{
                //有勾选优先勾选数据
                param = getPageParams();
            }
            Joyin.exportExcel("export",param);
        });
        //删除点击事件
        $('#MstBondCreditRating001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var errorMessage='';
            for(var i=0; i<selArr.length; i++){
                if(selArr[i].status.indexOf("D")!= -1){
                    errorMessage = "选择的数据中包含数据状态为删除的数据,请重新选择"+"<br>";
                }
            }
            if(errorMessage != '' && errorMessage != null){
                Joyin.error(errorMessage);
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'svreport/system/mstbond/MstBondCreditRating001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });
        $('#MstBondCreditRating001_btn_search').click(function() {
            table.load();
        });
        //新增点击事件
        $('#MstBondCreditRating001_btn_add').click(function() {
            var data={"operateType":"add"};
            Joyin.dialog('债券评级数据信息新建',basePath+'svreport/system/mstbond/MstBondCreditRating002/init', '98%', '95%',data,function(){table.reload();});
        });

        //修改、删除事件绑定
        tableBind();
    });
}());