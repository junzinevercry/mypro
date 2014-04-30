package com.abs.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserContext {

    public static OperatorDetails getUser() {
        OperatorDetails operatorDetails = null;
        try {
            operatorDetails = (OperatorDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            throw new ClassCastException("登录超时,请重新登录");
        } catch (RuntimeException e) {
            throw new RuntimeException("登录超时,请重新登录");
        }
        return operatorDetails;
    }

    public static boolean hasUser() {
        if (SecurityContextHolder.getContext() == null
                || SecurityContextHolder.getContext().getAuthentication() == null
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            return false;
        } else {
            return false;
        }
    }

    public static String getUsername() {
        return getUser().getUsername();
    }

    public static String getUserid() {
        return getUser().getUserid();
    }

    public static String getUserRealName() {
        return getUser().getRealName();
    }

}
