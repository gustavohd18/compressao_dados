package huffman;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        Huffman huffman = new Huffman();
        huffman.encode("test.txt");
    }
}