package com.abs.service.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

public class OperatorDetails extends User {

    private static final long serialVersionUID = 2859758677160232193L;

    // 用户ID
    private String userid = null;
    // 用户真实名称
    private String realName = null;
    // 权限
    private Collection<GrantedAuthority> authorities;

    public OperatorDetails(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        setAuthorities(authorities);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Set<GrantedAuthority> getAuthoritySet() {
        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        for (GrantedAuthority role : authorities) {
            set.add(role);
        }
        return set;
    }

    public void setAuthoritySet(Set<GrantedAuthority> authorities) {
        Collection<GrantedAuthority> list = authorities;
        setAuthorities(list);
    }

    @SuppressWarnings("rawtypes")
    protected void setAuthorities(Collection<GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
        int i = 0;
        for (Iterator it = authorities.iterator(); it.hasNext();) {
            Assert.notNull(it.next(), "Granted authority element " + i
                    + " is null - GrantedAuthority[] cannot contain any null elements");
            i++;
        }

        this.authorities = authorities;
    }

    /**
     * Whether the current user has the certain authority.
     * 
     * @param authority
     * @return true if user has given authority.
     */
    public boolean hasAuthority(String authority) {
        for (GrantedAuthority role : authorities) {
            if (role.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }

}
