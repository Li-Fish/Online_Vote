package fish.service;

import fish.dao.IUserDao;
import fish.dao.UserDao;
import fish.po.SysUser;

import java.sql.SQLException;

public class UserService implements IUserService {

    public boolean checkNotNull(SysUser user) {
        boolean flag = false;
        if (user.getUserName() == null || "".equals(user.getUserName()) || user.getPwd() == null || "".equals(user.getPwd())) {
            flag = true;
        }
        return flag;
    }

    public boolean checkUser(SysUser user) throws ClassNotFoundException, SQLException {
        IUserDao dao = new UserDao();
        return dao.checkUser(user);
    }

}
