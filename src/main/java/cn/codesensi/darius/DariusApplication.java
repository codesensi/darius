package cn.codesensi.darius;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("cn.codesensi.darius.*.mapper")
@SpringBootApplication
public class DariusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DariusApplication.class, args);
    }

}
