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

            // call reduceCharges method
            reduceCharges(newFirstMole);
            reduceCharges(newSecondMole);

            // call arrangeMoles method
            arrangeMoles(firstMole, secondMole);

            // call balance method
            balance(firstMole, secondMole, newFirstMole, newSecondMole);

            // call removeSingularIons method
            removeSingularIons(newFirstMole);
            removeSingularIons(newSecondMole);

            // call addBrackets method
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
     * @Description: add brackets on polyatomic ions when there's more than one of then
     * 
     * @author Ian
     * @param compoundOne   the array containing the first compound in the chemical equation
     * @param compoundTwo   the array containing the second compound in the chemical equation
     * @param newFirstMole  int array storing the charges of the first compound after the DD reaction
     * @param newSecondMole int array storing the charges of the seond compound after the DD reaction
     */
    public static void addBrackets(String[] compoundOne, String[] compoundTwo, int[] newFirstMole, int[] newSecondMole) {
        // if the polyatomic ion is more than 1 in the reaction
        if (newFirstMole[2] > 1 && Character.toString(compoundTwo[1].charAt(compoundTwo[1].length()) ).matches("-?\\d+")) {
            compoundTwo[1] = "(" + compoundTwo[1] + ")";    // add brackets
        }
        // if the polyatomic ion in the second compound is more than 1 in the reaction
        if (newSecondMole[2] > 1 && Character.toString(compoundOne[1].charAt(compoundOne[1].length()) ).matches("-?\\d+")) {
            compoundOne[1] = "(" + compoundOne[1] + ")";    // add brackets
        }
    }
    /**
     * Description: find the total amount of moles in the original compound and arrange them in the same 
     *              pattern as the charges after the double displacement reaction
     * 
     * @author Ian 
     * @param firstMole     int array storing the moles of the first compound before the DD reaction
     * @param secondMole    int array storing the moles of the second compound before the DD reaction
     */
    public static void arrangeMoles(int[] firstMole, int[] secondMole) {
        int temp;    // a temporary string

        // iritate accross each index and multiply the amount by the amount at the first index
        for (int i = 1; i < 3; i++) {
            firstMole[i] = firstMole[i]*firstMole[0];
            secondMole[i] = secondMole[i]*secondMole[0];
        }

        // switching the amounts of the anions
        temp = firstMole[2];
        firstMole[2] = secondMole[2];
        secondMole[2] = temp;
    }
    /**
     * Description: Method to scan the percipitate chart and check if a reaction occurs in a 
     *              word equation.
     * @author Ian & William
     * @param reaction      boolean array to indicate if a reaction occurs or not
     * @param arrOne        string array of the first compound inputted by the user
     * @param arrTwo        string array of the second compound inputted by the user
     * @throws FileNotFoundException    outputs error the the file is not found in the same folder
     */
    public static void checkPercipitate(boolean[] reaction, String[] arrOne, String[] arrTwo) throws FileNotFoundException{
        // The path to the csv file may vary

        //    LEO, YOU WILL NEED TO DOWNLOAD THE FILE IN THE SAME FOLDER OF THE JAVA PROJECT
        //              FOR EXAMPLE :   src\\solubility.csv
        String path = "solubility.csv";
        
        //Parsing a csv file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader(path));

        // Variable line that equals to nothing right now
        String line = "";

        // Try, catch, and finally statement are for if the code goes wrong
        // Try block goes first 
        try{
            // A while loop that infinantly go over the file and read each line unitl it is empty
            while((line = br.readLine()) != null){
                // A string array that seperates the different infos by the comma in the file
                String[] value = line.split(",");

                // condition: if anion in compound 1 and 2 are found
                if ((value[0].indexOf(arrOne[1]) > -1) || (value[0].indexOf(arrTwo[1]) > -1 )) {
                    // iterate across the horizontal row
                    for (String ion : value) {
                        // condition: if the anion from compound 1 is in the same row as the cation from compound 2
                        // Or   if the anion from compound 2 is in the same row as the cation from compound 1
                        if (  (  ion.indexOf(arrTwo[0]) > -1 && (value[0].indexOf(arrOne[1]) > -1)) || (ion.indexOf(arrOne[0]) > -1 && (value[0].indexOf(arrTwo[1]) > -1))  ) {
                            reaction[0] = true;     // a percipitate forms --> reaction occurs
                        }
                    }
                }
            }
        }
        // The two catch files are for if the file is not found from the file path, they will print the files' stack trace
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        // Finally block that will be executed in every case, success or caught exception
        finally{
            // Close the bufferscanner if all lines in the file is read
            if(br != null){
                try{
                    br.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Description: reduce the same or factorable charges
     * 
     * @author Ian
     * @param mole    the int array containing charges from the csv file
     */
    public static void reduceCharges(int[] mole) {
        for (int i = 2; i < 5; i++) {
            if ((mole[1]%i == 0 ) && (mole[2]%i == 0)) {
                mole[1] = mole[1]/i;
                mole[2] = mole[2]/i;
            }
        }
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
    /**
     * Description: Scan the csv and grab charges
     * 
     * @author William Wu
     * @param compoundOne   the array containing the first compound in the chemical equation
     * @param compoundTwo   the array containing the second compound in the chemical equation
     * @param newFirstMole  int array storing the charges of the first compound after the DD reaction
     * @param newSecondMole int array storing the charges of the seond compound after the DD reaction
     * @throws FileNotFoundException    outputs error the the file is not found in the same folder
     */
    public static void scanCharges(String[] compoundOne, String[] compoundTwo, int[] newFirstMole,
    int[] newSecondMole) throws FileNotFoundException{
        // need path
        String path = "charges.csv";

        //Parsing a csv file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader(path));

        // Variable line that equals to nothing right now
        String line = "";

        // Try, catch, and finally statement are for if the code goes wrong
        // Try block goes first 
        try{
            // A while loop that infinantly go over the file and read each line unitl it is empty
            while((line = br.readLine()) != null){
                // A string array that seperates the different infos by the comma in the file
                String[] value = line.split(",");

                // if the cation from the first compound is found
                if (value[0].indexOf(compoundOne[0]) > -1) {
                    newFirstMole[2] = Integer.parseInt(value[0]);   // store the charge
                }
                // if the cation from the second compound is found
                else if (value[0].indexOf(compoundTwo[0]) > -1) {
                    newSecondMole[2] = Integer.parseInt(value[0]);  // store the charge
                }
                // if the anion from the second compound is found
                else if (value[0].indexOf(compoundTwo[1]) > -1) {
                    newFirstMole[1] = Integer.parseInt(value[0]);   // store the charge
                }
                // if the anion from the first compound is found
                else if (value[0].indexOf(compoundOne[1]) > -1) {
                    newSecondMole[1] = Integer.parseInt(value[0]);  // store the charge
                }
            }
        }
        // The two catch files are for if the file is not found from the file path, they will print the files' stack trace
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        // Finally block that will be executed in every case, success or caught exception
        finally{
            // Close the bufferscanner if all lines in the file is read
            if(br != null){
                try{
                    br.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}