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
    private int keyIndex = 0, valueIndex = 1;

    /*
    ----------------------- Constructor -------------------------
     */

    public Reader(String fileName, String splitter) {
        this.fileName = fileName;
        this.splitter = splitter;
    }

    @Deprecated
    public Reader(String filePath,String fileName,String splitter) {
        this.filePath = filePath;
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

    public int getValueIndex() {
        return valueIndex;
    }

    public void setValueIndex(int valueIndex) {
        this.valueIndex = valueIndex;
    }


    // </editor-folder>

    /*
    ---------------------- Public Methods -----------------------
     */

    public void readFileIntoHashMap(HashMap<String,String> hashMap) {

        try {
            FileReader fileReader = new FileReader(Reader.class.getClassLoader().getResource(fileName).getFile());
            bufferedReader = new BufferedReader(fileReader);
            
            while ((line = bufferedReader.readLine()) != null) {

                String[] tempKeyAndValue = line.split(splitter);
                hashMap.put(tempKeyAndValue[keyIndex],tempKeyAndValue[valueIndex]);

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


}
