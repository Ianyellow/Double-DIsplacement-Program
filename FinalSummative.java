/*
* Date: May  20, 2021
* Name: Ian, William, Leo
* Teacher: Mr. Ho
* Description: A program that finds the double displacement reaction of compounds, given a chemical or word equation
**/

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

        // condition: if user inputs a word equation
        if (accum > 4) {
            try {
                // call doesItExist method 
                checkPercipitate(reaction, compoundOne, compoundTwo);
            }
            // catch file not found error when reading the method
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            // chemical equation
            System.out.println("chem");
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
}