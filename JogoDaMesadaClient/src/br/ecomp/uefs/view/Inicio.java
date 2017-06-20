package br.ecomp.uefs.view;

import br.com.uefs.conexao.ConexaoServidor;
import br.com.uefs.model.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Marcos Ramos
 */
public class Inicio extends javax.swing.JFrame {

    private final DefaultListModel dlm;
    public String nome;
    static ConexaoServidor con;

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        dlm = new DefaultListModel();
        con = ConexaoServidor.getInstance(); /** starta o jogador */
        //con.start();

        JSONObject j = new JSONObject();
        //   System.out.println();
        try {
            j.put("status", 1);
            String msg = null;
            try {
                msg = con.comunicacao(j);
                //System.out.println(msg);
                if (msg != null) {
                    JSONArray ja = new JSONArray(msg);
                    if (ja.length() > 0) {
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo = ja.getJSONObject(i);
                            inserirSala(jo.getInt("id") + " " + jo.getString("nome") + " jogadores: " + jo.get("n"));
                            //System.out.println(jo.toString());
                        }
                    } else {
                        inserirSala("Cadastre uma sala");
                    }
                } else {
                    inserirSala("Cadastre uma sala");
                }
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JSONException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**a função a seguir serve para instância a tela em questão. <br> */
    public void setNomeSala(String nome) {
        this.nome = nome;
        jLabel4.setText(nome);
    }
    /** a função privada  a seguir serve  para criar os objetos  que  serão  usados para criar os compomentes  que serão usados para a interface poder realizar o que foi projetada.<br> */
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane(); /** criação  do scroll  . <br> */
        jList1 = new javax.swing.JList<>(); /** para  inseir  da  lista de sala. <br> */
        jButton1 = new javax.swing.JButton(); /** criação  de um botão que será  usado pela  inteface . <br> */
        jButton2 = new javax.swing.JButton(); /** criação  de um botão que será  usado pela  inteface . <br> */
        jButton3 = new javax.swing.JButton(); /** criação  de um botão que será  usado pela  inteface . <br> */

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Mesada"); /** set o nome da tela  */

        jLabel1.setFont(new java.awt.Font("Ravie", 0, 36)); // NOI18N
        jLabel1.setText("Jogo da Mesada");/** set o nome da interface  */

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 24)));

        jScrollPane1.setViewportView(jList1);

        jButton1.setFont(new java.awt.Font("Verdana", 1, 18));/**  determina  a fonte  e  o tamanho da letra  */
        jButton1.setText("Cadastrar Sala");  /**  colocar o nome da botão  */
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (JSONException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jButton2.setFont(new java.awt.Font("Verdana", 1, 18));/**  determina  a fonte  e  o tamanho da letra  */
        jButton2.setText("Entrar na Sala"); /**  colocar o nome da botão  */
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (JSONException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1); /** criação do scroll para aguarda  muitos cadastros de sala caso necessários */
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) 
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)/** determina  o tamanho da caixa onde o scroll vai funcionar */
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton1)
                                .addGap(31, 31, 31)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE) /** dertemina o  tamanho da tela  */
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap())
        );

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); /**  determina  a fonte  e  o tamanho da letra  */
        jButton3.setText("Sair"); /**  colocar o nome da botão  */
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 36, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton3)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addContainerGap()));
        pack();
    }
    /** a função  privada a seguir  serve para ação de um  botão e  junto  com um poup que vai capturar informação que usuario  vai inserir. <br> */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws JSONException, IOException, ClassNotFoundException {
        String nomeSala = JOptionPane.showInputDialog(rootPane, "Digite o nome da sala:", "Jogo da Mesada", JOptionPane.QUESTION_MESSAGE);
        
        if (!nomeSala.equals("null")) {

            JSONObject j = new JSONObject();
            j.put("status", 2);
            j.put("nome", nomeSala);
            String msg = con.comunicacao(j);
            dlm.clear();
            if (msg != null) {
                JSONArray ja = new JSONArray(msg);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    inserirSala(jo.getInt("id") + " " + jo.getString("nome") + " jogadores: " + jo.get("n"));
                }
            } else {
                inserirSala("Cadastre uma sala");
            }
        }
    }
  /** a função  privada a seguir  serve para ação de um  botão ,colocar uma  mensagem informado  ao jogador para aguardar  após o cadastro
  , determina  o tamanho da tela   e  assim também   instancia  a thread responsável  pela comunicação  entre hosts  e  sevidor . <br> */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws JSONException, IOException, ClassNotFoundException, InterruptedException {
        
        Font fonte = new Font("Tahoma", Font.BOLD, 24);

        if (jList1.getSelectedValue() != null) {

            JLabel label = new JLabel("AGUARDANDO JOGADORES...");
            jPanel1.removeAll();
            jPanel1.setLayout(new BorderLayout());
            jPanel1.setPreferredSize(new Dimension(450, 300));
            label.setFont(fonte);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setForeground(Color.BLUE);
            jPanel1.add(label, BorderLayout.CENTER);
            jPanel1.repaint();
            jPanel1.revalidate();
            //dispose();
           // System.out.println(jList1.getSelectedValue());

            String Str = new String(jList1.getSelectedValue());
            //Posição do caracter na string
            int pos = Str.indexOf(" ");
            //Substring iniciando em 0 até posição do caracter especial
            String idS = Str.substring(0, pos);
            Integer id = Integer.parseInt(idS);
            JSONObject j = new JSONObject();
            j.put("status", 4);
            j.put("sala", id);
            j.put("ip", con.getPlayer().getIp());
            j.put("id", con.getPlayer().getId());
            j.put("username", con.getPlayer().getUsername());

            new Thread() {
                @Override
                public void run() {
                    String s = "";
                    int espera = 0;
                    do {
                        try {
                            j.put("espera", espera);
                            s = con.comunicacao(j);
                            new Thread().sleep(1000);
                            if (espera == 0) {
                                if (s.equals("1")) {
                                    espera = 1;
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JSONException ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //System.out.println("TESTE");
                    } while (s.equals("1"));
                    List<Player> jogadores = new ArrayList<Player>();
                    try {
                        JSONArray ja = new JSONArray(s);
                        for (int i=0; ja.length() > i; i++) {
                               JSONObject jo = (JSONObject) ja.get(i);
                               Player jogador = new Player();
                               jogador.setId(jo.getInt("id"));
                               jogador.setIp(jo.getString("ip"));
                               jogador.setPino(jo.getInt("pino"));
                               jogador.setSaldo(jo.getInt("saldo"));
                               //System.out.println("\n Pinos: "+ jogador.getPino());
                               jogador.setUsername(jo.getString("username"));
                               jogadores.add(jogador);
                               if (jogador.getId() == con.getPlayer().getId()){
                                   con.getPlayer().setPino(jogador.getPino());
                               }
                        }
                        //super.run(); //To change body of generated methods, choose Tools | Templates.
                    } catch (JSONException ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    con.getPlayer().setSaldo(3000);
                   TabuleiroNovo tabuleiro = new TabuleiroNovo(con.getPlayer(), jogadores);
                   tabuleiro.setNomeSala(jList1.getSelectedValue());
                   tabuleiro.setVisible(true);
                   dispose();
                    try {
                        join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }.start();
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Antes de jogar, escolha ou das salas!");
        }
    }
       /**a função a seguir  serve  para detrminação a ação  de sair da tela  no caso fechar  a tela . <br> */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel4;


    /** a função a seguir  serve para  poder instanciar a tela  em questão. <br>*/
    private void inserirSala(String nomeSala) {
        dlm.addElement(nomeSala);
        jList1.setModel(dlm);
    }
}
