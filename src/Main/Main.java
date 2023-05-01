package Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<extFilm.actedFilms> ActSeznamFilmu = new ArrayList<>();
        ArrayList<extFilm.AnimatedFilm> AniseznamFilmu = new ArrayList<>();



        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:movies.db");
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ActFilms (title VARCHAR(100), director VARCHAR(100), year INT NOT NULL, actors VARCHAR(4000), rating FLOAT NOT NULL)");
            statement.execute("CREATE TABLE IF NOT EXISTS AniFilms (title VARCHAR(100), director VARCHAR(100), year INT NOT NULL, animators VARCHAR(4000), rating FLOAT NOT NULL, age INT NOT NULL)");

            ResultSet Ars = statement.executeQuery("SELECT * FROM ActFilms");
            while (Ars.next()) {
                String title = Ars.getString("title");
                String director = Ars.getString("director");
                int year = Ars.getInt("year");
                double rating = Ars.getDouble("rating");
                ArrayList<String> seznamHercu = new ArrayList<>();
                String[] actorsArray = Ars.getString("actors").split(", ");
                for (String herec : actorsArray) {
                    seznamHercu.add(herec.trim());
                }

                extFilm.actedFilms film = new extFilm.actedFilms(title, director, year, seznamHercu, rating);
                ActSeznamFilmu.add(film);
            }

            ResultSet rs = statement.executeQuery("SELECT * FROM AniFilms");
            while (Ars.next()) {
                String title = rs.getString("title");
                String director = rs.getString("director");
                int year = rs.getInt("year");
                ArrayList<String> seznamHercu = new ArrayList<>();
                String[] actorsArray = rs.getString("animators").split(", ");
                for (String herec : actorsArray) {
                    seznamHercu.add(herec.trim());
                }
                double rating = rs.getDouble("rating");
                int age = rs.getInt("age");

                extFilm.AnimatedFilm film = new extFilm.AnimatedFilm(title, director, year, seznamHercu, rating, age);
                AniseznamFilmu.add(film);
            }

            con.close();
            System.out.println("Data byla úspěšně načtena z databáze.");


        } catch (SQLException e) {
            System.out.println("Nastala chyba při načítání dat z databáze.");
            e.printStackTrace();
        }



        Scanner sc = new Scanner(System.in);

        int choice;
        boolean run = true;

        while (run) {

            System.out.println("Choose action");
            System.out.println("1 .. Acted films");
            System.out.println("2 .. Animated films");
            System.out.println("3 .. Close application");

            choice = InputConsole.ReaderFromInput(sc);

            switch (choice) {
                case 1:
                    ActedFilms FilmAct = new ActedFilms();
                    FilmAct.Cross(ActSeznamFilmu);
                    break;
                case 2:

                    AnimatedFilms FilmAni = new AnimatedFilms();
                    FilmAni.Cross(AniseznamFilmu);
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

        }
        DBmanager.save(ActSeznamFilmu, AniseznamFilmu);
    }
}