package cn.org.xmind.sale.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    /**
     * 类型
     */
    @Column(name = "TYPE")
    private String type;
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 型号
     */
    @Column(name = "MODEL")
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 类型
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * 类型
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 名称
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 型号
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * 型号
     *
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

}
