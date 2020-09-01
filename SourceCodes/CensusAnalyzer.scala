package CensusAnalyzerProject
import java.nio.file.{Files, Paths}
import java.util
import java.util.Comparator

import com.google.gson.Gson


class CensusAnalyzer {
  var censusCSVList:util.List[IndiaStateCensus] = null

  object AgeOrdering extends Ordering[IndiaStateCensus] {
    def compare(a:IndiaStateCensus, b:IndiaStateCensus) = a.state compare b.state
  }

  def sort(censusComparator: Comparator[IndiaStateCensus]) = {
    val n = censusCSVList.size()
    for (i <- 0 until n - 1) {
      for (j <- 0 until n - i - 1) {
        val census1 = censusCSVList.get(j)
        val census2 = censusCSVList.get(j+1)
        if (censusComparator.compare(census1,census2) > 0) {
          censusCSVList.set(j,census2)
          censusCSVList.set(j+1,census1)
        }
      }
    }
  }

  def getStateWiseSortedStatesData():String  = {
    if(censusCSVList == null || censusCSVList.size() == 0){
      throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.noCensusData)
    }
    val censusComparator = new Comparator[IndiaStateCensus] {
      override def compare(o1: IndiaStateCensus, o2: IndiaStateCensus): Int = {
        o1.state.compareTo(o2.state)
      }
    }
    sort(censusComparator)
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def loadCSVDataIndiaStateCensus(filePath:String): Int = {
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      censusCSVList = csvBuilder.getList(reader,classOf[IndiaStateCensus])
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
