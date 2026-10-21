package br.com.niltonpereira.finance_flow_api.service;


import br.com.niltonpereira.finance_flow_api.dto.CategoriaRequestDto;
import br.com.niltonpereira.finance_flow_api.dto.CategoriaResponseDto;
import br.com.niltonpereira.finance_flow_api.model.entity.Categoria;
import br.com.niltonpereira.finance_flow_api.model.entity.Usuario;
import br.com.niltonpereira.finance_flow_api.model.repository.CategoriaRepository;
import br.com.niltonpereira.finance_flow_api.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public CategoriaResponseDto cadastrar(CategoriaRequestDto categoriaRequestDto){

        Usuario usuario = usuarioRepository.findById(categoriaRequestDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Úsuario não encontrado: " + categoriaRequestDto.usuarioId()));


        Categoria categoria = Categoria.builder()
                .nome(categoriaRequestDto.nome())
                .tipo(String.valueOf(categoriaRequestDto.tipo()))
                .usuario(usuario)
                .build();

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return new CategoriaResponseDto(
                categoriaSalva.getId(),
                categoriaSalva.getNome(),
                categoriaSalva.getTipo(),
                categoriaSalva.getUsuario().getId()
        );
    }

    public List<CategoriaResponseDto> listarTodas(){
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaResponseDto(
                        categoria.getId(),
                        categoria.getNome(),
                        categoria.getTipo(),
                        categoria.getUsuario().getId()
                ))
                .toList();
    }
    @Transactional
    public CategoriaResponseDto atualizar(Long id, CategoriaRequestDto categoriaRequestDto){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));

        Usuario usuario = usuarioRepository.findById(categoriaRequestDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Úsuario não encontrado: " + categoriaRequestDto.usuarioId()));

        categoria.setNome(categoriaRequestDto.nome());
        categoria.setTipo(String.valueOf(categoriaRequestDto.tipo()));
        categoria.setUsuario(usuario);

        Categoria categoriaAtualizada = categoriaRepository.save(categoria);

        return new CategoriaResponseDto(
                categoriaAtualizada.getId(),
                categoriaAtualizada.getNome(),
                String.valueOf(categoriaAtualizada.getTipo()),
                categoriaAtualizada.getUsuario().getId()


        );
    }

    public void deletar(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id));

        categoriaRepository.delete(categoria);
    }
}
