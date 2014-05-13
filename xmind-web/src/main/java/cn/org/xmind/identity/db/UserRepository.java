package cn.org.xmind.identity.db;

import cn.org.xmind.identity.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rodney
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据登录名查询用户信息
     *
     * @param loginName 登录名
     * @return
     */
    public User findByLoginName(String loginName);

    /**
     * 根据电子邮件查询用户信息
     *
     * @param email 电子邮件地址
     * @return
     */
    public User findByEmail(String email);
}
