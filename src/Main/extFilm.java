package Main;

import java.util.ArrayList;
import java.util.List;

public class extFilm {

    public static class actedFilms extends cmonFilm {
        private List<String> listOfActors;
        private double rate;

        public actedFilms(String name, String director, int publishedYear, List<String> listOfActors, double rate) {
            super(name, director, publishedYear);
            this.listOfActors = listOfActors;
            this.rate = rate;

        }

        public List<String> getListOfActors() {
            return listOfActors;
        }

        public void setListOfActors(List<String> listOfActors) {
            this.listOfActors = listOfActors;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        @Override
        public void vypisInformace() {
            System.out.println("Název: " + getName());
            System.out.println("Režisér: " + getDirector());
            System.out.println("Rok vydání: " + getPublishedYear());
            System.out.println("Seznam herců: " + getListOfActors());
            System.out.println("Hodnocení: " + getRate());
        }
    }


    int AgeRating;


    static class AnimatedFilm extends cmonFilm {
        private List<String> seznamAnimatoru;
        private double hodnoceni;
        private int doporucenyVek;

        public AnimatedFilm(String nazev, String reziser, int rokVydani, List<String> seznamAnimatoru, double hodnoceni, int doporucenyVek) {
            super(nazev, reziser, rokVydani);
            this.seznamAnimatoru = seznamAnimatoru;
            this.hodnoceni = hodnoceni;
            this.doporucenyVek = doporucenyVek;
        }

        public List<String> getSeznamAnimatoru() {

            return seznamAnimatoru;
        }

        public void setSeznamAnimatoru(List<String> seznamAnimatoru) {
            this.seznamAnimatoru = seznamAnimatoru;
        }

        public double getHodnoceni() {

            return hodnoceni;
        }

        public void setHodnoceni(double hodnoceni) {

            this.hodnoceni = hodnoceni;
        }

        public int getDoporucenyVek() {
            return doporucenyVek;
        }

        public void setDoporucenyVek(int doporucenyVek) {
            this.doporucenyVek = doporucenyVek;
        }

        @Override
        public void vypisInformace() {
            System.out.println("Název: " + getName());
            System.out.println("Režisér: " + getDirector());
            System.out.println("Seznam animátorů: " + getSeznamAnimatoru());
            System.out.println("Rok vydání: " + getPublishedYear());
            System.out.println("Doporučený věk diváka: " + getDoporucenyVek() + "+");
            System.out.println("Hodnocení: " + getHodnoceni());
            System.out.println("----------");
        }


    }
}

