package collections;
import model.Personagem;
import java.util.ArrayList;
import exceptions.CadastroDuplicadoException;
import exceptions.RegistroNaoEncontradoException;
import exceptions.DadosInvalidosException;

public class PersonagemRepository {
    private int proximoId = 1;
    private ArrayList<Personagem> personagens;

    public PersonagemRepository() {
        personagens = new ArrayList<>();
    }
                    
    public void adicionar(Personagem personagem) throws CadastroDuplicadoException, DadosInvalidosException {
        if (personagem == null) {
            throw new DadosInvalidosException("O personagem não pode ser nulo.");
        }

        if (personagem.getNome() == null || personagem.getNome().trim().isEmpty()) {
            throw new DadosInvalidosException("O nome do personagem não pode ser vazio.");
        }

        if (personagem.getNivel() <= 0 || personagem.getVida() <= 0) {
            throw new DadosInvalidosException("O nível e a vida devem ser maiores que zero.");
        }

        if (personagem.getId() <= 0) {
            personagem.setId(proximoId++);
        } else if (personagem.getId() >= proximoId) {
            proximoId = personagem.getId() + 1;
        }

        try {
            buscarPorId(personagem.getId());
            throw new CadastroDuplicadoException("Já existe um personagem com esse ID.");
        } catch (RegistroNaoEncontradoException e) {
            personagens.add(personagem);
        }
    }
    
    public ArrayList<Personagem> listar() {
        return new ArrayList<>(personagens);
    }
        
    public Personagem buscarPorId(int id) throws RegistroNaoEncontradoException {
        for (Personagem personagem : personagens) {
            if (personagem.getId() == id) {
                return personagem;
            }
        }

        throw new RegistroNaoEncontradoException("Personagem com ID " + id + " não encontrado.");
    }
    
    
    public void remover(int id) throws RegistroNaoEncontradoException {
        Personagem personagem = buscarPorId(id);
        personagens.remove(personagem);
    }

}