-- Add/modify columns 
alter table MOD_ASEET_MAPPING modify input1 not null;
alter table MOD_ASEET_MAPPING modify input2 not null;
alter table MOD_ASEET_MAPPING modify output1 not null;
alter table MOD_ASEET_MAPPING modify output2 not null;
alter table MOD_ASEET_MAPPING modify output3 not null;
alter table MOD_ASEET_MAPPING modify output4 not null;
alter table MOD_ASEET_MAPPING modify output5 not null;

drop index MOD_ASEET_MAPPING_INDEX1;
drop index MOD_ASEET_MAPPING_INDEX2;
-- Create/Recreate indexes 
create index MOD_ASEET_MAPPING_INDEX1 on MOD_ASEET_MAPPING (MAPPING_TYPE, INPUT1, INPUT2);
create index MOD_ASEET_MAPPING_INDEX2 on MOD_ASEET_MAPPING (MAPPING_TYPE, OUTPUT5);
  
-- Add/modify columns 
alter table MOD_ASSET_GRADE_CONFIG modify asset_type not null;
alter table MOD_ASSET_GRADE_CONFIG modify grade_org_id not null;
alter table MOD_ASSET_GRADE_CONFIG modify o_type not null;
alter table MOD_ASSET_GRADE_CONFIG modify create_time not null;
alter table MOD_ASSET_GRADE_CONFIG modify update_user not null;
alter table MOD_ASSET_GRADE_CONFIG modify update_time not null;
  
drop index MOD_ASSET_GRADE_CONFIG_INDEX;
-- Create/Recreate indexes 
create index MOD_ASSET_GRADE_CONFIG_INDEX on MOD_ASSET_GRADE_CONFIG (ASSET_TYPE, ASSET_DETAIL_TYPE);


-- Add/modify columns 
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify finprod_id not null;
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify finprod_name not null;
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify grade_org_id not null;
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify o_type not null;

drop index MOD_ASSET_GRADE_SPECIAL_CONFIG_INDEX;
-- Create/Recreate indexes 
create index MOD_ASSET_GRADE_SPECIAL_CONFIG_INDEX on MOD_ASSET_GRADE_SPECIAL_CONFIG (FINPROD_ID);

-- Add/modify columns 
alter table MOD_GRADE_PD_CONFIG modify vouch_year not null;
alter table MOD_GRADE_PD_CONFIG modify grade_result not null;
alter table MOD_GRADE_PD_CONFIG modify forward_year_pd not null;

-- Add/modify columns 
alter table MOD_PD_FACTOR_CONFIG modify vouch_year not null;
alter table MOD_PD_FACTOR_CONFIG modify vouch_quarter not null;
alter table MOD_PD_FACTOR_CONFIG modify optimistic_factor not null;
alter table MOD_PD_FACTOR_CONFIG modify basic_factor not null;
alter table MOD_PD_FACTOR_CONFIG modify pessimism_factor not null;

-- Add/modify columns 
alter table MOD_LGD_CONFIG modify asset_type not null;


-- Add/modify columns 
alter table MOD_LGD_SPECIAL_CONFIG modify finprod_id not null;
alter table MOD_LGD_SPECIAL_CONFIG modify lgd not null;

drop index MOD_LGD_SPECIAL_CONFIG_INDEX;
-- Create/Recreate indexes 
create index MOD_LGD_SPECIAL_CONFIG_INDEX on MOD_LGD_SPECIAL_CONFIG (FINPROD_ID);
  
  
-- Add/modify columns 
alter table MOD_STAGE_PARA modify stage_of_asset not null;
alter table MOD_STAGE_PARA modify absolute_of_elements not null;
alter table MOD_STAGE_PARA modify elements not null;


-- Add/modify columns 
alter table MOD_STANDARD_GRADE_MAPPING modify grade_inst not null;
alter table MOD_STANDARD_GRADE_MAPPING modify o_type not null;
alter table MOD_STANDARD_GRADE_MAPPING modify grade_result not null;
alter table MOD_STANDARD_GRADE_MAPPING modify grade_standard_result not null;

drop index MOD_STANDARD_GRADE_MAPPING_INDEX;
-- Create/Recreate indexes 
create index MOD_STANDARD_GRADE_MAPPING_INDEX on MOD_STANDARD_GRADE_MAPPING (GRADE_INST, O_TYPE, GRADE_RESULT);


-- Add/modify columns 
alter table MOD_PD_WEIGHT_CONFIG modify optimistic_weight not null;
alter table MOD_PD_WEIGHT_CONFIG modify basic_weight not null;
alter table MOD_PD_WEIGHT_CONFIG modify pessimism_weight not null;


-- Add/modify columns 
alter table MOD_CONFIG_USEAGE modify config_seqno not null;
alter table MOD_CONFIG_USEAGE modify config_type not null;
alter table MOD_CONFIG_USEAGE modify use_process not null;

drop index MOD_CONFIG_USEAGE_INDEX;
-- Create/Recreate indexes 
create index MOD_CONFIG_USEAGE_INDEX on MOD_CONFIG_USEAGE (CONFIG_SEQNO, CONFIG_TYPE, USE_PROCESS);
  
  
-- Add/modify columns 
alter table MOD_ECL_RESULT modify prod_id not null;
alter table MOD_ECL_RESULT modify prod_name not null;
alter table MOD_ECL_RESULT modify asset_type not null;
alter table MOD_ECL_RESULT modify portfolio_id not null;
alter table MOD_ECL_RESULT modify buss_no not null;
alter table MOD_ECL_RESULT modify trade_date not null;
alter table MOD_ECL_RESULT modify dec_date not null;
alter table MOD_ECL_RESULT modify allow_model_cover not null;

drop index MOD_ECL_RESULT_INDEX;
-- Create/Recreate indexes 
create index MOD_ECL_RESULT_INDEX on MOD_ECL_RESULT (PORTFOLIO_ID, DEC_DATE);
  
drop index MOD_ECL_RESULT_PROCESS_INDEX;
-- Create/Recreate indexes 
create index MOD_ECL_RESULT_PROCESS_INDEX on MOD_ECL_RESULT_PROCESS (DEC_DATE, PROCESS);