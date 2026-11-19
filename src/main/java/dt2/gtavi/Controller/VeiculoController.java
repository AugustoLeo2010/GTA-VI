
package dt2.gtavi.Controller;

import dt2.gtavi.entities.Veiculo;
import dt2.gtavi.repository.VeiculoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoRepository repository;

    public VeiculoController(VeiculoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Veiculo> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> criar(@Valid @RequestBody Veiculo registro) {
        Veiculo salvo = repository.save(registro);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Veiculo dados) {

        return repository.findById(id).map(registro -> {

            registro.setNome(dados.getNome());
            registro.setMarca(dados.getMarca());
            registro.setTipo(dados.getTipo());
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

