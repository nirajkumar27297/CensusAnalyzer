package CensusAnalyzerProject
import java.nio.file.{Files, Paths}
import java.util

class CensusAnalyzer {

  def loadCSVDataIndiaStateCensus(filePath:String): Int = {
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      val censusCSVList = csvBuilder.getList(reader,classOf[IndiaStateCensus])
      censusCSVList.size()
    }
    catch {
      case _:java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
      case _:CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
    }
  }

  def loadCSVDataIndiaStateCode(filePath:String):Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val fileReader = Files.newBufferedReader(Paths.get(filePath))
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      val censusCSVList = csvBuilder.getList(fileReader,classOf[IndianStateCode])
      censusCSVList.size()
    }
    catch {
      case _: java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
      case _:CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
    }
  }



  def getCountRows[T](fileiterator: util.Iterator[T]):Int = {
    var countRows = 0
    while(fileiterator.hasNext()) {
      countRows += 1
      fileiterator.next()
    }
    countRows
  }
}
