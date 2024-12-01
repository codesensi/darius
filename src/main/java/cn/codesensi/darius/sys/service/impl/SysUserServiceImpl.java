package cn.codesensi.darius.sys.service.impl;

import cn.codesensi.darius.sys.entity.SysUser;
import cn.codesensi.darius.sys.mapper.SysUserMapper;
import cn.codesensi.darius.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户信息表 服务实现类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
