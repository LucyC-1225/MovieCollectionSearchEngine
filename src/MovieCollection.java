import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie object to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }
    private void sortResults2(ArrayList<String> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            String temp = listToSort.get(j);

            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(listToSort.get(possibleIndex - 1)) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }
    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter a cast member: ");
        String search = scanner.nextLine();
        search = search.toLowerCase();
        ArrayList<String> allCastMembers = new ArrayList<String>();

        for (int i = 0; i < movies.size(); i++){
            String[] membersInMovie = movies.get(i).getCast().split("\\|");
            for (int j = 0; j < membersInMovie.length; j++){
                boolean exist = false;
                for (int k = 0; k < allCastMembers.size(); k++) {
                    if (allCastMembers.get(k).equalsIgnoreCase(membersInMovie[j])){
                        exist = true;
                    }
                }
                if (!exist){
                    allCastMembers.add(membersInMovie[j]);
                }
            }
        }
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < allCastMembers.size(); i++){
            if (allCastMembers.get(i).toLowerCase().indexOf(search.toLowerCase()) != -1){
                result.add(allCastMembers.get(i));
            }
        }
        for (int i = 0; i < result.size(); i++)
        {
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + result.get(i));
        }
        System.out.println("Which cast member would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        String castMember = result.get(choice - 1);
        castMember.toLowerCase();
        ArrayList<Movie> movieResults = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++){
            String temp = movies.get(i).getCast();
            if (temp.indexOf(castMember) != -1){
                movieResults.add(movies.get(i));
            }
        }
        sortResults(movieResults);
        for (int i = 0; i < movieResults.size(); i++){
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + movieResults.get(i).getTitle());
        }
        System.out.print("Which movie do you want to know more about? ");
        int choice2 = scanner.nextInt();
        displayMovieInfo(movieResults.get(choice2 - 1));

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String search = scanner.nextLine();
        ArrayList<Movie> result = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++){
            if (movies.get(i).getKeywords().toLowerCase().indexOf(search.toLowerCase()) != -1){
                result.add(movies.get(i));
            }
        }
        sortResults(result);
        for (int i = 0; i < result.size(); i++)
        {
            String title = result.get(i).getTitle();

            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = result.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String> allGenres = new ArrayList<String>();

        for (int i = 0; i < movies.size(); i++){
            String[] genreInMovie = movies.get(i).getGenres().split("\\|");
            for (int j = 0; j < genreInMovie.length; j++){
                boolean exist = false;
                for (int k = 0; k < allGenres.size(); k++) {
                    if (allGenres.get(k).equalsIgnoreCase(genreInMovie[j])){
                        exist = true;
                    }
                }
                if (!exist){
                    allGenres.add(genreInMovie[j]);
                }
            }
        }
        sortResults2(allGenres);
        for (int i = 0; i < allGenres.size(); i++){
            System.out.println("" + (i + 1) + ". " + allGenres.get(i));
        }
        System.out.print("Choose a genre: ");
        int genreChoice = scanner.nextInt();
        ArrayList<Movie> movieResults = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++){
            if (movies.get(i).getGenres().toLowerCase().indexOf(allGenres.get(genreChoice - 1).toLowerCase()) != -1){
                movieResults.add(movies.get(i));
            }
        }
        sortResults(movieResults);
        for (int i = 0; i < movieResults.size(); i++){
            System.out.println("" + (i + 1) + ". " + movieResults.get(i).getTitle());
        }

        System.out.print("Choose a title: ");
        int titleChoice = scanner.nextInt();
        displayMovieInfo(movieResults.get(titleChoice - 1));

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {

    }

    private void listHighestRevenue()
    {

    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}
