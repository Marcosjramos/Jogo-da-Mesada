/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.view;

import br.com.uefs.conexao.ConexaoServidor;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class Painel extends JFrame{
    private JTextField tfValor1;
    private JButton button;
    private JLabel texto1, texto2;
    ConexaoServidor servidor;
    public Painel(){
        super("Painel");
        servidor = new ConexaoServidor();
        texto1 = new JLabel("Valor");
        button = new JButton("Button");
        texto2 = new JLabel();
        Container cp = getContentPane(); // obtém container
        cp.setLayout(new GridLayout(3,2,5,5));// ajuste do layout
        cp.add(texto1); cp.add(tfValor1);// edição dos componestes
        cp.add(button); cp.add(texto2);
       new ConexaoServidor().start();
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    JSONObject j = servidor.buscarSalas(1);
                    texto2.setText(j.toString());
                } catch (JSONException ex) {
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    public static void main(String a[]){
        new Painel().setVisible(true);
    }
}
