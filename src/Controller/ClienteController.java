package Controller;

import DAO.ClienteDAO;
import Model.Cliente;

import java.util.List;

public class ClienteController {

    ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(List<Cliente> clienteList, boolean append){
        clienteDAO.cadastrarCliente(clienteList, append, true);
    }

    public List<Cliente> listarClientes(){
        return clienteDAO.listarClientes();
    }

    public void cadastrarMensagem(Cliente cliente, String mensagem, boolean append){
        clienteDAO.cadastrarMensagem(cliente, mensagem, append, true);
    }

    public List<String> listarMensagens(Cliente cliente){
        return clienteDAO.listarMensagens(cliente);
    }

}
