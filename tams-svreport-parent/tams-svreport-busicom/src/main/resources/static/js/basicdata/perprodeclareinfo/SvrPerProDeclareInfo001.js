(function(){

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.prodName){
            param.prodName = param.prodName;
        }
        if(param.termtype){
            param.termtype = param.termtype;
        }if(param.operateMode){
            param.operateMode = param.operateMode;
        }
        param.raisePstrdateStart = $('#raisePstrdate').startDate();
        param.raisePstrdateEnd = $('#raisePstrdate').endDate();
        if (param.status){
            param.status = param.status;
        }
        // param.key="REP_FINPROD_INS_ID";
        // param.order="asc";
        return param;
    };
    // 表格的列
    var tabCols = [
        {field:'declareUuid', title: '主键UUID', visible : false}
        ,{field:'statusStr', title: 'SvrPerProDeclareInfo001.status', width:90}
        ,{field:'prodName', title: 'SvrPerProDeclareInfo001.prodName', width:100}
        ,{field:'prodInsId', title: 'SvrPerProDeclareInfo001.prodInsId', width:100}
        ,{field:'prodBrand', title: 'SvrPerProDeclareInfo001.prodBrand', width:100}
        ,{field:'prdSpecPropertyStr', title: '产品特殊属性',width:130}
        ,{field:'prodStage', title: '产品期次', width:100}
        ,{field:'issuePartyId', title: '发行机构代码', width:100}
        ,{field:'approverName', title: '产品审批人姓名', width:130}
        ,{field:'approverId', title: '产品审批人身份证号', width:160}
        ,{field:'designerName', title: '产品设计人姓名', width:100}
        ,{field:'designerId', title: '产品设计人身份证号', width:100}
        ,{field:'managerName', title: '投资经理姓名', width:130}
        ,{field:'managerId', title: '投资经理身份证号',width:130}
        ,{field:'liaisonName', title: '业务联络人姓名',width:130}
        ,{field:'liaisonTel', title: '业务联络人座机',width:130}
        ,{field:'liaisonMobile', title: '业务联络人手机',width:130}
        ,{field:'liaisonEmail', title: '业务联络人邮箱',width:130}
        ,{field:'raiseTypeStr', title: '募集方式',width:130}
        ,{field:'profitTypeStr', title: '收益类型',width:130}
        ,{field:'isFhxgcpStr', title: '新老产品标记',width:130}
        ,{field:'termtypeStr', title: '产品期限',width:130}
        ,{field:'termtype', title: '产品期限',width:130, visible:false}
        ,{field:'excFinSectorStr', title: '是否金融同业专属',width:130}
        ,{field:'investAraeStr', title: '资金投向地区',width:130}
        ,{field:'investCountryStr', title: '产品投资国家或地区',width:130}
        ,{field:'finanSerModelStr', title: '理财业务服务模式',width:130}
        ,{field:'operateModeStr', title: '产品运作模式',width:130}
        ,{field:'isShortTermStr', title: '是否设置最短持有期限',width:130}
        ,{field:'shortTerm', title: '最短持有期限（天）',width:130}
        ,{field:'isFreeRedemStr', title: '最短持有期后是否自由赎回',width:130}
        ,{field:'cashManaCateStr', title: '是否现金管理类',width:130}
        ,{field:'cmmodeStr', title: '产品资产配置方式',width:130}
        ,{field:'pmmodeStr', title: '产品管理模式',width:130}
        ,{field:'mname', title: '实际管理人名称',width:130}
        ,{field:'pricedModeStr', title: '产品定价方式',width:130}
        ,{field:'ivstorientationStr', title: '产品投资性质',width:130}
        ,{field:'contModeStr', title: '合作模式',width:130}
        ,{field:'cooperationName', title: '合作机构名称',width:130}
        ,{field:'saleAreaStr', title: '产品销售区域',width:130}
        ,{field:'raiseCcyStr', title: '募集币种',width:130}
        ,{field:'cashPrinCcyStr', title: '兑付本金币种',width:130}
        ,{field:'cashProfCcyStr', title: '兑付收益币种',width:130}
        ,{field:'saleAmtStr', title: '起点销售金额',width:130,type: 'number'}
        ,{field:'raiseAmtPlan', title: '计划募集金额',width:130,type: 'number'}
        ,{field:'raisePstrdate', title: '募集起始日（从)',width:130,type: 'date'}
        ,{field:'raisePenddate', title: '募集起始日（到)',width:130,type: 'date'}
        ,{field:'investPrinDateStr', title: '投资本金到账日',width:130}
        ,{field:'investProfDateStr', title: '投资收益到账日',width:130,}
        ,{field:'saleFeeRate', title: '销售手续费率%',width:130,type: 'number'}
        ,{field:'investFeeRate', title: '投资管理费率%',width:130,type: 'number'}
        ,{field:'inDepNameStr', title: '境内托管机构名称',width:130}
        ,{field:'inDepCode', title: '境内托管机构代码',width:130}
        ,{field:'outDepCtryStr', title: '境外托管机构国别',width:130}
        ,{field:'outDepName', title: '境外托管机构名称',width:130}
        ,{field:'truFeeRate', title: '托管费率%',width:130,type: 'number'}
        ,{field:'riskLevelStr', title: '产品风险等级',width:130}
        ,{field:'riskTypeStr', title: '投资者风险偏好',width:130}
        ,{field:'termRightFlagStr', title: '发行机构提前终止权标识',width:130}
        ,{field:'cusRedeemFlagStr', title: '投资者赎回权标识',width:130}
        ,{field:'creditFlagStr', title: '产品增信标识',width:130}
        ,{field:'creditTypeStr', title: '产品增信机构类型',width:130}
        ,{field:'creditModeStr', title: '产品增信形式',width:130}
        ,{field:'invTypeRat', title: '投资资产种类及比例',width:130}
        ,{field:'yield', title: '业绩比较基准',width:130,type:'number'}
        ,{field:'remark', title: '备注',width:130}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
    ];

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SvrPerProDeclareInfo001.reference"),
                JoyinUtil.getRealPath("basicdata/perprodeclareinfo/SvrPerProDeclareInfo002/init"),
                "98%", "95%",data,function(){table.reload();}
            );
        },
        cols: tabCols,
    });


    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.pageId='SvrPerProDeclareInfo002';
        Joyin.ajaxJson(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("SvrPerProDeclareInfo001.edit"),
                basePath + "basicdata/perprodeclareinfo/SvrPerProDeclareInfo002/init",
                "98%", "95%", {declareUuid : data.declareUuid, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});

        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        if(data.status != "E"){
            return Joyin.error("只能删除有效数据，请重新选择");
        }
        var object = {selectedItem: [data], operateType:"delete", declareUuid : data.declareUuid};
        data.pageId='SvrPerProDeclareInfo001';
        Joyin.ajaxJson(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/delete', object, "SvrPerProDeclareInfo001_btn_delete", "SvrPerProDeclareInfo002",
                    function(){table.reload();});
            });
        });
    });

    /**
     * 导出
     */
    var exportExcel = function(){
        if (table != null && table != '') {
            // 是否有选中行
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            // 所有表格数据
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    // 附件上传
    $('#SvrPerProDeclareInfo001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function () {
            var options = {};
            options.pageId = "SvrPerProDeclareInfo001";
            Joyin.uploadFile(options, function (fileName) {
                Joyin.ajax(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function () {
                    Joyin.ajax(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/importData', {fileName: fileName});
                });
            }, function () {
                table.reload();
            });
        });
    });

    // 002 页面
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("SvrPerProDeclareInfo001.new"),
            JoyinUtil.getRealPath("basicdata/perprodeclareinfo/SvrPerProDeclareInfo002/init"),
            "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    };


    $(function() {
        $('#SvrPerProDeclareInfo001_btn_search').click(function() {
            table.load();
            $('#SvrPerProDeclareInfo001_btn_delete').show();
        });
        $('#SvrPerProDeclareInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#SvrPerProDeclareInfo001_btn_add').click(function() {
            openNew();
        });
        // 删除按钮点击事件
        $("#SvrPerProDeclareInfo001_btn_delete").click(function(){
            if(!table.isSearched()) {
                return;
            }
            var selArr = table.getAllCheckedRows();
            if (!selArr || selArr.length == 0) {
                Joyin.error("请从列表中选择数据");
                return;
            }
            var rowDatas = [];
            var prdRegistCodes = "";
            var declareUuids= "";
            $(selArr).each(function(i, val) {
                rowDatas.push(val.declareUuid);
                declareUuids += val.declareUuid+",";
                // prdRegistCodes +=val.regCode+",";
            });
            var object = {selectedItem: selArr, operateType:"delete" ,datas : declareUuids};
            // console.log(object);
            Joyin.ajaxJson(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SvrPerProDeclareInfo001.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'basicdata/perprodeclareinfo/SvrPerProDeclareInfo001/deleteList', object,"SvrPerProDeclareInfo001_btn_delete", "SvrPerProDeclareInfo002", function(result) {
                        table.reload();
                    });
                });
            });
        });
    });
}());