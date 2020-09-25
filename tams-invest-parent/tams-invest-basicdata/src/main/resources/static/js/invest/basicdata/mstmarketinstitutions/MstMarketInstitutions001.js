$(function(){
	
	/**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
        	customerId: $('#customerId').val(),
        	customerName: $('#customerName').val(),
            isFinLicence: $('#isFinLicence').val(),
        };
    };
	
    /**
     * 表格数据查询
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /*
         * 双击查看数据
         */
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("MstMarketInstitutions002"),
                    JoyinUtil.getRealPath("invest/basicdata/mstmarketinstitutions/MstMarketInstitutions002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
         cols:[
              {field:'customerId', width:'10%', sortField:'CUSTOMER_ID', title: 'MstMarketInstitutions002.customerId'}
              ,{field:'customerName', width:'10%', sortField:'CUSTOMER_NAME', title: 'MstMarketInstitutions002.customerName'}
              ,{field:'industryStr', width:'10%', sortField:'INDUSTRY2', title: 'MstMarketInstitutions002.industry'}
              //,{field:'industry', visible: false}
              ,{field:'industry2Str', width:'10%', sortField:'INDUSTRY2', title: 'MstMarketInstitutions002.industrySecondClass'}
              //,{field:'industry2', visible: false}
              ,{field:'insitituteCode', width:'10%', sortField:'INSITITUTE_CODE', title: 'MstMarketInstitutions002.insitituteCode'}
              ,{field:'isGovStr', width:'10%', sortField:'IS_GOV', title: 'MstMarketInstitutions002.isGov'}
              //,{field:'isGov', visible: false}
              ,{field:'partyType3Str', width:'10%', sortField:'PARTY_TYPE3', title: 'MstMarketInstitutions002.partyType3'}
              //,{field:'partyType3', visible: false}
              ,{field:'partyType2Str', width:'10%', sortField:'PARTY_TYPE2', title: 'MstMarketInstitutions002.partyType2'}
              //,{field:'partyType2', visible: false}
              ,{field:'partyType1Str', width:'10%',  sortField:'PARTY_TYPE1', title: 'MstMarketInstitutions002.partyType1'}
              //,{field:'partyType1', visible: false}
             <!--添加字段'是否持有金融牌照' add by lishuping 20190823 start-->
             ,{field:'isFinLicenceStr', width:'10%', sortField:'IS_FIN_LICENCE', title: 'MstMarketInstitutions001.isFinLicence'}
             <!--添加字段'是否持有金融牌照' add by lishuping 20190823 end-->
              ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
          ]
    });
        
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("MstMarketInstitutions002.edit"),
                    basePath + "invest/basicdata/mstmarketinstitutions/MstMarketInstitutions002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
        						   customerId:data.customerId},function(){table.reload();})
    });
    
    
  /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem : [ data ],operateType : "delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001/check', object, function(result) {
        Joyin.confirm(
                JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
                function() {
                    WorkFlow.doDeleteList(basePath
                            + 'invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001/delete',
                            object, "MstMarketInstitutions001_btn_delete",
                            "MstMarketInstitutions002",function(){table.reload();});
                });
         });
    });
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    $(function() {
        // 绑定按钮事件
        $('#MstMarketInstitutions001_btn_search').click(function() {
            table.load();
            $('#MstMarketInstitutions001_btn_delete').show();
        });
        $('#MstMarketInstitutions001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstMarketInstitutions001_btn_add').click(
                function() {
                    Joyin.dialog(JoyinUtil.getLabel("MstMarketInstitutions002"), basePath
                            + "invest/basicdata/mstmarketinstitutions/MstMarketInstitutions002/init",
                            "95%", "90%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
                });
        $('#MstMarketInstitutions001_btn_delete').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
              var object = {
                      selectedItem : selArr,
                       operateType : "delete"
              };
          Joyin.ajaxJson(basePath + 'invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001/check', object, function(result) {
          Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
             function() {
                      WorkFlow.doDeleteList(basePath
                      + 'invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001/delete'
                      ,object,"MstMarketInstitutions001_btn_delete","MstMarketInstitutions002",function(){table.reload();});
             });
           });
        });
    });
})
