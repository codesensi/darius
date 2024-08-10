package cn.codesensi.darius.common.constant;

/**
 * 常量类
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
public class Constant {

    public static final Integer ZERO_INT = 0;

    public static final Integer ONE_INT = 1;

    public static final String ZERO_STRING = "0";

    public static final String ONE_STRING = "1";

    /**
     * 异步任务执行器名称
     */
    public static final String ASYNC_TASK_EXECUTOR_NAME = "asyncTaskExecutor";

    /**
     * 定时任务执行器名称
     */
    public static final String SCHEDULED_TASK_EXECUTOR_NAME = "scheduledTaskExecutor";

    /**
     * 超级管理员id
     */
    public static final Long ADMIN_ID = 1L;

    /**
     * 超级管理员权限码
     */
    public static final String PERMIT_ADMIN_CODE = "*";

    /**
     * 根接口路径
     */
    public static final String ROOT_PATH = "/**";

    /**
     * SWAGGER接口路径
     */
    public static final String[] SWAGGER_PATH = {"/doc.html", "/favicon.ico", "/v3/api-docs/**", "/webjars/**"};

    /**
     * 系统管理接口路径
     */
    public static final String SYSTEM_PATH = "/system/**";

    /**
     * 超级管理员角色标识
     */
    public static final String ROLE_ADMIN_CODE = "admin";

}
