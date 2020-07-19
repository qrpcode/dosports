package com.xrafece.do_sport.service.impl;

import com.xrafece.do_sport.mapper.AdminMapper;
import com.xrafece.do_sport.model.Admin;
import com.xrafece.do_sport.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xrafece
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> allAdminList() {
        return adminMapper.allAdminList();
    }

    @Override
    public boolean queryForLogin(String username, String password) {
        Admin admin = adminMapper.queryForLogin(username, password);
        return admin != null;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin) != 0;
    }

    @Override
    public boolean addAdminWithDefault(Admin admin) {
        return adminMapper.addAdminWithDefault(admin) != 0;
    }

    @Override
    public Admin queryAdminByUsername(String username) {
        return adminMapper.queryAdminByUsername(username);
    }

    @Override
    public Admin queryAdminById(int id) {
        return null;
    }

    @Override
    public boolean deleteAdmin(Admin admin) {
        return adminMapper.deleteAdmin(admin) != 0;
    }

    @Override
    public boolean deleteAdminById(int id) {
        return adminMapper.deleteAdminById(id) != 0;
    }

    private String queryLevel(Admin admin) {
        return adminMapper.queryLevel(admin);
    }
}
