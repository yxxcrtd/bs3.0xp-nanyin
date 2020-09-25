/**
 * 开放计划共通JS
 */
(function(){
      var table = $("#fundAccountTable").joyintable({
        data:funDto==null?[]:funDto,
        searchOnInit:true,
        height:'225px',
        cols:[
              {field:'acctType', title: 'FundAccountInfo.acctType',sortable:false, edit:true, editOption:{type:'select',formatoption:true,result: ACCT_TYPE}}
             ,{field:'acctNo', title: 'FundAccountInfo.acctNo', edit:true,sortable:false, editOption:{type:'select',showField:'id',id:'acctNo',formatoption:true,url: JoyinUtil.getRealPath('/component/portfolio/FundAccountInfo/getAcctNo')}}
             ,{field:'acctName', title: 'FundAccountInfo.acctName',sortable:false}
             ,{field:'bankDep', title: 'FundAccountInfo.bankDep',sortable:false}
             ,{field:'largePayno', title: 'FundAccountInfo.largePayno',sortable:false}
             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
             ,{field:'ccy', visible:false}
         ],
         addbtn:true,
         done:function(){
              this.bind('click', '.delt', function(data, index) {
                  var object = {selectedItem: [data], operateType:"delete"};
                  table.deleteRow(index);
              });
              $('body').on('change','#acctNo',function(event,data){
                  if(data.value[0] != ''){
                      var selectData  = $('#acctNo').parents('td').data('sd')[0];
                      var obj = {'acctNo':data.value[0].concat('_',selectData.object)};
                      Joyin.ajax(JoyinUtil.getRealPath( "/component/portfolio/FundAccountInfo/getCctInfo"),obj,function(result){
                          table.renderRow(data.rowIndex, {acctName:result.acctName,bankDep:result.bankDep,largePayno:result.largePayno, ccy: selectData.object});
                      });
                  }else{
                      table.renderRow(data.rowIndex, {acctName:'',bankDep:'',largePayno:'', ccy: ''});
                  }
              });
         }
    });
        
    // 隐藏
    var hide = function() {
        $('#FundAccountInfo_DIV').hide();
    };
    // 展示
    var show = function() {
        $('#FundAccountInfo_DIV').show();
    };
    
    $('#FundAccountInfo_btn_new').click(function(){
        Joyin.dialog(JoyinUtil.getLabel("MstCaptialAccount002"),
                basePath + "invest/basicdata/mstcaptialaccount/MstCaptialAccount002/init",
                "100%", "100%", {pageModel:PAGE_MODEL_NEW});
    });
    // 开放计划公开接口
    window.FundAccountInfo = {
        table:table,
        hide: hide,
        show: show
    };
}());