$(function () {
    var tableInitFlag=true;
    var selectcolor;
    var color = ['rgb(168,222,100)','rgb(251,247,134)', 'rgb(255,153,153)'];
    var xmlData={};
    readConfig=function(){
        Joyin.ajaxSync('readConfig', null, function(result){
            //把读取到的配置文件赋值
        	xmlData.tops = result["top"];
        	xmlData.mids = result["mid"];
        });
    };
    readConfig();
    var curSelectedIndex = 0;
    var xAxisData = ["证件类型", "证件匹配","及时性","产品状态","投资者数量匹配"];
    var xAxisDataRate = ["证件类型合格率", "证件匹配合格率","及时性合格率","产品状态合格率","投资者数量匹配合格率"];
    var xAxisDataDetail = ["用于检验境内个人投资者未使用“居民身份证”或“临时身份证”进行登记的情况", 
                     "用于校验投资者证件类型、证件号码相同，投资者姓名不同的情况",
                     "用于检验银行是否按照T+1（下一工作日）及时性要求开展登记的情况",
                     "",""];

    window.investorsqualitycheck = function() {
        return {
            /**
             * 初始化的函数
             */
            init : function(){
        		/*var tableDiv0 = document.getElementById('tableDiv0');
        		var tableDiv1 =document.getElementById("tableDiv1");
        		var tableDiv2 =document.getElementById("tableDiv2");

            		tableDiv0.style.display="block";
            		tableDiv1.style.display="none";
            		tableDiv2.style.display="none";*/
                //防止报出时间为空的信息
                setTimeout(function() {investorsqualitycheck.searchData();},500);
                document.getElementById("registrationTitle").innerText=xAxisDataRate[0];
            	document.getElementById("registration").innerText=xAxisDataDetail[0];
                
            },
            getPageParams : function() {
                return {
                	 orgCode : $('#orgCode').val()==null?null:$('#orgCode').val().join(","),
                	// 查询日期
                    searchDate : $('#searchDate').val(),
                };
            },
            searchData : function () {
                var param=investorsqualitycheck.getPageParams();
                if(param.searchDate==''||param.searchDate==null){
                    Joyin.error("查询日期不可为空！");
                    return;
                }
                if(investorsqualitycheck.table01.table){
                	investorsqualitycheck.table01.reload();
                }
                else{
                	investorsqualitycheck.table01.load();
                }
            },
            table0 : $('#table0').joyintable({
                ajax: {
                    url: 'searchTableZJLX',
                    type: 'POST',
                    data: function() {
                        var parm={};
                        parm=investorsqualitycheck.getPageParams();
                    	parm.index=curSelectedIndex+1;
                        return parm;
                    },
                },
//                checkbox : false,
//                rowno: false, // 显示行号
//                searchOnInit: false, //初始化的时候就请求后台数据
//                scrollX:true,
                cols: [
	                	{title: "识别标识", field: "indentifyMark",width:'25%'},
	                    {title: "投资者境内外标识", field: "holderMark",width:'25%'},
	                    {title: "投资者类别", field: "holderType",width:'25%'},
	                    {title: "证件类别", field: "identifyTypeStr",width:'25%'},
	                ]
            }),
            table1 : $('#table1').joyintable({
                ajax: {
                    url: 'searchTableZJPP',
                    type: 'POST',
                    data: function() {
                        var parm={};
                        parm=investorsqualitycheck.getPageParams();
                    	parm.index=curSelectedIndex+1;
                        return parm;
                    },
                },
//                checkbox : false,
//                rowno: false, // 显示行号
//                searchOnInit: false, //初始化的时候就请求后台数据
//                scrollX:true,
                cols: [
                       	{title: "登记银行代码", field: "registBankCode",width:'20%'},
	                	{title: "识别标识", field: "indentifyMark",width:'20%'},
	                    {title: "证件类别", field: "identifyTypeStr",width:'20%',sortField:'identifyType'},
	                    {title: "证件号码", field: "identifyNumber",width:'20%'},
	                    {title: "投资者姓名", field: "holderName",width:'20%'},
	                ]
            }),
            table2 : $('#table2').joyintable({
                ajax: {
                    url: 'searchTableJSX',
                    type: 'POST',
                    data: function() {
                        var parm={};
                        parm=investorsqualitycheck.getPageParams();
                    	parm.index=curSelectedIndex+1;
                        return parm;
                    },
                },
//                checkbox : false,
//                rowno: false, // 显示行号
//                searchOnInit: false, //初始化的时候就请求后台数据
//                scrollX:true,
                cols: [
						{title: "产品登记编码", field: "prdRegistCode",width:'20%'},
						{title: "识别标识", field: "indentifyMark",width:'20%'},
						{title: "持有日期", field: "holdDateStr",width:'20%',sortField:'holdDate'},
						{title: "报送状态", field: "sendStatus",width:'20%'},
						{title: "报送日期", field: "submitTimeStr",width:'20%',sortField:'submitTime'},
	                ]
            }),
            table01 : $('#indexIdTable01').joyintable({
            	ajax: {
                    url: 'searchInvestorsPassRate',
                    type: 'POST',
                    data: function() {
                        var parm=parm=investorsqualitycheck.getPageParams();
                        return parm;
                    },
                },
//                page : false,
//            	checkbox : false,
//                rowno: false, // 显示行号
//                searchOnInit: false, //初始化的时候就请求后台数据
                click:function(table, data){
//                	var index = xAxisData.indexOf(data.pageType-1);
                	document.getElementById("registration").innerText=xAxisDataDetail[data.pageType-1];
                	document.getElementById("registrationTitle").innerText=xAxisDataRate[data.pageType-1];
                	curSelectedIndex=data.pageType-1;
            		/*var tableDiv0 = document.getElementById('tableDiv0');
            		var tableDiv1 =document.getElementById("tableDiv1");
            		var tableDiv2 =document.getElementById("tableDiv2");
                	if(data.pageType == '1'){
                		tableDiv0.style.display="block";
                		tableDiv1.style.display="none";
                		tableDiv2.style.display="none";
	                	if(investorsqualitycheck.table0.table){
	                        investorsqualitycheck.table0.reload();
	                    }else{
	                        investorsqualitycheck.table0.load();
	                    }
                	}else if(data.pageType == '2'){
                		tableDiv0.style.display="none";
                		tableDiv1.style.display="block";
                		tableDiv2.style.display="none";
	                	if(investorsqualitycheck.table1.table){
	                        investorsqualitycheck.table1.reload();
	                    }else{
	                        investorsqualitycheck.table1.load();
	                    }
                	}else if(data.pageType == '3'){
                		tableDiv0.style.display="none";
                		tableDiv1.style.display="none";
                		tableDiv2.style.display="block";
	                	if(investorsqualitycheck.table2.table){
	                        investorsqualitycheck.table2.reload();
	                    }else{
	                        investorsqualitycheck.table2.load();
	                    }
                	}*/
                    checkOnClickRow(data);
                },
                page : false,
            	checkbox : false,
                rowno: false, // 显示行号
//                searchOnInit: false, 
            	cols: [
						{field:'unQualified', width:0,visible:false},
						{title: "质量核查不合格数量/总数量", field: "pageType",width:'80%',sortable:false,
							render: function (data, type, row, meta) {
								var index = row.pageType-1;
//								if(parseInt(row.rate)>=parseInt(xmlData.tops)){
//				                	selectcolor=color[0];
//				            	}
//								else if(parseInt(row.rate)>=parseInt(xmlData.mids)){
//				                	selectcolor=color[1];
//				            	}
//				             	else{
//				                	selectcolor=color[2];
//				             	}
								//100%为绿色，非100%为红色
								if(row.rate=="100"){
				                	selectcolor=color[0];
				            	}
				             	else{
				                	selectcolor=color[2];
				             	}
								return '<div class="layui-col-md6" style="float:left">'+'<span style="float:right">'+xAxisData[row.pageType-1]+'</span>'+'</div>'+
							    	'<div class="layui-col-md6" style="float:right ;padding-left: 5px;padding-right: 5px;">'+'<div style="float:left ;background:'+selectcolor
							    	+';color:white ;height: 100%;width:100%">'+'<div style="padding-left: 5px;padding-right: 5px;">'+row.unQualified+"/"+row.total+'</div>'+'</div>'+'</div>';
						    }
					    },
                        {title: "合格率", field: "rate",width:'20%',sortable:false,
                        	render: function (data, type, row, meta) {
					    		return row.rate+'%';
					    	}
                        }
                   ]
            }),
            // add by zhangliang 20210309 新增投资者质量核查相关 start
            tableCPZT : $('#tableCPZT').joyintable({
                ajax: {
                    url: 'searchTableCPZT',
                    type: 'POST',
                    data: function() {
                        var parm={};
                        parm=investorsqualitycheck.getPageParams();
                        parm.index=curSelectedIndex+1;
                        return parm;
                    },
                },
                cols: [
                    {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
                    {title: "识别标识", field: "indentifyMark",width:'20%'},
                    {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
                    {title: "产品发行状态", field: "prdIssueSts",width:'20%'},
                    {title: "实际终止日期", field: "prdAclMDate",width:'20%', type:"date"},
                ]
            }),
            tableSLPP : $('#tableSLPP').joyintable({
                ajax: {
                    url: 'searchTableSLPP',
                    type: 'POST',
                    data: function() {
                        var parm={};
                        parm=investorsqualitycheck.getPageParams();
                        parm.index=curSelectedIndex+1;
                        return parm;
                    },
                },
                cols: [
                    {title: "产品登记编码", field: "prdRegistCode",width:'20%'},
                    {title: "产品起息日", field: "prdVDate",width:'20%', type:"date"},
                    {title: "持有日期", field: "holdDate",width:'20%', type:"date"},
                    {title: "产品端个人投资者数", field: "prdSingleTotal",width:'20%'},
                    {title: "产品端法人投资者数", field: "prdLegalTotal",width:'20%'},
                    {title: "产品端非法人投资者数", field: "prdUnLegalTotal",width:'20%'},
                    {title: "持有端个人投资者数", field: "investSingleTotal",width:'20%'},
                    {title: "持有端法人投资者数", field: "investLegalTotal",width:'20%'},
                    {title: "持有端非法人投资者数", field: "investUnLegalTotal",width:'20%'},
                    {title: "错误记录数", field: "errRecTotal",width:'20%'},
                ]
            }),
            checkOnClickRow : function (data) {
                $('#tableDiv0').toggle(false);
                $('#tableDiv1').toggle(false);
                $('#tableDiv2').toggle(false);
                $('#tableDivCPZT').toggle(false);
                $('#tableDivSLPP').toggle(false);
                if(data.pageType == '1'){
                    $('#tableDiv0').toggle(true);
                    investorsqualitycheck.table0.table ? investorsqualitycheck.table0.reload() : investorsqualitycheck.table0.load();
                }else if(data.pageType == '2'){
                    $('#tableDiv1').toggle(true);
                    investorsqualitycheck.table1.table ? investorsqualitycheck.table1.reload() : investorsqualitycheck.table1.load();
                }else if(data.pageType == '3'){
                    $('#tableDiv2').toggle(true);
                    investorsqualitycheck.table2.table ? investorsqualitycheck.table2.reload() : investorsqualitycheck.table2.load();
                }else if(data.pageType == '4'){
                    $('#tableDivCPZT').toggle(true);
                    investorsqualitycheck.tableCPZT.table ? investorsqualitycheck.tableCPZT.reload() : investorsqualitycheck.tableCPZT.load();
                }else if(data.pageType == '5'){
                    $('#tableDivSLPP').toggle(true);
                    investorsqualitycheck.tableSLPP.table ? investorsqualitycheck.tableSLPP.reload() : investorsqualitycheck.tableSLPP.load();
                }
            },
            // add by zhangliang 20210309 新增投资者质量核查相关 end
        }
    }();
    $(function() {
    	investorsqualitycheck.init();
        // 查询按钮点击事件
        $('#SvrInvestorsQualityCheck001_btn_search').bind("click",investorsqualitycheck.searchData);
    }); 
}())