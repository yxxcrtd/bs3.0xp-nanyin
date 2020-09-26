$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetIds : $("#booksetIds").val(),
            schemeType: $("#schemeType").val(),
            schId: $("#schId").val()
        }
    };
    var tableCols = [
                     {field:'booksetId', title: '账套代码'},
                     {field:'booksetName', title: '账套简称'},
                     {field:'acctModeFmt', sortfield:'acctMode', title: '收益类型'},
                     {field:'orgSchName', title: '原对账方案'},
                     {field:'orgSchId', title: '原对账方案', visible:false},
                     {field:'nowSchId', title: '对账方案代码', visible:false},
                     {field:'booksetSchemeId', sortfield: 'booksetSchemeId', title: '账套方案代码', visible: false}
                     ];
      
      if(workflowId==null || workflowId.length==0){
          tableCols.push({field:'nowSchName', width:180, title: '对账方案', edit:true, 
              editOption:{type:'select',result: SCHEMENAME}});
      }else{
          checkfalg = false;
          tableCols.push({field:'nowSchName', width:180, title: '对账方案'});
      }

    /**
     * 定义数据表格——结构映射
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url:  "findPage",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        editCell: function(value, data, field, cell, column) {
            if (field != 'nowSchName') {
                return;
            }
            //为新会计分类赋值
            data.nowSchId=getVal(null,value,SCHEMENAME);
            nowSchId=data.nowSchId;
        },
        searchOnInit: true,
        checkbox: false,
        loadonce:true,
        page: false,
        height:300,
        cols: tableCols
        
    });
    
    $('#schId').change(function() {
        var data = table.getAllRows();
        for(var i=0; i<data.length; i++){
            // 获取下拉框显示的值
            data[i].nowSchName = $('#select2-schId-container').attr("title");
            // 获取下拉框value值，获取方案代码
            data[i].nowSchId = $('#schId').val();
        }
        table.reload({data:data});
    });
    
    //根据json中的value获取key
    function getVal(key,value,json){
        var results = $.parseJSON(json);
        //为新会计分类Code赋值
        for (var p in results) {
            if(key&&results[p].id==key){
                return results[p].text;
            }
            if(value&&results[p].text==value){
                return results[p].id;
            }
        }
    }
    /**
     * 绑定按钮事件
     */
    $('#ValProdSchemeConfig002_btn_submit').click(function() {
        var datas = JSON.stringify(table.getAllRows());
        
        Joyin.ajax('save', {
            datas:datas,
            schId : $("#schId").val(),
            schemeType : $("#schemeType").val()
         }, function(result) {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });
});
