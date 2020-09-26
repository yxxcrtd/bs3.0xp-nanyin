$(function () {
	var table=$("#dataTable").joyintable({
		data:[
		  {"cpmc":"某某的某某的产品1","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品2","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品3","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品4","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品5","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品6","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品7","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品8","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品9","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品10","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品11","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品12","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品13","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品14","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品15","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品16","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品17","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品18","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品19","zqzt":"某某账套名称1","qhzt":"某某账套名称A","bxrq":"2018/05/02"},
		  {"cpmc":"某某的某某的产品20","zqzt":"某某账套名称2","qhzt":"某某账套名称B","bxrq":"2018/05/02"},
		],
		searchOnInit: true,
		cellMinWidth:100 ,
		cols: [
		  {field:'cpmc', title: '产品名称', fixed:true, sort: true},
		  {field:'zqzt', title: '当前账套', sort: true},
		  {field:'qhzt', title: '切换账套', sort: true},
		  {field:'bxrq', title: '并行开始日',sort: true},
		  {title: '操作', width:90, fixed:'right', align:'center', type:'action', buttons: ['duibi','queren']}
		]
	  });
	
	  $.fn.joyintable.defaults = {
	    // 按钮设置
	    buttons: {
	    	duibi: {
			text: '<i class="iconfont icon-duibivs"></i>',
			clas: 'duibi'
		  },
		  queren: {
			text: '<i class="iconfont icon-submit"></i>',
			clas: 'queren'
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
  $('#ValCoverSwitch001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建账套切换"),
      basePath + "valuation/accounadjustmanage/coverwwitch/ValCoverSwitch002/init",
      "500px", "320px", {pageModel:PAGE_MODEL_NEW});
  });
});