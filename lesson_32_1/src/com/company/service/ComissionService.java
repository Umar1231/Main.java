package com.company.service;

import com.company.exeptions.CardTypeNotFoundExeption;
import com.company.constant.ComissionConstant;
import com.company.enums.CardType;

public class ComissionService {


    public static Double getTransactionComission(CardType CardA, CardType CardB){
        if(CardA.equals(CardType.HUMO) && CardB.equals(CardType.HUMO))
            return ComissionConstant.HumoToHumo;
        else if(CardA.equals(CardType.UZCARD) && CardB.equals(CardType.UZCARD))
            return ComissionConstant.UzCardToUzcard;
        else if(CardA.equals(CardType.HUMO) && CardB.equals(CardType.UZCARD) ||
                CardA.equals(CardType.UZCARD) && CardB.equals(CardType.HUMO))
            return ComissionConstant.UzCardToHumo;
        else
            throw new CardTypeNotFoundExeption("Card Type Not Found !");
    }
}
