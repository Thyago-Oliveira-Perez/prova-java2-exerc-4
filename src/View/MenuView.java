package View;

import java.util.Scanner;

public class MenuView {

    ClienteView clienteView = new ClienteView();
    Scanner entrada = new Scanner(System.in);

    public void menu(){

        System.out.println("------------------------");
        System.out.println("1 - Cadastrar Cliente   ");
        System.out.println("2 - Mostrar Cliente     ");
        System.out.println("3 - Selecionar Cliente  ");
        System.out.println("0 - Exit                ");
        System.out.println("------------------------");
        String escolha = entrada.next();
        if(isNumeric(escolha)){
            switch (Integer.parseInt(escolha)){
                case 1:
                    clienteView.cadastrarCliente();
                    menu();
                    break;
                case 2:
                    clienteView.listarClientes();
                    menu();
                    break;
                case 3:
                    clienteView.selecionarCliente();
                    menu();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }else{
            menu();
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
