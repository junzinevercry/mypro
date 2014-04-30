package com.abs.dao.statistic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.dto.statistic.ProfoundsStatisticDto;
import com.abs.entity.common.PageObject;

@Repository
public class ProFoundsStatisticDao extends BaseDao {
    public void listProfoundsStatisticForPage(PageObject<ProfoundsStatisticDto> pageObject,
            Map<String, String> queryObject) {
    	StringBuilder sql = new StringBuilder("select * from (SELECT itemId,itemNum,itemName,foundsType,SUM(founds) AS founds FROM vs_item_founds WHERE 1 = 1");
    	if(queryObject.get("itemName")!=null&&!"".equals(queryObject.get("itemName").trim())){
    		sql.append(" and itemName like '%");
    		sql.append(queryObject.get("itemName").trim());
    		sql.append("%'");
    	}
    	if(queryObject.get("itemNum")!=null&&!"".equals(queryObject.get("itemNum").trim())){
    		sql.append(" and itemNum like '%");
    		sql.append(queryObject.get("itemNum").trim());
    		sql.append("%'");
    	}
    	if(queryObject.get("startDate")!=null&&!"".equals(queryObject.get("startDate").trim())){
    		sql.append(" and date_format(paymentDate,'%Y,%m,%d') >= date_format('");
    		sql.append(queryObject.get("startDate").trim());
    		sql.append("','%Y,%m,%d')");
    	}
    	if(queryObject.get("endDate")!=null&&!"".equals(queryObject.get("endDate").trim())){
    		sql.append(" and date_format(paymentDate,'%Y,%m,%d') <= date_format('");
    		sql.append(queryObject.get("endDate").trim());
    		sql.append("','%Y,%m,%d')");
    	}
    	if(queryObject.get("itemFoundsType")!=null&&!"".equals(queryObject.get("itemFoundsType").trim())){
    		sql.append(" and foundsType = '");
    		sql.append(queryObject.get("itemFoundsType").trim());
    		sql.append("'");
    	}
    	sql.append(" GROUP BY itemId,itemNum,itemName,foundsType) l ");
    	this.listForPage(ProfoundsStatisticDto.class, sql.toString(), pageObject);
    }

	public List<ProfoundsStatisticDto> listProfoundsStatisticByTypeAndItemId(Map<String, String> map) {
		StringBuilder sql = new StringBuilder("select * from vs_item_founds where itemId = '");
		sql.append(map.get("id"));
		sql.append("' and foundsType = '");
		sql.append(map.get("type"));
		sql.append("'");
		if(map.get("startDate")!=null&&!"".equals(map.get("startDate").trim())){
    		sql.append(" and date_format(paymentDate,'%Y,%m,%d') >= date_format('");
    		sql.append(map.get("startDate").trim());
    		sql.append("','%Y,%m,%d')");
    	}
    	if(map.get("endDate")!=null&&!"".equals(map.get("endDate").trim())){
    		sql.append(" and date_format(paymentDate,'%Y,%m,%d') <= date_format('");
    		sql.append(map.get("endDate").trim());
    		sql.append("','%Y,%m,%d')");
    	}
    	sql.append(" order by foundsType");
    	return this.listObject(ProfoundsStatisticDto.class, sql.toString());
	}
}
