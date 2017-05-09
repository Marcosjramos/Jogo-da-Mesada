/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.dao;

import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.uefs.model.Conta;
import br.com.uefs.model.Usuario;
import br.com.uefs.model.Usuarios;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cassio
 */
public class BancoDao {


    /**
     *  Metodo responsavel por cadastrar um usuário
     * @param data formulário com dados de cadastro
     */
    public void cadastrarUsuario(String data) { // Serialização na conta
        synchronized( this ) {
            FileWriter mWriter = null;
            try {
                System.out.println(data);
                mWriter = new FileWriter("/home/cassio/Documentos/Desk/usuarios.txt");

                mWriter.write(data);
                mWriter.close();
            } catch (IOException ex) {
                
                Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
    }
    /**
     * Metodo responsavel por cadastrar um usuário
     * @param data variaveis da conta
     */
    public void CadastrarConta(String data){
        synchronized( this ) {
          FileWriter mWriter = null;
        try {
           // System.out.println(data);
            mWriter = new FileWriter("/home/cassio/Documentos/Desk/contas.txt");
            mWriter.write(data);
            mWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Medoto responsavel por 
     * @param id Identificador do usuário
     * @return
     * @throws IOException 
     */
    public Usuario getUsuario(int id) throws IOException{
        List<Usuario> usuarios = readUsuario();
        //Usuario usuario = null;
        for (Usuario u: usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    
    /**
     * Metodo responsavel por buscar os arquivos no banco
     * @return Usuários
     * @throws IOException 
     */
    public List<Usuario> readUsuario() throws IOException {
        Gson gson = new Gson();
            File file = new File("/home/cassio/Documentos/Desk/usuarios.txt");
            String teste = "";
            if (file.exists()){
                try {
                    InputStream inpt = new FileInputStream(file);
                    byte[] bytes = readFromStream(inpt);
                    teste = new String(bytes);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //System.out.println(teste);
            if (!teste.equals("")) {
                List<Usuario> usuarios = new ArrayList<>();
            try {
                JSONArray ja = new JSONArray(teste);
                for (int i=0; ja.length() > i;i++){
                    //System.out.println(i);
                    Usuario u = new Usuario();
                    JSONObject jo = (JSONObject) ja.get(i);
                     //System.out.println(jo);
                    u.setUsername(jo.getString("username"));
                    u.setCpf(jo.getString("cpf"));
                    u.setNome(jo.getString("nome"));
                    u.setPassword(jo.getString("password"));
                    //u.setCnpj(jo.getString("npj"));
                     //u.setContas(jo.getInt("contas"));
                      u.setId(jo.getInt("id"));
                    //System.out.println(u.getUsername());
                    //System.out.println(u.getCpf());
                    usuarios.add(u);
                }
                return usuarios;
                //Usuarios us = gson.fromJson(teste, Usuarios.class);
                //return us.getUsuarios();
            } catch (JSONException ex) {
                Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            return null;
        }
    
    /**
     * Metodo responsavel por buscar todas as contas
     * @return Lista de cont
     * @throws IOException 
     */
      public List<Conta> readContas() throws IOException {
        Gson gson = new Gson();
            File file = new File("/home/cassio/Documentos/Desk/contas.txt");
            String teste = "";
            if (file.exists()){
                try {
                    InputStream inpt = new FileInputStream(file);
                    byte[] bytes = readFromStream(inpt);
                    teste = new String(bytes);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(teste);
            if (!teste.equals("")) {
                List<Conta> contas = new ArrayList<>();
            try {
                JSONArray ja = new JSONArray(teste);
                for (int i=0; ja.length() > i;i++){
                    System.out.println(i);
                    Conta u = gson.fromJson(ja.get(i).toString(), Conta.class);
                    contas.add(u);
                }
                return contas;
            } catch (JSONException ex) {
                Logger.getLogger(BancoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            return null;
        }
      
      /**
       * Metodo responsavel por buscar uma unica conta
       * @param id identificador do usuário
       * @param tipo identificador do tipo de conta
       * @return Conta
       * @throws IOException 
       */
      public Conta getConta(int id, int tipo) throws IOException{
          List<Conta> contas = readContas();
            Conta conta = null;
                   if (contas!=null) {
                        //List<Conta> minhas = new ArrayList<>();
                        for (Conta c: contas) {
                            System.out.println(c.getTipo());
                            if (c.getIdUser() == id){
                                if (c.getTipo() == tipo) {
                                    //System.out.println(c);
                                    conta = c;
                                    break;
                                }
                            }
                        }
                   }
                   return conta;
      }
      
      /**
       * Metodo responsavel por retorna o saldo do usuário
       * @param id identificador do usuário
       * @param tipo identificador da conta
       * @return saldo
       * @throws IOException 
       */
      public int saldo(int id, int tipo) throws IOException{
          Conta conta = getConta(id, tipo);
          if (conta != null) {
              return conta.getSaldo();
          } else {
              return -1;
          }
      }
      
      /**
       * Metodo responsavel por buscar e creditar na conta
       * @param id identificador da conta
       * @param tipo tipo da conta
       * @param valor valor do deposito
       * @return resultado da opração
       * @throws IOException 
       */
      public int depositar(int id, int tipo, int valor) throws IOException{
          List<Conta> contas = readContas();
          Gson gson = new Gson();
            Conta conta = null;
                   if (contas!=null) {
                        //List<Conta> minhas = new ArrayList<>();
                        for (Conta c: contas) {
                            System.out.println(c.getTipo());
                            if (c.getIdUser() == id){
                                if (c.getTipo() == tipo) {
                                    //System.out.println(c);
                                    conta = c;
                                    break;
                                }
                            }
                        }
                   }
                   if (conta != null) {
                       contas.remove(conta);
                       conta.setSaldo(conta.getSaldo()+valor);
                       
                       contas.add(conta);
                       System.out.println(conta.getSaldo());
                       CadastrarConta(gson.toJson(contas));
                       return 1;
                   } else {
                       return -1;
                   }
                   //return conta;
      }
      
      /**
       * Metodo responsavel por debitar o valor da conta
       * @param id identificador da conta 
       * @param tipo tipo de conta
       * @param valor valor a ser debitado
       * @return valor da operação
       * @throws IOException 
       */
        public int debitar(int id, int tipo, int valor) throws IOException{
          List<Conta> contas = readContas();
          Gson gson = new Gson();
            Conta conta = null;
                   if (contas!=null) {
                        //List<Conta> minhas = new ArrayList<>();
                        for (Conta c: contas) {
                            System.out.println(c.getTipo());
                            if (c.getIdUser() == id){
                                if (c.getTipo() == tipo) {
                                    //System.out.println(c);
                                    conta = c;
                                    break;
                                }
                            }
                        }
                   }
                   if (conta != null) {
                       if (conta.getSaldo() >= valor) {
                           conta.setSaldo(conta.getSaldo() - valor);
                           contas.remove(conta);   
                           contas.add(conta);
                           CadastrarConta(gson.toJson(contas));
                           return 1;
                       } else {
                           return 2; // Saldo insuficiente
                       }
                   } else {
                       return -1;
                   }
                   //return conta;
      }
        
        /**
         * Metodo responsavel por atualizar dados do usuario
         * @param u Objeto
         * @throws IOException 
         */
        public void atualizarUzuario(Usuario u) throws IOException{
            List<Usuario> usuarios = readUsuario();
            usuarios.remove(u);
            usuarios.add(u);
            Gson gson = new Gson();
            cadastrarUsuario(gson.toJson(usuarios));
        }
    
    /**
     * Metodo responsavel por converter objeto em caracteres
     * @param stream dados do arquivo
     * @return array de bytes
     * @throws IOException 
     */
    private byte[] readFromStream(InputStream stream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1000];
        int wasRead = 0;

        do {
            wasRead = stream.read(buffer);
            if (wasRead > 0) {
                baos.write(buffer, 0, wasRead);
            }
        } while (wasRead > - 1);
        return baos.toByteArray();

    }
}
