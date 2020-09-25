function changeTab( id, name){
	var option = "";
	option += '<option value ="' + id + '">' + name + '</option>';
	$('#AgencyDetailsInf001_agencyName').empty();
	$("#AgencyDetailsInf001_agencyName").append('<option value =""></option>' + option);
	  
	$("#AgencyDetailsInf001_agencyName").val(id).trigger("change");
	$('#AgencyDetailsInf001_btn_search').trigger('click');
	
	layui.use('element', function(){
		var element = layui.element;
		//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
		element.tabChange('demo', 'agencyTree');
	});
	
}
var AgencyDetailsInf001 = function () {
    
	
	/**
     * 获取查询参数
     */
    var getPageParams = function () {
        var obj = $.serializeToObject('#AgencyDetailsInf001_searchForm');
        obj.agencyId = obj.agencyName;
        return obj;
    };


    /**
     * 定义持仓信息的数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getPageParams();
            }
        },
    	isMulti:true,
    	height:384,
    	checkbox:false,
        cols: [
            [{
                field: 'seqNo',
                title: 'AgencyDetailsInf001.seqNo',
                width: 100,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'agencyAttr',
                title: 'AgencyDetailsInf001.agencyAttr',
                width: 150,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'portfolioId',
                title: 'AgencyDetailsInf001.portfolioId',
                width: 130,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'portfolioName',
                title: 'AgencyDetailsInf001.portfolioName',
                width: 150,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetTypeOneStr',
                title: 'AgencyDetailsInf001.assetTypeOne',
                width: 150,
                sortField: 'assetTypeOne',
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetTypeTwoStr',
                title: 'AgencyDetailsInf001.assetTypeTwo',
                width: 150,
                sortField: 'assetTypeTwo',
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetTypeThreeStr',
                title: 'AgencyDetailsInf001.assetTypeThree',
                width: 150,
                sortField: 'assetTypeThree',
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetTypeFourStr',
                title: 'AgencyDetailsInf001.assetTypeFour',
                width: 150,
                sortField: 'assetTypeFour',
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetTypeFiveStr',
                title: 'AgencyDetailsInf001.assetTypeFive',
                width: 150,
                sortField: 'assetTypeFive',
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetName',
                title: 'AgencyDetailsInf001.assetName',
                width: 100,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetId',
                title: 'AgencyDetailsInf001.assetId',
                width: 150,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'assetTypeStr',
                title: 'AgencyDetailsInf001.assetType',
                width: 160,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'cost',
                title: 'AgencyDetailsInf001.cost',
                width: 160,
                type:'number',
                digit:2,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'amount',
                title: 'AgencyDetailsInf001.amount',
                width: 160,
                type:'number',
                digit:0,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'marketValue',
                title: 'AgencyDetailsInf001.marketValue',
                width: 160,
                type:'number',
                digit:2,
                rowspan: 2,
                sortable:false
            },            
            {
                field: 'interestReceivable',
                title: 'AgencyDetailsInf001.interestReceivable',
                width: 160,
                type:'number',
                digit:2,
                rowspan: 2,
                sortable:false
            },
            {
                field: 'positionAll',
                title: 'AgencyDetailsInf001.positionAll',
                width: 160,
                type:'number',
                digit:2,
                rowspan: 2,
                sortable:false
            },
            {
            	align : "center",
                title: 'AgencyDetailsInf001.directInvestment',
                colspan: 4,
                sortable:false
            },
            {
            	align : "center",
                title: 'AgencyDetailsInf001.indirectInvestment',
                colspan: 4,
                sortable:false
            }
            ],[
				 {field:'diCost', title: 'AgencyDetailsInf001.diCost', width: 130,type:'number',digit:2,sortable:false}
				,{field:'diAmount', title: 'AgencyDetailsInf001.diAmount', width: 130,type:'number',digit:0,sortable:false}
				,{field:'diMarketValue', title: 'AgencyDetailsInf001.diMarketValue',width: 130,type:'number',digit:2,sortable:false}
				,{field:'diInterestReceivable', title: 'AgencyDetailsInf001.diInterestReceivable', width: 130,type:'number',digit:2,sortable:false}
				
//				,{field:'iiPass', title: 'invest.channelId', width: 130,sortable:false}
				,{field:'iiCost', title: 'AgencyDetailsInf001.iiCost', width: 130,type:'number',digit:2,sortable:false}
				,{field:'iiAmount', title: 'AgencyDetailsInf001.iiAmount', width: 130,type:'number',digit:0,sortable:false}
				,{field:'iiMarketValue', title: 'AgencyDetailsInf001.iiMarketValue', width: 130,type:'number',digit:2,sortable:false}
				,{field:'iiInterestReceivable', title: 'AgencyDetailsInf001.iiInterestReceivable', width: 130,type:'number',digit:2,sortable:false}
             ]
            ],
            done:function(table, allData){
            	 $('.layui-none').attr("style","width:3555px!important;")
            }
        });

    /**
     * 导出事件
     */
    var exportExcel = function () {
    	var paramObj = getPageParams();
    	paramObj.fileName = $('#select2-AgencyDetailsInf001_agencyName-container').attr('title').trim()
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function () {
        //定义选项卡是哪一个
        var element;
        var index = '0';//tab卡初始状态
        layui.use('element',function () {
                    element = layui.element;
                    element.on('tab(demo)',function (data) {
                        //改变指令状态搜索框状态
                        index = data.index;
                    });
                });
        //搜索
        $('#AgencyDetailsInf001_btn_search').click(
                function () {
                	//先移除旧的svg
        			d3.select("svg").remove();
//                    if (index == '0') {//持仓信息
                    	if(getPageParams().agencyId == null || getPageParams().agencyId == undefined || getPageParams().agencyId == ''){
                    		Joyin.error(JoyinUtil.getMessage('RSK_W_0017',JoyinUtil.getLabel('AgencyDetailsInf001.agencyName')));
                    		return;
                    	}
                        table.table ? table.reload() : table.load();
//                    } else {//关联机构
//                    	if(getPageParams().agencyId == null || getPageParams().agencyId == undefined || getPageParams().agencyId == ''){
//                    		Joyin.error(JoyinUtil.getMessage('RSK_W_0017',JoyinUtil.getLabel('AgencyDetailsInf001.agencyName')));
//                    		return;
//                    	}
                        var agencyId = $.serializeToObject('#AgencyDetailsInf001_searchForm').agencyName;
                    	Joyin.ajax(JoyinUtil.getRealPath('invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/getTreeData'),{'agencyId':agencyId}, function (result) {
	                    	if(result.nodeCount){
                    			
//                    			Joyin.ajaxJson(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/getTreeData', {'agencyId':agencyId}, function(result) {
//                    		        if(result.nodeCount){
                    		        	var winW = window.innerWidth;
                    		    		var margin = {top: 50, right: 10, bottom: 10, left: 10},
                    		    		    width = winW - margin.left - margin.right,
                    		    		    halfWidth = width / 2,
                    		    		    height = 50*result.nodeCount,
                    		    		    i = 0,
                    		    		    duration = 500,
                    		    		    root;
                    		    	
                    		    		var getChildren = function(d){
                    		    		      var a = [];
                    		    		      if(d.leftChi) for(var i = 0; i < d.leftChi.length; i++){
                    		    		        d.leftChi[i].isRight = false;
                    		    		        d.leftChi[i].parent = d;
                    		    		        a.push(d.leftChi[i]);
                    		    		      }
                    		    		      if(d.rightChi) for(var i = 0; i < d.rightChi.length; i++){
                    		    		        d.rightChi[i].isRight = true;
                    		    		        d.rightChi[i].parent = d;
                    		    		        a.push(d.rightChi[i]);
                    		    		      }
                    		    		      return a.length?a:null;
                    		    		    }
                    		    		    ;
                    		    		
                    		    		var tree = d3.layout.tree()
                    		    		    .size([height, width])
                    		    		    ;
                    		    		
                    		    		var diagonal = d3.svg.diagonal()
                    		    		    .projection(function(d) { return [d.y, d.x]; });
                    		    		var elbow = function (d, i){
                    		    		      var source = calcLeft(d.source);
                    		    		      var target = calcLeft(d.target);
                    		    		      var hy = (target.y-source.y)/2;
                    		    		      if(d.isRight) hy = -hy;
                    		    		      return "M" + source.y + "," + source.x
                    		    		             + "H" + (source.y+hy)
                    		    		             + "V" + target.x + "H" + target.y;
                    		    		    };
                    		    		var connector = elbow;
                    		    		
                    		    		var calcLeft = function(d){
                    		    		  var l = d.y;
                    		    		  if(!d.isRight){
                    		    		    l = d.y-halfWidth;
                    		    		    l = halfWidth - l;
                    		    		  }
                    		    		  return {x : d.x, y : l};
                    		    		};
                    		    		
                    		    		
                    		    		var vis = d3.select("#chart").append("svg")
                    		    		    .attr("width", width + margin.right + margin.left)
                    		    		    .attr("height", height + margin.top + margin.bottom)
                    		    		  .append("g")
                    		    		  	.attr("class", 'parentSvg')
                    		    		    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
                    		    		
                    		    		d3.json(JoyinUtil.getRealPath("invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/getTreeData?agencyId='"+$.serializeToObject('#AgencyDetailsInf001_searchForm').agencyName+"'"), function(json) {
                    		    		  	
                    		    		  root = json;
                    		    		  root.x0 = height / 2;
                    		    		  root.y0 = width / 2;
                    		    		  
                    		    		  var t1 = d3.layout.tree().size([height, halfWidth]).children(function(d){return d.leftChi;}).nodeSize([50,200]),
                    		    		      t2 = d3.layout.tree().size([height, halfWidth]).children(function(d){return d.rightChi;}).nodeSize([50,200]);
                    		    		  t1.nodes(root);
                    		    		  t2.nodes(root);
                    		    		  
                    		    		  var rebuildChildren = function(node){
                    		    		    node.children = getChildren(node);
                    		    		    if(node.children) node.children.forEach(rebuildChildren);
                    		    		  }
                    		    		  rebuildChildren(root);
                    		    		  root.isRight = false;
                    		    		  update(root);
                    		    		});
                    		    		
                    		    		var toArray = function(item, arr){
                    		    		  arr = arr || [];
                    		    		  var i = 0, l = item.children?item.children.length:0;
                    		    		  arr.push(item);
                    		    		  for(; i < l; i++){
                    		    		    toArray(item.children[i], arr);
                    		    		  }
                    		    		  return arr;
                    		    		};
                    		    		
                    		    		function update(source) {
                    		    		  // Compute the new tree layout.
                    		    		  var nodes = toArray(source);
                    		    		  
                    		    		  // Normalize for fixed-depth.
                    		    		  //path 线长度150
                    		    		  nodes.forEach(function(d) { d.y = d.depth * 150 + halfWidth; });
                    		    		
                    		    		  // Update the nodes…
                    		    		  var node = vis.selectAll("g.node")
                    		    		      .data(nodes, function(d) { return d.id || (d.id = ++i); });
                    		    		
                    		    		  // Enter any new nodes at the parent's previous position.
                    		    		  var nodeEnter = node.enter().append("g")
                    		    		      .attr("class", "node")
                    		    		      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
                    		    		      //.on("click", click)//单击事件
                    		    		      //.on("contextmenu",rClick);//右击事件
                    		    		  
                    		    		  //坐标系修正
                    		    		  var newX = 0;
										  vis.selectAll("g.node")
										  	.attr("transform", function(d) {
										  		if(newX > d.x) newX = d.x;
											});	

										  d3.select("#chart").select("g.parentSvg")										
										  	.attr("transform", "translate(" + margin.left + "," + (-newX+margin.top) + ")");

                    		    		  //root图片添加
                    		    		  var a = nodeEnter.append("pattern")
											.attr("id", function(d) {
											  return "111";
											})
											.attr("x", -30)
											.attr("y", -30)
											.attr("height", 10)
											.attr("width", 10)
											.append("svg:image");	
		              		    			node.selectAll("image")
		              		    			.attr("preserveAspectRatio","none")										
											.attr("xlink:href", function(d) {
												return document.location.origin+'/tams/js/invest/risk/customerrelationtree/agencydetailsinf/agency.png';
											})
                    		    		 
                    		    		  nodeEnter.append("circle")
                    		    		      .attr("r", 1e-6)
                    		    		      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });
                    		    		  
		              		    		  //按钮
//                    		    		  nodeEnter.append("rect")
//                    		    		     .attr("height", function(d){
//                    		    		    	if(!d.children){
//                     		    		     		return "3";	
//                     		    		     	}
//                     		    		     	else{
//                     		    		     		return null;
//                     		    		     	}
//                            		    	 })
//                            		    	 .attr("width",function(d){
//                            		    		 if(!d.children){
//                     		    		     		return "10";	
//                     		    		     	}
//                     		    		     	else{
//                     		    		     		return null;
//                     		    		     	}
//                            		    	 })
//                            		    	 .attr("x",function(d){
//                            		    		 if(!d.children ){
//                            		    			 if(!d.isRight){
//                            		    				 return "-20";
//                            		    			 }
//                            		    			 else{
//                            		    				 return "9";
//                            		    			 }
//                     		    		     			
//                     		    		     	}
//                     		    		     	else{
//                     		    		     		return null;
//                     		    		     	}
//                            		    	 })
//                            		    	 .attr("y",function(d){
//                            		    		 if(!d.children){
//                            		    		 	return "-2";
//                 		    		     		 }
//                     		    		     	else{
//                     		    		     		return null;
//                     		    		     	}
//                            		    	 })
//                            		    	 .attr("fill","red")
//                            		    	 .attr("class","svg-wrapper")
//	                    		    		 .on("click",rClick);//右击事件
	                    		    		  
		              		    			nodeEnter.append("g")
		              		    				.on("click",rClick)//右击事件
		              		    				.attr('class','deleteNode')
		              		    				.attr('fill','red')
		              		    				.attr('transform',function(d){
		                    		    		  	if(!d.children ){
		                           		    			 if(!d.isRight){
		                           		    				 return "translate(-26,-12)";
		                           		    			 }
		                           		    			 else{
		                           		    				 return "translate(4,-12)";
		                           		    			 }
	                    		    		     	}
	                    		    		     	else{
	                    		    		     		return null;
	                    		    		     	}                    		    		  	
		                    		    		  })
	                    		    		    .on("click",rClick)//右击事件
		              		    				.append('g')
		              		    				.append("path")
	                    		    		    .attr("d", function(d){
	                    		    		    	
	                            		    	 })
	                            		    	 .attr("class",function(d){
	                            		    		 if(!d.children){
	                     		    		     		return "fill";	
	                     		    		     	}
	                     		    		     	else{
	                     		    		     		return null;
	                     		    		     	}
	                            		    	 })                            		    	                          		    	                             		    	 
		                    		    		 
		                    		    		  
		                    		    		  vis.selectAll("g.deleteNode").append('g').append("g")
		                    		    		  .append('path')
	                            		    	 .attr("d", function(d){
	                    		    		    	
	                            		    	 })
	                            		    	 .attr("class",function(d){
	                            		    		 if(!d.children){
	                     		    		     		return "fill";	
	                     		    		     	}
	                     		    		     	else{
	                     		    		     		return null;
	                     		    		     	}
	                            		    	 }) 

                            		    	 vis.selectAll("g.deleteNode").append('g').append("g")
	                    		    		  .append('path')
                            		    	 .attr("d", function(d){
                    		    		    	
                            		    	 })
                            		    	 .attr("class",function(d){
                            		    		 if(!d.children){
                     		    		     		return "fill";	
                     		    		     	}
                     		    		     	else{
                     		    		     		return null;
                     		    		     	}
                            		    	 }); 

                        		    	 vis.selectAll("g.deleteNode").append('g').append("g")
                    		    		  .append('path')
                        		    	  .attr("d", function(d){
                		    		    	
                        		    	 })
                        		    	 .attr("class",function(d){
                        		    		 if(!d.children){
                 		    		     		return "fill";	
                 		    		     	}
                 		    		     	else{
                 		    		     		return null;
                 		    		     	}
                        		    	 }); 
                        		    	 //添加 矩形，为了点击事件不会不精确
                        		    	 vis.selectAll("g.deleteNode").append('circle')
                    		    		  
                    		    		  .attr("cx",function(d){
                    		    		  	if(!d.children ){
												 if(!d.isRight){
													 return "18";
												 }
												 else{
													 return "4";
												 }
											}
											else{
												return null;
											}     	
                    		    		  })
                    		    		  .attr("cy",'11')
                    		    		  .attr("r",function(d){
                    		    		  	if(!d.children){
                 		    		     		return "7";	
                 		    		     	}
                 		    		     	else{
                 		    		     		return null;
                 		    		     	}
                    		    		  })
                    		    		  .attr("width",function(d){
                    		    		  	if(!d.children){
                 		    		     		return "23";	
                 		    		     	}
                 		    		     	else{
                 		    		     		return null;
                 		    		     	}
                    		    		  })
                    		    		  .attr("height",function(d){
                    		    		  	if(!d.children){
                 		    		     		return "23";	
                 		    		     	}
                 		    		     	else{
                 		    		     		return null;
                 		    		     	}
                    		    		  })
                    		    		  .attr('style','fill: #FF9F9C;stroke: #FF9F9C;')
                    		    		  //.attr('fill','transparent')
                    		    		  .attr('cursor','pointer');
                        		    	 vis.selectAll("g.deleteNode").append('g')
                    		    		  .append('rect')
                    		    		  .attr("width",function(d){
                    		    		  	if(!d.children){
                 		    		     		return "7";	
                 		    		     	}
                 		    		     	else{
                 		    		     		return null;
                 		    		     	}
                    		    		  })
                    		    		  .attr("height",function(d){
                    		    		  	if(!d.children){
                 		    		     		return "2";	
                 		    		     	}
                 		    		     	else{
                 		    		     		return null;
                 		    		     	}
                    		    		  })
                    		    		  .attr("x",function(d){
                    		    		  	if(!d.children ){
												 if(!d.isRight){
													 return "14.5";
												 }
												 else{
													 return "0.5";
												 }
											}
											else{
												return null;
											} 
                    		    		  })
                    		    		  .attr("y",'10')
                    		    		  .attr("fill",'white')
                    		    		  .attr('class','scroll-area')
                    		    		  //.attr('fill','transparent')
                    		    		  .attr('cursor','pointer');
                    		    		  //按钮	
		              		    			
                    		    		  nodeEnter.append("text")
                    		    		  		//y轴偏移  节点中文字的方向    14 就向上偏移  -8就向下偏移  3 居中 只有3，加1少1都不行 就不偏移
                    		    		      .attr("dy", function(d){
                    		    		    	   	if(!d.parent){
                    		    		    	   		return 46;
	                   		    				   	}
	                   		    				   	else{
	                   		    				   		return 3;
	                   		    				   	}	
                    		    		       })
                    		    			   .attr("dx", function(d) {//x轴偏移 
                    		    				   if( !d.children ){
                    		    					   if( d.isRight == true){
                    		    						   return 25;//25为了删除的-号
                    		    					   }else{
                    		    						   return -25;
                    		    					   }
                    		    					   
                    		    				   }
                    		    			   })
                    		    			   //文本 对齐方式：start,middle,end
                    		    		      .attr("text-anchor", function(d){
                    		    		    	 if( !d.children ){
                    		    		    		 if( d.isRight == true){
                    		    						   return 'start';
                    		    					   }else{
                    		    						   return 'end';
                    		    					   }
                    		    		    	 }else{
                    		    		    		 return 'middle';
                    		    		    	 } 
                    		    		       })
                    		    		      .text(function(d) { return d.name; })
                    		    		      .style("fill-opacity", 1e-6)
                    		    		      .style("font-size", function(d){
                    		    		    	  if(!d.parent){
                    		    		    		  return "13";
                    		    		    	  }
                    		    		    	  else if( !d.children ){
                     		    		    		return "11";
                 		    		    	 	  }else{
                     		    		    		return "12";
                 		    		    	 	  } 
                    		    		      })
                    		    		      .style("font-weight", function(d){
                    		    		    	  if( !d.children ){
                     		    		    		 return "bold";
                     		    		    	 }else{
                     		    		    		 return "normal";
                     		    		    	 } 
                    		    		      })//下面三个属性为机构点击属性
                    		    		      .style("cursor", function(d){
                    		    		    	  if(d.hasEndAgency){
                    		    		    		  return "pointer";
                    		    		    	  }else{
                    		    		    		return "null";  
                    		    		    	  }
                    		    		      })
                    		    		      .style("text-decoration", function(d){
                    		    		    	  if(d.hasEndAgency){
                    		    		    		  return "underline";
                    		    		    	  }else{
                    		    		    		return "null";  
                    		    		    	  }
                    		    		      })
                    		    		      .on("click", click);//颜色在401行设置.style("fill", function(d){return "#blue";});
                    		    		
                    		    		  // Transition nodes to their new position.
                    		    		  var nodeUpdate = node.transition()
                    		    		      .duration(duration)
                    		    		      .attr("transform", function(d) { p = calcLeft(d); return "translate(" + p.y + "," + p.x + ")"; })
                    		    		      ;
                    		    		
                    		    		  nodeUpdate.select("circle")
                    		    		//      .attr("r", 4.5)
                    		    		  	  .attr("r", function(d){
                    		    		  		  if(!d.parent){
                    		    		  			  return 31;
	                  		    		  		  }else if(d.children && d.children.length >0){
	                  		    		  			  return 27;
	                  		    		  		  }else{
	                  		    		  			  return null;
	                  		    		  		  }
                    		    		  	  })//圆形节点的背景颜色
                    		    		      .style("fill", function(d) { 
                    		    		    	  if(!d.parent){
                      		    		    	  	return 'url(#111)';
                      		    		    	  }
                      		    		    	  else if(d.children){
                      		    		    		  //取出随机颜色
                      		    		    		  var arr = ['#FF9347','#F1B112','#FB7171', '#7288E5','#38CFC3','#F99C29' ]; 
                      		    		    		  var index = Math.floor((Math.random()*arr.length)); 
                      		    		    		  return arr[index];
                      		    		    		  
                      		    		    	  }else{
                      		    		    		  return '#fff';
                      		    		    	  }
                		    		    	  });
                    		    		  
                    		    		  nodeUpdate.select("text")
                    		    		      .style("fill-opacity", 1)
	                    		    		  .style("fill", function(d) { 
	                    		    			  if(!d.parent){
	                    		    		  	  	return '#0188FB';
	                    		    		  	  }
	                    		    			  else if(d.children){//字体颜色
	                		    		    		  return '#FFFFFF';
	                		    		    	  }else{//判断是否有下级机构
	                		    		    		  if(d.hasEndAgency){
	                		    		    			  return 'blue';
	                		    		    		  }
	                		    		    		  else{
	                		    		    			  return '#000000'; 
	                		    		    		  }
	                		    		    		  
	                		    		    	  }
	            		    		    	  })
	            		    		    	  .attr("class", function(d){
	            		    		    		  if(d.hasEndAgency){
                		    		    			  return 'hasEndAgency';
                		    		    		  }
                		    		    		  else{
                		    		    			  return null; 
                		    		    		  }
	            		    		    	  });
                    		    		  
                    		    		  
                    		    		  // Transition exiting nodes to the parent's new position.
                    		    		  var nodeExit = node.exit().transition()
                    		    		      .duration(duration)
                    		    		      .attr("transform", function(d) { p = calcLeft(d.parent||source); return "translate(" + p.y + "," + p.x + ")"; })
                    		    		      .remove();
                    		    		
                    		    		  nodeExit.select("circle")
                    		    		      .attr("r", 1e-6);
                    		    		
                    		    		  nodeExit.select("text")
                    		    		      .style("fill-opacity", 1e-6);
                    		    		
                    		    		  // Update the links...
                    		    		  var link = vis.selectAll("path.link")
                    		    		      .data(tree.links(nodes), function(d) { return d.target.id; });
                    		    		
                    		    		  // Enter any new links at the parent's previous position.
                    		    		  link.enter().insert("path", "g")
                    		    		      .attr("class", "link")
                    		    		      .attr("d", function(d) {
                    		    		        var o = {x: source.x0, y: source.y0};
                    		    		        return connector({source: o, target: o});
                    		    		      });
                    		    		
                    		    		  // Transition links to their new position.
                    		    		  link.transition()
                    		    		      .duration(duration)
                    		    		      .attr("d", connector);
                    		    		
                    		    		  // Transition exiting nodes to the parent's new position.
                    		    		  link.exit().transition()
                    		    		      .duration(duration)
                    		    		      .attr("d", function(d) {
                    		    		        var o = calcLeft(d.source||source);
                    		    		        if(d.source.isRight) o.y -= halfWidth - (d.target.y - d.source.y);
                    		    		        else o.y += halfWidth - (d.target.y - d.source.y);
                    		    		        return connector({source: o, target: o});
                    		    		      })
                    		    		      .remove();
                    		    		
                    		    		  // Stash the old positions for transition.
                    		    		  nodes.forEach(function(d) {
                    		    		    var p = calcLeft(d);
                    		    		    d.x0 = p.x;
                    		    		    d.y0 = p.y;
                    		    		  });
                    		    		  
                    		    		  // Toggle children on click.
                    		    		  function click(d) {
                    		    			  if(!d.children && d.hasEndAgency && d.hasEndAgency == true ){
                    		    				  //old 覆盖掉以前的tree
//                    		    				  var option = "";
//                    		    				  option += '<option value ="' + d.id + '">' + d.name + '</option>';
//                    		    				  $('#AgencyDetailsInf001_agencyName').empty();
//                    		    				  $("#AgencyDetailsInf001_agencyName").append('<option value =""></option>' + option);
//                    		    				  
//                    		    				  $("#AgencyDetailsInf001_agencyName").val(d.id).trigger("change");
//                    		    				  $('#AgencyDetailsInf001_btn_search').trigger('click');
                    		    				  
                    		    				  //new 新弹出个tree窗口
//                    		    				  Joyin.dialog(JoyinUtil.getLabel("AgencyDetailsInf001"),
//		        		    			                basePath + "invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/init",
//		        		    			                "90%", "90%",{agencyId : d.id},function() {
//		        		    			                                    
//		        		    			                });
                    		    				  //无限套娃
                    		    				  window.open(window.document.location.href.substring(0,window.document.location.href.indexOf('?'))+ '?agencyId='+d.id, '_blank');
                		    			  		  }
                    		    			  
//                    		    		    if (d.children) {
//                    		    		      d._children = d.children;
//                    		    		      d.children = null;
//                    		    		    } else {
//                    		    		      d.children = d._children;
//                    		    		      d._children = null;
//                    		    		    }
//                    		    		    update(source);
                    		    		  }
                    		    		  
                    		    		  //右击事件
                    		    		  function rClick(d) {
                    		    			  // 先remove掉现有的右键菜单
//	                    		    		      $('body').find(".dropdown-menu").remove();
//	                    		    		      var scVar = ScollPostion();
                    		    		      //只给三级节点加上右键事件
                    		    		      if(!d.children){
//	                    		    		    	  // 加上右键菜单
                    		    		    	  	  var a =['<span id="delNode" name="delNode" value="'+d.seqNo+'"></span>'];
//	                    		    		          var a = ['<div class="dropdown-menu" style="left:0px;height: 30px;width: 50px;cursor: pointer;border-radius: 50;text-align: center;background-color: rgb(245, 245, 245);box-shadow: 0 0 3px rgba(86,96,117,.3);">', "<ul>", '<li class="kit-item" data-target="delNode" id="delNode" name="delNode" value="'+d.name+'">删除</li></ul>', "</div>"];
//	                    		    		          var oLeft = d.y0;
//	                    		    		          var oTop = d.x0;
//	                    		    		          //计算div的x y位置
//	                    		    		          var max = $('html').outerHeight();
//	                    		    		          var top = $('.layui-tab-content').outerHeight();
//	                    		    		          
//	                    		    		          var add = max - top - scrTopNew;
//	                    		    		          
//	                    		    		          
//	                    		    		          
	                    		    		          $('#tabHeadUl').prepend(a.join(""));
//	                    		    		          $('#tabHeadUl').css('left',oLeft + 30);
//	                    		    		          $('#tabHeadUl').css('top',oTop+add);
                    		    		    	  
                    		    		    	  Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    		    		    	  	var seqNo = $('#delNode').attr('value');
                    		    		  			var pId = $('#AgencyDetailsInf001_agencyName').val();
                    			    		  		var obj ={};
                    			    		  		obj.seqNo = seqNo;
                    			    		  		obj.pId = pId;
                    			    		  		Joyin.ajax(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/delNode', obj, function(result) {
                    			    		  	        Joyin.alert('删除成功!',"",function(){
                    			    		  	        	$('#AgencyDetailsInf001_btn_search').trigger('click');
                    			    		  	        });
                    			    		  	    });
                    			    		  	//	layui.layer.msg('删除成功!');
//	                    			    		  		$('body').find(".dropdown-menu").remove();
                    			    		  		
                    		    		    	  });
                    		    		      }
                    		    		      
                    		    		  }
                    		    		 
                    		    		}
//                    		        }
//                    		    });
	                    			
	                    	}else{
	                    		//Joyin.error('无机构数据！');
	                    	}
                    	
                    		//
                    	});	
                    		//
                    	
