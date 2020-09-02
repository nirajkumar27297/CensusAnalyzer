package CensusAnalyzerProject

import java.nio.file.{Files, Paths}
import java.util

class CensusAdapter {
  def loadData[T](filepaths: String*): Map[String, CensusDAO] = {
    try {
      for (filepath <- filepaths) {
        if (!filepath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
        }
      }
      var censusMap: Map[String, CensusDAO] = Map()
      val readerStateCensus = Files.newBufferedReader(Paths.get(filepaths(0)))
      val csvBuilderStateCensus = CSVBuilderFactory.createCSVBuilder()
      if (filepaths.length > 1) {
        val censusCSVIteratorStateCensus: util.Iterator[IndiaStateCensus] = csvBuilderStateCensus.getIterator(readerStateCensus, classOf[IndiaStateCensus])
        censusCSVIteratorStateCensus.forEachRemaining { objDAO =>
          censusMap += (objDAO.state -> new CensusDAO(objDAO))
        }
        val readerStateCode = Files.newBufferedReader(Paths.get(filepaths(1)))
        val censusCSVIterator: util.Iterator[IndianStateCode] = csvBuilderStateCensus.getIterator(readerStateCode, classOf[IndianStateCode])
        var censusStateMap: Map[String, CensusDAO] = Map()
        censusCSVIterator.forEachRemaining { objDAO =>
          censusStateMap += (objDAO.stateName -> new CensusDAO(objDAO))
        }
        for(statenameCensus <- censusMap.keys;statename <- censusStateMap.keys;if(statename.equals(statenameCensus)) == true) {
          val censusData = censusMap(statenameCensus)
          censusData.stateCode = censusStateMap(statename).stateCode
        }
      }
      else if(filepaths.length == 1){
          val censusCSVIterator: util.Iterator[USCensus] = csvBuilderStateCensus.getIterator(readerStateCensus, classOf[USCensus])
          censusCSVIterator.forEachRemaining { objDAO =>
            censusMap += (objDAO.state -> new CensusDAO(objDAO))
          }
      }
      censusMap
    }
    catch {
      case _:java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
      case _:CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
      case _:java.lang.RuntimeException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
    }
  }
}