package CensusAnalyzerProject

class CensusAnalyzerException(message:CensusAnalyzerExceptionEnum.Value) extends Exception(message.toString) {}


object CensusAnalyzerExceptionEnum extends Enumeration {
  type CensusAnalyzerException = Value
  val inCorrectPath = Value("Incorrect Path Specified")
  val inCorrectFile = Value("Incorrect File Specified")
}
