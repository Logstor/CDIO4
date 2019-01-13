package model.reader;

import java.io.*;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */

public class Reader {

    /*
    -------------------------- Fields --------------------------
     */

    private String filePath, fileName;
    private String splitter;
    private String line;
    private BufferedReader bufferedReader;

    /*
    ----------------------- Constructor -------------------------
     */

    public Reader(String fileName, String splitter) {
        this.fileName = fileName;
        this.splitter = splitter;
    }

    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties">

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    // </editor-folder>

    /*
    ---------------------- Public Methods -----------------------
     */

    /**
     * This method reads through every line of the information file of the Reader
     * and saves the information in the HashMap.
     * @param hashMap HashMap where all the information from the file is saved.
     */
    public void readFileIntoHashMap(HashMap<String,String> hashMap) {

        try {

            // BESKED FRA CHRISTIAN:
            // måske hvis i ikke bruger systemclassloader men getClass().getClassloader() istedet

            // Prøv Nedenstående WassMann:
            filePath = getClass().getClassLoader().getResource(fileName).getPath();

            // Nedenstående er den gamle filePath
            //filePath = ClassLoader.getSystemClassLoader().getResource(fileName).getPath().
            //       replace("%20", " ");

            bufferedReader = new BufferedReader(new FileReader(filePath));

            while ((line = bufferedReader.readLine()) != null) {

                String[] tempKeyAndValue = line.split(splitter);

                infoArrayIntoHashMap(hashMap,tempKeyAndValue);

            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /*
    ---------------------- Support Methods ----------------------
     */

    /**
     * Runs through all the information i the String[] and adds it to a single Line (StringBuilder).
     * Every information is separated by ";" and added at the Value position in the HashMap.
     * @param hashMapToLoadWithInfo HashMap that the information is loaded into.
     * @param stringInfoArray Array of Strings that holds the information on the specified field.
     */

    private void infoArrayIntoHashMap (HashMap<String, String> hashMapToLoadWithInfo, String[] stringInfoArray) {
        int noOfInformationFieldsInArray = stringInfoArray.length-1;

        StringBuilder builderForAllInfo = new StringBuilder();
        for (int i =1 ; i <= noOfInformationFieldsInArray; i++) {
            builderForAllInfo.append(stringInfoArray[i]);

            // Makes sure not to add an ";" after the last information is added to the StringBuilder.
            // Causes problems then the splitting the last value of the valueString  from the HashMap.
            if (i != noOfInformationFieldsInArray) {
                builderForAllInfo.append(";");
            }
        }
        hashMapToLoadWithInfo.put(stringInfoArray[0],builderForAllInfo.toString());
    }
}
