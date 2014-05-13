package cn.org.xmind.identity.db.entity;

import cn.org.xmind.commons.db.entity.Resource;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author rodney
 */
@Entity
@Table(name = "ID_PERMISSION")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 是否激活
     */
    @Column(name = "ENABLED")
    private boolean enabled;
    /**
     * 所有拥有此角色的用户
     */
    @ManyToMany(mappedBy = "permissions")
    private List<User> users = new LinkedList<User>();
    /**
     * 该角色所有的可访问资源
     */
    @ManyToMany(mappedBy = "permissions")
    private List<Resource> resources;

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
     * 角色名称
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名称
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 是否激活
     *
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 是否激活
     *
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 所有拥有此角色的用户
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * 所有拥有此角色的用户
     *
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * 该角色所有的可访问资源
     *
     * @return the resources
     */
    public List<Resource> getResources() {
        return resources;
    }

    /**
     * 该角色所有的可访问资源
     *
     * @param resources the resources to set
     */
    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
