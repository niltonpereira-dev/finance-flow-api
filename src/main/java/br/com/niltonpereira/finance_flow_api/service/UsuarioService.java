package br.com.niltonpereira.finance_flow_api.service;


import br.com.niltonpereira.finance_flow_api.dto.UsuarioRequestDto;
import br.com.niltonpereira.finance_flow_api.dto.UsuarioResponseDto;
import br.com.niltonpereira.finance_flow_api.dto.UsuarioUpdateDto;
import br.com.niltonpereira.finance_flow_api.model.entity.Usuario;
import br.com.niltonpereira.finance_flow_api.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDto cadastrar(UsuarioRequestDto usuarioRequestDto){
        Usuario usuario = Usuario.builder()
                .nome(usuarioRequestDto.nome())
                .email(usuarioRequestDto.email())
                .senha(usuarioRequestDto.senha())
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> listarTodos(){

        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail()
                ))
                .toList();
    }

    @Transactional
    public UsuarioResponseDto atualizar(Long id, UsuarioUpdateDto usuarioUpdateDto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Úsuario não encontrado com o ID: " + id));

        usuario.setNome(usuarioUpdateDto.nome());
        usuario.setEmail(usuarioUpdateDto.email());


        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getEmail()
        );
    }

    @Transactional
    public void deletar(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Úsuario não encontrado com o ID: " + id));

        usuarioRepository.delete(usuario);
    }
}
