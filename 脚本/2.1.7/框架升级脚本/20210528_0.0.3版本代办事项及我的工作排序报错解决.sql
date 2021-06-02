update SYS_MYTODOANDWORK_PSL  
set PSL_FIELD ='{field:''processStateValue'', width:100, title: ''MainPage.processState'', sortField: ''processState''}'
 where seq_no ='006' and psl_field like'%processStateValue%';

 
 update SYS_MYTODOANDWORK_PSL  
set PSL_FIELD ='{field:''ps'', width:100, title: ''MainPage.ps'', sortField: ''ps''}'
 where seq_no in ('104','004') and psl_field like'%ps%';