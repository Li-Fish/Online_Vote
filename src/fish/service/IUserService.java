package fish.service;

import fish.po.SysUser;

public interface IUserService {
    boolean checkUser(SysUser user) throws Exception;
    boolean checkNotNull(SysUser user);
}
