## Steps to run
1. Execute `docker-compose up`
2. Run `DonationsApplication`
3. Run `BalanceApplication`
4. Visit:
   1. http://localhost:8080/donations/swagger-ui/index.html
   2. http://localhost:8090/balance/swagger-ui/index.html

## How to operate
1. In Donations (swagger) execute `POST /event` to create some event.
2. In Balance execute `GET /balance` to get balances.
3. In Donations execute `POST /event/donate` to donate some amount to event
4. Check `GET /balance` again, this should reflect donation.

### Curls
#### Create event
``
curl -X 'POST' \
'http://localhost:8080/donations/event' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d 'another-event'
``

returns
``
{
"id": 2,
"name": "another-event"
}
``
#### Check balance
``
curl -X 'GET' \
'http://localhost:8090/balance/balance' \
-H 'accept: */*'
``

returns 
``
[
{
"id": 1,
"balance": 84,
"name": "some-event"
},
{
"id": 2,
"balance": 0,
"name": "another-event"
}
]
``
#### Donation
``
curl -X 'POST' \
'http://localhost:8080/donations/event/donate' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"id": 2,
"amount": 99
}'
``

returns
``
{
"id": 3,
"amount": 99,
"event": {
"id": 2,
"name": null
}
}
``
#### New balance
``
curl -X 'GET' \
'http://localhost:8090/balance/balance' \
-H 'accept: */*'
``

returns 
``
[
{
"id": 1,
"balance": 84,
"name": "some-event"
},
{
"id": 2,
"balance": 99,
"name": "another-event"
}
]``

## Issues
1. Donation and Balance were commented out in `docker-compose.yml` due to startup dependency issue. This should be resolved with sth like `wait-for-it`.
2. `name.wilu.zch.cdc.balance.EventHandler.isEvent` and `name.wilu.zch.cdc.balance.EventHandler.canTake` should be a bit more sophisticated.
3. `name.wilu.zch.cdc.balance.EventService.addDonation` is unsafe.

## Remarks
This is just a fast prototype of CDC with debezium. Implementation is rough and has cavities but general idea was demonstrated.