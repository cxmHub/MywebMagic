package com.cxm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LaGouDao {
    private Connection connection = null;
    private PreparedStatement PPstatement = null;

    public LaGouDao() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/qiushi";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(QiuShi qiushi) {
        try {
            String sql = "INSERT INTO joke(u_name, content, ilike) value(?,?,?)";
            PPstatement = connection.prepareStatement(sql);
            PPstatement.setString(1, qiushi.getU_name());
            PPstatement.setString(2, qiushi.getContent());
            PPstatement.setInt(3, qiushi.getIlike());
            PPstatement.execute();
            System.out.println("插入成功了");
        } catch (SQLException e) {
            System.out.println("插入失败了");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (PPstatement != null) {
                    PPstatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
