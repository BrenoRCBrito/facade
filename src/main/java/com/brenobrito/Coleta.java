package com.brenobrito;

public class Coleta
{

  private static final double VOLUME_MINIMO_ML = 2.0;

  private int sequencia;

  public Amostra coletar(String paciente, String exame, double volumeMl, double concentracao)
  {
    if (volumeMl < VOLUME_MINIMO_ML)
    {
      throw new IllegalArgumentException("Volume insuficiente para a coleta");
    }
    this.sequencia++;
    return new Amostra("AM-" + this.sequencia, paciente, exame, volumeMl, concentracao);
  }
}
