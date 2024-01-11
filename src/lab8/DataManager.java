package lab8;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataManager {

    private final List<Object> processors; // Список для хранения объектов-обработчиков данных
    private List<Movie> movies;
    private final ExecutorService executor; // Пул потоков для многопоточной обработки

    public DataManager() {
        processors = new ArrayList<>();
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void registerDataProcessor(Object processor) { // Регистрация обработчика данных
        processors.add(processor);
    }

    public void loadData(String source) {// Загрузка данных из исходного файла, с помощью нашего CsvReader
        movies = CsvReader.readMoviesFromCsv(source);
    }

    public void processData(String filterType, String filterValue) { // Обработка данных с использованием нашего фильтра
        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                // Проверка наличия аннотации @DataProcessor и соответствия имени метода типу фильтра
                if (method.isAnnotationPresent(DataProcessor.class) && method.getName().equals(filterType)) {
                    try {
                        //Мы вызываем нужный нам метод, помеченный нашей аннотацией и фильтруем с его помощью список
                        movies = (List<Movie>) method.invoke(processor, movies, filterValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // Сохранение обработанных данных в новый файл
    public void saveData(String destination) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            writer.write("Poster_Link,Series_Title,Released_Year,Certificate,Runtime,Genre,IMDB_Rating,Overview,Meta_score,Director,Star1,Star2,Star3,Star4,No_of_Votes,Gross");
            writer.newLine();

            for (Movie movie : movies) {
                writer.write(formatMovieForCsv(movie));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatMovieForCsv(Movie movie) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%d",
                movie.getPosterLink(),
                movie.getSeriesTitle(),
                movie.getReleasedYear(),
                movie.getCertificate(),
                movie.getRuntime(),
                movie.getGenre(),
                movie.getImdbRating(),
                movie.getOverview(),
                movie.getMetaScore(),
                movie.getDirector(),
                movie.getStars().get(0),
                movie.getStars().get(1),
                movie.getStars().get(2),
                movie.getStars().get(3),
                movie.getNoOfVotes(),
                movie.getGross());
    }


    private String cleanString(String input) {
        return input.replaceAll(",", "").replace("\n", "");
    }



    public void shutdown() {
        executor.shutdown();
    }
}

