/**
 * 情景管理
 */
$(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
       // var sceneNameArr = new Array();
      //  var sceneIdArrr = new Array();
        sceneNameArr=$('#sceneNames').val();
        sceneIdArr=$('#sceneIds').val();
        ftrIdArr=$('#ftrIds').val();

        if(sceneNameArr==null){
        	sceneNameArr = new Array();
        }
        if(sceneIdArr==null){
            sceneIdArr = new Array();
        }
        if(ftrIdArr==null){
            ftrIdArr = new Array();
        }
       /* $.each($('#sceneNames')[0], function(n, val) {
            sceneNameArr.push(val.value);
        });
        //console.log('sceneArr: ' + sceneArr);
        $.each($('#sceneIds')[0], function(n, val) {
            sceneIdArrr.push(val.value);
        });*/

        return {
        	riskType: $('#riskType').val(),		           //适用风险分类=流动性风险
        	//riskType2:	$('#riskType2').val(),		   //适用二级分类
        	sceneType: $('#sceneType').val(),		   //情景类型=假设情景
        	sceneType2: $('#sceneType2').val(),      //情景二级类型
        	tpmlId: $('#tpmlId').val(),                     //情景模板编号
        	openMode: $('#openMode').val(),        //适用产品
        	ftrIds: ftrIdArr.join(","),                         //关联因子
            sceneNames: sceneNameArr.join(","),  //情景名称
        	sceneIds:sceneIdArr.join(","),		           //情景编号
        	effStatus: $('#effStatus').val()		       //启用状态
        }
    };

	/**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        searchOnInit: false, 
        checkbox:true,
        cols: [
                  {field:'tmplName', title: 'FlowRiskSceneManage001.sceneTmplName', sortable:false}
	             ,{field:'riskTypeName', title: 'FlowRiskSceneManage001.riskType', sortField:'riskType'}
	             //,{field:'riskType2', title: 'FlowRiskSceneManage001.riskType2', sortField:'riskType2'}
	             ,{field:'openModeName', title: 'FlowRiskSceneManage001.suitProduct', sortField:'openMode'}
	             ,{field:'openMode', visible:false}
	             ,{field:'sceneTypeName' , title: 'FlowRiskSceneManage001.sceneType', sortField:'sceneType'}
	             ,{field:'sceneType2Name' , title: 'FlowRiskSceneManage001.sceneType2', sortField:'sceneType2'}
	             ,{field:'sceneName', title: 'FlowRiskSceneManage001.sceneName', sortField:'scene_name'}
	             ,{field:'sceneId', title: 'FlowRiskSceneManage001.sceneNo', sortField:'scene_id'}
	             ,{field:'sceneFtrNames', title: 'FlowRiskSceneManage001.relatedFactor', sortable:false}
	             ,{field: 'effStatusName', title: 'FlowRiskSceneManage001.effStatus', sortField: "eff_status",
	                 render: function (data, type, row, meta) {
	                     if (row.effStatusName == '启用') {
	                         return '<a class="btn layui-btn effstatus" title="历史数据" style="background:#81bb5e;color:white;">启用</a>';
	                     } else {
	                         return '<a class="btn layui-btn effstatus" title="历史数据" style="background:white;color:black;border:1px solid #e6e6e6;">停用</a>';
	                     }

	                 }
	             }
	             ,{field:'dataStatus', title: 'FlowRiskSceneManage001.dataStatus', sortField:'dataStatus'}
	             ,{field:'remark', title: 'FlowRiskSceneManage001.remark', sortable:false}
	             ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.dialog(JoyinUtil.getLabel("FlowRiskSceneManage002.edit"),
                basePath + "invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/init",
                "1200", "90%",data,function() {
                                    table.reload();
                                });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        //operType: DELETE
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage001/checkDelete', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage001/delete', object, 
                        "FlowRiskSceneManage001_btn_delete", "FlowRiskSceneManage002",function() {
                    table.reload();
                });
            });
        });
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
        Joyin.dialog(JoyinUtil.getLabel("FlowRiskSceneManage002"),
                JoyinUtil.getRealPath("invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/init"),
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
            Joyin.error(JoyinUtil.getMessage('RSK_W_0013', "01" == status ? 
                    JoyinUtil.getLabel("FlowRiskSceneManage001.notuse") : JoyinUtil.getLabel("FlowRiskSceneManage001.inuse")));
            return;
        }

        var object = {selectedItem: selArr, effStatus: status, operateType: "01" == status ? "effective" : "neffective"};
        Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage001/checkEffective', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", "01" == status ? 
                    JoyinUtil.getLabel("FlowRiskSceneManage001.inuse") : JoyinUtil.getLabel("FlowRiskSceneManage001.notuse")), function(){
                pageModel = PAGE_MODEL_EDIT;
                object.pageModel = pageModel;
                object.operateType = 'save';
                WorkFlow.doSubmitList('/invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage001/updateScenesEffStatus', object, 
                        "01" == status ? "FlowRiskSceneManage001_btn_effective" : "FlowRiskSceneManage001_btn_neffective", "FlowRiskSceneManage002", function() {
                    table.reload();
                });
            });
        });
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#FlowRiskSceneManage001_btn_search').click(function() {
            table.load();
        });
        $('#FlowRiskSceneManage001_btn_export').click(function() {
            exportExcel();
        });
        $('#FlowRiskSceneManage001_btn_add').click(function() {
            openNew();
        });
        $('#FlowRiskSceneManage001_btn_effective').click(function() {
            sceneBatchEffective("01");
        });
        $('#FlowRiskSceneManage001_btn_neffective').click(function() {
            sceneBatchEffective("02");
        });
      
/*        $('#d').click(function(){
        	$('#riskType').attr('data-url','');
        	$('#riskType').attr('data-url','@{/a/getAssetId(finprodType333=3)}');
        });*/
  
    });
}());