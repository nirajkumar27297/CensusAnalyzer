package CensusAnalyzerProject

class CensusAnalyzer {

  def loadCSVDataIndiaStateCensus(filePath:String): Int = {
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val fileReader = io.Source.fromFile(filePath)
      var countRows = 0
      var colsLength = 0
      for(line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)
        if(countRows == 0) {
          colsLength = cols.length
          if(cols(0).toLowerCase != "state" || cols(1).toLowerCase != "population" || cols(2).toLowerCase != "areainsqkm" || cols(3).toLowerCase != "densitypersqkm" ) {
            throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFields)
          }
        }
        if(cols.length != colsLength) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectDelimiter)
        }
        countRows += 1
      }
      countRows - 1
    }
    catch {
      case ex:java.io.FileNotFoundException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
    }
  }

  def loadCSVDataIndiaStateCode(filePath:String):Int = {
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val fileReader = io.Source.fromFile(filePath)
      var countRows = 0
      var colsLength = 0
      for(line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)
        if(countRows == 0) {
          colsLength = cols.length
          if(cols(1).toLowerCase != "state name" || cols(3).toLowerCase != "statecode") {
            throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFields)
          }
        }
        if(cols.length != colsLength) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectDelimiter)
        }
        countRows += 1
      }

      countRows - 1
    }
    catch {
      case ex:java.io.FileNotFoundException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
    }

  }
}
