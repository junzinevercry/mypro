package com.abs.dto.link;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-6 下午2:45:11</P>
 * @author zhangjun
 * @version 1.0
 */
public class LinkDto {
    private String id;
    /** link_name varchar 300 0 -1 0 0 0 0 0 链接名称 utf8 utf8_general_ci 0 0. */
    private String name;
    /** link_url varchar 300 0 -1 0 0 0 0 0 链接地址 utf8 utf8_general_ci 0 0. */
    private String url;

    private Long sort;

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
