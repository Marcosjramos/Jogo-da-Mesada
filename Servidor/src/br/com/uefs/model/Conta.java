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
public class Conta {
    
    private int id;
    private int idUser;
    private int tipo;// 1:Corrente  2:Poupança  3:Conjunta
    private List<Integer> titulares;
    private int saldo;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Integer> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Integer> titulares) {
        this.titulares = titulares;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
}
