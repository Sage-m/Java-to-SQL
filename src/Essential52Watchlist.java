import java.sql.*;
import java.util.Scanner; 

public class Essential52Watchlist {
    Scanner userIn = new Scanner(System.in);
    String user;
    String orderBy = "movie.id";
    Boolean orderByRating = false;
    String userResponse;

    private void login(){
        System.out.println("Welcome to Essential 52 Watchlist: The Movie Night Experience");
        
        //Prompts for username or creates a new user
        while(true){
            System.out.printf("Please Enter Username: ");
            user = userIn.nextLine();
            if(verifyElement("select * from users where username = ?", user) == 0){
                //change to it asks if you want to make that a username a new account
                System.out.println("That is not a valid username, would you like to add it to the database and log in?\n");
                if(userYN() == 1){
                    updateTable("INSERT INTO users (username) VALUES ( ? )", user);
                    break;
                }
            } else{
                break;
            }
        }
        //on success
        home();
    }

    //Core of the applicaiton, has authentication of the user as well as the main menu
    private void home(){

        System.out.println("Welcome " + user + " What would you like to do today?");
        //main menu for the application
        while(true){
            System.out.println("1: See movie list");
            System.out.println("2: Leave/Change a review");
            System.out.println("3: See a user's reviews");
            System.out.println("4: View all users");
            System.out.println("5 Change how results are sorted");
            System.out.println("Q: Quit");

            //Change to a switch
            System.out.printf("Please Enter Menu Selection: ");
            userResponse = userIn.nextLine();
            switch (userResponse) {
                case "1":
                    printMovie("SELECT * FROM MOVIE");
                    break;
                case "2":
                    review();
                    break;
                case "3":
                    printAllReviews();
                    break;
                case "4":
                    printUsers();
                    break;
                case "5":
                    updateSort();
                    break;
                case "q":
                    userIn.close();
                    return;
                case "Q":
                    userIn.close();
                    return;
                default:
                    System.out.println("Please Enter a valid selection");
            }
        }
    }

    private void updateSort(){
        System.out.println("Please enter one of the following for movies to be sorted by:");
        System.out.println("1: Id (Watch Order)\n2: Title\n3: Release Date\n4: Genre\n5: Box Office (US and International Combined)");
        userResponse = userIn.nextLine();
        Boolean goodInput = false;
        while(!goodInput){
            switch (userResponse) {
                case "1":
                    orderBy = "movie.id";
                    goodInput = true;
                    break;
                case "2":
                    orderBy = "movie.title";
                    goodInput = true;
                    break;
                case "3":
                    orderBy = "movie.released";
                    goodInput = true;
                    break;
                case "4":
                    orderBy = "movie.genre";
                    goodInput = true;
                    break;
                case "5":
                    orderBy = "movie.earnings";
                    goodInput = true;
                    break;
                default:
                    System.out.println("Incorrect input, please try again");
                    break;
            }
        }
        System.out.println("Would you like to movie in order of rating when viewing multiple reviews?");
        if(userYN() == 0){
            orderByRating = false;
        } else {
            orderByRating = true;
        }
    }

