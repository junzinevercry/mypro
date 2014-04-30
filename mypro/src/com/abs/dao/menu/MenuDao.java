package com.abs.dao.menu;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.entity.common.PageObject;
import com.abs.entity.menu.Menu;
import com.abs.util.BeanUtil;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-4 下午3:59:09</P>
 * @author zhangjun
 * @version 1.0
 */
@Repository
public class MenuDao extends BaseDao {

    /**
     * <P><B>说明：</B>获得首页菜单列表(分页)</P>
     * <P><B>日期：</B>2013-6-6 上午9:11:54</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param queryMenu
     * @param pageObject
     * @param userId
     */
    public void listMenuForPage(Menu queryMenu, PageObject<Menu> pageObject, String userId) {
        StringBuilder sql = new StringBuilder("select * from MENU where 1=1 ");
        if (queryMenu != null) {
            if (StringUtils.isNotEmpty(queryMenu.getName())) {
                sql.append(" and name like '%");
                sql.append(queryMenu.getName().trim());
                sql.append("%'");
            }
            // setRoleIDSql(userId, sql);
        }
        sql.append(" order by sort desc");
        this.listForPage(Menu.class, sql.toString(), pageObject);
    }

    /**
     * <P><B>说明：</B>根据名称获得菜单</P>
     * <P><B>日期：</B>2013-6-5 下午5:00:27</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param winName
     * @param userId
     * @return
     */
    public Menu findMenuByWinNameAndUserId(String winName, String userId) {
        StringBuilder sql = new StringBuilder("select * from menu where 1=1 ");
        sql.append(" and WIN_NAME='");
        sql.append(winName);
        sql.append("'");
        setRoleIDSql(userId, sql);
        return this.findObject(Menu.class, sql.toString());
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-6-5 下午3:36:10</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param userId
     * @return
     */
    public List<Menu> listMenuForHomePage(String userId) {
        StringBuilder sql = new StringBuilder("select * from MENU where pId='0' ");
        setRoleIDSql(userId, sql);
        sql.append(" order by sort ");
        return this.listObject(Menu.class, sql.toString());
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-6-5 下午4:46:57</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param userId
     * @param pId
     * @return
     */
    public List<Menu> listSubMenuForHomePage(String userId, String pId) {
        StringBuilder sql = new StringBuilder("select * from MENU where 1=1 ");
        sql.append(" and pId='");
        sql.append(pId);
        sql.append("'");
        setRoleIDSql(userId, sql);
        sql.append(" order by sort ");
        return this.listObject(Menu.class, sql.toString());
    }

    /**
     * <P><B>说明：</B>获得首页菜单列表</P>
     * <P><B>日期：</B>2013-6-6 上午9:13:37</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param userId
     * @return
     */
    public List<Menu> listParentMenu(String userId) {
        StringBuilder sql = new StringBuilder("select * from MENU where pId='0' ");
//        setRoleIDSql(userId, sql);
        return this.listObject(Menu.class, sql.toString());
    }

    private void setRoleIDSql(String userId, StringBuilder sql) {
        if (userId != null) {
            sql.append(" and id in (");
            sql.append(" select menu_id from role_menu where role_id in (");
            sql.append(" select t.role_id from USER_ROLE t where user_id='");
            sql.append(userId);
            sql.append("'))");
        }
    }

    /**
     * <P><B>说明：</B>删除菜单</P>
     * <P><B>日期：</B>2013-6-6 上午9:14:41</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param menu
     */
    public void deleteMenu(Menu menu) {
        String delete1 = BeanUtil.getDeleteBeanSQL4MySQL(menu);
        String delete2 = "delete from ROLE_MENU where menu_id='" + menu.getId() + "'";
        this.getJdbcTemplate().execute(delete1);
        this.getJdbcTemplate().execute(delete2);
    }

    public Menu findMenuByName(String name, String id) {
        StringBuilder sql = new StringBuilder("select * from MENU where 1=1 ");
        if (StringUtils.isNotEmpty(name)) {
            sql.append(" and name='");
            sql.append(name);
            sql.append("'");
        }
        if (id != null) {
            sql.append(" and id <> '");
            sql.append(id);
            sql.append("'");
        }
        return this.findObject(Menu.class, sql.toString());
    }

    public Menu findMenuByUrl(String url, String id) {
        StringBuilder sql = new StringBuilder("select * from MENU where 1=1 ");
        if (StringUtils.isNotEmpty(url)) {
            sql.append(" and url='");
            sql.append(url);
            sql.append("'");
        }
        if (id != null) {
            sql.append(" and id <> '");
            sql.append(id);
            sql.append("'");
        }
        return this.findObject(Menu.class, sql.toString());
    }

    public Menu findMenuByWinName(String winName, String id) {
        StringBuilder sql = new StringBuilder("select * from MENU where 1=1 ");
        if (StringUtils.isNotEmpty(winName)) {
            sql.append(" and win_name='");
            sql.append(winName);
            sql.append("'");
        }
        if (id != null) {
            sql.append(" and id <> '");
            sql.append(id);
            sql.append("'");
        }
        return this.findObject(Menu.class, sql.toString());
    }

    /**
     * <P><B>说明：</B>获得所有菜单</P>
     * <P><B>日期：</B>2013-6-6 上午9:55:20</P>
     * <P><B>作者：</B>zhangjun</P>
     * @return
     */
    public List<Menu> listAllMenu() {
        StringBuilder sql = new StringBuilder("select * from  menu order by sort");
        return this.listObject(Menu.class, sql.toString());

    }

}
