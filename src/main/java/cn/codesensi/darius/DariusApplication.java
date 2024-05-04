package cn.codesensi.darius;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.codesensi.darius.**.mapper")
@SpringBootApplication
public class DariusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DariusApplication.class, args);
    }

}
