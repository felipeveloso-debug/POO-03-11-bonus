import collections.PersonagemRepository;
import model.Personagem;
import exceptions.CadastroDuplicadoException;
import exceptions.DadosInvalidosException;
import exceptions.RegistroNaoEncontradoException;

public class Main {
    public static void main(String[] args) {
        PersonagemRepository repository = new PersonagemRepository();
        Personagem p1 = new Personagem("Arthur", 15, 100);
        Personagem p2 = new Personagem("Merlin", 20, 80);
        Personagem p3 = new Personagem("Morgana", 18, 90);

        try {
            repository.adicionar(p1);
            repository.adicionar(p2);
            repository.adicionar(p3);
            System.out.println("Personagens cadastrados:");

            for (Personagem personagem : repository.listar()) {
                System.out.println(personagem);
            }
            
            System.out.println("\nBuscando personagem com ID 2:");
            Personagem encontrado = repository.buscarPorId(2);
            System.out.println(encontrado);
            System.out.println("\nRemovendo personagem com ID 2:");
            repository.remover(2);

            System.out.println("Personagens após a remoção:");
            for (Personagem personagem : repository.listar()) {
                System.out.println(personagem);
            }
            
            
            System.out.println("\nTestando cadastro com dados inválidos:");
            try {
                Personagem invalido = new Personagem("", 10, 50);
                repository.adicionar(invalido);
            } catch (DadosInvalidosException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (CadastroDuplicadoException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println("\nTestando cadastro duplicado:");
            try {
                Personagem duplicado = new Personagem("Arthur Clone", 10, 50);
                duplicado.setId(1);
                repository.adicionar(duplicado);
            } catch (CadastroDuplicadoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (DadosInvalidosException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
            System.out.println("\nTestando busca de personagem inexistente:");
            try {
                repository.buscarPorId(99);
            } catch (RegistroNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
            System.out.println("\nTestando remoção de personagem inexistente:");
            try {
                repository.remover(99);
            } catch (RegistroNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
        } catch (CadastroDuplicadoException | DadosInvalidosException | RegistroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}