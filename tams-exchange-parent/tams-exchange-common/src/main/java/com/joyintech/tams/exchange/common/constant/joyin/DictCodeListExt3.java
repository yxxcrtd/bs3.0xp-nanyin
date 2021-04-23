package com.joyintech.tams.exchange.common.constant.joyin;

import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典明细枚举
 *
 * @author chenzhuo
 * @version 1.0 2016年11月12日
 * @see DictCodeListExt3
 * @since 1.0
 */
public enum DictCodeListExt3 implements BaseDictCodeList {
    //add by lishuping 20191106 start
    质押券状态_正常(DictCode.质押券状态, "01","正常"),
    质押券状态_触发预警(DictCode.质押券状态, "02","触发预警"),
    质押券状态_触发平仓(DictCode.质押券状态, "03","触发平仓"),
    //add by lishuping 20191106 end
    共通业务类型_产品端轧差划款(DictCode.共通业务类型,"PTL16","产品端轧差划款"),
    //添加价格类别 add by tanxuan
    增值税品种_产品增值税(DictCode.增值税品种,"01","产品增值税"),
    增值税品种_通道增值税(DictCode.增值税品种,"02","通道增值税"),
    平安业务类型_融入债券付息还本_付(DictCode.平安业务类型,"137","融入债券付息还本（付）"),
    平安业务类型_融出债券付息还本_收(DictCode.平安业务类型,"138","融出债券收息还本（收）"),

    需求发起端_产品端(DictCode.需求发起端,"01","产品端"),
    需求发起端_投资端(DictCode.需求发起端,"02","投资端"),
    需求发起端_客户端(DictCode.需求发起端,"03","客户端"),
    可行性状态_可行(DictCode.可行性状态,"01","可行"),
    可行性状态_不可行(DictCode.可行性状态,"02","不可行"),
    可行性状态_待定(DictCode.可行性状态,"03","待定"),
    可行需求分类_原有系列(DictCode.可行需求分类,"01","原有系列"),
    可行需求分类_新研发系列(DictCode.可行需求分类,"02","新研发系列"),

    /**
     * 指令新建业务类型 yzg 20191106
     */
    共通业务类型_指令新建(DictCode.共通业务类型,"PTL15","指令新建"),
    共通业务类型_产品申报登记要素管理(DictCode.共通业务类型,"FINPROD28","产品申报登记要素管理"),
    共通业务类型_资产配置计划(DictCode.共通业务类型,"PRDPLAN03","资产配置计划"),

    匹配状态_已匹配(DictCode.匹配状态,"01","已匹配"),
    匹配状态_未匹配(DictCode.匹配状态,"02","未匹配"),
    匹配状态_部分匹配(DictCode.匹配状态,"03","部分匹配"),
    //add by gaopeng 20191108 start 增加债转股业务类型以及交易方向
    平安业务类型_债转股(DictCode.平安业务类型,"139","债转股"),
    交易方向_债转股(DictCode.交易方向,"T49","债转股"),
    //add by gaopeng 20191108 end 增加债转股业务类型以及交易方向
    交易方向_投资端_提前终止(DictCode.交易方向,"T54","提前终止"),
    交易方向_投资端_逾期返售(DictCode.交易方向,"T55","逾期返售"),

    /**利率上下限区分 **/

    利率上下限区分_利率上限(DictCode.利率上下限区分,"01","利率上限"),
    利率上下限区分_利率下限(DictCode.利率上下限区分,"02","利率下限"),

