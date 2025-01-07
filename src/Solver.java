public class Solver {

    public static boolean not_equals(String[][] maze, int i, int j, String Symbol) {
        /**
         * This function compares an item with some predifined items
         * Take a String table (maze)
         * Take an integers (i) for having an index of the String table
         * Take an integers (j) for having an index of the String table
         * Take a String (Symbol) to compare the items with this specific symbol
         */
        if (maze[i][j].equals(Symbol)) {
            return false;
        } else if (maze[i][j].equals(Main.PATH)) {
            return false;
        } else if (maze[i][j].equals(Main.WALL)) {
            return false;
        } else if (maze[i][j].equals(Main.EXIT)) {
            return false;
        } else {
            return !maze[i][j].equals(Main.SOLVE);
        }
    }

    public static boolean Validite_labyrinth(String[][] maze, int i, int j, int NPC) {
        /**
         * This function returns True if the labyrinth is solvent or returns a false
         * Take a String table (maze) for the labyrinth
         * Take an integers (i) for having an index of the String table
         * Take an integers (j) for having an index of the String table
         * Take an integers (NPC) which increase while this function browse the labyrinth
         */
        maze[i][j] = String.valueOf(NPC);
        if (j > 0 && maze[i][j - 1].equals(Main.PATH)) {
            Remplissage(maze, i, j - 1, NPC + 1);
        }

        if (j < maze[i].length - 1 && maze[i][j + 1].equals(Main.PATH)) {
            Remplissage(maze, i, j + 1, NPC + 1);
        }

        if (i > 0 && maze[i - 1][j].equals(Main.PATH)) {
            Remplissage(maze, i - 1, j, NPC + 1);
        }

        if (i < maze.length - 1 && maze[i + 1][j].equals(Main.PATH)) {
            Remplissage(maze, i + 1, j, NPC + 1);
        }

        return j <= 0 || !maze[i][j - 1].equals(Main.WALL) || j >= maze[i].length - 1 || !maze[i][j + 1].equals(Main.WALL) || i <= 0 || !maze[i - 1][j].equals(Main.WALL) || i >= maze.length - 1 || !maze[i + 1][j].equals(Main.WALL);
    }

    public static String[][] Remplissage(String[][] maze, int i, int j, int NPC) {
        /**
         * This function returns a labyrinth with a number that increase from entry to exit
         * Take a String table (maze) for the labyrinth
         * Take an integers (i) for having an index of the String table
         * Take an integers (j) for having an index of the String table
         * Take an integers (NPC) which increase while this function browse the labyrinth
         */
        maze[i][j] = String.valueOf(NPC);
        if (i == Main.height - 1 && j == Main.width - 2) {
            return maze;
        } else {
            if (j > 0 && maze[i][j - 1].equals(Main.PATH)) {
                Remplissage(maze, i, j - 1, NPC + 1);
            }

            if (j < maze[i].length - 1 && maze[i][j + 1].equals(Main.PATH)) {
                Remplissage(maze, i, j + 1, NPC + 1);
            }

            if (i > 0 && maze[i - 1][j].equals(Main.PATH)) {
                Remplissage(maze, i - 1, j, NPC + 1);
            }

            if (i < maze.length - 1 && maze[i + 1][j].equals(Main.PATH)) {
                Remplissage(maze, i + 1, j, NPC + 1);
            }

            return maze;
        }
    }

    public static String[][] Chemin(String[][] maze, int i, int j, String Symbol) {
        /**
         * This function returns a labyrinth with a symbol from entry to exit
         * and a path for the other path except the path that resolve the labyrinth
         * Take a String table (maze) for the labyrinth
         * Take an integers (i) for having an index of the String table
         * Take an integers (j) for having an index of the String table
         * Take a String (Symbol) to change a number in a specific symbol
         */
        int valeur = Integer.parseInt(maze[i][j]);
        maze[i][j] = Symbol;
        if (i == 1 && j == 0) {
            return maze;
        } else {
            if (Symbol.equals(Main.SOLVE)) {
                String casePreceente = String.valueOf(valeur - 1);
                if (i > 0 && not_equals(maze, i - 1, j, Symbol) && maze[i - 1][j].equals(casePreceente)) {
                    Chemin(maze, i - 1, j, Symbol);
                    if (not_equals(maze, i + 1, j, Symbol)) {
                        Chemin(maze, i + 1, j, Main.PATH);
                    }

                    if (not_equals(maze, i, j + 1, Symbol)) {
                        Chemin(maze, i, j + 1, Main.PATH);
                    }

                    if (not_equals(maze, i, j - 1, Symbol)) {
                        Chemin(maze, i, j - 1, Main.PATH);
                    }
                }

                if (i < Main.height && not_equals(maze, i + 1, j, Symbol) && maze[i + 1][j].equals(casePreceente)) {
                    Chemin(maze, i + 1, j, Symbol);
                    if (not_equals(maze, i - 1, j, Symbol)) {
                        Chemin(maze, i - 1, j, Main.PATH);
                    }

                    if (not_equals(maze, i, j + 1, Symbol)) {
                        Chemin(maze, i, j + 1, Main.PATH);
                    }

                    if (not_equals(maze, i, j - 1, Symbol)) {
                        Chemin(maze, i, j - 1, Main.PATH);
                    }
                }

                if (j > 0 && not_equals(maze, i, j - 1, Symbol) && maze[i][j - 1].equals(casePreceente)) {
                    Chemin(maze, i, j - 1, Symbol);
                    if (not_equals(maze, i, j + 1, Symbol)) {
                        Chemin(maze, i, j + 1, Main.PATH);
                    }

                    if (not_equals(maze, i + 1, j, Symbol)) {
                        Chemin(maze, i + 1, j, Main.PATH);
                    }

                    if (not_equals(maze, i - 1, j, Symbol)) {
                        Chemin(maze, i - 1, j, Main.PATH);
                    }
                }

                if (j < Main.width && not_equals(maze, i, j + 1, Symbol) && maze[i][j + 1].equals(casePreceente)) {
                    Chemin(maze, i, j + 1, Symbol);
                    if (not_equals(maze, i, j - 1, Symbol)) {
                        Chemin(maze, i, j - 1, Main.PATH);
                    }

                    if (not_equals(maze, i + 1, j, Symbol)) {
                        Chemin(maze, i + 1, j, Main.PATH);
                    }

                    if (not_equals(maze, i - 1, j, Symbol)) {
                        Chemin(maze, i - 1, j, Main.PATH);
                    }
                }
            } else {
                if (i > 0 && not_equals(maze, i - 1, j, Symbol)) {
                    Chemin(maze, i - 1, j, Main.PATH);
                }

                if (i < maze.length - 1 && not_equals(maze, i + 1, j, Symbol)) {
                    Chemin(maze, i + 1, j, Main.PATH);
                }

                if (j > 0 && not_equals(maze, i, j - 1, Symbol)) {
                    Chemin(maze, i, j - 1, Main.PATH);
                }

                if (j < maze[i].length - 1 && not_equals(maze, i, j + 1, Symbol)) {
                    Chemin(maze, i, j + 1, Main.PATH);
                }
            }

            return maze;
        }
    }

//    public static void main(String[] args) {
        ///  This is the test to see if the solver works
//        width = 31;
//        height = 31;
//        maze = new String[height][width];
//        generateMaze();
//        displayMaze();
//    }
//
//    public static void generateMaze() {
//        for(int i = 0; i < height; ++i) {
//            for(int j = 0; j < width; ++j) {
//                maze[i][j] = Main.WALL;
//            }
//        }
//
//        maze[1][0] = Main.ENTRY;
//        maze[height - 1][width - 2] = Main.EXIT;
//        carvePath(1, 1);
//        System.out.println(Validite_labyrinth(maze, 1, 0, 0));
//        System.out.println();
//        maze = Remplissage(maze, 1, 0, 0);
//        displayMaze();
//        System.out.println();
//        maze = Chemin(maze, height - 2, width - 2, Main.SOLVE);
//    }
//
//    public static void carvePath(int x, int y) {
//        int[][] directions = new int[][]{{0, -2}, {0, 2}, {-2, 0}, {2, 0}};
//        randomizeDir(directions, 4);
//        int[][] var3 = directions;
//        int var4 = directions.length;
//
//        for(int var5 = 0; var5 < var4; ++var5) {
//            int[] dir = var3[var5];
//            int nx = x + dir[0];
//            int ny = y + dir[1];
//            if (nx > 0 && ny > 0 && nx < height - 1 && ny < width - 1 && maze[nx][ny].equals(Main.WALL)) {
//                maze[nx - dir[0] / 2][ny - dir[1] / 2] = Main.PATH;
//                maze[nx][ny] = Main.PATH;
//                carvePath(nx, ny);
//            }
//        }
//
//    }
//
//    public static void randomizeDir(int[][] arr, int n) {
//        Random r = new Random();
//
//        for(int i = n - 1; i > 0; --i) {
//            int j = r.nextInt(i);
//            int[] temp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = temp;
//        }
//
//    }
//
//    public static void displayMaze() {
//        for(int i = 0; i < maze.length; ++i) {
//            for(int j = 0; j < maze[i].length; ++j) {
//                System.out.print(maze[i][j] + Main.PATH);
//            }
//
//            System.out.print("\n");
//        }
//
//    }

}
