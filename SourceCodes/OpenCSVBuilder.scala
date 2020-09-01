package CensusAnalyzerProject

import java.io.Reader
import java.util

import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}

class OpenCSVBuilder[T] extends TraitCSVBuilder {
  @throws[CSVBuilderException]
  def getIterator[T](reader: Reader, csvClass:Class[T]): util.Iterator[T] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.iterator()
    }
    catch {
      case _: java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.unableToParse)
    }

  }


  def getCSVBean[T](reader: Reader, csvClass: Class[T]): CsvToBean[T] =  {
    try {
      val csvToBeanBuilder = new CsvToBeanBuilder[T](reader)
      csvToBeanBuilder.withType(csvClass)
      csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true)
      val csvToBean = csvToBeanBuilder.build()
      csvToBean
    }
    catch {
      case _:java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.unableToParse)
    }
  }

  def getList[T](reader: Reader, csvClass:Class[T]): util.List[T] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.parse()
    }
    catch {
      case _: java.lang.RuntimeException => throw new CSVBuilderException(CSVBuilderExceptionEnum.unableToParse)
    }
  }

}
