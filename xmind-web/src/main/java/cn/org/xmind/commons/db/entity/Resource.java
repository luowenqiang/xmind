package cn.org.xmind.commons.db.entity;

import cn.org.xmind.identity.db.entity.Permission;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
 *
 * @author rodney
 */
@Entity
@Table(name = "COMMONS_RESOURCE")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 资源的名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 资源对应的访问URI，可以支持正则表达式模糊匹配
     */
    @Column(name = "URI")
    private String uri;
    /**
     * 资源的类型
     */
    @Column(name = "TYPE")
    private String type;
    /**
     * 优先级
     */
    @Column(name = "PRIORITY")
    private int priority;
    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;
    /**
     * 所有可以访问该资源的角色
     */
    @ManyToMany
    @JoinTable(name = "COMMONS_RESOURCE_PERMISSION",
    joinColumns =
    @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID"),
    inverseJoinColumns =
    @JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID"))
    private Set<Permission> permissions = new HashSet<Permission>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openoa.commons.db.entity.Resource[ id=" + getId() + " ]";
    }

    /**
     * 资源的名称
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 资源的名称
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 资源对应的访问URI，可以支持正则表达式模糊匹配
     *
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * 资源对应的访问URI，可以支持正则表达式模糊匹配
     *
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 资源的诶性
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * 资源的诶性
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 优先级
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 优先级
     *
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * 备注
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     *
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 所有可以访问该资源的角色
     *
     * @return the permissions
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    /**
     * 所有可以访问该资源的角色
     *
     * @param permissions the permissions to set
     */
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
