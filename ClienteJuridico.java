/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author user
 */
public class ClienteJuridico extends Cliente{

    private String cnpj;

    public ClienteJuridico(String nome, String endereco, String cnpj) {
        super(nome, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getTipoCliente() {
        return "Cliente Jurídico";
    }

    @Override
    public String toString() {
        return "Cliente Jurídico{" +
                "nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", CNPJ='" + cnpj + '\'' +
                '}';
    }
}