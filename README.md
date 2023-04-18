# API RESTful pour les statistiques des joueurs de tennis
Cette API RESTful est construite avec Spring Boot et permet de gérer les statistiques des joueurs de tennis. Elle expose plusieurs endpoints pour effectuer quelques opérations de lecture et statistiques sur les données des joueurs.

## Comment lancer l'application
Suivez les étapes ci-dessous pour lancer l'application localement sur votre machine :

### Prérequis :

- Java JDK (version 8 ou supérieure) installé sur votre machine
- Maven 3 installé sur votre machine ou se baser sur le maven de votre editeur de code

### Étapes :

1. Cloner le repository depuis GitHub : git clone https://github.com/amalmoum/atelier-restapp.git
2. Accéder au répertoire du projet : cd atelier-restapp
4. Compiler le projet : mvn clean install
5. Lancer l'application : mvn spring-boot:run
6. L'application sera maintenant accessible à l'URL : http://localhost:8080

## Endpoints de l'API
L'API expose les endpoints suivants pour gérer les statistiques des joueurs de tennis :

### GET /joueurs
Ce endpoint permet de récupérer la liste de tous les joueurs de tennis.

#### Exemple de requête :

    GET http://localhost:8080/joueurs

#### Réponse attendue :
    
    "statut":"OK",
	"response": [
        {
            "id": 17,
            "firstname": "Rafael",
            "lastname": "Nadal",
            "shortname": "R.NAD",
            "sex": "M",
            "country": {
                "code": "ESP",
                "picture": "https://data.latelier.co/training/tennis_stats/resources/Espagne.png"
            },
            "picture": "https://data.latelier.co/training/tennis_stats/resources/Nadal.png",
            "data": {
                "rank": 1,
                "points": 1982,
                "weight": 85000,
                "height": 185,
                "age": 33,
                "last": [1, 0, 0, 0, 1 ]
            }
        },
        {
            "id": 52,
            "firstname": "Novak",
            "lastname": "Djokovic",
            "shortname": "N.DJO",
            "sex": "M",
            "country": {
                "code": "SRB",
                "picture": "https://data.latelier.co/training/tennis_stats/resources/Serbie.png"
            },
            "picture": "https://data.latelier.co/training/tennis_stats/resources/Djokovic.png",
            "data": {
                "rank": 2,
                "points": 2542,
                "weight": 80000,
                "height": 188,
                "age": 31,
                "last": [1, 1, 1, 1, 1 ]
            }
        },
        ....
    ]
    
### GET /joueurs/{id}
Ce endpoint permet de récupérer les détails d'un joueur de tennis spécifique en utilisant son ID.

#### Exemple de requête :

    GET http://localhost:8080/joueurs/52

#### Réponse attendue :
    
    "statut":"OK",
	"response": {
        "id": 52,
        "firstname": "Novak",
        "lastname": "Djokovic",
        "shortname": "N.DJO",
        "sex": "M",
        "country": {
            "code": "SRB",
            "picture": "https://data.latelier.co/training/tennis_stats/resources/Serbie.png"
        },
        "picture": "https://data.latelier.co/training/tennis_stats/resources/Djokovic.png",
        "data": {
            "rank": 2,
            "points": 2542,
            "weight": 80000,
            "height": 188,
            "age": 31,
            "last": [1, 1, 1, 1, 1 ]
        }
    }

### GET /joueurs/statistics
Ce endpoint permet de récupérer les statistics concernant les joueurs, comme le pays qui a le plus grand ratio de parties gagnées, l'IMC moyen de tous les joueurs et la médiane de la taille des joueurs

#### Exemple de requête :

    GET http://localhost:8080/joueurs/statistics

#### Réponse attendue :
    
    "statut":"OK",
	"response": {
		"countryWithHighestWinRatio": "SRB",
		"averageIMC": 23357.838995505834,
		"medianHeight": 185.0
	}