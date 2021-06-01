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
        int[] firstMole = {1, 1, 1};
        int[] secondMole = {1, 1, 1};
        int[] newFirstMole = {1, 1, 1};
        int[] newSecondMole = {1, 1, 1};


        // Condition: if the user inputs a chemical equation
        if (accum < 5) {
            // call identifyFirstNum method
            identifyFirstNum(compoundOne, firstMole);
            identifyFirstNum(compoundTwo, secondMole);

            // call cationAmount method
            cationAmount(compoundOne, firstMole);
            cationAmount(compoundTwo, secondMole);

            // call anionAmount method
            anionAmount(compoundOne, firstMole);
            anionAmount(compoundTwo, secondMole);

            try {
                // call scanCharges method
                scanCharges(compoundOne, compoundTwo, newFirstMole, newSecondMole);
            }
            // catch file not found error when reading the method
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // call cancelSameCharge method
            cancelSameCharge();

            // add brackets method
            addBrackets(compoundOne, compoundTwo, newFirstMole, newSecondMole);
        }
        

        try {
            // call doesItExist method 
            checkPercipitate(reaction, compoundOne, compoundTwo);
        }
        // catch file not found error when reading the method
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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
     * Description: check how many anions there are in the compound and store that amount, and 
     *              remove brackets if necessary.
     * 
     * @author Ian
     * @param compound  the String array containing the compound the user inputted
     * @param moles    the int array for storing the amount of each ion in the compound
     */
    public static void anionAmount(String[] compound, int[] moles) {
        // if the anion is multiple
        if (Character.toString(compound[1].charAt(compound[1].length()-1)).matches("-?\\d+")) {
            // store the amount
            moles[2] = Character.getNumericValue(compound[1].charAt(compound[1].length()-1));
            compound[1] = compound[1].substring(0, compound[1].length()-1);     // remove the integer
        }

        // store the polyatomic ion and remove the brakets
        if (Character.toString(compound[1].charAt(0)).equals("(")) {
            compound[1] = compound[1].substring(1, compound[1].length()-1);
        }
    }
    /**
     * Description: check how many cations there are in the compound and store that amount.
     * 
     * @author Ian
     * @param compound  the String array containing the compound the user inputted
     * @param moles    the int array for storing the amount of each ion in the compound
     */
    public static void cationAmount(String[] compound, int[] moles) {
        // if cation is more than one
        if (Character.toString(compound[0].charAt(compound[0].length()-1)).matches("-?\\d+")) {
            // store the amount of the cation
            moles[1] = Character.getNumericValue(compound[0].charAt(compound[0].length()-1));
            compound[0] = compound[0].substring(0, compound[0].length()-1);     // remove the integer
        }
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
    }
}