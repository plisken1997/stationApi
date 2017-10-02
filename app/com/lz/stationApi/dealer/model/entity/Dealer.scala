package com.lz.stationApi.dealer.model.entity

case class Dealer(
                   id: DealerId,
                   name: DealerName,
                   dealerType: DealerType,
                   stations: Stations = Nil
                 )
