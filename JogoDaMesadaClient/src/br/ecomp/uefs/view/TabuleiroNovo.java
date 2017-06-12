/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.view;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Ramos
 */
public final class TabuleiroNovo extends JFrame {

    private Player player;
    private final Container principal = getContentPane();
    private final JLabel jogador1;
    private final JLabel jogador2;
    private final JLabel jogador3;
    private final JLabel jogador4;
    private final JLabel jogador5;
    private final JLabel jogador6;
    private String nomeSala;
    private javax.swing.JLabel jLabel4;
    private int p;

    static List<Player> jogadores;

    public TabuleiroNovo(Player player, List<Player> mJogadores) {
        super("JOGO DA MESADA");

        jogadores = mJogadores;
        this.player = player;
        System.out.println(this.player.getPino());
        this.p = 0;
        this.principal.setLayout(null);
        this.principal.setBackground(Color.WHITE);

        //for (int i=0; i < 6; i ++) {
        this.jogador1 = new JLabel(new ImageIcon("img/amarela.png"));
        this.jogador2 = new JLabel(new ImageIcon("img/pink.png"));
        this.jogador3 = new JLabel(new ImageIcon("img/branca.png"));
        this.jogador4 = new JLabel(new ImageIcon("img/laranja.png"));
        this.jogador5 = new JLabel(new ImageIcon("img/azul.png"));
        this.jogador6 = new JLabel(new ImageIcon("img/vermelha.png"));

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

        for (Player p : jogadores) {
            System.out.println(p.getIp());
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("[Criando Servidor...]");
                    ServerSocket servidor = new ServerSocket(123);
                    System.out.println("[Servidor operando na porta 123]");
                    while (true) {
                        System.out.println("[Esperando conexão...]");
                        Socket cliente = servidor.accept();
                        //new Contador(idCliente++, cliente).start();
                        new ServidorLocal(cliente).start();
                    }
                } catch (Exception e) {
                    System.out.println("Erro!\n" + e.getMessage());
                }
                // super.run(); //To change body of generated methods, choose Tools | Templates.
            }

        };
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
        jLabel4.setText(nomeSala);
    }

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

    public void setPosicao(int mP, JLabel jogador, int x, int y) {
        if (mP == 0) {
            jogador.setBounds(x, y, 50, 50);
        }
        if (mP == 1) {
            jogador.setBounds(x+110, y, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA CORREIOS  ! ");
            JOptionPane.showMessageDialog(null, "PUXE 1 CARTA DO TIPO CORREIOS!");
            // exemplo de  com vai ficar as outras cartas do jogo  o que você acha 
        } else if (mP == 2) {
            jogador.setBounds(x+225, y, 50, 50);
            JOptionPane.showMessageDialog(null, "CASA COMPRAS E ENTRETENIMENTOS ! ");
            JOptionPane.showMessageDialog(null, "PUXE 1 COMPRAS E ENTRETENIMENTOS ! ");
        } else if (mP == 3) {
            jogador.setBounds(x-335, y, 50, 50);
            String valor = JOptionPane.showInputDialog(rootPane, "INSIARA $100", JOptionPane.QUESTION_MESSAGE);
            int v = Integer.parseInt(valor);
            JOptionPane.showMessageDialog(null, "VALOR INSERIDO COM SUCESSO: " + v);
        } else if (mP == 4) {
            jogador.setBounds(x+445, y, 50, 50);
            boolean premio = false;

            JOptionPane.showMessageDialog(null, "PRÊMIO!- RETIRE $5.000 DO BANCO !");

            premio = true;

        } else if (mP == 5) {
            jogador.setBounds(x+557, y, 50, 50);
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
            jogador.setBounds(x+669, y, 50, 50);
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
            jogador.setBounds(x, y+114, 50, 50);
            JOptionPane.showMessageDialog(null, "MARATONA BENEFICENTE!");

            JOptionPane.showMessageDialog(null, "TODO MUNDO VAI JOGAR O DADO AÍ ,\n E CADA UMA AÍ VAI  TER  QUE PAGARA $100  vezes x  \n QUE ELES SORTEAREM!");

            JOptionPane.showMessageDialog(null, " FOI PESSOA !");
        } else if (mP == 8) {
            jogador.setBounds(x+110, y+114, 50, 50);

            JOptionPane.showMessageDialog(null, " VENDE-SE ! \n NEGÓCIO DE OCASIÃO ! \n SEU POR APENAS $100 VEZES O NÚMERO QUE FOI SORETEADO");
            int a = 8 * 100;
            JOptionPane.showMessageDialog(null, "  NO CASO  :!" + a + " VALOR QUE O BANCO VAI PAGAR ");
            JOptionPane.showMessageDialog(null, " PUXE  1 CARTA  DE COMPRA E ENTRETENIMENTOS !");
        } else if (mP == 9) {
            jogador.setBounds(x+225, y+114, 50, 50);
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
            jogador.setBounds(x+335, y+114, 50, 50);
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
            jogador.setBounds(x+630, y+114, 50, 50);
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
            jogador.setBounds(x+557, y+114, 50, 50);
        } else if (mP == 13) {
            jogador.setBounds(x+669, y+114, 50, 50);
        } else if (mP == 14) {
            jogador.setBounds(x, y+226, 50, 50);
        } else if (mP == 15) {
            jogador.setBounds(x+110,y+226, 50, 50);
        } else if (mP == 16) {
            jogador.setBounds(x+225, y+226, 50, 50);
        } else if (mP == 17) {
            jogador.setBounds(x+335, y+226, 50, 50);
        } else if (mP == 18) {
            jogador.setBounds(x+445, y+226, 50, 50);
        } else if (mP == 19) {
            jogador.setBounds(x+557, y+226, 50, 50);
        } else if (mP == 20) {
            jogador.setBounds(x+669, y+226, 50, 50);
            //
        } else if (mP == 21) {
            jogador.setBounds(x, y+338, 50, 50);
        } else if (mP == 22) {
            jogador.setBounds(x+110, y+338, 50, 50);
        } else if (mP == 23) {
            jogador.setBounds(x+225, y+338, 50, 50);
        } else if (mP == 24) {
            jogador.setBounds(x+335, y+338, 50, 50);
        } else if (mP == 25) {
            jogador.setBounds(x+445, y+338, 50, 50);
        } else if (mP == 26) {
            jogador.setBounds(x+557, y+338, 50, 50);
        } else if (mP == 27) {
            jogador.setBounds(x+669, y+338, 50, 50);
        } else if (mP == 28) {
            jogador.setBounds(x, y+369, 50, 50);
        } else if (mP == 29) {
            jogador.setBounds(x+110, y+369, 50, 50);
        } else if (mP == 30) {
            jogador.setBounds(x+225, y+369, 50, 50);
        } else if (mP == 31) {
            jogador.setBounds(x+335, y+369, 50, 50);
        }
    }

    /*public static void main(String[] args) {
        new TabuleiroNovo();
    }*/
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
    }

    private void setLayoutPainel2() {

        JPanel painel2 = new JPanel();
        JPanel branco = new JPanel();
        JButton jogar = new JButton("Jogar");
        JButton sair = new JButton("Sair");
        JLabel sala = new JLabel("Sala: ");
        JLabel saldo = new JLabel("Seu saldo é: ");
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

                p = p + Dado;
                JOptionPane.showMessageDialog(null, "SEU NÚMERO FOI: " + Dado);
                // passar o valor do dado para todos os tabuleiros

                JOptionPane.showMessageDialog(null, "SEU NÚMERO FOI: " + Dado);

                if (p >= 31) {

                    p = 31;

                    JOptionPane.showMessageDialog(null, "VOCÊ CHEGOU NO FIM DO MÊS");
                      
                    switch (player.getPino()) {
                        case 1:
                            setPosicao(p, jogador1, 185,104);
                            break;
                        case 2:
                            setPosicao(p, jogador2, 185,139);
                            break;
                        case 3:
                            setPosicao(p, jogador3, 185,174);
                            break;
                        case 4:
                            setPosicao(p, jogador4, 240,104);
                            break;
                        case 5:
                            setPosicao(p, jogador5, 240,139);
                            break;
                        case 6:
                            setPosicao(p, jogador6, 240, 174);
                            break;
                    }

                } else {

                       switch (player.getPino()) {
                        case 1:
                            setPosicao(p, jogador1, 185,104);
                            break;
                        case 2:
                            setPosicao(p, jogador2, 185,139);
                            break;
                        case 3:
                            setPosicao(p, jogador3, 185,174);
                            break;
                        case 4:
                            setPosicao(p, jogador4, 240,104);
                            break;
                        case 5:
                            setPosicao(p, jogador5, 240,139);
                            break;
                        case 6:
                            setPosicao(p, jogador6, 240, 174);
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
