package huffman;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

public class Huffman {

    private HashMap tabela = new HashMap<String, String>();

    public void encode(String fileName) throws IOException {
        Map<String, Integer> tabelaFrequencias = contFrequencia(fileName);
        Node arvoreHuffman = geraArvore(tabelaFrequencias);
        Iterator i = fazTabela(arvoreHuffman).entrySet().iterator();
        while(i.hasNext()) {
            Map.Entry element = (Map.Entry)i.next();
            System.out.println(element.getKey());
            System.out.println(element.getValue());
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("encode.txt", false));
        Scanner sc = new Scanner(new FileReader(fileName));
    
        createFileHeader(writer);
    
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                writer.write(fazTabela(arvoreHuffman).get(c+""));
            }
        }
        sc.close();
        writer.close();
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
    private  Hashtable<String,Integer> contFrequencia(String name) {
        Hashtable<String,Integer> frequency =  new Hashtable<>();
        try {
        Scanner sc = new Scanner(new FileReader(name));
        
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            for(int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (frequency.containsKey(c+"")) {
                    frequency.replace(c+"",frequency.get(c+"") + 1);
                } else {
                    frequency.put(c+"", 1);
                }
            }
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("file not found: s.txt");
    }
        return frequency;
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
    private void createFileHeader(BufferedWriter writer) throws IOException {
        Set<String> characters = tabela.keySet();
        boolean firstIteration = true;
        for(String character: characters){
            if(firstIteration){
                writer.write(character + "=" + tabela.get(character));
                firstIteration = false;
            } else {
                writer.write(";");
                writer.write(character + "=" + tabela.get(character));
            }
        }
        writer.write(" \r\n");
    }
}