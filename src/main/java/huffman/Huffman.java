package huffman;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Huffman {
    public void encode(String fileName) {
        Map<String, Integer> tabelaFrequencias = contFrequencia(fileName);
        Node arvoreHuffman = geraArvore(tabelaFrequencias);
    }
    private Node geraArvore(Map<String,Integer> tabelaFrequencias) {
        Iterator i = tabelaFrequencias.entrySet().iterator();
        List<Node> trees = new LinkedList<Node>();
        while(i.hasNext()){
            Map.Entry element = (Map.Entry)i.next();
            
            trees.add(new Node());
        }
        return null;
    }
    private Map<String, Integer> contFrequencia(String name) {
        Map<String, Integer> mapPalavras;

        mapPalavras = new HashMap<String, Integer>();
        try {
            FileInputStream entrada = new FileInputStream(name);
            InputStreamReader entradaFormatada = new InputStreamReader(entrada);
            int c = entradaFormatada.read();

            while (c != -1) {
                Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõôê]+)");
                Matcher m = p.matcher((char) c + "");

                while (m.find()) {
                    String token = m.group();
                    Integer freq = mapPalavras.get(token);

                    if (freq != null) {
                        mapPalavras.put(token, freq + 1);
                    } else {
                        mapPalavras.put(token, 1);
                    }
                }
                c = entradaFormatada.read();
            }
            entradaFormatada.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return mapPalavras;
    }
}