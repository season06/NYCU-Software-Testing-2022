// Jeff Offutt, June 1989 (in C), Java version March 2003 

// Stutter checks for repeat words in a text file.
// It prints a list of repeat words, by line number.
// Stutter will accept standard input or a list
// of file names.

import java.io.*;

public class Stutter
{
    // Class variables used in multiple methods.
    private static boolean lastdelimit = true;
    private static String curWord = "", prevWord = "";
    private static char delimits [] =
            {'	', ' ', ',', '.', '!', '-', '+', '=', ';', ':', '?', '&', '{', '}', '\\'}; // First char in list is a tab

    //************************************************
    // main parses the arguments, decides if stdin
    // or a file name, and calls Stut().
    //************************************************
    public static void main (String[] args) throws IOException
    {
        String fileName;
        FileReader myFile;
        BufferedReader inFile = null;

        if (args.length == 0)
        {  // no file, use stdin
            inFile = new BufferedReader (new InputStreamReader (System.in));
        }
        else
        {
            fileName = args [0];
            if (fileName == null)
            {  // no file name, use stdin
                inFile = new BufferedReader (new InputStreamReader (System.in));
            }
            else
            {  // file name, open the file.
                myFile = new FileReader (fileName);
                inFile = new BufferedReader (myFile);
            }
        }

        stut (inFile);
    }

    //************************************************
    //************************************************
    private static void stut (BufferedReader inFile) throws IOException
    {
        String inLine;
        char c;
        int linecnt = 1;
        while ((inLine = inFile.readLine()) != null)
        {  // For each line

            for (int i=0; i<inLine.length(); i++)
            {  // for each character
                c = inLine.charAt(i);

                if (isDelimit (c))
                {  // Found an end of a word.
                    checkDupes (linecnt);
                }
                else
                {
                    lastdelimit = false;
                    curWord = curWord + c;
                }
            }
            checkDupes (linecnt);
            linecnt++;

        }
    }  // end Stut

    //************************************************
    //************************************************
    private static void checkDupes (int line)
    {
        if (lastdelimit)
            return; // already checked, keep skipping
        lastdelimit = true;
        if (curWord.equals(prevWord))
        {
            System.out.println ("Repeated word on line " + line + ": " + prevWord+ " " + curWord);
        }
        else
        {
            prevWord = curWord;
        }
        curWord = "";
    }  // end checkDupes

    //************************************************
    //************************************************
    private static boolean isDelimit (char C)
    {
        for (int i = 0; i < delimits.length; i++)
            if (C == delimits [i])
                return (true);
        return (false);
    }

} // end class Stutter