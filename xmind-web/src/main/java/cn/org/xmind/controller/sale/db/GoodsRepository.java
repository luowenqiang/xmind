package cn.org.xmind.controller.sale.db;

import cn.org.xmind.controller.sale.db.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LuoWenqiang
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
