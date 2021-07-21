package com.github.meloflavio.ias.interfaces;

import com.github.meloflavio.ias.model.StatusDocumento;

public interface CreativeWorkInterface extends ThingInterface {

    /*creativeWorkStatus*/
    void setStatusDocumento(StatusDocumento status);
    StatusDocumento getStatusDocumento();

    /*accountablePerson*/
    void setResponsavel(PersonInterface responsavel);
    PersonInterface getResponsavel();
}
