package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.learnjava.util.LoggerUtil.log;

public class MoviesClient {
    private final WebClient webClient;

    public MoviesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(Long movieInfoId) {
        MovieInfo movieInfo = invokeMovieInfoService(movieInfoId);
        List<Review> reviews = invokeReviewService(movieInfoId);
        return new Movie(movieInfo, reviews);
    }

    public List<Movie> retrieveMovies(List<Long> movieInfoIds) {
        return movieInfoIds
                .stream()
                .map(this::retrieveMovie)
                .collect(Collectors.toList());
    }

    public List<Movie> retrieveMoviesAsync(List<Long> movieInfoIds) {
        List<CompletableFuture<Movie>> futures = movieInfoIds
                .stream()
                .map(this::retrieveMovieAsync)
                .collect(Collectors.toList());

        return futures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<Movie> retrieveMoviesAsyncAllOf(List<Long> movieInfoIds) {
        List<CompletableFuture<Movie>> futures = movieInfoIds
                .stream()
                .map(this::retrieveMovieAsync)
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allOf
                .thenApply(v -> futures
                        .stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
        .join();
    }

    public CompletableFuture<Movie> retrieveMovieAsync(Long movieInfoId) {
        CompletableFuture<MovieInfo> movieInfoCompletableFuture = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));
        CompletableFuture<List<Review>> listCompletableFuture = CompletableFuture.supplyAsync(() -> invokeReviewService(movieInfoId));

        return movieInfoCompletableFuture
                .thenCombine(listCompletableFuture, (movieInfo, reviews) -> new Movie(movieInfo, reviews))
                .exceptionally(e -> {
                    log("E" + e.getMessage());
                    return null;
                });
    }

    private MovieInfo invokeMovieInfoService(Long movieInfoId) {
        String moviesInfoUlrPath = "/v1/movie_infos/{movieInfoId}";
        return webClient
                .get()
                .uri(moviesInfoUlrPath, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

    private List<Review> invokeReviewService(Long movieInfoId) {
        String reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId", movieInfoId)
                .buildAndExpand()
                .toString();

        return webClient
                .get()
                .uri(reviewUri)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
