package cn.org.xmind.sale.db;

import cn.org.xmind.sale.db.entity.EntryAndExitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LuoWenqiang
 */
@Repository
public interface EntryAndExitRecordRepository extends JpaRepository<EntryAndExitRecord, Long> {

    @Query("select sum(r.quantity) from EntryAndExitRecord r where r.model=:model")
    public Integer getInventory(@Param("model") String model);
}
