package CensusAnalyzerProject

class CensusAnalyzer {

  def loadCSVData(filePath:String): Int ={
    try {
      val filereader = io.Source.fromFile(filePath)
      var countRows = 0
      for(line <- filereader.getLines()) {
        val cols = line.split(",").map(_.trim)
        countRows += 1
      }
      countRows - 1
    }
    catch {
      case ex:java.io.FileNotFoundException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnum.inCorrectFile)
      case ex:Exception => println(ex)
        -1
    }
  }
}
