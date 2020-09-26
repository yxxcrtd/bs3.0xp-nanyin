$(function () {
  //按钮
  $.fn.joyintable.defaults = {
    // 按钮设置
    buttons: {
	  copy: {
		text: '<i class="iconfont icon-fuzhi"></i>',
		clas: 'copy'
	  },
	  edit : {
        text : '&#xe642;',
       clas : 'edit'
    },
    delt : {
        text : '&#xe640;',
        clas : 'delt'
    }
    }
  };
	  
  var table=$("#dataTable").joyintable({
	data:[
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日+1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	  {"zjpz":"债券","zjlx":"商业银行债","jysc":"银行间","tzmd":"以摊余成本计量(AC)","gzff":"摊余成本法","gzsj":"优先：平均价/备用：最低价","gzrq":"估值日-1","hqws":"5","ksr":"2015/01/01","jsr":"2025/12/31",},
	],
	searchOnInit: true,
	cols:[
          {field:'zjpz', title:'证券品种', width:90, sort:true},
          {field:'zjlx', title:'证券细分类', minWidth:120, sort:true},
		  {field:'jysc', title:'交易市场', minWidth:120, sort:true},
		  {field:'tzmd', title:'投资目的', minWidth:150, sort:true},
		  {field:'gzff', title:'估值方法', minWidth:120, sort:true},
		  {field:'gzsj', title:'估值市价来源', minWidth:200, sort:true},
		  {field:'gzrq', title:'行情日期', minWidth:90, sort:true},
		  {field:'hqws', title:'行情位数', width:90, sort:true},
		  {field:'ksr', title:'开始日', minWidth:95, sort:true},
		  {field:'jsr', title:'结束日', minWidth:95, sort:true},
		  {fixed:'right', title:'操作', width:110, align:'center', type:'action', buttons: ['edit','copy','delt']}
		]
  });
  
  //新建方案
  $('#ValMethodScheme001_btn_addfa').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建估值方法方案"),
      basePath + "valuation/valuationmanage/valmethodscheme/ValMethodScheme002/init",
      "500px", "320px", {pageModel:PAGE_MODEL_NEW});
  });
 //新建方案
  $('#ValMethodScheme001_btn_addmx').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建估值方法方案明细"),
      basePath + "valuation/valuationmanage/valmethodscheme/ValMethodSchemeDetail001/init",
      "760px", "560px", {pageModel:PAGE_MODEL_NEW});
  });

});