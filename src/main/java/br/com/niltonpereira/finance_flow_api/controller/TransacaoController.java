package br.com.niltonpereira.finance_flow_api.controller;


import br.com.niltonpereira.finance_flow_api.dto.TransacaoRequestDto;
import br.com.niltonpereira.finance_flow_api.dto.TransacaoResponseDto;
import br.com.niltonpereira.finance_flow_api.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDto> salvar(@Valid @RequestBody TransacaoRequestDto transacaoRequestDto){
        TransacaoResponseDto transacao = transacaoService.salvar(transacaoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDto>> listarTodos(){
        return ResponseEntity.ok(transacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDto> buscarPorId( @PathVariable Long id){
        return ResponseEntity.ok(transacaoService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<TransacaoResponseDto> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody TransacaoRequestDto transacaoRequestDto){
        TransacaoResponseDto transacao =  transacaoService.atualizar(id, transacaoRequestDto);

        return ResponseEntity.ok(transacao);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        transacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
