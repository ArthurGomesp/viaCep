package exceptions;

public class erroLetrasNoCep extends RuntimeException{
    private String message = "Somente numeros são permitinos na busca por cep!";
    public erroLetrasNoCep(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
