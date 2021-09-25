UPDATE SYS_PARA SET REMARK='【中债交易对手取值】0：真实交易对手，即交易表存的交易对手， 1：虚拟交易对手，参数配置'
WHERE SYS_PARA.PARA_CODE='SVR_IF_REAL_COUNTER';