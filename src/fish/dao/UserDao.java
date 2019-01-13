package fish.dao;

import fish.po.SysUser;
import fish.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao implements IUserDao {
    public boolean checkUser(SysUser user) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Statement psta = null;
        ResultSet rst = null;
        boolean flag = false;
        String sql = "select * from t_sysuser where username='" + user.getUserName() + "' and password='" + user.getPwd() + "'";
        con = DBUtil.createConnection();
        psta = con.createStatement();
        rst = psta.executeQuery(sql);
        if (rst.next()) {
            flag = true;
        }
        DBUtil.disconnect(con, psta, rst);
        homework.util.DBUtil.disconnect(con, psta, rst);
        return flag;
    }
}
