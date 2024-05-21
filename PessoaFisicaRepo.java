import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo implements Serializable {
    private static final long serialVersionUID = 1L;


    // ArrayList de PessoaFisica
    private List<PessoaFisica> listaPessoas;

    // Construtor
    public PessoaFisicaRepo() {
        this.listaPessoas = new ArrayList<>();
    }

    // Método para inserir uma nova PessoaFisica
    public void inserir(PessoaFisica pessoa) {
        listaPessoas.add(pessoa);
    }

    // Método para alterar uma PessoaFisica existente
    public void alterar(PessoaFisica pessoa) throws IllegalArgumentException {
        if (!listaPessoas.contains(pessoa)) {
            throw new IllegalArgumentException("Pessoa não encontrada para alteração.");
        }

        int index = listaPessoas.indexOf(pessoa);
        listaPessoas.set(index, pessoa);
    }

    // Método para excluir uma PessoaFisica pelo ID
    public void excluir(int id) {
        listaPessoas.removeIf(p -> p.getId() == id);
    }

    // Método para obter uma PessoaFisica pelo ID
    public PessoaFisica obter(int id) {
        return listaPessoas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para obter todas as PessoasFisicas
    public List<PessoaFisica> obterTodos() {
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
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(nomeArquivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            listaPessoas = (ArrayList<PessoaFisica>) in.readObject();
        }
    }



}



