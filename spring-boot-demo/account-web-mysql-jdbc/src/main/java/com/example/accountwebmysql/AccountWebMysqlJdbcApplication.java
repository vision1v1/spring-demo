package com.example.accountwebmysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
public class AccountWebMysqlJdbcApplication {

    private static Connection conn = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountWebMysqlJdbcApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(AccountWebMysqlJdbcApplication.class, args);
        TestConnection();
        TestSelect();
        TestAutoIncrement01();
        TestAutoIncrement02();
        TestAutoIncrement03();
    }

    //Connection 用于连接的对象
    private static void TestConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.209.131:3306/Account?useSSL=false&user=root&password=Admin@123");
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
