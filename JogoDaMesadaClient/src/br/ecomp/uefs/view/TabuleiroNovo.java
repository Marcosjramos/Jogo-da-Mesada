/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.view;

import br.com.uefs.conexao.ConexaoP2P;
import br.com.uefs.conexao.ServidorLocal;
import br.com.uefs.model.Player;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Marcos Ramos
 * 
 */
public final class TabuleiroNovo extends JFrame {

    public static Player player;/** variável que será responsável por representar o jogador sua atribuições no jogo no caso no tabuleiro. <br/ >*/
    private final Container principal = getContentPane();/** variável que representar o container principal no caso a tela de exibição.<br/> */
    private static JLabel jogador1;/** variável que representar bolinha que simbolizar o jogador um (primeiro a entrar no jogo ) no tabuleiro. <br/ >*/
    private static JLabel jogador2;/** variável que representar bolinha que simbolizar o jogador dois (segundo a entrar no jogo ) no tabuleiro. <br/ >*/
    private static JLabel jogador3;/** variável que representar bolinha que simbolizar o jogador três (terceiro a entrar no jogo ) no tabuleiro. <br/ >*/
    private static JLabel jogador4;/** variável que representar bolinha que simbolizar o jogador quatro(quarto a entrar no jogo ) no tabuleiro. <br/ >*/
    private static JLabel jogador5;/** variável que representar bolinha que simbolizar o jogador cinco (quinto a entrar no jogo ) no tabuleiro. <br/ >*/
    private static JLabel jogador6;/** variável que representar bolinha que simbolizar o jogador seis (sexto a entrar no jogo ) no tabuleiro. <br/ >*/
    private String nomeSala; /** variável que serve para poder chamara a tela em questão onde vai acontecer o jogo. <br/>  */
    private javax.swing.JLabel jLabel4;/** variável serve para poder andar e conjunto com a variável nomesala para ocorrer à exibição da tela onde vai ocorrer o jogo. <br/>  */
    public static final List<Player> jogadores = new ArrayList<>();/** vai armazenar  uma coleção de jogadores do jogo. <br/> */
    private int p;/** está variável serve para informará a posição que o jogador vai parar depois de jogado dado. <br/> */
    ConexaoP2P con;
    static TabuleiroNovo uniqueInstance;
    //ServidorLocal scon;

    //static List<Player> jogadores;
    /*public static synchronized TabuleiroNovo getInstance() {
        
        if (uniqueInstance == null) {
            uniqueInstance = new TabuleiroNovo();
        }
        return uniqueInstance;
    }*/

    /**  a seguir está o construtor da tela Tabuleiro Novo onde vai ocorrer o jogo e que receber os seguintes parâmetros :
     * @param : o jogador em  questão . <br/>
     * @param : a lista de todos o jodaores que vão jogado jogo. <br/>
     * assim no construtor vai estbalecer uma coneção  entre os jogadores , e vai instanciar a tela do jogo 
     */
    public TabuleiroNovo(Player mPlayer, List<Player> mJogadores) {
        super("JOGO DA MESADA");

        con = ConexaoP2P.getInstance();

        player = mPlayer;
        jogadores.addAll(mJogadores);

        this.p = 0;
        this.principal.setLayout(null);
        this.principal.setBackground(Color.WHITE);

        //for (int i=0; i < 6; i ++) {
        jogador1 = new JLabel(new ImageIcon("img/amarela.png"));
        jogador2 = new JLabel(new ImageIcon("img/pink.png"));
        jogador3 = new JLabel(new ImageIcon("img/branca.png"));
        jogador4 = new JLabel(new ImageIcon("img/laranja.png"));
        jogador5 = new JLabel(new ImageIcon("img/azul.png"));
        jogador6 = new JLabel(new ImageIcon("img/vermelha.png"));

        this.init();
        this.setLayoutPainel1();
        this.setLayoutPainel2();
        this.setLayoutPainel3();
        this.setLayoutPainel4();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            System.out.println("[Criando Servidor...]");
            ServerSocket servidor = new ServerSocket(1803);
            System.out.println("[Servidor operando na porta 1803]");

            while (true) {
                //
                System.out.println("[Esperando conexão...]");
                Socket cliente = servidor.accept();
                new ServidorLocal(cliente).start();
            }
        } catch (Exception e) {
            System.out.println("Erro!\n" + e.getMessage());
        }

