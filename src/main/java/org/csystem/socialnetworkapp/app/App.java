package org.csystem.socialnetworkapp.app;

import org.csystem.socialnetworkapp.entity.Person;
import org.csystem.socialnetworkapp.service.CheckPersonEligibleForSelectiveMaleBekarWithRangeService;
import org.csystem.socialnetworkapp.service.CheckPersonWithRangeMaleBekarCityService;
import org.csystem.socialnetworkapp.service.ICheckPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args)
    {
        /*
               What if you want to print members of a specified sex, or a combination of a specified gender and age range?
               What if you decide to change the Person class and add other attributes such as relationship status or geographical location?
               Although this method is more generic than printPersonsOlderThan, trying to create a separate method for each possible search query can still lead to brittle code.
               You can instead separate the code that specifies the criteria for which you want to search in a different class.
        */

        //İnit data
        List<Person> persons = new ArrayList<>();
        initData(persons);

        //printPersonsOlderThan(persons, 25);           //bu listede, 25 yaşından büyük olanlar
        //printPersonsWithAgeRange(persons, 20, 35);    //bu listede 20 ile 35 yaş arasında olanlar
        //printPersonsWithAgeRange(persons, 20, 35, Person.Sex.MALE); //bu listede 20 ile 35 yaş arasındaki erkekler

        //Bu yaş aralığında, erkek olup, bekar olanları getir.
        //printPersonsWithAgeRange(persons, 20, 50, Person.Sex.MALE, Person.MaritalStatus.BEKAR);

        //eee geolocation eklense sınıfa git constructora ekle veri elemanı al.
        //getter setter yap.
        //sonra metot yaz.

        //listeyi, criteria interface ile isteneni vermek, erkek ve bekar  20 -50
        //printPersonsWithCriteria(persons, new CheckPersonEligibleForSelectiveMaleBekarWithRangeService());

        //Şimdi sınıfa yeni bir özellik eklendiğinde işimiz daha kolay. city ekledim
        //printPersonsWithCriteria(persons, new CheckPersonWithRangeMaleBekarCityService());

        //yani özellik ekle anlaşmaya uyan bir nesne yaz onu gönder.

        //bypass the local classses service is local class with anonim class.
        //Sonuçta, CheckPersonWithRangeMaleBekarCityService bundan nesne alıcakmısın
        //CheckPersonWithRangeMaleBekarCityService cs = new CheckPersonWithRangeMaleBekarCityService(); gereksiz, search için anlamlı

        /*anonim sınıf nesnesi veriyorum.
        printPersonsWithCriteria(persons, new ICheckPerson() { //dinamik türün testi çağrılır yani bizimki.
            @Override
            public boolean test(Person p)
            {
                return p.getGender() == Person.Sex.MALE && //erkek
                        p.getMaritalStatus() == Person.MaritalStatus.EVLI && //bekar olup
                        p.getAge() >= 20 &&
                        p.getAge() <= 51 && //bu yaş aralığında
                        p.getCity().equals("ankara"); //ankaralı
            }
        });*/


        //Approach 5: Specify Search Criteria Code with a Lambda Expression
        //Neden lambda kullanmayasın search criteria. Anonim vermek yerine lambda ifadesi ver.
        //Bu lambdaya göre işlemleri yap.
        /*printPersonsWithCriteria(persons,
                (Person p) -> // just p
                p.getGender() == Person.Sex.MALE && //erkek
                p.getMaritalStatus() == Person.MaritalStatus.EVLI && //bekar olup
                p.getAge() >= 20 &&
                p.getAge() <= 51 && //bu yaş aralığında
                p.getCity().equals("ankara")); //ankaralı);

        //You can use a standard functional interface in place of the interface CheckPerson, which reduces even further the amount of code required.
        //Predicate(test with search criteria), Function, Kendi yazdığın farketmez.

        printPersonsWithPredicate(persons,
                        (Person p)
                        ->
                        p.getGender() == Person.Sex.MALE && //erkek
                        p.getMaritalStatus() == Person.MaritalStatus.EVLI && //bekar olup
                        p.getAge() >= 20 &&
                        p.getAge() <= 51 &&
                        p.getCity().equals("ankara")); //ankaralı););
                        */

        //printPersonsWithPredicate(persons, generateLambda());

        //Benim testimden(Predicate - tahmin) geçerse
        //Benim accept metodum çalışsın.
        //Liste, collection üzerinde tam bir kontrol.
        //Şu kriterlere uyanlara mail atmak istiyorum.
        //Senaryo sana kalmış.
        processPersons(persons,
                p -> p.getGender() == Person.Sex.MALE && //erkek
                        p.getMaritalStatus() == Person.MaritalStatus.EVLI && //bekar olup
                p.getAge() >= 20 &&
                p.getAge() <= 51 &&
                p.getCity().equals("ankara"), p -> System.out.println(p.getName().toUpperCase()));


    }

    //Genel yazmak
    //Senin verdiğin kritierden geçerse
    //Senin verdiğin kodu çalıştır. Daha ne olsun.
    public static  void processPersons(List<Person> persons, Predicate<Person> tester, Consumer<Person> con)
    {
        for (Person p : persons) {
            if (tester.test(p)) //adamın verdiği testen geçerse
                con.accept(p); //adamın verdiği kodu çalıştır, derleyici nesne işlerini virtual function table kullanarak halletti.
        }
    }

    public static Predicate<Person> generateLambda()
    {
        return (Person p) -> // just p
                p.getGender() == Person.Sex.MALE && //erkek
                        p.getMaritalStatus() == Person.MaritalStatus.EVLI && //bekar olup
                        p.getAge() >= 20 &&
                        p.getAge() <= 51 && //bu yaş aralığında
                        p.getCity().equals("ankara"); //ankaralı);
    }


    //you don't have to rewrite methods if you change the structure of the Person with interface and dinamik tür.
    public static void printPersonsWithPredicate(List<Person> people, Predicate<Person> tester)
    {
        for (Person p: people) {
            if (tester.test(p))
                System.out.println(p.toString());
        }
    }

    public static void printPersonsWithCriteria(List<Person> persons, Predicate<Person> pred)
    {
        for (Person p : persons) {
            if (pred.test(p)) //p yi gönder, test true dönerse, testten geçersen kriter istenendir, dinamik türün testi
                System.out.println(p.toString());
        }
    }

    //öyleki marital status eklenseydi. Evli olanları getir
    public static void printPersonsWithAgeRange(List<Person> persons, int low, int high, Person.Sex gender, Person.MaritalStatus maritalStatus)
    {
        for (Person p : persons) {
            int age = p.getAge();
            if (p.getMaritalStatus() == maritalStatus && p.getGender() == gender && age >= low && age <= high)
                System.out.println(p.toString());
        }
    }


    public static void printPersonsWithAgeRange(List<Person> persons, int low, int high, Person.Sex gender)
    {
        for (Person p : persons) {
            int age = p.getAge();
            if (p.getGender() == gender && age >= low && age <= high)
                System.out.println(p.toString());
        }
    }


    public static void printPersonsWithAgeRange(List<Person> persons, int low, int high)
    {
        for (Person p : persons) {
            int age = p.getAge();
            if (age >= low && age <= high)
                System.out.println(p.toString());
        }
    }

    public static void printPersonsOlderThan(List<Person> persons, int age)
    {
        for (Person p : persons) {
            if (p.getAge() > age)
                System.out.println(p.toString());
        }
    }

    public static void initData(List<Person> persons)
    {
        persons.add(new Person("tugberk", LocalDate.of(1994, 5, 23), Person.Sex.MALE, "m@gmail.com", Person.MaritalStatus.BEKAR, "ankara"));
        persons.add(new Person("sumeyye", LocalDate.of(1994, 3, 14), Person.Sex.FEMALE, "s@gmail.com", Person.MaritalStatus.BEKAR, "trabzon"));
        persons.add(new Person("bugra", LocalDate.of(1998, 2, 21), Person.Sex.MALE, "b@gmail.com", Person.MaritalStatus.BEKAR, "istanbul"));
        persons.add(new Person("ertan", LocalDate.of(1968, 8, 1), Person.Sex.MALE, "er@gmail.com", Person.MaritalStatus.EVLI, "ankara"));
        persons.add(new Person("esma", LocalDate.of(1971, 6, 1), Person.Sex.FEMALE, "es@gmail.com", Person.MaritalStatus.EVLI, "ankara"));
    }
}

