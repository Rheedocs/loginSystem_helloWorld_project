# LoginSystem
Programmet er et simpelt login-system.

Lavet af gruppen, Hello World.

## Funktioner
- Brugeren kan logge ind med brugernavn og adgangskode
- Brugeren har maks. 3 forsøg
- Kontoen bliver låst efter 3 forkerte forsøg
- Det er muligt at oprette nye brugere
- Alle loginforsøg gemmes i en log med tidspunkt og resultat

## Standardbrugere
Programmet starter med tre brugere:
- **Alice** / pass1  
- **Bob** / pass2  
- **Charlie** / pass3  

## Eksempel på login
```
--- VELKOMMEN ---
24. September 2025 | Kl. 15:37:20

--- LOGIN SYSTEM ---

1. Login
2. Registrér ny bruger
3. Vis login-log
4. Afslut
Vælg (1-4): 1
Indtast brugernavn: Alice
Indtast adgangskode: pass1

✅ Velkommen Alice!
Login d. 24. September 2025 | Kl. 15:37:27
```
## Bemærkninger
- Brugernavne og adgangskoder gemmes i **klartekst** i ArrayLists.  
- Programmet er kun en **øvelsesopgave** og ikke beregnet til rigtig sikkerhed.
