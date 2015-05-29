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
        compress(inFile, compressedFile);
        //ZLdecompress(compressedFile, decompressedFile);
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
            ArrayList<String> dictionary = new ArrayList<>();
            for(int i = 0; i < 26; i++)
            {
                String temp = (char)(i+65)+"";
                dictionary.add(temp);
            }

            //Read input and generate decompressed output
            String curr, outTemp, temp;
            String[] line;
            Integer[] ints;

            while(in.hasNextLine())
            { //Read every line of file
                curr = in.nextLine();                   // Read in next line of input
                line = curr.split(" ");                 // Convert input into an array
                ints = new Integer[line.length];
                for(int i = 0; i < ints.length; i++ )   // Parse Strings in array into Ints
                    ints[i] = Integer.parseInt(line[i]);// and transfer to Integer array

                for(int i = 0; i < ints.length; i++)
                { //Cycle through each character on that line

                    temp = dictionary.get(ints[i]);

                    int j = 1;
                    outTemp = null;
                    while((dictionary.contains(temp)) && ((i+j) < ints.length))
                    {
                        outTemp = dictionary.get(dictionary.indexOf(temp));
                        //System.out.println(temp + " FOUND IN DICT");
                        temp += dictionary.get(ints[i + j]);
                        j++;
                    }
                    dictionary.add(temp);
                    if(outTemp != null) {
                        System.out.println("Writing: " + temp);
                        writer.write(outTemp + "\n");
                    }
                }

            }

            //print dictionary
            System.out.println(dictionary.toString());
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

    public static boolean compress(String inFilePath, String outFilePath)
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
            ArrayList<String> dictionary = new ArrayList<>(255);
            for(int i = 0; i < 26; i++)
            {
                String temp = (char)(i+65)+"";
                dictionary.add(temp);
            }

            //Read input and generate compressed output
            String P, C, line;
            Integer outTemp;
            while((line = reader.readLine()) != null)
            {
                int inc = 0;
                while( inc < line.length())
                {
                    P = line.charAt(inc++) + "";
                    C = line.charAt(inc) + "";

                    while(dictionary.contains(P+C))
                    {
                        P = P+C;
                        inc++;
                        if(inc < line.length())
                            C = line.charAt(inc) + "";
                    }

                    dictionary.add(P+C);
                    outTemp = dictionary.indexOf(P);

                    System.out.println("Writing: " + dictionary.get(outTemp));
                    writer.write(outTemp + " = " + dictionary.get(outTemp) + "\n");
                }
            }

            //print dictionary
            System.out.println(dictionary.toString());
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

    public static boolean decompress(String inFilePath, String outFilePath)
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
            ArrayList<String> dictionary = new ArrayList<>(255);
            for(int i = 0; i < 26; i++)
            {
                String temp = (char)(i+65)+"";
                dictionary.add(temp);
            }

            //Read input and generate compressed output
            String P, C, line;
            Integer outTemp;
            while((line = reader.readLine()) != null)
            {
                int inc = 0;
                while( inc < line.length())
                {
                    P = line.charAt(inc++) + "";
                    C = line.charAt(inc) + "";

                    while(dictionary.contains(P+C))
                    {
                        P = P+C;
                        inc++;
                        if(inc < line.length())
                            C = line.charAt(inc) + "";
                    }

                    dictionary.add(P+C);
                    outTemp = dictionary.indexOf(P);

                    System.out.println("Writing: " + dictionary.get(outTemp));
                    writer.write(outTemp + " = " + dictionary.get(outTemp) + "\n");
                }
            }

            //print dictionary
            System.out.println(dictionary.toString());
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
