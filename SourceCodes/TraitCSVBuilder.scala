package CensusAnalyzerProject

import java.io.Reader
import java.util

trait TraitCSVBuilder {
  def getIterator[T] (reader: Reader, csvClass:Class[T]): util.Iterator[T]
}