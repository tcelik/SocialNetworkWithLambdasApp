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
        Iterable<Person> persons = new ArrayList<>();
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
       /* processPersons(persons,
                p -> p.getGender() == Person.Sex.MALE && //erkek
                        p.getMaritalStatus() == Person.MaritalStatus.EVLI && //bekar olup
                p.getAge() >= 20 &&
                p.getAge() <= 51 &&
                p.getCity().equals("ankara"), p -> System.out.println(p.getName().toUpperCase()));

        /*processPersonsIf(persons, //al sana liste
                p -> p.getGender() == Person.Sex.MALE &&
                        p.getMaritalStatus() == Person.MaritalStatus.EVLI &&
                        p.getAge() >= 20 &&
                        p.getAge() <= 51 &&
                        p.getCity().equals("ankara"), //bu koşullara uyanların
                p -> p.getName(), //isimlerini
                name -> System.out.println(name)); //ekrana bas.*/




        /*Approach 8: Use Generics More Extensively
        processElementsHow(persons,
                p -> p.getGender() == Person.Sex.MALE &&
                p.getMaritalStatus() == Person.MaritalStatus.EVLI &&
                p.getAge() >= 20 &&
                p.getAge() <= 51 &&
                p.getCity().equals("ankara"),
                p -> p.getMail(),
                mail -> sendEmail(mail));
        */


        //stream almak, nesneleri almak
        ((ArrayList<Person>) persons).stream().forEach(person -> sendEmail(person.getMail()));

        //adamlar yazmış iterable default metot her adımda nesne'lere consume etmek.
        persons.forEach(p -> sendEmail(p.getMail()));

        //zaten processElementHow yazılmış metotlar yazılmış yani lambda ifadesi bekleyen.
        ((ArrayList<Person>) persons).stream().forEach(p -> System.out.println(p.getName()));

        //aynısı yukarıdakinin.
        for (Person p : persons)
            System.out.println(p.getName());

        //forEach consumer alan bir arayüz.
        persons.forEach(person -> System.out.println(person.getAge()));

        //fluent kalıbı stream nesneler var içinde collection tutuyor dinamik sınıf her neyse.
        //filter criteria test için yazılmış filtrelediklerini bir listede tutuyor aslında stream de tutuyor içinde byte var içinde collection var.
        ((ArrayList<Person>) persons).stream().filter(p -> p.getGender() == Person.Sex.MALE &&
                p.getMaritalStatus() == Person.MaritalStatus.EVLI &&
                p.getAge() >= 20 &&
                p.getAge() <= 51 &&
                p.getCity().equals("ankara")).map(p -> p.getMail()).forEach(App::sendEmail); //forEach(mail -> sendMail(mail)) ile aynı

        //Amaç ortada bir dinamik tür olsun ve onun metotlarına çağrısı olduğu için lambda yerine çözünürlük operatörü
        //kullanımı da yaygın
        //action.accept(p) ne bu? bir metot çalışsın dimi al sana p bu p ile birşey yap mail -> sendMail

        //e bu noktada doğrudan sendMail verilebilseydi. harika olmazmıydı. App::sendMail yazman yeterli maili o geçicek
        //sendMail adresini aldı sendMail(mail) olarak her defasında içerinde çağırıyor.
        //accept yerine sendMail çağrısı accept(p-mail) sendMail(mail) olarak runtimeda işlem görüyor.


        //Dolayısıyla lamba ifadesinin çözünürlük operatörü ile kullanımında
        //işin sadece businesini kodlaman yeterli
        //üstelik sınıfa eklentide yapabilirsin.
        //Sınıfı düşünmen gerekmez ortada local bir sınıf yok ki
        //işin busines kısmını tabiki düşünüp lambda ifadelerine eklenti yap.
        //metotları değiştirme zaten built-in support kullandın.
        //interface isteğe bağlı zaten adamlar yazmış java.util.function.<FunctionalInterface - Pred, Func, Consumer, Supplier>


    }//main

    private static void sendEmail(String mail)
    {
        //<send email code here>
    }

    //Generic istediğin liste gelebilir (Person tutan liste, Car tutan liste, Node tutan liste.)
    //İstediğin şekilde test edebilir.
    //İstediğin datayı alabilir.
    //İstediğini yapabilirsin -> mail gönder gibi.
    //Sınıfa veri elemanı eklersen ne yapıcağını lambda ifadelerini değiştir gerisine karışma.
    //Sınıfa veri elemanı eklendiğinde metotları kopyalamak yerine lambda yazıcaz, test kısmında.
    //Bunu biz yazdık zaten functional support apisi var java.util.stream paketinde.
    public static <X, Y> void processElementsHow(Iterable<X> source,
                                          Predicate<X> tester,
                                          Function<X, Y> mapper,
                                             Consumer<Y> block)
    {
        for (X x : source) {
            if (tester.test(x)) {
                Y y = mapper.apply(x); //data
                block.accept(y);
            }
        }
    }

    //Mapper arayüzü ile data çekmek onu concumer ile consume etmek.
    public static void processPersonsIf(List<Person> persons,
                                                  Predicate<Person> tester,
                                                  Function<Person, String> mapper,
                                                  Consumer<String> block)
    {
        for (Person p : persons) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
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

    //Lambda üretilebilen birşey aslında aklında bulunsun.
    public static Predicate<Person> generateLambdaPredGibi()
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

    //Bu iş böyle olmayacak diyip interface ICheckPerson yazmak, sonra built-in function geçmek.
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

    //İnit data
    public static void initData(Iterable<Person> persons)
    {
        List<Person> persons_ = (List<Person>) persons;

        persons_.add(new Person("tugberk", LocalDate.of(1994, 5, 23), Person.Sex.MALE, "m@gmail.com", Person.MaritalStatus.BEKAR, "ankara"));
        persons_.add(new Person("sumeyye", LocalDate.of(1994, 3, 14), Person.Sex.FEMALE, "s@gmail.com", Person.MaritalStatus.BEKAR, "trabzon"));
        persons_.add(new Person("bugra", LocalDate.of(1998, 2, 21), Person.Sex.MALE, "b@gmail.com", Person.MaritalStatus.BEKAR, "istanbul"));
        persons_.add(new Person("ertan", LocalDate.of(1968, 8, 1), Person.Sex.MALE, "er@gmail.com", Person.MaritalStatus.EVLI, "ankara"));
        persons_.add(new Person("esma", LocalDate.of(1971, 6, 1), Person.Sex.FEMALE, "es@gmail.com", Person.MaritalStatus.EVLI, "ankara"));
    }
}

