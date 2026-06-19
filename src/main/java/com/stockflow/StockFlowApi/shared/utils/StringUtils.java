package com.stockflow.StockFlowApi.shared.utils;

public final class StringUtils {

    public static String stringSaveFormat(String text){
        return ((text.substring(0, 1).toUpperCase() +
                text.substring(1).toLowerCase())
                .strip());
    }
}
