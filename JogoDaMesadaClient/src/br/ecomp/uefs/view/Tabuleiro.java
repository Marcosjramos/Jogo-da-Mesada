package br.ecomp.uefs.view;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Ramos
 */
public class Tabuleiro extends javax.swing.JFrame {

    private String nomeSala;
    private int semana = 1;
    private int numDado;
    private int posicaoInicialX;
    private int posicaoInicialY;
    private JLabel pino = null;

    public Tabuleiro() {
        initComponents();
        setIcon();
    }
                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pinoPreto = new javax.swing.JLabel();
        pinoAmarelo = new javax.swing.JLabel();
        pinoVerde = new javax.swing.JLabel();
        pinoBranco = new javax.swing.JLabel();
        pinoAzul = new javax.swing.JLabel();
        pinoVermelho = new javax.swing.JLabel();
        labelSorteGrande = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Mesada");

        jPanel1.setLayout(null);

       // pinoPreto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pinoPreto.png"))); // NOI18N
        jPanel1.add(pinoPreto);
        pinoPreto.setBounds(30, 110, 20, 31);

       // pinoAmarelo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pinoAmarelo.png"))); // NOI18N
        jPanel1.add(pinoAmarelo);
        pinoAmarelo.setBounds(20, 90, 16, 31);

       // pinoVerde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pinoVerde.png"))); // NOI18N
        jPanel1.add(pinoVerde);
        pinoVerde.setBounds(40, 90, 16, 31);

        //pinoBranco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pinoBranco.png"))); // NOI18N
        jPanel1.add(pinoBranco);
        pinoBranco.setBounds(50, 110, 16, 31);

       // pinoAzul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pinoAzul.png"))); // NOI18N
        jPanel1.add(pinoAzul);
        pinoAzul.setBounds(60, 90, 16, 31);

       // pinoVermelho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pinoVermelho.png"))); // NOI18N
        jPanel1.add(pinoVermelho);
        pinoVermelho.setBounds(70, 110, 16, 31);

        labelSorteGrande.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelSorteGrande.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(labelSorteGrande);
        labelSorteGrande.setBounds(530, 560, 200, 50);

      //  jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/a_3.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 650);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Cartas Correio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Cartas para Compras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Suas Cartas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 18))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 18))); // NOI18N

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jButton4.setText("Jogar ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Sair");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("SEU SALDO É:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Sala:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        dispose();
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Random random = new Random();
        numDado = random.nextInt(6) + 1;
        JOptionPane.showMessageDialog(null, "SEU NÚMERO FOI: " + numDado);
        movimentacaoPino(3);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        mostrarCartas();
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        JOptionPane.showMessageDialog(null, "VOCÊ TIROU A CARTA: ");
    }                                        

    /**
     *
     */
    /*
    public void iniciarJogo() {
        pinoAmarelo.setLocation(20, 90);
        pinoVerde.setLocation(45, 90);
        pinoAzul.setLocation(70, 90);
        pinoPreto.setLocation(30, 110);
        pinoBranco.setLocation(55, 110);
        pinoVermelho.setLocation(80, 110);
    }
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tabuleiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tabuleiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tabuleiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tabuleiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tabuleiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel labelSorteGrande;
    private javax.swing.JLabel pinoAmarelo;
    private javax.swing.JLabel pinoAzul;
    private javax.swing.JLabel pinoBranco;
    private javax.swing.JLabel pinoPreto;
    private javax.swing.JLabel pinoVerde;
    private javax.swing.JLabel pinoVermelho;
    // End of variables declaration                   

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
        jLabel4.setText(nomeSala);
    }

    private void setIcon() {
      //  setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagens/iconeJogoMesada.jpg")));
    }

    private void movimentacaoPino(int numJogador) {
        pino = retornarPino(numJogador);

        for (int x = 0; x < numDado; x++) {
            if (pino.getLocation().x > 650) {
                semana++;
                labelSorteGrande.setText("R$:" + "5000");
                pino.setLocation(posicaoInicialX, posicaoInicialY + 120);
                alterarPosicao(numJogador, pino);
            } else {
                System.out.println(pino.getLocation().x);
                pino.setLocation(pino.getLocation().x + 115, pino.getLocation().y);
            }
        }
    }

    private JLabel retornarPino(int numJogador) {
        switch (numJogador) {
            case 1:
                pino = pinoAmarelo;
                if (semana == 1) {
                    posicaoInicialX = 20;
                    posicaoInicialY = 90;
                }
                break;
            case 2:
                pino = pinoVerde;
                if (semana == 1) {
                    posicaoInicialX = 40;
                    posicaoInicialY = 90;
                }
                break;
            case 3:
                pino = pinoAzul;
                if (semana == 1) {
                    posicaoInicialX = 60;
                    posicaoInicialY = 90;
                }
                break;
            case 4:
                pino = pinoPreto;
                if (semana == 1) {
                    posicaoInicialX = 30;
                    posicaoInicialY = 110;
                }
                break;
            case 5:
                pino = pinoBranco;
                if (semana == 1) {
                    posicaoInicialX = 50;
                    posicaoInicialY = 110;
                }
                break;
            case 6:
                pino = pinoVermelho;
                if (semana == 1) {
                    posicaoInicialX = 70;
                    posicaoInicialY = 110;
                }
                break;
        }
        return pino;
    }

    private void alterarPosicao(int numJogador, JLabel pino) {
        switch (numJogador) {
            case 1:
                posicaoInicialX = pino.getLocation().x;
                posicaoInicialY = pino.getLocation().y;
                break;
            case 2:
                posicaoInicialX = pino.getLocation().x;
                posicaoInicialY = pino.getLocation().y;
                break;
            case 3:
                posicaoInicialX = pino.getLocation().x;
                posicaoInicialY = pino.getLocation().y;
                break;
            case 4:
                posicaoInicialX = pino.getLocation().x;
                posicaoInicialY = pino.getLocation().y;
                break;
            case 5:
                posicaoInicialX = pino.getLocation().x;
                posicaoInicialY = pino.getLocation().y;
                break;
            case 6:
                posicaoInicialX = pino.getLocation().x;
                posicaoInicialY = pino.getLocation().y;
                break;
        }
    }

    private void mostrarCartas() {
        Font fonte = new Font("Tahoma", Font.BOLD, 16);

        System.out.println("AQUI!");
        JLabel carta = new JLabel("Carta");
        carta.setFont(fonte);
        JLabel carta3 = new JLabel("VALOR: 900,00");
        JLabel carta2 = new JLabel("---------------------------------");
        carta.setText("CARTA");
        jPanel3.setLayout(new GridLayout(22, 1, 0, 8));
        jPanel3.add(carta2);
        jPanel3.add(carta);
        jPanel3.add(carta3);
        jPanel3.repaint();
        jPanel3.revalidate();
    }
}
