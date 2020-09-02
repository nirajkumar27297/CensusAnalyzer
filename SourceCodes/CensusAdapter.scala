package CensusAnalyzerProject

import java.nio.file.{Files, Paths}
import java.util

import CensusAnalyzerProject.Country.Country

class CensusAdapter {
  def loadData[T](country: Country,filepaths: Seq[String]): Map[String, CensusDAO] = {
    try {
      for (filepath <- filepaths) {
        if (!filepath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
        }
      }
      var censusMap: Map[String, CensusDAO] = Map()
      val readerStateCensus = Files.newBufferedReader(Paths.get(filepaths(0)))
      val csvBuilderStateCensus = CSVBuilderFactory.createCSVBuilder()
      if (country.equals(Country.India)) {
        val censusCSVIteratorStateCensus: util.Iterator[IndiaStateCensusDTO] = csvBuilderStateCensus.getIterator(readerStateCensus, classOf[IndiaStateCensusDTO])
        censusCSVIteratorStateCensus.forEachRemaining { objDAO =>
          censusMap += (objDAO.state -> new CensusDAO(objDAO))
        }
      }
      else if(country.equals(Country.USA)) {
        val censusCSVIterator: util.Iterator[USCensusDTO] = csvBuilderStateCensus.getIterator(readerStateCensus, classOf[USCensusDTO])
        censusCSVIterator.forEachRemaining { objDAO =>
          censusMap += (objDAO.state -> new CensusDAO(objDAO))
        }
      }
      else {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.invalidCountry)
        }
      if(filepaths.length == 1) {
        return censusMap
      }
      loadStateCode(censusMap,filepaths(1):String)
    }
    catch {
      case _:java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
      case _:CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
      case _:java.lang.RuntimeException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
    }
  }
  private def loadStateCode(censusMap:Map[String, CensusDAO],filepath:String): Map[String, CensusDAO] = {
    try {
      val readerStateCode = Files.newBufferedReader(Paths.get(filepath))
      val csvBuilderStateCensus = CSVBuilderFactory.createCSVBuilder()
      val censusCSVIterator: util.Iterator[IndianStateCodeDTO] = csvBuilderStateCensus.getIterator(readerStateCode, classOf[IndianStateCodeDTO])
      var censusStateMap: Map[String, CensusDAO] = Map()
      censusCSVIterator.forEachRemaining { objDAO =>
        censusStateMap += (objDAO.stateName -> new CensusDAO(objDAO))
      }
      for (statenameCensus <- censusMap.keys; statename <- censusStateMap.keys; if (statename.equals(statenameCensus)) == true) {
        val censusData = censusMap(statenameCensus)
        censusData.stateCode = censusStateMap(statename).stateCode
      }
      censusMap
    }
    catch {
      case _: java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
      case _: CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
      case _: java.lang.RuntimeException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
    }
  }
}