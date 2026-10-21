package br.com.niltonpereira.finance_flow_api.controller;


import br.com.niltonpereira.finance_flow_api.dto.UsuarioRequestDto;
import br.com.niltonpereira.finance_flow_api.dto.UsuarioResponseDto;
import br.com.niltonpereira.finance_flow_api.dto.UsuarioUpdateDto;
import br.com.niltonpereira.finance_flow_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        UsuarioResponseDto usuario = usuarioService.cadastrar(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid UsuarioUpdateDto usuarioUpdateDto){
        return ResponseEntity.ok(usuarioService.atualizar(id, usuarioUpdateDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
