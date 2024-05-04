package cn.codesensi.darius.common.enums;

import lombok.Getter;

/**
 * 操作类型
 *
 * @author codesensi
 * @since 2024/1/13 10:57
 */
@Getter
public enum OperateTypeEnum {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 登录
     */
    LOGIN(1, "登录"),

    /**
     * 登出
     */
    LOGOUT(2, "登出"),

    /**
     * 新增
     */
    INSERT(3, "新增"),

    /**
     * 更新
     */
    UPDATE(4, "更新"),

    /**
     * 查询
     */
    QUERY(5, "查询"),

    /**
     * 删除
     */
    DELETE(6, "删除");

    /**
     * 操作类型编码
     */
    private final Integer code;

    /**
     * 操作类型说明
     */
    private final String message;

    OperateTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
