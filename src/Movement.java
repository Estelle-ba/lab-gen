import java.util.Scanner;


public class Movement {

    public static String player = Main.PLAYER;
    public static int posX = 0;
    public static int posY = Utils.entryY;
    public static int latestX;
    public static int latestY;

    /**
     * Move system for player in the Maze
     */
    public static void mouvement() {
        // Création du scanner pour lire les entrées utilisateur
        Scanner scanner = new Scanner(System.in);
        String input;
        // Initialisation de la position de départ du joueur dans le labyrinthe
        Main.maze[0][Utils.entryY] = player;
        latestX = 0;
        latestY = Utils.entryY;

        // Affichage initial du labyrinthe
        Utils.displayMaze();
        // Boucle jusqu'à ce que le joueur atteigne la sortie
        while (Main.maze[posX][posY] != Main.maze[Main.height -1][Utils.exitY]) {
            System.out.println("Veuillez entrer 'z' ou 'q' ou 's' ou 'd' :");

            // Lecture et conversion de l'entrée utilisateur en minuscules

            input = scanner.nextLine().toLowerCase();

            // Vérification si l'entrée est valide (z, q, s, d)

            if (input.equals("z") || input.equals("q") || input.equals("s") || input.equals("d")) {

                // Mouvement vers le haut (z)

                if (input.equals("z")) {
                    posX --;

                    // Vérification de collision avec un mur

                    if (Main.maze[posX][posY] != "#"){
                        Main.maze[posX][posY] = player;
                        Main.maze[latestX][latestY] = Main.PASSAGE;
                        latestX = posX ;
                        latestY = posY ;

                        // Affichage du labyrinthe après le déplacement

                        Utils.displayMaze();
                        System.out.println("touche Z");
                    }
                    else {
                        posX ++;
                        System.out.println("Tu ne peux pas car il y a un mur");
                    }
                    // Mouvement vers la gauche (q)

                }if (input.equals("q")) {
                    posY --;
                    if (Main.maze[posX][posY] != Main.WALL){
                        Main.maze[posX][posY] = player;
                        Main.maze[latestX][latestY] = Main.PASSAGE;
                        latestX = posX ;
                        latestY = posY ;
                        //Utils.ClearTerminal();
                        Utils.displayMaze();
                        System.out.println("touche Q");
                    }
                    else {
                        posY ++;
                        System.out.println("Tu ne peux pas car il y a un mur");
                    }
                    // Mouvement vers le bas (s)
                }if (input.equals("s")) {
                    posX ++;
                    if (Main.maze[posX][posY] != Main.WALL){
                        Main.maze[posX][posY] = player;
                        Main.maze[latestX][latestY] = Main.PASSAGE;
                        latestX = posX ;
                        latestY = posY ;
                        //Utils.ClearTerminal();
                        Utils.displayMaze();
                        System.out.println("touche S");
                    }
                    else {
                        posX --;
                        System.out.println("Tu ne peux pas car il y a un mur");
                    }
                    // Mouvement vers la droite (d)
                }if (input.equals("d")) {
                    posY ++;
                    if (Main.maze[posX][posY] != Main.WALL){
                        Main.maze[posX][posY] = player;
                        Main.maze[latestX][latestY] = Main.PASSAGE;
                        latestX = posX ;
                        latestY = posY ;
                        //Utils.ClearTerminal();
                        Utils.displayMaze();
                        System.out.println("touche D");
                    }
                    else {
                        posY --;
                        System.out.println("Tu ne peux pas car il y a un mur");
                    }

                }

            }
            else {
                // Message d'erreur si l'entrée n'est pas valide
                System.out.println("Error ! vous n'avez sélectionné aucun inputs");
            }
        }
        System.out.println("vous avez fini");
    }
}