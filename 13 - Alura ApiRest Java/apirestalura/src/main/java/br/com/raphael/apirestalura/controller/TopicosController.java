package br.com.raphael.apirestalura.controller;

import br.com.raphael.apirestalura.controller.form.TopicoForm;
import br.com.raphael.apirestalura.dto.TopicoDto;
import br.com.raphael.apirestalura.modelo.Curso;
import br.com.raphael.apirestalura.modelo.Topico;
import br.com.raphael.apirestalura.repository.CursoRepository;
import br.com.raphael.apirestalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        List<Topico> topicos = new ArrayList<>();
        if (nomeCurso == null) {
            topicos = topicoRepository.findAll();
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        }
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

}
