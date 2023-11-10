package com.customer.movie.booking.repository;

import com.customer.movie.booking.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {
    @Query(value="select seat_available from Movie where movie_name=:picture", nativeQuery=true)
    int numberOfSeats(@Param("picture") String name);
    @Query(value="select * from Movie", nativeQuery=true)
    List<Movie> getMovies();

    @Modifying
    @Query(value="update movie set seat_available=:seats where movie_name=:picture", nativeQuery=true)
    void updateSeats(@Param("seats") int seats,@Param("picture") String name);
}
