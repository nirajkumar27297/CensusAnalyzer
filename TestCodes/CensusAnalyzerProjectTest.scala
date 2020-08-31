import org.scalatest.FunSuite
import CensusAnalyzerProject.{CensusAnalyzer, CensusAnalyzerExceptionEnum}

class CensusAnalyzerProjectTest extends FunSuite{

  test("test_IndiaStateCensus_MatchingNumberOfRows_Input_CSVFileWithRightPath_ReturnNumberOfrows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv") == 29)
  }

  test("test_IndiaStateCensus_InputFilePathIsWrong_ReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
    val objCensus = new CensusAnalyzer()
    objCensus.loadCSVDataIndiaStateCensus("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_IndiaStateCensus_InputFileTypeIsWrong_ReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusData.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_IndiaStateCensus_InputFileDelimiterWrong_ReturnIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectDelimiter.toString)
  }

  test("test_IndiaStateCensus_InputFileFieldsWrong_ReturnIncorrectFieldsException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCensusDataWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFields.toString)
    }

  test("test_IndiaStateCode_MatchingNumberOfRows_Input_CSVFileWithRightPath_ReturnNumberOfrows"){
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadCSVDataIndiaStateCode("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv") == 37)
  }

  test("test_InputFilePathIsWrong_ReturnIncorrectFilePathException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCode("./src/scala/CensusAnalyzerProject/Resources/IndiaStateCode.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectPath.toString)
  }

  test("test_InputFileTypeIsWrong_ReturnIncorrectFileException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCode("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCode.txt")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFile.toString)
  }

  test("test_InputFileDelimiterWrong_ReturnIncorrectDelimiterException") {
    val thrown = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadCSVDataIndiaStateCode("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCodeWrongDelimiter.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectDelimiter.toString)
  }

  test("test_InputFileFieldsWrong_ReturnIncorrectFieldsException") {
    val objCensus = new CensusAnalyzer
    val thrown = intercept[Exception] {
      objCensus.loadCSVDataIndiaStateCensus("./src/main/scala/CensusAnalyzerProject/Resources/IndiaStateCodeWrongFields.csv")
    }
    assert(thrown.getMessage == CensusAnalyzerExceptionEnum.inCorrectFields.toString)
  }



}
