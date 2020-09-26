/**拆借人行资产类型映射修改（交易对手类型为非银行业金融机构） start**/
DELETE chb_type_mapping_ex WHERE MAPPINGCODE='MAPPINGCODE_PBCASSETTYPE' AND srcfield1 = 'F08' AND OBJFIELD3='L07';
UPDATE chb_type_mapping_ex SET SRCFIELD='F08||||||C0402|' WHERE  MAPPINGCODE='MAPPINGCODE_PBCASSETTYPE' AND srcfield1 = 'F08' AND OBJFIELD3='L06' AND SRCFIELD='F08|||||02|C0402|';
/**拆借人行资产类型映射修改（交易对手类型为非银行业金融机构） end**/
