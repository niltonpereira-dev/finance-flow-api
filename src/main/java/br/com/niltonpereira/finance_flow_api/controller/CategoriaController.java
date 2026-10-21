package br.com.niltonpereira.finance_flow_api.controller;

import br.com.niltonpereira.finance_flow_api.dto.CategoriaRequestDto;
import br.com.niltonpereira.finance_flow_api.dto.CategoriaResponseDto;
import br.com.niltonpereira.finance_flow_api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> cadastrar(@Valid @RequestBody CategoriaRequestDto categoriaRequestDto){
        CategoriaResponseDto categoria = categoriaService.cadastrar(categoriaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);

    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarTodos(){
        List<CategoriaResponseDto> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody CategoriaRequestDto categoriaRequestDto){
        CategoriaResponseDto categoria = categoriaService.atualizar(id, categoriaRequestDto);

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        categoriaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
