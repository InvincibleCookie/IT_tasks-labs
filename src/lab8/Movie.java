package lab8;

import java.util.List;

public class Movie {
    private String posterLink;
    private String seriesTitle;
    private String releasedYear;
    private String certificate;
    private String runtime;
    private String genre;
    private double imdbRating;
    private String overview;
    private int metaScore;
    private String director;
    private List<String> stars;
    private int noOfVotes;
    private long gross;

    public Movie() {
    }
    // Геттеры и сеттеры
    public String getPosterLink() {
        return posterLink;
    }
    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }
    public String getSeriesTitle() {
        return seriesTitle;
    }
    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }
    public String getReleasedYear() {
        return releasedYear;
    }
    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(int metaScore) {
        this.metaScore = metaScore;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
    }

    public int getNoOfVotes() {
        return noOfVotes;
    }

    public void setNoOfVotes(int noOfVotes) {
        this.noOfVotes = noOfVotes;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

}

