package accountmysqljdbcbonecp;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountMySqlJDBCBoneCPMain {
    public static void main(String[] args){
        test01();
    }

    public static void test01(){
        BoneCP connectionPool = null;
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }

        try{
            //建立连接池
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            config.setUsername("root");
            config.setPassword("Admin@123");
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            connectionPool = new BoneCP(config);

            connection = connectionPool.getConnection();
            if(connection != null){
                System.out.println("Connection successful!");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from Users");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
            }

            connectionPool.shutdown();


        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
