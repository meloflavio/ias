package com.github.meloflavio.ias.interfaces;

import java.util.Date;
import java.util.List;

public interface EventInterface extends ThingInterface{

    /*----attendee-----*/
    List<PersonInterface> getParticipantes();
    /*----startDate-----*/
    Date getDataInicio();
    void setDataInicio(Date dataInicio);
    /*----endDate-----*/
    Date getDataFim();
    void setDataFim(Date dataFim);


}
