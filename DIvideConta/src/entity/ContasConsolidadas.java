package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContasConsolidadas {
    private Set<Responsavel> responsaveis = new HashSet<>();
    private List<ResumoDaSimplificacao> resumoDaSimplificacaoList = new ArrayList<>();

    public void setResponsaveis(Set<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public List<ResumoDaSimplificacao> getResumoDaSimplificacaoList() {
        return resumoDaSimplificacaoList;
    }

    public void addResumoDaSimplificacao(ResumoDaSimplificacao resumoDaSimplificacao) {
        this.resumoDaSimplificacaoList.add(resumoDaSimplificacao);
    }

    public ContasConsolidadas() {
    }

    public ContasConsolidadas(Set<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public Set<Responsavel> getResponsaveis() {
        return Collections.unmodifiableSet(responsaveis);
    }

    public void adicionaParticipante(Responsavel responsavel) {
        responsaveis.add(responsavel);
    }

    public Responsavel buscarResponsavel(String nome) {
        for (Responsavel responsavel : responsaveis) {
            if(responsavel.getNome().equalsIgnoreCase(nome)) {
                return responsavel;
            }
        }
        return null;
    }
}
