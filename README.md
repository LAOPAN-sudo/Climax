# Agitex-test
Application spring boot qui permet de lire des fichiers partenaire et calcul la moyenne par profession

# configuration

-Installer java version SE jdk-11

-IDE(sts-4.17.2)

-Spring boot version 2.7.9

-Maven

-Wampserver(3.2.3) ou Xampp

Base de donnee: Mysql(5.7.31)

-Acces par defaut a la base de donnee

-Port utilise:8087

# Deploiement
-Cloner le depot sur sa machine
-Demarrer wampserver ou xampp et lancer les services
-Ensuite:
        //sur window
          -Ouvrir le CMD et se rendre a la racine du projet

          -Taper>: mvnw clean packet

          -Ensuite taper>: java -jar target/statistiques-0.0.1-SNAPSHOT.jar

        //sur linux
          -Ouvrir le terminal et se rendre a la racine du projet

          -Taper>: mvn clean packet

          -Ensuite taper>: java -jar target/statistiques-0.0.1-SNAPSHOT.jar

-Lancer le navigateur et entrer l'url suivant: http://localhost:8087/clients/showCreate