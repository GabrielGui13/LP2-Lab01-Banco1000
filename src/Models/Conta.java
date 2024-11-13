package Models;

import Models.Cliente;
import Models.Movimentacao;

import java.util.ArrayList;

public class Conta {
    private int numero;
    private String tipo;
    private Cliente cliente;
    private double saldo;
    private boolean ativa;
    private String senha;
    private ArrayList<Movimentacao> extrato = new ArrayList<>();

    public Conta(int numero, String tipo, Cliente cliente, String senha) {
        this.numero = numero;
        this.tipo = tipo;
        this.cliente = cliente;
        this.senha = senha;
        this.saldo = tipo.equals("CC") ? 50.0 : 100.0;
        this.ativa = true;
        extrato.add(new Movimentacao("Abertura de conta", saldo));
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void depositar(double valor) {
        saldo += valor;
        extrato.add(new Movimentacao("Depósito", valor));
        System.out.println("Depósito realizado com sucesso.");
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            extrato.add(new Movimentacao("Saque", valor));
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(Conta destinatario, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            destinatario.depositar(valor);
            extrato.add(new Movimentacao("Transferência para conta " + destinatario.getNumero(), valor));
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void mostrarExtrato() {
        System.out.println("Extrato da conta " + numero + ":");
        for (Movimentacao mov : extrato) {
            System.out.println(mov);
        }
    }
}