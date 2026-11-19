package com.brenobrito;

public class Centrifuga
{

  public void preparar(Amostra amostra)
  {
    if (amostra.isPreparada())
    {
      throw new IllegalStateException("Amostra ja centrifugada");
    }
    amostra.marcarPreparada();
  }
}
