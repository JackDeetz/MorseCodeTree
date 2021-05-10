package edu.miracosta.cs113;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Constructor
     * sets the head node to ' ' via super constructor
     * calls the readMorseCodeTree() function, to load the letters into the binary tree in the order to reflect their MorseCode counterpart
     *
     * @param
     */
    MorseCodeTree() {
        super(new Node<Character>(' '));
        readMorseCodeTree();
    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     * <p>
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     * <p>
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
        Node traverser = root;
        String output = "";
        try {

            for (int i = 0; i < morseCode.length(); i++) {
                if (morseCode.charAt(i) != '*' & morseCode.charAt(i) != '-' && morseCode.charAt(i) != ' ')
                    throw new Exception("Hey, that's not a viable char for morse code") ;
                if (morseCode.charAt(i) == '-') {
                    if (traverser.right == null)
                        throw new Exception("Whoops");
                    traverser = traverser.right;
                }

                if (morseCode.charAt(i) == '*') {
                    if (traverser.left == null)
                        throw new Exception("Whoops");
                    traverser = traverser.left;
                }

                if (morseCode.charAt(i) == ' ' || i == morseCode.length() - 1) {
                    if (traverser == null)
                        throw new Exception("Whoops");
                    output += traverser.toString();
                    traverser = root;

                }
            }
        } catch (Exception e) {
            throw new Exception("Whoops") ;
        }

        return output;
    }



    /**
     * Function takes a string of alphabetter letters and spaces and returns a string in MorseCode
     * @param alphaString String can be upper or lower case, will be converted to lower
     * @return a String with the MorseCode version of the parameter
     */
    public String translateToMorseCode(String alphaString)
    {
        alphaString = alphaString.toLowerCase() ;
        Node traverser = root ;
        String output = "" ;
        for (int i = 0 ; i < alphaString.length() ; i++)
        {
            output += translateToMorseCodeHelper(traverser, alphaString.charAt(i), "") ;
        }
        return output.equals("") ? "Error, not a valid char" : output ;
    }
    /**
     * private helper function facilitates the tree traversal for the translateToMorseCode(String) function
     * Builds a string of * and - based on it's movements to the left or right child node,
     * when a match is made between the node data and the char inputChar, the String of it's path is returned
     * @param position Node to traverse the MorseCodeTree with
     * @param inputChar char to be converted to MorseCode
     * @param outputString is the string that is built by every recursive call of the helper function, * and -, returns the String when/if a match is found
     * @return a String representing the MorseCode translation of the inputChar
     */
    private String translateToMorseCodeHelper(Node position, char inputChar, String outputString)
    {
        if (position == null)
            return "" ;
        if (position.data.equals(inputChar))
        {
            return outputString + " " ;
        }
        return (translateToMorseCodeHelper(position.left, inputChar, outputString + "*"))
                + (translateToMorseCodeHelper(position.right, inputChar, outputString + "-")) ;
    }

    /**
     * Reads the data from MorseCodeTree.txt and translates it into a morse code tree.
     * The root of the object will be set to '', and the rest of the child nodes hold the chars,
     * left movement represents a '*' and right movement represents a '-'
     *
     * @param
     * @return
     **/
    public void readMorseCodeTree()
    {
        try //try catch loop for the exceptions related to reading from an external file
        {
            File text = new File("MorseCodeTree.txt") ;
            Scanner fromFile = new Scanner(text) ;

            while (fromFile.hasNextLine())                      //reads 1 line at a time
            {
                String line = fromFile.nextLine() ;
                Node temp = new Node<>(line.charAt(0)) ;        //extracts the letter (Data for the Node)
                line = line.substring(line.indexOf(' ') + 1) ;  //trim the rest of the line to the MorseCode (remove the letter and space)
                Node traverse = root ;              //Node allows traversing of the tree
                while (line.length() > 1)               //loop traverses to the correct position for the new Node
                {
                    if (line.charAt(0) == '*')
                        traverse = traverse.left ;
                    else if (line.charAt(0) == '-')
                        traverse = traverse.right ;

                    line = line.substring(1) ;
                }
                if (line.charAt(0) == '*')                  //finally set the new Node in its final resting place
                    traverse.left = temp ;
                else if (line.charAt(0) == '-')
                    traverse.right = temp ;
            }
            fromFile.close(); //close the resource

        } catch (FileNotFoundException | NoSuchElementException e) { //try to open the file to read from but it fails for some reason
            e.printStackTrace();
        }

    }

    /**
     * Function takes a String with a filename, reads its contents (MorseCode),
     * converts it to English and returns that as a String
     *
     * @param fileName The file name to be read from ie "textFile.txt", store in project directory
     * @return a String translating the MorseCode from the file into English
     **/
    public String translateFromMorseCodeFromFile(String fileName)
    {
        String output = "", passer = "" ; //passer
        try
        {
            File text = new File(fileName) ;        //external file access
            Scanner fromFile = new Scanner(text) ;  //instantiate Scanner object on external file access object

            while (fromFile.hasNextLine())          //reads each line from the file
            {                                       //translates it from MorseCode
                output += translateFromMorseCode(fromFile.nextLine()) ;
            }                                       //and adds it to the output String

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output ;                 //return the complete file contents translated
    }

    /**
     * Function takes a String with a filename, reads its contents (MorseCode),
     * converts it to English and returns that as a String
     *
     * @param userMorseCode a String of MorseCode from the user to be translated and written to an external file, "output.txt"
     * @return
     **/
    public void translateFromMorseCodeToFile(String userMorseCode)
    {
        try {
            FileWriter writer = new FileWriter("output.txt") ;
            writer.write(translateFromMorseCode(userMorseCode)) ;         //writes to the external file the English translation of userMorseCode
            writer.close();     //close the resource

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} // End of class MorseCodeTree