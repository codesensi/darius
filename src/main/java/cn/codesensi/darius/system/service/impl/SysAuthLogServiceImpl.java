package cn.codesensi.darius.system.service.impl;

import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.util.Ip2regionUtil;
import cn.codesensi.darius.common.util.IpUtil;
import cn.codesensi.darius.common.util.ServletUtil;
import cn.codesensi.darius.system.entity.SysAuthLog;
import cn.codesensi.darius.system.mapper.SysAuthLogMapper;
import cn.codesensi.darius.system.service.ISysAuthLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 授权日志表 服务实现类
 *
 * @author codesensi
 * @since 2024-07-28 11:16:28
 */
@Service
public class SysAuthLogServiceImpl extends ServiceImpl<SysAuthLogMapper, SysAuthLog> implements ISysAuthLogService {

}
