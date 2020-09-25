/*
 * 客户性质修改 
 */
$(function() { 
            $('#customerId').attr("disabled",true);
            $('#customerName').attr("disabled",true);
    function saveOrSubmit(operateType) {
        var selectedItem = table.getAllRows();
        var selectedItemArray=[];
        $.each(selectedItem, function(idx,v) {
            selectedItemArray.push(v.customerCharater);
        });
        var object = $.serializeToObject('#submitForm');
        for(var i=0;i<selectedItemArray.length;i++){
            for(var j=i;j<selectedItemArray.length;j++){
                if (selectedItemArray[i] == undefined){
                Joyin.error(JoyinUtil.getMessage("MST_CUSTOMER_NATURE_0001", JoyinUtil.getLabel("MstCustomerNature003.customerCharater")));
                return; 
                } else if (selectedItemArray[i]==selectedItemArray[j+1]){
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0006", JoyinUtil.getLabel("MstCustomerNature003.customerCharater")));
                    return; 
            }
            }
        }
        $.each(selectedItem, function(idx,v) {
            if(v.customerCharater === CUSTOMER_NATURE_01){
                object.isIssuer="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_03){
                object.isAssteManager="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_04){
                object.isTruster="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_02){
                object.isFinancier="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_05){
                object.isGuarantee="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_07){
                object.isSaler="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_08){
                object.isDepositBank="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_09){
                object.isInvestAdviser="Y";
            }
            if(v.customerCharater==CUSTOMER_NATURE_06){
                object.isPledgor="Y";
            }
        });
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.selectedItem = selectedItem;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcustomernature/MstCustomerNature003/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstcustomernature/MstCustomerNature003/save', object, "MstCustomerNature003_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstcustomernature/MstCustomerNature003/save', object, "MstCustomerNature003_btn_submit");
                }
            });
        });
    };
    
    /**
     * 页面加载初始化
     */
    var options = {
        page: true,
        checkbox: false,
        addbtn: true,
        searchOnInit: true,
        height:'225px',
        resize:true,
        data:[],
        cols:[
            {field:'customerCharater',width:'90%', title: 'MstCustomerNature003.customerCharater',edit:true, sortable:false, editOption:{type:'select',result: CUSTOMER_NATURE}}
            ,{field:'operation', width:'10%',title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ],
    };

    if(entityData.isIssuer=='Y'){
    	options.data.push({"customerCharater": CUSTOMER_NATURE_01});
    }
    if(entityData.isTruster=='Y'){
    	options.data.push({"customerCharater": CUSTOMER_NATURE_04});
    }
    if(entityData.isAssteManager=='Y'){
    	options.data.push({"customerCharater": CUSTOMER_NATURE_03});
    }
    if(entityData.isFinancier=='Y'){
    	options.data.push({"customerCharater": CUSTOMER_NATURE_02});
    }
    if(entityData.isGuarantee=='Y'){
    	options.data.push({"customerCharater": CUSTOMER_NATURE_05});
    }
    if(entityData.isSaler=='Y'){
        options.data.push({"customerCharater": CUSTOMER_NATURE_07});
    }
    if(entityData.isDepositBank=='Y'){
        options.data.push({"customerCharater": CUSTOMER_NATURE_08});
    }
    if(entityData.isInvestAdviser=='Y'){
        options.data.push({"customerCharater": CUSTOMER_NATURE_09});
    }
    if(entityData.isPledgor=='Y'){
        options.data.push({"customerCharater": CUSTOMER_NATURE_06});
    }
    /*
     * 初始化表格
     */
    var table = $("#dataTable").joyintable(options);
    
    /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    /**
     * 画面加载完成
     */
    // 绑定按钮事件
    $('#MstCustomerNature003_btn_save').click(function() {
        saveOrSubmit('save');
    });
    $('#MstCustomerNature003_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
}());