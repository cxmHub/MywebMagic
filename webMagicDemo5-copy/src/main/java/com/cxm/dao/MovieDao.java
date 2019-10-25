package com.cxm.dao;

import com.cxm.pojo.World;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public MovieDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/world";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(World world){


        try {
            String sql = "insert into world(m_name, comment, score) value(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, world.getId());
            preparedStatement.setString(2, world.getName());
            preparedStatement.setString(3,world.getContent());
            preparedStatement.execute();
            System.out.println("插入成功");
        } catch (SQLException e) {
            System.out.println("插入失败");
            e.printStackTrace();
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
