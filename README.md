# Drools Demo

This is small drools demo project. 

<b>Drools:</b>
Drools is a business rule management system. Let's suppose you want to separate business logic from the code. In case we want to avoid rebuild and redeploy our application but without taking any downtime or rebuild the entire application then in that case we can make use of some business rule management
system where we de-couple business logic from the code. For example, during the festive season, an e-commerce company might want to give different discounts to different customers lets suppose customer A is the premium customer than in the festive season we have to give 10% discount where as on other days we want to give 5% only.

1. We have to write business logic in a DRL file.
2. We can pass drl file to drools engine and can get list of results which are passed and which are failed.
3. Based on the rules passed/failed status we can take the appropriate decisions.
