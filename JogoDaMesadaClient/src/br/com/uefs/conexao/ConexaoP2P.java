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
 *
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
    
     public String comunicacao(JSONObject j, String ip) throws IOException, ClassNotFoundException {
        // String a = "{\"a\":\"a\"}";
        //System.out.println(j.toString());
       // saida.writeObject(j.toString());
        //saida.flush();
        //saida.close();
        //System.out.println((String) entrada.readObject());
        //conexao.close();
            String mensagem = "";
        
           try (Socket conexao = new Socket(ip, 123);) {
            System.out.printf("[Conexao aceita de: %s]\n", conexao.getInetAddress().toString());
               //System.out.println(conexao.getInetAddress().getHostAddress());
            this.conexao = conexao;
            //ConexaoServidor(saida, entrada)
            saida = new ObjectOutputStream(conexao.getOutputStream());
            entrada = new ObjectInputStream(conexao.getInputStream());
            saida.flush();
            
            saida.writeObject(j.toString());
            String msg = "";
            do{
                
                msg = (String) entrada.readObject();
                System.out.println(msg);
// System.out.println(msg);
                //return mensagem;
                if (!msg.equals("EOT")) {
                    mensagem = msg;
                }
            } while (!msg.equals("EOT"));
            //saida.close();
           // return mensagem;
        } catch (IOException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

           return mensagem;
        //return data;
// teste(j, saida, entrada);
//System.out.println(data);
    }

}
