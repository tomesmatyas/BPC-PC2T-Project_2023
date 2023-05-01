package Main;

abstract class cmonFilm {
    private String name;
    private String director;
    private int publishedYear;

    public cmonFilm(String name, String director, int publishedYear) {
        this.name = name;
        this.director = director;
        this.publishedYear = publishedYear;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDirector() {

        return director;
    }

    public void setDirector(String director) {

        this.director = director;
    }

    public int getPublishedYear() {

        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {

        this.publishedYear = publishedYear;
    }

    public abstract void vypisInformace();
}

