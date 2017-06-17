/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.conexao;


import br.com.uefs.model.Player;
import br.com.uefs.model.Sala;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *Classe responsavel pela comunicação com o servidor 
 * @author cassio
 */
public class ConexaoServidor extends Thread {
    //private int op;

    //private JSONObject request;
    // private int op;
    private String salas;
    private Player player;
    private static ConexaoServidor uniqueInstance;
    public static ObjectOutputStream saida;
    public static ObjectInputStream entrada;
    Socket conexao;
    public ConexaoServidor() {
        //request = new JSONObject();
    }

    //public ConexaoServidor(ObjectOutputStream saida, ObjectInputStream entrada) {
    //  }
    public static synchronized ConexaoServidor getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConexaoServidor();
        }
        return uniqueInstance;
    }

    public void run() {
    }

    /**
     * Metodo responsavel pela comunicação com o servidor remoto
     * @param j JSONObject com os dados do usuário
     * @return retorna uma string cons os valores requisitados
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public String comunicacao(JSONObject j) throws IOException, ClassNotFoundException {

            String mensagem = "";
        
           try (Socket conexao = new Socket("192.168.1.108", 1234);) {
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
                if (!msg.equals("EOT")) {
                    mensagem = msg;
                }
            } while (!msg.equals("EOT"));
        } catch (IOException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

           return mensagem;
    }
        
    
    
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public String getSalas() {
        return salas;
    }
    public void setSalas(String salas) {
        this.salas = salas;
    }

}
