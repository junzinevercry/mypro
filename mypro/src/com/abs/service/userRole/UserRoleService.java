package com.abs.service.userRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abs.dao.userRole.UserRoleDao;
import com.abs.entity.role.Role;

@Service
@Transactional
public class UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    public Map<String, String> findRoleByUserId(String id) {
        Map<String, String> map = new HashMap<String, String>();
        String ids = "";
        String names = "";
        List<Role> roles = this.userRoleDao.findRoleByUserId(id);
        for (Role r : roles) {
            ids = ids + r.getId() + ",";
            names = names + r.getName() + ",";
        }
        map.put("ids", ids.substring(0, ids.length() - 1));
        map.put("names", names.substring(0, names.length() - 1));
        return map;
    }
}
