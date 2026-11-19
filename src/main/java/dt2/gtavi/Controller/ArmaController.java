package dt2.gtavi.Controller;

import dt2.gtavi.entities.Arma;
import dt2.gtavi.repository.ArmaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/armas")
public class ArmaController {
    private final ArmaRepository repository;

    public ArmaController(ArmaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Arma> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arma> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Arma> criar(@Valid @RequestBody Arma registro) {
        Arma salvo = repository.save(registro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arma> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Arma dados) {
        return repository.findById(id).map(registro -> {
            registro.setNome(dados.getNome());
            registro.setCategoria(dados.getCategoria());
            registro.setFabricante(dados.getFabricante());
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

