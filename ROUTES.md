# API routes

## /membership 
```
RequestBody
{
    "firstName": <String>,
    "lastName": <String>,
    "email": <String>,
    "role": <"Contractor" | "Tradesman">,
    "paymentMethodId": <String>
}
```
|Route|Method|Query|Body|Description|Response|Error| 
|--|--|--|--|--|--|--| 
|/|POST||RequestBody|Route permettant d'enregistrer un nouvel utilisateur.|200 - OK|400 - Bad Request|