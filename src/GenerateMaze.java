import java.util.Random;

public class GenerateMaze {

    public static int nmoperation = 0;
    public static int checkmaze = 0;

    /**
     * Function generate new Maze Randomly
     */
    public static void generateMaze() {
        // Fill entire grid with wall

        Utils.customizable();
        Utils.createFile();
        Utils.writeFile();

    }

    /**
     * Function who carve a path into the Maze Array
     * @param x: coordinate x of start
     * @param y: coordinate y of start
     */
    public static void carvePath(int x, int y) {
        // Directions possibles (haut, bas, gauche, droite)
        int[][] directions = {{0, -2}, {0, 2}, {-2, 0}, {2, 0}};
        //randomize direction
        randomizeDir(directions, 4);

        for (int[] dir : directions) {
            int nx = x + dir[0]; // Nouvelle position x
            int ny = y + dir[1]; // Nouvelle position y

            checkmaze ++;

            // Vérifier si la nouvelle position est dans les limites et si c'est un mur
            if (nx > 0 && ny > 0 && nx < Main.height - 1 && ny < Main.width - 1 && Main.maze[nx][ny].equals(Main.WALL)) {
                // Créer un chemin entre les cellules
                Main.maze[nx - dir[0] / 2][ny - dir[1] / 2] = Main.PATH;
                Main.maze[nx][ny] = Main.PATH;

                nmoperation ++;

                // Recur
                carvePath(nx, ny);
            }
        }
    }

    /**
     * Function for randomize the direction of carve
     * @param arr: Array of Direction
     * @param n: lenght of that Array
     */
    public static void randomizeDir(int[][] arr, int n) {
        Random r = new Random();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i);

            // Swap arr[i] with the element at random index
            int[] temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * get complexity
     * @param nmoperation: number operation
     * @param checkmaze: number of case check
     */
    public static void complex(int nmoperation , int checkmaze){
        System.out.println("nbr operations : "+ String.valueOf(nmoperation));
        System.out.println("nbr cellules check : " + String.valueOf(checkmaze));
    }

}
