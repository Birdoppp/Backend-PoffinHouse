# PoffinHouse - Backend

PoffinHouse is een REST API-backend, gebouwd met **Spring Boot** en **Java 21**, die een platform biedt voor het bijhouden van Pokémon gameplay. De applicatie maakt gebruik van **Spring Security** voor authenticatie en autorisatie en ondersteunt verschillende rollen, zoals ADMIN en TRAINER.

## Inhoud
- [Benodigde Software](#benodigde-software)
- [Installatie-instructies](#installatie-instructies)
- [Projectstructuur](#projectstructuur)
- [API-documentatie](#api-documentatie)
- [Bedankt](#bedankt)

## Benodigde Software

- **IDE** naar keuze. Wij gebruiken **IntelliJ IDEA**.
- **Java 21** (Zet de juiste versie in je IDE)
- **Spring Boot**
- **Maven**
- **PostgreSQL**
- **Postman** (voor API-testen)
- **PgAdmin** (om de Database te beheren)

De beveiliging van de applicatie is gebaseerd op rollen: ADMIN heeft volledige toegang tot alle endpoints, terwijl TRAINER beperkte toegang heeft. Authenticatie gebeurt via JWT.


### JWT Tokens

Om toegang te krijgen tot de beveiligde endpoints, moet je een geldig JWT-token meesturen in je verzoeken. De toegang tot verschillende endpoints wordt bepaald door de rol van de ingelogde gebruiker.

## Installatie-instructies

### 1. Broncode verkrijgen

Je kunt de broncode van dit project op twee manieren verkrijgen:

#### a) Via Git

1. Open de terminal in je IDE.
2. Initieer Git met het commando:
    ```bash
    git init
    ```
3. Clone de repository met het commando:
    ```bash
    git clone git@github.com:Birdoppp/Backend-PoffinHouse.git
    ```

#### b) Als ZIP-bestand

1. Ga naar [de repository](https://github.com/Birdoppp/Backend-PoffinHouse) en klik op de groene “**< > Code**”-knop.
2. Selecteer **Download ZIP**.
3. Pak het ZIP-bestand uit en open de uitgepakte map in je IDE.

### 2. Dependency Library

1. Zorg ervoor dat je **Maven** geïnstalleerd hebt. Voer het volgende commando uit in de terminal om alle benodigde dependencies te downloaden:
    ```bash
    mvn clean install
    ```

### 3. Database Configuratie

1.  Pas de PostgreSQL-instellingen aan naar je eigen configuratie:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/poffinhouse
    spring.datasource.username=JOUW-GEBRUIKERSNAAM
    spring.datasource.password=JOUW-WACHTWOORD
    ```

### 4. Applicatie Runnen

1. Start de applicatie door op de **Run**-knop in je IDE te klikken, of voer het volgende commando uit:
    ```bash
    mvn spring-boot:run
    ```
2. De applicatie zal nu draaien op [http://localhost:8080](http://localhost:8080).


## Projectstructuur

De belangrijkste onderdelen van het project zijn:

- **Controller**: Behandelt inkomende HTTP-verzoeken.
- **Service**: Bevat de bedrijfslogica.
- **Repository**: Interactie met de database.
- **DTO (Data Transfer Objects)**: Behandelt gegevensoverdracht tussen lagen.
- **Entity**: Vertegenwoordigt de database-entiteiten.
## API-documentatie

De documentatie voor de API komt samen met een **Postman-collectie**. Deze kun je importeren in Postman voor een rondleiding door de verschillende endpoints van de applicatie.

## Bedankt

Bedankt voor het gebruiken van **PoffinHouse**! We hopen dat je een fijne ervaring hebt. Heb je feedback of suggesties, dan horen we dat graag.
