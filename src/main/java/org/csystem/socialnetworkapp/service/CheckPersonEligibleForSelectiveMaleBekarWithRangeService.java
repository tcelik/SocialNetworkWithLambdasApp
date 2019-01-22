package org.csystem.socialnetworkapp.service;

import org.csystem.socialnetworkapp.entity.Person;

public class CheckPersonEligibleForSelectiveMaleBekarWithRangeService implements ICheckPerson {
    @Override
    public boolean test(Person p)
    {
        return p.getGender() == Person.Sex.MALE && //erkek ise
                p.getAge() >= 20 && //20 den büyük ise
                p.getAge() <= 50 &&
                p.getMaritalStatus() == Person.MaritalStatus.BEKAR; //bekar ise true döner yoksa false döner.
    }

}
