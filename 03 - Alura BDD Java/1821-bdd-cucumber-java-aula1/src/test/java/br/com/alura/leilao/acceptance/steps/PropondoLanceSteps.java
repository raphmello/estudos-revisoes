package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropondoLanceSteps {

    private Lance lance;
    private Leilao leilao;
    private List<Lance> lista;

    @Before
    public void setup() {
        this.lista = new ArrayList<>();
        leilao = new Leilao("Tablet XPTO");
//        System.out.println("Before");
    }

    @After
    public void tearDown() {
//        System.out.println("After");
    }

    @Dado("um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }
    @Quando("propoe ao leilao")
    public void quando_propoe_o_lance() {
        leilao.propoe(lance);
    }
    @Entao("o lance eh aceito")
    public void entao_o_lance_eh_aceito() {
        Assertions.assertEquals(1,leilao.getLances().size());
        Assertions.assertEquals(BigDecimal.TEN,leilao.getLances().get(0).getValor());
    }

//    @Dado("varios lances validos")
//    public void varios_lances_validos() {
//        Usuario usuario1 = new Usuario("fulano");
//        lance10 = new Lance(usuario1, BigDecimal.TEN);
//        Usuario usuario2 = new Usuario("beltrano");
//        lance15 = new Lance(usuario2, new BigDecimal("15.0"));
//        leilao = new Leilao("Tablet XPTO");
//    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
        Lance lance = new Lance(new Usuario(nomeUsuario),BigDecimal.valueOf(valor));
        lista.add(lance);
    }
    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        this.lista.forEach(lance -> leilao.propoe(lance));
    }
    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        Assertions.assertEquals(this.lista.size(),leilao.getLances().size());
        Assertions.assertEquals(this.lista.get(0).getValor(),leilao.getLances().get(0).getValor());
        Assertions.assertEquals(this.lista.get(1).getValor(),leilao.getLances().get(1).getValor());
    }

    @Dado("um lance invalido de {double} reais e do usuario {string}")
    public void umLanceDeValorReais(Double valor, String nomeUsuario) {
//        System.out.println(nomeUsuario);
        lance = new Lance(BigDecimal.valueOf(valor));
    }

    @Entao("o lance nao eh aceito")
    public void oLanceNaoEhAceito() {
        Assertions.assertEquals(0,leilao.getLances().size());
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>> valores = dataTable.asMaps();
        valores.forEach(lance->{
            String valor = lance.get("valor");
            String nome = lance.get("nomeUsuario");
            Lance lance2 = new Lance(new Usuario(nome),new BigDecimal(valor));
            lista.add(lance2);
        });
    }

    @Entao("o segundo lance nao eh aceito")
    public void o_segundo_lance_nao_eh_aceito() {
        Assertions.assertEquals(1,leilao.getLances().size());
        Assertions.assertEquals(this.lista.get(0).getValor(),leilao.getLances().get(0).getValor());
    }


}
