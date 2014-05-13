/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.org.xmind.identity.db;

import cn.org.xmind.identity.db.entity.AccountActiveLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rodney
 */
@Repository
public interface AccountActiveLogRepository extends JpaRepository<AccountActiveLog, Long> {

    /**
     * 根据激活码查询激活记录
     *
     * @param activeCode
     * @return
     */
    public AccountActiveLog findByActiveCode(String activeCode);
}
