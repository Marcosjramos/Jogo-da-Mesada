/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.view;

import br.com.uefs.conexao.ConexaoServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class Teste {
    
    public static void main(String a[]){
      //  new ConexaoServidor().run();
        
        final Inicio i = new Inicio();
       // new Inicio();
       //cs.start();
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            // ConexaoServidor  cs = new ConexaoServidor();
             //cs.start();
                
                i.setVisible(true);
                
                try {
                    //new Inicio().setVisible(true);
                    JSONObject j = new JSONObject();
                    try (Socket conexao =  new Socket("40.0.0.105", 1234);) {
                    System.out.printf("[Conexao aceita de: %s]\n", conexao.getInetAddress().toString());
                    ObjectOutputStream saida= new ObjectOutputStream(conexao.getOutputStream());
                    ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());            
                    saida.flush();
                     
          //  i = new Inicio();
                    i.inserirSala(teste(j, saida, entrada));
           
              //JSONObject j = new JSONObject();
              
//            test4(j);
            
            
        
            
            
            
            teste(j, saida, entrada);
            
            //System.out.println(data);
            saida.close();
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexaoServidor.class.getName()).log(Level.SEVERE, null, ex);
        }           catch (ClassNotFoundException ex) {  
                        Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                    }  
              
                } catch (JSONException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                } 
               
            }

            public String teste(JSONObject j, ObjectOutputStream saida, ObjectInputStream entrada) throws IOException, JSONException, ClassNotFoundException {
                j.put("status", 1); // buscar salas
                saida.writeObject(j.toString());
                String data = (String) entrada.readObject();
                return data;
            }
        });
        
    }
}
