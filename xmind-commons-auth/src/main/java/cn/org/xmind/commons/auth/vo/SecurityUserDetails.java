package cn.org.xmind.commons.auth.vo;

import cn.org.xmind.commons.identity.db.entity.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author rodney
 */
public class SecurityUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;
    private Long userId;

    public SecurityUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        /*
         * public User(String username, String password,
         * boolean enabled, 
         * boolean accountNonExpired,
         * boolean credentialsNonExpired, 
         * boolean accountNonLocked, 
         * Collection<? extends GrantedAuthority> authorities)
         */
        super(user.getLoginName() == null ? user.getEmail() : user.getLoginName(), user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isPasswordNonExpired(),
                user.isAccountNonLocked(),
                authorities);
        this.user = user;
        this.userId = user.getId();
    }

    public SecurityUserDetails(User user, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLoginName(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
