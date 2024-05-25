package id.kingra.userservice.entity.redis;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash(value = "account", timeToLive = 3600)
public class TempAccount {

    @Id
    private String id;

    @Indexed
    private String email;
    private Boolean valid = false;
}
