package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class endereco {

    private  String Cep;
    private  String Cidade;
    private  String Estado;
    private  String Endereco;


    public endereco(enderecoRec meuEnderecoAPI) {
        this.Cep = meuEnderecoAPI.cep();
        this.Cidade = meuEnderecoAPI.localidade();
        this.Estado = meuEnderecoAPI.uf();
        this.Endereco = meuEnderecoAPI.logradouro();
    }
}
