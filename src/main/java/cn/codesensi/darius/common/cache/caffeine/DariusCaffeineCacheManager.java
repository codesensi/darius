package cn.codesensi.darius.common.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Caffeine缓存管理器
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DariusCaffeineCacheManager extends CaffeineCacheManager implements InitializingBean {

    private Map<String, String> caffeineSpec = new HashMap<>();

    private Map<String, Caffeine<Object, Object>> builders = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        for (Map.Entry<String, String> caffeineSpecEntry : caffeineSpec.entrySet()) {
            builders.put(caffeineSpecEntry.getKey(), Caffeine.from(caffeineSpecEntry.getValue()));
        }
    }
}
