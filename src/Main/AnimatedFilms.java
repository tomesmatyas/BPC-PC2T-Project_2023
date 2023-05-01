package Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AnimatedFilms {
    public void Cross(ArrayList<extFilm.AnimatedFilm> seznamFilmu) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean run = true;
        while (run) {
            System.out.println("---Animated films---");
            System.out.println("Choose action:");
            System.out.println("1 .. Add film");
            System.out.println("2 .. Add film from/to file");
            System.out.println("3 .. Edit film");
            System.out.println("4 .. Search a film");
            System.out.println("5 .. Return to back");
            choice = InputConsole.ReaderFromInput(sc);
            switch (choice) {
                case 1 -> {
                    int brake = 0;
                    sc.nextLine();
                    System.out.println("Zadejte název hraného filmu:");
                    String nazevAnimatedFilmu = sc.nextLine();
                    for (extFilm.AnimatedFilm film : seznamFilmu) {
                        if (film.getName().equalsIgnoreCase(nazevAnimatedFilmu)) {
                            System.out.println("Již tento film existuje...");
                            brake = 1;
                            break;
                        }
                    }
                    if (brake != 1) {
                        System.out.println("Zadejte jméno režiséra:");
                        String reziserAnimatedFilmu = sc.nextLine();
                        System.out.println("Zadejte rok vydání:");
                        int rokVydaniAnimatedFilmu = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Zadejte seznam herců (oddělené čárkou):");
                        String seznamAnimatoruString = sc.nextLine();
                        ArrayList<String> seznamAnimatoru = new ArrayList<>();
                        if (!seznamAnimatoruString.isEmpty()) {
                            String[] herci = seznamAnimatoruString.split(", ");
                            for (String herec : herci) {
                                seznamAnimatoru.add(herec.trim());
                            }
                        }

                        double rateFilmu = 0;
                        int rating = 1;
                        do {
                            System.out.println("Zadejte hodnocení filmu (1-10):");

                            if (sc.hasNextDouble()) {
                                rateFilmu = sc.nextDouble();

                                if (rateFilmu >= 1 && rateFilmu <= 10) {
                                    rating = 0;
                                } else {
                                    System.out.println("Hodnocení musí být v rozmezí 1-10.");
                                }
                            } else {
                                System.out.println("Neplatný vstup, zadejte prosím číslo.");
                                sc.next();
                            }

                        } while (rating == 1);
                        System.out.println("Zadejte doporučený věk filmu:");
                        sc.next();
                        int doporucenyVekFilmu = InputConsole.ReaderFromInput(sc);
                        extFilm.AnimatedFilm AnimatedFilms = new extFilm.AnimatedFilm(nazevAnimatedFilmu, reziserAnimatedFilmu, rokVydaniAnimatedFilmu, seznamAnimatoru, rateFilmu, doporucenyVekFilmu);
                        seznamFilmu.add(AnimatedFilms);
                        System.out.println("Animovaný film byl úspěšně přidán.");
                    }
                }
                case 2 -> {
                    System.out.println("Choose action:");
                    System.out.println("1 .. Add film from file");
                    System.out.println("2 .. Add film to file");
                    int choiceSave = InputConsole.ReaderFromInput(sc);
                    switch (choiceSave) {
                        case 1 -> {
                            System.out.println("Zadejte název filmu, který chcete načíst:");
                            sc.nextLine();
                            String filmNameFromFile = sc.nextLine();
                            try {
                                FileReader reader = new FileReader(filmNameFromFile + ".txt");
                                Scanner fileScanner = new Scanner(reader);
                                String name = fileScanner.nextLine();
                                String director = fileScanner.nextLine();
                                int releaseYear = Integer.parseInt(fileScanner.nextLine());
                                ArrayList<String> seznamHercuFromFile = new ArrayList<>();
                                String[] herci = fileScanner.nextLine().split(", ");
                                for (String herec : herci) {
                                    seznamHercuFromFile.add(herec.trim());
                                }
                                double rate = Double.parseDouble(fileScanner.nextLine());
                                int ageYear = Integer.parseInt(fileScanner.nextLine());
                                extFilm.AnimatedFilm loadedFilm = new extFilm.AnimatedFilm(name, director, releaseYear, seznamHercuFromFile, rate, ageYear);
                                seznamFilmu.add(loadedFilm);
                                System.out.println("Nalezený film: " + filmNameFromFile);
                                reader.close();
                            } catch (IOException e) {
                                System.out.println("Při načtení ze souboru došlo k chybě.");
                            }
                        }
                        case 2 -> {
                            System.out.println("Zadejte název filmu, který chcete uložit:");
                            sc.nextLine();
                            String filmNameToSave = sc.nextLine();
                            extFilm.AnimatedFilm filmToSave = null;
                            for (extFilm.AnimatedFilm film : seznamFilmu) {
                                if (film.getName().equals(filmNameToSave)) {
                                    filmToSave = film;
                                    break;
                                } else {
                                    System.out.println("Film s názvem " + filmNameToSave + " nebyl nalezen.");
                                }
                            }
                            if (filmToSave != null) {
                                try {
                                    FileWriter writer = new FileWriter(filmNameToSave + ".txt");
                                    writer.write(filmToSave.getName() + "\n");
                                    writer.write(filmToSave.getDirector() + "\n");
                                    writer.write(filmToSave.getPublishedYear() + "\n");
                                    writer.write(String.join(", ", filmToSave.getSeznamAnimatoru()) + "\n");
                                    writer.write(filmToSave.getHodnoceni() + "\n");
                                    writer.write(filmToSave.getDoporucenyVek() + "\n");
                                    writer.close();
                                    System.out.println("Film byl úspěšně uložen do souboru.");
                                } catch (IOException e) {
                                    System.out.println("Při zápisu do souboru došlo k chybě.");
                                }
                            } else {
                                System.out.println("Film nebyl nalezen.");
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Zadejte název filmu, který chcete upravit:");
                    sc.nextLine();
                    String filmNameToEdit = sc.nextLine();
                    extFilm.AnimatedFilm filmToUpdate = null;
                    for (extFilm.AnimatedFilm film : seznamFilmu) {
                        if (film.getName().equalsIgnoreCase(filmNameToEdit)) {
                            filmToUpdate = film;

                        } else {
                            System.out.println("Film s názvem " + filmNameToEdit + " nebyl nalezen.");
                            break;
                        }
                    }
                    if (filmToUpdate != null) {
                        System.out.println("Vyberte, co chcete upravit:");
                        System.out.println("1 - Název");
                        System.out.println("2 - Režiséra");
                        System.out.println("3 - Rok vydání");
                        System.out.println("4 - Seznam herců/animátorů");
                        System.out.println("5 - Hodnocení");
                        System.out.println("6 - Doporučený věk");
                        System.out.println("7 - smazat");
                        System.out.println("8 - zpátky");
                        int volba = InputConsole.ReaderFromInput(sc);

                        switch (volba) {
                            case 1:
                                System.out.println("Zadejte nový název:");
                                sc.nextLine();
                                String novyNazev = sc.nextLine();
                                filmToUpdate.setName(novyNazev);
                                System.out.println("Název byl úspěšně změněn.");
                                break;
                            case 2:
                                System.out.println("Zadejte nového režiséra:");
                                sc.nextLine();
                                String novyReziser = sc.nextLine();
                                filmToUpdate.setDirector(novyReziser);
                                System.out.println("Režisér byl úspěšně změněn.");
                                break;
                            case 3:
                                System.out.println("Zadejte nový rok vydání:");
                                sc.nextLine();
                                int novyRokVydani = sc.nextInt();
                                filmToUpdate.setPublishedYear(novyRokVydani);
                                System.out.println("Rok vydání byl úspěšně změněn.");
                                break;
                            case 4:
                                System.out.println("Zadejte nový seznam herců/animátorů, oddělený čárkou:");
                                sc.nextLine();
                                String novySeznam = sc.nextLine();
                                String[] novySeznamHercu = novySeznam.split(", ");
                                filmToUpdate.setSeznamAnimatoru(Arrays.asList(novySeznamHercu));
                                System.out.println("Seznam herců/animátorů byl úspěšně změněn.");
                                break;
                            case 5:
                                double rateFilmu = 0;
                                int rating = 1;

                                do {
                                    System.out.println("Zadejte nové hodnocení filmu (1-10):");
                                    if (sc.hasNextDouble()) {
                                        rateFilmu = sc.nextDouble();
                                        filmToUpdate.setHodnoceni(rateFilmu);

                                        if (rateFilmu >= 1 && rateFilmu <= 10) {
                                            rating = 0;
                                        } else {
                                            System.out.println("Hodnocení musí být v rozmezí 1-10.");
                                        }
                                    } else {
                                        System.out.println("Neplatný vstup, zadejte prosím číslo.");
                                        sc.next();
                                    }

                                } while (rating == 1);

                                int novyHodnoceni = sc.nextInt();
                                filmToUpdate.setHodnoceni(novyHodnoceni);
                                System.out.println("Rok vydání byl úspěšně změněn.");
                                break;
                            case 6:
                                System.out.println("Zadejte nový doporučený věk filmu:");
                                sc.nextLine();
                                int novyDoporucení = sc.nextInt();
                                filmToUpdate.setDoporucenyVek(novyDoporucení);
                                System.out.println("Rok vydání byl úspěšně změněn.");
                                break;
                            case 7:
                                for (int i = 0; i < seznamFilmu.size(); i++) {
                                    extFilm.AnimatedFilm filmDelete = seznamFilmu.get(i);
                                    if (filmDelete.getName().equals(filmNameToEdit)) {
                                        seznamFilmu.remove(i);
                                        System.out.println("Film byl úspěšně smazán.");
                                        break;
                                    }
                                }
                                break;
                            case 8:
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + volba);
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Choose action:");
                    System.out.println("1 .. vypsat všechny Animované filmy");
                    System.out.println("2 .. vypsat film podle názvu");
                    System.out.println("3 .. vypsat filmy podle jména animátora");
                    System.out.println("4 .. vypsat filmy ve kterých animatoři pracovali na více než 1 film");
                    System.out.println("5 .. Zpátky");
                    sc.nextLine();
                    int choiceSrc = InputConsole.ReaderFromInput(sc);

                    switch (choiceSrc) {
                        case 1:
                            System.out.println("Seznam filmů:");
                            sc.nextLine();
                            for (extFilm.AnimatedFilm film : seznamFilmu) {
                                film.vypisInformace();
                                System.out.println("-------------------------------");
                            }
                            break;
                        case 2:
                            System.out.println("Vyhledej film podle názvu:");
                            sc.nextLine();
                            String nameSrcName = sc.nextLine();

                            System.out.println("-------------------------------");
                            for (extFilm.AnimatedFilm film : seznamFilmu) {
                                if (film.getName().equalsIgnoreCase(nameSrcName)) {
                                    film.vypisInformace();
                                    System.out.println("-------------------------------");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Napiš jméno animatora:");
                            sc.nextLine();
                            String nameSrcAct = sc.nextLine();
                            System.out.println("Seznam filmů s " + nameSrcAct + ":");
                            for (extFilm.AnimatedFilm film : seznamFilmu) {
                                if (film.getSeznamAnimatoru().contains(nameSrcAct)) {
                                    System.out.println(film.getName());
                                }
                            }

                            System.out.println("-------------------------------");
                            break;
                        case 4:
                            HashMap<String, ArrayList<extFilm.AnimatedFilm>> AniToFilms = new HashMap<>();
                            for (extFilm.AnimatedFilm film : seznamFilmu) {
                                for (String castMember : film.getSeznamAnimatoru()) {
                                    if (AniToFilms.containsKey(castMember)) {
                                        AniToFilms.get(castMember).add(film);
                                    } else {
                                        ArrayList<extFilm.AnimatedFilm> newFilmList = new ArrayList<>();
                                        newFilmList.add(film);
                                        AniToFilms.put(castMember, newFilmList);
                                    }
                                }
                            }
                            System.out.println("Seznam animátorů, kteří se podíleli na více než jednom filmu:");
                            for (String Act : AniToFilms.keySet()) {
                                ArrayList<extFilm.AnimatedFilm> filmsForCastMember = AniToFilms.get(Act);
                                if (filmsForCastMember.size() > 1) {
                                    System.out.println("- " + Act + ":");
                                    for (extFilm.AnimatedFilm film : filmsForCastMember) {
                                        System.out.println("  - " + film.getName());
                                    }
                                }
                            }
                            break;
                        case 5:
                            break;
                    }
                }
                case 5 -> run = false;
            }
        }
    }
}
