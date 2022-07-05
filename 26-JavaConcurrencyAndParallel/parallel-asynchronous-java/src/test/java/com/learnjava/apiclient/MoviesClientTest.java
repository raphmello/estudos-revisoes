package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    public static final long MOVIE_INFO_ID = 1L;
    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();
    private MoviesClient moviesClient = new MoviesClient(webClient);

    @RepeatedTest(10)
    @Disabled
    void retrieveMovie() {
        startTimer();
        Movie movie = moviesClient.retrieveMovie(MOVIE_INFO_ID);
        assertNotNull(movie.getMovieInfo());
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
        timeTaken();
        stopWatchReset();
    }

    @RepeatedTest(10)
    void retrieveMovies() {
        startTimer();
        List<Movie> movies = moviesClient.retrieveMovies(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L));
        assertNotNull(movies);
        assertEquals(7, movies.size());
        timeTaken();
        stopWatchReset();
    }

    @RepeatedTest(10)
    @Disabled
    void retrieveMovieAsync() {
        startTimer();
        Movie movie = moviesClient.retrieveMovieAsync(MOVIE_INFO_ID).join();
        assertNotNull(movie.getMovieInfo());
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
        timeTaken();
        stopWatchReset();
    }

    @RepeatedTest(10)
    void retrieveMoviesAsync() {
        startTimer();
        List<Movie> movies = moviesClient.retrieveMoviesAsync(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L));
        assertNotNull(movies);
        assertEquals(7, movies.size());
        timeTaken();
        stopWatchReset();
    }

    @RepeatedTest(10)
    void retrieveMoviesAsyncAllOf() {
        startTimer();
        List<Movie> movies = moviesClient.retrieveMoviesAsyncAllOf(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L));
        assertNotNull(movies);
        assertEquals(7, movies.size());
        timeTaken();
        stopWatchReset();
    }
}