-ReadMe

Team Members: Anqi Zhong, Nham N. Dinh, Yasemin Turkkan

General Overview:

-Home Page: Contains the last 5 products the user bought, and a product selection of our featured pets. This is a jsp, making use of a java bean (getID).

-Dogs Page: Contains all dog animals being sold. This is a jsp.

-Cat Page: Contains all cat animals being sold. This is a jsp.

-Contact Page: Brief description of our company, our team members. Contains out business information including email, phone, address, store hours. Plus, it includes extra citations used for our website. This is a jsp.

-Product Page: An animal’s information is displayed here, and can be added to a user’s cart. This page is a jsp.

-Cart Page: A user can checkout with the products in their cart by filling out an order form.

-Order Confirmation Page: Displayed when a user submits an order form with correct information.

-Navigation: Pages can be navigated to by using the navigation bar. Clicking on any animal’s image will take you to their specific product page.
 
 
Requirements Satisfaction:

1. We have made our websites home page a jsp called HomePage. Furthermore, the pages DogPage, CatPage, ContactPage, and ProductDetailPage have been made into jsps.

2. Ajax has been used on our checkout page. When filling out the order information, Ajax is implemented in three ways:
- First, when a U.S. zipcode is entered, the state shall automatically be filled in. For example, writing 92612 would make the state become "CA".
- Second, when the user is filling out their phone number, we require their formatting to be sepereated by dashes. Because this felt bulky for the user to go back and change if inputted incorrectly, we added Ajax to automatically change the format of the phone number. For example, if the user inputs 9491112222 as their phone number, the ajax will reformat it to 949-111-2222.
- Third, Ajax is implemented to calculate the tax rate and total cost after adding the tax. When the user filled in or click on the input box for state(which is filled already) then click on white spaces outside the input box, the total cost and tax rate will be displayed in the corresponding area.

3. Extra credit: We created REST server that provides multiple services:
- Getting rating of a pet:
    - Method Type: GET
    - Request URL: v1/api/ratings/{pet_id}
    - This service is invoked in GetRating.java from client side
    - Sample Request: a GET request to http://localhost:8088/PA3_Rest/v1/api/ratings/D101 with blank body
    - Sample Response: [
        {
            "user_id": 0,
            "pet_id": "D101",
            "rating": 4
        },
        {
            "user_id": 1,
            "pet_id": "D101",
            "rating": 5
        },
        {
            "user_id": 2,
            "pet_id": "D101",
            "rating": 5
        }
    ]
- Delete rating from a user to a pet:
    - Method Type: DELETE
    - Request URL: v1/api/ratings/
    - This service is invoked in HandleRating.java from client side
    - Sample Request: a DELETE request to http://localhost:8088/PA3_Rest/v1/api/ratings/ with content type of JSON, such as {"user_id": 0, "pet_id": "D101"}
    - Sample Response:
        - "Rating Deleted Successfully" if record exists in database.
        - "Failed to delete rating" if record does not exist in database.
- Add a rating for pet:
    - Method Type: POST
    - Request URL: v1/api/ratings/
    - This service is invoked in HandleRating.java from client side
    - Sample Request: a POST request to http://localhost:8088/PA3_Rest/v1/api/ratings/ with content type of JSON, such as {"user_id": 0, "pet_id": "D101"}
    - Sample Response:
        - "Rating Added Successfully" if record does not exists in database.
        - "Failed to add rating" if record exists in database.