package com.cxm.dao;

import com.cxm.pojo.Movie;

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
            String url = "jdbc:mysql://47.94.174.237/movie?useUnicode=true&characterEncoding=utf8";
            String username = "root";
            String password = "Chenxinmao0.";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Movie movie){
        try {
            String sql = "insert into movie(m_name, comment, score) value(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getComment());
            preparedStatement.setDouble(3,movie.getScore());
            preparedStatement.execute();
            System.out.println("插入成功<"+movie.getName()+"::"+movie.getComment());
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
