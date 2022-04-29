# Rent-a-car-project
*Rent a car project for Turkcell Camp Program*

You should run query in cities.txt to add cities.
Don't forget to edit [application.properties](https://github.com/Mr-OMD/Rent-a-car-project/blob/master/src/main/resources/application.properties) according to your own system.

There is 3 bank POS system in this program. To use them you should have appropriate CVV and credit card numbers.
Sum of last 3 digits of creditcard and CVV must equal to that bank's number.
Bank's numbers are: 
- Ziraat Bankasi = 999
- Is Bankasi = 979
- Ak Bank = 989

For Example: In order to use Ziraat Bankasi, your sum must be 999. If CVV is 123, last digis of card must be 876.

There is a few known errors and they will be fixed in future. Such as, Invoice creation errors, sometimes rental errors, kilometer errors.

This project developed in Turkcell Gelecegi Yazanlar Java Camp Program with Engin Demirog.
