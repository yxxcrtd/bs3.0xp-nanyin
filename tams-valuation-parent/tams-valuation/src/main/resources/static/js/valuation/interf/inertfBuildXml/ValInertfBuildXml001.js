

$(function() {
    
	var clickFlag = true;
    /**
     *执行
     */
        var runAsData = function() {
            if ($.trim($('#executeDate').val()) == null || $.trim($('#executeDate').val()) == '') {
            	progress(0);
            	Joyin.error(JoyinUtil.getMessage("没有选定时间"));
                return;
            }
            var startDate = $('#executeDate').startDate();
            var endDate = $('#executeDate').endDate();
            
            
            Joyin.ajax('runAs', 
                    { startDate:startDate,
                     endDate:endDate
                    },                    
                 function(data) {
                    var result = JSON.parse(data); 
                   // var object = JSON.stringify(result);      
                	//var key=Object.keys(result[0]).length;
                   var count=0;
                   for(var i=0;i<result.length;i++){
                	   var key=Object.keys(result[i]).length;
                	   if(key==1){
                		   count++;
                		   result.splice(i,result.length);
                	   }
                   }
                    var datas = new Array();
                    if(count!=""&&count==1&&result.length==0){
                		Joyin.alert("组装xml文件时数据库连接成功，但是未查询到相关数据！",2);
              			progress(0);
              		    dataTable.reload({data: datas});
                    } 
                    if(count!=""&&count<=result.length){
                    	 $.each(result, function(i, rep){
                 			  datas.push({type:rep.type,console:rep.console,errorMessage:rep.errorMessage});
                 			 });
                             dataTable.reload({data: datas});
                             progress(100);
                    }
                    if(result==""&&count==0){
                    	Joyin.alert("组装xml文件时数据库连接失败 请联系管理员配置相关数据库配置!",2);
                    	progress(0);
                    }
                   /* else{
                    	
                    }*/
                   
                        // $("#ValInertfBuildXml001_btn_save").addClass("layui-btn-disabled");
                         //$("#ValPublicDataImport001_btn_doInterBtn").prop("disabled",true).trigger("change");
                   
                });          
        };    
        /**进度条**/
    	function progress(percent){
    		var percentText = parseInt(percent) + '%';
            layui.element.progress('progressBar', percentText);
            $('#progressBar').find('.layui-progress-bar').text(percentText);
    	}       
    	
     	   var dataTable=$("#dataTable").joyintable({
   	    	data: [],
   		    searchOnInit: true,
   	    	cellMinWidth:100 ,
   	 	    checkbox: false,
   	 	    rowno:true,
   	 	    height:'755%',
	   	 	//loadonce:true,

   	 	    page:false,
   	        cols:[
   	            {field:'type',title: '数据类型',fixed:true,width:500}
   		        ,{field:'console',  title: '组装结果',fixed:true,width:200,
   		         render: function (data, type, row, meta) {
                	 if(data=='01'){
                         return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                     }else if(data=='02'){
                         return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                     }else if(data=='03'){
                         return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
                     }
                 }} ,
                 {field:'errorMessage',title: '异常信息',fixed:true,width:500}
   		        ]
   	    });	    	   
   	    var executeDateChange=function(){
	        var executeDate=$.trim($('#executeDate').val());
	        if(executeDate!=''||executeDate!=null){
	        	progress(0);
	        }
	    }
     	   
   	   $(function(){
			  
		   /**
		     * 执行
		     */
		   $('#ValInertfBuildXml001_btn_save').click(function() {
		        if(clickFlag){
		        	progress(50);
		        	runAsData();
		        }else{
		        	layui.layer.alert("任务正在执行，请勿重复点击！");
		        }
		    });
		   $('#executeDate').change(function(){
			   executeDateChange();
		      });
	   });
    });