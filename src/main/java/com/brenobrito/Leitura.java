package com.brenobrito;

public class Leitura
{

  private static final double FATOR_DE_CALIBRACAO = 0.98;

  public double medir(Amostra amostra)
  {
    if (!amostra.isPreparada())
    {
      throw new IllegalStateException("Amostra nao centrifugada");
    }
    amostra.marcarLida();
    return amostra.getConcentracao() * FATOR_DE_CALIBRACAO;
  }
}
