package com.brenobrito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LaboratorioTest
{

  private Laboratorio laboratorio;

  @BeforeEach
  void abrirOLaboratorio()
  {
    this.laboratorio = new Laboratorio();
  }

  @Test
  void deveEmitirLaudoEmUmaUnicaChamada()
  {
    Laudo laudo = laboratorio.processar("Ana", "Glicemia", 5.0, 90.0);

    assertEquals("Ana", laudo.getPaciente());
    assertEquals("Glicemia", laudo.getExame());
    assertEquals(88.2, laudo.getValor(), 0.001);
    assertEquals("Normal", laudo.getSituacao());
  }

  @Test
  void deveApontarResultadoAlterado()
  {
    Laudo laudo = laboratorio.processar("Bruno", "Glicemia", 5.0, 150.0);

    assertEquals("Alterado", laudo.getSituacao());
  }

  @Test
  void deveUsarAFaixaDeReferenciaDeCadaExame()
  {
    assertEquals("Normal", laboratorio.processar("Caio", "Colesterol", 5.0, 150.0).getSituacao());
    assertEquals("Alterado", laboratorio.processar("Caio", "Colesterol", 5.0, 200.0).getSituacao());
    assertEquals("Normal", laboratorio.processar("Caio", "Hemoglobina", 5.0, 14.0).getSituacao());
    assertEquals("Alterado", laboratorio.processar("Caio", "Hemoglobina", 5.0, 11.0).getSituacao());
  }

  @Test
  void deveRecusarVolumeInsuficiente()
  {
    assertThrows(IllegalArgumentException.class, () -> laboratorio.processar("Duda", "Glicemia", 1.0, 90.0));
  }

  @Test
  void deveRecusarExameDesconhecido()
  {
    assertThrows(IllegalArgumentException.class, () -> laboratorio.processar("Elis", "Vitamina D", 5.0, 30.0));
  }

  @Test
  void deveNumerarAsAmostrasEmSequencia()
  {
    Coleta coleta = new Coleta();

    assertEquals("AM-1", coleta.coletar("Fabio", "Glicemia", 5.0, 90.0).getCodigo());
    assertEquals("AM-2", coleta.coletar("Gilda", "Glicemia", 5.0, 90.0).getCodigo());
  }

  @Test
  void deveRecusarLeituraSemCentrifugar()
  {
    Amostra amostra = new Coleta().coletar("Hugo", "Glicemia", 5.0, 90.0);

    assertThrows(IllegalStateException.class, () -> new Leitura().medir(amostra));
  }

  @Test
  void deveRecusarLaudoSemLeitura()
  {
    Amostra amostra = new Coleta().coletar("Iris", "Glicemia", 5.0, 90.0);
    new Centrifuga().preparar(amostra);

    assertThrows(IllegalStateException.class, () -> new EmissaoDeLaudo().emitir(amostra, 88.2));
  }

  @Test
  void deveRecusarSegundaCentrifugacao()
  {
    Amostra amostra = new Coleta().coletar("Joana", "Glicemia", 5.0, 90.0);
    Centrifuga centrifuga = new Centrifuga();
    centrifuga.preparar(amostra);

    assertThrows(IllegalStateException.class, () -> centrifuga.preparar(amostra));
  }

  @Test
  void deveChegarAoMesmoLaudoQueOFluxoManual()
  {
    Amostra amostra = new Coleta().coletar("Lia", "Colesterol", 5.0, 180.0);
    new Centrifuga().preparar(amostra);
    double valor = new Leitura().medir(amostra);
    Laudo manual = new EmissaoDeLaudo().emitir(amostra, valor);

    Laudo pelaFachada = laboratorio.processar("Lia", "Colesterol", 5.0, 180.0);

    assertEquals(manual.getValor(), pelaFachada.getValor(), 0.001);
    assertEquals(manual.getSituacao(), pelaFachada.getSituacao());
  }

  @Test
  void deveDeixarAAmostraPreparadaELidaAoFinal()
  {
    Amostra amostra = new Coleta().coletar("Mara", "Glicemia", 5.0, 90.0);
    new Centrifuga().preparar(amostra);
    new Leitura().medir(amostra);

    assertTrue(amostra.isPreparada());
    assertTrue(amostra.isLida());
    assertEquals(5.0, amostra.getVolumeMl(), 0.001);
  }
}
