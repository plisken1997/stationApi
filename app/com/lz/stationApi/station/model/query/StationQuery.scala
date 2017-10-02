package com.lz.stationApi.station.model.query
import com.lz.stationApi.common.query.stationApiQuery
import com.lz.stationApi.station.model.entity.Station

trait StationQuery extends stationApiQuery[Station, Int]
