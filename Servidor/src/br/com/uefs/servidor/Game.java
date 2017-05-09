/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.servidor;

import br.com.uefs.model.Sala;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class Game extends Thread {

    private Socket conexao; //conexão com cliente remoto
    //private String idCliente;

    public Game(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        System.out.printf(conexao.getInetAddress().toString());
        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());) {
            String msg = "";
            List<Sala> salas = new ArrayList<Sala>();
            do {
                saida.flush();
                msg = (String) entrada.readObject();// recebe as informações necessárias
                JSONObject obj = null;
                try {
                    obj = new JSONObject(msg);
                } catch (JSONException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                switch (obj.getInt("status")) {
                    case 1: // Verificar salas
                        if (!salas.isEmpty()) {
                            obj.put("status", 1);
                            saida.writeObject(obj.toString());
                            JSONArray json = new JSONArray(salas);
                            saida.writeObject(json.toString());
                        } else {
                            obj.put("status", 0);
                            saida.writeObject(obj.toString());
                        }
                        //System.out.println("\n Pegou o status");
                        break;
                }
               
                System.out.println(msg);
                 msg = "EOT";
            } while (!msg.equals("EOT"));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
