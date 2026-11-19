package com.brenobrito;

public class Laudo
{

  private final String paciente;
  private final String exame;
  private final double valor;
  private final String situacao;

  Laudo(String paciente, String exame, double valor, String situacao)
  {
    this.paciente = paciente;
    this.exame = exame;
    this.valor = valor;
    this.situacao = situacao;
  }

  public String getPaciente()
  {
    return this.paciente;
  }

  public String getExame()
  {
    return this.exame;
  }

  public double getValor()
  {
    return this.valor;
  }

  public String getSituacao()
  {
    return this.situacao;
  }
}
