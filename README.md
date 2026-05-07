# M-motors
🚗 M-Motors – Application de gestions et de ventes de véhicules
---
📌 Présentation du projet

M-Motors est une application web dédiée à la vente de véhicules d’occasion et à la location longue durée avec option d’achat (LLD/LOA).

Ce projet est réalisé dans le cadre du Bachelor Développeur d’Application Java – STUDI.
Il s’inscrit dans une stratégie de diversification des revenus de l’entreprise M-Motors et de modernisation de ses processus métiers, notamment par la dématérialisation complète des dossiers clients.

---
## 🎯 Enjeux & objectifs métier

- Introduire une nouvelle offre LLD avec option d’achat
- Maintenir l’activité historique de vente de véhicules
- Réduire les coûts opérationnels liés aux dossiers papier
- Améliorer l’expérience client grâce à un espace personnel
- Garantir la sécurité, la traçabilité et la continuité de service
- Concevoir une solution scalable et évolutive hébergée dans le cloud

---

## ⚙️ Fonctionnalités principales (MVP)

## 👤 Gestion des utilisateurs
- Création de compte client
- Authentification sécurisée
- Accès différencié selon le rôle (client / back-office)
- Accès à un espace personnel

## 🚘 Recherche & catalogue véhicules
- Recherche de véhicules à l’achat ou en location longue durée
- Consultation détaillée des fiches véhicules
- Distinction claire des types d’offres

## 📄 Gestion des dossiers clients
- Dépôt de dossier d’achat ou de location en ligne
- Téléversement des documents justificatifs
- Dossier 100 % dématérialisé

## 🧾 Suivi des dossiers
- Consultation de l’état d’avancement du dossier depuis l’espace client
- Réduction des sollicitations du service commercial

## 🏢 Back-office commercial
- Ajout et gestion des véhicules (vente / location)
- Consultation des dossiers clients
- Validation ou refus des dossiers

## 🔐 Sécurité & continuité
- Authentification via JWT (JSON Web Token)
- Protection des données clients
- Préparation aux exigences RPO / RTO

  ---

## 🧠 Méthodologie de gestion de projet

- Méthodologie Agile : Scrum
- Livraison incrémentale via MVP
- Découpage fonctionnel par EPICs
- Gestion du backlog produit
- Prise en compte des notions :
   - Definition of Ready (DoR)
   - Definition of Done (DoD)

---

 ## 🏗️ Architecture du projet
Architecture MVP réellement développée

L’application repose sur une architecture monolithique Spring Boot MVC.

Stack technique MVP :
Front-end : Thymeleaf / HTML / CSS
Back-end : Spring Boot MVC
Base de données : H2 Database
Stockage documents : stockage local
Déploiement : Docker + Render

Cette architecture a été choisie afin :

de simplifier le développement ;
de limiter les coûts ;
d’accélérer la mise en production du MVP ;
tout en préparant une évolution future vers une architecture cloud plus robuste.


## 🔮 Architecture cible envisagée

Une architecture cloud plus industrialisable a également été conçue afin d’anticiper :

- une montée en charge ;
- une meilleure persistance des données ;
- des sauvegardes automatisées ;
- du monitoring avancé ;
- une meilleure scalabilité.

 ## Évolutions futures possibles :
PostgreSQL managé
Stockage cloud (Google Cloud Storage)
Load Balancer
Monitoring & logging
Sauvegardes automatiques
Haute disponibilité

## 🛠️ Technologies utilisées

## Langages
- Java
- JavaScript
- HTML 5
- CSS
  
## Outils de développement :
- Vs Code
  
## Frameworks & bibliothèques

- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- Thymeleaf
  
## Base de données & stockage

H2 Database

## Outils & DevOps*

- Git / GitHub
- GitHub 
- Jira Software
- Microsoft Teams
- Docker
- Render
- JaCoCo
- Trello
  ---

  ## 🚀 Déploiement

Application déployée sur Render 

https://m-motors-1ugy.onrender.com 


## 🚀 Installation & démarrage
## Prérequis

- Java 17 ou +
- Maven
- Git

  ## 🔑 Comptes de démonstration
## Administrateur
Email : admin@mmotors.com
Mot de passe : admin123

## Client
Email : client@mmotors.com
Mot de passe : test123
    
## Cloner le projet 
git clone https://github.com/DHZLN/M-motors-back.git



## Lancer l’application
  mvn spring-boot:run

  ## 📌 Perspectives d’évolution

Le projet pourra évoluer vers :

- une architecture cloud complète ;
- une base PostgreSQL managée ;
- un stockage objet cloud ;
- des outils de supervision avancés ;
- des sauvegardes automatisées ;
- une architecture plus scalable ;
- des intégrations externes (signature électronique, reporting, automatisation).
## 👩‍💻 Réalisé dans le cadre du Bachelor Développeur d’Application Java – STUDI

Projet conçu et développé par Hélène Dhervillez.
  

