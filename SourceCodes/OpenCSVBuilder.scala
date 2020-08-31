package CensusAnalyzerProject

import java.io.Reader
import java.util

import com.opencsv.bean.CsvToBeanBuilder

class OpenCSVBuilder[T] extends TraitCSVBuilder {
  @throws[CSVBuilderException]
  def getIterator[T](reader: Reader, csvClass:Class[T]): util.Iterator[T] = {
    try {
      val csvToBeanBuilder = new CsvToBeanBuilder[T](reader)
      csvToBeanBuilder.withType(csvClass)
      csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true)
      val csvToBean = csvToBeanBuilder.build()
      csvToBean.iterator()
    }
    catch {
      case _:java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.unableToParse)
    }
  }
}
