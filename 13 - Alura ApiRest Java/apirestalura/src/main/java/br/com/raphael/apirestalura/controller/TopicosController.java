package br.com.raphael.apirestalura.controller;

import br.com.raphael.apirestalura.controller.form.TopicoForm;
import br.com.raphael.apirestalura.controller.form.UpdateTopicoForm;
import br.com.raphael.apirestalura.dto.DetalhesDoTopicoDto;
import br.com.raphael.apirestalura.dto.TopicoDto;
import br.com.raphael.apirestalura.modelo.Topico;
import br.com.raphael.apirestalura.repository.CursoRepository;
import br.com.raphael.apirestalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,
                                 @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        //http://localhost:8080/topicos?page=0&size=3&sort=id,asc&sort=dataCriacao,desc <<< COMO FAZER A REQUISIÇÃO NO POSTMAN
        Page<Topico> topicos;
//        Pageable pageable = PageRequest.of(pagina, qtd, Sort.Direction.DESC,ordenacao);
        if (nomeCurso == null) {
            topicos = topicoRepository.findAll(pageable);
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso, pageable);
        }
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
