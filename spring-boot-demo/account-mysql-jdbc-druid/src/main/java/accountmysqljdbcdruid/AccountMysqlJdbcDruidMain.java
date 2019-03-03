package accountmysqljdbcdruid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AccountMysqlJdbcDruidMain {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {

        try {
            Properties config = new Properties();
            config.setProperty("url", "jdbc:mysql://192.168.209.131:3306/Account?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            config.setProperty("username", "root");
            config.setProperty("password", "Admin@123");

            //监控统计
            config.setProperty("filters", "stat");

            //最大连接数量
            config.setProperty("maxActive", "20");

            //初始化连接数量
            config.setProperty("initialSize", "1");

            //超时等待时间以毫秒为单位
            config.setProperty("maxWait", "60000");

            //最小空闲连接
            config.setProperty("minIdle", "1");

            //校验连接池中限制时间超过minEvictableIdleTimeMillis的连接对象
            config.setProperty("timeBetweenEvictionRunsMillis", "3000");

            //连接在池中保持空闲而不被空闲连接回收器线程(如果有)回收的最小时间值，单位毫秒
            config.setProperty("minEvictableIdleTimeMillis", "300000");

            //SQL查询,用来验证从连接池取出的连接,在将连接返回给调用者之前
            config.setProperty("validationQuery", "SELECT now();");

            //指明连接是否被空闲连接回收器(如果有)进行检验.
            //如果检测失败,则连接将被从池中去除.
            config.setProperty("testWhileIdle", "true");

            //指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个.
            config.setProperty("testOnBorrow", "false");

            //指明是否在归还到池中前进行检验
            config.setProperty("testOnReturn", "false");

            //poolPreparedStatements=true
            config.setProperty("maxPoolPreparedStatementPerConnectionSize", "20");

            DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(config);

            DruidPooledConnection dpc = null;
            Statement stmt = null;
            ResultSet rset = null;

            try {
                dpc = druidDataSource.getConnection();
                if (dpc != null) {
                    System.out.println("连接成功");
                    stmt = dpc.createStatement();
                    rset = stmt.executeQuery("select * from Users");
                    while (rset.next()) {
                        System.out.println(rset.getString(1) + "," + rset.getString(2));
                    }
                }

            } finally {
                if (rset != null) {
                    tryClose(rset::close);
                }
                if (stmt != null) {
                    tryClose(stmt::close);
                }
                if (dpc != null) {
                    tryClose(dpc::close);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void tryClose(CloseFunction func) {
        try {
            func.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FunctionalInterface
    interface CloseFunction {
        void close() throws SQLException;
    }
}
