package com.abs.dao.base;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abs.entity.base.BaseEntity;
import com.abs.entity.common.PageObject;
import com.abs.util.BeanUtil;

@Repository
public class BaseDao {
    private Connection connection = null;
    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 保存对象
     * 
     * @param obj
     */
    public <T extends BaseEntity> void createObject(T entity) {
        this.setSEQ(entity);
        String sql = BeanUtil.getCreateBeanSQL4MySQL(entity);
        this.jdbcTemplate.execute(sql);
    }

    /**
     * 得到新增对象主键
     * 
     * @param obj
     * @return
     */
    private final <T extends BaseEntity> void setSEQ(T entity) {
        String id = BeanUtil.getSequenceSQL4MySQL();
        entity.setId(id);
    }

    /**
     * 根据主键查询对象
     * 
     * @param clazz
     * @param id
     * @return
     */
    public <T extends BaseEntity> T findObjectById(Class<T> clazz, String id) {
        return (T) BeanUtil.getAnyBean4MySQL(clazz, this.jdbcTemplate.queryForList(BeanUtil.getBeanByIdSQL(clazz, id)));
    }

    /**
     * 根据主键查询对象
     * 
     * @param clazz
     * @param sql
     * @return
     */
    protected <T extends BaseEntity> T findObject(Class<T> clazz, String sql) {
        return (T) BeanUtil.getAnyBean4MySQL(clazz, this.jdbcTemplate.queryForList(sql));
    }

    /**
     * 根据主键查询对象
     * 
     * @param clazz
     * @param id
     * @return
     */
    protected <T> List<T> listObject(Class<T> clazz, String sql) {
        return (List<T>) BeanUtil.getAnyBeans4MySQL(clazz, this.jdbcTemplate.queryForList(sql));
    }

    /**
     * 删除对象
     * 
     * @param entity
     */
    public <T extends BaseEntity> void deleteObject(T entity) {
        this.jdbcTemplate.execute(BeanUtil.getDeleteBeanSQL4MySQL(entity));
    }

    /**
     * 更新对象
     * 
     * @param obj
     */
    public <T extends BaseEntity> void updateObject(T entity) {
        this.jdbcTemplate.execute(BeanUtil.getUpdateBeanSQL4MySQL(entity));
    }

    /**
     * 
     * @param clazz
     * @param sql
     * @param pageObject
     * @return
     */
    protected final <T> PageObject<T> listForPage(Class<T> clazz, String sql, PageObject<T> pageObject) {
        int dataCount = findCount(sql);
        pageObject.setDataCount(dataCount);
        StringBuilder queryBuilder = new StringBuilder(sql);
        queryBuilder.append(" limit ").append(pageObject.getStartPoint());
        queryBuilder.append(" , ").append(pageObject.getEndPoint());
        pageObject.setPageList(BeanUtil.getAnyBeans4MySQL(clazz, getJdbcTemplate()
                .queryForList(queryBuilder.toString())));
        return pageObject;
    }

    /**
     * 获得需要查询数据的总数据数
     * 
     * @param sql
     *            select count(*) from xx where ...
     * @return 数据总行数
     */
    private final int findCount(String sql) {
        StringBuilder countSQL = new StringBuilder("select count(*) ");
        try {
            countSQL.append(sql.substring(sql.toLowerCase().indexOf("from")));
        } catch (RuntimeException ex) {
            throw new BadSqlGrammarException("分页查询", sql, null);
        }
        return getJdbcTemplate().queryForInt(countSQL.toString());
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-12-2 上午9:30:00</P>
     * <P><B>作者：</B>zhangjun</P>
     * @return
     * @throws Exception 
     */
    public Connection getConnection() throws Exception {
        initDAO();
        return connection;
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-12-2 上午9:48:36</P>
     * <P><B>作者：</B>zhangjun</P>
     * @throws Exception 
     */
    private void initDAO() throws Exception {
        if (connection == null) {
            connection = this.getJdbcTemplate().getDataSource().getConnection();
        }
    }

}
