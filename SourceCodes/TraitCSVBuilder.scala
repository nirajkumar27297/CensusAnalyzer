package CensusAnalyzerProject

import java.io.Reader
import java.util

@throws[CSVBuilderException]
trait TraitCSVBuilder {
  def getIterator[T] (reader: Reader, csvClass:Class[T]): util.Iterator[T]
  def getList[T] (reader: Reader, csvClass:Class[T]): util.List[T]
}