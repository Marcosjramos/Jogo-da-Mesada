/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author cassio
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */

    
    public static void main(String[] args) {
        //int idCliente = 0;
        
        try {
            System.out.println("[Criando Servidor...]");
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("[Servidor operando na porta 1234]");
            while (true) {
                System.out.println("[Esperando conexão...]");
                Socket cliente = servidor.accept();
               // new Contador(idCliente++, cliente).start();
               new Game(cliente).start();
            }
        } catch (Exception e) {
            System.out.println("Erro!\n"+e.getMessage());
        }
    }

 

}
