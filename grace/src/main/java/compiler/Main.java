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

   // Create a Parser instance.
    Parser p =
    new Parser(
    new Lexer(
    new PushbackReader(
    new InputStreamReader(System.in), 1024)));

   // Parse the input.
    try
    {
      Start tree = p.parse();
	    System.out.println(tree.toString());
    }
    catch(Exception e)
    {
   	  e.printStackTrace();
    }
  }
}
