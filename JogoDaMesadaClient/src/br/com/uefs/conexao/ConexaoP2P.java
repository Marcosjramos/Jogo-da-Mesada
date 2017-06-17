/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * Classe responsavel por conectar aos servidores dos clientes remotos
 * @author cassio
 */
public class ConexaoP2P extends Thread {

    public static ObjectOutputStream saida;
    public static ObjectInputStream entrada;
    private static ConexaoP2P uniqueInstance;
    Socket conexao;

    public ConexaoP2P() {

    }

    public static synchronized ConexaoP2P getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConexaoP2P();
        }
        return uniqueInstance;
    }

    public void run() {
    }
    
    /**
     *  Metodo responsavel por troca de dados entre usuários
     * @param j JSOMObject que é passado por parametro para todos os clientes
     * @param ip Endereço ip do ciente remoto
     * @return mensagem com a resposta do servidor 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
     public String comunicacao(JSONObject j, String ip) throws IOException, ClassNotFoundException {
         
            String mensagem = "";
        
           try (Socket conexao = new Socket(ip, 1803);) {
            System.out.printf("[Conexao aceita de: %s]\n", conexao.getInetAddress().toString());
            
            this.conexao = conexao;
            saida = new ObjectOutputStream(conexao.getOutputStream());
            entrada = new ObjectInputStream(conexao.getInputStream());
            saida.flush();
            
            saida.writeObject(j.toString());
            String msg = "";
            do{
                
                msg = (String) entrada.readObject();
                System.out.println(msg);
                if (!msg.equals("EOT")) {
                        mensagem = msg;
                }
            } while (!msg.equals("EOT"));
        } catch (IOException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
           return mensagem;
    }

}
