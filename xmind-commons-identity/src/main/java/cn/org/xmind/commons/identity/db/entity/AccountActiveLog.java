package cn.org.xmind.commons.identity.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 账户激活记录
 *
 * @author rodney
 */
@Entity
@Table(name = "ID_ACTIVE_LOG",
indexes = {
    @Index(columnList = "ACTIVE_CODE")
})
public class AccountActiveLog implements Serializable {

    private static final long serialVersionUID = 1L;

    public AccountActiveLog() {
    }

    public AccountActiveLog(User user, String activeCode) {
        this.user = user;
        this.activeCode = activeCode;
        Date current = new Date();
        this.createTime = current;
        //24小时内激活码有效
        this.validTime = new Date(current.getTime() + (60 * 60 * 24 * 1000));
    }
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 对应的用户对象
     */
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    /**
     * 激活码
     */
    @Column(name = "ACTIVE_CODE")
    private String activeCode;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 有效时间
     */
    @Column(name = "VALID_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTime;
    /**
     * 激活时间
     */
    @Column(name = "ACTIVE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeTime;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountActiveLog other = (AccountActiveLog) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
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
     * 对应的用户对象
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * 对应的用户对象
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 激活码
     *
     * @return the activeCode
     */
    public String getActiveCode() {
        return activeCode;
    }

    /**
     * 激活码
     *
     * @param activeCode the activeCode to set
     */
    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    /**
     * 创建时间
     *
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 有效时间
     *
     * @return the validTime
     */
    public Date getValidTime() {
        return validTime;
    }

    /**
     * 有效时间
     *
     * @param validTime the validTime to set
     */
    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    /**
     * 激活时间
     *
     * @return the activeTime
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * 激活时间
     *
     * @param activeTime the activeTime to set
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
}
