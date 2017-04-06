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
     if (arguments.length == 0)
     {
         System.out.println("Error! No program given for compilation.");
         return;
     }
     else if (arguments.length != 1)
     {
         System.out.println("Error! Too many arguments");
         return;
     }


   // Create a Parser instance.
     Parser p = null;
     Start tree = null;
     try {
         p =
                 new Parser(
                         new Lexer(
                                 new PushbackReader(
                                         new InputStreamReader(new FileInputStream(arguments[0])), 1024)));
     }
     catch (Exception e)
     {
         e.printStackTrace();
     }

   // Parse the input.
    try
    {
      tree = p.parse();
	    System.out.println(tree.toString());
    }
    catch(Exception e)
    {
   	  e.printStackTrace();
    }

    tree.apply(new GenericsVisitor());
  }
}
