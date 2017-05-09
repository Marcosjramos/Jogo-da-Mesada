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
public class Usuario {
    private String nome;
    private String cpf;
    private String cnpj;
    private String username;
    private int id;
    private String password;
    private int inContaConjunta;

    public int getInContaConjunta() {
        return inContaConjunta;
    }

    public void setInContaConjunta(int inContaConjunta) {
        this.inContaConjunta = inContaConjunta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    
    
}
