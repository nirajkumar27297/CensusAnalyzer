package CensusAnalyzerProject

class CensusAnalyzer {

  def loadCSVData(filePath:String): Int ={
    try {
      if(!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      }
      val fileReader = io.Source.fromFile(filePath)
      var countRows = 0
      for(line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)
        if(cols.length != 4) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectDelimiter)
        }
        if(countRows == 0){
          if(cols(0).toLowerCase != "state" || cols(1).toLowerCase != "population" || cols(2).toLowerCase != "areainsqkm" || cols(3).toLowerCase != "densitypersqkm" ) {
            throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFields)
          }
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
