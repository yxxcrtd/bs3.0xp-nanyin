/**
 * 市场性情景管理
 */
$(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function(){
        var sceneNameArr = new Array();
        var sceneIdArrr = new Array();
        var sceneIds = "";
        if($('#sceneIds').val() != null){
            sceneIds = $('#sceneIds').val().join(',');
        }
        //取开始日期和结束日期
        var dateRangeStr = $("#dateRange").val();
        var startDate = "";
        var endDate = "";
        if(dateRangeStr != ""){
            var dates = dateRangeStr.split(' - ');
            startDate = dates[0];
            endDate = dates[1];
        }

        return {
            riskType: $('#riskType').val(),                //适用风险分类=市场性风险
            //riskType2:    $('#riskType2').val(),         //适用二级分类
            sceneType: $('#sceneType').val(),          //情景类型=假设情景
            sceneType2: $('#sceneType2').val(),      //情景二级类型
            finprodAttr:$('#finprodAttr').val(),           //适用产品-产品属性
            finprodId: $('#finprodId').val(),              //适用产品-产品
            sceneIds: sceneIds,              //情景编号
            ftrId:$('#ftrId').val(),
            effStatus: $('#effStatus').val(),              //启用状态
            suitProduct: $('#suitProduct').val(),         //适用产品
            startDate:startDate,
            endDate:endDate
        }
    };
    
    /**
     * 定义表格
     */
    var table = $("#dataTable").joyintable({
        ajax:{
            url:'findPage',
            type:'POST',
            data:function(){
                return getSearchParams();
            }
        },
        searchOnInit: false,
        checkbox:true,
        cols:[
              {field:'riskTypeName', title: '适用风险分类',width:'9%', sortField:'riskType'},
              //{field:'riskType2', title: '适用二级分类'},
              {field:'sceneName', title: '情景名称',width:'16%'},
              {field:'sceneId', title: '情景编号',width:'8%'},
              {field:'sceneTypeName' , title: '情景类型',width:'8%', sortField:'sceneType'},
              {field:'suitProductName' , title: '适用产品',width:'8%', sortField:'suitProduct'},
              {field:'startDate' , title: '开始时间',width:'8%'},
              {field:'endDate' , title: '结束时间',width:'8%'},
              {field:'ftrName', title: '关联因子',width:'11%'},
//              {field:'effStatusName', title: '启用状态',width:'8%', sortField:'effStatus'},
              {field: 'effStatusName', width: '8%', sortField: "effStatus", title: '启用状态',
                  render: function (data, type, row, meta) {
                      if (row.effStatusName == '启用') {
                          return '<a class="btn layui-btn effstatus" title="启用状态" style="background:#81bb5e;color:white;">启用</a>';
                      } else {
                          return '<a class="btn layui-btn effstatus" title="启用状态" style="background:white;color:black;border:1px solid #e6e6e6;">停用</a>';
                      }

                  }
              },
              {field:'dataStatus', title: '数据状态',width:'8%'},
              {field:'remark', title: '备注',width:'7%', sortField:'remark'},
              {field:'operation', type:'action',
              title: 'SysCommon.operation', width:'120', sortable:false,
              //fixed:'right',
                  render: function (data, type, row, meta) {
                	  	var html = "";
                	  	if(purviewElements.indexOf('_batch')!=-1){
                	  		html +='<i class="btn detail jyiconfont jyicon-detail batch" title="情景详细" data-ignore="undefined"></i>';
                	  	}
                	  	if(purviewElements.indexOf('_edit')!=-1){
                	  		html += '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i>';
                	  	}
                	  	if(purviewElements.indexOf('_delete')!=-1){
                	  		html += '<i class="btn layui-icon delt delete" title="删除" data-ignore="false"></i>';
                	  	}
                	  	return html;
//                      return '<i class="btn detail jyiconfont jyicon-detail" title="情景详细" data-ignore="undefined"></i>'+
//                             '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i>'+
//                             '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
                  }
              }
              ]}
    );
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.dialog(JoyinUtil.getLabel("MarketRiskSceneManage002.edit"),
                basePath + "invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/init",
                "1200", "90%",data,function() {
                                    table.reload();
                                });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delete', function(data, index) {
        //operType: DELETE
        data.operateType = 'delete';
//        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage001/checkDelete', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage001/delete', data, 
                        "MarketRiskSceneManage001_btn_delete", "MarketRiskSceneManage001",function() {
                    table.reload();
                });
            });
//        });
    });
    
    table.bind('click', '.batch', function(data, index) {
        var object = {sceneId: data.sceneId, origin:"manage"};//请求参数
        Joyin.dialog('历史情景详细信息展示',
                basePath + "invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage003/init",
                "1200", "90%", object);
    });
    
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
        Joyin.dialog(JoyinUtil.getLabel("MarketRiskSceneManage002"),
                JoyinUtil.getRealPath("invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/init"),
                "1200", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 启用停用
     */
    function sceneBatchEffective(status) {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.warn(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var flag=true;
        var selectedItem = [];
        $.each(selArr,function(i,v){
            if(v.effStatus == status) {
                flag=false;
                return false;
            }
        })
        if(!flag){
            Joyin.error(JoyinUtil.getMessage('RSK_W_0013', "01" == status ? "停用" : "启用"));
            return;
        }

        var object = {selectedItem: selArr, effStatus: status, operateType: "01" == status ? "effective" : "neffective"};
        pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage001/checkEffective', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", "01" == status ? "启用" : "停用"), function(){
                object.pageModel = pageModel;
                object.operateType = 'save';
                WorkFlow.doSubmitList('/invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage001/updateScenesEffStatus', object, 
                        "01" == status ? "MarketRiskSceneManage001_btn_effective" : "MarketRiskSceneManage001_btn_neffective", "MarketRiskSceneManage002", function() {
                    table.reload();
                });
            });
        });
    }
    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#MarketRiskSceneManage001_btn_search').click(function() {
            table.load();
        });
        $('#MarketRiskSceneManage001_btn_add').click(function() {
            openNew();
        });
        $('#MarketRiskSceneManage001_btn_effective').click(function() {
            sceneBatchEffective("01");
        });
        $('#MarketRiskSceneManage001_btn_neffective').click(function() {
            sceneBatchEffective("02");
        });
        $('#MarketRiskSceneManage001_btn_export').click(function() {
            exportExcel();
        });
//        table.bind('click', '.batch', function(data, index) {
//            var object = {sceneId: data.sceneId, origin:"manage"};//请求参数
//            Joyin.dialog('历史情景详细信息展示',
//                    basePath + "invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage003/init",
//                    "98%", "95%", object);
//        });
    });
}())