package cn.org.xmind.commons.security.db.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LuoWenqiang
 */
@Entity
@Table(name = "XSEC_PERMISSION")
public class Permission implements Serializable {

    @Id
    private Long ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

}
