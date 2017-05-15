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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class ConexaoServidor extends Thread {
        ObjectOutputStream saida;
        ObjectInputStream entrada;
        JSONObject j;
    public void run() {
        System.out.println("Entrou");
        try (Socket conexao = new Socket("192.168.1.157", 1234);) {
            System.out.printf("[Conexao aceita de: %s]\n", conexao.getInetAddress().toString());
            saida = new ObjectOutputStream(conexao.getOutputStream());
            entrada = new ObjectInputStream(conexao.getInputStream());
            saida.flush();
            j = new JSONObject();
            j.put("status", 1); // buscar salas
            saida.writeObject(j.toString());
            String data = (String) entrada.readObject();
            j = new JSONObject(data);
            
            if (j.getInt("status") == 0){
                System.out.println("Nenhuma sala encontrada");
            } else {
                JSONArray ja = new JSONArray(data);
                System.out.println(ja.toString());
            }
            
            System.out.println(data);
            saida.close();
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public JSONObject buscarSalas(int op) throws JSONException, IOException, ClassNotFoundException{
       
        switch (op) {
            case 1: // Listar salas
                  String data = (String) entrada.readObject();
                  j = new JSONObject(data);
                  return j;
                //break;
        }
        return null;
    }
    
 
}
