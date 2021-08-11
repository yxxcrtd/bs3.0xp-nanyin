package com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam;

import com.joyintech.tams.model.valuation.batch.calculation.InputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.entity.KeyPointDto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 插值输出参数
 * @author zznn
 * @date 2021/7/2
 */
public class TargetRateInputParameter implements InputParameter {

	//参考数据集
	private List<KeyPointDto> keyPointDtoList;
	
	//目标期限
	private BigDecimal targetTerm;
	
	
	@Override
	public void check() {
		checkNull(keyPointDtoList, "参考数据集");
		checkNull(targetTerm, "目标期限");
		
		//按关键年限点升序排列
        Collections.sort(keyPointDtoList, new Comparator<KeyPointDto>() {
            @Override
            public int compare(KeyPointDto o1, KeyPointDto o2) {
            	return o1.getKeyPoint().compareTo(o2.getKeyPoint());
            }
        });
        
	}
	
	public TargetRateInputParameter(){
		super();
	}
	/**
	 * 对象初始化
	 * @param keyPointDtoList 参考数据集
	 * @param targetTerm 目标期限
	 */
	public TargetRateInputParameter(List<KeyPointDto> keyPointDtoList, BigDecimal targetTerm){
		super();
		this.keyPointDtoList = keyPointDtoList;
		this.targetTerm = targetTerm;
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TargetRateInputParameter [keyPointDtoList=" + keyPointDtoList + ", targetTerm=" + targetTerm
               + "]";
    }

	public List<KeyPointDto> getKeyPointDtoList() {
		return keyPointDtoList;
	}

	public void setKeyPointDtoList(List<KeyPointDto> keyPointDtoList) {
		this.keyPointDtoList = keyPointDtoList;
	}

	public BigDecimal getTargetTerm() {
		return targetTerm;
	}

	public void setTargetTerm(BigDecimal targetTerm) {
		this.targetTerm = targetTerm;
	}
	
	

	
}
