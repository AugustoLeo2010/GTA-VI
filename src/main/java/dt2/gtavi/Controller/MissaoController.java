package dt2.gtavi.Controller;

import dt2.gtavi.entities.Missao;
import dt2.gtavi.repository.MissaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {
    private final MissaoRepository repository;

    public MissaoController(MissaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Missao> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Missao> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Missao> criar(@Valid @RequestBody Missao registro) {
        Missao salvo = repository.save(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Missao> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Missao dados) {

        return repository.findById(id).map(registro -> {

            registro.setNome(dados.getNome());
            registro.setLocal(dados.getLocal());
            registro.setDificuldade(dados.getDificuldade());
            registro.setImagem(dados.getImagem());
            registro.setDescricao(dados.getDescricao());
            return ResponseEntity.ok(repository.save(registro));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

