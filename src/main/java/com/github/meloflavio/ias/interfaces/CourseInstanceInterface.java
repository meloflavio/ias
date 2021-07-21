package com.github.meloflavio.ias.interfaces;

public interface CourseInstanceInterface extends EventInterface {

    /*----instructor-----*/
    void setInstrutor(PersonInterface instrutor);
    PersonInterface getInstrutor();
}
