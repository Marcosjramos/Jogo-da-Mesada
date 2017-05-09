/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uefs.servidor;

import com.google.gson.Gson;
import br.com.uefs.dao.BancoDao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.uefs.model.Conta;
import br.com.uefs.model.Resposta;
import br.com.uefs.model.User;
import br.com.uefs.model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe responsavel pela conexão com o cliente
 * @author cassio
 */
public class Contador extends Thread {

    private Socket conexao; //conexão com cliente remoto
    private int idCliente; // id do cliente remoto
    Gson gson = new Gson();

    public Contador(int idCliente, Socket conexao) {
        this.idCliente = idCliente;
        this.conexao = conexao;
    }
/**
 * Metodo que é statartado ao iniciar a thread
 */
    public void run() {
        System.out.printf("[%d: Conexao aberta de: %s]\n", idCliente, conexao.getInetAddress().toString());
        try (ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());) {
            String msg = "";
            do {
                saida.flush();
               // System.out.println("LOOP");
                //System.out.printf("[%d: Recebendo Limite...]\n", idCliente);
                BancoDao bd = new BancoDao();
                String data = (String) entrada.readObject();// recebe as informações necessárias
                //System.out.println(data);
                int regra = 0;
                String dados = "";
                JSONObject response = null;
                try {
                    response = new JSONObject(data);
                    regra = response.getInt("status");
                    //JSONObject obj = response.getJSONObject("obj");

                } catch (JSONException ex) {
                    Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
                }

                Resposta r = new Resposta();
                //Usuario usuario = null;

              //  System.out.println("regrea = " + regra);
                switch (regra) {

                    case 1:                         
                        login(bd, response, saida);
                        break;
                    case 2:                         
                        cadastrarUsuario(bd, response, r, saida);
                        break;
                    case 3: 
                        cadastrarConta(response, bd, saida);
                        //System.out.println("obj");
                        //List<Usuario> usuarios4 = bd.readUsuario();
                        break;
                    case 4:                         
                        verificarSaldo(bd, response, saida);
                        //System.out.println(saldo);
                        break;

                    case 5:                         
                        verificarTitular(bd, response, saida);
                        break;

                    case 6://Deposito                         
                        deposito(response, bd, saida);
                        break;

                    case 7: // Transferencia                    
                        tranferencia(bd, response, saida);
                        break;
                    case 8:// Saque
                        int saque = bd.debitar(response.getInt("id"), response.getInt("tipo"), response.getInt("valor"));

                        response = new JSONObject();
                        response.put("status", saque);

                        saida.writeObject(response.toString());
                        break;
                    /*case 9: 
                        criarContaConjunta(response, bd, saida);
                        //System.out.println("obj");
                        //List<Usuario> usuarios4 = bd.readUsuario();
                        break;*/
                }

                //  Integer limite = (Integer) entrada.readObject(); //recebe limite
                /*System.out.printf("[%d: Limite = %d\n", idCliente, limite);
            for (int i= limite.intValue(); i>=0; i--) {//enviar contagem
                saida.writeObject(idCliente +": "+i);//Strings
            }*/
                //saida.writeObject("EOT");
                //conexao.close();
            } while (!msg.equals("EOT"));
        } catch (IOException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo responsavel por criar uma conta conjunta
     * @param response entrada de dados
     * @param bd banco de dados
     * @param saida saida de dados
     * @throws JSONException
     * @throws IOException 
     */
    private void criarContaConjunta(JSONObject response, BancoDao bd, final ObjectOutputStream saida) throws JSONException, IOException {
        // Criar conta compartilhada
        //System.out.println("Entrou");
        int idUserCompartilhara = response.getInt("id");
        int idUser2 = response.getInt("titular");
        Usuario user1 = bd.getUsuario(idUserCompartilhara);
        Usuario user2 = bd.getUsuario(idUser2);
        Conta conta1 = bd.getConta(user1.getId(), response.getInt("tipo"));
        Conta conta2 = bd.getConta(user2.getId(), response.getInt("tipo"));
        if (conta1 != null || conta2 != null) {
            // System.out.println("Não cadastrar");
            //r.setStatus(0);
            //r.setMsg("Voce ja possui uma conta corrente!");
            response = new JSONObject();
            response.put("status", 0);
            saida.writeObject(response.toString());
        } else {
           // System.out.println("Cadastra");
            List<Conta> contas = bd.readContas();
            if (contas == null) {
                contas = new ArrayList<>();
            }
            conta1 = new Conta();
            conta1.setId(contas.size());
            conta1.setIdUser(-1);
            conta1.setTipo(response.getInt("tipo"));
            contas.add(conta1);
            bd.CadastrarConta(gson.toJson(contas));
            response = new JSONObject();
            response.put("status", 1);
            saida.writeObject(response.toString());
            user1.setInContaConjunta(contas.size());
            user2.setInContaConjunta(contas.size());
            
            
        }
    }
    /**
     * Metodo responsavel por fazer a transferencia entre as contas
     * @param bd banco de dados
     * @param response entrada de dados
     * @param saida saida de dados
     * @throws JSONException
     * @throws IOException 
     */
    private void tranferencia(BancoDao bd, JSONObject response, final ObjectOutputStream saida) throws JSONException, IOException {
        //Transferir
        int debitar = bd.debitar(response.getInt("id"), response.getInt("tipo"), response.getInt("valor"));
        if (debitar == 1) {
            if (bd.depositar(response.getInt("idConta"), response.getInt("tipo"), response.getInt("valor")) == 1) {
                response = new JSONObject();
                response.put("status", 1);
            } else {
                response = new JSONObject();
                response.put("status", 0);
            }
        } else if (debitar == 2) {
            response = new JSONObject();
            response.put("status", 2);
        } else {
            response = new JSONObject();
            response.put("status", 0);
        }
        saida.writeObject(response.toString());
    }
    /**
     * Metodo responsavel por fazer depositos
     * @param response entrada de dados
     * @param bd banco de dados
     * @param saida saida de dados
     * @throws JSONException
     * @throws IOException 
     */
    private void deposito(JSONObject response, BancoDao bd, final ObjectOutputStream saida) throws JSONException, IOException {
        //Realizar depositos
        // System.out.println("TEST");
        //System.out.println(response.toString());
        
        int depositar = bd.depositar(response.getInt("id"), response.getInt("tipo"), response.getInt("valor"));
        response = new JSONObject();
        if (depositar == 1) {
            response.put("status", 1);
        } else {
            response.put("status", 0);
        }
        saida.writeObject(response.toString());
    }
    /**
     * Metodo responsavel por verificar se o titular existe no banco
     * @param bd banco de dados
     * @param response entrada de dados 
     * @param saida saida de dados
     * @throws IOException
     * @throws JSONException 
     */
    private void verificarTitular(BancoDao bd, JSONObject response, final ObjectOutputStream saida) throws IOException, JSONException {
        // Verificar Titular da conta
        
        Conta c = bd.getConta(response.getInt("id"), response.getInt("tipo"));
        response = new JSONObject();
        if (c != null) {
            Usuario uC = bd.getUsuario(c.getIdUser());
            
            response.put("nome", uC.getNome());
            response.put("cpf", uC.getCpf());
            response.put("status", 1);
        } else {
            response.put("status", 0);
        }
        saida.writeObject(response.toString());
    }

    /**
     * Metodo responsavel por verificar o saldo de uma determinada conta
     * @param bd banco de dados
     * @param response entrada de dados
     * @param saida saida de dados
     * @throws JSONException
     * @throws IOException 
     */
    private void verificarSaldo(BancoDao bd, JSONObject response, final ObjectOutputStream saida) throws JSONException, IOException {
        // Verificar saldo
        // System.out.println(response.toString());
        int saldo = bd.saldo(response.getInt("id"), response.getInt("tipo"));
        response = new JSONObject();
        response.put("saldo", saldo);
        saida.writeObject(response.toString());
    }

    /**
     * Metodo responsavel por cadastrar uma conta
     * @param response entrada de dados do cliente
     * @param bd banco de dados
     * @param saida saida de dados
     * @throws IOException
     * @throws JSONException 
     */
    private void cadastrarConta(JSONObject response, BancoDao bd, final ObjectOutputStream saida) throws IOException, JSONException {
        //Cadastrar uma conta
        //System.out.println("Entrou");
        int idUser = response.getInt("id");
        Usuario u = bd.getUsuario(idUser);
        Conta conta = bd.getConta(u.getId(), response.getInt("tipo"));
        if (conta != null) {
           // System.out.println("Não cadastrar");
            //r.setStatus(0);
            //r.setMsg("Voce ja possui uma conta corrente!");
            response = new JSONObject();
            response.put("status", 0);
            saida.writeObject(response.toString());
        } else {
            //System.out.println("Cadastra");
            List<Conta> contas = bd.readContas();
            if (contas == null) {
                contas = new ArrayList<>();
            }
            conta = new Conta();
            conta.setId(contas.size());
            conta.setIdUser(u.getId());
            conta.setTipo(response.getInt("tipo"));
            contas.add(conta);
            bd.CadastrarConta(gson.toJson(contas));
            response = new JSONObject();
            response.put("status", 1);
            saida.writeObject(response.toString());
        }
    }

    /**
     * Metodo responsavel por receber e cadastrar o usuário
     * @param bd banco de dados
     * @param response entrada do cliente
     * @param r auxiar para envio
     * @param saida saida de dados para o cliente
     * @throws IOException
     * @throws JSONException 
     */
    private void cadastrarUsuario(BancoDao bd, JSONObject response, Resposta r, final ObjectOutputStream saida) throws IOException, JSONException {
        // Cadastrar Usuario
        List<Usuario> usuarios2 = bd.readUsuario();
        Usuario usuario = new Usuario();
        usuario.setCpf(response.getString("cpf"));
        usuario.setUsername(response.getString("username"));
        usuario.setNome(response.getString("nome"));
        usuario.setPassword(response.getString("password"));
        boolean verificar = false;
        if (usuarios2 != null) {
            for (int i = 0; usuarios2.size() > i; i++) {
                //System.out.println(u.toString());
                if (usuarios2.get(i).getUsername().equals(usuario.getUsername())) {
                    
                    verificar = true;
                }
                if (usuarios2.get(i).getCpf().equals(usuario.getCpf())) {
                    
                    verificar = true;
                }
                if (verificar == true) {
                    break;
                }
            }
            if (verificar) {
                r.setStatus(0);
                r.setMsg("O CPF ou o username estao em uso");
                saida.writeObject(gson.toJson(r));
            } else {
                //System.out.println("TESTE");
                usuario.setId(usuarios2.size());
                usuarios2.add(usuario);
                
                bd.cadastrarUsuario(gson.toJson(usuarios2));
                r.setStatus(1);
                r.setMsg("Usuario cadastrado com sucesso!");
                saida.writeObject(gson.toJson(r));
            }
        } else {
            List<Usuario> usuarios3 = new ArrayList<Usuario>();
            usuarios3.add(usuario);
            //  JSONArray ja = new JSONArray(usuarios);
            bd.cadastrarUsuario(gson.toJson(usuarios3));
        }
    }
    
    /**
     * Metodo responsavel por autenticar o usuário
     * @param bd base de dados
     * @param response entrada de dados do cliente 
     * @param saida saida de dados 
     * @throws IOException
     * @throws JSONException 
     */
    private void login(BancoDao bd, JSONObject response, final ObjectOutputStream saida) throws IOException, JSONException {
        //Logar
        List<Usuario> usuarios = bd.readUsuario();
        User user = new User();
        user.setUsername(response.getString("username"));
        user.setPassword(response.getString("password"));
        int nConta = 0;
        int id = 0;
        if (usuarios != null) {
            //  System.out.println(user.getUsername());
            // System.out.println(user.getPassword());
            
            boolean verificar = false;
            for (Usuario u : usuarios) {
                //  System.out.println(u.getUsername());
                //System.out.println(u.getPassword());
                if (u.getUsername().equals(user.getUsername())) {
                    if (u.getPassword().equals(user.getPassword())) {
                        verificar = true;
                        id = u.getId();
                        nConta = u.getInContaConjunta();
                        //saida.writeObject(gson.toJson(u));
                        break;
                    }
                }
            }
            if (verificar) {
                //r.setStatus(1);
                //r.setMsg("Login realizado");
                JSONObject j = new JSONObject();
                j.put("status", 1);
                j.put("id", id);
                j.put("conta", nConta);
                saida.writeObject(j.toString());
            } else {
                JSONObject v = new JSONObject();
                v.put("status", 0);
                saida.writeObject(v.toString());
            }
        } else {
            JSONObject v = new JSONObject();
            v.put("status", 2);
            saida.writeObject(v.toString());
        }
    }

}
