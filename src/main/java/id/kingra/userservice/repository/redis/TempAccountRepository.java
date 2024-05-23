package id.kingra.userservice.repository.redis;

import id.kingra.userservice.entity.redis.TempAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempAccountRepository extends CrudRepository<TempAccount, String> {
}
