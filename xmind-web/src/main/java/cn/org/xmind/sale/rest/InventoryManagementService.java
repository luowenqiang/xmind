package cn.org.xmind.sale.rest;

import cn.org.xmind.sale.db.EntryAndExitRecordRepository;
import cn.org.xmind.sale.db.GoodsRepository;
import cn.org.xmind.sale.db.entity.EntryAndExitRecord;
import java.util.Date;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * 库存管理相关的操作，常见的包括：出库、入库、统计汇总等
 *
 * @author LuoWenqiang
 */
@Service("inventoryManagementService")
@Path("/inventory")
@Description(value = "")
public class InventoryManagementService {

    /**
     * 商品的库存管理接口
     */
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private EntryAndExitRecordRepository entryAndExitRecordRepository;

    /**
     * 查询当前的库存列表
     *
     * @param pageNumber 分页查询时的开始行数
     * @param pageSize 每页记录数，如果为0则表示使用默认的分页数
     * @return
     */
    //@Path("/list")
    @GET
    @Produces(value = {"application/json"})
    public Page<EntryAndExitRecord> list(@QueryParam("pageNumber") Integer pageNumber,
            @QueryParam("pageSize") Integer pageSize) {
        //从0页开始计算
        if (pageNumber == null) {
            pageNumber = 0;
        } else if (pageNumber < 0) {
            pageNumber = 0;
        }

        //如果没有pageSize参数，默认显示10条
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        //使用操作时间倒序排列
        Sort sort = new Sort(Direction.DESC, "operationTime");
        PageRequest req = new PageRequest(pageNumber, pageSize, sort);

        Page<EntryAndExitRecord> page = entryAndExitRecordRepository.findAll(req);

        //统计每个型号的货物的活动库存量
//        for (EntryAndExitRecord eae : page.getContent()) {
//
//        }
        return page;
    }

    /**
     * 新增商品数据
     *
     * @param entryAndExitRecord
     * @return
     */
    //@Path("/add")
    @PUT
    @Consumes(value = {"application/json"})
    @Produces(value = {"application/json"})
    @Transactional
    public Response add(EntryAndExitRecord entryAndExitRecord) {
        Response response = Response.ok().encoding("UTF-8").entity("{\"msg\":\"添加成功\"}").build();
        entryAndExitRecord.setOperationTime(new Date());
        //计算当前的剩余库存量
        //1.查询现有的活动库存量
        Integer inventory = entryAndExitRecordRepository.getInventory(entryAndExitRecord.getModel());
        if (inventory == null) {
            inventory = 0;
        }
        //2.把本次操作的数量加上现有的活动库存量，得到操作后的库存量
        entryAndExitRecord.setInventory(entryAndExitRecord.getQuantity() + inventory);
        if (entryAndExitRecord.getInventory() < 0) {
            response = Response.ok().encoding("UTF-8").entity("{\"msg\":\"添加成功，但是现有库存量已经为负数，请核对数据\"}").build();
        } else if (entryAndExitRecord.getInventory() == 0) {
            response = Response.ok().encoding("UTF-8").entity("{\"msg\":\"添加成功，但是现有库存量已经为零，请及时补货\"}").build();
        }
        entryAndExitRecordRepository.save(entryAndExitRecord);
        return response;
    }
}
