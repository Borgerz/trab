/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author user
 */
public class ClienteFisico extends Cliente{
    private String cpf;

    public ClienteFisico(String nome, String endereco, String cpf) {
        super(nome, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getTipoCliente() {
        return "Cliente Físico";
    }

    @Override
    public String toString() {
        return "Cliente Físico{" +
                "nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", CPF='" + cpf + '\'' +
                '}';
    }
}