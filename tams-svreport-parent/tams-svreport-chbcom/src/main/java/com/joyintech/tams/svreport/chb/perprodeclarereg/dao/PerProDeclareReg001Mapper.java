package com.joyintech.tams.svreport.chb.perprodeclarereg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerProDeclareReg001Mapper {
    public List<PerProDeclareRegDto> selectAllData(Map<String, Object> map);

	public List<PerProDeclareRegDto> selcteDataByList(List<PerProDeclareRegDto> dataList);

	public void updateSubmitStatusToOk(List<String> registSeqList);

	public void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("submitFeeBackStatus")String submitFeeBackStatus, @Param("registList")List<PerProDeclareRegDto> proDeclareList);

	public void cancelMannultRegist(List<PerProDeclareRegDto> proDeclareList);

	public PerProDeclareRegDto selectByPrimaryKey(String registSeqno);

	public void insertFile(HashMap<String, String> fileMap);

	public int selectFileNumByFileId(String fileId);

	public void updateFileNo(PerProDeclareRegDto perProDeclareRegDto);

	public int selectCountByProdId(String prodId);

	public void insertPerProdDeaclarReg();

	public List<PerProDeclareRegDto> selectPerProdList(@Param("generateDate")String generateDate,
	        @Param("prodInfos")List<String> prodIds);
	
	public List<PerProDeclareRegDto> selectPerProdListEx(@Param("generateDate")String generateDate,@Param("model_type")String model_type,@Param("prodInfos")List<String> prodIds);
	public List<PerProDeclareRegDto> selectPerProdListEx(@Param("generateDate")String generateDate, @Param("model_type")String model_type);

	public void insertList(List<PerProDeclareRegDto> list);
	
	int deleteDeclaresub(Map<String,Object> map);

    /**
     * 功能:根据产品代码删除产品申报登记
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月23日
     * @param subList 
     * void
     */
    void deleteByProdid(@Param("deleteList")List<PerProDeclareRegDto> subList);

    /**
     * 功能:插入产品申报登记
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月28日
     * @param perProDeclareRegDto
     * @return 
     * int
     */
    public int insert(PerProDeclareRegDto perProDeclareRegDto);
    
    /**
     * @Description 查询登记的数据
     * @param list
     * @return List<PerProDeclareRegDto>
     */
    public List<PerProDeclareRegDto> selectRegPerProdList(@Param("list")List<PerProDeclareRegDto> list);

	public List<String> selectRegistSeqnoList(@Param("list")List<PerProDeclareRegDto> proDeclareList,@Param("flag")String flag);

	public void deleteRegistSeqnoList(@Param("regList")List<String> regList);

	public void insertSeletForSub(@Param("regList")List<String> seqnolist);

	/**
	 *  查询行内标识码
	 * @param map 入参
	 * @return 行内标识码列表
	 */
	List<SelectOptionDto> queryProdInsIdList(Map<String,Object> map);

	List<PerProDeclareRegDto> queryPrdDeclareList( @Param("columnList")List<String> columnList);

	int insertSysUploadFileInfo(SysUploadFileInfo sysUploadFileInfo);

	int deleteBySeqNo(String seqNo);
	void updateChbPrdDeclare(@Param("fileNo")String fileNo, @Param("seqNo")String seqNo);
}