        // atualizar();
    }
    /** a seguir vamos ter primira thread, que vai receber dados de cada jogador para poder atualizar a exibição de cada jogador no tabuleiro. <br/> */

    public void atualizar() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    for (Player jog : getJogadores()) {
                        switch (jog.getPino()) {
                            case 1:
                                setPosition(jog.getPosition(), jogador1, 185, 104);
                                System.out.println(jog.getPosition() + " " + jog.getPino() + " " + jog.getUsername());
                                break;
                            case 2:
                                setPosition(jog.getPosition(), jogador2, 185, 139);
                                System.out.println(jog.getPosition() + " " + jog.getPino() + " " + jog.getUsername());
                                break;
                            case 3:
                                setPosition(jog.getPosition(), jogador3, 185, 174);
                                System.out.println(jog.getPosition() + " " + jog.getPino() + " " + jog.getUsername());
                                break;
                            case 4:
                                setPosition(jog.getPosition(), jogador4, 240, 104);
                                break;
                            case 5:
                                setPosition(jog.getPosition(), jogador5, 240, 139);
                                break;
                            case 6:
                                setPosition(jog.getPosition(), jogador6, 240, 174);
                                break;
                        }
                    }
                }
            }
        }.start();
    }
      /** a função a seguir realizar a instanciação da tela do tabuleiro. <br/>  */ 
    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
        jLabel4.setText(nomeSala);
    }
    /** a função a seguir vai ser responsável posição inicial de cada bolinha que representa os jogadores no tabuleiro. <br>  */
    public void init() {
        jogador1.setBounds(185, 104, 50, 50);
        jogador2.setBounds(185, 139, 50, 50);
        jogador3.setBounds(185, 174, 50, 50);
        jogador4.setBounds(240, 104, 50, 50);
        jogador5.setBounds(240, 139, 50, 50);
        jogador6.setBounds(240, 174, 50, 50);

        principal.add(jogador1);
        principal.add(jogador2);
        principal.add(jogador3);
        principal.add(jogador4);
        principal.add(jogador5);
        principal.add(jogador6);
    }

    /** A função a seguir serve para poder alterar a posição de cada jogador recebendo alguns parâmetros que são:
    * @param: posição que vai ser a posições o jogador vai. <br/>
    * @param: jogador em questão que vai poder alterar sua posição. <br/>  
    * @param: as coordenadas   de alteração do jogador . <br>
    * e assim  vai poder  manipular a posição do jogado no tabuleiro . <br/>*/

    public void setPosicao(int mP, JLabel jogador, int x, int y) {
        if (mP == 0) {
            jogador.setBounds(x, y, 50, 50);
        }
        if (mP == 1) {
            jogador.setBounds(x + 110, y, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA CORREIOS  ! ");
            JOptionPane.showMessageDialog(null, "PUXE 1 CARTA DO TIPO CORREIOS!");

        } else if (mP == 2) {
            jogador.setBounds(x + 225, y, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA COMPRAS E ENTRETENIMENTOS ! ");
            JOptionPane.showMessageDialog(null, "PUXE 1 COMPRAS E ENTRETENIMENTOS ! ");
        } else if (mP == 3) {
            jogador.setBounds(x - 335, y, 50, 50);
            String valor = JOptionPane.showInputDialog(rootPane, "INSIRA $100", JOptionPane.QUESTION_MESSAGE);
            int v = Integer.parseInt(valor);
            JOptionPane.showMessageDialog(null, "VALOR INSERIDO COM SUCESSO: " + v);
        } else if (mP == 4) {
            jogador.setBounds(x + 445, y, 50, 50);
            boolean premio = false;

            JOptionPane.showMessageDialog(null, "PRÊMIO!- RETIRE $5.000 DO BANCO !");

            premio = true;

        } else if (mP == 5) {
            jogador.setBounds(x + 557, y, 50, 50);
            JOptionPane.showMessageDialog(null, "PRAIA DOMINGO!");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO  \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIARA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 6) {
            jogador.setBounds(x + 669, y, 50, 50);
            JOptionPane.showMessageDialog(null, "CONCURSO DE BANDA  DE ARROCHA !");

            boolean opcao = false;
            JOptionPane.showMessageDialog(null, "APERTE AQUI PARA RODAR O DADOS DA CASA!");

            do {
                Random rand = new Random();
                int dado = rand.nextInt(6) + 1;

                if (dado == 3) {

                    JOptionPane.showMessageDialog(null, "PARABÉNS VOCÊ ACABAR DE GANHAR $1000!");
                    opcao = true;
                }

            } while (opcao != true);

        } else if (mP == 7) {
            jogador.setBounds(x, y + 114, 50, 50);
            JOptionPane.showMessageDialog(null, "MARATONA BENEFICENTE!");

            JOptionPane.showMessageDialog(null, "TODO MUNDO VAI JOGAR O DADO AÍ ,\n E CADA UMA AÍ VAI  TER  QUE PAGARA $100  vezes x  \n QUE ELES SORTEAREM!");

            JOptionPane.showMessageDialog(null, " FOI PESSOA !");
        } else if (mP == 8) {
            jogador.setBounds(x + 110, y + 114, 50, 50);

            JOptionPane.showMessageDialog(null, " VENDE-SE ! \n NEGÓCIO DE OCASIÃO ! \n SEU POR APENAS $100 VEZES O NÚMERO QUE FOI SORETEADO");
            int a = 8 * 100;
            JOptionPane.showMessageDialog(null, "  NO CASO  :!" + a + " VALOR QUE O BANCO VAI PAGAR ");
            JOptionPane.showMessageDialog(null, " PUXE  1 CARTA  DE COMPRA E ENTRETENIMENTOS !");
        } else if (mP == 9) {
            jogador.setBounds(x + 225, y + 114, 50, 50);
            JOptionPane.showMessageDialog(null, "AJUDE A FLORESTA AMAZÔNICA !");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO  \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIRA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 10) {
            jogador.setBounds(x + 335, y + 114, 50, 50);
            JOptionPane.showMessageDialog(null, " LANCHONETE  !");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIRA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 11) {
            jogador.setBounds(x + 630, y + 114, 50, 50);
            JOptionPane.showMessageDialog(null, "  COMPRAS NO SHOPPING  !");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO  \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIRA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 12) {
            jogador.setBounds(x + 557, y + 114, 50, 50);
        } else if (mP == 13) {
            jogador.setBounds(x + 669, y + 114, 50, 50);
        } else if (mP == 14) {
            jogador.setBounds(x, y + 226, 50, 50);
        } else if (mP == 15) {
            jogador.setBounds(x + 110, y + 226, 50, 50);
        } else if (mP == 16) {
            jogador.setBounds(x + 225, y + 226, 50, 50);
        } else if (mP == 17) {
            jogador.setBounds(x + 335, y + 226, 50, 50);
        } else if (mP == 18) {
            jogador.setBounds(x + 445, y + 226, 50, 50);
        } else if (mP == 19) {
            jogador.setBounds(x + 557, y + 226, 50, 50);
        } else if (mP == 20) {
            jogador.setBounds(x + 669, y + 226, 50, 50);
        } else if (mP == 21) {
            jogador.setBounds(x, y + 338, 50, 50);
        } else if (mP == 22) {
            jogador.setBounds(x + 110, y + 338, 50, 50);
        } else if (mP == 23) {
            jogador.setBounds(x + 225, y + 338, 50, 50);
        } else if (mP == 24) {
            jogador.setBounds(x + 335, y + 338, 50, 50);
        } else if (mP == 25) {
            jogador.setBounds(x + 445, y + 338, 50, 50);
        } else if (mP == 26) {
            jogador.setBounds(x + 557, y + 338, 50, 50);
        } else if (mP == 27) {
            jogador.setBounds(x + 669, y + 338, 50, 50);
        } else if (mP == 28) {
            jogador.setBounds(x, y + 369, 50, 50);
        } else if (mP == 29) {
            jogador.setBounds(x + 110, y + 369, 50, 50);
        } else if (mP == 30) {
            jogador.setBounds(x + 225, y + 369, 50, 50);
            jogador.setBounds(x + 557, y + 114, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA COMPRAS E ENTRETENIMENTOS ! ");
            JOptionPane.showMessageDialog(null, "PUXE 2 COMPRAS E ENTRETENIMENTOS ! ");
        } else if (mP == 13) {
            jogador.setBounds(x + 669, y + 114, 50, 50);
            JOptionPane.showMessageDialog(null, "PRAIA DOMINGO!");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO  \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIARA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 14) {
            jogador.setBounds(x, y + 226, 50, 50);
            JOptionPane.showMessageDialog(null, "MARATONA BENEFICENTE!");

            JOptionPane.showMessageDialog(null, "TODO MUNDO VAI JOGAR O DADO AÍ ,\n E CADA UMA AÍ VAI  TER  QUE PAGARA $100  vezes x  \n QUE ELES SORTEAREM!");

            JOptionPane.showMessageDialog(null, " FOI PESSOA !");

        } else if (mP == 15) {
            jogador.setBounds(x + 110, y + 226, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA CORREIOS  ! ");
            JOptionPane.showMessageDialog(null, "PUXE 3 CARTAs DO TIPO CORREIOS!");
        } else if (mP == 16) {
            jogador.setBounds(x + 225, y + 226, 50, 50);
            JOptionPane.showMessageDialog(null, " LANCHONETE  !");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIRA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 17) {
            jogador.setBounds(x + 335, y + 226, 50, 50);
            JOptionPane.showMessageDialog(null, "CONCURSO DE BANDA  DE ARROCHA !");

            boolean opcao = false;
            JOptionPane.showMessageDialog(null, "APERTE AQUI PARA RODAR O DADOS DA CASA!");

            do {
                Random rand = new Random();
                int dado = rand.nextInt(6) + 1;

                if (dado == 3) {

                    JOptionPane.showMessageDialog(null, "PARABÉNS VOCÊ ACABAR DE GANHAR $1000!");
                    opcao = true;
                }

            } while (opcao != true);
        } else if (mP == 18) {
            jogador.setBounds(x + 445, y + 226, 50, 50);
            boolean premio = false;

            JOptionPane.showMessageDialog(null, "PRÊMIO!- RETIRE $5.000 DO BANCO !");
        } else if (mP == 19) {
            jogador.setBounds(x + 557, y + 226, 50, 50);
            String valor = JOptionPane.showInputDialog(rootPane, "INSIARA $100", JOptionPane.QUESTION_MESSAGE);
            int v = Integer.parseInt(valor);
            JOptionPane.showMessageDialog(null, "VALOR INSERIDO COM SUCESSO: " + v);
        } else if (mP == 20) {
            jogador.setBounds(x + 669, y + 226, 50, 50);
            JOptionPane.showMessageDialog(null, "PRAIA DOMINGO!");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO  \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIARA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
            //
        } else if (mP == 21) {
            jogador.setBounds(x, y + 338, 50, 50);
            JOptionPane.showMessageDialog(null, "CONCURSO DE BANDA  DE ARROCHA !");

            boolean opcao = false;
            JOptionPane.showMessageDialog(null, "APERTE AQUI PARA RODAR O DADOS DA CASA!");

            do {
                Random rand = new Random();
                int dado = rand.nextInt(6) + 1;

                if (dado == 3) {

                    JOptionPane.showMessageDialog(null, "PARABÉNS VOCÊ ACABAR DE GANHAR $1000!");
                    opcao = true;
                }

            } while (opcao != true);
        } else if (mP == 22) {
            jogador.setBounds(x + 110, y + 338, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA CORREIOS  ! ");
            JOptionPane.showMessageDialog(null, "PUXE 1 CARTA DO TIPO CORREIOS!");
        } else if (mP == 23) {
            jogador.setBounds(x + 225, y + 338, 50, 50);
            JOptionPane.showMessageDialog(null, "MARATONA BENEFICENTE!");

            JOptionPane.showMessageDialog(null, "TODO MUNDO VAI JOGAR O DADO AÍ ,\n E CADA UMA AÍ VAI  TER  QUE PAGARA $100  vezes x  \n QUE ELES SORTEAREM!");

            JOptionPane.showMessageDialog(null, " FOI PESSOA !");
        } else if (mP == 24) {
            jogador.setBounds(x + 335, y + 338, 50, 50);
            String valor = JOptionPane.showInputDialog(rootPane, "INSIARA $100", JOptionPane.QUESTION_MESSAGE);
            int v = Integer.parseInt(valor);
            JOptionPane.showMessageDialog(null, "VALOR INSERIDO COM SUCESSO: " + v);
        } else if (mP == 25) {
            jogador.setBounds(x + 445, y + 338, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA CORREIOS  ! ");
            JOptionPane.showMessageDialog(null, "PUXE 2 CARTAS DO TIPO CORREIOS!");
        } else if (mP == 26) {
            jogador.setBounds(x + 557, y + 338, 50, 50);

            player.getSaldo();
            int valorTotal;
            JOptionPane.showMessageDialog(null, " OBA!, DIA DA MESADA -PARE!");
            JOptionPane.showMessageDialog(null, " VOCÊS  TEM $ !" + player.getSaldo());
            JOptionPane.showMessageDialog(null, " PORÉM , VOCÊ TEVE \n QUE PAGA  SUA  DIVIDA COM JUROS CASO VOCÊ TENHA");
            if (player.getSaldo() <= 0) {
                valorTotal = player.getSaldo() * 35 / 100;
            } else {
                valorTotal = 0;
            }

            JOptionPane.showMessageDialog(null, " VOCÊ  VAI RECEBER $!" + valorTotal);
        } else if (mP == 27) {
            jogador.setBounds(x + 669, y + 338, 50, 50);
            JOptionPane.showMessageDialog(null, " LANCHONETE  !");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIRA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 28) {
            jogador.setBounds(x, y + 369, 50, 50);
            JOptionPane.showMessageDialog(null, "PRAIA DOMINGO!");
            JOptionPane.showMessageDialog(null, "ADICIONE A QUANTIA QUE ESTÁ EM SORTE GRANDE! \n CASO NÃO TENHA DINHEIRO UM EMPRESTIMO .");
            String op = JOptionPane.showInputDialog(rootPane, "1-FAZER EMPRESTIMO  \n 2- NÃO FAZER EMPRESTINO ", JOptionPane.QUESTION_MESSAGE);
            int p = Integer.parseInt(op);
            if (p == 1) {
                String rs = JOptionPane.showInputDialog(rootPane, "INSIARA O VALOR DO EMPRÉSTIMO A SER FEITO ", JOptionPane.QUESTION_MESSAGE);
                int S = Integer.parseInt(rs);
            } else {

                JOptionPane.showMessageDialog(null, "SEGUE  O JOGO !");
            }
        } else if (mP == 29) {
            jogador.setBounds(x + 110, y + 369, 50, 50);
            JOptionPane.showMessageDialog(null, " VENDE-SE ! \n NEGÓCIO DE OCASIÃO ! \n SEU POR APENAS $100 VEZES O NÚMERO QUE FOI SORETEADO");
            int a = 8 * 100;
            JOptionPane.showMessageDialog(null, "  NO CASO  :!" + a + " VALOR QUE O BANCO VAI PAGAR ");
            JOptionPane.showMessageDialog(null, " PUXE  1 CARTA  DE COMPRA E ENTRETENIMENTOS !");
        } else if (mP == 30) {
            jogador.setBounds(x + 225, y + 369, 50, 50);
            boolean premio = false;

            JOptionPane.showMessageDialog(null, "PRÊMIO!- RETIRE $5.000 DO BANCO !");
        } else if (mP == 31) {
            jogador.setBounds(x + 335, y + 369, 50, 50);
        }
    }
     /** altera a posição  do jagador no tabuleiro no jogo . <br>*/
    public void setPosition(int mP, JLabel jogador, int x, int y) {
        System.out.println(mP);
        switch (mP) {
            case 0:
                jogador.setBounds(x, y, 50, 50);
                break;
            case 1:
                jogador.setBounds(x + 110, y, 50, 50);
                break;
            case 2:
                jogador.setBounds(x + 225, y, 50, 50);
                break;
            case 3:
                jogador.setBounds(x + 335, y, 50, 50);
                break;
            case 4:
                jogador.setBounds(x + 445, y, 50, 50);
                break;
            case 5:
                jogador.setBounds(x + 557, y, 50, 50);
                break;
            case 6:
                jogador.setBounds(x + 669, y, 50, 50);
                break;
            case 7:
                jogador.setBounds(x, y + 114, 50, 50);
                break;
            case 8:
                jogador.setBounds(x + 110, y + 114, 50, 50);
                break;
            case 9:
                jogador.setBounds(x + 225, y + 114, 50, 50);
                break;
            case 11:
                jogador.setBounds(x + 630, y + 114, 50, 50);
                break;
            case 12:
                jogador.setBounds(x + 557, y + 114, 50, 50);
                break;
            case 13:
                jogador.setBounds(x + 669, y + 114, 50, 50);
                break;
            case 14:
                jogador.setBounds(x, y + 226, 50, 50);
                break;
            case 15:
                jogador.setBounds(x + 110, y + 226, 50, 50);
                break;
            case 16:
                jogador.setBounds(x + 225, y + 226, 50, 50);
                break;
            case 17:
                jogador.setBounds(x + 335, y + 226, 50, 50);
                break;
            case 18:
                jogador.setBounds(x + 445, y + 226, 50, 50);
                break;
            case 19:
                jogador.setBounds(x + 557, y + 226, 50, 50);
                break;
            case 20:
                jogador.setBounds(x + 669, y + 226, 50, 50);
                break;
            case 21:
                jogador.setBounds(x, y + 338, 50, 50);
                break;
            case 22:
                jogador.setBounds(x + 110, y + 338, 50, 50);
                break;
            case 23:
                jogador.setBounds(x + 225, y + 338, 50, 50);
                break;
            case 24:
                jogador.setBounds(x + 335, y + 338, 50, 50);
                break;
            case 25:
                jogador.setBounds(x + 445, y + 338, 50, 50);
                break;
            case 26:
                jogador.setBounds(x + 557, y + 338, 50, 50);
                break;
            case 27:
                jogador.setBounds(x + 669, y + 338, 50, 50);
                break;
            case 28:
                jogador.setBounds(x, y + 369, 50, 50);
                break;
            case 29:
                jogador.setBounds(x + 110, y + 369, 50, 50);
                break;
            case 30:
                jogador.setBounds(x + 225, y + 369, 50, 50);
                break;
            case 31:
                jogador.setBounds(x + 335, y + 369, 50, 50);
                break;
        }

    }
  /** altera a posição  do jagador determinado no tabuleiro no jogo . <br>*/
    public static void setPosition2(int mP, int select, int x, int y) {
        System.out.println(mP);
        JLabel jogador = null;
        switch (select) {
            case 1:
                jogador = jogador1;
                break;
                 case 2:
                jogador = jogador2;
                break;
                 case 3:
                jogador = jogador3;
                break;
                 case 4:
                jogador = jogador4;
                break;
                 case 5:
                jogador = jogador5;
                break;
                 case 6:
                jogador = jogador6;
                break;
        }
        switch (mP) {
            case 0:
                jogador.setBounds(x, y, 50, 50);
                break;
            case 1:
                jogador.setBounds(x + 110, y, 50, 50);
                break;
            case 2:
                jogador.setBounds(x + 225, y, 50, 50);
                break;
            case 3:
                jogador.setBounds(x + 335, y, 50, 50);
                break;
            case 4:
                jogador.setBounds(x + 445, y, 50, 50);
                break;
            case 5:
                jogador.setBounds(x + 557, y, 50, 50);
                break;
            case 6:
                jogador.setBounds(x + 669, y, 50, 50);
                break;
            case 7:
                jogador.setBounds(x, y + 114, 50, 50);
                break;
            case 8:
                jogador.setBounds(x + 110, y + 114, 50, 50);
                break;
            case 9:
                jogador.setBounds(x + 225, y + 114, 50, 50);
                break;
            case 11:
                jogador.setBounds(x + 630, y + 114, 50, 50);
                break;
            case 12:
                jogador.setBounds(x + 557, y + 114, 50, 50);
                break;
            case 13:
                jogador.setBounds(x + 669, y + 114, 50, 50);
                break;
            case 14:
                jogador.setBounds(x, y + 226, 50, 50);
                break;
            case 15:
                jogador.setBounds(x + 110, y + 226, 50, 50);
                break;
            case 16:
                jogador.setBounds(x + 225, y + 226, 50, 50);
                break;
            case 17:
                jogador.setBounds(x + 335, y + 226, 50, 50);
                break;
            case 18:
                jogador.setBounds(x + 445, y + 226, 50, 50);
                break;
            case 19:
                jogador.setBounds(x + 557, y + 226, 50, 50);
                break;
            case 20:
                jogador.setBounds(x + 669, y + 226, 50, 50);
                break;
            case 21:
                jogador.setBounds(x, y + 338, 50, 50);
                break;
            case 22:
                jogador.setBounds(x + 110, y + 338, 50, 50);
                break;
            case 23:
                jogador.setBounds(x + 225, y + 338, 50, 50);
                break;
            case 24:
                jogador.setBounds(x + 335, y + 338, 50, 50);
                break;
            case 25:
                jogador.setBounds(x + 445, y + 338, 50, 50);
                break;
            case 26:
                jogador.setBounds(x + 557, y + 338, 50, 50);
                break;
            case 27:
                jogador.setBounds(x + 669, y + 338, 50, 50);
                break;
            case 28:
                jogador.setBounds(x, y + 369, 50, 50);
                break;
            case 29:
                jogador.setBounds(x + 110, y + 369, 50, 50);
                break;
            case 30:
                jogador.setBounds(x + 225, y + 369, 50, 50);
                break;
            case 31:
                jogador.setBounds(x + 335, y + 369, 50, 50);
                break;
        }
    }

    /*public static void main(String[] args) {
        new TabuleiroNovo();
    }*/
    /**colocar na tela os componentes que necessários  para o jogo e  criar as ações  de de alguns botões na do jogo .*/
    private void setLayoutPainel1() {
        JPanel painel1 = new JPanel();
        JButton correio = new JButton("Cartas Correio");
        JButton compra = new JButton("Cartas para Compra");

        painel1.setBounds(980, 20, 190, 285);
        painel1.setBorder(new LineBorder(Color.BLACK, 2, true));
        painel1.setBackground(Color.WHITE);

        correio.setBounds(995, 35, 160, 120);
        correio.setFont(new Font("Tahoma", 0, 14));

        compra.setBounds(995, 170, 160, 120);
        compra.setFont(new Font("Tahoma", 0, 14));

        principal.add(correio);
        principal.add(compra);
        principal.add(painel1);

        compra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Random random = new Random();

                int num = random.nextInt(6) + 1;

                if (num == 1) {
                    JOptionPane.showMessageDialog(null, " PUXE MAIS  UM CARTA  !");

                } else if (num == 2) {
                    JOptionPane.showMessageDialog(null, " VOCÊ  VAI GANHAR $200  EM SUA  CONTA \n  VAI CURTI A VIDA !");

                } else if (num == 3) {
                    JOptionPane.showMessageDialog(null, " JOGUE  O DADO MAIS  UMA VEZ  !");

                } else if (num == 4) {
                    JOptionPane.showMessageDialog(null, " INFROME  O VALOR  QUE  VOCÊ  QUE VENDER  SUAS  PROPRIEDADE!");
                    String m = JOptionPane.showInputDialog(rootPane, " INSIRA  AQUI O VALOR : ", JOptionPane.QUESTION_MESSAGE);
                    int d = Integer.parseInt(m);

                } else if (num == 5) {
                    JOptionPane.showMessageDialog(null, " VOCÊ É FORÇADA A FAZER UM EMPRESTIMO  APARTI  DE $100!");
                    String emp = JOptionPane.showInputDialog(rootPane, " INSIRA  AQUI O VALOR : ", JOptionPane.QUESTION_MESSAGE);
                    int banco = Integer.parseInt(emp);

                } else if (num == 6) {
                    JOptionPane.showMessageDialog(null, " AVANCE  UMA CASA  !");
                    p = p + 1;

                    switch (player.getPino()) {
                        case 1:
                            setPosicao(p, jogador1, 185, 104);
                            break;
                        case 2:
                            setPosicao(p, jogador2, 185, 139);
                            break;
                        case 3:
                            setPosicao(p, jogador3, 185, 174);
                            break;
                        case 4:
                            setPosicao(p, jogador4, 240, 104);
                            break;
                        case 5:
                            setPosicao(p, jogador5, 240, 139);
                            break;
                        case 6:
                            setPosicao(p, jogador6, 240, 174);
                            break;
                    }

                } else {
                    switch (player.getPino()) {
                        case 1:
                            setPosicao(p, jogador1, 185, 104);
                            break;
                        case 2:
                            setPosicao(p, jogador2, 185, 139);
                            break;
                        case 3:
                            setPosicao(p, jogador3, 185, 174);
                            break;
                        case 4:
                            setPosicao(p, jogador4, 240, 104);
                            break;
                        case 5:
                            setPosicao(p, jogador5, 240, 139);
                            break;
                        case 6:
                            setPosicao(p, jogador6, 240, 174);
                            break;
                    }
                }

            }
        });
        correio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();

                int num = random.nextInt(6) + 1;

                if (num == 1) {
                    JOptionPane.showMessageDialog(null, " PUXE MAIS  UMA CARTA !");

                } else if (num == 2) {
                    JOptionPane.showMessageDialog(null, "DINHEIRO EXTRA \n VOCÊ VAI RECEBER O VALOR  DE SUA CASA EM DINHEIRO !");
                    String name = JOptionPane.showInputDialog(rootPane, "PARA ISSO INDICO O NOME  DO JOGADOR QUE VAI PAGAR \n ESTE VALOR A VOCÊ  * 100", JOptionPane.QUESTION_MESSAGE);

                } else if (num == 3) {
                    JOptionPane.showMessageDialog(null, " VOCÊ VAI PODER JOGAR MAIS  UM VEZ O DADO !");

                } else if (num == 4) {
                    JOptionPane.showMessageDialog(null, "PAGUE  A SEU VIZINHO AGORA  \n  VOCÊ PAGAR  AO SEU VIZINHO QUE VOCÊ DEVE \n  O VALOR INDICADOR PELO NUMERO QUE SUA CASA  * $100!");

                } else if (num == 5) {
                    String vl = JOptionPane.showInputDialog(rootPane, " INSIRA  O VALOR  QUE  VOCÊ  VAI VENDER SUA  CARTA :", JOptionPane.QUESTION_MESSAGE);
                    int R$ = Integer.parseInt(vl);

                } else if (num == 6) {
                    String fsa = JOptionPane.showInputDialog(rootPane, "DOAÇÃO VOCÊ VAI DOAR O VALOR PRESENTE  EM QUEM TIROU SORTE GRANDE\n  QUE É  DE INSIRA  AI :", JOptionPane.QUESTION_MESSAGE);
                    int ba = Integer.parseInt(fsa);
                }

            }
        });

    }
    //private javax.swing.JList<String> jList1;
    //
    /** inser  na tela  mais  alguns  imagens  e algns componetes e criando ação mais importente  do jogo que é do dado. <br> */

    private void setLayoutPainel2() {

        JPanel painel2 = new JPanel();
        JPanel branco = new JPanel();
        JButton jogar = new JButton("Jogar");
        JButton sair = new JButton("");
        JLabel sala = new JLabel("Jogador: " + player.getUsername());
        JLabel saldo = new JLabel("Seu saldo é: " + player.getSaldo());
        JLabel dado = new JLabel(new ImageIcon("img/dadoPreto.gif"));

        painel2.setBounds(980, 320, 190, 200);
        painel2.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Opções ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 2, 20), Color.RED));
        painel2.setBackground(Color.WHITE);

        branco.setBackground(Color.WHITE);
        branco.setBounds(980, 540, 190, 130);
        branco.setBorder(new LineBorder(Color.BLACK, 2, true));

        jogar.setBounds(1015, 410, 120, 50);
        jogar.setFont(new Font("Tahoma", 0, 20));

        sair.setBounds(1015, 470, 120, 30);
        sair.setFont(new Font("Tahoma", 0, 15));
        sair.setBackground(Color.yellow);
        sala.setBounds(1000, 330, 180, 50);
        sala.setFont(new Font("Tahoma", 0, 17));

        saldo.setBounds(1000, 360, 180, 50);
        saldo.setFont(new Font("Tahoma", 0, 17));

        dado.setBounds(995, 535, 150, 150);

        principal.add(sala);
        principal.add(saldo);
        principal.add(jogar);
        principal.add(sair);
        principal.add(painel2);
        principal.add(dado);
        principal.add(branco);

        jogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();

                int Dado = random.nextInt(6) + 1;

                p = player.getPosition() + Dado;
                JOptionPane.showMessageDialog(null, "SEU NÚMERO FOI: " + Dado);
