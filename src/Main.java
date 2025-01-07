import java.util.Scanner;

public class Main {

    //First Maze Initialize
    public static String[][] maze;
    public static int width = 13;
    public static int height = 13;
    public static final String WALL = "#";
    public static final String PATH = " ";
    public static final String ENTRY = "E";
    public static final String EXIT = "S";
    public static final String SOLVE = ">";
    public static final String PLAYER = "X";
    public static final String PASSAGE = "-";


    public static int lastFilenumber;


    public static void main(String[] args) {



        Utils.customLoadFile();
        Utils.displayMaze();

        //choice mouvement or solve:
        while (!Utils.inputVerif){
            System.out.println("Souhaitez-vous jouer ('play') ou résoudre le labyrinthe ('solve')? (p/s)");
            Scanner scanner = new Scanner(System.in);
            String result;
            if(scanner.hasNextLine()){
                result = scanner.nextLine();
                if(result.equals("p") || result.equals("play")){
                    Movement.mouvement();
                    Utils.inputVerif = true;
                }else if (result.equals("s") || result.equals("solve")){
                    maze = Solver.Remplissage(maze, 1, Utils.entryY, 0);
                    maze = Solver.Chemin(maze, height - 2, Utils.exitY, Main.SOLVE);
                    Utils.displayMaze();
                    Utils.inputVerif = true;
                }else{
                    System.out.println("Veuillez répondre par 'p' ou 's' (play or solve)...");
                }
            }else{

                System.out.println("Veuillez répondre par 'p' ou 's' (play or solve)...");
            }
        }
        Utils.inputVerif = false;
        GenerateMaze.complex(GenerateMaze.nmoperation , GenerateMaze.checkmaze);

    }
}