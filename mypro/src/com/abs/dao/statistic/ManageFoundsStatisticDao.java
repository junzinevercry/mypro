package com.abs.dao.statistic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.dto.statistic.ManageFoundsStatisticDto;
import com.abs.entity.common.PageObject;
@Repository
public class ManageFoundsStatisticDao extends BaseDao {
	public void listManagefoundsStatisticForPage(
			PageObject<ManageFoundsStatisticDto> pageObject,
			Map<String, String> queryObject) {
		StringBuilder sql = new StringBuilder(
				"select * from (SELECT foundsType,SUM(founds) AS founds FROM vs_manage_founds WHERE 1 = 1");
		if (queryObject.get("startDate") != null
				&& !"".equals(queryObject.get("startDate").trim())) {
			sql.append(" and date_format(paymentDate,'%Y,%m,%d') >= date_format('");
			sql.append(queryObject.get("startDate").trim());
			sql.append("','%Y,%m,%d')");
		}
		if (queryObject.get("endDate") != null
				&& !"".equals(queryObject.get("endDate").trim())) {
			sql.append(" and date_format(paymentDate,'%Y,%m,%d') <= date_format('");
			sql.append(queryObject.get("endDate").trim());
			sql.append("','%Y,%m,%d')");
		}
		if (queryObject.get("manageType") != null
				&& !"".equals(queryObject.get("manageType").trim())) {
			sql.append(" and foundsType = '");
			sql.append(queryObject.get("manageType").trim());
			sql.append("'");
		}
		sql.append(" GROUP BY foundsType) l ");
		this.listForPage(ManageFoundsStatisticDto.class, sql.toString(),
				pageObject);
	}

	public List<ManageFoundsStatisticDto> listManagefoundsStatisticByType(
			Map<String, String> map) {
		StringBuilder sql = new StringBuilder(
				"select * from vs_manage_founds where foundsType = '");
		sql.append(map.get("type"));
		sql.append("'");
		if (map.get("startDate") != null
				&& !"".equals(map.get("startDate").trim())) {
			sql.append(" and date_format(paymentDate,'%Y,%m,%d') >= date_format('");
			sql.append(map.get("startDate").trim());
			sql.append("','%Y,%m,%d')");
		}
		if (map.get("endDate") != null && !"".equals(map.get("endDate").trim())) {
			sql.append(" and date_format(paymentDate,'%Y,%m,%d') <= date_format('");
			sql.append(map.get("endDate").trim());
			sql.append("','%Y,%m,%d')");
		}
		sql.append(" order by foundsType");
		return this.listObject(ManageFoundsStatisticDto.class, sql.toString());
	}
}
