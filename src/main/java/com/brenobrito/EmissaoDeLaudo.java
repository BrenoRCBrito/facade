package com.brenobrito;

public class EmissaoDeLaudo
{

  public Laudo emitir(Amostra amostra, double valor)
  {
    if (!amostra.isLida())
    {
      throw new IllegalStateException("Amostra nao lida");
    }
    return new Laudo(amostra.getPaciente(), amostra.getExame(), valor, situacao(amostra.getExame(), valor));
  }

  private String situacao(String exame, double valor)
  {
    return switch (exame)
    {
      case "Glicemia" -> valor <= 99.0 ? "Normal" : "Alterado";
      case "Colesterol" -> valor < 190.0 ? "Normal" : "Alterado";
      case "Hemoglobina" -> valor >= 12.0 && valor <= 16.0 ? "Normal" : "Alterado";
      default -> throw new IllegalArgumentException("Exame desconhecido: " + exame);
    };
  }
}
