package br.com.niltonpereira.finance_flow_api.service;


import br.com.niltonpereira.finance_flow_api.dto.TransacaoRequestDto;
import br.com.niltonpereira.finance_flow_api.dto.TransacaoResponseDto;
import br.com.niltonpereira.finance_flow_api.model.entity.Categoria;
import br.com.niltonpereira.finance_flow_api.model.entity.Transacao;
import br.com.niltonpereira.finance_flow_api.model.entity.Usuario;
import br.com.niltonpereira.finance_flow_api.model.repository.CategoriaRepository;
import br.com.niltonpereira.finance_flow_api.model.repository.TransacaoRepository;
import br.com.niltonpereira.finance_flow_api.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository ;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional

    public TransacaoResponseDto salvar(TransacaoRequestDto transacaoRequestDto){

        Usuario usuario = usuarioRepository.findById(transacaoRequestDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(transacaoRequestDto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrado."));

        Transacao transacao = Transacao.builder()
                .descricao(transacaoRequestDto.descricao())
                .valor(transacaoRequestDto.valor())
                .tipo(transacaoRequestDto.tipo())
                .data(transacaoRequestDto.data())
                .usuario(usuario)
                .categoria(categoria)
                .build();

        Transacao transacaoSalva = transacaoRepository.save(transacao);

        return new TransacaoResponseDto(
                transacaoSalva.getId(),
                transacaoSalva.getDescricao(),
                transacaoSalva.getValor(),
                transacaoSalva.getTipo().name(),
                transacaoSalva.getData(),
                transacaoSalva.getCategoria().getId(),
                transacaoSalva.getUsuario().getId()
        );
    }

    @Transactional
    public List<TransacaoResponseDto> listarTodos(){
        return transacaoRepository.findAll().stream()
                .map(transacao -> new TransacaoResponseDto(
                        transacao.getId(),
                        transacao.getDescricao(),
                        transacao.getValor(),
                        transacao.getTipo().name(),
                        transacao.getData(),
                        transacao.getCategoria().getId(),
                        transacao.getUsuario().getId()
                ))
                .toList();
    }

    @Transactional
    public TransacaoResponseDto buscarPorId(Long id){
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada: " + id));

        return new TransacaoResponseDto(
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getTipo().name(),
                transacao.getData(),
                transacao.getCategoria().getId(),
                transacao.getUsuario().getId()
        );
    }

    @Transactional
    public TransacaoResponseDto atualizar( Long id, TransacaoRequestDto transacaoRequestDto){
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaçaonão encontrada: " + id));

        Usuario usuario = usuarioRepository.findById(transacaoRequestDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(transacaoRequestDto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encotrada"));

        transacao.setDescricao(transacaoRequestDto.descricao());
        transacao.setValor(transacaoRequestDto.valor());
        transacao.setTipo(transacaoRequestDto.tipo());
        transacao.setData(transacaoRequestDto.data());
        transacao.setUsuario(usuario);
        transacao.setCategoria(categoria);

        Transacao trasacaoAtualizada = transacaoRepository.save(transacao);

        return new TransacaoResponseDto(
                trasacaoAtualizada.getId(),
                trasacaoAtualizada.getDescricao(),
                trasacaoAtualizada.getValor(),
                trasacaoAtualizada.getTipo().name(),
                trasacaoAtualizada.getData(),
                trasacaoAtualizada.getCategoria().getId(),
                trasacaoAtualizada.getUsuario().getId()
        );
    }

    @Transactional
    public void deletar(Long id){
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada: " + id));

        transacaoRepository.delete(transacao);
    }
}