    平安业务类型_货币基金认购申请(DictCode.平安业务类型,"127","货币基金认购申请"),
    平安业务类型_非货币基金认购申请(DictCode.平安业务类型,"128","非货币基金认购申请"),
    平安业务类型_货币基金认购确认(DictCode.平安业务类型,"129","货币基金认购确认"),
    平安业务类型_非货币基金认购确认(DictCode.平安业务类型,"130","非货币基金认购确认"),
    平安业务类型_货币基金买入(DictCode.平安业务类型,"133","货币基金买入"),
    平安业务类型_非货币基金买入(DictCode.平安业务类型,"134","非货币基金买入"),
    平安业务类型_货币基金卖出(DictCode.平安业务类型,"135","货币基金卖出"),
    平安业务类型_非货币基金卖出(DictCode.平安业务类型,"136","非货币基金卖出"),
    //add by nixiukuan 20191113 start 质押式回购交易、债券借贷交易 -》质押券置换
    共通业务类型_质押券置换(DictCode.共通业务类型,"126","质押券置换"),
    质押券置换_置换方向_换入(DictCode.置换方向,"01","换入"),
    质押券置换_置换方向_换出(DictCode.置换方向,"02","换出"),
    质押券置换_置换结果_置换前(DictCode.置换结果,"01","置换前"),
    质押券置换_置换结果_置换后(DictCode.置换结果,"02","置换后"),
    交易方向_费用计提(DictCode.交易方向,"T57","费用计提"),
    //add by nixiukuan 20191113 end 质押式回购交易、债券借贷交易 -》质押券置换
    共通业务类型_可转债转股交易(DictCode.共通业务类型,"TRD63","可转债转股交易"),

    共通业务类型_立项指令(DictCode.共通业务类型,"TRD75","立项指令"),

    // add by leipeng 20191123
    资产_负债类别_公募和私募基金(DictCode.资产_负债类别,"L01","公募基金/私募基金"),


    发行机构类型_按规模划分_中小微企业(DictCode.发行机构类型_按规模划分,"01","中小微企业"),

    //add by wangxiaoyong 2019-11-29 end
    共通业务类型_交易费用管理(DictCode.共通业务类型,"TRD68","交易费用管理"),

    平安业务类型_货币型产品轧差清算(DictCode.平安业务类型,"140","货币型产品轧差清算"),

    //行内资产一级分类
    资产一级分类_同业融资类(DictCode.资产一级分类,"01","同业融资类"),
    资产一级分类_债券类信用债(DictCode.资产一级分类,"02","债券类-信用债"),
    资产一级分类_债券类利率债(DictCode.资产一级分类,"03","债券类-利率债"),
    资产一级分类_其他债权类(DictCode.资产一级分类,"04","其他债权类"),
    交易方向_退款到账(DictCode.交易方向,"T58","退款到账"),
    //add daiguofu 20191225
    共通业务类型_产品规划信息维护(DictCode.共通业务类型,"PRDPLAN08","产品规划信息维护"),
    策略分类_投资策略(DictCode.策略分类,"01","投资策略"),
    策略分类_规划策略(DictCode.策略分类,"02","规划策略"),
    /**计提份额占比收息 add  by yanya on 20191219 **/
    计提份额占比收息_按占比收息(DictCode.计提份额占比收息,"01","按占比收息"),
    计提份额占比收息_不按占比收息(DictCode.计提份额占比收息,"02","不按占比收息"),
    存款类资产类型_活期存放(DictCode.存款类资产类型,"D03","活期存放"),
    现金流二级类型_罚息(DictCode.现金流二级类型,"C19","罚息"),
    共通业务类型_逾期计息要素维护(DictCode.共通业务类型,"FINASSET20","逾期计息要素维护"),
    共通业务类型_场外期权类资产信息(DictCode.共通业务类型,"ETR22","场外期权类资产信息"),
    应收款类型_逾期未收本金(DictCode.应收款类型,"01","逾期未收本金"),
    应收款类型_逾期未收利息(DictCode.应收款类型,"02","逾期未收利息"),
    应收款类型_还本未收利息(DictCode.应收款类型,"03","还本未收利息"),
    应收款类型_卖出未收利息(DictCode.应收款类型,"04","卖出未收利息"),
    应收款类型_买入未付利息(DictCode.应收款类型,"05","买入未付利息"),
    共通业务类型_已到期资产转让(DictCode.共通业务类型,"TRD67","已到期资产转让"),
    共通业务类型_已到期资产存续期(DictCode.共通业务类型,"TRD68","已到期资产存续期"),
    平安业务类型_已到期资产转让(DictCode.平安业务类型,"141","已到期资产转让"),
    平安业务类型_已到期资产存续期(DictCode.平安业务类型,"144","已到期资产存续期"),
    罚息计提基数_逾期本金(DictCode.罚息计提基数,"01","逾期本金"),
    罚息计提基数_逾期利息(DictCode.罚息计提基数,"02","逾期利息"),
    罚息计提基数_逾期本息和(DictCode.罚息计提基数,"03","逾期本息和"),
    现金流类型_罚息收息(DictCode.现金流类型,"06","罚息收息"),
    计息利率方式_买断利率计提(DictCode.计息利率方式,"01","买断利率计提"),
    计息利率方式_净投资收益率计提(DictCode.计息利率方式,"02","净投资收益率计提"),
    交易日历_中国法定节假日(DictCode.交易日历,"01","中国法定节假日"),
    展示维度_日(DictCode.展示维度,"01","日"),
    展示维度_周(DictCode.展示维度,"02","周"),
    展示维度_月(DictCode.展示维度,"03","月"),
    展示维度_短期(DictCode.展示维度,"04","短期"),

