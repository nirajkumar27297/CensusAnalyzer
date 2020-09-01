package CensusAnalyzerProject
class CSVBuilderException(message:CSVBuilderExceptionEnum.Value) extends Exception(message.toString) {}


object CSVBuilderExceptionEnum extends Enumeration {
  type CSVBuilderException = Value
  val inCorrectPath = Value("Incorrect Path Specified")
  val inCorrectFile = Value("Incorrect File Specified")
  val unableToParse = Value("Not able to Parse Invalid Delimiter or Fields")
}

