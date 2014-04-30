package com.abs.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.abs.entity.base.BaseEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.PK;
import com.abs.util.annotation.Table;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BeanUtil {

    /**
     * 获得与表关联实体的表名.
     * 
     * @param obj
     * @return
     */
    public static final <T extends BaseEntity> String getTableName(T entity) {
        String tableName = null;
        Annotation[] annotations = entity.getClass().getAnnotations();
        for (Annotation annotation : annotations) {
            if (Table.class.equals(annotation.annotationType())) {
                tableName = ((Table) annotation).value();
            }
        }
        return tableName;
    }

    /**
     * 获得删除的SQL.
     * 
     * @param obj
     * @return
     */
    @Deprecated
    public static final <T extends BaseEntity> String getDeleteBeanSQL(T entity) {
        StringBuilder deleteSql = new StringBuilder("delete from ");
        String[] nameAndValue = getPKNameAndValue(entity);
        String pkName = nameAndValue[0];
        String pkValue = nameAndValue[1];
        deleteSql.append(getTableName(entity));
        deleteSql.append(" where ");
        deleteSql.append(pkName);
        deleteSql.append("=");
        deleteSql.append(pkValue);
        return deleteSql.toString();
    }

    private static String[] getPKNameAndValue(Object obj) {
        String[] result = new String[2];
        String pkName = "";
        String pkValue = "";
        Class clazz = obj.getClass();
        // 得到所有方法
        Method[] ms = clazz.getMethods();
        for (Method m : ms) {
            String mName = m.getName();
            if (!mName.startsWith("get") || "getClass".equals(mName)) {
                continue;
            }
            // PK
            PK pk = m.getAnnotation(PK.class);
            if (pk == null) {
                continue;
            }
            Columns cs = m.getAnnotation(Columns.class);
            pkName = cs.value();
            try {
                Object tempObj = m.invoke(obj, new Object[0]);
                if (tempObj != null) {
                    pkValue = (String) tempObj;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
        result[0] = pkName;
        result[1] = pkValue;
        return result;
    }

    /**
     * 根据ID和类型查询实体.
     * 
     * @param clazz
     * @param id
     * @return
     */
    public static final <T extends BaseEntity> String getBeanByIdSQL(Class<T> clazz, String id) {
        Class result = null;
        StringBuilder selectSql = new StringBuilder();
        T entity = null;
        try {
            result = Class.forName(clazz.getName());
            entity = (T) result.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (entity != null) {
            selectSql.append("select * from ");
            String[] nameAndValue = getPKNameAndValue(entity);
            String pkName = nameAndValue[0];
            selectSql.append(getTableName(entity));
            selectSql.append(" where ");
            selectSql.append(pkName);
            selectSql.append("='");
            selectSql.append(id);
            selectSql.append("'");
        }
        return selectSql.toString();
    }

    /**
     * 将数据库中查询出的List<Map>转换成List<class>.
     * 
     * @param clazz
     *            需要去除单个实体的Class
     * @param resultList
     *            查询出的List<Map>
     * @return
     */
    @Deprecated
    public static final <T> List<T> getAnyBeans(Class<T> clazz, List<Map<String, Object>> resultList) {
        List<T> objList = new ArrayList<T>();
        for (Map<String, Object> result : resultList) {
            Object resultObj = null;
            List<String> propNames = getPropNames(result);
            try {
                resultObj = doReflect(result, propNames, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            objList.add((T) resultObj);
        }
        return objList;
    }

    /**
     * 将数据库中查询出的List<Map>转换成class.
     * 
     * @param clazz
     *            需要去除单个实体的Class
     * @param resultList
     *            查询出的List<Map>
     * @return
     */
    @Deprecated
    public static final <T> T getAnyBean(Class<T> clazz, List<Map<String, Object>> resultList) {
        Object resultObj = null;
        if (CommonUtils.isNotEmptyList(resultList)) {
            Map<String, Object> result = resultList.get(0);
            List<String> propNames = getPropNames(result);
            try {
                resultObj = doReflect(result, propNames, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) resultObj;
    }

    private static final <T> T doReflect(Map<String, Object> oneBean, List<String> propNames, Class<T> clazz)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class result = null;
        result = Class.forName(clazz.getName());
        Object oResult = result.newInstance();
        Method[] ms = result.getMethods();
        for (Method m : ms) {
            String mName = m.getName();
            if (mName.startsWith("get")) {
                continue;
            }
            mName = mName.toLowerCase().substring(3, mName.length());
            if (propNames.contains(mName)) {
                if (m.getGenericParameterTypes() != null && m.getGenericParameterTypes().length > 0) {
                    Object o = oneBean.get(mName);
                    if (o == null) {
                        continue;
                    }
                    try {
                        Class<?>[] parameterTypes = m.getParameterTypes();
                        if (parameterTypes != null && parameterTypes.length > 0) {
                            String paramType = parameterTypes[0].getName();
                            if ("java.lang.Double".equals(paramType)) {
                                BigDecimal bValue = (BigDecimal) o;
                                m.invoke(oResult, bValue.doubleValue());
                            } else if ("java.lang.Integer".equals(paramType)) {
                                BigDecimal bValue = (BigDecimal) o;
                                m.invoke(oResult, bValue.intValue());
                            } else if ("java.lang.Long".equals(paramType)) {
                                BigDecimal bValue = (BigDecimal) o;
                                m.invoke(oResult, bValue.longValue());
                            } else if ("java.lang.String".equals(paramType)) {
                                m.invoke(oResult, (String) o);
                            } else if ("java.util.Date".equals(paramType)) {
                                m.invoke(oResult, (java.util.Date) o);
                            }
                        }
                    } catch (Exception e) {
                        //System.err.println(mName + ": reflect error!!");
                        //System.err.println(mName + " type : " + o.getClass().getName());
                    }
                }
            }
        }
        return (T) oResult;
    }

    private static final List<String> getPropNames(Map<String, Object> oneBean) {
        List<String> propNames = new ArrayList<String>();
        List<Object> propValues = new ArrayList<Object>();
        Set<String> keys = oneBean.keySet();
        for (String key : keys) {
            String propName = getPropName(key);
            propNames.add(propName);
            propValues.add(oneBean.get(key));
        }
        for (int i = 0; i < propNames.size(); i++) {
            String propName = propNames.get(i);
            Object propValue = propValues.get(i);
            oneBean.put(propName, propValue);
        }
        return propNames;
    }

    private static final String getPropName(String key) {
        key = key.toLowerCase();
        if (key.contains("_")) {
            key = key.replace("_", "");
        }
        return key;
    }

    /**
     * 去掉单引号.
     * 
     * @param date
     * 
     * @return
     */
    private static final String replaceSingle(String str) {
        if (StringUtils.isNotEmpty(str)) {
            if (str.contains("'")) {
                str = str.replace("'", "''");
            }
        } else {
            str = "";
        }
        return str;
    }

    /*********************************************************/
    /*********************************************************/
    /*********************************************************/

    /**
     * 获得一个序列的名称MySQL.
     * 
     * @param obj
     * @return
     */
    public static final String getSequenceSQL4MySQL() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获得删除的SQL.
     * 
     * @param entity
     * @return
     */
    public static final <T extends BaseEntity> String getDeleteBeanSQL4MySQL(T entity) {
        StringBuilder deleteSql = new StringBuilder("delete from ");
        String[] nameAndValue = getPKNameAndValue(entity);
        String pkName = nameAndValue[0];
        String pkValue = nameAndValue[1];
        deleteSql.append(getTableName(entity));
        deleteSql.append(" where ");
        deleteSql.append(pkName);
        deleteSql.append("='");
        deleteSql.append(pkValue);
        deleteSql.append("'");
        return deleteSql.toString();
    }

    /**
     * 生成插入的sql语句.
     * 
     * @param baseEntity
     *            实体表的对象
     * @return
     */
    public static final <T extends BaseEntity> String getUpdateBeanSQL4MySQL(T baseEntity) {
        List<String> columnList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();
        Class clazz = baseEntity.getClass();
        // 得到所有方法
        Method[] ms = clazz.getMethods();
        String pkName = "";
        String pkValue = "";
        for (Method m : ms) {
            String mName = m.getName();
            if (!mName.startsWith("get") || "getClass".equals(mName)) {
                continue;
            }
            // PK
            PK pk = m.getAnnotation(PK.class);
            if (pk != null) {
                Columns cs = m.getAnnotation(Columns.class);
                pkName = cs.value();
                try {
                    pkValue = (String) m.invoke(baseEntity, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                continue;
            }
            // 列名
            String columnName = "";
            // 值
            String columnValue = "";
            // 其他字段
            Columns cs = m.getAnnotation(Columns.class);
            if (cs == null) {
                continue;
            }
            columnName = cs.value();
            Object tempColumnValue = null;
            // 执行方法
            try {
                tempColumnValue = m.invoke(baseEntity, new Object[0]);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (tempColumnValue instanceof Integer) {
                columnValue = ((Integer) tempColumnValue).toString();
            } else if (tempColumnValue instanceof String) {
                columnValue = (String) tempColumnValue;
                columnValue = "'" + replaceSingle(columnValue) + "'";
            } else if (tempColumnValue instanceof Double) {
                columnValue = ((Double) tempColumnValue).toString();
            } else if (tempColumnValue instanceof BigDecimal) {
                columnValue = ((BigDecimal) tempColumnValue).toString();
            } else if (tempColumnValue instanceof Long) {
                columnValue = ((Long) tempColumnValue).toString();
            } else if (tempColumnValue instanceof java.util.Date) {
                java.util.Date dateValue = (java.util.Date) tempColumnValue;
                columnValue = "'" + CommonUtils.dateToString(dateValue, "yyyy-MM-dd HH:mm:ss") + "'";
            }
            // 如果是空就设成null,否则就加个''
            if (StringUtils.isEmpty(columnValue)) {
                columnValue = "null";
            }
            columnList.add(columnName);
            valueList.add(columnValue);
        }
        StringBuilder sql = new StringBuilder("update ");
        sql.append(getTableName(baseEntity));
        sql.append(" set ");
        for (int i = 0; i < columnList.size(); i++) {
            String column = columnList.get(i);
            String value = valueList.get(i);
            sql.append(column + "=" + value);
            if (i < valueList.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(" where ");
        sql.append(pkName);
        sql.append("='");
        sql.append(pkValue);
        sql.append("'");
        return sql.toString();
    }

    /**
     * 生成插入的sql语句.
     * 
     * @param entity
     *            实体表的对象
     * @return
     */
    public static <T extends BaseEntity> String getCreateBeanSQL4MySQL(T entity) {
        List<String> columnList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();
        Class clazz = entity.getClass();
        // 得到所有方法
        Method[] ms = clazz.getMethods();
        for (Method m : ms) {
            String mName = m.getName();
            if (!mName.startsWith("get") || "getClass".equals(mName)) {
                continue;
            }
            // 列名
            String columnName = "";
            // 值
            String columnValue = "";
            Columns cs = m.getAnnotation(Columns.class);
            if (cs == null) {
                continue;
            }
            columnName = cs.value();
            Object tempColumnValue = null;
            // 执行方法
            try {
                tempColumnValue = m.invoke(entity, new Object[0]);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (tempColumnValue instanceof Integer) {
                columnValue = ((Integer) tempColumnValue).toString();
            } else if (tempColumnValue instanceof String) {
                columnValue = (String) tempColumnValue;
                columnValue = "'" + replaceSingle(columnValue) + "'";
            } else if (tempColumnValue instanceof Double) {
                columnValue = ((Double) tempColumnValue).toString();
            } else if (tempColumnValue instanceof BigDecimal) {
                columnValue = ((BigDecimal) tempColumnValue).toString();
            } else if (tempColumnValue instanceof Long) {
                columnValue = ((Long) tempColumnValue).toString();
            } else if (tempColumnValue instanceof java.util.Date) {
                java.util.Date dateValue = (java.util.Date) tempColumnValue;
                columnValue = "'" + CommonUtils.dateToString(dateValue, "yyyy-MM-dd HH:mm:ss") + "'";
            }
            // 如果是空就设成null,否则就加个''
            if (StringUtils.isEmpty(columnValue)) {
                columnValue = "null";
            }
            columnList.add(columnName);
            valueList.add(columnValue);
        }
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(getTableName(entity));
        sql.append(" (");
        for (int i = 0; i < columnList.size(); i++) {
            String column = columnList.get(i);
            sql.append(column);
            if (i < columnList.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(") values (");
        for (int i = 0; i < valueList.size(); i++) {
            String value = valueList.get(i);
            sql.append(value);
            if (i < valueList.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(") ");
        return sql.toString();
    }

    /**
     * 将数据库中查询出的List<Map>转换成List<class>.
     * 
     * @param clazz
     *            需要去除单个实体的Class
     * @param resultList
     *            查询出的List<Map>
     * @return
     */
    public static final <T> List<T> getAnyBeans4MySQL(Class<T> clazz, List<Map<String, Object>> resultList) {
        List<T> objList = new ArrayList<T>();
        for (Map<String, Object> result : resultList) {
            Object resultObj = null;
            List<String> propNames = getPropNames(result);
            try {
                resultObj = doReflect4MySQL(result, propNames, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            objList.add((T) resultObj);
        }
        return objList;
    }

    /**
     * 将数据库中查询出的List<Map>转换成class.
     * 
     * @param clazz
     *            需要去除单个实体的Class
     * @param resultList
     *            查询出的List<Map>
     * @return
     */
    public static final <T> T getAnyBean4MySQL(Class<T> clazz, List<Map<String, Object>> resultList) {
        Object resultObj = null;
        if (CommonUtils.isNotEmptyList(resultList)) {
            Map<String, Object> result = resultList.get(0);
            List<String> propNames = getPropNames(result);
            try {
                resultObj = doReflect4MySQL(result, propNames, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) resultObj;
    }

    private static final <T> T doReflect4MySQL(Map<String, Object> oneBean, List<String> propNames, Class<T> clazz)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class result = null;
        result = Class.forName(clazz.getName());
        Object oResult = result.newInstance();
        Method[] ms = result.getMethods();
        for (Method m : ms) {
            String mName = m.getName();
            //            if ("getFounds".equals(mName)) {
            //                System.err.println("mName:" + mName);
            //                System.err.println("equals:" + mName.startsWith("get"));
            //            }
            if (mName.startsWith("get")) {
                continue;
            }
            mName = mName.toLowerCase().substring(3, mName.length());
            if (propNames.contains(mName)) {
                if (m.getGenericParameterTypes() != null && m.getGenericParameterTypes().length > 0) {
                    Object o = oneBean.get(mName);
                    if (o == null) {
                        continue;
                    }
                    try {
                        Class<?>[] parameterTypes = m.getParameterTypes();
                        if (parameterTypes != null && parameterTypes.length > 0) {
                            String paramType = parameterTypes[0].getName();
                            if ("java.lang.Double".equals(paramType)) {
                                Double bValue = (Double) o;
                                m.invoke(oResult, bValue.doubleValue());
                            } else if ("java.lang.Integer".equals(paramType)) {
                                Integer bValue = (Integer) o;
                                m.invoke(oResult, bValue.intValue());
                            } else if ("java.lang.Long".equals(paramType)) {
                                Long bValue = (Long) o;
                                m.invoke(oResult, bValue.longValue());
                            } else if ("java.lang.String".equals(paramType)) {
                                m.invoke(oResult, (String) o);
                            } else if ("java.util.Date".equals(paramType)) {
                                m.invoke(oResult, (java.util.Date) o);
                            } else if ("java.math.BigDecimal".equals(paramType)) {
                                BigDecimal bValue = CommonUtils.stringToBigDecimal((String) o);
                                m.invoke(oResult, bValue);
                            }
                        }
                    } catch (Exception e) {
                        // System.err.println("【" + mName + "】: reflect error!!");
                        // System.err.println("【" + mName + "】 type : " + o.getClass().getName());
                    }
                }
            }
        }
        return (T) oResult;
    }

}
