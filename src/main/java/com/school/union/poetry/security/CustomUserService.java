package com.school.union.poetry.security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.school.union.poetry.entity.Role;
import com.school.union.poetry.entity.User;
import com.school.union.poetry.entity.UserRole;
import com.school.union.poetry.mapper.RoleMapper;
import com.school.union.poetry.mapper.UserMapper;
import com.school.union.poetry.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomUserService
 *
 * @author houxin
 * @date 2019/4/30
 */
@Slf4j
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("username = {}", s);
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, s));
        log.info("user = {}", JSON.toJSONString(user));
        if (user == null) {
            throw new BadCredentialsException("用户名不存在");
        }
        List<Role> roles = new ArrayList<>();
        List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        userRoles.forEach((userRole -> roles.add(roleMapper.selectById(userRole.getRoleId()))));
        return new MyUserDetails(user, roles);
    }
}
