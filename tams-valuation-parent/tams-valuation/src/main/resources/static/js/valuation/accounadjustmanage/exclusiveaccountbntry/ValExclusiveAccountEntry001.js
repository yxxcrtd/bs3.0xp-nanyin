$(function () {
	
	  $.fn.joyintable.defaults = {
	    // 按钮设置
	    buttons: {
		  copy: {
			text: '<i class="iconfont icon-fuzhi"></i>',
			clas: 'copy'
		  },
		  addchild: {
				text: '<i class="iconfont icon-fuzhi1"></i>',
				clas: 'addchild'
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
  $('#ValExclusiveAccountEntry001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建专属会计分录"),
      basePath + "valuation/accounadjustmanage/exclusiveaccountbntry/ValExclusiveAccountEntry002/init",
      "1000px", "450px", {pageModel:PAGE_MODEL_NEW});
  });
  $('#ValExclusiveAccountEntry001_btn_addchild').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建专属会计分录配置"),
      basePath + "valuation/accounadjustmanage/exclusiveaccountbntry/ValExclusiveAccountEntry003/init",
      "500px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
});