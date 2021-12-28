package com.joyintech.sso.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点
 *
 */
@ApiModel(value="路由分类树",description="路由分类树")
public class TreeNode implements Serializable {

	private static final long serialVersionUID = 3913327991191482097L;

	@ApiModelProperty(required = true, name="id",value="节点id",example="")
	private String id;

	@ApiModelProperty(required = true, name="pid",value="父节点id",example="")
    private String pid;

	@ApiModelProperty(required = true, name="name",value="节点名称",example="")
    private String name;

	@ApiModelProperty(required = true, name="realname",value="节点名称",example="")
	private String realname;
    
	@ApiModelProperty(required = true, name="pid",value="节点类型（1：分类菜单，2：路由）",example="")
    private String type;
    
	@ApiModelProperty(required = true, name="children",value="子节点",example="")
    private List<TreeNode> children;
	
	@ApiModelProperty(required = true, name="sortnum",value="菜单排序",example="")
	private Integer sortnum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode(String id, String pid, String name, String type) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.type = type;
	}

	public Integer getSortnum() {
		return sortnum;
	}

	public void setSortnum(Integer sortnum) {
		this.sortnum = sortnum;
	}

	public TreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}

}
