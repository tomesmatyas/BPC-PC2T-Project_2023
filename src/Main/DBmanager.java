package Main;

import com.mysql.cj.conf.ConnectionUrlParser;

import java.sql.*;
import java.util.ArrayList;

public class DBmanager {


    public static void save(ArrayList<extFilm.actedFilms> ActSeznamFilmu, ArrayList<extFilm.AnimatedFilm> AniSeznamFilmu)
            throws SQLException, ClassNotFoundException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:movies.db");
        Statement statement = con.createStatement();

        statement.execute("DROP TABLE IF EXISTS ActFilms");
        statement.execute("CREATE TABLE IF NOT EXISTS ActFilms (title VARCHAR(100), director VARCHAR(100), year INT NOT NULL, actors VARCHAR(4000), rating FLOAT NOT NULL)");
        statement.execute("DROP TABLE IF EXISTS AniFilms");
        statement.execute("CREATE TABLE IF NOT EXISTS AniFilms (title VARCHAR(100), director VARCHAR(100), year INT NOT NULL, animators VARCHAR(4000), rating FLOAT NOT NULL, age INT NOT NULL)");

        for (extFilm.actedFilms film : ActSeznamFilmu) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO ActFilms VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, film.getName());
            ps.setString(2, film.getDirector());
            ps.setInt(3, film.getPublishedYear());
            String actors = String.join(", ", film.getListOfActors());
            ps.setString(4, actors);
            ps.setDouble(5, film.getRate());
            ps.executeUpdate();

        }

        for (extFilm.AnimatedFilm film : AniSeznamFilmu) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO AniFilms VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, film.getName());
            ps.setString(2, film.getDirector());
            ps.setInt(3, film.getPublishedYear());
            String actors = String.join(", ", film.getSeznamAnimatoru());
            ps.setString(4, actors);
            ps.setDouble(5, film.getHodnoceni());
            ps.setDouble(6, film.getDoporucenyVek());
            ps.executeUpdate();

        }
        statement.close();
        con.close();
        System.out.println("Data byla úspěšně uložena do databáze.");

    }

}


