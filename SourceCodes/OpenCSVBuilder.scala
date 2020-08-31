package CensusAnalyzerProject

import java.io.Reader
import java.util

import com.opencsv.bean.CsvToBeanBuilder

class OpenCSVBuilder {
  def getIterator[T](reader: Reader, csvClass:Class[T]): util.Iterator[T] = {
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

}
