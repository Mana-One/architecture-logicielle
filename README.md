# Architecture Logicielle - ESGI 4e année
### Paolo MANAOIS - 4AL3 - Promotion 2021-2022

Modules :  
* common  
* membership  
* subscriptions 
* workflows

Patterns :
* Event-sourcing 
* CQS  

Tests : 
* unitaires, au niveau du domain (entités User et Subscription)

## Module Common  
Contient des classes communes à plusieurs modules, notamment une classe pour gérer le temps et une classe pour construire
des UniqueIds.  
Les évènements de l'application sont également définis dans ce module.

## Module Membership
La classe principale ici est User. Lorsqu'un user est créé, un évènement du domaine UserInitialized est enregistré dans un EventStore. 
Au niveau de l'application, un évènement UserRegistered est dispatché, afin qu'un listener puisse gérer les frais d'inscriptions. 
Le nouveau user ne sera valide qu'une fois ces frais réglés. Ainsi, un listener pour un autre évènement de l'application 
ajoutera dans l'EventStore l'évènenement du domaine UserVerified. 
Pour interagir avec la route API voir [ROUTES.md](./ROUTES.md)

## Module Subscriptions
Ce module gère la partie paiement de l'application. Après la création d'un User, un listener pour l'évènement UserRegistered 
créé une Subscription correspondante et tentera de régler les frais d'inscription. En cas de réussite, un évènement SubscriptionCreated 
sera dispatché afin de vérifier un User a posteriori. Ce module gère également le règlement des abonnements en coordination 
avec le module workflows. Un listener dédié est donc chargé de récupérer tous les abonnements dont le paiement est dû pour 
le jour courant, et de tenter de régler les paiements. Pour chaque abonnement dont le paiement est un succès, les dates de début et de fin 
de période de paiement sont mises à jour.

## Module Workflows
Pour l'instant ce module ne contient qu'un Schedule Spring qui émet un évènement PaymentsInitiated de manière récurrente. 
Pour ce TP, le scheduler est déclenché toutes les minutes.