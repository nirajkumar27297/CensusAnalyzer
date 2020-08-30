package CensusAnalyzerProject

class CensusAnalyzer {

  def loadCSVData(filePath:String): Unit ={
    try {
      println(filePath)
      val filereader = io.Source.fromFile(filePath)
      var countRows = 0
      for(line <- filereader.getLines()) {
        countRows += 1
      }
    }
    catch {
      case ex:Exception => println(ex)
    }
  }
}
