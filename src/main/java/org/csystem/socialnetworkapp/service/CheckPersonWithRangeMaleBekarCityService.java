package org.csystem.socialnetworkapp.service;

import org.csystem.socialnetworkapp.entity.Person;

//Burada istediğin kriteri yazıyorsun.
//Sınıfa veri elemanı eklesen bile cons ekle, getter setter yap.
public class CheckPersonWithRangeMaleBekarCityService implements ICheckPerson {

    @Override
    public boolean test(Person p)
    {
        return p.getGender() == Person.Sex.FEMALE &&
                p.getMaritalStatus() == Person.MaritalStatus.BEKAR &&
                p.getAge() >= 20 &&
                p.getAge() <= 50 &&
                p.getCity().equals("trabzon");
    }
}
