(function() {

    var getPageParams = function() {
        return {
            employeeType:$('#employeeType').val()==null?null:$('#employeeType').val().join(),
            status:$('#status').val()==null?null:$('#status').val().join()
        };
    };

    var table = $('#ChbEmployeeInfo001_prorate').joyintable({
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
            { title: "从业人员类型", field:'employeeTypeStr',width:'8%',sortField:'employeeType'},
            { visible:false, field:'employeeType'},
            { title: "姓名", field:'employeeName',width:'8%',sortField:'employeeName'},
            { title: "性别", field:'employeeSexStr',width:'6%',sortField:'employeeSex'},
            { visible:false, field:'employeeSex'},
            { title: "发行机构代码", field:'issuePartyId',width:'10%',sortField:'issuePartyId'},
            { title: "身份证号码", field:'identifyNumber',width:'10%',sortField:'identifyNumber'},
            { title: "所属总行或分支行", field:'belongsBankStr',width:'10%',sortField:'belongsBank'},
            { field:'belongsBank',visible:false,width:'10%'},
            { title: "所属区域", field:'belongsPlaceStr',width:'10%',sortField:'belongsPlace'},
            { field:'belongsPlace',visible:false,width:'10%'},
            { title: "具体单位名称", field:'companyName',width:'10%',sortField:'companyName'},
            { title: "所属部门", field:'belongsDept',width:'10%',sortField:'belongsDept'},
            { title: "职位", field:'position',width:'10%',sortField:'position'},
            { title: "学历", field:'educationStr',width:'10%',sortField:'education'},
            { field:'education',visible:false,width:'10%'},
            { title: "学位", field:'academicDegreeStr',width:'10%',sortField:'academicDegree'},
            { field:'academicDegree',visible:false,width:'10%'},
            { title: "首次参加工作时间", field:'firsttime2work',width:'8%',sortField:'firsttime2work'},
            { title: "首次从事理财业务时间", field:'firsttime2finwork',width:'12%',sortField:'firsttime2finwork'},
            { title: "专业技术职称", field:'professionalTitleStr',width:'8%',sortField:'professionalTitle'},
            { field:'professionalTitle', visible:false },
            { title: "理财专业证书", field:'finProfessionalCrtfStr',width:'8%',sortField:'finProfessionalCrtf'},
            { field:'finProfessionalCrtf', visible:false },
            { title: "理财登记培训证书编号", field:'finTrainingNo',width:'8%',sortField:'finTrainingNo'},
            { title: "所获奖励", field:'awards',width:'8%',sortField:'awards'},
            { title: "办公电话", field:'officeTel',width:'8%',sortField:'officeTel'},
            { title: "移动电话", field:'mobileTel',width:'8%',sortField:'mobileTel'},
            { title: "电子邮箱", field:'mail',width:'8%',sortField:'mail'},
            { title: "登记业务分类", field:'regBusiTypeStr', width:'8%',sortField:'regBusiType'},
            { field:'regBusiType', visible:false },
            { title: "登记人员类别", field:'regEmployeeTypeStr', width:'10%',sortField:'regEmployeeType'},
            { field:'regEmployeeType', visible:false },
            { title: "备注", field:'remark', width:'10%',sortField:'remark'},
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
            if(data.identifyNumber == null || data.identifyNumber == '' || data.identifyNumber == undefined){
                Joyin.error("身份证号码为空,数据不允许编辑");
                return;
            }else{
                data.pageModel = PAGE_MODEL_EDIT;
                data.type="update";
                Joyin.dialog(JoyinUtil.getLabel("编辑从业人员信息"),
                    basePath + "svreport/system/chbemployee/ChbEmployeeInfo002/init",
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
            Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });

    };

    $(function() {
        // 导出按钮点击事件
        $('#ChbEmployeeInfo001_btn_export').click(function() {
            var param ={};
            var rows = table.getAllCheckedRows();
            if(rows.length > 0) {
                var keyList = [];
                $.each(rows, function(i, row) {
                    keyList.push(row.employeeType+','+row.identifyNumber+" ");
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
        $('#ChbEmployeeInfo001_btn_delete').click(function() {
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
            Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });
        $('#ChbEmployeeInfo001_btn_search').click(function() {
            table.load();
        });
        //新增点击事件
        $('#ChbEmployeeInfo001_btn_add').click(function() {
            var data={"operateType":"add"};
            Joyin.dialog('从业人员数据信息新建',basePath+'svreport/system/chbemployee/ChbEmployeeInfo002/init', '98%', '95%',data,function(){table.reload();});
        });

        //修改、删除事件绑定
        tableBind();
    });
}());