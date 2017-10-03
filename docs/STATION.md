# Station API

## description
...

## Endpoints

### Station by id

#### Request
```
GET /api/v1/station/{stationId}
example /api/v1/station/34117
```

#### parameters
* stationId
  * type: int
  * required: true

#### Response

```
Status: 200 
Content-Type: application/json

Body:
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
}

or

Status: 404
Content-Type: application/json

Body:
{
    "error": "id 34117 not found"
}
```

### Stations list filtered by country code

#### Request

/!\ pagination is not handled yet /!\

```
GET api/v1/station/?countryCode={countryCode}
example : api/v1/station/?countryCode=FR
```

```
Status: 200 
Content-Type: application/json

Body:
[
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
```
#### parameters
* countryCode
  * type: string
  * values: `FR` | `DE`
  * required: false