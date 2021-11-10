package View;

import Controller.ClienteController;
import Model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    Model.Cliente cliente = new Model.Cliente();
    List<String> mensagens = new ArrayList<>();
    ClienteController clienteController = new ClienteController();
    Scanner entrada = new Scanner(System.in);

    public void cadastrarCliente(){
        List<Model.Cliente> clienteList = new ArrayList<>();
        System.out.print("Nome: ");
        String nome = entrada.next();
        System.out.print("CPF: ");
        String cnpj = entrada.next();
        System.out.print("Telefone: ");
        String telefone = entrada.next();
        cliente = new Model.Cliente(nome, cnpj, telefone);
        clienteList.add(cliente);
        clienteController.cadastrarCliente(clienteList, true);
    }

    public List<Cliente> listarClientes(){
        int tamanho = clienteController.listarClientes().size();
        if(clienteController.listarClientes().isEmpty()){
            System.out.println("Nenhum cliente cadastrado");
        }else{
            for(int i = 0; i < tamanho; i++){
                System.out.println((i+1)+" "+ clienteController.listarClientes().get(i).mostrar());
            }
        }
        return clienteController.listarClientes();
    }

    public void selecionarCliente(){
        List<Cliente> clienteList = new ArrayList<>();
        if(!listarClientes().isEmpty()){
            System.out.println("Selecione o Cliente:");
            int escolha = entrada.nextInt();
            int numeroDecliente = clienteController.listarClientes().size();
            if((escolha-1) < numeroDecliente){
                System.out.println("1 - Cadastrar Mensagens\n2 - Listar Mensagens");
                int escolha2 = entrada.nextInt();
                clienteList = clienteController.listarClientes();
                if(escolha2 == 1){
                    cadastrarMensagens(clienteList.get((escolha-1)));
                }else if(escolha2 == 2){
                    System.out.println(listarMensagens(clienteList.get((escolha-1))));
                }else{
                    System.out.println("Opções inválidas");
                    selecionarCliente();
                }
            }else{
                System.out.println("!!!Selecione um cliente válido!!!");
                selecionarCliente();
            }
        }
    }

    public void cadastrarMensagens(Cliente cliente){
        System.out.println("Digite sua mensagem:");
        String mensagem = entrada.next();
        clienteController.cadastrarMensagem(cliente, mensagem, true);
    }

    public List<String> listarMensagens(Cliente cliente){
        return clienteController.listarMensagens(cliente);
    }

}
