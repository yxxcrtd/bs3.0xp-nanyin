
package com.joyintech.tams.report.submitexamination.investorsdetailcheck.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.report.submitexamination.investorsdetailcheck.dto.InvestorsDetailCheck001Dto;
import org.springframework.stereotype.Repository;


@Repository
public interface InvestorsDetailCheck001Mapper {

    List<InvestorsDetailCheck001Dto> searchTable(Map<String, Object> param);
    
}