    人行产品运作模式_开放式净值型(DictCode.人行产品运作模式,"2","开放式净值型"),
    人行产品运作模式_开放式非净值型(DictCode.人行产品运作模式,"3","开放式非净值型"),
    人行产品运作模式_封闭式净值型(DictCode.人行产品运作模式,"4","封闭式净值型"),
    人行产品运作模式_封闭式非净值型(DictCode.人行产品运作模式,"5","封闭式非净值型"),

    人行客户类型_单位(DictCode.人行客户类型,"1","单位"),
    人行客户类型_个人(DictCode.人行客户类型,"2","个人"),
    人行客户类型_单位及个人(DictCode.人行客户类型,"3","单位及个人"),
    人行客户类型_住户(DictCode.人行客户类型,"4","住户"),
    人行客户类型_广义政府(DictCode.人行客户类型,"5","广义政府"),
    人行客户类型_非金融企业(DictCode.人行客户类型,"6","非金融企业"),
    人行客户类型_金融机构_实体(DictCode.人行客户类型,"7","金融机构（实体）"),
    人行客户类型_特定目的载体(DictCode.人行客户类型,"8","特定目的载体"),
    人行客户类型_境外(DictCode.人行客户类型,"9","境外"),
    批处理系统代码_估值(DictCode.批处理系统代码,"VAL","估值"),
    批处理系统代码_投管(DictCode.批处理系统代码,"INV","投管"),
    批处理系统代码_信批(DictCode.批处理系统代码,"DIS","信批"),
    批处理系统代码_中债(DictCode.批处理系统代码,"SVR","中债"),
    批处理任务组类型_T日(DictCode.批处理任务组类型,"01","T日"),
    批处理任务组类型_日切(DictCode.批处理任务组类型,"99","日切"),
    批处理任务组类型_T1日(DictCode.批处理任务组类型,"02","T+1日"),
    结息方式_结转为本金(DictCode.结息方式,"01","结转为本金"),
    结息方式_现金结息(DictCode.结息方式,"02","现金结息"),
    /**发行规划试算 	20191223 **/
    共通业务类型_发行规划试算(DictCode.共通业务类型,"PRDPLAN07","发行规划试算"),
    //查询口径 yzg 20191224
    查询口径_全部查询(DictCode.查询口径,"01","全部查询"),
    查询扣经_按产品系列(DictCode.查询口径,"02","按产品系列"),
    //从DictCodeListExt1移动都DictCodeListExt3中，新增 03：无需处理 add by chendijun 2019/12/3
    贴补状态_已贴补(DictCode.贴补状态,"01","已贴补"),
    贴补状态_未贴补(DictCode.贴补状态,"02","未贴补"),
    贴补状态_无需处理(DictCode.贴补状态,"03","无需处理"),
    //修正场景 yzg 20191226
    修正场景_高悲观(DictCode.修正场景,"01","高悲观"),
    修正场景_中悲观(DictCode.修正场景,"02","中悲观"),
    修正场景_轻悲观(DictCode.修正场景,"03","轻悲观"),
    修正场景_中性(DictCode.修正场景,"04","中性"),
    修正场景_轻乐观(DictCode.修正场景,"05","轻乐观"),
    修正场景_中乐观(DictCode.修正场景,"06","中乐观"),
    修正场景_高乐观(DictCode.修正场景,"07","高乐观"),
    共通业务类型_战术配置优化(DictCode.共通业务类型,"PRDPLAN02","战术配置优化"),
    共通业务类型_资产分类管理(DictCode.共通业务类型,"PRDPLAN01","资产分类管理"),
    /**PB要素类型 add by caoxue 2019-12-24 start**/
    PB要素类型_产品(DictCode.PB要素类型,"0","产品"),
    PB要素类型_子组合(DictCode.PB要素类型,"1","子组合"),
    /**PB要素类型 add by caoxue 2019-12-24 end**/
    投资策略类型_单一资产策略(DictCode.投资策略类型, "01", "单一资产策略"),
    投资策略类型_混合资产策略(DictCode.投资策略类型, "02", "混合资产策略"),

