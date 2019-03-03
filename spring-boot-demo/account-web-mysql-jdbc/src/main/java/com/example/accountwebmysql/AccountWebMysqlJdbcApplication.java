package com.example.accountwebmysql;

import com.mysql.cj.jdbc.NonRegisteringDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@SpringBootApplication
public class AccountWebMysqlJdbcApplication {

    private static Connection conn = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountWebMysqlJdbcApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(AccountWebMysqlJdbcApplication.class, args);

        TestDebugJdbc();
        //TestConnection();

        //TestConnectionBySpringContext();
        //TestAutoCommitAndInsert();
        //TestSelect();

        //TestAutoIncrement01();
        //TestAutoIncrement02();
        //TestAutoIncrement03();
    }

    private static void TestDebugJdbc(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{

            Driver driver = new NonRegisteringDriver();

            String url = "jdbc:mysql://192.168.209.131:3306/Account?useSSL=false&user=root&password=Admin@123";
            java.util.Properties info = new java.util.Properties();

            connection = driver.connect(url,info);
            statement = connection.createStatement();

            if(statement.execute("select * from Users")){
                resultSet = statement.getResultSet();
                printResultSet(resultSet);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e){

            }
        }
    }

    //Connection 用于连接的对象
    private static void TestConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false&user=root&password=Admin@123");

            printConnectionDefaultParameter(conn);

            //设置是否自动提交，
            //conn.setAutoCommit(false);

            LOGGER.info("连接成功");
            //conn.close();
        } catch (SQLException ex) {
            LOGGER.info("SQLException: {}", ex.getMessage());
            LOGGER.info("SQLState: {}", ex.getSQLState());
            LOGGER.info("VendorError: {}", ex.getErrorCode());
        }
    }

    // Statement 执行sql语句对象
    // ResultSet 接收结果对象
    private static void TestSelect() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql = "select * from Users";
