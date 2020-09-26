package com.joyintech.tams.valuation.transformation.constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 数据移植模板转换常量设置
 *
 * @author 吴洁
 * @version 1.0 2018年12月21日
 * @see TransConstant
 * @since 1.0
 */
public class TransConstant {
	
	 public static final String 产品余额报表_实收资本 = "SSZB";
	 
	 public static final String 产品余额报表_应付利润 = "YFLR";
	 
	 public static final String 产品余额报表_应付托管费 = "YFTGF";
	 
	 public static final String 产品余额报表_应付销售服务费 = "YFXSFWF";
	 
	 public static final String 产品余额报表_应付固定管理费 = "YFGDGLF";
	 
	 public static final String 产品余额报表_应付超额管理费 = "YFCEGLF";
	 
	 public static final String 产品余额报表_损益平准金 = "SYPZJ";
	 
	 public static final String 产品余额报表_未分配利润 = "WFPLR";
	 
	 public static final String 资产余额报表_成本科目 = "CB";
	 
	 public static final String 资产余额报表_应计利息科目  = "YSLX";
	 
	 public static final String 资产余额报表_公允价值变动  = "GYJZBD";
	 
	 public static final String 资产余额报表_利息调整科目  = "LXTZ";
	 
	 public static final Map<String,String> 产品余额报表_科目;
	 
	 public static final Map<String,String> 产品余额报表_科目余额方向;
	 
	 public static final Map<String,String> 资产余额报表_科目余额方向;
	 
	 public static final Map<String,BigDecimal> 资产余额报表_科目余额金额;
	 
	 public static final String 解析状态_成功 = "Y";
	 
	 public static final String 解析状态_失败 = "N";
	 
	 public static final String 文件上传参数CHECK_未通过 = "2222";
	 
	 
	 public static final String 下载模板名称_产品= "_Product";
	 
	 public static final String 下载模板名称_资产= "_Asset";
	 
	 public static final Map<String,String> 币种Map;
	 
	 static{
		 币种Map = new HashMap<String,String>();
		 币种Map.put("人民币", "CNY");
		 币种Map.put("港元", "HKD");  
		 币种Map.put("美元", "USD");
		 币种Map.put("欧元", "EUR");
		 币种Map.put("日元", "JPY");
		 币种Map.put("澳门元", "MOP");
		 币种Map.put("瑞士法郎", "CHF");
		 币种Map.put("英镑", "GBP");
		 币种Map.put("俄罗斯卢布", "RUB");
		 币种Map.put("加拿大元", "CAD");
		 币种Map.put("丹麦克朗", "DKK");
		 币种Map.put("澳大利亚元", "AUD");
		 币种Map.put("瑞典克朗", "SEK");
		 币种Map.put("新加坡元", "SGD");
		 币种Map.put("新台币", "TWD");
		 
		 资产余额报表_科目余额方向 = new HashMap<String,String>();
		 资产余额报表_科目余额金额 = new HashMap<String,BigDecimal>();
		 
		 /**
		  * 1. 资产余额报表_成本科目
		  */
		 String keyStart = TransConstant.资产余额报表_成本科目;
		 
		 String key = keyStart+DictCodeListExt1.金融产品类型_债券.getValue();
		 //债券
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //债券质押式正回购
		 key = keyStart+ DictCodeListExt1.金融产品类型_债券质押式正回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_贷.getValue());
		 资产余额报表_科目余额金额.put(key,ArithmeticUtil.subtract(BigDecimal.ZERO, BigDecimal.ONE));
		 
		 //债券质押式逆回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //债券买断式正回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券买断式正回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_贷.getValue());
		 资产余额报表_科目余额金额.put(key,ArithmeticUtil.subtract(BigDecimal.ZERO, BigDecimal.ONE));
		 
