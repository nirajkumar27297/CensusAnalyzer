package CensusAnalyzerProject

import java.nio.file.{Files, Paths}
import java.util

class CensusLoader {
  def loadData[T](csvClass:Class[T],filepaths: String*): Map[String, IndiaStateCensusDAO] = {
    try {
      var censusMap: Map[String, IndiaStateCensusDAO] = Map()
      for (filepath <- filepaths) {
        if (!filepath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
        }
        val reader = Files.newBufferedReader(Paths.get(filepath))
        val csvBuilder = CSVBuilderFactory.createCSVBuilder()
        if (csvClass.getName == "CensusAnalyzerProject.IndiaStateCensus") {
          val censusCSVIterator: util.Iterator[IndiaStateCensus] = csvBuilder.getIterator(reader, classOf[IndiaStateCensus])
          censusCSVIterator.forEachRemaining { objDAO =>
            censusMap += (objDAO.state -> new IndiaStateCensusDAO(objDAO))
          }
      }
        else if (csvClass.getName == "CensusAnalyzerProject.IndianStateCode") {
          val censusCSVIterator: util.Iterator[IndianStateCode] = csvBuilder.getIterator(reader, classOf[IndianStateCode])
          censusCSVIterator.forEachRemaining { objDAO =>
            censusMap += (objDAO.stateName -> new IndiaStateCensusDAO(objDAO))
          }
        }
        else if(csvClass.getName == "CensusAnalyzerProject.USCensus") {
          val censusCSVIterator: util.Iterator[USCensus] = csvBuilder.getIterator(reader, classOf[USCensus])
          censusCSVIterator.forEachRemaining { objDAO =>
            censusMap += (objDAO.state -> new IndiaStateCensusDAO(objDAO))
          }
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