    /**增加 交易方向_提前行权 和 交易方向_到期行权**/
    交易方向_提前行权(DictCode.交易方向,"T59","提前行权"),
    交易方向_到期行权(DictCode.交易方向,"T60","到期行权"),

    下钻层级_资产一级分类(DictCode.下钻层级,"01","资产一级分类"),
    下钻层级_资产二级分类(DictCode.下钻层级,"02","资产二级分类"),
    下钻层级_资产三级分类(DictCode.下钻层级,"03","资产三级分类"),
    下钻层级_资产四级分类(DictCode.下钻层级,"04","资产四级分类"),
    下钻层级_资产五级分类(DictCode.下钻层级,"05","资产五级分类"),
    展示方式_单组合汇总(DictCode.展示方式,"01","单组合汇总"),
    展示方式_多组合汇总(DictCode.展示方式,"02","多组合汇总"),
    指标类型代码_综合指标(DictCode.指标类型代码,"01","综合指标"),
    指标类型代码_债券指标(DictCode.指标类型代码,"02","债券指标"),
    指标类型代码_股票指标(DictCode.指标类型代码,"03","股票指标"),

    含权信息_永续(DictCode.含权信息,"01","永续"),
    含权信息_赎回权(DictCode.含权信息,"02","赎回权"),
    含权信息_回售权(DictCode.含权信息,"03","回售权"),
    含权信息_赎回权_回售权(DictCode.含权信息,"04","赎回权、回售权"),
    共通业务类型_场外期权(DictCode.共通业务类型,"38","场外期权"),
    //DictCodeList 文件超长移动 guyunfei 20200103 start
    拆分标识_已拆分(DictCode.拆分标识, "01", "已拆分"),
    拆分标识_未拆分(DictCode.拆分标识, "02", "未拆分"),
    //DictCodeList 文件超长移动 guyunfei 20200103 end
    基准类型_2_合约标的(DictCode.基准类型_2,"07","合约标的"),    //add by tanxuan 20200102 start
    //add by tanxuan 20200102 end
    计算模式_计算回购利率(DictCode.计算模式,"01","计算模式_计算回购利率"),
    计算模式_计算首次净价(DictCode.计算模式,"02","计算模式_计算首次净价"),
    计算模式_计算到期净价(DictCode.计算模式,"03","计算模式_计算到期净价"),
    //add by jyl 20200107
    共通业务类型_产品风险等级评定(DictCode.共通业务类型,"REQ06","产品风险等级评定"),
    // 质押券优先级设置字典 2020-1-8 lizhi start
    质押优先级_极高(DictCode.质押优先级,"01","极高"),
    质押优先级_高(DictCode.质押优先级,"02","高"),
    质押优先级_中(DictCode.质押优先级,"03","中"),
    质押优先级_低(DictCode.质押优先级,"04","低"),
    质押优先级_极低(DictCode.质押优先级,"05","极低"),
    收付方向_收取(DictCode.收付方向,"03","收取"),
    剩余期限_剩余期限短的优先质押(DictCode.剩余期限,"01","剩余期限短的优先质押"),
    剩余期限_剩余期限长的优先质押(DictCode.剩余期限,"02","剩余期限长的优先质押"),
    收付方向_支付(DictCode.收付方向,"04","支付"),
    债项评级_债项评级低的优先质押(DictCode.债项评级,"01","债项评级低的优先质押"),
    债项评级_债项评级高的优先质押(DictCode.债项评级,"02","债项评级高的优先质押"),
    交易类型_投资指令(DictCode.交易类型,"02","投资指令"),
    交易类型_预录入(DictCode.交易类型,"01","预录入"),
    // 质押券优先级设置字典 2020-1-8 lizhi end
    //add by likai 2020-1-14
    共通业务类型_期权投资委托(DictCode.共通业务类型,"ETR21","期权投资委托"),




