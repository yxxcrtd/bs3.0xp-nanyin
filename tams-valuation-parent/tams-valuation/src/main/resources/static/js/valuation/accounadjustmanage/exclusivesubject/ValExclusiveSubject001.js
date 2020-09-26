$(function () {
	var table=$("#dataTable").joyintable({
		data:[
		  {"cpdm":"PD000000000000000001","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000002","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000003","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000004","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000005","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000006","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000007","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000008","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000009","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000010","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000011","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000012","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000013","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000014","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000015","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000016","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000017","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000018","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000019","cpmc":"某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"},
		  {"cpdm":"PD000000000000000020","cpmc":"某某的某某的某某的某某的产品A","sszt":"某某账套名称1","kmh":"150210","kmmc":"债券成本科目","sjkmh":"1502","yefx":"借方","kmlx":"负债类","szfx":"支出","beizhu":"某某相关的备注"}
		],
		searchOnInit: true,
		cellMinWidth:100 ,
		cols: [
		  {field:'cpdm', title: '产品代码', width:160, fixed:true, sort: true},
		  {field:'cpmc',title: '产品名称', width:240, sort: true},
		  {field:'sszt', title: '所属账套', width:130, sort: true},
		  {field:'kmh', title: '科目号', width:130, sort: true},
		  {field:'kmmc', title: '科目名称',  width:160,sort: true},
		  {field:'sjkmh', title: '上级科目号', width:130, sort: true},
		  {field:'yefx', title: '余额方向', width:100, sort: true},
		  {field:'kmlx', title: '科目类型', width:130, sort: true},
		  {field:'szfx', title: '收支方向', width:100, sort: true},
		  {field:'beizhu', title: '备注', width:300, sort: true},
		  {title: '操作', width:90, fixed:'right', align:'center', type:'action', buttons: ['edit','delt']}
		]
	  });
	
	  $.fn.joyintable.defaults = {
	    // 按钮设置
	    buttons: {
		  copy: {
			text: '<i class="iconfont icon-fuzhi"></i>',
			clas: 'copy'
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
  $('#ValExclusiveSubject001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建专属科目"),
      basePath + "valuation/accounadjustmanage/exclusivesubject/ValExclusiveSubject002/init",
      "1000px", "540px", {pageModel:PAGE_MODEL_NEW});
  });
});