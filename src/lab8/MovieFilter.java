package lab8;

import java.util.List;
import java.util.stream.Collectors;

public class MovieFilter {

    @DataProcessor
    public List<Movie> filterByActor(List<Movie> movies, String actor) {
        return movies.stream() // Преобразование списка фильмов в поток (stream)
                .filter(movie -> movie.getStars().contains(actor)) //оставляем только фильмы с указанным актером
                .collect(Collectors.toList()); // Сбор отфильтрованных элементов обратно в список
    }

    @DataProcessor
    public List<Movie> filterByGenre(List<Movie> movies, String genre) {
        return movies.stream()
                .filter(movie -> movie.getGenre().contains(genre))
                .collect(Collectors.toList());
    }
}



