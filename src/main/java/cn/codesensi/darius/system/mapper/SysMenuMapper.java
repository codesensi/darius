package cn.codesensi.darius.system.mapper;

import cn.codesensi.darius.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单信息表 Mapper接口
 *
 * @author codesensi
 * @since 2024-05-15 22:09:56
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 查询用户菜单权限码
     *
     * @param userId
     * @return
     */
    List<String> listMenuCodeByUserId(@Param("userId") Long userId);
}
