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
        countRows += 1
      }
      countRows - 1
    }
    catch {
      case ex:java.io.FileNotFoundException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectPath)
    }
  }
}
