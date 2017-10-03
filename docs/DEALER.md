# Dealer API

## description
...

## Endpoints

### Dealer with stations list

#### Request
```
GET /api/v1/dealer/{dealerId}/stations/
example /api/v1/dealer/1086/stations/
```

#### parameters
* dealerId
  * type: int
  * required: true

#### Response

```
Status: 200 
Content-Type: application/json

Body:
{
    id: 1086,
    name: "xxx",
    dealerType: 1,
    stations: [
        {
            id: 34117,
            dealerId: 61,
            countryCode: "FR",
            stationName: "...",
            latitude: 43.xxxxx,
            longitude: 5.xxxxx,
            address: "...",
            postalCode: "xxxxx",
            city: "..."
        },
        {
            id: 40028,
            dealerId: 32,
            countryCode: "FR",
            stationName: "...",
            latitude: 45.xxxxx,
            longitude: 6.xxxxx,
            address: "...",
            postalCode: "xxxxx",
            city: "..."
        }
    ]
 }

or

Status: 404
Content-Type: application/json

Body:
{
    error: "dealer 91086 not found"
}
```


### stations count by dealer

#### Request
```
GET /api/v1/dealer/countStations/?countryCode={countryCode}
example /api/v1/dealer/1086/stations/?countryCode=FR
```
#### parameters
* countryCode
  * type: string
  * values: `FR` | `DE`
  * required: false

#### Response
```
Status: 200 
Content-Type: application/json

Body:
[
    ["xxx", 8],
    ["yyy", 32],
    ["zzz", 10]
]

```