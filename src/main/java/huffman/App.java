package huffman;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.io.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class App extends JFrame implements ActionListener {
    static public File opened_file, other_file;
    static long past, future;
    static JLabel redLabel, blueLabel, redScore, blueScore;
    static JPanel buttonPanel, titlePanel, scorePanel;
    static JButton ZH, UH, EX;

    public JPanel createContentPane() {
        // Criando os botoes da tela
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(90, 20);
        titlePanel.setSize(170, 70);
        totalGUI.add(titlePanel);

        redLabel = new JLabel("Tamanho do Arquivo: ");
        redLabel.setLocation(40, 0);
        redLabel.setSize(150, 30);
        redLabel.setHorizontalAlignment(0);
        titlePanel.add(redLabel);

        blueLabel = new JLabel("Thanos irá Estalar os Dedos");
        blueLabel.setLocation(10, 30);
        blueLabel.setSize(160, 30);
        blueLabel.setHorizontalAlignment(0);
        titlePanel.add(blueLabel);

        scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setLocation(270, 20);
        scorePanel.setSize(120, 60);
        totalGUI.add(scorePanel);

        redScore = new JLabel("");
        redScore.setLocation(0, 0);
        redScore.setSize(100, 30);
        redScore.setHorizontalAlignment(0);
        scorePanel.add(redScore);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10, 130);
        buttonPanel.setSize(5200, 150);
        totalGUI.add(buttonPanel);

        ZH = new JButton("Encode");
        ZH.setLocation(0, 0);
        ZH.setSize(120, 30);
        ZH.addActionListener(this);
        buttonPanel.add(ZH);

        UH = new JButton("Decode");
        UH.setLocation(390, 0);
        UH.setSize(120, 30);
        UH.addActionListener(this);
        buttonPanel.add(UH);

        EX = new JButton("EXIT");
        EX.setLocation(130, 70);
        EX.setSize(250, 30);
        EX.addActionListener(this);
        buttonPanel.add(EX);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public void actionPerformed(ActionEvent e) {
        Huffman huffman = new Huffman();
        //e.getSourcer == para pegar o  botao selecionado no caso ZH encode apos tera um else if para decode
        if (e.getSource() == ZH) {
            try {
                huffman.encode(opened_file.getPath());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
			JOptionPane.showMessageDialog(null, "..........................Thanos Terminou..........................",
					"Status", JOptionPane.PLAIN_MESSAGE);
		} else if (e.getSource() == EX) {
			System.exit(0);
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Compressor de Texto Thanos");
        // cria a tela na main
		App demo = new App();
		frame.setContentPane(demo.createContentPane());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(350, 170, 550, 300);

		frame.setVisible(true);

		JMenu fileMenu = new JMenu("Arquivo");

		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		bar.add(fileMenu);

		JMenuItem openItem = new JMenuItem("Abrir");
		fileMenu.add(openItem);
		openItem.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						int result = fileChooser.showOpenDialog(null);
						opened_file = fileChooser.getSelectedFile();
						past = opened_file.length();
						redScore.setText(past + "Bytes");
					}
				});

		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		exitItem.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				});

		JMenu helpMenu = new JMenu("Ajuda");
		frame.setJMenuBar(bar);
		bar.add(helpMenu);

		JMenuItem helpItem = new JMenuItem("Como Usar");
		helpMenu.add(helpItem);
		helpItem.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(null,
								"O algoritmo utilizado foi o de Huffman para utilizar a aplicacao:" + "\n" + "1. Vá na aba arquivo" + "\n"
										+ "2. Selecione Abrir" + "\n"
										+ " e selecione o arquivo .txt que ira encodar" + "\n"
										+ "3. pressione o botão encode para encodar o arquivo" + "\n"
										+ "4. selecione o arquivo encodado que está como encode.thanos e selecione decode" + "\n"
										+ "Após o arquivo estará descomprimido no formato txt" ,
								"Como Usar...", JOptionPane.PLAIN_MESSAGE);
					}
				});

		JMenuItem aboutItem = new JMenuItem("Sobre");
		helpMenu.add(aboutItem);

		aboutItem.addActionListener(

				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(null,
								"Projeto desenvolvido na disciplina de Algoritmos e Estrutura de Dados 2 PUCRS 2019/1" + "\n"
										+ "Alunos envolvidos no projeto:" + "\n"
										+ "Giulia " + "\n"
										+ "Lucas" + "\n"
										+ "Gustavo",
								"Sobre", JOptionPane.PLAIN_MESSAGE);
					}
				});

	}
    public static void main(String[] args) throws IOException {
       // Huffman huffman = new Huffman();
       // huffman.encode("t.txt");
       //aqui na main iniciamos a tela ou só comentar e utilizar na forma antiga no terminal
       SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI();
        }
    });
       
    }
}