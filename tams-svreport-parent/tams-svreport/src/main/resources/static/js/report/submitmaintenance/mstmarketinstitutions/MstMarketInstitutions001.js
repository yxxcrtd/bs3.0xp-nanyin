$(function(){
	
	/**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
        	customerId: $('#customerId').val(),
        	customerName: $('#customerName').val()
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
                    JoyinUtil.getRealPath("submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/init"),
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
              ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
          ]
    });
        
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("MstMarketInstitutions002.edit"),
                    basePath + "submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
        						   customerId:data.customerId},function(){table.reload();})
    });
    
    
  /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
    	var object = data;
        var keyList = [];
        keyList.push(data.customerId +'|'+data.customerName);
        Joyin.ajaxJson(basePath + 'submitmaintenance/mstmarketinstitutions/MstMarketInstitutions001/check', object, function(result) {
        	Joyin.confirm("请确认是否要删除选中记录？", function() {
                Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                    table.reload();
                });
            });
         });
    });
    var exportExcel = function() {
    	if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
         }
    	var param = getSearchParams();
         if (!selArr || selArr.length == 0) {
         } else {
        	 param.keyList = JSON.stringify(selArr);
         }
        Joyin.exportExcel('export', param);
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
                            + "submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/init",
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
              var keyList = [];
              $.each(selArr, function(i, row) {
              	keyList.push(row.customerId +'|'+row.customerName);
              });
          Joyin.ajaxJson(basePath + 'submitmaintenance/mstmarketinstitutions/MstMarketInstitutions001/check', object, function(result) {
        	  Joyin.confirm("请确认是否要删除选中记录？", function() {
                  Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                      table.reload();
                  });
              });
           });
        });
    });
})
