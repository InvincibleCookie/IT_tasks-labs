package lab8;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    public static List<Movie> readMoviesFromCsv(String fileName) {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                movies.add(parseMovie(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    private static Movie parseMovie(String[] data) {
        Movie movie = new Movie();
        movie.setPosterLink(data[0]);
        movie.setSeriesTitle(data[1]);
        movie.setReleasedYear(data[2]);
        movie.setCertificate(data[3]);
        movie.setRuntime(data[4]);
        movie.setGenre(data[5]);
        movie.setImdbRating(Double.parseDouble(data[6]));
        movie.setOverview(data[7]);
        if (!data[8].isEmpty()) {
            movie.setMetaScore(Integer.parseInt(data[8]));
        } else {
            movie.setMetaScore(0);
        }
        movie.setDirector(data[9]);
        movie.setStars(Arrays.asList(data[10], data[11], data[12], data[13]));
        if (!data[14].isEmpty()) {
            movie.setNoOfVotes(Integer.parseInt(data[14]));
        } else {
            movie.setNoOfVotes(0);
        }
        if (!data[15].isEmpty()) {
            movie.setGross(Long.parseLong(data[15].replaceAll("[^\\d]", "")));
        } else {
            movie.setGross(0);
        }
        return movie;
    }
}
