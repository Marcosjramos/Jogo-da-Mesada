/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ecomp.uefs.view;

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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author JhansenBarreto
 */
public final class TabuleiroNovo extends JFrame {
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
    
    public TabuleiroNovo(){
        super("JOGO DA MESADA");
        
        this.p = 0;        
        this.principal.setLayout(null);
        this.principal.setBackground(Color.WHITE);
        
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
    }
     public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
        jLabel4.setText(nomeSala);
    }
    
    public void init(){
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
    
    public void setPosicao(int x){
        if(x == 0){
            jogador1.setBounds(185, 104, 50, 50);
        }
        if(x == 1){
            jogador1.setBounds(295, 104, 50, 50);
        }
        else if(x == 2){
            jogador1.setBounds(410, 104, 50, 50);
        }
        else if(x == 3){
            jogador1.setBounds(520, 104, 50, 50);
        }
        else if(x == 4){
            jogador1.setBounds(630, 104, 50, 50);
        }
        else if(x == 5){
            jogador1.setBounds(742, 104, 50, 50);
        }
        else if(x == 6){
            jogador1.setBounds(854, 104, 50, 50);
        }
        else if(x == 7){
            jogador1.setBounds(185, 218, 50, 50);
        }
        else if(x == 8){
            jogador1.setBounds(295, 218, 50, 50);
        }
        else if(x == 9){
            jogador1.setBounds(410, 218, 50, 50);
        }
        else if(x == 10){
            jogador1.setBounds(520, 218, 50, 50);
        }
        else if(x == 11){
            jogador1.setBounds(630, 218, 50, 50);
        }
        else if(x == 12){
            jogador1.setBounds(742, 218, 50, 50);
        }
        else if(x == 13){
            jogador1.setBounds(854, 218, 50, 50);
        }
        else if(x == 14){
            jogador1.setBounds(185, 330, 50, 50);
        }
        else if(x == 15){
            jogador1.setBounds(295, 330, 50, 50);
        }
        else if(x == 16){
            jogador1.setBounds(410, 330, 50, 50);
        }
        else if(x == 17){
            jogador1.setBounds(520, 330, 50, 50);
        }
        else if(x == 18){
            jogador1.setBounds(630, 330, 50, 50);
        }
        else if(x == 19){
            jogador1.setBounds(742, 330, 50, 50);
        }
        else if(x == 20){
            jogador1.setBounds(854, 330, 50, 50);
        }
        else if(x == 21){
            jogador1.setBounds(185, 442, 50, 50);
        }
        else if(x == 22){
            jogador1.setBounds(295, 442, 50, 50);
        }
        else if(x == 23){
            jogador1.setBounds(410, 442, 50, 50);
        }
        else if(x == 24){
            jogador1.setBounds(520, 442, 50, 50);
        }
        else if(x == 25){
            jogador1.setBounds(630, 442, 50, 50);
        }
        else if(x == 26){
            jogador1.setBounds(742, 442, 50, 50);
        }
        else if(x == 27){
            jogador1.setBounds(854, 442, 50, 50);
        }
        else if(x == 28){
            jogador1.setBounds(185, 554, 50, 50);
        }
        else if(x == 29){
            jogador1.setBounds(295, 554, 50, 50);
        }
        else if(x == 30){
            jogador1.setBounds(410, 554, 50, 50);
        }
        else if(x == 31){
            jogador1.setBounds(520, 554, 50, 50);
        }
    }
    
    public static void main(String[] args) {
        new TabuleiroNovo();
    }
    
    private void setLayoutPainel1(){
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
    
    private void setLayoutPainel2(){
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
                if(p == 31){
                    p = -1;
                }
                p++;
                setPosicao(p);
            }
        });
    }
    
    private void setLayoutPainel3(){
        JPanel painel3 = new JPanel();
        painel3.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Suas Cartas ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 2, 18), Color.RED));
        painel3.setBounds(20, 10, 150, 663);
        painel3.setBackground(Color.WHITE);
        principal.add(painel3);
    }
    
    private void setLayoutPainel4(){
        JLabel tab = new JLabel(new ImageIcon("img/tabuleiro.jpg"));
        tab.setBounds(180, 25, 785, 645);
        principal.add(tab);
    }
}