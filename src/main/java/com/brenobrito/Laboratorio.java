package com.brenobrito;

public class Laboratorio
{

  private final Coleta coleta = new Coleta();
  private final Centrifuga centrifuga = new Centrifuga();
  private final Leitura leitura = new Leitura();
  private final EmissaoDeLaudo emissaoDeLaudo = new EmissaoDeLaudo();

  public Laudo processar(String paciente, String exame, double volumeMl, double concentracao)
  {
    Amostra amostra = this.coleta.coletar(paciente, exame, volumeMl, concentracao);
    this.centrifuga.preparar(amostra);
    double valor = this.leitura.medir(amostra);
    return this.emissaoDeLaudo.emitir(amostra, valor);
  }
}