    //add by tanxuan on 20200101
    金融产品类型_投管用_期权(DictCode.金融产品类型_投管用,"F35","期权"),
    /**
     * 截标状态明细 add by gejiaao 20200109
     */
    截标状态_已截标(DictCode.截标状态,"01","已截标"),
    截标状态_未截标(DictCode.截标状态,"02","未截标"),

    /** add by yanya on 20200111 交易方向增加 转换赎回申请、转换赎回确认、转换申购确认**/
    交易方向_转换赎回申请(DictCode.交易方向,"T62","转换赎回申请"),
    交易方向_转换赎回确认(DictCode.交易方向,"T63","转换赎回确认"),
    交易方向_转换申购确认(DictCode.交易方向,"T64","转换申购确认"),
    指令类型_债券类资产_申购划款(DictCode.指令类型,"2741","资金划款-债券类资产申购划款"),
    指令类型_债券类资产_申购退款(DictCode.指令类型,"2742","资金收款-债券类资产申购退款"),
    金融产品类型_期权(DictCode.金融产品类型,"F35","期权"),
    共通业务类型_期权交易(DictCode.共通业务类型,"TRD71","期权交易"),
    平安资产类型_期权(DictCode.平安资产类型,"F18","期权"),
    利率类型_结构性(DictCode.利率类型,"03","结构性"),
    基准类型_合约标的(DictCode.基准类型,"07","合约标的"),
    行权方式_欧式(DictCode.行权方式,"06","欧式"),
    行权方式_美式(DictCode.行权方式,"07","美式"),
    结构类型_看涨(DictCode.结构类型,"01","看涨"),
    结构类型_看跌(DictCode.结构类型,"02","看跌"),
    结构类型_二元看涨(DictCode.结构类型,"03","二元看涨"),
    结构类型_看涨鲨鱼鳍(DictCode.结构类型,"04","看涨鲨鱼鳍"),
    结构类型_双向鲨鱼鳍(DictCode.结构类型,"05","双向鲨鱼鳍"),
    期权费支付类型_先付期权费(DictCode.期权费支付类型,"01","先付期权费"),
    期权费支付类型_后付期权费(DictCode.期权费支付类型,"02","后付期权费"),

    共通业务类型_基金账户管理(DictCode.共通业务类型,"MST25","基金账户管理"),
    证券账户类型_证券账户(DictCode.证券账户类型,"SEC","证券账户"),
    证券账户类型_基金账户(DictCode.证券账户类型,"FUND","基金账户"),

    //穿透维度 add by chendijun 20200113 start
    穿透维度_不穿透(DictCode.穿透维度,"01","不穿透"),
    穿透维度_穿透(DictCode.穿透维度,"02","穿透"),
    穿透维度_底层资产(DictCode.穿透维度,"03","底层资产"),
    //穿透维度 add by chendijun 20200113 end

