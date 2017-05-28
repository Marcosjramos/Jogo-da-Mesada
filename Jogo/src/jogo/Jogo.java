/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import br.com.uefs.conexao.ConexaoP2P;
import br.com.uefs.conexao.ConexaoServidor;
import br.com.uefs.view.Painel;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cassio
 */
public class Jogo {

    /**
     * @param args the command line arguments
     */
//    ConexaoServidor c = new ConexaoServidor();
    Painel p = new Painel();
    public static void main(String[] args) {
        // TODO code application logic here
        int idCliente = 0;
        try {
            System.out.println("[Criando Servidor...]");
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("[Servidor operando na porta 12345]");
           // new ConexaoServidor().start();
         //   c.setOp(1);
            while (true) {
                System.out.println("[Esperando conexão...]");
                Socket cliente = servidor.accept();
                //System.out.println("Teste1");
                // new Contador(idCliente++, cliente).start();
                //System.out.println("Teste2");
                new ConexaoP2P(cliente).start();
                //System.out.println("Teste3");
            }
        } catch (Exception e) {
            System.out.println("Erro!\n" + e.getMessage());
        }

    

    }
}
