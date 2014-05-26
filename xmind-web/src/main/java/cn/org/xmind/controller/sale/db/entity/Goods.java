package cn.org.xmind.controller.sale.db.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LuoWenqiang
 */
@Entity
@Table(name = "XMIND_SALE_GOODS")
public class Goods implements Serializable {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
