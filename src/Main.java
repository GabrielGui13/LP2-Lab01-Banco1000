import Models.Banco;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo ao banco digital Arael Pictures!");
            System.out.println("1 - Abrir Conta");
            System.out.println("2 - Acessar Conta");
            System.out.println("0 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    banco.criarConta(scanner);
                    break;
                case 2:
                    banco.acessarConta(scanner);
                    break;
                case 0:
                    System.out.println("Obrigado por usar o banco digital Arael Pictures!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}