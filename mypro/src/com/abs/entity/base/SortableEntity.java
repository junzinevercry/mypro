package com.abs.entity.base;

import com.abs.util.annotation.Columns;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-5-13 下午4:51:37</P>
 * @author DELL
 * @version 1.0
 */
public class SortableEntity extends BaseEntity {

    /** @Fields serialVersionUID : 描述这个变量表示什么 */
    private static final long serialVersionUID = 1L;
    private Integer sort;

    /** 不需要注解. */
    @Columns("sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
