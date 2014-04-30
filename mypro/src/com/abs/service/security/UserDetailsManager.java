package com.abs.service.security;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abs.dao.user.UserDao;
import com.abs.entity.user.User;

/**
 * @author <a href="mailto:xianfu416@163.com">马现福</a>
 * @version 创建时间：Jul 13, 2011 3:15:34 PM
 * @功能：
 */
@Service
public class UserDetailsManager implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    /**
     * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User sysuser = null;
        try {
            username = username.trim();
            List<User> userList = userDao.findUserByUserName(new String(username.getBytes("ISO-8859-1"), "UTF-8"));
            if (userList.size() > 0) {
                sysuser = userList.get(0);
            } else {
                throw new UnsupportedEncodingException("用户不存在！");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (sysuser == null) {
            throw new UsernameNotFoundException("无此" + username + "用户");
        }

        Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(sysuser);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        OperatorDetails userDetails = new OperatorDetails(sysuser.getUserName(), sysuser.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
        userDetails.setRealName(sysuser.getRealName());
        userDetails.setUserid(sysuser.getId());
        return userDetails;
    }

    /**
     * 获得用户所有角色的权限.
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
        grantedAuthoritySet.add(new GrantedAuthorityImpl("ROLE_LOGIN"));
        List<String> codes = userDao.getRoleCodesByUserId(user.getId());
        for (String code : codes) {
            grantedAuthoritySet.add(new GrantedAuthorityImpl(code));
        }

        return grantedAuthoritySet;
    }

}
