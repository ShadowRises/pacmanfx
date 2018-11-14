package PacMan.Model.Parser;

import PacMan.Model.Case;
import PacMan.Model.Couloir;
import PacMan.Model.Jeu;
import PacMan.Model.Mur;

import java.io.*;

public class Parser {

    private BufferedReader br;

    public Parser() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("/home/shadowrises/Programmation/Java/pacmanfx/src/Ressources/defaultPlateau.txt");

        this.br = new BufferedReader(new InputStreamReader(fis));
    }

    public Parser(String s) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(s);

        this.br = new BufferedReader(new InputStreamReader(fis));
    }

    public Case[][] createPlateau() throws IOException {
        Case[][] plateau = new Case[Jeu.LONGUEUR][Jeu.LARGEUR];

        int ligne = 0;
        int colonne = 0;

        String ligneString = this.br.readLine();
        while(ligneString != null) {
            for(char c : ligneString.toCharArray()) {

                switch(c) {
                    case ' ':
                        plateau[ligne][colonne] = new Case();
                        break;

                    case 'M':
                        plateau[ligne][colonne] = new Mur();
                        break;

                    case 'p':
                        plateau[ligne][colonne] = new Couloir(true, false);
                        break;

                    case 'P':
                        plateau[ligne][colonne] = new Couloir(false, true);
                        break;

                    case 'c':
                        plateau[ligne][colonne] = new Couloir(false,false);
                        break;

                    default:
                        throw new IOException("Caractère non reconnu");
                }

                ligne++;

            }

            ligne = 0;
            colonne++;
            ligneString = this.br.readLine();
        }

        return plateau;
    }
}
