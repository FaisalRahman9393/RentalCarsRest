# A simple REST output server providing output in JSON

Note: This server uses the latest GlassFish (version 5)

# Return a response with names of all cars

A call http://localhost:8080/RentalCars/rest/ascendingbyprice returns a 
JSON with the names and prices(ascending).

# Return a response with SIPP specifcations of all cars

A call http://localhost:8080/RentalCars/rest/sipp returns a 
JSON containing the Name, SIPP, CarType, DoorType, Transmission, Fuel and AirCon of a car.


# Return a response with the rating of cars

A call http://localhost:8080/RentalCars/rest/supplierrating returns a 
JSON containing the Name, SIPP, CarType, DoorType, Transmission, Fuel and AirCon of a car.

# Return an overall rating of a car which combines the rating of the car as well as the rating of the car company

A call http://localhost:8080/RentalCars/rest/highsumratingdescending returns a 
JSON with the Name, VehicleScore, Rating, Sum(Descending) of a car.


This application was devloped as an excercise. For any issues, feel free to contact me at FaisalRahman9393@gmail.com
