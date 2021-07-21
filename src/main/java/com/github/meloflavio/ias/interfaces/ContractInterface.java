package com.github.meloflavio.ias.interfaces;

public interface ContractInterface extends ThingInterface{

    String getContractAddress();
    void setContractAddress(String contractAddress);

    String getContractHashTransation();
    void setContractHashTransation(String contractHashTransation);
}
