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
    String curLine;
         
    Map<String,Integer> mapPalavras; 
     
    mapPalavras = new HashMap<String,Integer>();
    try{
       // FileReader txtFile = new FileReader("test.txt");
       //BufferedReader txtBuffer = new BufferedReader(txtFile);
       FileInputStream entrada = new FileInputStream("test.txt");
       InputStreamReader entradaFormatada = new InputStreamReader(entrada);
       int c = entradaFormatada.read();
       
        //curLine = txtBuffer.readLine();
     
    while (c!=-1) {
         
       // String minusculo = curLine;
        //for (int i = 0; i< minusculo.length();i++){

    
        Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõôê]+)");
        Matcher m = p.matcher((char) c+"");

        while(m.find())
        {
          String token = m.group();  
          Integer freq = mapPalavras.get(token); 

            if (freq != null) { 
                mapPalavras.put(token, freq+1);
            }
            else { 
                mapPalavras.put(token,1);
            }
        }
         
     //   curLine = txtBuffer.readLine();
     c = entradaFormatada.read();
    }
//}
     
entradaFormatada.close();
} catch (Exception e){
    System.out.println(e);
}

 for (Map.Entry<String, Integer> entry : mapPalavras.entrySet()) {
    System.out.println(entry.getKey() + "\tfreq=" + entry.getValue());
 }
}
}