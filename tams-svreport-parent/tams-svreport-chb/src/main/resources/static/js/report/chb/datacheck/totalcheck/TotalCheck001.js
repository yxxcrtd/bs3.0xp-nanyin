$(function () {
    // 当前选择的tabIndex
    var curSelectedIndex = "01";
    var table01 = $('#table01').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "理财产品存续只数", field: "prdNum",type:"number",digit:0,width:'150'},
                {title: "理财产品存续金额(万元)", field: "prdAmt",type:"money",width:'150'},
                {title: "月度统计表存续只数", field: "monthPrdNum",type:"number",digit:0,width:'150'},
                {title: "月度统计表存续金额(万元)", field: "monthPrdAmt",type:"money",width:'150'},
                {title: "偏离度", field: "deviationStr",width:'150',align:"right"},
//                {title: "理财产品存续只数(人民币产品)", field: "prdNumCny",type:"number",digit:0,width:'150'},
//                {title: "理财产品存续金额(万元、人民币产品)", field: "prdAmtCny",type:"money",width:'150'},
//                {title: "月度统计表存续只数(人民币产品)", field: "monthPrdNumCny",type:"number",digit:0,width:'150'},
//                {title: "月度统计表存续金额(万元、人民币产品)", field: "monthPrdAmtCny",type:"money",width:'150'},
//                {title: "偏离度(人民币产品)", field: "deviationCnyStr",width:'150',align:"right"},
                {title: "合格率", field: "detailYieldStr",width:'150',align:"right"},
            ]
    });
    var table02 = $('#table02').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "理财产品代码", field: "prdId",width:'150'},
                {title: "产品运作模式", field: "operateMode",width:'150'},
                {title: "存续金额", field: "prdAmt",type:'money',width:'150'},
                {title: "资产持仓金额", field: "prdAstholdAmt",type:'money',width:'150'},
                {title: "负债持仓金额", field: "prdBalholdAmt",type:'money',width:'150'},
                {title: "现金持仓金额", field: "prdCashholdAmt",type:'money',width:'150'},
                {title: "是否偏差过大", field: "isDeviation",width:'150'},
                {title: "持仓日期", field: "prdHolddate",type:'date',width:'150'},
                {title: "检查日期", field: "checkDate",type:'date',width:'150'},
            ]
    });
    var table03 = $('#table03').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "理财产品代码", field: "prdId",width:'150'},
                {title: "产品名称", field: "prdName",width:'200'},
                {title: "募集结束日", field: "raiseEnddate",type:'date',width:'150'},
                {title: "募集登记日", field: "raiseRegdate",type:'date',width:'150'},
                {title: "是否已登记", field: "isReg",width:'150'},
                {title: "是否登记不及时", field: "raiseIsDelay",width:'150'},
                {title: "检查日期", field: "checkDate",type:'date',width:'150'},
            ]
    });
    var table04 = $('#table04').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "资产负债编码", field: "assetCode",width:'150'},
                {title: "是否登记过底层", field: "isReg",width:'150'},
                {title: "当前发行总量", field: "botIssueNum",type:'number',digit:2,width:'150'},
                {title: "当前总发行金额", field: "botIssueAmt",type:'money',width:'150'},
                {title: "未投资头寸", field: "botUninvestCash",type:'money',width:'150'},
                {title: "底层资产总市值", field: "botAssetAmt",type:'money',width:'150'},
                {title: "底层负债总市值", field: "botDebtAmt",type:'money',width:'150'},
                {title: "是否偏差过大", field: "isDeviation",width:'150'},
                {title: "检查日期", field: "checkDate",type:'date',width:'150'},
            ]
    });
    var table05 = $('#table05').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "理财产品代码", field: "prdId",width:'150'},
                {title: "行内识别码", field: "prdInsId",width:'150'},
                {title: "产品名称", field: "prdName",width:'200'},
                {title: "募集起始日", field: "raiseStartdate",type:'date',width:'150'},
                {title: "募集结束日", field: "raiseEnddate",type:'date',width:'150'},
                {title: "产品起息日", field: "prdVdate",type:'date',width:'150'},
                {title: "资产类型", field: "assetType2",width:'150'},
                {title: "资产代码", field: "assetCode",width:'150'},
                {title: "持仓数量", field: "astHoldNum",type:'number',digit:2,width:'150'},
                {title: "检查日期", field: "checkDate",type:'date',width:'150'},
            ]
    });
    var table06 = $('#table06').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "理财产品代码", field: "prdId",width:'150'},
                {title: "行内识别码", field: "prdInsId",width:'150'},
                {title: "产品名称", field: "prdName",width:'150'},
                {title: "募集起始日", field: "raiseStartdate",type:'date',width:'150'},
                {title: "募集结束日", field: "raiseEnddate",type:'date',width:'150'},
                {title: "产品起息日", field: "prdVdate",type:'date',width:'150'},
                {title: "现金持仓金额", field: "prdCashholdAmt",type:'money',width:'150'},
            ]
    });
    var table07 = $('#table07').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'200'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "理财产品代码", field: "prdId",width:'200'},
                {title: "资产负债代码", field: "assetCode",width:'200'},
                {title: "最大单价", field: "astMaxUprice",type:'money',width:'300'},
                {title: "最小单价", field: "astMinUprice",type:'money',width:'300'},
            ]
    });
    var table08 = $('#table08').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                var parm={};
                parm=totalCheck.getPageParams();
                parm.index=curSelectedIndex;
                return parm;
            },
        },
        checkbox : false,
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
                {title: "机构代码", field: "issuePartyId",width:'150'},
                {title: "机构名称", field: "issuePartyName",width:'200'},
                {title: "资产负债编码", field: "assetCode",width:'150'},
                {title: "是否登记了底层", field: "isReg",width:'150'},
                {title: "当前发行总量", field: "botIssueNum",type:'number',digit:2,width:'150'},
                {title: "当前总发行金额", field: "botIssueAmt",type:'money',width:'150'},
                {title: "未投资头寸", field: "botUninvestCash",type:'money',width:'150'},
                {title: "底层资产总市值", field: "botAssetAmt",type:'money',width:'150'},
                {title: "底层负债总市值", field: "botDebtAmt",type:'money',width:'150'},
                {title: "是否偏差过大", field: "isDeviation",width:'150'},
            ]
    });
	// 判断对应table
    var getTable = function (checkType){
		if (checkType) {
			if ("01" == checkType) {
				return table01;
			} if ("02" == checkType) {
				return table02;
			} if ("03" == checkType) {
				return table03;
			} if ("04" == checkType) {
				return table04;
			} if ("05" == checkType) {
				return table05;
			} if ("06" == checkType) {
				return table06;
			} if ("07" == checkType) {
				return table07;
			} if ("08" == checkType) {
				return table08;
			}else {
				Joyin.error("不存在该tab页");
				return null;
			}
		} else {
			Joyin.error("请传入对应检查类型！");
			return null;
		}
	};
    // 阈值颜色提醒
    var color = ['rgb(168,222,100)','rgb(135,206,250)', 'rgb(255,153,153)'];
    // 自定义的阈值
    var xmlData={};
    // 读取后台阈值参数方法
    readConfig=function(){
        Joyin.ajaxSync('readConfig', null, function(result){
            //把读取到的配置文件赋值
        	xmlData.tops = result["tops"];
        	xmlData.mids = result["mids"];
            var resultHtml=$("#colorTitle")[0].innerHTML;
			var top = $('#top').text().substring(1,3)
			var mid = $('#end').text().substring(1,3)
			var topReg = '/'+top+'/g';
			var midReg = '/'+mid+'/g'
			resultHtml=resultHtml.replace(eval(topReg),xmlData.tops[0]);
			resultHtml=resultHtml.replace(eval(midReg),xmlData.mids[0]);
            $("#colorTitle")[0].innerHTML=resultHtml;
        });
    };
    // 先读取配置，后面初始化页面用到
    readConfig(); 
    // 展示与上层的样式
	var labelTop = {
		normal : {
			label : {
				show : false,
				position : 'center',
				textStyle : {
					baseline : 'bottom'
				}
			},
			labelLine : {
				show : false
			}
		}
	};
	// 扇形图样式
	var labelFromatter = {
		normal : {
			label : {
				formatter : function(params) {
					if (params.name == "other"){
						return "";
					}
					return params.value + '%'
				},
				position:'center',
		        textStyle:{
		            color:'black',
		            fontSize:'20',
		            align:'center',
		            fontWeight:'bold',
		        }
			},
			color : function(param) {
				var index = param.seriesIndex;
				// 根据合格率动态展示颜色
				if (parseFloat(param.data.value) >= parseFloat(xmlData.tops[index]) ) {
					return color[0];
				} else if (parseFloat(param.data.value) >= parseFloat(xmlData.mids[index])) {
					return color[1];
				} else {
					return color[2];
				}
			}
		},
	}
	// 展示于底层的样式
	var labelBottom = {
		normal : {
			color : '#ccc',
			label : {
				show : false,
				position : 'center'
			}
		}
	};
    // 内外半径
	var radius = [ '50%', '65%' ];
	var option = {
        tooltip: {
            trigger: 'item',
            formatter: function (param) {
				return param.data.tips + "<br/>" + "合格率：" + param.data.realvalue + "%";
            }
        },
        // 绘制外部框线
        grid: {show: true, top: 5, left: 0,right : 0, bottom: 5},
	    series : [
	        {
	            type : 'pie',
	            center : ['6.25%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x: '0%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：100%-绝对值（存续余额-月度统计表存续余额）/月度统计表存续余额*100%"},
	                {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：100%-绝对值（存续余额-月度统计表存续余额）/月度统计表存续余额*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['18.25%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x:'10%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
		            {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：100%-偏差过大封闭式产品数/存续封闭式产品数*100%"},
		            {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：100%-偏差过大封闭式产品数/存续封闭式产品数*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['31%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x:'20%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
                    {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：已报送数量/应报送数量*100%"},
                    {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：已报送数量/应报送数量*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['43%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x:'30%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
                    {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：实报底层持仓资产数/应报底层持仓资产数*100%"},
                    {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：实报底层持仓资产数/应报底层持仓资产数*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['55%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x:'40%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
                    {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：100%-存在持仓为负的理财产品只数/存续产品数*100%"},
                    {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：100%-存在持仓为负的理财产品只数/存续产品数*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['67%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x: '50%',    // for funnel
	            itemStyle : labelFromatter,
	            data : [
                    {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：100%-存在现金持仓为负的理财产品只数/存续产品数*100%"},
                    {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：100%-存在现金持仓为负的理财产品只数/存续产品数*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['79.5%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x:'60%',    // for funnel
	            itemStyle : labelFromatter,
	            data : [
                    {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：100%-交易价格偏差过大资产对应的产品数/登记系统存续产品数*100%"},
                    {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：100%-交易价格偏差过大资产对应的产品数/登记系统存续产品数*100%"}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['92%', '50%'],
	            radius : radius,
                hoverAnimation:false,
	            x:'70%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
                    {name:'cal', value:0,realvalue:0,itemStyle : labelTop,tips:"计算规则：100%-计划发行金额与资产市值偏差过大数量/报送底层的计划数*100%"},
                    {name:'other', value:100,realvalue:0, itemStyle : labelBottom,tips:"计算规则：100%-计划发行金额与资产市值偏差过大数量/报送底层的计划数*100%"}
	            ]
	        }
	    ]
	};
	// 默认展示第一个列表
	$("#tabpan01").show();
    var pieDivs = $(".joy-pie .joy-pie-div");
    var pie = echarts.init(pieDivs[0]);
    pie.setOption(option, true);
//    $(".tab-pane").hide();
    pie.on('click', function (params) {
            var index = params.seriesIndex;
            if (typeof index != "undefine" 
            	&& index != null) {
                curSelectedIndex = "0"+(index+1);
            	// 根据上面的内容自动切换tab页
				$("#tab0"+(index+1)).click();
				// 动态展示每项指标的阈值
				var resultHtml=$("#colorTitle")[0].innerHTML;
				var top = $('#top').text().substring(1,3)
				var mid = $('#end').text().substring(1,3)
				var topReg = '/'+top+'/g';
				var midReg = '/'+mid+'/g'
				resultHtml=resultHtml.replace(eval(topReg),'tp');
				resultHtml=resultHtml.replace(eval(midReg),'md');
				$("#colorTitle")[0].innerHTML=resultHtml;
				var resultHtmlReal=$("#colorTitle")[0].innerHTML;
				var topReal = $('#top').text().substring(1,3)
				var midReal = $('#end').text().substring(1,3)
				var topRegReal = '/'+topReal+'/g';
				var midRegReal = '/'+midReal+'/g'
				resultHtmlReal=resultHtmlReal.replace(eval(topRegReal),xmlData.tops[index]);
				resultHtmlReal=resultHtmlReal.replace(eval(midRegReal),xmlData.mids[index]);
				$("#colorTitle")[0].innerHTML=resultHtmlReal;
                pie.setOption(option, true);
                if($('#searchForm').joyinvalid()) {
                    var table = getTable(curSelectedIndex);
                    if (table.table){
                        table.reload();
                    } else {
                    	table.load();
                    }
                }
            }
    
    });
    window.onresize = function () {
        pie.resize();
    };
    window.totalCheck = function() {
        return {
            /**
             * 初始化的函数
             */
            init : function(){
            	// 防止报出时间为空的信息
				setTimeout(function() {
					totalCheck.searchData();
				}, 500);
            },
            getPageParams : function() {
                return {
                    // 查询日期
                    searchDate : $('#searchDate').val(),
                    // 机构代码List
                    issuePartyIdList : $('#issuePraty').val()==null?null:$('#issuePraty').val().join(","),
                };
            },
            searchData : function () {
                var param = totalCheck.getPageParams();
                if(!param.searchDate){
                    Joyin.error("查询日期不可为空！");
                    return;
                }
                Joyin.ajax('searchPage', param, function(result) {
                    if (getLength(result) <= 0){
                        for (var indext = 1; indext <= 8 ; indext++){
                            option.series[indext-1].data[0].value=0;
                            option.series[indext-1].data[1].value=100;
                            option.series[indext-1].data[0].realvalue=0;
                            option.series[indext-1].data[1].realvalue=100;
                        }
                    } else {
                        for (var index in result){
                            var data=result[index];
                            option.series[index-1].data[0].value=(parseFloat(data.yield)*100).toFixed(0);
                            option.series[index-1].data[1].value=(parseFloat(1-data.yield)*100).toFixed(0);
                            option.series[index-1].data[0].realvalue=(parseFloat(data.yield)*100).toFixed(0);
                            option.series[index-1].data[1].realvalue=(parseFloat(data.yield)*100).toFixed(0);
                        }
                    }
                    pie.setOption(option, true);
                });
                if($('#searchForm').joyinvalid()) {
                    var table = getTable(curSelectedIndex);
                    if (table.table){
                        table.reload();
                    } else {
                    	table.load();
                    }
                }
            },
        }
    }();
    $(function() {
        totalCheck.init();
        // 查询按钮点击事件
        $('#SvrTotalCheck001_btn_search').bind("click",totalCheck.searchData);
    }); 
	// 判断字符是否为空的方法
	function isEmpty(obj) {
		if (typeof obj == "undefined" || obj == null || obj == "") {
			return true;
		} else {
			return false;
		}
	};
	// tab选项卡切换
	$("[lay-tab-id]").click(function(){
		var index = this.getAttribute("lay-tab-id");
        $(".tab-pane.active").hide();
		$(".tab-pane").hide();
		$("#tabpan"+index).show();
		// 重新设置当前标签
		curSelectedIndex = index;
	});
	// 获取数组长度
	function getLength(obj){
		var count = 0;
		for(var i in obj){
			if(obj.hasOwnProperty(i)){
	                count++;
			}
		}
		return count;　　
	};
}())