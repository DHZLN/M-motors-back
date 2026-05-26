# 🚗 M-Motors – Application de gestion et vente de véhicules

---

## 📌 Présentation du projet

M-Motors est une application web dédiée à la vente de véhicules d’occasion ainsi qu’à la location longue durée avec option d’achat (LLD/LOA).

Ce projet a été réalisé dans le cadre du **Bachelor Concepteur Développeur d’Application – spécialité Java (STUDI)**.

L’objectif principal du projet est de moderniser le parcours client de l’entreprise M-Motors à travers une solution digitale permettant la gestion complète du processus d’achat ou de location, de manière entièrement dématérialisée.

---

## 🎯 Enjeux & objectifs métier

* Introduire une offre de location longue durée avec option d’achat (LLD/LOA)
* Maintenir l’activité historique de vente de véhicules
* Dématérialiser les dossiers clients
* Améliorer l’expérience utilisateur via un espace client dédié
* Permettre un suivi simplifié des dossiers
* Faciliter la gestion commerciale via un back-office administrateur

---

## ⚙️ Fonctionnalités principales (MVP)

### 👤 Gestion des utilisateurs

* Création de compte client
* Authentification sécurisée
* Gestion des rôles (Administrateur / Client)

### 🚘 Catalogue véhicules

* Consultation des véhicules disponibles
* Recherche de véhicules à l’achat ou en location
* Visualisation détaillée des fiches véhicules

### 📄 Gestion des dossiers

* Dépôt de dossier d’achat ou de location
* Téléversement des documents justificatifs
* Gestion 100 % dématérialisée

### 🧾 Suivi client

* Consultation de l’état d’avancement du dossier
* Accès aux informations depuis l’espace personnel

### 🏢 Back-office administrateur

* Ajout, modification et suppression de véhicules
* Gestion des offres achat/location
* Consultation des dossiers clients
* Validation ou refus des demandes

### 🔐 Sécurité

* Authentification sécurisée via Spring Security
* Gestion des rôles utilisateurs
* Chiffrement des mots de passe avec BCrypt
* Gestion sécurisée des variables sensibles

---

## 🧠 Méthodologie de gestion de projet

Le projet a été développé selon une approche inspirée des principes **Agile / Scrum** avec une logique de développement incrémental orientée MVP (*Minimum Viable Product*).

La démarche de développement reposait sur :

* Analyse des besoins métier
* Découpage fonctionnel en User Stories
* Développement progressif des fonctionnalités
* Validation continue des fonctionnalités développées
* Gestion du versionning via Git/GitHub

---

## 🏗️ Architecture générale

L’application repose sur une architecture **Spring Boot MVC monolithique** :

* **Front-End :** Thymeleaf, HTML, CSS
* **Back-End :** Java 17, Spring Boot
* **Sécurité :** Spring Security, JWT, BCrypt
* **Base de données :** H2 Database
* **Déploiement :** Docker & Render
* **Versionning :** Git / GitHub

Architecture conçue pour :

* Séparation des responsabilités (MVC)
* Maintenabilité du code
* Évolutivité fonctionnelle
* Déploiement simplifié dans le cloud

---

## 🛠️ Technologies utilisées

### Langages

* Java 17
* HTML
* CSS

### Frameworks & bibliothèques

* Spring Boot
* Spring Security
* Spring Data JPA
* Thymeleaf
* JWT
* BCrypt

### Base de données

* H2 Database

### Outils de développement

* Visual Studio Code
* Maven
* Git / GitHub
* Docker
* Render

---

## 🚀 Installation & démarrage

### Prérequis

* Java 17+
* Maven
* Git

### Installation

1. Cloner le dépôt GitHub

2. Configurer les variables d’environnement nécessaires

3. Lancer l’application :

mvn spring-boot:run

4. Accéder à l’application :

[http://localhost:8081](https://m-motors-1ugy.onrender.com/)

### Console H2 (développement)

http://localhost:8081/h2-console
