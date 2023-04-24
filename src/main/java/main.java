import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import exceptions.erroLetrasNoCep;
import models.endereco;
import models.enderecoRec;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args)  throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String continuar = "";

        Gson gson = new Gson();
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//                .setPrettyPrinting()
//                .create();
        List<endereco> listaDeEnderecos = new ArrayList<>();


        while (!continuar.equalsIgnoreCase("sair")) {
            System.out.print("Digite seu cep:");
            var cep = sc.nextLine();

                if(continuar.equalsIgnoreCase("sair")){
                    break;
                }else{
                    var cepSemTraco =cep.replace("‑", "");
                     cepSemTraco =cep.replace("-", "");
                    String enderecoAPI = "http://viacep.com.br/ws/" +  cepSemTraco + "/json/";
                    System.out.println(enderecoAPI);
                    try {
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(enderecoAPI))
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());
                        String json = response.body();


                        enderecoRec meuEnderecoAPI =  gson.fromJson(json, enderecoRec.class);


                        System.out.println(meuEnderecoAPI);

                    endereco meuEndereco = new endereco(meuEnderecoAPI);
                    System.out.println(meuEndereco.toString());
                    System.out.println(meuEndereco.getCep());
                    System.out.println(meuEndereco.toString());
                    listaDeEnderecos.add(meuEndereco);

                    } catch (JsonSyntaxException e){
                        System.out.println("ocorreu um erro:");
                        System.out.println("O cep nao pode conter letras e deve ter  8 numeros");

                        System.out.println(e.getMessage());
                    }
                    System.out.println("deseja Sair? ficar/sair");
                    continuar = sc.nextLine();
                }
            }


        FileWriter escrita = new FileWriter("enderecos.json");
        escrita.write(gson.toJson(listaDeEnderecos));
        escrita.close();
        }
    }

