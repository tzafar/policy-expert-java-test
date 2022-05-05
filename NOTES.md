# Notes
## Assumptions
- The product price is unique, hence used as an identifier. 

## Implementation
- The `ProductDiscountRepository` simulates products and discounts data coming from persistence layer.
- To add a new type of discount we need to implement `Discount` interface.
- To add an existing discount to a product use `ProductDiscountRepository` 

## Tests
- to run all tests use command `mvn test`
- Tests added for new components `BuyOneGetOneFree` and `DiscountCalculator`
- Existing tests `BasketTests` are refactored. 
