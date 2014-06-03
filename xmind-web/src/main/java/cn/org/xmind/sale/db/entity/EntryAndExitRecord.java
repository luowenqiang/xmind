package cn.org.xmind.sale.db.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 进出库记录表
 *
 * @author LuoWenqiang
 */
@Entity
@Table(name = "XMIND_SALE_ENTRY_AND_EXIT")
public class EntryAndExitRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /**
     * 操作时间
     */
    @Column(name = "OPER_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operationTime;
    /**
     * 型号
     */
    @Column(name = "MODEL")
    private String model;
    /**
     * 数量，如果是负数表示出库、正数表示入库
     */
    @Column(name = "QUANTITY")
    private int quantity;

    /**
     * 进行出入库操作后的即时库存量，用于记录每次操作后的剩余库存
     */
    @Column(name = "INVENTORY")
    private int inventory;

    /**
     * 当前活动库存量，不保存到数据库
     */
    @Transient
    private int activityInventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 操作时间
     *
     * @return the operationTime
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * 操作时间
     *
     * @param operationTime the operationTime to set
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
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

    /**
     * 数量，如果是负数表示出库、正数表示入库
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 数量，如果是负数表示出库、正数表示入库
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 进行出入库操作后的即时库存量，用于记录每次操作后的剩余库存
     * @return the inventory
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * 进行出入库操作后的即时库存量，用于记录每次操作后的剩余库存
     * @param inventory the inventory to set
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    /**
     * 当前活动库存量，不保存到数据库
     * @return the activityInventory
     */
    public int getActivityInventory() {
        return activityInventory;
    }

    /**
     * 当前活动库存量，不保存到数据库
     * @param activityInventory the activityInventory to set
     */
    public void setActivityInventory(int activityInventory) {
        this.activityInventory = activityInventory;
    }
}
