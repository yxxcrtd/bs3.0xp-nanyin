(function () {

    /**
     * 查询参数
     */
    var searchform = function(){
        if(RSK_INPUT_TYPE_BATCH == $("#operateStyle").val()){
            return  {
                listId:$("#listId").val(),
                rskAccessListLogDtoList: JSON.stringify(parent.AssetNameManagement004.getAssetNameNewAllRows())
            }
        }else{
            return  {
                listId:$("#listId").val(),
                rskAccessListLogDtoList: JSON.stringify(parent.AssetNameManagement003.getAssetNameNewAllRows())
            }
        }

    }
    /**
     * 名单明细变更列表（Detail）
     */
    var assetNamePriviewDetailTbl = $('#dataTable').joyintable({
        ajax : {
            url : 'getAdjPriview',
                type : 'POST',
                data : function() {
                return searchform();
            }
        },
        searchOnInit: true,
        loadonce:true,
        addbtn:true,
        checkbox:false,
        cols: [
            {field:'oldlistDetailName',  title: 'AssetNameManagement003.listDetailName',visible:false} //金融产品代码
            // 资产代码
            ,{
                field: 'listDetailName',
                width: 300,
                title: 'AssetNameManagement003.listDetailName',
                sortable: false
            }
            // 资产简称
            , {
                field: 'listDetailId',
                width: 300,
                title: 'AssetNameManagement003.listDetailId',
                sortable: false
            }
            , {
                field: 'operatePs',
                width: 300,
                title: 'AssetNameManagement003.operatePs',
                sortable: false,
                render:function (data, type, row, meta) {
                    if (data == RSK_OPERATE_PS_01||data==RSK_OPERATE_PS_NAME_01)
                    {return RSK_OPERATE_PS_NAME_01;}
                    else if (data == RSK_OPERATE_PS_02||data==RSK_OPERATE_PS_NAME_02){
                        return RSK_OPERATE_PS_NAME_02;
                    }else if (data == RSK_OPERATE_PS_03||data==RSK_OPERATE_PS_NAME_03){
                        return RSK_OPERATE_PS_NAME_03;
                    }else{ return RSK_OPERATE_PS_NAME_99};
                }
            }
        ],
    });

    /**
     * 画面加载完成
     */
    $(function () {
    });
}());