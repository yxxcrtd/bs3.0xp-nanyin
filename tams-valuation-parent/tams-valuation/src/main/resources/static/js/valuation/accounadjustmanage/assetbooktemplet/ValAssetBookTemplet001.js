$(function () {
	var table=$("#dataTable").joyintable({
		  data:[
		  	  {"ztmc":"理财账","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账1","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账1","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账2","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账2","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账3","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账3","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账4","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账4","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账5","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账5","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账6","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账6","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账7","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账7","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账8","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账8","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"},
			  {"ztmc":"理财账9","ztlx":"理财账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='01' checked='' lay-text='是|否'>"},
			  {"ztmc":"银行账9","ztlx":"银行账套","syfw":"某某跟某某的范围","mrzt":"<input type='checkbox' name='moren' lay-skin='switch' value='02' lay-text='是|否'>"}
		  	],
		searchOnInit: true,
		cellMinWidth:100 ,
		cols: [
		 	  {field:'ztmc', title: '账套名称', sort: true},
		 	  {field:'ztlx', title: '账套类型',sort: true},
			  {field:'syfw', title: '适用产品范围',sort: true},  
		 	  {field:'mrzt', title: '默认账套', sort: true},
		 	  {field:'caozuo', title: '操作', width:100, align:'center', fixed:'right', type:'action', buttons: ['edit','delt']}
		 	]
	  });
  //新建
  $('#ValAssetBookTemplet001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建账套模板"),
      basePath + "valuation/accounadjustmanage/assetbooktemplet/ValAssetBookTemplet002/init",
      "500px", "240px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValAssetBookTemplet001_btn_copy').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("复制账套模版"),
      basePath + "valuation/accounadjustmanage/assetbooktemplet/ValAssetBookTemplet003/init",
      "500px", "300px", {pageModel:PAGE_MODEL_NEW});
  });
  
  
});