$(function() {
    var getPageParams = function() {
        return {
            //产品登记编码
            prdRegistCode:$('#prdRegistCode').val()==null?null:$('#prdRegistCode').val().join(","),
            // 查询日开始
            holdDateStart : $('#holdDate').startDate(),
            // 查询日结束
            holdDateEnd : $('#holdDate').endDate()
        };
    };
    var table = $('#dataTable').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
                  { title: "产品代码", field: 'prodId',width:150},
                  { title: "产品登记编码", field: 'prdRegistCode',width:160},
                  { title: "产品名称", field: 'prodName',width:200 },
                  { title: "币种", field: 'ccy',width:110},
                  { title: "持有日期", field: 'holdDate', type:'date', width:100},
                  { title: "产品份额",field: 'qty', type:'number', digit:5, width:175},
                  { title: "产品总金额",field: 'sumQty', type:'number', digit:2, width:175},
                  { title: "客户总数",field: 'sumCus', type:'number', digit:0, width:175},
                  { title: "上一日产品份额", field: 'lastQty',type:'number', digit:5, width: 175},
                  { title: "产品份额变动", field: 'changeQty',type:'number', digit:5, width: 175}
              ]
    });
    
    $(function(){
        $('#holdDate').val(nowSysDate + ' - ' + nowSysDate);

        // 查询按钮点击事件
        $('#ProdShareCount001_btn_search').click(function() {
            table.load();
        });
        
        // 导出按钮点击事件
        $('#ProdShareCount001_btn_export').click(function() {
            var selectedItem = table.getAllCheckedRows();
            var paramObj={};
            if(selectedItem.length==0){
                paramObj= getPageParams();
            }
            else{
                paramObj.keyList = JSON.stringify(selectedItem);
            }
            Joyin.exportExcel('export', paramObj);
        });
    });
});