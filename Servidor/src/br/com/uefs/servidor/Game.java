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

    public static final List<Sala> salas = new ArrayList<>();
    public static final List<Player> jogadores = new ArrayList<>();

    public Game(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        System.out.printf(conexao.getInetAddress().toString());
        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());) {
            String msg = "";

            saida.flush();

            try {
                //System.out.println((String)entrada.readObject());
                String a = entrada.readObject().toString();
                JSONObject obj = new JSONObject(a);
                System.out.println(obj.toString());

                int status = obj.getInt("status");
                //System.out.println(status);
                switch (status) {//Listar salas
                    case 1: // Listar salas
                        //listarSalas(obj, saida);
                        saida.writeObject(listarSalas().toString());
                        //System.out.println("\n Pegou o status");
                        break;
                    case 2: //Cadastrar uma sala
                        //  System.out.println("Teste  " + obj.toString());
                        boolean verificar = false;
                        if (!salas.isEmpty()) {
                            for (Sala mSala : salas) {
                                if (mSala.getAdm().equals(conexao.getInetAddress().toString())) {
                                    verificar = true;
                                    //System.out.println(mSala.getAdm()+" / "+conexao.getInetAddress().toString());
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
                          //  Player p = new Player();
                            s.setAdm(conexao.getInetAddress().toString());
                            //p.setUsername(obj.getString("nome"));
                            //p.setIp(conexao.getInetAddress().toString());
                            //p.setUsername(obj.getString("username"));
                            //p.setId(obj.getInt("id"));
                            List<Player> players = new ArrayList<>();
                            //players.add(p);
                            s.setPlayers(players);
                            salas.add(s);
                           // System.out.println(listarSalas().toString());
                            saida.writeObject(listarSalas().toString());
                            saida.writeObject("EOT");
                        } else {
                            System.out.println("Entrou no else");
                            JSONObject j = new JSONObject();
                            j.put("status", 0);
                        }
                        break;
                    case 3: // Cadastrar jogador
                        System.out.println(obj.toString());
                        Player p = new Player();
                        p.setIp(conexao.getInetAddress().toString());
                        p.setUsername(obj.getString("nome"));
                        p.setId(jogadores.size());
                        jogadores.add(p);
                        Gson gson = new Gson();
                        saida.writeObject(gson.toJson(p));
                        break;
                    case 4: // entrar na sala
                        //int id = obj.
                        System.out.println(obj.toString());
                        int espera = obj.getInt("espera");
                        if (espera != 1) {
                            // Player mPlay = null;
                            Sala sala = null;
                            for (Sala s : salas) {
                                if (s.getId() == obj.getInt("sala")) {
                                    List<Player> jogdores = s.getPlayers();
                                    if (jogdores == null) {
                                        jogdores = new ArrayList<>();
                                    }
                                    if (jogdores.size() < 6) {
                                         Player player = new Player();
                                         player.setId(obj.getInt("id"));
                                        player.setSaldo(3000);
                                         player.setPino(jogdores.size() + 1);
                                         player.setIp(obj.getString("ip"));
                                         player.setUsername(obj.getString("username"));
                                         jogdores.add(player);
                                         s.setPlayers(jogdores);
                                         sala = s;
                                         break;
                                    }
                                }
                                //System.out.println(s.getId());
                            }
                            if (sala != null) {
                                salas.remove(sala);
                                salas.add(sala);
                            }
                            //System.out.println(salas.size());
                            saida.writeObject("1");
                            
                        }else {
                            //Sala mSala = null;
                            for (Sala s : salas) {
                                if (s.getId() == obj.getInt("sala")) {
                                    List<Player> jogdores = s.getPlayers();
                                    if (jogdores.size() >= 1) {
                                         //mSala = s;
                                        gson = new Gson();
                                        saida.writeObject(gson.toJson(s.getPlayers()));
                                    }else {
                                        saida.writeObject("1");
                                    }
                                }
                              }
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
        
        if (salas != null) {
            for (Sala s : salas) {
                //System.out.println(s.toString());   
                JSONObject j = new JSONObject();
                j.put("nome", s.getNome());
                j.put("id", s.getId());
                j.put("n", s.getPlayers().size());
                j.put("adm", s.getAdm());
                ja.put(j);
            }
            //j.put("status", 1);
            System.out.println(ja.toString());
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
