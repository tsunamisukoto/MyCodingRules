package com.mcr.lgss.questionresolved.Services;

import com.mcr.lgss.questionresolved.Entities.Person;

/**
 * Created by scott on 10/11/2015.
 */
public class PersonService {
    public Person GetPersonById(int id)
    {
        return new Person(1,"Cal", "Daniel Calabio",  "Any String you want".getBytes());
    }
}
