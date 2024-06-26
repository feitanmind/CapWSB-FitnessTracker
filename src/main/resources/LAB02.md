# LABORATORIUM 02

## Kontynuacja LABORATORIUM 01 oraz stworzenie aspekt logującego wywołania metod serwisów

## ZADANIE 1. Sieciowe API do operacji typu CRUD na Training (bez użycia rekordów)

### Potrzeba biznesowa

Jako użytkownik, chce mieć możliwość dostępu do panelu z treningami:

- tworzenie nowych,
- wyświetlanie swoich treningów,
- aktualizacji trenigów

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [X] utworzenie nowego treningu
- [X] wyszukiwanie wszystkich treningów
- [X] wyszukiwanie treningów dla określonego Użytkownika:
- [X] wyszukiwanie wszystkich treningów zakończonych (po konkretnej zdefiniowanej dacie)
- [X] wyszukiwanie wszystkich treningów dla konkretnej aktywności (np. wszystkie treningi biegowe)
- [X] aktualizacja treningu (dowolnie wybrane pole np. dystans)

### Wymagania techniczne

- [X] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [X] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [X] rozwiązanie powinno spełniać zasady SOLID
- [X] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [X] OPCJONALNE rozwiązanie powinno implementować logikę potrzebną do spełnienia już
  istniejących [testów integracyjnych API]
  . NIE należy zmieniać logiki tych testów.
- [X] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [X] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [X] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [X] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

## ZADANIE 2 (opcjonalne). Sieciowe API do operacji typu CRUD na Statistics (bez użycia rekordów)

### Potrzeba biznesowa

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [X] wylistowanie podstawowych informacji o wszystkich statystykach zapisanych w systemie
- [X] utworzenie nowych statystyk
- [X] aktualizacja Statystyk Użytkownika implementacja funkcjonalności do aktualizacji istniejących statystyk dla
  użytkownika.
- [X] pobranie szczegółów dotyczących statystyk dla danego użytkownika
- [X] usunięcie statystyk
- [X] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [X] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany

### Wymagania techniczne

- [X] przygotowanie danych wejściowych (uzupełnienie skryptu ładującego dane przy starcie aplikacji)
- [X] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [X] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [X] rozwiązanie powinno spełniać zasady SOLID
- [X] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [X] OPCJONALNE rozwiązanie powinno implementować logikę potrzebną do spełnienia już
  istniejących [testów integracyjnych API]
  . NIE należy zmieniać logiki tych testów.
- [X] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [X] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [X] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [X] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

## ZADANIE 3. Programowanie aspektowe.

### Potrzeba biznesowa

Jako pracownik utrzymania, chcę mieć możliwość kontroli wykonania się kodu programu za pomocą logów aplikacji.

### Wymagania funkcjonalne

- [X] być uruchomiony podczas wywoływania metod publicznych serwisów (klas adnotowanych `@Service`)
- [X] przed wywołaniem metody logować o niej informację w
  formacie (`typ zwracany nazwaKlasy.nazwaMetody(typParametru1 nazwaParametru1, ...)`),
  np. `void MyService.myMethod(String param1, Boolean param2)`
- [X] po wywołaniu metody logować informację o metodzie (w tym samym formacie co przed wywołaniem) wraz z informacją na
  temat zwróconej wartości (wystarczy jej toString())

### Wymagania techniczne

- [X] aspekt powinien zostać zaimplementowany z użyciem biblioteki AspectJ
- [X] rozwiązanie powinno spełniać zasady SOLID
- [X] testy jednostkowe rozwiązania nie są wymagane
- [X] testy integracyjne rozwiązania nie są wymagane
- [X] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [X] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
