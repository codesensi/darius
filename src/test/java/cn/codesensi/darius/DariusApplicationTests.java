package cn.codesensi.darius;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DariusApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(DariusApplicationTests.class);

    @Test
    void contextLoads() {
    }

    /**
     * 生成密码
     */
    @Test
    public void getPassword() {
        String inputPw = "123456";
        // BCrypt加密生成密码
        String pw = BCrypt.hashpw(inputPw, BCrypt.gensalt());
        logger.info("生成的密码：{}", pw);
        // 使用checkpw方法检查被加密的字符串是否与原始字符串匹配
        boolean result = BCrypt.checkpw(inputPw, pw);
        logger.info("匹配结果：{}", result);
    }

    /**
     * 生成雪花id
     */
    @Test
    public void getId() {
        // 生成id
        long id = IdUtil.getSnowflakeNextId();
        logger.info("生成的id：{}", id);
    }

}
