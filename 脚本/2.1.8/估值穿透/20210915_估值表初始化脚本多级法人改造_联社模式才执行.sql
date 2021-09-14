--此脚本仅仅联社模式才执行
update rsk_valtbl_struct_map set is_default='Y' where id in ('dd92cdec63c8481fabff','a44a2fba3898472896f1');
update RSK_VALTBL_ASSET_SUB_MAP set is_default='Y' where id in ('33a065e3bb984674ae64','a11822dd3b7c40ee9f8a','d25b93ed1d73404d9a42','fe985eda73f04721a2e0');
update RSK_VALTBL_SUMSUB_MAP set is_default='Y' where id in ('4b589bf1db1d48c0910d','a167adf8bc7d46fe8f6f');
