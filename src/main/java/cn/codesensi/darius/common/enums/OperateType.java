package cn.codesensi.darius.common.enums;

import lombok.Getter;

/**
 * 操作类型
 *
 * @author codesensi
 * @since 2024/1/13 10:57
 */
@Getter
public enum OperateType {

    /**
     * 未知
     */
    OTHER(0, "未知"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 更新
     */
    UPDATE(2, "更新"),

    /**
     * 查询
     */
    QUERY(3, "查询"),

    /**
     * 删除
     */
    DELETE(4, "删除");

    /**
     * 操作类型编码
     */
    private final Integer code;

    /**
     * 操作类型说明
     */
    private final String message;

    OperateType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