//<<<<<<< HEAD
                // passar o valor do dado para todos os tabuleiros

                player.setPosition(p);
                jogadores.remove(player);
                jogadores.add(player);
                for (Player mPlayer : jogadores) {
                    System.out.println(mPlayer.getIp());
                    if (mPlayer.getId() != player.getId()) {
                        StringTokenizer st = new StringTokenizer(mPlayer.getIp());
                        String ip = st.nextToken();
                        //System.out.println(ip);
                        JSONObject j = new JSONObject(player);
                        System.out.println("OBJ: " + j.toString());
                        try {
                            //j.put("teste", "teste");

                            String s = con.comunicacao(j, ip);
                            System.out.println(s);
                        } catch (IOException ex) {
                            Logger.getLogger(TabuleiroNovo.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TabuleiroNovo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "SEU NÚMERO FOI: " + Dado);
                if (Dado == 6) {

                    JOptionPane.showMessageDialog(null, "VOCÊ CAIU NA SORTE GRANDE! \n VOCÊ VAI PODER PUXA 1 CARTAS COMPRAS \n  E PODE VAI GANHAR TODO O DINHEIRO QUE TEM NO BANCO! ");
                }

                JOptionPane.showMessageDialog(null, "SEU NÚMERO FOI: " + Dado);

                if (player.getPosition() >= 31) {

                    //p = 31;
                    player.setPosition(31);
                    JOptionPane.showMessageDialog(null, "VOCÊ CHEGOU NO FIM DO MÊS");

                    switch (player.getPino()) {
                        case 1:
                            setPosicao(player.getPosition(), jogador1, 185, 104);
                            break;
                        case 2:
                            setPosicao(player.getPosition(), jogador2, 185, 139);
                            break;
                        case 3:
                            setPosicao(player.getPosition(), jogador3, 185, 174);
                            break;
                        case 4:
                            setPosicao(player.getPosition(), jogador4, 240, 104);
                            break;
                        case 5:
                            setPosicao(player.getPosition(), jogador5, 240, 139);
                            break;
                        case 6:
                            setPosicao(player.getPosition(), jogador6, 240, 174);
                            break;
                    }

                } else {
                    switch (player.getPino()) {
                        case 1:
                            setPosicao(player.getPosition(), jogador1, 185, 104);
                            break;
                        case 2:
                            setPosicao(player.getPosition(), jogador2, 185, 139);
                            break;
                        case 3:
                            setPosicao(player.getPosition(), jogador3, 185, 174);
                            break;
                        case 4:
                            setPosicao(player.getPosition(), jogador4, 240, 104);
                            break;
                        case 5:
                            setPosicao(player.getPosition(), jogador5, 240, 139);
                            break;
                        case 6:
                            setPosicao(player.getPosition(), jogador6, 240, 174);
                            break;
                    }
                }
            }
        });
    }

    private void setLayoutPainel3() {
        JPanel painel3 = new JPanel();
        painel3.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Suas Cartas ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 2, 18), Color.RED));
        painel3.setBounds(20, 10, 150, 663);
        painel3.setBackground(Color.WHITE);
        principal.add(painel3);
    }

    private void setLayoutPainel4() {
        JLabel tab = new JLabel(new ImageIcon("img/tabuleiro.jpg"));
        tab.setBounds(180, 25, 785, 645);
        principal.add(tab);
    }

    public static List<Player> getJogadores() {
        return jogadores;
    }

}
