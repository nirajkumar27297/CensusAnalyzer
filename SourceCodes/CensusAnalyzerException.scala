package CensusAnalyzerProject

class CensusAnalyzerException(message:CensusAnalyzerExceptionEnum.Value) extends Exception(message.toString) {}


object CensusAnalyzerExceptionEnum extends Enumeration {
  type CensusAnalyzerException = Value
  val inCorrectFile = Value("Incorrect File Given")

}
