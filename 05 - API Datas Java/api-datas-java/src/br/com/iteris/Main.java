package br.com.iteris;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Instant inicio = Instant.now();
        List<String> lista = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            lista.add("A");
        }

        Instant fim = Instant.now();

        Duration duracao = Duration.between(inicio,fim);
        System.out.println(duracao.toMillis());

        LocalDate hoje = LocalDate.now();
        System.out.println(hoje);

        LocalDate emissaoRG = LocalDate.of(2000, Month.JANUARY, 15);
        System.out.println(emissaoRG);

        LocalDate homemNoEspaco = LocalDate.of(1961, Month.APRIL, 12);
        LocalDate homemNaLua = LocalDate.of(1969, Month.MAY, 25);

        Period periodo = Period.between(homemNoEspaco, homemNaLua);
        System.out.println(periodo.toTotalMonths());

        LocalTime horarioDeEntrada = LocalTime.of(9, 0);
        System.out.println(horarioDeEntrada); //09:00

        LocalDateTime aberturaDaCopa = LocalDateTime.of(2014, Month.JUNE, 12, 17, 0);
        System.out.println(aberturaDaCopa);

        ZoneId fusoSaoPaulo = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime agoraEmSaoPaulo = ZonedDateTime.now(fusoSaoPaulo);
        System.out.println(agoraEmSaoPaulo);

        ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");
        ZoneId fusoHorarioDeNovaYork = ZoneId.of("America/New_York");



        LocalDateTime saidaDeSaoPauloSemFusoHorario =
                LocalDateTime.of(2014, Month.APRIL, 4, 22, 30);
        LocalDateTime chegadaEmNovaYorkSemFusoHorario =
                LocalDateTime.of(2014, Month.APRIL, 5, 7, 10);

        ZonedDateTime saidaDeSaoPauloComFusoHorario =
                ZonedDateTime.of(saidaDeSaoPauloSemFusoHorario, fusoHorarioDeSaoPaulo);
        System.out.println(saidaDeSaoPauloComFusoHorario); //2014-04-04T22:30-03:00[America/Sao_Paulo]

        ZonedDateTime chegadaEmNovaYorkComFusoHorario =
                ZonedDateTime.of(chegadaEmNovaYorkSemFusoHorario, fusoHorarioDeNovaYork);
        System.out.println(chegadaEmNovaYorkComFusoHorario); //2014-04-05T07:10-04:00[America/New_York]

        Duration duracaoDoVoo =
                Duration.between(saidaDeSaoPauloComFusoHorario, chegadaEmNovaYorkComFusoHorario);
        System.out.println(duracaoDoVoo); //PT9H40M

        MonthDay natal = MonthDay.of(Month.DECEMBER, 25);
        YearMonth copaDoMundo2014 = YearMonth.of(2014, Month.JUNE);

        //formatter
        System.out.println(chegadaEmNovaYorkComFusoHorario.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(new Locale("pt","br"))));

        // somar dias
        LocalDate hoje2 = LocalDate.now();
        LocalDate amanha = hoje2.plusDays(1);

        //UNTIL
        MonthDay natal2 = MonthDay.of(Month.DECEMBER, 25);
        LocalDate natalDesseAno = natal2.atYear(Year.now().getValue());
        long diasAteONatal = LocalDate.now()
                .until(natalDesseAno,  ChronoUnit.DAYS);

        TemporalAdjuster ajustadorParaProximaTerca = TemporalAdjusters.next(DayOfWeek.TUESDAY);
        LocalDate proximaTerca = LocalDate.now().with(ajustadorParaProximaTerca);
        System.out.println(proximaTerca);

        LocalDate hoje3 = LocalDate.now();
        LocalDate amanha3 = hoje3.plusDays(1);
        boolean mesmoObjeto = hoje3 == amanha3; //false, já que é imutável

        Instant hoje4 = Instant.now();
        LocalDateTime agora2 = LocalDateTime.ofInstant(hoje4,ZoneOffset.);
        System.out.println(agora2);
    }
}