    //Verifies if an entry exists for a given query
    private int verifyElement(String query, String... elements){
        int amtFound = 0;
        //Connect to database
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javatosql",
                        "root", "m^XAu7Z#ZwZ@Zx")) {
            
            //Create statement using user input if needed
            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 1;
            for(String elem : elements){
                stmt.setString(i, elem);
                i++;
            }
            //Run command
            ResultSet rs = stmt.executeQuery();

            //Count how many lines are returned
            while(rs.next()){
                amtFound ++;
            }
            return amtFound;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return 0;
        }
    }

    //This function takes a query and updates the table without printing or returning anything
    private void updateTable(String query, String... elements){
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javatosql",
                        "root", "m^XAu7Z#ZwZ@Zx")) {

            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 1;
            for(String elem : elements){
                stmt.setString(i, elem);
                i++;
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return;
        }
    }

    private int getResult(String query, String... elements){

        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javatosql",
                        "root", "m^XAu7Z#ZwZ@Zx")) {
            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 1;
            for(String elem : elements){
                stmt.setString(i, elem);
                i++;
            }
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
                return -1;
            }
    }
    //Prints the table for the given sql command, used for retreiving and displaying informaiton to the user
    //Must be used without user variables, only prints pre-approved statements.
    private void printMovie(String query, String... elements){
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javatosql",
                        "root", "m^XAu7Z#ZwZ@Zx")) {
            
            //Add the sorting functionality
            query += " ORDER BY " + orderBy;
            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 1;
            for(String elem : elements){
                stmt.setString(i, elem);
                i++;
            }
            ResultSet rs = stmt.executeQuery();
            
            System.out.format("%3s %20s %10s %10s %10s", "Id", "Title", "Released", "Genre", "Box Office");
            System.out.print("\n");
            while (rs.next()) { // will traverse through all rows
                System.out.format("%3s %20s %10s %10s %10s", 
                rs.getInt("id"), 
                rs.getString("title"), 
                rs.getDate("released"), 
                rs.getString("genre"),
                rs.getInt("earnings"));
                System.out.print("\n");
            } 
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    //print statement formatted to print for reviews of a user
    private void printReviews(String query, String... elements){
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javatosql",
                        "root", "m^XAu7Z#ZwZ@Zx")) {
            
            //Add the sorting functionality
            if(orderByRating){
                query += " ORDER BY reviews.rating";
            } else{
                query += " ORDER BY " + orderBy;
            }

            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 1;
            for(String elem : elements){
                stmt.setString(i, elem);
                i++;
            }
            ResultSet rs = stmt.executeQuery();
            
            System.out.format("%3s %20s %5s", "Id", "Title", "Stars");
            System.out.print("\n");
            while (rs.next()) { // will traverse through all rows
                System.out.format("%3s %20s %5s", 
                rs.getInt("id"), 
                rs.getString("title"), 
                getStars(rs.getInt("rating")));
                System.out.print("\n");
            } 
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void printUsers(){
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/javatosql",
                        "root", "m^XAu7Z#ZwZ@Zx")) {

            PreparedStatement stmt = conn.prepareStatement("SELECT username from users");
            ResultSet rs = stmt.executeQuery();
            
            System.out.format("%20s %32s %32s","Username", "amount of reveiews (coming soon)", "Average review (coming soon)");
            System.out.print("\n");
            while (rs.next()) { // will traverse through all rows
                System.out.format("%20s", 
                rs.getString("username"));
                System.out.print("\n");
            } 
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void printAllReviews(){
        int userExists = 0;
        userResponse = "";
        while(userExists == 0){
            System.out.println("Please enter the user's reveiews you would like to see, or leave empty to see your reviews");
            userResponse = userIn.nextLine();
            if(userResponse == ""){
                userExists = verifyElement("select * from users where username = ?", user);
            } else {
                userExists = verifyElement("select * from users where username = ?", userResponse);
            }
            if(userExists == 0){
                System.out.println("Please try another username");
            }
        }
        printReviews("SELECT * FROM movie JOIN users JOIN reviews ON movie.id = reviews.id AND users.uid = reviews.uid WHERE users.username = ?", userResponse);
    }

    private String getStars(int rating){
        //make it actually show stars
        return Float.toString((float) rating/2);
    }

    private void review(){
        String toReview;
        int numFound;
        Boolean isId;

        //Enter the menu to review a movie
        while(true){
            System.out.printf("Which movie would you like to review? \nPress enter for the full list or q to exit \nEnter the movie id or partial title to select your movie: ");
            isId = true;
            toReview = userIn.nextLine();
            //Print the list if there is nothing entered
            if(toReview.equalsIgnoreCase("")){
                printMovie("SELECT * FROM MOVIE");
            } else if(toReview.equalsIgnoreCase("q")){
                return;
            } else {
                //Check if the input was a valid id (primary key do no chance of duplicates)
                numFound = verifyElement("SELECT * FROM MOVIE WHERE id = ?", toReview);
                if(numFound == 0){
                    //make it have a wildcard at the end, then see how many results come up
                    toReview += '%';
                    numFound = verifyElement("SELECT * FROM MOVIE WHERE title LIKE ? ", toReview);
                    isId = false;
                }
                if(numFound <= 0){
                    System.out.println("Not found, Please try something else");
                } else if(numFound == 1){
                    if(!isId){
                        toReview = Integer.toString(getResult("SELECT * FROM MOVIE WHERE title LIKE ? ", toReview));
                    }
                    leaveReview(toReview);
                } else if(numFound > 1){
                    //multiple found, print the list then return to previous prompt
                    System.out.println("Multiple entries found, please be more specific between these options:");
                    printMovie("SELECT * FROM MOVIE WHERE title LIKE ?", toReview);
                }
            }
        }
    }

    private int userYN(){
        userResponse = "";
        while(!userResponse.equalsIgnoreCase("y") && !userResponse.equalsIgnoreCase("n")){
            System.out.printf("(y/n): ");
            userResponse = userIn.nextLine();
            if(userResponse.equalsIgnoreCase("n")){
                return 0;
            } else if(userResponse.equalsIgnoreCase("y")){
                return 1;
            } else{
                System.out.println("Invalid input, please try again");
            }
        }
        return -1;
    }

    private void leaveReview(String movie){
        int isReviewed;
        isReviewed = verifyElement("SELECT * FROM reviews JOIN users JOIN movie ON movie.id = reviews.id AND users.uid = reviews.uid WHERE users.username = ? AND movie.id = ?", user, movie);
        if(isReviewed < 0 || isReviewed > 1){
            System.out.println("Something has gone wrong with querying the database");
        } else if(isReviewed == 1){
            System.out.println("Review already exists, are you sure you would like to erase and update it?");
            printReviews("SELECT * FROM reviews JOIN users JOIN movie ON movie.id = reviews.id AND users.uid = reviews.uid WHERE users.username = ? AND movie.id = ?", user, movie);
            if(userYN() == 0){
                return;
            }
        }
        if(isReviewed == 0 || isReviewed == 1){
            String query;
            int rating = 0;
            while(rating < 1 || rating > 10){
                System.out.printf("Enter your rating (1-10): ");
                rating = userIn.nextInt();
                userIn.nextLine();
                if(rating < 1 ||rating > 10){
                    System.out.println("Please Enter a valid rating");
                }
            }
            if(isReviewed == 0){
                query = "INSERT INTO reviews (uid, id, rating) VALUES (" + getResult("SELECT uid FROM USERS WHERE username = ?", user) + ", " + movie + ", " + Integer.toString(rating) + ")";
            } else {
                query = "UPDATE reviews SET rating = " + Integer.toString(rating) + " WHERE rid = " + Integer.toString(getResult("SELECT reviews.rid FROM reviews JOIN users JOIN movie ON movie.id = reviews.id AND users.uid = reviews.uid WHERE users.username = ? AND movie.id = ?", user, movie));
            }
            updateTable(query);
        }
    }

    public static void main(String[] args) throws Exception {
        Essential52Watchlist list = new Essential52Watchlist();
        list.login();
    }
}