    //评级来源类型 add by chendijun 20200113 start
    评级来源类型_外部(DictCode.评级来源类型,"01","外部"),
    评级来源类型_内部(DictCode.评级来源类型,"02","内部"),
    //评级来源类型度 add by chendijun 20200113 end

    //融资人性质 add by chendijun 20200113 start
    融资人性质_企业类_事业类_债务人或保证人(DictCode.融资人性质,"01","企业类&事业类（债务人或保证人）"),
    融资人性质_商业银行(DictCode.融资人性质,"02","商业银行"),
    融资人性质_非银金融机构(DictCode.融资人性质,"03","非银金融机构"),
    融资人性质_企事业法人_银行间市场证券化(DictCode.融资人性质,"04","企事业法人&银行间市场证券化"),
    融资人性质_金融机构(DictCode.融资人性质,"05","金融机构"),
    融资人性质_其他证券化(DictCode.融资人性质,"06","其他证券化"),
    融资人性质_企事业法人_金融机构(DictCode.融资人性质,"07","企事业法人&金融机构"),
    //融资人性质 add by chendijun 20200113 end

    //行内资产四级分类 add by chendijun 20200113 start
    行内资产四级分类_短融(DictCode.行内资产四级分类,"015","短融"),
    行内资产四级分类_超短融(DictCode.行内资产四级分类,"016","超短融"),
    行内资产四级分类_金融机构次级债(DictCode.行内资产四级分类,"009","金融机构次级债"),
    //行内资产四级分类 add by chendijun 20200113 end

    //行内资产四级分类 add by chendijun 20200113 start
    行内资产三级分类_资产支持证券(DictCode.行内资产四级分类,"12","资产支持证券"),
    //行内资产四级分类 add by chendijun 20200113 end

    /** 区间内浮动管理费计提方式 add by yanya on 20200114 start**/
    区间内浮动管理费计提方式_区间内每日倒减计提(DictCode.区间内浮动管理费计提方式,"02","区间内每日倒减计提"),
    区间内浮动管理费计提方式_区间结束日一次计提(DictCode.区间内浮动管理费计提方式,"01","区间结束日一次计提"),
    /** 区间内浮动管理费计提方式 add by yanya on 20200114 end**/
    /**
     * 调整策略明细 add by gejiaao 20200109
     */
    调整策略_按比例分配(DictCode.调整策略,"01","按比例分配"),
    调整策略_价格优先分配(DictCode.调整策略,"02","价格优先分配"),
    /**
     *
     */
    行内资产五级分类_上市银行优先股(DictCode.行内资产五级分类,"142","上市银行优先股"),
    增值税资产分类_场外期权(DictCode.增值税资产分类,"P01","场外期权"),

    /**债券借贷费用损益计入方式 add by yanya 2020-01-22 start**/
    债券借贷费用损益计入方式_计入投资收益(DictCode.债券借贷费用损益计入方式,"01","计入投资收益"),
    债券借贷费用损益计入方式_计入费用支出(DictCode.债券借贷费用损益计入方式,"02","计入费用支出"),
    /**债券借贷费用损益计入方式 add by yanya 2020-01-22 end**/

    /**费用交易管理 	20200205 **/
    共通业务类型_费用交易管理(DictCode.共通业务类型,"FEETRADE","费用交易管理"),

    计息基础_30U_360(DictCode.计息基础,"30U/360","330/360 US"),

    处理状态_流程中(DictCode.处理状态,"03","流程中"),

    名单主体明细类型_交易对手(DictCode.名单主体明细类型, "10", "交易对手"),
    /** add by 谈旋 2020-03-05 **/
    金融产品类型_券商集合计划(DictCode.金融产品类型,"F55","券商集合计划"),
    /**
     * 账户性质
     */
    账户性质_银行账户(DictCode.账户性质,"01","银行账户"),
    账户性质_非银行账户(DictCode.账户性质,"02","非银行账户"),
    共通业务类型_产品信息管理(DictCode.共通业务类型,"V28","产品信息管理"),
    共通业务类型_期权标的管理(DictCode.共通业务类型,"V29","期权标的管理"),

