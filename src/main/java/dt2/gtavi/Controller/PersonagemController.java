package dt2.gtavi.Controller;

import dt2.gtavi.entities.Personagem;
import dt2.gtavi.repository.PersonagemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personagem")
public class PersonagemController {

    private final PersonagemRepository repository;
    public PersonagemController(PersonagemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public String criarPersonagem(@RequestBody Personagem lucas){
        try {
            return "Salvo com sucesso" + repository.save(lucas);
        }
        catch (Exception e) {
            return"Dados incorretos";
        }
    }

    @GetMapping
    public List<Personagem> mostrar(){
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void buscarId(@PathVariable int id){
        repository.findById(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable int id,
                           @RequestBody Personagem p) {
        Personagem personagem = repository.getReferenceById(id);
        personagem.setNome(p.getNome());
        personagem.setApelido(p.getApelido());
        personagem.setCategoria(p.getCategoria());
        personagem.setDescricao(p.getDescricao());
        personagem.setImagem(p.getImagem());


    }
}
