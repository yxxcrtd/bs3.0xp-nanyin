$(function(){

    var getPageParams = function() {
        var obj=$.serializeToObject('#informationForm');
        return obj;
    }

    var table= $("#autoDispenseGightTable").joyintable({
        ajax: {
            url: 'getTraderAuthList',
            type: 'POST',
            data: function() {
              return getPageParams();
            }
          },
			searchOnInit:true,
			height:300,
            rowno:true,
           loadonce:true,
		   page:false,
           checkbox:false,
			cols:[
				{field:'userName',  width:150,title: '用户名', sortable:false,type:'text'}
				,{field:'realName',  width:150,title: '真实姓名', sortable:false,type:'text'}
				,{field:'finprodType', width:600, title: '投资品',sortable:false,edit:true, editOption:{ type:'select',multiple:'true',result: PINAN_ASSET_TYPE}}

			]
		});

    // 绑定按钮事件 保存是判断品种权限未选，将默认所有交易员都有此权限，是否继续？authConfigCheck
    $('#TrdCommandDispatcher004_btn_save').click(function(){
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001","保存"),function() {
            var selectRows=table.getAllRows();

            Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/investtrade/commanddispatcher/TrdCommandDispatcher004/authConfigCheck'),selectRows, function(data) {
                if(data.data!='ok')
                {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",data.data),function() {

                        Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/investtrade/commanddispatcher/TrdCommandDispatcher004/saveTraderAuthList'),selectRows, function(data) {
                            Joyin.alert("保存成功",null,function(){
                                var index = window.parent.layer.getFrameIndex(window.name);
                                window.parent.layer.close(index);
                            });
                        });
                    });

                }
                else
                {
                    Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/investtrade/commanddispatcher/TrdCommandDispatcher004/saveTraderAuthList'),selectRows, function(data) {
                        Joyin.alert("保存成功",null,function(){
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        });
                    });
                }

            });

        });


    });

}());