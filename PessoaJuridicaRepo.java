import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaJuridicaRepo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PessoaJuridica> listaPessoas;

    // Construtor
    public PessoaJuridicaRepo() {
        this.listaPessoas = new ArrayList<>();
    }

    // Método para inserir uma nova PessoaJuridica
    public void inserir(PessoaJuridica pessoa) {
        listaPessoas.add(pessoa);
    }

    // Método para alterar uma PessoaJuridica existente
    public boolean alterar(PessoaJuridica pessoa) {
        Optional<PessoaJuridica> pessoaExistente = listaPessoas.stream()
                .filter(p -> p.getId() == pessoa.getId())
                .findFirst();

        if (pessoaExistente.isPresent()) {
            int index = listaPessoas.indexOf(pessoaExistente.get());
            listaPessoas.set(index, pessoa);
            return true;
        }

        return false;
    }

    // Método para excluir uma PessoaJuridica pelo ID
    public void excluir(int id) {
        listaPessoas.removeIf(p -> p.getId() == id);
    }

    // Método para obter uma PessoaJuridica pelo ID
    public PessoaJuridica obter(int id) {
        return listaPessoas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para obter todas as PessoasJuridicas
    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(listaPessoas);
    }
    // Método para persistir os dados no disco
    public void persistir(String nomeArquivo) {
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(listaPessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Método para recuperar os dados do disco
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) {
        try (FileInputStream fileIn = new FileInputStream(nomeArquivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            listaPessoas = (ArrayList<PessoaJuridica>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


