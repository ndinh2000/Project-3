ReadMe

Team Members: Anqi Zhong, Nham N. Dinh, Yasemin Turkkan

General Overview:
-Home Page: Contains the last 5 products the user bought, and a product selection of our featured pets.
-Dogs Page: Contains all dog animals being sold.
-Cat Page: Contains all cat animals being sold.
-Contact Page: Brief description of our company, our team members. Contains out business information including email, phone, address, store hours. Plus, it includes extra citations used for our website.
-Product Page: An animal’s information is displayed here, and can be added to a user’s cart.
-Cart Page: A user can checkout with the products in their cart by filling out an order form.
-Order Confirmation Page: Displayed when a user submits an order form with correct information.
-Navigation: Pages can be navigated to by using the navigation bar. Clicking on any animal’s image will take you to their specific product page.
 
Requirements Satisfaction:

1. Our homepage itself is a servlet called “HomeServlet” which calls two other servlets to display its information. The “Last5” Servlet is called and displays the user’s last 5 purchases from the site. The “Products” servlet dynamically generates pet displays from our “petstore” database. The database is created statically beforehand, but dynamically filled as the user interacts with the page through making orders. Pets are rated beforehand, and users can rate pets by selecting the stars under the pets which they ordered recently.

2. Our “ProductDetail” servlet takes in a pet_id as a parameter, and uses this identification to print out the pet’s information from the database. From a product detail page, the user can add a pet to their cart. Unique sessions are created for each user based on their browser cookies. (For example, if you were on a computer and entered our website from a normal browser, and an incognito one, they would both have different session IDs).

3. When a user submits an order form, their information is added to our database of “Orders.” The form is validated as follows:
    The first and last name are validated not to be empty parameters.
    Pet ID validation is in the form D101, C101, etc.
    Quantity is validated to be 1 or greater
    Phone number is checked to be in the form: ###-###-####
    Email is validated  to be formatted correctly
    Credit card number is checked by regex to be a valid Visa number
    Expiration date is selected from a dropdown menu
    Shipping address must be a number followed by a street name
    zip code must be 5 numbers
    State must be a valid state in the US
    Shipping method is selected from a set of list options.

4. Our “Checkout” servlet holds all pets that the user has added to their cart in their session, and the total price for them. The user can choose to checkout from this page, and is forwarded to a “Confirmation Page” which repeats the products they bought as well as the quantities and total prices.

