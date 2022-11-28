package com.kh.netflix.dao;

import com.kh.netflix.dto.MoviesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    DataSource dataSource;

    public void insert(MoviesDTO movieDto) throws Exception{
        String sql = "insert into movies values(movies_seq.nextval, ?, ?, sysdate)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, movieDto.getTitle());
            statement.setString(2, movieDto.getGenre());

            statement.executeUpdate();
            con.commit();
        }
    }

    public List<MoviesDTO> selectAll() throws SQLException {
        List<MoviesDTO> movies = new ArrayList<>();

        String sql = "select * from movies";

        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();) {

            while(resultSet.next()){
                MoviesDTO movie = new MoviesDTO();
                movie.setId(resultSet.getInt("id"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setTitle(resultSet.getString("title"));
                movie.setLaunch_date(resultSet.getTimestamp("launch_date"));

                movies.add(movie);
            }

            return movies;
        }
    }

}
