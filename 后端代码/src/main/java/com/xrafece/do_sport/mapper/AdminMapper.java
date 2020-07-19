package com.xrafece.do_sport.mapper;

import com.xrafece.do_sport.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xrafece
 */
@Repository
public interface AdminMapper {
    List<Admin> allAdminList();

    Admin queryForLogin(@Param("username") String username, @Param("password") String password);

    String queryLevel(Admin admin);

    int addAdmin(Admin admin);

    int addAdminWithDefault(Admin admin);

    Admin queryAdminByUsername(String username);

    int deleteAdmin(Admin admin);

    int deleteAdminById(@Param("id") int id);
}