//            rs = stmt.executeQuery(sql);
//            printResultSet(rs);
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                printResultSet(rs);
            }

        } catch (SQLException ex) {
            LOGGER.info("SQLException: {}", ex.getMessage());
            LOGGER.info("SQLState: {}", ex.getSQLState());
            LOGGER.info("VendorError: {}", ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private static void TestAutoCommitAndInsert(){
        try{
            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();

            LocalDateTime dateTime = LocalDateTime.now();

            //insert Users value(uuid(),'xyx','xyx@123',now(),now());

            UUID uuid = UUID.randomUUID();

            String name = "th000";
            String email = "th000@123";

            String sql = String.format("insert Users value('%s','%s','%s','%s','%s');",uuid,name,email,dateTime,dateTime);

            int count = statement.executeUpdate(sql);

            System.out.println("影响行数 " + count);

            //手动提交
            conn.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    private static void TestAutoIncrement01() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            stmt.executeUpdate("drop table if exists autoIncTutorial");
            stmt.executeUpdate("create table autoIncTutorial(" +
                    "priKey int not null auto_increment," +
                    "dataField varchar(64)," +
                    "primary key(priKey)" + ")"
            );

            stmt.executeUpdate("insert into autoIncTutorial (dataField) values ('Can I Get the Auto Increment Field?')"
                    , Statement.RETURN_GENERATED_KEYS);

            int autoIncKeyFromApi = -1;

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            } else {

            }

            LOGGER.info("Key returned from getGeneratedKeys():"
                    + autoIncKeyFromApi);

        } catch (SQLException ex) {
            LOGGER.info("SQLException: {}", ex.getMessage());
            LOGGER.info("SQLState: {}", ex.getSQLState());
            LOGGER.info("VendorError: {}", ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private static void TestAutoIncrement02() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            stmt.executeUpdate("drop table if exists autoIncTutorial");
            stmt.executeUpdate("create table autoIncTutorial(" +
                    "priKey int not null auto_increment," +
                    "dataField varchar(64)," +
                    "primary key(priKey)" + ")"
            );

            stmt.executeUpdate("insert into autoIncTutorial (dataField) values ('Can I Get the Auto Increment Field?')");

            //我们使用 Mysql 的 LAST_INSERT_ID() 特性

            int autoIncKeyFromFunc = -1;

            rs = stmt.executeQuery("select last_insert_id()");

            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1);
            } else {

            }

            LOGGER.info("Key returned from 'SELECT LAST_INSERT_ID()': "
                    + autoIncKeyFromFunc);

        } catch (SQLException ex) {
            LOGGER.info("SQLException: {}", ex.getMessage());
            LOGGER.info("SQLState: {}", ex.getSQLState());
            LOGGER.info("VendorError: {}", ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private static void TestAutoIncrement03() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            stmt.executeUpdate("drop table if exists autoIncTutorial");
            stmt.executeUpdate("create table autoIncTutorial(" +
                    "priKey int not null auto_increment," +
                    "dataField varchar(64)," +
                    "primary key(priKey)" + ")"
            );

            //
            // Example of retrieving an AUTO INCREMENT key
            // from an updatable result set
            //

            rs = stmt.executeQuery("select priKey,dataField from autoIncTutorial");

            rs.moveToInsertRow();

            rs.updateString("dataField", "AUTO INCREMENT here?");
            rs.insertRow();

            //
            // the driver adds rows at the end
            //

            rs.last();

            //
            // We should now be on the row we just inserted
            //

            int autoIncKeyFromRS = rs.getInt("priKey");

            LOGGER.info("Key returned for inserted row: " + autoIncKeyFromRS);

        } catch (SQLException ex) {
            LOGGER.info("SQLException: {}", ex.getMessage());
            LOGGER.info("SQLState: {}", ex.getSQLState());
            LOGGER.info("VendorError: {}", ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
    }


    //使用spring cloud的上下文容器创建数据连接对象，注意连接对象使用完的释放
    private static void TestConnectionBySpringContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("dbcontext.xml");

        DataSource ds = (DataSource) context.getBean("dataSource");

        conn = DataSourceUtils.getConnection(ds);

        //DataSourceUtils.releaseConnection(conn,ds);
    }

    private static void printConnectionDefaultParameter(Connection conn){
        if(conn != null){
            try{
                DatabaseMetaData databaseMetaData = conn.getMetaData();

                System.out.println("------------------catalogs------------------");
                ResultSet catalogs = databaseMetaData.getCatalogs();
                printResultSet(catalogs);
                System.out.println("--------------------------------------------");

                System.out.println("--------------clientInfoProperties----------");
                ResultSet clientInfoProperties = databaseMetaData.getClientInfoProperties();
                printResultSet(clientInfoProperties);
                System.out.println("--------------------------------------------");

                System.out.println("MaxConnections " + databaseMetaData.getMaxConnections());

                System.out.println("auto commit " + conn.getAutoCommit());
                System.out.println("catalog " + conn.getCatalog());


                System.out.println("-----------client info---------------");
                Properties properties = conn.getClientInfo();
                properties.forEach((key,value)->{
                    System.out.println(key + ":" + value);
                });
                System.out.println("-------------------------------------");

                System.out.println("---------------typeMap---------------");
                Map<String,Class<?>> typeMap = conn.getTypeMap();
                typeMap.forEach((s,c)->{
                    System.out.println(s + " " + c);
                });
                System.out.println("-------------------------------------");

            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //ResultSetMetaData 描述结果的元数据
    private static void printResultSet(ResultSet rs) throws SQLException {
        //rs.getRow();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder head = new StringBuilder("No.    ");
        //table head
        for (int i = 1; i < columnCount; i++) {
            head.append(metaData.getColumnName(i) + "    ");
        }
        LOGGER.info(head.toString());

        //table data
        while (rs.next()) {
            StringBuilder data = new StringBuilder(rs.getRow() + " : ");
            for (int i = 1; i <= columnCount; i++) {
                data.append(rs.getObject(i).toString() + "    ");
            }
            LOGGER.info(data.toString());
        }
    }
}
