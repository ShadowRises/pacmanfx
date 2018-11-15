package PacMan.Model.Parser;

import PacMan.Model.*;


import java.io.*;
import java.util.ArrayList;

public class Parser {


    /**
     * Inner class to save the position of the Entite when Parser parse the file
     */
    private class EntitePosition {

        private char type;
        private int line;
        private int column;

        private EntitePosition(char type, int line, int column) {
            this.type = type;
            this.line = line;
            this.column = column;
        }

    }


    private BufferedReader br;
    private InputStreamReader isr;
    private ArrayList<EntitePosition> entitePositions;


    public Parser() throws FileNotFoundException {
        this.entitePositions = new ArrayList<>();
        this.isr = new InputStreamReader(System.class.getResourceAsStream("/plateaux/defaultPlateau.txt"));
        this.br = new BufferedReader(this.isr);
    }

    public Parser(String s) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(s);

        this.br = new BufferedReader(new InputStreamReader(fis));
    }


    /**
     *  Function which reads the file to create the array of Case for the Plateau
     * @return Case[][] the array of Case which represent the Plateau
     * @throws IOException
     */
    public Case[][] createPlateau() throws IOException {
        Case[][] plateau = new Case[Jeu.LONGUEUR][Jeu.LARGEUR];

        int ligne = 0;
        int colonne = 0;

        while(!this.br.ready()) {}

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

                    case 'J':
                        this.entitePositions.add(new EntitePosition('J', ligne, colonne));
                        plateau[ligne][colonne] = new Couloir(false,false);
                        break;

                    case 'F':
                        this.entitePositions.add(new EntitePosition('F', ligne, colonne));
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

        this.br.close();
        this.isr.close();
        return plateau;
    }


    /**
     *  Function which creates the array which stores the Entites position
     * @return Entite[][] the array which stores the Entites position
     */
    public Entite[][] createEntite() {
        Entite[][] tabEntite = new Entite[Jeu.LONGUEUR][Jeu.LARGEUR];

        for(EntitePosition ep : this.entitePositions) {
            if(ep.type == 'J')
                tabEntite[ep.line][ep.column] = new Pacman();
            else
                tabEntite[ep.line][ep.column] = new Fantome();
        }

        return tabEntite;
    }
}
