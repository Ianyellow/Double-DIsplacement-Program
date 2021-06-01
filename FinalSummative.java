/*
* Date: May  20, 2021
* Name: Ian, William, Leo
* Teacher: Mr. Ho
* Description: A program that finds the double displacement reaction of compounds, given a chemical or word equation
**/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class FinalSummative {
    public static void main(String [] args) {

        boolean reaction[] = {false};
        int accum = 0;

        // indentify if the user inputs a chemical or word equation
        for (int i = 0; i < txtfiieldOne.length(); i ++) {
            if (Character.isLowerCase(txtfiieldOne.charAt(i)) == true) {
                accum++;
            }
        }

        // spliting the blank spaces and storing each ion into their coresponding compound array
        String[] compoundOne = txtfieldOne.getText().split(" ");
        String[] compoundTwo = txtfieldTwo.getText().split(" ");
        int[] firstMole = new int[3];
        int[] secondMole = new int[3];


        // Condition: if the user inputs a chemical equation
        if (accum < 5) {
            // call identifyFirstNum method
            identifyFirstNum(compoundOne, firstMole);
            identifyFirstNum(compoundTwo, secondMole);
        }
        
        try {
            // call doesItExist method 
            checkPercipitate(reaction, compoundOne, compoundTwo);
        }
        // catch file not found error when reading the method
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // add brackets method


        // display the product after a reaction occurs
        if (reaction[0] == true) { 
            display.setText("--> " + compoundOne[0] + " " + compoundTwo[1] + " + " + compoundTwo[0] + " " + compoundOne[1]);
        }
        // display no reaction if a reaction doesn't occur
        else {
            display.setText("--> no reaction");
        }
    }
    /**
     * Description: Method to scan the percipitate chart and check if a reaction occurs in a 
     *              word equation.
     * @author Ian & William
     * @param reaction
     * @param arrOne
     * @param arrTwo
     * @throws FileNotFoundException
     */
    public static void checkPercipitate(boolean[] reaction, String[] arrOne, String[] arrTwo) throws FileNotFoundException{
        
    }
    /**
     * Description: if the user inputted a number before the compound, store it to the charges array and remove it
     *              from the compound array.
     * 
     * @author Ian
     * @param compound  the String array containing the compound the user inputted
     * @param moles    the int array for storing the amount of each ion in the compound
     */
    public static void identifyFirstNum(String[] compound, int[] moles) {
        // if the first index is a integer
        if (compound[0].matches("-?\\d+")) {
            moles[0] = Integer.parseInt(compound[0]);   // store the integer
            compound[0] = compound[1];      // switch indexes
            compound[1] = compound[2];      // switch indexes
        }
        // if the first index of the compound isn't a number
        else {
            moles[0] = 1;   // store the charge as 1
        }
    }
}