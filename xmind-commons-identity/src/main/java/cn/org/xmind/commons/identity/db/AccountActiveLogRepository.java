package cn.org.xmind.commons.identity.db;


import cn.org.xmind.commons.identity.db.entity.AccountActiveLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rodney
 */
@Repository
@Transactional
public interface AccountActiveLogRepository extends JpaRepository<AccountActiveLog, Long> {

    /**
     * 根据激活码查询激活记录
     *
     * @param activeCode
     * @return
     */
    public AccountActiveLog findByActiveCode(String activeCode);
}
