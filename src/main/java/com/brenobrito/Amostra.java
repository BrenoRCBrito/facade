package com.brenobrito;

public class Amostra
{

  private final String codigo;
  private final String paciente;
  private final String exame;
  private final double volumeMl;
  private final double concentracao;
  private boolean preparada;
  private boolean lida;

  Amostra(String codigo, String paciente, String exame, double volumeMl, double concentracao)
  {
    this.codigo = codigo;
    this.paciente = paciente;
    this.exame = exame;
    this.volumeMl = volumeMl;
    this.concentracao = concentracao;
  }

  public String getCodigo()
  {
    return this.codigo;
  }

  public String getPaciente()
  {
    return this.paciente;
  }

  public String getExame()
  {
    return this.exame;
  }

  public double getVolumeMl()
  {
    return this.volumeMl;
  }

  public boolean isPreparada()
  {
    return this.preparada;
  }

  public boolean isLida()
  {
    return this.lida;
  }

  double getConcentracao()
  {
    return this.concentracao;
  }

  void marcarPreparada()
  {
    this.preparada = true;
  }

  void marcarLida()
  {
    this.lida = true;
  }
}
