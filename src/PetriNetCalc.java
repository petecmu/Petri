import java.util.ArrayList;
import java.util.Scanner;

public class PetriNetCalc
{
    //Regex definitions
    static final String POS_INT = "\\d+";
    static final String STATE =  "[(]?(\\d+, )*(\\d+)+[)]?";





    //Variable declarations
    static Scanner in = new Scanner(System.in);
    static String input;
    static boolean safeInput=false;
    static int noPlaces=0;
    static int noTrans=0;
    static int transIn[][];
    static int transOut[][];
    static String delim = " ";
    static ArrayList<String> markings = new ArrayList<String>();





    public static void main(String[] args)
    {
        //Process number of Places
        getNumPlaces();



        //Process number of Transitions
        getNumTrans();

        

        //Create trans Arrays
        transIn = new int[noTrans][noPlaces];
        transOut = new int[noTrans][noPlaces];


        
        //Process Transition Inputs
        processTransIn();


            
        //Process Transition Outputs
        processTransOut();
            
            
            
        //DEBUG - this block is for debugging only. Makes sure that the values stored inside transIn
        //and transOut are making sense
        for(int i=0; i<noTrans; i++)
            for(int j=0; j<noPlaces; j++)
            {
                System.out.print("[" + transIn[i][j] + "] ");
                if(j == noPlaces - 1)
                    System.out.println();
            }



        getInit();



        in.close();
    }
    






    //String s is any string (the input variable is expected) and String reg is a regular expression (a predefined regex variable is expected)
    private static boolean verify(String s, String reg)
    {
        if(s.matches(reg))
            return true;
        else
            return false;
    }





    //String s is any string (input variable is expected) and int num is any number (noPlaces variable is expected)
    //Method checks a string to make sure it is the in appropriate format for a state OR Transition IO ex:  (W, X, Y, Z)
    //It also makes sure that it has the correct number of places determined by "int num"
    //Typical call would look like verify(input, noPlaces) which will make sure that the input is in the appropriate format
    //  and accounts for the correct number of places
    private static boolean verify(String s, int num)
    {
        String tempReg = "[(]?(\\d+, ){"+(num-1)+"}(\\d+)+[)]?";

        if(s.matches(tempReg))
           return true;
        else
           return false;
    }





    //Method asks for and stores initial Marking
    private static void getInit()
    {
        safeInput = false;
        do
        {
            System.out.println("Please enter the initial marking for your Net:  ");
            input = in.nextLine();
            if(verify(input, noPlaces))
            {
                safeInput = true;
                System.out.println();
                input = input.replace("(","");
                input = input.replace(",", "");
                input = input.replace(")", "");

                markings.add(input);
            }
            else
                System.out.println("\n\n\nERROR - Please enter your marking in the format (int, int, . . . int)" +
                                       "\n--------------------------------------------------------------------\n\n\n");
        }while(!safeInput);
    }





    //Method converts markings into an array of integers for usability
    private static int[] convertMarking(String in)
    {
        String[] s;
        s = in.split(delim);
        int[] temp = new int[noPlaces];
        for(int i=0; i<noPlaces; i++)
        {
            temp[i] = Integer.parseInt(s[i]);
        }

        return temp;
    }





    //Method finds runs through the ArrayList markings and uses the markings within it to create a new markings until no
    //unique markings are generated.
    private static void runMarkings()
    {

    }





    //Process Transout
    private static void processTransOut()
    {
        for(int i=0; i < noTrans; i++)
        {
            safeInput = false;
            do
            {
                System.out.println("Please enter the outputs for Transition " + (i+1) +":  ");
                input = in.nextLine();
                if(verify(input, noPlaces))
                {
                    safeInput = true;
                    System.out.println();
                    input = input.replace("(","");
                    input = input.replace(",", "");
                    input = input.replace(")", "");



                    String[] s;
                    s = input.split(delim);



                    for(int j=0; j<noPlaces; j++)
                    {
                        System.out.println(s[j]);
                        transOut[i][j] = Integer.parseInt(s[j]);
                    }
                }
                else
                    System.out.println("\n\n\nERROR - Please enter your outputs in the format (int, int, . . . int)" +
                            "\n--------------------------------------------------------------------\n\n\n");
            }while(!safeInput);
        }
    }





    //Get Number of Places
    private static void getNumPlaces()
    {
        do
        {
            System.out.println("Please enter the number of Places in your net:  ");
            input = in.nextLine();
            if(verify(input, POS_INT))
                safeInput = true;
            else
                System.out.println("\n\n\nERROR - Please enter a positive integer!" +
                        "\n----------------------------------------\n\n\n");
        }while(!safeInput);

        //Create Places
        noPlaces = Integer.parseInt(input);
        System.out.println("\n\n\nYou entered:  " + noPlaces + "\n\n\n");
    }





    //Get number of trans
    private static void getNumTrans()
    {
        safeInput = false;
        do
        {
            System.out.println("Please enter the number of Transitions in your net:  ");
            input = in.nextLine();
            if(verify(input, POS_INT))
                safeInput = true;
            else
                System.out.println("\n\n\nERROR - Please enter a positive integer!" +
                        "\n----------------------------------------\n\n\n");
        }while(!safeInput);



        //Create Transitions
        noTrans = Integer.parseInt(input);
        System.out.println("\n\n\n You entered:  " + noTrans + "\n\n\n");
    }






    //Process transIn
    private static void processTransIn()
    {
        for(int i=0; i < noTrans; i++)
        {
            safeInput = false;
            do
            {
                System.out.println("Please enter the inputs for Transition " + (i+1) +":  ");
                input = in.nextLine();
                if(verify(input, noPlaces))
                {
                    safeInput = true;
                    System.out.println();
                    input = input.replace("(","");
                    input = input.replace(",", "");
                    input = input.replace(")", "");



                    String[] s;
                    s = input.split(delim);



                    for(int j=0; j<noPlaces; j++)
                    {
                        System.out.println(s[j]);
                        transIn[i][j] = Integer.parseInt(s[j]);
                    }



                }
                else
                    System.out.println("\n\n\nERROR - Please enter your inputs in the format (int, int, . . . int)" +
                            "\n--------------------------------------------------------------------\n\n\n");
            }while(!safeInput);
        }
    }
}
