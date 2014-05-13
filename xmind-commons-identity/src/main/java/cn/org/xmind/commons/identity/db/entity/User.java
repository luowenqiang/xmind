package cn.org.xmind.commons.identity.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * 用户信息
 *
 * @author rodney
 */
@Entity
@Table(name = "XID_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 唯一，用户的姓名
     */
    @Column(name = "USER_NAME")
    private String userName;
    /**
     * 唯一，登录名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;
    /**
     * 用户的昵称，可重复
     */
    @Column(name = "NICK_NAME")
    private String nickName;
    /**
     * 加密后的密码，在比较用户的密码时，把用户的密码使用相同的加密算法进行加密，比较两个密文是否相同来判断密码是否一致。
     */
    @Column(name = "PASSWORD")
    @NotNull
    private String password;
    /**
     * 用户的电邮地址，不可重复
     */
    @Column(name = "EMAIL")
    @NotNull
    private String email;
    /**
     * 手机号码
     */
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;
    /**
     * 性别
     */
    @Column(name = "GENDER")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    /**
     * 年龄
     */
    @Column(name = "AGE")
    private Integer age;
    /**
     * 生日
     */
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    /**
     * 账户过期时间，如果是null，表示永远有效
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACT_EXPIRE_TIME")
    private Date accountExpireTime;
    /**
     * 账户是否已过期，如果是true表示未过期
     */
    @Transient
    private boolean accountNonExpired = true;
    /**
     * 是否已锁定，如果是true表示未锁定
     */
    @Column(name = "ACT_NON_LOCKED")
    private boolean accountNonLocked = true;
    /**
     * 密码过期时间，如果null，表示永久有效
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PWD_EXPIRE_TIME")
    private Date passwordExpireTime;
    /**
     * 密码是否已过期，如果是true表示未过期
     */
    @Transient
    private boolean passwordNonExpired = true;
    /**
     * 是否已激活，默认未激活，需要使用激活链接进行激活后才能登陆、使用
     */
    @Column(name = "ENABLED")
    private boolean enabled = false;

    /**
     * 用户和用户组的关系
     */
    @ManyToMany
    @JoinTable(name = "XID_USER_GROUP",
    joinColumns =
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
    inverseJoinColumns =
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"))
    private List<Group> groups = new LinkedList<Group>();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openoa.identity.db.entity.User[ id=" + getId() + " ]";
    }

    /**
     * 检查时间是否已过期
     *
     * @param time
     * @return 未过期返回true，已过期返回false
     */
    private boolean isNonExpried(Date expireTime) {
        if (expireTime != null) {
            //如果过期时间不为空，判断过期时间是否已经到达，到达时间表示已经过期
            long time = expireTime.getTime();
            long currentTime = System.currentTimeMillis();
            if (time >= currentTime) {
                //过期时间大于或等于当前时间，表示已经过期
                return false;
            }
        }
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 唯一，用户的姓名
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 唯一，用户的姓名
     *
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 唯一，登录名
     *
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 唯一，登录名
     *
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 用户的昵称，可重复
     *
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 用户的昵称，可重复
     *
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 加密后的密码，在比较用户的密码时，把用户的密码使用相同的加密算法进行加密，比较两个密文是否相同来判断密码是否一致。
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 加密后的密码，在比较用户的密码时，把用户的密码使用相同的加密算法进行加密，比较两个密文是否相同来判断密码是否一致。
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户的电邮地址，不可重复
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户的电邮地址，不可重复
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 手机号码
     *
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机号码
     *
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 性别
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 性别
     *
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 年龄
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄
     *
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 生日
     *
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 生日
     *
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 账户过期时间，如果是null，表示永远有效
     *
     * @return the accountExpireTime
     */
    public Date getAccountExpireTime() {
        return accountExpireTime;
    }

    /**
     * 账户过期时间，如果是null，表示永远有效
     *
     * @param accountExpireTime the accountExpireTime to set
     */
    public void setAccountExpireTime(Date accountExpireTime) {
        this.accountExpireTime = accountExpireTime;
    }

    /**
     * 账户是否已过期，如果是true表示未过期
     *
     * @return the accountNonExpired
     */
    public boolean isAccountNonExpired() {
        accountNonExpired = isNonExpried(accountExpireTime);
        return accountNonExpired;
    }

    /**
     * 是否已锁定，如果是true表示未锁定
     *
     * @return the accountNonLocked
     */
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * 是否已锁定，如果是true表示未锁定
     *
     * @param accountNonLocked the accountNonLocked to set
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * 密码过期时间，如果null，表示永久有效
     *
     * @return the passwordExpireTime
     */
    public Date getPasswordExpireTime() {
        return passwordExpireTime;
    }

    /**
     * 密码过期时间，如果null，表示永久有效
     *
     * @param passwordExpireTime the passwordExpireTime to set
     */
    public void setPasswordExpireTime(Date passwordExpireTime) {
        this.passwordExpireTime = passwordExpireTime;
    }

    /**
     * 密码是否已过期，如果是true表示未过期
     *
     * @return the passwordNonExpired
     */
    public boolean isPasswordNonExpired() {
        passwordNonExpired = isNonExpried(passwordExpireTime);
        return passwordNonExpired;
    }

    /**
     * 是否已激活，默认未激活，需要使用激活链接进行激活后才能登陆、使用
     *
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 是否已激活，默认未激活，需要使用激活链接进行激活后才能登陆、使用
     *
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 用户和用户组的关系
     *
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * 用户和用户组的关系
     *
     * @param groups the groups to set
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
