package CensusAnalyzerProject
import java.io.Reader
import java.nio.file.{Files, Paths}
import java.util

import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBeanBuilder
import CensusAnalyzerProject.IndiaStateCensus
class CensusAnalyzer {

  def loadCSVDataIndiaStateCensus(filePath:String): Int = {
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val censusCSVIterator = getIterator(reader,classOf[IndiaStateCensus])
      getCountRows(censusCSVIterator)
    }
    catch {
      case ex:java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
    }
  }

  def loadCSVDataIndiaStateCode(filePath:String):Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val fileReader = Files.newBufferedReader(Paths.get(filePath))
      var countRows = 0
      val censusCSVIterator = getIterator(fileReader,classOf[IndianStateCode])
      getCountRows(censusCSVIterator)
    }
    catch {
      case ex: java.nio.file.NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
    }
  }

  private def getIterator[T](reader: Reader, csvClass:Class[T]): util.Iterator[T] = {
    try {
        val csvToBeanBuilder = new CsvToBeanBuilder[T](reader)
        csvToBeanBuilder.withType(csvClass)
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true)
        val csvToBean = csvToBeanBuilder.build()
        csvToBean.iterator()
    }
    catch {
      case ex:java.lang.RuntimeException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.unableToParse)
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
