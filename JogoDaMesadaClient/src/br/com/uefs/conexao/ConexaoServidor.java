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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class ConexaoServidor extends Thread {
    //private int op;

    //private JSONObject request;
    // private int op;
    private String salas;
    
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

    public String comunicacao(JSONObject j) throws IOException, ClassNotFoundException {
        // String a = "{\"a\":\"a\"}";
        //System.out.println(j.toString());
       // saida.writeObject(j.toString());
        //saida.flush();
        //saida.close();
        //System.out.println((String) entrada.readObject());
        //conexao.close();
            String mensagem = "";
        
           try (Socket conexao = new Socket("40.0.0.105", 1234);) {
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
        
    

    /*public void buscarSalas() throws IOException, JSONException, ClassNotFoundException {
        //Carregar salas
        System.out.println("TESTE");
        JSONObject j = new JSONObject();
        j.put("status", 1); // buscar salas
        System.out.println(j.toString());
        saida.writeObject(j.toString());
        String data = (String) entrada.readObject();
        setSalas(data);
       // System.out.println(getSalas());
        //data = null;
    }*/
 /*public String teste(JSONObject rt) throws JSONException {
        rt.put("DFSDf", "TESE");
        return rt.toString();
    }*/
 /*public String teste(int op) throws JSONException, IOException, ClassNotFoundException{
            switch(getOp()){
                case 1:
                     
                     j.put("status", 0); // buscar salas
                     saida.writeObject(j.toString());
                    String data = (String) entrada.readObject();
                    return data;
                     //i.inserirSala(data);
                   
            }
            return null;
    }*/
    public String getSalas() {
        return salas;
    }

    public void setSalas(String salas) {
        this.salas = salas;
    }

}
