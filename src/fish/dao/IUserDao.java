package fish.dao;

import fish.po.SysUser;

import java.sql.SQLException;

public interface IUserDao {
    boolean checkUser(SysUser user) throws ClassNotFoundException, SQLException;
}
