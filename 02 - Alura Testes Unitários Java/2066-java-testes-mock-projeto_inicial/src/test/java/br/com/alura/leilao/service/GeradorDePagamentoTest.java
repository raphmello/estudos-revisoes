package br.com.alura.leilao.service;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.*;

class GeradorDePagamentoTest {

    private GeradorDePagamento service;

    @Mock
    private PagamentoDao pagamentoDao;

    @Captor
    private ArgumentCaptor<Pagamento> captor;

    @Mock
    private Clock clock;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.service = new GeradorDePagamento(pagamentoDao, clock);
    }

    @Test
    void deveriaCriarPagamentoParaVencedorDoLeilao() {
        Leilao leilao = leilao();
        Lance lanceVencedor = leilao.getLanceVencedor();
        LocalDate data = LocalDate.of(2020,12,7);
        Instant instant = data.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Mockito.when(clock.instant()).thenReturn(instant);
        Mockito.when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        service.gerarPagamento(lanceVencedor);
        Mockito.verify(pagamentoDao).salvar(captor.capture());
        Pagamento pagamento = captor.getValue();
        Assertions.assertEquals(data.plusDays(1),pagamento.getVencimento());
        Assertions.assertEquals(lanceVencedor.getValor(),pagamento.getValor());
        Assertions.assertFalse(pagamento.getPago());
        Assertions.assertEquals(lanceVencedor.getUsuario(),pagamento.getUsuario());
        Assertions.assertEquals(leilao,pagamento.getLeilao());
    }

    private Leilao leilao() {
        Leilao leilao = new Leilao("Celular",new BigDecimal("500"),new Usuario("Fulano"));
        Lance segundo = new Lance(new Usuario("Ciclano"), new BigDecimal("900"));

        leilao.propoe(segundo);
        leilao.setLanceVencedor(segundo);

        return leilao;
    }

}