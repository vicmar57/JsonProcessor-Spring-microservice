# The product-processor: 
The Product-processor is a micro-service that accepts a product in a raw form and processes it into a standard form.

A product is just a JSON object with an id and a list of attributes. 
Each attribute is composed of a name and a list of values (strings)

For example:
```
{
  "id": 1000,
  "attributes": [
    {
      "name": "title",
      "values": [
        "Red and blue T-shirt with a chicken print. XL"
      ]
    },
    {
      "name": "color",
      "values": [
        "red",
        "blue"
      ]
    },
    {
      "name": "size",
      "values": [
        "xl"
      ]
    }
  ]
}
```

The product-processor makes the following changes to the product input:
* Normalize all attribute values except title
* Remove illegal values
* Sort the attributes by the attribute name
* Persist the processed products and fetch processed products

## Initial state
We start with the following :
* Spring-boot application (MAVEN based) with Guava dependency and an embedded MongoDB 
* Rest controller (src/main/java/com/vicmar57/productprocessor/controller/ProductController.java)
* API definitions (part of the rest controller)
* Integration-tests that check the service

## Tasks
### Task 1 - Normalize all attribute values except title
We want to normalize the values of each attribute to a canonical form.
* The chosen canonical form is Capitalization.
** You can use an external library to do the capitalization if you want to.
* The title attribute should not be normalized (it is excluded).
* Add unit-tests to the Normalization logic 

#### Example
* The value "red" becomes "Red" 
* The value "Blue" stays "Blue"
* The value "extra large" becomes "Extra large"

### Task 2 - Remove (filter) illegal values
Some attribute values are illegal and should be filtered out. 
* The list of illegal values is given in the file src/main/resources/application.properties under: "attribute-values.exclusion-list" property.
* If all the values of an attribute are removed - remove the attribute as well.
* Add unit-tests to the filter logic 

#### Examples
Assuming the values "N/a" and "As Seen" are filtered out.
* {"size": ["8"] , "color": ["N/a"] } -> { "size": ["8"] }
* {"size": ["8"] , "color": ["Blue","As Seen"] } -> { "size": ["8"], "color": ["Blue"] }

### Task 3 - Sort the attributes by the attribute name
We want to sort the attributes by their names (alphabetical - case sensitive). 

#### Example
* { "attachment":"wings" , "size": ["8"] , "Color": ["Blue"] } -> { "Color": ["Blue"]  , "attachment":"wings" , "size": ["8"] }

### Task 4 - Persist the processed products and fetch processed products
Provided is a product repository at src/main/java/com/vicmar57/productprocessor/repository/ProductRepository.java

You can autowire that interface and use it to persist and retrieve products.

* When the upsert product API is called, the product is processed and returned as before, but it is also persisted to the repository.
* Add a rest GET operation at "/product/{id}" that fetches processed products by their id.
* Make sure the integration test src/test/java/com/vicmar57/productprocessor/integration/GetProductTest.java works as expected.
