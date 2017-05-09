/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
    /*public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("[Criando Servidor...");
        try (ServerSocket servidor = new ServerSocket(1234);) {
            System.out.println("[Servidor esperando na porta 1234]");
            while (true) {
                System.out.println("[Esperando conexao...]");
                Socket cliente = servidor.accept(); //aceita conexao
                System.out.println("[Conexão aberta de: "+cliente.getInetAddress().toString()+"]");
                System.out.println("[Enviando dados...]");
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                saida.flush(); //Enviando cabeçalho de preparo do outro endpoint
                saida.writeObject("Servidor basico conectado");
                saida.writeObject("Dados conexão: "+ cliente.toString());
                 saida.writeObject("Tchau");
                saida.writeObject("EOT");
                cliente.close();
                System.out.println("[Conexão enserrada] ");
            }
        } catch (IOException e) {
            System.out.println("Error!:\n" + e.getMessage());
        }
    }*/
    public static void main(String[] args) {
        int idCliente = 0;
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
