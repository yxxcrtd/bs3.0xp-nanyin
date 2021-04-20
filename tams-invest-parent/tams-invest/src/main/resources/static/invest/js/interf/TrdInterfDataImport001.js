$(function () {

	// var queryDate;
	// var status;
	// var execTaskId;
	// var prodId;

	//轮询间隔事件
	var time = 5000;
	//轮询启动接口ID
	var interId;
	//前置流水号
	var preseqno;

	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		var pageParam = {
			queryDate : $('#queryDate').val(),
			// prodId : $('#prodId').val(),
			prodId : $('#prodId').select2('data'),
			datas :null,
			preseqno:preseqno
		}
		var treeObj=$.fn.zTree.getZTreeObj("typeDatatree_tree"),
			nodes=treeObj.getCheckedNodes(true),
			datas = "";
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].check_Child_State==-1){
				datas+=nodes[i].id + ",";
			}
		}
		pageParam.datas = datas;
		// if ($("#prodId").val()) {
		// 	pageParam.prodId = $("#prodId").val().join(",");
		// } else {
		// 	pageParam.prodId = null;
		// }
		if (pageParam.prodId) {
			var prodList = pageParam.prodId,
				prodIds="";
			for(var i=0;i<prodList.length;i++){
				if(prodList[i].selected){
					prodIds+=prodList[i].object + ",";
				}
			}
		}
		pageParam.prodId = prodIds;
		return pageParam;
	};

	var getTreeParams = function(){
		return {
			name: $('#name').val()
		}
	}

	var getUUID = function() {
		/*生成32位随机流水号*/
		/*默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1*/
		var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
		var maxPos = chars.length;
		var pwd = '';
		for (i = 0; i < 32; i++) {
			pwd += chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return pwd;
	};



	$('#typeDatatree').joyinMixTree({
		onTreeLoadSuccess: function() {
			$.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(true);
			$.fn.zTree.getZTreeObj('typeDatatree_tree').checkAllNodes(true);
		}
	});

	function expandAll() {
		$.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(!$('#expandAll').is(':checked'));
	};


	/**
	 * 日志页签
	 */
	var mainTable = function(obj){

		var id = $(obj).attr("id");
		if(!id){
			id='log-title';
		}
		//进入前status初始化
		status = "";

		if(id=='success-title'){
			status = '02';
		}else if(id=='error-title'){
			status = '03';
		}else if(id=='warning-title'){
			status = '09';
		}else if(id=='eWarning-title'){
			status = '04';
		}
		//根据title获取table
		var table = id.replace('title','table');

		var docH= $(document).height();


		var tabTable = $('#'+table).joyintable({
			ajax: {
				url: 'findLogPage',
				type: 'POST',
				data : function(){
					return getSearchParams();
				}
			},
			done:function(data){
				if(data.length>0){
					//如果结束时产品全部执行完毕,则取消轮询
					var flag = 1;
					var index = 0;
					for(var i=0; i<data.length;i++){
						var status = data[i].status;
						if(status==1||status==0){
							flag = 0;
							index++;
						}
					}
					if(flag==1){
						stopInterval();

						$("#TrdInterfDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
						clickFlag=true;
						progress(1);
					}else{
						progress((data.length-index)/data.length);
					}
				}
			},
			height:docH-160,
			checkbox: false,
			cellMinWidth : 100,
			rowno:true,
			cols:[ {field:'id', title: 'id', visible:false},
				{field:'busidate', title: '业务日期', width:120, sort: true},
				{field:'rtname', title: '任务名称',width:160, sort: true},
				{field:'status', title: '执行状态',width:105,
					render: function (data, type, row, meta) {
						if(data=='2'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
						}else if(data=='3'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
						}else if(data=='1'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#13a7edd1;color:#fff">'+'执行中'+'</span>';
						}
					}},
				{field:'runmsg', title: '执行结果',width:300,sort: true},
				{field:'optdate',sorfiled:"optdate", title: '操作日期',width:120, sort: true},
				{field:'runtime', title: '耗时(s)',width:100,
					render: function (data, type, row, meta) {
						return (data/1000).toFixed(2);
					}
				},
				{field:'sttime',sorfiled:"sttime", title: '开始时间',width:150, sort: true},
				{field:'edtime',sorfiled:"edtime",title: '结束时间',width:150, sort: true}]
			,loadonce:true,
			page:false
		});
		//tabTable.load();
		if(!tabTable.table) {
			tabTable.load();
		}else {
			tabTable.reload();
		}
	};

	//发送启动命令
	var doInter = function (){
		var param = getSearchParams();
		preseqno = getUUID();
		if(param.datas==''){
			layer.alert('请选择导入的接口文件！');
			return ;
		}
		if(param.queryDate==''){
			layer.alert('请选择时间!');
			return ;
		}
		Joyin.ajax('runAs',
			{
				datas : param.datas,
				queryDate : param.queryDate,
				prodList : param.prodId,
				preseqno : preseqno
			},
			function(data) {
				var result = data.statuCode;
				if(result==200) {
					//再次执行启动前停止上一次轮询
					stopInterval();
					//成功调用轮询
					//调用后一秒再查询接口,快速展示界面数据
					startInterval();
					progress(0);
					$("#TrdInterfDataImport001_btn_doInterBtn").addClass("layui-btn-disabled");
					clickFlag = false;
				}
			});
		// $.ajax({
		// 	url : "checkExecute",
		// 	type : "POST",
		// 	data : '',
		// 	success: function(msg){
		// 		if(msg>0){
		// 			layer.alert('当前正在进行登帐，请稍后再试!!');
		// 			return;
		// 		}else{
		//
		// 		}
		// 	},
		// 	failure : function() {
		// 	}
		// });
	};

	//发送停止命令
	var stopInter=function(){
		Joyin.ajax('stopInterf',
			'',
			function() {
				mainTable();
				$("#TrdInterfDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
				clickFlag=true;
				layui.layer.alert("批处理停止请求已经发送");
			});
		//停止轮询
		stopInterval();
	};

	var jumpLogDetail = function(){

		//跳转详情界面
		Joyin.dialog(JoyinUtil.getLabel("接口日志"),
			JoyinUtil.getRealPath("/valuation/interf/inertfLog/ValInertfLog001/init"),
			"95%", "95%", '',
			function(){});

	}

	var processIndex;

	var doprocess = function(){
		processIndex = layer.open({
			type: 1
			,title: false //不显示标题栏
			,closeBtn: false
			,area: '300px;'
			,shade: 0.6
			,id: 'JOY_process' //设定一个id，防止重复弹出
			,moveType: 1 //拖拽模式，0或者1
			,content: '<div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="progressBar" id="progressBar"><div class="layui-progress-bar" lay-percent="0%">0%</div></div>'
			,success: function(layero){
				layero.css('background', 'transparent');
				// 开始进度条
				progress(0);
			}
		});
	}

	function progress(percent){
		var percentText = parseInt(percent * 100) + '%';
		layui.element.progress('progressBar', percentText);
		$('#progressBar').find('.layui-progress-bar').text(percentText);
	}

	function close(){
		layer.close(processIndex);
	}

	//轮询开始
	var startInterval = function(){
		//每5秒进行一次轮序
		interId = setInterval(mainTable,time);
	};
	//轮询结束
	var stopInterval = function(){
		clearInterval(interId);
	}

	mainTable();

	/**
	 * 取消全选或者全选按钮
	 */
	var checkOrNoAll = function() {
		var treeObj_ = $.fn.zTree.getZTreeObj("typeDatatree_tree");
		if(treeObj_) {
			var noCheckNodes_ = treeObj_.getCheckedNodes(false);
			if(noCheckNodes_.length > 0) {
				treeObj_.checkAllNodes(true);//全选
			}
			else {
				treeObj_.checkAllNodes(false);//取消全选
			}
		}
	}


	var clickFlag = true;

	/**
	 * 画面加载完成
	 */
	$(function() {

		// 绑定查询按钮事件
		$('#TrdInterfDataImport001_btn_doInterBtn').click(function() {
			if(clickFlag){
				doInter();
			}
		});

		// 绑定查询按钮事件
		$('#TrdInterfDataImport001_btn_stopInterBtn').click(function() {
			stopInter();
		});
		// 绑定查询按钮事件
		$('#TrdInterfDataImport001_btn_logBtn').click(function() {
			jumpLogDetail();
		});

		//日志页签点击事件
		$('#log-title').click(function(){
			mainTable(this);
		});

		$('#success-title').click(function(){
			mainTable(this);
		});
		$('#error-title').click(function(){
			mainTable(this);
		});
		$('#warning-title').click(function(){
			mainTable(this);
		});

		$('#eWarning-title').click(function(){
			mainTable(this);
		});

        // 绑定全选或者取消全选宫那个
		$('#TrdInterfDataImport001_btn_checkall').click(function() {
			checkOrNoAll();
		});

		var tree=$("#typeDatatree");
		var docH= $(document).height();
		var tabHeadUlH=100;
		tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");
		tree.children('div').eq(1).css("overflow-x", "hidden");

	});
});


function serachKey(){
	$('#typeDatatree').reloadTree({
		name: $('#name').val()
	});
}
