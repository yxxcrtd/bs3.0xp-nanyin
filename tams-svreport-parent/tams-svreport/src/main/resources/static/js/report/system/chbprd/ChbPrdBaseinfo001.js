(function() {

    var getPageParams = function() {
        return {
            prodId:$('#prodId').val()==null?null:$('#prodId').val().join(),
            //募集起始日期
            raiseVdate:$('#raiseDate').startDate(),
            raiseEnddate:$('#raiseDate').endDate(),
            //产品起始日期
            vdate:$('#vdate').startDate(),
            mdate:$('#vdate').endDate(),
            status:$('#status').val()==null?null:$('#status').val().join()
        };
    };

    var table = $('#ChbPrdBaseinfo001_prorate').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
            { title: "数据状态", field:'statusName',width:'8%', sortField:'status'},
            { visible:false, field:'status'},
            { title: "产品代码", field:'prodId',width:'8%', sortField:'prodId'},
            { title: "产品名称", field:'prodName',width:'12%', sortField:'prodName'},
            { title: "行内标识码", field:'prodInsId',width:'10%', sortField:'prodInsId'},
            { title: "产品品牌", field:'prodBrand',width:'6%', sortField:'prodBrand'},
            { title: "产品期次", field:'prodStage',width:'8%', sortField:'prodStage'},
            { title: "产品审批人身份证号", field:'approverId',width:'12%', sortField:'approverId'},
            { title: "产品审批人姓名", field:'approverName',width:'10%', sortField:'approverName'},
            { title: "产品设计人身份证号", field:'designerId',width:'6%', sortField:'designerId'},
            { title: "产品设计人姓名", field:'designerName',width:'8%', sortField:'designerName'},
            { title: "投资经理身份证号", field:'managerId',width:'12%', sortField:'managerId'},
            { title: "投资经理姓名", field:'managerName',width:'10%', sortField:'managerName'},
            { title: "业务联络人姓名", field:'liaisonName',width:'6%', sortField:'liaisonName'},
            { title: "业务联络人座机", field:'liaisonTel',width:'8%', sortField:'liaisonTel'},
            { title: "业务联络人手机", field:'liaisonMobile',width:'12%', sortField:'liaisonMobile'},
            { title: "业务联络人邮箱", field:'liaisonEmail',width:'10%', sortField:'liaisonEmail'},
            { title: "产品募集方式", field:'raiseTypeName',width:'6%', sortField:'raiseType'},
            { visible:false, field:'raiseType'},
            { title: "产品收益类型", field:'profitTypeName',width:'8%', sortField:'profitType'},
            { visible:false, field:'profitType'},
            { title: "新老产品标记", field:'isFhxgcpName',width:'8%', sortField:'isFhxgcp'},
            { visible:false, field:'isFhxgcp'},
            { title: "产品期限", field:'termtypeName',width:'12%', sortField:'termtype'},
            { visible:false, field:'termtype'},
            { title: "是否金融同业专属", field:'excFinSectorName',width:'10%', sortField:'excFinSector'},
            { visible:false, field:'excFinSector'},
            { title: "资金投向地区", field:'investAraeName',width:'6%', sortField:'investArae'},
            { visible:false, field:'investArae'},
            { title: "产品投资国家或地区", field:'investCountryName',width:'8%', sortField:'investCountry'},
            { visible:false, field:'investCountry'},
            { title: "理财业务服务模式", field:'finanSerModelName',width:'12%', sortField:'finanSerModel'},
            { visible:false, field:'finanSerModel'},
            { title: "产品运作模式", field:'operateModeName',width:'10%', sortField:'operateMode'},
            { visible:false, field:'operateMode'},
            { title: "是否设置最短持有期限", field:'isShortTermName',width:'6%', sortField:'isShortTerm'},
            { visible:false, field:'isShortTerm'},
            { title: "最短持有期限（天）", field:'shortTerm',width:'8%', sortField:'shortTerm'},
            { title: "最短持有期后是否自由赎回", field:'isFreeRedemName',width:'12%', sortField:'isFreeRedem'},
            { visible:false, field:'isFreeRedem'},
            { title: "是否现金管理类", field:'cashManaCateName',width:'10%', sortField:'cashManaCate'},
            { visible:false, field:'cashManaCate'},
            { title: "产品资产配置方式", field:'cmmodeName',width:'6%', sortField:'cmmode'},
            { visible:false, field:'cmmode'},
            { title: "产品管理模式", field:'pmmodeName',width:'8%', sortField:'pmmode'},
            { visible:false, field:'pmmode'},
            { title: "实际管理人员名称", field:'mname',width:'12%', sortField:'mname'},
            { title: "产品定价方式", field:'pricedModeName',width:'10%', sortField:'pricedMode'},
            { visible:false, field:'pricedMode'},
            { title: "产品投资性质", field:'ivstorientationName',width:'6%', sortField:'ivstorientation'},
            { visible:false, field:'ivstorientation'},
            { title: "合作模式", field:'contModeName',width:'6%', sortField:'contMode'},
            { visible:false, field:'contMode'},
            { title: "合作机构名称", field:'cooperationName',width:'8%', sortField:'cooperationName'},
            { title: "投资资产种类及比例", field:'invTypeRatName',width:'12%', sortField:'invTypeRat'},
            { visible:false, field:'invTypeRat'},
            { title: "业绩比较基准%", field:'yield',width:'10%', sortField:'yield'},
            { title: "投资者风险偏好", field:'riskTypeName',width:'6%', sortField:'riskType'},
            { visible:false, field:'riskType'},
            { title: "产品销售区域", field:'saleAreaName',width:'8%', sortField:'saleArea'},
            { visible:false, field:'saleArea'},
            { title: "募集币种", field:'raiseCcyName',width:'12%', sortField:'raiseCcy'},
            { visible:false, field:'raiseCcy'},
            { title: "兑付本金币种", field:'cashPrinCcyName',width:'10%', sortField:'cashPrinCcy'},
            { visible:false, field:'cashPrinCcy'},
            { title: "兑付收益币种", field:'cashProfCcyName',width:'6%', sortField:'cashProfCcy'},
            { visible:false, field:'cashProfCcy'},
            { title: "起点销售金额", field:'saleAmtStr',width:'8%', sortField:'saleAmtStr'},
            { title: "计划募集金额（元）", field:'raiseAmtPlan',width:'12%', sortField:'raiseAmtPlan'},
            { title: "募集起始日（从）", field:'raisePstrdate',width:'10%', sortField:'raisePstrdate'},
            { title: "募集起始日（到）", field:'raisePenddate',width:'6%', sortField:'raisePenddate'},
            { title: "投资本金到账日", field:'investPrinDateName',width:'8%', sortField:'investPrinDate'},
            { visible:false, field:'investPrinDate'},
            { title: "投资收益到账日", field:'investProfDateName',width:'12%', sortField:'investProfDate'},
            { visible:false, field:'investProfDate'},
            { title: "销售手续费率%", field:'saleFeeRate',width:'10%', sortField:'saleFeeRate'},
            { title: "投资管理费率%", field:'investFeeRate',width:'6%', sortField:'investFeeRate'},
            { title: "境内托管机构名称", field:'inDepNameStr',width:'8%', sortField:'inDepName'},
            { visible:false, field:'inDepName'},
            { title: "境内托管机构代码", field:'inDepCode',width:'12%', sortField:'inDepCode'},
            { title: "境外托管机构国别", field:'outDepCtryName',width:'10%', sortField:'outDepCtry'},
            { visible:false, field:'outDepCtry'},
            { title: "境外托管机构名称", field:'outDepName',width:'6%', sortField:'outDepName'},
            { title: "托管费率%", field:'truFeeRate',width:'6%', sortField:'truFeeRate'},
            { title: "产品风险等级", field:'riskLevelName',width:'8%', sortField:'riskLevel'},
            { visible:false, field:'riskLevel'},
            { title: "发型机构提前终止权标识", field:'termRightFlagName',width:'12%', sortField:'termRightFlag'},
            { visible:false, field:'termRightFlag'},
            { title: "客户赎回权标识", field:'cusRedeemFlagName',width:'10%', sortField:'cusRedeemFlag'},
            { visible:false, field:'cusRedeemFlag'},
            { title: "产品增信标识", field:'creditFlagName',width:'6%', sortField:'creditFlag'},
            { visible:false, field:'creditFlag'},
            { title: "产品增信机构类型", field:'creditTypeName',width:'8%', sortField:'creditType'},
            { visible:false, field:'creditType'},
            { title: "产品增信形式", field:'creditModeName',width:'12%', sortField:'creditMode'},
            { visible:false, field:'creditMode'},
            { title: "募集起始日期", field:'raiseVdate',width:'10%', sortField:'raiseVdate',type:'date'},
            { title: "募集结束日期", field:'raiseEnddate',width:'6%', sortField:'raiseEnddate',type:'date'},
            { title: "起息日", field:'vdate',width:'8%', sortField:'vdate',type:'date'},
            { title: "到期日", field:'mdate',width:'12%', sortField:'mdate',type:'date'},
            { title: "管理方式", field:'manageTypeName',width:'10%', sortField:'manageType'},
            { visible:false, field:'manageType'},
            { title: "是否为结构化（分级）产品", field:'isConstracteName',width:'6%', sortField:'isConstracte'},
            { visible:false, field:'isConstracte'},
            { title: "业绩比较基准上限%", field:'yieldHigh',width:'8%', sortField:'yieldHigh'},
            { title: "业绩比较基准下限%", field:'yieldLow',width:'12%', sortField:'yieldLow'},
            { title: "业绩比较基准说明", field:'yieldRemark',width:'10%', sortField:'yieldRemark'},
            { title: "开放模式", field:'openModeName',width:'6%', sortField:'openMode'},
            { visible:false, field:'openMode'},
            { title: "规律开放周期", field:'openCircleName',width:'6%', sortField:'openCircle'},
            { visible:false, field:'openCircle'},
            { title: "其他规律开放周期（天）", field:'openCircleDays',width:'8%', sortField:'openCircleDays'},
            { title: "无规律开放说明", field:'ruleComment',width:'12%', sortField:'ruleComment'},
            { title: "首次开放周期起始日", field:'firstOpenDay',width:'10%', sortField:'firstOpenDay',type:'date'},
            { title: "节假日是否开放", field:'holidayOnFlagName',width:'6%', sortField:'holidayOnFlag'},
            { visible:false, field:'holidayOnFlag'},
            { title: "平均开放次数（年化）", field:'avgOpenCount',width:'8%', sortField:'avgOpenCount'},
            { title: "开放期业务", field:'openBussName',width:'12%', sortField:'openBuss'},
            { visible:false, field:'openBuss'},
            { title: "开放业务说明", field:'openBussRemark',width:'10%', sortField:'openBussRemark'},
            { title: "资金托管账号", field:'busiAcctNo',width:'6%', sortField:'busiAcctNo'},
            { title: "资金托管账户", field:'acctName',width:'8%', sortField:'acctName'},
            { title: "受托职责", field:'fiduciaryDutyName',width:'12%', sortField:'fiduciaryDuty'},
            { visible:false, field:'fiduciaryDuty'},
            { title: "客户类型", field:'customerTypeName',width:'10%', sortField:'customerType'},
            { visible:false, field:'customerType'},
            { title: "是否母行划转产品", field:'isTransferName',width:'6%', sortField:'isTransfer'},
            { visible:false, field:'isTransfer'},
            { title: "现金管理类产品-是否完全符合", field:'isFullyCompliantName',width:'8%', sortField:'isFullyCompliant'},
            { visible:false, field:'isFullyCompliant'},
            { title: "是否养老理财", field:'isYllccpName',width:'12%', sortField:'isYllccp'},
            { visible:false, field:'isYllccp'},
            { title: "转让类型", field:'transferTypeName',width:'10%', sortField:'transferType'},
            { visible:false, field:'transferType'},
            { title: "备注", field:'remark',width:'6%', sortField:'remark'},
            { title: "创建人", field:'createUser',width:'8%', sortField:'createUser'},
            { title: "创建时间", field:'createTime',type:'date',width:'12%'},
            { title: "更新人", field:'updateUser',width:'8%', sortField:'updateUser'},
            { title: "更新时间", field:'updateTime',type:'date', width:'8%'},
            { title: "数据来源", field:'dataSourceName', width:'10%', sortField:'dataSource'},
            { title: "dataSource",  visible:false, field:'dataSource'},
            { title: "dataUuid",  visible:false, field:'dataUuid'},
            {
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 150,
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
            if(data.prodId == null || data.prodId == '' || data.prodId == undefined){
                Joyin.error("产品代码为空,数据不允许编辑");
                return;
            }else{
                data.pageModel = PAGE_MODEL_EDIT;
                data.type="update";
                Joyin.dialog(JoyinUtil.getLabel("编辑产品基本信息"),
                    basePath + "svreport/system/chbprd/ChbPrdBaseinfo002/init",
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
            Joyin.ajaxJson(basePath + 'svreport/system/chbprd/ChbPrdBaseinfo001/deleteData', object, function(result) {
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
        $('#ChbPrdBaseinfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'svreport/system/chbprd/ChbPrdBaseinfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'svreport/system/chbprd/ChbPrdBaseinfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });

        // 导出按钮点击事件
        $('#ChbPrdBaseinfo001_btn_export').click(function() {
            var param ={};
            var rows = table.getAllCheckedRows();
            if(rows.length > 0) {
                var prodIdList = [];
                $.each(rows, function(i, row) {
                    prodIdList.push(row.prodId);
                });
                param.keyprodIdList = prodIdList.join();
            }
            else{
                //有勾选优先勾选数据
                param = getPageParams();
            }
            Joyin.exportExcel("export",param);
        });
        //删除点击事件
        $('#ChbPrdBaseinfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var errorMessage='';

            for(var i=0; i<selArr.length; i++){
                if( selArr[i].status.indexOf("D")!= -1){
                    errorMessage = "选择的数据中包含数据状态为删除的数据,请重新选择"+"<br>";
                }
            }
            if(errorMessage != '' && errorMessage != null){
                Joyin.error(errorMessage);
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'svreport/system/chbprd/ChbPrdBaseinfo001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });
        $('#ChbPrdBaseinfo001_btn_search').click(function() {
            table.load();
        });
        //新增点击事件
        $('#ChbPrdBaseinfo001_btn_add').click(function() {
            var data={"operateType":"add"};
            Joyin.dialog('产品费率信息新建',basePath+'svreport/system/chbprd/ChbPrdBaseinfo002/init', '98%', '95%',data,function(){table.reload();});
        });

        //修改、删除事件绑定
        tableBind();
    });
}());