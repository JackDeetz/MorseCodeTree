package edu.miracosta.cs113;

import java.io.IOException;
import java.util.Scanner ;

public class main
{
    public static void main(String[] args) {
        int userInput = 0 ;
        System.out.println("~~~~The MorseCode BinaryTree Program~~~~~\n") ;

        do
        {
            System.out.println("\nAvailable Options: ") ;
            System.out.println("\t1) Test output for all morse code letters with their respective translated alphabet letters.") ;
            System.out.println("\t2) Provide the name of a text file of MorseCode to covert to english on the console.") ;
            System.out.println("\t3) Enter MorseCode to have translated and printed to an external text file.") ;
            System.out.println("\t4) Exit this program.") ;
            System.out.println("\t5) Enter a String to have translated to MorseCode.") ;
            Scanner keyboard = new Scanner(System.in) ;

            userInput = keyboard.nextInt() ;
            keyboard.nextLine() ;

            MorseCodeTree mTree = new MorseCodeTree() ;

            switch(userInput)
            {
                case 1:
                    System.out.printf("%-15.30s %-15.30s%n", "Letter", "MorseCode");
                    for (int i = 65 ; i < 91 ; i++)
                    {
                        System.out.printf("%-15.30s %-15.30s%n", (char) i , mTree.translateToMorseCode("" +((char) i)));
                        //System.out.println("letter: " + (char) i + " = MorseCode: " + mTree.translateToMorseCode("" +((char) i)));
                    }
                    System.out.println("What about a character that isn't a normal character?");
                    System.out.printf("%-15.30s %-15.30s%n", (char) 2 , mTree.translateToMorseCode("" +((char) 2)));
                    break ;
                case 2:
                    String fileName = "" ;
                    System.out.println("Enter your filename: ");
                    fileName = keyboard.nextLine() ;
                    String output = mTree.translateFromMorseCodeFromFile(fileName) ;
                    System.out.println(output);
                    break ;
                case 3:
                    System.out.println("Enter your MorseCode to translate to file:");
                    String userMorseCode = "" ;
                    userMorseCode = keyboard.nextLine() ;
                    mTree.translateFromMorseCodeToFile(userMorseCode) ;

                    System.out.println("Look for output in the output.txt file!");
                    break ;
                case 4:
                    break ;
                case 5:
                    System.out.println("Enter your String to have translated:");
                    String englishToMorseCode = keyboard.nextLine() ;
                    System.out.println(mTree.translateToMorseCode(englishToMorseCode));
                    break ;
                default:
                    System.out.println("That's not a viable option");
                    break ;
            }
        } while (userInput != 4) ;


    }
}
