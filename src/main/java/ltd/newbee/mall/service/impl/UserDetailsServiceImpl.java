package ltd.newbee.mall.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ltd.newbee.mall.dao.AdminUserMapper;
import ltd.newbee.mall.entity.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByMallInfo(HttpServletRequest request, HttpServletResponse response) throws UsernameNotFoundException {
        //int username = Integer.parseInt(request.getParameter("userId"));
        int username = 1;
        AdminUser user = adminUserMapper.selectByPrimaryKey(username);
        if (user == null) {
            throw new UsernameNotFoundException("未找到当前用户.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLoginUserName(),
                user.getLoginPassword(),
                getAuthorities()
        );
    }

    private List<GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }
}
