## Install project

### requirements 

* java >= 8
* scala >= 2.12
* sbt >= 0.13
* mongodb >= 3.4.4

### install

This is a simple [playframework 2.6.5](https://www.playframework.com/documentation/2.6.5/ScalaHome) application. Just run `sbt` to load all the dependencies.

## Run

### production

```
sbt run
```

If you want to use the in memory data providers, you should place all csv files into `resources/csv` folder.

@see `tests/com/lz/stationApi/station/service/StationCSVReaderSpec.scala` to have a description of the data loading process.

/!\ The mongodb configuration is not handled yet. /!\

## Test

```
sbt test
```

The tests file structure follow the application structure.
Only the domain and the application layers are tested for now.