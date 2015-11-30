import java.util.Scanner;
import java.util.regex.*;

public class PetriNetCalc
{
    public static void main(String[] args)
    {
        //Regex definitions
        final String POS_INT = "\\d+";
        final String STATE =  "\\(?(\\d+, )*(\\d+)+\\)?";





        //Variable declarations
        Scanner in = new Scanner(System.in);
        String input;
        boolean safeInput=false;
        int noPlaces=0;
        int noTrans=0;
        Transition[] trans;
        Place[] places;





        //Process number of Places
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
        places = new Place[noPlaces];
        for(int i=0; i<noPlaces; i++)
        {
            places[i] = new Place((i+1));
        }





        //Process number of Transitions
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
        trans = new Transition[noTrans];
        for(int i=0; i<noTrans; i++)
        {
            trans[i] = new Transition((i+1));
        }






        //Process Transition Inputs
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
                }
                else
                System.out.println("\n\n\nERROR - Please enter your inputs in the format (int, int, . . . int)" +
                                       "\n--------------------------------------------------------------------\n\n\n");
            }while(!safeInput);
       }
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
        String tempReg = "\\(?(\\d+, ){"+(num-1)+"}(\\d+)+\\)?";

        if(s.matches(tempReg))
           return true;
        else
           return false;
    }
}
