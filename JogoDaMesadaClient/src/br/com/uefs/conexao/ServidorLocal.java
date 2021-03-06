/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.conexao;

import br.com.uefs.model.Player;
import br.ecomp.uefs.view.TabuleiroNovo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe do servidor do cliente
 *
 * @author cassio
 */
public class ServidorLocal extends Thread {

    //private List<Player> jogdores;
    private Socket conexao; //conexão com cliente remoto
    private static ServidorLocal uniqueInstance;
    public static List<Player> jogadores;
    int i = 0;
    TabuleiroNovo tab;

    public ServidorLocal(Socket conexao) {
        this.conexao = conexao;
//        tab = TabuleiroNovo.getInstance();
    }

    /**
     * Cria uma instancia unica do socket que fica recebendo as cartas
     *
     * @return
     */
    public static synchronized ServidorLocal getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ServidorLocal();
        }
        return uniqueInstance;
    }

    private ServidorLocal() {
    }

    /**
     * Conexão com o cliente remoto
     */
    public void run() {
        System.out.printf(conexao.getInetAddress().toString());
        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());) {
            System.out.println("Esperando conexão");

            String msg = entrada.readObject().toString();
            //System.out.println("Testando o envio de objetos"+ entrada.toString());
            if (msg != null) {
                JSONObject j = new JSONObject(msg);
                Player p = new Player();
                p.setId(j.getInt("id"));
                p.setIp(j.getString("ip"));
                p.setPino(j.getInt("pino"));
                p.setPosition(j.getInt("position"));
                p.setSaldo(j.getInt("saldo"));
                p.setUsername(j.getString("username"));
                
                //tab.setPosition(p.getPino(), jogador, i, i);
                jogadores = TabuleiroNovo.jogadores;
                jogadores.remove(p);
                jogadores.add(p);
                int x = 0;
                int y = 0;
                switch (p.getPino()) {
                    case 1:
                        x = 185;
                        y = 104;
                        break;
                    case 2:
                        x = 185;
                        y = 139;
                        break;
                    case 3:
                        x = 185;
                        y = 174;
                        break;
                    case 4:
                        x = 240;
                        y = 104;
                        break;
                    case 5:
                        x = 240;
                        y = 139;
                        break;
                    case 6:
                        x = 240;
                        y = 174;
                        break;
                }
                 System.out.println("Usuario: " + p.toString());
                System.out.println("OBJETO " + j.toString());
                TabuleiroNovo.setPosition2(p.getPosition(), p.getPino(), x, y);
               
                saida.writeObject("obj");
                //TabuleiroNovo.setJogadores(jogadores);
            }
            //saida.writeObject("Tetano a saida");
            //saida.writeObject("EOT");
        } catch (IOException ex) {
            Logger.getLogger(ServidorLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ServidorLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Player> getJogadores() {
        return jogadores;
    }

    public static void setJogadores(List<Player> jogadores) {
        ServidorLocal.jogadores = jogadores;
    }

}
