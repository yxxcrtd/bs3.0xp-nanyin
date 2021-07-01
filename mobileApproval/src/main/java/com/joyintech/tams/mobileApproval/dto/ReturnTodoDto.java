package com.joyintech.tams.mobileApproval.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnTodoDto extends BaseEntity {
    //pageId
    private String pageId;

    //业务类型
    private String busiType;

    //业务实体
    private Object businessData;

    
//      流程实例ID
    private String processInstanceId;
    
    //工作流Id
    private String workflowId;

    //流程状态
    private String status;

    //当前处理人
    private String curUser;

    //下一核审人集合
    private List<WflNextReviewerDto> candidateUser;
    
    //是否有下一审核人
    private String isLastStep;

    //操作类型集合
    private List<String> operationType;
    
  //审核意见集合
    private List<WflBusinessNodeDto> opinions;
//    流程状态
    private String tradeStatus;
    
    //发起人
    private String  firstUser;


    public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getFirstUser() {
		return firstUser;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}

	public String getIsLastStep() {
		return isLastStep;
	}

	public void setIsLastStep(String isLastStep) {
		this.isLastStep = isLastStep;
	}

	public List<WflBusinessNodeDto> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<WflBusinessNodeDto> opinions) {
		this.opinions = opinions;
	}

	public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Object getBusinessData() {
        return businessData;
    }

    public void setBusinessData(Object businessData) {
    	
    	JSONObject object = (JSONObject) JSONObject.toJSON(businessData);
    	Map map = new HashMap();
    	print(object, "",map);
    	
        this.businessData = JSONObject.toJSON(map);
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }

    public List<WflNextReviewerDto> getCandidateUser() {
        return candidateUser;
    }

    public void setCandidateUser(List<WflNextReviewerDto> candidateUser) {
        this.candidateUser = candidateUser;
    }

    public List<String> getOperationType() {
        return operationType;
    }

    public void setOperationType(List<String> operationType) {
        this.operationType = operationType;
    }
    
    public static void print(JSONObject object, String str, Map map) {
        if (null == str || "".equals(str)) {
            str = "";
        } else {
            str = str + ".";
        }

        for (String key : object.keySet()) {

//            if(key.contains("code")||key.contains("data")){
//                continue;
//            }


            if (object.get(key) instanceof JSONArray) {
                System.out.println("key-" + str + key + ":List");
                List<UploadFile> fileArray=new  ArrayList<UploadFile>();
                if("fileArray".equals(key)) {
                	for (int i = 0; i < ((JSONArray) object.get(key)).size(); i++) {
//                        print(((JSONArray) object.get(key)).getJSONObject(i), key,map);
//                		((JSONArray) object.get(key)).getJSONObject(i)
                		JSONObject fileObject=((JSONArray) object.get(key)).getJSONObject(i);
                		UploadFile file=new UploadFile();
                		for(String filekey : fileObject.keySet()) {
                			if("seqNo".equals(filekey)) {
                				file.setFileNo((String) fileObject.get(filekey));
                			}else if("fileNo".equals(filekey)) {
                				file.setFileNo((String) fileObject.get(filekey));
                			}
                			if("srcName".equals(filekey) && (String) fileObject.get(filekey)!=null) {
                				file.setFileName((String) fileObject.get(filekey));
                			}else
                			if("fileName".equals(filekey)) {
                				if((String)fileObject.get(filekey)!=null && (((String)fileObject.get(filekey)).indexOf("\\")!=-1 || ((String)fileObject.get(filekey)).indexOf("/")!=-1) && ((String)fileObject.get(filekey)).indexOf("_")!=-1) {
                					if(((String) fileObject.get(filekey)).lastIndexOf("\\")>((String) fileObject.get(filekey)).lastIndexOf("/")) {
                						file.setFileName(((String) fileObject.get(filekey)).substring(((String) fileObject.get(filekey)).lastIndexOf("\\")+1, ((String) fileObject.get(filekey)).lastIndexOf("_"))+
                    							((String) fileObject.get(filekey)).substring(((String) fileObject.get(filekey)).lastIndexOf("."), ((String) fileObject.get(filekey)).length()));
                					}else {
                						file.setFileName(((String) fileObject.get(filekey)).substring(((String) fileObject.get(filekey)).lastIndexOf("/")+1, ((String) fileObject.get(filekey)).lastIndexOf("_"))+
                    							((String) fileObject.get(filekey)).substring(((String) fileObject.get(filekey)).lastIndexOf("."), ((String) fileObject.get(filekey)).length()));
                					}
                					
                				}else {
                					file.setFileName((String) fileObject.get(filekey));
                				}
                			}
                		}
                		fileArray.add(file);
                    }
                	map.put(str + "fileArray",fileArray);
                }else if("uploadFileList".equals(key)) {
                	for (int i = 0; i < ((JSONArray) object.get(key)).size(); i++) {
                		JSONObject fileObject=((JSONArray) object.get(key)).getJSONObject(i);
                		UploadFile file=new UploadFile();
                		for(String filekey : fileObject.keySet()) {
                			if("seqNo".equals(filekey)) {
                				file.setFileNo((String) fileObject.get(filekey));
                			}else if("fileNo".equals(filekey)) {
                				file.setFileNo((String) fileObject.get(filekey));
                			}
                			if("srcName".equals(filekey) && (String) fileObject.get(filekey)!=null) {
                				file.setFileName((String) fileObject.get(filekey));
                			}else
                			if("fileName".equals(filekey)) {
                				if((String)fileObject.get(filekey)!=null && ((String)fileObject.get(filekey)).indexOf("\\")!=-1 && ((String)fileObject.get(filekey)).indexOf("_")!=-1) {
                					file.setFileName(((String) fileObject.get(filekey)).substring(((String) fileObject.get(filekey)).lastIndexOf("\\")+1, ((String) fileObject.get(filekey)).indexOf("_")));
                				}else {
                					file.setFileName((String) fileObject.get(filekey));
                				}
                			}
                		}
                		fileArray.add(file);
                    }
                	map.put(str + "fileArray",fileArray);
                }else if("fileCommonList".equals(key)) {
                	for (int i = 0; i < ((JSONArray) object.get(key)).size(); i++) {
                		JSONObject fileObject=((JSONArray) object.get(key)).getJSONObject(i);
                		UploadFile file=new UploadFile();
                		for(String filekey : fileObject.keySet()) {
                			if("seqNo".equals(filekey)) {
                				file.setFileNo((String) fileObject.get(filekey));
                			}else if("fileNo".equals(filekey)) {
                				file.setFileNo((String) fileObject.get(filekey));
                			}
                			if("srcName".equals(filekey)) {
                				file.setFileName((String) fileObject.get(filekey));
                			}else
                			if("fileName".equals(filekey)) {
                				if((String)fileObject.get(filekey)!=null && ((String)fileObject.get(filekey)).indexOf("\\")!=-1 && ((String)fileObject.get(filekey)).indexOf("_")!=-1) {
                					file.setFileName(((String) fileObject.get(filekey)).substring(((String) fileObject.get(filekey)).lastIndexOf("\\")+1, ((String) fileObject.get(filekey)).indexOf("_")));
                				}else {
                					file.setFileName((String) fileObject.get(filekey));
                				}
                			}
                		}
                		fileArray.add(file);
                	}
                	map.put(str + "fileArray",fileArray);
                }else {
                	map.put(str + key,object.get(key));
                }

//                print((JSONObject) JSONObject.toJSON(object.get(key)), key,map);
            } else if (object.get(key) instanceof String) {
                System.out.println("key-" + str + key + ":String");
                map.put(str + key,object.get(key));
            } else if (object.get(key) instanceof JSONObject) {
                System.out.println("key-" + key + ":Object");
                print((JSONObject) JSONObject.toJSON(object.get(key)), key,map);
            }else {
            	map.put(str + key,object.get(key));
            }
        }
    }
}
