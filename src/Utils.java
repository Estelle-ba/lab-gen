import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Utils {

    public static int widthi;
    public static int heighti;
    public static int entryY;
    public static int exitY;
    public static boolean hasFileToCharge;
    public static boolean bn;
    public static boolean inputVerif = false;
    public static Scanner scanner;

    /**
     * Ask user if it is customizable and launch gen
     */
    public static void customizable(){
        //ConfigQuestionx
        while (!inputVerif){
            System.out.println("Souhaitez-vous configurer le labyrinthe ? (o/n)");
            scanner = new Scanner(System.in);
            String result;
            if(scanner.hasNextLine()){
                result = scanner.nextLine();
                if(result.equals("o")){
                    bn = true;
                    inputVerif = true;

                }else if (result.equals("n")){
                    bn = false;
                    inputVerif = true;
                }else{
                    System.out.println("Veuillez répondre par 'o' ou 'n'...");
                }
            }else{
                System.out.println("Veuillez répondre par 'o' ou 'n'...");
            }
        }
        inputVerif = false;

        if (bn == true){

            verifHeight();
            verifWidth();
            // Crée le labyrinthe avec des dimensions personnalisées et initialise toutes les cases avec des murs
            Main.maze = new String[Main.height][Main.width];
            for (int i = 0; i < Main.height; i++) {
                for (int j = 0; j < Main.width; j++) {
                    Main.maze[i][j] = Main.WALL;
                }
            }
            //EntryY
            while (!inputVerif){
                // Demande à l'utilisateur de spécifier la position d'entrée en haut du labyrinth
                System.out.println("Choisi La Position Entry");
                scanner = new Scanner(System.in);
                if(scanner.hasNextInt()){
                    entryY = scanner.nextInt();
                    inputVerif = true;
                }else{
                    System.out.println("Veuillez répondre par un chiffre");
                }
            }
            inputVerif = false;

            //ExitY
            while (!inputVerif){
                // Demande à l'utilisateur de spécifier la position de sortie en haut du labyrinth
                System.out.println("Choisi La Position exit");
                scanner = new Scanner(System.in);
                if(scanner.hasNextInt()){
                    exitY = scanner.nextInt();
                    inputVerif = true;
                }else{
                    System.out.println("Veuillez répondre par un chiffre");
                }
            }
            inputVerif = false;

            // Positionne l'entrée et la sortie du labyrinthe selon les choix de l'utilisateur
            Main.maze[0][entryY] = Main.ENTRY;
            Main.maze[Main.height -1][exitY] = Main.EXIT;
            // Génère un chemin dans le labyrinthe à partir de la position (1,1)
            GenerateMaze.carvePath(1,1);
        }
        else if (bn == false) {
            System.out.println("Génération automatique...");

            Main.maze = new String[Main.height][Main.width];
            for (int i = 0; i < Main.height; i++) {
                for (int j = 0; j < Main.width; j++) {
                    Main.maze[i][j] = Main.WALL;
                }
            }
            // Initialise un labyrinthe de taille prédéfinie avec des murs
            entryY = 1;// Entrée par défaut en haut à gauche
            exitY = Main.width - 2;// sortie par défaut en haut à gauche
            //Set Entry on top left
            Main.maze[0][entryY] = Main.ENTRY;
            //set exit on bottom right
            Main.maze[Main.height - 1][exitY] = Main.EXIT;
            GenerateMaze.carvePath(1,1);
        }
    }

    /**
     * Get Height custom and verify peer
     */
    public static void verifHeight(){
        //Hauteur
        while (!inputVerif){
            System.out.println("Entrez la hauteur du labyrinte (Chiffre impair) :");
            scanner = new Scanner(System.in);
            if(scanner.hasNextInt()){
                Main.height = scanner.nextInt();
                inputVerif = true;
            }else{
                System.out.println("Veuillez répondre par un chiffre");
            }
        }
        inputVerif = false;

        if (Main.height % 2 == 0) {
            System.out.println("Ce chiffre est pair ! Choisissez un nombre impair");
            verifHeight();
        }
    }

    /**
     * Get Width custom and verify peer
     */
    public static void verifWidth(){
        //largeur
        while (!inputVerif){
            System.out.println("Entrez la largeur du labyrinte (Chiffre impair) :");
            scanner = new Scanner(System.in);
            if(scanner.hasNextInt()){
                Main.width = scanner.nextInt();
                inputVerif = true;
            }else{
                System.out.println("Veuillez répondre par un chiffre");
            }
        }
        inputVerif = false;

        if (Main.width % 2 == 0) {
            System.out.println("Ce chiffre est pair ! Choisissez un nombre impair");
            verifWidth();
        }
    }

    /**
     * Function for Display the maze array
     */
    public static void displayMaze() {
        //Display maze in console
         String WALL = "\uD83E\uDDF1";
         String PATH = "\uD83E\uDD52";
         String ENTRY = "\uD83C\uDFC1";
         String EXIT = "\uD83D\uDCA3";
         String SOLVE = "\uD83D\uDCA5";
         String PLAYER = "\uD83D\uDC73";
         String PASSAGE = "\uD83C\uDF46";

        for (int i = 0; i < Main.maze.length; i++) {
            for (int j = 0; j < Main.maze[i].length; j++) {
                if(Main.maze[i][j].equals("#")){
                    System.out.print(WALL + " ");
                }else if (Main.maze[i][j].equals(" ")){
                    System.out.print(PATH + " ");
                } else if (Main.maze[i][j].equals("E")) {
                    System.out.print(ENTRY + " ");
                } else if (Main.maze[i][j].equals("S")) {
                    System.out.print(EXIT + " ");
                } else if (Main.maze[i][j].equals(">")) {
                    System.out.print(SOLVE + " ");
                } else if (Main.maze[i][j].equals("X")) {
                    System.out.print(PLAYER + " ");
                } else if (Main.maze[i][j].equals("-")) {
                    System.out.print(PASSAGE + " ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Function for create New File
     */
    public static void createFile(){
        getLastNumber();
        try {
            File myObj = new File("src/labfiles/lab" + (Main.lastFilenumber + 1) + ".labgen");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Function for write into the new file
     */
    public static void writeFile(){
        try {
            FileWriter myWriter = new FileWriter("src/labfiles/lab" + (Main.lastFilenumber + 1) + ".labgen");
            myWriter.write(printMaze());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Function for write the generate maze into a new string
     * @return string of the new generate maze
     */
    public static String printMaze() {
        String result = "";
        //print maze in a String
        for (int i = 0; i < Main.maze.length; i++) {
            for (int j = 0; j < Main.maze[i].length; j++) {
                result = result + Main.maze[i][j];
            }
            result = result + "\n";
        }
        return result;
    }

    /**
     * Function for get the last file number
     */
    public static void getLastNumber(){
        String directoryPath = "src/labfiles";
        File directory = new File(directoryPath);

        // On initialise lastFilenumber à 0 par défaut
        Main.lastFilenumber = 0;

        // Utilisation de listFiles pour récupérer les fichiers
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                String str = file.getName();
                // On récupère uniquement les chiffres dans le nom du fichier
                String numberOnly = str.replaceAll("[^0-9]", "");
                // On vérifie si la chaîne est non vide avant de la convertir en entier
                if (!numberOnly.isEmpty()) {
                    int num = Integer.parseInt(numberOnly);
                    // On garde le plus grand numéro
                    if (num > Main.lastFilenumber) {
                        Main.lastFilenumber = num;
                    }
                }
            }
        }
    }


    /**
     * Get list of avaible files
     */
    public static void getAllFiles(){
        String directoryPath = "src/labfiles";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            System.out.println("Fichier disponible :");
            for (File file : files) {
                hasFileToCharge = true;
                System.out.println(" - " + file.getName());

            }
            System.out.println("Quel fichier voulez-vous ? (tapez le nom)");
        }else{
            hasFileToCharge = false;
            System.out.println("Il n'y a aucun fichier. Générez en un nouveau !");
        }
    }

    /**
     * Charge File
     */
    public static void customLoadFile() {
        System.out.println("\n");

        System.out.println("Voulez-vous charger un fichier labyrinthe pour le résoudre ? (o/n)");
        Scanner scannerCustom = new Scanner(System.in);
        String result = scannerCustom.nextLine();

        if (Objects.equals(result, "o")) {

            getAllFiles();
            if(hasFileToCharge){
                boolean fileCharge = false;
                while (!fileCharge){
                    Scanner scanner = new Scanner(System.in);
                    String resultFileName = scanner.nextLine();

                    // Chemin du fichier sélectionné
                    String filePath = "src/labfiles/" + resultFileName;
                    File file = new File(filePath);

                    if (file.exists()) {
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            // Lire toutes les lignes du fichier
                            List<String> lines = new ArrayList<>();
                            String line;
                            while ((line = br.readLine()) != null) {
                                lines.add(line);
                            }
                            // Initialiser le tableau `maze` avec la taille des lignes du fichier
                            int rows = lines.size();
                            int cols = lines.get(0).length(); // Assumer que toutes les lignes ont la même longueur
                            Main.maze = new String[rows][cols];

                            // Remplir le tableau `maze` avec le contenu du fichier
                            for (int i = 0; i < rows; i++) {
                                Main.maze[i] = lines.get(i).split(""); // Convertir chaque ligne en tableau de caractères
                            }
                            for (int j = 0; j < cols; j++) {
                                if (Main.maze[0][j].equals(Main.ENTRY)) {
                                    entryY = j;
                                }
                                if (Main.maze[rows - 1][j].equals(Main.EXIT)) {
                                    exitY = j;
                                }
                            }
                            System.out.println("Le labyrinthe a été chargé avec succès.");
                            fileCharge = true;
                        } catch (IOException e) {
                            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
                        }
                    } else {
                        System.out.println("Le fichier n'existe pas.");
                    }
                }
            }else{
                while (!inputVerif){
                    System.out.println("Entrez 's' pour généré un nouveau :");
                    scanner = new Scanner(System.in);
                    String result2;
                    if(scanner.hasNextLine()){
                        result2 = scanner.nextLine();
                        if(result2.equals("s")){
                            GenerateMaze.generateMaze();
                            inputVerif = true;
                        }else{
                            System.out.println("Erreur : entrez 's' pour généré un nouveau :");
                        }
                    }else{
                        System.out.println("Erreur : entrez 's' pour généré un nouveau :");
                    }
                }
                inputVerif = false;
            }
        } else if (Objects.equals(result, "n")) {
            GenerateMaze.generateMaze(); // Si l'utilisateur ne veut pas charger, générer un nouveau labyrinthe
        } else {
            System.out.println("Répondez par o/n");
            customLoadFile();
        }
    }
}
