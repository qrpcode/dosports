package com.xrafece.do_sport.service;

import com.xrafece.do_sport.model.Admin;

import java.util.List;

/**
 * @author Xrafece
 */
public interface AdminService {
    List<Admin> allAdminList();

    boolean queryForLogin(String username, String password);

    boolean addAdmin(Admin admin);

    boolean addAdminWithDefault(Admin admin);

    Admin queryAdminByUsername(String username);

    Admin queryAdminById(int id);

    boolean deleteAdmin(Admin admin);

    boolean deleteAdminById(int id);
}
