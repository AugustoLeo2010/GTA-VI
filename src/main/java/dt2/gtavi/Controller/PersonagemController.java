
package dt2.gtavi.Controller;

import dt2.gtavi.entities.Personagem;
import dt2.gtavi.repository.PersonagemRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    private final PersonagemRepository repository;

    public PersonagemController(PersonagemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Personagem> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.<Personagem>notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personagem> criar(@Valid @RequestBody Personagem registro) {
        Personagem salvo = repository.save(registro);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Personagem dados) {

        return repository.findById(id).map(registro -> {

            registro.setNome(dados.getNome());
            registro.setApelido(dados.getApelido());
            registro.setCategoria(dados.getCategoria());
            registro.setImagem(dados.getImagem());
            registro.setDescricao(dados.getDescricao());

            return ResponseEntity.ok(repository.save(registro));

        }).orElseGet(() -> ResponseEntity.<Personagem>notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

