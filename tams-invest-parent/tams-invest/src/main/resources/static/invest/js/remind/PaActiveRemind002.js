/**
 * 主动提醒详情页面
 */
$(function() {
    // 时间和分类切换标记
    var flag = true;
    
     
    /**
     * 处理按钮点击事件
     */
    function dealRemind(url, lable, id, remType, days, feeId, menuId, table,mdate,orgId,orgName,ddate){
        if(flag == true){

            /*
            data={
                    finprodId:id,
                    feeId:feeId,
                    remType:remType,
                    days:days,
                    quaryDate : $('#quaryDate').val(),
                    pageModel : PAGE_MODEL_NEW
                 };
             // 弹窗打开
            Joyin.dialog(JoyinUtil.getLabel(lable),
                    JoyinUtil.getRealPath(url),
                    "98%", "95%",data,
                 function(){
                        table.table? table.reload() : table.load();
                 }
            );
            */

            var urlPara = "";

            if (remType == 'R7S3') {
                // R7S3 将 查询时间去掉。查全部数据
                urlPara = "?finprodId=" + id + "&feeId=" + feeId + "&remType=" + remType + "&days=" + days
                    + "&pageModel=" + PAGE_MODEL_NEW + "&mdate=" + mdate
                    + "&orgId=" + orgId + "&orgName=" + orgName;
            } else if (remType == 'R3S5') {
                // R3S5 添加计息截止日作为跳转后查询条件
                urlPara = "?finprodId=" + id + "&feeId=" + feeId + "&remType=" + remType + "&days=" + days
                    + "&quaryDate=" + $('#quaryDate').val() + "&pageModel=" + PAGE_MODEL_NEW + "&mdate=" + mdate
                    + "&orgId=" + orgId + "&orgName=" + orgName + "&dDate=" + ddate;

            } else {
                urlPara = "?finprodId=" + id + "&feeId=" + feeId + "&remType=" + remType + "&days=" + days
                    + "&quaryDate=" + $('#quaryDate').val() + "&pageModel=" + PAGE_MODEL_NEW + "&mdate=" + mdate
                    + "&orgId=" + orgId + "&orgName=" + orgName;
            }
            
            
            var urls = "/invest"+url+urlPara;
            if(remType=='R6S1'){
            	urls = url+urlPara;
            }
            // 新窗口打开
            var newTab = {
                pageId: lable,
                title: JoyinUtil.getLabel(lable),
                url:urls,
                id: menuId,
                icon: ''
            };
            top.tab.tabDelete(newTab.id);
            top.tab.tabAdd(newTab);

        }else{
            Joyin.alert(JoyinUtil.getMessage("FIN_E_0003"));
        }
    }

    /**
     * 关闭按钮点击事件
     */
    function closeRemind(remType, id, name, feeId, mdate, table){
        if(flag == true){
            Joyin.confirm(JoyinUtil.getMessage('FIN_E_0001'), function(){
                Joyin.ajax(JoyinUtil.getRealPath('/invest/remind/PaActiveRemind002/closeRemind'),
                    {
                        cdate : $('#quaryDate').val(),
                        remType : remType,
                        finprodId : id,
                        feeId : feeId,
                        finprodName : name,
                        mdate : mdate
                    },
                    function(result){
                        Joyin.alert(JoyinUtil.getMessage("FIN_E_0002"));
                        table.table? table.reload() : table.load();
                    })
            });
        }else{
            Joyin.alert(JoyinUtil.getMessage("FIN_E_0003"));
        }
    }

    /**
     * 获取查询参数
     */
    var searchform = function() {
        if ($('#quaryDate').val() == null || $('#quaryDate').val() == '') {
            layer.msg(JoyinUtil.getLabel("请选择日期"));
        } else {
            return {
                quaryDate : $('#quaryDate').val(),
                mainCategory : $('#mainCategory').val(),
                smallCategory : $('#smallCategory').val()
            }
        }
    };

    /**
     * 定义数据表格
     */
    var tableR1 = $("#dataTableR1").joyintable(
        {
            ajax : {
                url : 'selramind',
                type : 'POST',
                data : function() {
                    return searchform();
                }
            },
            cols : [
                {
                    field : 'remTypeStr',
                    title : 'PaActiveRemind002.remType',
                    width : 200
                },
                {
                    field : 'finprodId',
                    title : 'PaActiveRemind002.finprodId',
                    width : 200
                },
                {
                    field : 'finprodName',
                    title : 'PaActiveRemind002.finprodName',
                    width : 300
                },
                {
                    field : 'reminder',
                    title : 'PaActiveRemind002.reminder',
                    width : 300
                },
                {
                    field : 'prodId',
                    visible : false
                },
                {
                    field : 'feeId',
                    visible : false
                },
                {
                    field : 'remType',
                    visible : false
                },
                {
                    field : 'url',
                    visible : false
                },
                {
                    field : 'lable',
                    visible : false
                },
                {
                    field : 'dealF',
                    visible : false
                },
                {
                    field : 'closF',
                    visible : false
                },
                {
                    field : 'days',
                    visible : false
                },
                {
                    field : 'mdate',
                    visible : false
                },
                {
                    field : 'objType',
                    visible : false
                },
                {
                    field : 'orgId',
                    visible : false
                },
                {
                    field : 'orgName',
                    visible : false
                },
                {
                    field : 'mdateStr',
                    visible : false
                },
                {
                    field : 'odate',
                    visible : false
                },
                {
                    field : 'odateStr',
                    visible : false
                },
                {
                    field : 'menuId',
                    visible : false
                },
                /*OPEX-612【主动提醒】项目类和计划类利率型资产 315检查中的提醒出来2条一样的提醒 guyunfei start*/
                {
                    field : 'cashType',
                    visible : false
                },
                /*OPEX-612【主动提醒】项目类和计划类利率型资产 315检查中的提醒出来2条一样的提醒 guyunfei end*/
                {
                    field : 'operating',
                    title : 'PaActiveRemind002.operating',
                    title : 'PaActiveRemind002.operating',
                    align : 'center',
                    width : 200,
                    render : function(data, type, row, meta) {
                        var operating = ""
                        if(row.dealF == "Y"){
                            operating = operating+"<a id=\"PaActiveRemind002_btnDeal_action\" style='color: #fff;' class=\"layui-btn layui-btn-sm\">"+JoyinUtil.getLabel("PaActiveRemind002.deal")+"</a>"
                        };
                        if(row.closF == "Y"){
                            operating = operating+"<a id=\"PaActiveRemind002_btnClose_action\" style='color: #fff;' class=\"layui-btn layui-btn-sm\">"+JoyinUtil.getLabel("PaActiveRemind002.close")+"</a>"
                        };
                        return operating
                    },
                } ],
            loadonce:true,
            searchOnInit:false
        });

    /**
     * 定义数据表格
     */
    var tableR2 = $("#dataTableR2").joyintable(
        {
            ajax : {
                url : 'selramind',
                type : 'POST',
                data : function() {
                    return searchform();
                }
            },
            cols : [
                {
                    field : 'remTypeStr',
                    title : 'PaActiveRemind002.remType',
                    width : 200
                },
                {
                    field : 'assetCode',
                    title : 'PaActiveRemind002.assetCode',
                    width : 200
                },
                {
                    field : 'assetName',
                    title : 'PaActiveRemind002.assetName',
                    width : 300
                },
                {
                    field : 'reminder',
                    title : 'PaActiveRemind002.reminder',
                    width : 300
                },
                {
                    field : 'remType',
                    visible : false
                },
                {
                    field : 'assetId',
                    visible : false
                },
                {
                    field : 'feeId',
                    visible : false
                },
                {
                    field : 'dealF',
                    visible : false
                },
                {
                    field : 'url',
                    visible : false
                },
                {
                    field : 'lable',
                    visible : false
                },
                {
                    field : 'dealF',
                    visible : false
                },
                {
                    field : 'closF',
                    visible : false
                },
                {
                    field : 'days',
                    visible : false
                },
                {
                    field : 'mdate',
                    visible : false
                },
                // add by jiangpeng on 20200728 债券存续期 计息截止日与支付日不同时 交易查询用 start
                {
                    field : 'dDate',
                    visible : false
                },
                {
                    field : 'dDateStr',
                    visible : false
                },
                // add by jiangpeng on 20200728 债券存续期 计息截止日与支付日不同时 交易查询用 end
                {
                    field : 'objType',
                    visible : false
                },
                {
                    field : 'orgId',
                    visible : false
                },
                {
                    field : 'orgName',
                    visible : false
                },
                {
                    field : 'mdateStr',
                    visible : false
                },
                {
                    field : 'menuId',
                    visible : false
                },
                /*OPEX-612【主动提醒】项目类和计划类利率型资产 315检查中的提醒出来2条一样的提醒 guyunfei start*/
                {
                    field : 'cashType',
                    visible : false
                },
                /*OPEX-612【主动提醒】项目类和计划类利率型资产 315检查中的提醒出来2条一样的提醒 guyunfei end*/
                {
                    field : 'operating',
                    title : 'PaActiveRemind002.operating',
                    align : 'center',
                    width : 200,
                    render : function(data, type, row, meta) {
                        var operating = ""
                        if(row.dealF == "Y"){
                            operating = operating+"<a id=\"PaActiveRemind002_btnDeal_action\" style='color: #fff;' class=\"layui-btn layui-btn-sm\">"+JoyinUtil.getLabel("PaActiveRemind002.deal")+"</a>"
                        };
                        if(row.closF == "Y"){
                            operating = operating+"<a id=\"PaActiveRemind002_btnClose_action\" style='color: #fff;' class=\"layui-btn layui-btn-sm\">"+JoyinUtil.getLabel("PaActiveRemind002.close")+"</a>"
                        };
                        return operating
                    },
                } ],
            loadonce:true,
            searchOnInit:false
        });

    /**
     * 定义数据表格
     */
    var tableR3 = $("#dataTableR3").joyintable(
        {
            ajax : {
                url : 'selramind',
                type : 'POST',
                data : function() {
                    return searchform();
                }
            },
            cols:[
            	{field:'portfolioId',visible :false}
                ,{field:'indId',visible :false}
                ,{field:'frequency',visible :false}
                ,{field:'supFrequency',visible :false}
                ,{field:'groupName', title: 'PaActiveRemindss002.groupNameStr',width:250}
                ,{field:'supTypeAbbr',sortField:"supType", title: 'PaActiveRemindss002.supType',width:100}
                ,{field:'supDate', title: 'PaActiveRemindss002.supDate',type:'date'}
                ,{field:'indName', title: 'PaActiveRemindss002.indName',width:200}
                ,{field:'warnCond', width:150 ,title: 'PaActiveRemindss002.warnCond',render: RskQuotaCommon.transferWarnFoul}
                ,{field:'approveCond',width:150, title: 'PaActiveRemindss002.approveCond',render: RskQuotaCommon.transferWarnFoul}
                 ,{field:'foulCond', width:150 ,title: 'PaActiveRemindss002.foulCond',render: RskQuotaCommon.transferWarnFoul}
                ,{field:'supVal', title: 'PaActiveRemindss002.threshol',width:150,render: RskQuotaCommon.transferSupVal}
                ,{field:'supStatAbbr',sortField:"supStat",width:100,title: 'PaActiveRemindss002.supStat',render:function(d,type, row, meta){
                     if(row.supStat == "ERROR"){
                     	 return   JoyinUtil.getLabel("PaActiveRemindss002.exception");
                      }
                	 if(d == normal){
                         if(row.mxCount==0){
                             return  '<a style="cursor: pointer;"><span style="color: green">'+d+'</span></a>';
                         }
                        else{
                            return  '<a style="cursor: pointer;"><span style="color: green">'+d+'</span></a>';
//                             return  '<a  href="javascript:;" style="text-decoration:underline" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
//                                            + '\',\''+ row.supFrequency+'\')" style="cursor: pointer;"><span style="color: green">'+d+'</span></a>';
                        }
                      }
                    else if(d == warning){
                         if(row.mxCount==0){
                             return  '<a style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
                         }
                        else{
                        	  return  '<a style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
//                            return  '<a  href="javascript:;" style="text-decoration:underline" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
//                                                + '\',\''+ row.supFrequency+'\')" style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
                        }

     					}
    				 else if(d == approve){
    				            if(row.mxCount==0){
                                  return  '<a style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
                              }
                             else{
                            	  return  '<a style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
//                                return  '<a  href="javascript:;" style="text-decoration:underline" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
//                                                + '\',\''+ row.supFrequency+'\')" style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
                             }

    					}
                    else if(d == violation){
                        if(row.mxCount==0){
                          return  '<a style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
                        }
                         else{
                        	 return  '<a style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
//                            return  '<a  href="javascript:;" style="text-decoration:underline" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
//                                            + '\',\''+ row.supFrequency+'\')" style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
                         }
    				}
                 }}
                ,{field:'supUniteStatAbbr',sortField:"supUniteStat",width:120,title: 'PaActiveRemindss002.supUniteStat',render:function(d,type, row, meta){
               	 if(d == normal)
                	 return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: green">'+d+'</span></a>';
                else if(d == warning)
                	 return  '<a  href="javascript:;"  style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
    			 else if(d == approve)
    				 return  '<a  href="javascript:;"  style="cursor: pointer;"><span style="color:  orange">'+d+'</span></a>';
                else if(d == violation)
                    return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
                }}
                ,{field:'foulDays',width:120,title: 'PaActiveRemindss002.foulDays',
//                	render:function(d,type, row, meta){
//                  	 return  '<a  href="javascript:;" onclick="javascript:showSupDetail2(\''+ row.indId+'\',\''+row.portfolioId+'\','+d+',\''+new Date(row.supDate).format('yyyy-MM-dd')
//    					+ '\',\''+row.supFrequency+'\')" style="cursor: pointer;">'+d+'</a>';         
//                  }
                }
    			,{field:'mathProcess', title: 'PaActiveRemindss002.mathProcess',width:200,visible :false}
                ,{field:'memo',width:200,title: 'PaActiveRemindss002.memo'} 
            ],
            loadonce:true,
            searchOnInit:false
        });

       /**
       * 定义数据表格--公共数据变更
       */
       var tableR9 = $("#dataTableR9").joyintable(
           {
               ajax : {
                   url : 'selramind',
                   type : 'POST',
                   data : function() {
                       return searchform();
                   }
               },
               cols:[
                   	{
                         field : 'remTypeStr',
                         title : 'PaActiveRemind002.remType',
                         width : 200
                    },
                    {
                         field : 'assetCode',
                         title : 'PaActiveRemind002.busiCode',
                         width : 200
                    },
                    {
                         field : 'assetName',
                         title : 'PaActiveRemind002.busiName',
                         width : 300
                    },
                    {
                         field : 'reminder',
                         title : 'PaActiveRemind002.reminder',
                         width : 500
                    }
                ],
                loadonce:true,
                searchOnInit:false
            }
       );

    function showSupDetail(indId,portfId,supdate,supFrequency){
    	var data={};
    	data.indId=indId;
    	data.portfId=portfId;
    	data.supDate=supdate;
    	data.supFrequency=supFrequency;
       Joyin.dialog(JoyinUtil.getLabel("RskSupervisionRiskAnalyze001.detail"),
               basePath + "invest/riskredis/quota/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze002/init",
               "70%", "90%",data);
    };
    

    function showSupDetail2(indId,portfId,foulDays,supdate,supFrequency){
    	var data={};
    	data.indId=indId;
    	data.portfId=portfId;
    	data.supDate=supdate;
    	data.foulDays=foulDays;
    	data.supFrequency=supFrequency;
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionRiskAnalyze001.detail2"),
               basePath + "invest/riskredis/quota/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze003/init",
               "70%", "90%",data);
    };

    /**
     * 绑定查询按钮事件
     */
    $('#PaActiveRemind002_btn_search').on('click', function() {
        if($('#mainCategory') && ($('#mainCategory').val() === 'R1'||$('#mainCategory').val() === 'R6')){
            $('#dataTableR2_div').toggle(false);
            $('#dataTableR3_div').toggle(false);
            $('#dataTableR9_div').toggle(false);
            $('#dataTableR1_div').toggle(true);
            tableR1.table? tableR1.reload() : tableR1.load();
        }else if($('#mainCategory') && $('#mainCategory').val() === 'R5'){
	    	$('#dataTableR1_div').toggle(false);
	    	$('#dataTableR3_div').toggle(true);
	        $('#dataTableR2_div').toggle(false);
	        $('#dataTableR9_div').toggle(false);
	        tableR3.table? tableR3.reload() : tableR3.load();
        }else if($('#mainCategory') && $('#mainCategory').val() === 'R9'){
         	$('#dataTableR1_div').toggle(false);
         	$('#dataTableR3_div').toggle(false);
         	$('#dataTableR2_div').toggle(false);
         	$('#dataTableR9_div').toggle(true);
         	tableR9.table? tableR9.reload() : tableR9.load();
        }else{
            $('#dataTableR1_div').toggle(false);
            $('#dataTableR2_div').toggle(true);
            $('#dataTableR3_div').toggle(false);
            $('#dataTableR9_div').toggle(false);
            tableR2.table? tableR2.reload() : tableR2.load();
        }
        flag = true;
    });

    /**
     * 关闭按钮绑定事件
     */
    tableR1.bind('click','#PaActiveRemind002_btnClose_action', function(row) {
        closeRemind(row.remType, row.prodId, row.finprodName, row.feeId, row.mdateStr, tableR1);
    });

    /**
     * 处理按钮绑定事件
     */
    tableR1.bind('click','#PaActiveRemind002_btnDeal_action', function(row) {
        dealRemind(row.url, row.lable, row.prodId, row.remType, row.days, row.feeId, row.menuId, tableR1,row.mdateStr,row.orgId,row.orgName);
    });

    /**
     * 关闭按钮绑定事件
     */
    tableR2.bind('click','#PaActiveRemind002_btnClose_action', function(row) {
        closeRemind(row.remType, row.assetCode, row.assetName, row.feeId, row.mdateStr, tableR2);
    });

    /**
     * 处理按钮绑定事件
     */
    tableR2.bind('click','#PaActiveRemind002_btnDeal_action', function(row) {
    	if(row.remType=='R7S1')
    		dealRemind(row.url, row.lable, row.assetCode, row.remType, row.days, row.feeId, row.menuId, tableR2,row.odateStr);
        if(row.remType=='R3S5')
            dealRemind(row.url, row.lable, row.assetCode, row.remType, row.days, row.feeId, row.menuId, tableR2,row.mdateStr,row.orgId,row.orgName,row.dDateStr);
    	else
            dealRemind(row.url, row.lable, row.assetCode, row.remType, row.days, row.feeId, row.menuId, tableR2,row.mdateStr,row.orgId,row.orgName);
    });

    /**
     * 查询要素变更后需重新点击查询
     */
    function itemChange(){
        flag = false;
    }

    // 所有dom对象加载完的处理
    $(document).ready(function() {
        if($('#mainCategory') && ($('#mainCategory').val() === 'R1'||$('#mainCategory').val() === 'R6')){
            $('#dataTableR2_div').toggle(false);
            $('#dataTableR3_div').toggle(false);
            $('#dataTableR1_div').toggle(true);
            $('#dataTableR9_div').toggle(false);
            tableR1.table? tableR1.reload() : tableR1.load();
        }else if($('#mainCategory') && $('#mainCategory').val() === 'R5'){
	    	$('#dataTableR1_div').toggle(false);
	    	$('#dataTableR3_div').toggle(true);
	        $('#dataTableR2_div').toggle(false);
	        $('#dataTableR9_div').toggle(false);
	        tableR3.table? tableR3.reload() : tableR3.load();
        }else if($('#mainCategory') && $('#mainCategory').val() === 'R9'){
            $('#dataTableR1_div').toggle(false);
            $('#dataTableR3_div').toggle(false);
            $('#dataTableR2_div').toggle(false);
            $('#dataTableR9_div').toggle(true);
            tableR9.table? tableR9.reload() : tableR9.load();
        }else{
            $('#dataTableR1_div').toggle(false);
            $('#dataTableR2_div').toggle(true);
            $('#dataTableR3_div').toggle(false);
            $('#dataTableR9_div').toggle(false);
            tableR2.table? tableR2.reload() : tableR2.load();
        }
        $('#mainCategory').change(itemChange);
        $('#smallCategory').change(itemChange);
        $('#quaryDate').change(itemChange);
    })
    /**
	 * 绑定鼠标悬浮事件
	 */
	tableR3.bind('mouseover', 'div.layui-table-main div.laytable-cell-2-groupName', function(data, index) {
		$('div[id*=xuanfu]:visible').each(function() {
			$(this).show();
		});
		$(this).after('<div id="xuanfu"'+index+' style="border-radius: 4px;text-align: center;display: block;z-index: 9999;border: 1px solid #ccc;height: 25px;width: 100px;position: absolute;top: 23px;background: #fff;left: 60px;">突破次数'+data.frequency+'次</div>')
	});
	
    tableR3.bind('mouseleave', 'div.layui-table-main div.laytable-cell-2-groupName', function(data, index) {
		$('div[id*=xuanfu]:visible').each(function() {
			$(this).hide();
		});
	});
}());
