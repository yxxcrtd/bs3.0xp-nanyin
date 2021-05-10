$(function () {
	//$('#executeDate').val(sysDate);

	var excuteDate;
	var status;
	var execTaskId;

	//轮询间隔事件
	var time = 5000;
	//轮询启动接口ID
	var interId;

	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {exDate : $.trim(excuteDate),
			status : status,
			execTaskId : $.trim(execTaskId)}
	};

	var getTreeParams = function(){
		return {
			TmpCName: $('#TmpCName').val()
		}
	}




	$('#typeDatatree').joyinMixTree({
		onTreeLoadSuccess: function() {
			$.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(true);
			$.fn.zTree.getZTreeObj('typeDatatree_tree').checkAllNodes(true);
		}
	});

	function expandAll() {
		$.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(!$('#expandAll').is(':checked'));
	};

	//搜索框查询勾选树菜单
	function CheckNodeById(pid){
		var zTree = $.fn.zTree.getZTreeObj("typeDatatree_tree");
		var node = zTree.getNodeByParam("id",pid );
		zTree.cancelSelectedNode();//先取消所有的选中状态
		zTree.selectNode(node);//将指定ID的节点选中
		zTree.expandNode(node, true, false);//将指定ID节点展开
		zTree.checkNode(node);//将制定ID勾选
	}

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
						//$("#ValTransactionDataImport001_btn_doInterBtn").attr("title", '').prop("disabled", false);
						//setTimeout(close(),3000);

						$("#ValTransactionDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
						clickFlag=true;
						progress(1);
					}else{
						progress((data.length-index)/data.length);
					}
				}
			},
			dblclick:function(table, data) {
				//跳转详情界面
				if(data.status=='02'||data.status=='09'){
					Joyin.dialog(JoyinUtil.getLabel("查看导入数据："+data.taskCname),
						JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport002/init"),
						"95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno},
						function(){
							//table.reload();
						});
				}
			},
			height:docH-160,
			checkbox: false,
			cellMinWidth : 100,
			rowno:true,
			cols:[ {field:'seqno', title: 'seqno', visible:false},
				{field:'busiDate', title: '业务日期', width:120, sort: true},
				{field:'taskCname', title: '任务名称',width:160, sort: true},
				{field:'status', title: '执行状态',width:105,
					render: function (data, type, row, meta) {
						if(data=='02'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
						}else if(data=='03'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
						}else if(data=='01'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#13a7edd1;color:#fff">'+'执行中'+'</span>';
						}else if(data=='00'){
							return '<span class="layui-badge-rim" style="width:55px;height:20px;">'+'未执行'+'</span>';
						}else if(data=='04'){
							return '<span class="layui-badge-rim" style="width:65px;height:20px;">'+'终止执行'+'</span>';
						}else if(data=='09'){
							return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
						}
					}},
				{field:'detailAction', title: '执行结果',width:300, sort: true},
				{field:'startDateFmt',sorfiled:"startTime", title: '操作日期',width:120, sort: true},
				{field:'lastTime', title: '耗时(s)',width:100, sort: true},
				{field:'startTimeFmt',sorfiled:"startTime", title: '开始时间',width:120, sort: true},
				{field:'endTimeFmt',sorfiled:"endTime",title: '结束时间',width:120, sort: true}]
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

		var startDate = $.trim($('#executeDate').startDate());
		var endDate = $.trim($('#executeDate').endDate());
		var booksetId = $.trim($('#booksetId').val());
		var batchBooksetType = $("#batchBooksetType").val();
		var booksetGroup = $.trim($("#booksetGroup").val());
		//var valModel = $("#isShowfl").next().hasClass('layui-form-onswitch') ? 'INTERF_MODEL_SINGLE' : 'INTERF_MODEL_ALL';
		//INTERF_MODEL_SINGLE代表: 指定账套
		if(booksetId == "" && booksetGroup == ""){
			var valModel = 'INTERF_MODEL_ALL' ;
		} else{
			var valModel = 'INTERF_MODEL_SINGLE' ;
		}

		var treeObj=$.fn.zTree.getZTreeObj("typeDatatree_tree"),
			nodes=treeObj.getCheckedNodes(true),
			datas = "";
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].check_Child_State==-1){
				datas+=nodes[i].id + ",";
			}
		}

		if(valModel == 'INTERF_MODEL_SINGLE' && batchBooksetType == ""){
			layer.alert('请选择账套或账套组！');
			return;
		}else {
			if(batchBooksetType == "0" && booksetGroup  == ""){// 账套组
				layer.alert('请选择账套组！');
				return;
			}else if(batchBooksetType == "1" && booksetId == ""){// 账套
				layer.alert('请选择账套！');
				return;
			}
		}

		if(datas==''){
			layer.alert('请选择导入的接口文件！');
			return ;
		}
		if(startDate==''||endDate==''){
			layer.alert('请选择时间!');
			return ;
		}
		$.ajax({
			url : "checkExecute",
			type : "POST",
			data : {
				startDate : $.trim($('#executeDate').startDate()),
				endDate : $.trim($('#executeDate').endDate()),
				batchBooksetType : $("#batchBooksetType").val(),
				booksetGroup : $.trim($("#booksetGroup").val()),
				booksetId : booksetId,
				valModel: valModel
			},
			success: function(msg){
				if(msg>0){
					layer.alert('当前正在进行登帐，请稍后再试!!');
					return;
				}else if(msg == -1){
					layer.alert('存在交易导入日期与账套或者账套组中一个产品账套日期不一致');
					return;
				}else{
					Joyin.ajax('runAs',
						{datas : datas,
							startDate:startDate,
							endDate:endDate,
							booksetId:booksetId,
							booksetGroup: booksetGroup,
							valModel:valModel,
							batchBooksetType: batchBooksetType
						},
						function(data) {
							var result = JSON.parse(data);
							if(result.isCheck==1){

								//再次执行启动前停止上一次轮询
								stopInterval();
								//成功调用轮询
								excuteDate = result.createDate;
								execTaskId = result.execTaskId;
								//调用后一秒再查询接口,快速展示界面数据
								startInterval();
								progress(0);
								$("#ValTransactionDataImport001_btn_doInterBtn").addClass("layui-btn-disabled");
								//$("#ValTransactionDataImport001_btn_doInterBtn").prop("disabled",true).trigger("change");
								clickFlag=false;
								//doprocess();
							}else if(result.isCheck==0){
								layui.layer.alert("任务执行中，请稍后再试!");
							}else{
								layui.layer.alert("发送启动请求失败");

							}
						});
				}
			},
			failure : function() {
			}
		});

		/*        $.ajax({
                    type : "POST",
                    url : 'runAs',
                    data : {datas : datas,
                        startDate:startDate,
                        endDate:endDate},

                    success : function(data) {
                        var result = JSON.parse(data);
                        if(result.isCheck==1){

                            //再次执行启动前停止上一次轮询
                            stopInterval();
                            //成功调用轮询
                            excuteDate = result.createDate;
                            execTaskId = result.execTaskId;
                          //调用后一秒再查询接口,快速展示界面数据
                            startInterval();
                            progress(0);
                            $("#ValTransactionDataImport001_btn_doInterBtn").addClass("layui-btn-disabled");
                            //$("#ValTransactionDataImport001_btn_doInterBtn").prop("disabled",true).trigger("change");
                            clickFlag=false;
                            //doprocess();
                        }else if(result.isCheck==0){
                            layui.layer.alert("任务执行中，请稍后再试!");
                        }else{
                            layui.layer.alert("发送启动请求失败");

                        }
                    }
                });*/
	};

	//发送停止命令
	var stopInter=function(){
		Joyin.ajax('stopInterf',
			'',
			function() {
				mainTable();
				$("#ValTransactionDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
				clickFlag=true;
				layui.layer.alert("批处理停止请求已经发送");
			});
		/*		$.ajax({
                    type : "POST",
                    url : 'stopInterf',

                    success : function(data) {
                        mainTable();
                        $("#ValTransactionDataImport001_btn_doInterBtn").removeClass("layui-btn-disabled");
                        clickFlag=true;
                        layui.layer.alert("批处理停止请求已经发送");
                    }
                });*/
		//停止轮询
		stopInterval();
	};


	//轮询开始
	var startInterval = function(){
		//每5秒进行一次轮序
		interId = setInterval(mainTable,time);
	};
	//轮询结束
	var stopInterval = function(){
		clearInterval(interId);
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

	var jumpLogDetail = function(){
		//跳转详情界面
		Joyin.dialog(JoyinUtil.getLabel("接口日志"),
			JoyinUtil.getRealPath("/valuation/interf/inertfLog/ValInertfLog001/init"),
			"95%", "95%", '',
			function(){});
	}

	mainTable();

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


	var dateChange = function(){

		//当前使用区间接入，取消平台日验证。

//		 setTimeout(function() {
//			 var date = $.trim($('#executeDate').val());
//		var nowDate = new Date(Date.parse(sysDate));
//		var chooseDate = new Date(Date.parse(date));
//		if(nowDate>chooseDate){
//            layui.layer.alert("选择日期小于平台日，请判断是否影响已生成账务信息，若影响需要调整账套会计日期，重新进行登账批处理");
//		}
//		 },10);




//		else if(nowDate<chooseDate){
//            layui.layer.alert("选择日期必须小于等于平台日");
//            $('#executeDate').val(sysDate);
//		}
	}

	var clickFlag = true;
	/**
	 * 画面加载完成
	 */
	$(function() {



		//typeTable.load();

		$('#tmpEName').change(function(){
			if($('#tmpEName').val()){
				//CheckNodeById($('#tmpEName').val());
			}
		});

		// 绑定查询按钮事件
		$('#ValTransactionDataImport001_btn_doInterBtn').click(function() {
			if(clickFlag){
				doInter();
			}
		});

		// 绑定查询按钮事件
		$('#ValTransactionDataImport001_btn_stopInterBtn').click(function() {
			stopInter();
		});
		// 绑定查询按钮事件
		$('#ValTransactionDataImport001_btn_logBtn').click(function() {
			jumpLogDetail();
		});

		$('#executeDate').change(function(){
			dateChange();
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
		$('#ValTransactionDataImport_btn_checkall').click(function() {
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
		TmpCName: $('#TmpCName').val()
	});
}