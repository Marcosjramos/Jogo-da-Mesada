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
        con = ConexaoServidor.getInstance(); // starta o jogador 
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

    public void setNomeSala(String nome) {
        this.nome = nome;
        jLabel4.setText(nome);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();// para  inseir  da   lista de sala 
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Mesada");

        jLabel1.setFont(new java.awt.Font("Ravie", 0, 36)); // NOI18N
        jLabel1.setText("Jogo da Mesada");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 24)));

        jScrollPane1.setViewportView(jList1);

        jButton1.setFont(new java.awt.Font("Verdana", 1, 18));
        jButton1.setText("Cadastrar Sala");
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

        jButton2.setFont(new java.awt.Font("Verdana", 1, 18));
        jButton2.setText("Entrar na Sala");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
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
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap())
        );

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Sair");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws JSONException, IOException, ClassNotFoundException {
        String nomeSala = JOptionPane.showInputDialog(rootPane, "Digite o nome da sala:", "Jogo da Mesada", JOptionPane.QUESTION_MESSAGE);
        //System.out.println(nomeSala);
        if (!nomeSala.equals("null")) {

            //inserirSala(nomeSala);
            JSONObject j = new JSONObject();
            j.put("status", 2);
            j.put("nome", nomeSala);
            //j.put("id", con.getPlayer().getId());
            //j.put("username", con.getPlayer().getUsername());
            String msg = con.comunicacao(j);
            dlm.clear();
            if (msg != null) {
                JSONArray ja = new JSONArray(msg);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    //  inserirSala("Sala: "+jo.getString("nome")+" jogadores: "+jo.get("n"));
                    inserirSala(jo.getInt("id") + " " + jo.getString("nome") + " jogadores: " + jo.get("n"));
                }
            } else {
                inserirSala("Cadastre uma sala");
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws JSONException, IOException, ClassNotFoundException, InterruptedException {
        //System.out.println(jList1.getSelectedValue());
        //  JSONObject j = new JSONObject((String) jList1.getSelectedValue());
        //j.put("status", 4);
        //j.put("", value).
        //System.out.println(j.toString());
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
                               System.out.println("\n Pinos: "+ jogador.getPino());
                               jogador.setUsername(jo.getString("username"));
                               jogadores.add(jogador);
                               if (jogador.getIp().equals(con.getPlayer().getIp())){
                                   con.getPlayer().setPino(jogador.getPino());
                               }
                        }
                        //super.run(); //To change body of generated methods, choose Tools | Templates.
                    } catch (JSONException ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                   TabuleiroNovo tabuleiro = new TabuleiroNovo(con.getPlayer(), jogadores);
                   tabuleiro.setNomeSala(jList1.getSelectedValue());
                   tabuleiro.setVisible(true);
                   dispose();
                }

            }.start();

            //System.out.println(s);
            /* Tabuleiro tabuleiro = new Tabuleiro();
            tabuleiro.setNomeSala(jList1.getSelectedValue());
            tabuleiro.setVisible(true);
            dispose();
             */
            //TabuleiroNovo tabuleiro = new TabuleiroNovo();
            //tabuleiro.setNomeSala(jList1.getSelectedValue());
            //tabuleiro.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Antes de jogar, escolha ou das salas!");
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /*public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windowns".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }*/

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jLabel4;

    private void inserirSala(String nomeSala) {
        dlm.addElement(nomeSala);
        jList1.setModel(dlm);
    }
}
