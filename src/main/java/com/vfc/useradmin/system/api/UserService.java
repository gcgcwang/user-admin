package com.vfc.useradmin.system.api;

public interface UserService {


    String findAllUser();
    String findUserByUsername(String loginName);
}
