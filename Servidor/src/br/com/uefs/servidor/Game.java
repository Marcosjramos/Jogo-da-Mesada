/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.servidor;

import br.com.uefs.model.Player;
import br.com.uefs.model.Sala;
import com.google.gson.Gson;
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
   
    public static final List<Sala> salas = new ArrayList<Sala>();

    public Game(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        System.out.printf(conexao.getInetAddress().toString());
        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());) {
            String msg = "";

            //List<Sala> salas = new ArrayList<Sala>();
            saida.flush();
            //   System.out.println(entrada.readObject());
            //msg = (String) entrada.readObject();
            // recebe as informações necessárias
           // JSONObject obj = new JSONObject();
            try {
                //System.out.println((String)entrada.readObject());
                String a = entrada.readObject().toString();
               JSONObject obj = new JSONObject(a);
                System.out.println(obj.toString());
                //System.out.println(entrada.readObject());
                //System.out.println(entrada.readObject().toString());
                //String g = obj.getString("status");
                int status = obj.getInt("status");
                //System.out.println(status);
                switch (status) {
                    case 1: 
                        //listarSalas(obj, saida);
                        saida.writeObject(listarSalas().toString());
                        //System.out.println("\n Pegou o status");
                        break;
                    case 2:
                      //  System.out.println("Teste  " + obj.toString());
                        boolean verificar = false;
                        if (!salas.isEmpty()) {
                            for (Sala mSala: salas){
                                if (mSala.getAdm().equals(conexao.getInetAddress().toString())){
                                    verificar = true;
                                    break;
                                }
                            }
                        }
                       // System.out.println(verificar);
                        if (!verificar) {
                        Sala s = new Sala();
                        s.setId(salas.size());
                        s.setNome(obj.getString("nome"));
                        //s.setAdm(obj.getString("sala"));
                        Player p = new Player();
                        s.setAdm(conexao.getInetAddress().toString());
                        //p.setUsername(obj.getString("nome"));
                        p.setIp(conexao.getInetAddress().toString());
                        List<Player> players = new ArrayList<>();
                        players.add(p);
                        s.setPlayers(players);
                        salas.add(s);
                     
                        //System.out.println(s.getId());
                        //Servidor.setSalas(Servidor.salas);
                        //Gson gson = new Gson();
                        //String ss = gson.toJson(salas);
                     // System.out.println(ss);
                        //saida.writeObject(gson.toJson(salas));
                        /*JSONObject j = new JSONObject();
                        j.put("status", 1);
                        j.put("nome");*/
                        System.out.println(listarSalas().toString());
                        saida.writeObject(listarSalas().toString());
                        saida.writeObject("EOT");
                        } else {
                           JSONObject j = new JSONObject();
                           j.put("status", 0);
                        }
                        break;
                }

            } catch (JSONException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(msg);
            msg = "EOT1";

            //    } while (!msg.equals("EOT"));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JSONArray listarSalas() throws JSONException {
        // Verificar salas
        JSONArray ja = new JSONArray();
        JSONObject j = new JSONObject();
        if (salas != null) {
            for (Sala s: salas) {
                j.put("nome", s.getNome());
                j.put("id", s.getId());
                j.put("n", s.getPlayers().size());
                j.put("adm", s.getAdm());
                ja.put(j);
            }
            //j.put("status", 1);
            return ja;
            //saida.writeObject(obj.toString());
            //JSONArray json = new JSONArray(salas);
            //saida.writeObject(json.toString());
        } else {
            //j.put("", salas
            return null;
            //obj.put("status", 0);
            //saida.writeObject(obj.toString());
        }
    }
}
