package cn.codesensi.darius.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j配置
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Darius后台管理系统")
                        .description("Darius后台管理系统api文档")
                        .contact(new Contact().name("codesensi"))
                        .version("V0.0.1")
                );
    }
}
