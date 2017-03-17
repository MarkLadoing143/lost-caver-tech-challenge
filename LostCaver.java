import java.util.*;
import java.io.*;
/**
 * Lost Caver Technical Challenge
 * @author Mark Ladoing
 * @version 1.0
 * Last Modified: March 17, 2017
 */
public class LostCaver
{
    private Scanner reader;
    private String[][] cave;
    private String direction;
    private int x;
    private int y;
    private boolean quit;
    private static int CAVE_HI = 16;
    private static int CAVE_WI = 20;

    /**
     * Constructor for Objects
     */
    public LostCaver()
    {
        cave = new String[CAVE_HI][CAVE_WI];
        x = 0;
        y = 0;
        direction = "N";
        quit = true;
    }

    /**
     * Sets up and Runs User Interface
     */
    public void run()
    {
        Scanner reader = new Scanner(System.in);
        quit = false;

        System.out.print("Enter your Current X Coordinate: ");
        int col = reader.nextInt();
        while(col < 0 || col >= CAVE_WI)
        {
            System.out.print("ERROR - X Out of Bounds - Please try again: ");
            col = reader.nextInt();
        }

        System.out.print("Enter your Current Y Coordinate: ");
        int row = reader.nextInt();
        while(row < 0 || row >= CAVE_HI)
        {
            System.out.print("ERROR - Y Out of Bounds - Please try again: ");
            row = reader.nextInt();
        }

        System.out.print("Enter your Current Direction: ");
        String dir = reader.next().trim();
        while(!dir.equals("N") && !dir.equals("S") && !dir.equals("E") && !dir.equals("W"))
        {
            System.out.print("ERROR - Invalid Direction - Please try again: ");
            dir = reader.next().trim();
        }

        System.out.println("####- INITIALIZING MAP... -####");
        setup(row, col, dir);
        System.out.println();

        do
        {
            printMenu();
            System.out.print("Please enter input (Type 'Q' to Quit): ");

            String choice = reader.next();
            switch(choice)
            {
                case "M":
                if(checkBounds() == true)
                {
                    move(choice);
                }
                else
                {
                    System.out.println("####- ERROR - Out of Bounds of Cave -####");
                }
                break;

                case "L":
                move(choice);
                break;

                case "R":
                move(choice);
                break;

                case "Q":
                quit = true;
                break;

                default:
                System.out.println("####- ERROR - Invalid Input -####");
                break;
            }
            System.out.println();
        }
        while(quit == false);

        if(quit == true)
            System.out.println("####- PROGRAM HAS QUIT -####");
    }

    /**
     * Updates the current coordinates and initializes the map data
     */
    public void setup(int row, int col, String dir)
    {
        x = col;
        y = row;
        direction = dir;
        cave = initializeCave(cave, row, col);
    }

    /**
     * sets up the 2d array which will represent the map and with the lost caver's position
     */
    private String[][] initializeCave(String[][] cave, int row, int col)
    {      
        int rCount = 0; //Needed to start the coordinates from the bottom left of the map
        for(int r=cave.length-1; r>=0; r--){
            for(int c=0; c<cave[r].length; c++){
                if(rCount == row && c == col){
                    cave[r][c] = "*"; 
                    //cave[r][c] = "*" + c + "," + rCount + "*"; //DEBUG ONLY - Adds Coordinates
                }
                else{
                    cave[r][c] = " ";
                    //cave[r][c] = " " + c + "," + rCount + " "; //DEBUG ONLY - Adds Coordinates
                }
            }
            rCount++;
        }

        return cave;
    }

    /**
     * Prints a map of the cave
     */
    public void printCave()
    {
        for(int row=0; row < cave.length; row++)
        {
            for(int col=0; col < cave[row].length; col++)
            {
                System.out.print("[" + cave[row][col] + "]");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints both the map and the lost caver's position; Used for user interaction
     */
    public void printMenu()
    {
        System.out.println("=== Current Position : (" + x + "," + y + ") " + direction + " ====");
        printCave();
    }

    /**
     * Moves the lost caver's position
     */
    public void move(String move)
    {
        int row = CAVE_HI - 1 - y;
        int col = x;
        if(row < 0) //Technically should never happen, but added for error prevention
        {
            row = 0;
        }
        if(col < 0)
        {
            col = 0;
        }

        if(move.equals("M"))
        {
            if(direction.equals("N"))
            {
                cave[row][col] = " ";
                col++;
                cave[row][col] = "*";
                x++;
            }
            else if(direction.equals("S"))
            {
                cave[row][col] = " ";
                col--;
                cave[row][col] = "*";
                x--;
            }
            else if(direction.equals("E"))
            {
                cave[row][col] = " ";
                row++;
                cave[row][col] = "*";
                y--;
            }
            else if(direction.equals("W"))
            {
                cave[row][col] = " ";
                row--;
                cave[row][col] = "*";
                y++;
            }
        }
        else if(move.equals("R"))
        {
            if(direction.equals("N"))
            {
                direction = "E";
            }
            else if(direction.equals("S"))
            {
                direction = "W";
            }
            else if(direction.equals("E"))
            {
                direction = "S";
            }
            else if(direction.equals("W"))
            {
                direction = "N";
            }
        }
        else if(move.equals("L"))
        {
            if(direction.equals("N"))
            {
                direction = "W";
            }
            else if(direction.equals("S"))
            {
                direction = "E";
            }
            else if(direction.equals("E"))
            {
                direction = "N";
            }
            else if(direction.equals("W"))
            {
                direction = "S";
            }
        }
    }

    /**
     * Checks if the caver is within bounds of the map;
     * Returns true if still within bounds; False if at the map's edge
     */
    private boolean checkBounds()
    {
        boolean good = false;
        int row = CAVE_HI - 1 - y;
        int col = x;

        if(direction.equals("N"))
        {
            int newDir = col + 1;
            if(newDir >= 0 && newDir < CAVE_WI){
                good = true;
            }
        }
        else if(direction.equals("S"))
        {
            int newDir = col - 1;
            if(newDir >= 0 && newDir < CAVE_WI){
                good = true;
            }
        }
        else if(direction.equals("E"))
        {
            int newDir = row + 1;
            if(newDir >= 0 && newDir < CAVE_HI){
                good = true;
            }
        }
        else if(direction.equals("W"))
        {
            int newDir = row - 1;
            if(newDir >= 0 && newDir < CAVE_HI){
                good = true;
            }
        }
        return good; 
    }
}
