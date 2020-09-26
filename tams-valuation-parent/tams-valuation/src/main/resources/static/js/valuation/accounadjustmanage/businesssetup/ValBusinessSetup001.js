$(function () {
	var table=$("#dataTable").joyintable({
	    data:[
		  {"cjdm":"CJ00000001","cjmc":"某某的业务场景名称1","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000002","cjmc":"某某的业务场景名称2","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000003","cjmc":"某某的业务场景名称3","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000004","cjmc":"某某的业务场景名称4","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000005","cjmc":"某某的业务场景名称5","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000006","cjmc":"某某的业务场景名称6","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000007","cjmc":"某某的业务场景名称7","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000008","cjmc":"某某的业务场景名称8","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000009","cjmc":"某某的业务场景名称9","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000010","cjmc":"某某的业务场景名称10","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000011","cjmc":"某某的业务场景名称1","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000012","cjmc":"某某的业务场景名称2","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000013","cjmc":"某某的业务场景名称3","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000014","cjmc":"某某的业务场景名称4","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000015","cjmc":"某某的业务场景名称5","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000016","cjmc":"某某的业务场景名称6","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000017","cjmc":"某某的业务场景名称7","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000018","cjmc":"某某的业务场景名称8","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000019","cjmc":"某某的业务场景名称9","beizhu":"一些的适用范围备注"},
		  {"cjdm":"CJ00000020","cjmc":"某某的业务场景名称10","beizhu":"一些的适用范围备注"}
		],
		searchOnInit: true,
		cellMinWidth:100 ,
		cols: [
		  {field:'cjdm', title: '业务场景代码', sort: true},
		  {field:'cjmc', title: '业务场景名称',sort: true},
		  {field:'beizhu', title: '备注',sort: true}, 
		  {fixed:'right',title: '操作', width:100, align:'center', type:'action', buttons: ['deploy','edit', 'delt']}
		  
		]
	  });
  $.fn.joyintable.defaults = {
    // 按钮设置
    buttons: {
	  deploy: {
		text: '&#xe614;',
		clas: 'deploy'
	  },
	  edit: {
		text: '&#xe642;',
		clas: 'edit'
	  },
	  delt: {
		text: '&#xe640;',
		clas: 'delt'
	  }
    }
  };
  //新建
  $('#ValBusinessSetup001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建业务场景参数"),
      basePath + "valuation/accounadjustmanage/businesssetup/ValBusinessSetup002/init",
      "500px", "320px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValBusinessSetup001_btn_deploy').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建业务参数设置"),
      basePath + "valuation/accounadjustmanage/businesssetup/ValBusinessSetup003/init",
      "500px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  
  
});