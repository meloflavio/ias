package com.github.meloflavio.ias.util;


import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class DefaultGasProvider extends StaticGasProvider {
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(100_000_000);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(4_100_000_000L);

    public DefaultGasProvider() {
        super(GAS_PRICE, GAS_LIMIT);
    }
}