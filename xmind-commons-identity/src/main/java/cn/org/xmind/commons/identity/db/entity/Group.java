package cn.org.xmind.commons.identity.db.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 用户组
 *
 * @author lwq
 */
@Entity
@Table(name = "ID_GROUP")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 组名
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 该组下所有的用户
     */
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new LinkedList<User>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Group)) {
            return false;
        }
        Group other = (Group) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openoa.identity.db.entity.Group[ id=" + id + " ]";
    }

    /**
     * 该组下所有的用户
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * 该组下所有的用户
     *
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * 组名
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 组名
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
