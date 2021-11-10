package DAO;

import Model.Cliente;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Cliente cliente = new Cliente();
    List<String> listaDeClientes = new ArrayList<>();
    List<String> listaDeMensagens = new ArrayList<>();
    List<Cliente> listaDeRetorno = new ArrayList<>();

    public void cadastrarCliente(List<Cliente> clienteList, boolean append, boolean cadastro){
        File file = new File("clientes.txt");
        if (file.isFile()) {
            try {
                FileWriter fileWriter = new FileWriter(file, append);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for(int i = 0; i < clienteList.size(); i++){
                    clienteList.get(i).setId(i);
                    printWriter.println(validarId(clienteList.get(i)));
                }
                logs(cadastro);
                printWriter.flush();
                fileWriter.close();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                file.createNewFile();
                cadastrarCliente(clienteList, append, cadastro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cadastrarMensagem(Cliente cliente, String mensagem, boolean append, boolean cadastro){
        File file = new File("mensagens.txt");
        if (file.isFile()) {
            try {
                FileWriter fileWriter = new FileWriter(file, append);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(cliente.getId()+";"+ mensagem);
                logsMensagens(cadastro);
                printWriter.flush();
                fileWriter.close();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                file.createNewFile();
                cadastrarMensagem(cliente, mensagem, append, cadastro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Cliente> listarClientes(){
        List<Cliente> listaDeRetornoListarClientes = new ArrayList<>();
        Path path = Paths.get("clientes.txt");
        try {
            listaDeClientes = Files.readAllLines(path);
            for (int j = 0; j < listaDeClientes.size(); j++) {
                String lojasNaLista = listaDeClientes.get(j);
                String[] valorEditar = lojasNaLista.split(";");
                if(isNumeric(valorEditar[0])){
                    cliente = new Cliente(Integer.parseInt(valorEditar[0]), valorEditar[1], valorEditar[2], valorEditar[3]);
                }
                listaDeRetornoListarClientes.add(cliente);
            }
            return listaDeRetornoListarClientes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> listarMensagens(Cliente cliente){
        List<String> listaDeRetornoListarMensagens = new ArrayList<>();
        Path path = Paths.get("mensagens.txt");
        try {
            listaDeMensagens = Files.readAllLines(path);
            for (int j = 0; j < listaDeMensagens.size(); j++) {
                String mensagens = listaDeMensagens.get(j);
                String[] dados = mensagens.split(";");
                if(cliente.getId() == Integer.parseInt(dados[0])){
                    listaDeRetornoListarMensagens.add(cliente.getNome() + " " +dados[1]);
                }
            }
            return listaDeRetornoListarMensagens;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente validarId(Cliente cliente){
        listaDeRetorno = listarClientes();
        for(int i = 0; i<listaDeRetorno.size();i++){
            if(cliente.getId() == listaDeRetorno.get(i).getId()){
                cliente.setId(i+1);
            }
        }
        return cliente;
    }

    public void logs(boolean cadastro){
        File file = new File("logs.txt");
        if (file.isFile()) {
            try {
                if(cadastro){
                    FileWriter fileWriter = new FileWriter(file,true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println("Cliente cadastrada com sucesso!");
                    printWriter.flush();
                    fileWriter.close();
                    printWriter.close();
                }else{
                    FileWriter fileWriter = new FileWriter(file,true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println("Cliente deletado com sucesso!");
                    printWriter.flush();
                    fileWriter.close();
                    printWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                file.createNewFile();
                logs(cadastro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logsMensagens(boolean cadastro){
        File file = new File("logsMensagens.txt");
        if (file.isFile()) {
            try {
                if(cadastro){
                    FileWriter fileWriter = new FileWriter(file,true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println("Mensagem cadastrada com sucesso!");
                    printWriter.flush();
                    fileWriter.close();
                    printWriter.close();
                }else{
                    FileWriter fileWriter = new FileWriter(file,true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println("Mensagem deletada com sucesso!");
                    printWriter.flush();
                    fileWriter.close();
                    printWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                file.createNewFile();
                logs(cadastro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
