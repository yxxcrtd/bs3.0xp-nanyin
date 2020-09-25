(function() {

	// 快速录入标志
	var quickFlag = false;

	var getElementById = function(id) {
		return $('#' + goalInfoEntityName + '_' + id);
	};

	/**
	 * 定义数据表格
	 */
	var bussFinProductGoalInfoTable = getElementById('dataTable')
			.joyintable(
					{
						ajax : {
							url : JoyinUtil.getRealPath('component/prod/BussFinProductGoalInfo/productGoalInfoQuery'),
							type : 'POST',
							data : function() {
								return {
									finprodId : goalInfoFinprodId,
									workflowId : workflowId,
									entityName : goalInfoEntityName,
									quickFlag : quickFlag
								};
							}
						},
						searchOnInit: true,
						checkbox : false,
						loadonce : true,
						height : 290,
						rowno : true,
						addbtn : true,
						cols : [ {
							field : 'assetClassify',
							title : 'BussFinProductGoalInfo.assetClassify',
							width : 300,
							edit : true
						}, {
							field : 'investMin',
							title : 'BussFinProductGoalInfo.investMin',
							width : 300,
							edit : true,
							type : 'number',
							textrange : '2',
							digit:4
						}, {
							field : 'investMax',
							title : 'BussFinProductGoalInfo.investMax',
							width : 300,
							edit : true,
							type : 'number',
							textrange : '2',
							digit:4
						}, {
							field : 'operation',
							title : 'SysCommon.operation',
							type : 'action',
							buttons : [ 'delt' ]
						} ]
					});

	/**
	 * 绑定删除事件
	 */
	bussFinProductGoalInfoTable.bind('click', '.delt', function(data, index) {
		bussFinProductGoalInfoTable.removeRow(data);
	});
	
	var goalInfoInitAction = function(input_finprodId){
		goalInfoFinprodId = input_finprodId;
		quickFlag = true;
		bussFinProductGoalInfoTable.table ? bussFinProductGoalInfoTable.reload() : bussFinProductGoalInfoTable.load();
	}
    
    // 公开接口
    window.BussFinProductGoalInfo = {
        getAllRows:getAllRows,
        goalInfoInitAction : goalInfoInitAction
    };

    function getAllRows(){
        if(bussFinProductGoalInfoTable != ''){
            return bussFinProductGoalInfoTable.getAllRows();
        }
    }
}());