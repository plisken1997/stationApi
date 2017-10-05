package com.lz.stationApi.common.service

import java.nio.file.{Files, Paths}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

trait InputDataReader[U] {
  /**
    *
    * @param filepath
    * @param ifExists
    * @return
    */
  private def withAcceptedFile(filepath: String)(ifExists: () => List[U]): Future[List[U]] = {
    if (Files.isReadable(Paths.get(filepath))) Future(ifExists())
    else Future.failed(new RuntimeException(s"file $filepath does not exists"))
  }


  /**
    * used for small dataset
    *
    * @param filepath
    * @param separ
    * @param entityFactory
    * @return
    */
  def read(filepath: String, separ: String, entityFactory: CSVEntityFactory[U]): Future[List[U]] = withAcceptedFile(filepath) { () =>
    val bufferedSource = Source.fromFile(filepath)
    var entities: List[U] = Nil
    for (line <- bufferedSource.getLines.drop(1)) {
      // @todo handle errors !
      entityFactory.fromCSV(line.split(separ).map(_.trim)).map { e =>
        entities = entities ++ List(e)
      }
    }
    bufferedSource.close
    entities
  }
}
