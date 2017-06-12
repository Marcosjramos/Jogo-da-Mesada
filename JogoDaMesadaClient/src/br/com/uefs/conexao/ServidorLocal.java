/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.conexao;

import br.com.uefs.model.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cassio
 */
public class ServidorLocal extends Thread {
     
    //private List<Player> jogdores;
    
    private Socket conexao; //conexão com cliente remoto
    int i = 0;
    public ServidorLocal(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        System.out.printf(conexao.getInetAddress().toString());
        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());) {
                System.out.println("Esperando conexão");
                
               System.out.println(entrada.toString());
         
        } catch (IOException ex) {
            Logger.getLogger(ServidorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
