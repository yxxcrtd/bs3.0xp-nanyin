$(function () {
  
  //两个DIV等高JS
  if($(".guzhileft").height() > $(".guzhiright").height()){
	$(".guzhiright").css("height",$(".guzhileft").height());
  }else{
	$(".guzhileft").css("height",$(".guzhiright").height());
  }
  //方案名称点击事件显示明细
  $(".guzhihd li").click(function(){
	$(this).addClass("JCurr").siblings().removeClass("JCurr");
  });
  //按钮组Hover效果
  $(".layui-btngroup").hover(function(){
	  $(this).find("b").addClass("reverse");
	  $(this).find("div").show();
	},function(){
	  $(this).find("b").removeClass("reverse");
	  $(this).find("div").hide();
	}
  ); 
});