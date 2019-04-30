package com.school.union.poetry.security;

import com.school.union.poetry.entity.Role;
import com.school.union.poetry.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * MyUserDetails
 *
 * @author houxin
 * @date 2019/4/30
 */
@NoArgsConstructor
public class MyUserDetails extends User implements UserDetails {
    private static final long serialVersionUID = 1123144364976433700L;

    private List<Role> roles;

    MyUserDetails(User user, List<Role> roles) {
        super(user.getUsername(), user.getPassword(), user.getTelephone(), user.getEmail(), user.getCreateTime(), user.getUpdateTime());
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = this.roles;
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleCode())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
