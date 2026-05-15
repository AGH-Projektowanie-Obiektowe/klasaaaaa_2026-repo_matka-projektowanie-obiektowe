# Laboratorium: Testowanie

## Cel ćwiczenia
Głównym problemem obecnego kodu jest to, że klasa `User` łamie zasadę Single Responsibility. Posiada ona logikę domenową, aplikacyjną i infrastrukturalną (sama zarządza swoim stanem w bazie danych). Dodatkowo statyczne zależności z bazą i szyną wiadomości uniemożliwiają sensowne wstrzyknięcie fałszywych implementacji (mocków). Naszym celem jest refaktoryzacja tego kodu i napisanie skutecznych testów.

## Etap 1: Refaktoryzacja (Krok po kroku)

1. **Przygotowanie pod testy (Interfejsy):** Za `Database` oraz `MessageBus` trzeba wrzucić interfejsy. Inaczej nie ma opcji, żeby to zmockować w testach. Dodatkowo nie mogą to być klasy statyczne (znaczy podobno mogą, ale to zła jest), bo inaczej nie mamy jak wstrzyknąć fałszywych implementacji
2. **Wydzielenie orkiestratora (Logika Aplikacyjna):** Wprowadź klasę `UserController`, która oddzieli logikę aplikacyjną od domenowej. To ten kontroler będzie pobierał dane, wywoływał logikę na czystym modelu i zapisywał efekty uboczne w zewnętrznych systemach.
3. **Wydzielenie logiki firmy (Logika Biznesowa):** Utwórz klasę `Company`, odpowiedzialną za logikę związaną z firmą. Przenieś do niej logikę która powinna być ogarniania przez klasę reprezentującą firmę.
4. **Izolacja tworzenia obiektów (Fabryki):** Ponieważ nie używamy tu prawdziwego ORM-a, logikę tworzenia obiektów `User` i `Company` z surowych tablic danych wyciągnij do osobnych klas `UserFactory` oraz `CompanyFactory`.

## Etap 2: Pisanie Testów

Testujemy obserwowalne zachowania (observable behaviour). Piramida testów mówi jasno: to, co możesz testować tanio, testuj tanio.

**Jedna ważna uwaga** Z racji że nie dysponujemy prawdziwą stojącą bazą danych, tylko taką żyjącą w pamięci RAM, należy w testach wywołać metodę database.initDatabase() żeby baza wgl istniała. 
### Testy Jednostkowe (Unit Tests)
Obejmują one model domenowy i algorytmy, bo dają dużo wartości przy niskim koszcie. Konstruktorów nie testujemy, bo nie ma tam krzty logiki.

* **Klasa `Company`:**
    * Przetestuj metodę `isEmailCorporate()`. Wykorzystaj asercję na zwrotce (Output-based testing), sprawdzając, czy wynik to prawda, czy fałsz.
    * Przetestuj metodę `changeNumberOfEmployees()`.
* **Klasa `User`:**
    * Przetestuj metodę `changeEmail()`. Z racji braku zwrotki z metody, wykorzystaj asercję na stanie (State-based testing).
    * Pamiętaj o pokryciu każdego możliwego scenariusza (klasy równoważności itp).

### Testy Integracyjne (Integration Tests)
Testują one cały przepływ, czyli nasz `UserController`. Taki test powinien pokrywać jak najwięcej kodu.

* **Faza Arrange (Przygotowanie):** Skonfiguruj prawdziwą bazę danych (Managed Dependency) i wrzuć do niej dane początkowe, bo stamtąd serwis będzie je pobierał. Dla szyny wiadomości (Unmanaged Dependency) należy stworzyć atrapy, ponieważ system komunikuje się z zewnętrznymi serwisami. **Wykonaj to na dwa sposoby:**
  1. **Za pomocą biblioteki Mockito** (używając np. `mock(MessageBus.class)`).
  2. **Poprzez własną implementację interfejsu (Fake):** Utwórz testową klasę rozszerzającą interfejs szyny, która zamiast wysyłać wiadomości dalej, będzie po prostu dodawać je do wewnątrzklasowej listy, którą sprawdzisz w asercji.
* **Faza Act (Działanie):** Wywołaj metodę kontrolera w jednej linijce.
* **Faza Assert (Weryfikacja):** Wykonaj dwa rodzaje asercji. Najpierw pobierz zaktualizowane dane z bazy i sprawdź ich stan (State-based testing). Następnie sprawdź na mocku, czy wykonano wywołanie metody wysyłającej zdarzenie (Communication-based testing). Lub w przypadku mockowania własną implementacją asercje na stanie (czy w jakieś liście wysłanych wiadomości znajduje się nasza)