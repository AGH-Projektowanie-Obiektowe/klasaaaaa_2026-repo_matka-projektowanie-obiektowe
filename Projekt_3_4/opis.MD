# Projekt – Laboratoria 3 i 4: System Smart Home Hub

### Wstęp
Siema, przed wami projekt, który został skrojony specjalnie w taki sposób, aby sprawdzić waszą umiejętność praktycznego wykorzystania wzorców projektowych w kodzie. Ogólnie trochę się w nim dzieje, więc jest potrzeba wstępu

Opis projektu jest naprawdę długi, ale nie ma się co bać. Całość jest napisana językiem którym posługują się osoby nietechniczne, bo przeważnie takie wyamgania się w pracy dostaje więc jest długo, ale samego mięsa (konkretnych wymagań) nie jest AŻ TAK dużo.

### Info typu przydatne
* **Porada:** Zanim zaczniecie pisać kod, polecam rozpisać sobie listę wymagań systemu od punktów na podstawie poniższego opisu.
* Główny moduł/klasę systemu można poniekąd traktować jak interfejs użytkownika (UI). Oznacza to, że punkt wejściowy do systemu docelowo może być duży i posiadać wiele metod obsługujących zdarzenia.
* Każde wymaganie (albo blok powiązanych wymagań) wskazujące na dany wzorzec są oddzielone nową linią

### Oczekiwane rezultaty (Zwrotka)
W ramach oddania projektu, aby mi się to przyjemnie i wygodnie oceniało, oczekiwane som:
1. **diagram UML** waszego rozwiązania.
2. Krótki opis tego, **jakie wzorce projektowe i w jaki sposób** zostały przez was użyte - nazwa wzorca i jakie klasy go realizują.
3. **Kod** rozwiązania (bez zaskoczenia)

### Lista wzorców projektowych do zidentyfikowania
Wymagania systemu zostały tak ułożone, aby wprost wskazywać na użycie poniższych wzorców projektowych:
* Factory / Factory Method
* Builder
* Strategia
* Kompozyt
* Dekorator
* Komenda
* Fasada

Dodatkowo dla ambitnych jest jeszcze wymaganie, które można ograć **Wizytorem**. Ale z racji że głosy w głowie mi mówią że projekt może zająć troooszkę więcej niż zakładałem, to jest ono jako dodatkowe + to bez wizytora też się da ograć (tak mi się wydaje przynajmniej).
***

## Wymagania Biznesowe Systemu

Tworzymy nową aplikację **Smart Home Hub** do zarządzania inteligentnym domem. Z biznesowego punktu widzenia nasz system musi potrafić bezproblemowo wprowadzać do domowej sieci **nowe sprzęty** z naszego katalogu. Obecnie sprzedajemy i obsługujemy trzy konkretne modele: **Żarówka LED SmartGlow**, **Termostat ThermoPro** oraz **Głośnik Multiroom SoundMax**. Przy dodawaniu urządzenia, trzeba podać jego **nazwę**, **typ urządzenia** (czy to żarówka, termostat czy głośnik) oraz **pomieszczenie**, do którego zostaje dodany. Każde z nich można **włączać** i **wyłączać**. Dodatkowo, na termostacie można **zmieniać temperaturę** (w danym pomieszczeniu może znajdować się tylko jeden termostat). Z kolei na głośniku można ustawiać **poziom głośności** – ale jeżeli zostaje on dodany do sypialni, **maksymalny poziom głośności** który można ustawić to 30.

Nasi klienci mają **duże domy** i nienawidzą klikać każdego urządzenia z osobna. Dla uproszczenia przyjmijmy, że każdy dom ma dokładnie taką samą budowę: **Parter**, wewnątrz którego znajdują się dwa pomieszczenia: **Salon** i **Kuchnia** i **Korytarz**, oraz **Piętro**, gdzie znajdują się **Sypialnia** i **Pokój biurowy**. To w nich fizycznie znajdują się sprzęty. Wymagamy, aby z perspektywy interfejsu aplikacji kliknięcie przycisku **"Wyłącz"** i **Włącz** na ikonie całego **Parteru** zadziałało na każdym sprzęcie w każdym pokoju – system ma sam płynnie **przekazać to polecenie w dół**, do każdego najdrobniejszego sprzętu w tej strefie. Polecenie wyłącz można wywołać na całym pokoju, piętrze oraz na całym **Domie**.

(dla ambitnych) Każde pojedyńcze polecenie (np. Ustaw głośność na głośniku na 70) na każdym typie sprzętów można odpalić na poziomie pokoju/piętra/domu.

Użytkownicy często popełniają błędy, na przykład przypadkowo wyłączając światło w całym domu. Dlatego każda wykonana akcja w systemie musi mieć **opcję cofnięcia**. Na chwilę obecną zakładamy, że można **cofnąć tylko ostatnio wykonaną akcję**.

