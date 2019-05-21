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

    private HashMap tabela = new HashMap<String, String>();

    public void encode(String fileName) {
        Map<String, Integer> tabelaFrequencias = contFrequencia(fileName);
        Node arvoreHuffman = geraArvore(tabelaFrequencias);
        Iterator i = fazTabela(arvoreHuffman).entrySet().iterator();
        while(i.hasNext()) {
            Map.Entry element = (Map.Entry)i.next();
            System.out.println(element.getKey());
            System.out.println(element.getValue());
        }
    }
    private Node geraArvore(Map<String,Integer> tabelaFrequencias) {
        Iterator i = tabelaFrequencias.entrySet().iterator();
        List<Node> trees = new LinkedList<>();
        while(i.hasNext()){
            Map.Entry element = (Map.Entry)i.next();
            
            trees.add(new Node((String)element.getKey(), (int)element.getValue(), null, null));
        }
        List<Node> clone = new LinkedList<>();
        clone.addAll(trees);
        while(trees.size() > 1) {
            Node menor = null, segundoMenor = null;
            Iterator interator = trees.iterator();
            while(interator.hasNext()){
                Node n = (Node) interator.next();
                if(menor == null || n.freq < menor.freq) {
                    segundoMenor = menor;
                    menor = n;
                } else {
                    if(segundoMenor == null || n.freq < segundoMenor.freq){
                        segundoMenor = n;
                    }
                }
            }
            trees.remove(menor);
            trees.remove(segundoMenor);
            trees.add(new Node("h", menor.freq + segundoMenor.freq, menor, segundoMenor));
        }

        return trees.get(0);
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

    private HashMap<String, String> fazTabela(Node root){
        fazCaminhos(root, "");
        return tabela;
    }

    private void fazCaminhos(Node n, String cod){
        if(n.isLeaf()){
            tabela.put(n.ch, cod);
        } else {
            fazCaminhos(n.left, cod.concat("0"));
            fazCaminhos(n.right, cod.concat("1"));
        }
    }
}