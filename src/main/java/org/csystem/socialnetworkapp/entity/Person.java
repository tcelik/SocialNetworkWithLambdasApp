package org.csystem.socialnetworkapp.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Person {
    public enum Sex { //enum bildirimi
        MALE, FEMALE
    }

    public enum MaritalStatus {
        EVLI, BEKAR
    }

    private String m_name;
    private LocalDate m_birthday;
    //enum type
    private Sex m_gender;
    private MaritalStatus m_maritalStatus;
    private String m_mail;
    private String m_city;

    public Person(String name, LocalDate birthday, Sex gender, String mail, MaritalStatus maritalStatus, String city)
    {
        m_name = name;
        m_birthday = birthday;
        m_gender = gender;
        m_mail = mail;
        m_maritalStatus = maritalStatus;
        m_city = city;
    }

    public void setCity(String city)
    {
        m_city = city;
    }

    public String getCity()
    {
        return m_city;
    }

    public int getAge()
    {
        return (int) ChronoUnit.YEARS.between(m_birthday, LocalDate.now());
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public LocalDate getBirthday()
    {
        return m_birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        m_birthday = birthday;
    }

    public Sex getGender()
    {
        return m_gender;
    }

    public void setGender(Sex gender)
    {
        m_gender = gender;
    }

    public String getMail()
    {
        return m_mail;
    }

    public void setMail(String mail)
    {
        m_mail = mail;
    }

    public MaritalStatus getMaritalStatus()
    {
        return m_maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus)
    {
        m_maritalStatus = maritalStatus;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "m_name='" + m_name + '\'' +
                ", m_birthday=" + m_birthday +
                ", m_gender=" + m_gender +
                ", m_maritalStatus=" + m_maritalStatus +
                ", m_mail='" + m_mail + '\'' +
                ", m_city='" + m_city + '\'' +
                '}';
    }
}
