;(function($){
    /**
     * 计划类资产中债报送 对应资产关系
     *
     * 更新 下拉框通用方法
     *
     * @param dataResult 原数据，不传时，默认取[data-result]
     * @param item 筛选，这里取的数据对象中的id，如：['1','2']，不传时，全部展示
     * @param chosen 选中某一项 string
     * @param disabled 是否禁用 boolean
     * @param callback 回调
     * @return $this
     *
     * @author yjm
     */
    $.fn.repSelectArr = function repSelectArr(pageModel){
        if (pageModel != PAGE_MODEL_REFERENCE){
            var item = [];
            var flag = $('#category').val() + $('#type').val();
            switch (flag) {
                case 'N01':
                    item = ['1703','2801'];
                    break;
                case 'N02':
                    item = ['1701','2802'];
                    break;
                case 'N03':
                    item = ['1704','2803'];
                    break;
                case 'N04':
                    item = ['1702','2804'];
                    break;
                case 'N05':
                    item = ['1706','2805'];
                    break;
                case 'N06':
                    item = ['1705','2806','2899'];
                    break;
                case 'N0101':
                    item = ['1703'];
                    break;
                case 'N0201':
                    item = ['1701'];
                    break;
                case 'N0301':
                    item = ['1704'];
                    break;
                case 'N0401':
                    item = ['1702'];
                case 'N0501':
                    item = ['1706'];
                    break;
                case 'N0601':
                    item = ['1705'];
                    break;
                case 'N0102': case  'N0103': case 'N0104':
                 /*   更改一二级联动时，资产负债类别枚举值  zhuyongkang  2019/8/12 start*/
                    item = ['2801','2802','2803','2804','2805','2806','2899'];
                    break;
                case 'N0202' :case 'N0203' :case 'N0204':
                    item = ['2802','2801','2803','2804','2805','2806','2899'];
                    break;
                case 'N0302' :case 'N0303' :case 'N0304':
                    item = ['2803','2801','2802','2804','2805','2806','2899'];
                    break;
                case 'N0402' :case 'N0403' :case 'N0404':
                    item = ['2804','2801','2802','2803','2805','2806','2899'];
                    break;
                case 'N0502' :case 'N0503' :case 'N0504':
                    item = ['2805','2801','2802','2803','2804','2806','2899'];
                    break;
                case 'N0602' :case 'N0603' :case 'N0604':
                    item = ['2806','2899','2801','2802','2803','2804','2805'];
                /*    zhuyongkang  2019/8/12 end*/
                    break;
                default:
                    item = [];
                    break;
            }

            $('#bussRepAssetMngProdInfo_repAssetType').updateChosenArr({
                item: item,
                disabled: false
            });
            $('#bussRepOutInvestAgrInfo_repAssetType').updateChosenArr({
                item: item,
                disabled: false
            });
        }
    }
})(jQuery)