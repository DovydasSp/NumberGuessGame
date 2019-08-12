package com.example.dovspe.NumberGuessGame;

public class Duomenys
{
    int id;
    int sunkumas;
    int sugeneruotas_sk;
    int spejimu;
    int liko_spejimu;

    public Duomenys(int _id, int _sunkumas, int _sugeneruotas_sk, int _spejimu, int _liko_spejimu)
    {
        id = _id;
        sunkumas = _sunkumas;
        sugeneruotas_sk = _sugeneruotas_sk;
        spejimu = _spejimu;
        liko_spejimu = _liko_spejimu;
    }

    public Duomenys()
    {}
}
