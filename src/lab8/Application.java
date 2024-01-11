package lab8;

public class Application {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.registerDataProcessor(new MovieFilter());

        //Отфильтруем датасет по фильмам, где снимался Райан Гослинг
        dataManager.loadData("C:\\Users\\stav2\\IdeaProjects\\IT_labs\\src\\lab8\\imdb_top_1000.csv");
        dataManager.processData("filterByActor", "Ryan Gosling");
        dataManager.saveData("C:\\Users\\stav2\\IdeaProjects\\IT_labs\\src\\lab8\\movies\\Gosling.csv");

        //Отфильтруем по триллерам
        dataManager.loadData("C:\\Users\\stav2\\IdeaProjects\\IT_labs\\src\\lab8\\movies\\Gosling.csv");
        dataManager.processData("filterByGenre", "Comedy");
        dataManager.saveData("C:\\Users\\stav2\\IdeaProjects\\IT_labs\\src\\lab8\\movies\\Thriller.csv");

        dataManager.shutdown();
    }
}
