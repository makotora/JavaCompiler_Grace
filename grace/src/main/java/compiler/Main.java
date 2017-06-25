package compiler;

import compiler.lexer.Lexer;
import compiler.node.*;
import compiler.parser.*;
import java.io.PushbackReader;
import java.io.InputStreamReader;
import java.io.*;

public class Main
{
 public static void main(String[] arguments)
 {
     String inputFile = null;
     boolean optimizeCode = true;

     if (arguments.length == 0)
     {
         System.out.println("Error! No program given for compilation.");
         System.exit(-1);
     }
     else if (arguments.length == 1)
     {
         inputFile = arguments[0];
     }
     else if (arguments.length == 2)
     {
         inputFile = arguments[0];
         optimizeCode = false;
     }
     else
     {
         System.out.println("Error! Too many arguments");
         System.exit(-1);
     }


   // Create a Parser instance.
     Parser p = null;
     Start tree = null;
     try {
         p =
                 new Parser(
                         new Lexer(
                                 new PushbackReader(
                                         new InputStreamReader(new FileInputStream(inputFile)), 1024)));
     }
     catch (Exception e)
     {
         e.printStackTrace();
     }

   // Parse the input.
    try
    {
      tree = p.parse();
//	    System.out.println(tree.toString());
    }
    catch(Exception e)
    {
   	  e.printStackTrace();
    }

    //cut .grace file extension if given.We want the name itself (x)
     int indexOfLast = inputFile.lastIndexOf(".grace");

     if (indexOfLast >= 0)
         inputFile = inputFile.substring(0, indexOfLast);


    tree.apply(new GenericsVisitor(inputFile, optimizeCode));//we passed the input file name so that we can create the assembly file

     System.exit(0);
  }
}
