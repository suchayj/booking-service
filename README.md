# booking-service
Booking Service

This is first Microservice which provides results to user.

Steps to use

This service runs on 8100 port and Another [booking](https://github.com/suchayj/booking) service will run on 8080.

/checkAvailable API

Payloads
```javascript
url: http://localhost:8100/api/bookings/checkAvailable
{
 "containerSize" : 20,
 "containerType" : "REEFER",
 "origin" : "Southampton",
 "destination" : "Singapore",
 "quantity" : 4
}
```

```javascript
response:
{
    "available": true
}
```

/api/bookings API

```javascript
url: http://localhost:8100/api/bookings/
{
 "containerSize" : 20,
 "containerType" : "REEFER",
 "origin" : "Southampton",
 "destination" : "Singapore",
 "quantity" : 4
}
```

```javascript
response:
{
    "bookingRef": 957000002
}
```


[booking](https://github.com/suchayj/booking) will seed Intial Sample Data from AvailableDataSeeder class.