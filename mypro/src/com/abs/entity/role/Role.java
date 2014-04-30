package com.abs.entity.role;

import com.abs.entity.base.GenericEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.Table;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-4 下午3:40:07</P>
 * @author zhangjun
 * @version 1.0
 */
@Table(value = "ROLE")
public class Role extends GenericEntity {

    /** @Fields serialVersionUID : 描述这个变量表示什么 */
    private static final long serialVersionUID = -864814907360207366L;

    /** @Fields name : 权限名称*/
    private String name;

    /** @Fields type : 权限类型*/
    private String type;

    /** @Fields code : 权限编码*/
    private String code;

    /** @Fields isEnabled : 启用状态*/
    private String isEnabled;

    /**
     * @return the isEnabled
     */
    @Columns(value = "is_enabled")
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Columns(value = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Columns(value = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Columns(value = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
