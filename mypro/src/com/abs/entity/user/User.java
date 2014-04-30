package com.abs.entity.user;

import java.io.Serializable;

import com.abs.entity.base.GenericEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.Table;

@Table(value = "user")
public class User extends GenericEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7207975511756056150L;

    private String userName;

    private String password;

    private String realName;

    private String sex;

    private Integer age;

    private String state;

    @Columns(value = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Columns(value = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Columns(value = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Columns(value = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Columns(value = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return the state
     */
    @Columns(value = "state")
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

}
