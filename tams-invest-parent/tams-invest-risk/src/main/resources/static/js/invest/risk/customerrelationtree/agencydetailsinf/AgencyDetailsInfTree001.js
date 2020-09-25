var AgencyDetailsInf001 = function () {
    
	


    /**
     * 画面加载完成
     */
    $(function () {
    	var agencyId = AGENCYID;
    	Joyin.ajax(JoyinUtil.getRealPath('invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/getTreeData'),{'agencyId':agencyId}, function (result) {
        	if(result.count){
    			//先移除旧的svg
    			d3.select("svg").remove();
    			Joyin.ajaxJson(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/getTreeData', {'agencyId':agencyId}, function(result) {
    		        if(result.count){
    		        	var winW = window.innerWidth;
    		    		var margin = {top: 30, right: 10, bottom: 50, left: 10},
    		    		    width = winW - margin.left - margin.right,
    		    		    halfWidth = width / 2,
    		    		    height = 30*result.count - margin.top - margin.bottom,
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
    		    		    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    		    		
    		    		d3.json(JoyinUtil.getRealPath("invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/getTreeData?agencyId='"+agencyId+"'"), function(json) {
    		    		  	
    		    		  root = json;
    		    		  root.x0 = height / 2;
    		    		  root.y0 = width / 2;
    		    		  
    		    		  var t1 = d3.layout.tree().size([height, halfWidth]).children(function(d){return d.leftChi;}),
    		    		      t2 = d3.layout.tree().size([height, halfWidth]).children(function(d){return d.rightChi;});
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
    		    		      .on("click", click)//单击事件
    		    		      .on("contextmenu",rClick);//右击事件
    		    		
    		    		  nodeEnter.append("circle")
    		    		      .attr("r", 1e-6)
    		    		      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });
    		    		  
    		    		  nodeEnter.append("text")
    		    		  		//y轴偏移  节点中文字的方向    14 就向上偏移  -8就向下偏移  3 居中 只有3，加1少1都不行 就不偏移
    		    		       .attr("dy", 3)
    		    			   .attr("dx", function(d) {//x轴偏移 
    		    				   if( !d.children ){
    		    					   if( d.isRight == true){
    		    						   return 10;
    		    					   }else{
    		    						   return -10;
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
    		    		    	  if( !d.children ){
     		    		    		 return "12";
     		    		    	 }else{
     		    		    		 return "13";
     		    		    	 } 
    		    		      })
    		    		      .style("font-weight", function(d){
    		    		    	  if( !d.children ){
     		    		    		 return "bold";
     		    		    	 }else{
     		    		    		 return "normal";
     		    		    	 } 
    		    		      })//下面三个属性为机构点击属性
    		    		      /*.style("cursor", function(d){
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
    		    		      })*/;//颜色在401行设置.style("fill", function(d){return "#blue";});
    		    		
    		    		  // Transition nodes to their new position.
    		    		  var nodeUpdate = node.transition()
    		    		      .duration(duration)
    		    		      .attr("transform", function(d) { p = calcLeft(d); return "translate(" + p.y + "," + p.x + ")"; })
    		    		      ;
    		    		
    		    		  nodeUpdate.select("circle")
    		    		//      .attr("r", 4.5)
    		    		  	  .attr("r", function(d){
    		    		  		  if(d.id && d.id == 'root'){
    		    		  			  return 30;
    		    		  		  }else if(d.children && d.children.length >0){
    		    		  			  return 30;
    		    		  		  }else{
    		    		  			  return 4.5;
    		    		  		  }
    		    		  	  })//圆形节点的背景颜色
    		    		      .style("fill", function(d) { 
    		    		    	  if(d.children){
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
    		    		    	  if(d.children){//字体颜色
    		    		    		  return '#FFFFFF';
    		    		    	  }else{//判断是否有下级机构
//    		    		    		  if(d.hasEndAgency){
//    		    		    			  return 'blue';
//    		    		    		  }
//    		    		    		  else{
    		    		    			  return '#000000'; 
//    		    		    		  }
    		    		    		  
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
    		    			  
    		    		  }
    		    		  
    		    		  //右击事件
    		    		  function rClick(d) {
    		    		      
    		    		  }
    		    		 
    		    		}
    		        }
    		    });
        			
        	}else{
        		Joyin.error('无机构数据！');
        	}
    	
    		//
    	});	
        
    });


}();