    /**
     * 生僻字类型_全角半角
     */
    生僻字类型_全角半角(DictCode.生僻字类型,"01","全角半角"),
    /**
     * 生僻字类型_生僻字
     */
    生僻字类型_生僻字(DictCode.生僻字类型,"02","生僻字"),

    平安业务类型_期权买入(DictCode.平安业务类型,"145","期权买入"),
    平安业务类型_期权卖出(DictCode.平安业务类型,"146","期权卖出"),
    平安业务类型_期权放弃行权(DictCode.平安业务类型,"147","期权-放弃行权"),
    平安业务类型_期权提前行权(DictCode.平安业务类型,"148","期权-提前行权"),
    平安业务类型_期权到期行权(DictCode.平安业务类型,"149","期权买-到期行权"),
    共通业务类型_自定义参数管理(
            DictCode.共通业务类型, "RSK33", "自定义参数管理"
    ), 共通业务类型_固定值参数管理(
            DictCode.共通业务类型, "RSK28", "固定值参数管理"
    ),
    扣减对象_发行人(DictCode.扣减对象,"01","发行人"),
    扣减对象_融资人(DictCode.扣减对象,"02","融资人"),
    扣减对象_交易对手(DictCode.扣减对象,"03","交易对手"),
    扣减对象_担保人(DictCode.扣减对象,"04","担保人"),
    扣减对象_管理人(DictCode.扣减对象,"05","管理人"),
    扣减对象_存放行(DictCode.扣减对象,"06","存放行"),
    扣减对象_合作机构(DictCode.扣减对象,"09","合作机构"),
    额度规则类型_资产大类(DictCode.额度规则类型,"01","资产大类"),
    额度规则类型_单笔资产(DictCode.额度规则类型,"02","单笔资产"),
    共通业务类型_单笔资产额度校验规则(DictCode.共通业务类型,"RSK45","单笔资产额度校验规则"),
    共通业务类型_主体额度占用调整(DictCode.共通业务类型,"RSK46","主体额度占用调整"),
    额度调整类型_额度扣减(DictCode.额度调整类型,"01","额度扣减"),
    额度调整类型_额度释放(DictCode.额度调整类型,"02","额度释放"),

    清算状态_待处理(DictCode.清算状态,"W","待处理"),
    清算状态_清算中(DictCode.清算状态,"A","清算中"),
    清算状态_清算成功(DictCode.清算状态,"S","清算成功"),
    清算状态_清算失败(DictCode.清算状态,"F","清算失败"),
    清算状态_清算异常(DictCode.清算状态,"Y","清算异常"),
    清算状态_线下清算(DictCode.清算状态,"X","线下清算"),

    /*add by lijie2 20200509*/
    金融产品其他代码类型_回售代码(DictCode.金融产品其他代码类型,"01","回售代码"),
    金融产品其他代码类型_质押代码(DictCode.金融产品其他代码类型,"02","质押代码"),
    金融产品其他代码类型_可转债信用申购代码(DictCode.金融产品其他代码类型,"03","可转债信用申购代码"),
    金融产品其他代码类型_可转债转股代码(DictCode.金融产品其他代码类型,"04","可转债转股代码"),
    金融产品其他代码类型_配债代码(DictCode.金融产品其他代码类型,"05","配债代码"),
    金融产品其他代码类型_配股代码(DictCode.金融产品其他代码类型,"06","配股代码"),
    金融产品其他代码类型_新股申购代码(DictCode.金融产品其他代码类型,"07","新股申购代码"),

    共通业务类型_产品账户维护(DictCode.共通业务类型, "PTL17", "产品账户维护"),