//                    }
                    
                });
        //导出
        $('#AgencyDetailsInf001_btn_export').click(function () {
        	if(getPageParams().agencyId == null || getPageParams().agencyId == undefined || getPageParams().agencyId == ''){
        		Joyin.error(JoyinUtil.getMessage('RSK_W_0017',JoyinUtil.getLabel('AgencyDetailsInf001.agencyName')));
        		return;
        	}
        	exportExcel();
        });
        
        //机构树新建事件
        $('#AgencyDetailsInf001_i_addTree').click(function () {
        	var orgNo = getPageParams().agencyId;
        
        	if(orgNo == null || orgNo == undefined || orgNo ==''){
        		Joyin.error(JoyinUtil.getMessage('RSK_W_0017',JoyinUtil.getLabel('AgencyDetailsInf001.agencyName')));
        	}
        	else{
        		Joyin.dialog(JoyinUtil.getLabel("AgencyDetailsInf002"),
	                basePath + "invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf002/init",
	                "99%", "99%",{orgNo : orgNo},function() {
        				$('#AgencyDetailsInf001_btn_search').trigger('click');              
	                });
        	}
        });
        
        //初始化查询
        if( AGENCYID && AGENCYID.length > 0){
        	$('#AgencyDetailsInf001_btn_search').trigger('click');
        	layui.use('element', function(){
        		var element = layui.element;
        		//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
        		element.tabChange('demo', 'agencyTree');
        	});
        }
        
    });


}();
