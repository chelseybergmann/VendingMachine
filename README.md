# VendingMachine
 
<h2>Overview</h2>
This Java program simulates a vending machine using the MVC architecture and Spring dependency injection.

<h2>Features</h2>
<ul>
  <li>Upon startup, the program displays all of the items and their respective prices, along with an option to exit the program.</li>
  <li>The user must put in some amount of money before an item can be selected.</li>
  <li>If the user selects an item that costs more than the amount the user put into the vending machine, the program will display a message indicating insufficient funds.</li>
 <li>If the user selects an item that costs equal to or less than the amount of money that the user put in the vending machine, the program will display their change.</li>
  <li>Inventory for the vending machine is read from a file when the program starts and is updated/written out to this file just before the program exits.</li>
  <li> If the machine runs out of an item, it will no longer be available as an option to the user.</li>
 <ul>