    /*add by lijie2 20200511*/
    申报单位_手(DictCode.申报单位,"1","手"),
    申报单位_张(DictCode.申报单位,"2","张"),
    特殊处理业务_质押出入库(DictCode.特殊处理业务,"1","质押出入库"),
    柜台接口类型_金证kcbp(DictCode.柜台接口类型,"1","金证kcbp"),
    柜台接口类型_恒生ufx(DictCode.柜台接口类型,"2","恒生ufx"),

    客户性质_券商(DictCode.客户性质,"10","券商"),
    客户性质_期货公司(DictCode.客户性质,"11","期货公司"),
    共通业务类型_席位信息管理(DictCode.共通业务类型,"MST26","席位信息管理"),

    // add by wanshiwei 20200731
    共通业务类型_场内交易下单(DictCode.共通业务类型,"EXC01","场内交易下单"),
    共通业务类型_场内交易撤销(DictCode.共通业务类型,"EXC02","场内交易撤销"),
    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    /**
     * 数据字典枚举
     */
    private DictCode dictCode;

    /**
     * 明细名称
     */
    private String codeName;

    DictCodeListExt3(DictCode dictCode, String value, String codeName) {
        this.dictCode = dictCode;
        this.value = value;
        this.setCodeName(codeName);
    }

    /**
     * 获得数据字典详细类
     *
     * @param value 数据字典值
     * @param dictCode 数据字典DictCode
     * @return DictCodeList
     * @see
     * @since 1.0
     */
    public static DictCodeListExt3 from(DictCode dictCode, String value) {
        DictCodeListExt3[] values = DictCodeListExt3.values();
        for (DictCodeListExt3 code : values) {
            if (StringUtil.equals(value, code.getValue()) && code.getDictCode() == dictCode) {
                return code;
            }
        }
        return null;
    }

    /**
     * 转换成DICTCODELIST
     * @param dictCodeListExt2
     * @return
     */
    public static Map<String,Object> fromDictCodeList(DictCodeListExt3 dictCodeListExt2){
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("codeName",dictCodeListExt2.codeName);
        retMap.put("value",dictCodeListExt2.value);
        retMap.put("dictCode",dictCodeListExt2.dictCode);
        return retMap;
    }
    /**
     * 获取数据字典明细List
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<SelectOptionDto> fromDictCode(DictCode dictCode) {
        List<SelectOptionDto> result = new ArrayList<>();
        DictCodeListExt3[] values = DictCodeListExt3.values();
        for (DictCodeListExt3 code : values) {
            if (code.getDictCode() == dictCode) {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(code.getValue());
                option.setText(code.getCodeName());
                result.add(option);
            }
        }
        return result;
    }

    /**
     * 获取数据字典明细valueList
     *
     * @param dictCode DictCode
     * @return List&lt;SelectOptionDto&gt;
     * @see
     * @since 1.0
     */
    public static List<String> valuesfromDictCode(DictCode dictCode) {
        List<String> result = new ArrayList<>();
        DictCodeListExt3[] values = DictCodeListExt3.values();
        for (DictCodeListExt3 code : values) {
            if (code.getDictCode() == dictCode) {
                result.add(code.getValue());
            }
        }
        return result;
    }

    /**
     * 获取 DictCode
     *
     * @return value.
     */
    @Override
    public DictCode getDictCode() {
        return dictCode;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 codeName
     *
     * @return codeName.
     */
    @Override
    public String getCodeName() {
        return codeName;
    }

    /**
     * 获取 带有引号的名称
     *
     * @return 带有引号的名称.
     */
    public String getQuotesCodeName() {
        return "“" + codeName + "”";
    }

    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public Integer getIntegerCodeName() {
        Integer result = null;
        try {
            result = Integer.parseInt(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取Integer value
     *
     * @return Integer value
     */
    public BigDecimal getBigDecimalCodeName() {
        BigDecimal result = null;
        try {
            result = new BigDecimal(codeName);
        }
        catch (Exception e) {
        }
        return result;
    }

    /**
     * 设置 codeName
     *
     * @param codeName codeName
     */
    @Override
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

}