		 //债券买断式逆回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //基金
		 key = keyStart+DictCodeListExt1.金融产品类型_基金.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //存款
		 key = keyStart+DictCodeListExt1.金融产品类型_存款.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //拆入
		 key = keyStart+DictCodeListExt1.金融产品类型_拆入.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_贷.getValue());
		 资产余额报表_科目余额金额.put(key,ArithmeticUtil.subtract(BigDecimal.ZERO, BigDecimal.ONE));
		 
		 //拆出
		 key = keyStart+DictCodeListExt1.金融产品类型_拆出.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //计划类资产
		 key = keyStart+DictCodeListExt1.金融产品类型_计划类资产.getValue();
		 资产余额报表_科目余额方向.put(key, 
				 DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //同业借款
		 key = keyStart+DictCodeListExt1.金融产品类型_同业借款.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //活期存款
		 key = keyStart+DictCodeListExt1.资金账户类型_产品托管户.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 
		 /**
		  * 2.资产余额报表_公允价值变动
		  */
		 keyStart = TransConstant.资产余额报表_公允价值变动;
		 key = keyStart+DictCodeListExt1.金融产品类型_债券.getValue();
		 //债券
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //基金
		 key = keyStart+DictCodeListExt1.金融产品类型_基金.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //计划类资产
		 key = keyStart+DictCodeListExt1.金融产品类型_计划类资产.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 /**
		  * 3.资产余额报表_应计利息科目
		  */
		 keyStart = TransConstant.资产余额报表_应计利息科目;
		 key = keyStart+DictCodeListExt1.金融产品类型_债券.getValue();
		 
		 //债券
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //债券质押式正回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券质押式正回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_贷.getValue());
		 资产余额报表_科目余额金额.put(key,ArithmeticUtil.subtract(BigDecimal.ZERO, BigDecimal.ONE));
		 
		 //债券质押式逆回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //债券买断式正回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券买断式正回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_贷.getValue());
		 资产余额报表_科目余额金额.put(key,ArithmeticUtil.subtract(BigDecimal.ZERO, BigDecimal.ONE));
		 
		 //债券买断式逆回购
		 key = keyStart+DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //基金
		 key = keyStart+DictCodeListExt1.金融产品类型_基金.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //存款
		 key = keyStart+DictCodeListExt1.金融产品类型_存款.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //拆入
		 key = keyStart+DictCodeListExt1.金融产品类型_拆入.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_贷.getValue());
		 资产余额报表_科目余额金额.put(key,ArithmeticUtil.subtract(BigDecimal.ZERO, BigDecimal.ONE));
		 
		 //拆出
		 key = keyStart+DictCodeListExt1.金融产品类型_拆出.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //计划类资产
		 key = keyStart+DictCodeListExt1.金融产品类型_计划类资产.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //同业借款
		 key = keyStart+DictCodeListExt1.金融产品类型_同业借款.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		//活期存款
		 key = keyStart+DictCodeListExt1.资金账户类型_产品托管户.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 
		 /**
		  * 4.利息调整科目
		  */
		 //债券
		 keyStart = TransConstant.资产余额报表_利息调整科目;
		 key = keyStart+DictCodeListExt1.金融产品类型_债券.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 //计划类资产
		 keyStart = TransConstant.资产余额报表_利息调整科目;
		 key = keyStart+DictCodeListExt1.金融产品类型_计划类资产.getValue();
		 资产余额报表_科目余额方向.put(key,DictCodeList.借贷方向_借.getValue());
		 资产余额报表_科目余额金额.put(key,BigDecimal.ONE);
		 
		 
		 
		 产品余额报表_科目 = new HashMap<String,String>();
		 产品余额报表_科目.put(TransConstant.产品余额报表_实收资本, "40010101");
		 产品余额报表_科目.put(TransConstant.产品余额报表_应付利润, "22320101");
		 产品余额报表_科目.put(TransConstant.产品余额报表_应付托管费, "22070101");
		 产品余额报表_科目.put(TransConstant.产品余额报表_应付销售服务费, "22080101");
		 产品余额报表_科目.put(TransConstant.产品余额报表_应付固定管理费, "22060101");
		 产品余额报表_科目.put(TransConstant.产品余额报表_应付超额管理费, "22060201");
		 产品余额报表_科目.put(TransConstant.产品余额报表_损益平准金, "40110101");
		 产品余额报表_科目.put(TransConstant.产品余额报表_未分配利润, "41040301");
		 
		 
		 产品余额报表_科目余额方向 = new HashMap<String,String>();
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_实收资本, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_应付利润, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_应付托管费, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_应付销售服务费, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_应付固定管理费, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_应付超额管理费, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_损益平准金, DictCodeList.借贷方向_贷.getValue());
		 产品余额报表_科目余额方向.put(TransConstant.产品余额报表_未分配利润, DictCodeList.借贷方向_贷.getValue());
	 }
	 
}
