import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by GusSilva on 5/25/15.
 */
public class Tester {

    public static void main(String args[])
    {
        String inFile = "files/input.txt";
        String compressedFile = "files/compressed.txt";
        String decompressedFile = "files/decompressed.txt";
        //ZLcompress(inFile, compressedFile);
        //ZLdecompress(compressedFile, decompressedFile);
    }

    public static boolean ZLcompress(String inFilePath, String outFilePath)
    {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            //Prepare Input File
            reader = new BufferedReader(new FileReader(inFilePath));
            //Prepare Output File
            writer = new BufferedWriter(new FileWriter(outFilePath, false));

            /**
             * We have chosen to initialize the dictionary with only letters A-Z
             * for simplicity. However, we acknowledge that industrial strength
             * code would initialize dictionary with entire ASCII table.
             * */
            String[] Dictionary = new String[255];
            Hashtable<String, Integer> dict = new Hashtable<>(255);
            for(int i = 0; i < 26; i++)
            {
                String temp = (char)(i+65)+"";
                Dictionary[i] = temp;
                dict.put(temp, i);

            }

            //Read input and generate compressed output
            String curr;
            String temp;
            Integer outTemp;
            while((curr = reader.readLine()) != null)
            { //Read every line of file
                for(int i = 0; i < curr.length(); i++)
                { //Cycle through each character on that line
                    temp = curr.charAt(i) + "";

                    int j = 1;
                    outTemp = null;
                    while((dict.get(temp) != null) && ((i+j) < curr.length()))
                    {
                        outTemp = dict.get(temp);
                        //System.out.println(temp + " FOUND IN DICT");
                        temp += curr.charAt(i + j);
                        j++;
                    }
                    dict.put(temp, 26 + i);
                    if(outTemp != null) {
                        System.out.println("Writing: " + outTemp);
                        writer.write(outTemp + " ");
                    }
                }
            }

            //print dictionary
            System.out.println(dict.toString());
            reader.close();
            writer.close();
            return true;
        }
        catch( FileNotFoundException e)
        {
            System.out.println("Input file not found. Exiting.");
            return false;
        }
        catch (IOException e)
        {
            System.out.println("Error creating output file. Exiting.");
            return false;
        }
    }

    public static boolean ZLdecompress(String inFilePath, String outFilePath)
    {
        BufferedReader reader;
        BufferedWriter writer;
        Scanner in;
        try {
            //Prepare Input File
            reader = new BufferedReader(new FileReader(inFilePath));
            in = new Scanner(reader);
            //Prepare Output File
            writer = new BufferedWriter(new FileWriter(outFilePath, false));

            /**
             * We have chosen to initialize the dictionary with only letters A-Z
             * for simplicity. However, we acknowledge that industrial strength
             * code would initialize dictionary with entire ASCII table.
             * */
            String[] Dictionary = new String[255];
            Hashtable<Integer, String> dict = new Hashtable<>(255);
            for(int i = 0; i < 26; i++)
            {
                String temp = (char)(i+65)+"";
                Dictionary[i] = temp;
                dict.put(i, temp);

            }

            //Read input and generate compressed output
            String curr = "";
            int temp;
            String outTemp;
            while(in.hasNextInt())
            { //Read every line of file
                temp = in.nextInt();
                outTemp = dict.get(temp);

                if(outTemp != null)
                {
                    curr += outTemp;
                    System.out.println("Writing: " + outTemp);
                    writer.write(outTemp + "\n");
                }

            }

            //print dictionary
            System.out.println(dict.toString());
            reader.close();
            writer.close();
            return true;
        }
        catch( FileNotFoundException e)
        {
            System.out.println("Input file not found. Exiting.");
            return false;
        }
        catch (IOException e)
        {
            System.out.println("Error creating output file. Exiting.");
            return false;
        }
    }
}
