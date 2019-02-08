# NessieExample
Hackathon Example App. Just a heads up, this app has little-to-no styling.

## Initial (Splash Screen)
3 - Buttons

  A - Enterprise Endpoint
  
  B - Customer Endpoint
  
  C - ATM Finder
  
## A - Enterprise Endpoint
Displays a list of the first 10 results from the **GET** Customers Enterprise Endpoint.

***This page takes a bit of time to load. It is not the most efficient call.***
 
## B - Customer Endpoint
* Displays a list of all Customers associated with my Nessie API Profile using a **GET** from the Customers Endpoint.
* Each Customer in the list is ***clickable*** and will navigate to the **Account List Screen**.

***3rd Customer in the list has Bills pre-created for future screens in the flow***
* Floating Action Button (FAB) in bottom right corner will navigate to the **Customer Creation Screen**.

## C - ATM Finder
Displays a text entry field in the bottom left of the screen and a **SEARCH** button in the bottom right. The entry text is for the radius of the ATM Finder's search. It will always search the default longitude and latitude. The response is re-queried using the ***paging*** object if any ATMs were returned in the previous call. You can see the list grow if you enter a radius of 3 or more.

## Account List Screen
* Displays a list of all Accounts associated with the Customer that was previously clicked using a **GET** from the Accounts Endpoint.
* Each Account in the list is ***clickable*** and will navigate to the ***Account Details Screen***.
***There is only 1 account associated with this user, but you can create another.***
* FAB in bottom right corner will navigate to the **Account Creation Screen**.

## Account Details Screen
* Displays 3 tabs. You can navigate between the 3 tabs by ***swiping***, the tabs are currently not ***clickable***.

  1 - Bills
  
  2 - Purchases
  
  3 - Withdrawals
  
* FAB in bottom right corner will navigate to the **Withdrawal Creation Screen**.
  
### 1 - Bills
* Displays a list of all Bills associated with the Account that was previously clicked using a **GET** from the Bills Endpoint.

### 2 - Purchases
* Displays a list of all Purchases associated with the Account that was previously clicked using a **GET** from the Purchases Endpoint.

### 3 - Withdrawals
* Displays a list of all Withdrawals associated with the Account that was previously clicked using a **GET** from the Withdrawals Endpoint.
* **Delete All Withdrawals** Button at the bottom of the screen will delete all Withdrawals associated with my Nessie API Profile using a **DELETE** from the Data Endpoint.

### Customer Creation Screen
Displays a form to create a new Customer to be associate to my Nessie API Profile using a **POST** to the Customers Endpoint.

### Account Creation Screen
Displays a form to create a new Account to be associate to the chosen Customer using a **POST** to the Accounts Endpoint.

### Withdrawal Creation Screen
Displays a form to create a new Account to be associate to the chosen Account using a **POST** to the Withdrawals Endpoint.
