package CensusAnalyzerProject

class CensusAnalyzerException(message:CensusAnalyzerExceptionEnum.Value) extends Exception(message.toString) {}


object CensusAnalyzerExceptionEnum extends Enumeration {


  type CensusAnalyzerException = Value
  val inCorrectPath = Value("Incorrect Path Specified")
  val inCorrectFile = Value("Incorrect File Specified")
  val unableToParse = Value("Not able to Parse Invalid Delimiter or Fields")
  val noCensusData = Value("No Census Data Present")
  val invalidCountry = Value("The country name is Invalid")
}
