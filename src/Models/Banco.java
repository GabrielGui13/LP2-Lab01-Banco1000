package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Banco {
    private ArrayList<Conta> contas = new ArrayList<>();
    private int proximoNumeroConta = 1000;

    public void criarConta(Scanner scanner) {
        System.out.println("Digite seu nome:");
        if (scanner.hasNextInt()) {
            System.out.println("O nome não deve ser um número!");
            return;
        }
        String nome = scanner.nextLine();

        System.out.println("Digite seu CPF:");
        String cpf = scanner.nextLine();

        if (cpf.length() != 11) {
            System.out.println("O número do seu CPF está inválido!");
            return;
        }

        System.out.println("Escolha o tipo de conta: (CC - Conta Corrente, CP - Conta Poupança)");
        String tipo = scanner.nextLine().toUpperCase();

        if (!tipo.equals("CC") && !tipo.equals("CP")) {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        System.out.println("Crie uma senha de 4 dígitos numerais:");
        if (!scanner.hasNextInt()) {
            System.out.println("A senha deve ser um número!");
        }
        String senha = scanner.nextLine();

        if (senha.length() != 4) {
            System.out.println("A senha deve ter 4 dígitos.");
            return;
        }

        Cliente cliente = new Cliente(nome, cpf);
        Conta conta = new Conta(proximoNumeroConta++, tipo, cliente, senha);
        contas.add(conta);

        System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumero());
    }

    public void acessarConta(Scanner scanner) {
        System.out.println("Digite o número ou o CPF da sua conta:");

        String numeroConta = scanner.nextLine();

        Conta conta = null;

        if (numeroConta.length() == 4) {
            for (Conta c : contas) {
                if (c.getNumero() == Integer.parseInt(numeroConta)) {
                    conta = c;
                    break;
                }
            }
        } else if (numeroConta.length() == 11) {
            for (Conta c : contas) {
                if (c.getCliente().getCpf().equals(numeroConta)) {
                    conta = c;
                    break;
                }
            }
        }

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        if (!conta.verificarSenha(senha)) {
            System.out.println("Senha incorreta.");
            return;
        }

        while (true) {
            System.out.println("\nBem-vindo, " + conta.getCliente().getNome());
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Ver Saldo");
            System.out.println("5 - Ver Extrato");
            System.out.println("0 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Valor do depósito:");
                    double deposito = scanner.nextDouble();
                    scanner.nextLine();
                    conta.depositar(deposito);
                    break;
                case 2:
                    System.out.println("Valor do saque:");
                    double saque = scanner.nextDouble();
                    scanner.nextLine();
                    conta.sacar(saque);
                    break;
                case 3:
                    System.out.println("Número da conta destinatária:");
                    int contaDestino = scanner.nextInt();
                    System.out.println("Valor da transferência:");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    Conta destinatario = contas.get(contaDestino);
                    if (destinatario != null) {
                        conta.transferir(destinatario, valor);
                    } else {
                        System.out.println("Conta destinatária não encontrada.");
                    }
                    break;
                case 4:
                    System.out.println("Saldo: R$ " + String.format("%.2f", conta.getSaldo()));
                    break;
                case 5:
                    conta.mostrarExtrato();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
