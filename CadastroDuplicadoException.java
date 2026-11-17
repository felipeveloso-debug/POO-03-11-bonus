package exceptions;

public class CadastroDuplicadoException extends Exception {
    public CadastroDuplicadoException(String mensagem) {
        super(mensagem);
    }
}