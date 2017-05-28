/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.model;

import java.util.List;

/**
 *
 * @author cassio
 */
public class Sala {
    
    private List<Player> players;
    //private String adm;
    private String adm;
    private int id;
    private String nome;

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
