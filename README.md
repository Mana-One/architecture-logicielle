# Architecture Logicielle - ESGI 4e année
### Paolo MANAOIS - 4AL3 - Promotion 2021-2022

Modules :  
* common  
* event  
* registration  
* user  

## Module Common  
Contient des classes communes à plusieurs esgi.pmanaois.cc.modules, notamment une classe pour gérer le temps et une classe pour construire
des UniqueIds.  
Les évènements de l'application sont également définis dans ce module.  
La class Role est assez particuliére car seules certaines valeurs sont autorisées, ainsi, le constructeur est privé et 
la classe expose des instances valides en tant qu'attributs statiques. Une static factory permet également de récupérer 
un Role selon un nom, un nom invalide lève une exception. Cette structure permet d'éviter d'avoir recours à des Enumérations.

## Module Event  
Contient les interfaces pour les évènements de l'application et les subscribers, ainsi que l'interface 
et une implémentation pour un bus d'évènement.  

## Module Registration
La class principale ici est CreditCard car il s'agit avant tout d'un module qui gère des paiements. 
Credit Card contient un Owner, lui-même possédant un Role (classe dans le module Common). Jusqu'à présent 
les seuls Roles valables sont "Tradesman" et "Contractor".  
Il y a deux moteurs de validation: un pour la carte de crédit et un pour l'email du Owner. La validation pour ces 
éléments ont été extraites des static factories / factories, car les mécanismes de vérification, en particulier pour 
la carte de crédit, sont relativement complexes (bien que pas implémentées dans le cadre de cet exercice).  
La logique concernant la validation du profil et l'initialisation du paiement est contenue dans la classe 
"PaymentService": elle utilise les invariants du domaine pour construire des objets valides, puis utilise un 
"PaymentGateway" pour procéder au paiement, cette interface pourra à l'avenir permettre l'intégration d'une API tierce 
de paiement.  
  
**Pourquoi des évènements ?**  
Le paiement ici n'est qu'initialisé: généralement la confirmation du paiement n'est pas instantannée, en particuier, les 
virements peuvent prendre plusieurs jours. On ne souhaite persister un utilisateur seulement si le paiement est valide, 
le bus d'évènement permet de gérer la nature asynchrone de cette opération. D'autres solutions sont toutefois envisageables, 
notamment l'API Stripe propose de mettre en place des webhooks pour réceptionner ses évènements.   

## Module User
Ce module permettra à terme la gestion complète des utilisateurs. Pour l'instant, le seul service présent 
permet d'enregistrer un Contractor ou un Tradesman suite à un paiement valide.  
Ici, le RegisterUserService utilise un repository Users pour intéragir avec la couche de persistance. Ce service est 
ensuite appelé dans un Subscriber pour RegistrationFeePaidEvent.
  
**Manque de validation ?**  
Il n'y a que peu de méchanismes de validation dans ce module car les données sont extraites d'un évènement de 
l'application, ainsi la construction d'un User depuis un CardOwnerDto (module Common) est peu susceptible de contenir 
des données erronées car validées en amont dans le module Registration.