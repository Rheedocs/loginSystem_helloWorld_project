# LoginSystem
Et simpelt login-system lavet af gruppen **Hello World**.

DAT-25

## Funktioner
- Login med brugernavn og adgangskode  
- Maks. 3 forsøg på at logge ind  
- Kontoen låses efter 3 forkerte forsøg  
- Mulighed for at oprette nye brugere  
- Alle loginforsøg gemmes i en log med tid og resultat  

## Standardbrugere
Programmet starter med tre brugere (user / password):
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
### Bemærkninger
- Brugernavne og passwords ligger i klartekst i koden, det er kun til øvelse.  
- Basisopgaven krævede brug af arrays (`String[]`).  
- Vi valgte at udvide med **ArrayList** for at kunne implementere bonusdelen (oprette nye brugere), da arrays ikke kan vokse.  
- Programmet er kun til at øve Java og datastrukturer, ikke til at beskytte ens WoW eller Steam account 😅  