Klienci uwielbiają automatyzację i chcą tworzyć **Scenariusze** dla swoich domów. Z biznesowego punktu widzenia, każdy scenariusz musi bezwzględnie posiadać swoją **nazwę** oraz **listę akcji do wykonania**. Zauważyliśmy jednak, że użytkownicy potrzebują ogromnej elastyczności, dlatego podczas tworzenia scenariusza mogą (ale nie muszą) skonfigurować opcjonalne parametry: **adres e-mail**, na który system wyśle raport po wykonaniu scenariusza (należy sprawdzić, czy jest on w bazie zapisanych maili w systemie) oraz **opcjonalne informacje na temat okresowego automatycznego powtarzania scenariusza**: **godzina uruchomienia** i **lista dni** w które ma być uruchamiany (okresowego uruchamiania nie trzeba implementowac). Oprócz możliwości ręcznego tworzenia dowolnych kombinacji przez użytkownika, aplikacja oferuje dwa predefiniowane scenariusze:

 - **"Tryb Party"** - Odpala wszystko na paterze i daje 100% głośności na głośnik w salonie (o ile w salonie jest głośnik). (w wersji dla ambitnych: ustawienia głośności na 100%, wyłącza wszystkie światła, oprócz tych w salonie i ustawia termostat na 20 stopni)
 - **"Wieczorny Audyt"** - scenariusz ustawiony na godzinę 23:00, z włączonym codziennym powtarzaniem, z listą akcji: Wyłącz Głośniki wszędzie, Wyłącz oświetlenie na Parterze oraz ustawienie termostatu w sypialni na 17 stopni (o ile jest w sypialni termostat).

Nasz Termostat **ThermoPro** domyślnie pozwala na klasyczne, **ręczne ustawienie temperatury** – użytkownik wpisuje zadaną wartość, a sprzęt ją utrzymuje. Klienci chcą jednak móc oddać kontrolę inteligentnemu systemowi. Wymagamy, aby użytkownik mógł w dowolnym momencie przypisać do termostatu jeden z wymiennych, **automatycznych algorytmów sterowania**. Do wyboru mamy: algorytm **Oszczędny (Eco)** (termostat zawsze ustawia 17 stopni), **Dobowy (Day/Night)** (w dzień, w godzinach 08:00-22:00, ustawia 22 stopnie, a w nocy obniża do 19 stopni) oraz **Pogodowy (Weather-based)** (jeśli na zewnątrz jest poniżej 10 stopni, ustawia 23 stopnie, inaczej 20). Kiedy wywołana zostanie akcja **"Dostosuj automatycznie"**, sprzęt ma pobrać temperaturę z wybranego algorytmu. Użytkownik w dowolnej chwili może w locie **podmienić algorytm na inny** lub **ręcznie ustawić temperature** (wtedy logika automatyczna przestaje działać).

Każde urządzenie możemy rozszerzyć o dodatkowe **funkcjonalności**, które nadzorują ich pracę, ale nie zmieniają samego działania rdzennego. Możliwe rozszerzenia to: **Szczegółowy Audyt** (każdorazowe włączenie/wyłączenie sprzętu skutkuje dodatkowym zapisaniem do logów konsoli dokładnej daty, godziny oraz nazwy sprzętu) oraz **Spanie** (sprzęt ignoruje wszelkie żądania po godzinie 22:00). Możemy nałożyć na jedno urządzenie oba te rozszerzenia na raz, a także w dowolnej chwili je **wyłączać**.

Ostatnim kluczowym wymaganiem biznesowym jest integracja. Klienci posiadają w domach **stare systemy alarmowe firmy LegacySecureNet** i musimy je wspierać. W interfejsie Smart Home Hub użytkownik ma widzieć tylko dwa proste przyciski: **Uzbrój Alarm** i **Sprawdź Czujnik Dymu**. Biblioteka zostawiona przez twórców tego starego systemu posiada API operujące na bardzo bazowym poziomie. Z punktu widzenia naszego kodu, do poprawnej komunikacji należy wykonać następujący 5-etapowy proces:

1. **Etap 1 (Inicjalizacja):** Należy utworzyć obiekt klasy `ConnectionNode`, przekazując mu adres IP "192.168.1.100" oraz port 9090, a następnie wywołać na nim metodę `handshake()`.
2. **Etap 2 (Autoryzacja):** Należy użyć statycznej metody `hash(String username, String pin)` z klasy `CryptoHelper` z danymi "admin" i "1234". Następnie utworzyć `AuthTokenGenerator`, przekazać mu uzyskany hash wywołując `generateToken(String hashedCredentials)` i odebrać obiekt `SessionToken`.
3. **Etap 3 (Konfiguracja kolejki):** Utworzyć obiekt `EventBusController` przekazując `ConnectionNode`, a następnie zarejestrować sesję metodą `registerSession(SessionToken)` i wywołać `subscribeToChannel(int channelId)` dla kanału numer 4.
4. **Etap 4 (Akcja):** Użyć klasy `PacketBuilder`. Przy uzbrajaniu alarmu, metodą `appendByte(byte b)` wstawić bajty 0x01 i (byte) 0xFF. Przy czujniku dymu – bajty 0x02 oraz 0x00. Następnie wywołać `getCompiledPacket()`, wsadzić tablicę bajtów w obiekt `LegacyCommand` i wysłać wywołując `dispatch(LegacyCommand command)` na `EventBusController`.
5. **Etap 5 (Sprzątanie):** Bezwzględnie wywołać `closeConnection()` na obiekcie `ConnectionNode` oraz `invalidateToken()` na obiekcie `SessionToken`, używając najlepiej bloku finally, aby nie uszkodzić systemu.